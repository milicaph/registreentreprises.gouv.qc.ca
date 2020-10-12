package parameters;

import com.sun.deploy.nativesandbox.NativeSandboxOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ReadWriteExcel {
    public static ArrayList<String> getEnterpriseList(){
        ArrayList<String> enterpriseNames = new ArrayList<String>();

        try {
            final String parameterFileLocation = "src/main/resources/Automation INC2.xlsx";
            FileInputStream excelFile = new FileInputStream(new File(parameterFileLocation));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet urlList = workbook.getSheetAt(0);
            Iterator<Row> iterator = urlList.iterator();
            int r = 0;

            while(iterator.hasNext()){

                Row currentRow = iterator.next();
                Cell cell1 = currentRow.getCell(0);
                String cell1Value = "";

                try {
                    cell1Value = cell1.getStringCellValue();
                } catch (Exception ignored) {}

                boolean notEmpty = cell1Value.length() > 3;

                if(notEmpty) {
                    enterpriseNames.add(cell1Value);
                }

                r++;

            }
            return enterpriseNames;

        } catch (IOException e){
            e.printStackTrace();
        }

        return enterpriseNames;

    }

    private static boolean checkIfRowIsEmpty(Row row) {
        if (row == null || row.getLastCellNum() <= 0) {
            return true;
        }
        Cell cell = row.getCell((int) row.getFirstCellNum());
        if (cell == null || "".equals(cell.getRichStringCellValue().getString())) {
            return true;
        }
        return false;

    }

    private static int emptyRowIndex(Sheet sheet){
        Iterator<Row> iterator = sheet.iterator();
        int r = 0;
        while(iterator.hasNext()){

            Row row = iterator.next();
            if (!checkIfRowIsEmpty(row)) {
                r++;
            }

        }

        return r;
    }

    public static void writeExcel(String company,
                           String statut,
                           String addressINC,
                           ArrayList<String> lastNames,
                           ArrayList<String> firstNames,
                           ArrayList<String> address) throws IOException {
        final String parameterFileLocation = "src/main/resources/Automation INC2.xlsx";
        FileInputStream excelFile = new FileInputStream(new File(parameterFileLocation));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(1);

        int r = emptyRowIndex(sheet);
        Row rowX = sheet.createRow(r);
        int hc = firstNames.size();
        //System.out.println("DEBUGGGGG" + hc);
        if(hc == 1){
            Cell cell0 = rowX.createCell(0);
            cell0.setCellValue(company);
            Cell cell1 = rowX.createCell(1);
            cell1.setCellValue(statut);
            Cell cell2 = rowX.createCell(2);
            cell2.setCellValue(addressINC);
            Cell cell3 = rowX.createCell(3);
            cell3.setCellValue(lastNames.get(0));
            Cell cell4 = rowX.createCell(4);
            cell4.setCellValue(firstNames.get(0));
            Cell cell5 = rowX.createCell(5);
            cell5.setCellValue(address.get(0));

        } else if(hc == 2){
            Cell cell0 = rowX.createCell(0);
            cell0.setCellValue(company);
            Cell cell1 = rowX.createCell(1);
            cell1.setCellValue(statut);
            Cell cell2 = rowX.createCell(2);
            cell2.setCellValue(addressINC);
            Cell cell3 = rowX.createCell(3);
            cell3.setCellValue(lastNames.get(0));
            Cell cell4 = rowX.createCell(4);
            cell4.setCellValue(firstNames.get(0));
            Cell cell5 = rowX.createCell(5);
            cell5.setCellValue(address.get(0));
            Cell cell6 = rowX.createCell(6);
            cell6.setCellValue(lastNames.get(1));
            Cell cell7 = rowX.createCell(7);
            cell7.setCellValue(firstNames.get(1));
            Cell cell8 = rowX.createCell(8);
            cell8.setCellValue(address.get(1));

        } else if (hc == 3){
            Cell cell0 = rowX.createCell(0);
            cell0.setCellValue(company);
            Cell cell1 = rowX.createCell(1);
            cell1.setCellValue(statut);
            Cell cell2 = rowX.createCell(2);
            cell2.setCellValue(addressINC);
            Cell cell3 = rowX.createCell(3);
            cell3.setCellValue(lastNames.get(0));
            Cell cell4 = rowX.createCell(4);
            cell4.setCellValue(firstNames.get(0));
            Cell cell5 = rowX.createCell(5);
            cell5.setCellValue(address.get(0));
            Cell cell6 = rowX.createCell(6);
            cell6.setCellValue(lastNames.get(1));
            Cell cell7 = rowX.createCell(7);
            cell7.setCellValue(firstNames.get(1));
            Cell cell8 = rowX.createCell(8);
            cell8.setCellValue(address.get(1));
            Cell cell9 = rowX.createCell(9);
            cell9.setCellValue(lastNames.get(2));
            Cell cell10 = rowX.createCell(10);
            cell10.setCellValue(firstNames.get(2));
            Cell cell11 = rowX.createCell(11);
            cell11.setCellValue(address.get(2));
        }

        try {
            FileOutputStream outputStream = new FileOutputStream("src/main/resources/Automation INC2.xlsx");
            workbook.write(outputStream);
        } catch (IndexOutOfBoundsException | IOException e) {
            e.printStackTrace();
        }


    }



}
