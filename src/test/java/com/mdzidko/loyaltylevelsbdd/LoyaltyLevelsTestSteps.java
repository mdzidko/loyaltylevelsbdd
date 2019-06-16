package com.mdzidko.loyaltylevelsbdd;

import com.mdzidko.loyaltylevelsbdd.loyaltylevels.LoyaltyLevelDto;
import com.mdzidko.loyaltylevelsbdd.loyaltylevels.LoyaltyLevelsConfiguration;
import com.mdzidko.loyaltylevelsbdd.loyaltylevels.LoyaltyLevelsFacade;
import cucumber.api.TestCase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LoyaltyLevelsTestSteps{


    private LoyaltyLevelsFacade loyaltyLevelsFacade = new LoyaltyLevelsConfiguration().loyaltyLevelsFacade();
    private List<LoyaltyLevelDto> allLoyaltyLevels;

    @Given("^there are 2 loyalty levels")
    public void there_are_2_loyalty_levels(){
        LoyaltyLevelDto bronze = new LoyaltyLevelDto("Bronze", 2000, 1);
        LoyaltyLevelDto gold = new LoyaltyLevelDto("Gold", 5000, 3);

        loyaltyLevelsFacade.add(bronze, gold);
    }

    @When("^I ask for all loyalty levels")
    public void i_ask_for_all_loyalty_levels(){
        allLoyaltyLevels = loyaltyLevelsFacade.findAll();
    }


    @Then("^I get 2 loyalty levels")
    public void i_get_2_loyalty_levels(){
        assertEquals(allLoyaltyLevels.size(), 2);
    }
}
