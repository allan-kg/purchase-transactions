Feature: Retrieve purchase transaction

	Context:
		Given the application is ready to retrieve transactions

#	Scenario: Retrieve a purchase transaction by its unique id
#		Given I have a valid transaction id
#		When I request to retrieve the purchase transaction
#		Then the application should return the purchase transaction details
#		And the transaction details must contain the original value in USD
#		And the transaction details must contain the date of the transaction
#		And the transaction details must contain the transaction description

	Scenario: Retrieve a purchase transaction by its unique id and convert it to another currency
		Given I have a valid transaction id
		And I have a valid target currency
		When I request to retrieve the purchase transaction
		Then the application should return the purchase transaction details
		And the transaction details must contain the original value in USD
		And the transaction details must contain the date of the transaction
		And the transaction details must contain the transaction description
		And the transaction details must include the exchange rate used
		And the transaction details must include the converted amount for the date of purchase

	Scenario: Retrieve all purchase transactions
		Given
		When I request to retrieve all of the purchase transactions stored
		Then the application should return a list containing all the transactions details
		And all the transactions must contain the original value in USD
		And all the transactions must contain the date of transaction
		And all the transactions must contain the transaction description
	
#	Scenario: Retrieve all purchase transactions, paginated
#		Given My request has a parameter to paginate the list of transactions to 5 transactions per page
#		When I request to retrieve all of the purchase transactions stored
#		Then the application should return a list containing the first 5 transactions details
#		And the transactions must contain the original value in USD
#		And the transactions must contain the date of transaction
#		And the transactions must contain the transaction description
#	
#	Scenario: Retrieve the second page of purchase transactions
#		Given My request has a parameter to paginate the list of transactions to 5 transactions per page
#		And there are more than 5 transactions stored
#		When I request to retrieve the second page of all of the purchase transactions stored
#		Then the application should return a list containing the 6th through 10th transactions details
#		And the transactions must contain the original value in USD
#		And the transactions must contain the date of transaction
#		And the transactions must contain the transaction description
