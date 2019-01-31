// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ApplicationPresenter.java

package ch.zhaw.dukesbank.client.presentation.application;

import a.b;
import ch.zhaw.dukesbank.client.presentation.application.account.AccountPresenter;
import ch.zhaw.dukesbank.client.presentation.application.account.AccountView;
import ch.zhaw.dukesbank.client.presentation.application.customer.CustomerPresenter;
import ch.zhaw.dukesbank.client.presentation.application.customer.CustomerView;
import ch.zhaw.dukesbank.client.presentation.application.payment.PaymentPresenter;
import ch.zhaw.dukesbank.client.presentation.application.payment.PaymentView;
import ch.zhaw.dukesbank.client.presentation.application.scan.ScanPresenter;
import ch.zhaw.dukesbank.client.presentation.application.scan.ScanView;
import ch.zhaw.dukesbank.client.presentation.application.transaction.TransactionPresenter;
import ch.zhaw.dukesbank.client.presentation.application.transaction.TransactionView;
import ch.zhaw.dukesbank.client.presentation.authentication.AuthenticationView;
import e.g;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

public class ApplicationPresenter
    implements Initializable
{

            public ApplicationPresenter()
            {
/*  47*/        resources = null;
            }

            public void initialize(URL url, ResourceBundle resourcebundle)
            {
/*  57*/        resources = resourcebundle;
/*  59*/        url = new CustomerView();
/*  60*/        customerPresenter = (CustomerPresenter)url.getPresenter();
/*  61*/        customer.setContent(url.getView());
/*  63*/        url = new AccountView();
/*  64*/        accountPresenter = (AccountPresenter)url.getPresenter();
/*  65*/        account.setContent(url.getView());
/*  67*/        url = new TransactionView();
/*  68*/        transactionPresenter = (TransactionPresenter)url.getPresenter();
/*  69*/        transaction.setContent(url.getView());
/*  71*/        url = new PaymentView();
/*  72*/        paymentPresenter = (PaymentPresenter)url.getPresenter();
/*  73*/        payment.setContent(url.getView());
/*  75*/        url = new ScanView();
/*  76*/        scanPresenter = (ScanPresenter)url.getPresenter();
/*  77*/        scan.setContent(url.getView());
            }

            public void logout()
            {
/*  82*/        b.a();
/*  83*/        g.a().a(new AuthenticationView());
            }

            private Tab customer;
            private Tab account;
            private Tab transaction;
            private Tab payment;
            private Tab scan;
            private ResourceBundle resources;
            private CustomerPresenter customerPresenter;
            private AccountPresenter accountPresenter;
            private TransactionPresenter transactionPresenter;
            private PaymentPresenter paymentPresenter;
            private ScanPresenter scanPresenter;
}
