// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Sample.java

package javassist.tools.reflect;


// Referenced classes of package javassist.tools.reflect:
//            ClassMetaobject, Metalevel, Metaobject

public class Sample
{

            public Sample()
            {
            }

            public Object trap(Object aobj[], int i)
                throws Throwable
            {
                Metaobject metaobject;
/*  28*/        if((metaobject = _metaobject) == null)
/*  30*/            return ClassMetaobject.invoke(this, i, aobj);
/*  32*/        else
/*  32*/            return metaobject.trapMethodcall(i, aobj);
            }

            public static Object trapStatic(Object aobj[], int i)
                throws Throwable
            {
/*  38*/        return _classobject.trapMethodcall(i, aobj);
            }

            public static Object trapRead(Object aobj[], String s)
            {
/*  42*/        if(aobj[0] == null)
/*  43*/            return _classobject.trapFieldRead(s);
/*  45*/        else
/*  45*/            return ((Metalevel)aobj[0])._getMetaobject().trapFieldRead(s);
            }

            public static Object trapWrite(Object aobj[], String s)
            {
                Metalevel metalevel;
/*  49*/        if((metalevel = (Metalevel)aobj[0]) == null)
/*  51*/            _classobject.trapFieldWrite(s, aobj[1]);
/*  53*/        else
/*  53*/            metalevel._getMetaobject().trapFieldWrite(s, aobj[1]);
/*  55*/        return null;
            }

            private Metaobject _metaobject;
            private static ClassMetaobject _classobject;
}
