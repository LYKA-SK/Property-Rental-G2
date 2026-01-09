package com.mindvault.Property.repositories;

import com.mindvault.Property.entities.RentalPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RentalPostRepository extends JpaRepository<RentalPost, Long> {
    
    // Search by title containing keyword
    List<RentalPost> findByTitleContainingIgnoreCase(String keyword);

    // Search by address containing keyword
    List<RentalPost> findByAddressContainingIgnoreCase(String keyword);

    // Combined search (title OR address)
    List<RentalPost> findByTitleContainingIgnoreCaseOrAddressContainingIgnoreCase(String title, String address);
    // users to filter by price range
    List<RentalPost> findByPriceBetween(Double minPrice, Double maxPrice);

}
