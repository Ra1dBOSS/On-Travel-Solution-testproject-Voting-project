package com.solutions.travel.on.voting_project.dto;

public class HrefDTO {

    private String name;
    private String href;

    public HrefDTO() {

    }

    public HrefDTO(String name, String href) {
        this.name = name;
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
