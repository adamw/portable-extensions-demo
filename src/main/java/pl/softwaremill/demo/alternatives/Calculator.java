package pl.softwaremill.demo.alternatives;

import javax.inject.Inject;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public class Calculator {
    private final Operation operation;

    @Inject
    public Calculator(Operation operation) {
        this.operation = operation;
    }

    public void run() {
        int a = 10;
        int b = 2;
        System.out.println("Result: " + operation.compute(a, b));
    }
}
