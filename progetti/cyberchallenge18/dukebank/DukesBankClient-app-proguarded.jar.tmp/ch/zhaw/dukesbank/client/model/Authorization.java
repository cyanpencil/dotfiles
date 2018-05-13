// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Authorization.java

package ch.zhaw.dukesbank.client.model;

import javafx.beans.property.*;

public class Authorization
{

            public Authorization()
            {
            }

            public String getUsername()
            {
/*  29*/        return (String)username.get();
            }

            public void setUsername(String s)
            {
/*  33*/        username.set(s);
            }

            public StringProperty usernameProperty()
            {
/*  37*/        return username;
            }

            public String getAuthenticationToken()
            {
/*  41*/        return (String)authenticationToken.get();
            }

            public void setAuthenticationToken(String s)
            {
/*  45*/        authenticationToken.set(s);
            }

            public StringProperty authenticationTokenProperty()
            {
/*  49*/        return authenticationToken;
            }

            public long getSeaSurfTokenSeed()
            {
/*  53*/        return seaSurfTokenSeed.get();
            }

            public void setSeaSurfTokenSeed(long l)
            {
/*  57*/        seaSurfTokenSeed.set(l);
            }

            public LongProperty seaSurfTokenSeedProperty()
            {
/*  61*/        return seaSurfTokenSeed;
            }

            private final StringProperty username = new SimpleStringProperty();
            private final StringProperty authenticationToken = new SimpleStringProperty();
            private final LongProperty seaSurfTokenSeed = new SimpleLongProperty();
}
