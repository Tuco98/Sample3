package org.example.practice.part1;

public class FieldEntry {
    private String value;
    private int expTime;

    public FieldEntry(String value, int expTime) {
        this.value = value;
        this.expTime = expTime;
    }

    public FieldEntry(String value) {
        this.value = value;
        this.expTime = -1;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getExpTime() {
        return expTime;
    }

    public void setExpTime(int expTime) {
        this.expTime = expTime;
    }
}
