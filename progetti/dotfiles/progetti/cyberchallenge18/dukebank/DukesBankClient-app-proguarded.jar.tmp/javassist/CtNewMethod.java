// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtNewMethod.java

package javassist;

import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ExceptionsAttribute;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.MethodInfo;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;

// Referenced classes of package javassist:
//            CannotCompileException, CtClass, CtField, CtMethod, 
//            CtNewWrappedMethod, Modifier, NotFoundException, ClassMap

public class CtNewMethod
{

            public CtNewMethod()
            {
            }

            public static CtMethod make(String s, CtClass ctclass)
                throws CannotCompileException
            {
/*  45*/        return make(s, ctclass, null, null);
            }

            public static CtMethod make(String s, CtClass ctclass, String s1, String s2)
                throws CannotCompileException
            {
/*  69*/        ctclass = new Javac(ctclass);
/*  71*/        if(s2 != null)
/*  72*/            ctclass.recordProceed(s1, s2);
/*  74*/        if((s = ctclass.compile(s)) instanceof CtMethod)
/*  76*/            return (CtMethod)s;
/*  82*/        else
/*  82*/            throw new CannotCompileException("not a method");
/*  78*/        s;
/*  79*/        throw new CannotCompileException(s);
            }

            public static CtMethod make(CtClass ctclass, String s, CtClass actclass[], CtClass actclass1[], String s1, CtClass ctclass1)
                throws CannotCompileException
            {
/* 106*/        return make(1, ctclass, s, actclass, actclass1, s1, ctclass1);
            }

            public static CtMethod make(int i, CtClass ctclass, String s, CtClass actclass[], CtClass actclass1[], String s1, CtClass ctclass1)
                throws CannotCompileException
            {
/* 134*/        (ctclass = new CtMethod(ctclass, s, actclass, ctclass1)).setModifiers(i);
/* 137*/        ctclass.setExceptionTypes(actclass1);
/* 138*/        ctclass.setBody(s1);
/* 139*/        return ctclass;
/* 141*/        ctclass;
/* 142*/        throw new CannotCompileException(ctclass);
            }

            public static CtMethod copy(CtMethod ctmethod, CtClass ctclass, ClassMap classmap)
                throws CannotCompileException
            {
/* 163*/        return new CtMethod(ctmethod, ctclass, classmap);
            }

            public static CtMethod copy(CtMethod ctmethod, String s, CtClass ctclass, ClassMap classmap)
                throws CannotCompileException
            {
/* 185*/        (ctmethod = new CtMethod(ctmethod, ctclass, classmap)).setName(s);
/* 187*/        return ctmethod;
            }

            public static CtMethod abstractMethod(CtClass ctclass, String s, CtClass actclass[], CtClass actclass1[], CtClass ctclass1)
                throws NotFoundException
            {
/* 208*/        (ctclass = new CtMethod(ctclass, s, actclass, ctclass1)).setExceptionTypes(actclass1);
/* 210*/        return ctclass;
            }

            public static CtMethod getter(String s, CtField ctfield)
                throws CannotCompileException
            {
                FieldInfo fieldinfo;
/* 225*/        String s1 = (fieldinfo = ctfield.getFieldInfo2()).getDescriptor();
/* 227*/        Object obj = (new StringBuilder("()")).append(s1).toString();
/* 228*/        Object obj1 = fieldinfo.getConstPool();
/* 229*/        (s = new MethodInfo(((ConstPool) (obj1)), s, ((String) (obj)))).setAccessFlags(1);
/* 232*/        obj = new Bytecode(((ConstPool) (obj1)), 2, 1);
/* 234*/        try
                {
/* 234*/            obj1 = fieldinfo.getName();
/* 235*/            if((fieldinfo.getAccessFlags() & 8) == 0)
                    {
/* 236*/                ((Bytecode) (obj)).addAload(0);
/* 237*/                ((Bytecode) (obj)).addGetfield(Bytecode.THIS, ((String) (obj1)), s1);
                    } else
                    {
/* 240*/                ((Bytecode) (obj)).addGetstatic(Bytecode.THIS, ((String) (obj1)), s1);
                    }
/* 242*/            ((Bytecode) (obj)).addReturn(ctfield.getType());
                }
/* 244*/        catch(NotFoundException notfoundexception)
                {
/* 245*/            throw new CannotCompileException(notfoundexception);
                }
/* 248*/        s.setCodeAttribute(((Bytecode) (obj)).toCodeAttribute());
/* 249*/        notfoundexception = ctfield.getDeclaringClass();
/* 251*/        return new CtMethod(s, notfoundexception);
            }

            public static CtMethod setter(String s, CtField ctfield)
                throws CannotCompileException
            {
                FieldInfo fieldinfo;
/* 268*/        String s1 = (fieldinfo = ctfield.getFieldInfo2()).getDescriptor();
/* 270*/        Object obj = (new StringBuilder("(")).append(s1).append(")V").toString();
/* 271*/        Object obj1 = fieldinfo.getConstPool();
/* 272*/        (s = new MethodInfo(((ConstPool) (obj1)), s, ((String) (obj)))).setAccessFlags(1);
/* 275*/        obj = new Bytecode(((ConstPool) (obj1)), 3, 3);
/* 277*/        try
                {
/* 277*/            obj1 = fieldinfo.getName();
/* 278*/            if((fieldinfo.getAccessFlags() & 8) == 0)
                    {
/* 279*/                ((Bytecode) (obj)).addAload(0);
/* 280*/                ((Bytecode) (obj)).addLoad(1, ctfield.getType());
/* 281*/                ((Bytecode) (obj)).addPutfield(Bytecode.THIS, ((String) (obj1)), s1);
                    } else
                    {
/* 284*/                ((Bytecode) (obj)).addLoad(1, ctfield.getType());
/* 285*/                ((Bytecode) (obj)).addPutstatic(Bytecode.THIS, ((String) (obj1)), s1);
                    }
/* 288*/            ((Bytecode) (obj)).addReturn(null);
                }
/* 290*/        catch(NotFoundException notfoundexception)
                {
/* 291*/            throw new CannotCompileException(notfoundexception);
                }
/* 294*/        s.setCodeAttribute(((Bytecode) (obj)).toCodeAttribute());
/* 295*/        notfoundexception = ctfield.getDeclaringClass();
/* 297*/        return new CtMethod(s, notfoundexception);
            }

            public static CtMethod delegator(CtMethod ctmethod, CtClass ctclass)
                throws CannotCompileException
            {
/* 325*/        return delegator0(ctmethod, ctclass);
/* 327*/        ctmethod;
/* 328*/        throw new CannotCompileException(ctmethod);
            }

            private static CtMethod delegator0(CtMethod ctmethod, CtClass ctclass)
                throws CannotCompileException, NotFoundException
            {
                Object obj;
/* 335*/        String s = ((MethodInfo) (obj = ctmethod.getMethodInfo2())).getName();
/* 337*/        String s1 = ((MethodInfo) (obj)).getDescriptor();
/* 338*/        ConstPool constpool = ctclass.getClassFile2().getConstPool();
                MethodInfo methodinfo;
/* 339*/        (methodinfo = new MethodInfo(constpool, s, s1)).setAccessFlags(((MethodInfo) (obj)).getAccessFlags());
/* 342*/        if((obj = ((MethodInfo) (obj)).getExceptionsAttribute()) != null)
/* 344*/            methodinfo.setExceptionsAttribute((ExceptionsAttribute)((ExceptionsAttribute) (obj)).copy(constpool, null));
/* 347*/        obj = new Bytecode(constpool, 0, 0);
/* 348*/        boolean flag = Modifier.isStatic(ctmethod.getModifiers());
/* 349*/        CtClass ctclass1 = ctmethod.getDeclaringClass();
/* 350*/        CtClass actclass[] = ctmethod.getParameterTypes();
                int i;
/* 352*/        if(flag)
                {
/* 353*/            i = ((Bytecode) (obj)).addLoadParameters(actclass, 0);
/* 354*/            ((Bytecode) (obj)).addInvokestatic(ctclass1, s, s1);
                } else
                {
/* 357*/            ((Bytecode) (obj)).addLoad(0, ctclass1);
/* 358*/            i = ((Bytecode) (obj)).addLoadParameters(actclass, 1);
/* 359*/            ((Bytecode) (obj)).addInvokespecial(ctclass1, s, s1);
                }
/* 362*/        ((Bytecode) (obj)).addReturn(ctmethod.getReturnType());
/* 363*/        ((Bytecode) (obj)).setMaxLocals(++i);
/* 364*/        ((Bytecode) (obj)).setMaxStack(i >= 2 ? i : 2);
/* 365*/        methodinfo.setCodeAttribute(((Bytecode) (obj)).toCodeAttribute());
/* 367*/        return new CtMethod(methodinfo, ctclass);
            }

            public static CtMethod wrapped(CtClass ctclass, String s, CtClass actclass[], CtClass actclass1[], CtMethod ctmethod, CtMethod.ConstParameter constparameter, CtClass ctclass1)
                throws CannotCompileException
            {
/* 473*/        return CtNewWrappedMethod.wrapped(ctclass, s, actclass, actclass1, ctmethod, constparameter, ctclass1);
            }
}
