// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyUriBuilder.java

package org.glassfish.jersey.uri.internal;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.AccessController;
import java.util.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.*;
import jersey.repackaged.com.google.common.collect.Maps;
import jersey.repackaged.com.google.common.net.InetAddresses;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import org.glassfish.jersey.uri.UriComponent;
import org.glassfish.jersey.uri.UriTemplate;

// Referenced classes of package org.glassfish.jersey.uri.internal:
//            UriParser

public class JerseyUriBuilder extends UriBuilder
{

            public JerseyUriBuilder()
            {
/* 101*/        path = new StringBuilder();
/* 102*/        query = new StringBuilder();
            }

            private JerseyUriBuilder(JerseyUriBuilder jerseyuribuilder)
            {
/* 106*/        scheme = jerseyuribuilder.scheme;
/* 107*/        ssp = jerseyuribuilder.ssp;
/* 108*/        authority = jerseyuribuilder.authority;
/* 109*/        userInfo = jerseyuribuilder.userInfo;
/* 110*/        host = jerseyuribuilder.host;
/* 111*/        port = jerseyuribuilder.port;
/* 112*/        path = new StringBuilder(jerseyuribuilder.path);
/* 113*/        matrixParams = jerseyuribuilder.matrixParams != null ? ((MultivaluedMap) (new MultivaluedStringMap(jerseyuribuilder.matrixParams))) : null;
/* 114*/        query = new StringBuilder(jerseyuribuilder.query);
/* 115*/        queryParams = jerseyuribuilder.queryParams != null ? ((MultivaluedMap) (new MultivaluedStringMap(jerseyuribuilder.queryParams))) : null;
/* 116*/        fragment = jerseyuribuilder.fragment;
            }

            public JerseyUriBuilder clone()
            {
/* 122*/        return new JerseyUriBuilder(this);
            }

            public JerseyUriBuilder uri(URI uri1)
            {
/* 127*/        if(uri1 == null)
/* 128*/            throw new IllegalArgumentException(LocalizationMessages.PARAM_NULL("uri"));
/* 131*/        if(uri1.getRawFragment() != null)
/* 132*/            fragment = uri1.getRawFragment();
/* 135*/        if(uri1.isOpaque())
                {
/* 136*/            scheme = uri1.getScheme();
/* 137*/            ssp = uri1.getRawSchemeSpecificPart();
/* 138*/            return this;
                }
/* 141*/        if(uri1.getScheme() == null)
                {
/* 142*/            if(ssp != null && uri1.getRawSchemeSpecificPart() != null)
                    {
/* 144*/                ssp = uri1.getRawSchemeSpecificPart();
/* 145*/                return this;
                    }
                } else
                {
/* 149*/            scheme = uri1.getScheme();
                }
/* 152*/        ssp = null;
/* 153*/        if(uri1.getRawAuthority() != null)
/* 154*/            if(uri1.getRawUserInfo() == null && uri1.getHost() == null && uri1.getPort() == -1)
                    {
/* 155*/                authority = uri1.getRawAuthority();
/* 156*/                userInfo = null;
/* 157*/                host = null;
/* 158*/                port = null;
                    } else
                    {
/* 160*/                authority = null;
/* 161*/                if(uri1.getRawUserInfo() != null)
/* 162*/                    userInfo = uri1.getRawUserInfo();
/* 164*/                if(uri1.getHost() != null)
/* 165*/                    host = uri1.getHost();
/* 167*/                if(uri1.getPort() != -1)
/* 168*/                    port = String.valueOf(uri1.getPort());
                    }
/* 173*/        if(uri1.getRawPath() != null && !uri1.getRawPath().isEmpty())
                {
/* 174*/            path.setLength(0);
/* 175*/            path.append(uri1.getRawPath());
                }
/* 177*/        if(uri1.getRawQuery() != null && !uri1.getRawQuery().isEmpty())
                {
/* 178*/            query.setLength(0);
/* 179*/            query.append(uri1.getRawQuery());
                }
/* 183*/        return this;
            }

            public JerseyUriBuilder uri(String s)
            {
/* 188*/        if(s == null)
/* 189*/            throw new IllegalArgumentException(LocalizationMessages.PARAM_NULL("uriTemplate"));
                UriParser uriparser;
/* 192*/        (uriparser = new UriParser(s)).parse();
                String s1;
/* 195*/        if((s1 = uriparser.getScheme()) != null)
/* 197*/            scheme(s1);
/* 198*/        else
/* 198*/        if(ssp != null)
                {
/* 206*/            ssp = null;
/* 207*/            (uriparser = new UriParser((new StringBuilder()).append(scheme).append(":").append(s).toString())).parse();
                }
/* 211*/        schemeSpecificPart(uriparser);
/* 213*/        if((s = uriparser.getFragment()) != null)
/* 215*/            fragment(s);
/* 218*/        return this;
            }

            private void schemeSpecificPart(UriParser uriparser)
            {
/* 227*/        if(uriparser.isOpaque())
                {
/* 228*/            if(uriparser.getSsp() != null)
                    {
/* 229*/                authority = host = port = null;
/* 230*/                path.setLength(0);
/* 231*/                query.setLength(0);
/* 234*/                ssp = uriparser.getSsp();
                    }
/* 236*/            return;
                }
/* 239*/        ssp = null;
/* 240*/        if(uriparser.getAuthority() != null)
/* 241*/            if(uriparser.getUserInfo() == null && uriparser.getHost() == null && uriparser.getPort() == null)
                    {
/* 242*/                authority = encode(uriparser.getAuthority(), org.glassfish.jersey.uri.UriComponent.Type.AUTHORITY);
/* 243*/                userInfo = null;
/* 244*/                host = null;
/* 245*/                port = null;
                    } else
                    {
/* 247*/                authority = null;
/* 248*/                if(uriparser.getUserInfo() != null)
/* 249*/                    userInfo(uriparser.getUserInfo());
/* 251*/                if(uriparser.getHost() != null)
/* 252*/                    host(uriparser.getHost());
/* 254*/                if(uriparser.getPort() != null)
/* 255*/                    port = uriparser.getPort();
                    }
/* 260*/        if(uriparser.getPath() != null)
                {
/* 261*/            path.setLength(0);
/* 262*/            path(uriparser.getPath());
                }
/* 264*/        if(uriparser.getQuery() != null)
                {
/* 265*/            query.setLength(0);
/* 266*/            query.append(uriparser.getQuery());
                }
            }

            public JerseyUriBuilder scheme(String s)
            {
/* 272*/        if(s != null)
                {
/* 273*/            scheme = s;
/* 274*/            UriComponent.validate(s, org.glassfish.jersey.uri.UriComponent.Type.SCHEME, true);
                } else
                {
/* 276*/            scheme = null;
                }
/* 278*/        return this;
            }

            public JerseyUriBuilder schemeSpecificPart(String s)
            {
/* 283*/        if(s == null)
/* 284*/            throw new IllegalArgumentException(LocalizationMessages.URI_BUILDER_SCHEME_PART_NULL());
                UriParser uriparser;
/* 287*/        (uriparser = new UriParser(scheme == null ? s : (new StringBuilder()).append(scheme).append(":").append(s).toString())).parse();
/* 290*/        if(uriparser.getScheme() != null && !uriparser.getScheme().equals(scheme))
/* 291*/            throw new IllegalStateException(LocalizationMessages.URI_BUILDER_SCHEME_PART_UNEXPECTED_COMPONENT(s, uriparser.getScheme()));
/* 294*/        if(uriparser.getFragment() != null)
                {
/* 295*/            throw new IllegalStateException(LocalizationMessages.URI_BUILDER_URI_PART_FRAGMENT(s, uriparser.getFragment()));
                } else
                {
/* 299*/            schemeSpecificPart(uriparser);
/* 301*/            return this;
                }
            }

            public JerseyUriBuilder userInfo(String s)
            {
/* 306*/        checkSsp();
/* 307*/        userInfo = s == null ? null : encode(s, org.glassfish.jersey.uri.UriComponent.Type.USER_INFO);
/* 309*/        return this;
            }

            public JerseyUriBuilder host(String s)
            {
/* 314*/        checkSsp();
/* 315*/        if(s != null)
                {
/* 316*/            if(s.isEmpty())
/* 317*/                throw new IllegalArgumentException(LocalizationMessages.INVALID_HOST());
/* 319*/            if(InetAddresses.isMappedIPv4Address(s) || InetAddresses.isUriInetAddress(s))
/* 320*/                host = s;
/* 322*/            else
/* 322*/                host = encode(s, org.glassfish.jersey.uri.UriComponent.Type.HOST);
                } else
                {
/* 326*/            host = null;
                }
/* 328*/        return this;
            }

            public JerseyUriBuilder port(int i)
            {
/* 333*/        checkSsp();
/* 334*/        if(i < -1)
                {
/* 338*/            throw new IllegalArgumentException(LocalizationMessages.INVALID_PORT());
                } else
                {
/* 340*/            port = i != -1 ? String.valueOf(i) : null;
/* 341*/            return this;
                }
            }

            public JerseyUriBuilder replacePath(String s)
            {
/* 346*/        checkSsp();
/* 347*/        path.setLength(0);
/* 348*/        if(s != null)
/* 349*/            appendPath(s);
/* 351*/        return this;
            }

            public JerseyUriBuilder path(String s)
            {
/* 356*/        checkSsp();
/* 357*/        appendPath(s);
/* 358*/        return this;
            }

            public UriBuilder path(Class class1)
                throws IllegalArgumentException
            {
/* 364*/        checkSsp();
/* 365*/        if(class1 == null)
/* 366*/            throw new IllegalArgumentException(LocalizationMessages.PARAM_NULL("resource"));
                Path path1;
/* 369*/        if((path1 = (Path)javax/ws/rs/Path.cast(class1.getAnnotation(javax/ws/rs/Path))) == null)
                {
/* 371*/            throw new IllegalArgumentException(LocalizationMessages.URI_BUILDER_CLASS_PATH_ANNOTATION_MISSING(class1));
                } else
                {
/* 373*/            appendPath(path1);
/* 374*/            return this;
                }
            }

            public JerseyUriBuilder path(Class class1, String s)
            {
/* 379*/        checkSsp();
/* 380*/        if(class1 == null)
/* 381*/            throw new IllegalArgumentException(LocalizationMessages.PARAM_NULL("resource"));
/* 383*/        if(s == null)
/* 384*/            throw new IllegalArgumentException(LocalizationMessages.PARAM_NULL("methodName"));
/* 387*/        Method amethod[] = (Method[])AccessController.doPrivileged(ReflectionHelper.getMethodsPA(class1));
/* 388*/        AnnotatedElement annotatedelement = null;
/* 389*/        int i = (amethod = amethod).length;
/* 389*/        for(int j = 0; j < i; j++)
                {
/* 389*/            Method method = amethod[j];
/* 390*/            if(!s.equals(method.getName()))
/* 391*/                continue;
/* 391*/            if(annotatedelement == null || annotatedelement.isSynthetic())
                    {
/* 392*/                annotatedelement = method;
/* 392*/                continue;
                    }
/* 393*/            if(!method.isSynthetic())
/* 394*/                throw new IllegalArgumentException();
                }

/* 399*/        if(annotatedelement == null)
                {
/* 400*/            throw new IllegalArgumentException(LocalizationMessages.URI_BUILDER_METHODNAME_NOT_SPECIFIED(s, class1));
                } else
                {
/* 403*/            appendPath(getPath(annotatedelement));
/* 405*/            return this;
                }
            }

            public JerseyUriBuilder path(Method method)
            {
/* 410*/        checkSsp();
/* 411*/        if(method == null)
                {
/* 412*/            throw new IllegalArgumentException(LocalizationMessages.PARAM_NULL("method"));
                } else
                {
/* 414*/            appendPath(getPath(method));
/* 415*/            return this;
                }
            }

            private Path getPath(AnnotatedElement annotatedelement)
            {
                Path path1;
/* 419*/        if((path1 = (Path)annotatedelement.getAnnotation(javax/ws/rs/Path)) == null)
/* 421*/            throw new IllegalArgumentException(LocalizationMessages.URI_BUILDER_ANNOTATEDELEMENT_PATH_ANNOTATION_MISSING(annotatedelement));
/* 423*/        else
/* 423*/            return path1;
            }

            public transient JerseyUriBuilder segment(String as[])
                throws IllegalArgumentException
            {
/* 428*/        checkSsp();
/* 429*/        if(as == null)
/* 430*/            throw new IllegalArgumentException(LocalizationMessages.PARAM_NULL("segments"));
/* 433*/        int i = (as = as).length;
/* 433*/        for(int j = 0; j < i; j++)
                {
/* 433*/            String s = as[j];
/* 434*/            appendPath(s, true);
                }

/* 436*/        return this;
            }

            public JerseyUriBuilder replaceMatrix(String s)
            {
/* 441*/        checkSsp();
                boolean flag;
/* 442*/        int i = (flag = path.charAt(path.length() - 1) == '/') ? path.lastIndexOf("/", path.length() - 2) : path.lastIndexOf("/");
/* 445*/        if((i = path.indexOf(";", i)) != -1)
/* 448*/            path.setLength(i + 1);
/* 449*/        else
/* 449*/        if(s != null)
/* 450*/            path.append(';');
/* 453*/        if(s != null)
/* 454*/            path.append(encode(s, org.glassfish.jersey.uri.UriComponent.Type.PATH));
/* 455*/        else
/* 455*/        if(i != -1)
                {
/* 456*/            path.setLength(i);
/* 458*/            if(flag)
/* 459*/                path.append("/");
                }
/* 463*/        return this;
            }

            public transient JerseyUriBuilder matrixParam(String s, Object aobj[])
            {
/* 468*/        checkSsp();
/* 469*/        if(s == null)
/* 470*/            throw new IllegalArgumentException(LocalizationMessages.PARAM_NULL("name"));
/* 472*/        if(aobj == null)
/* 473*/            throw new IllegalArgumentException(LocalizationMessages.PARAM_NULL("value"));
/* 475*/        if(aobj.length == 0)
/* 476*/            return this;
/* 479*/        s = encode(s, org.glassfish.jersey.uri.UriComponent.Type.MATRIX_PARAM);
/* 480*/        if(matrixParams == null)
                {
/* 481*/            int i = (aobj = aobj).length;
/* 481*/            for(int k = 0; k < i; k++)
                    {
/* 481*/                Object obj = aobj[k];
/* 482*/                path.append(';').append(s);
/* 484*/                if(obj == null)
/* 485*/                    throw new IllegalArgumentException(LocalizationMessages.MATRIX_PARAM_NULL());
/* 488*/                if(!((String) (obj = obj.toString())).isEmpty())
/* 490*/                    path.append('=').append(encode(((String) (obj)), org.glassfish.jersey.uri.UriComponent.Type.MATRIX_PARAM));
                    }

                } else
                {
/* 494*/            int j = (aobj = aobj).length;
/* 494*/            for(int l = 0; l < j; l++)
                    {
                        Object obj1;
/* 494*/                if((obj1 = aobj[l]) == null)
/* 496*/                    throw new IllegalArgumentException(LocalizationMessages.MATRIX_PARAM_NULL());
/* 499*/                matrixParams.add(s, encode(obj1.toString(), org.glassfish.jersey.uri.UriComponent.Type.MATRIX_PARAM));
                    }

                }
/* 502*/        return this;
            }

            public transient JerseyUriBuilder replaceMatrixParam(String s, Object aobj[])
            {
/* 507*/        checkSsp();
/* 509*/        if(s == null)
/* 510*/            throw new IllegalArgumentException(LocalizationMessages.PARAM_NULL("name"));
/* 513*/        if(matrixParams == null)
                {
                    int i;
/* 514*/            if((i = path.lastIndexOf("/")) == -1)
/* 516*/                i = 0;
/* 518*/            matrixParams = UriComponent.decodeMatrix(path.substring(i), false);
/* 519*/            if((i = path.indexOf(";", i)) != -1)
/* 521*/                path.setLength(i);
                }
/* 525*/        s = encode(s, org.glassfish.jersey.uri.UriComponent.Type.MATRIX_PARAM);
/* 526*/        matrixParams.remove(s);
/* 527*/        if(aobj != null)
                {
                    Object aobj1[];
/* 528*/            aobj = (aobj1 = aobj).length;
/* 528*/            for(int j = 0; j < aobj; j++)
                    {
                        Object obj;
/* 528*/                if((obj = aobj1[j]) == null)
/* 530*/                    throw new IllegalArgumentException(LocalizationMessages.MATRIX_PARAM_NULL());
/* 533*/                matrixParams.add(s, encode(obj.toString(), org.glassfish.jersey.uri.UriComponent.Type.MATRIX_PARAM));
                    }

                }
/* 536*/        return this;
            }

            public JerseyUriBuilder replaceQuery(String s)
            {
/* 541*/        checkSsp();
/* 542*/        query.setLength(0);
/* 543*/        if(s != null)
/* 544*/            query.append(encode(s, org.glassfish.jersey.uri.UriComponent.Type.QUERY));
/* 546*/        return this;
            }

            public transient JerseyUriBuilder queryParam(String s, Object aobj[])
            {
/* 551*/        checkSsp();
/* 552*/        if(s == null)
/* 553*/            throw new IllegalArgumentException(LocalizationMessages.PARAM_NULL("name"));
/* 555*/        if(aobj == null)
/* 556*/            throw new IllegalArgumentException(LocalizationMessages.PARAM_NULL("values"));
/* 558*/        if(aobj.length == 0)
/* 559*/            return this;
/* 562*/        s = encode(s, org.glassfish.jersey.uri.UriComponent.Type.QUERY_PARAM);
/* 563*/        if(queryParams == null)
                {
/* 564*/            int i = (aobj = aobj).length;
/* 564*/            for(int k = 0; k < i; k++)
                    {
/* 564*/                Object obj = aobj[k];
/* 565*/                if(query.length() > 0)
/* 566*/                    query.append('&');
/* 568*/                query.append(s);
/* 570*/                if(obj == null)
/* 571*/                    throw new IllegalArgumentException(LocalizationMessages.QUERY_PARAM_NULL());
/* 574*/                query.append('=').append(encode(obj.toString(), org.glassfish.jersey.uri.UriComponent.Type.QUERY_PARAM));
                    }

                } else
                {
/* 577*/            int j = (aobj = aobj).length;
/* 577*/            for(int l = 0; l < j; l++)
                    {
                        Object obj1;
/* 577*/                if((obj1 = aobj[l]) == null)
/* 579*/                    throw new IllegalArgumentException(LocalizationMessages.QUERY_PARAM_NULL());
/* 582*/                queryParams.add(s, encode(obj1.toString(), org.glassfish.jersey.uri.UriComponent.Type.QUERY_PARAM));
                    }

                }
/* 585*/        return this;
            }

            public transient JerseyUriBuilder replaceQueryParam(String s, Object aobj[])
            {
/* 590*/        checkSsp();
/* 592*/        if(queryParams == null)
                {
/* 593*/            queryParams = UriComponent.decodeQuery(query.toString(), false, false);
/* 594*/            query.setLength(0);
                }
/* 597*/        s = encode(s, org.glassfish.jersey.uri.UriComponent.Type.QUERY_PARAM);
/* 598*/        queryParams.remove(s);
/* 600*/        if(aobj == null)
/* 601*/            return this;
/* 604*/        int i = (aobj = aobj).length;
/* 604*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/* 604*/            if((obj = aobj[j]) == null)
/* 606*/                throw new IllegalArgumentException(LocalizationMessages.QUERY_PARAM_NULL());
/* 609*/            queryParams.add(s, encode(obj.toString(), org.glassfish.jersey.uri.UriComponent.Type.QUERY_PARAM));
                }

/* 611*/        return this;
            }

            public JerseyUriBuilder resolveTemplate(String s, Object obj)
                throws IllegalArgumentException
            {
/* 616*/        resolveTemplate(s, obj, true, true);
/* 618*/        return this;
            }

            public JerseyUriBuilder resolveTemplate(String s, Object obj, boolean flag)
            {
/* 623*/        resolveTemplate(s, obj, true, flag);
/* 624*/        return this;
            }

            public JerseyUriBuilder resolveTemplateFromEncoded(String s, Object obj)
            {
/* 629*/        resolveTemplate(s, obj, false, false);
/* 630*/        return this;
            }

            private JerseyUriBuilder resolveTemplate(String s, Object obj, boolean flag, boolean flag1)
            {
/* 637*/        if(s == null)
/* 638*/            throw new IllegalArgumentException(LocalizationMessages.PARAM_NULL("name"));
/* 640*/        if(obj == null)
                {
/* 641*/            throw new IllegalArgumentException(LocalizationMessages.PARAM_NULL("value"));
                } else
                {
                    java.util.HashMap hashmap;
/* 644*/            (hashmap = Maps.newHashMap()).put(s, obj);
/* 646*/            resolveTemplates(hashmap, flag, flag1);
/* 647*/            return this;
                }
            }

            public JerseyUriBuilder resolveTemplates(Map map)
                throws IllegalArgumentException
            {
/* 652*/        resolveTemplates(map, true, true);
/* 653*/        return this;
            }

            public JerseyUriBuilder resolveTemplates(Map map, boolean flag)
                throws IllegalArgumentException
            {
/* 659*/        resolveTemplates(map, true, flag);
/* 660*/        return this;
            }

            public JerseyUriBuilder resolveTemplatesFromEncoded(Map map)
            {
/* 665*/        resolveTemplates(map, false, false);
/* 666*/        return this;
            }

            private JerseyUriBuilder resolveTemplates(Map map, boolean flag, boolean flag1)
            {
/* 672*/        if(map == null)
/* 673*/            throw new IllegalArgumentException(LocalizationMessages.PARAM_NULL("templateValues"));
                java.util.Map.Entry entry;
/* 675*/        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
/* 675*/            if((entry = (java.util.Map.Entry)iterator.next()).getKey() == null || entry.getValue() == null)
/* 677*/                throw new IllegalArgumentException(LocalizationMessages.TEMPLATE_PARAM_NULL());

/* 682*/        scheme = UriTemplate.resolveTemplateValues(org.glassfish.jersey.uri.UriComponent.Type.SCHEME, scheme, false, map);
/* 683*/        userInfo = UriTemplate.resolveTemplateValues(org.glassfish.jersey.uri.UriComponent.Type.USER_INFO, userInfo, flag, map);
/* 684*/        host = UriTemplate.resolveTemplateValues(org.glassfish.jersey.uri.UriComponent.Type.HOST, host, flag, map);
/* 685*/        port = UriTemplate.resolveTemplateValues(org.glassfish.jersey.uri.UriComponent.Type.PORT, port, false, map);
/* 686*/        authority = UriTemplate.resolveTemplateValues(org.glassfish.jersey.uri.UriComponent.Type.AUTHORITY, authority, flag, map);
                org.glassfish.jersey.uri.UriComponent.Type type;
/* 689*/        String s = UriTemplate.resolveTemplateValues(type = flag1 ? org.glassfish.jersey.uri.UriComponent.Type.PATH_SEGMENT : org.glassfish.jersey.uri.UriComponent.Type.PATH, path.toString(), flag, map);
/* 691*/        path.setLength(0);
/* 692*/        path.append(s);
/* 694*/        flag1 = UriTemplate.resolveTemplateValues(org.glassfish.jersey.uri.UriComponent.Type.QUERY_PARAM, query.toString(), flag, map);
/* 696*/        query.setLength(0);
/* 697*/        query.append(flag1);
/* 699*/        fragment = UriTemplate.resolveTemplateValues(org.glassfish.jersey.uri.UriComponent.Type.FRAGMENT, fragment, flag, map);
/* 701*/        return this;
            }

            public JerseyUriBuilder fragment(String s)
            {
/* 706*/        fragment = s == null ? null : encode(s, org.glassfish.jersey.uri.UriComponent.Type.FRAGMENT);
/* 709*/        return this;
            }

            private void checkSsp()
            {
/* 713*/        if(ssp != null)
/* 714*/            throw new IllegalArgumentException(LocalizationMessages.URI_BUILDER_SCHEMA_PART_OPAQUE());
/* 716*/        else
/* 716*/            return;
            }

            private void appendPath(Path path1)
            {
/* 719*/        if(path1 == null)
                {
/* 720*/            throw new IllegalArgumentException(LocalizationMessages.PARAM_NULL("path"));
                } else
                {
/* 723*/            appendPath(path1.value());
/* 724*/            return;
                }
            }

            private void appendPath(String s)
            {
/* 727*/        appendPath(s, false);
            }

            private void appendPath(String s, boolean flag)
            {
/* 731*/        if(s == null)
/* 732*/            throw new IllegalArgumentException(LocalizationMessages.PARAM_NULL("segments"));
/* 734*/        if(s.isEmpty())
/* 735*/            return;
/* 739*/        encodeMatrix();
/* 741*/        s = encode(s, flag ? org.glassfish.jersey.uri.UriComponent.Type.PATH_SEGMENT : org.glassfish.jersey.uri.UriComponent.Type.PATH);
/* 744*/        flag = path.length() > 0 && path.charAt(path.length() - 1) == '/';
/* 745*/        boolean flag1 = s.charAt(0) == '/';
/* 747*/        if(path.length() > 0 && !flag && !flag1)
/* 748*/            path.append('/');
/* 749*/        else
/* 749*/        if(flag && flag1 && (s = s.substring(1)).isEmpty())
/* 752*/            return;
/* 756*/        path.append(s);
            }

            private void encodeMatrix()
            {
/* 760*/        if(matrixParams == null || matrixParams.isEmpty())
/* 761*/            return;
/* 764*/        for(Iterator iterator = matrixParams.entrySet().iterator(); iterator.hasNext();)
                {
                    Object obj;
/* 764*/            String s = (String)((java.util.Map.Entry) (obj = (java.util.Map.Entry)iterator.next())).getKey();
/* 767*/            obj = ((List)((java.util.Map.Entry) (obj)).getValue()).iterator();
/* 767*/            while(((Iterator) (obj)).hasNext()) 
                    {
/* 767*/                String s1 = (String)((Iterator) (obj)).next();
/* 768*/                path.append(';').append(s);
/* 769*/                if(!s1.isEmpty())
/* 770*/                    path.append('=').append(s1);
                    }
                }

/* 774*/        matrixParams = null;
            }

            private void encodeQuery()
            {
/* 778*/        if(queryParams == null || queryParams.isEmpty())
/* 779*/            return;
/* 782*/        for(Iterator iterator = queryParams.entrySet().iterator(); iterator.hasNext();)
                {
                    Object obj;
/* 782*/            String s = (String)((java.util.Map.Entry) (obj = (java.util.Map.Entry)iterator.next())).getKey();
/* 785*/            obj = ((List)((java.util.Map.Entry) (obj)).getValue()).iterator();
/* 785*/            while(((Iterator) (obj)).hasNext()) 
                    {
/* 785*/                String s1 = (String)((Iterator) (obj)).next();
/* 786*/                if(query.length() > 0)
/* 787*/                    query.append('&');
/* 789*/                query.append(s).append('=').append(s1);
                    }
                }

/* 792*/        queryParams = null;
            }

            private String encode(String s, org.glassfish.jersey.uri.UriComponent.Type type)
            {
/* 796*/        return UriComponent.contextualEncode(s, type, true);
            }

            public URI buildFromMap(Map map)
            {
/* 801*/        return _buildFromMap(true, true, map);
            }

            public URI buildFromMap(Map map, boolean flag)
            {
/* 806*/        return _buildFromMap(true, flag, map);
            }

            public URI buildFromEncodedMap(Map map)
                throws IllegalArgumentException, UriBuilderException
            {
/* 811*/        return _buildFromMap(false, false, map);
            }

            private URI _buildFromMap(boolean flag, boolean flag1, Map map)
            {
/* 815*/        if(ssp != null)
                {
/* 816*/            throw new IllegalArgumentException(LocalizationMessages.URI_BUILDER_SCHEMA_PART_OPAQUE());
                } else
                {
/* 819*/            encodeMatrix();
/* 820*/            encodeQuery();
/* 822*/            flag = UriTemplate.createURI(scheme, authority, userInfo, host, port, path.toString(), query.toString(), fragment, map, flag, flag1);
/* 826*/            return createURI(flag);
                }
            }

            public transient URI build(Object aobj[])
            {
/* 831*/        return _build(true, true, aobj);
            }

            public URI build(Object aobj[], boolean flag)
            {
/* 836*/        return _build(true, flag, aobj);
            }

            public transient URI buildFromEncoded(Object aobj[])
            {
/* 841*/        return _build(false, false, aobj);
            }

            public String toTemplate()
            {
/* 846*/        encodeMatrix();
/* 847*/        encodeQuery();
/* 849*/        StringBuilder stringbuilder = new StringBuilder();
/* 851*/        if(scheme != null)
/* 852*/            stringbuilder.append(scheme).append(':');
/* 855*/        if(ssp != null)
                {
/* 856*/            stringbuilder.append(ssp);
                } else
                {
/* 858*/            boolean flag = false;
/* 859*/            if(userInfo != null || host != null || port != null)
                    {
/* 860*/                flag = true;
/* 861*/                stringbuilder.append("//");
/* 863*/                if(userInfo != null && !userInfo.isEmpty())
/* 864*/                    stringbuilder.append(userInfo).append('@');
/* 867*/                if(host != null)
/* 869*/                    stringbuilder.append(host);
/* 872*/                if(port != null)
/* 873*/                    stringbuilder.append(':').append(port);
                    } else
/* 875*/            if(authority != null)
                    {
/* 876*/                flag = true;
/* 877*/                stringbuilder.append("//").append(authority);
                    }
/* 880*/            if(path.length() > 0)
                    {
/* 881*/                if(flag && path.charAt(0) != '/')
/* 882*/                    stringbuilder.append("/");
/* 884*/                stringbuilder.append(path);
                    } else
/* 885*/            if(flag && (query.length() > 0 || fragment != null && !fragment.isEmpty()))
/* 888*/                stringbuilder.append("/");
/* 891*/            if(query.length() > 0)
/* 892*/                stringbuilder.append('?').append(query);
                }
/* 896*/        if(fragment != null && !fragment.isEmpty())
/* 897*/            stringbuilder.append('#').append(fragment);
/* 900*/        return stringbuilder.toString();
            }

            private transient URI _build(boolean flag, boolean flag1, Object aobj[])
            {
/* 904*/        if(ssp != null)
                {
/* 905*/            if(aobj == null || aobj.length == 0)
/* 906*/                return createURI(create());
/* 908*/            else
/* 908*/                throw new IllegalArgumentException(LocalizationMessages.URI_BUILDER_SCHEMA_PART_OPAQUE());
                } else
                {
/* 911*/            encodeMatrix();
/* 912*/            encodeQuery();
/* 914*/            flag = UriTemplate.createURI(scheme, authority, userInfo, host, port, path.toString(), query.toString(), fragment, aobj, flag, flag1);
/* 918*/            return createURI(flag);
                }
            }

            private String create()
            {
/* 922*/        return UriComponent.encodeTemplateNames(toTemplate());
            }

            private URI createURI(String s)
            {
/* 927*/        return new URI(s);
/* 928*/        s;
/* 929*/        throw new UriBuilderException(s);
            }

            public String toString()
            {
/* 935*/        return toTemplate();
            }

            public boolean isAbsolute()
            {
/* 947*/        return scheme != null;
            }

            public volatile UriBuilder resolveTemplatesFromEncoded(Map map)
            {
/*  72*/        return resolveTemplatesFromEncoded(map);
            }

            public volatile UriBuilder resolveTemplates(Map map, boolean flag)
                throws IllegalArgumentException
            {
/*  72*/        return resolveTemplates(map, flag);
            }

            public volatile UriBuilder resolveTemplates(Map map)
            {
/*  72*/        return resolveTemplates(map);
            }

            public volatile UriBuilder resolveTemplateFromEncoded(String s, Object obj)
            {
/*  72*/        return resolveTemplateFromEncoded(s, obj);
            }

            public volatile UriBuilder resolveTemplate(String s, Object obj, boolean flag)
            {
/*  72*/        return resolveTemplate(s, obj, flag);
            }

            public volatile UriBuilder resolveTemplate(String s, Object obj)
            {
/*  72*/        return resolveTemplate(s, obj);
            }

            public volatile UriBuilder fragment(String s)
            {
/*  72*/        return fragment(s);
            }

            public volatile UriBuilder replaceQueryParam(String s, Object aobj[])
            {
/*  72*/        return replaceQueryParam(s, aobj);
            }

            public volatile UriBuilder queryParam(String s, Object aobj[])
            {
/*  72*/        return queryParam(s, aobj);
            }

            public volatile UriBuilder replaceQuery(String s)
            {
/*  72*/        return replaceQuery(s);
            }

            public volatile UriBuilder replaceMatrixParam(String s, Object aobj[])
            {
/*  72*/        return replaceMatrixParam(s, aobj);
            }

            public volatile UriBuilder matrixParam(String s, Object aobj[])
            {
/*  72*/        return matrixParam(s, aobj);
            }

            public volatile UriBuilder replaceMatrix(String s)
            {
/*  72*/        return replaceMatrix(s);
            }

            public volatile UriBuilder segment(String as[])
            {
/*  72*/        return segment(as);
            }

            public volatile UriBuilder path(Method method)
            {
/*  72*/        return path(method);
            }

            public volatile UriBuilder path(Class class1, String s)
            {
/*  72*/        return path(class1, s);
            }

            public volatile UriBuilder path(String s)
            {
/*  72*/        return path(s);
            }

            public volatile UriBuilder replacePath(String s)
            {
/*  72*/        return replacePath(s);
            }

            public volatile UriBuilder port(int i)
            {
/*  72*/        return port(i);
            }

            public volatile UriBuilder host(String s)
            {
/*  72*/        return host(s);
            }

            public volatile UriBuilder userInfo(String s)
            {
/*  72*/        return userInfo(s);
            }

            public volatile UriBuilder schemeSpecificPart(String s)
            {
/*  72*/        return schemeSpecificPart(s);
            }

            public volatile UriBuilder scheme(String s)
            {
/*  72*/        return scheme(s);
            }

            public volatile UriBuilder uri(String s)
            {
/*  72*/        return uri(s);
            }

            public volatile UriBuilder uri(URI uri1)
            {
/*  72*/        return uri(uri1);
            }

            public volatile UriBuilder clone()
            {
/*  72*/        return clone();
            }

            public volatile Object clone()
                throws CloneNotSupportedException
            {
/*  72*/        return clone();
            }

            private String scheme;
            private String ssp;
            private String authority;
            private String userInfo;
            private String host;
            private String port;
            private final StringBuilder path;
            private MultivaluedMap matrixParams;
            private final StringBuilder query;
            private MultivaluedMap queryParams;
            private String fragment;
}
