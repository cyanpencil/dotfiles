// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ObjectArrays.java

package jersey.repackaged.com.google.common.collect;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Platform

public final class ObjectArrays
{

            public static Object[] newArray(Class class1, int i)
            {
/*  50*/        return (Object[])Array.newInstance(class1, i);
            }

            public static Object[] newArray(Object aobj[], int i)
            {
/*  61*/        return Platform.newArray(aobj, i);
            }

            static Object[] arraysCopyOf(Object aobj[], int i)
            {
/* 112*/        Object aobj1[] = newArray(aobj, i);
/* 113*/        System.arraycopy(((Object) (aobj)), 0, ((Object) (aobj1)), 0, Math.min(aobj.length, i));
/* 115*/        return aobj1;
            }

            static Object[] toArrayImpl(Collection collection, Object aobj[])
            {
/* 143*/        int i = collection.size();
/* 144*/        if(aobj.length < i)
/* 145*/            aobj = newArray(aobj, i);
/* 147*/        fillArray(collection, aobj);
/* 148*/        if(aobj.length > i)
/* 149*/            aobj[i] = null;
/* 151*/        return aobj;
            }

            static Object[] toArrayImpl(Collection collection)
            {
/* 191*/        return fillArray(collection, new Object[collection.size()]);
            }

            private static Object[] fillArray(Iterable iterable, Object aobj[])
            {
/* 209*/        int i = 0;
/* 210*/        for(iterable = iterable.iterator(); iterable.hasNext();)
                {
/* 210*/            Object obj = iterable.next();
/* 211*/            aobj[i++] = obj;
                }

/* 213*/        return aobj;
            }

            static transient Object[] checkElementsNotNull(Object aobj[])
            {
/* 226*/        return checkElementsNotNull(aobj, aobj.length);
            }

            static Object[] checkElementsNotNull(Object aobj[], int i)
            {
/* 230*/        for(int j = 0; j < i; j++)
/* 231*/            checkElementNotNull(aobj[j], j);

/* 233*/        return aobj;
            }

            static Object checkElementNotNull(Object obj, int i)
            {
/* 239*/        if(obj == null)
/* 240*/            throw new NullPointerException((new StringBuilder(20)).append("at index ").append(i).toString());
/* 242*/        else
/* 242*/            return obj;
            }

            static final Object EMPTY_ARRAY[] = new Object[0];

}
