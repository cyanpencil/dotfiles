// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Trilean.java

package com.owlike.genson;


// Referenced classes of package com.owlike.genson:
//            Trilean

static class nit> extends Trilean
{

            public final boolean booleanValue()
            {
/*  24*/        throw new IllegalStateException("Unknown state can not be converter to a boolean, only TRUE AND FALSE can!");
            }

            ion(String s, int i)
            {
/*  21*/        super(s, i);
            }

            // Unreferenced inner class com/owlike/genson/Trilean$1

/* anonymous class */
    static class Trilean._cls1 extends Trilean
    {

                public final boolean booleanValue()
                {
/*  12*/            return true;
                }

    }

}
