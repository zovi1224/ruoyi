package com.ruoyi.cpwz.stockInOut.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 出库信息对象 chen_material_out_main
 * 
 * @author zovi
 * @date 2020-04-02
 */
public class ChenMaterialOutMain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long outMainId;

    /** 出库单编码 */
    @Excel(name = "出库单编码")
    private String outMainCode;

    /** 运费 */
    @Excel(name = "运费")
    private BigDecimal carriage;

    /** 合计价格（毛） */
    @Excel(name = "合计价格", readConverterExp = "毛=")
    private BigDecimal totalPrice;

    /** 本单利润 */
    @Excel(name = "本单利润")
    private BigDecimal totalProfit;
    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;
    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 是否销账（0未销账 1已销账） */
    @Excel(name = "是否销账", readConverterExp = "0=未销账,1=已销账")
    private String pay;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setOutMainId(Long outMainId)
    {
        this.outMainId = outMainId;
    }

    public Long getOutMainId() 
    {
        return outMainId;
    }
    public void setOutMainCode(String outMainCode) 
    {
        this.outMainCode = outMainCode;
    }

    public String getOutMainCode() 
    {
        return outMainCode;
    }
    public void setCarriage(BigDecimal carriage) 
    {
        this.carriage = carriage;
    }

    public BigDecimal getCarriage() 
    {
        return carriage;
    }
    public void setTotalPrice(BigDecimal totalPrice) 
    {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPrice() 
    {
        return totalPrice;
    }
    public void setTotalProfit(BigDecimal totalProfit) 
    {
        this.totalProfit = totalProfit;
    }

    public BigDecimal getTotalProfit() 
    {
        return totalProfit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("outMainId", getOutMainId())
            .append("outMainCode", getOutMainCode())
            .append("createTime", getCreateTime())
            .append("carriage", getCarriage())
            .append("totalPrice", getTotalPrice())
            .append("totalProfit", getTotalProfit())
            .append("userId", getUserId())
            .toString();
    }
}
