package com.fast.excel;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("export")
@Controller
public class ExcelController {

    @RequestMapping("exportExcel.do")
    @CrossOrigin(origins="*",maxAge = 3600)
    public void exportExcel(HttpServletResponse response, @RequestBody JSONObject params) {
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> result1 = new HashMap<>();
        Map<String, Object> result2 = new HashMap<>();
        Map<String, Object> result3 = new HashMap<>();
        Map<String, Object> result4 = new HashMap<>();
        result1.put("name", "A");
        result1.put("age", 34);
        result1.put("gender", "男");
        result1.put("ID", "12345678sdfasdf9");
        result1.put("number", "String");
        result1.put("img", "");
        result2.put("name", "B");
        result2.put("age", 34);
        result2.put("gender", "男");
        result2.put("ID", "1234567897979878");
        result2.put("number", "String");
        result2.put("img", "");
        result3.put("name", "C");
        result3.put("age", 34);
        result3.put("gender", "男");
        result3.put("ID", "123456sdfasd4564789");
        result3.put("number", "String");
        result3.put("img", "");
        result4.put("name", "D");
        result4.put("age", 34);
        result4.put("gender", "男");
        result4.put("ID", "1234567asdfasdf64448489");
        result4.put("number", "String");
        result4.put("img", "");
        result.add(result1);
        result.add(result2);
        result.add(result3);
        result.add(result4);
        //创建HSSFWorkbook对象===>文档对象
        HSSFWorkbook hwb = new HSSFWorkbook();
        //创建HSSFCellStyle设置样式
        HSSFCellStyle cellStyle = hwb.createCellStyle();
        //设置边框
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);

        //水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        //垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //创建HSSFSheet对象====>下面的文档名称Sheet
        HSSFSheet sheet = hwb.createSheet("测试");

        //创建HSSFRow===>行
        HSSFRow row0 = sheet.createRow(0);

        CellStyle style = hwb.createCellStyle();
        HSSFFont font = hwb.createFont();
        font.setBold(true);//true>>>700  false===>400
        style.setFont(font);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.MEDIUM_DASHED);
        //水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        //垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        if (result.size() != 0) {

            //设置单元格第一行第一个值
            HSSFCell cell0 = row0.createCell(0);
            cell0.setCellValue("名字");
            cell0.setCellStyle(style);
            sheet.setColumnWidth(cell0.getColumnIndex(), 100 * 50);
            //设置单元格第一行第二个值
            HSSFCell cell1 = row0.createCell(1);
            cell1.setCellValue("年龄");
            cell1.setCellStyle(style);
            sheet.setColumnWidth(cell1.getColumnIndex(), 100 * 50);

            //设置单元格第一行第三个值
            HSSFCell cell2 = row0.createCell(2);
            cell2.setCellValue("性别");
            cell2.setCellStyle(style);
            sheet.setColumnWidth(cell2.getColumnIndex(), 100 * 50);

            //设置单元格第一行第四个值
            HSSFCell cell3 = row0.createCell(3);
            cell3.setCellValue("身份证号");
            cell3.setCellStyle(style);
            sheet.setColumnWidth(cell3.getColumnIndex(), 100 * 50);

            //设置单元格第一行第五个值
            HSSFCell cell4 = row0.createCell(4);
            cell4.setCellValue("Number");
            cell4.setCellStyle(style);
            sheet.setColumnWidth(cell4.getColumnIndex(), 100 * 50);


            HSSFCell cell5 = row0.createCell(5);
            cell5.setCellValue("img");
            cell5.setCellStyle(style);
            sheet.setColumnWidth(cell5.getColumnIndex(), 100 * 50);
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            for (int i = 0; i < result.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);

                //设置单元格的值
                HSSFCell cell1_0 = row.createCell(0);
                cell1_0.setCellValue(result.get(i).get("name").toString());
                cell1_0.setCellStyle(cellStyle);
                row.setHeight((short) 1000);
                HSSFCell cell1_1 = row.createCell(1);
                cell1_1.setCellValue(result.get(i).get("age").toString());
                cell1_1.setCellStyle(cellStyle);
                HSSFCell cell1_2 = row.createCell(2);
                cell1_2.setCellValue(result.get(i).get("gender").toString());
                cell1_2.setCellStyle(cellStyle);
                HSSFCell cell1_3 = row.createCell(3);
                cell1_3.setCellValue(result.get(i).get("ID").toString());
                cell1_3.setCellStyle(cellStyle);
                HSSFCell cell1_4 = row.createCell(4);
                cell1_4.setCellValue(result.get(i).get("number").toString());
                cell1_4.setCellStyle(cellStyle);
                HSSFCell cell1_5 = row.createCell(5);
                cell1_5.setCellStyle(cellStyle);
                try {
                    BufferedImage bufferImg = null;
                    // 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
                    ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                    //获取服务器上的图片
                    //bufferImg = ImageIO.read(new File("/data/lrkj.com/resources/upload/breakpoint"+result.get(i).get("goodsImage").toString()));
                    String strImg = "/img/anc.jpg";
                    String subImg = strImg.substring(strImg.lastIndexOf(".") + 1);
                    org.springframework.core.io.Resource resource = new ClassPathResource(strImg);
                    File file = resource.getFile();
                    bufferImg = ImageIO.read(file);
                    ImageIO.write(bufferImg, subImg, byteArrayOut);
                    HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

               /**
                *   该构造函数有8个参数
                前四个参数是控制图片在单元格的位置，分别是图片距离单元格left，top，right，bottom的像素距离
                后四个参数，前连个表示图片左上角所在的cellNum和 rowNum，后天个参数对应的表示图片右下角所在的cellNum和 rowNum，
                excel中的cellNum和rowNum的index都是从0开始的

                 */
                    //图片导出到单元格中
                    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,
                            (short)6, i+1, (short)5, i+2);
                    // 插入图片
                    Long ImgFormat = 0l;
                    if (subImg.equals("emf")) {
                        ImgFormat = 2l;
                    } else if (subImg.equals("wmf")) {
                        ImgFormat = 3l;
                    } else if (subImg.equals("pict")) {
                        ImgFormat = 4l;
                    } else if (subImg.equals("jpeg") || subImg.equals("jpg")) {
                        ImgFormat = 5l;
                    } else if (subImg.equals("dib")) {
                        ImgFormat = 7l;
                    } else {
                        ImgFormat = 6l;
                    }
                    patriarch.createPicture(anchor, hwb.addPicture(byteArrayOut
                            .toByteArray(), Integer.valueOf(String.valueOf(ImgFormat))));
                } catch (Exception e) {
                }
            }
        }
        try {
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            //传excel到前端响应头文件上
            String msg = new String("测试" + "-" + timeInMillis + ".xls");
            String fileName = URLEncoder.encode(msg, "UTF-8");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            hwb.write(response.getOutputStream());
            hwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
