package com.ruoyi.cpwz.stockInOut.mapper;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialInDetail;
import org.apache.ibatis.annotations.Param;

/**
 * 入库明细Mapper接口
 * 
 * @author zovi
 * @date 2020-04-02
 */
public interface ChenMaterialInDetailMapper 
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
    public int updateChenMaterialInDetail(ChenMaterialInDetail chenMaterialInDetail);

    /**
     * 删除入库明细
     * 
     * @param inDetailId 入库明细ID
     * @return 结果
     */
    public int deleteChenMaterialInDetailById(Long inDetailId);

    /**
     * 批量删除入库明细
     * 
     * @param inDetailIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteChenMaterialInDetailByIds(String[] inDetailIds);

    /**
     * 查询对应 mainode明细行数
     * @param inMainCode
     * @return num
     */
    public Integer selectChenMaterialInDetailByInMainCode(String inMainCode);

    /**
     * 批量更新明细的运费
     * @param carriage
     * @param inMainCode
     * @return
     */
    public int updateCarriageByInMainCode(@Param("carriage") BigDecimal carriage, @Param("inMainCode") String inMainCode);
}
