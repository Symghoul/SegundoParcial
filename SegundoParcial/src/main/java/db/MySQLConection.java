package db;

import java.sql.*;

public class MySQLConection {

    private Connection connection;

    public MySQLConection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private void connect(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/segundo_parcial","root","");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean createDataBase(){
        boolean success = false;
        try {
            connect();
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS tareas(id INT PRIMARY KEY AUTO_INCREMENT, texto VARCHAR(300), fecha INT (20), tipo INT(5))");
            success=true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            success = false;
        }finally {
            disconnect();
        }
        return success;
    }

    //Ordenes
    public boolean executeSQL(String sql){
        boolean success = false;
        try {
            connect();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            success = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            disconnect();
        }
        return success;
    }

    //Query
    public ResultSet query(String sql) {
        ResultSet output = null;
        try {
            connect();
            Statement statement = connection.createStatement();
            output = statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return output;
    }
}
