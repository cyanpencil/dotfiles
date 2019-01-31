// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SonarJerseyCommon.java

package org.glassfish.jersey.internal.sonar;


public class SonarJerseyCommon
{

            public SonarJerseyCommon()
            {
            }

            public String common()
            {
/*  54*/        return "common";
            }

            public String unitTest()
            {
/*  61*/        return (new StringBuilder()).append(common()).append(" unit test").toString();
            }

            public String e2e()
            {
/*  68*/        return (new StringBuilder()).append(common()).append(" e2e").toString();
            }

            public String integrationTestJvm()
            {
/*  75*/        return (new StringBuilder()).append(common()).append(" test jvm").toString();
            }

            public String integrationServerJvm()
            {
/*  83*/        return (new StringBuilder()).append(common()).append(" server jvm").toString();
            }
}
