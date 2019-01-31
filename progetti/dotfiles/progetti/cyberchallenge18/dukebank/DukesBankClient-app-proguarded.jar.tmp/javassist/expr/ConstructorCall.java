// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstructorCall.java

package javassist.expr;

import javassist.*;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.MethodInfo;

// Referenced classes of package javassist.expr:
//            MethodCall

public class ConstructorCall extends MethodCall
{

            protected ConstructorCall(int i, CodeIterator codeiterator, CtClass ctclass, MethodInfo methodinfo)
            {
/*  37*/        super(i, codeiterator, ctclass, methodinfo);
            }

            public String getMethodName()
            {
/*  44*/        if(isSuper())
/*  44*/            return "super";
/*  44*/        else
/*  44*/            return "this";
            }

            public CtMethod getMethod()
                throws NotFoundException
            {
/*  53*/        throw new NotFoundException("this is a constructor call.  Call getConstructor().");
            }

            public CtConstructor getConstructor()
                throws NotFoundException
            {
/*  60*/        return getCtClass().getConstructor(getSignature());
            }

            public boolean isSuper()
            {
/*  68*/        return super.isSuper();
            }
}
