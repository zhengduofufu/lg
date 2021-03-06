package com.lg.lg.controller;
import	java.math.BigDecimal;
import java.math.RoundingMode;
import	java.util.ArrayList;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lg.lg.config.AjaxResult;
import com.lg.lg.config.BaseController;
import com.lg.lg.entity.*;
import com.lg.lg.service.*;
import com.lg.lg.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author admin
 * @date 2020/5/8 16:35
 */
@Controller
@RequestMapping("/lgQuarter")
@SessionAttributes({"user","lgAuthorities"})
public class LgQuarterController extends BaseController {

    @Autowired
    private LgQuarterService lgQuarterService;
    @Autowired
    private LgScoredetailsService lgScoredetailsService;
    @Autowired
    private LgScorelibraryService lgScorelibraryService;
    @Autowired
    private LgUserService lgUserService;
    @Autowired
    private LgCalculationrulesService lgCalculationrulesService;
    @Autowired
    private LgScoresummaryService lgScoresummaryService;


    /**
     * 所有季度列表
     * @param model
     * @param pageNo
     * @param pageSize
     * @param createtimeSpace
     * @param updatetimeSpace
     * @param lgQuarter
     * @return
     */
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


        if(dat.getScoreId()==null){
            return toAjax(0);
        }

        for (int i = 0; i < dat.getAmisAmount().size();i++){
            count=count.add(dat.getWeights().get(i));
            System.out.println("count:"+count);

            LgUser lgUser=lgUserService.getById(userId);
            if(lgUser.getType().equals("0")){

                //部门负责人评分
                LgScoredetails lgScoredetail=new LgScoredetails();
                lgScoredetail.setScoreId(dat.getScoreId().get(i).longValue());
                lgScoredetail.setAmisAmount(dat.getAmisAmount().get(i));
                lgScoredetail.setFinishedAmount(dat.getFinishedAmount().get(i));
                lgScoredetail.setWeights(dat.getWeights().get(i));
                lgScoredetail.setUserId(userId);
                lgScoredetail.setStatus(4);
                lgScoredetail.setQuarterId(quarterId);
                lgScoredetail.setTaterId(lgUser.getDepartmentId());
                if(dat.getAmisAmount().get(i).compareTo(new BigDecimal(0))==0||dat.getFinishedAmount().get(i).compareTo(new BigDecimal(0))==0){

                    lgScoredetail.setScore(new BigDecimal(0));

                }else{
                    lgScoredetail.setScore((dat.getFinishedAmount().get(i)).divide(dat.getAmisAmount().get(i), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
                }
                lgScoredetailsList.add(lgScoredetail);
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
                    if(dat.getAmisAmount().get(i).compareTo(new BigDecimal(0))==0||dat.getFinishedAmount().get(i).compareTo(new BigDecimal(0))==0){

                        lgScoredetails.setScore(new BigDecimal(0));

                    }else{
                        lgScoredetails.setScore((dat.getFinishedAmount().get(i)).divide(dat.getAmisAmount().get(i),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
                    }
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
                    if(dat.getAmisAmount().get(i).compareTo(new BigDecimal(0))==0||dat.getFinishedAmount().get(i).compareTo(new BigDecimal(0))==0){

                        lgScoredetails.setScore(new BigDecimal(0));

                    }else{
                        lgScoredetails.setScore((dat.getFinishedAmount().get(i)).divide(dat.getAmisAmount().get(i)).multiply(new BigDecimal(100)));
                    }
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
        model.addAttribute("quarterId",id);

        return "quarter/progressList";
    }


    //都是要获取领导Id的项
    /**
     * 跳转leader评审员工页面
     * @return
     */
    @RequestMapping("toUserReviewListDetial")
    public String toUserReviewListDetial(Model model, long id, HttpSession session){
        //获取所有的除需要评审的当季在职员工
        LgUser user=(LgUser)session.getAttribute("user");
        List<LgUser> lgUser=lgUserService.selectAllByAvaliableAndLeaderId(user.getId(),id);
        List<LgUser> lgUsers= lgUserService.selectFinishByAvaliableAndLeaderId(user.getId(),id);
        for(LgUser u:lgUser){
            if(lgUsers.contains(u)){
                u.setColor(0);
            }else{
                u.setColor(1);
            }
        }
        Integer status=lgScoredetailsService.selectStatusByUserIdAndQuarterId(id,user.getId());
        model.addAttribute("lgUser",lgUser);
        model.addAttribute("quarter",lgQuarterService.getById(id));
        model.addAttribute("status",status);

        return "user/UserReviewList";
    }
    /**
     * 跳转leader查看考核员工页面
     * @return
     */
    @RequestMapping("toUserQuaretrReviewList")
    public String toUserQuaretrReviewList(Model model, long id, HttpSession session){
        //获取所有的除需要评审的当季在职员工
        LgUser user=(LgUser)session.getAttribute("user");
        List<LgUser> lgUser=lgUserService.selectAllByAvaliableAndLeaderId(user.getId(),id);
        List<LgUser> lgUsers= lgUserService.selectFinishByAvaliableAndLeaderId(user.getId(),id);
        for(LgUser u:lgUser){
            if(lgUsers.contains(u)){
                u.setColor(0);
            }else{
                u.setColor(1);
            }
        }
        model.addAttribute("lgUser",lgUser);
        model.addAttribute("quarter",lgQuarterService.getById(id));

        return "user/UserQuaretrReviewList";
    }
    /**
     * 根据用户季度领导查询所有的员工考核详情
     * @param model
     * @param userId
     * @param quarterId
     * @return
     */
    @RequestMapping("userReviewScoreDetial")
    public String userReviewScoreDetial(Model model,Long userId,Long quarterId,HttpSession session){

        LgUser user=(LgUser)session.getAttribute("user");
        //根据用户和季度和leaderId查询拥有的考核项目
        List<LgScoredetails> lgScoredetails=lgScoredetailsService.selectScoreDetialByUserIdAndQuarterIdAndLeaderId(userId,quarterId,user.getId());
        BigDecimal nowTotalNumber=new BigDecimal(0);
        for(LgScoredetails a:lgScoredetails){
            if(a.getType()==2){
                nowTotalNumber=nowTotalNumber.add(a.getScore().multiply(new BigDecimal(1)));
            }else {
                nowTotalNumber = nowTotalNumber.add(a.getScore().multiply(a.getWeights()));
            }
        }

        List<LgQuarter> lgQuarters=lgQuarterService.selectByUserIdAndLeaderId(userId,quarterId,user.getId());
        List<QuarterAndSocreDetial> lists=new ArrayList<>();
        for(LgQuarter q:lgQuarters){
            QuarterAndSocreDetial a=new QuarterAndSocreDetial();
            BigDecimal totalNumber=new BigDecimal(0);
            List<LgScoredetails> lgScoredetails1=lgScoredetailsService.selectScoreDetialByUserIdAndQuarterIdAndLeaderIdB(userId,q.getId(),user.getId());

            for(LgScoredetails s:lgScoredetails1){
                if(s.getType()==2){
                    totalNumber=totalNumber.add(s.getScore().multiply(new BigDecimal(1)));
                }else {
                    totalNumber = totalNumber.add(s.getScore().multiply(s.getWeights()));
                }
            }
            a.setLgQuarter(q);
            a.setLgScoredetailsList(lgScoredetails1);
            a.setTotalNum(totalNumber.longValue());
            lists.add(a);
        }
        model.addAttribute("userId",userId);
        model.addAttribute("quarterId",quarterId);
        model.addAttribute("userScoreDetials",lgScoredetails);
        model.addAttribute("lists",lists);
        model.addAttribute("usera",lgUserService.getById(userId));
        model.addAttribute("quarter",lgQuarterService.getById(quarterId));
        model.addAttribute("nowTotalNumber",nowTotalNumber.longValue());
        return "user/UserReviewSocreDetial";
    }
    /**
     * 根据用户季度领导查询所有的员工考核详情
     * @param model
     * @param userId
     * @param quarterId
     * @return
     */
    @RequestMapping("toUserQuarterDetial")
    public String toUserQuarterDetial(Model model,Long userId,Long quarterId,HttpSession session){

        LgUser user=(LgUser)session.getAttribute("user");
        //根据用户和季度和leaderId查询拥有的考核项目
        List<LgScoredetails> lgScoredetails=lgScoredetailsService.selectScoreDetialByUserIdAndQuarterIdAndLeaderIdB(userId,quarterId,user.getId());
        BigDecimal totalNumber=new BigDecimal(0);
        for(LgScoredetails s:lgScoredetails){
            if(s.getType()==2){
                totalNumber=totalNumber.add(s.getScore().multiply(new BigDecimal(1)));
            }else{
                totalNumber=totalNumber.add(s.getScore().multiply(s.getWeights()));
            }

        }
        model.addAttribute("userId",userId);
        model.addAttribute("quarterId",quarterId);
        model.addAttribute("userScoreDetials",lgScoredetails);
        model.addAttribute("usera",lgUserService.getById(userId));
        model.addAttribute("quarter",lgQuarterService.getById(quarterId));
        model.addAttribute("totalNumber",totalNumber.longValue());
        return "user/UserQuarterDetial";
    }

    /**
     * 评分
     * @param dat
     * @param userId
     * @param quarterId
     * @return
     */
    @PostMapping("updateUserScoreDetialByScore")
    @ResponseBody
    public AjaxResult updateUserScoreDetialByScore(ScoreDetialList dat,long userId,long quarterId){
        List<LgScoredetails> lgScore=new ArrayList<>();
        System.out.println(dat.getId().size()+"aaaa");
        for (int i = 0; i < dat.getId().size();i++){
            LgScoredetails lgScoredetails=lgScoredetailsService.getById(dat.getId().get(i));
            lgScoredetails.setScore(dat.getScore().get(i));
            lgScoredetails.setStatus(1);
            lgScore.add(lgScoredetails);
        }
        return toAjax(lgScoredetailsService.saveOrUpdateBatch(lgScore));
    }

    /**
     * 根据季度确认所有的考核详情
     * @param quarterId
     * @return
     */
    @PostMapping("updateUserComfirmSocreDetials")
    @ResponseBody
    public AjaxResult updateUserComfirmSocreDetials(long quarterId){
        List<LgScoredetails> lgScore=lgScoredetailsService.selectDetialsUserByQuarter(quarterId);
        LgQuarter lgQuarter=lgQuarterService.getById(quarterId);
        for (LgScoredetails s:lgScore){
            s.setStatus(0);
        }
        lgQuarter.setStatus(2);
        lgQuarterService.saveOrUpdate(lgQuarter);
        return toAjax(lgScoredetailsService.saveOrUpdateBatch(lgScore));
    }

    /**
     * 领导根据季度确认所有的考核详情
     * @param quarterId
     * @return
     */
    @PostMapping("updateLeaderQuarterComfirmSocreDetials")
    @ResponseBody
    public AjaxResult updateLeaderQuarterComfirmSocreDetials(long quarterId,HttpSession session){
        LgUser user=(LgUser)session.getAttribute("user");
        List<LgScoredetails> lgScore=lgScoredetailsService.selectScoreDetialByQuarterIdAndLeaderId(quarterId,user.getId());
        for (LgScoredetails s:lgScore){
            if(s.getType()==2){
                s.setWeights(new BigDecimal(1));
                s.setStatus(2);
            }else{
                s.setStatus(2);
            }

        }
        return toAjax(lgScoredetailsService.saveOrUpdateBatch(lgScore));
    }

    /**
     * 季度结算界面
     * @param quarterId
     * @return
     */
    @PostMapping("settlementQuarterDetials")
    @ResponseBody
    public AjaxResult settlementQuarterDetials(long quarterId){
        List<LgUser> LgUsers=lgUserService.selectAllUserByQuarter(quarterId);
        List<LgScoresummary> lgScoresummaries=new ArrayList<>();
        for(LgUser u:LgUsers){
            if(u.getType().equals("0")){
                LgScoresummary lgScoresummary=new LgScoresummary();
                LgCalculationrules lgCalculationrules=lgCalculationrulesService.selectByType(0);
                BigDecimal a=lgScoredetailsService.selectScoreSumAByQuarterIdAndUserId(quarterId,u.getId())!=null?lgScoredetailsService.selectScoreSumAByQuarterIdAndUserId(quarterId,u.getId()):new BigDecimal(0);
                BigDecimal b=lgScoredetailsService.selectScoreSumBByQuarterIdAndUserId(quarterId,u.getId())!=null?lgScoredetailsService.selectScoreSumBByQuarterIdAndUserId(quarterId,u.getId()):new BigDecimal(0);
                BigDecimal c=lgScoredetailsService.selectScoreSumCByQuarterIdAndUserId(quarterId,u.getId())!=null?lgScoredetailsService.selectScoreSumCByQuarterIdAndUserId(quarterId,u.getId()):new BigDecimal(0);
                BigDecimal d=lgScoredetailsService.selectScoreSumDByQuarterIdAndUserId(quarterId,u.getId())!=null?lgScoredetailsService.selectScoreSumDByQuarterIdAndUserId(quarterId,u.getId()):new BigDecimal(0);

                lgScoresummary.setUserId(u.getId());
                lgScoresummary.setQuarterId(quarterId);
                lgScoresummary.setAScore(a);
                lgScoresummary.setBScore(b);
                lgScoresummary.setCScore(c);
                lgScoresummary.setDScore(d);
                lgScoresummary.setTotalScore(a.multiply(lgCalculationrules.getAweights()).add(b.multiply(lgCalculationrules.getBweights())).add(
                        c.multiply(lgCalculationrules.getCweights())).add(d.multiply(lgCalculationrules.getDweights())));
                System.out.println("aa:"+a+"bb:"+b+"cc:"+c+"dd:"+d+"total:"+lgScoresummary.getTotalScore());
                lgScoresummaries.add(lgScoresummary);
            }else if(u.getType().equals("1")){
                LgScoresummary lgScoresummary=new LgScoresummary();
                LgCalculationrules lgCalculationrules=lgCalculationrulesService.selectByType(1);
                BigDecimal a=lgScoredetailsService.selectScoreSumAByQuarterIdAndUserId(quarterId,u.getId())!=null?lgScoredetailsService.selectScoreSumAByQuarterIdAndUserId(quarterId,u.getId()):new BigDecimal(0);
                BigDecimal b=lgScoredetailsService.selectScoreSumBByQuarterIdAndUserId(quarterId,u.getId())!=null?lgScoredetailsService.selectScoreSumBByQuarterIdAndUserId(quarterId,u.getId()):new BigDecimal(0);
                BigDecimal c=lgScoredetailsService.selectScoreSumCByQuarterIdAndUserId(quarterId,u.getId())!=null?lgScoredetailsService.selectScoreSumCByQuarterIdAndUserId(quarterId,u.getId()):new BigDecimal(0);
                BigDecimal e=lgScoredetailsService.selectScoreSumEByQuarterIdAndUserId(quarterId,u.getId())!=null?lgScoredetailsService.selectScoreSumEByQuarterIdAndUserId(quarterId,u.getId()):new BigDecimal(0);
                lgScoresummary.setUserId(u.getId());
                lgScoresummary.setQuarterId(quarterId);
                lgScoresummary.setAScore(a);
                lgScoresummary.setBScore(b);
                lgScoresummary.setCScore(c);
                lgScoresummary.setEScore(e);
                lgScoresummary.setTotalScore(a.multiply(lgCalculationrules.getAweights()).add(b.multiply(lgCalculationrules.getBweights())).add(
                        c.multiply(lgCalculationrules.getCweights())).add(e.multiply(lgCalculationrules.getEweights())));
                System.out.println("aa:"+a+"bb:"+b+"cc:"+c+"ee:"+e+"total:"+lgScoresummary.getTotalScore());
                lgScoresummaries.add(lgScoresummary);
            }
        }
        LgQuarter lgQuarter=lgQuarterService.getById(quarterId);
        lgQuarter.setStatus(1);
        lgQuarterService.saveOrUpdate(lgQuarter);
        return toAjax(lgScoresummaryService.saveOrUpdateBatch(lgScoresummaries));
    }

    /**
     * 通过季度Id查找所有的考核汇总详情
     * @param model
     * @param pageNo
     * @param pageSize
     * @param createtimeSpace
     * @param updatetimeSpace
     * @return
     */
    @RequestMapping("/allScoreSummaryDetialByQuarterId")
    public String allScoreSummaryDetialByQuarterId(Model model, @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
            , @ModelAttribute("createtimeSpace")String createtimeSpace,
                                   @ModelAttribute("updatetimeSpace")String updatetimeSpace,long id){

        System.out.println("id:"+id);
        QueryWrapper<LgScoresummary> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(LgScoresummary::getQuarterId,id);
        Page<LgScoresummary> page = lgScoresummaryService.selectByQuarterId(new Page<>(pageNo, pageSize),queryWrapper);
        model.addAttribute("pageInfo",new PageInfo(page));
        model.addAttribute("id",id);
        return "quarter/scoreSummaryList";
    }


    /**
     * 跳转查看考核详情历史版本界面
     * @param model
     * @param quarterId
     * @return
     */
    @RequestMapping("scoreDetialsHistory")
    public String scoreDetialsHistory(Model model,Long quarterId){

        List<LgQuarter> lgQuarters=lgQuarterService.selectByQuarterId(quarterId);
        model.addAttribute("lgQuarters",lgQuarters);
        model.addAttribute("id",quarterId);
        return "quarter/scoreDetialHistory";
    }

    /**
     * 引用历史版本考核详情
     * @param quarterId
     * @return
     */
    @PostMapping("updateScoreDetialByHistory")
    @ResponseBody
    public AjaxResult updateScoreDetialByHistory(long quarterId,long id){

        List<LgScoredetails> lgScoredetailsList=new ArrayList<>();
        //获取所有的除总经理经营班子以外的在职员工
        List<LgUser> lgUser=lgUserService.selectAllByAvaliable();
        //根据季度和用户查找所有考核详情
        for(LgUser u:lgUser){
            List<LgScoredetails> lgScoredetails=lgScoredetailsService.selectUserByQuarterAndUserNotId(u.getId(),quarterId);
            lgScoredetailsList.addAll(lgScoredetails);
        }

        for(LgScoredetails s:lgScoredetailsList){
            s.setQuarterId(id);
            s.setStatus(4);
            if(s.getAmisAmount().compareTo(new BigDecimal(0))==0||s.getFinishedAmount().compareTo(new BigDecimal(0))==0){

                s.setScore(new BigDecimal(0));
            }else{
                s.setScore((s.getFinishedAmount().divide(s.getAmisAmount())).multiply(new BigDecimal(100)));
            }
        }

        return toAjax(lgScoredetailsService.saveBatch(lgScoredetailsList));
    }

    /**
     * 删除季度
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        QueryWrapper<LgScoredetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(LgScoredetails::getQuarterId,id);
        return toAjax( lgScoredetailsService.remove(queryWrapper)&&lgQuarterService.removeById(id));
    }


}
