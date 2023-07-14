package servlet;
import com.mysql.cj.PreparedQuery;
import db.Database;
import model.Usedata;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "RegisterServlet",urlPatterns = "/Register")
public class RegisterServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //解决网页乱码问题
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");//设置编码

        //int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String pwd=request.getParameter("pwd");
        String pwd2=request.getParameter("pwd2");
        PrintWriter out=response.getWriter();

        //判断这两个密码是不是一样的
        if(pwd.equals(pwd2)){
            try {
                Database database = new Database();
                database.insert(name ,pwd);
                out.println("注册成功");

                /*ArrayList<Usedata> usedata = database.getAlluser();
                out.println("总表里共有："+usedata.size());
                for (Usedata each:usedata){
                    out.println(each.getId()+"-----"+each.getName()+"----"+each.getPassword());
                }*/
                out.println("请返回登录界面" + "<a href= \"http://localhost:8080/webJZB_war_exploded/login.jsp\">返回登录</a>");

            } catch (ClassNotFoundException e) {

                e.printStackTrace();
                out.println("注册失败");
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("注册失败2");
            }
        }else {
            out.println("两次输入的密码不一致");
            out.println("请返回重新注册" + "<a href= \"http://localhost:8080/webJZB_war_exploded/Register.jsp\">返回注册</a>");
        }
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
