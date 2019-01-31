// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PathPattern.java

package org.glassfish.jersey.uri;

import java.util.Comparator;

// Referenced classes of package org.glassfish.jersey.uri:
//            PatternWithGroups, PathTemplate, UriTemplate

public final class PathPattern extends PatternWithGroups
{
    public static final class RightHandPath extends Enum
    {

                public static RightHandPath[] values()
                {
/*  88*/            return (RightHandPath[])$VALUES.clone();
                }

                public static RightHandPath valueOf(String s)
                {
/*  88*/            return (RightHandPath)Enum.valueOf(org/glassfish/jersey/uri/PathPattern$RightHandPath, s);
                }

                private String getRegex()
                {
/* 108*/            return regex;
                }

                public static final RightHandPath capturingZeroOrMoreSegments;
                public static final RightHandPath capturingZeroSegments;
                private final String regex;
                private static final RightHandPath $VALUES[];

                static 
                {
/*  94*/            capturingZeroOrMoreSegments = new RightHandPath("capturingZeroOrMoreSegments", 0, "(/.*)?");
/*  99*/            capturingZeroSegments = new RightHandPath("capturingZeroSegments", 1, "(/)?");
/*  88*/            $VALUES = (new RightHandPath[] {
/*  88*/                capturingZeroOrMoreSegments, capturingZeroSegments
                    });
                }


                private RightHandPath(String s, int i, String s1)
                {
/* 103*/            super(s, i);
/* 104*/            regex = s1;
                }
    }


            public static PathPattern asClosed(PathPattern pathpattern)
            {
/* 120*/        return new PathPattern(pathpattern.getTemplate().getTemplate(), RightHandPath.capturingZeroSegments);
            }

            private PathPattern()
            {
/* 128*/        template = UriTemplate.EMPTY;
            }

            public PathPattern(String s)
            {
/* 139*/        this(new PathTemplate(s));
            }

            public PathPattern(PathTemplate pathtemplate)
            {
/* 150*/        super(postfixWithCapturingGroup(pathtemplate.getPattern().getRegex()), addIndexForRightHandPathCapturingGroup(pathtemplate.getNumberOfRegexGroups(), pathtemplate.getPattern().getGroupIndexes()));
/* 154*/        template = pathtemplate;
            }

            public PathPattern(String s, RightHandPath righthandpath)
            {
/* 164*/        this(new PathTemplate(s), righthandpath);
            }

            public PathPattern(PathTemplate pathtemplate, RightHandPath righthandpath)
            {
/* 174*/        super(postfixWithCapturingGroup(pathtemplate.getPattern().getRegex(), righthandpath), addIndexForRightHandPathCapturingGroup(pathtemplate.getNumberOfRegexGroups(), pathtemplate.getPattern().getGroupIndexes()));
/* 178*/        template = pathtemplate;
            }

            public final UriTemplate getTemplate()
            {
/* 182*/        return template;
            }

            private static String postfixWithCapturingGroup(String s)
            {
/* 186*/        return postfixWithCapturingGroup(s, RightHandPath.capturingZeroOrMoreSegments);
            }

            private static String postfixWithCapturingGroup(String s, RightHandPath righthandpath)
            {
/* 190*/        if(s.endsWith("/"))
/* 191*/            s = s.substring(0, s.length() - 1);
/* 194*/        return (new StringBuilder()).append(s).append(righthandpath.getRegex()).toString();
            }

            private static int[] addIndexForRightHandPathCapturingGroup(int i, int ai[])
            {
/* 198*/        if(ai.length == 0)
                {
/* 199*/            return ai;
                } else
                {
/* 202*/            int ai1[] = new int[ai.length + 1];
/* 203*/            System.arraycopy(ai, 0, ai1, 0, ai.length);
/* 205*/            ai1[ai.length] = i + 1;
/* 206*/            return ai1;
                }
            }

            public static final PathPattern EMPTY_PATTERN = new PathPattern();
            public static final PathPattern END_OF_PATH_PATTERN;
            public static final PathPattern OPEN_ROOT_PATH_PATTERN;
            public static final Comparator COMPARATOR = new Comparator() {

                public final int compare(PathPattern pathpattern, PathPattern pathpattern1)
                {
/*  80*/            return UriTemplate.COMPARATOR.compare(pathpattern.template, pathpattern1.template);
                }

                public final volatile int compare(Object obj, Object obj1)
                {
/*  76*/            return compare((PathPattern)obj, (PathPattern)obj1);
                }

    };
            private final UriTemplate template;

            static 
            {
/*  67*/        END_OF_PATH_PATTERN = new PathPattern("", RightHandPath.capturingZeroSegments);
/*  71*/        OPEN_ROOT_PATH_PATTERN = new PathPattern("", RightHandPath.capturingZeroOrMoreSegments);
            }

}
