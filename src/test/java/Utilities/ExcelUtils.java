package Utilities;

	import java.io.FileInputStream;
	import java.io.FileOutputStream; 
	import java.io.IOException;
	import org.apache.poi.ss.usermodel.CellStyle; 
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.ss.usermodel.FillPatternType;
	import org.apache.poi.ss.usermodel. IndexedColors;
	import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet; 
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
		public static FileInputStream fi; 
		public static FileOutputStream fo;
		public static XSSFWorkbook wb; 
		public static XSSFSheet ws; 
		public static XSSFRow row; 
		public static XSSFCell cell; 
		public static CellStyle style;
		
	//----get Row Count 
	public static int getRowCount (String xlfile, String xlsheet) throws IOException
	{
	fi=new FileInputStream(xlfile);
	wb=new XSSFWorkbook(fi);
	ws=wb.getSheet (xlsheet);
	int rowcount=ws.getLastRowNum(); wb.close();
	fi.close();
	return rowcount;
	}


	public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException
	{
	fi=new FileInputStream(xlfile);
	wb=new XSSFWorkbook (fi);
	ws=wb.getSheet (xlsheet);
	row=ws.getRow(rownum);
	int cellcount=row.getLastCellNum(); wb.close(); fi.close();
	return cellcount;
	}

	//reading data in from single cell if we want to read data from multiple cells then we call this through looping statement.

	public static String getCellData(String xlfile, String xlsheet, int rownum, int colnum) throws IOException
	{
	fi=new FileInputStream(xlfile);
	wb=new XSSFWorkbook (fi);
	ws=wb.getSheet (xlsheet); row=ws.getRow(rownum); 
	cell=row.getCell(colnum);
	String data;
	try
	{

	//data-cell.toString();
	DataFormatter formatter = new DataFormatter();
	data= formatter.formatCellValue(cell);//Returns the formatted value of a cell as a String regardelss of type
	}

	catch (Exception e)
	{
	data="";
	}
	wb.close();
	fi.close();
	return data;
	}

	//we are reading and write the data in same sheet parallally in same excel sheet

	public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data) throws IOException
	{
	fi=new FileInputStream(xlfile);
	wb=new XSSFWorkbook (fi);
	ws=wb.getSheet(xlsheet);
	row=ws.getRow(rownum);
	cell=row.createCell(colnum);
	cell.setCellValue(data);
	fo=new FileOutputStream(xlfile);
	wb.write(fo);
	wb.close();
	fi.close();
	fo.close();
	}


	public static void fillGreenColor(String xlfile, String xlsheet, int rownum, int colnum) throws IOException
	{
	fi=new FileInputStream(xlfile);
	wb=new XSSFWorkbook (fi); 
	ws=wb.getSheet(xlsheet); 
	row=ws.getRow(rownum); 
	cell=row.getCell(colnum);
	style=wb.createCellStyle();
	style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	cell.setCellStyle(style);
	fo=new FileOutputStream(xlfile);
	wb.write(fo);
	wb.close();
	fi.close();
	fo.close();
	}

	public static void fillREDColor(String xlfile, String xlsheet, int rownum, int colnum) throws IOException
	{
	fi=new FileInputStream(xlfile);
	wb=new XSSFWorkbook (fi); 
	ws=wb.getSheet(xlsheet); 
	row=ws.getRow(rownum); 
	cell=row.getCell(colnum);
	style=wb.createCellStyle();
	style.setFillForegroundColor(IndexedColors.RED.getIndex());
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	cell.setCellStyle(style);
	fo=new FileOutputStream(xlfile);
	wb.write(fo);
	wb.close();
	fi.close();
	fo.close();
	}
	}

