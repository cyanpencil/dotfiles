// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassMetaobject.java

package javassist.tools.reflect;

import java.io.*;
import java.lang.reflect.*;
import java.util.Arrays;

// Referenced classes of package javassist.tools.reflect:
//            CannotCreateException, CannotInvokeException

public class ClassMetaobject
    implements Serializable
{

            public ClassMetaobject(String as[])
            {
/*  74*/        try
                {
/*  74*/            javaClass = getClassObject(as[0]);
                }
/*  76*/        catch(ClassNotFoundException classnotfoundexception)
                {
/*  77*/            throw new RuntimeException((new StringBuilder("not found: ")).append(as[0]).append(", useContextClassLoader: ").append(Boolean.toString(useContextClassLoader)).toString(), classnotfoundexception);
                }
/*  82*/        constructors = javaClass.getConstructors();
/*  83*/        methods = null;
            }

            private void writeObject(ObjectOutputStream objectoutputstream)
                throws IOException
            {
/*  87*/        objectoutputstream.writeUTF(javaClass.getName());
            }

            private void readObject(ObjectInputStream objectinputstream)
                throws IOException, ClassNotFoundException
            {
/*  93*/        javaClass = getClassObject(objectinputstream.readUTF());
/*  94*/        constructors = javaClass.getConstructors();
/*  95*/        methods = null;
            }

            private Class getClassObject(String s)
                throws ClassNotFoundException
            {
/*  99*/        if(useContextClassLoader)
/* 100*/            return Thread.currentThread().getContextClassLoader().loadClass(s);
/* 103*/        else
/* 103*/            return Class.forName(s);
            }

            public final Class getJavaClass()
            {
/* 110*/        return javaClass;
            }

            public final String getName()
            {
/* 117*/        return javaClass.getName();
            }

            public final boolean isInstance(Object obj)
            {
/* 124*/        return javaClass.isInstance(obj);
            }

            public final Object newInstance(Object aobj[])
                throws CannotCreateException
            {
                int i;
                int j;
/* 135*/        i = constructors.length;
/* 136*/        j = 0;
_L2:
/* 136*/        if(j >= i)
/* 138*/            break; /* Loop/switch isn't completed */
/* 138*/        return constructors[j].newInstance(aobj);
/* 140*/        JVM INSTR pop ;
/* 151*/        break MISSING_BLOCK_LABEL_58;
/* 143*/        aobj;
/* 144*/        throw new CannotCreateException(((Exception) (aobj)));
/* 146*/        aobj;
/* 147*/        throw new CannotCreateException(((Exception) (aobj)));
/* 149*/        aobj;
/* 150*/        throw new CannotCreateException(((Exception) (aobj)));
/* 136*/        j++;
/* 136*/        if(true) goto _L2; else goto _L1
_L1:
/* 154*/        throw new CannotCreateException("no constructor matches");
            }

            public Object trapFieldRead(String s)
            {
/* 165*/        Class class1 = getJavaClass();
/* 167*/        return class1.getField(s).get(null);
/* 169*/        s;
/* 170*/        throw new RuntimeException(s.toString());
/* 172*/        s;
/* 173*/        throw new RuntimeException(s.toString());
            }

            public void trapFieldWrite(String s, Object obj)
            {
/* 185*/        Class class1 = getJavaClass();
/* 187*/        try
                {
/* 187*/            class1.getField(s).set(null, obj);
/* 194*/            return;
                }
                // Misplaced declaration of an exception variable
/* 189*/        catch(String s)
                {
/* 190*/            throw new RuntimeException(s.toString());
                }
                // Misplaced declaration of an exception variable
/* 192*/        catch(String s)
                {
/* 193*/            throw new RuntimeException(s.toString());
                }
            }

            public static Object invoke(Object obj, int i, Object aobj[])
                throws Throwable
            {
                Method amethod[];
                int j;
                int k;
/* 206*/        j = (amethod = obj.getClass().getMethods()).length;
/* 208*/        i = (new StringBuilder("_m_")).append(i).toString();
/* 209*/        k = 0;
_L2:
/* 209*/        if(k >= j)
/* 210*/            break; /* Loop/switch isn't completed */
/* 210*/        if(!amethod[k].getName().startsWith(i))
/* 212*/            break MISSING_BLOCK_LABEL_79;
/* 212*/        return amethod[k].invoke(obj, aobj);
/* 213*/        JVM INSTR dup ;
/* 214*/        obj;
/* 214*/        getTargetException();
/* 214*/        throw ;
/* 215*/        obj;
/* 216*/        throw new CannotInvokeException(((IllegalAccessException) (obj)));
/* 209*/        k++;
/* 209*/        if(true) goto _L2; else goto _L1
_L1:
/* 220*/        throw new CannotInvokeException("cannot find a method");
            }

            public Object trapMethodcall(int i, Object aobj[])
                throws Throwable
            {
                Method amethod[];
/* 235*/        return (amethod = getReflectiveMethods())[i].invoke(null, aobj);
/* 238*/        JVM INSTR dup ;
                InvocationTargetException invocationtargetexception;
/* 239*/        invocationtargetexception;
/* 239*/        getTargetException();
/* 239*/        throw ;
                IllegalAccessException illegalaccessexception;
/* 241*/        illegalaccessexception;
/* 242*/        throw new CannotInvokeException(illegalaccessexception);
            }

            public final Method[] getReflectiveMethods()
            {
/* 251*/        if(methods != null)
/* 252*/            return methods;
                Class class1;
                Method amethod[];
                int i;
/* 254*/        int ai[] = new int[i = (amethod = (class1 = getJavaClass()).getDeclaredMethods()).length];
/* 258*/        int j = 0;
/* 259*/        for(int k = 0; k < i; k++)
                {
                    Object obj;
/* 260*/            if(!((String) (obj = ((Method) (obj = amethod[k])).getName())).startsWith("_m_"))
/* 263*/                continue;
/* 263*/            int i1 = 0;
/* 264*/            int j1 = 3;
/* 265*/            do
                    {
/* 265*/                char c = ((String) (obj)).charAt(j1);
/* 266*/                if('0' > c || c > '9')
/* 267*/                    break;
/* 267*/                i1 = (i1 * 10 + c) - 48;
/* 264*/                j1++;
                    } while(true);
/* 272*/            ai[k] = ++i1;
/* 273*/            if(i1 > j)
/* 274*/                j = i1;
                }

/* 278*/        methods = new Method[j];
/* 279*/        for(int l = 0; l < i; l++)
/* 280*/            if(ai[l] > 0)
/* 281*/                methods[ai[l] - 1] = amethod[l];

/* 283*/        return methods;
            }

            public final Method getMethod(int i)
            {
/* 299*/        return getReflectiveMethods()[i];
            }

            public final String getMethodName(int i)
            {
/* 307*/        i = getReflectiveMethods()[i].getName();
/* 308*/        int j = 3;
                char c;
/* 310*/        while((c = i.charAt(j++)) >= '0' && '9' >= c) ;
/* 315*/        return i.substring(j);
            }

            public final Class[] getParameterTypes(int i)
            {
/* 324*/        return getReflectiveMethods()[i].getParameterTypes();
            }

            public final Class getReturnType(int i)
            {
/* 332*/        return getReflectiveMethods()[i].getReturnType();
            }

            public final int getMethodIndex(String s, Class aclass[])
                throws NoSuchMethodException
            {
/* 356*/        Method amethod[] = getReflectiveMethods();
/* 357*/        for(int i = 0; i < amethod.length; i++)
/* 358*/            if(amethod[i] != null && getMethodName(i).equals(s) && Arrays.equals(aclass, amethod[i].getParameterTypes()))
/* 364*/                return i;

/* 367*/        throw new NoSuchMethodException((new StringBuilder("Method ")).append(s).append(" not found").toString());
            }

            static final String methodPrefix = "_m_";
            static final int methodPrefixLen = 3;
            private Class javaClass;
            private Constructor constructors[];
            private Method methods[];
            public static boolean useContextClassLoader = false;

}
