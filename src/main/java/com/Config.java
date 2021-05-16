package com;

import java.util.Properties;

public interface Config {
    String url = "jdbc:postgresql://localhost:5432/Spotify_test_data";
    String user = "postgres";
    String password = "postgres";
    String getCount = "select count(id), genre from spotify_data as s\n" +
            "    group by genre\n" +
            "    order by count";
    String getPop = "select * from spotify_data\n" +
            "where genre like 'pop'";

}
