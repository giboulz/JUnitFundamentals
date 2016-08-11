package com.gbz.JUnitTest;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTests {

	@Test
	public void can_open_google(){
		WebDriver driver = new ChromeDriver();  
		driver.get("http://google.com");
		WebElement searchBox = driver.findElement(By.name("q")); 
		searchBox.sendKeys("test");
		searchBox.submit();
	}
}
