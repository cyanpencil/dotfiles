// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AccountView.java

package ch.zhaw.dukesbank.client.presentation.application.account;

import com.airhacks.afterburner.views.FXMLView;

// Referenced classes of package ch.zhaw.dukesbank.client.presentation.application.account:
//            AccountPresenter

public class AccountView extends FXMLView
{

            public AccountView()
            {
            }

            public AccountPresenter getRealPresenter()
            {
/*  16*/        return (AccountPresenter)super.getPresenter();
            }
}
