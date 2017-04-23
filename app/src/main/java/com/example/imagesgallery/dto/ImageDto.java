package com.example.imagesgallery.dto;

/**
 * Created by hyemi on 2017. 4. 23..
 */

public class ImageDto {
    private String host;
    private String link;
    private String src;

    public ImageDto(String host, String link, String src) {
        this.host = host;
        this.link = link;
        this.src = src;
    }

    public String getHost() {
        return  host;
    }

    public String getLink() {
        return link;
    }

    public String getSrc() {
        return src;
    }
}
