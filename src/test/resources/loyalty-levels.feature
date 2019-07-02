Feature: Loyalty level module

    Scenario: Get all available loyalty levels
        Given There are given loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|default|
            |Bronze|1                    |1000           |true   |
            |Gold  |2                    |2000           |false  |
        When I ask for all loyalty levels
        Then Found loyalty levels has
            |name  |pointsBonusPercentage|lowerLevelBound|default|
            |Bronze|1                    |1000           |true   |
            |Gold  |2                    |2000           |false  |
        And I get 2 loyalty levels


    Scenario: Add one loyalty level
        Given There are given loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|default|
            |Bronze|1                    |1000           |true   |
            |Gold  |3                    |3000           |false  |
        When I add loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|default|
            |Silver|2                    |2000           |false   |
        And I ask for all loyalty levels
        Then Found loyalty levels has
            |name  |pointsBonusPercentage|lowerLevelBound|default|
            |Bronze|1                    |1000           |true   |
            |Silver|2                    |2000           |false  |
            |Gold  |3                    |3000           |false  |
        And I get 3 loyalty levels


    Scenario: Try to add loyalty level with existing name
        Given There are given loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|default|
            |Bronze|1                    |1000           |true   |
            |Gold  |3                    |3000           |false  |
        When I add loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|default|
            |Gold|2                    |2000             |false  |
        And I ask for all loyalty levels
        Then Message "Can't add loyalty level with same name" is logged
        And I get 2 loyalty levels
        And Found loyalty levels has
            |name  |pointsBonusPercentage|lowerLevelBound|default|
            |Bronze|1                    |1000           |true   |
            |Gold  |3                    |3000           |false  |


    Scenario: Try to add second default loyalty level
        Given There are given loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|default|
            |Bronze|1                    |1000           |true   |
            |Gold  |3                    |3000           |false  |
        When I add loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|default|
            |Gold|2                    |2000             |true  |
        And I ask for all loyalty levels
        Then Message "Can't add second default loyalty level" is logged
        And I get 2 loyalty levels
        And Found loyalty levels has
            |name  |pointsBonusPercentage|lowerLevelBound|default|
            |Bronze|1                    |1000           |true   |
            |Gold  |3                    |3000           |false  |


    Scenario: Try to add loyalty level with points bonus percentage lower than 0
        When I add loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|default|
            |Silver|-2                   |2000           |false  |
        And I ask for all loyalty levels
        Then I get 0 loyalty levels
        And Message "Value of points bonus percentage cant't be lower than 0" is logged


    Scenario: Try to add loyalty level with lower level bound lower than 0
        When I add loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|default|
            |Silver|2                    |-1000          |false  |
        And I ask for all loyalty levels
        Then I get 0 loyalty levels
        And Message "Value of lower level bound can't be lower than 0" is logged

    Scenario: Try to add loyalty level with no name
        When I add loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|default|
            |Silver|2                    |-1000          |false  |
        And I ask for all loyalty levels
        Then I get 0 loyalty levels
        And Message "Loyalty level name can't be empty" is logged


    Scenario: Remove one loyalty level
        Given There are given loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|default|
            |Bronze|1                    |1000           |true   |
            |Gold  |2                    |2000           |false  |
        When I ask for all loyalty levels
        And I remove loyalty level "Gold"
        And I ask for all loyalty levels
        Then Found loyalty levels has
            |name  |pointsBonusPercentage|lowerLevelBound|default|
            |Bronze|1                    |1000           |true   |
        And I get 1 loyalty levels


    Scenario: Try to remove not existing loyalty level
        Given There are given loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|default|
            |Bronze|1                    |1000           |true   |
            |Gold  |2                    |2000           |false  |
        When I remove not existing loyalty level
        And I ask for all loyalty levels
        Then Message "Loyalty level with given id doesn't exist" is logged
        And Found loyalty levels has
            |name  |pointsBonusPercentage|lowerLevelBound|default|
            |Bronze|1                    |1000           |true   |
            |Gold  |2                    |2000           |false  |
        And I get 2 loyalty levels

