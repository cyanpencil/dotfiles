// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtClass.java

package javassist;


// Referenced classes of package javassist:
//            ClassMap, CtClass

class init> extends ClassMap
{

            public void put(String s, String s1)
            {
/* 520*/        put0(s, s1);
            }

            public Object get(Object obj)
            {
/* 524*/        obj = toJavaName((String)obj);
/* 525*/        put0(obj, obj);
/* 526*/        return null;
            }

            public void fix(String s)
            {
            }

            final CtClass this$0;

            ()
            {
/* 518*/        this$0 = CtClass.this;
/* 518*/        super();
            }
}
