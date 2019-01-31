// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TopicDistributionService.java

package org.glassfish.hk2.api.messaging;


// Referenced classes of package org.glassfish.hk2.api.messaging:
//            Topic

public interface TopicDistributionService
{

    public abstract void distributeMessage(Topic topic, Object obj);

    public static final String HK2_DEFAULT_TOPIC_DISTRIBUTOR = "HK2TopicDistributionService";
}
