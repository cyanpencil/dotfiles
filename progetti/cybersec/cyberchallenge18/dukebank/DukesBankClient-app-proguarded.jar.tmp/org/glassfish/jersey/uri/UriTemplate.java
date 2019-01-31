// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UriTemplate.java

package org.glassfish.jersey.uri;

import java.net.URI;
import java.util.*;
import java.util.regex.*;
import jersey.repackaged.com.google.common.base.Preconditions;
import org.glassfish.jersey.uri.internal.UriTemplateParser;

// Referenced classes of package org.glassfish.jersey.uri:
//            PatternWithGroups, UriComponent

public class UriTemplate
{
    static interface TemplateValueStrategy
    {

        public abstract String valueFor(String s, String s1);
    }


            private UriTemplate()
            {
/* 205*/        template = normalizedTemplate = "";
/* 206*/        pattern = PatternWithGroups.EMPTY;
/* 207*/        endsWithSlash = false;
/* 208*/        templateVariables = Collections.emptyList();
/* 209*/        numOfExplicitRegexes = numOfCharacters = numOfRegexGroups = 0;
            }

            public UriTemplate(String s)
                throws PatternSyntaxException, IllegalArgumentException
            {
/* 231*/        this(new UriTemplateParser(s));
            }

            protected UriTemplate(UriTemplateParser uritemplateparser)
                throws PatternSyntaxException, IllegalArgumentException
            {
/* 252*/        template = uritemplateparser.getTemplate();
/* 254*/        normalizedTemplate = uritemplateparser.getNormalizedTemplate();
/* 256*/        pattern = initUriPattern(uritemplateparser);
/* 258*/        numOfExplicitRegexes = uritemplateparser.getNumberOfExplicitRegexes();
/* 260*/        numOfRegexGroups = uritemplateparser.getNumberOfRegexGroups();
/* 262*/        numOfCharacters = uritemplateparser.getNumberOfLiteralCharacters();
/* 264*/        endsWithSlash = template.charAt(template.length() - 1) == '/';
/* 266*/        templateVariables = Collections.unmodifiableList(uritemplateparser.getNames());
            }

            private static PatternWithGroups initUriPattern(UriTemplateParser uritemplateparser)
            {
/* 276*/        return new PatternWithGroups(uritemplateparser.getPattern(), uritemplateparser.getGroupIndexes());
            }

            public static URI resolve(URI uri, String s)
            {
/* 290*/        return resolve(uri, URI.create(s));
            }

            public static URI resolve(URI uri, URI uri1)
            {
/* 302*/        Preconditions.checkNotNull(uri, "Input base URI parameter must not be null.");
/* 303*/        Preconditions.checkNotNull(uri1, "Input reference URI parameter must not be null.");
                String s;
/* 305*/        if((s = uri1.toString()).isEmpty())
/* 308*/            uri1 = URI.create("#");
/* 309*/        else
/* 309*/        if(s.startsWith("?"))
                {
/* 310*/            uri = ((URI) ((uri1 = (uri = uri.toString()).indexOf('?')) < 0 ? uri : ((URI) (uri.substring(0, uri1)))));
/* 313*/            return URI.create((new StringBuilder()).append(uri).append(s).toString());
                }
/* 316*/        uri = uri.resolve(uri1);
/* 317*/        if(s.isEmpty())
/* 318*/            uri = URI.create((uri1 = uri.toString()).substring(0, uri1.indexOf('#')));
/* 322*/        return normalize(uri);
            }

            public static URI normalize(String s)
            {
/* 342*/        return normalize(URI.create(s));
            }

            public static URI normalize(URI uri)
            {
/* 361*/        Preconditions.checkNotNull(uri, "Input reference URI parameter must not be null.");
                Object obj;
/* 363*/        if((obj = uri.getPath()) == null || ((String) (obj)).isEmpty() || !((String) (obj)).contains("/."))
/* 366*/            return uri;
/* 369*/        String as[] = ((String) (obj)).split("/");
/* 370*/        ArrayDeque arraydeque = new ArrayDeque(as.length);
/* 372*/        int i = (as = as).length;
/* 372*/        for(int j = 0; j < i; j++)
                {
                    String s2;
/* 372*/            if((s2 = as[j]).isEmpty() || ".".equals(s2))
/* 375*/                continue;
/* 375*/            if("..".equals(s2))
/* 376*/                arraydeque.pollLast();
/* 378*/            else
/* 378*/                arraydeque.offer(s2);
                }

/* 382*/        as = new StringBuilder();
                String s1;
/* 383*/        for(Iterator iterator = arraydeque.iterator(); iterator.hasNext(); as.append('/').append(s1))
/* 383*/            s1 = (String)iterator.next();

                String s;
/* 387*/        return URI.create(s = createURIWithStringValues(uri.getScheme(), uri.getAuthority(), null, null, null, as.toString(), uri.getQuery(), uri.getFragment(), EMPTY_VALUES, false, false));
            }

            public static URI relativize(URI uri, URI uri1)
            {
/* 412*/        Preconditions.checkNotNull(uri, "Input base URI parameter must not be null.");
/* 413*/        Preconditions.checkNotNull(uri1, "Input reference URI parameter must not be null.");
/* 415*/        return normalize(uri.relativize(uri1));
            }

            public final String getTemplate()
            {
/* 424*/        return template;
            }

            public final PatternWithGroups getPattern()
            {
/* 434*/        return pattern;
            }

            public final boolean endsWithSlash()
            {
/* 444*/        return endsWithSlash;
            }

            public final List getTemplateVariables()
            {
/* 453*/        return templateVariables;
            }

            public final boolean isTemplateVariablePresent(String s)
            {
                String s1;
/* 465*/        for(Iterator iterator = templateVariables.iterator(); iterator.hasNext();)
/* 465*/            if((s1 = (String)iterator.next()).equals(s))
/* 467*/                return true;

/* 471*/        return false;
            }

            public final int getNumberOfExplicitRegexes()
            {
/* 480*/        return numOfExplicitRegexes;
            }

            public final int getNumberOfRegexGroups()
            {
/* 489*/        return numOfRegexGroups;
            }

            public final int getNumberOfExplicitCharacters()
            {
/* 499*/        return numOfCharacters;
            }

            public final int getNumberOfTemplateVariables()
            {
/* 508*/        return templateVariables.size();
            }

            public final boolean match(CharSequence charsequence, Map map)
                throws IllegalArgumentException
            {
/* 530*/        if(map == null)
/* 531*/            throw new IllegalArgumentException();
/* 534*/        else
/* 534*/            return pattern.match(charsequence, templateVariables, map);
            }

            public final boolean match(CharSequence charsequence, List list)
                throws IllegalArgumentException
            {
/* 555*/        if(list == null)
/* 556*/            throw new IllegalArgumentException();
/* 559*/        else
/* 559*/            return pattern.match(charsequence, list);
            }

            public final String createURI(final Map values)
            {
/* 573*/        StringBuilder stringbuilder = new StringBuilder();
/* 574*/        resolveTemplate(normalizedTemplate, stringbuilder, new TemplateValueStrategy() {

                    public String valueFor(String s, String s1)
                    {
/* 577*/                return (String)values.get(s);
                    }

                    final Map val$values;
                    final UriTemplate this$0;

                    
                    {
/* 574*/                this$0 = UriTemplate.this;
/* 574*/                values = map;
/* 574*/                super();
                    }
        });
/* 580*/        return stringbuilder.toString();
            }

            public final transient String createURI(String as[])
            {
/* 595*/        return createURI(as, 0, as.length);
            }

            public final String createURI(final String values[], final int offset, final int length)
            {
/* 613*/        values = new TemplateValueStrategy() {

                    public String valueFor(String s, String s1)
                    {
/* 623*/                if((s1 = (String)mapValues.get(s)) == null && v < lengthPlusOffset && (s1 = values[v++]) != null)
/* 628*/                    mapValues.put(s, s1);
/* 633*/                return s1;
                    }

                    private final int lengthPlusOffset;
                    private int v;
                    private final Map mapValues = new HashMap();
                    final int val$length;
                    final int val$offset;
                    final String val$values[];
                    final UriTemplate this$0;

                    
                    {
/* 613*/                this$0 = UriTemplate.this;
/* 613*/                length = i;
/* 613*/                offset = j;
/* 613*/                values = as;
/* 613*/                super();
/* 614*/                lengthPlusOffset = length + offset;
/* 615*/                v = offset;
                    }
        };
/* 637*/        offset = new StringBuilder();
/* 638*/        resolveTemplate(normalizedTemplate, offset, values);
/* 639*/        return offset.toString();
            }

            private static void resolveTemplate(String s, StringBuilder stringbuilder, TemplateValueStrategy templatevaluestrategy)
            {
/* 655*/        Matcher matcher = TEMPLATE_NAMES_PATTERN.matcher(s);
                int i;
/* 657*/        for(i = 0; matcher.find(); i = matcher.end())
                {
/* 659*/            stringbuilder.append(s, i, matcher.start());
                    char c;
/* 660*/            if((c = (i = matcher.group(1)).charAt(0)) == '?' || c == ';')
                    {
                        char c1;
                        String s2;
/* 667*/                if(c == '?')
                        {
/* 669*/                    c = '?';
/* 670*/                    c1 = '&';
/* 671*/                    s2 = "=";
                        } else
                        {
/* 674*/                    c = ';';
/* 675*/                    c1 = ';';
/* 676*/                    s2 = "";
                        }
/* 679*/                int j = stringbuilder.length();
/* 680*/                int k = (i = i = i.substring(1).split(", ?")).length;
/* 681*/                for(int l = 0; l < k; l++)
                        {
/* 681*/                    String s3 = i[l];
/* 683*/                    try
                            {
                                String s4;
/* 683*/                        if((s4 = templatevaluestrategy.valueFor(s3, matcher.group())) == null)
/* 685*/                            continue;
/* 685*/                        if(j != stringbuilder.length())
/* 686*/                            stringbuilder.append(c1);
/* 689*/                        stringbuilder.append(s3);
/* 690*/                        if(s4.isEmpty())
                                {
/* 691*/                            stringbuilder.append(s2);
                                } else
                                {
/* 693*/                            stringbuilder.append('=');
/* 694*/                            stringbuilder.append(s4);
                                }
                            }
/* 697*/                    catch(IllegalArgumentException _ex) { }
                        }

/* 702*/                if(j != stringbuilder.length() && (j == 0 || stringbuilder.charAt(j - 1) != c))
/* 703*/                    stringbuilder.insert(j, c);
/* 705*/                continue;
                    }
                    String s1;
/* 706*/            if((s1 = templatevaluestrategy.valueFor(i, matcher.group())) != null)
/* 709*/                stringbuilder.append(s1);
                }

/* 715*/        stringbuilder.append(s, i, s.length());
            }

            public final String toString()
            {
/* 720*/        return pattern.toString();
            }

            public final int hashCode()
            {
/* 731*/        return pattern.hashCode();
            }

            public final boolean equals(Object obj)
            {
/* 743*/        if(obj instanceof UriTemplate)
                {
/* 744*/            obj = (UriTemplate)obj;
/* 745*/            return pattern.equals(((UriTemplate) (obj)).pattern);
                } else
                {
/* 747*/            return false;
                }
            }

            public static String createURI(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, 
                    Map map, boolean flag, boolean flag1)
            {
/* 783*/        HashMap hashmap = new HashMap();
/* 784*/        map = map.entrySet().iterator();
/* 784*/        do
                {
/* 784*/            if(!map.hasNext())
/* 784*/                break;
                    java.util.Map.Entry entry;
/* 784*/            if((entry = (java.util.Map.Entry)map.next()).getValue() != null)
/* 786*/                hashmap.put(entry.getKey(), entry.getValue().toString());
                } while(true);
/* 790*/        return createURIWithStringValues(s, s1, s2, s3, s4, s5, s6, s7, hashmap, flag, flag1);
            }

            public static String createURIWithStringValues(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, 
                    Map map, boolean flag, boolean flag1)
            {
/* 827*/        return createURIWithStringValues(s, s1, s2, s3, s4, s5, s6, s7, EMPTY_VALUES, flag, flag1, map);
            }

            public static String createURI(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, 
                    Object aobj[], boolean flag, boolean flag1)
            {
/* 864*/        String as[] = new String[aobj.length];
/* 865*/        for(int i = 0; i < aobj.length; i++)
/* 866*/            if(aobj[i] != null)
/* 867*/                as[i] = aobj[i].toString();

/* 871*/        return createURIWithStringValues(s, s1, s2, s3, s4, s5, s6, s7, as, flag, flag1);
            }

            public static String createURIWithStringValues(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, 
                    String as[], boolean flag, boolean flag1)
            {
/* 905*/        HashMap hashmap = new HashMap();
/* 906*/        return createURIWithStringValues(s, s1, s2, s3, s4, s5, s6, s7, as, flag, flag1, ((Map) (hashmap)));
            }

            private static String createURIWithStringValues(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, 
                    String as[], boolean flag, boolean flag1, Map map)
            {
/* 915*/        StringBuilder stringbuilder = new StringBuilder();
/* 916*/        int i = 0;
/* 918*/        if(s != null)
                {
/* 919*/            i = createUriComponent(UriComponent.Type.SCHEME, s, as, 0, false, map, stringbuilder);
/* 921*/            stringbuilder.append(':');
                }
/* 924*/        s = 0;
/* 925*/        if(notEmpty(s2) || notEmpty(s3) || notEmpty(s4))
                {
/* 926*/            s = 1;
/* 927*/            stringbuilder.append("//");
/* 929*/            if(notEmpty(s2))
                    {
/* 930*/                i = createUriComponent(UriComponent.Type.USER_INFO, s2, as, i, flag, map, stringbuilder);
/* 932*/                stringbuilder.append('@');
                    }
/* 935*/            if(notEmpty(s3))
/* 937*/                i = createUriComponent(UriComponent.Type.HOST, s3, as, i, flag, map, stringbuilder);
/* 941*/            if(notEmpty(s4))
                    {
/* 942*/                stringbuilder.append(':');
/* 943*/                i = createUriComponent(UriComponent.Type.PORT, s4, as, i, false, map, stringbuilder);
                    }
                } else
/* 946*/        if(notEmpty(s1))
                {
/* 947*/            s = 1;
/* 948*/            stringbuilder.append("//");
/* 950*/            i = createUriComponent(UriComponent.Type.AUTHORITY, s1, as, i, flag, map, stringbuilder);
                }
/* 954*/        if(notEmpty(s5) || notEmpty(s6) || notEmpty(s7))
                {
/* 956*/            if(s != 0 && (s5 == null || s5.isEmpty() || s5.charAt(0) != '/'))
/* 957*/                stringbuilder.append('/');
/* 960*/            if(notEmpty(s5))
/* 962*/                i = createUriComponent(s = flag1 ? ((String) (UriComponent.Type.PATH_SEGMENT)) : ((String) (UriComponent.Type.PATH)), s5, as, i, flag, map, stringbuilder);
/* 968*/            if(notEmpty(s6))
                    {
/* 969*/                stringbuilder.append('?');
/* 970*/                i = createUriComponent(UriComponent.Type.QUERY_PARAM, s6, as, i, flag, map, stringbuilder);
                    }
/* 974*/            if(notEmpty(s7))
                    {
/* 975*/                stringbuilder.append('#');
/* 976*/                createUriComponent(UriComponent.Type.FRAGMENT, s7, as, i, flag, map, stringbuilder);
                    }
                }
/* 980*/        return stringbuilder.toString();
            }

            private static boolean notEmpty(String s)
            {
/* 984*/        return s != null && !s.isEmpty();
            }

            private static int createUriComponent(final UriComponent.Type componentType, String s, final String values[], final int valueOffset, final boolean encode, final Map mapValues, StringBuilder stringbuilder)
            {
/* 996*/        mapValues = mapValues;
/* 998*/        if(s.indexOf('{') == -1)
                {
/* 999*/            stringbuilder.append(s);
/*1000*/            return valueOffset;
                } else
                {
/*1004*/            s = (new UriTemplateParser(s)).getNormalizedTemplate();
            class _cls1ValuesFromArrayStrategy
                implements TemplateValueStrategy
            {

                        public String valueFor(String s1, String s2)
                        {
/*1013*/                    if((s2 = ((String) (mapValues.get(s1)))) == null && offset < values.length)
                            {
/*1015*/                        s2 = values[offset++];
/*1016*/                        mapValues.put(s1, s2);
                            }
/*1018*/                    if(s2 == null)
/*1019*/                        throw new IllegalArgumentException(String.format("The template variable '%s' has no value", new Object[] {
/*1019*/                            s1
                                }));
/*1022*/                    if(encode)
/*1023*/                        return UriComponent.encode(s2.toString(), componentType);
/*1025*/                    else
/*1025*/                        return UriComponent.contextualEncode(s2.toString(), componentType);
                        }

                        private int offset;
                        final int val$valueOffset;
                        final Map val$mapValues;
                        final String val$values[];
                        final boolean val$encode;
                        final UriComponent.Type val$componentType;


                    _cls1ValuesFromArrayStrategy()
                    {
/*1007*/                valueOffset = i;
/*1007*/                mapValues = map;
/*1007*/                values = as;
/*1007*/                encode = flag;
/*1007*/                componentType = type;
/*1007*/                super();
/*1008*/                offset = valueOffset;
                    }
            }

/*1029*/            componentType = new _cls1ValuesFromArrayStrategy();
/*1030*/            resolveTemplate(s, stringbuilder, componentType);
/*1032*/            return ((_cls1ValuesFromArrayStrategy) (componentType)).offset;
                }
            }

            public static String resolveTemplateValues(UriComponent.Type type, String s, boolean flag, Map map)
            {
/*1054*/        if(s == null || s.isEmpty() || s.indexOf('{') == -1)
                {
/*1055*/            return s;
                } else
                {
/*1058*/            map = map;
/*1061*/            s = (new UriTemplateParser(s)).getNormalizedTemplate();
/*1063*/            StringBuilder stringbuilder = new StringBuilder();
/*1064*/            resolveTemplate(s, stringbuilder, new TemplateValueStrategy(map, flag, type) {

                        public final String valueFor(String s1, String s2)
                        {
                            Object obj;
/*1068*/                    if((obj = mapValues.get(s1)) != null)
                            {
/*1071*/                        if(encode)
/*1072*/                            obj = UriComponent.encode(obj.toString(), type);
/*1074*/                        else
/*1074*/                            obj = UriComponent.contextualEncode(obj.toString(), type);
/*1076*/                        return obj.toString();
                            }
/*1078*/                    if(mapValues.containsKey(s1))
/*1079*/                        throw new IllegalArgumentException(String.format("The value associated of the template value map for key '%s' is 'null'.", new Object[] {
/*1079*/                            s1
                                }));
/*1085*/                    else
/*1085*/                        return s2;
                        }

                        final Map val$mapValues;
                        final boolean val$encode;
                        final UriComponent.Type val$type;

                    
                    {
/*1064*/                mapValues = map;
/*1064*/                encode = flag;
/*1064*/                type = type1;
/*1064*/                super();
                    }
            });
/*1090*/            return stringbuilder.toString();
                }
            }

            private static final String EMPTY_VALUES[] = new String[0];
            public static final Comparator COMPARATOR = new Comparator() {

                public final int compare(UriTemplate uritemplate, UriTemplate uritemplate1)
                {
/*  82*/            if(uritemplate == null && uritemplate1 == null)
/*  83*/                return 0;
/*  85*/            if(uritemplate == null)
/*  86*/                return 1;
/*  88*/            if(uritemplate1 == null)
/*  89*/                return -1;
/*  92*/            if(uritemplate == UriTemplate.EMPTY && uritemplate1 == UriTemplate.EMPTY)
/*  93*/                return 0;
/*  95*/            if(uritemplate == UriTemplate.EMPTY)
/*  96*/                return 1;
/*  98*/            if(uritemplate1 == UriTemplate.EMPTY)
/*  99*/                return -1;
                    int i;
/* 106*/            if((i = uritemplate1.getNumberOfExplicitCharacters() - uritemplate.getNumberOfExplicitCharacters()) != 0)
/* 108*/                return i;
/* 116*/            if((i = uritemplate1.getNumberOfTemplateVariables() - uritemplate.getNumberOfTemplateVariables()) != 0)
/* 118*/                return i;
/* 123*/            if((i = uritemplate1.getNumberOfExplicitRegexes() - uritemplate.getNumberOfExplicitRegexes()) != 0)
/* 125*/                return i;
/* 132*/            else
/* 132*/                return uritemplate1.pattern.getRegex().compareTo(uritemplate.pattern.getRegex());
                }

                public final volatile int compare(Object obj, Object obj1)
                {
/*  78*/            return compare((UriTemplate)obj, (UriTemplate)obj1);
                }

    };
            private static final Pattern TEMPLATE_NAMES_PATTERN = Pattern.compile("\\{([\\w\\?;][-\\w\\.,]*)\\}");
            public static final UriTemplate EMPTY = new UriTemplate();
            private final String template;
            private final String normalizedTemplate;
            private final PatternWithGroups pattern;
            private final boolean endsWithSlash;
            private final List templateVariables;
            private final int numOfExplicitRegexes;
            private final int numOfRegexGroups;
            private final int numOfCharacters;


}
