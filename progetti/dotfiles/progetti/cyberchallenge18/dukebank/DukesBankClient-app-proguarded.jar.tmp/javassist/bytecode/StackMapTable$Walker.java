// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StackMapTable.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            BadBytecode, ByteArray, StackMapTable

public static class numOfEntries
{

            public final int size()
            {
/* 172*/        return numOfEntries;
            }

            public void parse()
                throws BadBytecode
            {
/* 178*/        int i = numOfEntries;
/* 179*/        int j = 2;
/* 180*/        for(int k = 0; k < i; k++)
/* 181*/            j = stackMapFrames(j, k);

            }

            int stackMapFrames(int i, int j)
                throws BadBytecode
            {
/* 194*/        if((j = info[i] & 0xff) < 64)
                {
/* 196*/            sameFrame(i, j);
/* 197*/            i++;
                } else
/* 199*/        if(j < 128)
                {
/* 200*/            i = sameLocals(i, j);
                } else
                {
/* 201*/            if(j < 247)
/* 202*/                throw new BadBytecode("bad frame_type in StackMapTable");
/* 203*/            if(j == 247)
/* 204*/                i = sameLocals(i, j);
/* 205*/            else
/* 205*/            if(j < 251)
                    {
/* 206*/                int k = ByteArray.readU16bit(info, i + 1);
/* 207*/                chopFrame(i, k, 251 - j);
/* 208*/                i += 3;
                    } else
/* 210*/            if(j == 251)
                    {
/* 211*/                int l = ByteArray.readU16bit(info, i + 1);
/* 212*/                sameFrame(i, l);
/* 213*/                i += 3;
                    } else
/* 215*/            if(j < 255)
/* 216*/                i = appendFrame(i, j);
/* 218*/            else
/* 218*/                i = fullFrame(i);
                }
/* 220*/        return i;
            }

            public void sameFrame(int i, int j)
                throws BadBytecode
            {
            }

            private int sameLocals(int i, int j)
                throws BadBytecode
            {
/* 234*/        int k = i;
/* 236*/        if(j < 128)
                {
/* 237*/            j -= 64;
                } else
                {
/* 239*/            j = ByteArray.readU16bit(info, i + 1);
/* 240*/            i += 2;
                }
/* 243*/        int l = info[i + 1] & 0xff;
/* 244*/        int i1 = 0;
/* 245*/        if(l == 7 || l == 8)
                {
/* 246*/            i1 = ByteArray.readU16bit(info, i + 2);
/* 247*/            objectOrUninitialized(l, i1, i + 2);
/* 248*/            i += 2;
                }
/* 251*/        sameLocals(k, j, l, i1);
/* 252*/        return i + 2;
            }

            public void sameLocals(int i, int j, int k, int l)
                throws BadBytecode
            {
            }

            public void chopFrame(int i, int j, int k)
                throws BadBytecode
            {
            }

            private int appendFrame(int i, int j)
                throws BadBytecode
            {
/* 280*/        j -= 251;
/* 281*/        int k = ByteArray.readU16bit(info, i + 1);
/* 282*/        int ai[] = new int[j];
/* 283*/        int ai1[] = new int[j];
/* 284*/        int l = i + 3;
/* 285*/        for(int i1 = 0; i1 < j; i1++)
                {
/* 286*/            int j1 = info[l] & 0xff;
/* 287*/            ai[i1] = j1;
/* 288*/            if(j1 == 7 || j1 == 8)
                    {
/* 289*/                ai1[i1] = ByteArray.readU16bit(info, l + 1);
/* 290*/                objectOrUninitialized(j1, ai1[i1], l + 1);
/* 291*/                l += 3;
                    } else
                    {
/* 294*/                ai1[i1] = 0;
/* 295*/                l++;
                    }
                }

/* 299*/        appendFrame(i, k, ai, ai1);
/* 300*/        return l;
            }

            public void appendFrame(int i, int j, int ai[], int ai1[])
                throws BadBytecode
            {
            }

            private int fullFrame(int i)
                throws BadBytecode
            {
/* 316*/        int j = ByteArray.readU16bit(info, i + 1);
                int k;
/* 317*/        int ai[] = new int[k = ByteArray.readU16bit(info, i + 3)];
/* 319*/        int ai1[] = new int[k];
/* 320*/        k = verifyTypeInfo(i + 5, k, ai, ai1);
                int l;
/* 321*/        int ai2[] = new int[l = ByteArray.readU16bit(info, k)];
/* 323*/        int ai3[] = new int[l];
/* 324*/        k = verifyTypeInfo(k + 2, l, ai2, ai3);
/* 325*/        fullFrame(i, j, ai, ai1, ai2, ai3);
/* 326*/        return k;
            }

            public void fullFrame(int i, int j, int ai[], int ai1[], int ai2[], int ai3[])
                throws BadBytecode
            {
            }

            private int verifyTypeInfo(int i, int j, int ai[], int ai1[])
            {
/* 346*/        for(int k = 0; k < j; k++)
                {
/* 347*/            int l = info[i++] & 0xff;
/* 348*/            ai[k] = l;
/* 349*/            if(l == 7 || l == 8)
                    {
/* 350*/                ai1[k] = ByteArray.readU16bit(info, i);
/* 351*/                objectOrUninitialized(l, ai1[k], i);
/* 352*/                i += 2;
                    }
                }

/* 356*/        return i;
            }

            public void objectOrUninitialized(int i, int j, int k)
            {
            }

            byte info[];
            int numOfEntries;

            public (StackMapTable stackmaptable)
            {
/* 153*/        this(stackmaptable.get());
            }

            public <init>(byte abyte0[])
            {
/* 165*/        info = abyte0;
/* 166*/        numOfEntries = ByteArray.readU16bit(abyte0, 0);
            }
}
