// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EANManufacturerOrgSupport.java

package com.google.zxing.oned;

import java.util.ArrayList;
import java.util.List;

final class EANManufacturerOrgSupport
{

            EANManufacturerOrgSupport()
            {
            }

            final String lookupCountryIdentifier(String s)
            {
/*  37*/        initIfNeeded();
/*  38*/        s = Integer.parseInt(s.substring(0, 3));
/*  39*/        int i = ranges.size();
/*  40*/        for(int j = 0; j < i; j++)
                {
                    int ai[];
/*  41*/            int l = (ai = (int[])ranges.get(j))[0];
/*  43*/            if(s < l)
/*  44*/                return null;
/*  46*/            int k = ai.length != 1 ? ai[1] : l;
/*  47*/            if(s <= k)
/*  48*/                return (String)countryIdentifiers.get(j);
                }

/*  51*/        return null;
            }

            private void add(int ai[], String s)
            {
/*  55*/        ranges.add(ai);
/*  56*/        countryIdentifiers.add(s);
            }

            private synchronized void initIfNeeded()
            {
/*  60*/        if(!ranges.isEmpty())
                {
/*  61*/            return;
                } else
                {
/*  63*/            add(new int[] {
/*  63*/                0, 19
                    }, "US/CA");
/*  64*/            add(new int[] {
/*  64*/                30, 39
                    }, "US");
/*  65*/            add(new int[] {
/*  65*/                60, 139
                    }, "US/CA");
/*  66*/            add(new int[] {
/*  66*/                300, 379
                    }, "FR");
/*  67*/            add(new int[] {
/*  67*/                380
                    }, "BG");
/*  68*/            add(new int[] {
/*  68*/                383
                    }, "SI");
/*  69*/            add(new int[] {
/*  69*/                385
                    }, "HR");
/*  70*/            add(new int[] {
/*  70*/                387
                    }, "BA");
/*  71*/            add(new int[] {
/*  71*/                400, 440
                    }, "DE");
/*  72*/            add(new int[] {
/*  72*/                450, 459
                    }, "JP");
/*  73*/            add(new int[] {
/*  73*/                460, 469
                    }, "RU");
/*  74*/            add(new int[] {
/*  74*/                471
                    }, "TW");
/*  75*/            add(new int[] {
/*  75*/                474
                    }, "EE");
/*  76*/            add(new int[] {
/*  76*/                475
                    }, "LV");
/*  77*/            add(new int[] {
/*  77*/                476
                    }, "AZ");
/*  78*/            add(new int[] {
/*  78*/                477
                    }, "LT");
/*  79*/            add(new int[] {
/*  79*/                478
                    }, "UZ");
/*  80*/            add(new int[] {
/*  80*/                479
                    }, "LK");
/*  81*/            add(new int[] {
/*  81*/                480
                    }, "PH");
/*  82*/            add(new int[] {
/*  82*/                481
                    }, "BY");
/*  83*/            add(new int[] {
/*  83*/                482
                    }, "UA");
/*  84*/            add(new int[] {
/*  84*/                484
                    }, "MD");
/*  85*/            add(new int[] {
/*  85*/                485
                    }, "AM");
/*  86*/            add(new int[] {
/*  86*/                486
                    }, "GE");
/*  87*/            add(new int[] {
/*  87*/                487
                    }, "KZ");
/*  88*/            add(new int[] {
/*  88*/                489
                    }, "HK");
/*  89*/            add(new int[] {
/*  89*/                490, 499
                    }, "JP");
/*  90*/            add(new int[] {
/*  90*/                500, 509
                    }, "GB");
/*  91*/            add(new int[] {
/*  91*/                520
                    }, "GR");
/*  92*/            add(new int[] {
/*  92*/                528
                    }, "LB");
/*  93*/            add(new int[] {
/*  93*/                529
                    }, "CY");
/*  94*/            add(new int[] {
/*  94*/                531
                    }, "MK");
/*  95*/            add(new int[] {
/*  95*/                535
                    }, "MT");
/*  96*/            add(new int[] {
/*  96*/                539
                    }, "IE");
/*  97*/            add(new int[] {
/*  97*/                540, 549
                    }, "BE/LU");
/*  98*/            add(new int[] {
/*  98*/                560
                    }, "PT");
/*  99*/            add(new int[] {
/*  99*/                569
                    }, "IS");
/* 100*/            add(new int[] {
/* 100*/                570, 579
                    }, "DK");
/* 101*/            add(new int[] {
/* 101*/                590
                    }, "PL");
/* 102*/            add(new int[] {
/* 102*/                594
                    }, "RO");
/* 103*/            add(new int[] {
/* 103*/                599
                    }, "HU");
/* 104*/            add(new int[] {
/* 104*/                600, 601
                    }, "ZA");
/* 105*/            add(new int[] {
/* 105*/                603
                    }, "GH");
/* 106*/            add(new int[] {
/* 106*/                608
                    }, "BH");
/* 107*/            add(new int[] {
/* 107*/                609
                    }, "MU");
/* 108*/            add(new int[] {
/* 108*/                611
                    }, "MA");
/* 109*/            add(new int[] {
/* 109*/                613
                    }, "DZ");
/* 110*/            add(new int[] {
/* 110*/                616
                    }, "KE");
/* 111*/            add(new int[] {
/* 111*/                618
                    }, "CI");
/* 112*/            add(new int[] {
/* 112*/                619
                    }, "TN");
/* 113*/            add(new int[] {
/* 113*/                621
                    }, "SY");
/* 114*/            add(new int[] {
/* 114*/                622
                    }, "EG");
/* 115*/            add(new int[] {
/* 115*/                624
                    }, "LY");
/* 116*/            add(new int[] {
/* 116*/                625
                    }, "JO");
/* 117*/            add(new int[] {
/* 117*/                626
                    }, "IR");
/* 118*/            add(new int[] {
/* 118*/                627
                    }, "KW");
/* 119*/            add(new int[] {
/* 119*/                628
                    }, "SA");
/* 120*/            add(new int[] {
/* 120*/                629
                    }, "AE");
/* 121*/            add(new int[] {
/* 121*/                640, 649
                    }, "FI");
/* 122*/            add(new int[] {
/* 122*/                690, 695
                    }, "CN");
/* 123*/            add(new int[] {
/* 123*/                700, 709
                    }, "NO");
/* 124*/            add(new int[] {
/* 124*/                729
                    }, "IL");
/* 125*/            add(new int[] {
/* 125*/                730, 739
                    }, "SE");
/* 126*/            add(new int[] {
/* 126*/                740
                    }, "GT");
/* 127*/            add(new int[] {
/* 127*/                741
                    }, "SV");
/* 128*/            add(new int[] {
/* 128*/                742
                    }, "HN");
/* 129*/            add(new int[] {
/* 129*/                743
                    }, "NI");
/* 130*/            add(new int[] {
/* 130*/                744
                    }, "CR");
/* 131*/            add(new int[] {
/* 131*/                745
                    }, "PA");
/* 132*/            add(new int[] {
/* 132*/                746
                    }, "DO");
/* 133*/            add(new int[] {
/* 133*/                750
                    }, "MX");
/* 134*/            add(new int[] {
/* 134*/                754, 755
                    }, "CA");
/* 135*/            add(new int[] {
/* 135*/                759
                    }, "VE");
/* 136*/            add(new int[] {
/* 136*/                760, 769
                    }, "CH");
/* 137*/            add(new int[] {
/* 137*/                770
                    }, "CO");
/* 138*/            add(new int[] {
/* 138*/                773
                    }, "UY");
/* 139*/            add(new int[] {
/* 139*/                775
                    }, "PE");
/* 140*/            add(new int[] {
/* 140*/                777
                    }, "BO");
/* 141*/            add(new int[] {
/* 141*/                779
                    }, "AR");
/* 142*/            add(new int[] {
/* 142*/                780
                    }, "CL");
/* 143*/            add(new int[] {
/* 143*/                784
                    }, "PY");
/* 144*/            add(new int[] {
/* 144*/                785
                    }, "PE");
/* 145*/            add(new int[] {
/* 145*/                786
                    }, "EC");
/* 146*/            add(new int[] {
/* 146*/                789, 790
                    }, "BR");
/* 147*/            add(new int[] {
/* 147*/                800, 839
                    }, "IT");
/* 148*/            add(new int[] {
/* 148*/                840, 849
                    }, "ES");
/* 149*/            add(new int[] {
/* 149*/                850
                    }, "CU");
/* 150*/            add(new int[] {
/* 150*/                858
                    }, "SK");
/* 151*/            add(new int[] {
/* 151*/                859
                    }, "CZ");
/* 152*/            add(new int[] {
/* 152*/                860
                    }, "YU");
/* 153*/            add(new int[] {
/* 153*/                865
                    }, "MN");
/* 154*/            add(new int[] {
/* 154*/                867
                    }, "KP");
/* 155*/            add(new int[] {
/* 155*/                868, 869
                    }, "TR");
/* 156*/            add(new int[] {
/* 156*/                870, 879
                    }, "NL");
/* 157*/            add(new int[] {
/* 157*/                880
                    }, "KR");
/* 158*/            add(new int[] {
/* 158*/                885
                    }, "TH");
/* 159*/            add(new int[] {
/* 159*/                888
                    }, "SG");
/* 160*/            add(new int[] {
/* 160*/                890
                    }, "IN");
/* 161*/            add(new int[] {
/* 161*/                893
                    }, "VN");
/* 162*/            add(new int[] {
/* 162*/                896
                    }, "PK");
/* 163*/            add(new int[] {
/* 163*/                899
                    }, "ID");
/* 164*/            add(new int[] {
/* 164*/                900, 919
                    }, "AT");
/* 165*/            add(new int[] {
/* 165*/                930, 939
                    }, "AU");
/* 166*/            add(new int[] {
/* 166*/                940, 949
                    }, "AZ");
/* 167*/            add(new int[] {
/* 167*/                955
                    }, "MY");
/* 168*/            add(new int[] {
/* 168*/                958
                    }, "MO");
/* 169*/            return;
                }
            }

            private final List ranges = new ArrayList();
            private final List countryIdentifiers = new ArrayList();
}
