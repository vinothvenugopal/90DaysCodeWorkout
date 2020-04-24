package working;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebListen implements WebDriverEventListener {
	EventFiringWebDriver event1;

	// constructor for initializing the EventfiringWebDriver for this class
	public WebListen(EventFiringWebDriver event1) {
		//this.event1 = event1;
	}



	@Override
	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	//executes the code before every click action
	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, 30); 
		Actions action = new Actions(driver); 
		// checking if the pop up element is present using the element.size. //if
		// size > 0, then the element is present
		if(driver.findElements(By.xpath("//div[@class='inside_notify_content']")).size()>0) 
		{ 
			WebElement popUp = driver.findElement(By.xpath("//div[@class='inside_notify_content']"));
			System.out.println("Pop Up displayed");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='inside_closeButton fonticon icon-hclose']")));
			driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[@class='inside_closeButton fonticon icon-hclose']")).click();
			System.out.println("Pop Up Closed");
			driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS); 
		} 
		else if(driver.findElements(By.xpath("//div[@id='regPopUp']//a[@class='popup-close']")).size()>0)
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='regPopUp']//a[@class='popup-close']")));
			System.out.println("Pop Up Dispayed");
			driver.findElement(By.xpath("//div[@id='regPopUp']//a[@class='popup-close']")).click();
			System.out.println("Pop Up Closed");
		}
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		System.out.println(throwable.getCause());
		System.out.println(throwable.getMessage());

	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		// TODO Auto-generated method stub

	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		// TODO Auto-generated method stub

	}


}
