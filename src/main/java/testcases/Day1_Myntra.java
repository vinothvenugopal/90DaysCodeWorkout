package testcases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Day1_Myntra {
	//@Test
	public void womenCoatAndJackets() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		ChromeDriver driver = new ChromeDriver(options);
		//ChromeDriver driver = new ChromeDriver();
		System.out.println("Browser Launched");
		driver.manage().window().maximize();
		System.out.println("Browser Mazimized");
		driver.get("https://www.myntra.com/");
		System.out.println("URL Loaded");
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//div[@class='desktop-navLink']//a[@href='/shop/women']")).build().perform();
		System.out.println("Mouse hovered to WOMEN");
		action.moveToElement(driver.findElementByXPath("//a[@href='/women-jackets-coats']")).click().build().perform();
		System.out.println("Coats&Jackets clicked");
		String totalItemCountTop = driver.findElementByXPath("//span[@class='title-count']").getText();
		String totalTopItemNumbers = totalItemCountTop.replaceAll("\\D", "");
		int intTotalTopItems = Integer.parseInt(totalTopItemNumbers);
		System.out.println("Total coats & Jackets For Women is "+intTotalTopItems);
		String jacketsCountString = driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
		String jacketsCountNumber = jacketsCountString.replaceAll("\\D", "");
		int intJacketsCount = Integer.parseInt(jacketsCountNumber);
		System.out.println("Jackets count: "+intJacketsCount);
		String coatsCountString = driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
		String coatsCountNumber = coatsCountString.replaceAll("\\D", "");
		int intCoatCount = Integer.parseInt(coatsCountNumber);
		System.out.println("Coats Count: "+intCoatCount);
		int total = intCoatCount+intJacketsCount;
		if (intTotalTopItems == total) 
		{
			System.out.println("Total number of coats and jackets matches with the individual category");	
		}
		else
		{
			System.out.println("Total number of coats and jackets doesn't matches with the individual category");
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label[text()='Coats']//div")));
		driver.findElementByXPath("//label[text()='Coats']//div").click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//div[@class='brand-more']")));
		driver.findElementByXPath("//div[@class='brand-more']").click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//div[@class='FilterDirectory-titleBar']//input")));
		driver.findElementByXPath("//div[@class='FilterDirectory-titleBar']//input").sendKeys("MANGO");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//label[text()='MANGO']//div[@class='common-checkboxIndicator'])[2]")));
		driver.findElementByXPath("(//label[text()='MANGO']//div[@class='common-checkboxIndicator'])[2]").click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//div[@class='FilterDirectory-titleBar']//span[1]")));
		driver.findElementByXPath("//div[@class='FilterDirectory-titleBar']//span[1]").click();
		Thread.sleep(2000);
		List<WebElement> mangoBrand = driver.findElementsByXPath("//ul[@class='results-base']//h3");
		int brandCount = 0;
		for (WebElement brandElement : mangoBrand ) {
			String brandName = brandElement.getText();
			if(brandName.equals("MANGO"))
			{
				brandCount = brandCount+1;
			}
		}
		if(brandCount == mangoBrand.size())
		{
			System.out.println("All coats displayed belongs to MANGO brand");
		}
		action.moveToElement(driver.findElementByXPath("//span[text()='Recommended']")).build().perform();
		action.moveToElement(driver.findElementByXPath("//label[text()='Better Discount']")).click().build().perform();
		String price = driver.findElementByXPath("(//span[@class='product-discountedPrice'])[1]").getText();
		
		Thread.sleep(2000);
		  List<WebElement> allDiscountedPrice = driver.findElementsByXPath(
		  "//span[@class='product-discountedPrice']");
		  String[] discountedPrice = new String[allDiscountedPrice.size()];
		  for (int i = 0; i < discountedPrice.length; i++) {
			discountedPrice[i] = allDiscountedPrice.get(i).getText();
		}
		  
		  System.out.println("Price of the first item displayed is: "
		  +allDiscountedPrice.get(0).getText());
		 
		Thread.sleep(2000);
		action.moveToElement(driver.findElementByXPath("(//picture[@class='img-responsive'])[1]")).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//span[text()='wishlist now'])[1]")));
		/*
		 * driver.findElementByXPath("(//span[text()='wishlist now'])[1]").click();
		 * if(driver.getTitle().equals("Login")) {
		 * System.out.println("Wishlist clicked successfully. Closing Browser"); } else
		 * { System.out.println("Wishlist click not successful"); }
		 */
		
		//driver.close();
	}
}
