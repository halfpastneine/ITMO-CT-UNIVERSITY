package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.EventService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused"})
public class EnterPage extends Page{
    private final UserService userService = new UserService();
    private final EventService eventService = new EventService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private void enter(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String loginOrEmail = request.getParameter("loginOrEmail");
        String password = request.getParameter("password");
        userService.validateEnter(loginOrEmail, password);
        User user = userService.findByLoginAndPassword(loginOrEmail, password);
        if (user == null) {
            user = userService.findByEmailAndPassword(loginOrEmail, password);
        }
        setUser(request, user);
        setMessage(request, "Hello, " + user.getLogin());
        Event event = new Event();
        event.setType(Event.Type.ENTER);
        event.setUserId(user.getId());
        eventService.enter(event);
        throw new RedirectException("/index");
    }
}
