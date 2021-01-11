package com.ruoyi.cpwz.stockInOut.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ruoyi.cpwz.baseInfo.domain.ChenMaterialType;
import com.ruoyi.cpwz.baseInfo.service.IChenMaterialTypeService;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialInDetail;
import com.ruoyi.cpwz.stockInOut.service.IChenMaterialInDetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialInMain;
import com.ruoyi.cpwz.stockInOut.service.IChenMaterialInMainService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 入库信息Controller
 *
 * @author zovi
 * @date 2020-04-02
 */
@Controller
@RequestMapping("/cpwz/stockInMain")
public class ChenMaterialInMainController extends BaseController {
    private String prefix = "cpwz/stockInMain";

    @Autowired
    private IChenMaterialInMainService chenMaterialInMainService;
    @Autowired
    private IChenMaterialInDetailService chenMaterialInDetailService;
    @Autowired
    private IChenMaterialTypeService chenMaterialTypeService;

    private String IN_MAIN_CODE;

    @RequiresPermissions("cpwz:stockInMain:view")
    @GetMapping()

    public String stockInMain(ModelMap mmap) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowDate = sdf.format(new Date());
        IN_MAIN_CODE = "RK" + nowDate;
        mmap.put("stockInCode", IN_MAIN_CODE);
        return prefix + "/stockInMain";
    }

    /**
     * 查询入库信息列表
     */
    @RequiresPermissions("cpwz:stockInMain:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ChenMaterialInDetail chenMaterialInDetail) {
        startPage();
        chenMaterialInDetail = new ChenMaterialInDetail();
        chenMaterialInDetail.setInMainCode(IN_MAIN_CODE);
        List<ChenMaterialInDetail> list = chenMaterialInDetailService.selectChenMaterialInDetailList(chenMaterialInDetail);
        return getDataTable(list);
    }

    /**
     * 更新运费信息
     * @param inMainCode
     * @param carriage
     * @return
     */
    @GetMapping("/updateCarriage")
    @ResponseBody
    public AjaxResult updateCarriage(String inMainCode,String carriage) {
        return toAjax(chenMaterialInMainService.updateCarriage(inMainCode,carriage));
    }

    /**
     * 导出入库信息列表
     */
    @RequiresPermissions("cpwz:stockInMain:export")
    @Log(title = "入库信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChenMaterialInMain chenMaterialInMain) {
        List<ChenMaterialInMain> list = chenMaterialInMainService.selectChenMaterialInMainList(chenMaterialInMain);
        ExcelUtil<ChenMaterialInMain> util = new ExcelUtil<ChenMaterialInMain>(ChenMaterialInMain.class);
        return util.exportExcel(list, "stockInMain");
    }

    /**
     * 入库明细添加列表
     */
    @GetMapping("/add/{meterialIds}")
    public String add(@PathVariable("meterialIds") String meterialIds,ModelMap mmap) {
        ChenMaterialType type = new ChenMaterialType();
        //查询所有的商品类型
        List<ChenMaterialType> typeList = chenMaterialTypeService.selectChenMaterialTypeList(type);
        mmap.put("typeList", typeList);
        mmap.put("inMainCode", IN_MAIN_CODE);
        mmap.put("meterialIds", meterialIds);

        return prefix + "/add";
    }

    /**
     * 新增保存入库信息主单
     */
    @RequiresPermissions("cpwz:stockInMain:add")
    @Log(title = "入库信息", businessType = BusinessType.INSERT_OR_UPDATE)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ChenMaterialInMain chenMaterialInMain) {
        AjaxResult result = new AjaxResult();
        //查询是否已经存在
        String inMainCode = chenMaterialInMain.getInMainCode();
        ChenMaterialInMain chen = new ChenMaterialInMain();
        chen.setInMainCode(inMainCode);
        List<ChenMaterialInMain> list = chenMaterialInMainService.selectChenMaterialInMainList(chen);
        //已存在更新
        if(list.size() >0){
            result = toAjax(chenMaterialInMainService.updateChenMaterialInMain(chenMaterialInMain));
        //不存在插入
        }else{
            chenMaterialInMain.setCreateTime(new Date());
            result = toAjax(chenMaterialInMainService.insertChenMaterialInMain(chenMaterialInMain));
        }
        return result;
    }
    /**
     * 修改保存入库明细
     */
    @RequiresPermissions("cpwz:stockInDetail:edit")
    @Log(title = "入库明细", businessType = BusinessType.UPDATE)
    @PostMapping("/editDetail")
    @ResponseBody
    public JSONObject editSave(int taxRate, String tableData, double carriage, boolean saveToStockInfo,String inMainData)
    {
        //保存主单信息
        if(saveToStockInfo){
            ChenMaterialInMain bean = JSON.parseObject(inMainData, new TypeReference<ChenMaterialInMain>(){});
            this.addSave(bean);
        }
        List<ChenMaterialInDetail> list = JSON.parseArray(tableData, ChenMaterialInDetail.class);
        JSONObject result = new JSONObject();
        for(int i = 0;i < list.size();i++){
            AjaxResult ajaxResult = toAjax(chenMaterialInDetailService.updateChenMaterialInDetail(taxRate,list.get(i),carriage,saveToStockInfo));
            result.put("ajaxResult",ajaxResult);
        }
        //计算本单总的税费和总费用
        JSONObject calculate  = chenMaterialInDetailService.calculateAllTaxPriceAndTotalPrice(taxRate,list.get(0).getInMainCode());
        result.put("calculate",calculate);
        return result;
    }
    /**
     * 添加入库明细
     */
    @RequiresPermissions("cpwz:stockInDetail:add")
    @Log(title = "入库明细", businessType = BusinessType.INSERT)
    @PostMapping("/addDetail")
    @ResponseBody
    public AjaxResult addSave(String ids,String inMainCode)
    {
        int i = chenMaterialInDetailService.addChenMaterialInDetailByIds(ids,inMainCode);
        return toAjax(i);
    }
    /**
     * 删除入库明细信息
     */
    @RequiresPermissions("cpwz:stockInDetail:remove")
    @Log(title = "入库信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {

        return toAjax(chenMaterialInDetailService.deleteChenMaterialInDetailByIds(ids));
    }
}
