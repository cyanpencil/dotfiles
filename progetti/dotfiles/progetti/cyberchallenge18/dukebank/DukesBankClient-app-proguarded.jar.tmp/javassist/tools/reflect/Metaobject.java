// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Metaobject.java

package javassist.tools.reflect;

import java.io.*;
import java.lang.reflect.*;

// Referenced classes of package javassist.tools.reflect:
//            CannotInvokeException, ClassMetaobject, Metalevel

public class Metaobject
    implements Serializable
{

            public Metaobject(Object obj, Object aobj[])
            {
/*  61*/        baseobject = (Metalevel)obj;
/*  62*/        classmetaobject = baseobject._getClass();
/*  63*/        methods = classmetaobject.getReflectiveMethods();
            }

            protected Metaobject()
            {
/*  72*/        baseobject = null;
/*  73*/        classmetaobject = null;
/*  74*/        methods = null;
            }

            private void writeObject(ObjectOutputStream objectoutputstream)
                throws IOException
            {
/*  78*/        objectoutputstream.writeObject(baseobject);
            }

            private void readObject(ObjectInputStream objectinputstream)
                throws IOException, ClassNotFoundException
            {
/*  84*/        baseobject = (Metalevel)objectinputstream.readObject();
/*  85*/        classmetaobject = baseobject._getClass();
/*  86*/        methods = classmetaobject.getReflectiveMethods();
            }

            public final ClassMetaobject getClassMetaobject()
            {
/*  95*/        return classmetaobject;
            }

            public final Object getObject()
            {
/* 102*/        return baseobject;
            }

            public final void setObject(Object obj)
            {
/* 111*/        baseobject = (Metalevel)obj;
/* 112*/        classmetaobject = baseobject._getClass();
/* 113*/        methods = classmetaobject.getReflectiveMethods();
/* 116*/        baseobject._setMetaobject(this);
            }

            public final String getMethodName(int i)
            {
/* 124*/        i = methods[i].getName();
/* 125*/        int j = 3;
                char c;
/* 127*/        while((c = i.charAt(j++)) >= '0' && '9' >= c) ;
/* 132*/        return i.substring(j);
            }

            public final Class[] getParameterTypes(int i)
            {
/* 141*/        return methods[i].getParameterTypes();
            }

            public final Class getReturnType(int i)
            {
/* 149*/        return methods[i].getReturnType();
            }

            public Object trapFieldRead(String s)
            {
/* 160*/        Class class1 = getClassMetaobject().getJavaClass();
/* 162*/        return class1.getField(s).get(getObject());
/* 164*/        s;
/* 165*/        throw new RuntimeException(s.toString());
/* 167*/        s;
/* 168*/        throw new RuntimeException(s.toString());
            }

            public void trapFieldWrite(String s, Object obj)
            {
/* 180*/        Class class1 = getClassMetaobject().getJavaClass();
/* 182*/        try
                {
/* 182*/            class1.getField(s).set(getObject(), obj);
/* 189*/            return;
                }
                // Misplaced declaration of an exception variable
/* 184*/        catch(String s)
                {
/* 185*/            throw new RuntimeException(s.toString());
                }
                // Misplaced declaration of an exception variable
/* 187*/        catch(String s)
                {
/* 188*/            throw new RuntimeException(s.toString());
                }
            }

            public Object trapMethodcall(int i, Object aobj[])
                throws Throwable
            {
/* 228*/        return methods[i].invoke(getObject(), aobj);
/* 230*/        JVM INSTR dup ;
/* 231*/        i;
/* 231*/        getTargetException();
/* 231*/        throw ;
/* 233*/        i;
/* 234*/        throw new CannotInvokeException(i);
            }

            protected ClassMetaobject classmetaobject;
            protected Metalevel baseobject;
            protected Method methods[];
}
