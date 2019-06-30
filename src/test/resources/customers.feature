Feature: Customer management

    Scenario: Get all customers
        Given There are given customers
            |cardNumber|loyaltyLevel|totalBet|
            |123456789 |"bronze"    |1000    |
            |234456123 |"gold"      |3000    |
        When I ask for all customers
        Then Found customers has
            |cardNumber|loyaltyLevel|totalBet|
            |123456789 |"bronze"    |1000    |
            |234456123 |"gold"      |3000    |
        And I get 2 customers


    Scenario: Add one customer
        Given There are given customers
            |cardNumber|loyaltyLevel|totalBet|
            |123456789 |"bronze"    |1000    |
            |234456123 |"gold"      |3000    |
        When I add customers
            |cardNumber|loyaltyLevel|
            |443454555 |"bronze"    |
        And I ask for all customers
        Then Found customers has
            |cardNumber|loyaltyLevel|totalBet|
            |123456789 |"bronze"    |1000    |
            |234456123 |"gold"      |3000    |
            |443454555 |"bronze"    |0       |
        And I get 3 customers