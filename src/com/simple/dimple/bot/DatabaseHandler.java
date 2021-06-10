package com.simple.dimple.bot;

import java.sql.*;

public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(url, user, password);

        return dbConnection;
    }

    public void signUpUser(String Login){

    }

    public ResultSet getTask(String Variant) throws SQLException, ClassNotFoundException {
        ResultSet resSet = null;
        String select = "SELECT" + Const.TASKS_TASK + "FROM" + Const.TABLE_NAME + "WHERE" + Const.TASKS_VARIANT +
                "=" + Variant;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ResultSet getAnswers(String Variant) throws SQLException, ClassNotFoundException {
        ResultSet resSet = null;
        String select = "SELECT" + Const.TASKS_ANSWERS + "FROM" + Const.TABLE_NAME + "WHERE" + Const.TASKS_VARIANT +
                "=" + Variant;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ResultSet getSolutions(String Variant) throws SQLException, ClassNotFoundException {
        ResultSet resSet = null;
        String select = "SELECT" + Const.TASKS_SOLUTIONS + "FROM" + Const.TABLE_NAME + "WHERE" + Const.TASKS_VARIANT +
                "=" + Variant;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

}
