package com.listeners;

import com.annotations.TestDescription;
import one.util.streamex.StreamEx;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import com.supplier.SupplierReader;
import com.supplier.TestCountSupplier;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestInterceptor implements IMethodInterceptor {
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        List<IMethodInstance> filteredMethods = new ArrayList<>();
        for (IMethodInstance methodInstance : methods) {
            Method method = methodInstance.getMethod().getConstructorOrMethod().getMethod();
            StreamEx<TestCountSupplier> supplierStream = SupplierReader.getCountFromExcel(method);
            TestCountSupplier testCountSupplier = supplierStream
                    .filter(t -> t.getTestCase().equalsIgnoreCase(method.getName()))
                    .findFirst().orElse(null);
            if (testCountSupplier != null && testCountSupplier.isExecute() && testCountSupplier.getTestCount() > 0) {
                if (method.isAnnotationPresent(TestDescription.class)) {
                    TestDescription testDescription = method.getAnnotation(TestDescription.class);
                    String description = testDescription.description();
                    context.getCurrentXmlTest().addParameter("testDescription", description);
                }
                methodInstance.getMethod().setInvocationCount(testCountSupplier.getTestCount());
                filteredMethods.add(methodInstance);
            }
        }
        return filteredMethods;
    }

}

