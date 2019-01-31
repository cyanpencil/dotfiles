// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PaymentPresenter.java

package ch.zhaw.dukesbank.client.presentation.application.payment;

import a.f;
import ch.zhaw.dukesbank.client.model.Transaction;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class PaymentPresenter
    implements Initializable
{

            public PaymentPresenter()
            {
/*  30*/        resources = null;
            }

            public void initialize(URL url, ResourceBundle resourcebundle)
            {
/*  34*/        resources = resourcebundle;
            }

            public void submit()
            {
                Transaction transaction;
/*  39*/        (transaction = new Transaction()).setAmount(Long.parseLong(amount.getText()));
/*  41*/        transaction.setDestinationIban(destinationIban.getText());
/*  42*/        transaction.setDescription(description.getText());
/*  43*/        f.a(transaction);
/*  45*/        reset();
            }

            public void reset()
            {
/*  50*/        amount.setText("");
/*  51*/        destinationIban.setText("");
/*  52*/        description.setText("");
            }

            private TextField amount;
            private TextField destinationIban;
            private TextField description;
            private ResourceBundle resources;
}
