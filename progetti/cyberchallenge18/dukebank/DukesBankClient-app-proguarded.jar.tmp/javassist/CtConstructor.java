// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtConstructor.java

package javassist;

import javassist.bytecode.BadBytecode;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.bytecode.MethodInfo;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;

// Referenced classes of package javassist:
//            CtBehavior, CannotCompileException, ClassMap, CtClass, 
//            CtMethod, NotFoundException

public final class CtConstructor extends CtBehavior
{

            protected CtConstructor(MethodInfo methodinfo, CtClass ctclass)
            {
/*  38*/        super(ctclass, methodinfo);
            }

            public CtConstructor(CtClass actclass[], CtClass ctclass)
            {
/*  57*/        this(((MethodInfo) (null)), ctclass);
/*  58*/        ctclass = ctclass.getClassFile2().getConstPool();
/*  59*/        actclass = Descriptor.ofConstructor(actclass);
/*  60*/        methodInfo = new MethodInfo(ctclass, "<init>", actclass);
/*  61*/        setModifiers(1);
            }

            public CtConstructor(CtConstructor ctconstructor, CtClass ctclass, ClassMap classmap)
                throws CannotCompileException
            {
/* 101*/        this(((MethodInfo) (null)), ctclass);
/* 102*/        copy(ctconstructor, true, classmap);
            }

            public final boolean isConstructor()
            {
/* 109*/        return methodInfo.isConstructor();
            }

            public final boolean isClassInitializer()
            {
/* 116*/        return methodInfo.isStaticInitializer();
            }

            public final String getLongName()
            {
/* 126*/        return (new StringBuilder()).append(getDeclaringClass().getName()).append(isConstructor() ? Descriptor.toString(getSignature()) : ".<clinit>()").toString();
            }

            public final String getName()
            {
/* 138*/        if(methodInfo.isStaticInitializer())
/* 139*/            return "<clinit>";
/* 141*/        else
/* 141*/            return declaringClass.getSimpleName();
            }

            public final boolean isEmpty()
            {
                Object obj;
                ConstPool constpool;
/* 152*/        if((obj = getMethodInfo2().getCodeAttribute()) == null)
/* 154*/            return false;
/* 157*/        constpool = ((CodeAttribute) (obj)).getConstPool();
/* 158*/        obj = ((CodeAttribute) (obj)).iterator();
                int i;
/* 161*/        if((i = ((CodeIterator) (obj)).byteAt(((CodeIterator) (obj)).next())) == 177 || i == 42 && ((CodeIterator) (obj)).byteAt(i = ((CodeIterator) (obj)).next()) == 183 && (i = constpool.isConstructor(getSuperclassName(), ((CodeIterator) (obj)).u16bitAt(i + 1))) != 0 && "()V".equals(constpool.getUtf8Info(i)) && ((CodeIterator) (obj)).byteAt(((CodeIterator) (obj)).next()) == 177 && !((CodeIterator) (obj)).hasNext())
/* 162*/            return true;
/* 162*/        return false;
/* 171*/        JVM INSTR pop ;
/* 172*/        return false;
            }

            private String getSuperclassName()
            {
                ClassFile classfile;
/* 176*/        return (classfile = declaringClass.getClassFile2()).getSuperclass();
            }

            public final boolean callsSuper()
                throws CannotCompileException
            {
                Object obj;
/* 186*/        if((obj = methodInfo.getCodeAttribute()) == null)
/* 188*/            break MISSING_BLOCK_LABEL_40;
/* 188*/        obj = ((CodeAttribute) (obj)).iterator();
                int i;
/* 190*/        if((i = ((CodeIterator) (obj)).skipSuperConstructor()) >= 0)
/* 191*/            return true;
/* 191*/        return false;
                BadBytecode badbytecode;
/* 193*/        badbytecode;
/* 194*/        throw new CannotCompileException(badbytecode);
/* 198*/        return false;
            }

            public final void setBody(String s)
                throws CannotCompileException
            {
/* 211*/        if(s == null)
/* 212*/            if(isClassInitializer())
/* 213*/                s = ";";
/* 215*/            else
/* 215*/                s = "super();";
/* 217*/        super.setBody(s);
            }

            public final void setBody(CtConstructor ctconstructor, ClassMap classmap)
                throws CannotCompileException
            {
/* 235*/        setBody0(ctconstructor.declaringClass, ctconstructor.methodInfo, declaringClass, methodInfo, classmap);
            }

            public final void insertBeforeBody(String s)
                throws CannotCompileException
            {
                CtClass ctclass;
/* 248*/        (ctclass = declaringClass).checkModify();
/* 250*/        if(isClassInitializer())
/* 251*/            throw new CannotCompileException("class initializer");
                CodeAttribute codeattribute;
/* 253*/        CodeIterator codeiterator = (codeattribute = methodInfo.getCodeAttribute()).iterator();
                Bytecode bytecode;
/* 255*/        (bytecode = new Bytecode(methodInfo.getConstPool(), codeattribute.getMaxStack(), codeattribute.getMaxLocals())).setStackDepth(codeattribute.getMaxStack());
/* 258*/        Javac javac = new Javac(bytecode, ctclass);
/* 260*/        try
                {
/* 260*/            javac.recordParams(getParameterTypes(), false);
/* 261*/            javac.compileStmnt(s);
/* 262*/            codeattribute.setMaxStack(bytecode.getMaxStack());
/* 263*/            codeattribute.setMaxLocals(bytecode.getMaxLocals());
/* 264*/            codeiterator.skipConstructor();
/* 265*/            s = codeiterator.insertEx(bytecode.get());
/* 266*/            codeiterator.insert(bytecode.getExceptionTable(), s);
/* 267*/            methodInfo.rebuildStackMapIf6(ctclass.getClassPool(), ctclass.getClassFile2());
/* 277*/            return;
                }
                // Misplaced declaration of an exception variable
/* 269*/        catch(String s)
                {
/* 270*/            throw new CannotCompileException(s);
                }
                // Misplaced declaration of an exception variable
/* 272*/        catch(String s)
                {
/* 273*/            throw new CannotCompileException(s);
                }
                // Misplaced declaration of an exception variable
/* 275*/        catch(String s)
                {
/* 276*/            throw new CannotCompileException(s);
                }
            }

            final int getStartPosOfBody(CodeAttribute codeattribute)
                throws CannotCompileException
            {
/* 284*/        codeattribute = codeattribute.iterator();
/* 286*/        codeattribute.skipConstructor();
/* 287*/        return codeattribute.next();
/* 289*/        codeattribute;
/* 290*/        throw new CannotCompileException(codeattribute);
            }

            public final CtMethod toMethod(String s, CtClass ctclass)
                throws CannotCompileException
            {
/* 317*/        return toMethod(s, ctclass, null);
            }

            public final CtMethod toMethod(String s, CtClass ctclass, ClassMap classmap)
                throws CannotCompileException
            {
                CtMethod ctmethod;
/* 351*/        (ctmethod = new CtMethod(null, ctclass)).copy(this, false, classmap);
/* 353*/        if(isConstructor() && (classmap = (classmap = ctmethod.getMethodInfo2()).getCodeAttribute()) != null)
                {
/* 357*/            removeConsCall(classmap);
/* 359*/            try
                    {
/* 359*/                methodInfo.rebuildStackMapIf6(ctclass.getClassPool(), ctclass.getClassFile2());
                    }
                    // Misplaced declaration of an exception variable
/* 362*/            catch(String s)
                    {
/* 363*/                throw new CannotCompileException(s);
                    }
                }
/* 368*/        ctmethod.setName(s);
/* 369*/        return ctmethod;
            }

            private static void removeConsCall(CodeAttribute codeattribute)
                throws CannotCompileException
            {
/* 375*/        CodeIterator codeiterator = codeattribute.iterator();
/* 377*/        try
                {
                    int i;
/* 377*/            if((i = codeiterator.skipConstructor()) >= 0)
                    {
/* 379*/                int j = codeiterator.u16bitAt(i + 1);
/* 380*/                if((j = Descriptor.numOfParameters(codeattribute = codeattribute.getConstPool().getMethodrefType(j)) + 1) > 3)
/* 383*/                    i = codeiterator.insertGapAt(i, j - 3, false).position;
/* 385*/                codeiterator.writeByte(87, i++);
/* 386*/                codeiterator.writeByte(0, i);
/* 387*/                codeiterator.writeByte(0, i + 1);
/* 388*/                codeattribute = new javassist.bytecode.Descriptor.Iterator(codeattribute);
/* 390*/                do
                        {
/* 390*/                    codeattribute.next();
/* 391*/                    if(!codeattribute.isParameter())
/* 392*/                        break;
/* 392*/                    codeiterator.writeByte(codeattribute.is2byte() ? 88 : 87, i++);
                        } while(true);
                    }
/* 401*/            return;
                }
/* 399*/        catch(BadBytecode badbytecode)
                {
/* 400*/            throw new CannotCompileException(badbytecode);
                }
            }
}
