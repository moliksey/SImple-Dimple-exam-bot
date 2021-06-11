package com.simple.dimple.bot;

import java.sql.*;

public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        dbConnection = DriverManager.getConnection(url, user, password);

        return dbConnection;
    }

    public void signUpUser(String Login){

    }

    public String getTask(String Variant) throws SQLException, ClassNotFoundException {
        ResultSet resSet = null;
        String select = "SELECT *" + " FROM " + Const.TABLE_TASKS + " WHERE " + Const.TASKS_VARIANT +
                "=" + Variant;
        String Task = null;
        try {
            Statement statement = getDbConnection().createStatement();
            resSet = statement.executeQuery(select);
            while(resSet.next()) {
                Task = resSet.getString(Const.TASKS_TASK);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return Task;
    }

    public String getAnswers(String Variant) throws SQLException, ClassNotFoundException {
        ResultSet resSet = null;
        String select = " SELECT *" + " FROM " + Const.TABLE_TASKS + " WHERE " + Const.TASKS_VARIANT +
                " =" + Variant;
        String Answers = null;
        try {
            Statement statement = getDbConnection().createStatement();
            resSet = statement.executeQuery(select);
            while (resSet.next()) {
                Answers = resSet.getString(Const.TASKS_ANSWERS);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return Answers;
    }

    public String getSolutions(String Variant) throws SQLException, ClassNotFoundException {
        ResultSet resSet = null;
        String select = "SELECT *" + " FROM " + Const.TABLE_TASKS + " WHERE " + Const.TASKS_VARIANT +
                "=" + Variant;
        String Solutions = null;
        try {
            Statement statement = getDbConnection().createStatement();
            resSet = statement.executeQuery(select);
            while (resSet.next()) {
                Solutions = resSet.getString(Const.TASKS_SOLUTIONS);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return Solutions;
    }

    public int getResult(int Primary_Score) {
        ResultSet resSet = null;
        String select = "SELECT *" + " FROM " + Const.TABLE_EVALUATION_CRITERIA_IT + " WHERE " +
                Const.EVALUATION_CRITERIA_IT_PRIMARY_SCORE + "=" + String.valueOf(Primary_Score);
        int Result = 0;
        try {
            Statement statement = getDbConnection().createStatement();
            resSet = statement.executeQuery(select);
            while (resSet.next()) {
                Result = resSet.getInt(Const.EVALUATION_CRITERIA_IT_SECONDARY_SCORE);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Result;
    }

}
