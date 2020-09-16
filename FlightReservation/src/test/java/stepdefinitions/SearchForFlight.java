package stepdefinitions;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.FlightsPage;
import pages.HomePage;

public class SearchForFlight {

	public WebDriver driver;
	static Properties confprop;
	static Properties OR;

	public SearchForFlight() throws IOException {

		// load the config and properties files
		String localDir = System.getProperty("user.dir");
		FileReader reader = new FileReader(localDir + "\\src\\test\\java\\configData\\config.properties");
		confprop = new Properties();
		confprop.load(reader);
	}

	@Given("^Open browser and launch the application$")
	public void open_browser_and_launch_the_application() throws Throwable {
			try {
			
	
		if (confprop.getProperty("browser").equals("chrome")) {
			String localDir = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver", localDir + "\\chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			driver = new SafariDriver();
		}
		// launch the application
		driver.get(confprop.getProperty("app_url"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		} catch(Exception e) {
			
		}
		
	}

	@And("Enter the username and password$")
	public void Enter_the_username_and_password() throws Throwable

	{
		HomePage homePage = new HomePage(driver);
		homePage.enterSearchCriteria();
		
	}

	@And("click on search to view available flights$")
	public void click_on_search_to_view_available_flights() throws Throwable {
		HomePage homePage = new HomePage(driver);
		homePage.clickOnSearch();


	}

	@Then("find itinearaty with best price and fastest flight$")
	public void search_for_itinearaty_with_best_price_and_fastest_flight() throws Throwable {
		
		FlightsPage flightsPage = new FlightsPage(driver);
		flightsPage.findBestItinery();
		
		}


}