// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MediaTypes.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import java.util.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import jersey.repackaged.com.google.common.base.Predicate;
import jersey.repackaged.com.google.common.collect.Maps;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AcceptableMediaType, HttpHeaderReader, QualitySourceMediaType

public final class MediaTypes
{

            private MediaTypes()
            {
/* 170*/        throw new AssertionError("Instantiation not allowed.");
            }

            public static boolean typeEqual(MediaType mediatype, MediaType mediatype1)
            {
/* 190*/        if(mediatype == null || mediatype1 == null)
/* 191*/            return false;
/* 194*/        return mediatype.getSubtype().equalsIgnoreCase(mediatype1.getSubtype()) && mediatype.getType().equalsIgnoreCase(mediatype1.getType());
            }

            public static boolean intersect(List list, List list1)
            {
/* 208*/        list = list.iterator();
/* 208*/label0:
/* 208*/        do
/* 208*/            if(list.hasNext())
                    {
/* 208*/                MediaType mediatype = (MediaType)list.next();
/* 209*/                Iterator iterator = list1.iterator();
                        MediaType mediatype1;
/* 209*/                do
                        {
/* 209*/                    if(!iterator.hasNext())
/* 209*/                        continue label0;
/* 209*/                    mediatype1 = (MediaType)iterator.next();
                        } while(!typeEqual(mediatype, mediatype1));
/* 211*/                break;
                    } else
                    {
/* 215*/                return false;
                    }
/* 210*/        while(true);
/* 211*/        return true;
            }

            public static MediaType mostSpecific(MediaType mediatype, MediaType mediatype1)
            {
/* 229*/        if(mediatype.isWildcardType() && !mediatype1.isWildcardType())
/* 230*/            return mediatype1;
/* 232*/        if(mediatype.isWildcardSubtype() && !mediatype1.isWildcardSubtype())
/* 233*/            return mediatype1;
/* 235*/        if(mediatype1.getParameters().size() > mediatype.getParameters().size())
/* 236*/            return mediatype1;
/* 239*/        else
/* 239*/            return mediatype;
            }

            public static List createFrom(Consumes consumes)
            {
/* 250*/        if(consumes == null)
/* 251*/            return WILDCARD_TYPE_SINGLETON_LIST;
/* 254*/        else
/* 254*/            return createFrom(consumes.value());
            }

            public static List createFrom(Produces produces)
            {
/* 265*/        if(produces == null)
/* 266*/            return WILDCARD_TYPE_SINGLETON_LIST;
/* 269*/        else
/* 269*/            return createFrom(produces.value());
            }

            public static List createFrom(String as[])
            {
/* 279*/        ArrayList arraylist = new ArrayList();
/* 282*/        try
                {
/* 282*/            int i = (as = as).length;
/* 282*/            for(int j = 0; j < i; j++)
                    {
/* 282*/                String s = as[j];
/* 283*/                HttpHeaderReader.readMediaTypes(arraylist, s);
                    }

                }
                // Misplaced declaration of an exception variable
/* 285*/        catch(String as[])
                {
/* 286*/            throw new IllegalArgumentException(as);
                }
/* 289*/        Collections.sort(arraylist, PARTIAL_ORDER_COMPARATOR);
/* 291*/        return Collections.unmodifiableList(arraylist);
            }

            public static List createQualitySourceMediaTypes(Produces produces)
            {
/* 303*/        if(produces == null || produces.value().length == 0)
/* 304*/            return WILDCARD_QS_TYPE_SINGLETON_LIST;
/* 307*/        else
/* 307*/            return new ArrayList(createQualitySourceMediaTypes(produces.value()));
            }

            public static List createQualitySourceMediaTypes(String as[])
            {
/* 321*/        return HttpHeaderReader.readQualitySourceMediaType(as);
/* 322*/        as;
/* 323*/        throw new IllegalArgumentException(as);
            }

            public static int getQuality(MediaType mediatype)
            {
/* 335*/        return readQualityFactor(mediatype = (String)mediatype.getParameters().get("q"));
            }

            private static int readQualityFactor(String s)
                throws IllegalArgumentException
            {
/* 340*/        if(s == null)
/* 341*/            return 1000;
/* 344*/        return HttpHeaderReader.readQualityFactor(s);
/* 345*/        s;
/* 346*/        throw new IllegalArgumentException(s);
            }

            public static MediaType stripQualityParams(MediaType mediatype)
            {
                Map map;
/* 359*/        if((map = mediatype.getParameters()).isEmpty() || !map.containsKey("qs") && !map.containsKey("q"))
/* 363*/            return mediatype;
/* 366*/        else
/* 366*/            return new MediaType(mediatype.getType(), mediatype.getSubtype(), Maps.filterKeys(map, QUALITY_PARAM_FILTERING_PREDICATE));
            }

            public static MediaType getTypeWildCart(MediaType mediatype)
            {
                MediaType mediatype1;
/* 377*/        if((mediatype1 = (MediaType)WILDCARD_SUBTYPE_CACHE.get(mediatype.getType())) == null)
/* 380*/            mediatype1 = new MediaType(mediatype.getType(), "*");
/* 383*/        return mediatype1;
            }

            public static String convertToString(Iterable iterable)
            {
/* 398*/        StringBuilder stringbuilder = new StringBuilder();
/* 399*/        boolean flag = true;
                MediaType mediatype;
/* 400*/        for(iterable = iterable.iterator(); iterable.hasNext(); stringbuilder.append("\"").append(mediatype.toString()).append("\""))
                {
/* 400*/            mediatype = (MediaType)iterable.next();
/* 401*/            if(!flag)
/* 402*/                stringbuilder.append(", ");
/* 404*/            else
/* 404*/                flag = false;
                }

/* 408*/        return stringbuilder.toString();
            }

            public static boolean isWildcard(MediaType mediatype)
            {
/* 421*/        return mediatype.isWildcardType() || mediatype.isWildcardSubtype();
            }

            public static final MediaType WADL_TYPE = MediaType.valueOf("application/vnd.sun.wadl+xml");
            public static final Comparator PARTIAL_ORDER_COMPARATOR = new Comparator() {

                private int rank(MediaType mediatype)
                {
/*  92*/            return (mediatype.isWildcardType() ? 1 : 0) << 1 | (mediatype.isWildcardSubtype() ? 1 : 0);
                }

                public final int compare(MediaType mediatype, MediaType mediatype1)
                {
/*  97*/            return rank(mediatype) - rank(mediatype1);
                }

                public final volatile int compare(Object obj, Object obj1)
                {
/*  88*/            return compare((MediaType)obj, (MediaType)obj1);
                }

    };
            public static final Comparator MEDIA_TYPE_LIST_COMPARATOR = new Comparator() {

                public final int compare(List list, List list1)
                {
/* 115*/            return MediaTypes.PARTIAL_ORDER_COMPARATOR.compare(getLeastSpecific(list), getLeastSpecific(list1));
                }

                private MediaType getLeastSpecific(List list)
                {
/* 120*/            return (MediaType)list.get(list.size() - 1);
                }

                public final volatile int compare(Object obj, Object obj1)
                {
/* 111*/            return compare((List)obj, (List)obj1);
                }

    };
            public static final List WILDCARD_TYPE_SINGLETON_LIST;
            public static final AcceptableMediaType WILDCARD_ACCEPTABLE_TYPE = new AcceptableMediaType("*", "*");
            public static final QualitySourceMediaType WILDCARD_QS_TYPE;
            public static final List WILDCARD_QS_TYPE_SINGLETON_LIST;
            private static final Map WILDCARD_SUBTYPE_CACHE = new HashMap() {

                private static final long serialVersionUID = 0x2b264c8fcb254f65L;

                    
                    {
/* 150*/                put("application", new MediaType("application", "*"));
/* 151*/                put("multipart", new MediaType("multipart", "*"));
/* 152*/                put("text", new MediaType("text", "*"));
                    }
    };
            private static final Predicate QUALITY_PARAM_FILTERING_PREDICATE = new Predicate() {

                public final boolean apply(String s)
                {
/* 161*/            return !"qs".equals(s) && !"q".equals(s);
                }

                public final volatile boolean apply(Object obj)
                {
/* 158*/            return apply((String)obj);
                }

    };

            static 
            {
/* 126*/        WILDCARD_TYPE_SINGLETON_LIST = Collections.singletonList(MediaType.WILDCARD_TYPE);
/* 135*/        WILDCARD_QS_TYPE_SINGLETON_LIST = Collections.singletonList(WILDCARD_QS_TYPE = new QualitySourceMediaType("*", "*"));
            }
}
