package com.mdzidko.loyaltylevelsbdd.customer.domain;

import com.mdzidko.loyaltylevelsbdd.customer.dto.CustomerDto;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CustomerTestSteps {

    private CustomerFacade customerFacade = new CustomerConfiguration().customerFacade();
    private Map<String, CustomerDto> allCustomers = new HashMap<>();


    @Given("^There are given customers$")
    public void thereAreGivenCustomers(DataTable customers) {

        allCustomers.clear();

        customers.asList(CustomerDto.class)
                .forEach(customer -> customerFacade.add(customer));
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
    }

    @Given("^There are given customer loyalty levels$")
    public void thereAreGivenCustomerLoyaltyLevels() {

    }

    @When("^I add customer with card number \"([^\"]*)\"$")
    public void iAddCustomerWithCardNumber(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I ask for customer with card number \"([^\"]*)\"$")
    public void iAskForCustomerWithCardNumber(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I add new bet with value (\\d+) for customer with card number \"([^\"]*)\"$")
    public void iAddNewBetWithValueForCustomerWithCardNumber(int arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I update customer loyalty levels$")
    public void iUpdateCustomerLoyaltyLevels() {

    }
}
