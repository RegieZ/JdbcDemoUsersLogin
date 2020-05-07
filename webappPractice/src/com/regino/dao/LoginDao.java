package com.regino.dao;

import com.regino.util.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {
    public boolean findById(String username, String password) {
        // 调用数据库查询
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // 2.操作JDBC
            // 2.1 获取连接
            connection = JdbcUtils.getConnection();
            // 2.2 编写sql
            String sql = "select * from user where username ='" + username + "' and password ='" + password + "'";
            System.out.println(sql);
            // 2.3 获取sql执行对象
            statement = connection.createStatement();
            // 2.4 执行sql并返回结果
            resultSet = statement.executeQuery(sql);

            // 3.判断是否登录成功
            // 正确则返回至少一条，错误则什么都没有，所以只要判断游标能否下移一次即可
            if (resultSet.next()) {// 成功
                return true;
            } else {// 失败
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, statement, connection);
        }
        return false;
    }
}
