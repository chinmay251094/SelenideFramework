package com.supplier;

import com.creditdatamw.zerocell.converter.Converter;
import com.enums.RunModes;

public class StringToRunMode implements Converter<RunModes> {
    @Override
    public RunModes convert(String value, String columnNumber, int rowNumber) {
        return RunModes.valueOf(value.toUpperCase());
    }
}
