package org.example.wiki;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PageService {

    PageRepository pageRepository;
    VersionService versionService;
    PermissionService permissionService;
    CollaborationService collaborationService;
    DocumentRepository documentRepository;

    String createPage( String title, String content, String parentId, String userId){

        Page page = new Page();
        page.pageId = UUID.randomUUID().toString();
        page.title = title;
        page.parentId = parentId;
        page.createdBy = userId;
        page.createdAt = System.currentTimeMillis();
        page.updatedAt = System.currentTimeMillis();
        page.isDeleted = false;
        page.currentVersion = 1;

        pageRepository.save(page);

//        versionService.createVersion(page.pageId,1,content,userId);
        CRDTDocument doc = new CRDTDocument();
        doc.pageId = page.pageId;
        doc.operations = new ArrayList<>();
        documentRepository.save(doc);

        int pos = 0;
        for(char c:content.toCharArray()){
            CRDTOperation op = new CRDTOperation();
            op.pageId = page.pageId;
            op.positionId = String.valueOf(pos);
            op.operationType = OperationType.INSERT;
            op.userId = userId;
            op.timestamp = System.currentTimeMillis();
            op.value = String.valueOf(c);
            collaborationService.applyOperation(op);
            pos++;
        }

        createSnapshot(page.pageId);


        return page.pageId;

    }
    //old

//    void updatePage(String pageId, String newContent, String userId){
//
//        if(!permissionService.hasAccess(userId,pageId,Permission.WRITE)){
//            throw new RuntimeException("User doesn't have access");
//        }
//
//        Page page = pageRepository.findById(pageId);
//        int version = page.currentVersion;
//        versionService.createVersion(pageId,version+1,newContent,userId);
//        page.currentVersion = version+1;
//        page.updatedAt = System.currentTimeMillis();
//
//        pageRepository.save(page);
//
//    }

    void applyEdits(String pageId, String userId, List<CRDTOperation> ops){
        if(!permissionService.hasAccess(userId,pageId,Permission.WRITE)){
            throw new RuntimeException("User doesn't have access");
        }

        for(CRDTOperation op: ops){
            collaborationService.applyOperation(op);
        }

    }

    String getPage(String pageId, String userId){
        if(!permissionService.hasAccess(userId,pageId,Permission.READ)){
            throw new RuntimeException("User doesn't have access");
        }

        Page page = pageRepository.findById(pageId);

//        return versionService.getVersion(pageId,page.currentVersion);
        return collaborationService.getContent(pageId);
    }

    void restoreVersion(String pageId, int versionNumber, String userId){

        if(!permissionService.hasAccess(userId,pageId,Permission.WRITE)){
            throw new RuntimeException("User doesn't have access");
        }

        PageVersion oldV = versionService.getVersion(pageId,versionNumber);

//        updatePage(pageId, oldV.content, userId);

        String snapshotContent = oldV.snapshot;
        CRDTDocument doc = new CRDTDocument();
        doc.pageId = pageId;
        doc.operations = new ArrayList<>();
        documentRepository.save(doc);

        int pos = 0;
        for(char c:snapshotContent.toCharArray()){
            CRDTOperation op = new CRDTOperation();
            op.pageId = pageId;
            op.positionId = String.valueOf(pos);
            op.operationType = OperationType.INSERT;
            op.userId = userId;
            op.timestamp = System.currentTimeMillis();
            op.value = String.valueOf(c);
            collaborationService.applyOperation(op);
            pos++;
        }

        createSnapshot(pageId);




    }

    void deletePage(String pageId, String userId){
        if(!permissionService.hasAccess(userId,pageId,Permission.WRITE)){
            throw new RuntimeException("User doesn't have access");
        }
        Page page = pageRepository.findById(pageId);
        page.isDeleted = true;

        pageRepository.save(page);
    }

    void createSnapshot(String pageId){
        String content = collaborationService.getContent(pageId);
        int version = getNextVersion(pageId);
        PageVersion pageVersion = new PageVersion();
        pageVersion.pageId = pageId;
        pageVersion.versionNumber = version;
        pageVersion.snapshot = content;
        pageVersion.timestamp = System.currentTimeMillis();
        versionService.createVersion(pageId,version,content,"");

    }

    private int getNextVersion(String pageId) {
        Page byId = pageRepository.findById(pageId);
        return byId.currentVersion+1;
    }

}
