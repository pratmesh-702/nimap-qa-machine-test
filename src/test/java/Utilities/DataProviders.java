package Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	  @DataProvider(name = "LoginData")
	    public String[][] getLoginData() throws IOException {

	        String path =  System.getProperty("user.dir")
	                + "\\testData\\LoginData.xlsx";
	        
	        System.out.println("Excel Path = " + path);
	                

	        ExcelUtility xlutil = new ExcelUtility(path);

	        int totalRows = xlutil.getRowCount("Sheet1");
	        int totalCols = xlutil.getCellCount("Sheet1",1);

	        String loginData[][] = new String[totalRows][totalCols];

	        for (int i = 1; i <= totalRows; i++) {
	        	
	        	 System.out.println(
	        		        "Row " + i + " => "
	        		        + "[" + xlutil.getCellData("Sheet1", i, 0) + "] "
	        		        + "[" + xlutil.getCellData("Sheet1", i, 1) + "] "
	        		        + "[" + xlutil.getCellData("Sheet1", i, 2) + "]"
	        		    );

	        	
	        	
	        	
	        	
	            for (int j = 0; j < totalCols; j++) {

	                loginData[i - 1][j] = xlutil.getCellData("Sheet1",i, j);
	            }
	        }
	        System.out.println("Rows = " + totalRows);
	        System.out.println("Cols = " + totalCols);
	        
	        return loginData;
	    }
	  
	  @DataProvider(name = "CustomerData")
	    public String[][] getCustomerData() throws IOException {

	      
	        String path = System.getProperty("user.dir") + "\\testData\\LoginData.xlsx";
	        ExcelUtility xlutil = new ExcelUtility(path);

	        
	        String sheetName = "Sheet2"; 

	        int totalRows = xlutil.getRowCount(sheetName);
	        int totalCols = xlutil.getCellCount(sheetName, 1);

	        String customerData[][] = new String[totalRows][totalCols];

	        for (int i = 1; i <= totalRows; i++) {
	            for (int j = 0; j < totalCols; j++) {
	                customerData[i - 1][j] = xlutil.getCellData(sheetName, i, j);
	            }
	        }
	        
	        System.out.println("Second Sheet (" + sheetName + ") Total Rows = " + totalRows);
	        System.out.println("Second Sheet (" + sheetName + ") Total Cols = " + totalCols);

	        return customerData;
	    }

}
