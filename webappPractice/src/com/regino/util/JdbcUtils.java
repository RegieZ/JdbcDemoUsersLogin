package com.regino.util;

import java.sql.*;
import java.util.ResourceBundle;

public class JdbcUtils {
    // 声明变量
    private static String driver = null;
    private static String url = null;
    private static String user = null;
    private static String password = null;

    // 加载jdbc.properties配置文件，初始化变量
    static {
        // new Properties().load();
        // sun公司专门提供了一从src目录下加载properties类型的工具类 ResourceBundle
        ResourceBundle jdbc = ResourceBundle.getBundle("jdbc");
        driver = jdbc.getString("jdbc.driver");
        url = jdbc.getString("jdbc.url");
        user = jdbc.getString("jdbc.user");
        password = jdbc.getString("jdbc.password");
    }

    // 1.注册驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // e.printStackTrace();
            throw new RuntimeException("加载MySQL驱动失败");
        }
    }

    // 2.提供获取连接的静态方法
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // 3.提供释放资源的方法
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 重载关闭方法
    public static void close(Statement statement, Connection connection) {
        close(null, statement, connection);
    }
}