// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PatternWithGroups.java

package org.glassfish.jersey.uri;

import java.util.List;
import java.util.Map;
import java.util.regex.*;

public class PatternWithGroups
{
    final class GroupIndexMatchResult
        implements MatchResult
    {

                public final int start()
                {
/* 223*/            return result.start();
                }

                public final int start(int i)
                {
/* 228*/            if(i > groupCount())
/* 229*/                throw new IndexOutOfBoundsException();
/* 232*/            if(i > 0)
/* 232*/                return result.start(groupIndexes[i - 1]);
/* 232*/            else
/* 232*/                return result.start();
                }

                public final int end()
                {
/* 237*/            return result.end();
                }

                public final int end(int i)
                {
/* 242*/            if(i > groupCount())
/* 243*/                throw new IndexOutOfBoundsException();
/* 246*/            if(i > 0)
/* 246*/                return result.end(groupIndexes[i - 1]);
/* 246*/            else
/* 246*/                return result.end();
                }

                public final String group()
                {
/* 251*/            return result.group();
                }

                public final String group(int i)
                {
/* 256*/            if(i > groupCount())
/* 257*/                throw new IndexOutOfBoundsException();
/* 260*/            if(i > 0)
/* 260*/                return result.group(groupIndexes[i - 1]);
/* 260*/            else
/* 260*/                return result.group();
                }

                public final int groupCount()
                {
/* 265*/            return groupIndexes.length;
                }

                private final MatchResult result;
                final PatternWithGroups this$0;

                GroupIndexMatchResult(MatchResult matchresult)
                {
/* 217*/            this$0 = PatternWithGroups.this;
/* 217*/            super();
/* 218*/            result = matchresult;
                }
    }

    static final class EmptyStringMatchResult
        implements MatchResult
    {

                public final int start()
                {
/* 168*/            return 0;
                }

                public final int start(int i)
                {
/* 173*/            if(i != 0)
/* 174*/                throw new IndexOutOfBoundsException();
/* 176*/            else
/* 176*/                return start();
                }

                public final int end()
                {
/* 181*/            return 0;
                }

                public final int end(int i)
                {
/* 186*/            if(i != 0)
/* 187*/                throw new IndexOutOfBoundsException();
/* 189*/            else
/* 189*/                return end();
                }

                public final String group()
                {
/* 194*/            return "";
                }

                public final String group(int i)
                {
/* 199*/            if(i != 0)
/* 200*/                throw new IndexOutOfBoundsException();
/* 202*/            else
/* 202*/                return group();
                }

                public final int groupCount()
                {
/* 207*/            return 0;
                }

                private EmptyStringMatchResult()
                {
                }

    }


            protected PatternWithGroups()
            {
/*  81*/        regex = "";
/*  82*/        regexPattern = null;
/*  83*/        groupIndexes = EMPTY_INT_ARRAY;
            }

            public PatternWithGroups(String s)
                throws PatternSyntaxException
            {
/*  94*/        this(s, EMPTY_INT_ARRAY);
            }

            public PatternWithGroups(String s, int ai[])
                throws PatternSyntaxException
            {
/* 108*/        this(compile(s), ai);
            }

            private static Pattern compile(String s)
                throws PatternSyntaxException
            {
/* 112*/        if(s == null || s.isEmpty())
/* 112*/            return null;
/* 112*/        else
/* 112*/            return Pattern.compile(s);
            }

            public PatternWithGroups(Pattern pattern)
                throws IllegalArgumentException
            {
/* 122*/        this(pattern, EMPTY_INT_ARRAY);
            }

            public PatternWithGroups(Pattern pattern, int ai[])
                throws IllegalArgumentException
            {
/* 133*/        if(pattern == null)
                {
/* 134*/            throw new IllegalArgumentException();
                } else
                {
/* 137*/            regex = pattern.toString();
/* 138*/            regexPattern = pattern;
/* 139*/            groupIndexes = (int[])ai.clone();
/* 140*/            return;
                }
            }

            public final String getRegex()
            {
/* 148*/        return regex;
            }

            public final int[] getGroupIndexes()
            {
/* 161*/        return (int[])groupIndexes.clone();
            }

            public final MatchResult match(CharSequence charsequence)
            {
/* 277*/        if(charsequence == null)
/* 278*/            if(regexPattern == null)
/* 278*/                return EMPTY_STRING_MATCH_RESULT;
/* 278*/            else
/* 278*/                return null;
/* 279*/        if(regexPattern == null)
/* 280*/            return null;
                Matcher matcher;
/* 284*/        if(!(matcher = regexPattern.matcher(charsequence)).matches())
/* 286*/            return null;
/* 289*/        if(charsequence.length() == 0)
/* 290*/            return EMPTY_STRING_MATCH_RESULT;
/* 293*/        if(groupIndexes.length > 0)
/* 293*/            return new GroupIndexMatchResult(matcher);
/* 293*/        else
/* 293*/            return matcher;
            }

            public final boolean match(CharSequence charsequence, List list)
                throws IllegalArgumentException
            {
/* 309*/        if(list == null)
/* 310*/            throw new IllegalArgumentException();
/* 314*/        if(charsequence == null || charsequence.length() == 0)
/* 315*/            return regexPattern == null;
/* 316*/        if(regexPattern == null)
/* 317*/            return false;
/* 321*/        if(!(charsequence = regexPattern.matcher(charsequence)).matches())
/* 323*/            return false;
/* 326*/        list.clear();
/* 327*/        if(groupIndexes.length > 0)
                {
/* 328*/            for(int i = 0; i < groupIndexes.length; i++)
/* 329*/                list.add(charsequence.group(groupIndexes[i]));

                } else
                {
/* 332*/            for(int j = 1; j <= charsequence.groupCount(); j++)
/* 333*/                list.add(charsequence.group(j));

                }
/* 340*/        return true;
            }

            public final boolean match(CharSequence charsequence, List list, Map map)
                throws IllegalArgumentException
            {
/* 360*/        if(map == null)
/* 361*/            throw new IllegalArgumentException();
/* 365*/        if(charsequence == null || charsequence.length() == 0)
/* 366*/            return regexPattern == null;
/* 367*/        if(regexPattern == null)
/* 368*/            return false;
/* 372*/        if(!(charsequence = regexPattern.matcher(charsequence)).matches())
/* 374*/            return false;
/* 378*/        map.clear();
/* 380*/        for(int i = 0; i < list.size(); i++)
                {
/* 381*/            String s = (String)list.get(i);
/* 382*/            String s1 = charsequence.group(groupIndexes.length <= 0 ? i + 1 : groupIndexes[i]);
                    String s2;
/* 386*/            if((s2 = (String)map.get(s)) != null && !s2.equals(s1))
/* 388*/                return false;
/* 391*/            map.put(s, s1);
                }

/* 394*/        return true;
            }

            public final int hashCode()
            {
/* 399*/        return regex.hashCode();
            }

            public final boolean equals(Object obj)
            {
/* 404*/        if(obj == null)
/* 405*/            return false;
/* 407*/        if(getClass() != obj.getClass())
/* 408*/            return false;
/* 410*/        obj = (PatternWithGroups)obj;
/* 411*/        return regex == ((PatternWithGroups) (obj)).regex || regex != null && regex.equals(((PatternWithGroups) (obj)).regex);
            }

            public final String toString()
            {
/* 419*/        return regex;
            }

            private static final int EMPTY_INT_ARRAY[] = new int[0];
            public static final PatternWithGroups EMPTY = new PatternWithGroups();
            private final String regex;
            private final Pattern regexPattern;
            private final int groupIndexes[];
            private static final EmptyStringMatchResult EMPTY_STRING_MATCH_RESULT = new EmptyStringMatchResult();


}
