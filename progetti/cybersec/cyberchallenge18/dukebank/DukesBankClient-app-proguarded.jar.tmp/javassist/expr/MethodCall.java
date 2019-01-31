// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MethodCall.java

package javassist.expr;

import javassist.*;
import javassist.bytecode.*;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;

// Referenced classes of package javassist.expr:
//            Expr

public class MethodCall extends Expr
{

            protected MethodCall(int i, CodeIterator codeiterator, CtClass ctclass, MethodInfo methodinfo)
            {
/*  32*/        super(i, codeiterator, ctclass, methodinfo);
            }

            private int getNameAndType(ConstPool constpool)
            {
/*  36*/        int i = currentPos;
/*  37*/        int j = iterator.byteAt(i);
/*  38*/        i = iterator.u16bitAt(i + 1);
/*  40*/        if(j == 185)
/*  41*/            return constpool.getInterfaceMethodrefNameAndType(i);
/*  43*/        else
/*  43*/            return constpool.getMethodrefNameAndType(i);
            }

            public CtBehavior where()
            {
/*  50*/        return super.where();
            }

            public int getLineNumber()
            {
/*  59*/        return super.getLineNumber();
            }

            public String getFileName()
            {
/*  68*/        return super.getFileName();
            }

            protected CtClass getCtClass()
                throws NotFoundException
            {
/*  76*/        return thisClass.getClassPool().get(getClassName());
            }

            public String getClassName()
            {
/*  86*/        Object obj = getConstPool();
/*  87*/        int i = currentPos;
/*  88*/        int j = iterator.byteAt(i);
/*  89*/        i = iterator.u16bitAt(i + 1);
/*  91*/        if(j == 185)
/*  92*/            obj = ((ConstPool) (obj)).getInterfaceMethodrefClassName(i);
/*  94*/        else
/*  94*/            obj = ((ConstPool) (obj)).getMethodrefClassName(i);
/*  96*/        if(((String) (obj)).charAt(0) == '[')
/*  97*/            obj = Descriptor.toClassName(((String) (obj)));
/*  99*/        return ((String) (obj));
            }

            public String getMethodName()
            {
/* 106*/        ConstPool constpool = getConstPool();
/* 107*/        int i = getNameAndType(constpool);
/* 108*/        return constpool.getUtf8Info(constpool.getNameAndTypeName(i));
            }

            public CtMethod getMethod()
                throws NotFoundException
            {
/* 115*/        return getCtClass().getMethod(getMethodName(), getSignature());
            }

            public String getSignature()
            {
/* 129*/        ConstPool constpool = getConstPool();
/* 130*/        int i = getNameAndType(constpool);
/* 131*/        return constpool.getUtf8Info(constpool.getNameAndTypeDescriptor(i));
            }

            public CtClass[] mayThrow()
            {
/* 141*/        return super.mayThrow();
            }

            public boolean isSuper()
            {
/* 149*/        return iterator.byteAt(currentPos) == 183 && !where().getDeclaringClass().getName().equals(getClassName());
            }

            public void replace(String s)
                throws CannotCompileException
            {
/* 180*/        thisClass.getClassFile();
/* 181*/        Object obj = getConstPool();
/* 182*/        int i = currentPos;
/* 183*/        int j = iterator.u16bitAt(i + 1);
                String s1;
                String s2;
                byte byte0;
                int k;
/* 187*/        if((k = iterator.byteAt(i)) == 185)
                {
/* 189*/            byte0 = 5;
/* 190*/            s1 = ((ConstPool) (obj)).getInterfaceMethodrefClassName(j);
/* 191*/            s2 = ((ConstPool) (obj)).getInterfaceMethodrefName(j);
/* 192*/            obj = ((ConstPool) (obj)).getInterfaceMethodrefType(j);
                } else
/* 194*/        if(k == 184 || k == 183 || k == 182)
                {
/* 196*/            byte0 = 3;
/* 197*/            s1 = ((ConstPool) (obj)).getMethodrefClassName(j);
/* 198*/            s2 = ((ConstPool) (obj)).getMethodrefName(j);
/* 199*/            obj = ((ConstPool) (obj)).getMethodrefType(j);
                } else
                {
/* 202*/            throw new CannotCompileException("not method invocation");
                }
/* 204*/        Javac javac = new Javac(thisClass);
/* 205*/        Object obj1 = thisClass.getClassPool();
/* 206*/        CodeAttribute codeattribute = iterator.get();
/* 208*/        try
                {
/* 208*/            CtClass actclass[] = Descriptor.getParameterTypes(((String) (obj)), ((ClassPool) (obj1)));
/* 209*/            obj1 = Descriptor.getReturnType(((String) (obj)), ((ClassPool) (obj1)));
/* 210*/            int l = codeattribute.getMaxLocals();
/* 211*/            javac.recordParams(s1, actclass, true, l, withinStatic());
/* 213*/            int i1 = javac.recordReturnType(((CtClass) (obj1)), true);
/* 214*/            if(k == 184)
/* 215*/                javac.recordStaticProceed(s1, s2);
/* 216*/            else
/* 216*/            if(k == 183)
/* 217*/                javac.recordSpecialProceed("$0", s1, s2, ((String) (obj)));
/* 220*/            else
/* 220*/                javac.recordProceed("$0", s2);
/* 224*/            checkResultValue(((CtClass) (obj1)), s);
/* 226*/            obj = javac.getBytecode();
/* 227*/            storeStack(actclass, k == 184, l, ((Bytecode) (obj)));
/* 228*/            javac.recordLocalVariables(codeattribute, i);
/* 230*/            if(obj1 != CtClass.voidType)
                    {
/* 231*/                ((Bytecode) (obj)).addConstZero(((CtClass) (obj1)));
/* 232*/                ((Bytecode) (obj)).addStore(i1, ((CtClass) (obj1)));
                    }
/* 235*/            javac.compileStmnt(s);
/* 236*/            if(obj1 != CtClass.voidType)
/* 237*/                ((Bytecode) (obj)).addLoad(i1, ((CtClass) (obj1)));
/* 239*/            replace0(i, ((Bytecode) (obj)), byte0);
/* 245*/            return;
                }
/* 241*/        catch(CompileError compileerror)
                {
/* 241*/            throw new CannotCompileException(compileerror);
                }
/* 242*/        catch(NotFoundException notfoundexception)
                {
/* 242*/            throw new CannotCompileException(notfoundexception);
                }
/* 243*/        catch(BadBytecode _ex)
                {
/* 244*/            throw new CannotCompileException("broken method");
                }
            }
}
