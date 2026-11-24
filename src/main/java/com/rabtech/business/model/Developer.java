package com.rabtech.business.model;

public class Developer extends Employee {

    private final String programmingLanguage;

    public Developer(int id, String name, double salary, String programmingLanguage) {
        super(id, name, salary);

        if (programmingLanguage == null || programmingLanguage.isBlank()) {
            throw new IllegalArgumentException("Invalid programming language");
        }

        this.programmingLanguage = programmingLanguage;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    @Override
    public String getRole() {
        return "Developer";
    }
}
