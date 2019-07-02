Feature: Customer module

    Scenario: Add one customer and get it by card number
        Given There are given customer loyalty levels
            |name    |lowerLevelBound|default|
            |"bronze"|0              |true   |
            |"silver"|1000           |false  |
            |"gold"  |2000           |false  |
        When I add customer with card number "123ABC456"
        And I ask for customer with card number "123ABC456"
        Then Found customers has
            |cardNumber |loyaltyLevel|totalBet|
            |"123ABC456"|"bronze"    |0       |


    Scenario: Try to add customer with existing card number
        Given There are given customers
            |name       |
            |"123ABC456"|
        When I add customer with card number "123ABC456"
        Then Message "Customer with given card number already exists" is logged


    Scenario: Bet for customer was received
        Given There are given customers
            |name       |
            |"123ABC456"|
        When I add new bet with value 100 for customer with card number "123ABC456"
        And I ask for customer with card number "123ABC456"
        Then Found customers has
            |cardNumber |loyaltyLevel|totalBet|
            |"123ABC456"|"bronze"    |100     |


    Scenario: Bet for not existing customer was received
        When I add new bet with value 100 for customer with card number "123ABC456"
        Then Message "Customer with given card number doesn't exists" is logged


    Scenario: Update customers loyalty levels
        Given There are given customer loyalty levels
            |name    |lowerLevelBound|default|
            |"bronze"|0              |true   |
            |"silver"|1000           |false  |
            |"gold"  |2000           |false  |
        And There are given customers
            |name       |
            |"123ABC456"|
            |"987XYZ001"|
        When I add new bet with value 100 for customer with card number "123ABC456"
        And I add new bet with value 200 for customer with card number "987XYZ001"
        And I add new bet with value 1000 for customer with card number "123ABC456"
        And I update customer loyalty levels
        And I ask for all customers
        Then Found customers has
            |cardNumber |loyaltyLevel|totalBet|
            |"123ABC456"|"silver"    |1100    |
            |"987XYZ001"|"bronze"    |200     |

