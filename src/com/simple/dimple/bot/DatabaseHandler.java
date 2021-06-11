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
            dbConnection.close();
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
            dbConnection.close();
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
            dbConnection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return Solutions;
    }

    public String getResult(int Primary_Score) {
        ResultSet resSet = null;
        if(Primary_Score==0)
            return "0";
        Primary_Score+=5;
        String select = "SELECT *" + " FROM " + Const.TABLE_EVALUATION_CRITERIA_IT + " WHERE " +
                Const.EVALUATION_CRITERIA_IT_PRIMARY_SCORE + "=" + String.valueOf(Primary_Score);
        String Result = null;
        try {
            Statement statement = getDbConnection().createStatement();
            resSet = statement.executeQuery(select);
            while (resSet.next()) {
                Result = String.valueOf(resSet.getInt(Const.EVALUATION_CRITERIA_IT_SECONDARY_SCORE));
                dbConnection.close();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return Result;
    }
    public void Input_Variant(String Answers, String Task, String Solutions) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.TABLE_TASKS + "(" + Const.TASKS_ANSWERS + "," + Const.TASKS_TASK +
                "," + Const.TASKS_SOLUTIONS + ") " + "VALUES(?,?,?)";
        PreparedStatement PrSt = getDbConnection().prepareStatement(insert);
        PrSt.setString(2, Const.TASKS_ANSWERS);
        PrSt.setString(3, Const.TASKS_TASK);
        PrSt.setString(4, Const.TASKS_SOLUTIONS);
        try {
            PrSt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
