// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TransformCall.java

package javassist.convert;

import javassist.*;
import javassist.bytecode.*;

// Referenced classes of package javassist.convert:
//            Transformer

public class TransformCall extends Transformer
{

            public TransformCall(Transformer transformer, CtMethod ctmethod, CtMethod ctmethod1)
            {
/*  38*/        this(transformer, ctmethod.getName(), ctmethod1);
/*  39*/        classname = ctmethod.getDeclaringClass().getName();
            }

            public TransformCall(Transformer transformer, String s, CtMethod ctmethod)
            {
/*  45*/        super(transformer);
/*  46*/        methodname = s;
/*  47*/        methodDescriptor = ctmethod.getMethodInfo2().getDescriptor();
/*  48*/        classname = newClassname = ctmethod.getDeclaringClass().getName();
/*  49*/        newMethodname = ctmethod.getName();
/*  50*/        constPool = null;
/*  51*/        newMethodIsPrivate = Modifier.isPrivate(ctmethod.getModifiers());
            }

            public void initialize(ConstPool constpool, CodeAttribute codeattribute)
            {
/*  55*/        if(constPool != constpool)
/*  56*/            newIndex = 0;
            }

            public int transform(CtClass ctclass, int i, CodeIterator codeiterator, ConstPool constpool)
                throws BadBytecode
            {
                int j;
/*  69*/        if((j = codeiterator.byteAt(i)) == 185 || j == 183 || j == 184 || j == 182)
                {
/*  72*/            int k = codeiterator.u16bitAt(i + 1);
                    String s;
/*  73*/            if((s = constpool.eqMember(methodname, methodDescriptor, k)) != null && matchClass(s, ctclass.getClassPool()))
                    {
/*  75*/                ctclass = constpool.getMemberNameAndType(k);
/*  76*/                i = match(j, i, codeiterator, constpool.getNameAndTypeDescriptor(ctclass), constpool);
                    }
                }
/*  81*/        return i;
            }

            private boolean matchClass(String s, ClassPool classpool)
            {
/*  85*/        if(classname.equals(s))
/*  86*/            return true;
/*  89*/        s = classpool.get(s);
/*  90*/        classpool = classpool.get(classname);
/*  91*/        if(!s.subtypeOf(classpool))
/*  93*/            break MISSING_BLOCK_LABEL_73;
/*  93*/        return (s = s.getMethod(methodname, methodDescriptor)).getDeclaringClass().getName().equals(classname);
/*  96*/        JVM INSTR pop ;
/*  98*/        return true;
/* 101*/        JVM INSTR pop ;
/* 102*/        return false;
/* 105*/        return false;
            }

            protected int match(int i, int j, CodeIterator codeiterator, int k, ConstPool constpool)
                throws BadBytecode
            {
/* 111*/        if(newIndex == 0)
                {
/* 112*/            k = constpool.addNameAndTypeInfo(constpool.addUtf8Info(newMethodname), k);
/* 114*/            int l = constpool.addClassInfo(newClassname);
/* 115*/            if(i == 185)
                    {
/* 116*/                newIndex = constpool.addInterfaceMethodrefInfo(l, k);
                    } else
                    {
/* 118*/                if(newMethodIsPrivate && i == 182)
/* 119*/                    codeiterator.writeByte(183, j);
/* 121*/                newIndex = constpool.addMethodrefInfo(l, k);
                    }
/* 124*/            constPool = constpool;
                }
/* 127*/        codeiterator.write16bit(newIndex, j + 1);
/* 128*/        return j;
            }

            protected String classname;
            protected String methodname;
            protected String methodDescriptor;
            protected String newClassname;
            protected String newMethodname;
            protected boolean newMethodIsPrivate;
            protected int newIndex;
            protected ConstPool constPool;
}
