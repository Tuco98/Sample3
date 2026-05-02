package org.example.practice.part5.model;

public abstract class Request {
    String id;
    RequestType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }
}
