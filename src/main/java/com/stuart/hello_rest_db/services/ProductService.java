package com.stuart.hello_rest_db.services;

import com.stuart.hello_rest_db.modul.Product;
import com.stuart.hello_rest_db.modul.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public List<Product> listAll(){
        return repo.findAll();
    }

    public void save(Product product){
        repo.save(product);
    }

    public Product get(long id){
        return repo.findOne(id);
    }

    public void delete(long id){
        repo.delete(id);
    }

}
