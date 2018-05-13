// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractStreamingHashFunction.java

package jersey.repackaged.com.google.common.hash;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.hash:
//            HashFunction, Hasher, HashCode, AbstractHasher

abstract class AbstractStreamingHashFunction
    implements HashFunction
{
    public static abstract class AbstractStreamingHasher extends AbstractHasher
    {

                protected abstract void process(ByteBuffer bytebuffer);

                protected void processRemaining(ByteBuffer bytebuffer)
                {
/* 133*/            bytebuffer.position(bytebuffer.limit());
/* 134*/            bytebuffer.limit(chunkSize + 7);
/* 135*/            for(; bytebuffer.position() < chunkSize; bytebuffer.putLong(0L));
/* 138*/            bytebuffer.limit(chunkSize);
/* 139*/            bytebuffer.flip();
/* 140*/            process(bytebuffer);
                }

                public final Hasher putLong(long l)
                {
/* 216*/            buffer.putLong(l);
/* 217*/            munchIfFull();
/* 218*/            return this;
                }

                public final HashCode hash()
                {
/* 229*/            munch();
/* 230*/            buffer.flip();
/* 231*/            if(buffer.remaining() > 0)
/* 232*/                processRemaining(buffer);
/* 234*/            return makeHash();
                }

                abstract HashCode makeHash();

                private void munchIfFull()
                {
/* 241*/            if(buffer.remaining() < 8)
/* 243*/                munch();
                }

                private void munch()
                {
/* 248*/            buffer.flip();
/* 249*/            for(; buffer.remaining() >= chunkSize; process(buffer));
/* 254*/            buffer.compact();
                }

                private final ByteBuffer buffer;
                private final int bufferSize;
                private final int chunkSize;

                protected AbstractStreamingHasher(int i)
                {
/*  95*/            this(i, i);
                }

                protected AbstractStreamingHasher(int i, int j)
                {
/* 109*/            Preconditions.checkArgument(j % i == 0);
/* 112*/            buffer = ByteBuffer.allocate(j + 7).order(ByteOrder.LITTLE_ENDIAN);
/* 115*/            bufferSize = j;
/* 116*/            chunkSize = i;
                }
    }


            AbstractStreamingHashFunction()
            {
            }

            public HashCode hashLong(long l)
            {
/*  53*/        return newHasher().putLong(l).hash();
            }
}
