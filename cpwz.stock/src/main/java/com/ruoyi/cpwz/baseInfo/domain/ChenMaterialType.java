package com.ruoyi.cpwz.baseInfo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 商品分类对象 chen_material_type
 * 
 * @author zovi
 * @date 2020-03-31
 */
public class ChenMaterialType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品id */
    private Long typeId;

    /** 父产品id */
    @Excel(name = "父产品id")
    private Long parentId;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String typeName;

    /** 父菜单名称 */
    private String parentName;

    /** 产品状态（0正常 1停用） */
    @Excel(name = "产品状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }
    @NotBlank(message = "类型名称不能为空")
    @Size(min = 0, max = 50, message = "类型名称长度不能超过50个字符")
    public String getTypeName() 
    {
        return typeName;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public String getParentName()
    {
        return parentName;
    }

    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("typeId", getTypeId())
            .append("parentId", getParentId())
            .append("typeName", getTypeName())
            .append("remark", getRemark())
            .append("status", getStatus())
            .toString();
    }
}
