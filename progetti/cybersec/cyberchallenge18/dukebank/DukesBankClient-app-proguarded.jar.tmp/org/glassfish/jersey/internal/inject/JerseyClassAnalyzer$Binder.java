// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyClassAnalyzer.java

package org.glassfish.jersey.internal.inject;

import javax.inject.Singleton;
import org.glassfish.hk2.api.ClassAnalyzer;
import org.glassfish.hk2.utilities.binding.*;

// Referenced classes of package org.glassfish.jersey.internal.inject:
//            JerseyClassAnalyzer

public static final class  extends AbstractBinder
{

            protected final void configure()
            {
/*  92*/        bind(org/glassfish/jersey/internal/inject/JerseyClassAnalyzer).analyzeWith("default").named("JerseyClassAnalyzer").to(org/glassfish/hk2/api/ClassAnalyzer).in(javax/inject/Singleton);
            }

            public ()
            {
            }
}
