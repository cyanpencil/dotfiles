// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Hashing.java

package jersey.repackaged.com.google.common.hash;


// Referenced classes of package jersey.repackaged.com.google.common.hash:
//            Murmur3_32HashFunction, HashFunction

public final class Hashing
{
    static class Murmur3_32Holder
    {

                static final HashFunction MURMUR3_32 = new Murmur3_32HashFunction(0);
                static final HashFunction GOOD_FAST_HASH_FUNCTION_32 = Hashing.murmur3_32(Hashing.GOOD_FAST_HASH_SEED);

    }


            public static HashFunction murmur3_32(int i)
            {
/*  97*/        return new Murmur3_32HashFunction(i);
            }

            public static HashFunction murmur3_32()
            {
/* 109*/        return Murmur3_32Holder.MURMUR3_32;
            }

            private static final int GOOD_FAST_HASH_SEED = (int)System.currentTimeMillis();


}
