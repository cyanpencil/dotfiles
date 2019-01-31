// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Priorities.java

package javax.ws.rs;


public final class Priorities
{

            private Priorities()
            {
            }

            public static final int AUTHENTICATION = 1000;
            public static final int AUTHORIZATION = 2000;
            public static final int HEADER_DECORATOR = 3000;
            public static final int ENTITY_CODER = 4000;
            public static final int USER = 5000;
}
