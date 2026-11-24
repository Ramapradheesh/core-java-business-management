package com.rabtech.business.model;

public abstract class Employee {

    private final int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        if (id <= 0 || salary < 0 || name == null || name.isBlank()) {
            throw new IllegalArgumentException("Invalid employee details");
        }

        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public abstract String getRole();

    public void displayDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
        System.out.println("Role: " + getRole());
    }
}
