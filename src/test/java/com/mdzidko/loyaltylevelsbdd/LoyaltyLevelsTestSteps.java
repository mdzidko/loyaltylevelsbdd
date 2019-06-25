package com.mdzidko.loyaltylevelsbdd;

import com.mdzidko.loyaltylevelsbdd.loyaltylevel.dto.LoyaltyLevelDto;
import com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain.LoyaltyLevelsConfiguration;
import com.mdzidko.loyaltylevelsbdd.loyaltylevel.domain.LoyaltyLevelsFacade;
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
    private Map<String, LoyaltyLevelDto> allLoyaltyLevelsByName = new HashMap<>();
    private String loggedMassage;


    @Given("^There are given loyalty levels")
    public void thereAreGivenLoyaltyLevels(DataTable loyaltyLevels){

        allLoyaltyLevelsByName.clear();

        loyaltyLevels.asList(LoyaltyLevelDto.class)
                .forEach(loyaltyLevel -> loyaltyLevelsFacade.add(loyaltyLevel));
    }


    @When("^I ask for all loyalty levels")
    public void iAskForAllLoyaltyLevels(){

        allLoyaltyLevelsByName.clear();

        List<LoyaltyLevelDto> fountLoyaltyLevels = loyaltyLevelsFacade.findAll();

        allLoyaltyLevelsByName = fountLoyaltyLevels.stream()
                .collect(Collectors.toMap(LoyaltyLevelDto::getName, level -> level));
    }

    @When("^I add loyalty levels$")
    public void iAddLoyaltyLevels(DataTable loyaltyLevels) {

        try {
            loyaltyLevels.asList(LoyaltyLevelDto.class)
                    .forEach(loyaltyLevel -> loyaltyLevelsFacade.add(loyaltyLevel));
        }
        catch(RuntimeException ex){
            loggedMassage = ex.getMessage();
        }
    }

    @When("^I ask for loyalty level \"([^\"]*)\"$")
    public void iAskForLoyaltyLevel(String name){

        String stringUuid = allLoyaltyLevelsByName.get(name).getUuid();
        UUID uuid = UUID.fromString(stringUuid );

        allLoyaltyLevelsByName.clear();

        allLoyaltyLevelsByName.put(name, loyaltyLevelsFacade.findByUuid(uuid));
    }

    @When("^I remove loyalty level \"([^\"]*)\"$")
    public void iRemoveLoyaltyLevel(String name){

        String stringUuid = allLoyaltyLevelsByName.get(name).getUuid();
        UUID uuid = UUID.fromString(stringUuid );

        loyaltyLevelsFacade.remove(uuid);
    }


    @Then("^I get (.*) loyalty levels")
    public void iGetNLoyaltyLevels(int count){
        assertEquals(allLoyaltyLevelsByName.size(), count);
    }


    @Then("^Found loyalty levels has$")
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

    @Then("^Message \"([^\"]*)\"$\" is logged")
    public void givenMessageIsLogged(String message){
        assertEquals(this.loggedMassage, message);
    }
}
