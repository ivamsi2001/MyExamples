package com.venkat.excel;

import java.io.File;

public class GenerateExcel {

	public static void main(String[] args) {
		try {
			generateExcel("EMP_TABLE_RESULTS","E:\\MyVenkatExcel.xls");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void generateExcel(String resultId, String fileName) throws Exception {
		DataProvider dataProvider = null;
		ResultSetToExcel rsExcel = null;
		try {
			dataProvider = new DataProvider("EMP_TABLE_RESULTS");
			rsExcel = new ResultSetToExcel(dataProvider.getResultSet(), resultId);
			rsExcel.generate(new File(fileName));
		} finally {
			if (dataProvider != null) {
				dataProvider.close();
			}
		}
	}
}
