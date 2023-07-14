package servlet;
import db.Database;
import model.Usedata;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet",urlPatterns = "/Login")
public class LoginServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //解决网页乱码问题
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");//设置编码


        String username=request.getParameter("name");
        String pwd=request.getParameter("pwd");
        String userCaptcha = request.getParameter("captcha");
        PrintWriter out=response.getWriter();

        // 验证验证码
        HttpSession session = request.getSession();
        String storedCaptcha = (String) session.getAttribute("captcha");
        if (!userCaptcha.equalsIgnoreCase(storedCaptcha)) {
            out.println("验证码错误" + "<a href=\"http://localhost:8080/webJZB_war_exploded/login.jsp\">返回登录</a>");
            return;
        }

        try {
            Database database = new Database();
            Usedata usedata = database.login(username,pwd);
            if(usedata==null){
                out.println("用户名不存在或者密码错误" + "<a href= \"http://localhost:8080/webJZB_war_exploded/login.jsp\">返回登录</a>");
            }
            else{
                response.sendRedirect("http://localhost:8080/webJZB_war_exploded/index.jsp");
                out.println("欢迎登陆"+usedata.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("登录失败");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("登录失败2");
        }
    }
}