package com.qingshan.cronExpressionParser;

import com.qingshan.cronExpressionParser.Exceptions.InvalidCronExpressionException;

public class App {

    public static void main(String[] args) {
        try {
            String expression = args[0];
            CronExpressionProcessor parser = new CronExpressionProcessor(expression);
            System.out.println(parser.getParsedResult());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide cron expression as an argument");
        } catch (InvalidCronExpressionException e) {
            System.out.println(e.getMessage());
        }
    }
}
