// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassMemberValue.java

package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.*;

// Referenced classes of package javassist.bytecode.annotation:
//            MemberValue, AnnotationsWriter, MemberValueVisitor

public class ClassMemberValue extends MemberValue
{

            public ClassMemberValue(int i, ConstPool constpool)
            {
/*  44*/        super('c', constpool);
/*  45*/        valueIndex = i;
            }

            public ClassMemberValue(String s, ConstPool constpool)
            {
/*  54*/        super('c', constpool);
/*  55*/        setValue(s);
            }

            public ClassMemberValue(ConstPool constpool)
            {
/*  63*/        super('c', constpool);
/*  64*/        setValue("java.lang.Class");
            }

            Object getValue(ClassLoader classloader, ClassPool classpool, Method method)
                throws ClassNotFoundException
            {
/*  69*/        if((classpool = getValue()).equals("void"))
/*  71*/            return Void.TYPE;
/*  72*/        if(classpool.equals("int"))
/*  73*/            return Integer.TYPE;
/*  74*/        if(classpool.equals("byte"))
/*  75*/            return Byte.TYPE;
/*  76*/        if(classpool.equals("long"))
/*  77*/            return Long.TYPE;
/*  78*/        if(classpool.equals("double"))
/*  79*/            return Double.TYPE;
/*  80*/        if(classpool.equals("float"))
/*  81*/            return Float.TYPE;
/*  82*/        if(classpool.equals("char"))
/*  83*/            return Character.TYPE;
/*  84*/        if(classpool.equals("short"))
/*  85*/            return Short.TYPE;
/*  86*/        if(classpool.equals("boolean"))
/*  87*/            return Boolean.TYPE;
/*  89*/        else
/*  89*/            return loadClass(classloader, classpool);
            }

            Class getType(ClassLoader classloader)
                throws ClassNotFoundException
            {
/*  93*/        return loadClass(classloader, "java.lang.Class");
            }

            public String getValue()
            {
/* 102*/        String s = cp.getUtf8Info(valueIndex);
/* 104*/        return SignatureAttribute.toTypeSignature(s).toString();
                BadBytecode badbytecode;
/* 105*/        badbytecode;
/* 106*/        throw new RuntimeException(badbytecode);
            }

            public void setValue(String s)
            {
/* 116*/        s = Descriptor.of(s);
/* 117*/        valueIndex = cp.addUtf8Info(s);
            }

            public String toString()
            {
/* 124*/        return (new StringBuilder()).append(getValue()).append(".class").toString();
            }

            public void write(AnnotationsWriter annotationswriter)
                throws IOException
            {
/* 131*/        annotationswriter.classInfoIndex(cp.getUtf8Info(valueIndex));
            }

            public void accept(MemberValueVisitor membervaluevisitor)
            {
/* 138*/        membervaluevisitor.visitClassMemberValue(this);
            }

            int valueIndex;
}
