// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Joiner.java

package jersey.repackaged.com.google.common.base;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Preconditions

public class Joiner
{
    public static final class MapJoiner
    {

                public final StringBuilder appendTo(StringBuilder stringbuilder, Map map)
                {
/* 322*/            return appendTo(stringbuilder, ((Iterable) (map.entrySet())));
                }

                public final Appendable appendTo(Appendable appendable, Iterator iterator)
                    throws IOException
                {
/* 354*/            Preconditions.checkNotNull(appendable);
/* 355*/            if(iterator.hasNext())
                    {
/* 356*/                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
/* 357*/                appendable.append(joiner.toString(entry.getKey()));
/* 358*/                appendable.append(keyValueSeparator);
/* 359*/                appendable.append(joiner.toString(entry.getValue()));
                        java.util.Map.Entry entry1;
/* 360*/                for(; iterator.hasNext(); appendable.append(joiner.toString(entry1.getValue())))
                        {
/* 361*/                    appendable.append(joiner.separator);
/* 362*/                    entry1 = (java.util.Map.Entry)iterator.next();
/* 363*/                    appendable.append(joiner.toString(entry1.getKey()));
/* 364*/                    appendable.append(keyValueSeparator);
                        }

                    }
/* 368*/            return appendable;
                }

                public final StringBuilder appendTo(StringBuilder stringbuilder, Iterable iterable)
                {
/* 380*/            return appendTo(stringbuilder, iterable.iterator());
                }

                public final StringBuilder appendTo(StringBuilder stringbuilder, Iterator iterator)
                {
/* 393*/            try
                    {
/* 393*/                appendTo(((Appendable) (stringbuilder)), iterator);
                    }
                    // Misplaced declaration of an exception variable
/* 394*/            catch(StringBuilder stringbuilder)
                    {
/* 395*/                throw new AssertionError(stringbuilder);
                    }
/* 397*/            return stringbuilder;
                }

                private final Joiner joiner;
                private final String keyValueSeparator;

                private MapJoiner(Joiner joiner1, String s)
                {
/* 304*/            joiner = joiner1;
/* 305*/            keyValueSeparator = (String)Preconditions.checkNotNull(s);
                }

    }


            public static Joiner on(String s)
            {
/*  71*/        return new Joiner(s);
            }

            public static Joiner on(char c)
            {
/*  78*/        return new Joiner(String.valueOf(c));
            }

            private Joiner(String s)
            {
/*  84*/        separator = (String)Preconditions.checkNotNull(s);
            }

            private Joiner(Joiner joiner)
            {
/*  88*/        separator = joiner.separator;
            }

            public Appendable appendTo(Appendable appendable, Iterator iterator)
                throws IOException
            {
/* 106*/        Preconditions.checkNotNull(appendable);
/* 107*/        if(iterator.hasNext())
                {
/* 108*/            appendable.append(toString(iterator.next()));
/* 109*/            for(; iterator.hasNext(); appendable.append(toString(iterator.next())))
/* 110*/                appendable.append(separator);

                }
/* 114*/        return appendable;
            }

            public final StringBuilder appendTo(StringBuilder stringbuilder, Iterable iterable)
            {
/* 140*/        return appendTo(stringbuilder, iterable.iterator());
            }

            public final StringBuilder appendTo(StringBuilder stringbuilder, Iterator iterator)
            {
/* 152*/        try
                {
/* 152*/            appendTo(((Appendable) (stringbuilder)), iterator);
                }
                // Misplaced declaration of an exception variable
/* 153*/        catch(StringBuilder stringbuilder)
                {
/* 154*/            throw new AssertionError(stringbuilder);
                }
/* 156*/        return stringbuilder;
            }

            public final String join(Iterable iterable)
            {
/* 183*/        return join(iterable.iterator());
            }

            public final String join(Iterator iterator)
            {
/* 193*/        return appendTo(new StringBuilder(), iterator).toString();
            }

            public Joiner useForNull(String s)
            {
/* 218*/        Preconditions.checkNotNull(s);
/* 219*/        return new Joiner(s) {

                    CharSequence toString(Object obj)
                    {
/* 221*/                if(obj == null)
/* 221*/                    return nullText;
/* 221*/                else
/* 221*/                    return Joiner.this.toString(obj);
                    }

                    public Joiner useForNull(String s1)
                    {
/* 225*/                throw new UnsupportedOperationException("already specified useForNull");
                    }

                    final String val$nullText;
                    final Joiner this$0;

                    
                    {
/* 219*/                this$0 = Joiner.this;
/* 219*/                nullText = s;
/* 219*/                super(final_joiner1, null);
                    }
        };
            }

            public MapJoiner withKeyValueSeparator(String s)
            {
/* 278*/        return new MapJoiner(this, s);
            }

            CharSequence toString(Object obj)
            {
/* 433*/        Preconditions.checkNotNull(obj);
/* 434*/        if(obj instanceof CharSequence)
/* 434*/            return (CharSequence)obj;
/* 434*/        else
/* 434*/            return obj.toString();
            }


            private final String separator;

}
