#Author: Srikanth Ksonda


Feature: Search for avialble flights and validate the itinerary.

  @tag1
	Scenario Outline: Search for a flight by providing source and destination
   Given Open browser and launch the application
   And Enter the username and password
   And click on search to view available flights
   Then find itinearaty with best price and fastest flight

  Examples:
  | source          | destination |
  | Hyderabad       | Delhi       | 

 