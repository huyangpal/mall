package com.xm.mall.view;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.xm.mall.bean.user;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author huyan
 * @date 2019-04-10 16:10
 */
public class PDFView extends AbstractPdfView {

    /**
     *
     * @param model model
     * @param document pdf上下文
     * @param writer
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置字体
        BaseFont baseFont = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont);

        //1、获取数据
        user user = (user)model.get("user");
        //2、创建PDF表格,5列
        PdfPTable pdfPTable = new PdfPTable(5);
        //3、表头信息
        pdfPTable.addCell(new Phrase(new Chunk("姓名",font)));
        pdfPTable.addCell(new Phrase(new Chunk("性别",font)));
        pdfPTable.addCell(new Phrase(new Chunk("年龄",font)));
        pdfPTable.addCell(new Phrase(new Chunk("联系方式",font)));
        pdfPTable.addCell(new Phrase(new Chunk("地址",font)));
        //4、数据
        pdfPTable.addCell(new Phrase(new Chunk(user.getName(),font)));
        pdfPTable.addCell(new Phrase(new Chunk(user.getSex(),font)));
        pdfPTable.addCell(new Phrase(new Chunk(String.valueOf(user.getAge()),font)));
        pdfPTable.addCell(new Phrase(new Chunk(user.getTelephone(),font)));
        pdfPTable.addCell(new Phrase(new Chunk(user.getAddress(),font)));

        //5、把表格放到文档中
        document.add(pdfPTable);


    }
}
