package com.qingshan.cronExpressionParser.parsers;

import com.qingshan.cronExpressionParser.models.ValueRange;

import java.util.List;

public class MultipleValuesParser implements Parser {

    @Override
    public List<String> getParsedString(String input, ValueRange range) {
        String[] values = input.split(",");
        return List.of(values);
    }
}
