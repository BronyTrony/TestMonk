package com.webserver.demo.models;

import java.sql.*;

public class SQLQueries {
    static String url = "jdbc:postgresql://192.168.1.95:5432/shop";
    static String dbusr = "postgres";
    static String dbpass = "postgrespassword";

    public static User Select(String login) {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(SQLQueries.url, SQLQueries.dbusr, SQLQueries.dbpass);

            Statement stat = conn.createStatement();

            String query = "select users.login, password, date, email from Users join Emails" +
                    " on Users.login = Emails.login where users.login like '" + login + "';";

            ResultSet rs;
            rs = stat.executeQuery(query);
            if (rs.next()){
                if (rs.getString(1) != null){
                    return new User(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4));
                }else{
                    return new User(null, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public static int Insert(User k) {

        String query = "insert into Users (login, password, date) values (?, ?, ?);  \n insert into Emails (login, email) values (?, ?);";

        try(Connection conn = DriverManager.getConnection(SQLQueries.url, SQLQueries.dbusr, SQLQueries.dbpass);
            PreparedStatement ps = conn.prepareStatement(query)){

            ps.setString(1, k.login);
            ps.setString(2, k.password);
            ps.setDate(3, k.date);
            ps.setString(4, k.login);
            ps.setString(5, k.email);

            int upd = ps.executeUpdate();

            return (upd);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
