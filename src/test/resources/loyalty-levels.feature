Feature: Getting loyalty levels

    Scenario: Get all available loyalty levels
        Given There are given loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|
            |Bronze|1                    |1000           |
            |Gold  |2                    |2000           |
        When I ask for all loyalty levels
        Then Found loyalty levels has
            |name  |pointsBonusPercentage|lowerLevelBound|
            |Bronze|1                    |1000           |
            |Gold  |2                    |2000           |
        And I get 2 loyalty levels

    Scenario: Get loyalty level by UUID
        Given There are given loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|
            |Bronze|1                    |1000           |
            |Gold  |2                    |2000           |
        When I ask for all loyalty levels
        And I ask for loyalty level "Gold"
        Then Found loyalty levels has
            |name  |pointsBonusPercentage|lowerLevelBound|
            |Gold  |2                    |2000           |
        And I get 1 loyalty levels

    Scenario: Add one loyalty level
        Given There are given loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|
            |Bronze|1                    |1000           |
            |Gold  |3                    |3000           |
        When I add loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|
            |Silver|2                    |2000           |
        And I ask for all loyalty levels
        Then Found loyalty levels has
            |name  |pointsBonusPercentage|lowerLevelBound|
            |Bronze|1                    |1000           |
            |Silver|2                    |2000           |
            |Gold  |3                    |3000           |
        And I get 3 loyalty levels

    Scenario: Remove one loyalty level
        Given There are given loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|
            |Bronze|1                    |1000           |
            |Gold  |2                    |2000           |
        When I ask for all loyalty levels
        And I remove loyalty level "Gold"
        And I ask for all loyalty levels
        Then Found loyalty levels has
            |name  |pointsBonusPercentage|lowerLevelBound|
            |Bronze|1                    |1000           |
        And I get 1 loyalty levels

    Scenario: Add loyalty level with existing name
        Given There are given loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|
            |Bronze|1                    |1000           |
            |Gold  |3                    |3000           |
        When I add loyalty levels
            |name  |pointsBonusPercentage|lowerLevelBound|
            |Gold|2                    |2000           |
        And I ask for all loyalty levels
        Then Message "Can't add loyalty level with same name" is logged
        And I get 2 loyalty levels
        And Found loyalty levels has
            |name  |pointsBonusPercentage|lowerLevelBound|
            |Bronze|1                    |1000           |
            |Gold  |3                    |3000           |