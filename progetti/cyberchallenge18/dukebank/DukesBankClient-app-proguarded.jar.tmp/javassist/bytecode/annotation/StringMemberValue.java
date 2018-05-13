// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StringMemberValue.java

package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;

// Referenced classes of package javassist.bytecode.annotation:
//            MemberValue, AnnotationsWriter, MemberValueVisitor

public class StringMemberValue extends MemberValue
{

            public StringMemberValue(int i, ConstPool constpool)
            {
/*  40*/        super('s', constpool);
/*  41*/        valueIndex = i;
            }

            public StringMemberValue(String s, ConstPool constpool)
            {
/*  50*/        super('s', constpool);
/*  51*/        setValue(s);
            }

            public StringMemberValue(ConstPool constpool)
            {
/*  58*/        super('s', constpool);
/*  59*/        setValue("");
            }

            Object getValue(ClassLoader classloader, ClassPool classpool, Method method)
            {
/*  63*/        return getValue();
            }

            Class getType(ClassLoader classloader)
            {
/*  67*/        return java/lang/String;
            }

            public String getValue()
            {
/*  74*/        return cp.getUtf8Info(valueIndex);
            }

            public void setValue(String s)
            {
/*  81*/        valueIndex = cp.addUtf8Info(s);
            }

            public String toString()
            {
/*  88*/        return (new StringBuilder("\"")).append(getValue()).append("\"").toString();
            }

            public void write(AnnotationsWriter annotationswriter)
                throws IOException
            {
/*  95*/        annotationswriter.constValueIndex(getValue());
            }

            public void accept(MemberValueVisitor membervaluevisitor)
            {
/* 102*/        membervaluevisitor.visitStringMemberValue(this);
            }

            int valueIndex;
}
