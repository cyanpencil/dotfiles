// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Loader.java

package javassist;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package javassist:
//            CannotCompileException, ClassPool, ClassPoolTail, CtClass, 
//            NotFoundException, Translator

public class Loader extends ClassLoader
{

            public Loader()
            {
/* 160*/        this(null);
            }

            public Loader(ClassPool classpool)
            {
/* 154*/        doDelegation = true;
/* 169*/        init(classpool);
            }

            public Loader(ClassLoader classloader, ClassPool classpool)
            {
/* 180*/        super(classloader);
/* 154*/        doDelegation = true;
/* 181*/        init(classpool);
            }

            private void init(ClassPool classpool)
            {
/* 185*/        notDefinedHere = new Hashtable();
/* 186*/        notDefinedPackages = new Vector();
/* 187*/        source = classpool;
/* 188*/        translator = null;
/* 189*/        domain = null;
/* 190*/        delegateLoadingOf("javassist.Loader");
            }

            public void delegateLoadingOf(String s)
            {
/* 202*/        if(s.endsWith("."))
                {
/* 203*/            notDefinedPackages.addElement(s);
/* 203*/            return;
                } else
                {
/* 205*/            notDefinedHere.put(s, this);
/* 206*/            return;
                }
            }

            public void setDomain(ProtectionDomain protectiondomain)
            {
/* 215*/        domain = protectiondomain;
            }

            public void setClassPool(ClassPool classpool)
            {
/* 222*/        source = classpool;
            }

            public void addTranslator(ClassPool classpool, Translator translator1)
                throws NotFoundException, CannotCompileException
            {
/* 236*/        source = classpool;
/* 237*/        translator = translator1;
/* 238*/        translator1.start(classpool);
            }

            public static void main(String args[])
                throws Throwable
            {
                Loader loader;
/* 257*/        (loader = new Loader()).run(args);
            }

            public void run(String as[])
                throws Throwable
            {
                int i;
/* 272*/        if((i = as.length - 1) >= 0)
                {
/* 274*/            String as1[] = new String[i];
/* 275*/            for(int j = 0; j < i; j++)
/* 276*/                as1[j] = as[j + 1];

/* 278*/            run(as[0], as1);
                }
            }

            public void run(String s, String as[])
                throws Throwable
            {
/* 289*/        s = loadClass(s);
/* 291*/        s.getDeclaredMethod("main", new Class[] {
/* 291*/            [Ljava/lang/String;
                }).invoke(null, new Object[] {
/* 291*/            as
                });
/* 297*/        return;
/* 295*/        JVM INSTR dup ;
/* 296*/        s;
/* 296*/        getTargetException();
/* 296*/        throw ;
            }

            protected Class loadClass(String s, boolean flag)
                throws ClassFormatError, ClassNotFoundException
            {
/* 305*/        String s1 = s = s.intern();
/* 306*/        JVM INSTR monitorenter ;
                Class class1;
/* 307*/        if((class1 = findLoadedClass(s)) == null)
/* 309*/            class1 = loadClassByDelegation(s);
/* 311*/        if(class1 == null)
/* 312*/            class1 = findClass(s);
/* 314*/        if(class1 == null)
/* 315*/            class1 = delegateToParent(s);
/* 317*/        if(flag)
/* 318*/            resolveClass(class1);
/* 320*/        return class1;
/* 321*/        s;
/* 321*/        throw s;
            }

            protected Class findClass(String s)
                throws ClassNotFoundException
            {
/* 339*/        if(source == null)
/* 340*/            break MISSING_BLOCK_LABEL_46;
/* 340*/        if(translator != null)
/* 341*/            translator.onLoad(source, s);
                byte abyte0[];
/* 344*/        try
                {
/* 344*/            abyte0 = source.get(s).toBytecode();
                }
/* 346*/        catch(NotFoundException _ex)
                {
/* 347*/            return null;
                }
/* 351*/        break MISSING_BLOCK_LABEL_124;
/* 351*/        Object obj = (new StringBuilder("/")).append(s.replace('.', '/')).append(".class").toString();
/* 352*/        if((obj = getClass().getResourceAsStream(((String) (obj)))) == null)
/* 354*/            return null;
/* 356*/        try
                {
/* 356*/            abyte0 = ClassPoolTail.readStream(((InputStream) (obj)));
                }
/* 359*/        catch(Exception exception)
                {
/* 360*/            throw new ClassNotFoundException((new StringBuilder("caught an exception while obtaining a class file for ")).append(s).toString(), exception);
                }
                int i;
/* 365*/        if((i = s.lastIndexOf('.')) != -1)
                {
/* 367*/            String s1 = s.substring(0, i);
/* 368*/            if(getPackage(s1) == null)
/* 370*/                try
                        {
/* 370*/                    definePackage(s1, null, null, null, null, null, null, null);
                        }
/* 373*/                catch(IllegalArgumentException _ex) { }
                }
/* 379*/        if(domain == null)
/* 380*/            return defineClass(s, abyte0, 0, abyte0.length);
/* 382*/        else
/* 382*/            return defineClass(s, abyte0, 0, abyte0.length, domain);
            }

            protected Class loadClassByDelegation(String s)
                throws ClassNotFoundException
            {
/* 398*/        Class class1 = null;
/* 399*/        if(doDelegation && (s.startsWith("java.") || s.startsWith("javax.") || s.startsWith("sun.") || s.startsWith("com.sun.") || s.startsWith("org.w3c.") || s.startsWith("org.xml.") || notDelegated(s)))
/* 407*/            class1 = delegateToParent(s);
/* 409*/        return class1;
            }

            private boolean notDelegated(String s)
            {
/* 413*/        if(notDefinedHere.get(s) != null)
/* 414*/            return true;
/* 416*/        int i = notDefinedPackages.size();
/* 417*/        for(int j = 0; j < i; j++)
/* 418*/            if(s.startsWith((String)notDefinedPackages.elementAt(j)))
/* 419*/                return true;

/* 421*/        return false;
            }

            protected Class delegateToParent(String s)
                throws ClassNotFoundException
            {
                ClassLoader classloader;
/* 427*/        if((classloader = getParent()) != null)
/* 429*/            return classloader.loadClass(s);
/* 431*/        else
/* 431*/            return findSystemClass(s);
            }

            private Hashtable notDefinedHere;
            private Vector notDefinedPackages;
            private ClassPool source;
            private Translator translator;
            private ProtectionDomain domain;
            public boolean doDelegation;
}
