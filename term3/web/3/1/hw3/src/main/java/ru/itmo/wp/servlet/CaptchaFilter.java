package ru.itmo.wp.servlet;

import ru.itmo.wp.util.ImageUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Random;



public class CaptchaFilter extends HttpFilter {

    Random random = new Random();

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getSession().getAttribute("true") != null) {
            super.doFilter(request, response, chain);
        } else {
            String number = null;
            HttpSession session = request.getSession();
            if (request.getParameter("captcha") != null) {
                number = String.valueOf(random.nextInt(899) + 100);
            } else {
                if (session.getAttribute("answer") == null) {
                    number = String.valueOf(random.nextInt(899) + 100);
                } else {
                    number = String.valueOf(session.getAttribute("answer"));
                }
            }
            byte[] bytes = ImageUtils.toPng(number);
            String user_ans = request.getParameter("captcha");
            if (user_ans != null) {
                if (Objects.equals(user_ans, session.getAttribute("answer"))) {
                    session.setAttribute("true", true);
                    super.doFilter(request, response, chain);
                    return;
                }
            }
            try (FileOutputStream fos = new FileOutputStream(getServletContext().getRealPath("/static/1.png"))) {
                fos.write(bytes);
            }
            try (OutputStream outputStream = response.getOutputStream()) {
                File file = new File(getServletContext().getRealPath("/static/captchafilter.html"));
                Files.copy(file.toPath(), outputStream);
            }
            session.setAttribute("answer", number);
        }
    }
}
