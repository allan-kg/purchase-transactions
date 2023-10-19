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

#	Scenario: Convert an amount to TODO with a specific transaction date that there is no conversion rate for that day
#		Given the target currency is TODO
#		And the stored transaction date is TODO
#		And the stored transaction value is TODO
#		When the conversion is requested with value TODO
#		Then the conversion rate being considered is TODO
#		And the conversion rate is not older than 6 months from TODO(transactiondate)
#		And the value returned is TODO

#	Scenario: Return an error value when the conversion is not possible
#		Given the target currency is TODO
#		And the stored transaction date is TODO
#		And the stored transaction value is TODO
#		When the conversion is requested with value TODO
#		Then there is no conversion rate for TODOthe transaction date
#		And there is no conversion rate up to 6 months from TODOthe transaction date
#		And the value returned is the error code

	Scenario: Convert an amount to the conversion rate of the same day as the transaction
		Given the target currency is TODO
		And the stored transaction date is TODO
		And the stored transaction value is TODO
		When the conversion is requested with value TODO
		Then a conversion rate must be found for the exact day of the transaction
		And the value returned is TODO
