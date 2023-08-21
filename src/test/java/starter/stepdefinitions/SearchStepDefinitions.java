package starter.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.*;

public class SearchStepDefinitions {

    @When("he calls endpoint {string}")
    public void heCallsEndpoint(String arg0) {
        SerenityRest.given().get(arg0);
    }

    @Then("he sees the results displayed for searched product")
    public void heSeesTheResultsDisplayedForProduct() {
        restAssuredThat(response -> response.statusCode(200));
    }

    @Then("he sees the results displayed for {string}")
    public void heSeesTheResultsDisplayedForSpecialProduct(String productName) {
        restAssuredThat(response -> lastResponse().jsonPath().getList("title").forEach(tile -> tile.toString().contains(productName)));
    }

    @Then("he does not see the results")
    public void heDoesNotSeeTheResults() {
        restAssuredThat(response -> response.body("detail.error", equalTo(true)));
    }
}
