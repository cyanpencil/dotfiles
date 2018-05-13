// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CustomAnnotationLiteral.java

package org.glassfish.jersey.internal.inject;

import org.glassfish.hk2.api.AnnotationLiteral;

// Referenced classes of package org.glassfish.jersey.internal.inject:
//            Custom

public final class CustomAnnotationLiteral extends AnnotationLiteral
    implements Custom
{

            private CustomAnnotationLiteral()
            {
            }

            public static final Custom INSTANCE = new CustomAnnotationLiteral();

}
