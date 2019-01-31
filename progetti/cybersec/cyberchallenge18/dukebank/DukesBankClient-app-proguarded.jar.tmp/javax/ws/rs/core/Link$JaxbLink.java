// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Link.java

package javax.ws.rs.core;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package javax.ws.rs.core:
//            Link

public static class params
{

            public URI getUri()
            {
/* 491*/        return uri;
            }

            public Map getParams()
            {
/* 501*/        if(params == null)
/* 502*/            params = new HashMap();
/* 504*/        return params;
            }

            void setUri(URI uri1)
            {
/* 513*/        uri = uri1;
            }

            void setParams(Map map)
            {
/* 522*/        params = map;
            }

            public boolean equals(Object obj)
            {
/* 527*/        if(this == obj)
/* 527*/            return true;
/* 528*/        if(!(obj instanceof params))
/* 528*/            return false;
/* 530*/        obj = (params)obj;
/* 532*/        if(uri == null ? ((uri) (obj)).uri != null : !uri.equals(((uri) (obj)).uri))
/* 533*/            return false;
/* 536*/        if(params == ((params) (obj)).params)
/* 537*/            return true;
/* 539*/        if(params == null)
/* 541*/            return ((params) (obj)).params.isEmpty();
/* 543*/        if(((params) (obj)).params == null)
/* 545*/            return params.isEmpty();
/* 548*/        else
/* 548*/            return params.equals(((params) (obj)).params);
            }

            public int hashCode()
            {
/* 553*/        int i = uri == null ? 0 : uri.hashCode();
/* 554*/        return i = i * 31 + (params == null || params.isEmpty() ? 0 : params.hashCode());
            }

            private URI uri;
            private Map params;

            public ()
            {
            }

            public (URI uri1)
            {
/* 470*/        uri = uri1;
            }

            public uri(URI uri1, Map map)
            {
/* 480*/        uri = uri1;
/* 481*/        params = map;
            }
}
