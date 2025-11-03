package net.tazgirl.tutilz.magic_json;

import net.tazgirl.tutilz.magic_json.statement_object.BaseStatementObject;
import net.tazgirl.tutilz.magic_json.statement_object.BooleanStatementObject;
import net.tazgirl.tutilz.magic_json.statement_object.compounds.and.AndBooleanStatementObject;

public class Test
{
    public static void main(String[] args)
    {
        System.out.println(Tokenise.TokeniseStatement("ANDBOOL(TRUE ARGBOOL(waves))"));

        BaseStatementObject statementObject = TokenToStatement.objectFromTokens(Tokenise.TokeniseStatement("LESSTHAN(ORNUM(12 14) ANDNUM(13 12))"));

        if(statementObject == null)
        {
            System.out.println("statement is null");
        }

        System.out.println(statementObject.toString());
        System.out.println(statementObject.Resolve());

    }


}
