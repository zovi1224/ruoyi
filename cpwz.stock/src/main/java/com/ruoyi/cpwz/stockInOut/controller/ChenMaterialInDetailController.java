package com.ruoyi.cpwz.stockInOut.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;

import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialInDetail;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialInMain;
import com.ruoyi.cpwz.stockInOut.service.IChenMaterialInDetailService;
import com.ruoyi.cpwz.stockInOut.service.IChenMaterialInMainService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 入库明细Controller
 *
 * @author zovi
 * @date 2020-04-02
 */
@Controller
@RequestMapping("/cpwz/stockInDetail")
public class ChenMaterialInDetailController extends BaseController {
    private String prefix = "cpwz/stockInDetail";

    @Autowired
    private IChenMaterialInDetailService chenMaterialInDetailService;
    @Autowired
    private IChenMaterialInMainService chenMaterialInMainService;

    @RequiresPermissions("cpwz:stockInDetail:view")
    @GetMapping()
    public String stockInDetail() {

        return prefix + "/stockInDetail";
    }

    /**
     * 查询入库主表列表
     */
    @RequiresPermissions("cpwz:stockInDetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ChenMaterialInMain chenMaterialInMain) {
        startPage();
        List<ChenMaterialInMain> list = chenMaterialInMainService.selectChenMaterialInMainList(chenMaterialInMain);
        return getDataTable(list);
    }

    /**
     * 根据主表id 查询明细列表
     *
     * @param id
     * @return
     */

    @RequiresPermissions("cpwz:stockInDetail:list")
    @RequestMapping(value = "/InDetailList")
    @ResponseBody
    public List<ChenMaterialInDetail> files(String id) {
        ChenMaterialInDetail chenMaterialInDetail = new ChenMaterialInDetail();
        chenMaterialInDetail.setInMainCode(id);
        return chenMaterialInDetailService.selectChenMaterialInDetailList(chenMaterialInDetail);
    }

    /**
     * 导出出库明细列表
     */
    @RequiresPermissions("cpwz:stockInDetail:export")
    @Log(title = "出库明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChenMaterialInDetail chenMaterialInDetail) {
        List<ChenMaterialInDetail> list = chenMaterialInDetailService.selectChenMaterialInDetailList(chenMaterialInDetail);
        ExcelUtil<ChenMaterialInDetail> util = new ExcelUtil<ChenMaterialInDetail>(ChenMaterialInDetail.class);
        return util.exportExcel(list, "stockInDetail");
    }


    /**
     * 删除出库单包括明细
     */
    @RequiresPermissions("cpwz:stockInDetail:remove")
    @Log(title = "出库明细", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long ids) {
        return toAjax(chenMaterialInMainService.deleteChenMaterialInMainById(ids));
    }

}
