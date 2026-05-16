package com.spring.backend.Controller.PrivateController.Categories;

import com.spring.backend.Entity.CategoriesEntity;
import com.spring.backend.Lists.AddCategories;
import com.spring.backend.Modal.CategoriesModal;
import com.spring.backend.Modal.DropCategoriesModal;
import com.spring.backend.Repo.CategoriesRepo;
import com.spring.backend.Service.Categories.CategoriesService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/private/api/v1")
public class CategoriesControl {

    @Autowired private CategoriesService categoriesService;

    @PostMapping("/addCategories")
    public ResponseEntity<?> addCategories(@RequestBody CategoriesModal categoriesModal, HttpServletRequest request){
        try {
            List<AddCategories> res = categoriesService.addProduct(categoriesModal, request);
            if (res != null){
                return ResponseEntity.ok().body(Map.of("msg", res));
            }
            return ResponseEntity.badRequest().body(Map.of("msg", "failed !"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }

    @DeleteMapping("/dropCategories")
    public ResponseEntity<?> dropCategories(@RequestBody DropCategoriesModal categoriesModal, HttpServletRequest request){
        try {
            List<AddCategories> res = categoriesService.dropProduct(categoriesModal, request);
            if (res != null){
                return ResponseEntity.ok().body(Map.of("msg", res));
            }
            return ResponseEntity.badRequest().body(Map.of("msg", "failed !"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }

    @GetMapping("/listProducts")
    public ResponseEntity<?> listProducts(HttpServletRequest request){
        try {
            List<AddCategories> res = categoriesService.listProducts(request);
            if (res != null){
                return ResponseEntity.ok().body(Map.of("msg", res));
            }
            return ResponseEntity.badRequest().body(Map.of("msg", "failed !"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }
}
