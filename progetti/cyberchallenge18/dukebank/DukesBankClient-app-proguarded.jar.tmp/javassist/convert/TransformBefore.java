// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TransformBefore.java

package javassist.convert;

import javassist.*;
import javassist.bytecode.*;

// Referenced classes of package javassist.convert:
//            TransformCall, Transformer

public class TransformBefore extends TransformCall
{

            public TransformBefore(Transformer transformer, CtMethod ctmethod, CtMethod ctmethod1)
                throws NotFoundException
            {
/*  34*/        super(transformer, ctmethod, ctmethod1);
/*  37*/        methodDescriptor = ctmethod.getMethodInfo2().getDescriptor();
/*  39*/        parameterTypes = ctmethod.getParameterTypes();
/*  40*/        locals = 0;
/*  41*/        maxLocals = 0;
/*  42*/        saveCode = loadCode = null;
            }

            public void initialize(ConstPool constpool, CodeAttribute codeattribute)
            {
/*  46*/        super.initialize(constpool, codeattribute);
/*  47*/        locals = 0;
/*  48*/        maxLocals = codeattribute.getMaxLocals();
/*  49*/        saveCode = loadCode = null;
            }

            protected int match(int i, int j, CodeIterator codeiterator, int k, ConstPool constpool)
                throws BadBytecode
            {
/*  55*/        if(newIndex == 0)
                {
/*  56*/            i = (new StringBuilder()).append(Descriptor.ofParameters(parameterTypes)).append('V').toString();
/*  57*/            i = Descriptor.insertParameter(classname, i);
/*  58*/            i = constpool.addNameAndTypeInfo(newMethodname, i);
/*  59*/            k = constpool.addClassInfo(newClassname);
/*  60*/            newIndex = constpool.addMethodrefInfo(k, i);
/*  61*/            constPool = constpool;
                }
/*  64*/        if(saveCode == null)
/*  65*/            makeCode(parameterTypes, constpool);
/*  67*/        return match2(j, codeiterator);
            }

            protected int match2(int i, CodeIterator codeiterator)
                throws BadBytecode
            {
/*  71*/        codeiterator.move(i);
/*  72*/        codeiterator.insert(saveCode);
/*  73*/        codeiterator.insert(loadCode);
/*  74*/        i = codeiterator.insertGap(3);
/*  75*/        codeiterator.writeByte(184, i);
/*  76*/        codeiterator.write16bit(newIndex, i + 1);
/*  77*/        codeiterator.insert(loadCode);
/*  78*/        return codeiterator.next();
            }

            public int extraLocals()
            {
/*  81*/        return locals;
            }

            protected void makeCode(CtClass actclass[], ConstPool constpool)
            {
/*  84*/        Bytecode bytecode = new Bytecode(constpool, 0, 0);
/*  85*/        constpool = new Bytecode(constpool, 0, 0);
/*  87*/        int i = maxLocals;
/*  88*/        int j = actclass != null ? actclass.length : 0;
/*  89*/        constpool.addAload(i);
/*  90*/        makeCode2(bytecode, constpool, 0, j, actclass, i + 1);
/*  91*/        bytecode.addAstore(i);
/*  93*/        saveCode = bytecode.get();
/*  94*/        loadCode = constpool.get();
            }

            private void makeCode2(Bytecode bytecode, Bytecode bytecode1, int i, int j, CtClass actclass[], int k)
            {
/* 100*/        if(i < j)
                {
/* 101*/            int l = bytecode1.addLoad(k, actclass[i]);
/* 102*/            makeCode2(bytecode, bytecode1, i + 1, j, actclass, k + l);
/* 103*/            bytecode.addStore(k, actclass[i]);
/* 104*/            return;
                } else
                {
/* 106*/            locals = k - maxLocals;
/* 107*/            return;
                }
            }

            protected CtClass parameterTypes[];
            protected int locals;
            protected int maxLocals;
            protected byte saveCode[];
            protected byte loadCode[];
}
