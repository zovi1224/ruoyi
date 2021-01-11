package com.ruoyi.cpwz.stockInOut.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.cpwz.baseInfo.domain.ChenMaterialInfo;
import com.ruoyi.cpwz.baseInfo.mapper.ChenMaterialInfoMapper;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialInDetail;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialOutDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cpwz.stockInOut.mapper.ChenStockInfoMapper;
import com.ruoyi.cpwz.stockInOut.domain.ChenStockInfo;
import com.ruoyi.cpwz.stockInOut.service.IChenStockInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 库存信息Service业务层处理
 * 
 * @author zovi
 * @date 2020-04-02
 */
@Service
public class ChenStockInfoServiceImpl implements IChenStockInfoService 
{
    @Autowired
    private ChenStockInfoMapper chenStockInfoMapper;

    /**
     * 查询库存信息
     * 
     * @param infoId 库存信息ID
     * @return 库存信息
     */
    @Override
    public ChenStockInfo selectChenStockInfoById(Long infoId)
    {
        return chenStockInfoMapper.selectChenStockInfoById(infoId);
    }

    /**
     * 查询库存信息列表
     * 
     * @param chenStockInfo 库存信息
     * @return 库存信息
     */
    @Override
    public List<ChenStockInfo> selectChenStockInfoList(ChenStockInfo chenStockInfo,String infoIds)
    {
        return chenStockInfoMapper.selectChenStockInfoList(chenStockInfo,Convert.toStrArray(infoIds));
    }

    /**
     * 新增库存信息
     * 
     * @param chenStockInfo 库存信息
     * @return 结果
     */
    @Override
    public int insertChenStockInfo(ChenStockInfo chenStockInfo)
    {
        chenStockInfo.setCreateTime(DateUtils.getNowDate());
        return chenStockInfoMapper.insertChenStockInfo(chenStockInfo);
    }

    /**
     * 修改库存信息
     * 
     * @param chenStockInfo 库存信息
     * @return 结果
     */
    @Override
    public int updateChenStockInfo(ChenStockInfo chenStockInfo)
    {
        return chenStockInfoMapper.updateChenStockInfo(chenStockInfo);
    }

    /**
     * 删除库存信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChenStockInfoByIds(String ids)
    {
        return chenStockInfoMapper.deleteChenStockInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除库存信息信息
     * 
     * @param infoId 库存信息ID
     * @return 结果
     */
    @Override
    public int deleteChenStockInfoById(Long infoId)
    {
        return chenStockInfoMapper.deleteChenStockInfoById(infoId);
    }

    /**
     * 根据入库信息更新库存表
     * @param chenMaterialInDetail
     * @return
     */
    @Override
    public int updateChenStockInfoByInDetail(ChenMaterialInDetail chenMaterialInDetail) {
        //先查对应的商品库存信息表是否已经存在（根据商品id和仓库id进行查询）
        ChenStockInfo chenStockInfo = new ChenStockInfo();
        chenStockInfo.setStockId(chenMaterialInDetail.getStockId());
        chenStockInfo.setMaterialId(chenMaterialInDetail.getMaterialId());
        List<ChenStockInfo> infoList = this.selectChenStockInfoList(chenStockInfo,"");
        //若不存在，insert，若存在，库存+1，
        // 重新计算avgPrice（每个商品的成本单价：含税和运费）和
        //        totalPrice（库存价值：avgPrice*StockNum）
        //        lowPrice：直接取入库单手工维护的()
        int i = 0;

        //不存在
        if(infoList.size()<=0){
            int stockNum = chenMaterialInDetail.getInNum();
            chenStockInfo.setStockNumber(stockNum);
            chenStockInfo.setLowPrice(chenMaterialInDetail.getLowPrice());
            //avgPrice（每个商品的  单价+总税额/数量+运费/数量）
            BigDecimal avgPrice = chenMaterialInDetail.getPrice().add(
                    chenMaterialInDetail.getCarriage().divide(new BigDecimal(stockNum), 2, BigDecimal.ROUND_HALF_UP))
                    .add(chenMaterialInDetail.getTaxPrice().divide(new BigDecimal(stockNum), 2, BigDecimal.ROUND_HALF_UP));
            chenStockInfo.setAvgPrice(avgPrice);
            //totalPrice（库存价值：avgPrice*StockNum）
            chenStockInfo.setTotalPrice(avgPrice.multiply(new BigDecimal(stockNum)));
            chenStockInfo.setCreateTime(chenMaterialInDetail.getCreateTime());

            //插入
            i = chenStockInfoMapper.insertChenStockInfo(chenStockInfo);
            //存在（只会有一个）
        }else{
            ChenStockInfo oldStock = infoList.get(0);
            //新入库存
            int stockNum = chenMaterialInDetail.getInNum();
            //加上后库存总量
            int stock = chenMaterialInDetail.getInNum()+oldStock.getStockNumber();
            //更新库存
            oldStock.setStockNumber(stock);
            //重新计算avgPrice：旧的avgPrice + 新avgPrice(把当前商品的单独一个的运费和税费已经算进去) /2
            BigDecimal avgPrice = chenMaterialInDetail.getPrice()
                    .add(chenMaterialInDetail.getCarriage().divide(new BigDecimal(stockNum), 2, BigDecimal.ROUND_HALF_UP))
                    .add(chenMaterialInDetail.getTaxPrice().divide(new BigDecimal(stockNum), 2, BigDecimal.ROUND_HALF_UP));
            BigDecimal newAvgPrice = (avgPrice.add(oldStock.getAvgPrice())).divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP);
            //更新avgPrice
            oldStock.setAvgPrice(newAvgPrice);
            //更新TotalPrice
            oldStock.setTotalPrice(newAvgPrice.multiply(new BigDecimal(stock)));
            oldStock.setLowPrice(chenMaterialInDetail.getLowPrice().equals(new BigDecimal(0))?oldStock.getLowPrice():chenMaterialInDetail.getLowPrice());
            oldStock.setCreateTime(chenMaterialInDetail.getCreateTime());
            //更新数据库
            i = chenStockInfoMapper.updateChenStockInfo(oldStock);

        }

        return i;
    }

    /**
     * 根据出库明细更新库存表
     * @param chenMaterialOutDetail
     * @return
     */
    @Override
    public int updateChenStockInfoByOutDetail(ChenMaterialOutDetail chenMaterialOutDetail) {

        ChenStockInfo chenStockInfo = this.selectChenStockInfoById(chenMaterialOutDetail.getInfoId());
        //出库数量
        int outNum = chenMaterialOutDetail.getOutNum();
        //库存数量=本来的库存-出库数量
        int stockNum = chenStockInfo.getStockNumber()-outNum;
        //更新库存数量
        chenStockInfo.setStockNumber(stockNum);
        //重新计算totalPrice = avgPrice*stockNumber
        BigDecimal totalPrice = chenStockInfo.getAvgPrice().multiply(new BigDecimal(stockNum));
        chenStockInfo.setTotalPrice(totalPrice);
        return chenStockInfoMapper.updateChenStockInfo(chenStockInfo);
    }


}
