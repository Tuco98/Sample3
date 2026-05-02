package org.example.wiki;

import java.util.List;

public interface PageRepository {
    void save(Page page);
    Page findById(String pageId);
    List<Page> findByParent(String parentPageId);

}
