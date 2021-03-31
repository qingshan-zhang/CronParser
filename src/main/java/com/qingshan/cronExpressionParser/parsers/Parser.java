package com.qingshan.cronExpressionParser.parsers;

import com.qingshan.cronExpressionParser.models.ValueRange;

import java.util.List;

public interface Parser {

    List<String> getParsedString(String input, ValueRange range);

}
