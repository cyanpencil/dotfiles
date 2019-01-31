// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LicensePresenter.java

package ch.zhaw.dukesbank.client.presentation.license;

import a.d;
import ch.zhaw.dukesbank.client.presentation.authentication.AuthenticationView;
import e.f;
import e.g;
import f.a;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class LicensePresenter
    implements Initializable
{

            public LicensePresenter()
            {
/*  45*/        resources = null;
            }

            public void initialize(URL url, ResourceBundle resourcebundle)
            {
/*  49*/        resources = resourcebundle;
            }

            public void verify()
            {
/*  54*/        f.a().a(restBaseUrl.getText());
/*  56*/        String s = firstname.getText();
/*  57*/        String s1 = lastname.getText();
/*  58*/        String s2 = street.getText();
/*  59*/        String s3 = city.getText();
/*  60*/        String s4 = zip.getText();
/*  61*/        String s5 = license.getText();
/*  63*/        if(a.a(s, s1, s2, s3, s4, s5))
                {
/*  64*/            d.a(s5);
/*  65*/            g.a().a(new AuthenticationView());
/*  65*/            return;
                } else
                {
/*  67*/            System.out.println("License not Valid...");
/*  69*/            return;
                }
            }

            private TextField restBaseUrl;
            private TextField firstname;
            private TextField lastname;
            private TextField street;
            private TextField city;
            private TextField zip;
            private TextField license;
            private ResourceBundle resources;
}
