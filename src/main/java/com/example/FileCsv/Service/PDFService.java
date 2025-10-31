package com.example.FileCsv.Service;

import com.example.FileCsv.Entity.Model;
import com.example.FileCsv.Repo.FileRepo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class PDFService {

    @Autowired
    private  FileRepo repo;


    public void genratepdf(HttpServletResponse response) throws IOException, DocumentException {
        List<Model> mod = repo.findAll();


        Document doc = new Document();
        PdfWriter.getInstance(doc,response.getOutputStream());
        doc.open();

        Font titlefont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,18);
        Paragraph para = new Paragraph("Empolyee Report ",titlefont);
        para.setAlignment(Element.ALIGN_CENTER);

        doc.add(para);
        doc.add(Chunk.NEWLINE);


        PdfPTable table = new PdfPTable(3);
        table.addCell("Name");
        table.addCell("Email");
        table.addCell("Salary");


        for(Model l :mod )
        {
            table.addCell(l.getName());
            table.addCell(l.getEmail());
            table.addCell(String.valueOf(l.getSalary()));
        }

        doc.add(table);
        doc.close();
    }


}
