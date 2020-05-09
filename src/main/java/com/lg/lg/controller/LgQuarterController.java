package com.lg.lg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lg.lg.config.AjaxResult;
import com.lg.lg.config.BaseController;
import com.lg.lg.entity.LgQuarter;
import com.lg.lg.entity.LgUser;
import com.lg.lg.entity.RoleType;
import com.lg.lg.entity.UserType;
import com.lg.lg.service.LgQuarterService;
import com.lg.lg.service.LgScoredetailsService;
import com.lg.lg.service.LgScorelibraryService;
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
 * @date 2020/5/8 16:35
 */
@Controller
@RequestMapping("/lgQuarter")
public class LgQuarterController extends BaseController {

    @Autowired
    private LgQuarterService lgQuarterService;
    /*@Autowired
    private LgScoredetailsService lgScoredetailsService;*/
    @Autowired
    private LgScorelibraryService lgScorelibraryService;
    @Autowired
    private LgUserService lgUserService;

    @RequestMapping("/allQuarter")
    public String selcetAllQuarter(Model model, @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
            , @ModelAttribute("createtimeSpace")String createtimeSpace,
                                @ModelAttribute("updatetimeSpace")String updatetimeSpace, LgQuarter lgQuarter){

        Page<LgQuarter> page = new Page<>(pageNo, pageSize);
        QueryWrapper<LgQuarter> queryWrapper = new QueryWrapper<LgQuarter>();

        IPage<LgQuarter> pageInfo = lgQuarterService.page(page, queryWrapper);
        model.addAttribute("pageInfo",new PageInfo(pageInfo));
        return "quarter/list";
    }

    /**
     * 添加跳转页面
     * @return
     */
    @GetMapping("addBefore")
    public String addBefore(){
        return "quarter/add";
    }
    /**
     * 添加
     * @param lgQuarter
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(LgQuarter lgQuarter){
        lgQuarter.setStatus(0);
        List<LgQuarter> lgQuarter1=lgQuarterService.selectByYearAndQuarter(lgQuarter);
        if(!lgQuarter1.isEmpty()){

            return toAjax(false);
        }
        return toAjax(lgQuarterService.save(lgQuarter));
    }

    /**
     * 跳转编辑页面
     * @return
     */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("lgQuarter",lgQuarterService.getById(id));
        return "quarter/edit";
    }
    /**
     * 修改
     * @param
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(LgQuarter lgQuarter){
        LgQuarter lgQuarter1=lgQuarterService.getById(lgQuarter.getId());
        lgQuarter1.setName(lgQuarter.getName());
        return toAjax(lgQuarterService.updateById(lgQuarter1));
    }

    /**
     * 跳转编辑页面
     * @return
     */
    @RequestMapping("editScoreDetial")
    public String editScoreDetial(Model model,long id){
        model.addAttribute("lgScoreLibrary",lgScorelibraryService.selectAllScoreLibary());
        model.addAttribute("lgUser",lgUserService.selectAllByAvaliable());
        return "quarter/ScoreDetialList";
    }

}
