// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Closing.java

package org.glassfish.jersey.internal.util;

import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            Closure

public class Closing
{

            public static Closing with(InputStream inputstream)
            {
/*  53*/        return new Closing(inputstream);
            }

            public Closing(InputStream inputstream)
            {
/*  59*/        in = inputstream;
            }

            public void invoke(Closure closure)
                throws IOException
            {
/*  63*/        if(in == null)
/*  64*/            return;
/*  67*/        closure.invoke(in);
/*  70*/        in.close();
/*  73*/        return;
/*  71*/        JVM INSTR dup ;
/*  72*/        closure;
/*  72*/        throw ;
/*  69*/        closure;
/*  70*/        in.close();
                  goto _L1
/*  71*/        JVM INSTR dup ;
/*  72*/        closure;
/*  72*/        throw ;
_L1:
/*  72*/        throw closure;
            }

            private final InputStream in;
}
