// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UriTemplate.java

package org.glassfish.jersey.uri;

import java.util.Comparator;

// Referenced classes of package org.glassfish.jersey.uri:
//            PatternWithGroups, UriTemplate

static class ups
    implements Comparator
{

            public final int compare(UriTemplate uritemplate, UriTemplate uritemplate1)
            {
/*  82*/        if(uritemplate == null && uritemplate1 == null)
/*  83*/            return 0;
/*  85*/        if(uritemplate == null)
/*  86*/            return 1;
/*  88*/        if(uritemplate1 == null)
/*  89*/            return -1;
/*  92*/        if(uritemplate == UriTemplate.EMPTY && uritemplate1 == UriTemplate.EMPTY)
/*  93*/            return 0;
/*  95*/        if(uritemplate == UriTemplate.EMPTY)
/*  96*/            return 1;
/*  98*/        if(uritemplate1 == UriTemplate.EMPTY)
/*  99*/            return -1;
                int i;
/* 106*/        if((i = uritemplate1.getNumberOfExplicitCharacters() - uritemplate.getNumberOfExplicitCharacters()) != 0)
/* 108*/            return i;
/* 116*/        if((i = uritemplate1.getNumberOfTemplateVariables() - uritemplate.getNumberOfTemplateVariables()) != 0)
/* 118*/            return i;
/* 123*/        if((i = uritemplate1.getNumberOfExplicitRegexes() - uritemplate.getNumberOfExplicitRegexes()) != 0)
/* 125*/            return i;
/* 132*/        else
/* 132*/            return UriTemplate.access$000(uritemplate1).getRegex().compareTo(UriTemplate.access$000(uritemplate).getRegex());
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/*  78*/        return compare((UriTemplate)obj, (UriTemplate)obj1);
            }

            ups()
            {
            }
}
