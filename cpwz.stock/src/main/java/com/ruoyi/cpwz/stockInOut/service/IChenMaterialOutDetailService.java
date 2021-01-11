package com.ruoyi.cpwz.stockInOut.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialOutDetail;

/**
 * 出库明细Service接口
 * 
 * @author zovi
 * @date 2020-04-02
 */
public interface IChenMaterialOutDetailService 
{
    /**
     * 查询出库明细
     * 
     * @param outDetailId 出库明细ID
     * @return 出库明细
     */
    public ChenMaterialOutDetail selectChenMaterialOutDetailById(Long outDetailId);

    /**
     * 查询出库明细列表
     * 
     * @param chenMaterialOutDetail 出库明细
     * @return 出库明细集合
     */
    public List<ChenMaterialOutDetail> selectChenMaterialOutDetailList(ChenMaterialOutDetail chenMaterialOutDetail);

    /**
     * 新增出库明细
     * 
     * @param chenMaterialOutDetail 出库明细
     * @return 结果
     */
    public int insertChenMaterialOutDetail(ChenMaterialOutDetail chenMaterialOutDetail);

    /**
     * 修改出库明细
     * 
     * @param chenMaterialOutDetail 出库明细
     * @return 结果
     */
    public int updateChenMaterialOutDetail(ChenMaterialOutDetail chenMaterialOutDetail);

    /**
     * 批量删除出库明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChenMaterialOutDetailByIds(String ids);

    /**
     * 删除出库明细信息
     * 
     * @param outDetailId 出库明细ID
     * @return 结果
     */
    public int deleteChenMaterialOutDetailById(Long outDetailId);

    /**
     * 批量添加入库明细
     *
     * @param materialIds 需要添加的的商品ID
     * @return 结果
     */
    public int addChenMaterialOutDetailByIds(String materialIds,String inMainCode);
    public JSONObject calculateAllTotalPriceAndProfitPrice(String outMainCode);
    public int updateChenMaterialOutDetail(ChenMaterialOutDetail chenMaterialOutDetail, double carriage,boolean saveToStockInfo);

}
