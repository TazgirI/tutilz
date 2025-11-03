package net.tazgirl.tutilz.magic_json.statement_object.interface_categories;

import java.util.Map;

public interface ResolvesBoolean
{
    public Boolean Resolve();
    public void SpreadArgs(Map<String, Object> newArgs);
}
