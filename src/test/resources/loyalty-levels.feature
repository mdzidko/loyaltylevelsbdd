Feature: Getting loyalty levels
    Scenario: Get all loyalty levels
        Given there are 2 loyalty levels
        When I ask for all loyalty levels
        Then I get 2 loyalty levels