package net.tazgirl.tutilz.magic_json.statement_object.compounds.or;

import net.tazgirl.tutilz.magic_json.statement_object.BaseStatementObject;
import net.tazgirl.tutilz.magic_json.statement_object.compounds.CompoundStatementObject;
import net.tazgirl.tutilz.magic_json.statement_object.interface_categories.ContainsNumbers;
import net.tazgirl.tutilz.magic_json.statement_object.interface_categories.NumericalEvaluator;
import net.tazgirl.tutilz.magic_json.statement_object.numbers.objects.NumberStatementObject;

import java.util.Map;
import java.util.function.BiFunction;

public class OrNumericalStatementObject extends CompoundStatementObject<NumberStatementObject<?>> implements ContainsNumbers, NumericalEvaluator
{

    public OrNumericalStatementObject()
    {
        identifier = "OrNum";
    }

    @Override
    public Boolean numericalTest(BiFunction<Number, Number, Boolean> test, BaseStatementObject operand, Boolean operandIsLeft)
    {
        for(NumberStatementObject<?> object: this.contents)
        {
            if(object.numericalTest(test, operand, operandIsLeft)){return true;}
        }

        return false;
    }

    @Override
    public void SpreadArgs(Map<String, Object> newArgs)
    {
        super.SpreadArgs(newArgs);
        for(NumberStatementObject<?> object: contents)
        {
            object.SpreadArgs(args);
        }
    }
}
