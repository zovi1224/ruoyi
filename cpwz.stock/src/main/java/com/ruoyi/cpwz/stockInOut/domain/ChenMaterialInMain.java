package com.ruoyi.cpwz.stockInOut.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 入库信息对象 chen_material_in_main
 * 
 * @author zovi
 * @date 2020-04-02
 */
public class ChenMaterialInMain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long inMainId;

    /** 入库单编码 */
    @Excel(name = "入库单编码")
    private String inMainCode;

    /** 运费 */
    @Excel(name = "运费")
    private BigDecimal carriage;

    /** 税率 */
    @Excel(name = "税率")
    private String taxRate;

    /** 税费 */
    @Excel(name = "税费")
    private BigDecimal taxPrice;

    /** 合计 */
    @Excel(name = "合计")
    private BigDecimal totalPrice;

    public void setInMainId(Long inMainId) 
    {
        this.inMainId = inMainId;
    }

    public Long getInMainId() 
    {
        return inMainId;
    }
    public void setInMainCode(String inMainCode) 
    {
        this.inMainCode = inMainCode;
    }

    public String getInMainCode() 
    {
        return inMainCode;
    }
    public void setCarriage(BigDecimal carriage) 
    {
        this.carriage = carriage;
    }

    public BigDecimal getCarriage() 
    {
        return carriage;
    }
    public void setTaxRate(String taxRate) 
    {
        this.taxRate = taxRate;
    }

    public String getTaxRate() 
    {
        return taxRate;
    }
    public void setTaxPrice(BigDecimal taxPrice) 
    {
        this.taxPrice = taxPrice;
    }

    public BigDecimal getTaxPrice() 
    {
        return taxPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) 
    {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPrice() 
    {
        return totalPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("inMainId", getInMainId())
            .append("inMainCode", getInMainCode())
            .append("createTime", getCreateTime())
            .append("carriage", getCarriage())
            .append("taxRate", getTaxRate())
            .append("taxPrice", getTaxPrice())
            .append("totalPrice", getTotalPrice())
            .toString();
    }
}
