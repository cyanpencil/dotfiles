// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FieldParser.java

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;

final class FieldParser
{

            private FieldParser()
            {
            }

            static String parseFieldsInGeneralPurpose(String s)
                throws NotFoundException
            {
/* 194*/        if(s.isEmpty())
/* 195*/            return null;
/* 200*/        if(s.length() < 2)
/* 201*/            throw NotFoundException.getNotFoundInstance();
/* 204*/        String s1 = s.substring(0, 2);
                Object aobj2[][];
/* 206*/        int j = (aobj2 = TWO_DIGIT_DATA_LENGTH).length;
/* 206*/        for(int k = 0; k < j; k++)
                {
                    Object aobj6[];
/* 206*/            if((aobj6 = aobj2[k])[0].equals(s1))
/* 208*/                if(aobj6[1] == VARIABLE_LENGTH)
/* 209*/                    return processVariableAI(2, ((Integer)aobj6[2]).intValue(), s);
/* 211*/                else
/* 211*/                    return processFixedAI(2, ((Integer)aobj6[1]).intValue(), s);
                }

/* 215*/        if(s.length() < 3)
/* 216*/            throw NotFoundException.getNotFoundInstance();
/* 219*/        String s2 = s.substring(0, 3);
                Object aobj4[][];
/* 221*/        int l = (aobj4 = THREE_DIGIT_DATA_LENGTH).length;
/* 221*/        for(int i1 = 0; i1 < l; i1++)
                {
                    Object aobj[];
/* 221*/            if((aobj = aobj4[i1])[0].equals(s2))
/* 223*/                if(aobj[1] == VARIABLE_LENGTH)
/* 224*/                    return processVariableAI(3, ((Integer)aobj[2]).intValue(), s);
/* 226*/                else
/* 226*/                    return processFixedAI(3, ((Integer)aobj[1]).intValue(), s);
                }

/* 231*/        l = (aobj4 = THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH).length;
/* 231*/        for(int j1 = 0; j1 < l; j1++)
                {
                    Object aobj1[];
/* 231*/            if((aobj1 = aobj4[j1])[0].equals(s2))
/* 233*/                if(aobj1[1] == VARIABLE_LENGTH)
/* 234*/                    return processVariableAI(4, ((Integer)aobj1[2]).intValue(), s);
/* 236*/                else
/* 236*/                    return processFixedAI(4, ((Integer)aobj1[1]).intValue(), s);
                }

/* 240*/        if(s.length() < 4)
/* 241*/            throw NotFoundException.getNotFoundInstance();
/* 244*/        String s3 = s.substring(0, 4);
                Object aobj5[][];
/* 246*/        int k1 = (aobj5 = FOUR_DIGIT_DATA_LENGTH).length;
/* 246*/        for(int i = 0; i < k1; i++)
                {
                    Object aobj3[];
/* 246*/            if((aobj3 = aobj5[i])[0].equals(s3))
/* 248*/                if(aobj3[1] == VARIABLE_LENGTH)
/* 249*/                    return processVariableAI(4, ((Integer)aobj3[2]).intValue(), s);
/* 251*/                else
/* 251*/                    return processFixedAI(4, ((Integer)aobj3[1]).intValue(), s);
                }

/* 255*/        throw NotFoundException.getNotFoundInstance();
            }

            private static String processFixedAI(int i, int j, String s)
                throws NotFoundException
            {
/* 259*/        if(s.length() < i)
/* 260*/            throw NotFoundException.getNotFoundInstance();
/* 263*/        String s1 = s.substring(0, i);
/* 265*/        if(s.length() < i + j)
/* 266*/            throw NotFoundException.getNotFoundInstance();
/* 269*/        String s2 = s.substring(i, i + j);
/* 270*/        i = s.substring(i + j);
/* 271*/        j = (new StringBuilder("(")).append(s1).append(')').append(s2).toString();
/* 272*/        if((i = parseFieldsInGeneralPurpose(i)) == null)
/* 273*/            return j;
/* 273*/        else
/* 273*/            return (new StringBuilder()).append(j).append(i).toString();
            }

            private static String processVariableAI(int i, int j, String s)
                throws NotFoundException
            {
/* 278*/        String s1 = s.substring(0, i);
/* 280*/        if(s.length() < i + j)
/* 281*/            j = s.length();
/* 283*/        else
/* 283*/            j = i + j;
/* 285*/        i = s.substring(i, j);
/* 286*/        j = s.substring(j);
/* 287*/        i = (new StringBuilder("(")).append(s1).append(')').append(i).toString();
/* 288*/        if((j = parseFieldsInGeneralPurpose(j)) == null)
/* 289*/            return i;
/* 289*/        else
/* 289*/            return (new StringBuilder()).append(i).append(j).toString();
            }

            private static final Object VARIABLE_LENGTH;
            private static final Object TWO_DIGIT_DATA_LENGTH[][];
            private static final Object THREE_DIGIT_DATA_LENGTH[][];
            private static final Object THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH[][];
            private static final Object FOUR_DIGIT_DATA_LENGTH[][];

            static 
            {
/*  37*/        VARIABLE_LENGTH = new Object();
/*  39*/        TWO_DIGIT_DATA_LENGTH = (new Object[][] {
/*  39*/            new Object[] {
/*  39*/                "00", Integer.valueOf(18)
                    }, new Object[] {
/*  39*/                "01", Integer.valueOf(14)
                    }, new Object[] {
/*  39*/                "02", Integer.valueOf(14)
                    }, new Object[] {
/*  39*/                "10", VARIABLE_LENGTH, Integer.valueOf(20)
                    }, new Object[] {
/*  39*/                "11", Integer.valueOf(6)
                    }, new Object[] {
/*  39*/                "12", Integer.valueOf(6)
                    }, new Object[] {
/*  39*/                "13", Integer.valueOf(6)
                    }, new Object[] {
/*  39*/                "15", Integer.valueOf(6)
                    }, new Object[] {
/*  39*/                "17", Integer.valueOf(6)
                    }, new Object[] {
/*  39*/                "20", Integer.valueOf(2)
                    }, new Object[] {
/*  39*/                "21", VARIABLE_LENGTH, Integer.valueOf(20)
                    }, new Object[] {
/*  39*/                "22", VARIABLE_LENGTH, Integer.valueOf(29)
                    }, new Object[] {
/*  39*/                "30", VARIABLE_LENGTH, Integer.valueOf(8)
                    }, new Object[] {
/*  39*/                "37", VARIABLE_LENGTH, Integer.valueOf(8)
                    }, new Object[] {
/*  39*/                "90", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/*  39*/                "91", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/*  39*/                "92", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/*  39*/                "93", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/*  39*/                "94", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/*  39*/                "95", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/*  39*/                "96", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/*  39*/                "97", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/*  39*/                "98", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/*  39*/                "99", VARIABLE_LENGTH, Integer.valueOf(30)
                    }
                });
/*  75*/        THREE_DIGIT_DATA_LENGTH = (new Object[][] {
/*  75*/            new Object[] {
/*  75*/                "240", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/*  75*/                "241", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/*  75*/                "242", VARIABLE_LENGTH, Integer.valueOf(6)
                    }, new Object[] {
/*  75*/                "250", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/*  75*/                "251", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/*  75*/                "253", VARIABLE_LENGTH, Integer.valueOf(17)
                    }, new Object[] {
/*  75*/                "254", VARIABLE_LENGTH, Integer.valueOf(20)
                    }, new Object[] {
/*  75*/                "400", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/*  75*/                "401", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/*  75*/                "402", Integer.valueOf(17)
                    }, new Object[] {
/*  75*/                "403", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/*  75*/                "410", Integer.valueOf(13)
                    }, new Object[] {
/*  75*/                "411", Integer.valueOf(13)
                    }, new Object[] {
/*  75*/                "412", Integer.valueOf(13)
                    }, new Object[] {
/*  75*/                "413", Integer.valueOf(13)
                    }, new Object[] {
/*  75*/                "414", Integer.valueOf(13)
                    }, new Object[] {
/*  75*/                "420", VARIABLE_LENGTH, Integer.valueOf(20)
                    }, new Object[] {
/*  75*/                "421", VARIABLE_LENGTH, Integer.valueOf(15)
                    }, new Object[] {
/*  75*/                "422", Integer.valueOf(3)
                    }, new Object[] {
/*  75*/                "423", VARIABLE_LENGTH, Integer.valueOf(15)
                    }, new Object[] {
/*  75*/                "424", Integer.valueOf(3)
                    }, new Object[] {
/*  75*/                "425", Integer.valueOf(3)
                    }, new Object[] {
/*  75*/                "426", Integer.valueOf(3)
                    }
                });
/* 104*/        THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH = (new Object[][] {
/* 104*/            new Object[] {
/* 104*/                "310", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "311", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "312", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "313", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "314", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "315", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "316", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "320", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "321", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "322", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "323", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "324", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "325", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "326", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "327", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "328", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "329", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "330", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "331", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "332", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "333", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "334", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "335", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "336", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "340", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "341", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "342", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "343", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "344", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "345", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "346", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "347", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "348", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "349", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "350", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "351", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "352", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "353", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "354", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "355", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "356", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "357", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "360", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "361", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "362", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "363", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "364", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "365", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "366", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "367", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "368", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "369", Integer.valueOf(6)
                    }, new Object[] {
/* 104*/                "390", VARIABLE_LENGTH, Integer.valueOf(15)
                    }, new Object[] {
/* 104*/                "391", VARIABLE_LENGTH, Integer.valueOf(18)
                    }, new Object[] {
/* 104*/                "392", VARIABLE_LENGTH, Integer.valueOf(15)
                    }, new Object[] {
/* 104*/                "393", VARIABLE_LENGTH, Integer.valueOf(18)
                    }, new Object[] {
/* 104*/                "703", VARIABLE_LENGTH, Integer.valueOf(30)
                    }
                });
/* 166*/        FOUR_DIGIT_DATA_LENGTH = (new Object[][] {
/* 166*/            new Object[] {
/* 166*/                "7001", Integer.valueOf(13)
                    }, new Object[] {
/* 166*/                "7002", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/* 166*/                "7003", Integer.valueOf(10)
                    }, new Object[] {
/* 166*/                "8001", Integer.valueOf(14)
                    }, new Object[] {
/* 166*/                "8002", VARIABLE_LENGTH, Integer.valueOf(20)
                    }, new Object[] {
/* 166*/                "8003", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/* 166*/                "8004", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/* 166*/                "8005", Integer.valueOf(6)
                    }, new Object[] {
/* 166*/                "8006", Integer.valueOf(18)
                    }, new Object[] {
/* 166*/                "8007", VARIABLE_LENGTH, Integer.valueOf(30)
                    }, new Object[] {
/* 166*/                "8008", VARIABLE_LENGTH, Integer.valueOf(12)
                    }, new Object[] {
/* 166*/                "8018", Integer.valueOf(18)
                    }, new Object[] {
/* 166*/                "8020", VARIABLE_LENGTH, Integer.valueOf(25)
                    }, new Object[] {
/* 166*/                "8100", Integer.valueOf(6)
                    }, new Object[] {
/* 166*/                "8101", Integer.valueOf(10)
                    }, new Object[] {
/* 166*/                "8102", Integer.valueOf(2)
                    }, new Object[] {
/* 166*/                "8110", VARIABLE_LENGTH, Integer.valueOf(70)
                    }, new Object[] {
/* 166*/                "8200", VARIABLE_LENGTH, Integer.valueOf(70)
                    }
                });
            }
}
