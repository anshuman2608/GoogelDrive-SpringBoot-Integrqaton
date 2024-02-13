package com.modulen.ModuleN.service;


import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.modulen.ModuleN.config.DriveQuickstart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DriveService {

    @Autowired
    DriveQuickstart driveQuickstart;

    public List<File> listEverything() throws IOException, GeneralSecurityException {
        // Print the names and IDs for up to 10 files.
        FileList result = driveQuickstart.getInstanceOfDrive().files().list()
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name, webContentLink, mimeType, thumbnailLink)")
                .execute();
        return result.getFiles();
    }

    public List<String> getFileDetails() throws IOException, GeneralSecurityException {
        // Print the names and IDs for up to 10 files.

        List<File> list=listEverything();
        List<String> list1=new ArrayList<>();
        for(File in:list){

            list1.add(in.getWebContentLink());
        }
        return list1;
    }

}
