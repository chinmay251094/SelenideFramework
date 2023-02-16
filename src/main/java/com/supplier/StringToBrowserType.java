package com.supplier;

import com.creditdatamw.zerocell.converter.Converter;
import com.enums.Browsers;

public class StringToBrowserType implements Converter<Browsers> {
    @Override
    public Browsers convert(String value, String columnNumber, int rowNumber) {
        return Browsers.valueOf(value.toUpperCase());
    }
}
