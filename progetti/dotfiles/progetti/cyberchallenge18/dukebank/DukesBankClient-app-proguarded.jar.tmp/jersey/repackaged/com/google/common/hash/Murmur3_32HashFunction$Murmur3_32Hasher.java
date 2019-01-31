// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Murmur3_32HashFunction.java

package jersey.repackaged.com.google.common.hash;

import java.nio.ByteBuffer;
import jersey.repackaged.com.google.common.primitives.UnsignedBytes;

// Referenced classes of package jersey.repackaged.com.google.common.hash:
//            AbstractStreamingHashFunction, Murmur3_32HashFunction, HashCode

static final class length extends reamingHasher
{

            protected final void process(ByteBuffer bytebuffer)
            {
/* 162*/        bytebuffer = Murmur3_32HashFunction.access$000(bytebuffer.getInt());
/* 163*/        h1 = Murmur3_32HashFunction.access$100(h1, bytebuffer);
/* 164*/        length += 4;
            }

            protected final void processRemaining(ByteBuffer bytebuffer)
            {
/* 168*/        length += bytebuffer.remaining();
/* 169*/        int i = 0;
/* 170*/        for(int j = 0; bytebuffer.hasRemaining(); j += 8)
/* 171*/            i ^= UnsignedBytes.toInt(bytebuffer.get()) << j;

/* 173*/        h1 ^= Murmur3_32HashFunction.access$000(i);
            }

            public final HashCode makeHash()
            {
/* 177*/        return Murmur3_32HashFunction.access$200(h1, length);
            }

            private int h1;
            private int length;

            reamingHasher(int i)
            {
/* 156*/        super(4);
/* 157*/        h1 = i;
/* 158*/        length = 0;
            }
}
