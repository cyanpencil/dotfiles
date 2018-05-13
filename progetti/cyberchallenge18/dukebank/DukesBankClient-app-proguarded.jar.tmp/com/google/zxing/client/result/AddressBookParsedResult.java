// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AddressBookParsedResult.java

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class AddressBookParsedResult extends ParsedResult
{

            public AddressBookParsedResult(String as[], String as1[], String as2[], String as3[], String as4[], String as5[], String as6[])
            {
/*  48*/        this(as, null, null, as1, as2, as3, as4, null, null, as5, as6, null, null, null, null, null);
            }

            public AddressBookParsedResult(String as[], String as1[], String s, String as2[], String as3[], String as4[], String as5[], 
                    String s1, String s2, String as6[], String as7[], String s3, String s4, String s5, 
                    String as8[], String as9[])
            {
/*  82*/        super(ParsedResultType.ADDRESSBOOK);
/*  83*/        names = as;
/*  84*/        nicknames = as1;
/*  85*/        pronunciation = s;
/*  86*/        phoneNumbers = as2;
/*  87*/        phoneTypes = as3;
/*  88*/        emails = as4;
/*  89*/        emailTypes = as5;
/*  90*/        instantMessenger = s1;
/*  91*/        note = s2;
/*  92*/        addresses = as6;
/*  93*/        addressTypes = as7;
/*  94*/        org = s3;
/*  95*/        birthday = s4;
/*  96*/        title = s5;
/*  97*/        urls = as8;
/*  98*/        geo = as9;
            }

            public final String[] getNames()
            {
/* 102*/        return names;
            }

            public final String[] getNicknames()
            {
/* 106*/        return nicknames;
            }

            public final String getPronunciation()
            {
/* 116*/        return pronunciation;
            }

            public final String[] getPhoneNumbers()
            {
/* 120*/        return phoneNumbers;
            }

            public final String[] getPhoneTypes()
            {
/* 128*/        return phoneTypes;
            }

            public final String[] getEmails()
            {
/* 132*/        return emails;
            }

            public final String[] getEmailTypes()
            {
/* 140*/        return emailTypes;
            }

            public final String getInstantMessenger()
            {
/* 144*/        return instantMessenger;
            }

            public final String getNote()
            {
/* 148*/        return note;
            }

            public final String[] getAddresses()
            {
/* 152*/        return addresses;
            }

            public final String[] getAddressTypes()
            {
/* 160*/        return addressTypes;
            }

            public final String getTitle()
            {
/* 164*/        return title;
            }

            public final String getOrg()
            {
/* 168*/        return org;
            }

            public final String[] getURLs()
            {
/* 172*/        return urls;
            }

            public final String getBirthday()
            {
/* 179*/        return birthday;
            }

            public final String[] getGeo()
            {
/* 186*/        return geo;
            }

            public final String getDisplayResult()
            {
/* 191*/        StringBuilder stringbuilder = new StringBuilder(100);
/* 192*/        maybeAppend(names, stringbuilder);
/* 193*/        maybeAppend(nicknames, stringbuilder);
/* 194*/        maybeAppend(pronunciation, stringbuilder);
/* 195*/        maybeAppend(title, stringbuilder);
/* 196*/        maybeAppend(org, stringbuilder);
/* 197*/        maybeAppend(addresses, stringbuilder);
/* 198*/        maybeAppend(phoneNumbers, stringbuilder);
/* 199*/        maybeAppend(emails, stringbuilder);
/* 200*/        maybeAppend(instantMessenger, stringbuilder);
/* 201*/        maybeAppend(urls, stringbuilder);
/* 202*/        maybeAppend(birthday, stringbuilder);
/* 203*/        maybeAppend(geo, stringbuilder);
/* 204*/        maybeAppend(note, stringbuilder);
/* 205*/        return stringbuilder.toString();
            }

            private final String names[];
            private final String nicknames[];
            private final String pronunciation;
            private final String phoneNumbers[];
            private final String phoneTypes[];
            private final String emails[];
            private final String emailTypes[];
            private final String instantMessenger;
            private final String note;
            private final String addresses[];
            private final String addressTypes[];
            private final String org;
            private final String birthday;
            private final String title;
            private final String urls[];
            private final String geo[];
}
