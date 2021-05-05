package com.justterror.auto_kit.user.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @Column(name="id")
    private long id;

    @Column(name="phone")
    private String phone;

    @Column(name="username")
    @NotNull
    private String username;

    @Column(name="role")
    @NotNull
    private String role;

    @Column(name="access_token")
    @NotNull
    private String accessToken;

    public User() {}

    public User(long id, String phone, String username, String role, String accessToken) {
        setId(id);
        setPhone(phone);
        setUsername(username);
        setRole(role);
        setAccessToken(accessToken);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (!username.equals(user.username)) return false;
        if (!role.equals(user.role)) return false;
        return accessToken.equals(user.accessToken);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + username.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + accessToken.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }
}
