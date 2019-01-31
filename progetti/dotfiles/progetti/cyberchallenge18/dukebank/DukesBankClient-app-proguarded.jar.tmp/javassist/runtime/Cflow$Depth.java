// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Cflow.java

package javassist.runtime;


// Referenced classes of package javassist.runtime:
//            Cflow

static class depth
{

            int get()
            {
/*  30*/        return depth;
            }

            void inc()
            {
/*  31*/        depth++;
            }

            void dec()
            {
/*  32*/        depth--;
            }

            private int depth;

            ()
            {
/*  29*/        depth = 0;
            }
}
