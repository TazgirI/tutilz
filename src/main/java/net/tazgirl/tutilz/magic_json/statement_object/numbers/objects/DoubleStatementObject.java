package net.tazgirl.tutilz.magic_json.statement_object.numbers.objects;

import net.tazgirl.tutilz.Tutilz;
import net.tazgirl.tutilz.admin.Logging;

public class DoubleStatementObject extends NumberStatementObject<Double>
{
    public DoubleStatementObject()
    {
        identifier = "Double";
        numberType = Double.class;
    }

    @Override
    public Boolean HandleValue(Object content)
    {
        if(content instanceof String contentString)
        {
            contentString = contentString.replace("D", "");
            contentString = contentString.replace("d", "");

            try
            {
                value = Double.valueOf(contentString);
            }
            catch (NumberFormatException error)
            {
                Logging.Error("Attempted to convert the following String to a Double but failed: " + contentString, Tutilz.LOGGER);
                return false;
            }
            return true;
        }

        return false;
    }
}
