// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TransformReadField.java

package javassist.convert;

import javassist.*;
import javassist.bytecode.*;

// Referenced classes of package javassist.convert:
//            Transformer

public class TransformReadField extends Transformer
{

            public TransformReadField(Transformer transformer, CtField ctfield, String s, String s1)
            {
/*  35*/        super(transformer);
/*  36*/        fieldClass = ctfield.getDeclaringClass();
/*  37*/        fieldname = ctfield.getName();
/*  38*/        methodClassname = s;
/*  39*/        methodName = s1;
/*  40*/        isPrivate = Modifier.isPrivate(ctfield.getModifiers());
            }

            static String isField(ClassPool classpool, ConstPool constpool, CtClass ctclass, String s, boolean flag, int i)
            {
/*  45*/        if(!constpool.getFieldrefName(i).equals(s))
/*  46*/            return null;
/*  49*/        if((classpool = classpool.get(constpool.getFieldrefClassName(i))) == ctclass || !flag && isFieldInSuper(classpool, ctclass, s))
/*  51*/            return constpool.getFieldrefType(i);
/*  53*/        break MISSING_BLOCK_LABEL_56;
/*  53*/        JVM INSTR pop ;
/*  54*/        return null;
            }

            static boolean isFieldInSuper(CtClass ctclass, CtClass ctclass1, String s)
            {
/*  58*/        if(!ctclass.subclassOf(ctclass1))
/*  59*/            return false;
/*  62*/        if((ctclass = ctclass.getField(s)).getDeclaringClass() == ctclass1)
/*  63*/            return true;
/*  63*/        return false;
/*  65*/        JVM INSTR pop ;
/*  66*/        return false;
            }

            public int transform(CtClass ctclass, int i, CodeIterator codeiterator, ConstPool constpool)
                throws BadBytecode
            {
                int j;
/*  72*/        if((j = codeiterator.byteAt(i)) == 180 || j == 178)
                {
/*  74*/            int k = codeiterator.u16bitAt(i + 1);
/*  75*/            if((ctclass = isField(ctclass.getClassPool(), constpool, fieldClass, fieldname, isPrivate, k)) != null)
                    {
/*  78*/                if(j == 178)
                        {
/*  79*/                    codeiterator.move(i);
/*  80*/                    i = codeiterator.insertGap(1);
/*  81*/                    codeiterator.writeByte(1, i);
/*  82*/                    i = codeiterator.next();
                        }
/*  85*/                ctclass = (new StringBuilder("(Ljava/lang/Object;)")).append(ctclass).toString();
/*  86*/                j = constpool.addClassInfo(methodClassname);
/*  87*/                ctclass = constpool.addMethodrefInfo(j, methodName, ctclass);
/*  88*/                codeiterator.writeByte(184, i);
/*  89*/                codeiterator.write16bit(ctclass, i + 1);
/*  90*/                return i;
                    }
                }
/*  94*/        return i;
            }

            protected String fieldname;
            protected CtClass fieldClass;
            protected boolean isPrivate;
            protected String methodClassname;
            protected String methodName;
}
