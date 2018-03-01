package ru.job4j.strategy;

public class Triangle implements Shape {
    public String draw() {
        StringBuilder triangle = new StringBuilder();
        triangle.append("   +   \n");
        triangle.append("  + +  \n");
        triangle.append(" + + + \n");
        triangle.append("+ + + +\n");
        return triangle.toString();
    }
}
