package com.spring.backend.Service.income;

import com.spring.backend.Entity.CategoriesEntity;
import com.spring.backend.Entity.IncomeEntity;
import com.spring.backend.Exception.UserException;
import com.spring.backend.Interface.IncomeInterface;
import com.spring.backend.Lists.AddCategories;
import com.spring.backend.Lists.ShowCategories;
import com.spring.backend.Modal.DropIncomeModal;
import com.spring.backend.Modal.IncomeModal;
import com.spring.backend.Repo.CategoriesRepo;
import com.spring.backend.Repo.TransactionRepo;
import com.spring.backend.Service.JwtValue.JwtTokenValue;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncomeService implements IncomeInterface {

    @Autowired private TransactionRepo transactionRepo;
    @Autowired private JwtTokenValue jwtTokenValue;
    @Autowired private CategoriesRepo categoriesRepo;

    @Override
    public List<IncomeEntity> addIncome(IncomeModal incomeModal, HttpServletRequest request) {
        String email = jwtTokenValue.extractEmail(request);

        if (email == null) {
            throw new UserException("Unauthorized user!");
        }

        List<CategoriesEntity> entity = categoriesRepo.findByEmail(email);

        if (entity.isEmpty()) {
            throw new UserException("No categories found!");
        }

        AddCategories category = entity.get(0)
                .getCategories()
                .stream()
                .filter(val -> val.getC_name().equals(incomeModal.getCategories()))
                .findAny()
                .orElseThrow(() -> new UserException("Invalid category selected!"));

        ShowCategories show = new ShowCategories(
                category.getC_name(),
                category.getImg(),
                category.getBackground(),
                category.getColor()
        );

        String notes = incomeModal.getNotes() != null ? incomeModal.getNotes() : "";

        IncomeEntity income = new IncomeEntity(
                null,
                email,
                true,
                incomeModal.getAmount(),
                show,   // ✅ make sure entity expects this type
                incomeModal.getDate(),
                notes
        );

        transactionRepo.save(income);

        return transactionRepo.findIncome(email);
    }

    @Override
    public List<IncomeEntity> dropIncome(DropIncomeModal dropIncomeModal, HttpServletRequest request) {
        String email = jwtTokenValue.extractEmail(request);
        if (email.equals(dropIncomeModal.getEmail())){
            int index = transactionRepo.deleteIncomeByEmailAndId(email, dropIncomeModal.getId());
            if (index > 0){
                return transactionRepo.findIncome(email);
            }else {
                throw new UserException("deletion failed !");
            }
        }
        return null;
    }

    @Override
    public List<IncomeEntity> listItems(HttpServletRequest request) {
        String email = jwtTokenValue.extractEmail(request);
        if (email != null){
            return transactionRepo.findIncome(email);
        }
        return new ArrayList<>();
    }
}
