// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LongMemberValue.java

package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;

// Referenced classes of package javassist.bytecode.annotation:
//            MemberValue, AnnotationsWriter, MemberValueVisitor

public class LongMemberValue extends MemberValue
{

            public LongMemberValue(int i, ConstPool constpool)
            {
/*  40*/        super('J', constpool);
/*  41*/        valueIndex = i;
            }

            public LongMemberValue(long l, ConstPool constpool)
            {
/*  50*/        super('J', constpool);
/*  51*/        setValue(l);
            }

            public LongMemberValue(ConstPool constpool)
            {
/*  58*/        super('J', constpool);
/*  59*/        setValue(0L);
            }

            Object getValue(ClassLoader classloader, ClassPool classpool, Method method)
            {
/*  63*/        return new Long(getValue());
            }

            Class getType(ClassLoader classloader)
            {
/*  67*/        return Long.TYPE;
            }

            public long getValue()
            {
/*  74*/        return cp.getLongInfo(valueIndex);
            }

            public void setValue(long l)
            {
/*  81*/        valueIndex = cp.addLongInfo(l);
            }

            public String toString()
            {
/*  88*/        return Long.toString(getValue());
            }

            public void write(AnnotationsWriter annotationswriter)
                throws IOException
            {
/*  95*/        annotationswriter.constValueIndex(getValue());
            }

            public void accept(MemberValueVisitor membervaluevisitor)
            {
/* 102*/        membervaluevisitor.visitLongMemberValue(this);
            }

            int valueIndex;
}
