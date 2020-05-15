package com.lg.lg.controller;
import	java.math.BigDecimal;
import java.math.BigDecimal;
import	java.util.ArrayList;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lg.lg.config.AjaxResult;
import com.lg.lg.config.BaseController;
import com.lg.lg.entity.*;
import com.lg.lg.service.LgQuarterService;
import com.lg.lg.service.LgScoredetailsService;
import com.lg.lg.service.LgScorelibraryService;
import com.lg.lg.service.LgUserService;
import com.lg.lg.util.PageInfo;
import org.apache.ibatis.annotations.Param;
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
    @Autowired
    private LgScoredetailsService lgScoredetailsService;
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
        //获取所有的除总经理经营班子以外的在职员工
        List<LgUser> lgUser=lgUserService.selectAllByAvaliable();
        List<LgUser> lgUsers= lgUserService.selectAllUserByQuarter(id);
        for(LgUser u:lgUser){
            if(lgUsers.contains(u)){
                u.setColor(0);
            }else{
                u.setColor(1);
            }
        }
        model.addAttribute("lgUser",lgUser);
        model.addAttribute("quarter",lgQuarterService.getById(id));

        return "quarter/ScoreDetialList";
    }

    @RequestMapping("userEditScoreDetial")
    public String userEditScoreDetial(Model model,Long userId,Long quarterId){
/*        model.addAttribute("lgScoreLibrary",lgScorelibraryService.selectAllScoreLibary());
        model.addAttribute("lgUser",lgUserService.selectAllByAvaliable());*/

         List<UserScoreLibraryDetials> userScoreLibraryDetialsList=new ArrayList<> ();
        //所有的考核项目
        List<LgScorelibrary> allLgScores=lgScorelibraryService.selectAllScoreLibary();
        for (LgScorelibrary s:allLgScores) {
            UserScoreLibraryDetials userScoreLibraryDetials=new UserScoreLibraryDetials();
            userScoreLibraryDetials.setValue(String.valueOf(s.getId()));
            userScoreLibraryDetials.setTitle(s.getName());
            userScoreLibraryDetialsList.add(userScoreLibraryDetials);
        }
        //根据用户和季度查询拥有的考核项目
        List<LgScorelibrary> lgScorelibraryList = lgScorelibraryService.selectScoreLibaryByUserIdAndQuarterId(userId,quarterId);
        List<Long> show=new ArrayList<>();
        for (LgScorelibrary s:lgScorelibraryList){
            show.add(s.getId());
        }

        List<LgScoredetails> lgScoredetails=lgScoredetailsService.selectUserByQuarterAndUser(userId,quarterId);
        for(LgScoredetails s:lgScoredetails){

        }
        model.addAttribute("userScoreLibraryDetialsList",userScoreLibraryDetialsList);
        model.addAttribute("allLgScores",allLgScores);
        model.addAttribute("show",show);
        model.addAttribute("userId",userId);
        model.addAttribute("quarterId",quarterId);
        model.addAttribute("userScoreDetials",lgScoredetails);
        return "quarter/UserScoreDetial";
    }

    @PostMapping("getData")
    @ResponseBody
    public List<LgScorelibrary> getData( @RequestBody List<UserScoreLibraryDetials> userScoreLibraryDetials){
        List<LgScorelibrary> lgScorelibraries=new ArrayList<>();
        for(UserScoreLibraryDetials s:userScoreLibraryDetials){
            lgScorelibraries.add(lgScorelibraryService.getById(String.valueOf(s.getValue())));
        }

        return lgScorelibraries;
    }

    @PostMapping("addUserScoreDetial")
    @ResponseBody
    public AjaxResult addUserScoreDetial(ScoreDetialList dat,long userId,long quarterId){
        List<LgScoredetails> lgScoredetailsList = new ArrayList<>();
        BigDecimal count=new BigDecimal(0);


        for (int i = 0; i < dat.getAmisAmount().size();i++){
            count=count.add(dat.getWeights().get(i));
            System.out.println("count:"+count);

            LgUser lgUser=lgUserService.getById(userId);
            if(lgUser.getType().equals("0")){

                //基层员工评分人
                List<LgUser> leaderId=lgUserService.selectUserJC();
                for(LgUser u:leaderId){
                    LgScoredetails lgScoredetails=new LgScoredetails();
                    lgScoredetails.setScoreId(dat.getScoreId().get(i).longValue());
                    lgScoredetails.setAmisAmount(dat.getAmisAmount().get(i));
                    lgScoredetails.setFinishedAmount(dat.getFinishedAmount().get(i));
                    lgScoredetails.setWeights(dat.getWeights().get(i));
                    lgScoredetails.setUserId(userId);
                    lgScoredetails.setStatus(4);
                    lgScoredetails.setQuarterId(quarterId);
                    lgScoredetails.setTaterId(u.getId());
                    lgScoredetailsList.add(lgScoredetails);
                }

            }else if(lgUser.getType().equals("1")){

                //部门负责人评分人
                List<LgUser> leaderId=lgUserService.selectUserDepart(lgUser);
                for(LgUser u:leaderId){
                    LgScoredetails lgScoredetails=new LgScoredetails();
                    lgScoredetails.setScoreId(dat.getScoreId().get(i).longValue());
                    lgScoredetails.setAmisAmount(dat.getAmisAmount().get(i));
                    lgScoredetails.setFinishedAmount(dat.getFinishedAmount().get(i));
                    lgScoredetails.setWeights(dat.getWeights().get(i));
                    lgScoredetails.setUserId(userId);
                    lgScoredetails.setStatus(4);
                    lgScoredetails.setQuarterId(quarterId);
                    lgScoredetails.setTaterId(u.getId());
                    lgScoredetailsList.add(lgScoredetails);
                }
            }
        }

        if(count.doubleValue()!=1d){
            return toAjax(false);
        }
        QueryWrapper<LgScoredetails> queryWrapper = new QueryWrapper<>();

        lgScoredetailsService.deleteUserByQuarterAndUser(userId,quarterId);
        return toAjax(lgScoredetailsService.saveBatch(lgScoredetailsList));
    }


    /**
     * 考核进度查看
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("toProgressList")
    public String toProgressList(Model model,Long id){

        List<ProgressUser> progressUsers=new ArrayList<> ();
        List<LgUser> lgUserList=lgUserService.selectAllUserByQuarter(id);
        for(LgUser u:lgUserList){
            ProgressUser progressUser=new ProgressUser();
            List<LgUser> finishUsers=lgUserService.selectFinishUserByQuarterAndUSerId(id,u.getId());
            List<LgUser> notFinishUsers=lgUserService.selectNotFinishUserByQuarterAndUSerId(id,u.getId());
            double a;
            if((finishUsers.size()+notFinishUsers.size())==0){
                a=0;
            }else{
                a=new Double(finishUsers.size())/new Double((finishUsers.size()+notFinishUsers.size()));
            }
            progressUser.setLgUser(u);
            progressUser.setFinishiList(finishUsers);
            progressUser.setNotFinishList(notFinishUsers);
            progressUser.setProportion(a*100);
            progressUsers.add(progressUser);
        }

        model.addAttribute("progressUsers",progressUsers);

        return "quarter/progressList";
    }
}
