// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MediaTypes.java

package org.glassfish.jersey.message.internal;

import java.util.HashMap;
import javax.ws.rs.core.MediaType;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            MediaTypes

static class put extends HashMap
{

            private static final long serialVersionUID = 0x2b264c8fcb254f65L;

            ()
            {
/* 150*/        put("application", new MediaType("application", "*"));
/* 151*/        put("multipart", new MediaType("multipart", "*"));
/* 152*/        put("text", new MediaType("text", "*"));
            }
}
