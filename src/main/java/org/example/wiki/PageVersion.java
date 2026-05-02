package org.example.wiki;

public class PageVersion {
    String versionId;
    String pageId;
    int versionNumber;
//    String content;
    String snapshot; //materialized content
    long timestamp;
    String updatedBy;
}
