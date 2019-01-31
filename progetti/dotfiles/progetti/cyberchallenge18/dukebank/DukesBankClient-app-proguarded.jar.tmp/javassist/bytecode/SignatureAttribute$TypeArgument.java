// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SignatureAttribute.java

package javassist.bytecode;


// Referenced classes of package javassist.bytecode:
//            SignatureAttribute

public static class <init>
{

            public static <init> subclassOf(<init> <init>1)
            {
/* 514*/        return new <init>(<init>1, '+');
            }

            public static <init> superOf(<init> <init>1)
            {
/* 524*/        return new <init>(<init>1, '-');
            }

            public char getKind()
            {
/* 533*/        return wildcard;
            }

            public boolean isWildcard()
            {
/* 539*/        return wildcard != ' ';
            }

            public wildcard getType()
            {
/* 548*/        return arg;
            }

            public String toString()
            {
/* 554*/        if(wildcard == '*')
/* 555*/            return "?";
/* 557*/        String s = arg.toString();
/* 558*/        if(wildcard == ' ')
/* 559*/            return s;
/* 560*/        if(wildcard == '+')
/* 561*/            return (new StringBuilder("? extends ")).append(s).toString();
/* 563*/        else
/* 563*/            return (new StringBuilder("? super ")).append(s).toString();
            }

            static void encode(StringBuffer stringbuffer, wildcard awildcard[])
            {
/* 567*/        stringbuffer.append('<');
/* 568*/        for(int i = 0; i < awildcard.length; i++)
                {
                    wildcard wildcard1;
/* 569*/            if((wildcard1 = awildcard[i]).isWildcard())
/* 571*/                stringbuffer.append(wildcard1.wildcard);
/* 573*/            if(wildcard1.getType() != null)
/* 574*/                wildcard1.getType().code(stringbuffer);
                }

/* 577*/        stringbuffer.append('>');
            }

            code arg;
            char wildcard;

            ( , char c)
            {
/* 485*/        arg = ;
/* 486*/        wildcard = c;
            }

            public wildcard(wildcard wildcard1)
            {
/* 497*/        this(wildcard1, ' ');
            }

            public <init>()
            {
/* 504*/        this(null, '*');
            }
}
