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
            |uuid    |name  |pointsBonusPercentage|lowerLevelBound|
            |f808dc02-b447-412e-9e04-24e6d5ff99ae|Bronze|1                    |1000           |
            |f808dc02-b447-412e-9e04-24e6d5ff99ac|Gold  |2                    |2000           |
        When I ask for loyalty level with UUID "f808dc02-b447-412e-9e04-24e6d5ff99ac"
        Then Found loyalty levels has
            |uuid    |name  |pointsBonusPercentage|lowerLevelBound|
            |f808dc02-b447-412e-9e04-24e6d5ff99ac|Gold  |2                    |2000           |
        And I get 1 loyalty levels

    Scenario: Get loyalty level by given UUID
        Given There are given loyalty levels
            |uuid    |name  |pointsBonusPercentage|lowerLevelBound|
            |f808dc02-b447-412e-9e04-24e6d5ff99ae|Bronze|1                    |1000           |
            |f808dc02-b447-412e-9e04-24e6d5ff99ac|Gold  |2                    |2000           |
        When I ask for loyalty level with UUID "f808dc02-b447-412e-9e04-24e6d5ff99ac"
        Then Found loyalty levels has
            |uuid|name  |pointsBonusPercentage|lowerLevelBound|
            |f808dc02-b447-412e-9e04-24e6d5ff99ac|Gold  |2                    |2000           |
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
            |uuid    |name  |pointsBonusPercentage|lowerLevelBound|
            |f808dc02-b447-412e-9e04-24e6d5ff99ae|Bronze|1                    |1000           |
            |f808dc02-b447-412e-9e04-24e6d5ff99ac|Gold  |2                    |2000           |
        When I remove loyalty level with UUID "f808dc02-b447-412e-9e04-24e6d5ff99ac"
        And I ask for all loyalty levels
        Then Found loyalty levels has
            |name  |pointsBonusPercentage|lowerLevelBound|
            |Bronze|1                    |1000           |
        And I get 1 loyalty levels
