# Cron Expression Parser

This is a script that parses a cron string into fields. It accepts the standard cron format with five time fields 
(minute, hour, day of month, month, and day of week) plus a command. The special characters like @yearly is not included
in this implementation.

eg: for ```*/15 0 1,15 * 1-5 /usr/bin/find``` the output would be
```
minute       0 15 30 45
hour         0
day of month 1 15
month        1 2 3 4 5 6 7 8 9 10 11 12
day of week  1 2 3 4 5
command      /usr/bin/find
```

Based on the ambiguity of the requirements. A few assumptions/constraints
have been made. The solution would support a cron expression that:

- In a single line of input and contains 6 fields separated by one empty space
- Each field supports ```",", "-", "/" and "*"``` characters and digit numbers

The assumptions include:

- There is possibility of invalid format for the cron expression.
  
- A valid cron format would have valid values. 
  eg: Assuming those scenarios would not occur 
  - for range expression, invalid range like ```2-1``` would not occur
  - for minute field, invalid value like ```68``` would not occur
  - for interval expression, the interval would not exceed the range, like dayOfWeek ```*/8``` would not occur  

- For day of month field, ```*``` would be parsed into 1-31 days. The reason is that it's not clear what the expected output
  for certain values of month. eg: for expression ```0 0 * 1,2,3 1 /usr/bin/find```, it's unclear what the output of day of 
  month field should be.
  
## Build and run the script

To build the jar file, execute the gradle build under the root path of the project:

```
./gradlew build
```
Execute the jar fild in built lib folder:
```
java -jar build/libs/cronExpressionParser-${version}-SNAPSHOT.jar "${cronExpression}"
```
eg a sample command:
```
java -jar build/libs/cronExpressionParser-1.0-SNAPSHOT.jar "*/15 0 1,15 * 1-5 /usr/bin/find"
```
output:
```
minute       0 15 30 45
hour         0
day of month 1 15
month        1 2 3 4 5 6 7 8 9 10 11 12
day of week  1 2 3 4 5
command      /usr/bin/find
```