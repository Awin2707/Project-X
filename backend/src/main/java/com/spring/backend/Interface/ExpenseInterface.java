package com.spring.backend.Interface;

import com.spring.backend.Entity.IncomeEntity;
import com.spring.backend.Modal.DropIncomeModal;
import com.spring.backend.Modal.IncomeModal;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ExpenseInterface {

    List<IncomeEntity> addExpenses(IncomeModal incomeModal, HttpServletRequest request);

    List<IncomeEntity> dropExpenses(DropIncomeModal dropIncomeModal, HttpServletRequest request);

    List<IncomeEntity> listItems(HttpServletRequest request);
}
