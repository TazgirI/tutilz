package net.tazgirl.tutilz.magic_json.statement_object.compounds.or;

import net.tazgirl.tutilz.magic_json.statement_object.BaseStatementObject;
import net.tazgirl.tutilz.magic_json.statement_object.compounds.CompoundStatementObject;
import net.tazgirl.tutilz.magic_json.statement_object.interface_categories.ContainsBooleans;
import net.tazgirl.tutilz.magic_json.statement_object.interface_categories.ContainsNumbers;
import net.tazgirl.tutilz.magic_json.statement_object.interface_categories.NumericalEvaluator;
import net.tazgirl.tutilz.magic_json.statement_object.interface_categories.ResolvesBoolean;
import net.tazgirl.tutilz.magic_json.statement_object.numbers.objects.NumberStatementObject;

import java.util.Map;
import java.util.function.BiFunction;

public class OrBooleanStatementObject extends CompoundStatementObject<ResolvesBoolean> implements ContainsBooleans, ResolvesBoolean
{

    public OrBooleanStatementObject()
    {
        identifier = "OrBool";
    }

    @Override
    public void SpreadArgs(Map<String, Object> newArgs)
    {
        super.SpreadArgs(newArgs);
        for(ResolvesBoolean object: contents)
        {
            object.SpreadArgs(args);
        }
    }

    @Override
    public Boolean Resolve()
    {
        for(ResolvesBoolean object: contents)
        {
            if(object.Resolve())
            {
                return true;
            }
        }

        return false;
    }
}
