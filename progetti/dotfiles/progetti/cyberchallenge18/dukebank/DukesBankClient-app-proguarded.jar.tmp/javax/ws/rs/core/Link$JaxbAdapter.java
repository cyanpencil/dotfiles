// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Link.java

package javax.ws.rs.core;

import java.util.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;

// Referenced classes of package javax.ws.rs.core:
//            Link

public static class .XmlAdapter extends XmlAdapter
{

            public Link unmarshal(.XmlAdapter xmladapter)
            {
/* 594*/        .XmlAdapter xmladapter1 = Link.fromUri(xmladapter.Uri());
                java.util.er er;
/* 595*/        for(xmladapter = xmladapter.Params().entrySet().iterator(); xmladapter.hasNext(); xmladapter1.m(((QName)er.param()).getLocalPart(), er.e().toString()))
/* 595*/            er = (java.util.e)xmladapter.next();

/* 598*/        return xmladapter1.d(new Object[0]);
            }

            public d marshal(Link link)
            {
/* 609*/        d d = new it>(link.getUri());
                java.util.ink ink;
                String s;
/* 610*/        for(link = link.getParams().entrySet().iterator(); link.hasNext(); d.Params().put(new QName("", s), ink.e()))
/* 610*/            s = (String)(ink = (java.util.e)link.next()).e();

/* 614*/        return d;
            }

            public volatile Object marshal(Object obj)
                throws Exception
            {
/* 584*/        return marshal((Link)obj);
            }

            public volatile Object unmarshal(Object obj)
                throws Exception
            {
/* 584*/        return unmarshal((unmarshal)obj);
            }

            public .XmlAdapter()
            {
            }
}
