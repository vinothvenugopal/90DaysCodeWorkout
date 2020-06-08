package cucumber.testcases;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Day1_CarWale {

	ChromeDriver driver;
	WebDriverWait wait;
	
	WebElement ele;
	String format = "";
	List<String> strAllKmsList = new ArrayList<String>();
	List<Integer> intAllKmsList = new ArrayList<Integer>();
	@Given("open carwale site")
	public void openCarWaleWebSite() 
	{
		//29/04/2020
		//==========

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions option = new ChromeOptions();
		option.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(option);
		wait = new WebDriverWait(driver, 30);
		Actions action = new Actions(driver);
		System.out.println("Browser Launched");
		driver.manage().window().maximize();
		System.out.println("Broswer Maximized");
		//1) Go to https://www.carwale.com/
		driver.get("https://www.carwale.com/");
		System.out.println("Url Loaded");
	}
	@And("Click on Used")
	public void clickOnUsed()
	{
		//2) Click on Used
		driver.findElementByXPath("//li[contains(text(),'Used')]").click();
		System.out.println("Clicked On the Used Button");
	}
	@And("Select the City as Chennai")
	public void selectCity() throws InterruptedException
	{
		//3) Select the City as Chennai
		driver.findElementById("usedCarsList").sendKeys("Chennai");
		Thread.sleep(500);
		driver.findElementById("usedCarsList").sendKeys(Keys.TAB);
	}
	@And("Select budget min 8L and max 12L and Click Search")
	public void selectBudget()
	{
		//4) Select budget min (8L) and max(12L) and Click Search
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("minInput")));
		driver.findElementById("minInput").sendKeys("8",Keys.TAB);
		System.out.println("Minimum budget set");
		driver.findElementById("maxInput").sendKeys("12",Keys.TAB);
		System.out.println("Maximum budget set");
		driver.findElementById("btnFindCar").click();
		System.out.println("Search Initiated");
	}
	@And("Select Cars with Photos under Only Show Cars With")
	public void selectCarsWithPhotos()
	{
		//5) Select Cars with Photos under Only Show Cars With
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Cars with Photos']")));
		driver.findElementByXPath("//span[text()='Cars with Photos']").click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@id='filterbyadditional']//span)[1]")));
		if(driver.findElementByXPath("(//span[@id='filterbyadditional']//span)[1]").getText().contains("Cars with Photos"))
		{
			System.out.println("Filter Applied -> Car with Photos");
		}
		else
		{
			System.out.println("Filter -> Car with Photos not applied");
		}
	}
	@And("Select Manufacturer as Hyundai Creta")
	public void selectCarModel() throws InterruptedException
	{
		//6) Select Manufacturer as "Hyundai" --> Creta
		Thread.sleep(2000);
		ele = driver.findElementByXPath("//ul[@id='makesList']//span[contains(text(),'Hyundai')]");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", ele);
		System.out.println("Filter Applied -> Hyundai");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='makesList']//span[contains(text(),'Creta')]")));
		ele = driver.findElementByXPath("//ul[@id='makesList']//span[contains(text(),'Creta')]");
		js.executeScript("arguments[0].click()", ele);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@id='car']//span)[1]")));
		if(driver.findElementByXPath("(//span[@id='car']//span)[1]").getText().contains(("Creta")))
		{
			System.out.println("Filter Applied -> Creta");
		}
		else
		{
			System.out.println("Filter -> Creta not applied");
		}
	}
	@And("Select Fuel Type as Petrol")
	public void selectFuelType()
	{
		//7) Select Fuel Type as Petrol
		ele = driver.findElementByXPath("//h3[contains(text(),'Fuel Type')]");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", ele);
		System.out.println("Fuel Type Expanded");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@name='fuel']//span[text()='Petrol']")));
		ele=driver.findElementByXPath("//div[@name='fuel']//span[text()='Petrol']");
		js.executeScript("arguments[0].click()", ele);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@id='fuel']//span)[1]")));
		if(driver.findElementByXPath("(//span[@id='fuel']//span)[1]").getText().contains("Petrol"))
		{
			System.out.println("Filter Applied -> Petrol");
		}
		else
		{
			System.out.println("Filter -> Petrol not applied");
		}
	}
	@And("Select Best Match as km low to high")
	public void sortCars()
	{
		//8) Select Best Match as "KM: Low to High"r
		WebElement sortElement = driver.findElementById("sort");
		Select sortDD = new Select(sortElement);
		sortDD.selectByVisibleText("KM: Low to High");
		System.out.println("Sorted by KM: Low to High");
	}
	@And("Validate the Cars are listed with KMs Low to High")
	public void validateSortedOrder() throws InterruptedException
	{
		//9) Validate the Cars are listed with KMs Low to High
		Thread.sleep(1000);
		//getting the kms value as WebElements in a list
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='card-detail__vehicle-data']//td[1]/span")));
		List<WebElement> kmsElement = driver.findElementsByXPath("//table[@class='card-detail__vehicle-data']//td[1]/span");
		for (WebElement kms: kmsElement) 
		{
			//storing the km values as string
			strAllKmsList.add(kms.getText());
			//storing the kms values as integer - this wil be used for sorting to find if the sort is done correctly
			intAllKmsList.add(Integer.parseInt(kms.getText().replaceAll("\\D", "")));
		}
		//sorting the integer list
		Collections.sort(intAllKmsList);
		boolean sort = false;
		for (int i = 0; i < intAllKmsList.size(); i++) 
		{
			//checking if the string kms list and the integer kms list contains the same value in each index.
			if(Integer.parseInt(strAllKmsList.get(i).replaceAll("\\D", "")) == intAllKmsList.get(i))
			{
				sort=true;
				System.out.println("Kms are sorted");
			}
			else
			{
				sort=false;
			}
		}
		if(sort == true)
		{
			System.out.println("Vehicles displayed are sorted by Kms low to high");
		}
		else
		{
			//this is just for the display purpose. result will display how the kms should have been sorted and how it is actually sorted
			System.out.println("Vehicles displayed are not sorted by Kms low to high");
			System.out.println("Sort order should have been "+'\t'+"but sorted like");
			for (int i = 0; i < intAllKmsList.size(); i++) 
			{
				System.out.println(NumberFormat.getInstance(Locale.US).format(intAllKmsList.get(i))+" km"+'\t'+'\t'+'\t'+strAllKmsList.get(i));
			}
		}
	}
	@And("Add the least KM ran car to Wishlist")
	public void addToWishList()
	{
		//10) Add the least KM ran car to Wishlist
		format = NumberFormat.getInstance(Locale.US).format(intAllKmsList.get(0))+" km";
		int listIndex = strAllKmsList.indexOf(format);
		ele = driver.findElementByXPath("//span[text()='"+format+"']/ancestor::div[@class='card-detail-block']/preceding-sibling::div//span[contains(@class,'shortlist-icon')]");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", ele);
	}
	@When("Go to Wishlist and Click on More Details")
	public void clickMoreDetails()
	{
		//11) Go to Wishlist and Click on More Details
		driver.findElementByXPath("//li[contains(text(),'Compare')]").click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='More details »']")));
		driver.findElementByXPath("//a[text()='More details »']").click();
	}
	@Then("Print all the details under Overview")
	public void printOverViewDetails()
	{
		//12) Print all the details under Overview 
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));
		List<WebElement> featureElementList = driver.findElementsByXPath("//div[@id='overview']//li/div[@class='equal-width text-light-grey']");
		List<WebElement> valueElemenetList = driver.findElementsByXPath("//div[@id='overview']//li/div[@class='equal-width dark-text']");
		Map<String, String> features = new LinkedHashMap<String, String>();
		for (int i = 1; i <= featureElementList.size(); i++) 
		{
			String feature = driver.findElementByXPath("//div[@id='overview']//li["+i+"]/div[@class='equal-width text-light-grey']").getText().trim();
			String value = driver.findElementByXPath("//div[@id='overview']//li["+i+"]/div[@class='equal-width dark-text']").getText().trim();
			features.put(feature, value);
		}

		System.out.println('\n'+"OverView of the selected Car"+'\n');
		for (Map.Entry<String, String> eachEntry : features.entrySet()) 
		{
			System.out.println(eachEntry.getKey()+'\t'+'\t'+eachEntry.getValue());
		}

		//13) Close the browser.
		driver.quit();
	}



}
