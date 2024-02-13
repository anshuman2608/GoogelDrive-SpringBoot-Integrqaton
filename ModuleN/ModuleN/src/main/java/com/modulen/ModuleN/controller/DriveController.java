package com.modulen.ModuleN.controller;


import com.google.api.services.drive.model.File;
import com.modulen.ModuleN.config.DriveQuickstart;
import com.modulen.ModuleN.service.DriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("/drive")
@CrossOrigin("*")
public class DriveController {

     @Autowired
    DriveService driveService;


    @GetMapping("/files")
    public ResponseEntity<List<File>> listEverything() throws IOException, GeneralSecurityException {
        List<File> files = driveService.listEverything();
        System.out.println("i am trying to work..");
        return ResponseEntity.ok(files);
    }

    //trying to get much details of the files like webcontentlink and other
//    @GetMapping("/files/list")
//    public ResponseEntity<List<String>> listdetails() throws IOException, GeneralSecurityException {
//        List<String> files = driveService.getFileDetails();
//        System.out.println("i am trying to work.."+files);
//        return ResponseEntity.ok(files);
//    }
}
