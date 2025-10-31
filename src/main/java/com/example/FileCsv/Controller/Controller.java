package com.example.FileCsv.Controller;

import com.example.FileCsv.Service.CSVService;
import com.example.FileCsv.Service.PDFService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/emp")
public class Controller
{
    @Autowired
    private CSVService csvService;

    @Autowired
    private PDFService pdfService;

    @PostMapping("/upload")
    public String uploadcsv (@RequestParam("file")MultipartFile file) throws IOException
    {
        csvService.saveCsv(file);
        return "Yes";
    }

    @GetMapping("/download-pdf")
    public void downloadPdf(HttpServletResponse response) throws Exception
    {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition","attachment;filename=employees.pdf");
        pdfService.genratepdf(response);
    }



}
