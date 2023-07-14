package com.wang.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
															  //���ݿ���
	public static String db_url = "jdbc:mysql://localhost:3306/home?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT";
    public static String db_user = "root";   //�û���
    public static String db_password = "123456"; //����
     
    public static Connection getConn () {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//��������
            conn = DriverManager.getConnection(db_url, db_user, db_password);//��ȡ���Ӷ���
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
     
    /**
     * �ر�����
     * @param state
     * @param conn
     */
    public static void close (Statement state, Connection conn) {
        if (state != null) {
            try {
                state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
     
    /**
     * �ر�����
     * @param rs
     * @param state
     * @param conn
     */
    public static void close (ResultSet rs, Statement state, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (state != null) {
            try {
                state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
 
}
