// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ScanView.java

package ch.zhaw.dukesbank.client.presentation.application.scan;

import com.airhacks.afterburner.views.FXMLView;

// Referenced classes of package ch.zhaw.dukesbank.client.presentation.application.scan:
//            ScanPresenter

public class ScanView extends FXMLView
{

            public ScanView()
            {
            }

            public ScanPresenter getRealPresenter()
            {
/*  16*/        return (ScanPresenter)super.getPresenter();
            }
}
