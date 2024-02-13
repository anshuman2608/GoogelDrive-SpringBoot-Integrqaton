package com.modulen.ModuleN.service;


import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.modulen.ModuleN.config.DriveQuickstart;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class SheetService {

    @Autowired
    DriveQuickstart driveQuickstart;



    //method to get internal contents of sheets
    public List<File> listGoogleSheetsWithData() throws IOException, GeneralSecurityException {
        // Print the names and IDs for all Google Sheets files.
        FileList result = driveQuickstart.getInstanceOfDrive().files().list()
                .setPageSize(10)
                .setQ("mimeType='application/vnd.google-apps.spreadsheet'")
                .setFields("nextPageToken, files(id, name, thumbnailLink)")
                .execute();

        List<File> sheets = result.getFiles();

        // Iterate through each Google Sheet and fetch its data
        for (File sheet : sheets) {
            String sheetId = sheet.getId();
            Sheets sheetsService = driveQuickstart.getInstanceOfSheet();
            ValueRange response = sheetsService.spreadsheets().values()
                    .get(sheetId, "A1:ZZ")
                    .execute();

            List<List<Object>> values = response.getValues();
            if (values == null || values.isEmpty()) {
                System.out.println("No data found in sheet: " + sheet.getName());
            } else {
                System.out.println("Data for sheet: " + sheet.getName());
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                System.out.println(gson.toJson(values));
                System.out.println("------------");
            }
        }

        return sheets;
    }

//    private String determineRange(String spreadsheetId) throws IOException, GeneralSecurityException {
//        Sheets service = driveQuickstart.getInstanceOfSheet(); // Implement this method to get the Sheets service
//        ValueRange response = service.spreadsheets().get(spreadsheetId).execute();
//        int lastRow = response.getValues().size();
//        int lastColumn = response.getValues().get(0).size();
//        return "Sheet1!A1:" + convertToLetter(lastColumn) + lastRow; // Assuming your sheet name is "Sheet1"
//    }



    // Method to convert column number to letter (e.g., 1 -> A, 2 -> B, ..., 26 -> Z)
//    private String convertToLetter(int column) {
//        StringBuilder sb = new StringBuilder();
//        while (column > 0) {
//            int remainder = (column - 1) % 26;
//            sb.insert(0, (char) ('A' + remainder));
//            column = (column - 1) / 26;
//        }
//        return sb.toString();
//    }
    public String  getSheetsData(String SheetId) throws GeneralSecurityException, IOException {
        //String range = determineRange(SheetId);
        Sheets sheet=driveQuickstart.getInstanceOfSheet();
        // Dynamically determine columns and rows
        ValueRange response = sheet.spreadsheets().values()
                .get(SheetId, "A1:Z1000") // Adjust range as needed
                .execute();
        List<List<Object>> values = response.getValues();

        if (values != null && !values.isEmpty()) {
            int numColumns = values.get(0).size();
            int numRows = values.size();

            System.out.println("Sheet contains " + numRows + " rows and " + numColumns + " columns.");

            // Create a JSON array to hold the data
            JSONArray jsonData = new JSONArray();
            // Access values dynamically
            for (int i = 0; i < numRows; i++) {
                JSONArray rowArray = new JSONArray();

                for (int j = 0; j < numColumns; j++) {
                    System.out.print(values.get(i).get(j) + ", ");
                    rowArray.put(values.get(i).get(j));

                }
                System.out.println();
                jsonData.put(rowArray);

            }
            String jsonString = jsonData.toString();
            return jsonString;
        } else {
            System.out.println("No data found in the specified range.");
            return "no data found";
        }
    }



}
