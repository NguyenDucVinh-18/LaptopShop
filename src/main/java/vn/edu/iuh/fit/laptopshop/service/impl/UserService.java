package vn.edu.iuh.fit.laptopshop.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.laptopshop.domain.User;
import vn.edu.iuh.fit.laptopshop.repository.UserRepository;
import vn.edu.iuh.fit.laptopshop.service.IService;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IService<User, Long> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return userRepository.findById(aLong);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean remove(Long aLong) {
        return false;
    }
}
