package com.ruoyi.cpwz.web.controller.cpwz;

import java.util.List;

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
import com.ruoyi.cpwz.stockInOut.domain.ChenStockInfo;
import com.ruoyi.cpwz.stockInOut.service.IChenStockInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 库存信息Controller
 *
 * @author zovi
 * @date 2020-04-02
 */
@Controller
@RequestMapping("/cpwz/stockInfo")
public class ChenStockInfoController extends BaseController {
    private String prefix = "cpwz/stockInfo";

    @Autowired
    private IChenStockInfoService chenStockInfoService;

    @RequiresPermissions("cpwz:stockInfo:view")
    @GetMapping()
    public String stockInfo() {
        return prefix + "/stockInfo";
    }

    /**
     * 查询库存信息列表
     */
    @RequiresPermissions("cpwz:stockInfo:list")
    @PostMapping("/list/{infoIds}")
    @ResponseBody
    public TableDataInfo list(ChenStockInfo chenStockInfo,@PathVariable("infoIds") String infoIds){
        startPage();
        if (chenStockInfo.getStockId().equals("")){
            chenStockInfo.setStockId(null);
        };
        if(!"".equals(infoIds)){

        }
        List<ChenStockInfo> list = chenStockInfoService.selectChenStockInfoList(chenStockInfo,infoIds);
        return getDataTable(list);
    }

    /**
     * 导出库存信息列表
     */
//    @RequiresPermissions("cpwz:stockInfo:export")
    @Log(title = "库存信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChenStockInfo chenStockInfo) {
        if (chenStockInfo.getStockId().equals("")){
            chenStockInfo.setStockId(null);
        };
        List<ChenStockInfo> list = chenStockInfoService.selectChenStockInfoList(chenStockInfo,"");
        ExcelUtil<ChenStockInfo> util = new ExcelUtil<ChenStockInfo>(ChenStockInfo.class);
        return util.exportExcel(list, "stockInfo");
    }

    /**
     * 新增库存信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存库存信息
     */
    @RequiresPermissions("cpwz:stockInfo:add")
    @Log(title = "库存信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ChenStockInfo chenStockInfo) {
        return toAjax(chenStockInfoService.insertChenStockInfo(chenStockInfo));
    }

    /**
     * 修改库存信息
     */
    @GetMapping("/edit/{infoId}")
    public String edit(@PathVariable("infoId") Long infoId, ModelMap mmap) {
        ChenStockInfo chenStockInfo = chenStockInfoService.selectChenStockInfoById(infoId);
        mmap.put("chenStockInfo", chenStockInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存库存信息
     */
    @RequiresPermissions("cpwz:stockInfo:edit")
    @Log(title = "库存信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ChenStockInfo chenStockInfo) {
        return toAjax(chenStockInfoService.updateChenStockInfo(chenStockInfo));
    }

    /**
     * 删除库存信息
     */
    @RequiresPermissions("cpwz:stockInfo:remove")
    @Log(title = "库存信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(chenStockInfoService.deleteChenStockInfoByIds(ids));
    }
}
