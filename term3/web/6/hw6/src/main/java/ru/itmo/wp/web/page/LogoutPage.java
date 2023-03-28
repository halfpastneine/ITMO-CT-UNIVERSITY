package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.service.EventService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused"})
public class LogoutPage extends Page {

    private final EventService eventService = new EventService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        if (request.getSession().getAttribute("user") != null) {
            Event event = new Event();
            event.setType(Event.Type.LOGOUT);
            event.setUserId(getUser(request).getId());
            eventService.logout(event);
            request.getSession().removeAttribute("user");
            setMessage(request, "Good bye. Hope to see you soon!");
        }
        throw new RedirectException("/index");
    }
}
