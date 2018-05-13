// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EnumMemberValue.java

package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;

// Referenced classes of package javassist.bytecode.annotation:
//            MemberValue, AnnotationsWriter, MemberValueVisitor

public class EnumMemberValue extends MemberValue
{

            public EnumMemberValue(int i, int j, ConstPool constpool)
            {
/*  45*/        super('e', constpool);
/*  46*/        typeIndex = i;
/*  47*/        valueIndex = j;
            }

            public EnumMemberValue(ConstPool constpool)
            {
/*  55*/        super('e', constpool);
/*  56*/        typeIndex = valueIndex = 0;
            }

            Object getValue(ClassLoader classloader, ClassPool classpool, Method method)
                throws ClassNotFoundException
            {
/*  63*/        return getType(classloader).getField(getValue()).get(null);
/*  65*/        JVM INSTR pop ;
/*  66*/        throw new ClassNotFoundException((new StringBuilder()).append(getType()).append(".").append(getValue()).toString());
/*  68*/        JVM INSTR pop ;
/*  69*/        throw new ClassNotFoundException((new StringBuilder()).append(getType()).append(".").append(getValue()).toString());
            }

            Class getType(ClassLoader classloader)
                throws ClassNotFoundException
            {
/*  74*/        return loadClass(classloader, getType());
            }

            public String getType()
            {
/*  83*/        return Descriptor.toClassName(cp.getUtf8Info(typeIndex));
            }

            public void setType(String s)
            {
/*  92*/        typeIndex = cp.addUtf8Info(Descriptor.of(s));
            }

            public String getValue()
            {
/*  99*/        return cp.getUtf8Info(valueIndex);
            }

            public void setValue(String s)
            {
/* 106*/        valueIndex = cp.addUtf8Info(s);
            }

            public String toString()
            {
/* 110*/        return (new StringBuilder()).append(getType()).append(".").append(getValue()).toString();
            }

            public void write(AnnotationsWriter annotationswriter)
                throws IOException
            {
/* 117*/        annotationswriter.enumConstValue(cp.getUtf8Info(typeIndex), getValue());
            }

            public void accept(MemberValueVisitor membervaluevisitor)
            {
/* 124*/        membervaluevisitor.visitEnumMemberValue(this);
            }

            int typeIndex;
            int valueIndex;
}
