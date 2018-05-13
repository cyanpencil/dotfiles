// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheBuilderSpec.java

package jersey.repackaged.com.google.common.cache;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import jersey.repackaged.com.google.common.base.*;
import jersey.repackaged.com.google.common.collect.ImmutableList;
import jersey.repackaged.com.google.common.collect.ImmutableMap;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            CacheBuilder, LocalCache

public final class CacheBuilderSpec
{
    static class RefreshDurationParser extends DurationParser
    {

                protected void parseDuration(CacheBuilderSpec cachebuilderspec, long l, TimeUnit timeunit)
                {
/* 470*/            Preconditions.checkArgument(cachebuilderspec.refreshTimeUnit == null, "refreshAfterWrite already set");
/* 471*/            cachebuilderspec.refreshDuration = l;
/* 472*/            cachebuilderspec.refreshTimeUnit = timeunit;
                }

                RefreshDurationParser()
                {
                }
    }

    static class WriteDurationParser extends DurationParser
    {

                protected void parseDuration(CacheBuilderSpec cachebuilderspec, long l, TimeUnit timeunit)
                {
/* 461*/            Preconditions.checkArgument(cachebuilderspec.writeExpirationTimeUnit == null, "expireAfterWrite already set");
/* 462*/            cachebuilderspec.writeExpirationDuration = l;
/* 463*/            cachebuilderspec.writeExpirationTimeUnit = timeunit;
                }

                WriteDurationParser()
                {
                }
    }

    static class AccessDurationParser extends DurationParser
    {

                protected void parseDuration(CacheBuilderSpec cachebuilderspec, long l, TimeUnit timeunit)
                {
/* 452*/            Preconditions.checkArgument(cachebuilderspec.accessExpirationTimeUnit == null, "expireAfterAccess already set");
/* 453*/            cachebuilderspec.accessExpirationDuration = l;
/* 454*/            cachebuilderspec.accessExpirationTimeUnit = timeunit;
                }

                AccessDurationParser()
                {
                }
    }

    static abstract class DurationParser
        implements ValueParser
    {

                protected abstract void parseDuration(CacheBuilderSpec cachebuilderspec, long l, TimeUnit timeunit);

                public void parse(CacheBuilderSpec cachebuilderspec, String s, String s1)
                {
/* 417*/            Preconditions.checkArgument(s1 != null && !s1.isEmpty(), "value of key %s omitted", new Object[] {
/* 417*/                s
                    });
/* 419*/            try
                    {
                        char c;
                        TimeUnit timeunit;
/* 419*/                switch(c = s1.charAt(s1.length() - 1))
                        {
/* 423*/                case 100: // 'd'
/* 423*/                    timeunit = TimeUnit.DAYS;
                            break;

/* 426*/                case 104: // 'h'
/* 426*/                    timeunit = TimeUnit.HOURS;
                            break;

/* 429*/                case 109: // 'm'
/* 429*/                    timeunit = TimeUnit.MINUTES;
                            break;

/* 432*/                case 115: // 's'
/* 432*/                    timeunit = TimeUnit.SECONDS;
                            break;

/* 435*/                default:
/* 435*/                    throw new IllegalArgumentException(String.format("key %s invalid format.  was %s, must end with one of [dDhHmMsS]", new Object[] {
/* 435*/                        s, s1
                            }));
                        }
/* 440*/                long l = Long.parseLong(s1.substring(0, s1.length() - 1));
/* 441*/                parseDuration(cachebuilderspec, l, timeunit);
/* 445*/                return;
                    }
/* 442*/            catch(NumberFormatException _ex)
                    {
/* 443*/                throw new IllegalArgumentException(String.format("key %s value set to %s, must be integer", new Object[] {
/* 443*/                    s, s1
                        }));
                    }
                }

                DurationParser()
                {
                }
    }

    static class RecordStatsParser
        implements ValueParser
    {

                public void parse(CacheBuilderSpec cachebuilderspec, String s, String s1)
                {
/* 402*/            Preconditions.checkArgument(s1 == null, "recordStats does not take values");
/* 403*/            Preconditions.checkArgument(cachebuilderspec.recordStats == null, "recordStats already set");
/* 404*/            cachebuilderspec.recordStats = Boolean.valueOf(true);
                }

                RecordStatsParser()
                {
                }
    }

    static class ValueStrengthParser
        implements ValueParser
    {

                public void parse(CacheBuilderSpec cachebuilderspec, String s, String s1)
                {
/* 389*/            Preconditions.checkArgument(s1 == null, "key %s does not take values", new Object[] {
/* 389*/                s
                    });
/* 390*/            Preconditions.checkArgument(cachebuilderspec.valueStrength == null, "%s was already set to %s", new Object[] {
/* 390*/                s, cachebuilderspec.valueStrength
                    });
/* 393*/            cachebuilderspec.valueStrength = strength;
                }

                private final LocalCache.Strength strength;

                public ValueStrengthParser(LocalCache.Strength strength1)
                {
/* 384*/            strength = strength1;
                }
    }

    static class KeyStrengthParser
        implements ValueParser
    {

                public void parse(CacheBuilderSpec cachebuilderspec, String s, String s1)
                {
/* 373*/            Preconditions.checkArgument(s1 == null, "key %s does not take values", new Object[] {
/* 373*/                s
                    });
/* 374*/            Preconditions.checkArgument(cachebuilderspec.keyStrength == null, "%s was already set to %s", new Object[] {
/* 374*/                s, cachebuilderspec.keyStrength
                    });
/* 375*/            cachebuilderspec.keyStrength = strength;
                }

                private final LocalCache.Strength strength;

                public KeyStrengthParser(LocalCache.Strength strength1)
                {
/* 368*/            strength = strength1;
                }
    }

    static class ConcurrencyLevelParser extends IntegerParser
    {

                protected void parseInteger(CacheBuilderSpec cachebuilderspec, int i)
                {
/* 357*/            Preconditions.checkArgument(cachebuilderspec.concurrencyLevel == null, "concurrency level was already set to ", new Object[] {
/* 357*/                cachebuilderspec.concurrencyLevel
                    });
/* 359*/            cachebuilderspec.concurrencyLevel = Integer.valueOf(i);
                }

                ConcurrencyLevelParser()
                {
                }
    }

    static class MaximumWeightParser extends LongParser
    {

                protected void parseLong(CacheBuilderSpec cachebuilderspec, long l)
                {
/* 345*/            Preconditions.checkArgument(cachebuilderspec.maximumWeight == null, "maximum weight was already set to ", new Object[] {
/* 345*/                cachebuilderspec.maximumWeight
                    });
/* 347*/            Preconditions.checkArgument(cachebuilderspec.maximumSize == null, "maximum size was already set to ", new Object[] {
/* 347*/                cachebuilderspec.maximumSize
                    });
/* 349*/            cachebuilderspec.maximumWeight = Long.valueOf(l);
                }

                MaximumWeightParser()
                {
                }
    }

    static class MaximumSizeParser extends LongParser
    {

                protected void parseLong(CacheBuilderSpec cachebuilderspec, long l)
                {
/* 333*/            Preconditions.checkArgument(cachebuilderspec.maximumSize == null, "maximum size was already set to ", new Object[] {
/* 333*/                cachebuilderspec.maximumSize
                    });
/* 335*/            Preconditions.checkArgument(cachebuilderspec.maximumWeight == null, "maximum weight was already set to ", new Object[] {
/* 335*/                cachebuilderspec.maximumWeight
                    });
/* 337*/            cachebuilderspec.maximumSize = Long.valueOf(l);
                }

                MaximumSizeParser()
                {
                }
    }

    static class InitialCapacityParser extends IntegerParser
    {

                protected void parseInteger(CacheBuilderSpec cachebuilderspec, int i)
                {
/* 323*/            Preconditions.checkArgument(cachebuilderspec.initialCapacity == null, "initial capacity was already set to ", new Object[] {
/* 323*/                cachebuilderspec.initialCapacity
                    });
/* 325*/            cachebuilderspec.initialCapacity = Integer.valueOf(i);
                }

                InitialCapacityParser()
                {
                }
    }

    static abstract class LongParser
        implements ValueParser
    {

                protected abstract void parseLong(CacheBuilderSpec cachebuilderspec, long l);

                public void parse(CacheBuilderSpec cachebuilderspec, String s, String s1)
                {
/* 309*/            Preconditions.checkArgument(s1 != null && !s1.isEmpty(), "value of key %s omitted", new Object[] {
/* 309*/                s
                    });
/* 311*/            try
                    {
/* 311*/                parseLong(cachebuilderspec, Long.parseLong(s1));
/* 315*/                return;
                    }
                    // Misplaced declaration of an exception variable
/* 312*/            catch(CacheBuilderSpec cachebuilderspec)
                    {
/* 313*/                throw new IllegalArgumentException(String.format("key %s value set to %s, must be integer", new Object[] {
/* 313*/                    s, s1
                        }), cachebuilderspec);
                    }
                }

                LongParser()
                {
                }
    }

    static abstract class IntegerParser
        implements ValueParser
    {

                protected abstract void parseInteger(CacheBuilderSpec cachebuilderspec, int i);

                public void parse(CacheBuilderSpec cachebuilderspec, String s, String s1)
                {
/* 293*/            Preconditions.checkArgument(s1 != null && !s1.isEmpty(), "value of key %s omitted", new Object[] {
/* 293*/                s
                    });
/* 295*/            try
                    {
/* 295*/                parseInteger(cachebuilderspec, Integer.parseInt(s1));
/* 299*/                return;
                    }
                    // Misplaced declaration of an exception variable
/* 296*/            catch(CacheBuilderSpec cachebuilderspec)
                    {
/* 297*/                throw new IllegalArgumentException(String.format("key %s value set to %s, must be integer", new Object[] {
/* 297*/                    s, s1
                        }), cachebuilderspec);
                    }
                }

                IntegerParser()
                {
                }
    }

    static interface ValueParser
    {

        public abstract void parse(CacheBuilderSpec cachebuilderspec, String s, String s1);
    }


            private CacheBuilderSpec(String s)
            {
/* 129*/        specification = s;
            }

            public static CacheBuilderSpec parse(String s)
            {
/* 138*/        CacheBuilderSpec cachebuilderspec = new CacheBuilderSpec(s);
/* 139*/        if(!s.isEmpty())
                {
                    String s1;
                    Object obj;
                    ValueParser valueparser;
/* 140*/            for(s = KEYS_SPLITTER.split(s).iterator(); s.hasNext(); valueparser.parse(cachebuilderspec, s1, ((String) (obj))))
                    {
/* 140*/                s1 = (String)s.next();
/* 141*/                Preconditions.checkArgument(!((List) (obj = ImmutableList.copyOf(KEY_VALUE_SPLITTER.split(s1)))).isEmpty(), "blank key-value pair");
/* 143*/                Preconditions.checkArgument(((List) (obj)).size() <= 2, "key-value pair %s with more than one equals sign", new Object[] {
/* 143*/                    s1
                        });
/* 147*/                s1 = (String)((List) (obj)).get(0);
/* 148*/                Preconditions.checkArgument((valueparser = (ValueParser)VALUE_PARSERS.get(s1)) != null, "unknown key %s", new Object[] {
/* 149*/                    s1
                        });
/* 151*/                obj = ((List) (obj)).size() != 1 ? ((Object) ((String)((List) (obj)).get(1))) : null;
                    }

                }
/* 156*/        return cachebuilderspec;
            }

            final CacheBuilder toCacheBuilder()
            {
/* 171*/        CacheBuilder cachebuilder = CacheBuilder.newBuilder();
/* 172*/        if(initialCapacity != null)
/* 173*/            cachebuilder.initialCapacity(initialCapacity.intValue());
/* 175*/        if(maximumSize != null)
/* 176*/            cachebuilder.maximumSize(maximumSize.longValue());
/* 178*/        if(maximumWeight != null)
/* 179*/            cachebuilder.maximumWeight(maximumWeight.longValue());
/* 181*/        if(concurrencyLevel != null)
/* 182*/            cachebuilder.concurrencyLevel(concurrencyLevel.intValue());
        static class _cls1
        {

                    static final int $SwitchMap$com$google$common$cache$LocalCache$Strength[];

                    static 
                    {
/* 185*/                $SwitchMap$com$google$common$cache$LocalCache$Strength = new int[LocalCache.Strength.values().length];
/* 185*/                try
                        {
/* 185*/                    $SwitchMap$com$google$common$cache$LocalCache$Strength[LocalCache.Strength.WEAK.ordinal()] = 1;
                        }
/* 185*/                catch(NoSuchFieldError _ex) { }
/* 185*/                try
                        {
/* 185*/                    $SwitchMap$com$google$common$cache$LocalCache$Strength[LocalCache.Strength.SOFT.ordinal()] = 2;
                        }
/* 185*/                catch(NoSuchFieldError _ex) { }
                    }
        }

/* 184*/        if(keyStrength != null)
/* 185*/            switch(_cls1..SwitchMap.com.google.common.cache.LocalCache.Strength[keyStrength.ordinal()])
                    {
/* 187*/            case 1: // '\001'
/* 187*/                cachebuilder.weakKeys();
                        break;

/* 190*/            default:
/* 190*/                throw new AssertionError();
                    }
/* 193*/        if(valueStrength != null)
/* 194*/            switch(_cls1..SwitchMap.com.google.common.cache.LocalCache.Strength[valueStrength.ordinal()])
                    {
/* 196*/            case 2: // '\002'
/* 196*/                cachebuilder.softValues();
                        break;

/* 199*/            case 1: // '\001'
/* 199*/                cachebuilder.weakValues();
                        break;

/* 202*/            default:
/* 202*/                throw new AssertionError();
                    }
/* 205*/        if(recordStats != null && recordStats.booleanValue())
/* 206*/            cachebuilder.recordStats();
/* 208*/        if(writeExpirationTimeUnit != null)
/* 209*/            cachebuilder.expireAfterWrite(writeExpirationDuration, writeExpirationTimeUnit);
/* 211*/        if(accessExpirationTimeUnit != null)
/* 212*/            cachebuilder.expireAfterAccess(accessExpirationDuration, accessExpirationTimeUnit);
/* 214*/        if(refreshTimeUnit != null)
/* 215*/            cachebuilder.refreshAfterWrite(refreshDuration, refreshTimeUnit);
/* 218*/        return cachebuilder;
            }

            public final String toParsableString()
            {
/* 228*/        return specification;
            }

            public final String toString()
            {
/* 237*/        return MoreObjects.toStringHelper(this).addValue(toParsableString()).toString();
            }

            public final int hashCode()
            {
/* 242*/        return Objects.hashCode(new Object[] {
/* 242*/            initialCapacity, maximumSize, maximumWeight, concurrencyLevel, keyStrength, valueStrength, recordStats, durationInNanos(writeExpirationDuration, writeExpirationTimeUnit), durationInNanos(accessExpirationDuration, accessExpirationTimeUnit), durationInNanos(refreshDuration, refreshTimeUnit)
                });
            }

            public final boolean equals(Object obj)
            {
/* 257*/        if(this == obj)
/* 258*/            return true;
/* 260*/        if(!(obj instanceof CacheBuilderSpec))
/* 261*/            return false;
/* 263*/        obj = (CacheBuilderSpec)obj;
/* 264*/        return Objects.equal(initialCapacity, ((CacheBuilderSpec) (obj)).initialCapacity) && Objects.equal(maximumSize, ((CacheBuilderSpec) (obj)).maximumSize) && Objects.equal(maximumWeight, ((CacheBuilderSpec) (obj)).maximumWeight) && Objects.equal(concurrencyLevel, ((CacheBuilderSpec) (obj)).concurrencyLevel) && Objects.equal(keyStrength, ((CacheBuilderSpec) (obj)).keyStrength) && Objects.equal(valueStrength, ((CacheBuilderSpec) (obj)).valueStrength) && Objects.equal(recordStats, ((CacheBuilderSpec) (obj)).recordStats) && Objects.equal(durationInNanos(writeExpirationDuration, writeExpirationTimeUnit), durationInNanos(((CacheBuilderSpec) (obj)).writeExpirationDuration, ((CacheBuilderSpec) (obj)).writeExpirationTimeUnit)) && Objects.equal(durationInNanos(accessExpirationDuration, accessExpirationTimeUnit), durationInNanos(((CacheBuilderSpec) (obj)).accessExpirationDuration, ((CacheBuilderSpec) (obj)).accessExpirationTimeUnit)) && Objects.equal(durationInNanos(refreshDuration, refreshTimeUnit), durationInNanos(((CacheBuilderSpec) (obj)).refreshDuration, ((CacheBuilderSpec) (obj)).refreshTimeUnit));
            }

            private static Long durationInNanos(long l, TimeUnit timeunit)
            {
/* 284*/        if(timeunit == null)
/* 284*/            return null;
/* 284*/        else
/* 284*/            return Long.valueOf(timeunit.toNanos(l));
            }

            private static final Splitter KEYS_SPLITTER = Splitter.on(',').trimResults();
            private static final Splitter KEY_VALUE_SPLITTER = Splitter.on('=').trimResults();
            private static final ImmutableMap VALUE_PARSERS;
            Integer initialCapacity;
            Long maximumSize;
            Long maximumWeight;
            Integer concurrencyLevel;
            LocalCache.Strength keyStrength;
            LocalCache.Strength valueStrength;
            Boolean recordStats;
            long writeExpirationDuration;
            TimeUnit writeExpirationTimeUnit;
            long accessExpirationDuration;
            TimeUnit accessExpirationTimeUnit;
            long refreshDuration;
            TimeUnit refreshTimeUnit;
            private final String specification;

            static 
            {
/*  96*/        VALUE_PARSERS = ImmutableMap.builder().put("initialCapacity", new InitialCapacityParser()).put("maximumSize", new MaximumSizeParser()).put("maximumWeight", new MaximumWeightParser()).put("concurrencyLevel", new ConcurrencyLevelParser()).put("weakKeys", new KeyStrengthParser(LocalCache.Strength.WEAK)).put("softValues", new ValueStrengthParser(LocalCache.Strength.SOFT)).put("weakValues", new ValueStrengthParser(LocalCache.Strength.WEAK)).put("recordStats", new RecordStatsParser()).put("expireAfterAccess", new AccessDurationParser()).put("expireAfterWrite", new WriteDurationParser()).put("refreshAfterWrite", new RefreshDurationParser()).put("refreshInterval", new RefreshDurationParser()).build();
            }
}
