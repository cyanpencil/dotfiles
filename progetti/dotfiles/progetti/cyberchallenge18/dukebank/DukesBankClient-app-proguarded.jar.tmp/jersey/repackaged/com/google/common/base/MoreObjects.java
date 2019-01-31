// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MoreObjects.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Preconditions

public final class MoreObjects
{
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
/* 185*/            return addHolder(s, obj);
                }

                public final ToStringHelper add(String s, int i)
                {
/* 235*/            return addHolder(s, String.valueOf(i));
                }

                public final ToStringHelper add(String s, long l)
                {
/* 245*/            return addHolder(s, String.valueOf(l));
                }

                public final ToStringHelper addValue(Object obj)
                {
/* 257*/            return addHolder(obj);
                }

                public final String toString()
                {
/* 346*/            boolean flag = omitNullValues;
/* 347*/            String s = "";
/* 348*/            StringBuilder stringbuilder = (new StringBuilder(32)).append(className).append('{');
/* 350*/            for(ValueHolder valueholder = holderHead.next; valueholder != null; valueholder = valueholder.next)
                    {
/* 352*/                if(flag && valueholder.value == null)
/* 353*/                    continue;
/* 353*/                stringbuilder.append(s);
/* 354*/                s = ", ";
/* 356*/                if(valueholder.name != null)
/* 357*/                    stringbuilder.append(valueholder.name).append('=');
/* 359*/                stringbuilder.append(valueholder.value);
                    }

/* 362*/            return stringbuilder.append('}').toString();
                }

                private ValueHolder addHolder()
                {
/* 366*/            ValueHolder valueholder = new ValueHolder();
/* 367*/            holderTail = holderTail.next = valueholder;
/* 368*/            return valueholder;
                }

                private ToStringHelper addHolder(Object obj)
                {
                    ValueHolder valueholder;
/* 372*/            (valueholder = addHolder()).value = obj;
/* 374*/            return this;
                }

                private ToStringHelper addHolder(String s, Object obj)
                {
                    ValueHolder valueholder;
/* 378*/            (valueholder = addHolder()).value = obj;
/* 380*/            valueholder.name = (String)Preconditions.checkNotNull(s);
/* 381*/            return this;
                }

                private final String className;
                private ValueHolder holderHead;
                private ValueHolder holderTail;
                private boolean omitNullValues;

                private ToStringHelper(String s)
                {
/* 155*/            holderHead = new ValueHolder();
/* 156*/            holderTail = holderHead;
/* 157*/            omitNullValues = false;
/* 163*/            className = (String)Preconditions.checkNotNull(s);
                }

    }


            public static Object firstNonNull(Object obj, Object obj1)
            {
/*  52*/        if(obj != null)
/*  52*/            return obj;
/*  52*/        else
/*  52*/            return Preconditions.checkNotNull(obj1);
            }

            public static ToStringHelper toStringHelper(Object obj)
            {
/*  95*/        return new ToStringHelper(simpleName(obj.getClass()));
            }

            public static ToStringHelper toStringHelper(Class class1)
            {
/* 109*/        return new ToStringHelper(simpleName(class1));
            }

            public static ToStringHelper toStringHelper(String s)
            {
/* 121*/        return new ToStringHelper(s);
            }

            static String simpleName(Class class1)
            {
                int i;
/* 130*/        if((i = (class1 = (class1 = class1.getName()).replaceAll("\\$[0-9]+", "\\$")).lastIndexOf('$')) == -1)
/* 142*/            i = class1.lastIndexOf('.');
/* 144*/        return class1.substring(i + 1);
            }

            private MoreObjects()
            {
            }
}
