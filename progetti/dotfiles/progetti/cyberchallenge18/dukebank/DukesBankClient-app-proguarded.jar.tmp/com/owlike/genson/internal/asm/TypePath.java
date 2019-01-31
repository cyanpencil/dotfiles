// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 

package com.owlike.genson.internal.asm;


// Referenced classes of package com.owlike.genson.internal.asm:
//            ByteVector

public class TypePath
{

    TypePath(byte abyte0[], int i)
    {
        a = abyte0;
        b = i;
    }

    public int getLength()
    {
        return a[b];
    }

    public int getStep(int i)
    {
        return a[b + 2 * i + 1];
    }

    public int getStepArgument(int i)
    {
        return a[b + 2 * i + 2];
    }

    public static TypePath fromString(String s)
    {
        if(s == null || s.length() == 0)
            return null;
        int i = s.length();
        ByteVector bytevector;
        (bytevector = new ByteVector(i)).putByte(0);
        int j = 0;
        do
        {
            if(j >= i)
                break;
            char c;
            if((c = s.charAt(j++)) == '[')
                bytevector.a(0, 0);
            else
            if(c == '.')
                bytevector.a(1, 0);
            else
            if(c == '*')
                bytevector.a(2, 0);
            else
            if(c >= '0' && c <= '9')
            {
                int k = c - 48;
                char c1;
                for(; j < i && (c1 = s.charAt(j)) >= '0' && c1 <= '9'; j++)
                    k = (k * 10 + c1) - 48;

                bytevector.a(3, k);
            }
        } while(true);
        bytevector.a[0] = (byte)(bytevector.b / 2);
        return new TypePath(bytevector.a, 0);
    }

    public String toString()
    {
        int i = getLength();
        StringBuffer stringbuffer = new StringBuffer(i << 1);
        for(int j = 0; j < i; j++)
            switch(getStep(j))
            {
            case 0: // '\0'
                stringbuffer.append('[');
                break;

            case 1: // '\001'
                stringbuffer.append('.');
                break;

            case 2: // '\002'
                stringbuffer.append('*');
                break;

            case 3: // '\003'
                stringbuffer.append(getStepArgument(j));
                break;

            default:
                stringbuffer.append('_');
                break;
            }

        return stringbuffer.toString();
    }

    public static final int ARRAY_ELEMENT = 0;
    public static final int INNER_TYPE = 1;
    public static final int WILDCARD_BOUND = 2;
    public static final int TYPE_ARGUMENT = 3;
    byte a[];
    int b;
}
