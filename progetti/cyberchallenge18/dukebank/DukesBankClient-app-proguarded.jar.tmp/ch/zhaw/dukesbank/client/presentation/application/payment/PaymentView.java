// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PaymentView.java

package ch.zhaw.dukesbank.client.presentation.application.payment;

import com.airhacks.afterburner.views.FXMLView;

// Referenced classes of package ch.zhaw.dukesbank.client.presentation.application.payment:
//            PaymentPresenter

public class PaymentView extends FXMLView
{

            public PaymentView()
            {
            }

            public PaymentPresenter getRealPresenter()
            {
/*  16*/        return (PaymentPresenter)super.getPresenter();
            }
}
