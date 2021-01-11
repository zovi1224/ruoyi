package com.ruoyi.cpwz.stockInOut.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 入库明细对象 chen_material_in_detail
 * 
 * @author zovi
 * @date 2020-04-02
 */
public class ChenMaterialInDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long inDetailId;

    /** 商品id */
    @Excel(name = "商品id")
    private Long materialId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String materialName;

    /** 入库单编码 */
    @Excel(name = "入库单编码")
    private String inMainCode;

    /** 仓库 */
    @Excel(name = "仓库")
    private String stockId;

    /** 入库数量 */
    @Excel(name = "入库数量")
    private Integer inNum;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 总价 */
    @Excel(name = "总价")
    private BigDecimal totalPrice;

    /** 税费 */
    @Excel(name = "税费")
    private BigDecimal taxPrice;

    /** 运费 */
    @Excel(name = "运费")
    private BigDecimal carriage;

    /** 最低售价 */
    @Excel(name = "最低售价")
    private BigDecimal lowPrice;

    /** 计量单位 */
    @Excel(name = "计量单位")
    private String unit;
    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }
    public void setInDetailId(Long inDetailId)
    {
        this.inDetailId = inDetailId;
    }

    public Long getInDetailId()
    {
        return inDetailId;
    }
    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setInMainCode(String inMainCode) 
    {
        this.inMainCode = inMainCode;
    }

    public String getInMainCode() 
    {
        return inMainCode;
    }
    public void setStockId(String stockId)
    {
        this.stockId = stockId;
    }

    public String getStockId()
    {
        return stockId;
    }
    public void setInNum(Integer inNum)
    {
        this.inNum = inNum;
    }

    public Integer getInNum()
    {
        return inNum;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setTotalPrice(BigDecimal totalPrice) 
    {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPrice() 
    {
        return totalPrice;
    }
    public void setTaxPrice(BigDecimal taxPrice) 
    {
        this.taxPrice = taxPrice;
    }

    public BigDecimal getTaxPrice() 
    {
        return taxPrice;
    }
    public void setCarriage(BigDecimal carriage) 
    {
        this.carriage = carriage;
    }

    public BigDecimal getCarriage() 
    {
        return carriage;
    }
    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("inDetailId", getInDetailId())
            .append("materialId", getMaterialId())
            .append("inMainCode", getInMainCode())
            .append("stockId", getStockId())
            .append("createTime", getCreateTime())
            .append("inNum", getInNum())
            .append("price", getPrice())
            .append("totalPrice", getTotalPrice())
            .append("taxPrice", getTaxPrice())
            .append("carriage", getCarriage())
            .append("unit", getUnit())
            .append("LowPrice", getLowPrice())
            .append("materialName", getMaterialName())
            .toString();
    }
}
