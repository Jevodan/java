package ru.jevo.chat.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.jevo.chat.model.User;
import ru.jevo.chat.server.api.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.LinkedHashMap;
import java.util.Map;

@ApplicationScoped
public class UserServiceBean implements UserService {

    @NotNull
    private Map<String, User> users = new LinkedHashMap<>();

    /**
     * Вызовется ,как только класс создастся в памяти CDI
     */
    @PostConstruct
    private void init() {
        registry("test", "test");
        registry("leka", "leka");
    }

    @Override
    public @Nullable User findByLogin(@Nullable String login) {
       if (!checkLogin(login)) return null;
       return users.get(login);
    }

    @Override
    public boolean check(@Nullable final String login, @Nullable final String password) {
        if (checkLogin(login) && checkPassword(password)) {
            final User user = findByLogin(login);
            if (user == null) return false;
            return password.equals(user.getPassword());
        }
        return false;
    }

    @Override
    public boolean registry(@Nullable String login, @Nullable String password) {
        if (checkLogin(login) && checkPassword(password)) {
            if (exists(login)) return false;
            @NotNull final User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            users.put(login, user);
            System.out.println("Добавлен пользователь: " + user.getLogin());
            return true;
        }
        return false;
    }

    @Override
    public boolean exists(@Nullable String login) {
        return users.containsKey(login);
    }

    private boolean checkLogin(@Nullable String login) {
        return (login == null || login.isEmpty()) ? false : true;
    }

    private boolean checkPassword(@Nullable String password) {
        return (password == null || password.isEmpty()) ? false : true;
    }
}
