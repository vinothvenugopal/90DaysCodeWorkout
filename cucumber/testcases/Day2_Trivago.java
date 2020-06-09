package cucumber.testcases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Day2_Trivago {
	
	ChromeDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions action;
	
	@Given("open the trivago website")
	public void open_the_trivago_website() throws InterruptedException {
	    System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	    System.setProperty("webdriver.chrome.silentOutput", "true");
	    driver = new ChromeDriver();
	    System.out.println("Browser Launched");
	    driver.manage().window().maximize();
	    System.out.println("Browser Maximized");
	    driver.get("https://www.trivago.com");
	}

	@And("Type Agra in Destination and select Agra, Uttar Pradesh.")
	public void type_Agra_in_Destination_and_select_Agra_Uttar_Pradesh() throws InterruptedException {
	    
		Thread.sleep(500);
	    driver.findElementById("querytext").sendKeys("Agra");
	    wait = new WebDriverWait(driver, 30);
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='City - Uttar Pradesh, India']/parent::div[@class='ssg-suggestion__info']")));
	    driver.findElementByXPath("//span[text()='City - Uttar Pradesh, India']/parent::div[@class='ssg-suggestion__info']").click();
	    System.out.println("Agra, Uttar Pradesh selected");
	}

	@And("Choose June 15 as check in and June 30 as check out")
	public void choose_June_as_check_in_and_June_as_check_out() {
	    js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,200)","");
	    action = new Actions(driver);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='15']")));
	    action.moveToElement(driver.findElementByXPath("//span[text()='15']")).click().build().perform();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='30']")));
	    action.moveToElement(driver.findElementByXPath("//span[text()='30']")).click().build().perform();
	    System.out.println("From and To dates selected");
	}

	@And("Select Room as Family Room")
	public void select_Room_as_Family_Room() {
	    
	    driver.findElementByXPath("//span[text()='Family rooms']").click();
	    System.out.println("Family rooms selected");
	}

	@And("Choose Number of Adults 2, Childern 1 and set Child's Age as 4")
	public void choose_Number_of_Adults_Childern_and_set_Child_s_Age_as() {
	    
		wait.until(ExpectedConditions.elementToBeClickable(By.id("select-num-adults-2")));
	    WebElement adultElement = driver.findElementById("select-num-adults-2");
	    Select adultDD = new Select(adultElement);
	    adultDD.selectByVisibleText("2");
	    WebElement childElement = driver.findElementById("select-num-children-2");
	    Select childDD = new Select(childElement);
	    childDD.selectByValue("1");
	    System.out.println("2 Adults and 1 Child were selected");
	}

	@And("Click Confirm button")
	public void click_Confirm_button() {
	    
	    driver.findElementByXPath("//span[text()='Confirm']").click();
	}

	@And("click Search")
	public void click_Search() {
	    
	    driver.findElementByXPath("//span[text()='Search']").click();
	    System.out.println("Search button clicked");
	}

	@And("Select Accommodation type as Hotels only and choose 4 stars")
	public void select_Accommodation_type_as_Hotels_only_and_choose_stars() {
	    
		driver.findElementByXPath("//span[@class='filter-item__placeholder']/span[text()='All types']").click();
		driver.findElementByXPath("//label[text()='Hotels only']").click();
		System.out.println("Accomodation type selected as Hotel");
		driver.findElementByXPath("//div[@class='refinement-row__content']//button[@title='4-star hotels']").click();
	    System.out.println("4 Star Hotels Selected");
	    driver.findElementById("filter-popover-done-button").click();
	}

	@And("Select Guest rating as Very Good")
	public void select_Guest_rating_as_Very_Good() {
	    
	    driver.findElementByXPath("//span[@class='filter-item__placeholder']/span[text()='All']").click();
	    driver.findElementByXPath("//span[text()='Very good']").click();
	    System.out.println("Rating -> Very Good selected");
	}

	@And("Set Hotel Location as Agra Fort")
	public void set_Hotel_Location_as_Agra_Fort() {
	    
	    driver.findElementByXPath("//span[@class='filter-item__placeholder']/span[text()='City center']").click();
	    wait.until(ExpectedConditions.elementToBeClickable(By.id("pois")));
	    WebElement hotelLocationElement = driver.findElementById("pois");
	    Select hotelLocationDD = new Select(hotelLocationElement);
	    hotelLocationDD.selectByVisibleText("Agra Fort");
	    System.out.println("Hotel Location set as Agra Fort");
	}

	@And("click Done")
	public void click_Done() {
	    
	    driver.findElementById("filter-popover-done-button").click();
	}

	@And("In more Filters, select Air conditioning, Restaurant and WiFi and click Done")
	public void in_more_Filters_select_Air_conditioning_Restaurant_and_WiFi_and_click_Done() {
	    
	    driver.findElementByXPath("//span[@class='filter-item__placeholder']/span[text()='Select']").click();
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Air conditioning']")));
	    driver.findElementByXPath("//label[text()='Air conditioning']").click();
	    driver.findElementByXPath("//label[text()='WiFi']").click();
	    driver.findElementById("more-filters-search").sendKeys("Restaurant");
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mark[text()='Restaurant']")));
	    driver.findElementByXPath("//mark[text()='Restaurant']").click();
	    driver.findElementById("filter-popover-done-button").click();
	    System.out.println("More filters set with Air Conditioning, Restaurant and Wifi");
	}

	@And("Sort the result as Rating & Recommended")
	public void sort_the_result_as_Rating_Recommended() {
	    
	    wait.until(ExpectedConditions.elementToBeClickable(By.id("mf-select-sortby")));
	    WebElement sortElement = driver.findElementById("mf-select-sortby");
	    Select sortDD = new Select(sortElement);
	    sortDD.selectByVisibleText("Rating & Recommended");
	    System.out.println("Results sorted by Rating & Recommendations");
	}

	@And("Print the Hotel name, Rating, Number of Reviews")
	public void print_the_Hotel_name_Rating_Number_of_Reviews() {
	    
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='pos-relative item__wrapper']")));
	    System.out.println("Hotel Name: "+driver.findElementByXPath("(//div[@class='item__flex-column'])[1]//h3[@itemprop='name']/span").getText());
	    System.out.println("Number of Stars: "+driver.findElementByXPath("(//div[@class='item__flex-column'])[1]//div[@class='stars-wrp']/meta").getAttribute("content"));
	    System.out.println("Number of Reviews: "+driver.findElementByXPath("(//div[@class='item__flex-column'])[1]//span[@class='review']//strong/parent::span").getText());
	}

	@And("Click View Deal")
	public void click_View_Deal() {
//		action = new Actions(driver);
//		action.moveToElement(driver.findElementByXPath("(//span[text()='View Deal'])[1]")).click().build().perform();
		js.executeScript("arguments[0].click()", driver.findElementByXPath("(//span[text()='View Deal'])[1]"));
	}

	@And("Print the URL of the Page")
	public void print_the_URL_of_the_Page() {
	    
	    Set<String> windowHandles = driver.getWindowHandles();
	    List<String> windowHandlesList = new ArrayList<String>(windowHandles);
	    driver.switchTo().window(windowHandlesList.get(1));
	    System.out.println("Current URL: "+driver.getCurrentUrl());
	}

	@When("Print the Price of the Room and click Choose Your Room")
	public void print_the_Price_of_the_Room_and_click_Choose_Your_Room() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='price']")));
	    System.out.println("Room Price: "+driver.findElementByXPath("//div[@class='price']").getText());
	    
	}

	@Then("Click Reserve and I'll Reserve")
	public void click_Reserve_and_I_ll_Reserve() {
	    
	    driver.findElementByXPath("//a[text()='BOOK NOW']").click();
	    driver.quit();
	}

	
	//		1) Go to https://www.trivago.com/
//		2) Type Agra in Destination and select Agra, Uttar Pradesh.
//		3) Choose May 15 as check in and May 30 as check out
//		4) Select Room as Family Room
//		5) Choose Number of Adults 2, Childern 1 and set Child's Age as 4
//		6) Click Confirm button and click Search
//		7) Select Accommodation type as Hotels only and choose 4 stars
//		8) Select Guest rating as Very Good
//		9) Set Hotel Location as Agra Fort and click Done
//		10) In more Filters, select Air conditioning, Restaurant and WiFi and click Done
//		11) Sort the result as Rating & Recommended
//		12) Print the Hotel name, Rating, Number of Reviews and Click View Deal
//		13) Print the URL of the Page
//		14) Print the Price of the Room and click Choose Your Room
//		15) Click Reserve and I'll Reserve
//		16) Close the browser

}
