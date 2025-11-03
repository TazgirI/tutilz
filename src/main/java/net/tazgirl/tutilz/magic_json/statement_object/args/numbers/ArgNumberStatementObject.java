package net.tazgirl.tutilz.magic_json.statement_object.args.numbers;

import net.tazgirl.tutilz.Tutilz;
import net.tazgirl.tutilz.admin.Logging;
import net.tazgirl.tutilz.magic_json.DefaultValues;
import net.tazgirl.tutilz.magic_json.statement_object.args.ArgStatementObject;

public class ArgNumberStatementObject<T extends Number> extends ArgStatementObject
{

    protected Class<T> numberClass;
    protected String numberIdentifier;


    public ArgNumberStatementObject(Class<T> myNewClass, String myNewNumberType)
    {
        numberClass = myNewClass;
        numberIdentifier = myNewNumberType;
    }

    @Override
    public T Resolve()
    {
        Object keyValue = args.get(value);
        if(numberClass.isInstance(keyValue))
        {
            return (T) keyValue;
        }
        else if(keyValue == null)
        {
            Logging.Error("Could not find the key \"" + value + "\" in provided args: \"" + args.toString() + "\"", Tutilz.LOGGER);
        }
        else
        {
            Logging.Error("The key \"" + value + "\" did not return " + numberIdentifier + " from provided args: \"" + args.toString() + "\"", Tutilz.LOGGER);
        }

        return (T) DefaultValues.getDefault(numberClass);
    }
}
