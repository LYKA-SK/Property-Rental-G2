package com.mindvault.Property.controller;

import com.mindvault.Property.entities.RentalPost;
import com.mindvault.Property.entities.User;
import com.mindvault.Property.services.RentalPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts") // This is the base path
@RequiredArgsConstructor
public class RentalPostController {

    private final RentalPostService service;

    @GetMapping("/all")
    public ResponseEntity<List<RentalPost>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<RentalPost> create(@RequestBody RentalPost post, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(service.create(post, user));
    }

    @PutMapping("/update/{id}") // This will result in /api/posts/update/{id}
    public ResponseEntity<RentalPost> update(@PathVariable Long id, @RequestBody RentalPost post, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(service.update(id, post, user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @AuthenticationPrincipal User user) {
        service.delete(id, user);
        return ResponseEntity.ok("Post deleted successfully");
    }
}
