package com.mdzidko.loyaltylevelsbdd.customer.domain;

import com.mdzidko.loyaltylevelsbdd.customer.dto.CustomerCardNumberDuplicationException;
import com.mdzidko.loyaltylevelsbdd.customer.dto.CustomerDoesntExistsException;
import com.mdzidko.loyaltylevelsbdd.customer.dto.CustomerDto;
import com.mdzidko.loyaltylevelsbdd.customer.dto.LoyaltyLevelDto;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CustomerTestSteps{

    private CustomerFacade customerFacade = new CustomerConfiguration().customerFacade();
    private Map<String, CustomerDto> allCustomers = new HashMap<>();
    private Set<LoyaltyLevelDto> loyaltyLevelsConfiguration = new HashSet<>();

    private String loggedMassage;

    @Given("^There are given customers$")
    public void thereAreGivenCustomers(DataTable cardNumbers) {

        allCustomers.clear();

        cardNumbers.asList(String.class)
                .forEach(card -> customerFacade.add(card, loyaltyLevelsConfiguration));
    }

    @When("^I ask for all customers$")
    public void iAskForAllCustomers() {
        allCustomers.clear();

        List<CustomerDto> foundCustomers = customerFacade.findAll();

        allCustomers = foundCustomers.stream()
                .collect(Collectors.toMap(CustomerDto::getCardNumber, customer -> customer));
    }

    @Then("^Found customers has$")
    public void foundCustomersHas(DataTable customers) {

        customers.asList(CustomerDto.class).forEach(
                customer -> {
                    CustomerDto foundCustomer = allCustomers.get(customer.getCardNumber());

                    assertNotNull(foundCustomer);
                    assertEquals(foundCustomer.getCardNumber(), customer.getCardNumber());
                    assertEquals(foundCustomer.getLoyaltyLevel(), customer.getLoyaltyLevel());
                    assertEquals(foundCustomer.getTotalBet(), customer.getTotalBet(), 0.0001);
                }
        );
    }

    @Given("^There is given loyalty levels configuration$")
    public void thereIsGivenLoyaltyLevelsConfiguration(DataTable configuration) {
        loyaltyLevelsConfiguration.clear();
        loyaltyLevelsConfiguration.addAll(configuration.asList(LoyaltyLevelDto.class));

    }

    @When("^I add customer with card number \"([^\"]*)\"$")
    public void iAddCustomerWithCardNumber(String cardNumber) {
        try {
            customerFacade.add(cardNumber, loyaltyLevelsConfiguration);
        }
        catch (CustomerCardNumberDuplicationException ex) {
            loggedMassage = ex.getMessage();
        }
    }


    @And("^I ask for customer with card number \"([^\"]*)\"$")
    public void iAskForCustomerWithCardNumber(String cardNumber){
        customerFacade.findByCardNumber(cardNumber)
                .ifPresent(customer -> allCustomers.put(customer.getCardNumber(), customer));

    }

    @When("^I add new bet with value (\\d+) for customer with card number \"([^\"]*)\"$")
    public void iAddNewBetWithValueForCustomerWithCardNumber(double bet, String cardNumber) {
        try{
            customerFacade.bet(cardNumber, bet);
        }
        catch(CustomerDoesntExistsException ex){
            loggedMassage = ex.getMessage();
        }

    }

    @And("^I update customers loyalty levels$")
    public void iUpdateCustomersLoyaltyLevels() {
        customerFacade.updateLoyaltyLevels(loyaltyLevelsConfiguration);
    }

    @Then("^CustomerCardNumberDuplicationException is thrown$")
    public void CustomerCardNumberDuplicationExceptionIsThrown() {
        assertEquals(this.loggedMassage, "Customer with given card number already exists");
    }

    @Then("^CustomerDoesntExistsException is thrown$")
    public void CustomerDoesntExistsExceptionExceptionIsThrown() {
        assertEquals(this.loggedMassage, "Customer with given card number doesn't exists");
    }
}
