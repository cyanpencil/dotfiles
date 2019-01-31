// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AuthenticationPresenter.java

package ch.zhaw.dukesbank.client.presentation.authentication;

import a.b;
import ch.zhaw.dukesbank.client.model.Authorization;
import ch.zhaw.dukesbank.client.presentation.application.ApplicationView;
import e.e;
import e.g;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class AuthenticationPresenter
    implements Initializable
{

            public AuthenticationPresenter()
            {
/*  31*/        resources = null;
            }

            public void initialize(URL url, ResourceBundle resourcebundle)
            {
/*  35*/        resources = resourcebundle;
            }

            public void login()
            {
                Authorization authorization;
/*  40*/        if((authorization = b.a(username.getText(), password.getText())) != null)
                {
/*  43*/            e.b.a().a(authorization);
/*  44*/            e.a().a(authorization.getSeaSurfTokenSeed());
/*  45*/            g.a().a(new ApplicationView());
                }
            }

            private TextField username;
            private TextField password;
            private ResourceBundle resources;
}
