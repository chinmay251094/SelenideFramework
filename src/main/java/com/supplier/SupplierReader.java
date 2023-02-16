package com.supplier;

import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.data.XlsxReader;
import one.util.streamex.StreamEx;

import java.lang.reflect.Method;

import static io.github.sskorol.data.TestDataReader.use;

public class SupplierReader {
    @DataSupplier
    public static StreamEx<TestDataSupplier> getDataFromExcel(Method method) {
        return use(XlsxReader.class)
                .withTarget(TestDataSupplier.class)
                .withSource("TestingFile.xlsx")
                .read()
                .filter(testDataSupplier -> testDataSupplier.isExecute() && testDataSupplier.getTestcase().equalsIgnoreCase(method.getName()));
    }

    @DataSupplier
    public static StreamEx<TestCountSupplier> getCountFromExcel(Method method) {
        return use(XlsxReader.class)
                .withTarget(TestCountSupplier.class)
                .withSource("TestingFile.xlsx")
                .read()
                .filter(testDataSupplier -> testDataSupplier.isExecute() && testDataSupplier.getTestCase().equalsIgnoreCase(method.getName()));
    }
}
