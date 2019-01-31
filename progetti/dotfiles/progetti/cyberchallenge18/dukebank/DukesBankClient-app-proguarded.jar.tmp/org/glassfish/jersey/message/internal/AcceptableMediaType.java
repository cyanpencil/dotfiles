// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AcceptableMediaType.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Map;
import javax.ws.rs.core.MediaType;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpHeaderReader, Qualified, Quality, MediaTypes

public class AcceptableMediaType extends MediaType
    implements Qualified
{

            public AcceptableMediaType(String s, String s1)
            {
/*  83*/        super(s, s1);
/*  84*/        q = 1000;
            }

            public AcceptableMediaType(String s, String s1, int i, Map map)
            {
/*  99*/        super(s, s1, Quality.enhanceWithQualityParameter(map, "q", i));
/* 100*/        q = i;
            }

            private AcceptableMediaType(String s, String s1, Map map, int i)
            {
/* 106*/        super(s, s1, map);
/* 107*/        q = i;
            }

            public int getQuality()
            {
/* 112*/        return q;
            }

            public static AcceptableMediaType valueOf(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 126*/        httpheaderreader.hasNext();
/* 129*/        String s = httpheaderreader.nextToken().toString();
/* 130*/        String s1 = "*";
/* 132*/        if(httpheaderreader.hasNextSeparator('/', false))
                {
/* 133*/            httpheaderreader.next(false);
/* 135*/            s1 = httpheaderreader.nextToken().toString();
                }
/* 138*/        Map map = null;
/* 139*/        int i = 1000;
/* 140*/        if(httpheaderreader.hasNext() && (map = HttpHeaderReader.readParameters(httpheaderreader)) != null && (httpheaderreader = (String)map.get("q")) != null)
/* 145*/            i = HttpHeaderReader.readQualityFactor(httpheaderreader);
/* 151*/        return new AcceptableMediaType(s, s1, map, i);
            }

            public static AcceptableMediaType valueOf(MediaType mediatype)
                throws ParseException
            {
/* 164*/        if(mediatype instanceof AcceptableMediaType)
/* 165*/            return (AcceptableMediaType)mediatype;
/* 168*/        Map map = mediatype.getParameters();
/* 170*/        int i = 1000;
                String s;
/* 171*/        if(map != null && (s = (String)map.get("q")) != null)
/* 174*/            i = HttpHeaderReader.readQualityFactor(s);
/* 179*/        return new AcceptableMediaType(mediatype.getType(), mediatype.getSubtype(), map, i);
            }

            public boolean equals(Object obj)
            {
/* 184*/        if(!super.equals(obj))
/* 185*/            return false;
/* 188*/        if(obj instanceof AcceptableMediaType)
                {
/* 189*/            obj = (AcceptableMediaType)obj;
/* 190*/            return q == ((AcceptableMediaType) (obj)).q;
                }
/* 194*/        return q == 1000;
            }

            public int hashCode()
            {
/* 200*/        int i = super.hashCode();
/* 201*/        if(q == 1000)
/* 201*/            return i;
/* 201*/        else
/* 201*/            return i * 47 + q;
            }

            public static final Comparator COMPARATOR = new Comparator() {

                public final int compare(AcceptableMediaType acceptablemediatype, AcceptableMediaType acceptablemediatype1)
                {
                    int i;
/*  62*/            if((i = Quality.QUALIFIED_COMPARATOR.compare(acceptablemediatype, acceptablemediatype1)) != 0)
/*  64*/                return i;
/*  67*/            else
/*  67*/                return MediaTypes.PARTIAL_ORDER_COMPARATOR.compare(acceptablemediatype, acceptablemediatype1);
                }

                public final volatile int compare(Object obj, Object obj1)
                {
/*  58*/            return compare((AcceptableMediaType)obj, (AcceptableMediaType)obj1);
                }

    };
            private final int q;

}
