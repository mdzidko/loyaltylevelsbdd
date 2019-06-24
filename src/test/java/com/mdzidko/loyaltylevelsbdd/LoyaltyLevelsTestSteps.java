package com.mdzidko.loyaltylevelsbdd;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;
import com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain.LoyaltyLevelsConfiguration;
import com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain.LoyaltyLevelsFacade;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoyaltyLevelsTestSteps{

    private LoyaltyLevelsFacade loyaltyLevelsFacade = new LoyaltyLevelsConfiguration().loyaltyLevelsFacade();
    private Map<String, LoyaltyLevelDto> allLoyaltyLevelsByName = new HashMap<>();




    @Given("^There are given loyalty levels")
    public void thereAreGivenLoyaltyLevels(DataTable loyaltyLevels){
        

        allLoyaltyLevelsByName.clear();

        loyaltyLevels.asList(LoyaltyLevelDto.class)
                .forEach(loyaltyLevel -> loyaltyLevelsFacade.add(loyaltyLevel));
    }

    @When("^I ask for all loyalty levels")
    public void iAskForAllLoyaltyLevels(){

        allLoyaltyLevelsByName.clear();

        allLoyaltyLevelsByName = loyaltyLevelsFacade.findAll().stream()
                                .collect(Collectors.toMap(LoyaltyLevelDto::getName, level -> level));
    }


    @Then("^I get (.*) loyalty levels")
    public void iGetNLoyaltyLevels(int count){
        assertEquals(allLoyaltyLevelsByName.size(), count);
    }


    @And("^I ask for loyalty level \"([^\"]*)\" by UUID$")
    public void iAskForLoyaltyLevelByUUID(String levelName){

        UUID levelUUID = UUID.fromString(allLoyaltyLevelsByName.get(levelName).getUuid());

        allLoyaltyLevelsByName.clear();
        allLoyaltyLevelsByName.put(levelName, loyaltyLevelsFacade.findByUuid(levelUUID));
    }


    @And("^Found loyalty levels has$")
    public void foundLoyaltyLevelsHas(DataTable loyaltyLevels) {

        loyaltyLevels.asList(LoyaltyLevelDto.class).forEach(
                loyaltyLevel -> {
                    LoyaltyLevelDto foundLoyaltyLevel = allLoyaltyLevelsByName.get(loyaltyLevel.getName());

                    assertNotNull(foundLoyaltyLevel);
                    assertEquals(foundLoyaltyLevel.getName(), loyaltyLevel.getName());
                    assertEquals(foundLoyaltyLevel.getLowerLevelBound(), loyaltyLevel.getLowerLevelBound());
                    assertEquals(foundLoyaltyLevel.getPointsBonusPercentage(), loyaltyLevel.getPointsBonusPercentage(), 0.0001);
                }
        );
    }

    @When("^I add loyalty levels$")
    public void iAddLoyaltyLevels(DataTable loyaltyLevels) {

        loyaltyLevels.asList(LoyaltyLevelDto.class)
                .forEach(loyaltyLevel -> loyaltyLevelsFacade.add(loyaltyLevel));
    }

    @When("^I update loyalty level$")
    public void iUpdateLoyaltyLevel(DataTable loyaltyLevels) {

        loyaltyLevels.asList(LoyaltyLevelDto.class)
                .forEach(loyaltyLevel -> loyaltyLevelsFacade.update(UUID.fromString(loyaltyLevel.getUuid()), loyaltyLevel));
    }
}
