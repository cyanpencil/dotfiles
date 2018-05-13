// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Resource.java

package javax.annotation;


// Referenced classes of package javax.annotation:
//            Resource

public static final class  extends Enum
{

            public static [] values()
            {
/* 101*/        return ([])$VALUES.clone();
            }

            public static e_3B_.clone valueOf(String s)
            {
/* 101*/        return (e_3B_.clone)Enum.valueOf(javax/annotation/Resource$AuthenticationType, s);
            }

            public static final APPLICATION CONTAINER;
            public static final APPLICATION APPLICATION;
            private static final APPLICATION $VALUES[];

            static 
            {
/* 102*/        CONTAINER = new <init>("CONTAINER", 0);
/* 103*/        APPLICATION = new <init>("APPLICATION", 1);
/* 101*/        $VALUES = (new .VALUES[] {
/* 101*/            CONTAINER, APPLICATION
                });
            }

            private (String s, int i)
            {
/* 101*/        super(s, i);
            }
}
