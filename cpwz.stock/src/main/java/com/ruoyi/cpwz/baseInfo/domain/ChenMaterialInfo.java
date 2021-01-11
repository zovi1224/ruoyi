package com.ruoyi.cpwz.baseInfo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 商品信息对象 chen_material_info
 * 
 * @author zovi
 * @date 2020-04-01
 */
public class ChenMaterialInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long materialId;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String materialCode;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String materialName;

    /** 商品类型(type表) */
    @Excel(name = "商品类型")
    private Long typeId;

    /** 商品类型 */
    @Excel(name = "商品类型名称")
    private String typeName;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String materialModel;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /** 计量单位 */
    @Excel(name = "计量单位")
    private String unit;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setMaterialCode(String materialCode) 
    {
        this.materialCode = materialCode;
    }

    public String getMaterialCode() 
    {
        return materialCode;
    }
    public void setMaterialName(String materialName) 
    {
        this.materialName = materialName;
    }

    public String getMaterialName() 
    {
        return materialName;
    }
    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }
    public void setMaterialModel(String materialModel) 
    {
        this.materialModel = materialModel;
    }

    public String getMaterialModel() 
    {
        return materialModel;
    }
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getCreateTime()
    {
        return createTime;
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
            .append("materialId", getMaterialId())
            .append("materialCode", getMaterialCode())
            .append("materialName", getMaterialName())
            .append("typeId", getTypeId())
            .append("materialModel", getMaterialModel())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("unit", getUnit())
            .toString();
    }
}
