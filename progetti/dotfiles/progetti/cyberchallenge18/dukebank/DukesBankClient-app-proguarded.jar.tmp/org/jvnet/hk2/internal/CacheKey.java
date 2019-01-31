// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheKey.java

package org.jvnet.hk2.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.glassfish.hk2.api.Unqualified;
import org.glassfish.hk2.utilities.general.GeneralUtilities;
import org.glassfish.hk2.utilities.reflection.Pretty;
import org.glassfish.hk2.utilities.reflection.ReflectionHelper;

public class CacheKey
{

            public transient CacheKey(Type type, String s, Unqualified unqualified1, Annotation aannotation[])
            {
/*  79*/        lookupType = type;
                Class class1;
/*  81*/        if((class1 = ReflectionHelper.getRawClass(type)) != null)
/*  83*/            removalName = class1.getName();
/*  86*/        else
/*  86*/            removalName = null;
/*  89*/        name = s;
/*  90*/        if(aannotation.length > 0)
/*  91*/            qualifiers = aannotation;
/*  94*/        else
/*  94*/            qualifiers = null;
/*  97*/        unqualified = unqualified1;
/*  99*/        int i = 0;
/* 101*/        if(type != null)
/* 102*/            i = 0 ^ type.hashCode();
/* 105*/        if(s != null)
/* 106*/            i ^= s.hashCode();
/* 109*/        s = (type = aannotation).length;
/* 109*/        for(aannotation = 0; aannotation < s; aannotation++)
                {
/* 109*/            Annotation annotation = type[aannotation];
/* 110*/            i ^= annotation.hashCode();
                }

/* 113*/        if(unqualified1 != null)
                {
/* 114*/            i = ~i;
/* 116*/            s = (type = unqualified1.value()).length;
/* 116*/            for(aannotation = 0; aannotation < s; aannotation++)
                    {
/* 116*/                Object obj = type[aannotation];
/* 117*/                i ^= obj.hashCode();
                    }

                }
/* 121*/        hashCode = i;
            }

            public int hashCode()
            {
/* 126*/        return hashCode;
            }

            public boolean equals(final Object other)
            {
/* 131*/        if(other == null)
/* 131*/            return false;
/* 132*/        if(!(other instanceof CacheKey))
/* 132*/            return false;
/* 134*/        other = (CacheKey)other;
/* 136*/        if(hashCode != ((CacheKey) (other)).hashCode)
/* 136*/            return false;
/* 137*/        if(!GeneralUtilities.safeEquals(lookupType, ((CacheKey) (other)).lookupType))
/* 137*/            return false;
/* 138*/        if(!GeneralUtilities.safeEquals(name, ((CacheKey) (other)).name))
/* 138*/            return false;
/* 140*/        if(qualifiers != null)
                {
/* 141*/            if(((CacheKey) (other)).qualifiers == null)
/* 141*/                return false;
/* 143*/            if(qualifiers.length != ((CacheKey) (other)).qualifiers.length)
/* 143*/                return false;
                    boolean flag;
/* 145*/            if(!(flag = ((Boolean)AccessController.doPrivileged(new PrivilegedAction() {

                public Boolean run()
                {
/* 149*/            for(int j = 0; j < qualifiers.length; j++)
/* 150*/                if(!GeneralUtilities.safeEquals(qualifiers[j], other.qualifiers[j]))
/* 150*/                    return Boolean.valueOf(false);

/* 153*/            return Boolean.valueOf(true);
                }

                public volatile Object run()
                {
/* 145*/            return run();
                }

                final CacheKey val$other;
                final CacheKey this$0;

                    
                    {
/* 145*/                this$0 = CacheKey.this;
/* 145*/                other = cachekey1;
/* 145*/                super();
                    }
    })).booleanValue()))
/* 158*/                return false;
                } else
/* 161*/        if(((CacheKey) (other)).qualifiers != null)
/* 161*/            return false;
/* 163*/        if(unqualified != null)
                {
/* 164*/            if(((CacheKey) (other)).unqualified == null)
/* 164*/                return false;
/* 166*/            Class aclass[] = unqualified.value();
/* 167*/            other = ((CacheKey) (other)).unqualified.value();
/* 169*/            if(aclass.length != other.length)
/* 169*/                return false;
/* 171*/            for(int i = 0; i < aclass.length; i++)
/* 172*/                if(!GeneralUtilities.safeEquals(aclass[i], other[i]))
/* 172*/                    return false;

                } else
/* 175*/        if(((CacheKey) (other)).unqualified != null)
/* 175*/            return false;
/* 177*/        return true;
            }

            public boolean matchesRemovalName(String s)
            {
/* 191*/        if(removalName == null)
/* 191*/            return false;
/* 192*/        if(s == null)
/* 192*/            return false;
/* 194*/        else
/* 194*/            return removalName.equals(s);
            }

            public String toString()
            {
/* 198*/        return (new StringBuilder("CacheKey(")).append(Pretty.type(lookupType)).append(",").append(name).append(",").append(qualifiers != null ? qualifiers.length : 0).append(",").append(System.identityHashCode(this)).append(",").append(hashCode).append(")").toString();
            }

            private final String removalName;
            private final Type lookupType;
            private final String name;
            private final Annotation qualifiers[];
            private final Unqualified unqualified;
            private final int hashCode;

}
