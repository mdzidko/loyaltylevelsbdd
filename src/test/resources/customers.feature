Feature: Customer module

    Scenario: Add one customer and get it by card number
        Given There is given loyalty levels configuration
            |name    |lowerLevelBound|isDefault|
            |bronze|0              |true   |
            |silver|1000           |false  |
            |gold  |2000           |false  |
        When I add customer with card number "123ABC456"
        And I ask for customer with card number "123ABC456"
        Then Found customers has
            |cardNumber |loyaltyLevel|totalBet|
            |123ABC456  |bronze      |0       |


    Scenario: Try to add customer with existing card number
        Given There is given loyalty levels configuration
            |name    |lowerLevelBound|isDefault|
            |bronze|0              |true   |
        And There are given customers
            |123ABC456|
        When I add customer with card number "123ABC456"
        Then CustomerCardNumberDuplicationException is thrown


    Scenario: Bet for customer was received
        Given There is given loyalty levels configuration
            |name    |lowerLevelBound|isDefault|
            |bronze|0              |true   |
        And There are given customers
            |123ABC456 |
        When I add new bet with value 100 for customer with card number "123ABC456"
        And I ask for customer with card number "123ABC456"
        Then Found customers has
            |cardNumber |loyaltyLevel|totalBet|
            |123ABC456  |bronze      |100     |


    Scenario: Bet for not existing customer was received
        When I add new bet with value 100 for customer with card number "123ABC456"
        Then CustomerDoesntExistsException is thrown


    Scenario: Update customers loyalty levels
        Given There is given loyalty levels configuration
            |name    |lowerLevelBound|isDefault|
            |bronze|0              |true   |
            |silver|1000           |false  |
            |gold  |2000           |false  |
            |platinium  |3000      |false  |
        And There are given customers
            |123ABC456|
            |987XYZ001|
        When I add new bet with value 100 for customer with card number "123ABC456"
        And I add new bet with value 200 for customer with card number "987XYZ001"
        And I add new bet with value 1000 for customer with card number "123ABC456"
        And I update customers loyalty levels
        And I ask for all customers
        Then Found customers has
            |cardNumber |loyaltyLevel|totalBet|
            |123ABC456  |silver      |1100    |
            |987XYZ001  |bronze      |200     |

