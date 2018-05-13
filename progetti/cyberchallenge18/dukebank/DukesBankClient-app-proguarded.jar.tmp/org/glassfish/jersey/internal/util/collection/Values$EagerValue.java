// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Values.java

package org.glassfish.jersey.internal.util.collection;


// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            Value, Values, LazyValue

static class result
    implements Value
{

            public Object get()
            {
/* 317*/        return result;
            }

            private final Object result;

            private (Value value)
            {
/* 312*/        result = value.get();
            }


            // Unreferenced inner class org/glassfish/jersey/internal/util/collection/Values$1

/* anonymous class */
    static class Values._cls1
        implements LazyValue
    {

                public final Object get()
                {
/*  52*/            return null;
                }

                public final boolean isInitialized()
                {
/*  57*/            return true;
                }

    }

}
