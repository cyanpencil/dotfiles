// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Handler.java

package javassist.expr;

import javassist.*;
import javassist.bytecode.*;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;

// Referenced classes of package javassist.expr:
//            Expr

public class Handler extends Expr
{

            protected Handler(ExceptionTable exceptiontable, int i, CodeIterator codeiterator, CtClass ctclass, MethodInfo methodinfo)
            {
/*  36*/        super(exceptiontable.handlerPc(i), codeiterator, ctclass, methodinfo);
/*  37*/        etable = exceptiontable;
/*  38*/        index = i;
            }

            public CtBehavior where()
            {
/*  44*/        return super.where();
            }

            public int getLineNumber()
            {
/*  52*/        return super.getLineNumber();
            }

            public String getFileName()
            {
/*  61*/        return super.getFileName();
            }

            public CtClass[] mayThrow()
            {
/*  68*/        return super.mayThrow();
            }

            public CtClass getType()
                throws NotFoundException
            {
                int i;
/*  76*/        if((i = etable.catchType(index)) == 0)
                {
/*  78*/            return null;
                } else
                {
                    ConstPool constpool;
/*  80*/            String s = (constpool = getConstPool()).getClassInfo(i);
/*  82*/            return thisClass.getClassPool().getCtClass(s);
                }
            }

            public boolean isFinally()
            {
/*  90*/        return etable.catchType(index) == 0;
            }

            public void replace(String s)
                throws CannotCompileException
            {
/*  99*/        throw new RuntimeException("not implemented yet");
            }

            public void insertBefore(String s)
                throws CannotCompileException
            {
/* 110*/        edited = true;
/* 112*/        getConstPool();
/* 113*/        CodeAttribute codeattribute = iterator.get();
                Javac javac;
                Bytecode bytecode;
/* 114*/        (bytecode = (javac = new Javac(thisClass)).getBytecode()).setStackDepth(1);
/* 117*/        bytecode.setMaxLocals(codeattribute.getMaxLocals());
/* 120*/        try
                {
/* 120*/            CtClass ctclass = getType();
/* 121*/            int i = javac.recordVariable(ctclass, EXCEPTION_NAME);
/* 122*/            javac.recordReturnType(ctclass, false);
/* 123*/            bytecode.addAstore(i);
/* 124*/            javac.compileStmnt(s);
/* 125*/            bytecode.addAload(i);
/* 127*/            s = etable.handlerPc(index);
/* 128*/            bytecode.addOpcode(167);
/* 129*/            bytecode.addIndex((s - iterator.getCodeLength() - bytecode.currentPc()) + 1);
/* 132*/            maxStack = bytecode.getMaxStack();
/* 133*/            maxLocals = bytecode.getMaxLocals();
/* 135*/            s = iterator.append(bytecode.get());
/* 136*/            iterator.append(bytecode.getExceptionTable(), s);
/* 137*/            etable.setHandlerPc(index, s);
/* 144*/            return;
                }
/* 139*/        catch(NotFoundException notfoundexception)
                {
/* 140*/            throw new CannotCompileException(notfoundexception);
                }
/* 142*/        catch(CompileError compileerror)
                {
/* 143*/            throw new CannotCompileException(compileerror);
                }
            }

            private static String EXCEPTION_NAME = "$1";
            private ExceptionTable etable;
            private int index;

}
