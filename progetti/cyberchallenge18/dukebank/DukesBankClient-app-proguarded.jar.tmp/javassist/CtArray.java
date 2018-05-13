// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtArray.java

package javassist;


// Referenced classes of package javassist:
//            CtClass, ClassPool, NotFoundException, CtMethod, 
//            CtConstructor

final class CtArray extends CtClass
{

            CtArray(String s, ClassPool classpool)
            {
/*  27*/        super(s);
/*  39*/        interfaces = null;
/*  28*/        pool = classpool;
            }

            public final ClassPool getClassPool()
            {
/*  32*/        return pool;
            }

            public final boolean isArray()
            {
/*  36*/        return true;
            }

            public final int getModifiers()
            {
/*  42*/        int i = 16;
/*  44*/        try
                {
/*  44*/            i = 0x10 | getComponentType().getModifiers() & 7;
                }
/*  47*/        catch(NotFoundException _ex) { }
/*  48*/        return i;
            }

            public final CtClass[] getInterfaces()
                throws NotFoundException
            {
/*  52*/        if(interfaces == null)
                {
/*  53*/            Class aclass[] = [Ljava/lang/Object;.getInterfaces();
/*  56*/            interfaces = new CtClass[aclass.length];
/*  57*/            for(int i = 0; i < aclass.length; i++)
/*  58*/                interfaces[i] = pool.get(aclass[i].getName());

                }
/*  61*/        return interfaces;
            }

            public final boolean subtypeOf(CtClass ctclass)
                throws NotFoundException
            {
/*  65*/        if(super.subtypeOf(ctclass))
/*  66*/            return true;
                String s;
/*  68*/        if((s = ctclass.getName()).equals("java.lang.Object"))
/*  70*/            return true;
/*  72*/        CtClass actclass[] = getInterfaces();
/*  73*/        for(int i = 0; i < actclass.length; i++)
/*  74*/            if(actclass[i].subtypeOf(ctclass))
/*  75*/                return true;

/*  77*/        return ctclass.isArray() && getComponentType().subtypeOf(ctclass.getComponentType());
            }

            public final CtClass getComponentType()
                throws NotFoundException
            {
/*  82*/        String s = getName();
/*  83*/        return pool.get(s.substring(0, s.length() - 2));
            }

            public final CtClass getSuperclass()
                throws NotFoundException
            {
/*  87*/        return pool.get("java.lang.Object");
            }

            public final CtMethod[] getMethods()
            {
/*  92*/        return getSuperclass().getMethods();
/*  94*/        JVM INSTR pop ;
/*  95*/        return super.getMethods();
            }

            public final CtMethod getMethod(String s, String s1)
                throws NotFoundException
            {
/* 102*/        return getSuperclass().getMethod(s, s1);
            }

            public final CtConstructor[] getConstructors()
            {
/* 107*/        return getSuperclass().getConstructors();
/* 109*/        JVM INSTR pop ;
/* 110*/        return super.getConstructors();
            }

            protected ClassPool pool;
            private CtClass interfaces[];
}
