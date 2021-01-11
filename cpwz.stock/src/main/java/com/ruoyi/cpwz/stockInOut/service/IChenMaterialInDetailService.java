package com.ruoyi.cpwz.stockInOut.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialInDetail;

/**
 * 入库明细Service接口
 * 
 * @author zovi
 * @date 2020-04-02
 */
public interface IChenMaterialInDetailService 
{
    /**
     * 查询入库明细
     * 
     * @param inDetailId 入库明细ID
     * @return 入库明细
     */
    public ChenMaterialInDetail selectChenMaterialInDetailById(Long inDetailId);

    /**
     * 查询入库明细列表
     * 
     * @param chenMaterialInDetail 入库明细
     * @return 入库明细集合
     */
    public List<ChenMaterialInDetail> selectChenMaterialInDetailList(ChenMaterialInDetail chenMaterialInDetail);

    /**
     * 新增入库明细
     * 
     * @param chenMaterialInDetail 入库明细
     * @return 结果
     */
    public int insertChenMaterialInDetail(ChenMaterialInDetail chenMaterialInDetail);

    /**
     * 修改入库明细
     * 
     * @param chenMaterialInDetail 入库明细
     * @return 结果
     */
    public int updateChenMaterialInDetail(int taxRate,ChenMaterialInDetail chenMaterialInDetail,double carriage,boolean saveToStockInfo);

    /**
     * 批量删除入库明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChenMaterialInDetailByIds(String ids);
    /**
     * 批量添加入库明细
     *
     * @param materialIds 需要添加的的商品ID
     * @return 结果
     */
    public int addChenMaterialInDetailByIds(String materialIds,String inMainCode);

    /**
     * 查询对应mainode明细行数
     * @param inMainCode
     * @return
     */
    public int selectChenMaterialInDetailByInMainCode(String inMainCode);

    /**
     * 删除入库明细信息
     * 
     * @param inDetailId 入库明细ID
     * @return 结果
     */
    public int deleteChenMaterialInDetailById(Long inDetailId);

    /**
     * 计算本单总的税费和总费用
     * @param taxRate
     * @param inMainCode
     * @return
     */
    public JSONObject calculateAllTaxPriceAndTotalPrice(int taxRate, String inMainCode);
}
