package com.example.simpleaffablebean.dao;

import com.example.simpleaffablebean.ds.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Integer> {
}
