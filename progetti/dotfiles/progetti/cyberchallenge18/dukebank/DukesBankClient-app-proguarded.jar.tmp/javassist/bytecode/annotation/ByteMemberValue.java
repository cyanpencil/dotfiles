// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ByteMemberValue.java

package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;

// Referenced classes of package javassist.bytecode.annotation:
//            MemberValue, AnnotationsWriter, MemberValueVisitor

public class ByteMemberValue extends MemberValue
{

            public ByteMemberValue(int i, ConstPool constpool)
            {
/*  39*/        super('B', constpool);
/*  40*/        valueIndex = i;
            }

            public ByteMemberValue(byte byte0, ConstPool constpool)
            {
/*  49*/        super('B', constpool);
/*  50*/        setValue(byte0);
            }

            public ByteMemberValue(ConstPool constpool)
            {
/*  57*/        super('B', constpool);
/*  58*/        setValue((byte)0);
            }

            Object getValue(ClassLoader classloader, ClassPool classpool, Method method)
            {
/*  62*/        return new Byte(getValue());
            }

            Class getType(ClassLoader classloader)
            {
/*  66*/        return Byte.TYPE;
            }

            public byte getValue()
            {
/*  73*/        return (byte)cp.getIntegerInfo(valueIndex);
            }

            public void setValue(byte byte0)
            {
/*  80*/        valueIndex = cp.addIntegerInfo(byte0);
            }

            public String toString()
            {
/*  87*/        return Byte.toString(getValue());
            }

            public void write(AnnotationsWriter annotationswriter)
                throws IOException
            {
/*  94*/        annotationswriter.constValueIndex(getValue());
            }

            public void accept(MemberValueVisitor membervaluevisitor)
            {
/* 101*/        membervaluevisitor.visitByteMemberValue(this);
            }

            int valueIndex;
}
