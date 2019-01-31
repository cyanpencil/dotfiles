// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Reflection.java

package javassist.tools.reflect;

import java.util.Iterator;
import java.util.List;
import javassist.*;
import javassist.bytecode.*;

// Referenced classes of package javassist.tools.reflect:
//            CannotReflectException

public class Reflection
    implements Translator
{

            private boolean isExcluded(String s)
            {
/*  89*/        return s.startsWith("_m_") || s.equals("_getClass") || s.equals("_setMetaobject") || s.equals("_getMetaobject") || s.startsWith("_r_") || s.startsWith("_w_");
            }

            public Reflection()
            {
/* 101*/        classPool = null;
/* 102*/        converter = new CodeConverter();
            }

            public void start(ClassPool classpool)
                throws NotFoundException
            {
/* 109*/        classPool = classpool;
/* 113*/        try
                {
/* 113*/            classpool = classPool.get("javassist.tools.reflect.Sample");
/* 114*/            rebuildClassFile(classpool.getClassFile());
/* 115*/            trapMethod = classpool.getDeclaredMethod("trap");
/* 116*/            trapStaticMethod = classpool.getDeclaredMethod("trapStatic");
/* 117*/            trapRead = classpool.getDeclaredMethod("trapRead");
/* 118*/            trapWrite = classpool.getDeclaredMethod("trapWrite");
/* 119*/            readParam = (new CtClass[] {
/* 119*/                classPool.get("java.lang.Object")
                    });
/* 126*/            return;
                }
/* 122*/        catch(NotFoundException _ex)
                {
/* 123*/            throw new RuntimeException("javassist.tools.reflect.Sample is not found or broken.");
                }
/* 124*/        catch(BadBytecode _ex)
                {
/* 125*/            throw new RuntimeException("javassist.tools.reflect.Sample is not found or broken.");
                }
            }

            public void onLoad(ClassPool classpool, String s)
                throws CannotCompileException, NotFoundException
            {
/* 136*/        (classpool = classpool.get(s)).instrument(converter);
            }

            public boolean makeReflective(String s, String s1, String s2)
                throws CannotCompileException, NotFoundException
            {
/* 157*/        return makeReflective(classPool.get(s), classPool.get(s1), classPool.get(s2));
            }

            public boolean makeReflective(Class class1, Class class2, Class class3)
                throws CannotCompileException, NotFoundException
            {
/* 183*/        return makeReflective(class1.getName(), class2.getName(), class3.getName());
            }

            public boolean makeReflective(CtClass ctclass, CtClass ctclass1, CtClass ctclass2)
                throws CannotCompileException, CannotReflectException, NotFoundException
            {
/* 210*/        if(ctclass.isInterface())
/* 211*/            throw new CannotReflectException((new StringBuilder("Cannot reflect an interface: ")).append(ctclass.getName()).toString());
/* 214*/        if(ctclass.subclassOf(classPool.get("javassist.tools.reflect.ClassMetaobject")))
/* 215*/            throw new CannotReflectException((new StringBuilder("Cannot reflect a subclass of ClassMetaobject: ")).append(ctclass.getName()).toString());
/* 219*/        if(ctclass.subclassOf(classPool.get("javassist.tools.reflect.Metaobject")))
                {
/* 220*/            throw new CannotReflectException((new StringBuilder("Cannot reflect a subclass of Metaobject: ")).append(ctclass.getName()).toString());
                } else
                {
/* 224*/            registerReflectiveClass(ctclass);
/* 225*/            return modifyClassfile(ctclass, ctclass1, ctclass2);
                }
            }

            private void registerReflectiveClass(CtClass ctclass)
            {
/* 233*/        CtField actfield[] = ctclass.getDeclaredFields();
/* 234*/        for(int i = 0; i < actfield.length; i++)
                {
                    CtField ctfield;
                    int j;
/* 235*/            if(((j = (ctfield = actfield[i]).getModifiers()) & 1) != 0 && (j & 0x10) == 0)
                    {
/* 238*/                String s = ctfield.getName();
/* 239*/                converter.replaceFieldRead(ctfield, ctclass, (new StringBuilder("_r_")).append(s).toString());
/* 240*/                converter.replaceFieldWrite(ctfield, ctclass, (new StringBuilder("_w_")).append(s).toString());
                    }
                }

            }

            private boolean modifyClassfile(CtClass ctclass, CtClass ctclass1, CtClass ctclass2)
                throws CannotCompileException, NotFoundException
            {
/* 249*/        if(ctclass.getAttribute("Reflective") != null)
/* 250*/            return false;
/* 252*/        ctclass.setAttribute("Reflective", new byte[0]);
/* 254*/        Object obj = classPool.get("javassist.tools.reflect.Metalevel");
                boolean flag;
/* 255*/        if(flag = !ctclass.subtypeOf(((CtClass) (obj))))
/* 257*/            ctclass.addInterface(((CtClass) (obj)));
/* 259*/        processMethods(ctclass, flag);
/* 260*/        processFields(ctclass);
/* 263*/        if(flag)
                {
/* 264*/            ((CtField) (obj = new CtField(classPool.get("javassist.tools.reflect.Metaobject"), "_metaobject", ctclass))).setModifiers(4);
/* 267*/            ctclass.addField(((CtField) (obj)), javassist.CtField.Initializer.byNewWithParams(ctclass1));
/* 269*/            ctclass.addMethod(CtNewMethod.getter("_getMetaobject", ((CtField) (obj))));
/* 270*/            ctclass.addMethod(CtNewMethod.setter("_setMetaobject", ((CtField) (obj))));
                }
/* 273*/        ((CtField) (obj = new CtField(classPool.get("javassist.tools.reflect.ClassMetaobject"), "_classobject", ctclass))).setModifiers(10);
/* 276*/        ctclass.addField(((CtField) (obj)), javassist.CtField.Initializer.byNew(ctclass2, new String[] {
/* 276*/            ctclass.getName()
                }));
/* 279*/        ctclass.addMethod(CtNewMethod.getter("_getClass", ((CtField) (obj))));
/* 280*/        return true;
            }

            private void processMethods(CtClass ctclass, boolean flag)
                throws CannotCompileException, NotFoundException
            {
/* 286*/        CtMethod actmethod[] = ctclass.getMethods();
/* 287*/        for(int i = 0; i < actmethod.length; i++)
                {
                    CtMethod ctmethod;
                    int j;
/* 288*/            if(Modifier.isPublic(j = (ctmethod = actmethod[i]).getModifiers()) && !Modifier.isAbstract(j))
/* 291*/                processMethods0(j, ctclass, ctmethod, i, flag);
                }

            }

            private void processMethods0(int i, CtClass ctclass, CtMethod ctmethod, int j, boolean flag)
                throws CannotCompileException, NotFoundException
            {
/* 300*/        String s = ctmethod.getName();
/* 302*/        if(isExcluded(s))
/* 303*/            return;
/* 306*/        if(ctmethod.getDeclaringClass() == ctclass)
                {
/* 307*/            if(Modifier.isNative(i))
/* 308*/                return;
/* 310*/            flag = ctmethod;
/* 311*/            if(Modifier.isFinal(i))
                    {
/* 312*/                i &= 0xffffffef;
/* 313*/                flag.setModifiers(i);
                    }
                } else
                {
/* 317*/            if(Modifier.isFinal(i))
/* 318*/                return;
/* 320*/            i &= 0xfffffeff;
/* 321*/            (flag = CtNewMethod.delegator(findOriginal(ctmethod, flag), ctclass)).setModifiers(i);
/* 323*/            ctclass.addMethod(flag);
                }
/* 326*/        flag.setName((new StringBuilder("_m_")).append(j).append("_").append(s).toString());
/* 329*/        if(Modifier.isStatic(i))
/* 330*/            flag = trapStaticMethod;
/* 332*/        else
/* 332*/            flag = trapMethod;
/* 334*/        (ctmethod = CtNewMethod.wrapped(ctmethod.getReturnType(), s, ctmethod.getParameterTypes(), ctmethod.getExceptionTypes(), flag, javassist.CtMethod.ConstParameter.integer(j), ctclass)).setModifiers(i);
/* 340*/        ctclass.addMethod(ctmethod);
            }

            private CtMethod findOriginal(CtMethod ctmethod, boolean flag)
                throws NotFoundException
            {
/* 346*/        if(flag)
/* 347*/            return ctmethod;
/* 349*/        flag = ctmethod.getName();
/* 350*/        CtMethod actmethod[] = ctmethod.getDeclaringClass().getDeclaredMethods();
/* 351*/        for(int i = 0; i < actmethod.length; i++)
                {
                    String s;
/* 352*/            if((s = actmethod[i].getName()).endsWith(flag) && s.startsWith("_m_") && actmethod[i].getSignature().equals(ctmethod.getSignature()))
/* 356*/                return actmethod[i];
                }

/* 359*/        return ctmethod;
            }

            private void processFields(CtClass ctclass)
                throws CannotCompileException, NotFoundException
            {
/* 365*/        CtField actfield[] = ctclass.getDeclaredFields();
/* 366*/        for(int i = 0; i < actfield.length; i++)
                {
                    Object obj;
                    int j;
/* 367*/            if(((j = ((CtField) (obj = actfield[i])).getModifiers()) & 1) != 0 && (j & 0x10) == 0)
                    {
/* 370*/                j |= 8;
/* 371*/                String s = ((CtField) (obj)).getName();
                        CtMethod ctmethod;
/* 372*/                (ctmethod = CtNewMethod.wrapped(((CtClass) (obj = ((CtField) (obj)).getType())), (new StringBuilder("_r_")).append(s).toString(), readParam, null, trapRead, javassist.CtMethod.ConstParameter.string(s), ctclass)).setModifiers(j);
/* 379*/                ctclass.addMethod(ctmethod);
                        CtClass actclass[];
/* 380*/                (actclass = new CtClass[2])[0] = classPool.get("java.lang.Object");
/* 382*/                actclass[1] = ((CtClass) (obj));
/* 383*/                (actclass = CtNewMethod.wrapped(CtClass.voidType, (new StringBuilder("_w_")).append(s).toString(), actclass, null, trapWrite, javassist.CtMethod.ConstParameter.string(s), ctclass)).setModifiers(j);
/* 388*/                ctclass.addMethod(actclass);
                    }
                }

            }

            public void rebuildClassFile(ClassFile classfile)
                throws BadBytecode
            {
/* 394*/        if(ClassFile.MAJOR_VERSION < 50)
/* 395*/            return;
                MethodInfo methodinfo;
/* 397*/        for(classfile = classfile.getMethods().iterator(); classfile.hasNext(); (methodinfo = (MethodInfo)classfile.next()).rebuildStackMap(classPool));
            }

            static final String classobjectField = "_classobject";
            static final String classobjectAccessor = "_getClass";
            static final String metaobjectField = "_metaobject";
            static final String metaobjectGetter = "_getMetaobject";
            static final String metaobjectSetter = "_setMetaobject";
            static final String readPrefix = "_r_";
            static final String writePrefix = "_w_";
            static final String metaobjectClassName = "javassist.tools.reflect.Metaobject";
            static final String classMetaobjectClassName = "javassist.tools.reflect.ClassMetaobject";
            protected CtMethod trapMethod;
            protected CtMethod trapStaticMethod;
            protected CtMethod trapRead;
            protected CtMethod trapWrite;
            protected CtClass readParam[];
            protected ClassPool classPool;
            protected CodeConverter converter;
}
