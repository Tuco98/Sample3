package org.example.practice.part2;

public class UrlEntry {
    String url;
    long expTime;

    public UrlEntry(String url, long expTime) {
        this.url = url;
        this.expTime = expTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getExpTime() {
        return expTime;
    }

    public void setExpTime(long expTime) {
        this.expTime = expTime;
    }
}
