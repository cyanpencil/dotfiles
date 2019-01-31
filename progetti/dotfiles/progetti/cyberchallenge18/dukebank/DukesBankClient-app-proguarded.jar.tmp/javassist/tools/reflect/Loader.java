// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Loader.java

package javassist.tools.reflect;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.NotFoundException;

// Referenced classes of package javassist.tools.reflect:
//            Reflection

public class Loader extends javassist.Loader
{

            public static void main(String args[])
                throws Throwable
            {
                Loader loader;
/* 125*/        (loader = new Loader()).run(args);
            }

            public Loader()
                throws CannotCompileException, NotFoundException
            {
/* 134*/        delegateLoadingOf("javassist.tools.reflect.Loader");
/* 136*/        reflection = new Reflection();
/* 137*/        ClassPool classpool = ClassPool.getDefault();
/* 138*/        addTranslator(classpool, reflection);
            }

            public boolean makeReflective(String s, String s1, String s2)
                throws CannotCompileException, NotFoundException
            {
/* 162*/        return reflection.makeReflective(s, s1, s2);
            }

            protected Reflection reflection;
}
