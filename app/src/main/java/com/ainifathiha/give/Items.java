package com.ainifathiha.give;

public class Items {

    public String name;
    public String description;
    public String category;
    public String url;

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getCategory() { return  category; }
    public String getUrl() { return url; }


    public Items(String name, String description, String category, String url) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.url = url;
    }

    public Items() {}

}
