// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConcurrentHashMapV8.java

package org.glassfish.jersey.internal.util.collection;

import java.io.Serializable;
import java.util.concurrent.locks.ReentrantLock;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            ConcurrentHashMapV8

static class loadFactor extends ReentrantLock
    implements Serializable
{

            private static final long serialVersionUID = 0x1f364c905893293dL;
            final float loadFactor;

            (float f)
            {
/*1376*/        loadFactor = f;
            }
}
