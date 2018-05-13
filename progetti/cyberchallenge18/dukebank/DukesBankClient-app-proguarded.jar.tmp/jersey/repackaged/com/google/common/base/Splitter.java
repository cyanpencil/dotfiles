// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Splitter.java

package jersey.repackaged.com.google.common.base;

import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            CharMatcher, Preconditions, AbstractIterator, Joiner

public final class Splitter
{
    static abstract class SplittingIterator extends AbstractIterator
    {

                abstract int separatorStart(int i);

                abstract int separatorEnd(int i);

                protected String computeNext()
                {
/* 557*/            int i = offset;
/* 558*/            do
                    {
/* 558*/                if(offset == -1)
/* 559*/                    break;
/* 559*/                int j = i;
                        int k;
                        int l;
/* 562*/                if((k = separatorStart(offset)) == -1)
                        {
/* 564*/                    l = toSplit.length();
/* 565*/                    offset = -1;
                        } else
                        {
/* 567*/                    l = k;
/* 568*/                    offset = separatorEnd(k);
                        }
/* 570*/                if(offset == i)
                        {
/* 578*/                    offset++;
/* 579*/                    if(offset >= toSplit.length())
/* 580*/                        offset = -1;
                        } else
                        {
/* 585*/                    for(; j < l && trimmer.matches(toSplit.charAt(j)); j++);
/* 588*/                    for(; l > j && trimmer.matches(toSplit.charAt(l - 1)); l--);
/* 592*/                    if(omitEmptyStrings && j == l)
                            {
/* 594*/                        i = offset;
                            } else
                            {
/* 598*/                        if(limit == 1)
                                {
/* 602*/                            l = toSplit.length();
/* 603*/                            offset = -1;
/* 605*/                            for(; l > j && trimmer.matches(toSplit.charAt(l - 1)); l--);
                                } else
                                {
/* 609*/                            limit--;
                                }
/* 612*/                        return toSplit.subSequence(j, l).toString();
                            }
                        }
                    } while(true);
/* 614*/            return (String)endOfData();
                }

                protected volatile Object computeNext()
                {
/* 522*/            return computeNext();
                }

                final CharSequence toSplit;
                final CharMatcher trimmer;
                final boolean omitEmptyStrings;
                int offset;
                int limit;

                protected SplittingIterator(Splitter splitter, CharSequence charsequence)
                {
/* 540*/            offset = 0;
/* 544*/            trimmer = splitter.trimmer;
/* 545*/            omitEmptyStrings = splitter.omitEmptyStrings;
/* 546*/            limit = splitter.limit;
/* 547*/            toSplit = charsequence;
                }
    }

    static interface Strategy
    {

        public abstract Iterator iterator(Splitter splitter, CharSequence charsequence);
    }


            private Splitter(Strategy strategy1)
            {
/* 110*/        this(strategy1, false, CharMatcher.NONE, 0x7fffffff);
            }

            private Splitter(Strategy strategy1, boolean flag, CharMatcher charmatcher, int i)
            {
/* 115*/        strategy = strategy1;
/* 116*/        omitEmptyStrings = flag;
/* 117*/        trimmer = charmatcher;
/* 118*/        limit = i;
            }

            public static Splitter on(char c)
            {
/* 130*/        return on(CharMatcher.is(c));
            }

            public static Splitter on(CharMatcher charmatcher)
            {
/* 144*/        Preconditions.checkNotNull(charmatcher);
/* 146*/        return new Splitter(new Strategy(charmatcher) {

                    public final SplittingIterator iterator(Splitter splitter, CharSequence charsequence)
                    {
/* 149*/                return splitter. new SplittingIterator(charsequence) {

                            int separatorStart(int i)
                            {
/* 151*/                        return separatorMatcher.indexIn(toSplit, i);
                            }

                            int separatorEnd(int i)
                            {
/* 155*/                        return i + 1;
                            }

                            final _cls1 this$0;

                            
                            {
/* 149*/                        this$0 = _cls1.this;
/* 149*/                        super(Splitter.this, charsequence);
                            }
                };
                    }

                    public final volatile Iterator iterator(Splitter splitter, CharSequence charsequence)
                    {
/* 146*/                return iterator(splitter, charsequence);
                    }

                    final CharMatcher val$separatorMatcher;

                    
                    {
/* 146*/                separatorMatcher = charmatcher;
/* 146*/                super();
                    }
        });
            }

            public final Splitter trimResults()
            {
/* 356*/        return trimResults(CharMatcher.WHITESPACE);
            }

            public final Splitter trimResults(CharMatcher charmatcher)
            {
/* 373*/        Preconditions.checkNotNull(charmatcher);
/* 374*/        return new Splitter(strategy, omitEmptyStrings, charmatcher, limit);
            }

            public final Iterable split(final CharSequence sequence)
            {
/* 386*/        Preconditions.checkNotNull(sequence);
/* 388*/        return new Iterable() {

                    public Iterator iterator()
                    {
/* 390*/                return splittingIterator(sequence);
                    }

                    public String toString()
                    {
/* 393*/                return Joiner.on(", ").appendTo(new StringBuilder("["), this).append(']').toString();
                    }

                    final CharSequence val$sequence;
                    final Splitter this$0;

                    
                    {
/* 388*/                this$0 = Splitter.this;
/* 388*/                sequence = charsequence;
/* 388*/                super();
                    }
        };
            }

            private Iterator splittingIterator(CharSequence charsequence)
            {
/* 402*/        return strategy.iterator(this, charsequence);
            }

            private final CharMatcher trimmer;
            private final boolean omitEmptyStrings;
            private final Strategy strategy;
            private final int limit;




}
