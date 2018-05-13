// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EntityTag.java

package javax.ws.rs.core;

import javax.ws.rs.ext.RuntimeDelegate;

public class EntityTag
{

            public EntityTag(String s)
            {
/*  68*/        this(s, false);
            }

            public EntityTag(String s, boolean flag)
            {
/*  79*/        if(s == null)
                {
/*  80*/            throw new IllegalArgumentException("value==null");
                } else
                {
/*  82*/            value = s;
/*  83*/            weak = flag;
/*  84*/            return;
                }
            }

            public static EntityTag valueOf(String s)
            {
/*  95*/        return (EntityTag)HEADER_DELEGATE.fromString(s);
            }

            public boolean isWeak()
            {
/* 104*/        return weak;
            }

            public String getValue()
            {
/* 113*/        return value;
            }

            public boolean equals(Object obj)
            {
/* 125*/        if(obj == null)
/* 126*/            return false;
/* 128*/        if(!(obj instanceof EntityTag))
/* 129*/            return super.equals(obj);
/* 131*/        obj = (EntityTag)obj;
/* 132*/        return value.equals(((EntityTag) (obj)).getValue()) && weak == ((EntityTag) (obj)).isWeak();
            }

            public int hashCode()
            {
/* 146*/        int i = 51 + (value == null ? 0 : value.hashCode());
/* 147*/        return i = i * 17 + (weak ? 1 : 0);
            }

            public String toString()
            {
/* 159*/        return HEADER_DELEGATE.toString(this);
            }

            private static final javax.ws.rs.ext.RuntimeDelegate.HeaderDelegate HEADER_DELEGATE = RuntimeDelegate.getInstance().createHeaderDelegate(javax/ws/rs/core/EntityTag);
            private String value;
            private boolean weak;

}
