package com.ruoyi.cpwz.baseInfo.service;

import java.util.List;
import com.ruoyi.cpwz.baseInfo.domain.ChenMaterialInfo;

/**
 * 商品信息Service接口
 * 
 * @author zovi
 * @date 2020-04-01
 */
public interface IChenMaterialInfoService 
{
    /**
     * 查询商品信息
     * 
     * @param materialId 商品信息ID
     * @return 商品信息
     */
    public ChenMaterialInfo selectChenMaterialInfoById(Long materialId);

    /**
     * 查询商品信息列表
     * 
     * @param chenMaterialInfo 商品信息
     * @return 商品信息集合
     */
    public List<ChenMaterialInfo> selectChenMaterialInfoList(ChenMaterialInfo chenMaterialInfo);

    /**
     * 新增商品信息
     * 
     * @param chenMaterialInfo 商品信息
     * @return 结果
     */
    public int insertChenMaterialInfo(ChenMaterialInfo chenMaterialInfo);

    /**
     * 修改商品信息
     * 
     * @param chenMaterialInfo 商品信息
     * @return 结果
     */
    public int updateChenMaterialInfo(ChenMaterialInfo chenMaterialInfo);

    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChenMaterialInfoByIds(String ids);
    /**
     *查询商品信息
     *
     * @param meterialIds 需要查询的数据ID
     * @return 结果
     */
    public List<ChenMaterialInfo> selectChenMaterialInfoListByIds(String meterialIds,ChenMaterialInfo chenMaterialInfo);

    /**
     * 删除商品信息信息
     * 
     * @param materialId 商品信息ID
     * @return 结果
     */
    public int deleteChenMaterialInfoById(Long materialId);
}
