// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProxyFactory.java

package javassist.util.proxy;

import java.lang.ref.WeakReference;
import java.lang.reflect.*;
import java.security.ProtectionDomain;
import java.util.*;
import javassist.CannotCompileException;
import javassist.bytecode.*;

// Referenced classes of package javassist.util.proxy:
//            FactoryHelper, MethodFilter, MethodHandler, Proxy, 
//            ProxyObject, RuntimeSupport, SecurityActions

public class ProxyFactory
{
    static class Find2MethodsArgs
    {

                String methodName;
                String delegatorName;
                String descriptor;
                int origIndex;

                Find2MethodsArgs(String s, String s1, String s2, int i)
                {
/*1300*/            methodName = s;
/*1301*/            delegatorName = s1;
/*1302*/            descriptor = s2;
/*1303*/            origIndex = i;
                }
    }

    public static interface UniqueName
    {

        public abstract String get(String s);
    }

    public static interface ClassLoaderProvider
    {

        public abstract ClassLoader get(ProxyFactory proxyfactory);
    }

    static class ProxyDetails
    {

                byte signature[];
                WeakReference proxyClass;
                boolean isUseWriteReplace;

                ProxyDetails(byte abyte0[], Class class1, boolean flag)
                {
/* 329*/            signature = abyte0;
/* 330*/            proxyClass = new WeakReference(class1);
/* 331*/            isUseWriteReplace = flag;
                }
    }


            public boolean isUseCache()
            {
/* 254*/        return factoryUseCache;
            }

            public void setUseCache(boolean flag)
            {
/* 266*/        if(handler != null && flag)
                {
/* 267*/            throw new RuntimeException("caching cannot be enabled if the factory default interceptor has been set");
                } else
                {
/* 269*/            factoryUseCache = flag;
/* 270*/            return;
                }
            }

            public boolean isUseWriteReplace()
            {
/* 278*/        return factoryWriteReplace;
            }

            public void setUseWriteReplace(boolean flag)
            {
/* 288*/        factoryWriteReplace = flag;
            }

            public static boolean isProxyClass(Class class1)
            {
/* 301*/        return javassist/util/proxy/Proxy.isAssignableFrom(class1);
            }

            public ProxyFactory()
            {
/* 339*/        superClass = null;
/* 340*/        interfaces = null;
/* 341*/        methodFilter = null;
/* 342*/        handler = null;
/* 343*/        signature = null;
/* 344*/        signatureMethods = null;
/* 345*/        hasGetHandler = false;
/* 346*/        thisClass = null;
/* 347*/        writeDirectory = null;
/* 348*/        factoryUseCache = useCache;
/* 349*/        factoryWriteReplace = useWriteReplace;
            }

            public void setSuperclass(Class class1)
            {
/* 356*/        superClass = class1;
/* 358*/        signature = null;
            }

            public Class getSuperclass()
            {
/* 366*/        return superClass;
            }

            public void setInterfaces(Class aclass[])
            {
/* 372*/        interfaces = aclass;
/* 374*/        signature = null;
            }

            public Class[] getInterfaces()
            {
/* 382*/        return interfaces;
            }

            public void setFilter(MethodFilter methodfilter)
            {
/* 388*/        methodFilter = methodfilter;
/* 390*/        signature = null;
            }

            public Class createClass()
            {
/* 397*/        if(signature == null)
/* 398*/            computeSignature(methodFilter);
/* 400*/        return createClass1();
            }

            public Class createClass(MethodFilter methodfilter)
            {
/* 407*/        computeSignature(methodfilter);
/* 408*/        return createClass1();
            }

            Class createClass(byte abyte0[])
            {
/* 419*/        installSignature(abyte0);
/* 420*/        return createClass1();
            }

            private Class createClass1()
            {
/* 424*/        if(thisClass == null)
                {
/* 425*/            ClassLoader classloader = getClassLoader();
/* 426*/            synchronized(proxyCache)
                    {
/* 427*/                if(factoryUseCache)
/* 428*/                    createClass2(classloader);
/* 430*/                else
/* 430*/                    createClass3(classloader);
                    }
                }
/* 435*/        Class class1 = thisClass;
/* 436*/        thisClass = null;
/* 438*/        return class1;
            }

            public String getKey(Class class1, Class aclass[], byte abyte0[], boolean flag)
            {
/* 447*/        StringBuffer stringbuffer = new StringBuffer();
/* 448*/        if(class1 != null)
/* 449*/            stringbuffer.append(class1.getName());
/* 451*/        stringbuffer.append(":");
/* 452*/        for(class1 = 0; class1 < aclass.length; class1++)
                {
/* 453*/            stringbuffer.append(aclass[class1].getName());
/* 454*/            stringbuffer.append(":");
                }

/* 456*/        for(class1 = 0; class1 < abyte0.length; class1++)
                {
/* 457*/            int i = (aclass = abyte0[class1]) & 0xf;
/* 459*/            aclass = aclass >> 4 & 0xf;
/* 460*/            stringbuffer.append(hexDigits[i]);
/* 461*/            stringbuffer.append(hexDigits[aclass]);
                }

/* 463*/        if(flag)
/* 464*/            stringbuffer.append(":w");
/* 467*/        return stringbuffer.toString();
            }

            private void createClass2(ClassLoader classloader)
            {
/* 471*/        String s = getKey(superClass, interfaces, signature, factoryWriteReplace);
                HashMap hashmap;
/* 478*/        if((hashmap = (HashMap)proxyCache.get(classloader)) == null)
                {
/* 481*/            hashmap = new HashMap();
/* 482*/            proxyCache.put(classloader, hashmap);
                }
                Object obj;
/* 484*/        if((obj = (ProxyDetails)hashmap.get(s)) != null)
                {
/* 486*/            obj = ((ProxyDetails) (obj)).proxyClass;
/* 487*/            thisClass = (Class)((WeakReference) (obj)).get();
/* 488*/            if(thisClass != null)
/* 489*/                return;
                }
/* 492*/        createClass3(classloader);
/* 493*/        obj = new ProxyDetails(signature, thisClass, factoryWriteReplace);
/* 494*/        hashmap.put(s, obj);
            }

            private void createClass3(ClassLoader classloader)
            {
/* 500*/        allocateClassName();
/* 503*/        try
                {
/* 503*/            ClassFile classfile = make();
/* 504*/            if(writeDirectory != null)
/* 505*/                FactoryHelper.writeFile(classfile, writeDirectory);
/* 507*/            thisClass = FactoryHelper.toClass(classfile, classloader, getDomain());
/* 508*/            setField("_filter_signature", signature);
/* 510*/            if(!factoryUseCache)
/* 511*/                setField("default_interceptor", handler);
/* 516*/            return;
                }
/* 514*/        catch(CannotCompileException cannotcompileexception)
                {
/* 515*/            throw new RuntimeException(cannotcompileexception.getMessage(), cannotcompileexception);
                }
            }

            private void setField(String s, Object obj)
            {
/* 521*/        if(thisClass != null && obj != null)
/* 523*/            try
                    {
/* 523*/                SecurityActions.setAccessible(s = thisClass.getField(s), true);
/* 525*/                s.set(null, obj);
/* 526*/                SecurityActions.setAccessible(s, false);
/* 530*/                return;
                    }
                    // Misplaced declaration of an exception variable
/* 528*/            catch(String s)
                    {
/* 529*/                throw new RuntimeException(s);
                    }
/* 531*/        else
/* 531*/            return;
            }

            static byte[] getFilterSignature(Class class1)
            {
/* 534*/        return (byte[])getField(class1, "_filter_signature");
            }

            private static Object getField(Class class1, String s)
            {
/* 539*/        (class1 = class1.getField(s)).setAccessible(true);
/* 541*/        s = ((String) (class1.get(null)));
/* 542*/        class1.setAccessible(false);
/* 543*/        return s;
/* 545*/        class1;
/* 546*/        throw new RuntimeException(class1);
            }

            public static MethodHandler getHandler(Proxy proxy)
            {
                Field field;
/* 559*/        (field = proxy.getClass().getDeclaredField("handler")).setAccessible(true);
/* 561*/        proxy = ((Proxy) (field.get(proxy)));
/* 562*/        field.setAccessible(false);
/* 563*/        return (MethodHandler)proxy;
                Exception exception;
/* 565*/        exception;
/* 566*/        throw new RuntimeException(exception);
            }

            protected ClassLoader getClassLoader()
            {
/* 613*/        return classLoaderProvider.get(this);
            }

            protected ClassLoader getClassLoader0()
            {
/* 617*/        ClassLoader classloader = null;
/* 618*/        if(superClass != null && !superClass.getName().equals("java.lang.Object"))
/* 619*/            classloader = superClass.getClassLoader();
/* 620*/        else
/* 620*/        if(interfaces != null && interfaces.length > 0)
/* 621*/            classloader = interfaces[0].getClassLoader();
/* 623*/        if(classloader == null && (classloader = getClass().getClassLoader()) == null && (classloader = Thread.currentThread().getContextClassLoader()) == null)
/* 629*/            classloader = ClassLoader.getSystemClassLoader();
/* 633*/        return classloader;
            }

            protected ProtectionDomain getDomain()
            {
                Class class1;
/* 638*/        if(superClass != null && !superClass.getName().equals("java.lang.Object"))
/* 639*/            class1 = superClass;
/* 640*/        else
/* 640*/        if(interfaces != null && interfaces.length > 0)
/* 641*/            class1 = interfaces[0];
/* 643*/        else
/* 643*/            class1 = getClass();
/* 645*/        return class1.getProtectionDomain();
            }

            public Object create(Class aclass[], Object aobj[], MethodHandler methodhandler)
                throws NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
            {
/* 660*/        ((Proxy)(aclass = ((Class []) (create(aclass, aobj))))).setHandler(methodhandler);
/* 662*/        return aclass;
            }

            public Object create(Class aclass[], Object aobj[])
                throws NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
            {
                Class class1;
/* 675*/        return (aclass = (class1 = createClass()).getConstructor(aclass)).newInstance(aobj);
            }

            /**
             * @deprecated Method setHandler is deprecated
             */

            public void setHandler(MethodHandler methodhandler)
            {
/* 692*/        if(factoryUseCache && methodhandler != null)
                {
/* 693*/            factoryUseCache = false;
/* 695*/            thisClass = null;
                }
/* 697*/        handler = methodhandler;
/* 700*/        setField("default_interceptor", handler);
            }

            private static String makeProxyName(String s)
            {
/* 732*/        UniqueName uniquename = nameGenerator;
/* 732*/        JVM INSTR monitorenter ;
/* 733*/        return nameGenerator.get(s);
/* 734*/        s;
/* 734*/        throw s;
            }

            private ClassFile make()
                throws CannotCompileException
            {
                ClassFile classfile;
/* 738*/        (classfile = new ClassFile(false, classname, superName)).setAccessFlags(1);
/* 740*/        setInterfaces(classfile, interfaces, ((Class) (hasGetHandler ? javassist/util/proxy/Proxy : javassist/util/proxy/ProxyObject)));
/* 741*/        ConstPool constpool = classfile.getConstPool();
/* 744*/        if(!factoryUseCache)
                {
                    FieldInfo fieldinfo;
/* 745*/            (fieldinfo = new FieldInfo(constpool, "default_interceptor", HANDLER_TYPE)).setAccessFlags(9);
/* 747*/            classfile.addField(fieldinfo);
                }
                Object obj;
/* 751*/        ((FieldInfo) (obj = new FieldInfo(constpool, "handler", HANDLER_TYPE))).setAccessFlags(2);
/* 753*/        classfile.addField(((FieldInfo) (obj)));
/* 756*/        ((FieldInfo) (obj = new FieldInfo(constpool, "_filter_signature", "[B"))).setAccessFlags(9);
/* 758*/        classfile.addField(((FieldInfo) (obj)));
/* 761*/        ((FieldInfo) (obj = new FieldInfo(constpool, "serialVersionUID", "J"))).setAccessFlags(25);
/* 763*/        classfile.addField(((FieldInfo) (obj)));
/* 767*/        makeConstructors(classname, classfile, constpool, classname);
/* 769*/        obj = new ArrayList();
/* 770*/        int i = overrideMethods(classfile, constpool, classname, ((ArrayList) (obj)));
/* 771*/        addClassInitializer(classfile, constpool, classname, i, ((ArrayList) (obj)));
/* 772*/        addSetter(classname, classfile, constpool);
/* 773*/        if(!hasGetHandler)
/* 774*/            addGetter(classname, classfile, constpool);
/* 776*/        if(factoryWriteReplace)
/* 778*/            try
                    {
/* 778*/                classfile.addMethod(makeWriteReplace(constpool));
                    }
/* 780*/            catch(DuplicateMemberException _ex) { }
/* 785*/        thisClass = null;
/* 786*/        return classfile;
            }

            private void checkClassAndSuperName()
            {
/* 790*/        if(interfaces == null)
/* 791*/            interfaces = new Class[0];
/* 793*/        if(superClass == null)
                {
/* 794*/            superClass = OBJECT_TYPE;
/* 795*/            superName = superClass.getName();
/* 796*/            basename = interfaces.length != 0 ? interfaces[0].getName() : superName;
                } else
                {
/* 799*/            superName = superClass.getName();
/* 800*/            basename = superName;
                }
/* 803*/        if(Modifier.isFinal(superClass.getModifiers()))
/* 804*/            throw new RuntimeException((new StringBuilder()).append(superName).append(" is final").toString());
/* 806*/        if(basename.startsWith("java."))
/* 807*/            basename = (new StringBuilder("org.javassist.tmp.")).append(basename).toString();
            }

            private void allocateClassName()
            {
/* 811*/        classname = makeProxyName(basename);
            }

            private void makeSortedMethodList()
            {
/* 826*/        checkClassAndSuperName();
/* 828*/        hasGetHandler = false;
/* 829*/        HashMap hashmap = getMethods(superClass, interfaces);
/* 830*/        signatureMethods = new ArrayList(hashmap.entrySet());
/* 831*/        Collections.sort(signatureMethods, sorter);
            }

            private void computeSignature(MethodFilter methodfilter)
            {
/* 836*/        makeSortedMethodList();
                int i;
/* 838*/        int j = (i = signatureMethods.size()) + 7 >> 3;
/* 840*/        signature = new byte[j];
/* 841*/        for(int k = 0; k < i; k++)
                {
                    Object obj;
                    int l;
/* 843*/            if(!Modifier.isFinal(l = ((Method) (obj = (Method)((java.util.Map.Entry) (obj = (java.util.Map.Entry)signatureMethods.get(k))).getValue())).getModifiers()) && !Modifier.isStatic(l) && isVisible(l, basename, ((Member) (obj))) && (methodfilter == null || methodfilter.isHandled(((Method) (obj)))))
/* 848*/                setBit(signature, k);
                }

            }

            private void installSignature(byte abyte0[])
            {
/* 855*/        makeSortedMethodList();
                int i;
/* 857*/        i = (i = signatureMethods.size()) + 7 >> 3;
/* 859*/        if(abyte0.length != i)
                {
/* 860*/            throw new RuntimeException("invalid filter signature length for deserialized proxy class");
                } else
                {
/* 863*/            signature = abyte0;
/* 864*/            return;
                }
            }

            private boolean testBit(byte abyte0[], int i)
            {
                int j;
/* 867*/        if((j = i >> 3) > abyte0.length)
/* 869*/            return false;
/* 871*/        i &= 7;
/* 872*/        i = 1 << i;
/* 873*/        return ((abyte0 = abyte0[j]) & i) != 0;
            }

            private void setBit(byte abyte0[], int i)
            {
                int j;
/* 879*/        if((j = i >> 3) < abyte0.length)
                {
/* 881*/            i &= 7;
/* 882*/            i = 1 << i;
/* 883*/            byte byte0 = abyte0[j];
/* 884*/            abyte0[j] = (byte)(byte0 | i);
                }
            }

            private static void setInterfaces(ClassFile classfile, Class aclass[], Class class1)
            {
/* 889*/        class1 = class1.getName();
                String as[];
/* 891*/        if(aclass == null || aclass.length == 0)
                {
/* 892*/            as = (new String[] {
/* 892*/                class1
                    });
                } else
                {
/* 894*/            as = new String[aclass.length + 1];
/* 895*/            for(int i = 0; i < aclass.length; i++)
/* 896*/                as[i] = aclass[i].getName();

/* 898*/            as[aclass.length] = class1;
                }
/* 901*/        classfile.setInterfaces(as);
            }

            private static void addClassInitializer(ClassFile classfile, ConstPool constpool, String s, int i, ArrayList arraylist)
                throws CannotCompileException
            {
                Object obj;
/* 908*/        ((FieldInfo) (obj = new FieldInfo(constpool, "_methods_", "[Ljava/lang/reflect/Method;"))).setAccessFlags(10);
/* 910*/        classfile.addField(((FieldInfo) (obj)));
/* 911*/        ((MethodInfo) (obj = new MethodInfo(constpool, "<clinit>", "()V"))).setAccessFlags(8);
/* 913*/        setThrows(((MethodInfo) (obj)), constpool, new Class[] {
/* 913*/            java/lang/ClassNotFoundException
                });
/* 915*/        (constpool = new Bytecode(constpool, 0, 2)).addIconst(i << 1);
/* 917*/        constpool.addAnewarray("java.lang.reflect.Method");
/* 919*/        constpool.addAstore(0);
/* 923*/        constpool.addLdc(s);
/* 924*/        constpool.addInvokestatic("java.lang.Class", "forName", "(Ljava/lang/String;)Ljava/lang/Class;");
/* 927*/        constpool.addAstore(1);
/* 929*/        for(i = arraylist.iterator(); i.hasNext(); callFind2Methods(constpool, ((Find2MethodsArgs) (arraylist)).methodName, ((Find2MethodsArgs) (arraylist)).delegatorName, ((Find2MethodsArgs) (arraylist)).origIndex, ((Find2MethodsArgs) (arraylist)).descriptor, 1, 0))
/* 931*/            arraylist = (Find2MethodsArgs)i.next();

/* 936*/        constpool.addAload(0);
/* 937*/        constpool.addPutstatic(s, "_methods_", "[Ljava/lang/reflect/Method;");
/* 939*/        constpool.addLconst(-1L);
/* 940*/        constpool.addPutstatic(s, "serialVersionUID", "J");
/* 941*/        constpool.addOpcode(177);
/* 942*/        ((MethodInfo) (obj)).setCodeAttribute(constpool.toCodeAttribute());
/* 943*/        classfile.addMethod(((MethodInfo) (obj)));
            }

            private static void callFind2Methods(Bytecode bytecode, String s, String s1, int i, String s2, int j, int k)
            {
/* 951*/        String s3 = javassist/util/proxy/RuntimeSupport.getName();
/* 952*/        String s4 = "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;[Ljava/lang/reflect/Method;)V";
/* 955*/        bytecode.addAload(j);
/* 956*/        bytecode.addLdc(s);
/* 957*/        if(s1 == null)
/* 958*/            bytecode.addOpcode(1);
/* 960*/        else
/* 960*/            bytecode.addLdc(s1);
/* 962*/        bytecode.addIconst(i);
/* 963*/        bytecode.addLdc(s2);
/* 964*/        bytecode.addAload(k);
/* 965*/        bytecode.addInvokestatic(s3, "find2Methods", s4);
            }

            private static void addSetter(String s, ClassFile classfile, ConstPool constpool)
                throws CannotCompileException
            {
                MethodInfo methodinfo;
/* 971*/        (methodinfo = new MethodInfo(constpool, "setHandler", HANDLER_SETTER_TYPE)).setAccessFlags(1);
/* 974*/        (constpool = new Bytecode(constpool, 2, 2)).addAload(0);
/* 976*/        constpool.addAload(1);
/* 977*/        constpool.addPutfield(s, "handler", HANDLER_TYPE);
/* 978*/        constpool.addOpcode(177);
/* 979*/        methodinfo.setCodeAttribute(constpool.toCodeAttribute());
/* 980*/        classfile.addMethod(methodinfo);
            }

            private static void addGetter(String s, ClassFile classfile, ConstPool constpool)
                throws CannotCompileException
            {
                MethodInfo methodinfo;
/* 986*/        (methodinfo = new MethodInfo(constpool, "getHandler", HANDLER_GETTER_TYPE)).setAccessFlags(1);
/* 989*/        (constpool = new Bytecode(constpool, 1, 1)).addAload(0);
/* 991*/        constpool.addGetfield(s, "handler", HANDLER_TYPE);
/* 992*/        constpool.addOpcode(176);
/* 993*/        methodinfo.setCodeAttribute(constpool.toCodeAttribute());
/* 994*/        classfile.addMethod(methodinfo);
            }

            private int overrideMethods(ClassFile classfile, ConstPool constpool, String s, ArrayList arraylist)
                throws CannotCompileException
            {
/*1000*/        String s1 = makeUniqueName("_d", signatureMethods);
/*1001*/        Iterator iterator = signatureMethods.iterator();
                int i;
/*1002*/        for(i = 0; iterator.hasNext(); i++)
                {
                    Object obj;
/*1004*/            String s2 = (String)((java.util.Map.Entry) (obj = (java.util.Map.Entry)iterator.next())).getKey();
/*1006*/            obj = (Method)((java.util.Map.Entry) (obj)).getValue();
/*1007*/            if((ClassFile.MAJOR_VERSION < 49 || !isBridge(((Method) (obj)))) && testBit(signature, i))
/*1009*/                override(s, ((Method) (obj)), s1, i, keyToDesc(s2, ((Method) (obj))), classfile, constpool, arraylist);
                }

/*1016*/        return i;
            }

            private static boolean isBridge(Method method)
            {
/*1020*/        return method.isBridge();
            }

            private void override(String s, Method method, String s1, int i, String s2, ClassFile classfile, ConstPool constpool, 
                    ArrayList arraylist)
                throws CannotCompileException
            {
/*1027*/        Class class1 = method.getDeclaringClass();
/*1028*/        s1 = (new StringBuilder()).append(s1).append(i).append(method.getName()).toString();
/*1029*/        if(Modifier.isAbstract(method.getModifiers()))
                {
/*1030*/            s1 = null;
                } else
                {
                    MethodInfo methodinfo;
/*1032*/            (methodinfo = makeDelegator(method, s2, constpool, class1, s1)).setAccessFlags(methodinfo.getAccessFlags() & 0xffffffbf);
/*1036*/            classfile.addMethod(methodinfo);
                }
/*1039*/        MethodInfo methodinfo1 = makeForwarder(s, method, s2, constpool, class1, s1, i, arraylist);
/*1042*/        classfile.addMethod(methodinfo1);
            }

            private void makeConstructors(String s, ClassFile classfile, ConstPool constpool, String s1)
                throws CannotCompileException
            {
/*1048*/        s1 = SecurityActions.getDeclaredConstructors(superClass);
/*1050*/        boolean flag = !factoryUseCache;
/*1051*/        for(int i = 0; i < s1.length; i++)
                {
                    Object obj;
                    int j;
/*1052*/            if(!Modifier.isFinal(j = ((Constructor) (obj = s1[i])).getModifiers()) && !Modifier.isPrivate(j) && isVisible(j, basename, ((Member) (obj))))
                    {
/*1056*/                obj = makeConstructor(s, ((Constructor) (obj)), constpool, superClass, flag);
/*1057*/                classfile.addMethod(((MethodInfo) (obj)));
                    }
                }

            }

            private static String makeUniqueName(String s, List list)
            {
/*1063*/        if(makeUniqueName0(s, list.iterator()))
/*1064*/            return s;
/*1066*/        for(int i = 100; i < 999; i++)
                {
                    String s1;
/*1067*/            if(makeUniqueName0(s1 = (new StringBuilder()).append(s).append(i).toString(), list.iterator()))
/*1069*/                return s1;
                }

/*1072*/        throw new RuntimeException("cannot make a unique method name");
            }

            private static boolean makeUniqueName0(String s, Iterator iterator)
            {
                Object obj;
/*1076*/        while(iterator.hasNext()) 
/*1077*/            if(((String) (obj = (String)((java.util.Map.Entry) (obj = (java.util.Map.Entry)iterator.next())).getKey())).startsWith(s))
/*1080*/                return false;
/*1083*/        return true;
            }

            private static boolean isVisible(int i, String s, Member member)
            {
/*1092*/        if((i & 2) != 0)
/*1093*/            return false;
/*1094*/        if((i & 5) != 0)
/*1095*/            return true;
/*1097*/        i = getPackageName(s);
/*1098*/        s = getPackageName(member.getDeclaringClass().getName());
/*1099*/        if(i == null)
/*1100*/            return s == null;
/*1102*/        else
/*1102*/            return i.equals(s);
            }

            private static String getPackageName(String s)
            {
                int i;
/*1107*/        if((i = s.lastIndexOf('.')) < 0)
/*1109*/            return null;
/*1111*/        else
/*1111*/            return s.substring(0, i);
            }

            private HashMap getMethods(Class class1, Class aclass[])
            {
/*1117*/        HashMap hashmap = new HashMap();
/*1118*/        HashSet hashset = new HashSet();
/*1119*/        for(int i = 0; i < aclass.length; i++)
/*1120*/            getMethods(hashmap, aclass[i], ((Set) (hashset)));

/*1122*/        getMethods(hashmap, class1, ((Set) (hashset)));
/*1123*/        return hashmap;
            }

            private void getMethods(HashMap hashmap, Class class1, Set set)
            {
/*1129*/        if(!set.add(class1))
/*1130*/            return;
/*1132*/        Class aclass[] = class1.getInterfaces();
/*1133*/        for(int i = 0; i < aclass.length; i++)
/*1134*/            getMethods(hashmap, aclass[i], set);

                Class class2;
/*1136*/        if((class2 = class1.getSuperclass()) != null)
/*1138*/            getMethods(hashmap, class2, set);
/*1145*/        class1 = SecurityActions.getDeclaredMethods(class1);
/*1146*/        for(set = 0; set < class1.length; set++)
                {
/*1147*/            if(Modifier.isPrivate(class1[set].getModifiers()))
/*1148*/                continue;
/*1148*/            Object obj = class1[set];
/*1149*/            if(((String) (obj = (new StringBuilder()).append(((Method) (obj)).getName()).append(':').append(RuntimeSupport.makeDescriptor(((Method) (obj)))).toString())).startsWith("getHandler:()"))
/*1151*/                hasGetHandler = true;
/*1155*/            Method method = (Method)hashmap.put(obj, class1[set]);
/*1158*/            if(method != null && Modifier.isPublic(method.getModifiers()) && !Modifier.isPublic(class1[set].getModifiers()))
/*1162*/                hashmap.put(obj, method);
                }

            }

            private static String keyToDesc(String s, Method method)
            {
/*1171*/        return s.substring(s.indexOf(':') + 1);
            }

            private static MethodInfo makeConstructor(String s, Constructor constructor, ConstPool constpool, Class class1, boolean flag)
            {
/*1176*/        String s1 = RuntimeSupport.makeDescriptor(constructor.getParameterTypes(), Void.TYPE);
                MethodInfo methodinfo;
/*1178*/        (methodinfo = new MethodInfo(constpool, "<init>", s1)).setAccessFlags(1);
/*1180*/        setThrows(methodinfo, constpool, constructor.getExceptionTypes());
/*1181*/        Bytecode bytecode = new Bytecode(constpool, 0, 0);
/*1186*/        if(flag)
                {
/*1187*/            bytecode.addAload(0);
/*1188*/            bytecode.addGetstatic(s, "default_interceptor", HANDLER_TYPE);
/*1189*/            bytecode.addPutfield(s, "handler", HANDLER_TYPE);
/*1190*/            bytecode.addGetstatic(s, "default_interceptor", HANDLER_TYPE);
/*1191*/            bytecode.addOpcode(199);
/*1192*/            bytecode.addIndex(10);
                }
/*1196*/        bytecode.addAload(0);
/*1197*/        bytecode.addGetstatic("javassist.util.proxy.RuntimeSupport", "default_interceptor", HANDLER_TYPE);
/*1198*/        bytecode.addPutfield(s, "handler", HANDLER_TYPE);
/*1199*/        s = bytecode.currentPc();
/*1201*/        bytecode.addAload(0);
/*1202*/        constructor = addLoadParameters(bytecode, constructor.getParameterTypes(), 1);
/*1203*/        bytecode.addInvokespecial(class1.getName(), "<init>", s1);
/*1204*/        bytecode.addOpcode(177);
/*1205*/        bytecode.setMaxLocals(constructor + 1);
/*1206*/        constructor = bytecode.toCodeAttribute();
/*1207*/        methodinfo.setCodeAttribute(constructor);
/*1209*/        (class1 = new javassist.bytecode.StackMapTable.Writer(32)).sameFrame(s);
/*1211*/        constructor.setAttribute(class1.toStackMapTable(constpool));
/*1212*/        return methodinfo;
            }

            private static MethodInfo makeDelegator(Method method, String s, ConstPool constpool, Class class1, String s1)
            {
/*1217*/        (s1 = new MethodInfo(constpool, s1, s)).setAccessFlags(0x11 | method.getModifiers() & 0xfffffad9);
/*1224*/        setThrows(s1, constpool, method);
/*1225*/        (constpool = new Bytecode(constpool, 0, 0)).addAload(0);
/*1227*/        int i = addLoadParameters(constpool, method.getParameterTypes(), 1);
/*1228*/        constpool.addInvokespecial(class1.getName(), method.getName(), s);
/*1229*/        addReturn(constpool, method.getReturnType());
/*1230*/        constpool.setMaxLocals(++i);
/*1231*/        s1.setCodeAttribute(constpool.toCodeAttribute());
/*1232*/        return s1;
            }

            private static MethodInfo makeForwarder(String s, Method method, String s1, ConstPool constpool, Class class1, String s2, int i, ArrayList arraylist)
            {
/*1242*/        (class1 = new MethodInfo(constpool, method.getName(), s1)).setAccessFlags(0x10 | method.getModifiers() & 0xfffffadf);
/*1247*/        setThrows(class1, constpool, method);
/*1248*/        int j = Descriptor.paramSize(s1);
/*1249*/        constpool = new Bytecode(constpool, 0, j + 2);
/*1262*/        int k = (i <<= 1) + 1;
/*1264*/        j++;
/*1265*/        constpool.addGetstatic(s, "_methods_", "[Ljava/lang/reflect/Method;");
/*1266*/        constpool.addAstore(j);
/*1268*/        arraylist.add(new Find2MethodsArgs(method.getName(), s2, s1, i));
/*1270*/        constpool.addAload(0);
/*1271*/        constpool.addGetfield(s, "handler", HANDLER_TYPE);
/*1272*/        constpool.addAload(0);
/*1274*/        constpool.addAload(j);
/*1275*/        constpool.addIconst(i);
/*1276*/        constpool.addOpcode(50);
/*1278*/        constpool.addAload(j);
/*1279*/        constpool.addIconst(k);
/*1280*/        constpool.addOpcode(50);
/*1282*/        makeParameterList(constpool, method.getParameterTypes());
/*1283*/        constpool.addInvokeinterface(javassist/util/proxy/MethodHandler.getName(), "invoke", "(Ljava/lang/Object;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", 5);
/*1286*/        s = method.getReturnType();
/*1287*/        addUnwrapper(constpool, s);
/*1288*/        addReturn(constpool, s);
/*1290*/        s = constpool.toCodeAttribute();
/*1291*/        class1.setCodeAttribute(s);
/*1292*/        return class1;
            }

            private static void setThrows(MethodInfo methodinfo, ConstPool constpool, Method method)
            {
/*1308*/        method = method.getExceptionTypes();
/*1309*/        setThrows(methodinfo, constpool, ((Class []) (method)));
            }

            private static void setThrows(MethodInfo methodinfo, ConstPool constpool, Class aclass[])
            {
/*1314*/        if(aclass.length == 0)
/*1315*/            return;
/*1317*/        String as[] = new String[aclass.length];
/*1318*/        for(int i = 0; i < aclass.length; i++)
/*1319*/            as[i] = aclass[i].getName();

                ExceptionsAttribute exceptionsattribute;
/*1321*/        (exceptionsattribute = new ExceptionsAttribute(constpool)).setExceptions(as);
/*1323*/        methodinfo.setExceptionsAttribute(exceptionsattribute);
            }

            private static int addLoadParameters(Bytecode bytecode, Class aclass[], int i)
            {
/*1328*/        int j = 0;
/*1329*/        int k = aclass.length;
/*1330*/        for(int l = 0; l < k; l++)
/*1331*/            j += addLoad(bytecode, j + i, aclass[l]);

/*1333*/        return j;
            }

            private static int addLoad(Bytecode bytecode, int i, Class class1)
            {
/*1337*/        if(class1.isPrimitive())
                {
/*1338*/            if(class1 == Long.TYPE)
                    {
/*1339*/                bytecode.addLload(i);
/*1340*/                return 2;
                    }
/*1342*/            if(class1 == Float.TYPE)
                    {
/*1343*/                bytecode.addFload(i);
                    } else
                    {
/*1344*/                if(class1 == Double.TYPE)
                        {
/*1345*/                    bytecode.addDload(i);
/*1346*/                    return 2;
                        }
/*1349*/                bytecode.addIload(i);
                    }
                } else
                {
/*1352*/            bytecode.addAload(i);
                }
/*1354*/        return 1;
            }

            private static int addReturn(Bytecode bytecode, Class class1)
            {
/*1358*/        if(class1.isPrimitive())
                {
/*1359*/            if(class1 == Long.TYPE)
                    {
/*1360*/                bytecode.addOpcode(173);
/*1361*/                return 2;
                    }
/*1363*/            if(class1 == Float.TYPE)
                    {
/*1364*/                bytecode.addOpcode(174);
                    } else
                    {
/*1365*/                if(class1 == Double.TYPE)
                        {
/*1366*/                    bytecode.addOpcode(175);
/*1367*/                    return 2;
                        }
/*1369*/                if(class1 == Void.TYPE)
                        {
/*1370*/                    bytecode.addOpcode(177);
/*1371*/                    return 0;
                        }
/*1374*/                bytecode.addOpcode(172);
                    }
                } else
                {
/*1377*/            bytecode.addOpcode(176);
                }
/*1379*/        return 1;
            }

            private static void makeParameterList(Bytecode bytecode, Class aclass[])
            {
/*1383*/        int i = 1;
/*1384*/        int j = aclass.length;
/*1385*/        bytecode.addIconst(j);
/*1386*/        bytecode.addAnewarray("java/lang/Object");
/*1387*/        for(int k = 0; k < j; k++)
                {
/*1388*/            bytecode.addOpcode(89);
/*1389*/            bytecode.addIconst(k);
                    Class class1;
/*1390*/            if((class1 = aclass[k]).isPrimitive())
                    {
/*1392*/                i = makeWrapper(bytecode, class1, i);
                    } else
                    {
/*1394*/                bytecode.addAload(i);
/*1395*/                i++;
                    }
/*1398*/            bytecode.addOpcode(83);
                }

            }

            private static int makeWrapper(Bytecode bytecode, Class class1, int i)
            {
/*1403*/        int j = FactoryHelper.typeIndex(class1);
/*1404*/        String s = FactoryHelper.wrapperTypes[j];
/*1405*/        bytecode.addNew(s);
/*1406*/        bytecode.addOpcode(89);
/*1407*/        addLoad(bytecode, i, class1);
/*1408*/        bytecode.addInvokespecial(s, "<init>", FactoryHelper.wrapperDesc[j]);
/*1410*/        return i + FactoryHelper.dataSize[j];
            }

            private static void addUnwrapper(Bytecode bytecode, Class class1)
            {
/*1414*/        if(class1.isPrimitive())
                {
/*1415*/            if(class1 == Void.TYPE)
                    {
/*1416*/                bytecode.addOpcode(87);
/*1416*/                return;
                    } else
                    {
/*1418*/                class1 = FactoryHelper.typeIndex(class1);
/*1419*/                String s = FactoryHelper.wrapperTypes[class1];
/*1420*/                bytecode.addCheckcast(s);
/*1421*/                bytecode.addInvokevirtual(s, FactoryHelper.unwarpMethods[class1], FactoryHelper.unwrapDesc[class1]);
/*1424*/                return;
                    }
                } else
                {
/*1427*/            bytecode.addCheckcast(class1.getName());
/*1428*/            return;
                }
            }

            private static MethodInfo makeWriteReplace(ConstPool constpool)
            {
/*1431*/        MethodInfo methodinfo = new MethodInfo(constpool, "writeReplace", "()Ljava/lang/Object;");
                String as[];
/*1432*/        (as = new String[1])[0] = "java.io.ObjectStreamException";
                ExceptionsAttribute exceptionsattribute;
/*1434*/        (exceptionsattribute = new ExceptionsAttribute(constpool)).setExceptions(as);
/*1436*/        methodinfo.setExceptionsAttribute(exceptionsattribute);
/*1437*/        (constpool = new Bytecode(constpool, 0, 1)).addAload(0);
/*1439*/        constpool.addInvokestatic("javassist.util.proxy.RuntimeSupport", "makeSerializedProxy", "(Ljava/lang/Object;)Ljavassist/util/proxy/SerializedProxy;");
/*1442*/        constpool.addOpcode(176);
/*1443*/        methodinfo.setCodeAttribute(constpool.toCodeAttribute());
/*1444*/        return methodinfo;
            }

            private Class superClass;
            private Class interfaces[];
            private MethodFilter methodFilter;
            private MethodHandler handler;
            private List signatureMethods;
            private boolean hasGetHandler;
            private byte signature[];
            private String classname;
            private String basename;
            private String superName;
            private Class thisClass;
            private boolean factoryUseCache;
            private boolean factoryWriteReplace;
            public String writeDirectory;
            private static final Class OBJECT_TYPE = java/lang/Object;
            private static final String HOLDER = "_methods_";
            private static final String HOLDER_TYPE = "[Ljava/lang/reflect/Method;";
            private static final String FILTER_SIGNATURE_FIELD = "_filter_signature";
            private static final String FILTER_SIGNATURE_TYPE = "[B";
            private static final String HANDLER = "handler";
            private static final String NULL_INTERCEPTOR_HOLDER = "javassist.util.proxy.RuntimeSupport";
            private static final String DEFAULT_INTERCEPTOR = "default_interceptor";
            private static final String HANDLER_TYPE = (new StringBuilder("L")).append(javassist/util/proxy/MethodHandler.getName().replace('.', '/')).append(';').toString();
            private static final String HANDLER_SETTER = "setHandler";
            private static final String HANDLER_SETTER_TYPE = (new StringBuilder("(")).append(HANDLER_TYPE).append(")V").toString();
            private static final String HANDLER_GETTER = "getHandler";
            private static final String HANDLER_GETTER_TYPE = (new StringBuilder("()")).append(HANDLER_TYPE).toString();
            private static final String SERIAL_VERSION_UID_FIELD = "serialVersionUID";
            private static final String SERIAL_VERSION_UID_TYPE = "J";
            private static final long SERIAL_VERSION_UID_VALUE = -1L;
            public static volatile boolean useCache = true;
            public static volatile boolean useWriteReplace = true;
            private static WeakHashMap proxyCache = new WeakHashMap();
            private static char hexDigits[] = {
/* 441*/        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
/* 441*/        'a', 'b', 'c', 'd', 'e', 'f'
            };
            public static ClassLoaderProvider classLoaderProvider = new ClassLoaderProvider() {

                public final ClassLoader get(ProxyFactory proxyfactory)
                {
/* 608*/            return proxyfactory.getClassLoader0();
                }

    };
            public static UniqueName nameGenerator = new UniqueName() {

                public final String get(String s)
                {
/* 727*/            return (new StringBuilder()).append(s).append(sep).append(Integer.toHexString(counter++)).toString();
                }

                private final String sep = (new StringBuilder("_$$_jvst")).append(Integer.toHexString(hashCode() & 0xfff)).append("_").toString();
                private int counter;

                    
                    {
/* 724*/                counter = 0;
                    }
    };
            private static Comparator sorter = new Comparator() {

                public final int compare(Object obj, Object obj1)
                {
/* 817*/            obj = (java.util.Map.Entry)obj;
/* 818*/            obj1 = (java.util.Map.Entry)obj1;
/* 819*/            obj = (String)((java.util.Map.Entry) (obj)).getKey();
/* 820*/            obj1 = (String)((java.util.Map.Entry) (obj1)).getKey();
/* 821*/            return ((String) (obj)).compareTo(((String) (obj1)));
                }

    };
            private static final String HANDLER_GETTER_KEY = "getHandler:()";

}
