package net.tazgirl.tutilz.magic_json.statement_object.interface_categories;

import net.tazgirl.tutilz.magic_json.statement_object.BaseStatementObject;

import java.util.function.BiFunction;

public interface NumericalEvaluator
{
    public Boolean numericalTest(BiFunction<Number, Number, Boolean> test, BaseStatementObject operand, Boolean operandIsLeft);
}
