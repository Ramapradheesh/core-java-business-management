package com.rabtech.business.service;

import com.rabtech.business.model.*;
import com.rabtech.business.exception.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    @Test
    void shouldAddEmployee() throws InvalidBusinessRuleException {
        EmployeeService service = new EmployeeService();
        service.addEmployee(new Developer(1, "Arun", 50000, "Java"));

        assertEquals(1, service.getEmployeeCount());
    }

    @Test
    void shouldRejectNullEmployee() {
        EmployeeService service = new EmployeeService();

        assertThrows(InvalidBusinessRuleException.class,
                () -> service.addEmployee(null));
    }

    @Test
    void shouldRejectZeroSalary() {
        EmployeeService service = new EmployeeService();
        Employee employee = new Developer(1, "Arun", 0, "Java");

        assertThrows(InvalidBusinessRuleException.class,
                () -> service.addEmployee(employee));
    }

    @Test
    void shouldRejectDuplicateEmployee() throws InvalidBusinessRuleException {
        EmployeeService service = new EmployeeService();
        service.addEmployee(new Developer(1, "Arun", 50000, "Java"));

        assertThrows(DuplicateEmployeeException.class,
                () -> service.addEmployee(
                        new Manager(1, "Bala", 60000, 5)));
    }

    @Test
    void shouldFindEmployeeById() throws InvalidBusinessRuleException {
        EmployeeService service = new EmployeeService();
        Employee employee = new Developer(1, "Arun", 50000, "Java");

        service.addEmployee(employee);

        assertEquals(employee, service.findById(1));
    }

    @Test
    void shouldThrowWhenEmployeeNotFound() {
        EmployeeService service = new EmployeeService();

        assertThrows(NoSuchElementException.class,
                () -> service.findById(99));
    }

    @Test
    void shouldSortEmployeesBySalary() throws InvalidBusinessRuleException {
        EmployeeService service = new EmployeeService();

        service.addEmployee(new Developer(1, "Arun", 50000, "Java"));
        service.addEmployee(new Manager(2, "Bala", 80000, 5));

        List<Employee> sorted = service.getEmployeesSortedBySalary();

        assertEquals(50000, sorted.get(0).getSalary());
        assertEquals(80000, sorted.get(1).getSalary());
    }

    @Test
    void shouldFilterEmployeesBySalary() throws InvalidBusinessRuleException {
        EmployeeService service = new EmployeeService();

        service.addEmployee(new Developer(1, "Arun", 50000, "Java"));
        service.addEmployee(new Manager(2, "Bala", 80000, 5));

        assertEquals(1, service.filterByMinimumSalary(60000).size());
    }

    @Test
    void shouldRemoveEmployee() throws InvalidBusinessRuleException {
        EmployeeService service = new EmployeeService();

        service.addEmployee(new Developer(1, "Arun", 50000, "Java"));

        assertTrue(service.removeEmployee(1));
        assertEquals(0, service.getEmployeeCount());
    }

    @Test
    void shouldReturnFalseWhenRemovingMissingEmployee() {
        EmployeeService service = new EmployeeService();

        assertFalse(service.removeEmployee(99));
    }
}
