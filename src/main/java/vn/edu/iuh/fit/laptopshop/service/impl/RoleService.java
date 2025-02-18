package vn.edu.iuh.fit.laptopshop.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.laptopshop.domain.Role;
import vn.edu.iuh.fit.laptopshop.repository.RoleRepository;

@Service
public class RoleService {
    private RoleRepository roleRepository;
    public Role findByName(String name){
        return roleRepository.findByName(name);
    }
}
