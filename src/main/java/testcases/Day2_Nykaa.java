package testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Day2_Nykaa {
	@Test
	public void nykaaAddProduct() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		ChromeDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//ChromeDriver driver = new ChromeDriver();
		System.out.println("Browser Launched");
		driver.manage().window().maximize();
		System.out.println("Browser Maximized");
		
		//1) Go to https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");
		System.out.println("URL Loaded");
		
		//2) Mouseover on Brands and Mouseover on Popular
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//a[text()='brands']")).build().perform();
		System.out.println("Brands Selected");
		action.moveToElement(driver.findElementByXPath("//a[text()='Popular']")).build().perform();
		System.out.println("Popular Selected");
		action.moveToElement(driver.findElementByXPath("(//li[@class='brand-logo menu-links']//img)[5]")).click().build().perform();
		System.out.println("L'Oreal Brand Clicked");
		
		//4) Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));
		System.out.println("Switched to new Window");
		String windowTitle = driver.getTitle();
		if(windowTitle.contains("L'Oreal"))
		{
			System.out.println("Page title contains the product - L-Oreal");
		}
		else
		{
			System.out.println("Page title doesn't contains the product - L'oreal");
		}
		//5) Click sort By and select customer top rated
		driver.findElementByXPath("//span[@class='pull-right']").click();
		driver.findElementByXPath("//span[text()='customer top rated']").click();
		System.out.println("Customer Top rated clicked");
		//6) Click Category and click Shampoo
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//div[text()='Category']")));
		action.moveToElement(driver.findElementByXPath("//div[text()='Category']")).click().build().perform();
		//driver.findElementByXPath("//div[text()='Category']").click();
		System.out.println("Category Clicked");
		Thread.sleep(2000);
		action.moveToElement(driver.findElementByXPath("(//label[@for='chk_Shampoo_undefined']//span)[1]")).click().build().perform();
		//driver.findElementByXPath("(//label[@for='chk_Shampoo_undefined']//span)[1]").click();
		System.out.println("Shampoo Clicked");
		
		//7) check whether the Filter is applied with Shampoo
		String selectedShampoo = driver.findElementByXPath("//li[text()='Shampoo']").getText();
		if(selectedShampoo.contains("Shampoo"))
		{
			System.out.println("Filter is applied for Shampoo");
		}
		else
		{
			System.out.println("Filter is not applied for Shampoo");
		}
		
		//8) Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElementByXPath("//div[@class='m-content__product-list__title']//span[contains(text(),'Protect')]").click();
		System.out.println("L'Oreal Color Protect Shampoo Clicked");
		Set<String> windowHandlesSet2 = driver.getWindowHandles();
		List<String> windowHandlesList2 = new ArrayList<String>(windowHandlesSet2);
		driver.switchTo().window(windowHandlesList2.get(2));
		System.out.println("Switched to 3rd window");
		
		//9) GO to the new window and select size as 175ml
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='175ml']")));
		driver.findElementByXPath("//span[text()='175ml']").click();
		System.out.println("175 ml size clicked");
		
		//10) Print the MRP of the product
		System.out.println("MRP Printed is "+driver.findElementByXPath("(//span[@class='post-card__content-price-offer'])[1]").getText());
		
		//11) Click on ADD to BAG
		driver.findElementByXPath("//div[@class='pull-left']//button").click();
		driver.findElementByXPath("//div[@class='AddBagIcon']").click();
		System.out.println("Add to bag clicked");
		
		//12) Go to Shopping Bag 
		Thread.sleep(3000);
		driver.findElementByXPath("//div[@class='second-col']//button").click();
		
		//13) Print the Grand Total amount
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//div[@class='value'])[2]")));
		String grandTotal = driver.findElementByXPath("(//div[@class='value'])[2]").getText();
		System.out.println("Grand Total :"+grandTotal.replaceAll("\\D", ""));
		
		//14) Click Proceed
		driver.findElementByXPath("(//button[contains(@class,'btn full')])[2]").click();
		
		//16) Print the warning message (delay in shipment)
		System.out.println("Warning Message : "+driver.findElementByXPath("//div[@class='message']").getText());
		
		//17) Close all windows
		driver.quit();
	}

}
