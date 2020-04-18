package testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Day4_HPStore {

	@Test
	public void laptopOrder() throws InterruptedException
	{
		//1) Go to https://store.hp.com/in-en/
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		System.out.println("Browser Launched");
		driver.manage().window().maximize();
		driver.get("https://store.hp.com/in-en/");
		System.out.println("URL Loaded");
		
		//2) Mouse over on Laptop	
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Actions action = new Actions(driver);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@role='menuitem']//span[text()='Laptops']")));
		action.moveToElement(driver.findElementByXPath("//a[@role='menuitem']//span[text()='Laptops']")).click().build().perform();
		System.out.println("Laptops selected");
		driver.findElementByXPath("(//a[@role='menuitem']//span[text()='Pavilion'])[1]").click();
		System.out.println("Pavilion Clicked");
		
		//3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Accept Cookies']")));
		driver.findElementByXPath("//button[@aria-label='Accept Cookies']").click();
		System.out.println("Accept Cookies button clicked");
		JavascriptExecutor js =  (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 200)");
		action.moveToElement(driver.findElementByXPath("(//span[text()='Processor'])[2]")).click().build().perform();
		driver.findElementByXPath("(//dt[@class='filter-options-title'])[4]").click();
		System.out.println("Processor element expanded");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='item']//span[text()='Intel Core i7'])[1]")));
		driver.findElementByXPath("(//li[@class='item']//span[text()='Intel Core i7'])[1]").click();
		System.out.println("Processor Intel Core i7 selected");
		
		//4) Hard Drive Capacity -->More than 1TB
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='item']//span[text()='More than 1 TB'])[1]")));
		driver.findElementByXPath("(//li[@class='item']//span[text()='More than 1 TB'])[1]").click();
		System.out.println("Hard Disk capacity More than 1 TB selected");
		
		//5) Select Sort By: Price: Low to High
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("sorter")));
		WebElement sortByElement = driver.findElementById("sorter");
		Select sortBy = new Select(sortByElement);
		sortBy.selectByValue("price_asc");
		System.out.println("Sorted by Price Low to High");
		
		//6) Print the First resulting Product Name and Price
		//List<WebElement> allLaptopsList = driver.findElementsByXPath("//li[@class='item product product-item']");
		System.out.println("Product Name of the first displayed laptop is "+driver.findElementByXPath("(//a[@class='product-item-link'])[1]").getText());
		String lapTopPrice = driver.findElementByXPath("(//span[@class='price-wrapper '])[1]").getText();
		//lapTopPrice = lapTopPrice.replaceAll("\\D", "");
		System.out.println("Price for the first displayed laptop is "+lapTopPrice);
		
		//7) Click on Add to Cart
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='action tocart primary'])[1]")));
		driver.findElementByXPath("(//button[@class='action tocart primary'])[1]").click();
		
		//8) Click on Shopping Cart icon --> Click on View and Edit Cart
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='action showcart']")));
		driver.findElementByXPath("//a[@class='action showcart']").click();
		driver.findElementByXPath("//a[@class='action primary viewcart']").click();
		
		//9) Check the Shipping Option --> Check availability at Pincode
		if(driver.findElementsByXPath("//input[@name='pincode']").size()>0) {
			driver.findElementByXPath("//input[@name='pincode']").sendKeys("600073");
			driver.findElementByXPath("//button[text()='check']").click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='estimate']")));
			System.out.println(driver.findElementByXPath("//div[@class='estimate']").getText()+
			driver.findElementByXPath("//div[@class='delivery-days']//span[1]").getText()+
			driver.findElementByXPath("//div[@class='delivery-days']//span[2]").getText());
		}
		else 
		{
			System.out.println(driver.findElementByXPath("//div[@class='estimate']").getText());
		}
			String cartTotal = driver.findElementByXPath("//tr[@class='grand totals']//span").getText();
			//cartTotal = cartTotal.replaceAll("\\D", "");
			
			//10) Verify the order Total against the product price
			//11) Proceed to Checkout if Order Total and Product Price matches
			if(cartTotal.equals(lapTopPrice))
			{
				System.out.println("Laptop price matches with cart price");
				driver.findElementById("sendIsCAC").click();
				System.out.println("Proceed to check out button clicked");
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Place Order'])[3]")));
				
				
//				/12) Click on Place Order
				driver.findElementByXPath("(//span[text()='Place Order'])[3]").click();
				wait.until(ExpectedConditions.elementToBeClickable(By.id("customer-email-error")));
				System.out.println("Place Order Button clicked");
				
				//13) Capture the Error message and Print
				System.out.println("Error Message "+driver.findElementByXPath("(//div[@id='shipping']//span)[3]").getText()+" "+driver.findElementById("customer-email-error").getText());
			}
			else
			{
				System.out.println("Cart value doesn't matches with the laptop price");
			}
//			14) Close Browser
			driver.quit();
	}
}
