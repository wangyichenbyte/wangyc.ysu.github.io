package db;

import model.Usedata;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    Connection conn = null;

    public Database() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");//注册驱动
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/home?serverTimezone=GMT%2B8", "root", "123456");
        //测试是否可以连接成功

    }

    public void showConnect() {
        if (conn == null) {
            System.out.println("数据库连接失败");
        } else {
            System.out.println("数据库连接成功");
        }
    }

    //关闭数据库连接
    public void close() throws SQLException {
        conn.close();
    }

    //再login表里添加数据
    public void insert(String name, String pwd) throws SQLException {
        PreparedStatement prep = conn.prepareStatement("insert into home.login (Name, password) value (?,?)");
        prep.setString(1, name);
        prep.setString(2, pwd);
        prep.execute();
    }

    public ArrayList<Usedata> getAlluser() throws SQLException {
        ArrayList<Usedata> uselist = new ArrayList<Usedata>();
        PreparedStatement prep = conn.prepareStatement("select * from home.login");
        prep.execute();
        ResultSet resultSet = prep.executeQuery();
        while (resultSet.next()) {
            int myid = resultSet.getInt("id");
            String myname = resultSet.getString("Name");
            String mypwd = resultSet.getString("password");
            uselist.add(new Usedata(myid, myname, mypwd));
        }
        return uselist;
    }

    //在表里查询数据
    public Usedata getUser(String username) throws SQLException {
        PreparedStatement prep = conn.prepareStatement("select * from home.login where Name=?");
        prep.setString(1, username);
        prep.execute();
        ResultSet resultSet = prep.executeQuery();
        if (resultSet.next()) {
            int myid = resultSet.getInt("id");
            String myname = resultSet.getString("Name");
            String mypwd = resultSet.getString("password");
            return new Usedata(myid, myname, mypwd);
        }
        return null;
    }

    public Usedata login(String username, String pwd) throws SQLException {
        PreparedStatement prep = conn.prepareStatement("SELECT password FROM home.login WHERE Name=?");

        prep.setString(1, username);
        prep.execute();
        ResultSet resultSet = prep.executeQuery();
        if (resultSet.next()) {
            String mypwd = resultSet.getString("password");
            if (pwd.equals(mypwd)) {
                return getUser(username);
            } else
                return null;
        } else
            return null;
    }
}
