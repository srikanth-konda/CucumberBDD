package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public WebDriver driver;
	
	@FindBy(xpath = "//*[@id='gosuggest_inputSrc']")
	WebElement source;
	
	@FindBy(xpath = "//*[@id='gosuggest_inputDest']")
	WebElement destination;
	
	@FindBy(xpath = "//*[@id='gi_search_btn']")
	WebElement searchBtn;
	
	@FindBy(xpath = "//*[@id='departureCalendar']")
	WebElement calenderBtn;
	
	@FindBy(xpath = "//*[@class='calDate' and text()='25']")
	WebElement selectDate;
	
	@FindBy(xpath = "//*[@id='react-autosuggest-1']/li[1]")
	WebElement contextText;
	
	
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
			    
	}
	
	
	public void setSource(String origin) {
		source.sendKeys(origin);
		waitForPageLoad();
		contextText.click();
	}
	public void setdestination(String destnationCity) {
		source.sendKeys(destnationCity);
		waitForPageLoad();
		contextText.click();
	}
	public void setDate() {
		contextText.click();
		calenderBtn.click();
		selectDate.click();
	}
	
	public void clickonSearch() {
		searchBtn.click();
	}
	
	public void enterSearchCriteria() {	
		//enter source and destination values
		source.sendKeys("Hyderabad");
		waitForPageLoad();
		contextText.click();
		destination.sendKeys("Delhi");
		waitForPageLoad();
		contextText.click();
		calenderBtn.click();
		selectDate.click();
	}
	
	public void clickOnSearch() {
		searchBtn.click();
		waitForPageLoad();
	}
	
	
	

	public void waitForPageLoad() {

		try {
			while (true) {

				boolean ajaxIsComplete = (Boolean) (((JavascriptExecutor) driver)
						.executeScript("return jquery.active == 0"));
				if (ajaxIsComplete)
					break;
			}

		} catch (Exception e) {

		}
	}
	

}
