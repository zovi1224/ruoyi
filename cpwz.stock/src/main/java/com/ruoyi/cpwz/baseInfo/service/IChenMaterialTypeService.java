package com.ruoyi.cpwz.baseInfo.service;

import java.util.List;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.cpwz.baseInfo.domain.ChenMaterialType;

/**
 * 商品分类Service接口
 * 
 * @author zovi
 * @date 2020-03-31
 */
public interface IChenMaterialTypeService 
{
    /**
     * 查询商品分类
     * 
     * @param typeId 商品分类ID
     * @return 商品分类
     */
    public ChenMaterialType selectChenMaterialTypeById(Long typeId);

    /**
     * 查询商品分类列表
     * 
     * @param chenMaterialType 商品分类
     * @return 商品分类集合
     */
    public List<ChenMaterialType> selectChenMaterialTypeList(ChenMaterialType chenMaterialType);

    /**
     * 新增商品分类
     * 
     * @param chenMaterialType 商品分类
     * @return 结果
     */
    public int insertChenMaterialType(ChenMaterialType chenMaterialType);

    /**
     * 修改商品分类
     * 
     * @param chenMaterialType 商品分类
     * @return 结果
     */
    public int updateChenMaterialType(ChenMaterialType chenMaterialType);

    /**
     * 批量删除商品分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChenMaterialTypeByIds(String ids);

    /**
     * 删除商品分类信息
     * 
     * @param typeId 商品分类ID
     * @return 结果
     */
    public int deleteChenMaterialTypeById(Long typeId);

    /**
     * 查询所有分类信息
     *
     * @return 分类列表
     */
    public List<Ztree> typeTreeData();
    /**
     * 校验名称是否唯一
     *
     * @param type 菜单信息
     * @return 结果
     */
    public String checkTypeNameUnique(ChenMaterialType type);

}
