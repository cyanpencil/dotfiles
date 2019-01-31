// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   VariantListBuilder.java

package org.glassfish.jersey.message.internal;

import java.util.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Variant;

public class VariantListBuilder extends javax.ws.rs.core.Variant.VariantListBuilder
{

            public VariantListBuilder()
            {
            }

            public List build()
            {
/*  65*/        if(!mediaTypes.isEmpty() || !languages.isEmpty() || !encodings.isEmpty())
/*  67*/            add();
/*  69*/        if(variants == null)
/*  70*/            variants = new ArrayList();
/*  73*/        return variants;
            }

            public VariantListBuilder add()
            {
/*  78*/        if(variants == null)
/*  79*/            variants = new ArrayList();
/*  82*/        addMediaTypes();
/*  84*/        languages.clear();
/*  85*/        encodings.clear();
/*  86*/        mediaTypes.clear();
/*  88*/        return this;
            }

            private void addMediaTypes()
            {
/*  92*/        if(mediaTypes.isEmpty())
                {
/*  93*/            addLanguages(null);
/*  93*/            return;
                }
                MediaType mediatype;
/*  95*/        for(Iterator iterator = mediaTypes.iterator(); iterator.hasNext(); addLanguages(mediatype))
/*  95*/            mediatype = (MediaType)iterator.next();

            }

            private void addLanguages(MediaType mediatype)
            {
/* 102*/        if(languages.isEmpty())
                {
/* 103*/            addEncodings(mediatype, null);
/* 103*/            return;
                }
                Locale locale;
/* 105*/        for(Iterator iterator = languages.iterator(); iterator.hasNext(); addEncodings(mediatype, locale))
/* 105*/            locale = (Locale)iterator.next();

            }

            private void addEncodings(MediaType mediatype, Locale locale)
            {
/* 112*/        if(encodings.isEmpty())
                {
/* 113*/            addVariant(mediatype, locale, null);
/* 113*/            return;
                }
                String s;
/* 115*/        for(Iterator iterator = encodings.iterator(); iterator.hasNext(); addVariant(mediatype, locale, s))
/* 115*/            s = (String)iterator.next();

            }

            private void addVariant(MediaType mediatype, Locale locale, String s)
            {
/* 122*/        variants.add(new Variant(mediatype, locale, s));
            }

            public transient VariantListBuilder languages(Locale alocale[])
            {
/* 127*/        languages.addAll(Arrays.asList(alocale));
/* 128*/        return this;
            }

            public transient VariantListBuilder encodings(String as[])
            {
/* 133*/        encodings.addAll(Arrays.asList(as));
/* 134*/        return this;
            }

            public transient VariantListBuilder mediaTypes(MediaType amediatype[])
            {
/* 139*/        mediaTypes.addAll(Arrays.asList(amediatype));
/* 140*/        return this;
            }

            public volatile javax.ws.rs.core.Variant.VariantListBuilder mediaTypes(MediaType amediatype[])
            {
/*  56*/        return mediaTypes(amediatype);
            }

            public volatile javax.ws.rs.core.Variant.VariantListBuilder encodings(String as[])
            {
/*  56*/        return encodings(as);
            }

            public volatile javax.ws.rs.core.Variant.VariantListBuilder languages(Locale alocale[])
            {
/*  56*/        return languages(alocale);
            }

            public volatile javax.ws.rs.core.Variant.VariantListBuilder add()
            {
/*  56*/        return add();
            }

            private List variants;
            private final List mediaTypes = new ArrayList();
            private final List languages = new ArrayList();
            private final List encodings = new ArrayList();
}
