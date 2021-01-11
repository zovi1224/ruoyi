package com.ruoyi.cpwz.baseInfo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.cpwz.baseInfo.domain.ChenMaterialType;
import com.ruoyi.cpwz.baseInfo.service.IChenMaterialInfoService;
import com.ruoyi.cpwz.baseInfo.service.IChenMaterialTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.cpwz.baseInfo.domain.ChenMaterialInfo;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品信息Controller
 * 
 * @author zovi
 * @date 2020-04-01
 */
@Controller
@RequestMapping("/cpwz/info")
public class ChenMaterialInfoController extends BaseController
{
    private String prefix = "cpwz/info";

    @Autowired
    private IChenMaterialInfoService chenMaterialInfoService;
    @Autowired
    private IChenMaterialTypeService chenMaterialTypeService;

    @RequiresPermissions("cpwz:info:view")
    @GetMapping()
    public String info(ModelMap model)
    {
        //查询所有的商品类型
        ChenMaterialType type = new ChenMaterialType();
        List<ChenMaterialType> typeList = chenMaterialTypeService.selectChenMaterialTypeList(type);
        model.put("typeList",typeList);
        return prefix + "/info";
    }

    /**
     * 查询商品信息列表
     */
    @RequiresPermissions("cpwz:info:list")
    @PostMapping("/list/{meterialIds}")
    @ResponseBody
    public TableDataInfo list(ChenMaterialInfo chenMaterialInfo,@PathVariable("meterialIds") String meterialIds)
    {
        startPage();
        List<ChenMaterialInfo> list = new ArrayList<>();
        if(meterialIds==""||meterialIds==null|| "null".equals(meterialIds)){
            list = chenMaterialInfoService.selectChenMaterialInfoList(chenMaterialInfo);
        }else{
            list = chenMaterialInfoService.selectChenMaterialInfoListByIds(meterialIds,chenMaterialInfo);
        }

        return getDataTable(list);
    }

    /**
     * 导出商品信息列表
     */
    @RequiresPermissions("cpwz:info:export")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChenMaterialInfo chenMaterialInfo)
    {
        List<ChenMaterialInfo> list = chenMaterialInfoService.selectChenMaterialInfoList(chenMaterialInfo);
        ExcelUtil<ChenMaterialInfo> util = new ExcelUtil<ChenMaterialInfo>(ChenMaterialInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 新增商品信息
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        ChenMaterialType type = null;
            type = new ChenMaterialType();
            type.setTypeId(0L);
            type.setTypeName("主分类");
        mmap.put("type", type);
        return prefix + "/add";
    }

    /**
     * 新增保存商品信息
     */
    @RequiresPermissions("cpwz:info:add")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ChenMaterialInfo chenMaterialInfo)
    {
        chenMaterialInfo.setCreateTime(new Date());
        return toAjax(chenMaterialInfoService.insertChenMaterialInfo(chenMaterialInfo));
    }

    /**
     * 修改商品信息
     */
    @GetMapping("/edit/{materialId}")
    public String edit(@PathVariable("materialId") Long materialId, ModelMap mmap)
    {
        ChenMaterialInfo chenMaterialInfo = chenMaterialInfoService.selectChenMaterialInfoById(materialId);
        mmap.put("chenMaterialInfo", chenMaterialInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存商品信息
     */
    @RequiresPermissions("cpwz:info:edit")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ChenMaterialInfo chenMaterialInfo)
    {
        return toAjax(chenMaterialInfoService.updateChenMaterialInfo(chenMaterialInfo));
    }

    /**
     * 删除商品信息
     */
    @RequiresPermissions("cpwz:info:remove")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(chenMaterialInfoService.deleteChenMaterialInfoByIds(ids));
    }
}
