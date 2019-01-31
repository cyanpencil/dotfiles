// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SeaSurfStore.java

package e;

import java.util.Random;
import javax.xml.bind.DatatypeConverter;

public final class e
{
    static class a
    {

                static e a()
                {
/*  26*/            return a;
                }

                private static final e a = new e((byte)0);

    }


            private e()
            {
/*  19*/        a = new Random();
            }

            public static e a()
            {
/*  23*/        return a.a();
            }

            public final void a(long l)
            {
/*  32*/        a.setSeed(l);
            }

            public final String b()
            {
/*  36*/        byte abyte0[] = new byte[16];
/*  37*/        a.nextBytes(abyte0);
/*  38*/        return DatatypeConverter.printHexBinary(abyte0);
            }

            e(byte byte0)
            {
/*  14*/        this();
            }

            private final Random a;
}
