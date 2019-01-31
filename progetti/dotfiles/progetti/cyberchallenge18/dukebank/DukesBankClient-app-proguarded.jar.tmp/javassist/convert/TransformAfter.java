// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TransformAfter.java

package javassist.convert;

import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeIterator;

// Referenced classes of package javassist.convert:
//            TransformBefore, Transformer

public class TransformAfter extends TransformBefore
{

            public TransformAfter(Transformer transformer, CtMethod ctmethod, CtMethod ctmethod1)
                throws NotFoundException
            {
/*  28*/        super(transformer, ctmethod, ctmethod1);
            }

            protected int match2(int i, CodeIterator codeiterator)
                throws BadBytecode
            {
/*  32*/        codeiterator.move(i);
/*  33*/        codeiterator.insert(saveCode);
/*  34*/        codeiterator.insert(loadCode);
/*  35*/        int j = codeiterator.insertGap(3);
/*  36*/        codeiterator.setMark(j);
/*  37*/        codeiterator.insert(loadCode);
/*  38*/        i = codeiterator.next();
/*  39*/        j = codeiterator.getMark();
/*  40*/        codeiterator.writeByte(codeiterator.byteAt(i), j);
/*  41*/        codeiterator.write16bit(codeiterator.u16bitAt(i + 1), j + 1);
/*  42*/        codeiterator.writeByte(184, i);
/*  43*/        codeiterator.write16bit(newIndex, i + 1);
/*  44*/        codeiterator.move(j);
/*  45*/        return codeiterator.next();
            }
}
