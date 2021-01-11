package com.ruoyi.cpwz.stockInOut.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialInDetail;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialOutDetail;
import com.ruoyi.cpwz.stockInOut.mapper.ChenMaterialOutDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cpwz.stockInOut.mapper.ChenMaterialOutMainMapper;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialOutMain;
import com.ruoyi.cpwz.stockInOut.service.IChenMaterialOutMainService;
import com.ruoyi.common.core.text.Convert;

/**
 * 出库信息Service业务层处理
 *
 * @author zovi
 * @date 2020-04-02
 */
@Service
public class ChenMaterialOutMainServiceImpl implements IChenMaterialOutMainService {
    @Autowired
    private ChenMaterialOutMainMapper chenMaterialOutMainMapper;
    @Autowired
    private ChenMaterialOutDetailMapper chenMaterialOutDetailMapper;
    //已销账
    private String IS_PAY ="1";

    /**
     * 查询出库信息
     *
     * @param outMainId 出库信息ID
     * @return 出库信息
     */
    @Override
    public ChenMaterialOutMain selectChenMaterialOutMainById(Long outMainId) {
        return chenMaterialOutMainMapper.selectChenMaterialOutMainById(outMainId);
    }

    /**
     * 查询出库信息列表
     *
     * @param chenMaterialOutMain 出库信息
     * @return 出库信息
     */
    @Override
    public List<ChenMaterialOutMain> selectChenMaterialOutMainList(ChenMaterialOutMain chenMaterialOutMain) {
        return chenMaterialOutMainMapper.selectChenMaterialOutMainList(chenMaterialOutMain);
    }

    /**
     * 新增出库信息
     *
     * @param chenMaterialOutMain 出库信息
     * @return 结果
     */
    @Override
    public int insertChenMaterialOutMain(ChenMaterialOutMain chenMaterialOutMain) {
        chenMaterialOutMain.setCreateTime(DateUtils.getNowDate());
        return chenMaterialOutMainMapper.insertChenMaterialOutMain(chenMaterialOutMain);
    }

    /**
     * 修改出库信息
     *
     * @param chenMaterialOutMain 出库信息
     * @return 结果
     */
    @Override
    public int updateChenMaterialOutMain(ChenMaterialOutMain chenMaterialOutMain) {
        return chenMaterialOutMainMapper.updateChenMaterialOutMain(chenMaterialOutMain);
    }

    /**
     * 删除出库信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChenMaterialOutMainByIds(String ids) {
        return chenMaterialOutMainMapper.deleteChenMaterialOutMainByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除出库信息信息
     * 包括主单and明细
     *
     * @param outMainId 出库信息ID
     * @return 结果
     */
    @Override
    public int deleteChenMaterialOutMainById(Long outMainId) {
        ChenMaterialOutMain chenMaterialOutMain = chenMaterialOutMainMapper.selectChenMaterialOutMainById(outMainId);
        ChenMaterialOutDetail chenMaterialOutDetail = new ChenMaterialOutDetail();
        chenMaterialOutDetail.setOutMainCode(chenMaterialOutMain.getOutMainCode());
        List<ChenMaterialOutDetail> details = chenMaterialOutDetailMapper.selectChenMaterialOutDetailList(chenMaterialOutDetail);
        StringBuffer buffer = new StringBuffer();
        for(ChenMaterialOutDetail detail:details){
            buffer.append(detail.getOutDetailId()).append(",");
        }
        String ids = buffer.deleteCharAt(buffer.length() - 1).toString();
        //批量删除明细
        chenMaterialOutDetailMapper.deleteChenMaterialOutDetailByIds(Convert.toStrArray(ids));
        return chenMaterialOutMainMapper.deleteChenMaterialOutMainById(outMainId);
    }

    @Override
    public int updateCarriage(String outMainCode, String carriage) {

        int detailNum = chenMaterialOutDetailMapper.selectChenMaterialOutDetailByOutMainCode(outMainCode);
        BigDecimal avgCarriage = new BigDecimal(carriage);
        avgCarriage = avgCarriage.divide(new BigDecimal(detailNum), 2, BigDecimal.ROUND_HALF_UP);
        return chenMaterialOutDetailMapper.updateCarriageByOutMainCode(avgCarriage, outMainCode);

    }

    /**
     * 更新销账
     * @param outMainId
     * @return
     */
    @Override
    public int payNow(Long outMainId) {
        ChenMaterialOutMain chenMaterialOutMain = chenMaterialOutMainMapper.selectChenMaterialOutMainById(outMainId);
        chenMaterialOutMain.setPay(IS_PAY);
        return chenMaterialOutMainMapper.updateChenMaterialOutMain(chenMaterialOutMain);
    }
}
