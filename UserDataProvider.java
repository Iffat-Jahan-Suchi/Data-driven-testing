package firstTest;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class UserDataProvider {
	
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static String file="F://Book1.xlsx";
	private static String sheetName="users";
	
	
	@DataProvider(name="userData")
	public static  Object[][] userDataSet()
	{
		
		 String file="F://Book1.xlsx";
			UserDataProvider udp=new UserDataProvider(file);
				int row=udp.getRowCount();
				int col=udp.getColCount();
				
				Object[][]data=new Object[row-1][col];
			
			for(int r=1;r<udp.getRowCount();r++)
			{
				for(int c=0;c<udp.getColCount();c++)
				{
					data[r-1][c]=udp.getCellDataCount(r, c);
					//System.out.println(data[r-1][c]);
				}
				
			}
			return data;
	
	}
	
	public  UserDataProvider(String file)
	{
		UserDataProvider.file=file;
		try {
			workbook=new XSSFWorkbook(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(workbook);
		sheet=workbook.getSheet(sheetName);
		System.out.println(sheet.getSheetName());
		
	}
	public int getRowCount()
	{
		return sheet.getPhysicalNumberOfRows();
	}
	
	public int getColCount()
	{
		return sheet.getRow(0).getPhysicalNumberOfCells();
	}
	
	public String getCellDataCount(int row,int col)
	{
		return sheet.getRow(row).getCell(col).getStringCellValue();
	}
	
	public static void main(String[] args)  {
		
		userDataSet();
	}

}
