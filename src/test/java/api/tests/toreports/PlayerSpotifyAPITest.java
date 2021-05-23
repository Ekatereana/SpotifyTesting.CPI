package api.tests.toreports;

import api.Config;
import api.services.AuthService;
import api.services.SpotifyApiEndpointService;
import lombok.SneakyThrows;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(SerenityRunner.class)
public class PlayerSpotifyAPITest implements Config {

    private static final String NOT_ALBUM_ID = "not id. any way";
    private final String ERROR = "error";
    private final String MESSAGE = "message";
    private final String NAME = "name";
    private final String ALBUM_ID = "12ViHXhXTAxPEDM1TYCxvF";
    private final String ALBUM_NAME = "The Wrong Kind of War";
    private final String INVALID_ID = "invalid id";
    private static String token;


    @BeforeClass
    public static void init() {
        System.setProperty("client_id", "e6726f7250e24b098f312c683d93c6a8");
        System.setProperty("client_secret", "e6c5c21ba8794c64a602c272cff4344e");
        token = AuthService.getToken("playlist-read-private");
    }

    @Test
    @Step("add new track to queue. Should Fail because this feature is premium")
    public void verifyPostToPlaybackOk() {
       SpotifyApiEndpointService.given()
                .header("Authorization", "Bearer " + token)
                .queryParam("uri", TRACK_URL)
                .post(PLAYER_ADD_URL)
                .then()
                .statusCode(200)
                .assertThat()
                .body(Matchers.notNullValue());
    }

    @SneakyThrows
    @Test
    @Step("get album by it's id. Should pass")
    public void verifyGetAlbumById() {
        var res = SpotifyApiEndpointService.given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", ALBUM_ID)
                .get(ALBUM_URL)
                .then()
                .statusCode(200)
                .extract()
                .body();
        JSONObject album = new JSONObject(res.asString());
        assertEquals(ALBUM_NAME, album.get(NAME));

    }


    @SneakyThrows
    @Test
    @Step("verify not existing album return error. Should pass")
    public void verifyGetNotExistAlbumById() {
        var res = SpotifyApiEndpointService.given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", NOT_ALBUM_ID)
                .get(ALBUM_URL)
                .then()
                .statusCode(400)
                .extract()
                .body();
        JSONObject error = new JSONObject(res.asString()).getJSONObject(ERROR);
        assertEquals(INVALID_ID, error.get(MESSAGE));

    }

    @Test
    @Title("get album's tracks by it's id and verify body not nullable. Should pass")
    public void verifySongFromAlbumNotExists() {
        var res = SpotifyApiEndpointService.given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", ALBUM_ID)
                .get(ALBUM_TRACK_URL)
                .then()
                .statusCode(200)
                .body(Matchers.notNullValue());

    }


}
