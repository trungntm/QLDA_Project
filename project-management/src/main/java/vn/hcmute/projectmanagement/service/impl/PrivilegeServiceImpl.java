package vn.hcmute.projectmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmute.projectmanagement.entity.Privilege;
import vn.hcmute.projectmanagement.repository.PrivilegeRepository;
import vn.hcmute.projectmanagement.service.PrivilegeService;


@Service
public class PrivilegeServiceImpl implements PrivilegeService {
    @Autowired
    private PrivilegeRepository privilegeRepository;


    @Override
    public Privilege findByName(String name) {
        return privilegeRepository.findByName(name).get();
    }
}
