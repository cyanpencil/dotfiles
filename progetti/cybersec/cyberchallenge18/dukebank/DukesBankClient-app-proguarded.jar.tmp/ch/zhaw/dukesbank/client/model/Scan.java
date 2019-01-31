// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Scan.java

package ch.zhaw.dukesbank.client.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Scan
{

            public Scan()
            {
            }

            public String getClientTimestamp()
            {
/*  31*/        return (String)clientTimestamp.get();
            }

            public void setClientTimestamp(String s)
            {
/*  35*/        clientTimestamp.set(s);
            }

            public StringProperty clientTimestampProperty()
            {
/*  39*/        return clientTimestamp;
            }

            public String getServerTimestamp()
            {
/*  43*/        return (String)serverTimestamp.get();
            }

            public void setServerTimestamp(String s)
            {
/*  47*/        serverTimestamp.set(s);
            }

            public StringProperty serverTimestampProperty()
            {
/*  51*/        return serverTimestamp;
            }

            public String getUsername()
            {
/*  55*/        return (String)username.get();
            }

            public void setUsername(String s)
            {
/*  59*/        username.set(s);
            }

            public StringProperty usernameProperty()
            {
/*  63*/        return username;
            }

            public String getSalt()
            {
/*  67*/        return (String)salt.get();
            }

            public void setSalt(String s)
            {
/*  71*/        salt.set(s);
            }

            public StringProperty saltProperty()
            {
/*  75*/        return salt;
            }

            public String getSecureRandomContent()
            {
/*  79*/        return (String)secureRandomContent.get();
            }

            public void setSecureRandomContent(String s)
            {
/*  83*/        secureRandomContent.set(s);
            }

            public StringProperty secureRandomContentProperty()
            {
/*  87*/        return secureRandomContent;
            }

            private final StringProperty clientTimestamp = new SimpleStringProperty();
            private final StringProperty serverTimestamp = new SimpleStringProperty();
            private final StringProperty username = new SimpleStringProperty();
            private final StringProperty salt = new SimpleStringProperty();
            private final StringProperty secureRandomContent = new SimpleStringProperty();
}
