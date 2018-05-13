// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SignatureAttribute.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            SignatureAttribute

public static abstract class  extends 
{

            public String encode()
            {
/* 648*/        StringBuffer stringbuffer = new StringBuffer();
/* 649*/        encode(stringbuffer);
/* 650*/        return stringbuffer.toString();
            }

            public ()
            {
            }
}
