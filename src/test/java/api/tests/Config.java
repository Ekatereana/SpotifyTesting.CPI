package api.tests;

import java.net.URL;

public interface Config {
    String BASE_URL = "https://api.spotify.com/v1";
    String TRACK_URL = "/tracks/{id}";
    String AUTH_TOKEN_URL ="https://accounts.spotify.com/api/token";
    String PLAYER_ADD_URL = "me/player/queue";
    String ALBUM_URL= "/albums/{id}";
    String NewONe_URL= "/albums/{id}";
    String ALBUM_TRACK_URL = ALBUM_URL + "/tracks";

}
