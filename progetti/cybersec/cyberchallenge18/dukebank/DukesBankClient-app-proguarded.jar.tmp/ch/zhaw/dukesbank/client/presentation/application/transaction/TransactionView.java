// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TransactionView.java

package ch.zhaw.dukesbank.client.presentation.application.transaction;

import com.airhacks.afterburner.views.FXMLView;

// Referenced classes of package ch.zhaw.dukesbank.client.presentation.application.transaction:
//            TransactionPresenter

public class TransactionView extends FXMLView
{

            public TransactionView()
            {
            }

            public TransactionPresenter getRealPresenter()
            {
/*  16*/        return (TransactionPresenter)super.getPresenter();
            }
}
