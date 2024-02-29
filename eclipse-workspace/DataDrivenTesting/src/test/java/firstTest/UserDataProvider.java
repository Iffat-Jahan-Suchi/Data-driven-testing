package firstTest;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;



public class UserDataProvider {
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static String file="F://Book1.xlsx";
	private static String sheetName="users";
	private XSSFCell cell;
	@DataProvider(name="userData")
	public static Object[][] userDataSet() throws IOException{
		String file="F://Book1.xlsx";
		UserDataProvider udp=new UserDataProvider(file);
		int row=udp.getRowCount();
		int col=udp.getColCount();
		Object[][] data=new Object[row-1][col];
		for(int r=1;r<udp.getRowCount();r++) {
			for(int c=0;c<udp.getColCount();c++) {
				data[r-1][c] =udp.getDatacell(r, c);
				System.out.println(data[r-1][c]);
			}
		}
			
		return data;
	
	}
	
	public UserDataProvider(String file) throws IOException {
		UserDataProvider.file=file;
		try {
			workbook=new XSSFWorkbook(UserDataProvider.file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sheet=workbook.getSheet(sheetName);
		System.out.print(sheet.getSheetName());
	}
	
	public int getRowCount() {
		return sheet.getPhysicalNumberOfRows();
	}
	
	public int getColCount() {
		return sheet.getRow(0).getPhysicalNumberOfCells();
	}
	
	public String getDatacell(int rows,int cols) {
		//return sheet.getRow(row).getCell(col).getStringCellValue();
		try {
			cell=sheet.getRow(rows).getCell(cols);
			String cellData=null;
			switch (cell.getCellType()) {
			case STRING:
				cellData=cell.getStringCellValue();
				break;
			case NUMERIC:
				if(DateUtil.isCellDateFormatted(cell)) {
					cellData=String.valueOf(cell.getStringCellValue());
				}else
				{
					cellData=String.valueOf((long)cell.getNumericCellValue());
				}
				break;
			
			}
			return cellData;
		} 
		
		
		
		catch (Exception e) {
			return "";
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		userDataSet();
	}
}