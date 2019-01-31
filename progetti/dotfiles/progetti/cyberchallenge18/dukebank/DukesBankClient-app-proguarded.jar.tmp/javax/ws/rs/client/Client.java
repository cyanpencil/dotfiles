// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Client.java

package javax.ws.rs.client;

import java.net.URI;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.core.*;

// Referenced classes of package javax.ws.rs.client:
//            Invocation, WebTarget

public interface Client
    extends Configurable
{

    public abstract void close();

    public abstract WebTarget target(String s);

    public abstract WebTarget target(URI uri);

    public abstract WebTarget target(UriBuilder uribuilder);

    public abstract WebTarget target(Link link);

    public abstract Invocation.Builder invocation(Link link);

    public abstract SSLContext getSslContext();

    public abstract HostnameVerifier getHostnameVerifier();
}
