package org.example.wiki;

public interface DocumentRepository {
    void save(CRDTDocument doc);
    CRDTDocument get(String pageId);
}
