# Allan Krama Guimarães - 2023 - All rights reserved.
Feature: Currency Conversion
	All values are considered to be stored in USD.

	Scenario: Convert an amount with a specific transaction date that there is no conversion rate for that day
		Given A new conversion is being made
		And the target country is <country>
		And the date is <date>
		And the value is <value>
		When the conversion is requested
		Then a conversion must be performed
		And the rounded converted value is <converted>
		And the exchange date found is <rate_date>
	  Examples:
		|country|date      |value|converted|rate_date |
		|Brazil  |2023-07-01|715  |3473.47  |2023-06-30|
		

	Scenario: Return an error value when the conversion is not possible
		Given A new conversion is being made
		And the target country is <country>
		And the date is <date>
		And the value is <value>
		When the impossible conversion is requested
		Then there is no exchange rate found
		
	  Examples:
		|country|date      |value|mindate   |converted|
		|Cuba    |2019-12-30|20   |2019-06-30|3473.47  |

	Scenario: Convert an amount to the conversion rate of the same day as the transaction
		Given A new conversion is being made
		And the target country is <country>
		And the date is <date>
		And the value is <value>
		When the conversion is requested
		Then a conversion must be performed
		And the rounded converted value is <converted>
		And the exchange date found is <rate_date>
	  Examples:
		|country|date      |value|converted|rate_date |
		|Brazil  |2023-06-30|1599 |7767.94  |2023-06-30|
