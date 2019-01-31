// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WebTarget.java

package javax.ws.rs.client;

import java.net.URI;
import java.util.Map;
import javax.ws.rs.core.*;

// Referenced classes of package javax.ws.rs.client:
//            Invocation

public interface WebTarget
    extends Configurable
{

    public abstract URI getUri();

    public abstract UriBuilder getUriBuilder();

    public abstract WebTarget path(String s);

    public abstract WebTarget resolveTemplate(String s, Object obj);

    public abstract WebTarget resolveTemplate(String s, Object obj, boolean flag);

    public abstract WebTarget resolveTemplateFromEncoded(String s, Object obj);

    public abstract WebTarget resolveTemplates(Map map);

    public abstract WebTarget resolveTemplates(Map map, boolean flag);

    public abstract WebTarget resolveTemplatesFromEncoded(Map map);

    public transient abstract WebTarget matrixParam(String s, Object aobj[]);

    public transient abstract WebTarget queryParam(String s, Object aobj[]);

    public abstract Invocation.Builder request();

    public transient abstract Invocation.Builder request(String as[]);

    public transient abstract Invocation.Builder request(MediaType amediatype[]);
}
