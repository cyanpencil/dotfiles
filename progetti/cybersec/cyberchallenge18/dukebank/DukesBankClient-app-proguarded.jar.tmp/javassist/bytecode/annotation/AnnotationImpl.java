// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AnnotationImpl.java

package javassist.bytecode.annotation;

import java.lang.reflect.*;
import javassist.*;
import javassist.bytecode.*;

// Referenced classes of package javassist.bytecode.annotation:
//            Annotation, MemberValue

public class AnnotationImpl
    implements InvocationHandler
{

            public static Object make(ClassLoader classloader, Class class1, ClassPool classpool, Annotation annotation1)
            {
/*  71*/        classpool = new AnnotationImpl(annotation1, classpool, classloader);
/*  72*/        return Proxy.newProxyInstance(classloader, new Class[] {
/*  72*/            class1
                }, classpool);
            }

            private AnnotationImpl(Annotation annotation1, ClassPool classpool, ClassLoader classloader)
            {
/*  46*/        cachedHashCode = 0x80000000;
/*  76*/        annotation = annotation1;
/*  77*/        pool = classpool;
/*  78*/        classLoader = classloader;
            }

            public String getTypeName()
            {
/*  87*/        return annotation.getTypeName();
            }

            private Class getAnnotationType()
            {
/*  97*/        if(annotationType == null)
                {
/*  98*/            Object obj = annotation.getTypeName();
/* 100*/            try
                    {
/* 100*/                annotationType = classLoader.loadClass(((String) (obj)));
                    }
/* 102*/            catch(ClassNotFoundException classnotfoundexception)
                    {
/* 103*/                ((NoClassDefFoundError) (obj = new NoClassDefFoundError((new StringBuilder("Error loading annotation class: ")).append(((String) (obj))).toString()))).setStackTrace(classnotfoundexception.getStackTrace());
/* 105*/                throw obj;
                    }
                }
/* 108*/        return annotationType;
            }

            public Annotation getAnnotation()
            {
/* 117*/        return annotation;
            }

            public Object invoke(Object obj, Method method, Object aobj[])
                throws Throwable
            {
/* 130*/        obj = method.getName();
/* 131*/        if(java/lang/Object == method.getDeclaringClass())
                {
/* 132*/            if("equals".equals(obj))
                    {
/* 133*/                aobj = ((Object []) (aobj[0]));
/* 134*/                return new Boolean(checkEquals(((Object) (aobj))));
                    }
/* 136*/            if("toString".equals(obj))
/* 137*/                return annotation.toString();
/* 138*/            if("hashCode".equals(obj))
/* 139*/                return new Integer(hashCode());
                } else
/* 141*/        if("annotationType".equals(obj) && method.getParameterTypes().length == 0)
/* 143*/            return getAnnotationType();
/* 145*/        if((aobj = annotation.getMemberValue(((String) (obj)))) == null)
/* 147*/            return getDefault(((String) (obj)), method);
/* 149*/        else
/* 149*/            return ((MemberValue) (aobj)).getValue(classLoader, pool, method);
            }

            private Object getDefault(String s, Method method)
                throws ClassNotFoundException, RuntimeException
            {
                String s1;
/* 155*/        s1 = annotation.getTypeName();
/* 156*/        if(pool == null)
/* 158*/            break MISSING_BLOCK_LABEL_106;
                Object obj;
/* 158*/        if((obj = ((ClassFile) (obj = ((CtClass) (obj = pool.get(s1))).getClassFile2())).getMethod(s)) != null && (obj = (AnnotationDefaultAttribute)((MethodInfo) (obj)).getAttribute("AnnotationDefault")) != null)
/* 166*/            return (s = ((AnnotationDefaultAttribute) (obj)).getDefaultValue()).getValue(classLoader, pool, method);
/* 174*/        break MISSING_BLOCK_LABEL_106;
/* 171*/        JVM INSTR pop ;
/* 172*/        throw new RuntimeException((new StringBuilder("cannot find a class file: ")).append(s1).toString());
/* 177*/        throw new RuntimeException((new StringBuilder("no default value: ")).append(s1).append(".").append(s).append("()").toString());
            }

            public int hashCode()
            {
                int i;
                Method amethod[];
                int j;
/* 185*/        if(cachedHashCode != 0x80000000)
/* 186*/            break MISSING_BLOCK_LABEL_202;
/* 186*/        i = 0;
/* 189*/        getAnnotationType();
/* 191*/        amethod = annotationType.getDeclaredMethods();
/* 192*/        j = 0;
_L3:
/* 192*/        if(j >= amethod.length) goto _L2; else goto _L1
_L1:
                String s;
                int k;
                MemberValue membervalue;
                Object obj;
/* 193*/        s = amethod[j].getName();
/* 194*/        k = 0;
/* 197*/        membervalue = annotation.getMemberValue(s);
/* 198*/        obj = null;
/* 200*/        if(membervalue != null)
/* 201*/            obj = membervalue.getValue(classLoader, pool, amethod[j]);
/* 202*/        if(obj == null)
/* 203*/            obj = getDefault(s, amethod[j]);
/* 210*/        break MISSING_BLOCK_LABEL_144;
/* 205*/        JVM INSTR dup ;
/* 206*/        i;
/* 206*/        throw ;
/* 208*/        i;
/* 209*/        throw new RuntimeException((new StringBuilder("Error retrieving value ")).append(s).append(" for annotation ").append(annotation.getTypeName()).toString(), i);
/* 213*/        if(obj != null)
/* 214*/            if(obj.getClass().isArray())
/* 215*/                k = arrayHashCode(obj);
/* 217*/            else
/* 217*/                k = obj.hashCode();
/* 219*/        i += 127 * s.hashCode() ^ k;
/* 192*/        j++;
                  goto _L3
_L2:
/* 222*/        cachedHashCode = i;
/* 224*/        return cachedHashCode;
            }

            private boolean checkEquals(Object obj)
                throws Exception
            {
                Method amethod[];
                int i;
/* 235*/        if(obj == null)
/* 236*/            return false;
                Object obj1;
/* 239*/        if((obj instanceof Proxy) && ((obj1 = Proxy.getInvocationHandler(obj)) instanceof AnnotationImpl))
                {
/* 242*/            obj1 = (AnnotationImpl)obj1;
/* 243*/            return annotation.equals(((AnnotationImpl) (obj1)).annotation);
                }
/* 247*/        obj1 = (Class)JDK_ANNOTATION_TYPE_METHOD.invoke(obj, null);
/* 248*/        if(!getAnnotationType().equals(obj1))
/* 249*/            return false;
/* 251*/        amethod = annotationType.getDeclaredMethods();
/* 252*/        i = 0;
_L2:
                String s;
                Object obj2;
                Object obj3;
/* 252*/        if(i >= amethod.length)
/* 253*/            break; /* Loop/switch isn't completed */
/* 253*/        s = amethod[i].getName();
/* 256*/        obj2 = annotation.getMemberValue(s);
/* 257*/        obj3 = null;
/* 260*/        if(obj2 != null)
/* 261*/            obj3 = ((MemberValue) (obj2)).getValue(classLoader, pool, amethod[i]);
/* 262*/        if(obj3 == null)
/* 263*/            obj3 = getDefault(s, amethod[i]);
/* 264*/        obj2 = amethod[i].invoke(obj, null);
/* 271*/        break MISSING_BLOCK_LABEL_202;
/* 266*/        JVM INSTR dup ;
/* 267*/        obj;
/* 267*/        throw ;
/* 269*/        obj;
/* 270*/        throw new RuntimeException((new StringBuilder("Error retrieving value ")).append(s).append(" for annotation ").append(annotation.getTypeName()).toString(), ((Throwable) (obj)));
/* 273*/        if(obj3 == null && obj2 != null)
/* 274*/            return false;
/* 275*/        if(obj3 != null && !obj3.equals(obj2))
/* 276*/            return false;
/* 252*/        i++;
/* 252*/        if(true) goto _L2; else goto _L1
_L1:
/* 279*/        return true;
            }

            private static int arrayHashCode(Object obj)
            {
/* 291*/        if(obj == null)
/* 292*/            return 0;
/* 294*/        int i = 1;
/* 296*/        obj = ((Object) ((Object[])obj));
/* 297*/        for(int j = 0; j < obj.length; j++)
                {
/* 298*/            int k = 0;
/* 299*/            if(obj[j] != null)
/* 300*/                k = obj[j].hashCode();
/* 301*/            i = i * 31 + k;
                }

/* 303*/        return i;
            }

            private static final String JDK_ANNOTATION_CLASS_NAME = "java.lang.annotation.Annotation";
            private static Method JDK_ANNOTATION_TYPE_METHOD = null;
            private Annotation annotation;
            private ClassPool pool;
            private ClassLoader classLoader;
            private transient Class annotationType;
            private transient int cachedHashCode;

            static 
            {
                Class class1;
/*  51*/        try
                {
/*  51*/            JDK_ANNOTATION_TYPE_METHOD = (class1 = Class.forName("java.lang.annotation.Annotation")).getMethod("annotationType", null);
                }
/*  54*/        catch(Exception _ex) { }
            }
}
