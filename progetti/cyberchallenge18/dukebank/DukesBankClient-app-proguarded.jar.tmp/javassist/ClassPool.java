// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassPool.java

package javassist;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.*;
import java.util.*;
import javassist.bytecode.Descriptor;

// Referenced classes of package javassist:
//            CannotCompileException, ClassPoolTail, CtArray, CtClass, 
//            CtClassType, CtNewClass, CtNewNestedClass, NotFoundException, 
//            CtMethod, ClassPath

public class ClassPool
{

            public ClassPool()
            {
/* 162*/        this(((ClassPool) (null)));
            }

            public ClassPool(boolean flag)
            {
/* 175*/        this(((ClassPool) (null)));
/* 176*/        if(flag)
/* 177*/            appendSystemPath();
            }

            public ClassPool(ClassPool classpool)
            {
/* 112*/        childFirstLookup = false;
/* 152*/        cflow = null;
/* 188*/        classes = new Hashtable(191);
/* 189*/        source = new ClassPoolTail();
/* 190*/        parent = classpool;
/* 191*/        if(classpool == null)
                {
/* 192*/            classpool = CtClass.primitiveTypes;
/* 193*/            for(int i = 0; i < classpool.length; i++)
/* 194*/                classes.put(classpool[i].getName(), classpool[i]);

                }
/* 197*/        cflow = null;
/* 198*/        compressCount = 0;
/* 199*/        clearImportedPackages();
            }

            public static synchronized ClassPool getDefault()
            {
/* 227*/        if(defaultPool == null)
/* 228*/            (defaultPool = new ClassPool(((ClassPool) (null)))).appendSystemPath();
/* 232*/        return defaultPool;
            }

            protected CtClass getCached(String s)
            {
/* 245*/        return (CtClass)classes.get(s);
            }

            protected void cacheCtClass(String s, CtClass ctclass, boolean flag)
            {
/* 256*/        classes.put(s, ctclass);
            }

            protected CtClass removeCached(String s)
            {
/* 267*/        return (CtClass)classes.remove(s);
            }

            public String toString()
            {
/* 274*/        return source.toString();
            }

            void compress()
            {
/* 282*/        if(compressCount++ > 100)
                {
/* 283*/            compressCount = 0;
/* 284*/            for(Enumeration enumeration = classes.elements(); enumeration.hasMoreElements(); ((CtClass)enumeration.nextElement()).compress());
                }
            }

            public void importPackage(String s)
            {
/* 308*/        importedPackages.add(s);
            }

            public void clearImportedPackages()
            {
/* 319*/        importedPackages = new ArrayList();
/* 320*/        importedPackages.add("java.lang");
            }

            public Iterator getImportedPackages()
            {
/* 330*/        return importedPackages.iterator();
            }

            /**
             * @deprecated Method recordInvalidClassName is deprecated
             */

            public void recordInvalidClassName(String s)
            {
            }

            void recordCflow(String s, String s1, String s2)
            {
/* 360*/        if(cflow == null)
/* 361*/            cflow = new Hashtable();
/* 363*/        cflow.put(s, ((Object) (new Object[] {
/* 363*/            s1, s2
                })));
            }

            public Object[] lookupCflow(String s)
            {
/* 372*/        if(cflow == null)
/* 373*/            cflow = new Hashtable();
/* 375*/        return (Object[])cflow.get(s);
            }

            public CtClass getAndRename(String s, String s1)
                throws NotFoundException
            {
                CtClass ctclass;
/* 399*/        if((ctclass = get0(s, false)) == null)
/* 401*/            throw new NotFoundException(s);
/* 403*/        if(ctclass instanceof CtClassType)
/* 404*/            ((CtClassType)ctclass).setClassPool(this);
/* 406*/        ctclass.setName(s1);
/* 408*/        return ctclass;
            }

            synchronized void classNameChanged(String s, CtClass ctclass)
            {
                CtClass ctclass1;
/* 417*/        if((ctclass1 = getCached(s)) == ctclass)
/* 419*/            removeCached(s);
/* 421*/        s = ctclass.getName();
/* 422*/        checkNotFrozen(s);
/* 423*/        cacheCtClass(s, ctclass, false);
            }

            public CtClass get(String s)
                throws NotFoundException
            {
                CtClass ctclass;
/* 444*/        if(s == null)
/* 445*/            ctclass = null;
/* 447*/        else
/* 447*/            ctclass = get0(s, true);
/* 449*/        if(ctclass == null)
                {
/* 450*/            throw new NotFoundException(s);
                } else
                {
/* 452*/            ctclass.incGetCounter();
/* 453*/            return ctclass;
                }
            }

            public CtClass getOrNull(String s)
            {
/* 472*/        CtClass ctclass = null;
/* 473*/        if(s == null)
/* 474*/            ctclass = null;
/* 481*/        else
/* 481*/            try
                    {
/* 481*/                ctclass = get0(s, true);
                    }
/* 483*/            catch(NotFoundException _ex) { }
/* 485*/        if(ctclass != null)
/* 486*/            ctclass.incGetCounter();
/* 488*/        return ctclass;
            }

            public CtClass getCtClass(String s)
                throws NotFoundException
            {
/* 512*/        if(s.charAt(0) == '[')
/* 513*/            return Descriptor.toCtClass(s, this);
/* 515*/        else
/* 515*/            return get(s);
            }

            protected synchronized CtClass get0(String s, boolean flag)
                throws NotFoundException
            {
                CtClass ctclass;
/* 527*/        if(flag && (ctclass = getCached(s)) != null)
/* 530*/            return ctclass;
/* 533*/        if(!childFirstLookup && parent != null && (ctclass = parent.get0(s, flag)) != null)
/* 536*/            return ctclass;
/* 539*/        if((ctclass = createCtClass(s, flag)) != null)
                {
/* 542*/            if(flag)
/* 543*/                cacheCtClass(ctclass.getName(), ctclass, false);
/* 545*/            return ctclass;
                }
/* 548*/        if(childFirstLookup && parent != null)
/* 549*/            ctclass = parent.get0(s, flag);
/* 551*/        return ctclass;
            }

            protected CtClass createCtClass(String s, boolean flag)
            {
/* 563*/        if(s.charAt(0) == '[')
/* 564*/            s = Descriptor.toClassName(s);
/* 566*/        if(s.endsWith("[]"))
                {
/* 567*/            String s1 = s.substring(0, s.indexOf('['));
/* 568*/            if((!flag || getCached(s1) == null) && find(s1) == null)
/* 569*/                return null;
/* 571*/            else
/* 571*/                return new CtArray(s, this);
                }
/* 574*/        if(find(s) == null)
/* 575*/            return null;
/* 577*/        else
/* 577*/            return new CtClassType(s, this);
            }

            public URL find(String s)
            {
/* 590*/        return source.find(s);
            }

            void checkNotFrozen(String s)
                throws RuntimeException
            {
                CtClass ctclass;
/* 602*/        if((ctclass = getCached(s)) == null)
                {
/* 604*/            if(!childFirstLookup && parent != null)
                    {
/* 606*/                try
                        {
/* 606*/                    ctclass = parent.get0(s, true);
                        }
/* 608*/                catch(NotFoundException _ex) { }
/* 609*/                if(ctclass != null)
/* 610*/                    throw new RuntimeException((new StringBuilder()).append(s).append(" is in a parent ClassPool.  Use the parent.").toString());
                    }
                } else
/* 615*/        if(ctclass.isFrozen())
/* 616*/            throw new RuntimeException((new StringBuilder()).append(s).append(": frozen class (cannot edit)").toString());
            }

            CtClass checkNotExists(String s)
            {
                CtClass ctclass;
/* 627*/        if((ctclass = getCached(s)) == null && !childFirstLookup && parent != null)
/* 631*/            try
                    {
/* 631*/                ctclass = parent.get0(s, true);
                    }
/* 633*/            catch(NotFoundException _ex) { }
/* 636*/        return ctclass;
            }

            InputStream openClassfile(String s)
                throws NotFoundException
            {
/* 642*/        return source.openClassfile(s);
            }

            void writeClassfile(String s, OutputStream outputstream)
                throws NotFoundException, IOException, CannotCompileException
            {
/* 648*/        source.writeClassfile(s, outputstream);
            }

            public CtClass[] get(String as[])
                throws NotFoundException
            {
/* 663*/        if(as == null)
/* 664*/            return new CtClass[0];
                int i;
/* 666*/        CtClass actclass[] = new CtClass[i = as.length];
/* 668*/        for(int j = 0; j < i; j++)
/* 669*/            actclass[j] = get(as[j]);

/* 671*/        return actclass;
            }

            public CtMethod getMethod(String s, String s1)
                throws NotFoundException
            {
/* 684*/        return (s = get(s)).getDeclaredMethod(s1);
            }

            public CtClass makeClass(InputStream inputstream)
                throws IOException, RuntimeException
            {
/* 706*/        return makeClass(inputstream, true);
            }

            public CtClass makeClass(InputStream inputstream, boolean flag)
                throws IOException, RuntimeException
            {
/* 726*/        compress();
/* 727*/        inputstream = new BufferedInputStream(inputstream);
/* 728*/        (inputstream = new CtClassType(inputstream, this)).checkModify();
/* 730*/        String s = inputstream.getName();
/* 731*/        if(flag)
/* 732*/            checkNotFrozen(s);
/* 734*/        cacheCtClass(s, inputstream, true);
/* 735*/        return inputstream;
            }

            public CtClass makeClassIfNew(InputStream inputstream)
                throws IOException, RuntimeException
            {
/* 756*/        compress();
/* 757*/        inputstream = new BufferedInputStream(inputstream);
/* 758*/        (inputstream = new CtClassType(inputstream, this)).checkModify();
/* 760*/        String s = inputstream.getName();
                CtClass ctclass;
/* 761*/        if((ctclass = checkNotExists(s)) != null)
                {
/* 763*/            return ctclass;
                } else
                {
/* 765*/            cacheCtClass(s, inputstream, true);
/* 766*/            return inputstream;
                }
            }

            public CtClass makeClass(String s)
                throws RuntimeException
            {
/* 787*/        return makeClass(s, ((CtClass) (null)));
            }

            public synchronized CtClass makeClass(String s, CtClass ctclass)
                throws RuntimeException
            {
/* 810*/        checkNotFrozen(s);
/* 811*/        ctclass = new CtNewClass(s, this, false, ctclass);
/* 812*/        cacheCtClass(s, ctclass, true);
/* 813*/        return ctclass;
            }

            synchronized CtClass makeNestedClass(String s)
            {
/* 824*/        checkNotFrozen(s);
/* 825*/        CtNewNestedClass ctnewnestedclass = new CtNewNestedClass(s, this, false, null);
/* 826*/        cacheCtClass(s, ctnewnestedclass, true);
/* 827*/        return ctnewnestedclass;
            }

            public CtClass makeInterface(String s)
                throws RuntimeException
            {
/* 839*/        return makeInterface(s, null);
            }

            public synchronized CtClass makeInterface(String s, CtClass ctclass)
                throws RuntimeException
            {
/* 854*/        checkNotFrozen(s);
/* 855*/        ctclass = new CtNewClass(s, this, true, ctclass);
/* 856*/        cacheCtClass(s, ctclass, true);
/* 857*/        return ctclass;
            }

            public ClassPath appendSystemPath()
            {
/* 871*/        return source.appendSystemPath();
            }

            public ClassPath insertClassPath(ClassPath classpath)
            {
/* 884*/        return source.insertClassPath(classpath);
            }

            public ClassPath appendClassPath(ClassPath classpath)
            {
/* 897*/        return source.appendClassPath(classpath);
            }

            public ClassPath insertClassPath(String s)
                throws NotFoundException
            {
/* 915*/        return source.insertClassPath(s);
            }

            public ClassPath appendClassPath(String s)
                throws NotFoundException
            {
/* 933*/        return source.appendClassPath(s);
            }

            public void removeClassPath(ClassPath classpath)
            {
/* 942*/        source.removeClassPath(classpath);
            }

            public void appendPathList(String s)
                throws NotFoundException
            {
/* 958*/        char c = File.pathSeparatorChar;
/* 959*/        int i = 0;
/* 961*/        do
                {
                    int j;
/* 961*/            if((j = s.indexOf(c, i)) < 0)
                    {
/* 963*/                appendClassPath(s.substring(i));
/* 964*/                return;
                    }
/* 967*/            appendClassPath(s.substring(i, j));
/* 968*/            i = j + 1;
                } while(true);
            }

            public Class toClass(CtClass ctclass)
                throws CannotCompileException
            {
/*1000*/        return toClass(ctclass, getClassLoader());
            }

            public ClassLoader getClassLoader()
            {
/*1014*/        return getContextClassLoader();
            }

            static ClassLoader getContextClassLoader()
            {
/*1022*/        return Thread.currentThread().getContextClassLoader();
            }

            /**
             * @deprecated Method toClass is deprecated
             */

            public Class toClass(CtClass ctclass, ClassLoader classloader)
                throws CannotCompileException
            {
/*1042*/        return toClass(ctclass, classloader, null);
            }

            public Class toClass(CtClass ctclass, ClassLoader classloader, ProtectionDomain protectiondomain)
                throws CannotCompileException
            {
                Method method;
/*1079*/        byte abyte0[] = ctclass.toBytecode();
/*1082*/        if(protectiondomain == null)
                {
/*1083*/            method = defineClass1;
/*1084*/            ctclass = ((CtClass) (new Object[] {
/*1084*/                ctclass.getName(), abyte0, new Integer(0), new Integer(abyte0.length)
                    }));
                } else
                {
/*1088*/            method = defineClass2;
/*1089*/            ctclass = ((CtClass) (new Object[] {
/*1089*/                ctclass.getName(), abyte0, new Integer(0), new Integer(abyte0.length), protectiondomain
                    }));
                }
/*1093*/        return (Class)toClass2(method, classloader, ctclass);
/*1095*/        JVM INSTR dup ;
                RuntimeException runtimeexception;
/*1096*/        runtimeexception;
/*1096*/        throw ;
                Object obj;
/*1098*/        obj;
/*1099*/        throw new CannotCompileException(((InvocationTargetException) (obj)).getTargetException());
/*1101*/        obj;
/*1102*/        throw new CannotCompileException(((Throwable) (obj)));
            }

            private static synchronized Object toClass2(Method method, ClassLoader classloader, Object aobj[])
                throws Exception
            {
/*1110*/        method.setAccessible(true);
/*1112*/        classloader = ((ClassLoader) (method.invoke(classloader, aobj)));
/*1115*/        method.setAccessible(false);
/*1115*/        return classloader;
/*1115*/        classloader;
/*1115*/        method.setAccessible(false);
/*1115*/        throw classloader;
            }

            public void makePackage(ClassLoader classloader, String s)
                throws CannotCompileException
            {
/*1139*/        s = ((String) (new Object[] {
/*1139*/            s, null, null, null, null, null, null, null
                }));
/*1143*/        toClass2(definePackage, classloader, s);
/*1144*/        return;
/*1146*/        JVM INSTR dup ;
/*1147*/        s;
/*1147*/        getTargetException();
/*1147*/        JVM INSTR dup ;
/*1148*/        classloader;
/*1148*/        JVM INSTR ifnonnull 65;
                   goto _L1 _L2
_L1:
/*1149*/        classloader = s;
                  goto _L3
_L2:
/*1150*/        if(classloader instanceof IllegalArgumentException)
/*1153*/            return;
                  goto _L3
/*1156*/        JVM INSTR dup ;
/*1157*/        s;
/*1157*/        classloader;
_L3:
/*1160*/        throw new CannotCompileException(classloader);
            }

            private static Method defineClass1;
            private static Method defineClass2;
            private static Method definePackage;
            public boolean childFirstLookup;
            public static boolean doPruning = false;
            private int compressCount;
            private static final int COMPRESS_THRESHOLD = 100;
            public static boolean releaseUnmodifiedClassFile = true;
            protected ClassPoolTail source;
            protected ClassPool parent;
            protected Hashtable classes;
            private Hashtable cflow;
            private static final int INIT_HASH_SIZE = 191;
            private ArrayList importedPackages;
            private static ClassPool defaultPool = null;

            static 
            {
/*  77*/        try
                {
/*  77*/            AccessController.doPrivileged(new PrivilegedExceptionAction() {

                        public final Object run()
                            throws Exception
                        {
                            Class class1;
/*  79*/                    ClassPool.defineClass1 = (class1 = Class.forName("java.lang.ClassLoader")).getDeclaredMethod("defineClass", new Class[] {
/*  80*/                        java/lang/String, [B, Integer.TYPE, Integer.TYPE
                            });
/*  84*/                    ClassPool.defineClass2 = class1.getDeclaredMethod("defineClass", new Class[] {
/*  84*/                        java/lang/String, [B, Integer.TYPE, Integer.TYPE, java/security/ProtectionDomain
                            });
/*  88*/                    ClassPool.definePackage = class1.getDeclaredMethod("definePackage", new Class[] {
/*  88*/                        java/lang/String, java/lang/String, java/lang/String, java/lang/String, java/lang/String, java/lang/String, java/lang/String, java/net/URL
                            });
/*  92*/                    return null;
                        }

            });
                }
/*  96*/        catch(PrivilegedActionException privilegedactionexception)
                {
/*  97*/            throw new RuntimeException("cannot initialize ClassPool", privilegedactionexception.getException());
                }
            }



}
