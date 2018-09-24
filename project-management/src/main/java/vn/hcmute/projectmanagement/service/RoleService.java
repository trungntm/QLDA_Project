package vn.hcmute.projectmanagement.service;

import vn.hcmute.projectmanagement.entity.Role;

public interface RoleService {
    Role retrieveRoleByName(String name);
}
