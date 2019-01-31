// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtNewNestedClass.java

package javassist;

import javassist.bytecode.ClassFile;
import javassist.bytecode.InnerClassesAttribute;

// Referenced classes of package javassist:
//            CtNewClass, ClassPool, CtClass, NotFoundException

class CtNewNestedClass extends CtNewClass
{

            CtNewNestedClass(String s, ClassPool classpool, boolean flag, CtClass ctclass)
            {
/*  29*/        super(s, classpool, flag, ctclass);
            }

            public void setModifiers(int i)
            {
/*  36*/        i &= -9;
/*  37*/        super.setModifiers(i);
/*  38*/        updateInnerEntry(i, getName(), this, true);
            }

            private static void updateInnerEntry(int i, String s, CtClass ctclass, boolean flag)
            {
                Object obj;
/*  42*/        if((obj = (InnerClassesAttribute)((ClassFile) (obj = ctclass.getClassFile2())).getAttribute("InnerClasses")) == null)
/*  46*/            return;
/*  48*/        int j = ((InnerClassesAttribute) (obj)).tableLength();
/*  49*/        int l = 0;
/*  49*/        do
                {
/*  49*/            if(l >= j)
/*  50*/                break;
/*  50*/            if(s.equals(((InnerClassesAttribute) (obj)).innerClass(l)))
                    {
/*  51*/                int k = ((InnerClassesAttribute) (obj)).accessFlags(l) & 8;
/*  52*/                ((InnerClassesAttribute) (obj)).setAccessFlags(l, i | k);
/*  53*/                if((obj = ((InnerClassesAttribute) (obj)).outerClass(l)) != null && flag)
/*  56*/                    try
                            {
/*  56*/                        ctclass = ctclass.getClassPool().get(((String) (obj)));
/*  57*/                        updateInnerEntry(i, s, ctclass, false);
/*  62*/                        return;
                            }
/*  59*/                    catch(NotFoundException _ex)
                            {
/*  60*/                        throw new RuntimeException((new StringBuilder("cannot find the declaring class: ")).append(((String) (obj))).toString());
                            }
/*  49*/                break;
                    }
/*  49*/            l++;
                } while(true);
            }
}
