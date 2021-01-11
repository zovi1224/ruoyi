package com.ruoyi.cpwz.stockInOut.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialInDetail;
import com.ruoyi.cpwz.stockInOut.domain.ChenStockInfo;
import com.ruoyi.cpwz.stockInOut.service.IChenStockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cpwz.stockInOut.mapper.ChenMaterialOutDetailMapper;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialOutDetail;
import com.ruoyi.cpwz.stockInOut.service.IChenMaterialOutDetailService;
import com.ruoyi.common.core.text.Convert;

/**
 * 出库明细Service业务层处理
 * 
 * @author zovi
 * @date 2020-04-02
 */
@Service
public class ChenMaterialOutDetailServiceImpl implements IChenMaterialOutDetailService 
{
    @Autowired
    private ChenMaterialOutDetailMapper chenMaterialOutDetailMapper;
    @Autowired
    private IChenStockInfoService chenStockInfoService;

    /**
     * 查询出库明细
     * 
     * @param outDetailId 出库明细ID
     * @return 出库明细
     */
    @Override
    public ChenMaterialOutDetail selectChenMaterialOutDetailById(Long outDetailId)
    {
        return chenMaterialOutDetailMapper.selectChenMaterialOutDetailById(outDetailId);
    }

    /**
     * 查询出库明细列表
     * 
     * @param chenMaterialOutDetail 出库明细
     * @return 出库明细
     */
    @Override
    public List<ChenMaterialOutDetail> selectChenMaterialOutDetailList(ChenMaterialOutDetail chenMaterialOutDetail)
    {
        return chenMaterialOutDetailMapper.selectChenMaterialOutDetailList(chenMaterialOutDetail);
    }

    /**
     * 新增出库明细
     * 
     * @param chenMaterialOutDetail 出库明细
     * @return 结果
     */
    @Override
    public int insertChenMaterialOutDetail(ChenMaterialOutDetail chenMaterialOutDetail)
    {
        chenMaterialOutDetail.setCreateTime(DateUtils.getNowDate());
        return chenMaterialOutDetailMapper.insertChenMaterialOutDetail(chenMaterialOutDetail);
    }

    /**
     * 修改出库明细
     * 
     * @param chenMaterialOutDetail 出库明细
     * @return 结果
     */
    @Override
    public int updateChenMaterialOutDetail(ChenMaterialOutDetail chenMaterialOutDetail)
    {
        return chenMaterialOutDetailMapper.updateChenMaterialOutDetail(chenMaterialOutDetail);
    }

    /**
     * 删除出库明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChenMaterialOutDetailByIds(String ids)
    {
        return chenMaterialOutDetailMapper.deleteChenMaterialOutDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除出库明细信息
     * 
     * @param outDetailId 出库明细ID
     * @return 结果
     */
    @Override
    public int deleteChenMaterialOutDetailById(Long outDetailId)
    {
        return chenMaterialOutDetailMapper.deleteChenMaterialOutDetailById(outDetailId);
    }

    @Override
    public int addChenMaterialOutDetailByIds(String infoIds, String outMainCode) {
        String[] ids = Convert.toStrArray(infoIds);
        int result = 0;
        for (int i = 0; i < ids.length; i++) {
            ChenMaterialOutDetail detail = new ChenMaterialOutDetail();

            detail.setInfoId(Long.valueOf(ids[i]));
            detail.setCreateTime(new Date());
            detail.setOutMainCode(outMainCode);

            result = chenMaterialOutDetailMapper.insertChenMaterialOutDetail(detail);
        }

        return result;
    }
    /**
     * 修改入库明细
     *
     * @param chenMaterialOutDetail 入库明细
     * @return 结果
     */
    @Override
    public int updateChenMaterialOutDetail(ChenMaterialOutDetail chenMaterialOutDetail, double carriage,boolean saveToStockInfo) {
        //单价
        BigDecimal price = chenMaterialOutDetail.getPrice() == null ? new BigDecimal(0.00) : chenMaterialOutDetail.getPrice();
        //入库数量
        int inNum = chenMaterialOutDetail.getOutNum()== null ? 0 : chenMaterialOutDetail.getOutNum();
        //总价
        BigDecimal totalPrice = price.multiply(new BigDecimal(inNum)).setScale(2);
        chenMaterialOutDetail.setTotalPrice(totalPrice);
        //当前入库单插入明细条数
        int insertNum = chenMaterialOutDetailMapper.selectChenMaterialOutDetailByOutMainCode(chenMaterialOutDetail.getOutMainCode());
        BigDecimal carriageOne = new BigDecimal(carriage).divide(new BigDecimal(insertNum), 2, BigDecimal.ROUND_HALF_UP);
        chenMaterialOutDetail.setCarriage(carriageOne);
        //更新库存信息表
        if(saveToStockInfo){
            chenStockInfoService.updateChenStockInfoByOutDetail(chenMaterialOutDetail);
        }

        return chenMaterialOutDetailMapper.updateChenMaterialOutDetail(chenMaterialOutDetail);
    }

    /**
     * 计算本单总金额和利润
     * @param outMainCode
     * @return
     */
    @Override
    public JSONObject calculateAllTotalPriceAndProfitPrice(String outMainCode) {
        ChenMaterialOutDetail chenMaterialOutDetail = new ChenMaterialOutDetail();
        chenMaterialOutDetail.setOutMainCode(outMainCode);
        List<ChenMaterialOutDetail> list = chenMaterialOutDetailMapper.selectChenMaterialOutDetailList(chenMaterialOutDetail);
        BigDecimal totalPrice = new BigDecimal(0);
        BigDecimal profitPrice = new BigDecimal(0);
        for(ChenMaterialOutDetail detail:list){
            BigDecimal dtotal = detail.getTotalPrice()==null?new BigDecimal(0):detail.getTotalPrice();
            totalPrice = totalPrice.add(dtotal);
            //出库单价
            BigDecimal price = detail.getPrice();
            long infoId = detail.getInfoId();
            ChenStockInfo chenStockInfo= chenStockInfoService.selectChenStockInfoById(infoId);
            //当前商品成本价
            BigDecimal avgPrice = chenStockInfo.getAvgPrice();
            //运费
            BigDecimal carriage = detail.getCarriage()==null?new BigDecimal(0):detail.getCarriage();
            //当前商品利润价 = （出库单价-成本价-运费）*出库数量
            BigDecimal pcsProfit = (price.subtract(avgPrice).subtract(carriage)).multiply(new BigDecimal(detail.getOutNum()));
            //累加到本单利润
            profitPrice = profitPrice.add(pcsProfit);
        }
        JSONObject json = new JSONObject();
        json.put("totalPrice",totalPrice);
        json.put("profitPrice",profitPrice);
        return json;
    }
}
