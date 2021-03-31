package com.qingshan.cronExpressionParser.parsers;

import com.qingshan.cronExpressionParser.models.ValueRange;

import java.util.List;

public class SingleValueParser implements Parser {

    @Override
    public List<String> getParsedString(String input, ValueRange range) {
        return List.of(input);
    }
}
