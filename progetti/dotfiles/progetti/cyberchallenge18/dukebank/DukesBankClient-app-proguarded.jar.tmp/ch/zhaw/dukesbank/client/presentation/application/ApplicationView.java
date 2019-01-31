// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ApplicationView.java

package ch.zhaw.dukesbank.client.presentation.application;

import com.airhacks.afterburner.views.FXMLView;

// Referenced classes of package ch.zhaw.dukesbank.client.presentation.application:
//            ApplicationPresenter

public class ApplicationView extends FXMLView
{

            public ApplicationView()
            {
            }

            public ApplicationPresenter getRealPresenter()
            {
/*  16*/        return (ApplicationPresenter)super.getPresenter();
            }
}
