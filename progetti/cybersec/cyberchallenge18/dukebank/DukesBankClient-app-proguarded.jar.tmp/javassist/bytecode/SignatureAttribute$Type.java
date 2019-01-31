// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SignatureAttribute.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            SignatureAttribute

public static abstract class 
{

            abstract void encode(StringBuffer stringbuffer);

            static void toString(StringBuffer stringbuffer,  a[])
            {
/* 587*/        for(int i = 0; i < a.length; i++)
                {
/* 588*/            if(i > 0)
/* 589*/                stringbuffer.append(", ");
/* 591*/            stringbuffer.append(a[i]);
                }

            }

            public ()
            {
            }
}
