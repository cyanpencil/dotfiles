// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExtractorException.java

package org.glassfish.jersey.internal.inject;

import javax.ws.rs.ProcessingException;

public class ExtractorException extends ProcessingException
{

            public ExtractorException(String s)
            {
/*  60*/        super(s);
            }

            public ExtractorException(String s, Throwable throwable)
            {
/*  70*/        super(s, throwable);
            }

            public ExtractorException(Throwable throwable)
            {
/*  79*/        super(throwable);
            }

            private static final long serialVersionUID = 0xbbbfabe78b2e8ae3L;
}
