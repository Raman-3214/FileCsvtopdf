package com.example.FileCsv.Service;

import com.example.FileCsv.Entity.Model;
import com.example.FileCsv.Repo.FileRepo;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class CSVService {

    @Autowired
    private FileRepo repo;


    public void saveCsv(MultipartFile file) throws IOException {
        try(CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream())))
        {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null)
            {
                Model mod = new Model();
                mod.setName(line[0]);
                mod.setEmail(line[1]);
                mod.setSalary(Double.parseDouble(line[2]));
                repo.save(mod);
            }
        }

        catch (CsvValidationException e)

        {
            throw new RuntimeException(e);
        }


    }

}
