// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtNewWrappedMethod.java

package javassist;

import java.util.Hashtable;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.SyntheticAttribute;
import javassist.compiler.JvstCodeGen;

// Referenced classes of package javassist:
//            CannotCompileException, ClassMap, CtClass, CtClassType, 
//            CtMember, CtMethod, CtPrimitiveType, Modifier, 
//            NotFoundException

class CtNewWrappedMethod
{

            CtNewWrappedMethod()
            {
            }

            public static CtMethod wrapped(CtClass ctclass, String s, CtClass actclass[], CtClass actclass1[], CtMethod ctmethod, CtMethod.ConstParameter constparameter, CtClass ctclass1)
                throws CannotCompileException
            {
/*  35*/        (s = new CtMethod(ctclass, s, actclass, ctclass1)).setModifiers(ctmethod.getModifiers());
/*  39*/        try
                {
/*  39*/            s.setExceptionTypes(actclass1);
                }
                // Misplaced declaration of an exception variable
/*  41*/        catch(CtClass ctclass)
                {
/*  42*/            throw new CannotCompileException(ctclass);
                }
/*  45*/        ctclass = makeBody(ctclass1, ctclass1.getClassFile2(), ctmethod, actclass, ctclass, constparameter);
/*  47*/        (actclass = s.getMethodInfo2()).setCodeAttribute(ctclass.toCodeAttribute());
/*  50*/        return s;
            }

            static Bytecode makeBody(CtClass ctclass, ClassFile classfile, CtMethod ctmethod, CtClass actclass[], CtClass ctclass1, CtMethod.ConstParameter constparameter)
                throws CannotCompileException
            {
/*  60*/        boolean flag = Modifier.isStatic(ctmethod.getModifiers());
/*  61*/        Bytecode bytecode = new Bytecode(classfile.getConstPool(), 0, 0);
/*  62*/        ctclass = makeBody0(ctclass, classfile, ctmethod, flag, actclass, ctclass1, constparameter, bytecode);
/*  64*/        bytecode.setMaxStack(ctclass);
/*  65*/        bytecode.setMaxLocals(flag, actclass, 0);
/*  66*/        return bytecode;
            }

            protected static int makeBody0(CtClass ctclass, ClassFile classfile, CtMethod ctmethod, boolean flag, CtClass actclass[], CtClass ctclass1, CtMethod.ConstParameter constparameter, Bytecode bytecode)
                throws CannotCompileException
            {
/*  79*/        if(!(ctclass instanceof CtClassType))
/*  80*/            throw new CannotCompileException((new StringBuilder("bad declaring class")).append(ctclass.getName()).toString());
/*  83*/        if(!flag)
/*  84*/            bytecode.addAload(0);
/*  86*/        actclass = compileParameterList(bytecode, actclass, flag ? 0 : 1);
                int i;
/*  90*/        if(constparameter == null)
                {
/*  91*/            i = 0;
/*  92*/            constparameter = CtMethod.ConstParameter.defaultDescriptor();
                } else
                {
/*  95*/            i = constparameter.compile(bytecode);
/*  96*/            constparameter = constparameter.descriptor();
                }
/*  99*/        checkSignature(ctmethod, constparameter);
/* 103*/        try
                {
/* 103*/            ctclass = addBodyMethod((CtClassType)ctclass, classfile, ctmethod);
                }
                // Misplaced declaration of an exception variable
/* 109*/        catch(CtClass ctclass)
                {
/* 110*/            throw new CannotCompileException(ctclass);
                }
/* 113*/        if(flag)
/* 114*/            bytecode.addInvokestatic(Bytecode.THIS, ctclass, constparameter);
/* 116*/        else
/* 116*/            bytecode.addInvokespecial(Bytecode.THIS, ctclass, constparameter);
/* 118*/        compileReturn(bytecode, ctclass1);
/* 120*/        if(actclass < i + 2)
/* 121*/            actclass = i + 2;
/* 123*/        return actclass;
            }

            private static void checkSignature(CtMethod ctmethod, String s)
                throws CannotCompileException
            {
/* 130*/        if(!s.equals(ctmethod.getMethodInfo2().getDescriptor()))
/* 131*/            throw new CannotCompileException((new StringBuilder("wrapped method with a bad signature: ")).append(ctmethod.getDeclaringClass().getName()).append('.').append(ctmethod.getName()).toString());
/* 135*/        else
/* 135*/            return;
            }

            private static String addBodyMethod(CtClassType ctclasstype, ClassFile classfile, CtMethod ctmethod)
                throws BadBytecode, CannotCompileException
            {
                Hashtable hashtable;
                String s;
/* 142*/        if((s = (String)(hashtable = ctclasstype.getHiddenMethods()).get(ctmethod)) == null)
                {
/* 146*/            do
/* 146*/                s = (new StringBuilder("_added_m$")).append(ctclasstype.getUniqueNumber()).toString();
/* 147*/            while(classfile.getMethod(s) != null);
                    Object obj;
/* 148*/            ((ClassMap) (obj = new ClassMap())).put(ctmethod.getDeclaringClass().getName(), ctclasstype.getName());
/* 150*/            int i = ((MethodInfo) (obj = new MethodInfo(classfile.getConstPool(), s, ctmethod.getMethodInfo2(), ((java.util.Map) (obj))))).getAccessFlags();
/* 154*/            ((MethodInfo) (obj)).setAccessFlags(AccessFlag.setPrivate(i));
/* 155*/            ((MethodInfo) (obj)).addAttribute(new SyntheticAttribute(classfile.getConstPool()));
/* 157*/            classfile.addMethod(((MethodInfo) (obj)));
/* 158*/            hashtable.put(ctmethod, s);
/* 159*/            if((classfile = ctclasstype.hasMemberCache()) != null)
/* 161*/                classfile.addMethod(new CtMethod(((MethodInfo) (obj)), ctclasstype));
                }
/* 164*/        return s;
            }

            static int compileParameterList(Bytecode bytecode, CtClass actclass[], int i)
            {
/* 176*/        return JvstCodeGen.compileParameterList(bytecode, actclass, i);
            }

            private static void compileReturn(Bytecode bytecode, CtClass ctclass)
            {
/* 183*/        if(ctclass.isPrimitive())
                {
/* 184*/            if((ctclass = (CtPrimitiveType)ctclass) != CtClass.voidType)
                    {
/* 186*/                String s = ctclass.getWrapperName();
/* 187*/                bytecode.addCheckcast(s);
/* 188*/                bytecode.addInvokevirtual(s, ctclass.getGetMethodName(), ctclass.getGetMethodDescriptor());
                    }
/* 192*/            bytecode.addOpcode(ctclass.getReturnOp());
/* 193*/            return;
                } else
                {
/* 195*/            bytecode.addCheckcast(ctclass);
/* 196*/            bytecode.addOpcode(176);
/* 198*/            return;
                }
            }

            private static final String addedWrappedMethod = "_added_m$";
}
