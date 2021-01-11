package com.ruoyi.cpwz.baseInfo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cpwz.baseInfo.mapper.ChenMaterialTypeMapper;
import com.ruoyi.cpwz.baseInfo.domain.ChenMaterialType;
import com.ruoyi.cpwz.baseInfo.service.IChenMaterialTypeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 商品分类Service业务层处理
 * 
 * @author zovi
 * @date 2020-03-31
 */
@Service
public class ChenMaterialTypeServiceImpl implements IChenMaterialTypeService 
{
    @Autowired
    private ChenMaterialTypeMapper chenMaterialTypeMapper;

    /**
     * 查询商品分类
     * 
     * @param typeId 商品分类ID
     * @return 商品分类
     */
    @Override
    public ChenMaterialType selectChenMaterialTypeById(Long typeId)
    {
        return chenMaterialTypeMapper.selectChenMaterialTypeById(typeId);
    }

    /**
     * 查询商品分类列表
     * 
     * @param chenMaterialType 商品分类
     * @return 商品分类
     */
    @Override
    public List<ChenMaterialType> selectChenMaterialTypeList(ChenMaterialType chenMaterialType)
    {
        return chenMaterialTypeMapper.selectChenMaterialTypeList(chenMaterialType);
    }

    /**
     * 新增商品分类
     * 
     * @param chenMaterialType 商品分类
     * @return 结果
     */
    @Override
    public int insertChenMaterialType(ChenMaterialType chenMaterialType)
    {
        return chenMaterialTypeMapper.insertChenMaterialType(chenMaterialType);
    }

    /**
     * 修改商品分类
     * 
     * @param chenMaterialType 商品分类
     * @return 结果
     */
    @Override
    public int updateChenMaterialType(ChenMaterialType chenMaterialType)
    {
        return chenMaterialTypeMapper.updateChenMaterialType(chenMaterialType);
    }

    /**
     * 删除商品分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChenMaterialTypeByIds(String ids)
    {
        return chenMaterialTypeMapper.deleteChenMaterialTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品分类信息
     * 
     * @param typeId 商品分类ID
     * @return 结果
     */
    @Override
    public int deleteChenMaterialTypeById(Long typeId)
    {
        return chenMaterialTypeMapper.deleteChenMaterialTypeById(typeId);
    }

    @Override
    public List<Ztree> typeTreeData() {
        List<ChenMaterialType> typeList = chenMaterialTypeMapper.selectTypeAll();
        List<Ztree> ztrees = initZtree(typeList);
        return ztrees;
    }

    @Override
    public String checkTypeNameUnique(ChenMaterialType type) {
        Long typeId = StringUtils.isNull(type.getTypeId()) ? -1L : type.getTypeId();
        ChenMaterialType info = chenMaterialTypeMapper.checkTypeNameUnique(type.getTypeName(), type.getParentId());
        if (StringUtils.isNotNull(info) && info.getTypeId().longValue() != typeId.longValue())
        {
            return UserConstants.TYPE_NAME_NOT_UNIQUE;
        }
        return UserConstants.TYPE_NAME_UNIQUE;
    }


    public List<Ztree> initZtree(List<ChenMaterialType> typeList)
    {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (ChenMaterialType type : typeList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(type.getTypeId());
            ztree.setpId(type.getParentId());
            ztree.setName(transtypeName(type));
            ztree.setTitle(type.getParentName());

            ztrees.add(ztree);
        }
        return ztrees;
    }

    public String transtypeName(ChenMaterialType type)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(type.getTypeName());

        return sb.toString();
    }


}
