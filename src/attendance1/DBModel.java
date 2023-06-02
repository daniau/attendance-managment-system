/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.ds.PGSimpleDataSource;

/**
 *
 * @author hp
 */
public class DBModel {

    private static DBModel dbmodel = null;
    //Connection con = null;

    //here our queries method
    DBModel() {
    }
    Connection con;
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "Dania123**";

    public void connect() {
        PGSimpleDataSource source = new PGSimpleDataSource();
        source.setServerName("localhost");
        source.setDatabaseName("Project2");
        source.setUser("postgres");
        source.setPassword("Dania123**");
        source.setCurrentSchema("public");

        try {
            con = source.getConnection();
            System.out.println("Connected to database");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }



    public ArrayList<String> getCourseName() {

        ArrayList<String> courseNames = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
             connect();
            statement = con.createStatement();
            String sqlQuery = "SELECT title  FROM courses ";
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String courseName = resultSet.getString("title");
                courseNames.add(courseName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return courseNames;

    }
    //this is test comment
    public ArrayList<String> getInstructorNames() {
        ArrayList<String> instructorNames = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connect();
           statement = con.createStatement();
            String sqlQuery = "SELECT name FROM instructors ";
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String instructorName = resultSet.getString("name");
                instructorNames.add(instructorName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return instructorNames;
    }
      public ArrayList<String> getRoom() {
        ArrayList<String> room = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connect();
           statement = con.createStatement();
            String sqlQuery = "SELECT room_name FROM courses  ";
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String instructorName = resultSet.getString("room_name");
                room.add(instructorName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return room;
    }
      public ArrayList<String> getStud() {
        ArrayList<String> id = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connect();
           statement = con.createStatement();
            String sqlQuery = "SELECT student_id FROM students  ";
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String instructorName = resultSet.getString("student_id");
                id.add(instructorName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return id;
    }
      public ArrayList<String> getLec() {
        ArrayList<String> lec = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connect();
           statement = con.createStatement();
            String sqlQuery = "SELECT l_title FROM lectures ";
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String instructorName = resultSet.getString("l_title");
                lec.add(instructorName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lec;
    }
    
}
