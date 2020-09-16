package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightsPage {
	
	WebDriver driver;

	@FindBy(xpath = "//*[@class='ico20 fb quicks']")
	List<WebElement> fares;
	
	@FindBy(xpath = "//*[@data-cy='duration']")
	List<WebElement> duration;
	
	@FindBy(xpath="//*[@data-cy='depTime']")
	List<WebElement> depTtime;
	
	@FindBy(xpath="//*[@class='ico13 padR10 padL5']")
	List<WebElement> airLines;
	
	public FlightsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void findBestItinery()
	{

		// retrieve all the fares
		String lowestfare = fares.get(0).getText();
		System.out.println(lowestfare);

		// check if the lowest fare is present for multiple flights
		int counter = 1;
		for (int i = 1; i < fares.size() - 1; i++) {

			if (lowestfare.equals(fares.get(i + 1).getText())) {
				counter = counter + 1;

			}
		}
		System.out.println("total occurance of lower fare: " + counter);

		// capture the journey time for the flights that has same lowest fare
		Integer[] durationarr = new Integer[counter];
		int totalduration;
		for (int j = 0; j < counter; j++) {
			String str = duration.get(j).getText();
			String[] splitstr = str.split(" ");
			splitstr[0].replace("h", "");
			splitstr[1].replace("m", "");

			totalduration = ((Integer.parseInt(splitstr[0].replace("h", ""))) * 60)
					+ Integer.parseInt(splitstr[1].replace("m", ""));
			System.out.println(totalduration);
			durationarr[j] = totalduration;

		}

		// find out the lowest journey time
		int index = 0;
		int min = durationarr[index];

		for (int i = 1; i < durationarr.length; i++) {
			if (durationarr[i] <= min) {
				min = durationarr[i];
				index = i;
			}
		}
		System.out.println(index);
		System.out.println(durationarr[index]);

		// check if other flights also has the same journey time then select the evening flight

		for (int i = 0; i < durationarr.length - 1; i++) {

			if (durationarr[index] == durationarr[i] && index != i) {

				System.out.println("multiple iternaries exist");
				String depttimeFirst = depTtime.get(index+1).getText(); 
				String depttimeNext = depTtime.get(i).getText(); 
				String[] firstFlight = depttimeFirst.split(":");
				String[] nextFlight = depttimeNext.split(":");

				if (Integer.parseInt(firstFlight[0]) >= Integer.parseInt(nextFlight[0])) {
					String airlines = airLines.get(index+1).getText();
					System.out.println("Best flight that matches the criteria is :" + airlines + "Departure Time: "
							+ depttimeFirst + "fare: " + lowestfare);
				} else {

					String airlines =airLines.get(i).getText();
					System.out.println("Best flight that matches the criteria is :" + airlines + "Departure Time: "
							+ depttimeNext + "fare: " + lowestfare);
				}

			}
		}

		// print the itinerary based on the fare & journey time taken
		String depttimeFirst = depTtime.get(index).getText();
		String airlines = airLines.get(index).getText();
		System.out.println("Best flight that matches the criteria is :" + airlines + "  --Departure Time: "
				+ depttimeFirst + "  --fare: " + lowestfare);

	}
}
