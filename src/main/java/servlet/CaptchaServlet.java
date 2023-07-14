package servlet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CaptchaServlet")
public class CaptchaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置响应内容类型
        response.setContentType("image/jpeg");

        // 创建验证码图片
        int width = 120;
        int height = 40;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // 生成随机验证码
        Random random = new Random();
        String captcha = generateCaptcha(random);

        // 将验证码存储在会话中
        HttpSession session = request.getSession();
        session.setAttribute("captcha", captcha);

        // 设置画笔样式
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.BLACK);

        // 绘制验证码
        int x = 30;
        int y = 25;
        g.drawString(captcha, x, y);

        // 绘制干扰线
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < 6; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }

        // 输出图像到客户端
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpeg", out);
        out.close();
    }

    private String generateCaptcha(Random random) {
        // 生成包含数字和字母的随机验证码
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
}
