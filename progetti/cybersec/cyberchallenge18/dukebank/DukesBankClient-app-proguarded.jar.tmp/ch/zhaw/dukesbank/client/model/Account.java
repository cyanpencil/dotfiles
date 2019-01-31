// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Account.java

package ch.zhaw.dukesbank.client.model;

import javafx.beans.property.*;

public class Account
{

            public Account()
            {
            }

            public Integer getId()
            {
/*  37*/        return Integer.valueOf(id.get());
            }

            public void setId(Integer integer)
            {
/*  41*/        id.set(integer.intValue());
            }

            public IntegerProperty idProperty()
            {
/*  45*/        return id;
            }

            public String getDescription()
            {
/*  49*/        return (String)description.get();
            }

            public void setDescription(String s)
            {
/*  53*/        description.set(s);
            }

            public StringProperty descriptionProperty()
            {
/*  57*/        return description;
            }

            public long getBalance()
            {
/*  61*/        return balance.get();
            }

            public void setBalance(long l)
            {
/*  65*/        balance.set(l);
            }

            public LongProperty balanceProperty()
            {
/*  69*/        return balance;
            }

            public String getIban()
            {
/*  73*/        return (String)iban.get();
            }

            public void setIban(String s)
            {
/*  77*/        iban.set(s);
            }

            public StringProperty ibanProperty()
            {
/*  81*/        return iban;
            }

            public String getAccountNumber()
            {
/*  85*/        return (String)accountNumber.get();
            }

            public void setAccountNumber(String s)
            {
/*  89*/        accountNumber.set(s);
            }

            public StringProperty accountNumberProperty()
            {
/*  93*/        return accountNumber;
            }

            public String getBlackCardVoucher()
            {
/*  97*/        return (String)blackCardVoucher.get();
            }

            public void setBlackCardVoucher(String s)
            {
/* 101*/        blackCardVoucher.set(s);
            }

            public StringProperty blackCardVoucherProperty()
            {
/* 105*/        return blackCardVoucher;
            }

            private final IntegerProperty id = new SimpleIntegerProperty();
            private final StringProperty description = new SimpleStringProperty();
            private final LongProperty balance = new SimpleLongProperty();
            private final StringProperty iban = new SimpleStringProperty();
            private final StringProperty accountNumber = new SimpleStringProperty();
            private final StringProperty blackCardVoucher = new SimpleStringProperty();
}
