// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Customer.java

package ch.zhaw.dukesbank.client.model;

import javafx.beans.property.*;

public class Customer
{

            public Customer()
            {
            }

            public Integer getId()
            {
/*  53*/        return Integer.valueOf(id.get());
            }

            public void setId(Integer integer)
            {
/*  57*/        id.set(integer.intValue());
            }

            public IntegerProperty idProperty()
            {
/*  61*/        return id;
            }

            public String getGender()
            {
/*  65*/        return (String)gender.get();
            }

            public void setGender(String s)
            {
/*  69*/        gender.set(s);
            }

            public StringProperty genderProperty()
            {
/*  73*/        return gender;
            }

            public String getFirstname()
            {
/*  77*/        return (String)firstname.get();
            }

            public void setFirstname(String s)
            {
/*  81*/        firstname.set(s);
            }

            public StringProperty firstnameProperty()
            {
/*  85*/        return firstname;
            }

            public String getLastname()
            {
/*  89*/        return (String)lastname.get();
            }

            public void setLastname(String s)
            {
/*  93*/        lastname.set(s);
            }

            public StringProperty lastnameProperty()
            {
/*  97*/        return lastname;
            }

            public String getStreet()
            {
/* 101*/        return (String)street.get();
            }

            public void setStreet(String s)
            {
/* 105*/        street.set(s);
            }

            public StringProperty streetProperty()
            {
/* 109*/        return street;
            }

            public String getCity()
            {
/* 113*/        return (String)city.get();
            }

            public void setCity(String s)
            {
/* 117*/        city.set(s);
            }

            public StringProperty cityProperty()
            {
/* 121*/        return city;
            }

            public String getZip()
            {
/* 125*/        return (String)zip.get();
            }

            public void setZip(String s)
            {
/* 129*/        zip.set(s);
            }

            public StringProperty zipProperty()
            {
/* 133*/        return zip;
            }

            public String getCountryCode()
            {
/* 137*/        return (String)countryCode.get();
            }

            public void setCountryCode(String s)
            {
/* 141*/        countryCode.set(s);
            }

            public StringProperty countryCodeProperty()
            {
/* 145*/        return countryCode;
            }

            public String getCountry()
            {
/* 149*/        return (String)country.get();
            }

            public void setCountry(String s)
            {
/* 153*/        country.set(s);
            }

            public StringProperty countryProperty()
            {
/* 157*/        return country;
            }

            public String getEmail()
            {
/* 161*/        return (String)email.get();
            }

            public void setEmail(String s)
            {
/* 165*/        email.set(s);
            }

            public StringProperty emailProperty()
            {
/* 169*/        return email;
            }

            public String getUsername()
            {
/* 173*/        return (String)username.get();
            }

            public void setUsername(String s)
            {
/* 177*/        username.set(s);
            }

            public StringProperty usernameProperty()
            {
/* 181*/        return username;
            }

            public String getPhone()
            {
/* 185*/        return (String)phone.get();
            }

            public void setPhone(String s)
            {
/* 189*/        phone.set(s);
            }

            public StringProperty phoneProperty()
            {
/* 193*/        return phone;
            }

            public String getPhoneCountryCode()
            {
/* 197*/        return (String)phoneCountryCode.get();
            }

            public void setPhoneCountryCode(String s)
            {
/* 201*/        phoneCountryCode.set(s);
            }

            public StringProperty phoneCountryCodeProperty()
            {
/* 205*/        return phoneCountryCode;
            }

            public String getBirthday()
            {
/* 209*/        return (String)birthday.get();
            }

            public void setBirthday(String s)
            {
/* 213*/        birthday.set(s);
            }

            public StringProperty birthdayProperty()
            {
/* 217*/        return birthday;
            }

            public String getCcType()
            {
/* 221*/        return (String)ccType.get();
            }

            public void setCcType(String s)
            {
/* 225*/        ccType.set(s);
            }

            public StringProperty ccTypeProperty()
            {
/* 229*/        return ccType;
            }

            private final IntegerProperty id = new SimpleIntegerProperty();
            private final StringProperty gender = new SimpleStringProperty();
            private final StringProperty firstname = new SimpleStringProperty();
            private final StringProperty lastname = new SimpleStringProperty();
            private final StringProperty street = new SimpleStringProperty();
            private final StringProperty city = new SimpleStringProperty();
            private final StringProperty zip = new SimpleStringProperty();
            private final StringProperty countryCode = new SimpleStringProperty();
            private final StringProperty country = new SimpleStringProperty();
            private final StringProperty email = new SimpleStringProperty();
            private final StringProperty username = new SimpleStringProperty();
            private final StringProperty phone = new SimpleStringProperty();
            private final StringProperty phoneCountryCode = new SimpleStringProperty();
            private final StringProperty birthday = new SimpleStringProperty();
            private final StringProperty ccType = new SimpleStringProperty();
}
