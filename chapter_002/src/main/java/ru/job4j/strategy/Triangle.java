package ru.job4j.strategy;

public class Triangle implements Shape {
    public static String newline = System.getProperty("line.separator");

    public String draw() {
        StringBuilder triangle = new StringBuilder();
        triangle.append("   +   " + newline);
        triangle.append("  + +  " + newline);
        triangle.append(" + + + " + newline);
        triangle.append("+ + + +" + newline);
        return triangle.toString();
    }

    public static void main(String[] args) {
        Triangle a = new Triangle();
        System.out.println(a.draw());
    }
}
