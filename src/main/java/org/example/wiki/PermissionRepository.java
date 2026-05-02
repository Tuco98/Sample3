package org.example.wiki;

public interface PermissionRepository {
    void save(PagePermission permission);
    PagePermission getPermission(String userId, String pageId);
}
