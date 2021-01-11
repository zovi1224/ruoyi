package com.ruoyi.cpwz.stockInOut.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialOutMain;
import com.ruoyi.cpwz.stockInOut.service.IChenMaterialOutMainService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialOutDetail;
import com.ruoyi.cpwz.stockInOut.service.IChenMaterialOutDetailService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 出库明细Controller
 * 
 * @author zovi
 * @date 2020-04-02
 */
@Controller
@RequestMapping("/cpwz/stockOutdetail")
public class ChenMaterialOutDetailController extends BaseController
{
    private String prefix = "cpwz/stockOutdetail";

    @Autowired
    private IChenMaterialOutDetailService chenMaterialOutDetailService;
    @Autowired
    private IChenMaterialOutMainService chenMaterialOutMainService;
    @Autowired
    private ISysUserService userService;

    @RequiresPermissions("cpwz:stockOutdetail:view")
    @GetMapping()
    public String stockOutdetail(ModelMap mmap)
    {
        //用户下拉框数据
        SysUser user = new SysUser();
        long deptId = 102;
        user.setDeptId(deptId);
        user.setParentId(deptId);
        List<SysUser> userList = userService.selectUserList(user);
        mmap.put("userList", userList);
        return prefix + "/stockOutdetail";
    }

    /**
     * 查询出库主表列表
     */
    @RequiresPermissions("cpwz:stockOutdetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ChenMaterialOutMain chenMaterialOutMain)
    {
        startPage();
        List<ChenMaterialOutMain> list = chenMaterialOutMainService.selectChenMaterialOutMainList(chenMaterialOutMain);
        return getDataTable(list);
    }

    /**
     * 根据主表id 查询明细列表
     * @param id
     * @return
     */

    @RequiresPermissions("cpwz:stockOutdetail:list")
    @RequestMapping(value="/outDetailList")
    @ResponseBody
    public List<ChenMaterialOutDetail> files(String id){
        ChenMaterialOutDetail chenMaterialOutDetail = new ChenMaterialOutDetail();
        chenMaterialOutDetail.setOutMainCode(id);
        return chenMaterialOutDetailService.selectChenMaterialOutDetailList(chenMaterialOutDetail);
    }

    /**
     * 导出出库明细列表
     */
    @RequiresPermissions("cpwz:stockOutdetail:export")
    @Log(title = "出库明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChenMaterialOutDetail chenMaterialOutDetail)
    {
        List<ChenMaterialOutDetail> list = chenMaterialOutDetailService.selectChenMaterialOutDetailList(chenMaterialOutDetail);
        ExcelUtil<ChenMaterialOutDetail> util = new ExcelUtil<ChenMaterialOutDetail>(ChenMaterialOutDetail.class);
        return util.exportExcel(list, "stockOutdetail");
    }

    /**
     * 修改出库明细
     */
    @GetMapping("/edit/{outDetailId}")
    public String edit(@PathVariable("outDetailId") Long outDetailId, ModelMap mmap)
    {
        ChenMaterialOutDetail chenMaterialOutDetail = chenMaterialOutDetailService.selectChenMaterialOutDetailById(outDetailId);
        mmap.put("chenMaterialOutDetail", chenMaterialOutDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存出库明细
     */
    @RequiresPermissions("cpwz:stockOutdetail:edit")
    @Log(title = "出库明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ChenMaterialOutDetail chenMaterialOutDetail)
    {
        return toAjax(chenMaterialOutDetailService.updateChenMaterialOutDetail(chenMaterialOutDetail));
    }

    /**
     * 删除出库单包括明细
     */
    @RequiresPermissions("cpwz:stockOutdetail:remove")
    @Log(title = "出库明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(Long ids)
    {
        return toAjax(chenMaterialOutMainService.deleteChenMaterialOutMainById(ids));
    }

    @RequiresPermissions("cpwz:stockOutdetail:edit")
    @Log(title = "更新出库主单", businessType = BusinessType.UPDATE)
    @GetMapping("/payNow")
    @ResponseBody
    public AjaxResult payNow(Long outMainId){
        return toAjax(chenMaterialOutMainService.payNow(outMainId));

    }
}
