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
    private Map<String, LoyaltyLevelDto> allLoyaltyLevelsByUuid = new HashMap<>();


    @Given("^There are given loyalty levels")
    public void thereAreGivenLoyaltyLevels(DataTable loyaltyLevels){


        allLoyaltyLevelsByUuid.clear();

        loyaltyLevels.asList(LoyaltyLevelDto.class)
                .forEach(loyaltyLevel -> loyaltyLevelsFacade.add(loyaltyLevel));
    }

    @When("^I ask for all loyalty levels")
    public void iAskForAllLoyaltyLevels(){

        allLoyaltyLevelsByUuid.clear();

        allLoyaltyLevelsByUuid = loyaltyLevelsFacade.findAll().stream()
                                .collect(Collectors.toMap(LoyaltyLevelDto::getUuid, level -> level));
    }


    @Then("^I get (.*) loyalty levels")
    public void iGetNLoyaltyLevels(int count){
        assertEquals(allLoyaltyLevelsByUuid.size(), count);
    }


    @Then("^Found loyalty levels has$")
    public void foundLoyaltyLevelsHas(DataTable loyaltyLevels) {

        loyaltyLevels.asList(LoyaltyLevelDto.class).forEach(
                loyaltyLevel -> {
                    LoyaltyLevelDto foundLoyaltyLevel = allLoyaltyLevelsByUuid.get(loyaltyLevel.getUuid());

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

    @When("^I ask for loyalty level with UUID \"([^\"]*)\"$")
    public void iAskForLoyaltyLevelWithUUID(String uuid){
        allLoyaltyLevelsByUuid.clear();

        UUID levelUUID = UUID.fromString(uuid);
        allLoyaltyLevelsByUuid.put(uuid, loyaltyLevelsFacade.findByUuid(levelUUID));
    }

    @When("^I update loyalty level with UUID \"([^\"]*)\"$")
    public void iUpdateLoyaltyLevelWithUUID(String uuid){
        UUID levelUUID = UUID.fromString(uuid);
        loyaltyLevelsFacade.update(levelUUID);
    }
}
