package com;

import java.util.Properties;

public abstract class CreateConnectionPropertiesService {

    public static Properties populateProps(String user, String password){
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        return props;
    }
}
