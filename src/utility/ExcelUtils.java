package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static FileInputStream fist;
	public static XSSFWorkbook book;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	
	public static void setExcelFile(String filePath,String SheetName) throws IOException
	{
		FileInputStream fist=new FileInputStream(filePath);
	    book=new XSSFWorkbook(fist);
	    sheet=book.getSheet(SheetName);
	}
	
	public static String getData(int rowNum,int colNum,String SheetName) 
	{
	    row=sheet.getRow(rowNum);
	    cell=row.getCell(colNum);
		if((cell)!=null)
		{
			return cell.getStringCellValue();
		}
		
		return "";
		
		
	}
	
	

}
