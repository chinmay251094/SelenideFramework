package com.supplier;

import com.creditdatamw.zerocell.annotation.Column;
import com.creditdatamw.zerocell.converter.BooleanConverter;
import com.creditdatamw.zerocell.converter.IntegerConverter;
import io.github.sskorol.data.Sheet;

@Sheet(name = "Count")
public class TestCountSupplier {
    @Column(name = "TestCase", index = 0)
    private String testCase;
    @Column(name = "Execute", index = 1, converterClass = BooleanConverter.class)
    private boolean execute;
    @Column(name = "TestDescription", index = 2)
    private String testDescription;
    @Column(name = "Count", index = 3, converterClass = IntegerConverter.class)
    private int testCount;

    public boolean isExecute() {
        return execute;
    }

    public String getTestCase() {
        return testCase;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public int getTestCount() {
        return testCount;
    }
}
