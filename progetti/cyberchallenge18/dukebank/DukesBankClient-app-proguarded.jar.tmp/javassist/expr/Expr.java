// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Expr.java

package javassist.expr;

import java.util.Iterator;
import java.util.LinkedList;
import javassist.*;
import javassist.bytecode.*;

// Referenced classes of package javassist.expr:
//            ExprEditor

public abstract class Expr
    implements Opcode
{

            protected Expr(int i, CodeIterator codeiterator, CtClass ctclass, MethodInfo methodinfo)
            {
/*  59*/        currentPos = i;
/*  60*/        iterator = codeiterator;
/*  61*/        thisClass = ctclass;
/*  62*/        thisMethod = methodinfo;
            }

            public CtClass getEnclosingClass()
            {
/*  71*/        return thisClass;
            }

            protected final ConstPool getConstPool()
            {
/*  74*/        return thisMethod.getConstPool();
            }

            protected final boolean edited()
            {
/*  78*/        return edited;
            }

            protected final int locals()
            {
/*  82*/        return maxLocals;
            }

            protected final int stack()
            {
/*  86*/        return maxStack;
            }

            protected final boolean withinStatic()
            {
/*  93*/        return (thisMethod.getAccessFlags() & 8) != 0;
            }

            public CtBehavior where()
            {
/* 100*/        MethodInfo methodinfo = thisMethod;
                CtBehavior actbehavior[];
/* 101*/        for(int j = (actbehavior = thisClass.getDeclaredBehaviors()).length - 1; j >= 0; j--)
/* 103*/            if(actbehavior[j].getMethodInfo2() == methodinfo)
/* 104*/                return actbehavior[j];

                CtConstructor ctconstructor;
/* 106*/        if((ctconstructor = thisClass.getClassInitializer()) != null && ctconstructor.getMethodInfo2() == methodinfo)
/* 108*/            return ctconstructor;
/* 115*/        for(int i = actbehavior.length - 1; i >= 0; i--)
/* 116*/            if(thisMethod.getName().equals(actbehavior[i].getMethodInfo2().getName()) && thisMethod.getDescriptor().equals(actbehavior[i].getMethodInfo2().getDescriptor()))
/* 119*/                return actbehavior[i];

/* 123*/        throw new RuntimeException("fatal: not found");
            }

            public CtClass[] mayThrow()
            {
/* 133*/        ClassPool classpool = thisClass.getClassPool();
/* 134*/        ConstPool constpool = thisMethod.getConstPool();
/* 135*/        LinkedList linkedlist = new LinkedList();
/* 137*/        try
                {
                    Object obj;
/* 137*/            obj = ((CodeAttribute) (obj = thisMethod.getCodeAttribute())).getExceptionTable();
/* 139*/            int i = currentPos;
/* 140*/            int k = ((ExceptionTable) (obj)).size();
/* 141*/            for(int i1 = 0; i1 < k; i1++)
                    {
                        int j1;
/* 142*/                if(((ExceptionTable) (obj)).startPc(i1) <= i && i < ((ExceptionTable) (obj)).endPc(i1) && (j1 = ((ExceptionTable) (obj)).catchType(i1)) > 0)
/* 146*/                    try
                            {
/* 146*/                        addClass(linkedlist, classpool.get(constpool.getClassInfo(j1)));
                            }
/* 148*/                    catch(NotFoundException _ex) { }
                    }

                }
/* 152*/        catch(NullPointerException _ex) { }
                ExceptionsAttribute exceptionsattribute;
                String as[];
/* 155*/        if((exceptionsattribute = thisMethod.getExceptionsAttribute()) != null && (as = exceptionsattribute.getExceptions()) != null)
                {
/* 159*/            int j = as.length;
/* 160*/            for(int l = 0; l < j; l++)
/* 162*/                try
                        {
/* 162*/                    addClass(linkedlist, classpool.get(as[l]));
                        }
/* 164*/                catch(NotFoundException _ex) { }

                }
/* 169*/        return (CtClass[])linkedlist.toArray(new CtClass[linkedlist.size()]);
            }

            private static void addClass(LinkedList linkedlist, CtClass ctclass)
            {
/* 173*/        for(Iterator iterator1 = linkedlist.iterator(); iterator1.hasNext();)
/* 175*/            if(iterator1.next() == ctclass)
/* 176*/                return;

/* 178*/        linkedlist.add(ctclass);
            }

            public int indexOfBytecode()
            {
/* 187*/        return currentPos;
            }

            public int getLineNumber()
            {
/* 196*/        return thisMethod.getLineNumber(currentPos);
            }

            public String getFileName()
            {
                ClassFile classfile;
/* 205*/        if((classfile = thisClass.getClassFile2()) == null)
/* 207*/            return null;
/* 209*/        else
/* 209*/            return classfile.getSourceFile();
            }

            static final boolean checkResultValue(CtClass ctclass, String s)
                throws CannotCompileException
            {
/* 217*/        if((s = s.indexOf("$_") < 0 ? 0 : 1) == 0 && ctclass != CtClass.voidType)
/* 219*/            throw new CannotCompileException("the resulting value is not stored in $_");
/* 223*/        else
/* 223*/            return s;
            }

            static final void storeStack(CtClass actclass[], boolean flag, int i, Bytecode bytecode)
            {
/* 235*/        storeStack0(0, actclass.length, actclass, i + 1, bytecode);
/* 236*/        if(flag)
/* 237*/            bytecode.addOpcode(1);
/* 239*/        bytecode.addAstore(i);
            }

            private static void storeStack0(int i, int j, CtClass actclass[], int k, Bytecode bytecode)
            {
/* 244*/        if(i >= j)
/* 245*/            return;
                CtClass ctclass;
                int l;
/* 247*/        if((ctclass = actclass[i]) instanceof CtPrimitiveType)
/* 250*/            l = ((CtPrimitiveType)ctclass).getDataSize();
/* 252*/        else
/* 252*/            l = 1;
/* 254*/        storeStack0(i + 1, j, actclass, k + l, bytecode);
/* 255*/        bytecode.addStore(k, ctclass);
            }

            public abstract void replace(String s)
                throws CannotCompileException;

            public void replace(String s, ExprEditor expreditor)
                throws CannotCompileException
            {
/* 285*/        replace(s);
/* 286*/        if(expreditor != null)
/* 287*/            runEditor(expreditor, iterator);
            }

            protected void replace0(int i, Bytecode bytecode, int j)
                throws BadBytecode
            {
/* 292*/        byte abyte0[] = bytecode.get();
/* 293*/        edited = true;
/* 294*/        int k = abyte0.length - j;
/* 295*/        for(int l = 0; l < j; l++)
/* 296*/            iterator.writeByte(0, i + l);

/* 298*/        if(k > 0)
/* 299*/            i = iterator.insertGapAt(i, k, false).position;
/* 301*/        iterator.write(abyte0, i);
/* 302*/        iterator.insert(bytecode.getExceptionTable(), i);
/* 303*/        maxLocals = bytecode.getMaxLocals();
/* 304*/        maxStack = bytecode.getMaxStack();
            }

            protected void runEditor(ExprEditor expreditor, CodeIterator codeiterator)
                throws CannotCompileException
            {
                CodeAttribute codeattribute;
/* 310*/        int i = (codeattribute = codeiterator.get()).getMaxLocals();
/* 312*/        int j = codeattribute.getMaxStack();
/* 313*/        int k = locals();
/* 314*/        codeattribute.setMaxStack(stack());
/* 315*/        codeattribute.setMaxLocals(k);
/* 316*/        ExprEditor.LoopContext loopcontext = new ExprEditor.LoopContext(k);
/* 318*/        int l = codeiterator.getCodeLength();
/* 319*/        int i1 = codeiterator.lookAhead();
/* 320*/        codeiterator.move(currentPos);
/* 321*/        if(expreditor.doit(thisClass, thisMethod, loopcontext, codeiterator, i1))
/* 322*/            edited = true;
/* 324*/        codeiterator.move((i1 + codeiterator.getCodeLength()) - l);
/* 325*/        codeattribute.setMaxLocals(i);
/* 326*/        codeattribute.setMaxStack(j);
/* 327*/        maxLocals = loopcontext.maxLocals;
/* 328*/        maxStack += loopcontext.maxStack;
            }

            int currentPos;
            CodeIterator iterator;
            CtClass thisClass;
            MethodInfo thisMethod;
            boolean edited;
            int maxLocals;
            int maxStack;
            static final String javaLangObject = "java.lang.Object";
}
