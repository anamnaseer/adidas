package pageObjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.configs.ConfigReader;


public class PetPage
{
    private static final String BASE_URL = ConfigReader.getInstance().getConfigValue("BASE_URL");
    private static final Logger log = LoggerFactory.getLogger(PetPage.class);

    public Response findByStatus(String status)
    {
        log.info("In findByStatus with BASE URL::" + BASE_URL);

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("accept", ContentType.JSON);

        return request.params("status", status).get("/pet/findByStatus");
    }

    public Response getPetById(String id)
    {
        log.info("In findByStatus with BASE URL::" + BASE_URL);

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("accept", ContentType.JSON);

        return request.get("/pet/"+id);
    }

    public Response addNewPetByStatus(String status, String id)
    {
        log.info("Adding new pet::" + BASE_URL);

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        String requestBody = "{\"id\":"+id+",\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";

        request.header("Content-type", "application/json");

        return request.body(requestBody).post("/pet");
    }

    public Response updatePetById(String id,String status)
    {
        log.info("Adding new pet::" + BASE_URL);

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        String requestBody = "{\"id\":"+id+",\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\""+status+"\"}";
        request.header("Content-type", "application/json");

        return request.body(requestBody).put("/pet");
    }

    public Response deletePetByID(String id)
    {
        log.info("Deleting new pet::" + BASE_URL);

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("api_key","special-key");

        return request.delete("/pet/"+id);
    }
}
