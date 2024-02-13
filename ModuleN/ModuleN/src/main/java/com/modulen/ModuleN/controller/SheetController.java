package com.modulen.ModuleN.controller;


import com.google.api.services.drive.model.File;
import com.modulen.ModuleN.service.SheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("/sheet")
@CrossOrigin("*")
public class SheetController {

    @Autowired
    SheetService sheetService;


    @GetMapping("/list")
    public ResponseEntity<List<File>> listGoogleSheetsWithData() throws IOException, GeneralSecurityException {
        List<File> sheets = sheetService.listGoogleSheetsWithData();
        System.out.println("Retrieved sheets data.");
        return ResponseEntity.ok(sheets);
    }

    @GetMapping("/data/{sheetId}")
    public ResponseEntity<String> getSheetData(@PathVariable String sheetId) throws GeneralSecurityException, IOException {
        String result=sheetService.getSheetsData(sheetId);
        System.out.println(result);

        return ResponseEntity.ok(result);
    }



}
