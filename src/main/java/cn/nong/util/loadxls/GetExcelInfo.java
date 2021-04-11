package cn.nong.util.loadxls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;



/**
 *
 */
public class GetExcelInfo  {
	public static void main(String[] args) {

        // 读取Excel文件
        File file = new File("E:/allData.xls");
        try {
            //得到所有数据
            List<List<String>> allData=readExcel(file);
            makeExcel(allData);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	/**
     * 获取数据
     * @param file
     * @return
     * @throws Exception
     */
    private static List<List<String>> readExcel(File file) throws Exception {

        // 创建输入流，读取Excel
        InputStream is = new FileInputStream(file.getAbsolutePath());
        // jxl提供的Workbook类
        Workbook wb = Workbook.getWorkbook(is);
        // 只有一个sheet,直接处理
        //创建一个Sheet对象
        Sheet sheet = wb.getSheet(0);
        // 得到所有的行数
        int rows = sheet.getRows();
        // 所有的数据
        List<List<String>> allData = new ArrayList<List<String>>();
        // 越过第一行 它是列名称
        for (int j = 1; j < rows; j++) {

            List<String> oneData = new ArrayList<String>();
            // 得到每一行的单元格的数据
            Cell[] cells = sheet.getRow(j);
            for (int k = 0; k < cells.length; k++) {

                oneData.add(cells[k].getContents().trim());
            }
            // 存储每一条数据
            allData.add(oneData);
            // 打印出每一条数据
            //System.out.println(oneData);

        }
        return allData;

    }
    
    /**
     * 将数据写入到excel中
     */
    public static  void makeExcel(List<List<String>> result) {

        //第一步，创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet("病例日期表");
        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row = sheet.createRow(0);
        //第四步，创建单元格，设置表头
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("date");
        cell = row.createCell(1);
        cell.setCellValue("month");
        cell = row.createCell(2);
        cell.setCellValue("weekday");
        cell = row.createCell(3);
        cell.setCellValue("number");
        cell = row.createCell(4);
        cell.setCellValue("IsCaseDay");
        cell = row.createCell(5);
        cell.setCellValue("temperature");
        cell = row.createCell(6);
        cell.setCellValue("airPressure");
        cell = row.createCell(7);
        cell.setCellValue("RH(%)");
        cell = row.createCell(8);
        cell.setCellValue("so2μg/m3");
        cell = row.createCell(9);
        cell.setCellValue("no2μg/m3");
        cell = row.createCell(10);
        cell.setCellValue("pm10μg/m3");
        cell = row.createCell(11);
        cell.setCellValue("co mg/m3");
        cell = row.createCell(12);
        cell.setCellValue("o3-8h μg/m3");
        cell = row.createCell(13);
        cell.setCellValue("pm2.5μg/m3");

        //第五步，写入数据
        for(int i=0;i<result.size();i++) {

            List<String> oneData = result.get(i);
            HSSFRow row1 = sheet.createRow(i + 1);
            for(int j=0;j<oneData.size();j++) {

                 //创建单元格设值
                row1.createCell(j).setCellValue(oneData.get(j));
            }
        }

        //将文件保存到指定的位置
        try {
            FileOutputStream fos = new FileOutputStream("E:\\result.xls");
            workbook.write(fos);
            System.out.println("写入成功");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void test(String filePath,int x,String value) {
		try {
			// 创建Excel的工作书册 Workbook,对应到一个excel文档
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(filePath));
			HSSFSheet sheet=wb.getSheetAt(0);
			HSSFRow row=sheet.getRow(x);
	        // 标题总列数
	        int colNum = row.getPhysicalNumberOfCells();
	        HSSFCell cell = row.createCell(colNum+1);
			cell.setCellValue(value);
			FileOutputStream os;
			os = new FileOutputStream(filePath);
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
    
}
