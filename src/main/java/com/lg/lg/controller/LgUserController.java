package com.lg.lg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lg.lg.config.AjaxResult;
import com.lg.lg.config.BaseController;
import com.lg.lg.entity.LgRole;
import com.lg.lg.entity.LgUser;
import com.lg.lg.entity.RoleType;
import com.lg.lg.entity.UserType;
import com.lg.lg.service.LgUserService;
import com.lg.lg.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author admin
 * @date 2020/4/28 14:49
 */
@Controller
@RequestMapping("/lgUser")
public class LgUserController extends BaseController {

    @Autowired
    private LgUserService lgUserService;

    @RequestMapping("/allUser")
    public String selcetAllUser(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
            , @ModelAttribute("createtimeSpace")String createtimeSpace,
                                @ModelAttribute("updatetimeSpace")String updatetimeSpace, LgUser lgUser){

        System.out.println(pageNo+"ssss"+pageSize);
        Page<LgUser> page = new Page<>(pageNo, pageSize);
        System.out.println(page.getTotal()+"ssss");
        QueryWrapper<LgUser> queryWrapper = new QueryWrapper<LgUser>();

       /* model.addAttribute("createtimeSpace", createtimeSpace);
        model.addAttribute("updatetimeSpace", updatetimeSpace);
        model.addAttribute("searchInfo", department);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"list";*/

        IPage<LgUser> pageInfo = lgUserService.page(page, queryWrapper);
        System.out.print(pageInfo.getSize()+"sss");
        model.addAttribute("pageInfo",new PageInfo(pageInfo));
        System.out.print(new PageInfo(pageInfo).toString()+"sss");
        return "admin/admin-list";
    }



    /**
     * 添加跳转页面
     * @return
     */
    @GetMapping("addBefore")
    public String addBefore(){
        return "admin/add";
    }
    /**
     * 添加
     * @param lgUser
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(LgUser lgUser){
        lgUser.setAvaliable(1);
        lgUser.setPassword("123456");
        return toAjax(lgUserService.save(lgUser));
    }
    /**
     * 跳转编辑页面
     * @return
     */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("lgUser",lgUserService.getById(id));
        model.addAttribute("department",lgUserService.selectAllDepartmentUser());
        model.addAttribute("leader",lgUserService.selectAllleaderUser());
        List<UserType> type=new ArrayList<>();
        type.add(new UserType("0","基层"));
        type.add(new UserType("1","部门负责人"));
        type.add(new UserType("2","经营班子"));
        type.add(new UserType("3","总经理"));
        model.addAttribute("type",type);
        List<RoleType> role=new ArrayList<>();
        role.add(new RoleType(0,"被评分人"));
        role.add(new RoleType(1,"评分人"));
        role.add(new RoleType(2,"审核人"));
        role.add(new RoleType(3,"管理员"));
        model.addAttribute("role",role);
        return "admin/edit";
    }
    /**
     * 修改
     * @param
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(LgUser lgUser){
        return toAjax(lgUserService.updateById(lgUser));
    }
    /**
     * 离职
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        LgUser lgUser=lgUserService.getById(id);
        lgUser.setAvaliable(0);
        return toAjax(lgUserService.updateById(lgUser));
    }
    /**
     * 批量离职
     * @param ids
     * @return
     */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        List<LgUser> lgUsers=new ArrayList<>();
        for(long id:ids){
            LgUser lgUser=lgUserService.getById(id);
            lgUser.setAvaliable(0);
            lgUsers.add(lgUser);
        }
        return toAjax(lgUserService.updateBatchById(lgUsers));
    }

    /**
     * 查询所有的部门负责人
     * @return
     */
    @RequestMapping("getAllDepartmentUser")
    @ResponseBody
    public List<LgUser> getAllDepartmentUser(){
        List<LgUser> lgUsers=lgUserService.selectAllDepartmentUser();
        return lgUsers;
    }

    /**
     * 查询所有的经营班子
     * @return
     */
    @RequestMapping("getAllLeaderUser")
    @ResponseBody
    public List<LgUser> getAllLeaderUser(){
        List<LgUser> lgUsers=lgUserService.selectAllleaderUser();
        return lgUsers;
    }
}
