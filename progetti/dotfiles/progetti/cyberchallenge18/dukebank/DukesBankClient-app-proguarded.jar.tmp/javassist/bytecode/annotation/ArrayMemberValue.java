// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ArrayMemberValue.java

package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;

// Referenced classes of package javassist.bytecode.annotation:
//            MemberValue, AnnotationsWriter, MemberValueVisitor

public class ArrayMemberValue extends MemberValue
{

            public ArrayMemberValue(ConstPool constpool)
            {
/*  38*/        super('[', constpool);
/*  39*/        type = null;
/*  40*/        values = null;
            }

            public ArrayMemberValue(MemberValue membervalue, ConstPool constpool)
            {
/*  49*/        super('[', constpool);
/*  50*/        type = membervalue;
/*  51*/        values = null;
            }

            Object getValue(ClassLoader classloader, ClassPool classpool, Method method)
                throws ClassNotFoundException
            {
/*  57*/        if(values == null)
/*  58*/            throw new ClassNotFoundException((new StringBuilder("no array elements found: ")).append(method.getName()).toString());
/*  61*/        int i = values.length;
                Object obj;
/*  63*/        if(type == null)
                {
/*  64*/            if((obj = method.getReturnType().getComponentType()) == null || i > 0)
/*  66*/                throw new ClassNotFoundException((new StringBuilder("broken array type: ")).append(method.getName()).toString());
                } else
                {
/*  70*/            obj = type.getType(classloader);
                }
/*  72*/        obj = Array.newInstance(((Class) (obj)), i);
/*  73*/        for(int j = 0; j < i; j++)
/*  74*/            Array.set(obj, j, values[j].getValue(classloader, classpool, method));

/*  76*/        return obj;
            }

            Class getType(ClassLoader classloader)
                throws ClassNotFoundException
            {
/*  80*/        if(type == null)
/*  81*/            throw new ClassNotFoundException("no array type specified");
/*  83*/        else
/*  83*/            return (classloader = ((ClassLoader) (Array.newInstance(type.getType(classloader), 0)))).getClass();
            }

            public MemberValue getType()
            {
/*  93*/        return type;
            }

            public MemberValue[] getValue()
            {
/* 100*/        return values;
            }

            public void setValue(MemberValue amembervalue[])
            {
/* 107*/        values = amembervalue;
/* 108*/        if(amembervalue != null && amembervalue.length > 0)
/* 109*/            type = amembervalue[0];
            }

            public String toString()
            {
/* 116*/        StringBuffer stringbuffer = new StringBuffer("{");
/* 117*/        if(values != null)
                {
/* 118*/            for(int i = 0; i < values.length; i++)
                    {
/* 119*/                stringbuffer.append(values[i].toString());
/* 120*/                if(i + 1 < values.length)
/* 121*/                    stringbuffer.append(", ");
                    }

                }
/* 125*/        stringbuffer.append("}");
/* 126*/        return stringbuffer.toString();
            }

            public void write(AnnotationsWriter annotationswriter)
                throws IOException
            {
/* 133*/        int i = values.length;
/* 134*/        annotationswriter.arrayValue(i);
/* 135*/        for(int j = 0; j < i; j++)
/* 136*/            values[j].write(annotationswriter);

            }

            public void accept(MemberValueVisitor membervaluevisitor)
            {
/* 143*/        membervaluevisitor.visitArrayMemberValue(this);
            }

            MemberValue type;
            MemberValue values[];
}
