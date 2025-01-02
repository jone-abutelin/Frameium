package com.frameium.testscripts;
/*
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import configuration.config.ObjectReader;
import ExcelUtility.ExcelUtil;
import pageObject.*;
import testScripts.*;
import configuration.config.ConfigReader;
import configuration.config.ObjectReader;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import logger.LoggerHelper;
import testbase.APITestBase;


public class MAF_API extends APITestBase {

	
	private final Logger log = LoggerHelper.getLogger(MAF_API.class);
	ExcelUtil ttmodule = new ExcelUtil();
	String root = System.getProperty ("user.dir");
	String testdataFile = root+"\\src\\main\\resources\\ConfigFiles\\TestData.xlsx";
	Map<String,String> testDataMap = ttmodule.getCellData(testdataFile, "MAF");
	Payload payload=new Payload();
	 Response response2;
	GenericFunctions generic=new GenericFunctions();
	 String accessToken, Payloads;
	@DataProvider(name = "excelData")
    public Object[][] excelDataProvider() throws IOException {
        Object[][] arrObj = ttmodule.getExcelData(
        		testdataFile,
        		"MAF");
        return arrObj;
    }
	
@BeforeClass(description="Generate Token")
	public String TokenGeneration()
	{

		 RestAssured.baseURI = "https://sit.maf-dev.auth0.com";
		 String strresponse =generic.TokenGeneration(payload.TokenGeneration()).asString();
		 JsonPath jsonPath = new JsonPath(strresponse);
		 accessToken = "Bearer "+jsonPath.getString("access_token");
		return accessToken;
		
	}
@Test(description = "API",enabled = true,priority = 1, dataProvider="excelData")
public void MAF_APIValidation(String TestCases,String URL,String EndPoint) throws Exception {
	for (int i=0;i<payload.getClass().getMethods().length-1;i++)			
	{
		if(payload.getClass().getMethods()[i].toString().contains(TestCases))
		{
			payload.getClass().getMethods()[i].invoke(null, null);
			
			try {
				response2 = generic.Carrefour(TestCases,accessToken, URL, EndPoint, "kKUiKT2BBM5YaQDxZxHzANko2lTlf4hi",payload.getClass().getMethods()[i].invoke(null, null).toString());
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 Assertions.assertEquals(200, response2.getStatusCode());
		}
	} 
	
}

}*/

		    
		
