package util;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by x_deng on 2017/11/8.
 */
public class DBUtils {

    //数据库连接地址
    public static String URL;
    //用户名
    public static String USERNAME;
    //密码
    public static String PASSWORD;
    //mysql的驱动类
    public static String DRIVER;

    private static ResourceBundle rb = ResourceBundle.getBundle("db");

    private DBUtils() {
    }

    //使用静态块加载驱动程序
    static {
        URL = rb.getString("url");
        USERNAME = rb.getString("username");
        PASSWORD = rb.getString("password");
        DRIVER = rb.getString("driverClassName");
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //定义一个获取数据库连接的方法
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取连接失败");
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     *
     * @param rs
     * @param stat
     * @param conn
     */
    public static void close(ResultSet rs, Statement stat, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (stat != null) stat.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

