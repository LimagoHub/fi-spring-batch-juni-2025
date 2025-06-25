package de.fi.tag1_02simplespringboot.math;

public class CalculatorImpl implements Calculator {

    @Override
    public double add(double a, double b) {
       return a+b;
    }

    @Override
    public double sub(double a, double b, double c) {
        return add(a, -b);
    }
}
