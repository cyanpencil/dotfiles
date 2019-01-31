// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ShortMemberValue.java

package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;

// Referenced classes of package javassist.bytecode.annotation:
//            MemberValue, AnnotationsWriter, MemberValueVisitor

public class ShortMemberValue extends MemberValue
{

            public ShortMemberValue(int i, ConstPool constpool)
            {
/*  40*/        super('S', constpool);
/*  41*/        valueIndex = i;
            }

            public ShortMemberValue(short word0, ConstPool constpool)
            {
/*  50*/        super('S', constpool);
/*  51*/        setValue(word0);
            }

            public ShortMemberValue(ConstPool constpool)
            {
/*  58*/        super('S', constpool);
/*  59*/        setValue((short)0);
            }

            Object getValue(ClassLoader classloader, ClassPool classpool, Method method)
            {
/*  63*/        return new Short(getValue());
            }

            Class getType(ClassLoader classloader)
            {
/*  67*/        return Short.TYPE;
            }

            public short getValue()
            {
/*  74*/        return (short)cp.getIntegerInfo(valueIndex);
            }

            public void setValue(short word0)
            {
/*  81*/        valueIndex = cp.addIntegerInfo(word0);
            }

            public String toString()
            {
/*  88*/        return Short.toString(getValue());
            }

            public void write(AnnotationsWriter annotationswriter)
                throws IOException
            {
/*  95*/        annotationswriter.constValueIndex(getValue());
            }

            public void accept(MemberValueVisitor membervaluevisitor)
            {
/* 102*/        membervaluevisitor.visitShortMemberValue(this);
            }

            int valueIndex;
}
