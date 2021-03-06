package com.ruoyi.cpwz.stockInOut.service;

import java.util.List;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialInMain;

/**
 * 入库信息Service接口
 * 
 * @author zovi
 * @date 2020-04-02
 */
public interface IChenMaterialInMainService 
{
    /**
     * 查询入库信息
     * 
     * @param inMainId 入库信息ID
     * @return 入库信息
     */
    public ChenMaterialInMain selectChenMaterialInMainById(Long inMainId);

    /**
     * 查询入库信息列表
     * 
     * @param chenMaterialInMain 入库信息
     * @return 入库信息集合
     */
    public List<ChenMaterialInMain> selectChenMaterialInMainList(ChenMaterialInMain chenMaterialInMain);

    /**
     * 新增入库信息
     * 
     * @param chenMaterialInMain 入库信息
     * @return 结果
     */
    public int insertChenMaterialInMain(ChenMaterialInMain chenMaterialInMain);

    /**
     * 修改入库信息
     * 
     * @param chenMaterialInMain 入库信息
     * @return 结果
     */
    public int updateChenMaterialInMain(ChenMaterialInMain chenMaterialInMain);

    /**
     * 批量删除入库信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChenMaterialInMainByIds(String ids);

    /**
     * 删除入库信息信息
     * 
     * @param inMainId 入库信息ID
     * @return 结果
     */
    public int deleteChenMaterialInMainById(Long inMainId);

    /**
     * 更新运费信息
     * @return
     */
    public int updateCarriage(String inMainCode,String carriage);
}
