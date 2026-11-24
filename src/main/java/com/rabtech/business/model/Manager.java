package com.rabtech.business.model;

public class Manager extends Employee {

    private final int teamSize;

    public Manager(int id, String name, double salary, int teamSize) {
        super(id, name, salary);

        if (teamSize < 0) {
            throw new IllegalArgumentException("Invalid team size");
        }

        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public String getRole() {
        return "Manager";
    }
}
