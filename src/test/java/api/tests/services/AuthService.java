package api.tests.services;

import api.tests.Config;
import io.restassured.path.json.JsonPath;
import lombok.SneakyThrows;
import org.apache.http.client.utils.URIBuilder;

import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static io.restassured.RestAssured.given;

public abstract class AuthService implements Config {
    private static final String CLIENT_ID = System.getProperty("client_id");
    private static final String CLIENT_SECRET = System.getProperty("client_secret");



    public static String getToken(String scopes) {
        var auth = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic " +
                        Base64.getEncoder().encodeToString(
                                (CLIENT_ID + ":" + CLIENT_SECRET)
                                        .getBytes(StandardCharsets.UTF_8)
                        )
                )
                .param("grant_type", "client_credentials")
                .queryParam("scopes", scopes)
                .post(Config.AUTH_TOKEN_URL)
                .body();

        System.out.println("client_id" + CLIENT_ID);
        return JsonPath.from(auth.asString()).get("access_token").toString();
    }



}
