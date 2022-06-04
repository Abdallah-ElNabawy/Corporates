package utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelHandler {
	
	public static Iterator<Row> loadExcelSheetRows(String filePath, int sheetNo) throws IOException,EncryptedDocumentException
	{
		Workbook workbook = null;
		Iterator<Row> rowIterator = null;

		workbook = WorkbookFactory.create(new File(filePath));
		Sheet sheet = workbook.getSheetAt(sheetNo);

		rowIterator = sheet.rowIterator();

		workbook.close();
		
		return rowIterator;
	}
	
	public static ArrayList<String> getExcelRowCells(Row row, short columnsNum)
	{

		DataFormatter dataFormatter = new DataFormatter();
		ArrayList<String> rowCellsValues = new ArrayList<String>();
		

		for(int i=0; i< columnsNum; i++)
		{
			// if there an empty cell in the row, return as empty string not null
			Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

			String cellValue= "";

			if(cell !=null)
				cellValue = dataFormatter.formatCellValue(cell);
			
			rowCellsValues.add(cellValue);
		}

		return rowCellsValues; 
	}


	  
}


