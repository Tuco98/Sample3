package org.example.wiki;

import java.util.List;

public class VersionService {

    PageVersionRepository pageVersionRepository;
    void createVersion(String pageId,int versionNumber, String content, String userId){
        PageVersion version = new PageVersion();
        version.pageId = pageId;
        version.versionNumber = versionNumber;
//        version.content = content;
        version.updatedBy = userId;
        version.timestamp  = System.currentTimeMillis();

        pageVersionRepository.save(version);
    }

    PageVersion getVersion(String pageId, int versionNumber){
        return pageVersionRepository.findByPageIdAndVersion(pageId,versionNumber);
    }

    List<PageVersion> getHistory(String pageId){
        return pageVersionRepository.findByPageId(pageId);
    }

}
