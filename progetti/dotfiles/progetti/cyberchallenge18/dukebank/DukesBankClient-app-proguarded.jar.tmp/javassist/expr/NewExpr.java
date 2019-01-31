// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NewExpr.java

package javassist.expr;

import javassist.*;
import javassist.bytecode.*;
import javassist.compiler.*;
import javassist.compiler.ast.ASTList;

// Referenced classes of package javassist.expr:
//            Expr

public class NewExpr extends Expr
{
    static class ProceedForNew
        implements ProceedHandler
    {

                public void doit(JvstCodeGen jvstcodegen, Bytecode bytecode, ASTList astlist)
                    throws CompileError
                {
/* 233*/            bytecode.addOpcode(187);
/* 234*/            bytecode.addIndex(newIndex);
/* 235*/            bytecode.addOpcode(89);
/* 236*/            jvstcodegen.atMethodCallCore(newType, "<init>", astlist, false, true, -1, null);
/* 238*/            jvstcodegen.setType(newType);
                }

                public void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
                    throws CompileError
                {
/* 244*/            jvsttypechecker.atMethodCallCore(newType, "<init>", astlist);
/* 245*/            jvsttypechecker.setType(newType);
                }

                CtClass newType;
                int newIndex;
                int methodIndex;

                ProceedForNew(CtClass ctclass, int i, int j)
                {
/* 225*/            newType = ctclass;
/* 226*/            newIndex = i;
/* 227*/            methodIndex = j;
                }
    }


            protected NewExpr(int i, CodeIterator codeiterator, CtClass ctclass, MethodInfo methodinfo, String s, int j)
            {
/*  36*/        super(i, codeiterator, ctclass, methodinfo);
/*  37*/        newTypeName = s;
/*  38*/        newPos = j;
            }

            public CtBehavior where()
            {
/*  59*/        return super.where();
            }

            public int getLineNumber()
            {
/*  68*/        return super.getLineNumber();
            }

            public String getFileName()
            {
/*  77*/        return super.getFileName();
            }

            private CtClass getCtClass()
                throws NotFoundException
            {
/*  84*/        return thisClass.getClassPool().get(newTypeName);
            }

            public String getClassName()
            {
/*  91*/        return newTypeName;
            }

            public String getSignature()
            {
/* 105*/        ConstPool constpool = getConstPool();
/* 106*/        int i = iterator.u16bitAt(currentPos + 1);
/* 107*/        return constpool.getMethodrefType(i);
            }

            public CtConstructor getConstructor()
                throws NotFoundException
            {
/* 114*/        Object obj = getConstPool();
/* 115*/        int i = iterator.u16bitAt(currentPos + 1);
/* 116*/        obj = ((ConstPool) (obj)).getMethodrefType(i);
/* 117*/        return getCtClass().getConstructor(((String) (obj)));
            }

            public CtClass[] mayThrow()
            {
/* 127*/        return super.mayThrow();
            }

            private int canReplace()
                throws CannotCompileException
            {
                int i;
/* 142*/        if((i = iterator.byteAt(newPos + 3)) == 89)
/* 144*/            return 4;
/* 145*/        return i != 90 || iterator.byteAt(newPos + 4) != 95 ? 3 : 5;
            }

            public void replace(String s)
                throws CannotCompileException
            {
/* 163*/        thisClass.getClassFile();
/* 166*/        int i = newPos;
/* 168*/        int j = iterator.u16bitAt(i + 1);
/* 172*/        int k = canReplace();
/* 173*/        int l = i + k;
/* 174*/        for(int i1 = i; i1 < l; i1++)
/* 175*/            iterator.writeByte(0, i1);

/* 177*/        Object obj = getConstPool();
/* 178*/        i = currentPos;
/* 179*/        l = iterator.u16bitAt(i + 1);
/* 181*/        obj = ((ConstPool) (obj)).getMethodrefType(l);
/* 183*/        Javac javac = new Javac(thisClass);
/* 184*/        Object obj1 = thisClass.getClassPool();
/* 185*/        CodeAttribute codeattribute = iterator.get();
/* 187*/        try
                {
/* 187*/            CtClass actclass[] = Descriptor.getParameterTypes(((String) (obj)), ((ClassPool) (obj1)));
/* 188*/            obj1 = ((ClassPool) (obj1)).get(newTypeName);
/* 189*/            int j1 = codeattribute.getMaxLocals();
/* 190*/            javac.recordParams(newTypeName, actclass, true, j1, withinStatic());
/* 192*/            int k1 = javac.recordReturnType(((CtClass) (obj1)), true);
/* 193*/            javac.recordProceed(new ProceedForNew(((CtClass) (obj1)), j, l));
/* 198*/            checkResultValue(((CtClass) (obj1)), s);
/* 200*/            Bytecode bytecode = javac.getBytecode();
/* 201*/            storeStack(actclass, true, j1, bytecode);
/* 202*/            javac.recordLocalVariables(codeattribute, i);
/* 204*/            bytecode.addConstZero(((CtClass) (obj1)));
/* 205*/            bytecode.addStore(k1, ((CtClass) (obj1)));
/* 207*/            javac.compileStmnt(s);
/* 208*/            if(k > 3)
/* 209*/                bytecode.addAload(k1);
/* 211*/            replace0(i, bytecode, 3);
/* 217*/            return;
                }
/* 213*/        catch(CompileError compileerror)
                {
/* 213*/            throw new CannotCompileException(compileerror);
                }
/* 214*/        catch(NotFoundException notfoundexception)
                {
/* 214*/            throw new CannotCompileException(notfoundexception);
                }
/* 215*/        catch(BadBytecode _ex)
                {
/* 216*/            throw new CannotCompileException("broken method");
                }
            }

            String newTypeName;
            int newPos;
}
