// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpHeaderReaderImpl.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import org.glassfish.jersey.internal.LocalizationMessages;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpHeaderReader, GrammarUtil

final class HttpHeaderReaderImpl extends HttpHeaderReader
{

            HttpHeaderReaderImpl(String s, boolean flag)
            {
/*  74*/        header = ((CharSequence) (s != null ? ((CharSequence) (s)) : ""));
/*  75*/        processComments = flag;
/*  76*/        index = 0;
/*  77*/        length = header.length();
            }

            HttpHeaderReaderImpl(String s)
            {
/*  81*/        this(s, false);
            }

            public final boolean hasNext()
            {
/*  86*/        return skipWhiteSpace();
            }

            public final boolean hasNextSeparator(char c, boolean flag)
            {
/*  91*/        if(flag)
/*  92*/            skipWhiteSpace();
/*  95*/        if(index >= length)
/*  96*/            return false;
/*  99*/        return GrammarUtil.isSeparator(flag = header.charAt(index)) && flag == c;
            }

            public final String nextSeparatedString(char c, char c1)
                throws ParseException
            {
/* 105*/        nextSeparator(c);
/* 106*/        int i = index;
/* 107*/        for(; index < length && header.charAt(index) != c1; index++);
/* 113*/        if(i == index)
/* 115*/            throw new ParseException(LocalizationMessages.HTTP_HEADER_NO_CHARS_BETWEEN_SEPARATORS(Character.valueOf(c), Character.valueOf(c1)), index);
/* 117*/        if(index == length)
                {
/* 119*/            throw new ParseException(LocalizationMessages.HTTP_HEADER_NO_END_SEPARATOR(Character.valueOf(c1)), index);
                } else
                {
/* 122*/            event = HttpHeaderReader.Event.Token;
/* 123*/            value = header.subSequence(i, index++);
/* 124*/            return value.toString();
                }
            }

            public final HttpHeaderReader.Event next()
                throws ParseException
            {
/* 129*/        return next(true);
            }

            public final HttpHeaderReader.Event next(boolean flag)
                throws ParseException
            {
/* 134*/        return next(flag, false);
            }

            public final HttpHeaderReader.Event next(boolean flag, boolean flag1)
                throws ParseException
            {
/* 139*/        return event = process(getNextCharacter(flag), flag1);
            }

            public final HttpHeaderReader.Event getEvent()
            {
/* 144*/        return event;
            }

            public final CharSequence getEventValue()
            {
/* 149*/        return value;
            }

            public final CharSequence getRemainder()
            {
/* 154*/        if(index < length)
/* 154*/            return header.subSequence(index, header.length());
/* 154*/        else
/* 154*/            return null;
            }

            public final int getIndex()
            {
/* 159*/        return index;
            }

            private boolean skipWhiteSpace()
            {
/* 163*/        for(; index < length; index++)
/* 164*/            if(!GrammarUtil.isWhiteSpace(header.charAt(index)))
/* 165*/                return true;

/* 169*/        return false;
            }

            private char getNextCharacter(boolean flag)
                throws ParseException
            {
/* 173*/        if(flag)
/* 174*/            skipWhiteSpace();
/* 177*/        if(index >= length)
/* 178*/            throw new ParseException(LocalizationMessages.HTTP_HEADER_END_OF_HEADER(), index);
/* 181*/        else
/* 181*/            return header.charAt(index);
            }

            private HttpHeaderReader.Event process(char c, boolean flag)
                throws ParseException
            {
/* 185*/        if(c > '\177')
                {
/* 186*/            index++;
/* 187*/            return HttpHeaderReader.Event.Control;
                }
/* 190*/        switch(GrammarUtil.getType(c))
                {
/* 192*/        case 0: // '\0'
/* 192*/            c = index;
/* 193*/            for(index++; index < length && GrammarUtil.isToken(header.charAt(index)); index++);
/* 198*/            value = header.subSequence(c, index);
/* 199*/            return HttpHeaderReader.Event.Token;

/* 202*/        case 1: // '\001'
/* 202*/            processQuotedString(flag);
/* 203*/            return HttpHeaderReader.Event.QuotedString;

/* 205*/        case 2: // '\002'
/* 205*/            if(!processComments)
                    {
/* 206*/                throw new ParseException(LocalizationMessages.HTTP_HEADER_COMMENTS_NOT_ALLOWED(), index);
                    } else
                    {
/* 209*/                processComment();
/* 210*/                return HttpHeaderReader.Event.Comment;
                    }

/* 212*/        case 3: // '\003'
/* 212*/            index++;
/* 213*/            value = String.valueOf(c);
/* 214*/            return HttpHeaderReader.Event.Separator;

/* 216*/        case 4: // '\004'
/* 216*/            index++;
/* 217*/            value = String.valueOf(c);
/* 218*/            return HttpHeaderReader.Event.Control;
                }
/* 221*/        throw new ParseException(LocalizationMessages.HTTP_HEADER_WHITESPACE_NOT_ALLOWED(), index);
            }

            private void processComment()
                throws ParseException
            {
/* 226*/        boolean flag = false;
/* 229*/        int j = ++index;
                int i;
/* 229*/        for(i = 1; i > 0 && index < length; index++)
                {
                    char c;
/* 230*/            if((c = header.charAt(index)) == '\\')
                    {
/* 232*/                index++;
/* 233*/                flag = true;
/* 233*/                continue;
                    }
/* 234*/            if(c == '\r')
                    {
/* 235*/                flag = true;
/* 235*/                continue;
                    }
/* 236*/            if(c == '(')
                    {
/* 237*/                i++;
/* 237*/                continue;
                    }
/* 238*/            if(c == ')')
/* 239*/                i--;
                }

/* 242*/        if(i != 0)
                {
/* 243*/            throw new ParseException(LocalizationMessages.HTTP_HEADER_UNBALANCED_COMMENTS(), index);
                } else
                {
/* 246*/            value = ((CharSequence) (flag ? ((CharSequence) (GrammarUtil.filterToken(header, j, index - 1))) : header.subSequence(j, index - 1)));
/* 247*/            return;
                }
            }

            private void processQuotedString(boolean flag)
                throws ParseException
            {
/* 250*/        boolean flag1 = false;
/* 251*/        int i = ++index;
/* 251*/        for(; index < length; index++)
                {
/* 252*/            char c = header.charAt(index);
/* 253*/            if(!flag && c == '\\')
                    {
/* 254*/                index++;
/* 255*/                flag1 = true;
/* 255*/                continue;
                    }
/* 256*/            if(c == '\r')
                    {
/* 257*/                flag1 = true;
/* 257*/                continue;
                    }
/* 258*/            if(c == '"')
                    {
/* 259*/                value = ((CharSequence) (flag1 ? ((CharSequence) (GrammarUtil.filterToken(header, i, index, flag))) : header.subSequence(i, index)));
/* 261*/                index++;
/* 262*/                return;
                    }
                }

/* 266*/        throw new ParseException(LocalizationMessages.HTTP_HEADER_UNBALANCED_QUOTED(), index);
            }

            public final volatile CharSequence nextSeparatedString(char c, char c1)
                throws ParseException
            {
/*  63*/        return nextSeparatedString(c, c1);
            }

            private final CharSequence header;
            private final boolean processComments;
            private final int length;
            private int index;
            private HttpHeaderReader.Event event;
            private CharSequence value;
}
