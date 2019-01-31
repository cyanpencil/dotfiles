// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CustomerView.java

package ch.zhaw.dukesbank.client.presentation.application.customer;

import com.airhacks.afterburner.views.FXMLView;

// Referenced classes of package ch.zhaw.dukesbank.client.presentation.application.customer:
//            CustomerPresenter

public class CustomerView extends FXMLView
{

            public CustomerView()
            {
            }

            public CustomerPresenter getRealPresenter()
            {
/*  16*/        return (CustomerPresenter)super.getPresenter();
            }
}
