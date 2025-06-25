package main;

import client.CalcClient;
import math.Calculator;
import math.CalculatorImpl;
import math.CalculatorLogger;
import math.CalculatorSecure;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpl();
        calculator = new CalculatorLogger(calculator);
        calculator = new CalculatorSecure(calculator);
        CalcClient calcClient = new CalcClient(calculator);
        calcClient.go();
    }
}