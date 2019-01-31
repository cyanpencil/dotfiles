// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BooleanMemberValue.java

package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;

// Referenced classes of package javassist.bytecode.annotation:
//            MemberValue, AnnotationsWriter, MemberValueVisitor

public class BooleanMemberValue extends MemberValue
{

            public BooleanMemberValue(int i, ConstPool constpool)
            {
/*  39*/        super('Z', constpool);
/*  40*/        valueIndex = i;
            }

            public BooleanMemberValue(boolean flag, ConstPool constpool)
            {
/*  49*/        super('Z', constpool);
/*  50*/        setValue(flag);
            }

            public BooleanMemberValue(ConstPool constpool)
            {
/*  57*/        super('Z', constpool);
/*  58*/        setValue(false);
            }

            Object getValue(ClassLoader classloader, ClassPool classpool, Method method)
            {
/*  62*/        return new Boolean(getValue());
            }

            Class getType(ClassLoader classloader)
            {
/*  66*/        return Boolean.TYPE;
            }

            public boolean getValue()
            {
/*  73*/        return cp.getIntegerInfo(valueIndex) != 0;
            }

            public void setValue(boolean flag)
            {
/*  80*/        valueIndex = cp.addIntegerInfo(flag ? 1 : 0);
            }

            public String toString()
            {
/*  87*/        if(getValue())
/*  87*/            return "true";
/*  87*/        else
/*  87*/            return "false";
            }

            public void write(AnnotationsWriter annotationswriter)
                throws IOException
            {
/*  94*/        annotationswriter.constValueIndex(getValue());
            }

            public void accept(MemberValueVisitor membervaluevisitor)
            {
/* 101*/        membervaluevisitor.visitBooleanMemberValue(this);
            }

            int valueIndex;
}
