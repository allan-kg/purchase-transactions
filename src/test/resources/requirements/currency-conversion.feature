Feature: Currency Conversion
	All values are considered to be stored in USD.

	Scenario: Use a date less than the day of transaction when not available for that specific day
		Given there is a target currency defined
		And there is a transaction registered with a day that the target conversion rate does not have an exact date match
		And there is a registered conversion rate less than the transaction date for that currency
		And the date isn't older than 6 months of the transaction date
		When the conversion is requested
		Then The value of that purchase transaction is converted to the target currency
		And the conversion rate is not more than 6 months old from the transaction date
		And the conversion rate being used is the first before the transaction date

	Scenario: Convert an amount with a specific transaction date that there is no conversion rate for that day
		Given the target currency is BRL
		And the stored transaction date is 2023-07-01
		And the stored transaction value is 715 USD
		When the purchase transaction is requested
		Then a conversion must be performed
		And the conversion rate is not older than 6 months from 2023-01-01
		And the value returned is 3473.47
		And the exchange date found is 2023-06-30

	Scenario: Return an error value when the conversion is not possible
		Given the target currency is CUP (Cuba)
		And the stored transaction date is 2019-12-30
		And the stored transaction value is 20
		When the purchase transaction is requested
		Then there is no exchange rate found
		And there is no conversion rate up to 2019-06-30
		And the value returned is the error message

	Scenario: Convert an amount to the conversion rate of the same day as the transaction
		Given the target currency is BRL
		And the stored transaction date is 2023-06-30
		And the stored transaction value is 1599 USD
		When the purchase transaction is requested
		Then an exchange rate must be found for the exact day of the transaction
		And the rounded converted value is 7767.94 BRL
