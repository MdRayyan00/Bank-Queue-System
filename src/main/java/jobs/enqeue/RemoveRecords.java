package jobs.enqeue;

import models.Transaction;
import util.ExcelUtil;

import java.io.IOException;
import java.util.List;

public class RemoveRecords {
    public static void main(String[] args) throws IOException {
        List<Transaction> fromExcel = new ExcelUtil().getFromExcel();

    }
}
