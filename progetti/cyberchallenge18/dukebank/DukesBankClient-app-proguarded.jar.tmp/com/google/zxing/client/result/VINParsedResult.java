// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   VINParsedResult.java

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class VINParsedResult extends ParsedResult
{

            public VINParsedResult(String s, String s1, String s2, String s3, String s4, String s5, int i, 
                    char c, String s6)
            {
/*  42*/        super(ParsedResultType.VIN);
/*  43*/        vin = s;
/*  44*/        worldManufacturerID = s1;
/*  45*/        vehicleDescriptorSection = s2;
/*  46*/        vehicleIdentifierSection = s3;
/*  47*/        countryCode = s4;
/*  48*/        vehicleAttributes = s5;
/*  49*/        modelYear = i;
/*  50*/        plantCode = c;
/*  51*/        sequentialNumber = s6;
            }

            public final String getVIN()
            {
/*  55*/        return vin;
            }

            public final String getWorldManufacturerID()
            {
/*  59*/        return worldManufacturerID;
            }

            public final String getVehicleDescriptorSection()
            {
/*  63*/        return vehicleDescriptorSection;
            }

            public final String getVehicleIdentifierSection()
            {
/*  67*/        return vehicleIdentifierSection;
            }

            public final String getCountryCode()
            {
/*  71*/        return countryCode;
            }

            public final String getVehicleAttributes()
            {
/*  75*/        return vehicleAttributes;
            }

            public final int getModelYear()
            {
/*  79*/        return modelYear;
            }

            public final char getPlantCode()
            {
/*  83*/        return plantCode;
            }

            public final String getSequentialNumber()
            {
/*  87*/        return sequentialNumber;
            }

            public final String getDisplayResult()
            {
                StringBuilder stringbuilder;
/*  92*/        (stringbuilder = new StringBuilder(50)).append(worldManufacturerID).append(' ');
/*  94*/        stringbuilder.append(vehicleDescriptorSection).append(' ');
/*  95*/        stringbuilder.append(vehicleIdentifierSection).append('\n');
/*  96*/        if(countryCode != null)
/*  97*/            stringbuilder.append(countryCode).append(' ');
/*  99*/        stringbuilder.append(modelYear).append(' ');
/* 100*/        stringbuilder.append(plantCode).append(' ');
/* 101*/        stringbuilder.append(sequentialNumber).append('\n');
/* 102*/        return stringbuilder.toString();
            }

            private final String vin;
            private final String worldManufacturerID;
            private final String vehicleDescriptorSection;
            private final String vehicleIdentifierSection;
            private final String countryCode;
            private final String vehicleAttributes;
            private final int modelYear;
            private final char plantCode;
            private final String sequentialNumber;
}
