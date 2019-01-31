// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Entity.java

package javax.ws.rs.client;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Locale;
import javax.ws.rs.core.*;

public final class Entity
{

            public static Entity entity(Object obj, MediaType mediatype)
            {
/*  73*/        return new Entity(obj, mediatype);
            }

            public static Entity entity(Object obj, MediaType mediatype, Annotation aannotation[])
            {
/*  86*/        return new Entity(obj, mediatype, aannotation);
            }

            public static Entity entity(Object obj, String s)
            {
/* 100*/        return new Entity(obj, MediaType.valueOf(s));
            }

            public static Entity entity(Object obj, Variant variant1)
            {
/* 112*/        return new Entity(obj, variant1);
            }

            public static Entity entity(Object obj, Variant variant1, Annotation aannotation[])
            {
/* 125*/        return new Entity(obj, variant1, aannotation);
            }

            public static Entity text(Object obj)
            {
/* 136*/        return new Entity(obj, MediaType.TEXT_PLAIN_TYPE);
            }

            public static Entity xml(Object obj)
            {
/* 147*/        return new Entity(obj, MediaType.APPLICATION_XML_TYPE);
            }

            public static Entity json(Object obj)
            {
/* 158*/        return new Entity(obj, MediaType.APPLICATION_JSON_TYPE);
            }

            public static Entity html(Object obj)
            {
/* 169*/        return new Entity(obj, MediaType.TEXT_HTML_TYPE);
            }

            public static Entity xhtml(Object obj)
            {
/* 181*/        return new Entity(obj, MediaType.APPLICATION_XHTML_XML_TYPE);
            }

            public static Entity form(Form form1)
            {
/* 193*/        return new Entity(form1, MediaType.APPLICATION_FORM_URLENCODED_TYPE);
            }

            public static Entity form(MultivaluedMap multivaluedmap)
            {
/* 205*/        return new Entity(new Form(multivaluedmap), MediaType.APPLICATION_FORM_URLENCODED_TYPE);
            }

            private Entity(Object obj, MediaType mediatype)
            {
/* 209*/        this(obj, new Variant(mediatype, null, null), null);
            }

            private Entity(Object obj, Variant variant1)
            {
/* 213*/        this(obj, variant1, null);
            }

            private Entity(Object obj, MediaType mediatype, Annotation aannotation[])
            {
/* 217*/        this(obj, new Variant(mediatype, null, null), aannotation);
            }

            private Entity(Object obj, Variant variant1, Annotation aannotation[])
            {
/* 221*/        entity = obj;
/* 222*/        variant = variant1;
/* 224*/        annotations = aannotation != null ? aannotation : EMPTY_ANNOTATIONS;
            }

            public final Variant getVariant()
            {
/* 233*/        return variant;
            }

            public final MediaType getMediaType()
            {
/* 242*/        return variant.getMediaType();
            }

            public final String getEncoding()
            {
/* 251*/        return variant.getEncoding();
            }

            public final Locale getLanguage()
            {
/* 260*/        return variant.getLanguage();
            }

            public final Object getEntity()
            {
/* 269*/        return entity;
            }

            public final Annotation[] getAnnotations()
            {
/* 279*/        return annotations;
            }

            public final boolean equals(Object obj)
            {
/* 284*/        if(this == obj)
/* 284*/            return true;
/* 285*/        if(!(obj instanceof Entity))
/* 285*/            return false;
/* 287*/        obj = (Entity)obj;
/* 289*/        if(!Arrays.equals(annotations, ((Entity) (obj)).annotations))
/* 289*/            return false;
/* 290*/        if(entity == null ? ((Entity) (obj)).entity != null : !entity.equals(((Entity) (obj)).entity))
/* 290*/            return false;
/* 291*/        return variant == null ? ((Entity) (obj)).variant == null : variant.equals(((Entity) (obj)).variant);
            }

            public final int hashCode()
            {
/* 298*/        int i = entity == null ? 0 : entity.hashCode();
/* 299*/        i = i * 31 + (variant == null ? 0 : variant.hashCode());
/* 300*/        return i = i * 31 + Arrays.hashCode(annotations);
            }

            public final String toString()
            {
/* 306*/        return (new StringBuilder("Entity{entity=")).append(entity).append(", variant=").append(variant).append(", annotations=").append(Arrays.toString(annotations)).append('}').toString();
            }

            private static final Annotation EMPTY_ANNOTATIONS[] = new Annotation[0];
            private final Object entity;
            private final Variant variant;
            private final Annotation annotations[];

}
