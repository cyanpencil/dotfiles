// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ThreadLocalRandom.java

package org.glassfish.jersey.internal.util.collection;

import java.util.Random;

class ThreadLocalRandom extends Random
{

            ThreadLocalRandom()
            {
/* 112*/        initialized = true;
            }

            public static ThreadLocalRandom current()
            {
/* 121*/        return (ThreadLocalRandom)localRandom.get();
            }

            public void setSeed(long l)
            {
/* 131*/        if(initialized)
                {
/* 132*/            throw new UnsupportedOperationException();
                } else
                {
/* 133*/            rnd = (l ^ 0x5deece66dL) & 0xffffffffffffL;
/* 134*/            return;
                }
            }

            protected int next(int i)
            {
/* 137*/        rnd = rnd * 0x5deece66dL + 11L & 0xffffffffffffL;
/* 138*/        return (int)(rnd >>> 48 - i);
            }

            public int nextInt(int i, int j)
            {
/* 152*/        if(i >= j)
/* 153*/            throw new IllegalArgumentException();
/* 154*/        else
/* 154*/            return nextInt(j - i) + i;
            }

            public long nextLong(long l)
            {
/* 167*/        if(l <= 0L)
/* 168*/            throw new IllegalArgumentException("n must be positive");
/* 174*/        long l1 = 0L;
                long l3;
/* 175*/        for(; l >= 0x7fffffffL; l = l3)
                {
/* 176*/            int i = next(2);
/* 177*/            long l2 = l >>> 1;
/* 178*/            l3 = (i & 2) != 0 ? l - l2 : l2;
/* 179*/            if((i & 1) == 0)
/* 180*/                l1 += l - l3;
                }

/* 183*/        return l1 + (long)nextInt((int)l);
            }

            public long nextLong(long l, long l1)
            {
/* 197*/        if(l >= l1)
/* 198*/            throw new IllegalArgumentException();
/* 199*/        else
/* 199*/            return nextLong(l1 - l) + l;
            }

            public double nextDouble(double d)
            {
/* 212*/        if(d <= 0.0D)
/* 213*/            throw new IllegalArgumentException("n must be positive");
/* 214*/        else
/* 214*/            return nextDouble() * d;
            }

            public double nextDouble(double d, double d1)
            {
/* 228*/        if(d >= d1)
/* 229*/            throw new IllegalArgumentException();
/* 230*/        else
/* 230*/            return nextDouble() * (d1 - d) + d;
            }

            private static final long multiplier = 0x5deece66dL;
            private static final long addend = 11L;
            private static final long mask = 0xffffffffffffL;
            private long rnd;
            boolean initialized;
            private long pad0;
            private long pad1;
            private long pad2;
            private long pad3;
            private long pad4;
            private long pad5;
            private long pad6;
            private long pad7;
            private static final ThreadLocal localRandom = new ThreadLocal() {

                protected final ThreadLocalRandom initialValue()
                {
/* 102*/            return new ThreadLocalRandom();
                }

                protected final volatile Object initialValue()
                {
/* 100*/            return initialValue();
                }

    };
            private static final long serialVersionUID = 0xaeca4f167a867673L;

}
