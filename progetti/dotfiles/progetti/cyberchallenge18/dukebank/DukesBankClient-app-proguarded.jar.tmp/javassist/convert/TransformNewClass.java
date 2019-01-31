// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TransformNewClass.java

package javassist.convert;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.bytecode.*;

// Referenced classes of package javassist.convert:
//            Transformer

public final class TransformNewClass extends Transformer
{

            public TransformNewClass(Transformer transformer, String s, String s1)
            {
/*  30*/        super(transformer);
/*  31*/        classname = s;
/*  32*/        newClassName = s1;
            }

            public final void initialize(ConstPool constpool, CodeAttribute codeattribute)
            {
/*  36*/        nested = 0;
/*  37*/        newClassIndex = newMethodNTIndex = newMethodIndex = 0;
            }

            public final int transform(CtClass ctclass, int i, CodeIterator codeiterator, ConstPool constpool)
                throws CannotCompileException
            {
/*  51*/        if((ctclass = codeiterator.byteAt(i)) == 187)
                {
/*  53*/            ctclass = codeiterator.u16bitAt(i + 1);
/*  54*/            if(constpool.getClassInfo(ctclass).equals(classname))
                    {
/*  55*/                if(codeiterator.byteAt(i + 3) != 89)
/*  56*/                    throw new CannotCompileException("NEW followed by no DUP was found");
/*  59*/                if(newClassIndex == 0)
/*  60*/                    newClassIndex = constpool.addClassInfo(newClassName);
/*  62*/                codeiterator.write16bit(newClassIndex, i + 1);
/*  63*/                nested++;
                    }
                } else
/*  66*/        if(ctclass == 183)
                {
/*  67*/            ctclass = codeiterator.u16bitAt(i + 1);
                    int j;
/*  68*/            if((j = constpool.isConstructor(classname, ctclass)) != 0 && nested > 0)
                    {
/*  70*/                ctclass = constpool.getMethodrefNameAndType(ctclass);
/*  71*/                if(newMethodNTIndex != ctclass)
                        {
/*  72*/                    newMethodNTIndex = ctclass;
/*  73*/                    newMethodIndex = constpool.addMethodrefInfo(newClassIndex, ctclass);
                        }
/*  76*/                codeiterator.write16bit(newMethodIndex, i + 1);
/*  77*/                nested--;
                    }
                }
/*  81*/        return i;
            }

            private int nested;
            private String classname;
            private String newClassName;
            private int newClassIndex;
            private int newMethodNTIndex;
            private int newMethodIndex;
}
