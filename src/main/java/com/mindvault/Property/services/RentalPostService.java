package com.mindvault.Property.services;

import com.mindvault.Property.entities.RentalPost;
import com.mindvault.Property.entities.User;
import com.mindvault.Property.repositories.RentalPostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalPostService {
    private final RentalPostRepository repository;

    // --- FIX: Method getAll() ---
    public List<RentalPost> getAll() { 
        return repository.findAll(); 
    }

    public RentalPost getById(Long id) { 
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Post not found")); 
    }

    public RentalPost create(RentalPost post, User currentUser) { 
        post.setUser(currentUser); 
        return repository.save(post); 
    }

    public RentalPost update(Long id, RentalPost details, User currentUser) {
        RentalPost post = getById(id);
        
        // Safety check: Prevent NullPointerException if post has no owner
        if (post.getUser() == null) {
            throw new RuntimeException("This post has no owner and cannot be updated.");
        }
        
        // Check ownership before updating
        if (!post.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Unauthorized: This is not your post.");
        }

        // Update fields
        post.setTitle(details.getTitle());
        post.setDescription(details.getDescription());
        post.setAddress(details.getAddress());
        post.setPrice(details.getPrice());
        post.setElectricityCost(details.getElectricityCost());
        post.setWaterCost(details.getWaterCost());
        
        return repository.save(post);
    }

    public void delete(Long id, User currentUser) {
        RentalPost post = getById(id);
        if (!post.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Unauthorized: You cannot delete this post.");
        }
        repository.delete(post); 
    }
}