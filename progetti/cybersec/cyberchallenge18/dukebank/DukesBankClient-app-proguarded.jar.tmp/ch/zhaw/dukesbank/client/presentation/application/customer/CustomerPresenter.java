// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CustomerPresenter.java

package ch.zhaw.dukesbank.client.presentation.application.customer;

import a.c;
import ch.zhaw.dukesbank.client.model.Customer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class CustomerPresenter
    implements Initializable
{

            public CustomerPresenter()
            {
/*  67*/        resources = null;
            }

            public void initialize(URL url, ResourceBundle resourcebundle)
            {
/*  71*/        resources = resourcebundle;
/*  72*/        refresh();
            }

            public void refresh()
            {
/*  77*/        e.c.a().a(c.a());
/*  78*/        Customer customer = e.c.a().b();
/*  80*/        id.setText(String.valueOf(customer.getId()));
/*  81*/        gender.setText(customer.getGender());
/*  82*/        firstname.setText(customer.getFirstname());
/*  83*/        lastname.setText(customer.getLastname());
/*  84*/        street.setText(customer.getStreet());
/*  85*/        city.setText(customer.getCity());
/*  86*/        zip.setText(customer.getZip());
/*  87*/        countryCode.setText(customer.getCountryCode());
/*  88*/        country.setText(customer.getCountry());
/*  89*/        email.setText(customer.getEmail());
/*  90*/        username.setText(customer.getUsername());
/*  91*/        phone.setText(customer.getPhone());
/*  92*/        phoneCountryNumber.setText(customer.getPhoneCountryCode());
/*  93*/        birthday.setText(customer.getBirthday());
/*  94*/        ccType.setText(customer.getCcType());
            }

            private TextField id;
            private TextField gender;
            private TextField firstname;
            private TextField lastname;
            private TextField street;
            private TextField city;
            private TextField zip;
            private TextField countryCode;
            private TextField country;
            private TextField email;
            private TextField username;
            private TextField phone;
            private TextField phoneCountryNumber;
            private TextField birthday;
            private TextField ccType;
            private ResourceBundle resources;
}
