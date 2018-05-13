// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   VCard.java

package net.glxn.qrgen.vcard;


public class VCard
{

            public VCard()
            {
            }

            public VCard(String s)
            {
/*  45*/        name = s;
            }

            public VCard setName(String s)
            {
/*  49*/        name = s;
/*  50*/        return this;
            }

            public VCard setCompany(String s)
            {
/*  54*/        company = s;
/*  55*/        return this;
            }

            public VCard setPhonenumber(String s)
            {
/*  59*/        phonenumber = s;
/*  60*/        return this;
            }

            public VCard setTitle(String s)
            {
/*  64*/        title = s;
/*  65*/        return this;
            }

            public VCard setEmail(String s)
            {
/*  69*/        email = s;
/*  70*/        return this;
            }

            public VCard setAddress(String s)
            {
/*  74*/        address = s;
/*  75*/        return this;
            }

            public VCard setWebsite(String s)
            {
/*  79*/        website = s;
/*  80*/        return this;
            }

            public String toString()
            {
                StringBuilder stringbuilder;
/*  90*/        (stringbuilder = new StringBuilder()).append("BEGIN:VCARD\n");
/*  92*/        if(name != null)
/*  93*/            stringbuilder.append("N:").append(name);
/*  95*/        if(company != null)
/*  96*/            stringbuilder.append("\nORG:").append(company);
/*  99*/        if(title != null)
/* 100*/            stringbuilder.append("\nTITLE:").append(title);
/* 103*/        if(phonenumber != null)
/* 104*/            stringbuilder.append("\nTEL:").append(phonenumber);
/* 107*/        if(website != null)
/* 108*/            stringbuilder.append("\nURL:").append(website);
/* 110*/        if(email != null)
/* 111*/            stringbuilder.append("\nEMAIL:").append(email);
/* 113*/        if(address != null)
/* 114*/            stringbuilder.append("\nADR:").append(address);
/* 116*/        stringbuilder.append("\nEND:VCARD");
/* 117*/        return stringbuilder.toString();
            }

            private static final String NAME = "N:";
            private static final String COMPANY = "ORG:";
            private static final String TITLE = "TITLE:";
            private static final String PHONE = "TEL:";
            private static final String WEB = "URL:";
            private static final String EMAIL = "EMAIL:";
            private static final String ADDRESS = "ADR:";
            private String name;
            private String company;
            private String title;
            private String phonenumber;
            private String email;
            private String address;
            private String website;
}
