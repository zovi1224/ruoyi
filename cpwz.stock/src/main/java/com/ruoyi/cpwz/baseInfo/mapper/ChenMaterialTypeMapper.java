package com.ruoyi.cpwz.baseInfo.mapper;

import java.util.List;

import com.ruoyi.cpwz.baseInfo.domain.ChenMaterialType;
import org.apache.ibatis.annotations.Param;

/**
 * 商品分类Mapper接口
 * 
 * @author zovi
 * @date 2020-03-31
 */
public interface ChenMaterialTypeMapper 
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
     * 删除商品分类
     * 
     * @param typeId 商品分类ID
     * @return 结果
     */
    public int deleteChenMaterialTypeById(Long typeId);

    /**
     * 批量删除商品分类
     * 
     * @param typeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteChenMaterialTypeByIds(String[] typeIds);

    /**
     * 查询所有商品分类
     * @return
     */
    public List<ChenMaterialType> selectTypeAll();
    /**
     * 校验名称是否唯一
     *
     * @param typeName 名称
     * @param parentId 父菜单ID
     * @return 结果
     */
    public ChenMaterialType checkTypeNameUnique(@Param("typeName") String typeName, @Param("parentId") Long parentId);

}
