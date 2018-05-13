// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SignatureAttribute.java

package javassist.bytecode;

import javassist.CtClass;

// Referenced classes of package javassist.bytecode:
//            Descriptor, SignatureAttribute

public static class <init> extends <init>
{

            public char getDescriptor()
            {
/* 617*/        return descriptor;
            }

            public CtClass getCtlass()
            {
/* 624*/        return Descriptor.toPrimitiveClass(descriptor);
            }

            public String toString()
            {
/* 631*/        return Descriptor.toClassName(Character.toString(descriptor));
            }

            void encode(StringBuffer stringbuffer)
            {
/* 635*/        stringbuffer.append(descriptor);
            }

            char descriptor;

            (char c)
            {
/* 601*/        descriptor = c;
            }

            public descriptor(String s)
            {
/* 609*/        this(Descriptor.of(s).charAt(0));
            }
}
