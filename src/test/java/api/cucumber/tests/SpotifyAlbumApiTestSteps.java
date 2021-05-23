package api.cucumber.tests;

import api.Config;
import api.services.AuthService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class SpotifyAlbumApiTestSteps implements Config  {

    private String api;
    private String TOKEN;
    private int statusCode;
    private ResponseBodyExtractionOptions resBody;

    @Given("^temporary vars$")
    public void temporary_vars() {
        api = BASE_URL + ALBUM_URL;
        System.setProperty("client_id", "e6726f7250e24b098f312c683d93c6a8");
        System.setProperty("client_secret", "e6c5c21ba8794c64a602c272cff4344e");
        TOKEN = AuthService.getToken("playlist-read-private");
    }

    @When("^I get album by \"([^\"]*)\" from spotify api$")
    public void i_get_album_by_from_spotify_api(String album_id) {
        var res = given()
                .header("Authorization", "Bearer " + TOKEN)
                .pathParam("id", album_id)
                .get(api);
        resBody = res.body();
        statusCode = res.statusCode();

    }

    @Then("spotify responds with status {int} and name is equals {string}")
    public void spotify_responds_with_status_and_name_is_equals(Integer expectedStatusCode, String album_name) {
        assertEquals(statusCode, (int)expectedStatusCode);
        JSONObject album = new JSONObject(resBody.asString());
        assertEquals(album_name, album.get("name"));
    }
}
