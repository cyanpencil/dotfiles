// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NewArray.java

package javassist.expr;

import javassist.*;
import javassist.bytecode.*;
import javassist.compiler.*;
import javassist.compiler.ast.ASTList;

// Referenced classes of package javassist.expr:
//            Expr

public class NewArray extends Expr
{
    static class ProceedForArray
        implements ProceedHandler
    {

                public void doit(JvstCodeGen jvstcodegen, Bytecode bytecode, ASTList astlist)
                    throws CompileError
                {
                    int i;
/* 256*/            if((i = jvstcodegen.getMethodArgsLength(astlist)) != dimension)
/* 258*/                throw new CompileError("$proceed() with a wrong number of parameters");
/* 261*/            jvstcodegen.atMethodArgs(astlist, new int[i], new int[i], new String[i]);
/* 263*/            bytecode.addOpcode(opcode);
/* 264*/            if(opcode == 189)
/* 265*/                bytecode.addIndex(index);
/* 266*/            else
/* 266*/            if(opcode == 188)
                    {
/* 267*/                bytecode.add(index);
                    } else
                    {
/* 269*/                bytecode.addIndex(index);
/* 270*/                bytecode.add(dimension);
/* 271*/                bytecode.growStack(1 - dimension);
                    }
/* 274*/            jvstcodegen.setType(arrayType);
                }

                public void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
                    throws CompileError
                {
/* 280*/            jvsttypechecker.setType(arrayType);
                }

                CtClass arrayType;
                int opcode;
                int index;
                int dimension;

                ProceedForArray(CtClass ctclass, int i, int j, int k)
                {
/* 247*/            arrayType = ctclass;
/* 248*/            opcode = i;
/* 249*/            index = j;
/* 250*/            dimension = k;
                }
    }


            protected NewArray(int i, CodeIterator codeiterator, CtClass ctclass, MethodInfo methodinfo, int j)
            {
/*  35*/        super(i, codeiterator, ctclass, methodinfo);
/*  36*/        opcode = j;
            }

            public CtBehavior where()
            {
/*  43*/        return super.where();
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
/*  71*/        return super.mayThrow();
            }

            public CtClass getComponentType()
                throws NotFoundException
            {
/*  81*/        if(opcode == 188)
                {
/*  82*/            int i = iterator.byteAt(currentPos + 1);
/*  83*/            return getPrimitiveType(i);
                }
/*  85*/        if(opcode == 189 || opcode == 197)
                {
/*  87*/            int j = iterator.u16bitAt(currentPos + 1);
                    String s;
/*  88*/            int k = Descriptor.arrayDimension(s = getConstPool().getClassInfo(j));
/*  90*/            return Descriptor.toCtClass(s = Descriptor.toArrayComponent(s, k), thisClass.getClassPool());
                } else
                {
/*  94*/            throw new RuntimeException((new StringBuilder("bad opcode: ")).append(opcode).toString());
                }
            }

            CtClass getPrimitiveType(int i)
            {
/*  98*/        switch(i)
                {
/* 100*/        case 4: // '\004'
/* 100*/            return CtClass.booleanType;

/* 102*/        case 5: // '\005'
/* 102*/            return CtClass.charType;

/* 104*/        case 6: // '\006'
/* 104*/            return CtClass.floatType;

/* 106*/        case 7: // '\007'
/* 106*/            return CtClass.doubleType;

/* 108*/        case 8: // '\b'
/* 108*/            return CtClass.byteType;

/* 110*/        case 9: // '\t'
/* 110*/            return CtClass.shortType;

/* 112*/        case 10: // '\n'
/* 112*/            return CtClass.intType;

/* 114*/        case 11: // '\013'
/* 114*/            return CtClass.longType;
                }
/* 116*/        throw new RuntimeException((new StringBuilder("bad atype: ")).append(i).toString());
            }

            public int getDimension()
            {
/* 124*/        if(opcode == 188)
/* 125*/            return 1;
/* 126*/        if(opcode == 189 || opcode == 197)
                {
/* 128*/            int i = iterator.u16bitAt(currentPos + 1);
                    String s;
/* 129*/            return Descriptor.arrayDimension(s = getConstPool().getClassInfo(i)) + (opcode != 189 ? 0 : 1);
                } else
                {
/* 134*/            throw new RuntimeException((new StringBuilder("bad opcode: ")).append(opcode).toString());
                }
            }

            public int getCreatedDimensions()
            {
/* 143*/        if(opcode == 197)
/* 144*/            return iterator.byteAt(currentPos + 3);
/* 146*/        else
/* 146*/            return 1;
            }

            public void replace(String s)
                throws CannotCompileException
            {
/* 161*/        try
                {
/* 161*/            replace2(s);
/* 167*/            return;
                }
                // Misplaced declaration of an exception variable
/* 163*/        catch(String s)
                {
/* 163*/            throw new CannotCompileException(s);
                }
                // Misplaced declaration of an exception variable
/* 164*/        catch(String s)
                {
/* 164*/            throw new CannotCompileException(s);
                }
/* 165*/        catch(BadBytecode _ex)
                {
/* 166*/            throw new CannotCompileException("broken method");
                }
            }

            private void replace2(String s)
                throws CompileError, NotFoundException, BadBytecode, CannotCompileException
            {
/* 174*/        thisClass.getClassFile();
/* 175*/        Object obj = getConstPool();
/* 176*/        int i = currentPos;
/* 180*/        int k = 1;
                byte byte0;
                int j;
/* 182*/        if(opcode == 188)
                {
/* 183*/            j = iterator.byteAt(currentPos + 1);
/* 184*/            CtPrimitiveType ctprimitivetype = (CtPrimitiveType)getPrimitiveType(j);
/* 185*/            obj = (new StringBuilder("[")).append(ctprimitivetype.getDescriptor()).toString();
/* 186*/            byte0 = 2;
                } else
/* 188*/        if(opcode == 189)
                {
/* 189*/            j = iterator.u16bitAt(i + 1);
/* 190*/            if(((String) (obj = ((ConstPool) (obj)).getClassInfo(j))).startsWith("["))
/* 192*/                obj = (new StringBuilder("[")).append(((String) (obj))).toString();
/* 194*/            else
/* 194*/                obj = (new StringBuilder("[L")).append(((String) (obj))).append(";").toString();
/* 196*/            byte0 = 3;
                } else
/* 198*/        if(opcode == 197)
                {
/* 199*/            j = iterator.u16bitAt(currentPos + 1);
/* 200*/            obj = ((ConstPool) (obj)).getClassInfo(j);
/* 201*/            k = iterator.byteAt(currentPos + 3);
/* 202*/            byte0 = 4;
                } else
                {
/* 205*/            throw new RuntimeException((new StringBuilder("bad opcode: ")).append(opcode).toString());
                }
/* 207*/        obj = Descriptor.toCtClass(((String) (obj)), thisClass.getClassPool());
/* 209*/        Javac javac = new Javac(thisClass);
/* 210*/        CodeAttribute codeattribute = iterator.get();
/* 212*/        CtClass actclass[] = new CtClass[k];
/* 213*/        for(int l = 0; l < k; l++)
/* 214*/            actclass[l] = CtClass.intType;

/* 216*/        int i1 = codeattribute.getMaxLocals();
/* 217*/        javac.recordParams("java.lang.Object", actclass, true, i1, withinStatic());
/* 222*/        checkResultValue(((CtClass) (obj)), s);
/* 223*/        int j1 = javac.recordReturnType(((CtClass) (obj)), true);
/* 224*/        javac.recordProceed(new ProceedForArray(((CtClass) (obj)), opcode, j, k));
/* 226*/        obj = javac.getBytecode();
/* 227*/        storeStack(actclass, true, i1, ((Bytecode) (obj)));
/* 228*/        javac.recordLocalVariables(codeattribute, i);
/* 230*/        ((Bytecode) (obj)).addOpcode(1);
/* 231*/        ((Bytecode) (obj)).addAstore(j1);
/* 233*/        javac.compileStmnt(s);
/* 234*/        ((Bytecode) (obj)).addAload(j1);
/* 236*/        replace0(i, ((Bytecode) (obj)), byte0);
            }

            int opcode;
}
