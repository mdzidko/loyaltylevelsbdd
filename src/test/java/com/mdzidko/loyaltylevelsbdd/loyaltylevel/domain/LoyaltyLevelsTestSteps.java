package com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDataValidationException;
import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDoesntExistException;
import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;
import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelNameDuplicationException;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoyaltyLevelsTestSteps{

    private LoyaltyLevelsFacade loyaltyLevelsFacade = new LoyaltyLevelsConfiguration().loyaltyLevelsFacade();
    private Map<String, LoyaltyLevelDto> allLoyaltyLevels = new HashMap<>();
    private String loggedMassage;


    @Given("^There are given loyalty levels")
    public void thereAreGivenLoyaltyLevels(DataTable loyaltyLevels){

        allLoyaltyLevels.clear();

        loyaltyLevels.asList(LoyaltyLevelDto.class)
                .forEach(loyaltyLevel -> loyaltyLevelsFacade.add(loyaltyLevel));
    }


    @When("^I ask for all loyalty levels")
    public void iAskForAllLoyaltyLevels(){

        allLoyaltyLevels.clear();

        List<LoyaltyLevelDto> fountLoyaltyLevels = loyaltyLevelsFacade.findAll();

        allLoyaltyLevels = fountLoyaltyLevels.stream()
                .collect(Collectors.toMap(LoyaltyLevelDto::getName, level -> level));
    }

    @When("^I add loyalty levels$")
    public void iAddLoyaltyLevels(DataTable loyaltyLevels) {

        try {
            loyaltyLevels.asList(LoyaltyLevelDto.class)
                    .forEach(loyaltyLevel -> loyaltyLevelsFacade.add(loyaltyLevel));
        }
        catch(LoyaltyLevelNameDuplicationException | LoyaltyLevelDataValidationException ex){
            loggedMassage = ex.getMessage();
        }
    }


    @When("^I remove loyalty level \"([^\"]*)\"$")
    public void iRemoveLoyaltyLevel(String name){

        long id = allLoyaltyLevels.get(name).getId();

        try {
            loyaltyLevelsFacade.remove(id);
        }
        catch(LoyaltyLevelDoesntExistException ex){
            loggedMassage = ex.getMessage();
        }
    }

    @When("^I remove not existing loyalty level")
    public void iRemoveNotExistingLoyaltyLevel(){

        long id = 987654321;

        try {
            loyaltyLevelsFacade.remove(id);
        }
        catch(LoyaltyLevelDoesntExistException ex){
            loggedMassage = ex.getMessage();
        }
    }


    @Then("^I get (.*) loyalty levels")
    public void iGetNLoyaltyLevels(int count){
        assertEquals(allLoyaltyLevels.size(), count);
    }


    @Then("^Found loyalty levels has$")
    public void foundLoyaltyLevelsHas(DataTable loyaltyLevels) {

        loyaltyLevels.asList(LoyaltyLevelDto.class).forEach(
                loyaltyLevel -> {
                    LoyaltyLevelDto foundLoyaltyLevel = allLoyaltyLevels.get(loyaltyLevel.getName());

                    assertNotNull(foundLoyaltyLevel);
                    assertEquals(foundLoyaltyLevel.getName(), loyaltyLevel.getName());
                    assertEquals(foundLoyaltyLevel.getLowerLevelBound(), loyaltyLevel.getLowerLevelBound());
                    assertEquals(foundLoyaltyLevel.getPointsBonusPercentage(), loyaltyLevel.getPointsBonusPercentage(), 0.0001);
                }
        );
    }

    @Then("^Message \"([^\"]*)\" is logged$")
    public void messageIsLogged(String message) {
        assertEquals(this.loggedMassage, message);
    }
}
