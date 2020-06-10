package com.lg.lg.controller;

import com.lg.lg.config.BaseController;
import com.lg.lg.entity.LgScoresummary;
import com.lg.lg.service.LgScoresummaryService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author admin
 * @date 2020/5/21 14:25
 */
@Controller
@RequestMapping("/excel")
public class ExcelController extends BaseController {
    //这里直接new了

    @Autowired
    private LgScoresummaryService lgScoresummaryService;
    //获取url链接上的参数
    @RequestMapping("/dowload")
     @ResponseBody
     public String dowm(HttpServletResponse response, @RequestParam("quarterId") long quarterId){
        response.setContentType("application/binary;charset=UTF-8");
        try{
            ServletOutputStream out=response.getOutputStream();
            try {
                //设置文件头：最后一个参数是设置下载文件名(这里我们叫：张三.xls)
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("季度汇总表.xls", "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            String[] titles = { "员工姓名", "部门","季度名称", "总经理评分",
                    "分管领导评分", "其他经营班子评分（平均分）" , "部门负责人评分" , "其他部门负责人评分（平均分）" , "总分数"  };
            export(titles, out,quarterId);
            return "1";
        } catch(Exception e){
            e.printStackTrace();
            return "导出信息失败";
        }
    }

    public void export(String[] titles, ServletOutputStream out,long quarterId) throws Exception{
        try{
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();

            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");

            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

            HSSFRow row = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();

            //居中样式
            hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFCell hssfCell = null;
            for (int i = 0; i < titles.length; i++) {
                hssfCell = row.createCell(i);//列索引从0开始
                hssfCell.setCellValue(titles[i]);//列名1
                hssfCell.setCellStyle(hssfCellStyle);//列居中显示
            }

            // 第五步，写入实体数据
            List<LgScoresummary> lgScoresummaryList=lgScoresummaryService.selectAllByQuarterId(quarterId);


            for (int i = 0; i < lgScoresummaryList.size(); i++) {
                row = hssfSheet.createRow(i+1);
                LgScoresummary lgScoresummary = lgScoresummaryList.get(i);

                // 第六步，创建单元格，并设置值

                String  userName = "";
                if(lgScoresummary.getUserName()!= null){
                    userName = lgScoresummary.getUserName();
                }
                row.createCell(0).setCellValue(userName);

                String  depart = "";
                if(lgScoresummary.getDepart()!= null){
                    depart = lgScoresummary.getDepart();
                }
                row.createCell(1).setCellValue(depart);

                String  quarterName = "";
                if(lgScoresummary.getQuarterName()!= null){
                    quarterName = lgScoresummary.getQuarterName();
                }
                row.createCell(2).setCellValue(quarterName);
                String  aScore = "";
                if(lgScoresummary.getAScore()!= null){
                    aScore = lgScoresummary.getAScore().toString();
                }
                row.createCell(3).setCellValue(aScore);
                String  bScore = "";
                if(lgScoresummary.getBScore()!= null){
                    bScore = lgScoresummary.getBScore().toString();
                }
                row.createCell(4).setCellValue(bScore);
                String  cScore = "";
                if(lgScoresummary.getCScore()!= null){
                    cScore = lgScoresummary.getCScore().toString();
                }
                row.createCell(5).setCellValue(cScore);
                String  dScore = "";
                if(lgScoresummary.getDScore()!= null){
                    dScore = lgScoresummary.getDScore().toString();
                }
                row.createCell(6).setCellValue(dScore);
                String  eScore = "";
                if(lgScoresummary.getEScore()!= null){
                    eScore = lgScoresummary.getEScore().toString();
                }
                row.createCell(7).setCellValue(eScore);
                String  totalScore = "";
                if(lgScoresummary.getTotalScore()!= null){
                    totalScore = lgScoresummary.getTotalScore().toString();
                }
                row.createCell(8).setCellValue(totalScore);
            }

            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("导出信息失败！");

        }
    }
}
