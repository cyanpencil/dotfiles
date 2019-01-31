// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MoreObjects.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            MoreObjects, Preconditions

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

                ValueHolder(MoreObjects._cls1 _pcls1)
                {
/* 384*/            this();
                }
    }


            public final ValueHolder add(String s, Object obj)
            {
/* 185*/        return addHolder(s, obj);
            }

            public final addHolder add(String s, int i)
            {
/* 235*/        return addHolder(s, String.valueOf(i));
            }

            public final addHolder add(String s, long l)
            {
/* 245*/        return addHolder(s, String.valueOf(l));
            }

            public final addHolder addValue(Object obj)
            {
/* 257*/        return addHolder(obj);
            }

            public final String toString()
            {
/* 346*/        boolean flag = omitNullValues;
/* 347*/        String s = "";
/* 348*/        StringBuilder stringbuilder = (new StringBuilder(32)).append(className).append('{');
/* 350*/        for(ValueHolder valueholder = holderHead.next; valueholder != null; valueholder = valueholder.next)
                {
/* 352*/            if(flag && valueholder.value == null)
/* 353*/                continue;
/* 353*/            stringbuilder.append(s);
/* 354*/            s = ", ";
/* 356*/            if(valueholder.name != null)
/* 357*/                stringbuilder.append(valueholder.name).append('=');
/* 359*/            stringbuilder.append(valueholder.value);
                }

/* 362*/        return stringbuilder.append('}').toString();
            }

            private ValueHolder addHolder()
            {
/* 366*/        ValueHolder valueholder = new ValueHolder(null);
/* 367*/        holderTail = holderTail.next = valueholder;
/* 368*/        return valueholder;
            }

            private ValueHolder.next addHolder(Object obj)
            {
                ValueHolder valueholder;
/* 372*/        (valueholder = addHolder()).value = obj;
/* 374*/        return this;
            }

            private ValueHolder.value addHolder(String s, Object obj)
            {
                ValueHolder valueholder;
/* 378*/        (valueholder = addHolder()).value = obj;
/* 380*/        valueholder.name = (String)Preconditions.checkNotNull(s);
/* 381*/        return this;
            }

            private final String className;
            private ValueHolder holderHead;
            private ValueHolder holderTail;
            private boolean omitNullValues;

            private ValueHolder(String s)
            {
/* 155*/        holderHead = new ValueHolder(null);
/* 156*/        holderTail = holderHead;
/* 157*/        omitNullValues = false;
/* 163*/        className = (String)Preconditions.checkNotNull(s);
            }

            className(String s, className classname)
            {
/* 153*/        this(s);
            }
}
