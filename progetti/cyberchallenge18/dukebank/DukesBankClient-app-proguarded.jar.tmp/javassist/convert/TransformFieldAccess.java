// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TransformFieldAccess.java

package javassist.convert;

import javassist.*;
import javassist.bytecode.*;

// Referenced classes of package javassist.convert:
//            Transformer, TransformReadField

public final class TransformFieldAccess extends Transformer
{

            public TransformFieldAccess(Transformer transformer, CtField ctfield, String s, String s1)
            {
/*  37*/        super(transformer);
/*  38*/        fieldClass = ctfield.getDeclaringClass();
/*  39*/        fieldname = ctfield.getName();
/*  40*/        isPrivate = Modifier.isPrivate(ctfield.getModifiers());
/*  41*/        newClassname = s;
/*  42*/        newFieldname = s1;
/*  43*/        constPool = null;
            }

            public final void initialize(ConstPool constpool, CodeAttribute codeattribute)
            {
/*  47*/        if(constPool != constpool)
/*  48*/            newIndex = 0;
            }

            public final int transform(CtClass ctclass, int i, CodeIterator codeiterator, ConstPool constpool)
            {
                int j;
/*  60*/        if((j = codeiterator.byteAt(i)) == 180 || j == 178 || j == 181 || j == 179)
                {
/*  63*/            int k = codeiterator.u16bitAt(i + 1);
/*  64*/            if((ctclass = TransformReadField.isField(ctclass.getClassPool(), constpool, fieldClass, fieldname, isPrivate, k)) != null)
                    {
/*  68*/                if(newIndex == 0)
                        {
/*  69*/                    ctclass = constpool.addNameAndTypeInfo(newFieldname, ctclass);
/*  71*/                    newIndex = constpool.addFieldrefInfo(constpool.addClassInfo(newClassname), ctclass);
/*  73*/                    constPool = constpool;
                        }
/*  76*/                codeiterator.write16bit(newIndex, i + 1);
                    }
                }
/*  80*/        return i;
            }

            private String newClassname;
            private String newFieldname;
            private String fieldname;
            private CtClass fieldClass;
            private boolean isPrivate;
            private int newIndex;
            private ConstPool constPool;
}
