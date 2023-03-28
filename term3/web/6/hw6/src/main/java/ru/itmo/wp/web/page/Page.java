package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class Page {

    private final UserService userService = new UserService();

    private void action(HttpServletRequest request, Map<String, Object> view) {}

        protected void before(HttpServletRequest request, Map<String, Object> view){
        view.put("userCount", userService.findCount());
        putUser(request, view);
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
    }
    protected void after(HttpServletRequest request, Map<String, Object> view){
        view.put("userCount", userService.findCount());
    }

    protected void setMessage(HttpServletRequest request, String message) {
        request.getSession().setAttribute("message", message);
    }

    void setUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute("user", user);
    }

    User getUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }

    public static void putUser(HttpServletRequest request, Map<String, Object> view) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            view.put("user", user);
        }
    }



}
