// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FieldAccess.java

package javassist.expr;

import javassist.*;
import javassist.bytecode.*;
import javassist.compiler.*;
import javassist.compiler.ast.ASTList;

// Referenced classes of package javassist.expr:
//            Expr

public class FieldAccess extends Expr
{
    static class ProceedForWrite
        implements ProceedHandler
    {

                public void doit(JvstCodeGen jvstcodegen, Bytecode bytecode, ASTList astlist)
                    throws CompileError
                {
/* 288*/            if(jvstcodegen.getMethodArgsLength(astlist) != 1)
/* 289*/                throw new CompileError("$proceed() cannot take more than one parameter for field writing");
                    int i;
/* 294*/            if(FieldAccess.isStatic(opcode))
                    {
/* 295*/                i = 0;
                    } else
                    {
/* 297*/                i = -1;
/* 298*/                bytecode.addAload(targetVar);
                    }
/* 301*/            jvstcodegen.atMethodArgs(astlist, new int[1], new int[1], new String[1]);
/* 302*/            jvstcodegen.doNumCast(fieldType);
/* 303*/            if(fieldType instanceof CtPrimitiveType)
/* 304*/                i -= ((CtPrimitiveType)fieldType).getDataSize();
/* 306*/            else
/* 306*/                i--;
/* 308*/            bytecode.add(opcode);
/* 309*/            bytecode.addIndex(index);
/* 310*/            bytecode.growStack(i);
/* 311*/            jvstcodegen.setType(CtClass.voidType);
/* 312*/            jvstcodegen.addNullIfVoid();
                }

                public void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
                    throws CompileError
                {
/* 318*/            jvsttypechecker.atMethodArgs(astlist, new int[1], new int[1], new String[1]);
/* 319*/            jvsttypechecker.setType(CtClass.voidType);
/* 320*/            jvsttypechecker.addNullIfVoid();
                }

                CtClass fieldType;
                int opcode;
                int targetVar;
                int index;

                ProceedForWrite(CtClass ctclass, int i, int j, int k)
                {
/* 279*/            fieldType = ctclass;
/* 280*/            targetVar = k;
/* 281*/            opcode = i;
/* 282*/            index = j;
                }
    }

    static class ProceedForRead
        implements ProceedHandler
    {

                public void doit(JvstCodeGen jvstcodegen, Bytecode bytecode, ASTList astlist)
                    throws CompileError
                {
/* 240*/            if(astlist != null && !jvstcodegen.isParamListName(astlist))
/* 241*/                throw new CompileError("$proceed() cannot take a parameter for field reading");
/* 245*/            if(FieldAccess.isStatic(opcode))
                    {
/* 246*/                astlist = 0;
                    } else
                    {
/* 248*/                astlist = -1;
/* 249*/                bytecode.addAload(targetVar);
                    }
/* 252*/            if(fieldType instanceof CtPrimitiveType)
/* 253*/                astlist += ((CtPrimitiveType)fieldType).getDataSize();
/* 255*/            else
/* 255*/                astlist++;
/* 257*/            bytecode.add(opcode);
/* 258*/            bytecode.addIndex(index);
/* 259*/            bytecode.growStack(astlist);
/* 260*/            jvstcodegen.setType(fieldType);
                }

                public void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
                    throws CompileError
                {
/* 266*/            jvsttypechecker.setType(fieldType);
                }

                CtClass fieldType;
                int opcode;
                int targetVar;
                int index;

                ProceedForRead(CtClass ctclass, int i, int j, int k)
                {
/* 231*/            fieldType = ctclass;
/* 232*/            targetVar = k;
/* 233*/            opcode = i;
/* 234*/            index = j;
                }
    }


            protected FieldAccess(int i, CodeIterator codeiterator, CtClass ctclass, MethodInfo methodinfo, int j)
            {
/*  32*/        super(i, codeiterator, ctclass, methodinfo);
/*  33*/        opcode = j;
            }

            public CtBehavior where()
            {
/*  40*/        return super.where();
            }

            public int getLineNumber()
            {
/*  49*/        return super.getLineNumber();
            }

            public String getFileName()
            {
/*  58*/        return super.getFileName();
            }

            public boolean isStatic()
            {
/*  65*/        return isStatic(opcode);
            }

            static boolean isStatic(int i)
            {
/*  69*/        return i == 178 || i == 179;
            }

            public boolean isReader()
            {
/*  76*/        return opcode == 180 || opcode == 178;
            }

            public boolean isWriter()
            {
/*  83*/        return opcode == 181 || opcode == 179;
            }

            private CtClass getCtClass()
                throws NotFoundException
            {
/*  90*/        return thisClass.getClassPool().get(getClassName());
            }

            public String getClassName()
            {
/*  97*/        int i = iterator.u16bitAt(currentPos + 1);
/*  98*/        return getConstPool().getFieldrefClassName(i);
            }

            public String getFieldName()
            {
/* 105*/        int i = iterator.u16bitAt(currentPos + 1);
/* 106*/        return getConstPool().getFieldrefName(i);
            }

            public CtField getField()
                throws NotFoundException
            {
                CtClass ctclass;
/* 113*/        return (ctclass = getCtClass()).getField(getFieldName());
            }

            public CtClass[] mayThrow()
            {
/* 124*/        return super.mayThrow();
            }

            public String getSignature()
            {
/* 136*/        int i = iterator.u16bitAt(currentPos + 1);
/* 137*/        return getConstPool().getFieldrefType(i);
            }

            public void replace(String s)
                throws CannotCompileException
            {
/* 151*/        thisClass.getClassFile();
/* 152*/        ConstPool constpool = getConstPool();
/* 153*/        int i = currentPos;
/* 154*/        int j = iterator.u16bitAt(i + 1);
/* 156*/        Javac javac = new Javac(thisClass);
/* 157*/        CodeAttribute codeattribute = iterator.get();
/* 161*/        try
                {
/* 161*/            CtClass ctclass1 = Descriptor.toCtClass(constpool.getFieldrefType(j), thisClass.getClassPool());
                    CtClass actclass[];
                    CtClass ctclass;
                    boolean flag1;
/* 164*/            if(flag1 = isReader())
                    {
/* 166*/                actclass = new CtClass[0];
/* 167*/                ctclass = ctclass1;
                    } else
                    {
/* 170*/                (actclass = new CtClass[1])[0] = ctclass1;
/* 172*/                ctclass = CtClass.voidType;
                    }
/* 175*/            int k = codeattribute.getMaxLocals();
/* 176*/            javac.recordParams(constpool.getFieldrefClassName(j), actclass, true, k, withinStatic());
/* 181*/            boolean flag = checkResultValue(ctclass, s);
/* 182*/            if(flag1)
/* 183*/                flag = true;
/* 185*/            int l = javac.recordReturnType(ctclass, flag);
/* 186*/            if(flag1)
                    {
/* 187*/                javac.recordProceed(new ProceedForRead(ctclass, opcode, j, k));
                    } else
                    {
/* 191*/                javac.recordType(ctclass1);
/* 192*/                javac.recordProceed(new ProceedForWrite(actclass[0], opcode, j, k));
                    }
/* 196*/            Bytecode bytecode = javac.getBytecode();
/* 197*/            storeStack(actclass, isStatic(), k, bytecode);
/* 198*/            javac.recordLocalVariables(codeattribute, i);
/* 200*/            if(flag)
/* 201*/                if(ctclass == CtClass.voidType)
                        {
/* 202*/                    bytecode.addOpcode(1);
/* 203*/                    bytecode.addAstore(l);
                        } else
                        {
/* 206*/                    bytecode.addConstZero(ctclass);
/* 207*/                    bytecode.addStore(l, ctclass);
                        }
/* 210*/            javac.compileStmnt(s);
/* 211*/            if(flag1)
/* 212*/                bytecode.addLoad(l, ctclass);
/* 214*/            replace0(i, bytecode, 3);
/* 220*/            return;
                }
/* 216*/        catch(CompileError compileerror)
                {
/* 216*/            throw new CannotCompileException(compileerror);
                }
/* 217*/        catch(NotFoundException notfoundexception)
                {
/* 217*/            throw new CannotCompileException(notfoundexception);
                }
/* 218*/        catch(BadBytecode _ex)
                {
/* 219*/            throw new CannotCompileException("broken method");
                }
            }

            int opcode;
}
