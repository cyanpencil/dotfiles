// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   License.java

package ch.zhaw.dukesbank.client.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class License
{

            public License()
            {
            }

            public String getLicense()
            {
/*  25*/        return (String)license.get();
            }

            public void setLicense(String s)
            {
/*  29*/        license.set(s);
            }

            public StringProperty licenseProperty()
            {
/*  33*/        return license;
            }

            public String getLicenseToken()
            {
/*  37*/        return (String)licenseToken.get();
            }

            public void setLicenseToken(String s)
            {
/*  41*/        licenseToken.set(s);
            }

            public StringProperty licenseTokenProperty()
            {
/*  45*/        return licenseToken;
            }

            private final StringProperty license = new SimpleStringProperty();
            private final StringProperty licenseToken = new SimpleStringProperty();
}
