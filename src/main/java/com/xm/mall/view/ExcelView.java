package com.xm.mall.view;

import com.xm.mall.bean.user;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * excel表格
 *
 * @author huyan
 * @date 2019-04-10 16:38
 */
public class ExcelView extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1、获取数据
        user user = (user)model.get("user");
        //2、创建sheet 单元格
        Sheet sheet = workbook.createSheet();
        //3、表头信息
        Row head = sheet.createRow(0);
        head.createCell(0).setCellValue("姓名");
        head.createCell(1).setCellValue("性别");
        head.createCell(2).setCellValue("年龄");
        head.createCell(3).setCellValue("联系方式");
        head.createCell(4).setCellValue("地址");

        //4、数据
        Row data = sheet.createRow(1);
        data.createCell(0).setCellValue(user.getName());
        data.createCell(1).setCellValue(user.getSex());
        data.createCell(2).setCellValue(user.getAge());
        data.createCell(3).setCellValue(user.getTelephone());
        data.createCell(4).setCellValue(user.getAddress());



    }
}
