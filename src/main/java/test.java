import db.Database;
import model.Usedata;

import java.sql.SQLException;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Database database = new Database();
        database.showConnect();
        //database.insert("xiaowang","202111040632@ysu");

//        ArrayList<Usedata> usedata = database.getAlluser();
//        System.out.println("总表里面共有："+usedata.size());

//        Usedata usedata = database.getUser(2);
//        System.out.println(usedata.getId()+usedata.getName());

        Usedata usedata = database.login(String.valueOf(1),"202111040631@ysu");
        System.out.println(usedata);
        if(usedata==null){
            System.out.println("用户名不存在或者密码错误");
        }
        else{
            System.out.println("欢迎登陆"+usedata.getName());
        }

        database.close();
    }
}
