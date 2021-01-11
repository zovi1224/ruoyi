package com.ruoyi.cpwz.stockInOut.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialInDetail;
import com.ruoyi.cpwz.stockInOut.mapper.ChenMaterialInDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cpwz.stockInOut.mapper.ChenMaterialInMainMapper;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialInMain;
import com.ruoyi.cpwz.stockInOut.service.IChenMaterialInMainService;
import com.ruoyi.common.core.text.Convert;

/**
 * 入库信息Service业务层处理
 *
 * @author zovi
 * @date 2020-04-02
 */
@Service
public class ChenMaterialInMainServiceImpl implements IChenMaterialInMainService {
    @Autowired
    private ChenMaterialInMainMapper chenMaterialInMainMapper;
    @Autowired
    private ChenMaterialInDetailMapper chenMaterialInDetailMapper;

    /**
     * 查询入库信息
     *
     * @param inMainId 入库信息ID
     * @return 入库信息
     */
    @Override
    public ChenMaterialInMain selectChenMaterialInMainById(Long inMainId) {
        return chenMaterialInMainMapper.selectChenMaterialInMainById(inMainId);
    }

    /**
     * 查询入库信息列表
     *
     * @param chenMaterialInMain 入库信息
     * @return 入库信息
     */
    @Override
    public List<ChenMaterialInMain> selectChenMaterialInMainList(ChenMaterialInMain chenMaterialInMain) {
        return chenMaterialInMainMapper.selectChenMaterialInMainList(chenMaterialInMain);
    }

    /**
     * 新增入库信息
     *
     * @param chenMaterialInMain 入库信息
     * @return 结果
     */
    @Override
    public int insertChenMaterialInMain(ChenMaterialInMain chenMaterialInMain) {
        chenMaterialInMain.setCreateTime(DateUtils.getNowDate());
        return chenMaterialInMainMapper.insertChenMaterialInMain(chenMaterialInMain);
    }

    /**
     * 修改入库信息
     *
     * @param chenMaterialInMain 入库信息
     * @return 结果
     */
    @Override
    public int updateChenMaterialInMain(ChenMaterialInMain chenMaterialInMain) {
        return chenMaterialInMainMapper.updateChenMaterialInMain(chenMaterialInMain);
    }

    /**
     * 删除入库信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChenMaterialInMainByIds(String ids) {
        return chenMaterialInMainMapper.deleteChenMaterialInMainByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除入库信息信息
     *
     * @param inMainId 入库信息ID
     * @return 结果
     */
    @Override
    public int deleteChenMaterialInMainById(Long inMainId) {
        return chenMaterialInMainMapper.deleteChenMaterialInMainById(inMainId);
    }

    @Override
    public int updateCarriage(String inMainCode, String carriage) {

        int detailNum = chenMaterialInDetailMapper.selectChenMaterialInDetailByInMainCode(inMainCode);
        BigDecimal avgCarriage = new BigDecimal(carriage);
        avgCarriage = avgCarriage.divide(new BigDecimal(detailNum),2,BigDecimal.ROUND_HALF_UP);
        chenMaterialInDetailMapper.updateCarriageByInMainCode(avgCarriage,inMainCode);
        return 0;
    }
}
