package com.braisedpanda.dooraccess.controller;

import com.braisedpanda.dooraccess.domian.po.Person;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @RequestMapping("upload")
    public void updateExcel(@RequestBody MultipartFile file) {
        try{
            Workbook workbook = getWorkBook(file);//获取工作簿workbook
            Sheet sheetAt = workbook.getSheetAt(0);//根据工作簿获取整张excel表的信息
            List<Person> personList = new ArrayList<>();
            for (int i=1; i<= sheetAt.getLastRowNum(); i++){//第一行是表头，所以不要，i从1开始
                Person person = new Person();
               for(int j=0; j < sheetAt.getRow(i).getLastCellNum(); j++){//循环每一行

                                    Cell cell = sheetAt.getRow(i).getCell(j);//获取每一个单元格的值
                                    String value = getValue(cell);//把单元格的值转成字符串
                                    if(j == 0){
                                        person.setId(value);
                                        System.out.print("ID:"+value+" ");
                                    }
                                    if(j == 1){
                                        person.setUsername(value);
                                        System.out.print("姓名:"+value+" ");
                                    }
                                    if(j == 2){
                                        person.setCategory(value);
                                        System.out.print("部门:"+value+" ");
                                    }

                }
               personList.add(person);
                System.out.println();
                }
            System.out.println("List:"+personList.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

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
