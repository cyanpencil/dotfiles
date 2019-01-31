// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Resource.java

package javax.annotation;

import java.lang.annotation.Annotation;

public interface Resource
    extends Annotation
{
    public static final class AuthenticationType extends Enum
    {

                public static AuthenticationType[] values()
                {
/* 101*/            return (AuthenticationType[])$VALUES.clone();
                }

                public static AuthenticationType valueOf(String s)
                {
/* 101*/            return (AuthenticationType)Enum.valueOf(javax/annotation/Resource$AuthenticationType, s);
                }

                public static final AuthenticationType CONTAINER;
                public static final AuthenticationType APPLICATION;
                private static final AuthenticationType $VALUES[];

                static 
                {
/* 102*/            CONTAINER = new AuthenticationType("CONTAINER", 0);
/* 103*/            APPLICATION = new AuthenticationType("APPLICATION", 1);
/* 101*/            $VALUES = (new AuthenticationType[] {
/* 101*/                CONTAINER, APPLICATION
                    });
                }

                private AuthenticationType(String s, int i)
                {
/* 101*/            super(s, i);
                }
    }


    public abstract String name();

    public abstract String lookup();

    public abstract Class type();

    public abstract AuthenticationType authenticationType();

    public abstract boolean shareable();

    public abstract String mappedName();

    public abstract String description();
}
