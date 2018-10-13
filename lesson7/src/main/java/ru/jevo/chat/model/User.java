package ru.jevo.chat.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

@Data
@NoArgsConstructor
public class User {

    @Nullable
    private String login;

    @Nullable
    private String password;

    @Nullable
    private String email;

    @Nullable
    private String nick;
}
