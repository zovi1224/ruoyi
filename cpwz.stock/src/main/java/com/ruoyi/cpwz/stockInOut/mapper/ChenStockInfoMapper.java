package com.ruoyi.cpwz.stockInOut.mapper;

import java.util.List;
import com.ruoyi.cpwz.stockInOut.domain.ChenStockInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 库存信息Mapper接口
 * 
 * @author zovi
 * @date 2020-04-02
 */
public interface ChenStockInfoMapper 
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
    public List<ChenStockInfo> selectChenStockInfoList(@Param("chenStockInfo") ChenStockInfo chenStockInfo, @Param("infoIds") String[] infoIds);

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
     * 删除库存信息
     * 
     * @param infoId 库存信息ID
     * @return 结果
     */
    public int deleteChenStockInfoById(Long infoId);

    /**
     * 批量删除库存信息
     * 
     * @param infoIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteChenStockInfoByIds(String[] infoIds);
}
