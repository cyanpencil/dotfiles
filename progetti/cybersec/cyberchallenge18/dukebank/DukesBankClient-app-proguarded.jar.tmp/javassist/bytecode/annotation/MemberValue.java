// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MemberValue.java

package javassist.bytecode.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;

// Referenced classes of package javassist.bytecode.annotation:
//            NoSuchClassError, MemberValueVisitor, AnnotationsWriter

public abstract class MemberValue
{

            MemberValue(char c, ConstPool constpool)
            {
/*  39*/        cp = constpool;
/*  40*/        tag = c;
            }

            abstract Object getValue(ClassLoader classloader, ClassPool classpool, Method method)
                throws ClassNotFoundException;

            abstract Class getType(ClassLoader classloader)
                throws ClassNotFoundException;

            static Class loadClass(ClassLoader classloader, String s)
                throws ClassNotFoundException, NoSuchClassError
            {
/*  56*/        return Class.forName(convertFromArray(s), true, classloader);
/*  58*/        classloader;
/*  59*/        throw new NoSuchClassError(s, classloader);
            }

            private static String convertFromArray(String s)
            {
                int i;
/*  65*/        if((i = s.indexOf("[]")) != -1)
                {
/*  67*/            Object obj = s.substring(0, i);
/*  68*/            obj = new StringBuffer(Descriptor.of(((String) (obj))));
/*  69*/            for(; i != -1; i = s.indexOf("[]", i + 1))
/*  70*/                ((StringBuffer) (obj)).insert(0, "[");

/*  73*/            return ((StringBuffer) (obj)).toString().replace('/', '.');
                } else
                {
/*  75*/            return s;
                }
            }

            public abstract void accept(MemberValueVisitor membervaluevisitor);

            public abstract void write(AnnotationsWriter annotationswriter)
                throws IOException;

            ConstPool cp;
            char tag;
}
