package com.ruoyi.cpwz.stockInOut.service;

import java.util.List;

import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialInDetail;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialOutDetail;
import com.ruoyi.cpwz.stockInOut.domain.ChenStockInfo;

/**
 * 库存信息Service接口
 * 
 * @author zovi
 * @date 2020-04-02
 */
public interface IChenStockInfoService 
{
    /**
     * 查询库存信息
     * 
     * @param infoId 库存信息ID
     * @return 库存信息
     */
    public ChenStockInfo selectChenStockInfoById(Long infoId);

    /**
     * 查询库存信息列表
     * 
     * @param chenStockInfo 库存信息
     * @return 库存信息集合
     */
    public List<ChenStockInfo> selectChenStockInfoList(ChenStockInfo chenStockInfo,String infoIds);

    /**
     * 新增库存信息
     * 
     * @param chenStockInfo 库存信息
     * @return 结果
     */
    public int insertChenStockInfo(ChenStockInfo chenStockInfo);

    /**
     * 修改库存信息
     * 
     * @param chenStockInfo 库存信息
     * @return 结果
     */
    public int updateChenStockInfo(ChenStockInfo chenStockInfo);

    /**
     * 批量删除库存信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChenStockInfoByIds(String ids);

    /**
     * 删除库存信息信息
     * 
     * @param infoId 库存信息ID
     * @return 结果
     */
    public int deleteChenStockInfoById(Long infoId);

    /**
     * 根据入库明细更新库存信息
     * @param chenMaterialInDetail
     * @return
     */

    public int updateChenStockInfoByInDetail(ChenMaterialInDetail chenMaterialInDetail);

    /**
     * 根据出库明细更新库存信息
     * @param chenMaterialOutDetail
     * @return
     */
    public int updateChenStockInfoByOutDetail(ChenMaterialOutDetail chenMaterialOutDetail);
}
