// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StubGenerator.java

package javassist.tools.rmi;

import java.lang.reflect.Method;
import java.util.Hashtable;
import javassist.*;

public class StubGenerator
    implements Translator
{

            public StubGenerator()
            {
/*  62*/        proxyClasses = new Hashtable();
            }

            public void start(ClassPool classpool)
                throws NotFoundException
            {
/*  72*/        classPool = classpool;
/*  73*/        CtClass ctclass = classpool.get("javassist.tools.rmi.Sample");
/*  74*/        forwardMethod = ctclass.getDeclaredMethod("forward");
/*  75*/        forwardStaticMethod = ctclass.getDeclaredMethod("forwardStatic");
/*  77*/        proxyConstructorParamTypes = classpool.get(new String[] {
/*  77*/            "javassist.tools.rmi.ObjectImporter", "int"
                });
/*  80*/        interfacesForProxy = classpool.get(new String[] {
/*  80*/            "java.io.Serializable", "javassist.tools.rmi.Proxy"
                });
/*  83*/        exceptionForProxy = (new CtClass[] {
/*  83*/            classpool.get("javassist.tools.rmi.RemoteException")
                });
            }

            public void onLoad(ClassPool classpool, String s)
            {
            }

            public boolean isProxyClass(String s)
            {
/* 101*/        return proxyClasses.get(s) != null;
            }

            public synchronized boolean makeProxyClass(Class class1)
                throws CannotCompileException, NotFoundException
            {
/* 116*/        String s = class1.getName();
/* 117*/        if(proxyClasses.get(s) != null)
                {
/* 118*/            return false;
                } else
                {
/* 120*/            class1 = produceProxyClass(classPool.get(s), class1);
/* 122*/            proxyClasses.put(s, class1);
/* 123*/            modifySuperclass(class1);
/* 124*/            return true;
                }
            }

            private CtClass produceProxyClass(CtClass ctclass, Class class1)
                throws CannotCompileException, NotFoundException
            {
                int i;
/* 131*/        if(Modifier.isAbstract(i = ctclass.getModifiers()) || Modifier.isNative(i) || !Modifier.isPublic(i))
/* 134*/            throw new CannotCompileException((new StringBuilder()).append(ctclass.getName()).append(" must be public, non-native, and non-abstract.").toString());
/* 137*/        (ctclass = classPool.makeClass(ctclass.getName(), ctclass.getSuperclass())).setInterfaces(interfacesForProxy);
                Object obj;
/* 142*/        ((CtField) (obj = new CtField(classPool.get("javassist.tools.rmi.ObjectImporter"), "importer", ctclass))).setModifiers(2);
/* 146*/        ctclass.addField(((CtField) (obj)), javassist.CtField.Initializer.byParameter(0));
/* 148*/        ((CtField) (obj = new CtField(CtClass.intType, "objectId", ctclass))).setModifiers(2);
/* 150*/        ctclass.addField(((CtField) (obj)), javassist.CtField.Initializer.byParameter(1));
/* 152*/        ctclass.addMethod(CtNewMethod.getter("_getObjectId", ((CtField) (obj))));
/* 154*/        ctclass.addConstructor(CtNewConstructor.defaultConstructor(ctclass));
/* 155*/        obj = CtNewConstructor.skeleton(proxyConstructorParamTypes, null, ctclass);
/* 158*/        ctclass.addConstructor(((javassist.CtConstructor) (obj)));
/* 161*/        addMethods(ctclass, class1.getMethods());
/* 162*/        return ctclass;
/* 164*/        ctclass;
/* 165*/        throw new CannotCompileException(ctclass);
            }

            private CtClass toCtClass(Class class1)
                throws NotFoundException
            {
/* 171*/        if(!class1.isArray())
                {
/* 172*/            class1 = class1.getName();
                } else
                {
/* 174*/            StringBuffer stringbuffer = new StringBuffer();
/* 176*/            do
/* 176*/                stringbuffer.append("[]");
/* 177*/            while((class1 = class1.getComponentType()).isArray());
/* 179*/            stringbuffer.insert(0, class1.getName());
/* 180*/            class1 = stringbuffer.toString();
                }
/* 183*/        return classPool.get(class1);
            }

            private CtClass[] toCtClass(Class aclass[])
                throws NotFoundException
            {
                int i;
/* 187*/        CtClass actclass[] = new CtClass[i = aclass.length];
/* 189*/        for(int j = 0; j < i; j++)
/* 190*/            actclass[j] = toCtClass(aclass[j]);

/* 192*/        return actclass;
            }

            private void addMethods(CtClass ctclass, Method amethod[])
                throws CannotCompileException, NotFoundException
            {
/* 202*/        for(int i = 0; i < amethod.length; i++)
                {
                    Object obj;
/* 203*/            int j = ((Method) (obj = amethod[i])).getModifiers();
/* 205*/            if(((Method) (obj)).getDeclaringClass() == java/lang/Object || Modifier.isFinal(j))
/* 207*/                continue;
/* 207*/            if(Modifier.isPublic(j))
                    {
                        CtMethod ctmethod;
/* 209*/                if(Modifier.isStatic(j))
/* 210*/                    ctmethod = forwardStaticMethod;
/* 212*/                else
/* 212*/                    ctmethod = forwardMethod;
/* 214*/                ((CtMethod) (obj = CtNewMethod.wrapped(toCtClass(((Method) (obj)).getReturnType()), ((Method) (obj)).getName(), toCtClass(((Method) (obj)).getParameterTypes()), exceptionForProxy, ctmethod, javassist.CtMethod.ConstParameter.integer(i), ctclass))).setModifiers(j);
/* 223*/                ctclass.addMethod(((CtMethod) (obj)));
/* 224*/                continue;
                    }
/* 225*/            if(!Modifier.isProtected(j) && !Modifier.isPrivate(j))
/* 228*/                throw new CannotCompileException("the methods must be public, protected, or private.");
                }

            }

            private void modifySuperclass(CtClass ctclass)
                throws CannotCompileException, NotFoundException
            {
/* 241*/        for(; (ctclass = ctclass.getSuperclass()) != null; ctclass = ctclass)
/* 246*/            try
                    {
/* 246*/                ctclass.getDeclaredConstructor(null);
/* 247*/                return;
                    }
/* 249*/            catch(NotFoundException _ex)
                    {
/* 252*/                ctclass.addConstructor(CtNewConstructor.defaultConstructor(ctclass));
                    }

            }

            private static final String fieldImporter = "importer";
            private static final String fieldObjectId = "objectId";
            private static final String accessorObjectId = "_getObjectId";
            private static final String sampleClass = "javassist.tools.rmi.Sample";
            private ClassPool classPool;
            private Hashtable proxyClasses;
            private CtMethod forwardMethod;
            private CtMethod forwardStaticMethod;
            private CtClass proxyConstructorParamTypes[];
            private CtClass interfacesForProxy[];
            private CtClass exceptionForProxy[];
}
