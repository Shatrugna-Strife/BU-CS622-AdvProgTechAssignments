package com.met622.Entity;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneOffset;

import static com.met622.singleton.Singleton.parserUtil;

/**
 * MysqlEntity class, it prepares the database and populates the table with the dblp data.
 */
public class MysqlEntity {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    public MysqlEntity() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connect = DriverManager
                .getConnection("jdbc:mysql://localhost:3306?"
                        + "user=root&password=admin123");
        statement = connect.createStatement();
        statement.execute("CREATE DATABASE IF NOT EXISTS bu;");
        statement = connect.createStatement();
        statement.execute("DROP TABLE IF EXISTS bu.DBLP;");
        statement = connect.createStatement();
        statement.execute("CREATE TABLE bu.DBLP(\n" +
                "author VARCHAR(1000),\n" +
                "title text,\n" +
                "pages VARCHAR(100),\n" +
                "mdate date,\n" +
                "volume VARCHAR(10),\n" +
                "journal VARCHAR(1000),\n" +
                "ee VARCHAR(5000),\n" +
                "url VARCHAR(1000)\n" +
                ");");
    }

    public void insertData() throws SQLException, ParserConfigurationException, IOException, SAXException {
        parserUtil.sqlInsert(connect);
    }
    public int textSearch(String text){
        String query = "SELECT count(*) AS count from bu.DBLP WHERE lower(bu.DBLP.title) like '%" + text + "%'";
        try (Statement stmt = connect.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int rangeTextSearch(String text, String from, String to){
        String query = "SELECT count(*) AS count from bu.DBLP WHERE lower(bu.DBLP.title) like '%" + text + "%' and mdate>date('"+from+"') and mdate<date('"+to+"');";
        try (Statement stmt = connect.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
