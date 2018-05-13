// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Predicates.java

package jersey.repackaged.com.google.common.base;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Joiner, Preconditions, Predicate, Function, 
//            Objects

public final class Predicates
{
    static class ContainsPatternFromStringPredicate extends ContainsPatternPredicate
    {

                public String toString()
                {
/* 629*/            String s = String.valueOf(String.valueOf(pattern.pattern()));
/* 629*/            return (new StringBuilder(28 + s.length())).append("Predicates.containsPattern(").append(s).append(")").toString();
                }

                ContainsPatternFromStringPredicate(String s)
                {
/* 625*/            super(Pattern.compile(s));
                }
    }

    static class ContainsPatternPredicate
        implements Serializable, Predicate
    {

                public boolean apply(CharSequence charsequence)
                {
/* 586*/            return pattern.matcher(charsequence).find();
                }

                public int hashCode()
                {
/* 593*/            return Objects.hashCode(new Object[] {
/* 593*/                pattern.pattern(), Integer.valueOf(pattern.flags())
                    });
                }

                public boolean equals(Object obj)
                {
/* 597*/            if(obj instanceof ContainsPatternPredicate)
                    {
/* 598*/                obj = (ContainsPatternPredicate)obj;
/* 602*/                return Objects.equal(pattern.pattern(), ((ContainsPatternPredicate) (obj)).pattern.pattern()) && Objects.equal(Integer.valueOf(pattern.flags()), Integer.valueOf(((ContainsPatternPredicate) (obj)).pattern.flags()));
                    } else
                    {
/* 605*/                return false;
                    }
                }

                public String toString()
                {
                    String s;
/* 609*/            s = String.valueOf(String.valueOf(s = Objects.toStringHelper(pattern).add("pattern", pattern.pattern()).add("pattern.flags", pattern.flags()).toString()));
/* 613*/            return (new StringBuilder(21 + s.length())).append("Predicates.contains(").append(s).append(")").toString();
                }

                public volatile boolean apply(Object obj)
                {
/* 575*/            return apply((CharSequence)obj);
                }

                final Pattern pattern;

                ContainsPatternPredicate(Pattern pattern1)
                {
/* 581*/            pattern = (Pattern)Preconditions.checkNotNull(pattern1);
                }
    }

    static class CompositionPredicate
        implements Serializable, Predicate
    {

                public boolean apply(Object obj)
                {
/* 552*/            return p.apply(f.apply(obj));
                }

                public boolean equals(Object obj)
                {
/* 556*/            if(obj instanceof CompositionPredicate)
                    {
/* 557*/                obj = (CompositionPredicate)obj;
/* 558*/                return f.equals(((CompositionPredicate) (obj)).f) && p.equals(((CompositionPredicate) (obj)).p);
                    } else
                    {
/* 560*/                return false;
                    }
                }

                public int hashCode()
                {
/* 564*/            return f.hashCode() ^ p.hashCode();
                }

                public String toString()
                {
/* 568*/            String s = String.valueOf(String.valueOf(p.toString()));
/* 568*/            String s1 = String.valueOf(String.valueOf(f.toString()));
/* 568*/            return (new StringBuilder(2 + s.length() + s1.length())).append(s).append("(").append(s1).append(")").toString();
                }

                final Predicate p;
                final Function f;

                private CompositionPredicate(Predicate predicate, Function function)
                {
/* 546*/            p = (Predicate)Preconditions.checkNotNull(predicate);
/* 547*/            f = (Function)Preconditions.checkNotNull(function);
                }

    }

    static class InPredicate
        implements Serializable, Predicate
    {

                public boolean apply(Object obj)
                {
/* 513*/            return target.contains(obj);
/* 514*/            JVM INSTR pop ;
/* 515*/            return false;
/* 516*/            JVM INSTR pop ;
/* 517*/            return false;
                }

                public boolean equals(Object obj)
                {
/* 522*/            if(obj instanceof InPredicate)
                    {
/* 523*/                obj = (InPredicate)obj;
/* 524*/                return target.equals(((InPredicate) (obj)).target);
                    } else
                    {
/* 526*/                return false;
                    }
                }

                public int hashCode()
                {
/* 530*/            return target.hashCode();
                }

                public String toString()
                {
/* 534*/            String s = String.valueOf(String.valueOf(target));
/* 534*/            return (new StringBuilder(15 + s.length())).append("Predicates.in(").append(s).append(")").toString();
                }

                private final Collection target;

                private InPredicate(Collection collection)
                {
/* 507*/            target = (Collection)Preconditions.checkNotNull(collection);
                }

    }

    static class AssignableFromPredicate
        implements Serializable, Predicate
    {

                public boolean apply(Class class1)
                {
/* 484*/            return clazz.isAssignableFrom(class1);
                }

                public int hashCode()
                {
/* 487*/            return clazz.hashCode();
                }

                public boolean equals(Object obj)
                {
/* 490*/            if(obj instanceof AssignableFromPredicate)
                    {
/* 491*/                obj = (AssignableFromPredicate)obj;
/* 492*/                return clazz == ((AssignableFromPredicate) (obj)).clazz;
                    } else
                    {
/* 494*/                return false;
                    }
                }

                public String toString()
                {
/* 497*/            String s = String.valueOf(String.valueOf(clazz.getName()));
/* 497*/            return (new StringBuilder(27 + s.length())).append("Predicates.assignableFrom(").append(s).append(")").toString();
                }

                public volatile boolean apply(Object obj)
                {
/* 474*/            return apply((Class)obj);
                }

                private final Class clazz;

                private AssignableFromPredicate(Class class1)
                {
/* 480*/            clazz = (Class)Preconditions.checkNotNull(class1);
                }

    }

    static class InstanceOfPredicate
        implements Serializable, Predicate
    {

                public boolean apply(Object obj)
                {
/* 455*/            return clazz.isInstance(obj);
                }

                public int hashCode()
                {
/* 458*/            return clazz.hashCode();
                }

                public boolean equals(Object obj)
                {
/* 461*/            if(obj instanceof InstanceOfPredicate)
                    {
/* 462*/                obj = (InstanceOfPredicate)obj;
/* 463*/                return clazz == ((InstanceOfPredicate) (obj)).clazz;
                    } else
                    {
/* 465*/                return false;
                    }
                }

                public String toString()
                {
/* 468*/            String s = String.valueOf(String.valueOf(clazz.getName()));
/* 468*/            return (new StringBuilder(23 + s.length())).append("Predicates.instanceOf(").append(s).append(")").toString();
                }

                private final Class clazz;

                private InstanceOfPredicate(Class class1)
                {
/* 451*/            clazz = (Class)Preconditions.checkNotNull(class1);
                }

    }

    static class IsEqualToPredicate
        implements Serializable, Predicate
    {

                public boolean apply(Object obj)
                {
/* 426*/            return target.equals(obj);
                }

                public int hashCode()
                {
/* 429*/            return target.hashCode();
                }

                public boolean equals(Object obj)
                {
/* 432*/            if(obj instanceof IsEqualToPredicate)
                    {
/* 433*/                obj = (IsEqualToPredicate)obj;
/* 434*/                return target.equals(((IsEqualToPredicate) (obj)).target);
                    } else
                    {
/* 436*/                return false;
                    }
                }

                public String toString()
                {
/* 439*/            String s = String.valueOf(String.valueOf(target));
/* 439*/            return (new StringBuilder(20 + s.length())).append("Predicates.equalTo(").append(s).append(")").toString();
                }

                private final Object target;

                private IsEqualToPredicate(Object obj)
                {
/* 422*/            target = obj;
                }

    }

    static class OrPredicate
        implements Serializable, Predicate
    {

                public boolean apply(Object obj)
                {
/* 392*/            for(int i = 0; i < components.size(); i++)
/* 393*/                if(((Predicate)components.get(i)).apply(obj))
/* 394*/                    return true;

/* 397*/            return false;
                }

                public int hashCode()
                {
/* 401*/            return components.hashCode() + 0x53c91cf;
                }

                public boolean equals(Object obj)
                {
/* 404*/            if(obj instanceof OrPredicate)
                    {
/* 405*/                obj = (OrPredicate)obj;
/* 406*/                return components.equals(((OrPredicate) (obj)).components);
                    } else
                    {
/* 408*/                return false;
                    }
                }

                public String toString()
                {
/* 411*/            String s = String.valueOf(String.valueOf(Predicates.COMMA_JOINER.join(components)));
/* 411*/            return (new StringBuilder(15 + s.length())).append("Predicates.or(").append(s).append(")").toString();
                }

                private final List components;

                private OrPredicate(List list)
                {
/* 387*/            components = list;
                }

    }

    static class AndPredicate
        implements Serializable, Predicate
    {

                public boolean apply(Object obj)
                {
/* 358*/            for(int i = 0; i < components.size(); i++)
/* 359*/                if(!((Predicate)components.get(i)).apply(obj))
/* 360*/                    return false;

/* 363*/            return true;
                }

                public int hashCode()
                {
/* 367*/            return components.hashCode() + 0x12472c2c;
                }

                public boolean equals(Object obj)
                {
/* 370*/            if(obj instanceof AndPredicate)
                    {
/* 371*/                obj = (AndPredicate)obj;
/* 372*/                return components.equals(((AndPredicate) (obj)).components);
                    } else
                    {
/* 374*/                return false;
                    }
                }

                public String toString()
                {
/* 377*/            String s = String.valueOf(String.valueOf(Predicates.COMMA_JOINER.join(components)));
/* 377*/            return (new StringBuilder(16 + s.length())).append("Predicates.and(").append(s).append(")").toString();
                }

                private final List components;

                private AndPredicate(List list)
                {
/* 353*/            components = list;
                }

    }

    static class NotPredicate
        implements Serializable, Predicate
    {

                public boolean apply(Object obj)
                {
/* 328*/            return !predicate.apply(obj);
                }

                public int hashCode()
                {
/* 331*/            return ~predicate.hashCode();
                }

                public boolean equals(Object obj)
                {
/* 334*/            if(obj instanceof NotPredicate)
                    {
/* 335*/                obj = (NotPredicate)obj;
/* 336*/                return predicate.equals(((NotPredicate) (obj)).predicate);
                    } else
                    {
/* 338*/                return false;
                    }
                }

                public String toString()
                {
/* 341*/            String s = String.valueOf(String.valueOf(predicate.toString()));
/* 341*/            return (new StringBuilder(16 + s.length())).append("Predicates.not(").append(s).append(")").toString();
                }

                final Predicate predicate;

                NotPredicate(Predicate predicate1)
                {
/* 324*/            predicate = (Predicate)Preconditions.checkNotNull(predicate1);
                }
    }

    static abstract class ObjectPredicate extends Enum
        implements Predicate
    {

                Predicate withNarrowedType()
                {
/* 315*/            return this;
                }

                public static final ObjectPredicate ALWAYS_TRUE;
                public static final ObjectPredicate ALWAYS_FALSE;
                public static final ObjectPredicate IS_NULL;
                public static final ObjectPredicate NOT_NULL;
                private static final ObjectPredicate $VALUES[];

                static 
                {
/* 277*/            ALWAYS_TRUE = new ObjectPredicate("ALWAYS_TRUE", 0) {

                        public final boolean apply(Object obj)
                        {
/* 279*/                    return true;
                        }

                        public final String toString()
                        {
/* 282*/                    return "Predicates.alwaysTrue()";
                        }

            };
/* 286*/            ALWAYS_FALSE = new ObjectPredicate("ALWAYS_FALSE", 1) {

                        public final boolean apply(Object obj)
                        {
/* 288*/                    return false;
                        }

                        public final String toString()
                        {
/* 291*/                    return "Predicates.alwaysFalse()";
                        }

            };
/* 295*/            IS_NULL = new ObjectPredicate("IS_NULL", 2) {

                        public final boolean apply(Object obj)
                        {
/* 297*/                    return obj == null;
                        }

                        public final String toString()
                        {
/* 300*/                    return "Predicates.isNull()";
                        }

            };
/* 304*/            NOT_NULL = new ObjectPredicate("NOT_NULL", 3) {

                        public final boolean apply(Object obj)
                        {
/* 306*/                    return obj != null;
                        }

                        public final String toString()
                        {
/* 309*/                    return "Predicates.notNull()";
                        }

            };
/* 275*/            $VALUES = (new ObjectPredicate[] {
/* 275*/                ALWAYS_TRUE, ALWAYS_FALSE, IS_NULL, NOT_NULL
                    });
                }

                private ObjectPredicate(String s, int i)
                {
/* 275*/            super(s, i);
                }

    }


            private Predicates()
            {
            }

            public static Predicate alwaysTrue()
            {
/*  59*/        return ObjectPredicate.ALWAYS_TRUE.withNarrowedType();
            }

            public static Predicate alwaysFalse()
            {
/*  67*/        return ObjectPredicate.ALWAYS_FALSE.withNarrowedType();
            }

            public static Predicate isNull()
            {
/*  76*/        return ObjectPredicate.IS_NULL.withNarrowedType();
            }

            public static Predicate notNull()
            {
/*  85*/        return ObjectPredicate.NOT_NULL.withNarrowedType();
            }

            public static Predicate not(Predicate predicate)
            {
/*  93*/        return new NotPredicate(predicate);
            }

            public static Predicate and(Iterable iterable)
            {
/* 107*/        return new AndPredicate(defensiveCopy(iterable));
            }

            public static transient Predicate and(Predicate apredicate[])
            {
/* 120*/        return new AndPredicate(defensiveCopy(apredicate));
            }

            public static Predicate and(Predicate predicate, Predicate predicate1)
            {
/* 131*/        return new AndPredicate(asList((Predicate)Preconditions.checkNotNull(predicate), (Predicate)Preconditions.checkNotNull(predicate1)));
            }

            public static Predicate or(Iterable iterable)
            {
/* 146*/        return new OrPredicate(defensiveCopy(iterable));
            }

            public static transient Predicate or(Predicate apredicate[])
            {
/* 159*/        return new OrPredicate(defensiveCopy(apredicate));
            }

            public static Predicate or(Predicate predicate, Predicate predicate1)
            {
/* 170*/        return new OrPredicate(asList((Predicate)Preconditions.checkNotNull(predicate), (Predicate)Preconditions.checkNotNull(predicate1)));
            }

            public static Predicate equalTo(Object obj)
            {
/* 179*/        if(obj == null)
/* 179*/            return isNull();
/* 179*/        else
/* 179*/            return new IsEqualToPredicate(obj);
            }

            public static Predicate instanceOf(Class class1)
            {
/* 201*/        return new InstanceOfPredicate(class1);
            }

            public static Predicate assignableFrom(Class class1)
            {
/* 214*/        return new AssignableFromPredicate(class1);
            }

            public static Predicate in(Collection collection)
            {
/* 231*/        return new InPredicate(collection);
            }

            public static Predicate compose(Predicate predicate, Function function)
            {
/* 242*/        return new CompositionPredicate(predicate, function);
            }

            public static Predicate containsPattern(String s)
            {
/* 256*/        return new ContainsPatternFromStringPredicate(s);
            }

            public static Predicate contains(Pattern pattern)
            {
/* 269*/        return new ContainsPatternPredicate(pattern);
            }

            private static List asList(Predicate predicate, Predicate predicate1)
            {
/* 638*/        return Arrays.asList(new Predicate[] {
/* 638*/            predicate, predicate1
                });
            }

            private static transient List defensiveCopy(Object aobj[])
            {
/* 642*/        return defensiveCopy(((Iterable) (Arrays.asList(aobj))));
            }

            static List defensiveCopy(Iterable iterable)
            {
/* 646*/        ArrayList arraylist = new ArrayList();
                Object obj;
/* 647*/        for(iterable = iterable.iterator(); iterable.hasNext(); arraylist.add(Preconditions.checkNotNull(obj)))
/* 647*/            obj = iterable.next();

/* 650*/        return arraylist;
            }

            private static final Joiner COMMA_JOINER = Joiner.on(',');


}
