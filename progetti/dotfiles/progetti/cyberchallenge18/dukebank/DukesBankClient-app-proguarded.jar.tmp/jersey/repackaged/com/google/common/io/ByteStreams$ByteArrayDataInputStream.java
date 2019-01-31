// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ByteStreams.java

package jersey.repackaged.com.google.common.io;

import java.io.*;

// Referenced classes of package jersey.repackaged.com.google.common.io:
//            ByteArrayDataInput, ByteStreams

static class input
    implements ByteArrayDataInput
{

            public void readFully(byte abyte0[])
            {
/* 214*/        try
                {
/* 214*/            input.readFully(abyte0);
/* 217*/            return;
                }
                // Misplaced declaration of an exception variable
/* 215*/        catch(byte abyte0[])
                {
/* 216*/            throw new IllegalStateException(abyte0);
                }
            }

            public void readFully(byte abyte0[], int i, int j)
            {
/* 222*/        try
                {
/* 222*/            input.readFully(abyte0, i, j);
/* 225*/            return;
                }
                // Misplaced declaration of an exception variable
/* 223*/        catch(byte abyte0[])
                {
/* 224*/            throw new IllegalStateException(abyte0);
                }
            }

            public int skipBytes(int i)
            {
/* 230*/        return input.skipBytes(i);
/* 231*/        i;
/* 232*/        throw new IllegalStateException(i);
            }

            public boolean readBoolean()
            {
/* 238*/        return input.readBoolean();
                IOException ioexception;
/* 239*/        ioexception;
/* 240*/        throw new IllegalStateException(ioexception);
            }

            public byte readByte()
            {
/* 246*/        return input.readByte();
                Object obj;
/* 247*/        obj;
/* 248*/        throw new IllegalStateException(((Throwable) (obj)));
/* 249*/        obj;
/* 250*/        throw new AssertionError(obj);
            }

            public int readUnsignedByte()
            {
/* 256*/        return input.readUnsignedByte();
                IOException ioexception;
/* 257*/        ioexception;
/* 258*/        throw new IllegalStateException(ioexception);
            }

            public short readShort()
            {
/* 264*/        return input.readShort();
                IOException ioexception;
/* 265*/        ioexception;
/* 266*/        throw new IllegalStateException(ioexception);
            }

            public int readUnsignedShort()
            {
/* 272*/        return input.readUnsignedShort();
                IOException ioexception;
/* 273*/        ioexception;
/* 274*/        throw new IllegalStateException(ioexception);
            }

            public char readChar()
            {
/* 280*/        return input.readChar();
                IOException ioexception;
/* 281*/        ioexception;
/* 282*/        throw new IllegalStateException(ioexception);
            }

            public int readInt()
            {
/* 288*/        return input.readInt();
                IOException ioexception;
/* 289*/        ioexception;
/* 290*/        throw new IllegalStateException(ioexception);
            }

            public long readLong()
            {
/* 296*/        return input.readLong();
                IOException ioexception;
/* 297*/        ioexception;
/* 298*/        throw new IllegalStateException(ioexception);
            }

            public float readFloat()
            {
/* 304*/        return input.readFloat();
                IOException ioexception;
/* 305*/        ioexception;
/* 306*/        throw new IllegalStateException(ioexception);
            }

            public double readDouble()
            {
/* 312*/        return input.readDouble();
                IOException ioexception;
/* 313*/        ioexception;
/* 314*/        throw new IllegalStateException(ioexception);
            }

            public String readLine()
            {
/* 320*/        return input.readLine();
                IOException ioexception;
/* 321*/        ioexception;
/* 322*/        throw new IllegalStateException(ioexception);
            }

            public String readUTF()
            {
/* 328*/        return input.readUTF();
                IOException ioexception;
/* 329*/        ioexception;
/* 330*/        throw new IllegalStateException(ioexception);
            }

            final DataInput input;

            Q(ByteArrayInputStream bytearrayinputstream)
            {
/* 209*/        input = new DataInputStream(bytearrayinputstream);
            }
}
