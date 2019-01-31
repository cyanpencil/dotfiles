// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   QualitySourceMediaType.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Map;
import javax.ws.rs.core.MediaType;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpHeaderReader, Qualified, Quality, MediaTypes

public class QualitySourceMediaType extends MediaType
    implements Qualified
{

            public QualitySourceMediaType(String s, String s1)
            {
/*  83*/        super(s, s1);
/*  84*/        qs = 1000;
            }

            public QualitySourceMediaType(String s, String s1, int i, Map map)
            {
/*  99*/        super(s, s1, Quality.enhanceWithQualityParameter(map, "qs", i));
/* 100*/        qs = i;
            }

            private QualitySourceMediaType(String s, String s1, Map map, int i)
            {
/* 105*/        super(s, s1, map);
/* 106*/        qs = i;
            }

            public int getQuality()
            {
/* 116*/        return qs;
            }

            public static QualitySourceMediaType valueOf(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 130*/        httpheaderreader.hasNext();
/* 133*/        String s = httpheaderreader.nextToken().toString();
/* 134*/        httpheaderreader.nextSeparator('/');
/* 136*/        String s1 = httpheaderreader.nextToken().toString();
/* 138*/        int i = 1000;
/* 139*/        Map map = null;
/* 140*/        if(httpheaderreader.hasNext() && (map = HttpHeaderReader.readParameters(httpheaderreader)) != null)
/* 143*/            i = getQs((String)map.get("qs"));
/* 148*/        return new QualitySourceMediaType(s, s1, map, i);
            }

            public static int getQualitySource(MediaType mediatype)
                throws IllegalArgumentException
            {
/* 164*/        if(mediatype instanceof QualitySourceMediaType)
/* 165*/            return ((QualitySourceMediaType)mediatype).getQuality();
/* 167*/        else
/* 167*/            return getQs(mediatype);
            }

            private static int getQs(MediaType mediatype)
                throws IllegalArgumentException
            {
/* 173*/        return getQs((String)mediatype.getParameters().get("qs"));
/* 174*/        mediatype;
/* 175*/        throw new IllegalArgumentException(mediatype);
            }

            private static int getQs(String s)
                throws ParseException
            {
/* 180*/        if(s == null)
/* 181*/            return 1000;
/* 184*/        else
/* 184*/            return HttpHeaderReader.readQualityFactor(s);
            }

            public boolean equals(Object obj)
            {
/* 189*/        if(!super.equals(obj))
/* 190*/            return false;
/* 193*/        if(obj instanceof QualitySourceMediaType)
                {
/* 194*/            obj = (QualitySourceMediaType)obj;
/* 195*/            return qs == ((QualitySourceMediaType) (obj)).qs;
                }
/* 199*/        return qs == 1000;
            }

            public int hashCode()
            {
/* 205*/        int i = super.hashCode();
/* 206*/        if(qs == 1000)
/* 206*/            return i;
/* 206*/        else
/* 206*/            return i * 47 + qs;
            }

            public String toString()
            {
/* 211*/        return (new StringBuilder("{")).append(super.toString()).append(", qs=").append(qs).append("}").toString();
            }

            public static final Comparator COMPARATOR = new Comparator() {

                public final int compare(QualitySourceMediaType qualitysourcemediatype, QualitySourceMediaType qualitysourcemediatype1)
                {
                    int i;
/*  63*/            if((i = Quality.QUALIFIED_COMPARATOR.compare(qualitysourcemediatype, qualitysourcemediatype1)) != 0)
/*  65*/                return i;
/*  67*/            else
/*  67*/                return MediaTypes.PARTIAL_ORDER_COMPARATOR.compare(qualitysourcemediatype, qualitysourcemediatype1);
                }

                public final volatile int compare(Object obj, Object obj1)
                {
/*  59*/            return compare((QualitySourceMediaType)obj, (QualitySourceMediaType)obj1);
                }

    };
            private final int qs;

}
