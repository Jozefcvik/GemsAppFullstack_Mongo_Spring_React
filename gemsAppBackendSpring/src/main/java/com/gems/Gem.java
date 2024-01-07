package com.gems;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gems")
public class Gem {
    @Id
    private String id;
    private String title;
    private String origin;
    private boolean reserved;

    public Gem () {};

    public Gem(String title, String origin, boolean reserved) {
        this.title = title;
        this.origin = origin;
        this.reserved = reserved;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean getReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return "Gem [id=" + id + ", title=" + title + ", origin=" + origin + ", reserved=" + reserved + "]";
    }
}
