// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TransformNew.java

package javassist.convert;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.bytecode.*;

// Referenced classes of package javassist.convert:
//            Transformer

public final class TransformNew extends Transformer
{

            public TransformNew(Transformer transformer, String s, String s1, String s2)
            {
/*  29*/        super(transformer);
/*  30*/        classname = s;
/*  31*/        trapClass = s1;
/*  32*/        trapMethod = s2;
            }

            public final void initialize(ConstPool constpool, CodeAttribute codeattribute)
            {
/*  36*/        nested = 0;
            }

            public final int transform(CtClass ctclass, int i, CodeIterator codeiterator, ConstPool constpool)
                throws CannotCompileException
            {
/*  55*/        if((ctclass = codeiterator.byteAt(i)) == 187)
                {
/*  57*/            ctclass = codeiterator.u16bitAt(i + 1);
/*  58*/            if(constpool.getClassInfo(ctclass).equals(classname))
                    {
/*  59*/                if(codeiterator.byteAt(i + 3) != 89)
/*  60*/                    throw new CannotCompileException("NEW followed by no DUP was found");
/*  63*/                codeiterator.writeByte(0, i);
/*  64*/                codeiterator.writeByte(0, i + 1);
/*  65*/                codeiterator.writeByte(0, i + 2);
/*  66*/                codeiterator.writeByte(0, i + 3);
/*  67*/                nested++;
/*  69*/                if((ctclass = (StackMapTable)codeiterator.get().getAttribute("StackMapTable")) != null)
/*  72*/                    ctclass.removeNew(i);
/*  74*/                if((ctclass = (StackMap)codeiterator.get().getAttribute("StackMap")) != null)
/*  77*/                    ctclass.removeNew(i);
                    }
                } else
/*  80*/        if(ctclass == 183)
                {
/*  81*/            ctclass = codeiterator.u16bitAt(i + 1);
/*  82*/            if((ctclass = constpool.isConstructor(classname, ctclass)) != 0 && nested > 0)
                    {
/*  84*/                ctclass = computeMethodref(ctclass, constpool);
/*  85*/                codeiterator.writeByte(184, i);
/*  86*/                codeiterator.write16bit(ctclass, i + 1);
/*  87*/                nested--;
                    }
                }
/*  91*/        return i;
            }

            private int computeMethodref(int i, ConstPool constpool)
            {
/*  95*/        int j = constpool.addClassInfo(trapClass);
/*  96*/        int k = constpool.addUtf8Info(trapMethod);
/*  97*/        i = constpool.addUtf8Info(Descriptor.changeReturnType(classname, constpool.getUtf8Info(i)));
/* 100*/        return constpool.addMethodrefInfo(j, constpool.addNameAndTypeInfo(k, i));
            }

            private int nested;
            private String classname;
            private String trapClass;
            private String trapMethod;
}
