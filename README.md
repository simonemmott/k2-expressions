# k2-expressions

## General Usage
```java
String strExpression = "true";
K2ExpressionParser<Boolean> parser = K2ExpressionParser.booleanExpression();
K2Expression expression = parser.parse(strExpression);
K2ExpressionExecutor<Boolean> executor = K2ExpressionExecutor.booleanExpression();
Boolean result = executor.execute(expression);
assert result;
```

```java
class Data {
    public String id;
    public String name;
}
class SomeContext {
    public Data data;
    public String location;
}
String strExpression = "data.id";

Data data = new Data();
data.id = "ID";
data.name = "NAME";
SomeContext context = new SomeContext();
context.data = data;
context.location = "LOCATION";

K2ExpressionParser<?> parser = K2ExpressionParser.expression();
K2Expression expression = parser.parse(strExpression);
K2ExpressionExecutor<Boolean> executor = K2ExpressionExecutor.expression();
Object result = executor.execute(expression, context);
assert ("ID".equals(result)); 
```