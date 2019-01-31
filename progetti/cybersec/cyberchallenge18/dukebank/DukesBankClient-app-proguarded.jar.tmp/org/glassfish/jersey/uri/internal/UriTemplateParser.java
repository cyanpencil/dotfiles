// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UriTemplateParser.java

package org.glassfish.jersey.uri.internal;

import java.util.*;
import java.util.regex.*;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.uri.UriComponent;

// Referenced classes of package org.glassfish.jersey.uri.internal:
//            CharacterIterator

public class UriTemplateParser
{

            private static Set initReserved()
            {
/*  69*/        char ac[] = {
/*  69*/            '.', '^', '&', '!', '?', '-', ':', '<', '(', '[', 
/*  69*/            '$', '=', ')', ']', ',', '>', '*', '+', '|'
                };
/*  76*/        HashSet hashset = new HashSet(19);
/*  77*/        ac = ac;
/*  77*/        for(int i = 0; i < 19; i++)
                {
/*  77*/            char c = ac[i];
/*  78*/            hashset.add(Character.valueOf(c));
                }

/*  80*/        return hashset;
            }

            public UriTemplateParser(String s)
                throws IllegalArgumentException
            {
/* 109*/        if(s == null || s.isEmpty())
/* 110*/            throw new IllegalArgumentException("Template is null or has zero length");
/* 113*/        template = s;
/* 114*/        parse(new CharacterIterator(s));
/* 116*/        try
                {
/* 116*/            pattern = Pattern.compile(regex.toString());
/* 122*/            return;
                }
                // Misplaced declaration of an exception variable
/* 117*/        catch(String s)
                {
/* 118*/            throw new IllegalArgumentException((new StringBuilder("Invalid syntax for the template expression '")).append(regex).append("'").toString(), s);
                }
            }

            public final String getTemplate()
            {
/* 131*/        return template;
            }

            public final Pattern getPattern()
            {
/* 140*/        return pattern;
            }

            public final String getNormalizedTemplate()
            {
/* 152*/        return normalizedTemplate.toString();
            }

            public final Map getNameToPattern()
            {
/* 161*/        return nameToPattern;
            }

            public final List getNames()
            {
/* 170*/        return names;
            }

            public final List getGroupCounts()
            {
/* 179*/        return groupCounts;
            }

            public final int[] getGroupIndexes()
            {
/* 192*/        if(names.isEmpty())
/* 193*/            return EMPTY_INT_ARRAY;
                int ai[];
/* 196*/        (ai = new int[names.size()])[0] = 0 + ((Integer)groupCounts.get(0)).intValue();
/* 198*/        for(int i = 1; i < ai.length; i++)
/* 199*/            ai[i] = ai[i - 1] + ((Integer)groupCounts.get(i)).intValue();

/* 202*/        return ai;
            }

            public final int getNumberOfExplicitRegexes()
            {
/* 211*/        return numOfExplicitRegexes;
            }

            public final int getNumberOfRegexGroups()
            {
                int ai[];
/* 222*/        if(groupCounts.isEmpty())
/* 223*/            return 0;
/* 225*/        else
/* 225*/            return (ai = getGroupIndexes())[ai.length - 1] + skipGroup;
            }

            public final int getNumberOfLiteralCharacters()
            {
/* 236*/        return literalCharacters;
            }

            protected String encodeLiteralCharacters(String s)
            {
/* 246*/        return s;
            }

            private void parse(CharacterIterator characteriterator)
            {
/* 251*/        try
                {
                    char c;
/* 251*/            while(characteriterator.hasNext()) 
/* 252*/                if((c = characteriterator.next()) == '{')
                        {
/* 254*/                    processLiteralCharacters();
/* 255*/                    skipGroup = parseName(characteriterator, skipGroup);
                        } else
                        {
/* 257*/                    literalCharactersBuffer.append(c);
                        }
/* 260*/            processLiteralCharacters();
/* 264*/            return;
                }
/* 261*/        catch(NoSuchElementException nosuchelementexception)
                {
/* 262*/            throw new IllegalArgumentException(LocalizationMessages.ERROR_TEMPLATE_PARSER_INVALID_SYNTAX_TERMINATED(template), nosuchelementexception);
                }
            }

            private void processLiteralCharacters()
            {
/* 268*/        if(literalCharactersBuffer.length() > 0)
                {
/* 269*/            literalCharacters += literalCharactersBuffer.length();
/* 271*/            String s = encodeLiteralCharacters(literalCharactersBuffer.toString());
/* 273*/            normalizedTemplate.append(s);
/* 276*/            for(int i = 0; i < s.length(); i++)
                    {
/* 277*/                char c = s.charAt(i);
/* 278*/                if(RESERVED_REGEX_CHARACTERS.contains(Character.valueOf(c)))
                        {
/* 279*/                    regex.append("\\");
/* 280*/                    regex.append(c);
/* 280*/                    continue;
                        }
/* 281*/                if(c == '%')
                        {
/* 282*/                    c = s.charAt(i + 1);
/* 283*/                    char c1 = s.charAt(i + 2);
/* 284*/                    if(UriComponent.isHexCharacter(c) && UriComponent.isHexCharacter(c1))
                            {
/* 285*/                        regex.append("%").append(HEX_TO_UPPERCASE_REGEX[c]).append(HEX_TO_UPPERCASE_REGEX[c1]);
/* 286*/                        i += 2;
                            }
                        } else
                        {
/* 289*/                    regex.append(c);
                        }
                    }

/* 292*/            literalCharactersBuffer.setLength(0);
                }
            }

            private static String[] initHexToUpperCaseRegex()
            {
/* 297*/        String as[] = new String[128];
/* 298*/        for(int i = 0; i < 128; i++)
/* 299*/            as[i] = String.valueOf((char)i);

/* 302*/        for(char c = 'a'; c <= 'f'; c++)
/* 304*/            as[c] = (new StringBuilder("[")).append(c).append((char)((c - 97) + 65)).append("]").toString();

/* 307*/        for(char c1 = 'A'; c1 <= 'F'; c1++)
/* 309*/            as[c1] = (new StringBuilder("[")).append((char)((c1 - 65) + 97)).append(c1).append("]").toString();

/* 311*/        return as;
            }

            private int parseName(CharacterIterator characteriterator, int i)
            {
/* 315*/        char c = consumeWhiteSpace(characteriterator);
/* 317*/        char c1 = 'p';
/* 318*/        StringBuilder stringbuilder = new StringBuilder();
/* 321*/        if(c == '?' || c == ';')
                {
/* 322*/            c1 = c;
/* 323*/            c = characteriterator.next();
                }
/* 326*/        if(Character.isLetterOrDigit(c) || c == '_')
/* 328*/            stringbuilder.append(c);
/* 330*/        else
/* 330*/            throw new IllegalArgumentException(LocalizationMessages.ERROR_TEMPLATE_PARSER_ILLEGAL_CHAR_START_NAME(Character.valueOf(c), Integer.valueOf(characteriterator.pos()), template));
/* 334*/        String s = "";
/* 336*/        do
                {
/* 336*/            for(; Character.isLetterOrDigit(c = characteriterator.next()) || c == '_' || c == '-' || c == '.'; stringbuilder.append(c));
/* 341*/            if(c != ',' || c1 == 'p')
/* 343*/                break;
/* 343*/            stringbuilder.append(c);
                } while(true);
/* 344*/        if(c == ':' && c1 == 'p')
/* 345*/            s = parseRegex(characteriterator);
/* 347*/        else
/* 347*/        if(c != '}')
/* 349*/            if(c == ' ')
                    {
/* 350*/                if((c = consumeWhiteSpace(characteriterator)) == ':')
/* 353*/                    s = parseRegex(characteriterator);
/* 355*/                else
/* 355*/                if(c != '}')
/* 359*/                    throw new IllegalArgumentException(LocalizationMessages.ERROR_TEMPLATE_PARSER_ILLEGAL_CHAR_AFTER_NAME(Character.valueOf(c), Integer.valueOf(characteriterator.pos()), template));
                    } else
                    {
/* 363*/                throw new IllegalArgumentException(LocalizationMessages.ERROR_TEMPLATE_PARSER_ILLEGAL_CHAR_PART_OF_NAME(Character.valueOf(c), Integer.valueOf(characteriterator.pos()), template));
                    }
/* 368*/        characteriterator = stringbuilder.toString();
/* 371*/        try
                {
                    Pattern pattern1;
/* 371*/            if(c1 == '?' || c1 == ';')
                    {
/* 372*/                String as[] = characteriterator.split(",\\s?");
/* 375*/                StringBuilder stringbuilder1 = new StringBuilder(c1 != '?' ? ";" : "\\?");
/* 376*/                i = c1 != '?' ? ";/\\?" : "\\&";
/* 381*/                boolean flag = true;
/* 383*/                stringbuilder1.append("(");
/* 384*/                int k = (as = as).length;
/* 384*/                for(int l = 0; l < k; l++)
                        {
/* 384*/                    String s1 = as[l];
/* 385*/                    stringbuilder1.append("(&?");
/* 386*/                    stringbuilder1.append(s1);
/* 387*/                    stringbuilder1.append("(=([^");
/* 388*/                    stringbuilder1.append(i);
/* 389*/                    stringbuilder1.append("]*))?");
/* 390*/                    stringbuilder1.append(")");
/* 391*/                    if(!flag)
/* 392*/                        stringbuilder1.append("|");
/* 395*/                    names.add(s1);
/* 396*/                    groupCounts.add(Integer.valueOf(flag ? 5 : 3));
/* 398*/                    flag = false;
                        }

/* 402*/                i = 1;
/* 405*/                stringbuilder1.append(")*");
/* 407*/                pattern1 = Pattern.compile(stringbuilder1.toString());
/* 410*/                characteriterator = (new StringBuilder()).append(c1).append(characteriterator).toString();
                    } else
                    {
/* 412*/                names.add(characteriterator);
/* 415*/                if(!s.isEmpty())
/* 416*/                    numOfExplicitRegexes++;
/* 418*/                pattern1 = s.isEmpty() ? TEMPLATE_VALUE_PATTERN : Pattern.compile(s);
/* 420*/                if(nameToPattern.containsKey(characteriterator))
                        {
/* 421*/                    if(!((Pattern)nameToPattern.get(characteriterator)).equals(pattern1))
/* 422*/                        throw new IllegalArgumentException(LocalizationMessages.ERROR_TEMPLATE_PARSER_NAME_MORE_THAN_ONCE(characteriterator, template));
                        } else
                        {
/* 426*/                    nameToPattern.put(characteriterator, pattern1);
                        }
                        Matcher matcher;
/* 430*/                int j = (matcher = pattern1.matcher("")).groupCount();
/* 432*/                groupCounts.add(Integer.valueOf(i + 1));
/* 433*/                i = j;
                    }
/* 436*/            regex.append('(').append(pattern1).append(')');
/* 440*/            normalizedTemplate.append('{').append(characteriterator).append('}');
                }
/* 443*/        catch(PatternSyntaxException patternsyntaxexception)
                {
/* 444*/            throw new IllegalArgumentException(LocalizationMessages.ERROR_TEMPLATE_PARSER_INVALID_SYNTAX(s, characteriterator, template), patternsyntaxexception);
                }
/* 449*/        return i;
            }

            private String parseRegex(CharacterIterator characteriterator)
            {
/* 453*/        StringBuilder stringbuilder = new StringBuilder();
/* 455*/        int i = 1;
/* 457*/        do
                {
                    char c;
/* 457*/            if((c = characteriterator.next()) == '{')
/* 459*/                i++;
/* 460*/            else
/* 460*/            if(c == '}' && --i == 0)
/* 466*/                break;
/* 466*/            stringbuilder.append(c);
                } while(true);
/* 469*/        return stringbuilder.toString().trim();
            }

            private char consumeWhiteSpace(CharacterIterator characteriterator)
            {
                char c;
/* 475*/        while(Character.isWhitespace(c = characteriterator.next())) ;
/* 478*/        return c;
            }

            static final int EMPTY_INT_ARRAY[] = new int[0];
            private static final Set RESERVED_REGEX_CHARACTERS = initReserved();
            private static final String HEX_TO_UPPERCASE_REGEX[] = initHexToUpperCaseRegex();
            public static final Pattern TEMPLATE_VALUE_PATTERN = Pattern.compile("[^/]+");
            private final String template;
            private final StringBuffer regex = new StringBuffer();
            private final StringBuffer normalizedTemplate = new StringBuffer();
            private final StringBuffer literalCharactersBuffer = new StringBuffer();
            private final Pattern pattern;
            private final List names = new ArrayList();
            private final List groupCounts = new ArrayList();
            private final Map nameToPattern = new HashMap();
            private int numOfExplicitRegexes;
            private int skipGroup;
            private int literalCharacters;

}
