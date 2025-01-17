package vn.edu.iuh.fit.laptopshop.service;

import java.util.List;
import java.util.Optional;

public interface IService<T, p> {
    T save(T t);
    T findById(p p);
    List<T> findAll();
    void deleteById(p p);
}
