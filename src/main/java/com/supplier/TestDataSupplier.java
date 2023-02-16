package com.supplier;

import com.creditdatamw.zerocell.annotation.Column;
import com.creditdatamw.zerocell.converter.BooleanConverter;
import com.enums.Browsers;
import com.enums.RunModes;
import io.github.sskorol.data.Sheet;

@Sheet(name = "TestData")
public class TestDataSupplier {
    @Column(name = "TestCase", index = 0)
    private String TestCase;
    @Column(name = "Browser", index = 1, converterClass = StringToBrowserType.class)
    private Browsers Browser;
    @Column(name = "URL", index = 2)
    private String URL;
    @Column(name = "Execute", index = 3, converterClass = BooleanConverter.class)
    private boolean Execute;
    @Column(name = "Mode", index = 4, converterClass = StringToRunMode.class)
    private RunModes Mode;
    @Column(name = "Username", index = 5)
    private String Username;
    @Column(name = "Password", index = 6)
    private String Password;
    @Column(name = "District", index = 7)
    private String District;

    public String getTestcase() {
        return TestCase;
    }

    public boolean isExecute() {
        return Execute;
    }

    public Browsers getBrowser() {
        return Browser;
    }

    public RunModes getMode() {
        return Mode;
    }

    public String getURL() {
        return URL;
    }

    public String getDistrict() {
        return District;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }
}
