// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   IntegerMemberValue.java

package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;

// Referenced classes of package javassist.bytecode.annotation:
//            MemberValue, AnnotationsWriter, MemberValueVisitor

public class IntegerMemberValue extends MemberValue
{

            public IntegerMemberValue(int i, ConstPool constpool)
            {
/*  40*/        super('I', constpool);
/*  41*/        valueIndex = i;
            }

            public IntegerMemberValue(ConstPool constpool, int i)
            {
/*  56*/        super('I', constpool);
/*  57*/        setValue(i);
            }

            public IntegerMemberValue(ConstPool constpool)
            {
/*  64*/        super('I', constpool);
/*  65*/        setValue(0);
            }

            Object getValue(ClassLoader classloader, ClassPool classpool, Method method)
            {
/*  69*/        return new Integer(getValue());
            }

            Class getType(ClassLoader classloader)
            {
/*  73*/        return Integer.TYPE;
            }

            public int getValue()
            {
/*  80*/        return cp.getIntegerInfo(valueIndex);
            }

            public void setValue(int i)
            {
/*  87*/        valueIndex = cp.addIntegerInfo(i);
            }

            public String toString()
            {
/*  94*/        return Integer.toString(getValue());
            }

            public void write(AnnotationsWriter annotationswriter)
                throws IOException
            {
/* 101*/        annotationswriter.constValueIndex(getValue());
            }

            public void accept(MemberValueVisitor membervaluevisitor)
            {
/* 108*/        membervaluevisitor.visitIntegerMemberValue(this);
            }

            int valueIndex;
}
