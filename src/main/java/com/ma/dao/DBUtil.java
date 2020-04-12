package com.ma.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/tangshi?characterEncoding=utf8&useSSL=true";
    private static final String username = "root";
    private static final String password = "123";
    private static volatile DataSource dataSource = null;
    public static DataSource getDataSource(){
        if (dataSource == null){
            synchronized (DBUtil.class){
                if (dataSource == null){
                    MysqlDataSource mysqlDataSource = new MysqlDataSource();
                    mysqlDataSource.setCharacterEncoding("utf8");
                    mysqlDataSource.setURL(url);
                    mysqlDataSource.setUser(username);
                    mysqlDataSource.setPassword(password);
                    dataSource = mysqlDataSource;
                }
            }
        }
        return dataSource;
    }
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (resultSet != null){
            resultSet.close();
        }if (statement!=null){
            statement.close();
        }if (connection != null){
            connection.close();
        }
    }
}
