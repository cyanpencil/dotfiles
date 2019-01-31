// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Transformer.java

package javassist.convert;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.bytecode.*;

public abstract class Transformer
    implements Opcode
{

            public Transformer(Transformer transformer)
            {
/*  38*/        next = transformer;
            }

            public Transformer getNext()
            {
/*  41*/        return next;
            }

            public void initialize(ConstPool constpool, CodeAttribute codeattribute)
            {
            }

            public void initialize(ConstPool constpool, CtClass ctclass, MethodInfo methodinfo)
                throws CannotCompileException
            {
/*  46*/        initialize(constpool, methodinfo.getCodeAttribute());
            }

            public void clean()
            {
            }

            public abstract int transform(CtClass ctclass, int i, CodeIterator codeiterator, ConstPool constpool)
                throws CannotCompileException, BadBytecode;

            public int extraLocals()
            {
/*  54*/        return 0;
            }

            public int extraStack()
            {
/*  56*/        return 0;
            }

            private Transformer next;
}
