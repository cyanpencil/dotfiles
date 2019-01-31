// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Objects.java

package jersey.repackaged.com.google.common.base;

import java.util.Arrays;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            MoreObjects, Preconditions

public final class Objects
{
    /**
     * @deprecated Class ToStringHelper is deprecated
     */

    public static final class ToStringHelper
    {
        static final class ValueHolder
        {

                    String name;
                    Object value;
                    ValueHolder next;

                    private ValueHolder()
                    {
                    }

        }


                public final ToStringHelper add(String s, Object obj)
                {
/* 230*/            return addHolder(s, obj);
                }

                public final ToStringHelper add(String s, int i)
                {
/* 280*/            return addHolder(s, String.valueOf(i));
                }

                public final String toString()
                {
/* 387*/            boolean flag = omitNullValues;
/* 388*/            String s = "";
/* 389*/            StringBuilder stringbuilder = (new StringBuilder(32)).append(className).append('{');
/* 391*/            for(ValueHolder valueholder = holderHead.next; valueholder != null; valueholder = valueholder.next)
                    {
/* 393*/                if(flag && valueholder.value == null)
/* 394*/                    continue;
/* 394*/                stringbuilder.append(s);
/* 395*/                s = ", ";
/* 397*/                if(valueholder.name != null)
/* 398*/                    stringbuilder.append(valueholder.name).append('=');
/* 400*/                stringbuilder.append(valueholder.value);
                    }

/* 403*/            return stringbuilder.append('}').toString();
                }

                private ValueHolder addHolder()
                {
/* 407*/            ValueHolder valueholder = new ValueHolder();
/* 408*/            holderTail = holderTail.next = valueholder;
/* 409*/            return valueholder;
                }

                private ToStringHelper addHolder(String s, Object obj)
                {
                    ValueHolder valueholder;
/* 419*/            (valueholder = addHolder()).value = obj;
/* 421*/            valueholder.name = (String)Preconditions.checkNotNull(s);
/* 422*/            return this;
                }

                private final String className;
                private ValueHolder holderHead;
                private ValueHolder holderTail;
                private boolean omitNullValues;

                private ToStringHelper(String s)
                {
/* 200*/            holderHead = new ValueHolder();
/* 201*/            holderTail = holderHead;
/* 202*/            omitNullValues = false;
/* 208*/            className = (String)Preconditions.checkNotNull(s);
                }

    }


            public static boolean equal(Object obj, Object obj1)
            {
/*  60*/        return obj == obj1 || obj != null && obj.equals(obj1);
            }

            public static transient int hashCode(Object aobj[])
            {
/*  84*/        return Arrays.hashCode(aobj);
            }

            /**
             * @deprecated Method toStringHelper is deprecated
             */

            public static ToStringHelper toStringHelper(Object obj)
            {
/* 130*/        return new ToStringHelper(MoreObjects.simpleName(obj.getClass()));
            }
}
