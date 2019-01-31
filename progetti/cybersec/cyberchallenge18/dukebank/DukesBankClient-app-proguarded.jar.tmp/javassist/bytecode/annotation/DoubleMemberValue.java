// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DoubleMemberValue.java

package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;

// Referenced classes of package javassist.bytecode.annotation:
//            MemberValue, AnnotationsWriter, MemberValueVisitor

public class DoubleMemberValue extends MemberValue
{

            public DoubleMemberValue(int i, ConstPool constpool)
            {
/*  41*/        super('D', constpool);
/*  42*/        valueIndex = i;
            }

            public DoubleMemberValue(double d, ConstPool constpool)
            {
/*  51*/        super('D', constpool);
/*  52*/        setValue(d);
            }

            public DoubleMemberValue(ConstPool constpool)
            {
/*  59*/        super('D', constpool);
/*  60*/        setValue(0.0D);
            }

            Object getValue(ClassLoader classloader, ClassPool classpool, Method method)
            {
/*  64*/        return new Double(getValue());
            }

            Class getType(ClassLoader classloader)
            {
/*  68*/        return Double.TYPE;
            }

            public double getValue()
            {
/*  75*/        return cp.getDoubleInfo(valueIndex);
            }

            public void setValue(double d)
            {
/*  82*/        valueIndex = cp.addDoubleInfo(d);
            }

            public String toString()
            {
/*  89*/        return Double.toString(getValue());
            }

            public void write(AnnotationsWriter annotationswriter)
                throws IOException
            {
/*  96*/        annotationswriter.constValueIndex(getValue());
            }

            public void accept(MemberValueVisitor membervaluevisitor)
            {
/* 103*/        membervaluevisitor.visitDoubleMemberValue(this);
            }

            int valueIndex;
}
