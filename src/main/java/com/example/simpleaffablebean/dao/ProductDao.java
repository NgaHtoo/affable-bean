package com.example.simpleaffablebean.dao;

import com.example.simpleaffablebean.ds.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product,Integer> {

    @Query("""
select p from Product p where p.category.id=?1
""")
    List<Product> findProductByCategoryId(int categoryId);

//    @Query("""
//select p from Product p where p.category.id=?1 and p.id=?2
//""")
    Product findProductById(int id);
}
