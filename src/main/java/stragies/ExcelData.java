package stragies;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;

import utilities.ExcelHandler;


public class ExcelData implements stragies.TestDataStrategy {
	
	public ArrayList<ArrayList<Object>> loadTestData(String filePath) throws Exception
	{
		ArrayList<ArrayList<Object>> results = new ArrayList<ArrayList<Object>>();
		
		String excelPath = filePath.split(";")[0];
		String sheetNo = filePath.split(";")[1];

		Iterator<Row> rows = ExcelHandler.loadExcelSheetRows(excelPath, Integer.parseInt(sheetNo));
		short lastColumnsNum = rows.next().getLastCellNum();
		
		while (rows.hasNext()) {
			Row row = rows.next();
			
			ArrayList<String> rowCells = ExcelHandler.getExcelRowCells(row, lastColumnsNum);
			ArrayList<Object> cellsObjects = new ArrayList<Object>();
			
			
			for (int i = 0; i < rowCells.size(); i++) {
				Object cell = new Object();
				cell = rowCells.get(i);
				cellsObjects.add(cell);
			}
			results.add(cellsObjects);
		}		
		return results;		
	}

}
