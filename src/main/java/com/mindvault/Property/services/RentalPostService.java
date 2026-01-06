package com.mindvault.Property.services;

import com.mindvault.Property.entities.RentalPost;
import com.mindvault.Property.repository.RentalPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalPostService {
    private final RentalPostRepository repository;

    public RentalPost create(RentalPost post) { return repository.save(post); }
    public List<RentalPost> getAll() { return repository.findAll(); }
    public RentalPost getById(Long id) { return repository.findById(id).orElseThrow(); }

    public RentalPost update(Long id, RentalPost details) {
        RentalPost post = getById(id);
        post.setTitle(details.getTitle());
        post.setDescription(details.getDescription());
        post.setAddress(details.getAddress());
        post.setPrice(details.getPrice());
        post.setElectricityCost(details.getElectricityCost());
        post.setWaterCost(details.getWaterCost());
        return repository.save(post);
    }

    public void delete(Long id) { repository.deleteById(id); }
}