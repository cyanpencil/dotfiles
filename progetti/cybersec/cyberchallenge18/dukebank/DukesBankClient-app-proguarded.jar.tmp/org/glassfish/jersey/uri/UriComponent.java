// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UriComponent.java

package org.glassfish.jersey.uri;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;

public class UriComponent
{
    static final class PathSegmentImpl
        implements PathSegment
    {

                public final String getPath()
                {
/* 623*/            return path;
                }

                public final MultivaluedMap getMatrixParameters()
                {
/* 628*/            return matrixParameters;
                }

                public final String toString()
                {
/* 633*/            return path;
                }

                private static final PathSegment EMPTY_PATH_SEGMENT = new PathSegmentImpl("", false);
                private final String path;
                private final MultivaluedMap matrixParameters;



                PathSegmentImpl(String s, boolean flag)
                {
/* 613*/            this(s, flag, ((MultivaluedMap) (new MultivaluedStringMap())));
                }

                PathSegmentImpl(String s, boolean flag, MultivaluedMap multivaluedmap)
                {
/* 617*/            path = flag ? UriComponent.decode(s, Type.PATH_SEGMENT) : s;
/* 618*/            matrixParameters = multivaluedmap;
                }
    }

    public static final class Type extends Enum
    {

                public static Type[] values()
                {
/*  73*/            return (Type[])$VALUES.clone();
                }

                public static Type valueOf(String s)
                {
/*  73*/            return (Type)Enum.valueOf(org/glassfish/jersey/uri/UriComponent$Type, s);
                }

                public static final Type UNRESERVED;
                public static final Type SCHEME;
                public static final Type AUTHORITY;
                public static final Type USER_INFO;
                public static final Type HOST;
                public static final Type PORT;
                public static final Type PATH;
                public static final Type PATH_SEGMENT;
                public static final Type MATRIX_PARAM;
                public static final Type QUERY;
                public static final Type QUERY_PARAM;
                public static final Type QUERY_PARAM_SPACE_ENCODED;
                public static final Type FRAGMENT;
                private static final Type $VALUES[];

                static 
                {
/*  78*/            UNRESERVED = new Type("UNRESERVED", 0);
/*  82*/            SCHEME = new Type("SCHEME", 1);
/*  86*/            AUTHORITY = new Type("AUTHORITY", 2);
/*  90*/            USER_INFO = new Type("USER_INFO", 3);
/*  94*/            HOST = new Type("HOST", 4);
/*  98*/            PORT = new Type("PORT", 5);
/* 102*/            PATH = new Type("PATH", 6);
/* 106*/            PATH_SEGMENT = new Type("PATH_SEGMENT", 7);
/* 110*/            MATRIX_PARAM = new Type("MATRIX_PARAM", 8);
/* 114*/            QUERY = new Type("QUERY", 9);
/* 120*/            QUERY_PARAM = new Type("QUERY_PARAM", 10);
/* 126*/            QUERY_PARAM_SPACE_ENCODED = new Type("QUERY_PARAM_SPACE_ENCODED", 11);
/* 130*/            FRAGMENT = new Type("FRAGMENT", 12);
/*  73*/            $VALUES = (new Type[] {
/*  73*/                UNRESERVED, SCHEME, AUTHORITY, USER_INFO, HOST, PORT, PATH, PATH_SEGMENT, MATRIX_PARAM, QUERY, 
/*  73*/                QUERY_PARAM, QUERY_PARAM_SPACE_ENCODED, FRAGMENT
                    });
                }

                private Type(String s, int i)
                {
/*  73*/            super(s, i);
                }
    }


            private UriComponent()
            {
            }

            public static void validate(String s, Type type)
            {
/* 146*/        validate(s, type, false);
            }

            public static void validate(String s, Type type, boolean flag)
            {
/* 160*/        flag = _valid(s, type, flag);
/* 161*/        JVM INSTR iflt 36;
                   goto _L1 _L2
_L1:
/* 162*/        break MISSING_BLOCK_LABEL_11;
_L2:
/* 162*/        break MISSING_BLOCK_LABEL_36;
/* 162*/        throw new IllegalArgumentException(LocalizationMessages.URI_COMPONENT_INVALID_CHARACTER(s, type, Character.valueOf(s.charAt(flag)), Integer.valueOf(flag)));
            }

            public static boolean valid(String s, Type type)
            {
/* 175*/        return valid(s, type, false);
            }

            public static boolean valid(String s, Type type, boolean flag)
            {
/* 188*/        return _valid(s, type, flag) == -1;
            }

            private static int _valid(String s, Type type, boolean flag)
            {
/* 192*/        type = ENCODING_TABLES[type.ordinal()];
/* 194*/        for(int i = 0; i < s.length(); i++)
                {
                    char c;
/* 195*/            if(((c = s.charAt(i)) < '\200' && c != '%' && type[c] == 0 || c >= '\200') && (!flag || c != '{' && c != '}'))
/* 198*/                return i;
                }

/* 202*/        return -1;
            }

            public static String contextualEncode(String s, Type type)
            {
/* 217*/        return _encode(s, type, false, true);
            }

            public static String contextualEncode(String s, Type type, boolean flag)
            {
/* 233*/        return _encode(s, type, flag, true);
            }

            public static String encode(String s, Type type)
            {
/* 247*/        return _encode(s, type, false, false);
            }

            public static String encode(String s, Type type, boolean flag)
            {
/* 262*/        return _encode(s, type, flag, false);
            }

            public static String encodeTemplateNames(String s)
            {
                int i;
/* 273*/        if((i = s.indexOf('{')) != -1)
/* 275*/            s = s.replace("{", "%7B");
/* 277*/        if((i = s.indexOf('}')) != -1)
/* 279*/            s = s.replace("}", "%7D");
/* 282*/        return s;
            }

            private static String _encode(String s, Type type, boolean flag, boolean flag1)
            {
/* 286*/        boolean aflag[] = ENCODING_TABLES[type.ordinal()];
/* 287*/        boolean flag2 = false;
/* 289*/        StringBuilder stringbuilder = null;
                int j;
/* 290*/        for(int i = 0; i < s.length(); i += Character.charCount(j))
                {
/* 291*/            if((j = s.codePointAt(i)) < 128 && aflag[j])
                    {
/* 294*/                if(stringbuilder != null)
/* 295*/                    stringbuilder.append((char)j);
/* 295*/                continue;
                    }
/* 298*/            if(flag)
                    {
/* 299*/                boolean flag3 = false;
/* 300*/                if(j == 123)
/* 301*/                    flag2 = true;
/* 302*/                else
/* 302*/                if(j == 125)
                        {
/* 303*/                    flag2 = false;
/* 304*/                    flag3 = true;
                        }
/* 306*/                if(flag2 || flag3)
                        {
/* 307*/                    if(stringbuilder != null)
/* 308*/                        stringbuilder.append(Character.toChars(j));
/* 308*/                    continue;
                        }
                    }
/* 314*/            if(flag1 && j == 37 && i + 2 < s.length() && isHexCharacter(s.charAt(i + 1)) && isHexCharacter(s.charAt(i + 2)))
                    {
/* 319*/                if(stringbuilder != null)
/* 320*/                    stringbuilder.append('%').append(s.charAt(i + 1)).append(s.charAt(i + 2));
/* 322*/                i += 2;
/* 323*/                continue;
                    }
/* 326*/            if(stringbuilder == null)
/* 327*/                (stringbuilder = new StringBuilder()).append(s.substring(0, i));
/* 331*/            if(j < 128)
                    {
/* 332*/                if(j == 32 && type == Type.QUERY_PARAM)
/* 333*/                    stringbuilder.append('+');
/* 335*/                else
/* 335*/                    appendPercentEncodedOctet(stringbuilder, (char)j);
                    } else
                    {
/* 338*/                appendUTF8EncodedCharacter(stringbuilder, j);
                    }
                }

/* 343*/        if(stringbuilder == null)
/* 343*/            return s;
/* 343*/        else
/* 343*/            return stringbuilder.toString();
            }

            private static void appendPercentEncodedOctet(StringBuilder stringbuilder, int i)
            {
/* 351*/        stringbuilder.append('%');
/* 352*/        stringbuilder.append(HEX_DIGITS[i >> 4]);
/* 353*/        stringbuilder.append(HEX_DIGITS[i & 0xf]);
            }

            private static void appendUTF8EncodedCharacter(StringBuilder stringbuilder, int i)
            {
/* 357*/        i = CharBuffer.wrap(Character.toChars(i));
/* 358*/        for(i = UTF_8_CHARSET.encode(i); i.hasRemaining(); appendPercentEncodedOctet(stringbuilder, i.get() & 0xff));
            }

            private static boolean[][] initEncodingTables()
            {
/* 371*/        boolean aflag[][] = new boolean[Type.values().length][];
                ArrayList arraylist;
/* 373*/        (arraylist = new ArrayList()).addAll(Arrays.asList(SCHEME));
/* 375*/        aflag[Type.SCHEME.ordinal()] = initEncodingTable(arraylist);
/* 377*/        arraylist.clear();
/* 379*/        arraylist.addAll(Arrays.asList(UNRESERVED));
/* 380*/        aflag[Type.UNRESERVED.ordinal()] = initEncodingTable(arraylist);
/* 382*/        arraylist.addAll(Arrays.asList(SUB_DELIMS));
/* 384*/        aflag[Type.HOST.ordinal()] = initEncodingTable(arraylist);
/* 386*/        aflag[Type.PORT.ordinal()] = initEncodingTable(Arrays.asList(new String[] {
/* 386*/            "0-9"
                }));
/* 388*/        arraylist.add(":");
/* 390*/        aflag[Type.USER_INFO.ordinal()] = initEncodingTable(arraylist);
/* 392*/        arraylist.add("@");
/* 394*/        aflag[Type.AUTHORITY.ordinal()] = initEncodingTable(arraylist);
/* 396*/        aflag[Type.PATH_SEGMENT.ordinal()] = initEncodingTable(arraylist);
/* 397*/        aflag[Type.PATH_SEGMENT.ordinal()][59] = false;
/* 399*/        aflag[Type.MATRIX_PARAM.ordinal()] = (boolean[])aflag[Type.PATH_SEGMENT.ordinal()].clone();
/* 400*/        aflag[Type.MATRIX_PARAM.ordinal()][61] = false;
/* 402*/        arraylist.add("/");
/* 404*/        aflag[Type.PATH.ordinal()] = initEncodingTable(arraylist);
/* 406*/        aflag[Type.QUERY.ordinal()] = initEncodingTable(arraylist);
/* 407*/        aflag[Type.QUERY.ordinal()][33] = false;
/* 408*/        aflag[Type.QUERY.ordinal()][42] = false;
/* 409*/        aflag[Type.QUERY.ordinal()][39] = false;
/* 410*/        aflag[Type.QUERY.ordinal()][40] = false;
/* 411*/        aflag[Type.QUERY.ordinal()][41] = false;
/* 412*/        aflag[Type.QUERY.ordinal()][59] = false;
/* 413*/        aflag[Type.QUERY.ordinal()][58] = false;
/* 414*/        aflag[Type.QUERY.ordinal()][64] = false;
/* 415*/        aflag[Type.QUERY.ordinal()][36] = false;
/* 416*/        aflag[Type.QUERY.ordinal()][44] = false;
/* 417*/        aflag[Type.QUERY.ordinal()][47] = false;
/* 418*/        aflag[Type.QUERY.ordinal()][63] = false;
/* 420*/        aflag[Type.QUERY_PARAM.ordinal()] = Arrays.copyOf(aflag[Type.QUERY.ordinal()], aflag[Type.QUERY.ordinal()].length);
/* 423*/        aflag[Type.QUERY_PARAM.ordinal()][61] = false;
/* 424*/        aflag[Type.QUERY_PARAM.ordinal()][43] = false;
/* 425*/        aflag[Type.QUERY_PARAM.ordinal()][38] = false;
/* 427*/        aflag[Type.QUERY_PARAM_SPACE_ENCODED.ordinal()] = aflag[Type.QUERY_PARAM.ordinal()];
/* 429*/        aflag[Type.FRAGMENT.ordinal()] = aflag[Type.QUERY.ordinal()];
/* 431*/        return aflag;
            }

            private static boolean[] initEncodingTable(List list)
            {
/* 435*/        boolean aflag[] = new boolean[128];
/* 436*/label0:
/* 436*/        do
                {
                    String s;
/* 436*/label1:
/* 436*/            do
                    {
/* 436*/                for(list = list.iterator(); list.hasNext();)
                        {
/* 436*/                    if((s = (String)list.next()).length() != 1)
/* 438*/                        continue label1;
/* 438*/                    aflag[s.charAt(0)] = true;
                        }

/* 438*/                break label0;
                    } while(s.length() != 3 || s.charAt(1) != '-');
/* 440*/            int i = s.charAt(0);
/* 440*/            while(i <= s.charAt(2)) 
                    {
/* 441*/                aflag[i] = true;
/* 440*/                i++;
                    }
                } while(true);
/* 446*/        return aflag;
            }

            public static String decode(String s, Type type)
            {
/* 473*/        if(s == null)
/* 474*/            throw new IllegalArgumentException();
                int i;
/* 477*/        if((i = s.length()) == 0)
/* 479*/            return s;
/* 483*/        if(s.indexOf('%') < 0)
                {
/* 485*/            if(type == Type.QUERY_PARAM)
                    {
/* 486*/                if(s.indexOf('+') < 0)
/* 487*/                    return s;
                    } else
                    {
/* 490*/                return s;
                    }
                } else
                {
/* 494*/            if(i < 2)
/* 495*/                throw new IllegalArgumentException(LocalizationMessages.URI_COMPONENT_ENCODED_OCTET_MALFORMED(Integer.valueOf(1)));
/* 499*/            if(s.charAt(i - 2) == '%')
/* 500*/                throw new IllegalArgumentException(LocalizationMessages.URI_COMPONENT_ENCODED_OCTET_MALFORMED(Integer.valueOf(i - 2)));
                }
/* 504*/        if(type == null)
/* 505*/            return decode(s, i);
        static class _cls1
        {

                    static final int $SwitchMap$org$glassfish$jersey$uri$UriComponent$Type[];

                    static 
                    {
/* 508*/                $SwitchMap$org$glassfish$jersey$uri$UriComponent$Type = new int[Type.values().length];
/* 508*/                try
                        {
/* 508*/                    $SwitchMap$org$glassfish$jersey$uri$UriComponent$Type[Type.HOST.ordinal()] = 1;
                        }
/* 508*/                catch(NoSuchFieldError _ex) { }
/* 508*/                try
                        {
/* 508*/                    $SwitchMap$org$glassfish$jersey$uri$UriComponent$Type[Type.QUERY_PARAM.ordinal()] = 2;
                        }
/* 508*/                catch(NoSuchFieldError _ex) { }
                    }
        }

/* 508*/        switch(_cls1..SwitchMap.org.glassfish.jersey.uri.UriComponent.Type[type.ordinal()])
                {
/* 510*/        case 1: // '\001'
/* 510*/            return decodeHost(s, i);

/* 512*/        case 2: // '\002'
/* 512*/            return decodeQueryParam(s, i);
                }
/* 514*/        return decode(s, i);
            }

            public static MultivaluedMap decodeQuery(URI uri, boolean flag)
            {
/* 531*/        return decodeQuery(uri.getRawQuery(), flag);
            }

            public static MultivaluedMap decodeQuery(String s, boolean flag)
            {
/* 547*/        return decodeQuery(s, true, flag);
            }

            public static MultivaluedMap decodeQuery(String s, boolean flag, boolean flag1)
            {
/* 566*/        MultivaluedStringMap multivaluedstringmap = new MultivaluedStringMap();
/* 568*/        if(s == null || s.length() == 0)
/* 569*/            return multivaluedstringmap;
/* 572*/        int i = 0;
                int j;
/* 574*/        do
/* 574*/            if((j = s.indexOf('&', i)) == -1)
/* 577*/                decodeQueryParam(multivaluedstringmap, s.substring(i), flag, flag1);
/* 578*/            else
/* 578*/            if(j > i)
/* 579*/                decodeQueryParam(multivaluedstringmap, s.substring(i, j), flag, flag1);
/* 582*/        while((i = j + 1) > 0 && i < s.length());
/* 584*/        return multivaluedstringmap;
            }

            private static void decodeQueryParam(MultivaluedMap multivaluedmap, String s, boolean flag, boolean flag1)
            {
                int i;
/* 591*/        try
                {
/* 591*/            if((i = s.indexOf('=')) > 0)
                    {
/* 593*/                multivaluedmap.add(flag ? ((Object) (URLDecoder.decode(s.substring(0, i), "UTF-8"))) : ((Object) (s.substring(0, i))), flag1 ? ((Object) (URLDecoder.decode(s.substring(i + 1), "UTF-8"))) : ((Object) (s.substring(i + 1))));
                    } else
                    {
/* 595*/                if(i != 0 && s.length() > 0)
/* 598*/                    multivaluedmap.add(flag ? ((Object) (URLDecoder.decode(s, "UTF-8"))) : ((Object) (s)), "");
/* 603*/                return;
                    }
                }
/* 600*/        catch(UnsupportedEncodingException unsupportedencodingexception)
                {
/* 602*/            throw new IllegalArgumentException(unsupportedencodingexception);
                }
            }

            public static List decodePath(URI uri, boolean flag)
            {
/* 648*/        if((uri = uri.getRawPath()) != null && uri.length() > 0 && uri.charAt(0) == '/')
/* 650*/            uri = uri.substring(1);
/* 652*/        return decodePath(((String) (uri)), flag);
            }

            public static List decodePath(String s, boolean flag)
            {
/* 672*/        LinkedList linkedlist = new LinkedList();
/* 674*/        if(s == null)
/* 675*/            return linkedlist;
/* 679*/        int j = -1;
                int i;
/* 681*/        do
                {
/* 681*/            i = j + 1;
/* 682*/            if((j = s.indexOf('/', i)) > i)
/* 685*/                decodePathSegment(linkedlist, s.substring(i, j), flag);
/* 686*/            else
/* 686*/            if(j == i)
/* 687*/                linkedlist.add(PathSegmentImpl.EMPTY_PATH_SEGMENT);
                } while(j != -1);
/* 690*/        if(i < s.length())
/* 691*/            decodePathSegment(linkedlist, s.substring(i), flag);
/* 693*/        else
/* 693*/            linkedlist.add(PathSegmentImpl.EMPTY_PATH_SEGMENT);
/* 695*/        return linkedlist;
            }

            public static void decodePathSegment(List list, String s, boolean flag)
            {
                int i;
/* 706*/        if((i = s.indexOf(';')) != -1)
                {
/* 708*/            list.add(new PathSegmentImpl(i != 0 ? s.substring(0, i) : "", flag, decodeMatrix(s, flag)));
/* 708*/            return;
                } else
                {
/* 711*/            list.add(new PathSegmentImpl(s, flag));
/* 713*/            return;
                }
            }

            public static MultivaluedMap decodeMatrix(String s, boolean flag)
            {
/* 724*/        MultivaluedStringMap multivaluedstringmap = new MultivaluedStringMap();
                int i;
/* 727*/        if((i = s.indexOf(';') + 1) == 0 || i == s.length())
/* 729*/            return multivaluedstringmap;
                int j;
/* 733*/        do
/* 733*/            if((j = s.indexOf(';', i)) == -1)
/* 736*/                decodeMatrixParam(multivaluedstringmap, s.substring(i), flag);
/* 737*/            else
/* 737*/            if(j > i)
/* 738*/                decodeMatrixParam(multivaluedstringmap, s.substring(i, j), flag);
/* 741*/        while((i = j + 1) > 0 && i < s.length());
/* 743*/        return multivaluedstringmap;
            }

            private static void decodeMatrixParam(MultivaluedMap multivaluedmap, String s, boolean flag)
            {
                int i;
/* 748*/        if((i = s.indexOf('=')) > 0)
                {
/* 750*/            multivaluedmap.add(decode(s.substring(0, i), Type.MATRIX_PARAM), flag ? ((Object) (decode(s.substring(i + 1), Type.MATRIX_PARAM))) : ((Object) (s.substring(i + 1))));
/* 750*/            return;
                }
/* 753*/        if(i != 0 && s.length() > 0)
/* 756*/            multivaluedmap.add(decode(s, Type.MATRIX_PARAM), "");
            }

            private static String decode(String s, int i)
            {
/* 761*/        StringBuilder stringbuilder = new StringBuilder(i);
/* 762*/        ByteBuffer bytebuffer = null;
                char c;
/* 764*/        for(int j = 0; j < i;)
/* 765*/            if((c = s.charAt(j++)) != '%')
                    {
/* 767*/                stringbuilder.append(c);
                    } else
                    {
/* 769*/                bytebuffer = decodePercentEncodedOctets(s, j, bytebuffer);
/* 770*/                j = decodeOctets(j, bytebuffer, stringbuilder);
                    }

/* 774*/        return stringbuilder.toString();
            }

            private static String decodeQueryParam(String s, int i)
            {
/* 778*/        StringBuilder stringbuilder = new StringBuilder(i);
/* 779*/        ByteBuffer bytebuffer = null;
                char c;
/* 781*/        for(int j = 0; j < i;)
/* 782*/            if((c = s.charAt(j++)) != '%')
                    {
/* 784*/                if(c != '+')
/* 785*/                    stringbuilder.append(c);
/* 787*/                else
/* 787*/                    stringbuilder.append(' ');
                    } else
                    {
/* 790*/                bytebuffer = decodePercentEncodedOctets(s, j, bytebuffer);
/* 791*/                j = decodeOctets(j, bytebuffer, stringbuilder);
                    }

/* 795*/        return stringbuilder.toString();
            }

            private static String decodeHost(String s, int i)
            {
/* 799*/        StringBuilder stringbuilder = new StringBuilder(i);
/* 800*/        ByteBuffer bytebuffer = null;
/* 802*/        boolean flag = false;
/* 803*/        for(int j = 0; j < i;)
                {
                    char c;
/* 804*/            if((c = s.charAt(j++)) == '[')
/* 806*/                flag = true;
/* 807*/            else
/* 807*/            if(flag && c == ']')
/* 808*/                flag = false;
/* 811*/            if(c != '%' || flag)
                    {
/* 812*/                stringbuilder.append(c);
                    } else
                    {
/* 814*/                bytebuffer = decodePercentEncodedOctets(s, j, bytebuffer);
/* 815*/                j = decodeOctets(j, bytebuffer, stringbuilder);
                    }
                }

/* 819*/        return stringbuilder.toString();
            }

            private static ByteBuffer decodePercentEncodedOctets(String s, int i, ByteBuffer bytebuffer)
            {
/* 829*/        if(bytebuffer == null)
/* 830*/            bytebuffer = ByteBuffer.allocate(1);
/* 832*/        else
/* 832*/            bytebuffer.clear();
/* 837*/        do
                {
/* 837*/            bytebuffer.put((byte)(decodeHex(s, i++) << 4 | decodeHex(s, i++)));
/* 840*/            if(i == s.length() || s.charAt(i++) != '%')
/* 850*/                break;
/* 850*/            if(bytebuffer.position() == bytebuffer.capacity())
                    {
/* 851*/                bytebuffer.flip();
                        ByteBuffer bytebuffer1;
/* 854*/                (bytebuffer1 = ByteBuffer.allocate(s.length() / 3)).put(bytebuffer);
/* 856*/                bytebuffer = bytebuffer1;
                    }
                } while(true);
/* 860*/        bytebuffer.flip();
/* 861*/        return bytebuffer;
            }

            private static int decodeOctets(int i, ByteBuffer bytebuffer, StringBuilder stringbuilder)
            {
/* 872*/        if(bytebuffer.limit() == 1 && (bytebuffer.get(0) & 0xff) < 128)
                {
/* 874*/            stringbuilder.append((char)bytebuffer.get(0));
/* 875*/            return i + 2;
                } else
                {
/* 878*/            CharBuffer charbuffer = UTF_8_CHARSET.decode(bytebuffer);
/* 879*/            stringbuilder.append(charbuffer.toString());
/* 880*/            return (i + bytebuffer.limit() * 3) - 1;
                }
            }

            private static int decodeHex(String s, int i)
            {
                int j;
/* 885*/        if((j = decodeHex(s.charAt(i))) == -1)
/* 887*/            throw new IllegalArgumentException(LocalizationMessages.URI_COMPONENT_ENCODED_OCTET_INVALID_DIGIT(Integer.valueOf(i), Character.valueOf(s.charAt(i))));
/* 889*/        else
/* 889*/            return j;
            }

            private static int[] initHexTable()
            {
                int ai[];
/* 895*/        Arrays.fill(ai = new int[128], -1);
/* 898*/        for(char c = '0'; c <= 57; c++)
/* 899*/            ai[c] = c - 48;

/* 901*/        for(char c1 = 'A'; c1 <= 70; c1++)
/* 902*/            ai[c1] = (c1 - 65) + 10;

/* 904*/        for(char c2 = 'a'; c2 <= 102; c2++)
/* 905*/            ai[c2] = (c2 - 97) + 10;

/* 907*/        return ai;
            }

            private static int decodeHex(char c)
            {
/* 911*/        if(c < '\200')
/* 911*/            return HEX_TABLE[c];
/* 911*/        else
/* 911*/            return -1;
            }

            public static boolean isHexCharacter(char c)
            {
/* 921*/        return c < '\200' && HEX_TABLE[c] != -1;
            }

            public static String fullRelativeUri(URI uri)
            {
/* 932*/        if(uri == null)
                {
/* 933*/            return null;
                } else
                {
/* 936*/            String s = uri.getRawQuery();
/* 938*/            return (new StringBuilder()).append(uri.getRawPath()).append(s == null || s.length() <= 0 ? "" : (new StringBuilder("?")).append(s).toString()).toString();
                }
            }

            private static final char HEX_DIGITS[] = {
/* 346*/        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
/* 346*/        'A', 'B', 'C', 'D', 'E', 'F'
            };
            private static final String SCHEME[] = {
/* 365*/        "0-9", "A-Z", "a-z", "+", "-", "."
            };
            private static final String UNRESERVED[] = {
/* 366*/        "0-9", "A-Z", "a-z", "-", ".", "_", "~"
            };
            private static final String SUB_DELIMS[] = {
/* 367*/        "!", "$", "&", "'", "(", ")", "*", "+", ",", ";", 
/* 367*/        "="
            };
            private static final boolean ENCODING_TABLES[][] = initEncodingTables();
            private static final Charset UTF_8_CHARSET = Charset.forName("UTF-8");
            private static final int HEX_TABLE[] = initHexTable();

}
