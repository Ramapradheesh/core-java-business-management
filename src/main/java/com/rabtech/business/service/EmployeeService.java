package com.rabtech.business.service;

import com.rabtech.business.model.Employee;
import com.rabtech.business.exception.InvalidBusinessRuleException;
import com.rabtech.business.exception.DuplicateEmployeeException;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();
    private final Set<Integer> employeeIds = new HashSet<>();
    private final Map<Integer, Employee> employeeMap = new HashMap<>();

    public void addEmployee(Employee employee)
            throws InvalidBusinessRuleException {

        if (employee == null) {
            throw new InvalidBusinessRuleException(
                    "Employee cannot be null");
        }

        if (employee.getSalary() <= 0) {
            throw new InvalidBusinessRuleException(
                    "Employee salary must be greater than zero");
        }

        if (employeeIds.contains(employee.getId())) {
            throw new DuplicateEmployeeException(
                    "Duplicate employee ID: " + employee.getId());
        }

        employeeIds.add(employee.getId());
        employees.add(employee);
        employeeMap.put(employee.getId(), employee);
    }

    public Employee findById(int id) {
        Employee employee = employeeMap.get(id);

        if (employee == null) {
            throw new NoSuchElementException(
                    "Employee not found: " + id);
        }

        return employee;
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public List<Employee> getEmployeesSortedBySalary() {
        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .collect(Collectors.toList());
    }

    public List<Employee> filterByMinimumSalary(double minimumSalary) {
        return employees.stream()
                .filter(employee -> employee.getSalary() >= minimumSalary)
                .collect(Collectors.toList());
    }

    public boolean removeEmployee(int id) {
        Employee employee = employeeMap.remove(id);

        if (employee == null) {
            return false;
        }

        employeeIds.remove(id);
        employees.remove(employee);
        return true;
    }

    public int getEmployeeCount() {
        return employees.size();
    }
}
