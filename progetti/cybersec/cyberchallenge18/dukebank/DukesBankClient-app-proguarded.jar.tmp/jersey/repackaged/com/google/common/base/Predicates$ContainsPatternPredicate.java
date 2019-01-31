// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Predicates.java

package jersey.repackaged.com.google.common.base;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Objects, Preconditions, Predicate, Predicates

static class pattern
    implements Serializable, Predicate
{

            public boolean apply(CharSequence charsequence)
            {
/* 586*/        return pattern.matcher(charsequence).find();
            }

            public int hashCode()
            {
/* 593*/        return Objects.hashCode(new Object[] {
/* 593*/            pattern.pattern(), Integer.valueOf(pattern.flags())
                });
            }

            public boolean equals(Object obj)
            {
/* 597*/        if(obj instanceof pattern)
                {
/* 598*/            obj = (pattern)obj;
/* 602*/            return Objects.equal(pattern.pattern(), ((pattern) (obj)).pattern.pattern()) && Objects.equal(Integer.valueOf(pattern.flags()), Integer.valueOf(((pattern) (obj)).pattern.flags()));
                } else
                {
/* 605*/            return false;
                }
            }

            public String toString()
            {
                String s;
/* 609*/        s = String.valueOf(String.valueOf(s = Objects.toStringHelper(pattern).pattern("pattern", pattern.pattern()).pattern("pattern.flags", pattern.flags()).pattern()));
/* 613*/        return (new StringBuilder(21 + s.length())).append("Predicates.contains(").append(s).append(")").toString();
            }

            public volatile boolean apply(Object obj)
            {
/* 575*/        return apply((CharSequence)obj);
            }

            final Pattern pattern;

            (Pattern pattern1)
            {
/* 581*/        pattern = (Pattern)Preconditions.checkNotNull(pattern1);
            }
}
