// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Throwables.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Preconditions

public final class Throwables
{

            public static void propagateIfInstanceOf(Throwable throwable, Class class1)
                throws Throwable
            {
/*  63*/        if(throwable != null && class1.isInstance(throwable))
/*  64*/            throw (Throwable)class1.cast(throwable);
/*  66*/        else
/*  66*/            return;
            }

            public static void propagateIfPossible(Throwable throwable)
            {
/*  83*/        propagateIfInstanceOf(throwable, java/lang/Error);
/*  84*/        propagateIfInstanceOf(throwable, java/lang/RuntimeException);
            }

            public static RuntimeException propagate(Throwable throwable)
            {
/* 159*/        propagateIfPossible((Throwable)Preconditions.checkNotNull(throwable));
/* 160*/        throw new RuntimeException(throwable);
            }
}
