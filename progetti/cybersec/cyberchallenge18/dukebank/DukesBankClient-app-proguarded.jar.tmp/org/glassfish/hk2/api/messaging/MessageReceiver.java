// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessageReceiver.java

package org.glassfish.hk2.api.messaging;

import java.lang.annotation.Annotation;

public interface MessageReceiver
    extends Annotation
{

    public abstract Class[] value();

    public static final String EVENT_RECEIVER_TYPES = "org.glassfish.hk2.messaging.messageReceiverTypes";
}
