package com.flip;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;

public class Login {

	private static TimeUnit seconds;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "E://SeleniumByChetan//setupfiles//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//WebDriver driver = new FirefoxDriver();
		//System.setProperty("webdriver.ie.driver", "E://SeleniumByChetan//setupfiles//IEDriverServer.exe");
		//WebDriver driver=new InternetExplorerDriver();
		//WebDriverWait  wait=new WebDriverWait(driver,10);
		driver.get("http://www.flipkart.com/");
		//driver.manage().timeouts().implicitlyWait(10,seconds);
		//driver.manage().window().maximize();
		//wait.until(expec);
		String title=driver.getTitle();
		System.out.println("Title is :"+title);
		//driver.manage().timeouts().implicitlyWait(10,seconds);
		//driver.findElement(By.xpath(".//*[@id='container']/div/div/header/div[2]/div/div[2]/div/form/div/input")).sendKeys("Routers");
		//driver.findElement(By.className("vh79eN")).click();
		//driver.findElement(By.xpath(".//*[@id='products']/div/div[1]/div[1]/div/div[2]/div[1]/a")).click();
		//driver.findElement(By.xpath(".//*[@id='fk-mainbody-id']/div/div[7]/div/div[3]/div/div/div[6]/div/div[2]/div[1]/div/div[2]/div/div[2]/form/input[10]")).click();
		driver.findElement(By.linkText("Log In")).click();
		//driver.findElement(By.name("otracker"));
		//driver.findElement(By.linkText("Download App"));	
		driver.findElement(By.xpath(".//*[@id='fk-mainbody-id']/div/div/div[1]/div/div[4]/div[2]/input")).sendKeys("7507831230");
		driver.findElement(By.xpath(".//*[@id='fk-mainbody-id']/div/div/div[1]/div/div[4]/div[4]/input")).sendKeys("Vedika@1");
		driver.findElement(By.cssSelector(".submit-btn.login-btn.btn")).click();
		
		
		
		}

}
