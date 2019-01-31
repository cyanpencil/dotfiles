// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DigestAuthenticator.java

package org.glassfish.jersey.client.authentication;

import java.io.IOException;
import java.security.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.message.MessageUtils;
import org.glassfish.jersey.uri.UriComponent;

// Referenced classes of package org.glassfish.jersey.client.authentication:
//            HttpAuthenticationFilter, RequestAuthenticationException, ResponseAuthenticationException

final class DigestAuthenticator
{
    final class DigestScheme
    {

                public final int incrementCounter()
                {
/* 456*/            return ++nc;
                }

                public final String getNonce()
                {
/* 460*/            return nonce;
                }

                public final String getRealm()
                {
/* 464*/            return realm;
                }

                public final String getOpaque()
                {
/* 468*/            return opaque;
                }

                public final Algorithm getAlgorithm()
                {
/* 472*/            return algorithm;
                }

                public final QOP getQop()
                {
/* 476*/            return qop;
                }

                public final boolean isStale()
                {
/* 480*/            return stale;
                }

                public final int getNc()
                {
/* 484*/            return nc;
                }

                private final String realm;
                private final String nonce;
                private final String opaque;
                private final Algorithm algorithm;
                private final QOP qop;
                private final boolean stale;
                private volatile int nc;
                final DigestAuthenticator this$0;

                DigestScheme(String s, String s1, String s2, QOP qop1, Algorithm algorithm1, boolean flag)
                {
/* 445*/            this$0 = DigestAuthenticator.this;
/* 445*/            super();
/* 446*/            realm = s;
/* 447*/            nonce = s1;
/* 448*/            opaque = s2;
/* 449*/            qop = qop1;
/* 450*/            algorithm = algorithm1;
/* 451*/            stale = flag;
/* 452*/            nc = 0;
                }
    }

    static final class Algorithm extends Enum
    {

                public static Algorithm[] values()
                {
/* 403*/            return (Algorithm[])$VALUES.clone();
                }

                public static Algorithm valueOf(String s)
                {
/* 403*/            return (Algorithm)Enum.valueOf(org/glassfish/jersey/client/authentication/DigestAuthenticator$Algorithm, s);
                }

                public final String toString()
                {
/* 416*/            return md;
                }

                public static Algorithm parse(String s)
                {
/* 420*/            if(s == null || s.isEmpty())
/* 421*/                return UNSPECIFIED;
/* 423*/            if((s = s.trim()).contains(MD5_SESS.md) || s.contains(MD5_SESS.md.toLowerCase()))
/* 425*/                return MD5_SESS;
/* 427*/            else
/* 427*/                return MD5;
                }

                public static final Algorithm UNSPECIFIED;
                public static final Algorithm MD5;
                public static final Algorithm MD5_SESS;
                private final String md;
                private static final Algorithm $VALUES[];

                static 
                {
/* 405*/            UNSPECIFIED = new Algorithm("UNSPECIFIED", 0, null);
/* 406*/            MD5 = new Algorithm("MD5", 1, "MD5");
/* 407*/            MD5_SESS = new Algorithm("MD5_SESS", 2, "MD5-sess");
/* 403*/            $VALUES = (new Algorithm[] {
/* 403*/                UNSPECIFIED, MD5, MD5_SESS
                    });
                }

                private Algorithm(String s, int i, String s1)
                {
/* 410*/            super(s, i);
/* 411*/            md = s1;
                }
    }

    static final class QOP extends Enum
    {

                public static QOP[] values()
                {
/* 376*/            return (QOP[])$VALUES.clone();
                }

                public static QOP valueOf(String s)
                {
/* 376*/            return (QOP)Enum.valueOf(org/glassfish/jersey/client/authentication/DigestAuthenticator$QOP, s);
                }

                public final String toString()
                {
/* 389*/            return qop;
                }

                public static QOP parse(String s)
                {
/* 393*/            if(s == null || s.isEmpty())
/* 394*/                return UNSPECIFIED;
/* 396*/            if(s.contains("auth"))
/* 397*/                return AUTH;
/* 399*/            else
/* 399*/                throw new UnsupportedOperationException(LocalizationMessages.DIGEST_FILTER_QOP_UNSUPPORTED(s));
                }

                public static final QOP UNSPECIFIED;
                public static final QOP AUTH;
                private final String qop;
                private static final QOP $VALUES[];

                static 
                {
/* 378*/            UNSPECIFIED = new QOP("UNSPECIFIED", 0, null);
/* 379*/            AUTH = new QOP("AUTH", 1, "auth");
/* 376*/            $VALUES = (new QOP[] {
/* 376*/                UNSPECIFIED, AUTH
                    });
                }

                private QOP(String s, int i, String s1)
                {
/* 383*/            super(s, i);
/* 384*/            qop = s1;
                }
    }


            DigestAuthenticator(HttpAuthenticationFilter.Credentials credentials1, final int final_i)
            {
/*  90*/        credentials = credentials1;
/*  92*/        digestCache = Collections.synchronizedMap(new LinkedHashMap(final_i) {

                    protected boolean removeEldestEntry(java.util.Map.Entry entry)
                    {
/*  98*/                return size() > limit;
                    }

                    private static final long serialVersionUID = 0x97c49ff9L;
                    final int val$limit;
                    final DigestAuthenticator this$0;

                    
                    {
/*  92*/                this$0 = DigestAuthenticator.this;
/*  92*/                limit = j;
/*  92*/                super(final_i);
                    }
        });
/* 103*/        try
                {
/* 103*/            randomGenerator = SecureRandom.getInstance("SHA1PRNG");
/* 106*/            return;
                }
                // Misplaced declaration of an exception variable
/* 104*/        catch(HttpAuthenticationFilter.Credentials credentials1)
                {
/* 105*/            throw new RequestAuthenticationException(LocalizationMessages.ERROR_DIGEST_FILTER_GENERATOR(), credentials1);
                }
            }

            final boolean filterRequest(ClientRequestContext clientrequestcontext)
                throws IOException
            {
                DigestScheme digestscheme;
                HttpAuthenticationFilter.Credentials credentials1;
/* 117*/        if((digestscheme = (DigestScheme)digestCache.get(clientrequestcontext.getUri())) != null && (credentials1 = HttpAuthenticationFilter.getCredentials(clientrequestcontext, credentials, HttpAuthenticationFilter.Type.DIGEST)) != null)
                {
/* 122*/            clientrequestcontext.getHeaders().add("Authorization", createNextAuthToken(digestscheme, clientrequestcontext, credentials1));
/* 123*/            return true;
                } else
                {
/* 126*/            return false;
                }
            }

            public final boolean filterResponse(ClientRequestContext clientrequestcontext, ClientResponseContext clientresponsecontext)
                throws IOException
            {
/* 141*/        if(javax.ws.rs.core.Response.Status.fromStatusCode(clientresponsecontext.getStatus()) == javax.ws.rs.core.Response.Status.UNAUTHORIZED)
                {
                    DigestScheme digestscheme;
/* 143*/            if((digestscheme = parseAuthHeaders((List)clientresponsecontext.getHeaders().get("WWW-Authenticate"))) == null)
/* 145*/                return false;
                    HttpAuthenticationFilter.Credentials credentials1;
/* 149*/            if((credentials1 = HttpAuthenticationFilter.getCredentials(clientrequestcontext, credentials, HttpAuthenticationFilter.Type.DIGEST)) == null)
/* 153*/                throw new ResponseAuthenticationException(null, LocalizationMessages.AUTHENTICATION_CREDENTIALS_MISSING_DIGEST());
/* 156*/            if((clientresponsecontext = HttpAuthenticationFilter.repeatRequest(clientrequestcontext, clientresponsecontext, createNextAuthToken(digestscheme, clientrequestcontext, credentials1))) != 0)
/* 159*/                digestCache.put(clientrequestcontext.getUri(), digestscheme);
/* 161*/            else
/* 161*/                digestCache.remove(clientrequestcontext.getUri());
/* 163*/            return clientresponsecontext;
                } else
                {
/* 165*/            return true;
                }
            }

            private DigestScheme parseAuthHeaders(List list)
                throws IOException
            {
/* 176*/        if(list == null)
/* 177*/            return null;
                Object obj;
                String as[];
/* 179*/        for(list = list.iterator(); list.hasNext();)
/* 179*/            if(((obj = list.next()) instanceof String) && (as = ((String) (obj = (String)obj)).trim().split("\\s+", 2)).length == 2 && "digest".equals(as[0].toLowerCase()))
                    {
/* 194*/                list = null;
/* 195*/                String s = null;
/* 196*/                String s1 = null;
/* 197*/                QOP qop = QOP.UNSPECIFIED;
/* 198*/                Algorithm algorithm = Algorithm.UNSPECIFIED;
/* 199*/                boolean flag = false;
/* 201*/                Matcher matcher = KEY_VALUE_PAIR_PATTERN.matcher(as[1]);
/* 202*/                do
                        {
/* 202*/                    if(!matcher.find())
/* 204*/                        break;
                            int i;
/* 204*/                    if((i = matcher.groupCount()) == 4)
                            {
/* 208*/                        String s2 = matcher.group(1);
/* 209*/                        String s3 = matcher.group(3);
/* 210*/                        String s4 = matcher.group(4);
/* 211*/                        s3 = s3 != null ? s3 : s4;
/* 212*/                        if("qop".equals(s2))
/* 213*/                            qop = QOP.parse(s3);
/* 214*/                        else
/* 214*/                        if("realm".equals(s2))
/* 215*/                            list = s3;
/* 216*/                        else
/* 216*/                        if("nonce".equals(s2))
/* 217*/                            s = s3;
/* 218*/                        else
/* 218*/                        if("opaque".equals(s2))
/* 219*/                            s1 = s3;
/* 220*/                        else
/* 220*/                        if("stale".equals(s2))
/* 221*/                            flag = Boolean.parseBoolean(s3);
/* 222*/                        else
/* 222*/                        if("algorithm".equals(s2))
/* 223*/                            algorithm = Algorithm.parse(s3);
                            }
                        } while(true);
/* 226*/                return new DigestScheme(list, s, s1, qop, algorithm, flag);
                    }

/* 228*/        return null;
            }

            private String createNextAuthToken(DigestScheme digestscheme, ClientRequestContext clientrequestcontext, HttpAuthenticationFilter.Credentials credentials1)
                throws IOException
            {
                StringBuilder stringbuilder;
/* 241*/        (stringbuilder = new StringBuilder(100)).append("Digest ");
/* 243*/        append(stringbuilder, "username", credentials1.getUsername());
/* 244*/        append(stringbuilder, "realm", digestscheme.getRealm());
/* 245*/        append(stringbuilder, "nonce", digestscheme.getNonce());
/* 246*/        append(stringbuilder, "opaque", digestscheme.getOpaque());
/* 247*/        append(stringbuilder, "algorithm", digestscheme.getAlgorithm().toString(), false);
/* 248*/        append(stringbuilder, "qop", digestscheme.getQop().toString(), false);
/* 250*/        String s = UriComponent.fullRelativeUri(clientrequestcontext.getUri());
/* 251*/        append(stringbuilder, "uri", s);
/* 254*/        if(digestscheme.getAlgorithm() == Algorithm.MD5_SESS)
/* 255*/            credentials1 = md5(new String[] {
/* 255*/                md5(new String[] {
/* 255*/                    credentials1.getUsername(), digestscheme.getRealm(), new String(credentials1.getPassword(), MessageUtils.getCharset(clientrequestcontext.getMediaType()))
                        })
                    });
/* 258*/        else
/* 258*/            credentials1 = md5(new String[] {
/* 258*/                credentials1.getUsername(), digestscheme.getRealm(), new String(credentials1.getPassword(), MessageUtils.getCharset(clientrequestcontext.getMediaType()))
                    });
/* 262*/        clientrequestcontext = md5(new String[] {
/* 262*/            clientrequestcontext.getMethod(), s
                });
/* 265*/        if(digestscheme.getQop() == QOP.UNSPECIFIED)
                {
/* 266*/            digestscheme = md5(new String[] {
/* 266*/                credentials1, digestscheme.getNonce(), clientrequestcontext
                    });
                } else
                {
/* 268*/            String s1 = randomBytes(4);
/* 269*/            append(stringbuilder, "cnonce", s1);
/* 270*/            String s2 = String.format("%08x", new Object[] {
/* 270*/                Integer.valueOf(digestscheme.incrementCounter())
                    });
/* 271*/            append(stringbuilder, "nc", s2, false);
/* 272*/            digestscheme = md5(new String[] {
/* 272*/                credentials1, digestscheme.getNonce(), s2, s1, digestscheme.getQop().toString(), clientrequestcontext
                    });
                }
/* 274*/        append(stringbuilder, "response", digestscheme);
/* 276*/        return stringbuilder.toString();
            }

            private static void append(StringBuilder stringbuilder, String s, String s1, boolean flag)
            {
/* 289*/        if(s1 == null)
/* 290*/            return;
/* 292*/        if(stringbuilder.length() > 0 && stringbuilder.charAt(stringbuilder.length() - 1) != ' ')
/* 294*/            stringbuilder.append(',');
/* 297*/        stringbuilder.append(s);
/* 298*/        stringbuilder.append('=');
/* 299*/        if(flag)
/* 300*/            stringbuilder.append('"');
/* 302*/        stringbuilder.append(s1);
/* 303*/        if(flag)
/* 304*/            stringbuilder.append('"');
            }

            private static void append(StringBuilder stringbuilder, String s, String s1)
            {
/* 317*/        append(stringbuilder, s, s1, true);
            }

            private static String bytesToHex(byte abyte0[])
            {
/* 327*/        char ac[] = new char[abyte0.length << 1];
/* 329*/        for(int j = 0; j < abyte0.length; j++)
                {
/* 330*/            int i = abyte0[j] & 0xff;
/* 331*/            ac[j << 1] = HEX_ARRAY[i >>> 4];
/* 332*/            ac[(j << 1) + 1] = HEX_ARRAY[i & 0xf];
                }

/* 334*/        return new String(ac);
            }

            private static transient String md5(String as[])
                throws IOException
            {
/* 345*/        StringBuilder stringbuilder = new StringBuilder(100);
/* 346*/        int i = (as = as).length;
/* 346*/        for(int j = 0; j < i; j++)
                {
/* 346*/            String s = as[j];
/* 347*/            if(stringbuilder.length() > 0)
/* 348*/                stringbuilder.append(':');
/* 350*/            stringbuilder.append(s);
                }

/* 355*/        try
                {
/* 355*/            as = MessageDigest.getInstance("MD5");
                }
/* 356*/        catch(NoSuchAlgorithmException nosuchalgorithmexception)
                {
/* 357*/            throw new IOException(nosuchalgorithmexception.getMessage());
                }
/* 359*/        as.update(stringbuilder.toString().getBytes(HttpAuthenticationFilter.CHARACTER_SET), 0, stringbuilder.length());
                byte abyte0[];
/* 360*/        return bytesToHex(abyte0 = as.digest());
            }

            private String randomBytes(int i)
            {
/* 371*/        i = new byte[i];
/* 372*/        randomGenerator.nextBytes(i);
/* 373*/        return bytesToHex(i);
            }

            private static final char HEX_ARRAY[] = {
/*  73*/        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
/*  73*/        'a', 'b', 'c', 'd', 'e', 'f'
            };
            private static final Pattern KEY_VALUE_PAIR_PATTERN = Pattern.compile("(\\w+)\\s*=\\s*(\"([^\"]+)\"|(\\w+))\\s*,?\\s*");
            private static final int CLIENT_NONCE_BYTE_COUNT = 4;
            private final SecureRandom randomGenerator;
            private final HttpAuthenticationFilter.Credentials credentials;
            private final Map digestCache;

}
