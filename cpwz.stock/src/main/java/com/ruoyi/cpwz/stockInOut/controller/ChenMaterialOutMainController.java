package com.ruoyi.cpwz.stockInOut.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialInDetail;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialInMain;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialOutDetail;
import com.ruoyi.cpwz.stockInOut.service.IChenMaterialOutDetailService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.cpwz.stockInOut.domain.ChenMaterialOutMain;
import com.ruoyi.cpwz.stockInOut.service.IChenMaterialOutMainService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 出库信息Controller
 *
 * @author zovi
 * @date 2020-04-02
 */
@Controller
@RequestMapping("/cpwz/stockOutMain")
public class ChenMaterialOutMainController extends BaseController {
    private String prefix = "cpwz/stockOutMain";
    private String OUT_MAIN_CODE;


    @Autowired
    private ISysUserService userService;
    @Autowired
    private IChenMaterialOutMainService chenMaterialOutMainService;
    @Autowired
    private IChenMaterialOutDetailService chenMaterialOutDetailService;

    @RequiresPermissions("cpwz:stockOutMain:view")
    @GetMapping()
    public String stockOutMain(ModelMap mmap) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowDate = sdf.format(new Date());
        OUT_MAIN_CODE = "CK" + nowDate;
        //用户下拉框数据
        SysUser user = new SysUser();
        long deptId = 102;
        user.setDeptId(deptId);
        user.setParentId(deptId);
        List<SysUser> userList = userService.selectUserList(user);
        mmap.put("userList", userList);
        mmap.put("outMainCode", OUT_MAIN_CODE);
        return prefix + "/stockOutMain";
    }

    /**
     * 添加入库明细
     */
    @RequiresPermissions("cpwz:stockOutDetail:add")
    @Log(title = "出库明细", businessType = BusinessType.INSERT)
    @PostMapping("/addDetail")
    @ResponseBody
    public AjaxResult addSave(String ids,String outMainCode)
    {
        int i = chenMaterialOutDetailService.addChenMaterialOutDetailByIds(ids,outMainCode);
        return toAjax(i);
    }
    /**
     * 更新运费信息
     * @param outMainCode
     * @param carriage
     * @return
     */
    @GetMapping("/updateCarriage")
    @ResponseBody
    public AjaxResult updateCarriage(String outMainCode,String carriage) {
        return toAjax(chenMaterialOutMainService.updateCarriage(outMainCode,carriage));
    }

    /**
     * 查询出库信息列表
     */
    @RequiresPermissions("cpwz:stockOutMain:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list() {

        startPage();

        ChenMaterialOutDetail chenMaterialOutDetail = new ChenMaterialOutDetail();
        chenMaterialOutDetail.setOutMainCode(OUT_MAIN_CODE);
        List<ChenMaterialOutDetail> list = chenMaterialOutDetailService.selectChenMaterialOutDetailList(chenMaterialOutDetail);

        return getDataTable(list);
    }

    /**
     * 导出出库信息列表
     */
    @RequiresPermissions("cpwz:stockOutMain:export")
    @Log(title = "出库信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChenMaterialOutMain chenMaterialOutMain) {
        List<ChenMaterialOutMain> list = chenMaterialOutMainService.selectChenMaterialOutMainList(chenMaterialOutMain);
        ExcelUtil<ChenMaterialOutMain> util = new ExcelUtil<ChenMaterialOutMain>(ChenMaterialOutMain.class);
        return util.exportExcel(list, "stockOutMain");
    }

    /**
     * 新增保存出库信息主单
     */
    @RequiresPermissions("cpwz:stockOutMain:add")
    @Log(title = "出库信息", businessType = BusinessType.INSERT_OR_UPDATE)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSaveMain(ChenMaterialOutMain chenMaterialOutMain) {
        AjaxResult result = new AjaxResult();
        //查询是否已经存在
        String outMainCode = chenMaterialOutMain.getOutMainCode();
        ChenMaterialOutMain chen = new ChenMaterialOutMain();
        chen.setOutMainCode(outMainCode);
        List<ChenMaterialOutMain> list = chenMaterialOutMainService.selectChenMaterialOutMainList(chen);
        //已存在更新
        if(list.size() >0){
            result = toAjax(chenMaterialOutMainService.updateChenMaterialOutMain(chenMaterialOutMain));
            //不存在插入
        }else{
            chenMaterialOutMain.setCreateTime(new Date());
            result = toAjax(chenMaterialOutMainService.insertChenMaterialOutMain(chenMaterialOutMain));
        }
        return result;
    }

    /**
     * 修改保存出库明细
     */
    @RequiresPermissions("cpwz:stockOutDetail:edit")
    @Log(title = "出库明细", businessType = BusinessType.UPDATE)
    @PostMapping("/editDetail")
    @ResponseBody
    public JSONObject editSave(String tableData, double carriage, boolean saveToStockInfo, String outMainData)
    {
        //保存主单信息
        if(saveToStockInfo){
            ChenMaterialOutMain bean = JSON.parseObject(outMainData, new TypeReference<ChenMaterialOutMain>(){});
            this.addSaveMain(bean);
        }
        List<ChenMaterialOutDetail> list = JSON.parseArray(tableData, ChenMaterialOutDetail.class);
        JSONObject result = new JSONObject();
        for(int i = 0;i < list.size();i++){
            AjaxResult ajaxResult = toAjax(chenMaterialOutDetailService.updateChenMaterialOutDetail(list.get(i),carriage,saveToStockInfo));
            result.put("ajaxResult",ajaxResult);
        }
        //计算本单总的税费和总费用
        JSONObject calculate  = chenMaterialOutDetailService.calculateAllTotalPriceAndProfitPrice(list.get(0).getOutMainCode());
        result.put("calculate",calculate);
        return result;
    }


    /**
     * 新增出库信息
     */
    @GetMapping("/add/{infoId}")
    public String add(@PathVariable("infoId") String infoIds, ModelMap mmap) {
        //新增返回库存信息的页面
        mmap.put("outMainCode", OUT_MAIN_CODE);
        mmap.put("infoIds", infoIds);
        return "cpwz/stockInfo/stockInfo";
    }

    /**
     * 修改出库信息
     */
    @GetMapping("/edit/{outMainId}")
    public String edit(@PathVariable("outMainId") Long outMainId, ModelMap mmap) {
        ChenMaterialOutMain chenMaterialOutMain = chenMaterialOutMainService.selectChenMaterialOutMainById(outMainId);
        mmap.put("chenMaterialOutMain", chenMaterialOutMain);
        return prefix + "/edit";
    }

    /**
     * 修改保存出库信息
     */
    @RequiresPermissions("cpwz:stockOutMain:edit")
    @Log(title = "出库信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ChenMaterialOutMain chenMaterialOutMain) {
        return toAjax(chenMaterialOutMainService.updateChenMaterialOutMain(chenMaterialOutMain));
    }

    /**
     * 删除出库信息
     */
    @RequiresPermissions("cpwz:stockOutDetail:remove")
    @Log(title = "出库信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
         return toAjax(chenMaterialOutDetailService.deleteChenMaterialOutDetailByIds(ids));
    }

}
