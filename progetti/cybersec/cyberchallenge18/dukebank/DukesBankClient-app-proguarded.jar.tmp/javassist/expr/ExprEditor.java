// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExprEditor.java

package javassist.expr;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.bytecode.*;

// Referenced classes of package javassist.expr:
//            Cast, ConstructorCall, Expr, FieldAccess, 
//            Handler, Instanceof, MethodCall, NewArray, 
//            NewExpr

public class ExprEditor
{
    static final class LoopContext
    {

                final void updateMax(int i, int j)
                {
/* 168*/            if(maxLocals < i)
/* 169*/                maxLocals = i;
/* 171*/            if(maxStack < j)
/* 172*/                maxStack = j;
                }

                NewOp newList;
                int maxLocals;
                int maxStack;

                LoopContext(int i)
                {
/* 162*/            maxLocals = i;
/* 163*/            maxStack = 0;
/* 164*/            newList = null;
                }
    }

    static final class NewOp
    {

                NewOp next;
                int pos;
                String type;

                NewOp(NewOp newop, int i, String s)
                {
/* 150*/            next = newop;
/* 151*/            pos = i;
/* 152*/            type = s;
                }
    }


            public ExprEditor()
            {
            }

            public boolean doit(CtClass ctclass, MethodInfo methodinfo)
                throws CannotCompileException
            {
                CodeAttribute codeattribute;
/*  82*/        if((codeattribute = methodinfo.getCodeAttribute()) == null)
/*  84*/            return false;
/*  86*/        CodeIterator codeiterator = codeattribute.iterator();
/*  87*/        boolean flag = false;
/*  88*/        LoopContext loopcontext = new LoopContext(codeattribute.getMaxLocals());
/*  90*/        do
                {
/*  90*/            if(!codeiterator.hasNext())
/*  91*/                break;
/*  91*/            if(loopBody(codeiterator, ctclass, methodinfo, loopcontext))
/*  92*/                flag = true;
                } while(true);
                ExceptionTable exceptiontable;
/*  94*/        int i = (exceptiontable = codeattribute.getExceptionTable()).size();
/*  96*/        for(int j = 0; j < i; j++)
                {
/*  97*/            Handler handler = new Handler(exceptiontable, j, codeiterator, ctclass, methodinfo);
/*  98*/            edit(handler);
/*  99*/            if(handler.edited())
                    {
/* 100*/                flag = true;
/* 101*/                loopcontext.updateMax(handler.locals(), handler.stack());
                    }
                }

/* 107*/        if(codeattribute.getMaxLocals() < loopcontext.maxLocals)
/* 108*/            codeattribute.setMaxLocals(loopcontext.maxLocals);
/* 110*/        codeattribute.setMaxStack(codeattribute.getMaxStack() + loopcontext.maxStack);
/* 112*/        try
                {
/* 112*/            if(flag)
/* 113*/                methodinfo.rebuildStackMapIf6(ctclass.getClassPool(), ctclass.getClassFile2());
                }
/* 116*/        catch(BadBytecode badbytecode)
                {
/* 117*/            throw new CannotCompileException(badbytecode.getMessage(), badbytecode);
                }
/* 120*/        return flag;
            }

            boolean doit(CtClass ctclass, MethodInfo methodinfo, LoopContext loopcontext, CodeIterator codeiterator, int i)
                throws CannotCompileException
            {
/* 130*/        boolean flag = false;
/* 131*/        do
                {
/* 131*/            if(!codeiterator.hasNext() || codeiterator.lookAhead() >= i)
/* 132*/                break;
/* 132*/            int j = codeiterator.getCodeLength();
/* 133*/            if(loopBody(codeiterator, ctclass, methodinfo, loopcontext))
                    {
/* 134*/                flag = true;
/* 135*/                int k = codeiterator.getCodeLength();
/* 136*/                if(j != k)
/* 137*/                    i += k - j;
                    }
                } while(true);
/* 141*/        return flag;
            }

            final boolean loopBody(CodeIterator codeiterator, CtClass ctclass, MethodInfo methodinfo, LoopContext loopcontext)
                throws CannotCompileException
            {
/* 181*/        Object obj = null;
/* 182*/        int i = codeiterator.next();
                int j;
/* 183*/        if((j = codeiterator.byteAt(i)) >= 178)
/* 187*/            if(j < 188)
                    {
/* 188*/                if(j == 184 || j == 185 || j == 182)
                        {
/* 191*/                    obj = new MethodCall(i, codeiterator, ctclass, methodinfo);
/* 192*/                    edit((MethodCall)obj);
                        } else
/* 194*/                if(j == 180 || j == 178 || j == 181 || j == 179)
                        {
/* 197*/                    obj = new FieldAccess(i, codeiterator, ctclass, methodinfo, j);
/* 198*/                    edit((FieldAccess)obj);
                        } else
/* 200*/                if(j == 187)
                        {
/* 201*/                    j = codeiterator.u16bitAt(i + 1);
/* 202*/                    loopcontext.newList = new NewOp(loopcontext.newList, i, methodinfo.getConstPool().getClassInfo(j));
                        } else
/* 205*/                if(j == 183)
/* 206*/                    if((j = loopcontext.newList) != null && methodinfo.getConstPool().isConstructor(((NewOp) (j)).type, codeiterator.u16bitAt(i + 1)) > 0)
                            {
/* 210*/                        obj = new NewExpr(i, codeiterator, ctclass, methodinfo, ((NewOp) (j)).type, ((NewOp) (j)).pos);
/* 212*/                        edit((NewExpr)obj);
/* 213*/                        loopcontext.newList = ((NewOp) (j)).next;
                            } else
/* 216*/                    if((j = new MethodCall(i, codeiterator, ctclass, methodinfo)).getMethodName().equals("<init>"))
                            {
/* 218*/                        obj = codeiterator = new ConstructorCall(i, codeiterator, ctclass, methodinfo);
/* 220*/                        edit(codeiterator);
                            } else
                            {
/* 223*/                        obj = j;
/* 224*/                        edit(j);
                            }
                    } else
/* 230*/            if(j == 188 || j == 189 || j == 197)
                    {
/* 232*/                obj = new NewArray(i, codeiterator, ctclass, methodinfo, j);
/* 233*/                edit((NewArray)obj);
                    } else
/* 235*/            if(j == 193)
                    {
/* 236*/                obj = new Instanceof(i, codeiterator, ctclass, methodinfo);
/* 237*/                edit((Instanceof)obj);
                    } else
/* 239*/            if(j == 192)
                    {
/* 240*/                obj = new Cast(i, codeiterator, ctclass, methodinfo);
/* 241*/                edit((Cast)obj);
                    }
/* 245*/        if(obj == null || !((Expr) (obj)).edited())
/* 246*/            break MISSING_BLOCK_LABEL_489;
/* 246*/        loopcontext.updateMax(((Expr) (obj)).locals(), ((Expr) (obj)).stack());
/* 247*/        return true;
/* 250*/        return false;
                BadBytecode badbytecode;
/* 252*/        badbytecode;
/* 253*/        throw new CannotCompileException(badbytecode);
            }

            public void edit(NewExpr newexpr)
                throws CannotCompileException
            {
            }

            public void edit(NewArray newarray)
                throws CannotCompileException
            {
            }

            public void edit(MethodCall methodcall)
                throws CannotCompileException
            {
            }

            public void edit(ConstructorCall constructorcall)
                throws CannotCompileException
            {
            }

            public void edit(FieldAccess fieldaccess)
                throws CannotCompileException
            {
            }

            public void edit(Instanceof instanceof1)
                throws CannotCompileException
            {
            }

            public void edit(Cast cast)
                throws CannotCompileException
            {
            }

            public void edit(Handler handler)
                throws CannotCompileException
            {
            }
}
