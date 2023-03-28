package ru.itmo.web.hw4.util;

import ru.itmo.web.hw4.model.Post;
import ru.itmo.web.hw4.model.Type;
import ru.itmo.web.hw4.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov", Type.RED),
            new User(6, "pashka", "Pavel Mavrin", Type.BLUE),
            new User(9, "geranazavr555", "Georgiy Nazarov", Type.RED),
            new User(11, "tourist", "Gennady Korotkevich", Type.GREEN)
    );

    private static final List<Post> POSTS  = Arrays.asList(
            new Post(123, "Number2", "Сделайте, чтобы подчеркивался текущий пункт в меню. Подумайте, что надо доложить в data, чтобы была возможность такое сделать. " +
                    "Минимизируйте дублирование кода в шаблонах вокруг menu items.", 1),
            new Post(213, "Number 3", "Исправьте FreemarkerServlet, чтобы по заходу на страницу без пути (по-умолчанию) " +
                    "и на страницу / осуществлялся мягкий (временный) редирект на /index.", 6),
            new Post(2, "Number 4", "Поддержите новый объект предметной области Post. У Post должно быть четыре поля id (long), title (String), text (String) и user_id (long). " +
                    "Создайте в системе по аналогии с User серию постов с разумными содержаниями (модифицируйте DataUtil). Используя вашу разметку из второго ДЗ отобразите на главной список всех постов в обратном порядке (от последнего к первому). " +
                    "Если длина text превышает 350 символов, то обрезайте его и используйте символ многоточия в конце (сокращайте длинные тексты). Страницу со списком пользователей перенесите в отдельную страницу /users. " +
                    "Измените её разметку так, чтобы использовать вёрстку таблицы из второго ДЗ для их отображения. Добавьте в меню пункт USERS.", 1)
    );

    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("users", USERS);
        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }
        data.put("posts", POSTS);
    }
}
