# A quick explanation

This package contains the calculator grammer. The entry point is usually the `Statement` class, though we sometimes
want to evaluate a `Sum` directly. Instead of a generic "Expression" class, the top class in expressions is called sum,
as expressions in this calculator are always sums of products.

The grammar implicit in these classes is basically this:

```
Statement -> VarName (=|+=|-=) Sum
Sum -> Product SumPart*
SumPart -> (+|-) Product
Product -> Operand ProductPart*
ProductPart -> (*|/) Operand
Operand -> (NumberOperand|VarOperand)
NumberOperand -> (+|-)* [0-9]+
VarOperand -> (+|-)* (++|--)* Identifier (++|--)*
```

Expressions are always evaluated left to right, so statements like:
```
i += i++ - ++i
```
Have a defined behavior.