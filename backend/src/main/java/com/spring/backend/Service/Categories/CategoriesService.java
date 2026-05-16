package com.spring.backend.Service.Categories;

import com.spring.backend.Entity.CategoriesEntity;
import com.spring.backend.Interface.CategoriesInterface;
import com.spring.backend.Lists.AddCategories;
import com.spring.backend.Modal.CategoriesModal;
import com.spring.backend.Modal.DropCategoriesModal;
import com.spring.backend.Repo.CategoriesRepo;
import com.spring.backend.Service.JwtValue.JwtTokenValue;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesService implements CategoriesInterface {

    @Autowired private JwtTokenValue jwtTokenValue;
    @Autowired private CategoriesRepo categoriesRepo;

    @Override
    public List<AddCategories> addProduct(CategoriesModal categoriesModal, HttpServletRequest request) {
        String email = jwtTokenValue.extractEmail(request);
        CategoriesEntity categoriesEntity;
        AddCategories categories = new AddCategories(0L, categoriesModal.getName(), categoriesModal.getImg(), categoriesModal.getBackground(), categoriesModal.getColor());
        if (email.equals(categoriesModal.getEmail())){
            List<CategoriesEntity> list = categoriesRepo.findByEmail(email);
            if (list.isEmpty()){
                list = null;
                categories.setId(1L);
                List<AddCategories> lis = new ArrayList<>();
                lis.add(categories);
                categories = null;
                categoriesEntity = new CategoriesEntity(null, email,lis);
                categoriesRepo.save(categoriesEntity);
                return lis;
            }else{
                categoriesEntity = list.get(0);
                list = null;
                if (categoriesEntity.getCategories() != null){
                    Long count = (long) (categoriesEntity.getCategories().size() + 1);
                    categories.setId(count);
                    categoriesEntity.getCategories().add(categories);
                    categories = null;
                    categoriesRepo.save(categoriesEntity);
                    return categoriesEntity.getCategories();
                }
            }
        }
        return null;
    }

    @Override
    public List<AddCategories> dropProduct(DropCategoriesModal dropCategoriesModal, HttpServletRequest request) {
        String email = jwtTokenValue.extractEmail(request);
        CategoriesEntity categoriesEntity;
        if (email.equals(dropCategoriesModal.getEmail())) {
            List<CategoriesEntity> list = categoriesRepo.findByEmail(email);
            if (list.isEmpty()) {
                list = null;
                return new ArrayList<>();
            } else {
                categoriesEntity = list.get(0);
                list = null;
                categoriesEntity.getCategories().removeIf((c) -> c.getId().equals(dropCategoriesModal.getId()));
                categoriesRepo.save(categoriesEntity);
                return categoriesEntity.getCategories();
            }
        }
        return null;
    }

    @Override
    public List<AddCategories> listProducts(HttpServletRequest request) {
        String email = jwtTokenValue.extractEmail(request);
        if (email != null){
            List<CategoriesEntity> list = categoriesRepo.findByEmail(email);
            if (list.isEmpty()){
                return new ArrayList<>();
            }else {
                return list.get(0).getCategories();
            }
        }
        return null;
    }
}
