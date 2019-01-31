// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Splitter.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            AbstractIterator, CharMatcher, Splitter

static abstract class toSplit extends AbstractIterator
{

            abstract int separatorStart(int i);

            abstract int separatorEnd(int i);

            protected String computeNext()
            {
/* 557*/        int i = offset;
/* 558*/        do
                {
/* 558*/            if(offset == -1)
/* 559*/                break;
/* 559*/            int j = i;
                    int k;
                    int l;
/* 562*/            if((k = separatorStart(offset)) == -1)
                    {
/* 564*/                l = toSplit.length();
/* 565*/                offset = -1;
                    } else
                    {
/* 567*/                l = k;
/* 568*/                offset = separatorEnd(k);
                    }
/* 570*/            if(offset == i)
                    {
/* 578*/                offset++;
/* 579*/                if(offset >= toSplit.length())
/* 580*/                    offset = -1;
                    } else
                    {
/* 585*/                for(; j < l && trimmer.matches(toSplit.charAt(j)); j++);
/* 588*/                for(; l > j && trimmer.matches(toSplit.charAt(l - 1)); l--);
/* 592*/                if(omitEmptyStrings && j == l)
                        {
/* 594*/                    i = offset;
                        } else
                        {
/* 598*/                    if(limit == 1)
                            {
/* 602*/                        l = toSplit.length();
/* 603*/                        offset = -1;
/* 605*/                        for(; l > j && trimmer.matches(toSplit.charAt(l - 1)); l--);
                            } else
                            {
/* 609*/                        limit--;
                            }
/* 612*/                    return toSplit.subSequence(j, l).toString();
                        }
                    }
                } while(true);
/* 614*/        return (String)endOfData();
            }

            protected volatile Object computeNext()
            {
/* 522*/        return computeNext();
            }

            final CharSequence toSplit;
            final CharMatcher trimmer;
            final boolean omitEmptyStrings;
            int offset;
            int limit;

            protected I(Splitter splitter, CharSequence charsequence)
            {
/* 540*/        offset = 0;
/* 544*/        trimmer = Splitter.access$200(splitter);
/* 545*/        omitEmptyStrings = Splitter.access$300(splitter);
/* 546*/        limit = Splitter.access$400(splitter);
/* 547*/        toSplit = charsequence;
            }
}
