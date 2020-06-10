package com.lg.lg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lg.lg.config.AjaxResult;
import com.lg.lg.config.BaseController;
import com.lg.lg.entity.LgScorelibrary;
import com.lg.lg.entity.LgUser;
import com.lg.lg.service.LgScorelibraryService;
import com.lg.lg.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author admin
 * @date 2020/5/8 15:21
 */
@Controller
@RequestMapping("/LgScoreLibrary")
public class LgScoreLibraryController extends BaseController {

    @Autowired
    private LgScorelibraryService lgScorelibraryService;

    @RequestMapping("/allScorelibrary")
    public String selcetAllScorelibrary(Model model, @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
            , @ModelAttribute("createtimeSpace")String createtimeSpace,
                                @ModelAttribute("updatetimeSpace")String updatetimeSpace, LgScorelibrary lgScorelibrary){

        Page<LgScorelibrary> page = new Page<>(pageNo, pageSize);
        QueryWrapper<LgScorelibrary> queryWrapper = new QueryWrapper<LgScorelibrary>();

        IPage<LgScorelibrary> pageInfo = lgScorelibraryService.page(page, queryWrapper);
        model.addAttribute("pageInfo",new PageInfo(pageInfo));
        return "ScoreLibrary/list";
    }

    /**
     * 添加跳转页面
     * @return
     */
    @GetMapping("addBefore")
    public String addBefore(){
        return "ScoreLibrary/add";
    }
    /**
     * 添加
     * @param lgScorelibrary
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(LgScorelibrary lgScorelibrary){
        QueryWrapper<LgScorelibrary> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(LgScorelibrary::getName,lgScorelibrary.getName());
        LgScorelibrary lg=lgScorelibraryService.getOne(queryWrapper);
        if(lg==null){
            return toAjax(lgScorelibraryService.save(lgScorelibrary));
        }else{
            return toAjax(0);
        }

    }
}
