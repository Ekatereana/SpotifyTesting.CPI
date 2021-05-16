package com;

import java.sql.*;
import java.util.Properties;

public class PostgresSQLConnection implements Config {
    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(url, CreateConnectionPropertiesService.populateProps(user, password));

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select * from spotify_data");
            while (rs.next())
                System.out.println(
                        rs.getInt(1)
                                + "  "
                                + rs.getString(2)
                                + "  "
                                + rs.getString(3)
                                + "  "
                                + rs.getString(4)
                                + "  "
                                + rs.getInt(5)
                                + "  "
                                + rs.getInt(6)
                                + "  "
                                + rs.getInt(7)
                );

            conn.close();

        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }
}
