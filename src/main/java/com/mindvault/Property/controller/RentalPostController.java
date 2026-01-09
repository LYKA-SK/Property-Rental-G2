// package com.mindvault.Property.controller;

// import com.mindvault.Property.entities.RentalPost;
// import com.mindvault.Property.services.RentalPostService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/posts")
// @RequiredArgsConstructor
// public class RentalPostController {
//     private final RentalPostService service;

//     @PostMapping("/create") // Protected by Token
//     public ResponseEntity<RentalPost> create(@RequestBody RentalPost post) {
//         return ResponseEntity.ok(service.create(post));
//     }

//     @GetMapping("/all") // Publicly visible
//     public ResponseEntity<List<RentalPost>> getAll() {
//         return ResponseEntity.ok(service.getAll());
//     }

//     @PutMapping("/update/{id}")
//     public ResponseEntity<RentalPost> update(@PathVariable Long id, @RequestBody RentalPost post) {
//         return ResponseEntity.ok(service.update(id, post));
//     }

//     @DeleteMapping("/delete/{id}")
//     public ResponseEntity<String> delete(@PathVariable Long id) {
//         service.delete(id);
//         return ResponseEntity.ok("Deleted Successfully");
//     }

//     @GetMapping("/search")
//     public ResponseEntity<List<RentalPost>> search(@RequestParam("q") String keyword) {
//         return ResponseEntity.ok(service.search(keyword));
//     }

//     @GetMapping("/filter")
//     public ResponseEntity<Object> filterByPrice(
//             @RequestParam Double min,
//             @RequestParam Double max) {
//         return ResponseEntity.ok(service.filterByPrice(min, max));
//     }
// }
package com.mindvault.Property.controller;

import com.mindvault.Property.entities.RentalPost;
import com.mindvault.Property.services.RentalPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class RentalPostController {

    private final RentalPostService service;

    // Create a post
    @PostMapping("/create")
    public ResponseEntity<RentalPost> create(@RequestBody RentalPost post) {
        return ResponseEntity.ok(service.create(post));
    }

    // Get all posts
    @GetMapping("/all")
    public ResponseEntity<List<RentalPost>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // Get post by ID
    @GetMapping("/{id}")
    public ResponseEntity<RentalPost> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // Update post
    @PutMapping("/update/{id}")
    public ResponseEntity<RentalPost> update(@PathVariable Long id, @RequestBody RentalPost post) {
        return ResponseEntity.ok(service.update(id, post));
    }

    // Delete post
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted Successfully");
    }

    // Search posts
    @GetMapping("/search")
    public ResponseEntity<List<RentalPost>> search(@RequestParam("q") String keyword) {
        return ResponseEntity.ok(service.search(keyword));
    }

    // Filter posts by price range
    @GetMapping("/filter")
    public ResponseEntity<List<RentalPost>> filterByPrice(
            @RequestParam Double min,
            @RequestParam Double max) {
        return ResponseEntity.ok(service.filterByPrice(min, max));
    }
}
