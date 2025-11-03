package net.tazgirl.tutilz.magic_json.statement_object.numbers.objects;

import net.tazgirl.tutilz.Tutilz;
import net.tazgirl.tutilz.admin.Logging;
import net.tazgirl.tutilz.magic_json.statement_object.interface_categories.PrimitiveObject;

public class FloatStatementObject extends NumberStatementObject<Float>
{
    public FloatStatementObject()
    {
        identifier = "Float";
        numberType = Float.class;
    }

    @Override
    public Boolean HandleValue(Object content)
    {
        if(content instanceof String contentString)
        {
            contentString = contentString.replace("F", "");
            contentString = contentString.replace("f", "");

            try
            {
                value = Float.valueOf(contentString);
            }
            catch (NumberFormatException error)
            {
                Logging.Error("Attempted to convert the following String to a Float but failed: " + contentString, Tutilz.LOGGER);
                return false;
            }
            return true;
        }

        return false;
    }

}
