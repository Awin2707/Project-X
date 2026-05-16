package com.spring.backend.Controller.PrivateController.Income;

import com.spring.backend.Entity.IncomeEntity;
import com.spring.backend.Lists.AddCategories;
import com.spring.backend.Modal.DropIncomeModal;
import com.spring.backend.Modal.IncomeModal;
import com.spring.backend.Service.income.IncomeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/private/api/v1")
public class IncomeController {

    @Autowired private IncomeService incomeService;

    @PostMapping("/addIncome")
    public ResponseEntity<?> addIncome(@RequestBody IncomeModal incomeModal, HttpServletRequest request){
        try {
            List<IncomeEntity> res = incomeService.addIncome(incomeModal, request);
            if (res != null){
                return ResponseEntity.ok().body(Map.of("msg", res));
            }
            return ResponseEntity.badRequest().body(Map.of("msg", "failed !"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }

    @DeleteMapping("/deleteIncome")
    public ResponseEntity<?> deleteIncome(@RequestBody DropIncomeModal incomeModal, HttpServletRequest request){
        try {
            List<IncomeEntity> res = incomeService.dropIncome(incomeModal, request);
            if (res != null){
                return ResponseEntity.ok().body(Map.of("msg", res));
            }
            return ResponseEntity.badRequest().body(Map.of("msg", "failed !"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }

    @GetMapping("/listIncome")
    public ResponseEntity<?> listIncome(HttpServletRequest request){
        try {
            List<IncomeEntity> res = incomeService.listItems(request);
            if (res != null){
                return ResponseEntity.ok().body(Map.of("msg", res));
            }
            return ResponseEntity.badRequest().body(Map.of("msg", "failed !"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }

}
