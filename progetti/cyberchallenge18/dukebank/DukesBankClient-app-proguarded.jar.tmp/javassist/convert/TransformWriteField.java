// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TransformWriteField.java

package javassist.convert;

import javassist.CtClass;
import javassist.CtField;
import javassist.bytecode.*;

// Referenced classes of package javassist.convert:
//            TransformReadField, Transformer

public final class TransformWriteField extends TransformReadField
{

            public TransformWriteField(Transformer transformer, CtField ctfield, String s, String s1)
            {
/*  27*/        super(transformer, ctfield, s, s1);
            }

            public final int transform(CtClass ctclass, int i, CodeIterator codeiterator, ConstPool constpool)
                throws BadBytecode
            {
                int j;
/*  33*/        if((j = codeiterator.byteAt(i)) == 181 || j == 179)
                {
/*  35*/            int k = codeiterator.u16bitAt(i + 1);
/*  36*/            if((ctclass = isField(ctclass.getClassPool(), constpool, fieldClass, fieldname, isPrivate, k)) != null)
                    {
/*  39*/                if(j == 179)
                        {
/*  40*/                    CodeAttribute codeattribute = codeiterator.get();
/*  41*/                    codeiterator.move(i);
                            char c;
/*  42*/                    if((c = ctclass.charAt(0)) == 'J' || c == 'D')
                            {
/*  45*/                        i = codeiterator.insertGap(3);
/*  46*/                        codeiterator.writeByte(1, i);
/*  47*/                        codeiterator.writeByte(91, i + 1);
/*  48*/                        codeiterator.writeByte(87, i + 2);
/*  49*/                        codeattribute.setMaxStack(codeattribute.getMaxStack() + 2);
                            } else
                            {
/*  53*/                        i = codeiterator.insertGap(2);
/*  54*/                        codeiterator.writeByte(1, i);
/*  55*/                        codeiterator.writeByte(95, i + 1);
/*  56*/                        codeattribute.setMaxStack(codeattribute.getMaxStack() + 1);
                            }
/*  59*/                    i = codeiterator.next();
                        }
/*  62*/                codeattribute = constpool.addClassInfo(methodClassname);
/*  63*/                String s = (new StringBuilder("(Ljava/lang/Object;")).append(ctclass).append(")V").toString();
/*  64*/                ctclass = constpool.addMethodrefInfo(codeattribute, methodName, s);
/*  65*/                codeiterator.writeByte(184, i);
/*  66*/                codeiterator.write16bit(ctclass, i + 1);
                    }
                }
/*  70*/        return i;
            }
}
