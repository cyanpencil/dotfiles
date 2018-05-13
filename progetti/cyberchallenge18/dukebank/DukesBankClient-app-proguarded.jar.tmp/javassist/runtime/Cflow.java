// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Cflow.java

package javassist.runtime;


public class Cflow extends ThreadLocal
{
    static class Depth
    {

                int get()
                {
/*  30*/            return depth;
                }

                void inc()
                {
/*  31*/            depth++;
                }

                void dec()
                {
/*  32*/            depth--;
                }

                private int depth;

                Depth()
                {
/*  29*/            depth = 0;
                }
    }


            public Cflow()
            {
            }

            protected synchronized Object initialValue()
            {
/*  36*/        return new Depth();
            }

            public void enter()
            {
/*  42*/        ((Depth)get()).inc();
            }

            public void exit()
            {
/*  47*/        ((Depth)get()).dec();
            }

            public int value()
            {
/*  52*/        return ((Depth)get()).get();
            }
}
