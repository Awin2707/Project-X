package com.spring.backend.Interface;

import com.spring.backend.Entity.CategoriesEntity;
import com.spring.backend.Lists.AddCategories;
import com.spring.backend.Modal.CategoriesModal;
import com.spring.backend.Modal.DropCategoriesModal;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface CategoriesInterface {

    public List<AddCategories> addProduct(CategoriesModal categoriesModal, HttpServletRequest request);

    public List<AddCategories> dropProduct(DropCategoriesModal dropCategoriesModal, HttpServletRequest request);

    public List<AddCategories> listProducts(HttpServletRequest request);
}
