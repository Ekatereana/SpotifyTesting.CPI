package api.cucumber.tests;

import api.Config;
import api.services.AuthService;
import api.services.SpotifyApiEndpointService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ResponseBodyExtractionOptions;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class SpotifyPlaybackApiTestSteps implements Config {
    private int statusCode;
    private String TOKEN;
    private final String TRACK_URL =
            "https://open.spotify.com/track/0ZBC6Eg6DjZRhgUmFdtzGH?si=bda424b075994711";
    private ResponseBodyExtractionOptions resBody;

    @Given("^client credentials$")
    public void temporary_vars() {
//        System.setProperty("client_id", "e6726f7250e24b098f312c683d93c6a8");
//        System.setProperty("client_secret", "e6c5c21ba8794c64a602c272cff4344e");
        TOKEN = AuthService.getToken("playlist-read-private");
    }

    @When("^I add new track to queue with url \"([^\"]*)\"$")
    public void i_add_new_track_queue_with_url(String track_url) {
        var res = given()
                .header("Authorization", "Bearer " + TOKEN)
                .queryParam("uri", track_url)
                .baseUri(BASE_URL)
                .post(PLAYER_ADD_URL);

        resBody = res.body();
        statusCode = res.statusCode();

    }

    @Then("spotify responds with status {int}")
    public void spotify_responds_with_status(Integer expectedStatusCode) {
        assertEquals((int)expectedStatusCode, statusCode);
    }
}
