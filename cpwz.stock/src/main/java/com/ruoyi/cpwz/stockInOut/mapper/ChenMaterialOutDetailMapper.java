package com.ruoyi.cpwz.stockInOut.mapper;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialOutDetail;
import org.apache.ibatis.annotations.Param;

/**
 * 出库明细Mapper接口
 * 
 * @author zovi
 * @date 2020-04-02
 */
public interface ChenMaterialOutDetailMapper 
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
     * 删除出库明细
     * 
     * @param outDetailId 出库明细ID
     * @return 结果
     */
    public int deleteChenMaterialOutDetailById(Long outDetailId);

    /**
     * 批量删除出库明细
     * 
     * @param outDetailIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteChenMaterialOutDetailByIds(String[] outDetailIds);
    /**
     * 查询对应 mainode明细行数
     * @param outMainCode
     * @return num
     */
    public Integer selectChenMaterialOutDetailByOutMainCode(String outMainCode);

    /**
     * 批量更新明细的运费
     * @param carriage
     * @param outMainCode
     * @return
     */
    public int updateCarriageByOutMainCode(@Param("carriage") BigDecimal carriage, @Param("outMainCode") String outMainCode);

}
