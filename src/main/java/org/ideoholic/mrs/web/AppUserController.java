package org.ideoholic.mrs.web;

import org.ideoholic.mrs.model.AppUser;
import org.ideoholic.mrs.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/app-users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    // Endpoint: GET /api/app-users
    @GetMapping(produces = "application/json")
    public ResponseEntity<Map<String, List<AppUser>>> getAppUsers() {
        return ResponseEntity.ok(appUserService.getAppUsers());
    }
}
