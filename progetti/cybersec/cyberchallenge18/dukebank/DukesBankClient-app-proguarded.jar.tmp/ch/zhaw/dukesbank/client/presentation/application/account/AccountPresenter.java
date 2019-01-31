// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AccountPresenter.java

package ch.zhaw.dukesbank.client.presentation.application.account;

import a.a;
import ch.zhaw.dukesbank.client.model.Account;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class AccountPresenter
    implements Initializable
{

            public AccountPresenter()
            {
/*  40*/        resources = null;
            }

            public void initialize(URL url, ResourceBundle resourcebundle)
            {
/*  44*/        resources = resourcebundle;
/*  45*/        refresh();
            }

            public void refresh()
            {
/*  50*/        e.a.a().a(a.a());
/*  51*/        Account account = e.a.a().b();
/*  53*/        id.setText(String.valueOf(account.getId()));
/*  54*/        accountNumber.setText(account.getAccountNumber());
/*  55*/        iban.setText(account.getIban());
/*  56*/        balance.setText(String.valueOf(account.getBalance()));
/*  57*/        description.setText(account.getDescription());
/*  59*/        if(account.getBlackCardVoucher() != null)
/*  60*/            blackCardVoucher.setText(account.getBlackCardVoucher());
            }

            private TextField id;
            private TextField accountNumber;
            private TextField iban;
            private TextField balance;
            private TextField description;
            private TextField blackCardVoucher;
            private ResourceBundle resources;
}
