// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Objects.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Objects, Preconditions

/**
 * @deprecated Class <init> is deprecated
 */

public static final class <init>
{
    static final class ValueHolder
    {

                String name;
                Object value;
                ValueHolder next;

                private ValueHolder()
                {
                }

                ValueHolder(Objects._cls1 _pcls1)
                {
/* 425*/            this();
                }
    }


            public final ValueHolder add(String s, Object obj)
            {
/* 230*/        return addHolder(s, obj);
            }

            public final addHolder add(String s, int i)
            {
/* 280*/        return addHolder(s, String.valueOf(i));
            }

            public final String toString()
            {
/* 387*/        boolean flag = omitNullValues;
/* 388*/        String s = "";
/* 389*/        StringBuilder stringbuilder = (new StringBuilder(32)).append(className).append('{');
/* 391*/        for(ValueHolder valueholder = holderHead.next; valueholder != null; valueholder = valueholder.next)
                {
/* 393*/            if(flag && valueholder.value == null)
/* 394*/                continue;
/* 394*/            stringbuilder.append(s);
/* 395*/            s = ", ";
/* 397*/            if(valueholder.name != null)
/* 398*/                stringbuilder.append(valueholder.name).append('=');
/* 400*/            stringbuilder.append(valueholder.value);
                }

/* 403*/        return stringbuilder.append('}').toString();
            }

            private ValueHolder addHolder()
            {
/* 407*/        ValueHolder valueholder = new ValueHolder(null);
/* 408*/        holderTail = holderTail.next = valueholder;
/* 409*/        return valueholder;
            }

            private ValueHolder.next addHolder(String s, Object obj)
            {
                ValueHolder valueholder;
/* 419*/        (valueholder = addHolder()).value = obj;
/* 421*/        valueholder.name = (String)Preconditions.checkNotNull(s);
/* 422*/        return this;
            }

            private final String className;
            private ValueHolder holderHead;
            private ValueHolder holderTail;
            private boolean omitNullValues;

            private ValueHolder(String s)
            {
/* 200*/        holderHead = new ValueHolder(null);
/* 201*/        holderTail = holderHead;
/* 202*/        omitNullValues = false;
/* 208*/        className = (String)Preconditions.checkNotNull(s);
            }

            ull(String s, ull ull)
            {
/* 198*/        this(s);
            }
}
