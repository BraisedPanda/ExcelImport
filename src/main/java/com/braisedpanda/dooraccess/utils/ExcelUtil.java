package com.braisedpanda.dooraccess.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtil {
    public static Workbook getWorkBook(MultipartFile file) {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        Sheet sheet = null;
        try {
            //获取excel文件的io流
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if(fileName.endsWith("xls")){
                //2003
                POIFSFileSystem poifsFileSystem = new POIFSFileSystem(is);
                workbook = new HSSFWorkbook(poifsFileSystem);
                sheet = workbook.getSheetAt(0);
            }else if(fileName.endsWith("xlsx")){
                //2007 及2007以上
                workbook = new XSSFWorkbook(is);
                sheet = workbook.getSheetAt(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }


    public static String getValue(Cell cell){
        if(cell.getCellTypeEnum() == org.apache.poi.ss.usermodel.CellType.BOOLEAN){
            return String.valueOf(cell.getBooleanCellValue());
        }else if(cell.getCellTypeEnum() == org.apache.poi.ss.usermodel.CellType.NUMERIC){
            String value = "";
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                Date d = cell.getDateCellValue();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                value = sdf.format(d);
            }else{
                double temp = cell.getNumericCellValue();
                //value = new BigDecimal(temp).toString();
                value = String.valueOf(temp);
            }
            return value;
        }else if (cell.getCellTypeEnum() == org.apache.poi.ss.usermodel.CellType.STRING){
            return String.valueOf(cell.getStringCellValue());
        }else{
            return String.valueOf(cell.getStringCellValue());
        }
    }
}
