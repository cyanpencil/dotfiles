// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Murmur3_32HashFunction.java

package jersey.repackaged.com.google.common.hash;

import java.io.Serializable;
import java.nio.ByteBuffer;
import jersey.repackaged.com.google.common.primitives.UnsignedBytes;

// Referenced classes of package jersey.repackaged.com.google.common.hash:
//            AbstractStreamingHashFunction, HashCode, Hasher

final class Murmur3_32HashFunction extends AbstractStreamingHashFunction
    implements Serializable
{
    static final class Murmur3_32Hasher extends AbstractStreamingHashFunction.AbstractStreamingHasher
    {

                protected final void process(ByteBuffer bytebuffer)
                {
/* 162*/            bytebuffer = Murmur3_32HashFunction.mixK1(bytebuffer.getInt());
/* 163*/            h1 = Murmur3_32HashFunction.mixH1(h1, bytebuffer);
/* 164*/            length += 4;
                }

                protected final void processRemaining(ByteBuffer bytebuffer)
                {
/* 168*/            length += bytebuffer.remaining();
/* 169*/            int i = 0;
/* 170*/            for(int j = 0; bytebuffer.hasRemaining(); j += 8)
/* 171*/                i ^= UnsignedBytes.toInt(bytebuffer.get()) << j;

/* 173*/            h1 ^= Murmur3_32HashFunction.mixK1(i);
                }

                public final HashCode makeHash()
                {
/* 177*/            return Murmur3_32HashFunction.fmix(h1, length);
                }

                private int h1;
                private int length;

                Murmur3_32Hasher(int i)
                {
/* 156*/            super(4);
/* 157*/            h1 = i;
/* 158*/            length = 0;
                }
    }


            Murmur3_32HashFunction(int i)
            {
/*  54*/        seed = i;
            }

            public final Hasher newHasher()
            {
/*  62*/        return new Murmur3_32Hasher(seed);
            }

            public final String toString()
            {
/*  67*/        int i = seed;
/*  67*/        return (new StringBuilder(31)).append("Hashing.murmur3_32(").append(i).append(")").toString();
            }

            public final boolean equals(Object obj)
            {
/*  72*/        if(obj instanceof Murmur3_32HashFunction)
                {
/*  73*/            obj = (Murmur3_32HashFunction)obj;
/*  74*/            return seed == ((Murmur3_32HashFunction) (obj)).seed;
                } else
                {
/*  76*/            return false;
                }
            }

            public final int hashCode()
            {
/*  81*/        return getClass().hashCode() ^ seed;
            }

            public final HashCode hashLong(long l)
            {
/*  92*/        int j = (int)l;
/*  93*/        l = (int)(l >>> 32);
/*  95*/        i = mixK1(j);
/*  96*/        j = mixH1(seed, i);
/*  98*/        i = mixK1(l);
/*  99*/        return fmix(j = mixH1(j, i), 8);
            }

            private static int mixK1(int i)
            {
/* 126*/        return i = (i = Integer.rotateLeft(i *= 0xcc9e2d51, 15)) * 0x1b873593;
            }

            private static int mixH1(int i, int j)
            {
/* 133*/        return i = (i = Integer.rotateLeft(i ^= j, 13)) * 5 + 0xe6546b64;
            }

            private static HashCode fmix(int i, int j)
            {
/* 141*/        return HashCode.fromInt(i = (i = (i = (i = (i = (i ^= j) ^ i >>> 16) * 0x85ebca6b) ^ i >>> 13) * 0xc2b2ae35) ^ i >>> 16);
            }

            private final int seed;



}
