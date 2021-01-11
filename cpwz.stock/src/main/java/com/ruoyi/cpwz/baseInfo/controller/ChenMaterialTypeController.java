package com.ruoyi.cpwz.baseInfo.controller;

import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.cpwz.baseInfo.service.IChenMaterialTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.cpwz.baseInfo.domain.ChenMaterialType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 商品分类Controller
 * 
 * @author zovi
 * @date 2020-03-31
 */
@Controller
@RequestMapping("/cpwz/type")
public class ChenMaterialTypeController extends BaseController
{
    private String prefix = "cpwz/type";

    @Autowired
    private IChenMaterialTypeService chenMaterialTypeService;

    @RequiresPermissions("cpwz:type:view")
    @GetMapping()
    public String type()
    {
        return prefix + "/type";
    }

    /**
     * 查询商品分类列表
     */
    @RequiresPermissions("cpwz:type:list")
    @PostMapping("/list")
    @ResponseBody
    public List<ChenMaterialType> list(ChenMaterialType chenMaterialType)
    {
        startPage();
        List<ChenMaterialType> list = chenMaterialTypeService.selectChenMaterialTypeList(chenMaterialType);
        return list;
    }

    /**
     * 导出商品分类列表
     */
    @RequiresPermissions("cpwz:type:export")
    @Log(title = "商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChenMaterialType chenMaterialType)
    {
        List<ChenMaterialType> list = chenMaterialTypeService.selectChenMaterialTypeList(chenMaterialType);
        ExcelUtil<ChenMaterialType> util = new ExcelUtil<ChenMaterialType>(ChenMaterialType.class);
        return util.exportExcel(list, "type");
    }

    /**
     * 新增商品分类
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
    {
        ChenMaterialType type = null;
        if (0L != parentId)
        {
            type = chenMaterialTypeService.selectChenMaterialTypeById(parentId);
        }
        else
        {
            type = new ChenMaterialType();
            type.setTypeId(0L);
            type.setTypeName("主分类");
        }
        mmap.put("type", type);
        return prefix + "/add";
    }

    /**
     * 新增保存商品分类
     */
    @RequiresPermissions("cpwz:type:add")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated ChenMaterialType chenMaterialType)
    {
        if (UserConstants.TYPE_NAME_NOT_UNIQUE.equals(chenMaterialTypeService.checkTypeNameUnique(chenMaterialType)))
        {
            return error("新增分类'" + chenMaterialType.getTypeName() + "'失败，分类名称已存在");
        }
        return toAjax(chenMaterialTypeService.insertChenMaterialType(chenMaterialType));
    }
    /**
     * 校验菜单名称
     */
    @PostMapping("/checkTypeNameUnique")
    @ResponseBody
    public String checkTypeNameUnique(ChenMaterialType chenMaterialType)
    {
        System.out.println(chenMaterialType);
        return chenMaterialTypeService.checkTypeNameUnique(chenMaterialType);
    }

    /**
     * 修改商品分类
     */
    @GetMapping("/edit/{typeId}")
    public String edit(@PathVariable("typeId") Long typeId, ModelMap mmap)
    {
        ChenMaterialType chenMaterialType = chenMaterialTypeService.selectChenMaterialTypeById(typeId);
        mmap.put("type", chenMaterialType);
        return prefix + "/edit";
    }

    /**
     * 修改保存商品分类
     */
    @RequiresPermissions("cpwz:type:edit")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ChenMaterialType chenMaterialType)
    {
        if (UserConstants.TYPE_NAME_NOT_UNIQUE.equals(chenMaterialTypeService.checkTypeNameUnique(chenMaterialType)))
        {
            return error("修改分类'" + chenMaterialType.getTypeName() + "'失败，分类名称已存在");
        }
        return toAjax(chenMaterialTypeService.updateChenMaterialType(chenMaterialType));
    }

    /**
     * 删除商品分类
     */
    @RequiresPermissions("cpwz:type:remove")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
    @GetMapping( "/remove/{typeId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("typeId") Long typeId)
    {
        return toAjax(chenMaterialTypeService.deleteChenMaterialTypeById(typeId));
    }
    /**
     * 加载所有菜单列表树
     */
    @GetMapping("/typeTreeData")
    @ResponseBody
    public List<Ztree> typeTreeData()
    {
        List<Ztree> ztrees = chenMaterialTypeService.typeTreeData();
        return ztrees;
    }

    /**
     * 选择树
     */
    @GetMapping("/selectTypeTree/{typeId}")
    public String selectMenuTree(@PathVariable("typeId") Long typeId, ModelMap mmap)
    {
        mmap.put("type", chenMaterialTypeService.selectChenMaterialTypeById(typeId));
        return prefix + "/tree";
    }
}
