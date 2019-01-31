// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Stage.java

package org.glassfish.jersey.process.internal;


// Referenced classes of package org.glassfish.jersey.process.internal:
//            Stage

public static final class next
{

            public static next of(Object obj, Stage stage)
            {
/*  93*/        return new <init>(obj, stage);
            }

            public static <init> of(Object obj)
            {
/* 105*/        return new <init>(obj, null);
            }

            public final Object result()
            {
/* 114*/        return result;
            }

            public final Stage next()
            {
/* 124*/        return next;
            }

            public final boolean hasNext()
            {
/* 139*/        return next != null;
            }

            private final Object result;
            private final Stage next;

            (Object obj, Stage stage)
            {
/*  77*/        result = obj;
/*  78*/        next = stage;
            }
}
