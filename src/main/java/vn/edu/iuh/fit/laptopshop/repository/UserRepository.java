package vn.edu.iuh.fit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.laptopshop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
