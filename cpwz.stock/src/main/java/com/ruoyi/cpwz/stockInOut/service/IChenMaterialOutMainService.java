package com.ruoyi.cpwz.stockInOut.service;

import java.util.List;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialOutMain;

/**
 * 出库信息Service接口
 * 
 * @author zovi
 * @date 2020-04-02
 */
public interface IChenMaterialOutMainService 
{
    /**
     * 查询出库信息
     * 
     * @param outMainId 出库信息ID
     * @return 出库信息
     */
    public ChenMaterialOutMain selectChenMaterialOutMainById(Long outMainId);

    /**
     * 查询出库信息列表
     * 
     * @param chenMaterialOutMain 出库信息
     * @return 出库信息集合
     */
    public List<ChenMaterialOutMain> selectChenMaterialOutMainList(ChenMaterialOutMain chenMaterialOutMain);

    /**
     * 新增出库信息
     * 
     * @param chenMaterialOutMain 出库信息
     * @return 结果
     */
    public int insertChenMaterialOutMain(ChenMaterialOutMain chenMaterialOutMain);

    /**
     * 修改出库信息
     * 
     * @param chenMaterialOutMain 出库信息
     * @return 结果
     */
    public int updateChenMaterialOutMain(ChenMaterialOutMain chenMaterialOutMain);

    /**
     * 批量删除出库信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChenMaterialOutMainByIds(String ids);

    /**
     * 删除出库信息信息
     * 
     * @param outMainId 出库信息ID
     * @return 结果
     */
    public int deleteChenMaterialOutMainById(Long outMainId);
    /**
     * 更新运费信息
     * @return
     */
    public int updateCarriage(String outMainCode,String carriage);
    /**
     * 销账
     * @return
     */
    public int payNow(Long outMainId);
}
