package com.frameium.pageobject;

public class Payload {

	
	public static String TokenGeneration()
	
	{
		
		String requestBody="{\r\n"+
							"\"client_id\": \"wxT3wKcJJMrafPT73VPxzFwfovuHbixh\",\r\n"+
							"\"client_secret\": \"97cAMlQ08F15DadAU40NW8U-2tn-ZUTSEZ_ry2R-KKO3-qqAYT8xIN6qw8wFTAEZ\",\r\n"+
							"\"audience\": \"mafid\",\r\n"+
							"\"grant_type\": \"client_credentials\"\r\n}";

		return requestBody;
		
		
	}
	
	
public static String Carrefour_Web_Full_Earn()
	
	{
		
	String requestBody="{\r\n"+
						"\"h_bit_date\": \"2022-04-12T05:55:11+0400\",\r\n"+
						"\"h_sponsor_id\": 2,\r\n"+
						"\"h_bit_category\": \"ACCRUAL\",\r\n" +
						"\"h_location\": \"AE0000000000231\",\r\n"+
						"\"h_bit_source\": \"WEB\",\r\n"+
						"\"h_bit_type\": \"SPEND\",\r\n"+
						"\"h_bit_amount\": 500.0,\r\n"+
						"\"h_bit_currency\": \"AED\",\r\n"+
						"\"h_program_id\": 4,\r\n"+
						"\"h_member_id\": \"9478400001556949\",\r\n"+
						"\"h_bit_source_generated_id\": \"e2cfa895-e3DGHggfhhghgh555-e339dghfyy99999fe0664f\",\r\n"+
						"\"lines\": [\r\n        {\r\n            \"l_product_external_id\": \"11506\",\r\n"+
						"\"l_quantity\": 1.0,\r\n"+
						"\"l_amount\": 500.0\r\n"+
						"},\r\n        {\r\n            \"l_product_external_id\": \"11530\",\r\n"+
						"\"l_quantity\": 0.0,\r\n"+
						"\"l_amount\": 0.0\r\n        },\r\n        {\r\n            \"l_product_external_id\": \"630818\",\r\n            \"l_quantity\": 0.0,\r\n            \"l_amount\": 0.0\r\n        },\r\n        {\r\n            \"l_product_external_id\": \"919608\",\r\n            \"l_quantity\": 0.0,\r\n            \"l_amount\": 0.0\r\n        },\r\n        {\r\n            \"l_product_external_id\": \"369\",\r\n            \"l_quantity\": 0.0,\r\n            \"l_amount\": 0.0\r\n        },\r\n        {\r\n            \"l_product_external_id\": \"446052\",\r\n            \"l_quantity\": 0.0,\r\n            \"l_amount\": 0.0\r\n        },\r\n        {\r\n            \"l_product_external_id\": \"1095533\",\r\n            \"l_quantity\": 0.0,\r\n            \"l_amount\": 0.0\r\n        }\r\n    ],\r\n    \"payment_details\": []\r\n}";

		return requestBody;
		
		
	}
	
public static String Carrefour_Web_Split_Pay()

{
	
	String requestBody="{\r\n"+
						"\"h_bit_date\": \"2022-04-12T05:55:11+0400\",\r\n"+
						"\"h_sponsor_id\": 2,\r\n"+
						"\"h_bit_category\": \"ACCRUAL\",\r\n"+
						"\"h_location\": \"AE0000000000231\",\r\n"+
						"\"h_bit_source\": \"WEB\",\r\n"+
						"\"h_bit_type\": \"SPEND\",\r\n"+
						"\"h_bit_amount\": 100.0,\r\n"+
						"\"h_pay_in_amount\":10.0,\r\n"+
						"\"h_bit_currency\": \"AED\",\r\n"+
						"\"h_program_id\": 4,\r\n"+
						"\"h_member_id\": \"9478400001556949\",\r\n"+
						"\"h_bit_source_generated_id\": \"e2cfa895-e3d5-49555-e339-jhuy9fe0664f\",\r\n"+
						"\"lines\": [\r\n        {\r\n            \"l_product_external_id\": \"11506\",\r\n"+
						"\"l_quantity\": 1.0,\r\n"+
						"\"l_amount\": 100.0\r\n        },\r\n"+
						"{\r\n            \"l_product_external_id\": \"11530\",\r\n"+
						"\"l_quantity\": 0.0,\r\n"+
						"\"l_amount\": 0.0\r\n        },\r\n"+
						"{\r\n            \"l_product_external_id\": \"630818\",\r\n"+
						"\"l_quantity\": 0.0,\r\n"+
						"\"l_amount\": 0.0\r\n        },\r\n        "+
						"{\r\n            \"l_product_external_id\": \"919608\",\r\n"+
						"\"l_quantity\": 0.0,\r\n"+
						"\"l_amount\": 0.0\r\n"+
						"},\r\n        {\r\n            \"l_product_external_id\": \"369\",\r\n"+
						"\"l_quantity\": 0.0,\r\n"+
						"\"l_amount\": 0.0\r\n        },\r\n        {\r\n            "+
						"\"l_product_external_id\": \"446052\",\r\n"+
						"\"l_quantity\": 0.0,\r\n"+
						"\"l_amount\": 0.0\r\n        },\r\n        {\r\n"+
						"\"l_product_external_id\": \"1095533\",\r\n"+
						"\"l_quantity\": 0.0,\r\n"+
						"\"l_amount\": 0.0\r\n"+
						"}\r\n    ],\r\n    \"payment_details\": []\r\n}";
	return requestBody;
	
	
}


public static String Carrefour_Web_Full_Burn_transaction_using_points_only()

{

String requestBody="{\"h_member_id\":\"9478400001592779\",\r\n\"h_bit_date\":\"2022-06-19T11:06:44+0400\",\r\n\"h_bit_category\":\"ACCRUAL\",\r\n\"h_bit_type\":\"PAYMENT_WITH_POINTS\",\r\n\"h_location\":\"AE0000000000231\",\r\n\"h_bit_source\":\"WEB\",\r\n\"h_pay_in_amount\":10.0,\r\n\"h_bit_currency\":\"AED\",\r\n\"h_bit_source_generated_id\":\"cd81b1fd-0329-5ec9-d585-GHG5bd8c5efd12340017\",\r\n\"h_program_id\":4,\r\n\"h_sponsor_id\":2}";

return requestBody;


}
	
public static String ZephyrTestExecution()



{
String requestBody="{\"projectKey\":\"SHLOYP\","
        + "\"testCaseKey\":\"SHLOYP-T7628\","
        + "\"testCycleKey\":\"SHLOYP-R76\","
        + "\"statusName\":\"StatusResult\","
        + "\"testScriptResults\":"
        + "[{\"statusName\":\"StatusResult\","
        + "\"actualEndDate\":\"2018-05-20T13:15:13Z\","
        + "\"actualResult\":\"ActualTestResults\"}],"
        + "\"comment\":\"AttachmentLink\"}";
return requestBody;

 


}

}