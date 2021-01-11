package com.ruoyi.cpwz.stockInOut.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 库存信息对象 chen_stock_info
 *
 * @author zovi
 * @date 2020-04-02
 */
public class ChenStockInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long infoId;

    /**
     * 商品名称
     */
//    @Excel(name = "商品名称")
    private Long materialId;


    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String materialName;

    /**
     * 类型id
     */
//    @Excel(name = "类型id")
    private Long typeId;

    /**
     * 类型名称
     */
    @Excel(name = "类型名称")
    private String typeName;


    /**
     * 规格型号
     */
    @Excel(name = "规格型号")
    private String materialModel;

    /**
     * 仓库
     */
    @Excel(name = "仓库")
    private String stockId;

    /**
     * 库存数量
     */
    @Excel(name = "库存数量")
    private Integer stockNumber;

    /**
     * 平均单价
     */
    @Excel(name = "平均单价")
    private BigDecimal avgPrice;

    /**
     * 库存总价
     */
    @Excel(name = "库存总价")
    private BigDecimal totalPrice;

    /**
     * 最低售价
     */
    @Excel(name = "最低售价")
    private BigDecimal lowPrice;

    /**
     * 计量单位
     */
    @Excel(name = "计量单位")
    private String unit;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMaterialModel() {
        return materialModel;
    }

    public void setMaterialModel(String materialModel) {
        this.materialModel = materialModel;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockNumber(Integer stockNumber) {
        this.stockNumber = stockNumber;
    }

    public Integer getStockNumber() {
        return stockNumber;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
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
                .append("infoId", getInfoId())
                .append("materialId", getMaterialId())
                .append("stockId", getStockId())
                .append("createTime", getCreateTime())
                .append("stockNumber", getStockNumber())
                .append("avgPrice", getAvgPrice())
                .append("totalPrice", getTotalPrice())
                .append("lowPrice", getLowPrice())
                .toString();
    }
}
