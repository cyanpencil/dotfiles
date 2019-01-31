// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassMap.java

package javassist;

import java.util.HashMap;
import javassist.bytecode.Descriptor;

// Referenced classes of package javassist:
//            CtClass

public class ClassMap extends HashMap
{

            public ClassMap()
            {
/*  56*/        parent = null;
            }

            ClassMap(ClassMap classmap)
            {
/*  58*/        parent = classmap;
            }

            public void put(CtClass ctclass, CtClass ctclass1)
            {
/*  71*/        put(ctclass.getName(), ctclass1.getName());
            }

            public void put(String s, String s1)
            {
/*  93*/        if(s == s1)
/*  94*/            return;
/*  96*/        s = toJvmName(s);
                String s2;
/*  97*/        if((s2 = (String)get(s)) == null || !s2.equals(s))
/*  99*/            super.put(s, toJvmName(s1));
            }

            public void putIfNone(String s, String s1)
            {
/* 112*/        if(s == s1)
/* 113*/            return;
/* 115*/        s = toJvmName(s);
                String s2;
/* 116*/        if((s2 = (String)get(s)) == null)
/* 118*/            super.put(s, toJvmName(s1));
            }

            protected final void put0(Object obj, Object obj1)
            {
/* 122*/        super.put(obj, obj1);
            }

            public Object get(Object obj)
            {
                Object obj1;
/* 136*/        if((obj1 = super.get(obj)) == null && parent != null)
/* 138*/            return parent.get(obj);
/* 140*/        else
/* 140*/            return obj1;
            }

            public void fix(CtClass ctclass)
            {
/* 147*/        fix(ctclass.getName());
            }

            public void fix(String s)
            {
/* 154*/        s = toJvmName(s);
/* 155*/        super.put(s, s);
            }

            public static String toJvmName(String s)
            {
/* 163*/        return Descriptor.toJvmName(s);
            }

            public static String toJavaName(String s)
            {
/* 171*/        return Descriptor.toJavaName(s);
            }

            private ClassMap parent;
}
