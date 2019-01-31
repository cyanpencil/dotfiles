// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Credential.java

package ch.zhaw.dukesbank.client.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Credential
{

            public Credential()
            {
            }

            public String getUsername()
            {
/*  25*/        return (String)username.get();
            }

            public void setUsername(String s)
            {
/*  29*/        username.set(s);
            }

            public StringProperty usernameProperty()
            {
/*  33*/        return username;
            }

            public String getPassword()
            {
/*  37*/        return (String)password.get();
            }

            public void setPassword(String s)
            {
/*  41*/        password.set(s);
            }

            public StringProperty passwordProperty()
            {
/*  45*/        return password;
            }

            private final StringProperty username = new SimpleStringProperty();
            private final StringProperty password = new SimpleStringProperty();
}
