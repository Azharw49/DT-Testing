package com.example.demo.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		 publish = true
		,features = "classpath:features"
		,glue = "com.example.demo.bdd"
		,plugin = {"pretty" ,"json:target/cucumber.json" , "html:target/cucumberreport.html"}
		,monochrome = true,dryRun = false
		//,tags="@filterbutton"		
		)
public class CucumberRunner extends AbstractTestNGCucumberTests{

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		// TODO Auto-generated method stub
		return super.scenarios();
	}
	
	
	

}
