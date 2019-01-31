// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HashCode.java

package jersey.repackaged.com.google.common.hash;

import java.io.Serializable;

// Referenced classes of package jersey.repackaged.com.google.common.hash:
//            HashCode

static final class hash extends HashCode
    implements Serializable
{

            public final int bits()
            {
/* 132*/        return 32;
            }

            public final byte[] asBytes()
            {
/* 137*/        return (new byte[] {
/* 137*/            (byte)hash, (byte)(hash >> 8), (byte)(hash >> 16), hash >> 24
                });
            }

            public final int asInt()
            {
/* 146*/        return hash;
            }

            final boolean equalsSameBits(HashCode hashcode)
            {
/* 167*/        return hash == hashcode.asInt();
            }

            final int hash;

            (int i)
            {
/* 127*/        hash = i;
            }
}
