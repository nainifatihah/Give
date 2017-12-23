package com.ainifathiha.give;

public class Users {
    String username;
    String location;
    String url;

    public Users(String username, String location, String url) {
        this.username = username;
        this.location = location;
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public String getLocation() {
        return location;
    }

    public String getUrl() { return url; }

    public Users() {}

}
