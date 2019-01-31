// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientResponse.java

package org.glassfish.jersey.client;

import java.net.URI;
import javax.ws.rs.core.Link;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientResponse

class this._cls0
    implements Function
{

            public Link apply(Link link)
            {
/* 247*/        if(link.getUri().isAbsolute())
/* 248*/            return link;
/* 251*/        else
/* 251*/            return Link.fromLink(link).Uri(getResolvedRequestUri()).d(new Object[0]);
            }

            public volatile Object apply(Object obj)
            {
/* 244*/        return apply((Link)obj);
            }

            final ClientResponse this$0;

            tion()
            {
/* 244*/        this$0 = ClientResponse.this;
/* 244*/        super();
            }
}
