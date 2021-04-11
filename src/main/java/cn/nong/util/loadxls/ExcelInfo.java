package cn.nong.util.loadxls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.StringUtil;

import cn.nong.util.baijingwei.BaiduMapUtils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;



/**
 *
 */
public class ExcelInfo  {


	public void doExcel(String filePath) throws IOException{
		if(filePath == null || "".equals(filePath)){
		}else{
			readWrite(filePath);
		}
	}
	
public String readWrite(String file){
		try {
            InputStream is = new FileInputStream(file);
            Map<Integer, Map<Integer, String>> content = new HashMap<Integer, Map<Integer, String>>();
	        String str = "";
	        HSSFWorkbook  wb=new HSSFWorkbook();
	        POIFSFileSystem fs;
	        HSSFSheet sheet;
	        HSSFRow row ;
	        try {
	        	fs = new POIFSFileSystem(is);
	        	wb = new HSSFWorkbook(fs);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        sheet= wb.getSheetAt(0);
	        // 得到总行数
	        int rowNum = sheet.getLastRowNum();
	        row = sheet.getRow(0);
	        int colNum = row.getPhysicalNumberOfCells();
	        if(colNum < 12){
	        	colNum = 12;
	        }
	        // 正文内容应该从第二行开始,第一行为表头的标题
	        for (int i = 1; i <= rowNum; i++) {
	            row = sheet.getRow(i);
	            int j = 0;
	            Map<Integer, String> map = new HashMap<Integer, String>();
	            while (j < colNum) {
				// 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
				// 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
				// str += getStringCellValue(row.getCell((short) j)).trim() +
				// "-";
	                //str += getCellFormatValue(row.getCell((short) j)).trim() + "    ";
	                map.put(j, getCellFormatValue(row.getCell((short) j)).trim());
	            	System.out.println(j+getCellFormatValue(row.getCell((short) j)).trim());
	                j++;
	            }
	            content.put(i, map);
	            str = "";
	        }
            //excel数据存放map集合
            
            System.out.println("rowNum:"+content.size());
            for (int i = 4; i <= content.size(); i++) {
            	Map<Integer, String> cellMap = content.get(i);
            	String returnStr = doSql(cellMap);
            	test(file, i, returnStr);
            }
            //写文件
			//out.write("测试java 文件操作\r\n".getBytes());
			//out.close();   
        } catch (Exception e) {
            System.out.println("未找到指定路径的文件!");
            e.printStackTrace();
        }
        return null;
	}


public String doSql(Map<Integer, String> cellMap){
	String password = "";
	String returnStr="";
	try {
		String cityName = cellMap.get(0);
		String areaName = cellMap.get(1);
		String townName = cellMap.get(2);
		String cunName = cellMap.get(3);
		String address = cityName+areaName+townName+cunName;
		Map<String, Double> map = BaiduMapUtils.getLngAndLat(address);
		for (String str : map.keySet()) {
			Double double1 = map.get(str);
			String jingwei = String.valueOf(double1);
			if(StringUtils.isEmpty(returnStr)){
				returnStr+=jingwei;
			}else{
				returnStr+=","+jingwei;
			}
		}
	} catch (Exception e) {
		return "异常"+e.getMessage();
	}
	return returnStr;
}


/**
 * 根据HSSFCell类型设置数据
 * @param cell
 * @return
 */
private String getCellFormatValue(HSSFCell cell) {
    String cellvalue = "";
    if (cell != null) {
    	// 判断当前Cell的Type
        switch (cell.getCellType()) {
        // 如果当前Cell的Type为NUMERIC
        case HSSFCell.CELL_TYPE_NUMERIC:
        case HSSFCell.CELL_TYPE_FORMULA: {
        	// 判断当前的cell是否为Date
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
            	// 如果是Date类型则，转化为Data格式

            	//方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
            	//cellvalue = cell.getDateCellValue().toLocaleString();

            	//方法2：这样子的data格式是不带带时分秒的：2011-10-12
                Date date = cell.getDateCellValue();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                cellvalue = sdf.format(date);

            }
            // 如果是纯数字
            else {
            	DecimalFormat df = new DecimalFormat("#");
            	// 取得当前Cell的数值
                cellvalue = String.valueOf(df.format(cell.getNumericCellValue()));
            }
            break;
        }
        // 如果当前Cell的Type为STRIN
        case HSSFCell.CELL_TYPE_STRING:
        	// 取得当前的Cell字符串
            cellvalue = cell.getRichStringCellValue().getString();
            break;
            // 默认的Cell值
        default:
            cellvalue = " ";
        }
    } else {
        cellvalue = "";
    }
    return cellvalue;

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
