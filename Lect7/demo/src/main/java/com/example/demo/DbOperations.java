package com.example.demo;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DbOperations {

    private static volatile Connection connection;

    public static Connection getConnection() throws SQLException {

        if(connection == null){
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_application", "root", "73293181p@pA");
        }

        return connection;

    }




    public static void addUserInDB(String user) throws SQLException {
        getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (null, ?, ?)");
        preparedStatement.setString(1, user);
        preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
        int rows_affected = preparedStatement.executeUpdate();

        if(rows_affected > 0){
            System.out.println("succesfully inserted the msg in DB");
        }else{
            System.out.println("unable to insert the msg in DB");
        }

    }

    public static void chatBackUp(String user, String msg_id, String msg) throws SQLException {

        getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO chat_backup VALUES (?, ?, ?)");
        preparedStatement.setString(1, msg_id);
        preparedStatement.setString(2, user);
        preparedStatement.setString(3, msg);
        int rows_affected = preparedStatement.executeUpdate();

        if(rows_affected > 0){
            System.out.println("succesfully inserted the msg in DB");
        }else{
            System.out.println("unable to insert the msg in DB");
        }

    }

    public static void createUsersTable(String name) throws SQLException {

        getConnection();
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE " + name + "(id int primary key auto_increment, name VARCHAR(30), joining_time date)";
        statement.execute(sql);
    }

    public static void createChatTable(String name) throws SQLException{
        getConnection();
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE " + name + "(msg_id VARCHAR(40) primary key, name VARCHAR(30), msg VARCHAR(200))";
        statement.execute(sql);
    }
}





//
//import java.sql.*;
//
//public class DbOperations {
//
//    private static volatile Connection connection;
//
//    public static Connection getConnection() throws SQLException {
//        if(connection==null)
//        {
//            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_application","root","73293181p@pA");
//        }
//        return connection;
//    }
//
//    public static void addUserInDb(String user) throws SQLException {
//        getConnection();
//        PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO users Values(null,?,?");
//        preparedStatement.setString(1,user);
//        preparedStatement.setDate(2,new Date(System.currentTimeMillis()));
//        int rows_affected=preparedStatement.executeUpdate();
//        if(rows_affected>0)
//            System.out.println("successfully inserted the user int the table");
//        else
//            System.out.println("unable to insert the user into the table");
////        connection.close();
//    }
//
//    public static void chatBackUp(String user,String msg_id,String id) throws SQLException {
//        getConnection();
//        PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO chat_backup VALUES(?,?,?)");
//        preparedStatement.setString(1,msg_id);
//        preparedStatement.setString(2,user);
//        preparedStatement.setString(3,msg_id);
//        int row_affected=preparedStatement.executeUpdate();
//        if(row_affected>0)
//            System.out.println("successfully inserted the message into the db");
//        else
//            System.out.println("unable to insert the message in the db");
////        connection.close();
//    }
//
//    public static void createUserTable(String name) throws SQLException {
//        getConnection();
//        Statement statement=connection.createStatement();
//        String sql="CREATE TABLE "+name+"(id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(30),joining_time DATE)";
//        statement.execute(sql);
////        connection.close();
//    }
//    public static void  createChatTable(String name) throws SQLException {
//        getConnection();
//        Statement statement=connection.createStatement();
//        String sql="CREATE TABLE "+name+"(msg_id VARCHAR(40)PRIMARY KEY,name VARCHAR(30), msg VARCHAR(200))";
//        statement.execute(sql);
////        connection.close();
//    }
//}
