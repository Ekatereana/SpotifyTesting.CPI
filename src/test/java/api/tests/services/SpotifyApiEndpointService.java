package api.tests.services;

import api.tests.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class SpotifyApiEndpointService implements Config {
    public static RequestSpecification given() {
        return RestAssured
                .given()
                .log().uri()
                .log().body()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }
}
