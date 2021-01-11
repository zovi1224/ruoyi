package com.ruoyi.cpwz.stockInOut.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 出库明细对象 chen_material_out_detail
 *
 * @author zovi
 * @date 2020-04-02
 */
public class ChenMaterialOutDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long outDetailId;

    /**
     * 库存id
     */
    @Excel(name = "库存id")
    private Long infoId;
    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String materialName;
    /**
     * 规格型号
     */
    @Excel(name = "规格型号")
    private String materialModel;

    /**
     * 出库单编码
     */
    @Excel(name = "出库单编码")
    private String outMainCode;

    /**
     * 仓库
     */
    @Excel(name = "仓库")
    private String stockId;

    /**
     * 出库数量
     */
    @Excel(name = "出库数量")
    private Integer outNum;
    /**
     * 库存数量
     */
    @Excel(name = "库存数量")
    private Integer stockNumber;

    /**
     * 单价
     */
    @Excel(name = "单价")
    private BigDecimal price;
    /**
     * 最低售价
     */
    @Excel(name = "最低售价")
    private BigDecimal lowPrice;

    /**
     * 总价
     */
    @Excel(name = "总价")
    private BigDecimal totalPrice;

    /**
     * 运费
     */
    @Excel(name = "运费")
    private BigDecimal carriage;

    /**
     * 计量单位
     */
    @Excel(name = "计量单位")
    private String unit;

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialModel() {
        return materialModel;
    }

    public void setMaterialModel(String materialModel) {
        this.materialModel = materialModel;
    }

    public Integer getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(Integer stockNumber) {
        this.stockNumber = stockNumber;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public void setOutDetailId(Long outDetailId) {
        this.outDetailId = outDetailId;
    }

    public Long getOutDetailId() {
        return outDetailId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setOutMainCode(String outMainCode) {
        this.outMainCode = outMainCode;
    }

    public String getOutMainCode() {
        return outMainCode;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setOutNum(Integer outNum) {
        this.outNum = outNum;
    }

    public Integer getOutNum() {
        return outNum;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setCarriage(BigDecimal carriage) {
        this.carriage = carriage;
    }

    public BigDecimal getCarriage() {
        return carriage;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("outDetailId", getOutDetailId())
                .append("infoId", getInfoId())
                .append("outMainCode", getOutMainCode())
                .append("stockId", getStockId())
                .append("createTime", getCreateTime())
                .append("outNum", getOutNum())
                .append("price", getPrice())
                .append("totalPrice", getTotalPrice())
                .append("carriage", getCarriage())
                .append("unit", getUnit())
                .toString();
    }
}
