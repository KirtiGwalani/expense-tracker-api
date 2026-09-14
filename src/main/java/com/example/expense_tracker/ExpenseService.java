package com.example.expense_tracker;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Expense not found"));
    }

    public Expense updateExpense(Long id, Expense expense) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Expense not found"));

        existingExpense.setTitle(expense.getTitle());
        existingExpense.setCategory(expense.getCategory());
        existingExpense.setDate(expense.getDate());
        existingExpense.setDescription(expense.getDescription());
        existingExpense.setAmount(expense.getAmount());

        return expenseRepository.save(existingExpense);
    }

    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Expense not found");
        }

        expenseRepository.deleteById(id);
    }

    public List<Expense> getByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }

    public Map<String, Object> getSummary() {

        List<Expense> expenses = expenseRepository.findAll();

        double total = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        Map<String, Double> byCategory = expenses.stream()
                .collect(Collectors.groupingBy(
                        Expense::getCategory,
                        Collectors.summingDouble(Expense::getAmount)));

        Map<String, Object> summary = new HashMap<>();
        summary.put("total", total);
        summary.put("byCategory", byCategory);

        return summary;
    }
}