// package com.mindvault.Property.repositories;

// import com.mindvault.Property.entities.Review;
// import com.mindvault.Property.entities.RentalPost;
// import com.mindvault.Property.entities.User;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.util.List;
// import java.util.Optional;

// @Repository
// public interface ReviewRepository extends JpaRepository<Review, Long> {

//     boolean existsByUserAndRentalPost(User user, RentalPost rentalPost);

//     List<Review> findByRentalPost(RentalPost rentalPost);

//     Optional<Review> findByUserAndRentalPost(User user, RentalPost rentalPost);
// }
package com.mindvault.Property.repositories;

import com.mindvault.Property.entities.Review;
import com.mindvault.Property.entities.RentalPost;
import com.mindvault.Property.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByRentalPost(RentalPost rentalPost);

    Optional<Review> findByUserAndRentalPost(User user, RentalPost rentalPost);

    long countByRentalPost(RentalPost rentalPost);

    Double findAverageRatingByRentalPost(RentalPost rentalPost);
}
