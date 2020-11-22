package com.stuart.hello_rest_db.controllers;

import com.stuart.hello_rest_db.error.BookNotFoundException;
import com.stuart.hello_rest_db.error.BookUnSupportedFieldPatchException;
import com.stuart.hello_rest_db.modul.Entities.Product;
import com.stuart.hello_rest_db.modul.ProductRepository;
import com.stuart.hello_rest_db.modul.Entities.SuccessEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/api")
public class RestController {
    @Autowired
    private ProductRepository repository;

    // Tìm tất cả sách trong repository
    @GetMapping("/products")
    @ApiOperation(value = "Get All Product", produces = "application/json")
    List<Product> findAll(){
        return repository.findAll();
    }

    // Thêm sách vào repository
    // return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/products")
    @ApiOperation(value = "Save new Product", produces = "application/json")
    Product newBook(@RequestBody Product newBook){
        return repository.save(newBook);
    }

    // Tìm product theo id
    @GetMapping("/products/{id}")
    @ApiOperation(value = "Search Product by Id", produces = "application/json")
    Product findOne(
            @ApiParam(name = "Id",
                    value = "The Id of the Product to be viewed",
                    required = true)
            @PathVariable Long id){
        Product product = repository.findOne(id);
        if (null==product){
            throw new BookNotFoundException(id);
        }

        return product;
    }

    // sửa thông tin sách theo id
    @PutMapping("/products/{id}")
    @ApiOperation(value = "Modify Product by Id", produces = "application/json")
    Product saveOrUpdate(
            @ApiParam(name = "Request Message",
                    value = "The information of the Product to be modified",
                    required = true)
            @RequestBody Product newProduct,

            @ApiParam(name = "Id",
                    value = "The Id of the Product to be modified",
                    required = true)
            @PathVariable Long id){
        Product product = repository.findOne(id);
        if (null==product){
            throw new BookNotFoundException(id);
        }else{
            product.setName(newProduct.getName());
            product.setAuthor(newProduct.getAuthor());
            product.setPrice(newProduct.getPrice());

            return product;
        }
    }

    //sửa thông tin author của sách theo id
    @PatchMapping("/products/{id}")
    @ApiOperation(value = "Modify Author Product by Id", produces = "application/json")
    Product patch(@RequestBody Map<String, String> update,
                  @ApiParam(name = "Id",
                          value = "The Id of the Product to modify author",
                          required = true)
                  @PathVariable Long id){
            Product product = repository.findOne(id);
            if (null==product){
                throw new BookNotFoundException(id);
            }

            String author = update.get("author");
            if (StringUtils.isEmpty(author)){
                throw new BookUnSupportedFieldPatchException(update.keySet());
            }

            product.setAuthor(author);
            return repository.save(product);
    }

    @DeleteMapping("/products/{id}")
    @ApiOperation(value = "Delete Product by Id", produces = "application/json")
    SuccessEntity deleteBook(
            @ApiParam(name = "Id",
                    value = "The Id of the Product to be deleted",
                    required = true)
            @PathVariable Long id){
        Product product = repository.findOne(id);
        if (null==product){
            throw new BookNotFoundException(id);
        }
        
        repository.delete(id);
        return new SuccessEntity(DateTime.now().toString(),"Deleted Product.");
    }

    @DeleteMapping("/products")
    @ApiOperation(value = "Delete all Products")
    SuccessEntity deleteAll(){
        repository.deleteAll();
        return new SuccessEntity(DateTime.now().toString(),"Deleted all Products.");
    }
}
