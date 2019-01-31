// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Variant.java

package javax.ws.rs.core;

import java.io.StringWriter;
import java.util.List;
import java.util.Locale;
import javax.ws.rs.ext.RuntimeDelegate;

// Referenced classes of package javax.ws.rs.core:
//            MediaType

public class Variant
{
    public static abstract class VariantListBuilder
    {

                public static VariantListBuilder newInstance()
                {
/* 309*/            return RuntimeDelegate.getInstance().createVariantListBuilder();
                }

                public abstract List build();

                public abstract VariantListBuilder add();

                public transient abstract VariantListBuilder languages(Locale alocale[]);

                public transient abstract VariantListBuilder encodings(String as[]);

                public transient abstract VariantListBuilder mediaTypes(MediaType amediatype[]);

                protected VariantListBuilder()
                {
                }
    }


            public Variant(MediaType mediatype, String s, String s1)
            {
/*  75*/        if(mediatype == null && s == null && s1 == null)
                {
/*  76*/            throw new IllegalArgumentException("mediaType, language, encoding all null");
                } else
                {
/*  78*/            encoding = s1;
/*  79*/            language = s != null ? new Locale(s) : null;
/*  80*/            mediaType = mediatype;
/*  81*/            return;
                }
            }

            public Variant(MediaType mediatype, String s, String s1, String s2)
            {
/*  97*/        if(mediatype == null && s == null && s2 == null)
                {
/*  98*/            throw new IllegalArgumentException("mediaType, language, encoding all null");
                } else
                {
/* 100*/            encoding = s2;
/* 101*/            language = s != null ? new Locale(s, s1) : null;
/* 102*/            mediaType = mediatype;
/* 103*/            return;
                }
            }

            public Variant(MediaType mediatype, String s, String s1, String s2, String s3)
            {
/* 123*/        if(mediatype == null && s == null && s3 == null)
                {
/* 124*/            throw new IllegalArgumentException("mediaType, language, encoding all null");
                } else
                {
/* 126*/            encoding = s3;
/* 127*/            language = s != null ? new Locale(s, s1, s2) : null;
/* 128*/            mediaType = mediatype;
/* 129*/            return;
                }
            }

            public Variant(MediaType mediatype, Locale locale, String s)
            {
/* 141*/        if(mediatype == null && locale == null && s == null)
                {
/* 142*/            throw new IllegalArgumentException("mediaType, language, encoding all null");
                } else
                {
/* 144*/            encoding = s;
/* 145*/            language = locale;
/* 146*/            mediaType = mediatype;
/* 147*/            return;
                }
            }

            public Locale getLanguage()
            {
/* 155*/        return language;
            }

            public String getLanguageString()
            {
/* 167*/        if(language == null)
/* 167*/            return null;
/* 167*/        else
/* 167*/            return language.toString();
            }

            public MediaType getMediaType()
            {
/* 176*/        return mediaType;
            }

            public String getEncoding()
            {
/* 185*/        return encoding;
            }

            public static transient VariantListBuilder mediaTypes(MediaType amediatype[])
            {
                VariantListBuilder variantlistbuilder;
/* 201*/        (variantlistbuilder = VariantListBuilder.newInstance()).mediaTypes(amediatype);
/* 203*/        return variantlistbuilder;
            }

            public static transient VariantListBuilder languages(Locale alocale[])
            {
                VariantListBuilder variantlistbuilder;
/* 217*/        (variantlistbuilder = VariantListBuilder.newInstance()).languages(alocale);
/* 219*/        return variantlistbuilder;
            }

            public static transient VariantListBuilder encodings(String as[])
            {
                VariantListBuilder variantlistbuilder;
/* 233*/        (variantlistbuilder = VariantListBuilder.newInstance()).encodings(as);
/* 235*/        return variantlistbuilder;
            }

            public int hashCode()
            {
/* 246*/        int i = 203 + (language == null ? 0 : language.hashCode());
/* 247*/        i = i * 29 + (mediaType == null ? 0 : mediaType.hashCode());
/* 248*/        return i = i * 29 + (encoding == null ? 0 : encoding.hashCode());
            }

            public boolean equals(Object obj)
            {
/* 261*/        if(obj == null)
/* 262*/            return false;
/* 264*/        if(getClass() != obj.getClass())
/* 265*/            return false;
/* 267*/        obj = (Variant)obj;
/* 268*/        if(language != ((Variant) (obj)).language && (language == null || !language.equals(((Variant) (obj)).language)))
/* 269*/            return false;
/* 271*/        if(mediaType != ((Variant) (obj)).mediaType && (mediaType == null || !mediaType.equals(((Variant) (obj)).mediaType)))
/* 272*/            return false;
/* 275*/        return encoding == ((Variant) (obj)).encoding || encoding != null && encoding.equals(((Variant) (obj)).encoding);
            }

            public String toString()
            {
                StringWriter stringwriter;
/* 280*/        (stringwriter = new StringWriter()).append("Variant[mediaType=");
/* 282*/        stringwriter.append(((CharSequence) (mediaType != null ? ((CharSequence) (mediaType.toString())) : "null")));
/* 283*/        stringwriter.append(", language=");
/* 284*/        stringwriter.append(((CharSequence) (language != null ? ((CharSequence) (language.toString())) : "null")));
/* 285*/        stringwriter.append(", encoding=");
/* 286*/        stringwriter.append(((CharSequence) (encoding != null ? ((CharSequence) (encoding)) : "null")));
/* 287*/        stringwriter.append("]");
/* 288*/        return stringwriter.toString();
            }

            private Locale language;
            private MediaType mediaType;
            private String encoding;
}
