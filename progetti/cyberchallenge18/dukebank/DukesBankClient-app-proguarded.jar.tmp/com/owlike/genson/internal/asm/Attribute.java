// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 

package com.owlike.genson.internal.asm;


// Referenced classes of package com.owlike.genson.internal.asm:
//            ByteVector, ClassReader, ClassWriter, Label

public class Attribute
{

    protected Attribute(String s)
    {
        type = s;
    }

    public boolean isUnknown()
    {
        return true;
    }

    public boolean isCodeAttribute()
    {
        return false;
    }

    protected Label[] getLabels()
    {
        return null;
    }

    protected Attribute read(ClassReader classreader, int i, int j, char ac[], int k, Label alabel[])
    {
        (ac = new Attribute(type)).b = new byte[j];
        System.arraycopy(classreader.b, i, ((Attribute) (ac)).b, 0, j);
        return ac;
    }

    protected ByteVector write(ClassWriter classwriter, byte abyte0[], int i, int j, int k)
    {
        (classwriter = new ByteVector()).a = b;
        classwriter.b = b.length;
        return classwriter;
    }

    final int a()
    {
        int i = 0;
        for(Attribute attribute = this; attribute != null; attribute = attribute.a)
            i++;

        return i;
    }

    final int a(ClassWriter classwriter, byte abyte0[], int i, int j, int k)
    {
        Attribute attribute = this;
        int l = 0;
        for(; attribute != null; attribute = attribute.a)
        {
            classwriter.newUTF8(attribute.type);
            l += attribute.write(classwriter, abyte0, i, j, k).b + 6;
        }

        return l;
    }

    final void a(ClassWriter classwriter, byte abyte0[], int i, int j, int k, ByteVector bytevector)
    {
        for(Attribute attribute = this; attribute != null; attribute = attribute.a)
        {
            ByteVector bytevector1 = attribute.write(classwriter, abyte0, i, j, k);
            bytevector.putShort(classwriter.newUTF8(attribute.type)).putInt(bytevector1.b);
            bytevector.putByteArray(bytevector1.a, 0, bytevector1.b);
        }

    }

    public final String type;
    byte b[];
    Attribute a;
}
