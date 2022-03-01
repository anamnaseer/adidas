package com.example.adidas.stepDefinitions;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import pageObjects.PetPage;


public class AptTestSteps
{
    private static final Logger log = LoggerFactory.getLogger(AptTestSteps.class);
    private static Response response;
    private static Response addResponse;
    private static String addedID;
    PetPage petPage = new PetPage();

    @Given("I send get request to fetch {string} type pets")
    public void iSendGetRequestToFetchAvailableTypePets(String type)
    {
        response = petPage.findByStatus(type);
        log.debug("Body of response:\n " + response.getBody().asString());

    }

    @Then("We validate the {int} response was send")
    public void weValidateTheCountOfPetsFound(int code)
    {
        Assert.assertEquals(code, response.getStatusCode());
    }

    @Given("I send post request to add new {string} type pet with id {string}")
    public void iSendPostRequestToAddNewAvailableTypePet(String status, String id)
    {
        addedID = id;
        addResponse = petPage.addNewPetByStatus(status, addedID);
        log.info("Body of response:\n " + addResponse.getBody().asString());
        Assert.assertEquals(200, addResponse.getStatusCode());
    }

    @Then("We validate the added pet response")
    public void weValidateTheAddedPetResponse()
    {
        Assert.assertEquals(addedID, addResponse.path("id").toString());
    }

    @Given("I send put request to update pet with id {string} to {string}")
    public void iSendPutRequestToUpdatePetWithIdToSold(String id, String status)
    {
        response = petPage.updatePetById(id, status);
        log.info("Body of response:: " + response.getBody().asString());
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("We validate the added pet response updated to {string} with id {string}")
    public void weValidateTheAddedPetResponseUpdatedToSold(String status, String id)
    {
        response = petPage.getPetById(id);
        log.info("Body of response:: " + response.getBody().asString());
        Assert.assertEquals(status, response.path("status").toString());

    }

    @Given("I send delete request to delete pet with id {string}")
    public void iSendDeleteRequestToDeletePetWithId(String id)
    {
        response=petPage.deletePetByID(id);
        log.info("Body of response:: " + response.getBody().asString());
        Assert.assertEquals(404, response.getStatusCode());
    }
}
