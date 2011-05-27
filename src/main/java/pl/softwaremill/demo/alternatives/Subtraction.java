package pl.softwaremill.demo.alternatives;

import javax.enterprise.inject.Alternative;

/**
 * @author Adam Warski (adam at warski dot org)
 */
@Alternative
public class Subtraction implements Operation {
    @Override
    public int compute(int a, int b) {
        return a-b;
    }
}
