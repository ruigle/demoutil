package cn.nong.test;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;



/**
 *
 */
public class Demo2 {
	public static void main(String[] args) {
		String path="C:/Users/admin/Desktop/ceshizhuanyong/jingxian.xls";
		//获取文件类型
		ArrayList<Object> lists = new ArrayList<Object>();
		String fileType = path.substring(path.lastIndexOf(".") + 1);
		FileInputStream is = null;
		POIFSFileSystem fs= null;
		Workbook wb = null;
		try {
			//获取io流
			is = new FileInputStream(new File(path));	
			//判断xls/xlsx
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook(is);
            } else if (fileType.equals("xlsx")) {
                wb = new XSSFWorkbook(is);
            } 
            //读取第一个工作页sheet
            int numberOfSheets = wb.getNumberOfSheets();
            System.out.println("共有"+":"+numberOfSheets);
            ArrayList<Sheet> list1 = new ArrayList<Sheet>();
            for(int i=0;i<numberOfSheets;i++){
            	Sheet sheet = wb.getSheetAt(0);
            	list1.add(sheet);
            }
            Sheet sheet = wb.getSheetAt(0);
            for (Row row : sheet) {
                ArrayList<String> list = new ArrayList<String>();
                Cell cell2 = row.getCell(0);
                for (Cell cell : row) {
                    //根据不同类型转化成字符串
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    list.add(cell.getStringCellValue());
                    int columnIndex = cell.getColumnIndex();
                    System.out.println("columnIndex"+columnIndex);
                }
                lists.add(list);
            }
            
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try{
                if(is != null){
                	is.close();
                } 
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
		System.err.println(lists);
		System.out.println("========================");
		
	}
	@Test
	public  void export() {
		//创建新的工作薄
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet("wo");
        Row row = sheet.createRow((short) 0);
        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();
        
        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();
        
        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());
        
        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置列名
        for(int i=0;i<5;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue("zhnag"+i);
            cell.setCellStyle(cs);
        }
        //设置每行每列的值
        for (short i = 1; i <= 5; i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow((short)i);
            for(short j=0;j<5;j++){
                // 在row行上创建一个方格
                Cell cell = row1.createCell(j);
                cell.setCellValue("zhi");
                cell.setCellStyle(cs2);
            }
        }
        
        String path="C:/Users/admin/Desktop/ceshizhuanyong/";
        File file = new File(path);
        
	}
	
	@Test
	public  void write() {
		String path="C:/Users/admin/Desktop/ceshizhuanyong/ceshi2.xlsx";
		OutputStream out = null;
		try {
            // 获取总列数
            int columnNumCount = 2;
            // 读取Excel文档
            File finalXlsxFile = new File(path);
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workBook.getSheetAt(0);
            /**
             * 删除原有数据，除了属性列
             */
            int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
            System.out.println("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 1; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(path);
            workBook.write(out);
            /**
             * 往Excel中写新数据
             */
            for (int j = 0; j < 5; j++) {
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(j + 1);
                // 得到要插入的每一条记录
                String name = "zhang"+j;
                String address = "henan"+j;
                String phone = "110";
                for (int k = 0; k <= columnNumCount; k++) {
                // 在一行内循环
                Cell first = row.createCell(0);
                first.setCellValue(name);
        
                Cell second = row.createCell(1);
                second.setCellValue(address);
        
                Cell third = row.createCell(2);
                third.setCellValue(phone);
                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(path);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
       
        
	}
	 public static Workbook getWorkbok(File file) throws IOException{
	        Workbook wb = null;
	        FileInputStream in = new FileInputStream(file);
	        if(file.getName().endsWith("xls")){     //Excel&nbsp;2003
	            wb = new HSSFWorkbook(in);
	        }else if(file.getName().endsWith("xlsx")){    // Excel 2007/2010
	            wb = new XSSFWorkbook(in);
	        }
	        return wb;
	    }
	

}
