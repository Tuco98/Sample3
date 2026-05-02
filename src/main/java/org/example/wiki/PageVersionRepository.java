package org.example.wiki;

import java.util.List;

public interface PageVersionRepository {
    void save(PageVersion pageVersion);
    List<PageVersion> findByPageId(String pageId);
    PageVersion findByPageIdAndVersion(String pageId, int version);
}
