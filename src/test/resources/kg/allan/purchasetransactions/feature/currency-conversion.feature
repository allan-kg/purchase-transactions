Feature: Currency Conversion
	All values are considered to be stored in USD.

	Scenario: Convert an amount with a specific transaction date that there is no conversion rate for that day
		Given A new transaction is being made
		And the target currency is <currency>
		And the stored transaction datetime is <datetime>
		And the stored transaction value is <value>
		When the purchase transaction is requested
		Then a conversion must be performed
		And the rounded converted value is <converted>
		And the exchange datetime found is <rate_date>
	  Examples:
		|currency|datetime               |value|converted|rate_date |
		|BRL     |2023-07-01T00:00:00.000|715  |3473.47  |2023-06-30|
		

	Scenario: Return an error value when the conversion is not possible
		Given A new transaction is being made
		And the target currency is <currency>
		And the stored transaction datetime is <datetime>
		And the stored transaction value is <value>
		When the purchase transaction is requested
		Then there is no exchange rate found
		And there is no conversion rate up to <mindate>
		And the value returned is the error message
	  Examples:
		|currency|datetime               |value|mindate   |converted|
		|CUP     |2019-12-30T00:00:00.000|20   |2019-06-30|3473.47  |
		# CUP - Cuba

	Scenario: Convert an amount to the conversion rate of the same day as the transaction
		Given A new transaction is being made
		And the target currency is <currency>
		And the stored transaction datetime is <datetime>
		And the stored transaction value is <value>
		When the purchase transaction is requested
		Then a conversion must be performed
		And the rounded converted value is <converted>
		And the exchange datetime found is <rate_date>
	  Examples:
		|currency|datetime               |value|converted|rate_date |
		|BRL     |2023-06-30T00:00:00.000|1599 |7767.94  |2023-06-30|
