// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtNewClass.java

package javassist;

import java.io.DataOutputStream;
import java.io.IOException;
import javassist.bytecode.ClassFile;

// Referenced classes of package javassist:
//            CtClassType, CannotCompileException, ClassPool, CtClass, 
//            CtConstructor, CtNewConstructor, Modifier, NotFoundException

class CtNewClass extends CtClassType
{

            CtNewClass(String s, ClassPool classpool, boolean flag, CtClass ctclass)
            {
/*  30*/        super(s, classpool);
/*  31*/        wasChanged = true;
/*  33*/        if(flag || ctclass == null)
/*  34*/            classpool = null;
/*  36*/        else
/*  36*/            classpool = ctclass.getName();
/*  38*/        classfile = new ClassFile(flag, s, classpool);
/*  39*/        if(flag && ctclass != null)
/*  40*/            classfile.setInterfaces(new String[] {
/*  40*/                ctclass.getName()
                    });
/*  42*/        setModifiers(Modifier.setPublic(getModifiers()));
/*  43*/        hasConstructor = flag;
            }

            protected void extendToString(StringBuffer stringbuffer)
            {
/*  47*/        if(hasConstructor)
/*  48*/            stringbuffer.append("hasConstructor ");
/*  50*/        super.extendToString(stringbuffer);
            }

            public void addConstructor(CtConstructor ctconstructor)
                throws CannotCompileException
            {
/*  56*/        hasConstructor = true;
/*  57*/        super.addConstructor(ctconstructor);
            }

            public void toBytecode(DataOutputStream dataoutputstream)
                throws CannotCompileException, IOException
            {
/*  63*/        if(!hasConstructor)
/*  65*/            try
                    {
/*  65*/                inheritAllConstructors();
/*  66*/                hasConstructor = true;
                    }
                    // Misplaced declaration of an exception variable
/*  68*/            catch(DataOutputStream dataoutputstream)
                    {
/*  69*/                throw new CannotCompileException(dataoutputstream);
                    }
/*  72*/        super.toBytecode(dataoutputstream);
            }

            public void inheritAllConstructors()
                throws CannotCompileException, NotFoundException
            {
                CtClass ctclass;
/*  88*/        CtConstructor actconstructor[] = (ctclass = getSuperclass()).getDeclaredConstructors();
/*  91*/        int i = 0;
/*  92*/        for(int j = 0; j < actconstructor.length; j++)
                {
                    CtConstructor ctconstructor;
/*  93*/            int k = (ctconstructor = actconstructor[j]).getModifiers();
/*  95*/            if(isInheritable(k, ctclass))
                    {
/*  96*/                (ctconstructor = CtNewConstructor.make(ctconstructor.getParameterTypes(), ctconstructor.getExceptionTypes(), this)).setModifiers(k & 7);
/* 100*/                addConstructor(ctconstructor);
/* 101*/                i++;
                    }
                }

/* 105*/        if(i <= 0)
/* 106*/            throw new CannotCompileException((new StringBuilder("no inheritable constructor in ")).append(ctclass.getName()).toString());
/* 109*/        else
/* 109*/            return;
            }

            private boolean isInheritable(int i, CtClass ctclass)
            {
/* 112*/        if(Modifier.isPrivate(i))
/* 113*/            return false;
/* 115*/        if(Modifier.isPackage(i))
                {
/* 116*/            i = getPackageName();
/* 117*/            ctclass = ctclass.getPackageName();
/* 118*/            if(i == null)
/* 119*/                return ctclass == null;
/* 121*/            else
/* 121*/                return i.equals(ctclass);
                } else
                {
/* 124*/            return true;
                }
            }

            protected boolean hasConstructor;
}
