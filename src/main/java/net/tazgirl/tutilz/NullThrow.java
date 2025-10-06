package net.tazgirl.tutilz;

public class NullThrow
{

    public static void Check(Object... objs)
    {
        for(Object obj: objs)
        {
            if(obj == null)
            {
                throw new NullPointerException("Argument was passed as null into " + Thread.currentThread().getStackTrace()[2].toString() + " from " + Thread.currentThread().getStackTrace()[3].toString() + " (approximate)");
            }
        }
    }

}
