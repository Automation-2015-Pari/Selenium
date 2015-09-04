package com.demo.test.SelNG.FEAutomation.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.demo.test.SelNG.FEAutomation.library.CommonLibrary;

public class FirefoxTest {
	public static FirefoxDriver driver;
	@BeforeSuite(groups = { "firefox" })
	public static void openBrowser(){	
		driver = new FirefoxDriver();
		driver.get(Constants.BASE_URL);
	}
	
	@Test(groups={"smoke"})
	public static void clickonYahooLogo() {
		System.out.println("Inside the clickonYahooLogo");
		driver.findElement(By.xpath(CommonLibrary.getPath("yahoo", "yahooLogo", "logo"))).click();
		System.out.println("clicked on the yahoo link");
	}
}
