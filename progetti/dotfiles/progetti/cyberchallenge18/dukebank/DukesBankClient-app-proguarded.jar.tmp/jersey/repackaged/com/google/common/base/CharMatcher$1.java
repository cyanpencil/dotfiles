// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CharMatcher.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            CharMatcher

static class  extends CharMatcher
{

            public final boolean matches(char c)
            {
/*  70*/        switch(c)
                {
/*  83*/        case 9: // '\t'
/*  83*/        case 10: // '\n'
/*  83*/        case 11: // '\013'
/*  83*/        case 12: // '\f'
/*  83*/        case 13: // '\r'
/*  83*/        case 32: // ' '
/*  83*/        case 133: 
/*  83*/        case 5760: 
/*  83*/        case 8232: 
/*  83*/        case 8233: 
/*  83*/        case 8287: 
/*  83*/        case 12288: 
/*  83*/            return true;

/*  85*/        case 8199: 
/*  85*/            return false;
                }
/*  87*/        return c >= '\u2000' && c <= '\u200A';
            }

            public final String toString()
            {
/*  93*/        return "CharMatcher.BREAKING_WHITESPACE";
            }

            public final volatile boolean apply(Object obj)
            {
/*  67*/        return super.apply((Character)obj);
            }

            ()
            {
            }
}
