package net.tazgirl.tutilz.magic_json.statement_object.numbers.objects;

import net.tazgirl.tutilz.Tutilz;
import net.tazgirl.tutilz.admin.Logging;

public class IntegerStatementObject extends NumberStatementObject<Integer>
{
    public IntegerStatementObject()
    {
        identifier = "Integer";
        numberType = Integer.class;
    }

    @Override
    public Boolean HandleValue(Object content)
    {
        if(content instanceof String contentString)
        {
            contentString = contentString.replace("I", "");
            contentString = contentString.replace("i", "");

            try
            {
                value = Integer.valueOf(contentString);
            }
            catch (NumberFormatException error)
            {
                Logging.Error("Attempted to convert the following String to a Integer but failed: " + contentString, Tutilz.LOGGER);
                return false;
            }
            return true;
        }

        return false;
    }

}
