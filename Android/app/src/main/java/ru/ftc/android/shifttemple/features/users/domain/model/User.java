package ru.ftc.android.shifttemple.features.users.domain.model;

public final class User {
    private String id;
    private String name;
    private String phone;
    private String vk_link;
    private String tg_link;
    private String email;
    private long karma;

    private String login;

    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User(String user_id, String name, String phone) {
        this.id = user_id;
        this.name = name;
        this.phone = phone;

    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getVkLink() {
        return vk_link;
    }

    public String getTgLink() {
        return tg_link;
    }

    public String getEmail() {
        return email;
    }

    public long getKarma() {
        return karma;
    }
}
