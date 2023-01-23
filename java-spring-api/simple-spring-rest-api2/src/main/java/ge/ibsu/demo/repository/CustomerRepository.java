package ge.ibsu.demo.repository;

import ge.ibsu.demo.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "from Customer where active = :active and "+ "(:fullName is null or concat(firstName, concat(' ',lastName)) like :fullName)")
    Slice<Customer> search(@Param("active")Integer active, @Param("fullName") String fullName, Pageable pageable);

}
