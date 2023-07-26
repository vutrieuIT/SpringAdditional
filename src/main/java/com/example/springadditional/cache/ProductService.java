package com.example.springadditional.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository repository;

    @Cacheable("productCache")
    public List<Product> getAllProduct(){
        return repository.findAll();
    }
}
