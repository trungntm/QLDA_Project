package vn.hcmute.projectmanagement.service;

import vn.hcmute.projectmanagement.entity.Privilege;


public interface PrivilegeService {
    Privilege findByName(String name);
}
