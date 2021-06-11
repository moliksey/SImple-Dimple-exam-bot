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

    public String getTask(String Variant) throws SQLException, ClassNotFoundException {
        ResultSet resSet = null;
        String select = "SELECT *" + " FROM " + Const.TABLE_NAME + " WHERE " + Const.TASKS_VARIANT +
                " =" + Variant;
        String Task = null;
        try {
            Statement statement = getDbConnection().createStatement();
            resSet = statement.executeQuery(select);
            Task = resSet.getString(Const.TASKS_TASK);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return Task;
    }

    public String getAnswers(String Variant) throws SQLException, ClassNotFoundException {
        ResultSet resSet = null;
        String select = " SELECT *" + " FROM " + Const.TABLE_NAME + " WHERE " + Const.TASKS_VARIANT +
                " =" + Variant;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        String Answers = resSet.getString(Const.TASKS_ANSWERS);
        return Answers;
    }

    public String getSolutions(String Variant) throws SQLException, ClassNotFoundException {
        ResultSet resSet = null;
        String select = " SELECT *" + " FROM " + Const.TABLE_NAME + " WHERE " + Const.TASKS_VARIANT +
                "=" + Variant;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        String Solutions = resSet.getString(Const.TASKS_SOLUTIONS);
        return Solutions;
    }

}
