// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Cookie.java

package javax.ws.rs.core;

import javax.ws.rs.ext.RuntimeDelegate;

public class Cookie
{

            public Cookie(String s, String s1, String s2, String s3, int i)
                throws IllegalArgumentException
            {
/*  81*/        if(s == null)
                {
/*  82*/            throw new IllegalArgumentException("name==null");
                } else
                {
/*  84*/            name = s;
/*  85*/            value = s1;
/*  86*/            version = i;
/*  87*/            domain = s3;
/*  88*/            path = s2;
/*  89*/            return;
                }
            }

            public Cookie(String s, String s1, String s2, String s3)
                throws IllegalArgumentException
            {
/* 102*/        this(s, s1, s2, s3, 1);
            }

            public Cookie(String s, String s1)
                throws IllegalArgumentException
            {
/* 114*/        this(s, s1, null, null);
            }

            public static Cookie valueOf(String s)
            {
/* 126*/        return (Cookie)HEADER_DELEGATE.fromString(s);
            }

            public String getName()
            {
/* 135*/        return name;
            }

            public String getValue()
            {
/* 144*/        return value;
            }

            public int getVersion()
            {
/* 153*/        return version;
            }

            public String getDomain()
            {
/* 162*/        return domain;
            }

            public String getPath()
            {
/* 171*/        return path;
            }

            public String toString()
            {
/* 182*/        return HEADER_DELEGATE.toString(this);
            }

            public int hashCode()
            {
/* 193*/        int i = 679 + (name == null ? 0 : name.hashCode());
/* 194*/        i = i * 97 + (value == null ? 0 : value.hashCode());
/* 195*/        i = i * 97 + version;
/* 196*/        i = i * 97 + (path == null ? 0 : path.hashCode());
/* 197*/        return i = i * 97 + (domain == null ? 0 : domain.hashCode());
            }

            public boolean equals(Object obj)
            {
/* 211*/        if(obj == null)
/* 212*/            return false;
/* 214*/        if(getClass() != obj.getClass())
/* 215*/            return false;
/* 217*/        obj = (Cookie)obj;
/* 218*/        if(name != ((Cookie) (obj)).name && (name == null || !name.equals(((Cookie) (obj)).name)))
/* 219*/            return false;
/* 221*/        if(value != ((Cookie) (obj)).value && (value == null || !value.equals(((Cookie) (obj)).value)))
/* 222*/            return false;
/* 224*/        if(version != ((Cookie) (obj)).version)
/* 225*/            return false;
/* 227*/        if(path != ((Cookie) (obj)).path && (path == null || !path.equals(((Cookie) (obj)).path)))
/* 228*/            return false;
/* 230*/        return domain == ((Cookie) (obj)).domain || domain != null && domain.equals(((Cookie) (obj)).domain);
            }

            public static final int DEFAULT_VERSION = 1;
            private static final javax.ws.rs.ext.RuntimeDelegate.HeaderDelegate HEADER_DELEGATE = RuntimeDelegate.getInstance().createHeaderDelegate(javax/ws/rs/core/Cookie);
            private final String name;
            private final String value;
            private final int version;
            private final String path;
            private final String domain;

}
