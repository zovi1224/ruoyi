package com.ruoyi.cpwz.stockInOut.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.cpwz.baseInfo.domain.ChenMaterialInfo;
import com.ruoyi.cpwz.baseInfo.mapper.ChenMaterialInfoMapper;
import com.ruoyi.cpwz.stockInOut.domain.ChenStockInfo;
import com.ruoyi.cpwz.stockInOut.service.IChenStockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cpwz.stockInOut.mapper.ChenMaterialInDetailMapper;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialInDetail;
import com.ruoyi.cpwz.stockInOut.service.IChenMaterialInDetailService;
import com.ruoyi.common.core.text.Convert;

/**
 * 入库明细Service业务层处理
 *
 * @author zovi
 * @date 2020-04-02
 */
@Service
public class ChenMaterialInDetailServiceImpl implements IChenMaterialInDetailService {
    @Autowired
    private ChenMaterialInDetailMapper chenMaterialInDetailMapper;
    @Autowired
    private IChenStockInfoService chenStockInfoService;


    /**
     * 查询入库明细
     *
     * @param inDetailId 入库明细ID
     * @return 入库明细
     */
    @Override
    public ChenMaterialInDetail selectChenMaterialInDetailById(Long inDetailId) {
        return chenMaterialInDetailMapper.selectChenMaterialInDetailById(inDetailId);
    }

    /**
     * 查询入库明细列表
     *
     * @param chenMaterialInDetail 入库明细
     * @return 入库明细
     */
    @Override
    public List<ChenMaterialInDetail> selectChenMaterialInDetailList(ChenMaterialInDetail chenMaterialInDetail) {
        return chenMaterialInDetailMapper.selectChenMaterialInDetailList(chenMaterialInDetail);
    }

    /**
     * 新增入库明细
     *
     * @param chenMaterialInDetail 入库明细
     * @return 结果
     */
    @Override
    public int insertChenMaterialInDetail(ChenMaterialInDetail chenMaterialInDetail) {
        chenMaterialInDetail.setCreateTime(DateUtils.getNowDate());
        return chenMaterialInDetailMapper.insertChenMaterialInDetail(chenMaterialInDetail);
    }

    /**
     * 修改入库明细
     *
     * @param chenMaterialInDetail 入库明细
     * @return 结果
     */
    @Override
    public int updateChenMaterialInDetail(int taxRate, ChenMaterialInDetail chenMaterialInDetail, double carriage,boolean saveToStockInfo) {
        //单价
        BigDecimal price = chenMaterialInDetail.getPrice() == null ? new BigDecimal(0.00) : chenMaterialInDetail.getPrice();
        //入库数量
        int inNum = chenMaterialInDetail.getInNum() == null ? 0 : chenMaterialInDetail.getInNum();
        //总价
        BigDecimal totalPrice = price.multiply(new BigDecimal(inNum)).setScale(2);
        chenMaterialInDetail.setTotalPrice(totalPrice);
        //税费
        BigDecimal taxPrice = totalPrice.multiply(new BigDecimal(taxRate).divide(new BigDecimal(100))).setScale(2);
        chenMaterialInDetail.setTaxPrice(taxPrice);
        //运费 = 入库单总运费/当前入库单插入明细条数
        //当前入库单插入明细条数
        int insertNum = chenMaterialInDetailMapper.selectChenMaterialInDetailByInMainCode(chenMaterialInDetail.getInMainCode());
        BigDecimal carriageOne = new BigDecimal(carriage).divide(new BigDecimal(insertNum), 2, BigDecimal.ROUND_HALF_UP);
        chenMaterialInDetail.setCarriage(carriageOne);
        //更新库存信息表
        if(saveToStockInfo){
            chenStockInfoService.updateChenStockInfoByInDetail(chenMaterialInDetail);
        }

        return chenMaterialInDetailMapper.updateChenMaterialInDetail(chenMaterialInDetail);
    }

    /**
     * 删除入库明细对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChenMaterialInDetailByIds(String ids) {
        return chenMaterialInDetailMapper.deleteChenMaterialInDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 批量添加入库明细
     *
     * @param materialIds 需要添加的的商品ID
     * @return
     */
    @Override
    public int addChenMaterialInDetailByIds(String materialIds, String inMainCode) {
        String[] ids = Convert.toStrArray(materialIds);
        int result = 0;
        for (int i = 0; i < ids.length; i++) {
            ChenMaterialInDetail detail = new ChenMaterialInDetail();

            detail.setMaterialId(Long.valueOf(ids[i]));
            detail.setCreateTime(new Date());
            detail.setInMainCode(inMainCode);

            result = chenMaterialInDetailMapper.insertChenMaterialInDetail(detail);
        }

        return result;
    }

    @Override
    public int selectChenMaterialInDetailByInMainCode(String inMainCode) {
        return chenMaterialInDetailMapper.selectChenMaterialInDetailByInMainCode(inMainCode);
    }

    /**
     * 删除入库明细信息
     *
     * @param inDetailId 入库明细ID
     * @return 结果
     */
    @Override
    public int deleteChenMaterialInDetailById(Long inDetailId) {
        return chenMaterialInDetailMapper.deleteChenMaterialInDetailById(inDetailId);
    }

    @Override
    public JSONObject calculateAllTaxPriceAndTotalPrice(int taxRate, String inMainCode) {
        ChenMaterialInDetail chenMaterialInDetail = new ChenMaterialInDetail();
        chenMaterialInDetail.setInMainCode(inMainCode);
        List<ChenMaterialInDetail> list = chenMaterialInDetailMapper.selectChenMaterialInDetailList(chenMaterialInDetail);
        BigDecimal totalPrice = new BigDecimal(0);
        BigDecimal taxPrice = new BigDecimal(0);
        for(ChenMaterialInDetail detail:list){
            BigDecimal dtotal = detail.getTotalPrice()==null?new BigDecimal(0):detail.getTotalPrice();
            totalPrice = totalPrice.add(dtotal);
            taxPrice = taxPrice.add(detail.getTaxPrice()==null?new BigDecimal(0):detail.getTaxPrice());
        }
        totalPrice = totalPrice.add(taxPrice);
        JSONObject json = new JSONObject();
        json.put("totalPrice",totalPrice);
        json.put("taxPrice",taxPrice);
        return json;
    }
}
