package com.ruoyi.cpwz.baseInfo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cpwz.baseInfo.mapper.ChenMaterialInfoMapper;
import com.ruoyi.cpwz.baseInfo.domain.ChenMaterialInfo;
import com.ruoyi.cpwz.baseInfo.service.IChenMaterialInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 商品信息Service业务层处理
 * 
 * @author zovi
 * @date 2020-04-01
 */
@Service
public class ChenMaterialInfoServiceImpl implements IChenMaterialInfoService 
{
    @Autowired
    private ChenMaterialInfoMapper chenMaterialInfoMapper;

    /**
     * 查询商品信息
     * 
     * @param materialId 商品信息ID
     * @return 商品信息
     */
    @Override
    public ChenMaterialInfo selectChenMaterialInfoById(Long materialId)
    {
        return chenMaterialInfoMapper.selectChenMaterialInfoById(materialId);
    }

    /**
     * 查询商品信息列表
     * 
     * @param chenMaterialInfo 商品信息
     * @return 商品信息
     */
    @Override
    public List<ChenMaterialInfo> selectChenMaterialInfoList(ChenMaterialInfo chenMaterialInfo)
    {
        return chenMaterialInfoMapper.selectChenMaterialInfoList(chenMaterialInfo);
    }

    /**
     * 新增商品信息
     * 
     * @param chenMaterialInfo 商品信息
     * @return 结果
     */
    @Override
    public int insertChenMaterialInfo(ChenMaterialInfo chenMaterialInfo)
    {
        return chenMaterialInfoMapper.insertChenMaterialInfo(chenMaterialInfo);
    }

    /**
     * 修改商品信息
     * 
     * @param chenMaterialInfo 商品信息
     * @return 结果
     */
    @Override
    public int updateChenMaterialInfo(ChenMaterialInfo chenMaterialInfo)
    {
        return chenMaterialInfoMapper.updateChenMaterialInfo(chenMaterialInfo);
    }

    /**
     * 删除商品信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChenMaterialInfoByIds(String ids)
    {
        return chenMaterialInfoMapper.deleteChenMaterialInfoByIds(Convert.toStrArray(ids));
    }
    /**
     *查询商品信息
     *
     * @param meterialIds 需要查询的数据ID
     * @return 结果
     */
    @Override
    public List<ChenMaterialInfo> selectChenMaterialInfoListByIds(String meterialIds,ChenMaterialInfo chenMaterialInfo) {
        return chenMaterialInfoMapper.selectChenMaterialInfoByIds(Convert.toStrArray(meterialIds),chenMaterialInfo);
    }

    /**
     * 删除商品信息信息
     * 
     * @param materialId 商品信息ID
     * @return 结果
     */
    @Override
    public int deleteChenMaterialInfoById(Long materialId)
    {
        return chenMaterialInfoMapper.deleteChenMaterialInfoById(materialId);
    }
}
