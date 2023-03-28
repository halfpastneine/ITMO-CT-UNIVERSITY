package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        String[] req = uri.split("\\+");
        String[] reqs = new String[req.length];
        boolean tr = false;
        for (int i = 0; i < req.length; i++) {
            File file = new File("src/main/webapp/static/" + req[i]);
            if (!file.isFile()) {
                file = new File(getServletContext().getRealPath("/static/" + req[i]));
            }
            if (file.isFile()) {
                reqs[i] = String.valueOf(file.toPath());
                reqs[i] = reqs[i].replace('\\', '/');
                if (i == 0) {
                    response.setContentType(getServletContext().getMimeType(file.getName()));
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                tr = true;
            }
        }
        if (!tr) {
            for (String s : reqs) {
                try (OutputStream outputStream = response.getOutputStream()) {
                    Files.copy(Paths.get(s), outputStream);
                }
            }
        }
    }
}
