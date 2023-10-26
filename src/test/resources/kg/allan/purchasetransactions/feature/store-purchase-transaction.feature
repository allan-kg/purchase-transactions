# Allan Krama Guimarães - 2023 - All rights reserved.
Feature: Store purchase transation
	The application must be able to accept and store a purchase transaction.

	Context:
		Given the application is ready to accept transactions

	Scenario: Store a purchase transaction in USD and generate its unique id
		Given the transaction request has been received
		And the description does not exceed 50 chars
		And the transaction date is in a valid date format
		And the purchase amount is a valid positive amount rounded to the nearest cent
		Then a unique identifier is generated for the transaction
		And the purchase amount is converted to USD
		And the transaction is stored
