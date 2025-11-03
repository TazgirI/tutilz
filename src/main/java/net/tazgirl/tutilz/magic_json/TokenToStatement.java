package net.tazgirl.tutilz.magic_json;

import net.tazgirl.tutilz.Tutilz;
import net.tazgirl.tutilz.admin.Logging;
import net.tazgirl.tutilz.magic_json.statement_object.BaseStatementObject;

import java.util.List;

public class TokenToStatement
{

    public static BaseStatementObject objectFromTokens(List<String> tokens)
    {
        if(TextSymbols.tokenStackObjectPairs.get(tokens.getFirst()) == null)
        {
            Logging.Log("Statement did not start with a recognizable token: " + tokens.toString(), Tutilz.LOGGER);
            return null;
        }

        Stack stack = new Stack(tokens);


        for(String token: tokens)
        {
            if(TextSymbols.tokenStackObjectPairs.get(token.toUpperCase()) != null)
            {
                Class<? extends BaseStatementObject> newObjectClass = TextSymbols.tokenStackObjectPairs.get(token.toUpperCase());
                stack.Put(newObjectClass);
            }
            else if(TextSymbols.objectCloseChars.contains(token.charAt(0)))
            {
                stack.Close();
            }
            else if(TextSymbols.booleanTokens.get(token.toLowerCase()) instanceof Boolean bool)
            {
                stack.Put(bool);
            }
            else if(Character.isDigit(token.charAt(0)))
            {
                stack.PutNum(token);
            }
            else
            {
                stack.ParsingError("Attempted to parse unrecognised token");
            }

        }

        return stack.GetStackResult();
    }
}
