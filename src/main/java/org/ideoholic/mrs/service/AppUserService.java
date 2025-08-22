package org.ideoholic.mrs.service;

import org.ideoholic.mrs.model.AppUser;
import org.ideoholic.mrs.dao.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public Map<String, List<AppUser>> getAppUsers() {
        // Call repository method and store result in a variable
        List<AppUser> appUsers = appUserRepository.findNonSelfServiceUsers();

        Map<String, List<AppUser>> response = new HashMap<>();
        response.put("appUsers", appUsers);
        return response;
    }
}
