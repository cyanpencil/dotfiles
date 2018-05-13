// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Transaction.java

package ch.zhaw.dukesbank.client.model;

import javafx.beans.property.*;

public class Transaction
{

            public Transaction()
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

            public String getTimestamp()
            {
/*  49*/        return (String)timestamp.get();
            }

            public void setTimestamp(String s)
            {
/*  53*/        timestamp.set(s);
            }

            public StringProperty timestampProperty()
            {
/*  57*/        return timestamp;
            }

            public long getAmount()
            {
/*  61*/        return amount.get();
            }

            public void setAmount(long l)
            {
/*  65*/        amount.set(l);
            }

            public LongProperty amountProperty()
            {
/*  69*/        return amount;
            }

            public String getDescription()
            {
/*  73*/        return (String)description.get();
            }

            public void setDescription(String s)
            {
/*  77*/        description.set(s);
            }

            public StringProperty descriptionProperty()
            {
/*  81*/        return description;
            }

            public String getSourceIban()
            {
/*  85*/        return (String)sourceIban.get();
            }

            public void setSourceIban(String s)
            {
/*  89*/        sourceIban.set(s);
            }

            public StringProperty sourceIbanProperty()
            {
/*  93*/        return sourceIban;
            }

            public String getDestinationIban()
            {
/*  97*/        return (String)destinationIban.get();
            }

            public void setDestinationIban(String s)
            {
/* 101*/        destinationIban.set(s);
            }

            public StringProperty destinationIbanProperty()
            {
/* 105*/        return destinationIban;
            }

            private final IntegerProperty id = new SimpleIntegerProperty();
            private final StringProperty timestamp = new SimpleStringProperty();
            private final LongProperty amount = new SimpleLongProperty();
            private final StringProperty description = new SimpleStringProperty();
            private final StringProperty sourceIban = new SimpleStringProperty();
            private final StringProperty destinationIban = new SimpleStringProperty();
}
