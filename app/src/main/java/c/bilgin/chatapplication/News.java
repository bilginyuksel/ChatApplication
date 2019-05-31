package c.bilgin.chatapplication;


import java.util.UUID;

public class News {
    private String title,link,image,group,description,uuid;
    public News(String group,String title,String link,String image,String description){
        this.group = group; this.title = title; this.link=link;this.image=image; this.description =description;
        this.uuid =UUID.randomUUID().toString();
    }public News(){}

    public String getDescription() {
        return description;
    }

    public String getGroup() {
        return group;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public String getUuid() {
        return uuid;
    }
}
