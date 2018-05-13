// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FloatMemberValue.java

package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;

// Referenced classes of package javassist.bytecode.annotation:
//            MemberValue, AnnotationsWriter, MemberValueVisitor

public class FloatMemberValue extends MemberValue
{

            public FloatMemberValue(int i, ConstPool constpool)
            {
/*  41*/        super('F', constpool);
/*  42*/        valueIndex = i;
            }

            public FloatMemberValue(float f, ConstPool constpool)
            {
/*  51*/        super('F', constpool);
/*  52*/        setValue(f);
            }

            public FloatMemberValue(ConstPool constpool)
            {
/*  59*/        super('F', constpool);
/*  60*/        setValue(0.0F);
            }

            Object getValue(ClassLoader classloader, ClassPool classpool, Method method)
            {
/*  64*/        return new Float(getValue());
            }

            Class getType(ClassLoader classloader)
            {
/*  68*/        return Float.TYPE;
            }

            public float getValue()
            {
/*  75*/        return cp.getFloatInfo(valueIndex);
            }

            public void setValue(float f)
            {
/*  82*/        valueIndex = cp.addFloatInfo(f);
            }

            public String toString()
            {
/*  89*/        return Float.toString(getValue());
            }

            public void write(AnnotationsWriter annotationswriter)
                throws IOException
            {
/*  96*/        annotationswriter.constValueIndex(getValue());
            }

            public void accept(MemberValueVisitor membervaluevisitor)
            {
/* 103*/        membervaluevisitor.visitFloatMemberValue(this);
            }

            int valueIndex;
}
