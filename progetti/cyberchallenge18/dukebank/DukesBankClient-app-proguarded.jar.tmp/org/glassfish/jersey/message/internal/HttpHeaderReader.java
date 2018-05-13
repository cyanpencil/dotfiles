// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpHeaderReader.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import java.util.*;
import javax.ws.rs.core.*;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AcceptableMediaType, CookiesParser, HttpDateFormat, HttpHeaderListAdapter, 
//            HttpHeaderReaderImpl, MatchingEntityTag, Quality, QualitySourceMediaType, 
//            AcceptableLanguageTag, AcceptableToken, MediaTypes, MediaTypeProvider

public abstract class HttpHeaderReader
{
    static interface ListElementCreator
    {

        public abstract Object create(HttpHeaderReader httpheaderreader)
            throws ParseException;
    }

    public static final class Event extends Enum
    {

                public static Event[] values()
                {
/*  68*/            return (Event[])$VALUES.clone();
                }

                public static Event valueOf(String s)
                {
/*  68*/            return (Event)Enum.valueOf(org/glassfish/jersey/message/internal/HttpHeaderReader$Event, s);
                }

                public static final Event Token;
                public static final Event QuotedString;
                public static final Event Comment;
                public static final Event Separator;
                public static final Event Control;
                private static final Event $VALUES[];

                static 
                {
/*  70*/            Token = new Event("Token", 0);
/*  70*/            QuotedString = new Event("QuotedString", 1);
/*  70*/            Comment = new Event("Comment", 2);
/*  70*/            Separator = new Event("Separator", 3);
/*  70*/            Control = new Event("Control", 4);
/*  68*/            $VALUES = (new Event[] {
/*  68*/                Token, QuotedString, Comment, Separator, Control
                    });
                }

                private Event(String s, int i)
                {
/*  68*/            super(s, i);
                }
    }


            public HttpHeaderReader()
            {
            }

            public abstract boolean hasNext();

            public abstract boolean hasNextSeparator(char c, boolean flag);

            public abstract Event next()
                throws ParseException;

            public abstract Event next(boolean flag)
                throws ParseException;

            protected abstract Event next(boolean flag, boolean flag1)
                throws ParseException;

            protected abstract CharSequence nextSeparatedString(char c, char c1)
                throws ParseException;

            protected abstract Event getEvent();

            public abstract CharSequence getEventValue();

            public abstract CharSequence getRemainder();

            public abstract int getIndex();

            public final CharSequence nextToken()
                throws ParseException
            {
                Event event;
/* 127*/        if((event = next(false)) != Event.Token)
/* 129*/            throw new ParseException("Next event is not a Token", getIndex());
/* 132*/        else
/* 132*/            return getEventValue();
            }

            public final void nextSeparator(char c)
                throws ParseException
            {
                Event event;
/* 139*/        if((event = next(false)) != Event.Separator)
/* 141*/            throw new ParseException("Next event is not a Separator", getIndex());
/* 144*/        if(c != getEventValue().charAt(0))
/* 145*/            throw new ParseException((new StringBuilder("Expected separator '")).append(c).append("' instead of '").append(getEventValue().charAt(0)).append("'").toString(), getIndex());
/* 148*/        else
/* 148*/            return;
            }

            public final CharSequence nextQuotedString()
                throws ParseException
            {
                Event event;
/* 154*/        if((event = next(false)) != Event.QuotedString)
/* 156*/            throw new ParseException("Next event is not a Quoted String", getIndex());
/* 159*/        else
/* 159*/            return getEventValue();
            }

            public final CharSequence nextTokenOrQuotedString()
                throws ParseException
            {
/* 166*/        return nextTokenOrQuotedString(false);
            }

            private CharSequence nextTokenOrQuotedString(boolean flag)
                throws ParseException
            {
/* 170*/        if((flag = next(false, flag)) != Event.Token && flag != Event.QuotedString)
/* 172*/            throw new ParseException((new StringBuilder("Next event is not a Token or a Quoted String, ")).append(getEventValue()).toString(), getIndex());
/* 176*/        else
/* 176*/            return getEventValue();
            }

            public static HttpHeaderReader newInstance(String s)
            {
/* 183*/        return new HttpHeaderReaderImpl(s);
            }

            public static HttpHeaderReader newInstance(String s, boolean flag)
            {
/* 190*/        return new HttpHeaderReaderImpl(s, flag);
            }

            public static Date readDate(String s)
                throws ParseException
            {
/* 197*/        return HttpDateFormat.readDate(s);
            }

            public static int readQualityFactor(CharSequence charsequence)
                throws ParseException
            {
/* 204*/        if(charsequence == null || charsequence.length() == 0)
/* 205*/            throw new ParseException("Quality value cannot be null or an empty String", 0);
/* 208*/        int i = 0;
                int j;
/* 209*/        if((j = charsequence.length()) > 5)
/* 211*/            throw new ParseException("Quality value is greater than the maximum length, 5", 0);
/* 216*/        i++;
                char c;
/* 216*/        if((c = charsequence.charAt(0)) == '0' || c == '1')
                {
/* 218*/            if(1 == j)
/* 219*/                return (c - 48) * 1000;
/* 221*/            i++;
                    char c1;
/* 221*/            if((c1 = charsequence.charAt(1)) != '.')
/* 223*/                throw new ParseException((new StringBuilder("Error parsing Quality value: a decimal place is expected rather than '")).append(c1).append("'").toString(), 2);
/* 226*/            if(2 == j)
/* 227*/                return (c1 - 48) * 1000;
                } else
/* 229*/        if(c == '.')
                {
/* 232*/            if(1 == j)
/* 233*/                throw new ParseException("Error parsing Quality value: a decimal numeral is expected after the decimal point", 1);
                } else
                {
/* 238*/            throw new ParseException((new StringBuilder("Error parsing Quality value: a decimal numeral '0' or '1' is expected rather than '")).append(c).append("'").toString(), 1);
                }
/* 243*/        int k = 0;
/* 244*/        int l = 100;
                char c2;
/* 245*/        while(i < j) 
/* 246*/            if((c2 = charsequence.charAt(i++)) >= '0' && c2 <= '9')
                    {
/* 248*/                k += (c2 - 48) * l;
/* 249*/                l /= 10;
                    } else
                    {
/* 251*/                throw new ParseException((new StringBuilder("Error parsing Quality value: a decimal numeral is expected rather than '")).append(c2).append("'").toString(), i);
                    }
/* 256*/        if(c == '1')
                {
/* 257*/            if(k > 0)
/* 258*/                throw new ParseException((new StringBuilder("The Quality value, ")).append(charsequence).append(", is greater than 1").toString(), i);
/* 260*/            else
/* 260*/                return 1000;
                } else
                {
/* 262*/            return k;
                }
            }

            public static int readQualityFactorParameter(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 270*/        while(httpheaderreader.hasNext()) 
                {
/* 271*/            httpheaderreader.nextSeparator(';');
/* 274*/            if(!httpheaderreader.hasNext())
/* 275*/                return 1000;
/* 279*/            CharSequence charsequence = httpheaderreader.nextToken();
/* 280*/            httpheaderreader.nextSeparator('=');
/* 282*/            CharSequence charsequence1 = httpheaderreader.nextTokenOrQuotedString();
/* 284*/            if(charsequence.length() == 1 && (charsequence.charAt(0) == 'q' || charsequence.charAt(0) == 'Q'))
/* 285*/                return readQualityFactor(charsequence1);
                }
/* 289*/        return 1000;
            }

            public static Map readParameters(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 296*/        return readParameters(httpheaderreader, false);
            }

            public static Map readParameters(HttpHeaderReader httpheaderreader, boolean flag)
                throws ParseException
            {
/* 303*/        LinkedHashMap linkedhashmap = null;
                String s;
                String s1;
/* 305*/        for(; httpheaderreader.hasNext(); linkedhashmap.put(s, s1))
                {
/* 306*/            httpheaderreader.nextSeparator(';');
/* 307*/            for(; httpheaderreader.hasNextSeparator(';', true); httpheaderreader.next());
/* 312*/            if(!httpheaderreader.hasNext())
/* 317*/                break;
/* 317*/            s = httpheaderreader.nextToken().toString().toLowerCase();
/* 318*/            httpheaderreader.nextSeparator('=');
/* 322*/            if("filename".equals(s) && flag)
/* 323*/                s1 = (s1 = httpheaderreader.nextTokenOrQuotedString(true).toString()).substring(s1.lastIndexOf('\\') + 1);
/* 326*/            else
/* 326*/                s1 = httpheaderreader.nextTokenOrQuotedString(false).toString();
/* 328*/            if(linkedhashmap == null)
/* 329*/                linkedhashmap = new LinkedHashMap();
                }

/* 336*/        return linkedhashmap;
            }

            public static Map readCookies(String s)
            {
/* 343*/        return CookiesParser.parseCookies(s);
            }

            public static Cookie readCookie(String s)
            {
/* 350*/        return CookiesParser.parseCookie(s);
            }

            public static NewCookie readNewCookie(String s)
            {
/* 357*/        return CookiesParser.parseNewCookie(s);
            }

            public static Set readMatchingEntityTag(String s)
                throws ParseException
            {
/* 378*/        if("*".equals(s))
/* 379*/            return MatchingEntityTag.ANY_MATCH;
/* 382*/        s = new HttpHeaderReaderImpl(s);
/* 383*/        HashSet hashset = new HashSet(1);
/* 384*/        HttpHeaderListAdapter httpheaderlistadapter = new HttpHeaderListAdapter(s);
/* 385*/        do
                {
/* 385*/            if(!s.hasNext())
/* 386*/                break;
/* 386*/            hashset.add(MATCHING_ENTITY_TAG_CREATOR.create(httpheaderlistadapter));
/* 387*/            httpheaderlistadapter.reset();
/* 388*/            if(s.hasNext())
/* 389*/                s.next();
                } while(true);
/* 393*/        return hashset;
            }

            public static List readMediaTypes(List list, String s)
                throws ParseException
            {
/* 409*/        return readList(list, MEDIA_TYPE_CREATOR, s);
            }

            public static List readAcceptMediaType(String s)
                throws ParseException
            {
/* 428*/        return readQualifiedList(AcceptableMediaType.COMPARATOR, ACCEPTABLE_MEDIA_TYPE_CREATOR, s);
            }

            public static List readQualitySourceMediaType(String s)
                throws ParseException
            {
/* 447*/        return readQualifiedList(QualitySourceMediaType.COMPARATOR, QUALITY_SOURCE_MEDIA_TYPE_CREATOR, s);
            }

            public static List readQualitySourceMediaType(String as[])
                throws ParseException
            {
/* 457*/        if(as.length < 2)
/* 458*/            return readQualitySourceMediaType(as[0]);
/* 461*/        StringBuilder stringbuilder = new StringBuilder();
/* 462*/        int i = (as = as).length;
/* 462*/        for(int j = 0; j < i; j++)
                {
/* 462*/            String s = as[j];
/* 463*/            if(stringbuilder.length() > 0)
/* 464*/                stringbuilder.append(",");
/* 467*/            stringbuilder.append(s);
                }

/* 470*/        return readQualitySourceMediaType(stringbuilder.toString());
            }

            public static List readAcceptMediaType(String s, List list)
                throws ParseException
            {
/* 479*/        return readQualifiedList(new Comparator(list) {

                    public final int compare(AcceptableMediaType acceptablemediatype, AcceptableMediaType acceptablemediatype1)
                    {
/* 485*/                boolean flag = false;
/* 486*/                int i = 0;
/* 487*/                boolean flag1 = false;
/* 488*/                int j = 0;
/* 489*/                Iterator iterator = priorityMediaTypes.iterator();
/* 489*/                do
                        {
/* 489*/                    if(!iterator.hasNext())
/* 489*/                        break;
/* 489*/                    QualitySourceMediaType qualitysourcemediatype = (QualitySourceMediaType)iterator.next();
/* 490*/                    if(!flag && MediaTypes.typeEqual(acceptablemediatype, qualitysourcemediatype))
                            {
/* 491*/                        i = acceptablemediatype.getQuality() * qualitysourcemediatype.getQuality();
/* 492*/                        flag = true;
                            } else
/* 493*/                    if(!flag1 && MediaTypes.typeEqual(acceptablemediatype1, qualitysourcemediatype))
                            {
/* 494*/                        j = acceptablemediatype1.getQuality() * qualitysourcemediatype.getQuality();
/* 495*/                        flag1 = true;
                            }
                        } while(true);
                        int k;
/* 498*/                if((k = j - i) != 0)
/* 500*/                    return k;
/* 503*/                if((k = acceptablemediatype1.getQuality() - acceptablemediatype.getQuality()) != 0)
/* 505*/                    return k;
/* 508*/                else
/* 508*/                    return MediaTypes.PARTIAL_ORDER_COMPARATOR.compare(acceptablemediatype, acceptablemediatype1);
                    }

                    public final volatile int compare(Object obj, Object obj1)
                    {
/* 480*/                return compare((AcceptableMediaType)obj, (AcceptableMediaType)obj1);
                    }

                    final List val$priorityMediaTypes;

                    
                    {
/* 480*/                priorityMediaTypes = list;
/* 480*/                super();
                    }
        }, ACCEPTABLE_MEDIA_TYPE_CREATOR, s);
            }

            public static List readAcceptToken(String s)
                throws ParseException
            {
/* 527*/        return readQualifiedList(ACCEPTABLE_TOKEN_CREATOR, s);
            }

            public static List readAcceptLanguage(String s)
                throws ParseException
            {
/* 543*/        return readQualifiedList(LANGUAGE_CREATOR, s);
            }

            private static List readQualifiedList(ListElementCreator listelementcreator, String s)
                throws ParseException
            {
/* 549*/        Collections.sort(listelementcreator = readList(listelementcreator, s), Quality.QUALIFIED_COMPARATOR);
/* 551*/        return listelementcreator;
            }

            private static List readQualifiedList(Comparator comparator, ListElementCreator listelementcreator, String s)
                throws ParseException
            {
/* 557*/        Collections.sort(listelementcreator = readList(listelementcreator, s), comparator);
/* 559*/        return listelementcreator;
            }

            public static List readStringList(String s)
                throws ParseException
            {
/* 566*/        return readList(new ListElementCreator() {

                    public final String create(HttpHeaderReader httpheaderreader)
                        throws ParseException
                    {
/* 570*/                httpheaderreader.hasNext();
/* 571*/                return httpheaderreader.nextToken().toString();
                    }

                    public final volatile Object create(HttpHeaderReader httpheaderreader)
                        throws ParseException
                    {
/* 566*/                return create(httpheaderreader);
                    }

        }, s);
            }

            private static List readList(ListElementCreator listelementcreator, String s)
                throws ParseException
            {
/* 577*/        return readList(((List) (new ArrayList())), listelementcreator, s);
            }

            private static List readList(List list, ListElementCreator listelementcreator, String s)
                throws ParseException
            {
/* 583*/        s = new HttpHeaderReaderImpl(s);
/* 584*/        HttpHeaderListAdapter httpheaderlistadapter = new HttpHeaderListAdapter(s);
/* 586*/        do
                {
/* 586*/            if(!s.hasNext())
/* 587*/                break;
/* 587*/            list.add(listelementcreator.create(httpheaderlistadapter));
/* 588*/            httpheaderlistadapter.reset();
/* 589*/            if(s.hasNext())
/* 590*/                s.next();
                } while(true);
/* 594*/        return list;
            }

            private static final ListElementCreator MATCHING_ENTITY_TAG_CREATOR = new ListElementCreator() {

                public final MatchingEntityTag create(HttpHeaderReader httpheaderreader)
                    throws ParseException
                {
/* 370*/            return MatchingEntityTag.valueOf(httpheaderreader);
                }

                public final volatile Object create(HttpHeaderReader httpheaderreader)
                    throws ParseException
                {
/* 366*/            return create(httpheaderreader);
                }

    };
            private static final ListElementCreator MEDIA_TYPE_CREATOR = new ListElementCreator() {

                public final MediaType create(HttpHeaderReader httpheaderreader)
                    throws ParseException
                {
/* 401*/            return MediaTypeProvider.valueOf(httpheaderreader);
                }

                public final volatile Object create(HttpHeaderReader httpheaderreader)
                    throws ParseException
                {
/* 397*/            return create(httpheaderreader);
                }

    };
            private static final ListElementCreator ACCEPTABLE_MEDIA_TYPE_CREATOR = new ListElementCreator() {

                public final AcceptableMediaType create(HttpHeaderReader httpheaderreader)
                    throws ParseException
                {
/* 420*/            return AcceptableMediaType.valueOf(httpheaderreader);
                }

                public final volatile Object create(HttpHeaderReader httpheaderreader)
                    throws ParseException
                {
/* 416*/            return create(httpheaderreader);
                }

    };
            private static final ListElementCreator QUALITY_SOURCE_MEDIA_TYPE_CREATOR = new ListElementCreator() {

                public final QualitySourceMediaType create(HttpHeaderReader httpheaderreader)
                    throws ParseException
                {
/* 439*/            return QualitySourceMediaType.valueOf(httpheaderreader);
                }

                public final volatile Object create(HttpHeaderReader httpheaderreader)
                    throws ParseException
                {
/* 435*/            return create(httpheaderreader);
                }

    };
            private static final ListElementCreator ACCEPTABLE_TOKEN_CREATOR = new ListElementCreator() {

                public final AcceptableToken create(HttpHeaderReader httpheaderreader)
                    throws ParseException
                {
/* 519*/            return new AcceptableToken(httpheaderreader);
                }

                public final volatile Object create(HttpHeaderReader httpheaderreader)
                    throws ParseException
                {
/* 515*/            return create(httpheaderreader);
                }

    };
            private static final ListElementCreator LANGUAGE_CREATOR = new ListElementCreator() {

                public final AcceptableLanguageTag create(HttpHeaderReader httpheaderreader)
                    throws ParseException
                {
/* 535*/            return new AcceptableLanguageTag(httpheaderreader);
                }

                public final volatile Object create(HttpHeaderReader httpheaderreader)
                    throws ParseException
                {
/* 531*/            return create(httpheaderreader);
                }

    };

}
