package org.example.wiki;

public class PermissionService {
    PermissionRepository permissionRepository;

    public boolean hasAccess(String userId, String pageId, Permission required){
        PagePermission permission = permissionRepository.getPermission(userId, pageId);
        if(permission == null) return false;

        if(permission.permission.equals(Permission.ADMIN)) return true;

        if(required.equals(Permission.READ)) return true;

        return permission.permission.equals(required);
    }
}
