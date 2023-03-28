package ru.itmo.web.hw4.model;

public class User {
    private final long id;
    private final String handle;
    private final String name;
    private final Type type;

    public User(long id, String handle, String name, Type type) {
        this.id = id;
        this.handle = handle;
        this.name = name;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getHandle() {
        return handle;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }
}
