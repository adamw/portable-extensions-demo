package pl.softwaremill.demo.alternatives;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public class Addition implements Operation {
    @Override
    public int compute(int a, int b) {
        return a+b;
    }
}
