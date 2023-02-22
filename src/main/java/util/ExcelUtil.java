package util;

import models.Transaction;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtil {
    private static List<Transaction> transactionList;

    public List<Transaction> getFromExcel() throws IOException {
        try {
            if (transactionList != null) {
                return transactionList;
            }

            transactionList = new ArrayList<>();
            URL resource = this.getClass().getClassLoader().getResource("transactionsfile.xlsx");
            new File(resource.toURI());
            FileInputStream file = new FileInputStream(new File(resource.toURI()));
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Transaction transaction = new Transaction();
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                transaction.setTransactionId(cellIterator.next().getStringCellValue());
                transaction.setAccountNumber(cellIterator.next().getStringCellValue());
                transaction.setDate(cellIterator.next().getDateCellValue());
                Cell cell = cellIterator.next();
                if (cell.getCellType() == CellType.STRING) {
                    transaction.setTransactionDetails(cell.getStringCellValue());
                } else {
                    transaction.setTransactionDetails(cell.getNumericCellValue() + "");
                }

                transaction.setWithdrawalAmount(cellIterator.next().getNumericCellValue());
                transaction.setDepositAmount(cellIterator.next().getNumericCellValue());
                transaction.setBalance(cellIterator.next().getNumericCellValue());
                transaction.setStatus(cellIterator.next().getStringCellValue());

                transactionList.add(transaction);
            }
            file.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactionList;
    }
}
