// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ScanPresenter.java

package ch.zhaw.dukesbank.client.presentation.application.scan;

import a.e;
import ch.zhaw.dukesbank.client.model.Scan;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public class ScanPresenter
    implements Initializable
{

            public ScanPresenter()
            {
/*  29*/        resources = null;
            }

            public void initialize(URL url, ResourceBundle resourcebundle)
            {
/*  33*/        resources = resourcebundle;
/*  34*/        refresh();
            }

            public void refresh()
            {
/*  39*/        Object obj = e.a();
/*  41*/        obj = QRCode.from(((String) (obj = (new StringBuilder()).append(((Scan) (obj)).getClientTimestamp()).append(System.getProperty("line.separator")).append(((Scan) (obj)).getServerTimestamp()).append(System.getProperty("line.separator")).append(((Scan) (obj)).getUsername()).append(System.getProperty("line.separator")).append("-----").append(System.getProperty("line.separator")).append(((Scan) (obj)).getSalt()).append(System.getProperty("line.separator")).append(((Scan) (obj)).getSecureRandomContent()).append(System.getProperty("line.separator")).append("-----").toString()))).to(ImageType.PNG).withSize(400, 400).withCharset("UTF-8").stream();
/*  51*/        obj = new ByteArrayInputStream(((ByteArrayOutputStream) (obj)).toByteArray());
/*  52*/        qrCode.setImage(new Image(((java.io.InputStream) (obj))));
            }

            private ImageView qrCode;
            private ResourceBundle resources;
}
