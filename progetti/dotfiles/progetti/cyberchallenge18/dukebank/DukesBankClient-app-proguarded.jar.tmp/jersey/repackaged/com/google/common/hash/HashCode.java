// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HashCode.java

package jersey.repackaged.com.google.common.hash;

import java.io.Serializable;

public abstract class HashCode
{
    static final class IntHashCode extends HashCode
        implements Serializable
    {

                public final int bits()
                {
/* 132*/            return 32;
                }

                public final byte[] asBytes()
                {
/* 137*/            return (new byte[] {
/* 137*/                (byte)hash, (byte)(hash >> 8), (byte)(hash >> 16), hash >> 24
                    });
                }

                public final int asInt()
                {
/* 146*/            return hash;
                }

                final boolean equalsSameBits(HashCode hashcode)
                {
/* 167*/            return hash == hashcode.asInt();
                }

                final int hash;

                IntHashCode(int i)
                {
/* 127*/            hash = i;
                }
    }


            HashCode()
            {
            }

            public abstract int bits();

            public abstract int asInt();

            public abstract byte[] asBytes();

            abstract boolean equalsSameBits(HashCode hashcode);

            public static HashCode fromInt(int i)
            {
/* 120*/        return new IntHashCode(i);
            }

            public final boolean equals(Object obj)
            {
/* 355*/        if(obj instanceof HashCode)
                {
/* 356*/            obj = (HashCode)obj;
/* 357*/            return bits() == ((HashCode) (obj)).bits() && equalsSameBits(((HashCode) (obj)));
                } else
                {
/* 359*/            return false;
                }
            }

            public final int hashCode()
            {
/* 371*/        if(bits() >= 32)
/* 372*/            return asInt();
                byte abyte0[];
/* 375*/        int i = (abyte0 = asBytes())[0] & 0xff;
/* 377*/        for(int j = 1; j < abyte0.length; j++)
/* 378*/            i |= (abyte0[j] & 0xff) << (j << 3);

/* 380*/        return i;
            }

            public final String toString()
            {
/* 396*/        byte abyte0[] = asBytes();
/* 397*/        StringBuilder stringbuilder = new StringBuilder(2 * abyte0.length);
/* 398*/        int i = (abyte0 = abyte0).length;
/* 398*/        for(int j = 0; j < i; j++)
                {
/* 398*/            byte byte0 = abyte0[j];
/* 399*/            stringbuilder.append(hexDigits[byte0 >> 4 & 0xf]).append(hexDigits[byte0 & 0xf]);
                }

/* 401*/        return stringbuilder.toString();
            }

            private static final char hexDigits[] = "0123456789abcdef".toCharArray();

}
