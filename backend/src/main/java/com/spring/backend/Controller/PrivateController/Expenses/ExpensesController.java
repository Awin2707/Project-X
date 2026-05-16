package com.spring.backend.Controller.PrivateController.Expenses;

import com.spring.backend.Entity.IncomeEntity;
import com.spring.backend.Modal.DropIncomeModal;
import com.spring.backend.Modal.IncomeModal;
import com.spring.backend.Service.Expenses.ExpensesService;
import com.spring.backend.Service.income.IncomeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/private/api/v1")
public class ExpensesController {
    @Autowired
    private ExpensesService incomeService;

    @PostMapping("/addExpenses")
    public ResponseEntity<?> addIncome(@RequestBody IncomeModal incomeModal, HttpServletRequest request){
        try {
            List<IncomeEntity> res = incomeService.addExpenses(incomeModal, request);
            if (res != null){
                return ResponseEntity.ok().body(Map.of("msg", res));
            }
            return ResponseEntity.badRequest().body(Map.of("msg", "failed !"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }

    @DeleteMapping("/deleteExpenses")
    public ResponseEntity<?> deleteIncome(@RequestBody DropIncomeModal incomeModal, HttpServletRequest request){
        try {
            List<IncomeEntity> res = incomeService.dropExpenses(incomeModal, request);
            if (res != null){
                return ResponseEntity.ok().body(Map.of("msg", res));
            }
            return ResponseEntity.badRequest().body(Map.of("msg", "failed !"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }

    @GetMapping("/listExpenses")
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
