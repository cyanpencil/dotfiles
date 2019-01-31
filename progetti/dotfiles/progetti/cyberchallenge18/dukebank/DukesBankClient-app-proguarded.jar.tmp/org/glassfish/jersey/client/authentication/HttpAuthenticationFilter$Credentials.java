// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpAuthenticationFilter.java

package org.glassfish.jersey.client.authentication;


// Referenced classes of package org.glassfish.jersey.client.authentication:
//            HttpAuthenticationFilter

static class T
{

            String getUsername()
            {
/* 376*/        return username;
            }

            byte[] getPassword()
            {
/* 385*/        return password;
            }

            private final String username;
            private final byte password[];

            (String s, byte abyte0[])
            {
/* 355*/        username = s;
/* 356*/        password = abyte0;
            }

            password(String s, String s1)
            {
/* 366*/        username = s;
/* 367*/        password = s1 != null ? s1.getBytes(HttpAuthenticationFilter.CHARACTER_SET) : null;
            }
}
