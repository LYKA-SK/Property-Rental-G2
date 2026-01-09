// package com.mindvault.Property.services;

// import com.mindvault.Property.entities.RentalPost;
// import com.mindvault.Property.repositories.RentalPostRepository;

// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Service;
// import java.util.List;

// @Service
// @RequiredArgsConstructor
// public class RentalPostService {
//     private final RentalPostRepository repository;

//     public RentalPost create(RentalPost post) { return repository.save(post); }
//     public List<RentalPost> getAll() { return repository.findAll(); }
//     public RentalPost getById(Long id) { return repository.findById(id).orElseThrow(); }

//     public RentalPost update(Long id, RentalPost details) {
//         RentalPost post = getById(id);
//         post.setTitle(details.getTitle());
//         post.setDescription(details.getDescription());
//         post.setAddress(details.getAddress());
//         post.setPrice(details.getPrice());
//         post.setElectricityCost(details.getElectricityCost());
//         post.setWaterCost(details.getWaterCost());
//         return repository.save(post);
//     }

//     public void delete(Long id) { repository.deleteById(id); }
//     public List<RentalPost> search(String keyword) {
//     return repository.findByTitleContainingIgnoreCaseOrAddressContainingIgnoreCase(keyword, keyword);
// }
//     public Object filterByPrice(Double min, Double max) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'filterByPrice'");
//     }

// }
package com.mindvault.Property.services;

import com.mindvault.Property.entities.RentalPost;
import com.mindvault.Property.repositories.RentalPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RentalPostService {

    private final RentalPostRepository repository;

    // Create a new post
    public RentalPost create(RentalPost post) {
        try {
            return repository.save(post);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to create post: " + ex.getRootCause().getMessage());
        }
    }

    // Get all posts
    public List<RentalPost> getAll() {
        return repository.findAll();
    }

    // Get post by ID
    public RentalPost getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("RentalPost with ID " + id + " not found"));
    }

    // Update post by ID
    public RentalPost update(Long id, RentalPost details) {
        RentalPost post = getById(id); // Will throw 404 if not found

        post.setTitle(details.getTitle());
        post.setDescription(details.getDescription());
        post.setAddress(details.getAddress());
        post.setPrice(details.getPrice());
        post.setElectricityCost(details.getElectricityCost());
        post.setWaterCost(details.getWaterCost());

        try {
            return repository.save(post);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to update post: " + ex.getRootCause().getMessage());
        }
    }

    // Delete post by ID
    public void delete(Long id) {
        RentalPost post = getById(id); // Will throw 404 if not found
        repository.delete(post);
    }

    // Search posts by title or address
    public List<RentalPost> search(String keyword) {
        return repository.findByTitleContainingIgnoreCaseOrAddressContainingIgnoreCase(keyword, keyword);
    }

    // Filter posts by price range
    public List<RentalPost> filterByPrice(Double min, Double max) {
        if (min > max) {
            throw new IllegalArgumentException("Min price cannot be greater than max price");
        }
        return repository.findByPriceBetween(min, max);
    }
}
