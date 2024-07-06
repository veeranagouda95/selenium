package com.github.genericlibery;

import java.lang.reflect.Method;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Base_Test extends FrameworkLibery{

	@BeforeSuite
	public void configReports() {
	  sparkReporter=new ExtentSparkReporter("./RestAssuredBasics/Reports");
	}
	@BeforeClass
	public  void intializeReporter() {
		
		reports=new ExtentReports();
		reports.attachReporter(sparkReporter);
		
	}
	@BeforeMethod
	public void beforeTest(Method method) {
		test=reports.createTest(method.getName());
	}
	
	@AfterSuite
	public void finishReport() {
		reports.flush();
	}
}
