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

    Scenario: Get loyalty level by UUID
        Given There are given loyalty levels
            |uuid    |name  |pointsBonusPercentage|lowerLevelBound|
            |44323442|Bronze|1                    |1000           |
            |26112313|Gold  |2                    |2000           |
        When I ask for loyalty level with UUID "26112313"
        Then Found loyalty levels has
            |name  |pointsBonusPercentage|lowerLevelBound|
            |Gold  |2                    |2000           |

    Scenario: Get loyalty level by given UUID
        Given There are given loyalty levels
            |uuid    |name  |pointsBonusPercentage|lowerLevelBound|
            |44323442|Bronze|1                    |1000           |
            |26112313|Gold  |2                    |2000           |
        When I ask for loyalty level with UUID "26112313"
        Then Found loyalty levels has
            |name  |pointsBonusPercentage|lowerLevelBound|
            |Gold  |2                    |2000           |

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

    Scenario: Update one loyalty level
        Given There are given loyalty levels
            |uuid    |name  |pointsBonusPercentage|lowerLevelBound|
            |44323442|Bronze|1                    |1000           |
            |26112313|Gold  |2                    |2000           |
        When I update loyalty level with UUID "26112313"
            |name  |pointsBonusPercentage|lowerLevelBound|
            |Gold  |2                    |2000           |
        And I ask for all loyalty levels
        Then Found loyalty levels has
            |name  |pointsBonusPercentage|lowerLevelBound|
            |Bronze|1                    |1000           |
            |Gold  |2                    |2000           |
