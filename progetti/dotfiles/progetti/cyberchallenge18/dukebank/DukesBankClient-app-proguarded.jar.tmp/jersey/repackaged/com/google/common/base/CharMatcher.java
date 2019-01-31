// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CharMatcher.java

package jersey.repackaged.com.google.common.base;

import java.util.Arrays;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Preconditions, Predicate

public abstract class CharMatcher
    implements Predicate
{
    static abstract class FastMatcher extends CharMatcher
    {

                public volatile boolean apply(Object obj)
                {
/* 813*/            return apply((Character)obj);
                }

                FastMatcher(String s)
                {
/* 819*/            super(s);
                }
    }

    static class Or extends CharMatcher
    {

                public boolean matches(char c)
                {
/* 746*/            return first.matches(c) || second.matches(c);
                }

                CharMatcher withToString(String s)
                {
/* 751*/            return new Or(first, second, s);
                }

                public volatile boolean apply(Object obj)
                {
/* 723*/            return apply((Character)obj);
                }

                final CharMatcher first;
                final CharMatcher second;

                Or(CharMatcher charmatcher, CharMatcher charmatcher1, String s)
                {
/* 728*/            super(s);
/* 729*/            first = (CharMatcher)Preconditions.checkNotNull(charmatcher);
/* 730*/            second = (CharMatcher)Preconditions.checkNotNull(charmatcher1);
                }

                Or(CharMatcher charmatcher, CharMatcher charmatcher1)
                {
/* 734*/            this(charmatcher, charmatcher1, (new StringBuilder(18 + (charmatcher = String.valueOf(String.valueOf(charmatcher))).length() + (charmatcher1 = String.valueOf(String.valueOf(charmatcher1))).length())).append("CharMatcher.or(").append(charmatcher).append(", ").append(charmatcher1).append(")").toString());
                }
    }

    static class RangesMatcher extends CharMatcher
    {

                public boolean matches(char c)
                {
                    int i;
/* 121*/            if((i = Arrays.binarySearch(rangeStarts, c)) >= 0)
/* 123*/                return true;
/* 125*/            return (i = ~i - 1) >= 0 && c <= rangeEnds[i];
                }

                public volatile boolean apply(Object obj)
                {
/* 102*/            return apply((Character)obj);
                }

                private final char rangeStarts[];
                private final char rangeEnds[];

                RangesMatcher(String s, char ac[], char ac1[])
                {
/* 107*/            super(s);
/* 108*/            rangeStarts = ac;
/* 109*/            rangeEnds = ac1;
/* 110*/            Preconditions.checkArgument(ac.length == ac1.length);
/* 111*/            for(s = 0; s < ac.length; s++)
                    {
/* 112*/                Preconditions.checkArgument(ac[s] <= ac1[s]);
/* 113*/                if(s + 1 < ac.length)
/* 114*/                    Preconditions.checkArgument(ac1[s] < ac[s + 1]);
                    }

                }
    }


            private static String showCharacter(char c)
            {
/* 229*/        String s = "0123456789ABCDEF";
/* 230*/        char ac[] = {
/* 230*/            '\\', 'u', '\0', '\0', '\0', '\0'
                };
/* 231*/        for(int i = 0; i < 4; i++)
                {
/* 232*/            ac[5 - i] = s.charAt(c & 0xf);
/* 233*/            c >>= '\004';
                }

/* 235*/        return String.copyValueOf(ac);
            }

            public static CharMatcher is(char c)
            {
/* 415*/        String s = String.valueOf(String.valueOf(showCharacter(c)));
/* 415*/        s = (new StringBuilder(18 + s.length())).append("CharMatcher.is('").append(s).append("')").toString();
/* 416*/        return new FastMatcher(s, c) {

                    public final boolean matches(char c1)
                    {
/* 418*/                return c1 == match;
                    }

                    public final CharMatcher or(CharMatcher charmatcher)
                    {
/* 430*/                if(charmatcher.matches(match))
/* 430*/                    return charmatcher;
/* 430*/                else
/* 430*/                    return super.or(charmatcher);
                    }

                    final char val$match;

                    
                    {
/* 416*/                match = c;
/* 416*/                super(s);
                    }
        };
            }

            public static CharMatcher inRange(char c, char c1)
            {
/* 550*/        Preconditions.checkArgument(c1 >= c);
/* 551*/        String s = String.valueOf(String.valueOf(showCharacter(c)));
/* 551*/        String s1 = String.valueOf(String.valueOf(showCharacter(c1)));
/* 551*/        s = (new StringBuilder(27 + s.length() + s1.length())).append("CharMatcher.inRange('").append(s).append("', '").append(s1).append("')").toString();
/* 554*/        return inRange(c, c1, s);
            }

            static CharMatcher inRange(char c, char c1, String s)
            {
/* 559*/        return new FastMatcher(s, c, c1) {

                    public final boolean matches(char c2)
                    {
/* 561*/                return startInclusive <= c2 && c2 <= endInclusive;
                    }

                    final char val$startInclusive;
                    final char val$endInclusive;

                    
                    {
/* 559*/                startInclusive = c;
/* 559*/                endInclusive = c1;
/* 559*/                super(s);
                    }
        };
            }

            CharMatcher(String s)
            {
/* 601*/        description = s;
            }

            protected CharMatcher()
            {
/* 609*/        description = super.toString();
            }

            public abstract boolean matches(char c);

            public CharMatcher or(CharMatcher charmatcher)
            {
/* 720*/        return new Or(this, (CharMatcher)Preconditions.checkNotNull(charmatcher));
            }

            CharMatcher withToString(String s)
            {
/* 775*/        throw new UnsupportedOperationException();
            }

            public int indexIn(CharSequence charsequence, int i)
            {
/*1006*/        int j = charsequence.length();
/*1007*/        Preconditions.checkPositionIndex(i, j);
/*1008*/        for(i = i; i < j; i++)
/*1009*/            if(matches(charsequence.charAt(i)))
/*1010*/                return i;

/*1013*/        return -1;
            }

            /**
             * @deprecated Method apply is deprecated
             */

            public boolean apply(Character character)
            {
/*1340*/        return matches(character.charValue());
            }

            public String toString()
            {
/*1349*/        return description;
            }

            public volatile boolean apply(Object obj)
            {
/*  55*/        return apply((Character)obj);
            }

            public static final CharMatcher BREAKING_WHITESPACE = new CharMatcher() {

                public final boolean matches(char c)
                {
/*  70*/            switch(c)
                    {
/*  83*/            case 9: // '\t'
/*  83*/            case 10: // '\n'
/*  83*/            case 11: // '\013'
/*  83*/            case 12: // '\f'
/*  83*/            case 13: // '\r'
/*  83*/            case 32: // ' '
/*  83*/            case 133: 
/*  83*/            case 5760: 
/*  83*/            case 8232: 
/*  83*/            case 8233: 
/*  83*/            case 8287: 
/*  83*/            case 12288: 
/*  83*/                return true;

/*  85*/            case 8199: 
/*  85*/                return false;
                    }
/*  87*/            return c >= '\u2000' && c <= '\u200A';
                }

                public final String toString()
                {
/*  93*/            return "CharMatcher.BREAKING_WHITESPACE";
                }

                public final volatile boolean apply(Object obj)
                {
/*  67*/            return apply((Character)obj);
                }

    };
            public static final CharMatcher ASCII = inRange('\0', '\177', "CharMatcher.ASCII");
            private static final String NINES;
            public static final CharMatcher DIGIT;
            public static final CharMatcher JAVA_DIGIT = new CharMatcher("CharMatcher.JAVA_DIGIT") {

                public final boolean matches(char c)
                {
/* 160*/            return Character.isDigit(c);
                }

                public final volatile boolean apply(Object obj)
                {
/* 158*/            return apply((Character)obj);
                }

    };
            public static final CharMatcher JAVA_LETTER = new CharMatcher("CharMatcher.JAVA_LETTER") {

                public final boolean matches(char c)
                {
/* 171*/            return Character.isLetter(c);
                }

                public final volatile boolean apply(Object obj)
                {
/* 169*/            return apply((Character)obj);
                }

    };
            public static final CharMatcher JAVA_LETTER_OR_DIGIT = new CharMatcher("CharMatcher.JAVA_LETTER_OR_DIGIT") {

                public final boolean matches(char c)
                {
/* 182*/            return Character.isLetterOrDigit(c);
                }

                public final volatile boolean apply(Object obj)
                {
/* 180*/            return apply((Character)obj);
                }

    };
            public static final CharMatcher JAVA_UPPER_CASE = new CharMatcher("CharMatcher.JAVA_UPPER_CASE") {

                public final boolean matches(char c)
                {
/* 193*/            return Character.isUpperCase(c);
                }

                public final volatile boolean apply(Object obj)
                {
/* 191*/            return apply((Character)obj);
                }

    };
            public static final CharMatcher JAVA_LOWER_CASE = new CharMatcher("CharMatcher.JAVA_LOWER_CASE") {

                public final boolean matches(char c)
                {
/* 204*/            return Character.isLowerCase(c);
                }

                public final volatile boolean apply(Object obj)
                {
/* 202*/            return apply((Character)obj);
                }

    };
            public static final CharMatcher JAVA_ISO_CONTROL = inRange('\0', '\037').or(inRange('\177', '\237')).withToString("CharMatcher.JAVA_ISO_CONTROL");
            public static final CharMatcher INVISIBLE = new RangesMatcher("CharMatcher.INVISIBLE", "\000\177\255\u0600\u061C\u06DD\u070F\u1680\u180E\u2000\u2028\u205F\u2066\u2067\u2068\u2069\u206A\u3000\uD800\uFEFF\uFFF9\uFFFA".toCharArray(), " \240\255\u0604\u061C\u06DD\u070F\u1680\u180E\u200F\u202F\u2064\u2066\u2067\u2068\u2069\u206F\u3000\uF8FF\uFEFF\uFFF9\uFFFB".toCharArray());
            public static final CharMatcher SINGLE_WIDTH = new RangesMatcher("CharMatcher.SINGLE_WIDTH", "\000\u05BE\u05D0\u05F3\u0600\u0750\u0E00\u1E00\u2100\uFB50\uFE70\uFF61".toCharArray(), "\u04F9\u05BE\u05EA\u05F4\u06FF\u077F\u0E7F\u20AF\u213A\uFDFF\uFEFF\uFFDC".toCharArray());
            public static final CharMatcher ANY = new FastMatcher("CharMatcher.ANY") {

                public final boolean matches(char c)
                {
/* 255*/            return true;
                }

                public final int indexIn(CharSequence charsequence, int j)
                {
/* 263*/            charsequence = charsequence.length();
/* 264*/            Preconditions.checkPositionIndex(j, charsequence);
/* 265*/            if(j == charsequence)
/* 265*/                return -1;
/* 265*/            else
/* 265*/                return j;
                }

                public final CharMatcher or(CharMatcher charmatcher)
                {
/* 318*/            Preconditions.checkNotNull(charmatcher);
/* 319*/            return this;
                }

    };
            public static final CharMatcher NONE = new FastMatcher("CharMatcher.NONE") {

                public final boolean matches(char c)
                {
/* 331*/            return false;
                }

                public final int indexIn(CharSequence charsequence, int j)
                {
/* 340*/            charsequence = charsequence.length();
/* 341*/            Preconditions.checkPositionIndex(j, charsequence);
/* 342*/            return -1;
                }

                public final CharMatcher or(CharMatcher charmatcher)
                {
/* 401*/            return (CharMatcher)Preconditions.checkNotNull(charmatcher);
                }

    };
            final String description;
            static final int WHITESPACE_SHIFT = Integer.numberOfLeadingZeros(32 - 1);
            public static final CharMatcher WHITESPACE = new FastMatcher("WHITESPACE") {

                public final boolean matches(char c)
                {
/*1374*/            return "\u2002\u3000\r\205\u200A\u2005\u2000\u3000\u2029\013\u3000\u2008\u2003\u205F\u3000\u1680\t \u2006\u2001\u202F\240\f\u2009\u3000\u2004\u3000\u3000\u2028\n\u2007\u3000".charAt(c * 0x6449bf0a >>> WHITESPACE_SHIFT) == c;
                }

    };

            static 
            {
/* 138*/        StringBuilder stringbuilder = new StringBuilder(31);
/* 139*/        for(int i = 0; i < 31; i++)
/* 140*/            stringbuilder.append((char)("0\u0660\u06F0\u07C0\u0966\u09E6\u0A66\u0AE6\u0B66\u0BE6\u0C66\u0CE6\u0D66\u0E50\u0ED0\u0F20\u1040\u1090\u17E0\u1810\u1946\u19D0\u1B50\u1BB0\u1C40\u1C50\uA620\uA8D0\uA900\uAA50\uFF10".charAt(i) + 9));

/* 142*/        NINES = stringbuilder.toString();
/* 150*/        DIGIT = new RangesMatcher("CharMatcher.DIGIT", "0\u0660\u06F0\u07C0\u0966\u09E6\u0A66\u0AE6\u0B66\u0BE6\u0C66\u0CE6\u0D66\u0E50\u0ED0\u0F20\u1040\u1090\u17E0\u1810\u1946\u19D0\u1B50\u1BB0\u1C40\u1C50\uA620\uA8D0\uA900\uAA50\uFF10".toCharArray(), NINES.toCharArray());
            }
}
