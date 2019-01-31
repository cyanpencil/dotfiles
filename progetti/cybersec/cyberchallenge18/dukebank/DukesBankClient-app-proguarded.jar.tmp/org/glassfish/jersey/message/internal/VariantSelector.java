// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   VariantSelector.java

package org.glassfish.jersey.message.internal;

import java.util.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Variant;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.collect.Lists;
import org.glassfish.jersey.internal.util.collection.Ref;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            InboundMessageContext, Qualified, QualitySourceMediaType, AcceptableToken, 
//            AcceptableLanguageTag, AcceptableMediaType

public final class VariantSelector
{
    static class VariantHolder
    {

                private final Variant v;
                private final int mediaTypeQs;



                public VariantHolder(Variant variant)
                {
/* 279*/            this(variant, 1000);
                }

                public VariantHolder(Variant variant, int i)
                {
/* 283*/            v = variant;
/* 284*/            mediaTypeQs = i;
                }
    }

    static interface DimensionChecker
    {

        public abstract Object getDimension(VariantHolder variantholder);

        public abstract int getQualitySource(VariantHolder variantholder, Object obj);

        public abstract boolean isCompatible(Object obj, Object obj1);

        public abstract String getVaryHeaderValue();
    }


            private VariantSelector()
            {
            }

            private static LinkedList selectVariants(List list, List list1, DimensionChecker dimensionchecker, Set set)
            {
/* 219*/        int i = 0;
/* 220*/        int j = 0;
/* 222*/        LinkedList linkedlist = new LinkedList();
/* 226*/        for(list1 = list1.iterator(); list1.hasNext();)
                {
                    Qualified qualified;
/* 226*/            int k = (qualified = (Qualified)list1.next()).getQuality();
/* 229*/            Iterator iterator = list.iterator();
/* 230*/            while(iterator.hasNext()) 
                    {
/* 231*/                VariantHolder variantholder1 = (VariantHolder)iterator.next();
                        Object obj;
/* 234*/                if((obj = dimensionchecker.getDimension(variantholder1)) != null)
                        {
/* 237*/                    set.add(dimensionchecker.getVaryHeaderValue());
                            int l;
/* 240*/                    if((l = dimensionchecker.getQualitySource(variantholder1, obj)) >= j && dimensionchecker.isCompatible(qualified, obj))
                            {
/* 242*/                        if(l > j)
                                {
/* 243*/                            j = l;
/* 244*/                            i = k;
/* 246*/                            linkedlist.clear();
/* 247*/                            linkedlist.add(variantholder1);
                                } else
/* 248*/                        if(k > i)
                                {
/* 249*/                            i = k;
/* 251*/                            linkedlist.addFirst(variantholder1);
                                } else
/* 252*/                        if(k == i)
/* 255*/                            linkedlist.add(variantholder1);
/* 257*/                        iterator.remove();
                            }
                        }
                    }
                }

/* 265*/        list1 = list.iterator();
/* 265*/        do
                {
/* 265*/            if(!list1.hasNext())
/* 265*/                break;
/* 265*/            VariantHolder variantholder = (VariantHolder)list1.next();
/* 266*/            if(dimensionchecker.getDimension(variantholder) == null)
/* 267*/                linkedlist.add(variantholder);
                } while(true);
/* 270*/        return linkedlist;
            }

            private static LinkedList getVariantHolderList(List list)
            {
/* 289*/        LinkedList linkedlist = new LinkedList();
                Variant variant;
                MediaType mediatype;
/* 290*/        for(list = list.iterator(); list.hasNext();)
/* 290*/            if((mediatype = (variant = (Variant)list.next()).getMediaType()) != null)
                    {
/* 293*/                if((mediatype instanceof QualitySourceMediaType) || mediatype.getParameters().containsKey("qs"))
                        {
/* 295*/                    int i = QualitySourceMediaType.getQualitySource(mediatype);
/* 296*/                    linkedlist.add(new VariantHolder(variant, i));
                        } else
                        {
/* 298*/                    linkedlist.add(new VariantHolder(variant));
                        }
                    } else
                    {
/* 301*/                linkedlist.add(new VariantHolder(variant));
                    }

/* 305*/        return linkedlist;
            }

            public static Variant selectVariant(InboundMessageContext inboundmessagecontext, List list, Ref ref)
            {
/* 321*/        if((inboundmessagecontext = selectVariants(inboundmessagecontext, list, ref)).isEmpty())
/* 322*/            return null;
/* 322*/        else
/* 322*/            return (Variant)inboundmessagecontext.get(0);
            }

            public static List selectVariants(InboundMessageContext inboundmessagecontext, List list, Ref ref)
            {
/* 336*/        list = getVariantHolderList(list);
/* 338*/        Object obj = new HashSet();
/* 339*/        if((list = selectVariants(list = selectVariants(list = selectVariants(list = selectVariants(list, inboundmessagecontext.getQualifiedAcceptableMediaTypes(), MEDIA_TYPE_DC, ((Set) (obj))), inboundmessagecontext.getQualifiedAcceptableLanguages(), LANGUAGE_TAG_DC, ((Set) (obj))), inboundmessagecontext.getQualifiedAcceptCharset(), CHARSET_DC, ((Set) (obj))), inboundmessagecontext.getQualifiedAcceptEncoding(), ENCODING_DC, ((Set) (obj)))).isEmpty())
/* 345*/            return Collections.emptyList();
/* 347*/        inboundmessagecontext = new StringBuilder();
                String s;
/* 348*/        for(obj = ((Set) (obj)).iterator(); ((Iterator) (obj)).hasNext(); inboundmessagecontext.append(s))
                {
/* 348*/            s = (String)((Iterator) (obj)).next();
/* 349*/            if(inboundmessagecontext.length() > 0)
/* 350*/                inboundmessagecontext.append(',');
                }

/* 354*/        if(!((String) (obj = inboundmessagecontext.toString())).isEmpty())
/* 356*/            ref.set(obj);
/* 358*/        return Lists.transform(list, new Function() {

                    public final Variant apply(VariantHolder variantholder)
                    {
/* 361*/                return variantholder.v;
                    }

                    public final volatile Object apply(Object obj1)
                    {
/* 358*/                return apply((VariantHolder)obj1);
                    }

        });
            }

            private static final DimensionChecker MEDIA_TYPE_DC = new DimensionChecker() {

                public final MediaType getDimension(VariantHolder variantholder)
                {
/* 115*/            return variantholder.v.getMediaType();
                }

                public final boolean isCompatible(AcceptableMediaType acceptablemediatype, MediaType mediatype)
                {
/* 120*/            return acceptablemediatype.isCompatible(mediatype);
                }

                public final int getQualitySource(VariantHolder variantholder, MediaType mediatype)
                {
/* 125*/            return variantholder.mediaTypeQs;
                }

                public final String getVaryHeaderValue()
                {
/* 130*/            return "Accept";
                }

                public final volatile boolean isCompatible(Object obj, Object obj1)
                {
/* 111*/            return isCompatible((AcceptableMediaType)obj, (MediaType)obj1);
                }

                public final volatile int getQualitySource(VariantHolder variantholder, Object obj)
                {
/* 111*/            return getQualitySource(variantholder, (MediaType)obj);
                }

                public final volatile Object getDimension(VariantHolder variantholder)
                {
/* 111*/            return getDimension(variantholder);
                }

    };
            private static final DimensionChecker LANGUAGE_TAG_DC = new DimensionChecker() {

                public final Locale getDimension(VariantHolder variantholder)
                {
/* 138*/            return variantholder.v.getLanguage();
                }

                public final boolean isCompatible(AcceptableLanguageTag acceptablelanguagetag, Locale locale)
                {
/* 143*/            return acceptablelanguagetag.isCompatible(locale);
                }

                public final int getQualitySource(VariantHolder variantholder, Locale locale)
                {
/* 148*/            return 0;
                }

                public final String getVaryHeaderValue()
                {
/* 153*/            return "Accept-Language";
                }

                public final volatile boolean isCompatible(Object obj, Object obj1)
                {
/* 134*/            return isCompatible((AcceptableLanguageTag)obj, (Locale)obj1);
                }

                public final volatile int getQualitySource(VariantHolder variantholder, Object obj)
                {
/* 134*/            return getQualitySource(variantholder, (Locale)obj);
                }

                public final volatile Object getDimension(VariantHolder variantholder)
                {
/* 134*/            return getDimension(variantholder);
                }

    };
            private static final DimensionChecker CHARSET_DC = new DimensionChecker() {

                public final String getDimension(VariantHolder variantholder)
                {
/* 161*/            if((variantholder = variantholder.v.getMediaType()) != null)
/* 162*/                return (String)variantholder.getParameters().get("charset");
/* 162*/            else
/* 162*/                return null;
                }

                public final boolean isCompatible(AcceptableToken acceptabletoken, String s)
                {
/* 167*/            return acceptabletoken.isCompatible(s);
                }

                public final int getQualitySource(VariantHolder variantholder, String s)
                {
/* 172*/            return 0;
                }

                public final String getVaryHeaderValue()
                {
/* 177*/            return "Accept-Charset";
                }

                public final volatile boolean isCompatible(Object obj, Object obj1)
                {
/* 157*/            return isCompatible((AcceptableToken)obj, (String)obj1);
                }

                public final volatile int getQualitySource(VariantHolder variantholder, Object obj)
                {
/* 157*/            return getQualitySource(variantholder, (String)obj);
                }

                public final volatile Object getDimension(VariantHolder variantholder)
                {
/* 157*/            return getDimension(variantholder);
                }

    };
            private static final DimensionChecker ENCODING_DC = new DimensionChecker() {

                public final String getDimension(VariantHolder variantholder)
                {
/* 185*/            return variantholder.v.getEncoding();
                }

                public final boolean isCompatible(AcceptableToken acceptabletoken, String s)
                {
/* 190*/            return acceptabletoken.isCompatible(s);
                }

                public final int getQualitySource(VariantHolder variantholder, String s)
                {
/* 195*/            return 0;
                }

                public final String getVaryHeaderValue()
                {
/* 200*/            return "Accept-Encoding";
                }

                public final volatile boolean isCompatible(Object obj, Object obj1)
                {
/* 181*/            return isCompatible((AcceptableToken)obj, (String)obj1);
                }

                public final volatile int getQualitySource(VariantHolder variantholder, Object obj)
                {
/* 181*/            return getQualitySource(variantholder, (String)obj);
                }

                public final volatile Object getDimension(VariantHolder variantholder)
                {
/* 181*/            return getDimension(variantholder);
                }

    };

}
