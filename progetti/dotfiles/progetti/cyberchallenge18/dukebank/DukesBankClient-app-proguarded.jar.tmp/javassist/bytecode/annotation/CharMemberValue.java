// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CharMemberValue.java

package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;

// Referenced classes of package javassist.bytecode.annotation:
//            MemberValue, AnnotationsWriter, MemberValueVisitor

public class CharMemberValue extends MemberValue
{

            public CharMemberValue(int i, ConstPool constpool)
            {
/*  40*/        super('C', constpool);
/*  41*/        valueIndex = i;
            }

            public CharMemberValue(char c, ConstPool constpool)
            {
/*  50*/        super('C', constpool);
/*  51*/        setValue(c);
            }

            public CharMemberValue(ConstPool constpool)
            {
/*  58*/        super('C', constpool);
/*  59*/        setValue('\0');
            }

            Object getValue(ClassLoader classloader, ClassPool classpool, Method method)
            {
/*  63*/        return new Character(getValue());
            }

            Class getType(ClassLoader classloader)
            {
/*  67*/        return Character.TYPE;
            }

            public char getValue()
            {
/*  74*/        return (char)cp.getIntegerInfo(valueIndex);
            }

            public void setValue(char c)
            {
/*  81*/        valueIndex = cp.addIntegerInfo(c);
            }

            public String toString()
            {
/*  88*/        return Character.toString(getValue());
            }

            public void write(AnnotationsWriter annotationswriter)
                throws IOException
            {
/*  95*/        annotationswriter.constValueIndex(getValue());
            }

            public void accept(MemberValueVisitor membervaluevisitor)
            {
/* 102*/        membervaluevisitor.visitCharMemberValue(this);
            }

            int valueIndex;
}
