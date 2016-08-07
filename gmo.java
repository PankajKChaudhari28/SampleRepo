package com.flip;

//package gmoOnline;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class gmo {

static	WebDriver wd;
static WebElement we;

public static void main(String[] args) {
		
		openBrowser();
		gmoHome();
		catlog();
		placeOrder();
		billingInfo();
		receipt();
		closeBrowser();
		
	}

	private static void openBrowser() {
		
	 wd=new FirefoxDriver();
	 wd.manage().window().maximize();
	 wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	private static void gmoHome() {
		
		wd.get("http://demo.borland.com/gmopost/");
		
		System.out.println("Navigated to : "+wd.getTitle());
	
		
  we=wd.findElement(By.name("bAbout"));
	  we.click();
	  System.out.println("Navigated to : "+wd.getTitle());
	  wd.navigate().back();
    	System.out.println("Navigated to : "+wd.getTitle());
		
	  we=wd.findElement(By.name("bBrowserTest"));
	  we.click();
	  System.out.println("Navigated to : "+wd.getTitle());
	  wd.navigate().back();
	 System.out.println("Navigated to : "+wd.getTitle());
		
		
		we=wd.findElement(By.name("bSubmit"));
		  we.click();
		  wd.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		 
		
	}

	private static void catlog() {
		  System.out.println("Navigated to : "+wd.getTitle());
			verifylinks();
		  
		  selectCatlog();
		 ExtractTableValues();
		 we=wd.findElement(By.name("bSubmit"));
			we.click();
			
			while(isAlertPresent())
			{
				System.out.println("Alert: "+wd.switchTo().alert().getText());
				wd.switchTo().alert().accept();
				selectCatlog();
				 ExtractTableValues();
				we=wd.findElement(By.name("bSubmit"));
				we.click();
			}
		wd.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	private static void verifylinks() {
		
	WebElement getlinks	=wd.findElement(By.xpath("//a/strong"));
		int nooflinks=getlinks.findElements(By.xpath("//a/strong")).size();
		System.out.println("No of links :"+nooflinks);
	
		for(int i=2; i<=nooflinks+1;i++)
		{
			we=wd.findElement(By.xpath("//tr["+i+"]/td[2]/a/strong"));
		String linkName= we.getText();
		we.click();
		we=wd.findElement(By.xpath("//h2["+(i-1)+"]/a"));
		String linkname2= we.getText();
  
		if(linkName.equals(linkname2))
		{
			System.out.println("linkName: "+linkName+ " is matched");
		}
		else
		{
			System.out.println("linkName: "+linkName+ " didn't matched");
			
		}
			
		wd.navigate().back();
		}
		
	}

	private static void ExtractTableValues() 
	{
		WebElement Table= wd.findElement(By.xpath("html/body/form/table/tbody/tr[2]/td/div/center/table/tBody"));
		int rows= Table.findElements(By.xpath("tr")).size();
		int cols= Table.findElements(By.xpath("tr[1]/td")).size();

		for(int row=1;row<=rows;row++)
		{
			for(int col=1;col<=cols;col++)
			{
				String CellData= Table.findElement(By.xpath("tr["+row+"]/td["+col+"]")).getText();
		
				if (CellData.isEmpty())
				{
					we= Table.findElement(By.xpath("tr["+row+"]/td["+col+"]/h1/input"));
					CellData=we.getAttribute("name")+ "=" +we.getAttribute("value");
				}
				System.out.print(CellData+ "\t \t\t    ");
			}
			System.out.println();
		}
	}

	private static boolean isAlertPresent() 
	{
		try
		{
		Alert alert= wd.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException nape)
	 	{
			//nape.printStackTrace();
			return false;
		}
	}

	private static void selectCatlog() {
		
	we=wd.findElement(By.xpath("//h1/input"));
		
		List<WebElement> InputNamelist = wd.findElements(By.xpath("//input[@type='text']"));
		System.out.println("No. of Textboxes: "+InputNamelist.size());
		int noOfinputs=InputNamelist.size();
		int index= (int) (Math.random()*noOfinputs);
		System.out.println("Random Textbox #: "+index);
		InputNamelist.get(index).clear();
		
		int orderQty= (int)(Math.random()*2);
		System.out.println("Random Order Qty :"+orderQty);
		InputNamelist.get(index).sendKeys(String.valueOf(orderQty));
			
	}

	private static void placeOrder() {
	
	
		ExtractPlaceOrderDetails();
		wd.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		we=wd.findElement(By.name("bSubmit"));
		we.click();
		wd.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	private static void ExtractPlaceOrderDetails() {
		
		
		WebElement Table= wd.findElement(By.xpath("html/body/form/table/tbody/tr[1]/td/div/center/table/tbody"));
		int rows= Table.findElements(By.xpath("tr")).size();
		for(int row=1;row<=rows;row++)
		{
			int cols= Table.findElements(By.xpath("tr["+row+"]/td")).size();
			for(int col=1;col<=cols;col++)
			{
				String CellData= Table.findElement(By.xpath("tr["+row+"]/td["+col+"]")).getText();		
				System.out.print(CellData+ "\t \t\t    ");
			}
			System.out.println();
		}
		
	}
	

	

	private static void billingInfo() {
		we=wd.findElement(By.name("billName"));
		we.clear();
		we.sendKeys("Dhiresh");
		we=wd.findElement(By.name("billAddress"));
		we.clear();
		we.sendKeys("Market yard");
		
		we=wd.findElement(By.name("billCity"));
		we.clear();
		we.sendKeys("Pune");
		
		we=wd.findElement(By.name("billState"));
		we.clear();
		we.sendKeys("Maharashtra");
		
		we=wd.findElement(By.name("billZipCode"));
		we.clear();
		we.sendKeys("11111");

		we=wd.findElement(By.name("billPhone"));
		we.clear();
		we.sendKeys("6158585852");

		String[] billingInfo= new String[10];	
		
		we=wd.findElement(By.name("billEmail"));
		we.clear();
		we.sendKeys("d@d.com");

		String selectedcard=selectCard();

	  if(selectedcard.equalsIgnoreCase("amex"))
		{
			wd.findElement(By.name("CardNumber")).sendKeys("123456789123456");				
		}
		else
		{
			wd.findElement(By.name("CardNumber")).sendKeys("1234567891234567");
			
		}
			

		we=wd.findElement(By.name("CardDate"));
		we.clear();
		we.sendKeys("02/22");

		we=wd.findElement(By.name("shipSameAsBill"));
		we.click();
		
		boolean selected=verifycheckboxselected();
		
		if(selected==true)
		{
		 System.out.println("CheckBox Value :"+selected);
		 		 
		}
		
		
		we=wd.findElement(By.cssSelector("[value='Place The Order']"));
		we.click();
		wd.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

	}

	private static boolean verifycheckboxselected() {
		
		boolean selection=we.isSelected();
		if(selection==true)
		{
		return true;
		}
		else return false;
		
	}

	private static String selectCard() {
	
		we=wd.findElement(By.name("CardType"));
		Select cardType= new Select(we);
		
		int noOfCards= cardType.getOptions().size();
		int index=(int)(Math.random()*noOfCards);
		//cardType.selectByIndex(index);
		System.out.println("Random Number: " +index);
		index=index+1;
		we=wd.findElement(By.cssSelector("select[name='CardType'] option:nth-child("+index+")"));
		we.click();
		System.out.println("Random Number: " +index+ " Card Selected:" +we.getAttribute("value"));
		return we.getAttribute("value");
	   
	}
	
	
	private static void receipt() {
		
		we.findElement(By.xpath("//a/strong"));
     	we.click();
     	wd.navigate().back();   

		we=wd.findElement(By.cssSelector("[value='Return to Home Page']"));
		we.click();
		System.out.println("Navigated to : "+wd.getTitle());
		
	}

	private static void closeBrowser() {
		wd.quit();
	}

}

