// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.annotation.JsonConverter;
import com.owlike.genson.annotation.JsonDateFormat;
import com.owlike.genson.reflect.BeanProperty;
import com.owlike.genson.reflect.TypeUtil;
import com.owlike.genson.stream.*;
import java.io.File;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.*;
import java.sql.Timestamp;
import java.text.*;
import java.util.*;

// Referenced classes of package com.owlike.genson.convert:
//            ContextualFactory

public final class DefaultConverters
{
    public static class WrappedRootValueConverter
        implements Converter
    {

                public void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/*1298*/            if(objectwriter.enclosingType() == JsonType.EMPTY)
                    {
/*1299*/                objectwriter.beginObject().writeName(outputName);
/*1300*/                delegateConverter.serialize(obj, objectwriter, context);
/*1301*/                objectwriter.endObject();
                    }
                }

                public Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/*1307*/            Object obj = null;
/*1308*/            if(objectreader.enclosingType() == JsonType.EMPTY)
                    {
/*1309*/                objectreader.beginObject();
/*1311*/                if(objectreader.hasNext())
                        {
/*1312*/                    objectreader.next();
/*1314*/                    if(!inputName.equalsIgnoreCase(objectreader.name()))
/*1315*/                        throw new JsonBindingException(String.format("Expected key %s for unwrapping the value, but encountered key %s", new Object[] {
/*1315*/                            inputName, objectreader.name()
                                }));
/*1324*/                    obj = delegateConverter.deserialize(objectreader, context);
                        }
/*1326*/                objectreader.endObject();
                    }
/*1328*/            return obj;
                }

                private final String inputName;
                private final String outputName;
                private final Converter delegateConverter;

                public WrappedRootValueConverter(String s, String s1, Converter converter)
                {
/*1291*/            inputName = s;
/*1292*/            outputName = s1;
/*1293*/            delegateConverter = converter;
                }
    }

    public static final class PropertyConverterFactory
        implements ContextualFactory
    {

                public final Converter create(BeanProperty beanproperty, Genson genson)
                {
/*1246*/            if((genson = (JsonConverter)beanproperty.getAnnotation(com/owlike/genson/annotation/JsonConverter)) == null)
/*1248*/                break MISSING_BLOCK_LABEL_225;
/*1248*/            Type type = TypeUtil.expandType(TypeUtil.lookupGenericType(com/owlike/genson/Converter, genson.value()), genson.value());
/*1250*/            type = TypeUtil.typeOf(0, type);
                    Class class1;
/*1252*/            if((class1 = beanproperty.getRawClass()).isPrimitive())
/*1253*/                class1 = TypeUtil.wrap(class1);
/*1256*/            if(!TypeUtil.match(class1, type, false))
/*1257*/                throw new ClassCastException((new StringBuilder("The type defined in ")).append(genson.value().getName()).append(" is not assignale from property ").append(beanproperty.getName()).append(" declared in ").append(beanproperty.getDeclaringClass()).toString());
/*1262*/            if(!(beanproperty = genson.value().getConstructor(new Class[0])).isAccessible())
/*1263*/                beanproperty.setAccessible(true);
/*1264*/            return (Converter)beanproperty.newInstance(new Object[0]);
/*1267*/            beanproperty;
/*1268*/            throw new RuntimeException(beanproperty);
/*1269*/            beanproperty;
/*1270*/            throw new RuntimeException(beanproperty);
/*1271*/            beanproperty;
/*1272*/            throw new RuntimeException(beanproperty);
/*1273*/            beanproperty;
/*1274*/            throw new RuntimeException(beanproperty);
/*1275*/            beanproperty;
/*1276*/            throw new RuntimeException(beanproperty);
/*1277*/            beanproperty;
/*1278*/            throw new RuntimeException(beanproperty);
/*1281*/            return null;
                }

                public PropertyConverterFactory()
                {
                }
    }

    public static final class FileConverter
        implements Converter
    {

                public final void serialize(File file, ObjectWriter objectwriter, Context context)
                {
/*1231*/            objectwriter.writeValue(file.getPath());
                }

                public final File deserialize(ObjectReader objectreader, Context context)
                {
/*1236*/            return new File(objectreader.valueAsString());
                }

                public final volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/*1221*/            return deserialize(objectreader, context);
                }

                public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/*1221*/            serialize((File)obj, objectwriter, context);
                }

                public static final FileConverter instance = new FileConverter();


                private FileConverter()
                {
                }
    }

    public static class CalendarConverter
        implements Converter
    {

                public void serialize(Calendar calendar, ObjectWriter objectwriter, Context context)
                {
/*1207*/            dateConverter.serialize(calendar.getTime(), objectwriter, context);
                }

                public Calendar deserialize(ObjectReader objectreader, Context context)
                {
/*1212*/            GregorianCalendar gregoriancalendar = null;
/*1213*/            if(ValueType.NULL != objectreader.getValueType())
/*1214*/                (gregoriancalendar = new GregorianCalendar()).setTime(dateConverter.deserialize(objectreader, context));
/*1217*/            return gregoriancalendar;
                }

                public volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/*1196*/            return deserialize(objectreader, context);
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/*1196*/            serialize((Calendar)obj, objectwriter, context);
                }

                private final DateConverter dateConverter;

                CalendarConverter(DateConverter dateconverter)
                {
/*1202*/            dateConverter = dateconverter;
                }
    }

    public static final class CalendarConverterFactory
        implements Factory
    {

                public final Converter create(Type type, Genson genson)
                {
/*1189*/            if(!java/util/Calendar.isAssignableFrom(TypeUtil.getRawClass(type)))
/*1190*/                throw new IllegalStateException("CalendarConverterFactory create method can be called only for Calendar type and subtypes.");
/*1192*/            else
/*1192*/                return calendarConverter;
                }

                public final volatile Object create(Type type, Genson genson)
                {
/*1180*/            return create(type, genson);
                }

                private final CalendarConverter calendarConverter;

                public CalendarConverterFactory(DateConverter dateconverter)
                {
/*1184*/            calendarConverter = new CalendarConverter(dateconverter);
                }
    }

    public static class UUIDConverter
        implements Converter
    {

                public void serialize(UUID uuid, ObjectWriter objectwriter, Context context)
                {
/*1170*/            objectwriter.writeValue(uuid.toString());
                }

                public UUID deserialize(ObjectReader objectreader, Context context)
                {
/*1175*/            return UUID.fromString(objectreader.valueAsString());
                }

                public volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/*1160*/            return deserialize(objectreader, context);
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/*1160*/            serialize((UUID)obj, objectwriter, context);
                }

                public static final UUIDConverter instance = new UUIDConverter();


                private UUIDConverter()
                {
                }
    }

    public static class TimestampConverter
        implements Converter
    {

                public Timestamp deserialize(ObjectReader objectreader, Context context)
                {
/*1151*/            return Timestamp.valueOf(objectreader.valueAsString());
                }

                public void serialize(Timestamp timestamp, ObjectWriter objectwriter, Context context)
                {
/*1156*/            objectwriter.writeValue(timestamp.toString());
                }

                public volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/*1141*/            return deserialize(objectreader, context);
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/*1141*/            serialize((Timestamp)obj, objectwriter, context);
                }

                public static final TimestampConverter instance = new TimestampConverter();


                private TimestampConverter()
                {
                }
    }

    public static class BigIntegerConverter
        implements Converter
    {

                public BigInteger deserialize(ObjectReader objectreader, Context context)
                {
/*1132*/            return new BigInteger(objectreader.valueAsString());
                }

                public void serialize(BigInteger biginteger, ObjectWriter objectwriter, Context context)
                {
/*1137*/            objectwriter.writeValue(biginteger);
                }

                public volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/*1122*/            return deserialize(objectreader, context);
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/*1122*/            serialize((BigInteger)obj, objectwriter, context);
                }

                public static final BigIntegerConverter instance = new BigIntegerConverter();


                private BigIntegerConverter()
                {
                }
    }

    public static class BigDecimalConverter
        implements Converter
    {

                public BigDecimal deserialize(ObjectReader objectreader, Context context)
                {
/*1113*/            return new BigDecimal(objectreader.valueAsString());
                }

                public void serialize(BigDecimal bigdecimal, ObjectWriter objectwriter, Context context)
                {
/*1118*/            objectwriter.writeValue(bigdecimal);
                }

                public volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/*1103*/            return deserialize(objectreader, context);
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/*1103*/            serialize((BigDecimal)obj, objectwriter, context);
                }

                public static final BigDecimalConverter instance = new BigDecimalConverter();


                private BigDecimalConverter()
                {
                }
    }

    public static class URIConverter
        implements Converter
    {

                public void serialize(URI uri, ObjectWriter objectwriter, Context context)
                {
/*1095*/            objectwriter.writeUnsafeValue(uri.toString());
                }

                public URI deserialize(ObjectReader objectreader, Context context)
                {
/*1099*/            return URI.create(objectreader.valueAsString());
                }

                public volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/*1086*/            return deserialize(objectreader, context);
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/*1086*/            serialize((URI)obj, objectwriter, context);
                }

                public static final URIConverter instance = new URIConverter();


                private URIConverter()
                {
                }
    }

    public static class URLConverter
        implements Converter
    {

                public URL deserialize(ObjectReader objectreader, Context context)
                {
/*1075*/            return new URL(objectreader.valueAsString());
/*1076*/            JVM INSTR pop ;
/*1077*/            throw new JsonBindingException((new StringBuilder("Can not deserializer <")).append(objectreader.valueAsString()).append("> to URL.").toString());
                }

                public void serialize(URL url, ObjectWriter objectwriter, Context context)
                {
/*1082*/            objectwriter.writeValue(url.toExternalForm());
                }

                public volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/*1065*/            return deserialize(objectreader, context);
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/*1065*/            serialize((URL)obj, objectwriter, context);
                }

                public static final URLConverter instance = new URLConverter();


                private URLConverter()
                {
                }
    }

    public static final class EnumConverterFactory
        implements Factory
    {

                public final Converter create(Type type, Genson genson)
                {
/*1059*/            if((type = TypeUtil.getRawClass(type)).isEnum() || java/lang/Enum.isAssignableFrom(type))
/*1060*/                return new EnumConverter(type, caseSensitive);
/*1060*/            else
/*1060*/                return null;
                }

                public final volatile Object create(Type type, Genson genson)
                {
/*1049*/            return create(type, genson);
                }

                public static final EnumConverterFactory instance = new EnumConverterFactory(true);
                public final boolean caseSensitive;


                public EnumConverterFactory(boolean flag)
                {
/*1054*/            caseSensitive = flag;
                }
    }

    public static class EnumConverter
        implements Converter
    {

                public void serialize(Enum enum, ObjectWriter objectwriter, Context context)
                {
/*1038*/            objectwriter.writeUnsafeValue(enum.name());
                }

                public Enum deserialize(ObjectReader objectreader, Context context)
                {
/*1042*/            objectreader = caseSensitive ? ((ObjectReader) (objectreader.valueAsString())) : ((ObjectReader) (objectreader.valueAsString().toUpperCase()));
/*1043*/            if((context = (Enum)deserializationNames.get(objectreader)) == null)
/*1044*/                throw new JsonBindingException((new StringBuilder("No enum constant ")).append(eClass.getCanonicalName()).append(".").append(objectreader).toString());
/*1045*/            else
/*1045*/                return context;
                }

                public volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/*1016*/            return deserialize(objectreader, context);
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/*1016*/            serialize((Enum)obj, objectwriter, context);
                }

                private final Class eClass;
                private final Map deserializationNames = new HashMap();
                private final boolean caseSensitive;

                public EnumConverter(Class class1, boolean flag)
                {
/*1024*/            eClass = class1;
/*1025*/            caseSensitive = flag;
/*1027*/            int i = (class1 = class1.getFields()).length;
/*1027*/            for(int j = 0; j < i; j++)
                    {
/*1027*/                Field field = class1[j];
/*1029*/                try
                        {
/*1029*/                    String s = flag ? field.getName() : field.getName().toUpperCase();
/*1030*/                    deserializationNames.put(s, (Enum)field.get(null));
                        }
/*1031*/                catch(IllegalAccessException illegalaccessexception)
                        {
/*1032*/                    throw new JsonBindingException((new StringBuilder("Failed to get enum value ")).append(field.getName()).toString(), illegalaccessexception);
                        }
                    }

                }
    }

    public static final class UntypedConverterFactory
        implements Factory
    {
        public static final class UntypedConverter
            implements Converter
        {

                    public final Object deserialize(ObjectReader objectreader, Context context)
                    {
/* 996*/                return context.genson.deserialize(GenericType.of(objectreader.getValueType().toClass()), objectreader, context);
                    }

                    public final void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    {
/*1001*/                if(java/lang/Object.equals(obj.getClass()))
                        {
/*1002*/                    throw new UnsupportedOperationException("Serialization of type Object is not supported by default serializers.");
                        } else
                        {
/*1004*/                    context.genson.serialize(obj, obj.getClass(), objectwriter, context);
/*1005*/                    return;
                        }
                    }

                    static final UntypedConverter instance = new UntypedConverter();


                    private UntypedConverter()
                    {
                    }
        }


                public final Converter create(Type type, Genson genson)
                {
/*1009*/            if(TypeUtil.match(type, java/lang/Object, true))
/*1010*/                return UntypedConverter.instance;
/*1012*/            else
/*1012*/                return null;
                }

                public final volatile Object create(Type type, Genson genson)
                {
/* 983*/            return create(type, genson);
                }

                public static final UntypedConverterFactory instance = new UntypedConverterFactory();


                private UntypedConverterFactory()
                {
                }
    }

    public static class DateConverter
        implements Converter
    {

                public void serialize(Date date, ObjectWriter objectwriter, Context context)
                {
/* 953*/            if(asTimeInMillis)
                    {
/* 954*/                objectwriter.writeValue(date.getTime());
/* 954*/                return;
                    } else
                    {
/* 956*/                objectwriter.writeUnsafeValue(format(date));
/* 957*/                return;
                    }
                }

                protected synchronized String format(Date date)
                {
/* 960*/            return dateFormat.format(date);
                }

                public Date deserialize(ObjectReader objectreader, Context context)
                {
/* 965*/            if((context = objectreader.getValueType()) == ValueType.INTEGER)
/* 967*/                return new Date(objectreader.valueAsLong());
/* 968*/            if(context == ValueType.STRING)
/* 969*/                return read(objectreader.valueAsString());
/* 970*/            try
                    {
/* 970*/                throw new JsonBindingException(String.format("Can not deserialize type %s to Date, only numeric and string accepted.", new Object[] {
/* 970*/                    context
                        }));
                    }
                    // Misplaced declaration of an exception variable
/* 972*/            catch(Context context)
                    {
/* 973*/                throw new JsonBindingException((new StringBuilder("Could not parse date ")).append(objectreader.valueAsString()).toString(), context);
                    }
                }

                protected synchronized Date read(String s)
                    throws ParseException
                {
/* 979*/            return dateFormat.parse(s);
                }

                public volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 936*/            return deserialize(objectreader, context);
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 936*/            serialize((Date)obj, objectwriter, context);
                }

                private DateFormat dateFormat;
                private final boolean asTimeInMillis;

                public DateConverter()
                {
/* 943*/            this(SimpleDateFormat.getDateTimeInstance(), true);
                }

                public DateConverter(DateFormat dateformat, boolean flag)
                {
/* 947*/            if(dateformat == null)
/* 947*/                dateformat = SimpleDateFormat.getDateTimeInstance();
/* 948*/            dateFormat = dateformat;
/* 949*/            asTimeInMillis = flag;
                }
    }

    public static class DateContextualFactory
        implements ContextualFactory
    {

                public Converter create(BeanProperty beanproperty, Genson genson)
                {
/* 919*/            if((genson = (JsonDateFormat)beanproperty.getAnnotation(com/owlike/genson/annotation/JsonDateFormat)) != null)
                    {
/* 921*/                Object obj = genson.lang().isEmpty() ? ((Object) (Locale.getDefault())) : ((Object) (new Locale(genson.lang())));
/* 923*/                obj = genson.value() == null || genson.value().isEmpty() ? ((Object) (SimpleDateFormat.getInstance())) : ((Object) (new SimpleDateFormat(genson.value(), ((Locale) (obj)))));
/* 926*/                if(java/util/Date.isAssignableFrom(beanproperty.getRawClass()))
/* 927*/                    return new DateConverter(((DateFormat) (obj)), genson.asTimeInMillis());
/* 928*/                if(java/util/Calendar.isAssignableFrom(beanproperty.getRawClass()))
/* 929*/                    return new CalendarConverter(new DateConverter(((DateFormat) (obj)), genson.asTimeInMillis()));
                    }
/* 932*/            return null;
                }

                public DateContextualFactory()
                {
                }
    }

    public static final class MapConverterFactory
        implements Factory
    {

                public final Converter create(Type type, Genson genson)
                {
/* 864*/            Type type1 = type;
/* 865*/            if(TypeUtil.getRawClass(type).getTypeParameters().length == 0)
/* 866*/                type1 = TypeUtil.expandType(TypeUtil.lookupGenericType(java/util/Map, TypeUtil.getRawClass(type)), type);
/* 869*/            Type type2 = TypeUtil.typeOf(0, type1);
/* 870*/            type1 = TypeUtil.typeOf(1, type1);
                    Object obj;
/* 871*/            if((obj = keyAdapter(((Class) (obj = TypeUtil.getRawClass(type2))))) != null)
/* 875*/                return createConverter(TypeUtil.getRawClass(type), ((KeyAdapter) (obj)), genson.provideConverter(type1));
/* 877*/            else
/* 877*/                return new ComplexMapConverter(genson.provideConverter(type2), genson.provideConverter(type1));
                }

                public static KeyAdapter keyAdapter(Class class1)
                {
/* 881*/            if(java/lang/Object.equals(class1))
/* 881*/                return KeyAdapter.runtimeAdapter;
/* 882*/            if(java/lang/String.equals(class1))
/* 882*/                return KeyAdapter.strAdapter;
/* 883*/            if(Integer.TYPE.equals(class1) || java/lang/Integer.equals(class1))
/* 884*/                return KeyAdapter.intAdapter;
/* 885*/            if(Double.TYPE.equals(class1) || java/lang/Double.equals(class1))
/* 886*/                return KeyAdapter.doubleAdapter;
/* 887*/            if(Long.TYPE.equals(class1) || java/lang/Long.equals(class1))
/* 888*/                return KeyAdapter.longAdapter;
/* 889*/            if(Float.TYPE.equals(class1) || java/lang/Float.equals(class1))
/* 890*/                return KeyAdapter.floatAdapter;
/* 891*/            if(Short.TYPE.equals(class1) || java/lang/Short.equals(class1))
/* 892*/                return KeyAdapter.shortAdapter;
/* 893*/            else
/* 893*/                return null;
                }

                private MapConverter createConverter(Class class1, KeyAdapter keyadapter, Converter converter)
                {
/* 899*/            if(java/util/Properties.equals(class1))
/* 900*/                return new PropertiesConverter(keyadapter, converter);
/* 902*/            if(java/util/Hashtable.equals(class1))
/* 903*/                return new HashTableConverter(keyadapter, converter);
/* 905*/            if(java/util/TreeMap.equals(class1))
/* 906*/                return new TreeMapConverter(keyadapter, converter);
/* 908*/            if(java/util/LinkedHashMap.equals(class1))
/* 909*/                return new LinkedHashMapConverter(keyadapter, converter);
/* 911*/            else
/* 911*/                return new HashMapConverter(keyadapter, converter);
                }

                public final volatile Object create(Type type, Genson genson)
                {
/* 854*/            return create(type, genson);
                }

                public static final MapConverterFactory instance = new MapConverterFactory();


                private MapConverterFactory()
                {
                }
    }

    public static class ComplexMapConverter
        implements Converter
    {

                public void serialize(Map map, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 818*/            objectwriter.beginArray();
/* 819*/            for(map = map.entrySet().iterator(); map.hasNext(); objectwriter.endObject())
                    {
/* 819*/                java.util.Map.Entry entry = (java.util.Map.Entry)map.next();
/* 820*/                objectwriter.beginObject().writeName("key");
/* 821*/                keyConverter.serialize(entry.getKey(), objectwriter, context);
/* 822*/                objectwriter.writeName("value");
/* 823*/                valueConverter.serialize(entry.getValue(), objectwriter, context);
                    }

/* 826*/            objectwriter.endArray();
                }

                public Map deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 831*/            HashMap hashmap = new HashMap();
/* 832*/            objectreader.beginArray();
/* 833*/            for(; objectreader.hasNext(); objectreader.endObject())
                    {
/* 834*/                objectreader.next();
/* 835*/                objectreader.beginObject();
/* 836*/                Object obj = null;
/* 837*/                Object obj1 = null;
/* 838*/                do
                        {
/* 838*/                    if(!objectreader.hasNext())
/* 839*/                        break;
/* 839*/                    objectreader.next();
/* 840*/                    if("key".equals(objectreader.name()))
/* 841*/                        obj = keyConverter.deserialize(objectreader, context);
/* 842*/                    else
/* 842*/                    if("value".equals(objectreader.name()))
/* 843*/                        obj1 = valueConverter.deserialize(objectreader, context);
                        } while(true);
/* 846*/                hashmap.put(obj, obj1);
                    }

/* 849*/            objectreader.endArray();
/* 850*/            return hashmap;
                }

                public volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 805*/            return deserialize(objectreader, context);
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 805*/            serialize((Map)obj, objectwriter, context);
                }

                private final Converter keyConverter;
                private final Converter valueConverter;

                private ComplexMapConverter(Converter converter, Converter converter1)
                {
/* 812*/            keyConverter = converter;
/* 813*/            valueConverter = converter1;
                }

    }

    public static abstract class KeyAdapter
    {

                public abstract Object adapt(String s);

                public abstract String adapt(Object obj);

                public static final KeyAdapter runtimeAdapter = new KeyAdapter() {

                    public final Object adapt(String s)
                    {
/* 728*/                return s;
                    }

                    public final String adapt(Object obj)
                    {
/* 733*/                return obj.toString();
                    }

        };
                public static final KeyAdapter strAdapter = new KeyAdapter() {

                    public final String adapt(String s)
                    {
/* 740*/                return s;
                    }

                    public final volatile String adapt(Object obj)
                    {
/* 737*/                return adapt((String)obj);
                    }

                    public final volatile Object adapt(String s)
                    {
/* 737*/                return adapt(s);
                    }

        };
                public static final KeyAdapter shortAdapter = new KeyAdapter() {

                    public final Short adapt(String s)
                    {
/* 747*/                return Short.valueOf(Short.parseShort(s));
                    }

                    public final String adapt(Short short1)
                    {
/* 752*/                return short1.toString();
                    }

                    public final volatile String adapt(Object obj)
                    {
/* 744*/                return adapt((Short)obj);
                    }

                    public final volatile Object adapt(String s)
                    {
/* 744*/                return adapt(s);
                    }

        };
                public static final KeyAdapter intAdapter = new KeyAdapter() {

                    public final Integer adapt(String s)
                    {
/* 759*/                return Integer.valueOf(Integer.parseInt(s));
                    }

                    public final String adapt(Integer integer)
                    {
/* 764*/                return integer.toString();
                    }

                    public final volatile String adapt(Object obj)
                    {
/* 756*/                return adapt((Integer)obj);
                    }

                    public final volatile Object adapt(String s)
                    {
/* 756*/                return adapt(s);
                    }

        };
                public static final KeyAdapter longAdapter = new KeyAdapter() {

                    public final Long adapt(String s)
                    {
/* 771*/                return Long.valueOf(Long.parseLong(s));
                    }

                    public final String adapt(Long long1)
                    {
/* 776*/                return long1.toString();
                    }

                    public final volatile String adapt(Object obj)
                    {
/* 768*/                return adapt((Long)obj);
                    }

                    public final volatile Object adapt(String s)
                    {
/* 768*/                return adapt(s);
                    }

        };
                public static final KeyAdapter floatAdapter = new KeyAdapter() {

                    public final Float adapt(String s)
                    {
/* 783*/                return Float.valueOf(Float.parseFloat(s));
                    }

                    public final String adapt(Float float1)
                    {
/* 788*/                return float1.toString();
                    }

                    public final volatile String adapt(Object obj)
                    {
/* 780*/                return adapt((Float)obj);
                    }

                    public final volatile Object adapt(String s)
                    {
/* 780*/                return adapt(s);
                    }

        };
                public static final KeyAdapter doubleAdapter = new KeyAdapter() {

                    public final Double adapt(String s)
                    {
/* 795*/                return Double.valueOf(Double.parseDouble(s));
                    }

                    public final String adapt(Double double1)
                    {
/* 800*/                return double1.toString();
                    }

                    public final volatile String adapt(Object obj)
                    {
/* 792*/                return adapt((Double)obj);
                    }

                    public final volatile Object adapt(String s)
                    {
/* 792*/                return adapt(s);
                    }

        };


                public KeyAdapter()
                {
                }
    }

    public static final class LinkedHashMapConverter extends MapConverter
    {

                protected final Map create()
                {
/* 716*/            return new LinkedHashMap();
                }

                public LinkedHashMapConverter(KeyAdapter keyadapter, Converter converter)
                {
/* 711*/            super(keyadapter, converter);
                }
    }

    public static final class TreeMapConverter extends MapConverter
    {

                public final void serialize(Map map, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 696*/            if(((TreeMap)map).comparator() != null)
                    {
/* 697*/                throw new UnsupportedOperationException("Serialization and deserialization of TreeMap with Comparator is not supported. You need to implement a custom Converter to handle it.");
                    } else
                    {
/* 700*/                super.serialize(map, objectwriter, context);
/* 701*/                return;
                    }
                }

                protected final Map create()
                {
/* 705*/            return new TreeMap();
                }

                public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 688*/            serialize((Map)obj, objectwriter, context);
                }

                public TreeMapConverter(KeyAdapter keyadapter, Converter converter)
                {
/* 690*/            super(keyadapter, converter);
                }
    }

    public static final class PropertiesConverter extends MapConverter
    {

                protected final Map create()
                {
/* 684*/            return new Properties();
                }

                public PropertiesConverter(KeyAdapter keyadapter, Converter converter)
                {
/* 679*/            super(keyadapter, converter);
                }
    }

    public static final class HashTableConverter extends MapConverter
    {

                protected final Map create()
                {
/* 671*/            return new Hashtable();
                }

                public HashTableConverter(KeyAdapter keyadapter, Converter converter)
                {
/* 666*/            super(keyadapter, converter);
                }
    }

    public static final class HashMapConverter extends MapConverter
    {

                protected final Map create()
                {
/* 660*/            return new HashMap();
                }

                public HashMapConverter(KeyAdapter keyadapter, Converter converter)
                {
/* 655*/            super(keyadapter, converter);
                }
    }

    public static abstract class MapConverter
        implements Converter
    {

                public Map deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 631*/            objectreader.beginObject();
/* 632*/            Map map = create();
/* 633*/            for(; objectreader.hasNext(); map.put(keyAdapter.adapt(objectreader.name()), valueConverter.deserialize(objectreader, context)))
/* 634*/                objectreader.next();

/* 637*/            objectreader.endObject();
/* 638*/            return map;
                }

                public void serialize(Map map, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 642*/            objectwriter.beginObject();
                    java.util.Map.Entry entry;
/* 643*/            for(map = map.entrySet().iterator(); map.hasNext(); valueConverter.serialize(entry.getValue(), objectwriter, context))
                    {
/* 643*/                entry = (java.util.Map.Entry)map.next();
/* 644*/                objectwriter.writeName(keyAdapter.adapt(entry.getKey()));
                    }

/* 647*/            objectwriter.endObject();
                }

                protected abstract Map create();

                public volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 620*/            return deserialize(objectreader, context);
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 620*/            serialize((Map)obj, objectwriter, context);
                }

                private final Converter valueConverter;
                private final KeyAdapter keyAdapter;

                public MapConverter(KeyAdapter keyadapter, Converter converter)
                {
/* 626*/            keyAdapter = keyadapter;
/* 627*/            valueConverter = converter;
                }
    }

    public static final class PrimitiveConverterFactory
        implements Factory
    {

                public final Converter create(Type type, Genson genson)
                {
/* 605*/            if((type = TypeUtil.getRawClass(type)) == java/lang/Boolean || type == Boolean.TYPE)
/* 607*/                return BooleanConverter.instance;
/* 608*/            if(type == java/lang/Integer || type == Integer.TYPE)
/* 608*/                return IntegerConverter.instance;
/* 609*/            if(type == java/lang/Double || type == Double.TYPE)
/* 609*/                return DoubleConverter.instance;
/* 610*/            if(type == java/lang/Long || type == Long.TYPE)
/* 610*/                return LongConverter.instance;
/* 611*/            if(type == java/lang/Short || type == Short.TYPE)
/* 611*/                return ShortConverter.instance;
/* 612*/            if(type == java/lang/Float || type == Float.TYPE)
/* 612*/                return FloatConverter.instance;
/* 613*/            if(type == java/lang/Character || type == Character.TYPE)
/* 613*/                return CharConverter.instance;
/* 614*/            if(type == java/lang/Byte || type == Byte.TYPE)
/* 614*/                return ByteConverter.instance;
/* 616*/            else
/* 616*/                return null;
                }

                public final volatile Object create(Type type, Genson genson)
                {
/* 598*/            return create(type, genson);
                }

                public static final PrimitiveConverterFactory instance = new PrimitiveConverterFactory();


                private PrimitiveConverterFactory()
                {
                }
    }

    public static final class ByteConverter
        implements Converter
    {

                public final void serialize(Byte byte1, ObjectWriter objectwriter, Context context)
                {
/* 590*/            objectwriter.writeValue(byte1.byteValue());
                }

                public final Byte deserialize(ObjectReader objectreader, Context context)
                {
/* 594*/            return Byte.valueOf((byte)objectreader.valueAsInt());
                }

                public final volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 581*/            return deserialize(objectreader, context);
                }

                public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 581*/            serialize((Byte)obj, objectwriter, context);
                }

                public static final ByteConverter instance = new ByteConverter();


                private ByteConverter()
                {
                }
    }

    public static final class CharConverter
        implements Converter
    {

                public final void serialize(Character character, ObjectWriter objectwriter, Context context)
                {
/* 568*/            objectwriter.writeValue(character.toString());
                }

                public final Character deserialize(ObjectReader objectreader, Context context)
                {
/* 572*/            if((objectreader = objectreader.valueAsString()).length() > 1)
/* 573*/                throw new JsonBindingException("Could not convert a string with length greater than 1 to a single char.");
/* 577*/            else
/* 577*/                return Character.valueOf(objectreader.charAt(0));
                }

                public final volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 559*/            return deserialize(objectreader, context);
                }

                public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 559*/            serialize((Character)obj, objectwriter, context);
                }

                public static final CharConverter instance = new CharConverter();


                private CharConverter()
                {
                }
    }

    public static final class NumberConverter
        implements Converter
    {

                public final Number deserialize(ObjectReader objectreader, Context context)
                {
/* 518*/            context = objectreader.getValueType();
/* 519*/            if(ValueType.INTEGER.equals(context))
/* 520*/                return Integer.valueOf(objectreader.valueAsInt());
/* 521*/            if(ValueType.DOUBLE.equals(context))
/* 522*/                return Double.valueOf(objectreader.valueAsDouble());
/* 524*/            objectreader = objectreader.valueAsString();
/* 525*/            if("".equals(objectreader))
/* 525*/                return null;
/* 525*/            else
/* 525*/                return parse(objectreader, context);
                }

                public final void serialize(Number number, ObjectWriter objectwriter, Context context)
                {
/* 530*/            if(isSpecialNumber(number))
                    {
/* 530*/                objectwriter.writeUnsafeValue(number.toString());
/* 530*/                return;
                    } else
                    {
/* 530*/                objectwriter.writeValue(number);
/* 531*/                return;
                    }
                }

                private boolean isSpecialNumber(Number number)
                {
/* 534*/            if((number instanceof Double) || (number instanceof Float))
/* 535*/                return (number = (Double)number).isInfinite() || number.isNaN();
/* 538*/            else
/* 538*/                return false;
                }

                private Number parse(String s, ValueType valuetype)
                {
/* 544*/            if(s.indexOf('.') >= 0)
/* 545*/                return Double.valueOf(Double.parseDouble(s));
                    long l;
/* 547*/            if((l = Long.parseLong(s)) <= 0x7fffffffL && l >= 0xffffffff80000000L)
/* 549*/                return Integer.valueOf((int)l);
/* 551*/            return Long.valueOf(Long.parseLong(s));
                    NumberFormatException numberformatexception;
/* 552*/            numberformatexception;
/* 553*/            throw new JsonBindingException((new StringBuilder("Could not convert input value ")).append(s).append(" of type ").append(valuetype.toClass()).append(" to a Number type.").toString(), numberformatexception);
                }

                public final volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 509*/            return deserialize(objectreader, context);
                }

                public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 509*/            serialize((Number)obj, objectwriter, context);
                }

                public static final NumberConverter instance = new NumberConverter();


                private NumberConverter()
                {
                }
    }

    public static final class FloatConverter
        implements Converter
    {

                public final Float deserialize(ObjectReader objectreader, Context context)
                {
/* 497*/            return Float.valueOf(objectreader.valueAsFloat());
                }

                public final void serialize(Float float1, ObjectWriter objectwriter, Context context)
                {
/* 501*/            if(float1.isNaN() || float1.isInfinite())
                    {
/* 502*/                objectwriter.writeUnsafeValue(float1.toString());
/* 502*/                return;
                    } else
                    {
/* 504*/                objectwriter.writeValue(float1.floatValue());
/* 506*/                return;
                    }
                }

                public final volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 488*/            return deserialize(objectreader, context);
                }

                public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 488*/            serialize((Float)obj, objectwriter, context);
                }

                public static final FloatConverter instance = new FloatConverter();


                private FloatConverter()
                {
                }
    }

    public static final class DoubleConverter
        implements Converter
    {

                public final Double deserialize(ObjectReader objectreader, Context context)
                {
/* 476*/            return Double.valueOf(objectreader.valueAsDouble());
                }

                public final void serialize(Double double1, ObjectWriter objectwriter, Context context)
                {
/* 480*/            if(double1.isNaN() || double1.isInfinite())
                    {
/* 481*/                objectwriter.writeUnsafeValue(double1.toString());
/* 481*/                return;
                    } else
                    {
/* 483*/                objectwriter.writeValue(double1.doubleValue());
/* 485*/                return;
                    }
                }

                public final volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 467*/            return deserialize(objectreader, context);
                }

                public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 467*/            serialize((Double)obj, objectwriter, context);
                }

                public static final DoubleConverter instance = new DoubleConverter();


                private DoubleConverter()
                {
                }
    }

    public static final class ShortConverter
        implements Converter
    {

                public final Short deserialize(ObjectReader objectreader, Context context)
                {
/* 459*/            return Short.valueOf(objectreader.valueAsShort());
                }

                public final void serialize(Short short1, ObjectWriter objectwriter, Context context)
                {
/* 463*/            objectwriter.writeValue(short1.shortValue());
                }

                public final volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 450*/            return deserialize(objectreader, context);
                }

                public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 450*/            serialize((Short)obj, objectwriter, context);
                }

                public static final ShortConverter instance = new ShortConverter();


                private ShortConverter()
                {
                }
    }

    public static final class LongConverter
        implements Converter
    {

                public final Long deserialize(ObjectReader objectreader, Context context)
                {
/* 442*/            return Long.valueOf(objectreader.valueAsLong());
                }

                public final void serialize(Long long1, ObjectWriter objectwriter, Context context)
                {
/* 446*/            objectwriter.writeValue(long1.longValue());
                }

                public final volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 433*/            return deserialize(objectreader, context);
                }

                public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 433*/            serialize((Long)obj, objectwriter, context);
                }

                public static final LongConverter instance = new LongConverter();


                private LongConverter()
                {
                }
    }

    public static final class IntegerConverter
        implements Converter
    {

                public final void serialize(Integer integer, ObjectWriter objectwriter, Context context)
                {
/* 425*/            objectwriter.writeValue(integer.intValue());
                }

                public final Integer deserialize(ObjectReader objectreader, Context context)
                {
/* 429*/            return Integer.valueOf(objectreader.valueAsInt());
                }

                public final volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 416*/            return deserialize(objectreader, context);
                }

                public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 416*/            serialize((Integer)obj, objectwriter, context);
                }

                public static final IntegerConverter instance = new IntegerConverter();


                private IntegerConverter()
                {
                }
    }

    public static final class BooleanConverter
        implements Converter
    {

                public final void serialize(Boolean boolean1, ObjectWriter objectwriter, Context context)
                {
/* 408*/            objectwriter.writeValue(boolean1.booleanValue());
                }

                public final Boolean deserialize(ObjectReader objectreader, Context context)
                {
/* 412*/            return Boolean.valueOf(objectreader.valueAsBoolean());
                }

                public final volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 399*/            return deserialize(objectreader, context);
                }

                public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 399*/            serialize((Boolean)obj, objectwriter, context);
                }

                public static final BooleanConverter instance = new BooleanConverter();


                private BooleanConverter()
                {
                }
    }

    public static final class StringConverter
        implements Converter
    {

                public final void serialize(String s, ObjectWriter objectwriter, Context context)
                {
/* 391*/            objectwriter.writeValue(s);
                }

                public final String deserialize(ObjectReader objectreader, Context context)
                {
/* 395*/            return objectreader.valueAsString();
                }

                public final volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 382*/            return deserialize(objectreader, context);
                }

                public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 382*/            serialize((String)obj, objectwriter, context);
                }

                public static final StringConverter instance = new StringConverter();


                private StringConverter()
                {
                }
    }

    public static final class ArrayConverterFactory
        implements Factory
    {

                public final Converter create(Type type, Genson genson)
                {
/* 367*/            if((type instanceof GenericArrayType) || (type instanceof Class) && ((Class)type).isArray())
                    {
/* 369*/                if(Byte.TYPE.equals(TypeUtil.getCollectionType(type)))
                        {
/* 370*/                    return ByteArrayConverter.instance;
                        } else
                        {
/* 372*/                    genson = genson.provideConverter(TypeUtil.getCollectionType(type));
/* 374*/                    return new ArrayConverter(TypeUtil.getRawClass(TypeUtil.getCollectionType(type)), genson);
                        }
                    } else
                    {
/* 378*/                return null;
                    }
                }

                public final volatile Object create(Type type, Genson genson)
                {
/* 359*/            return create(type, genson);
                }

                public static final ArrayConverterFactory instance = new ArrayConverterFactory();


                private ArrayConverterFactory()
                {
                }
    }

    public static class ByteArrayAsIntArrayConverter
        implements Converter
    {

                public void serialize(byte abyte0[], ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 338*/            objectwriter.beginArray();
/* 339*/            for(context = 0; context < abyte0.length; context++)
/* 339*/                objectwriter.writeValue(abyte0[context]);

/* 340*/            objectwriter.endArray();
                }

                public byte[] deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 345*/            context = new byte[256];
/* 346*/            objectreader.beginArray();
                    int i;
/* 348*/            for(i = 0; objectreader.hasNext(); i++)
                    {
/* 349*/                objectreader.next();
/* 350*/                Operations.expandArray(context, i, 2D);
/* 351*/                context[i] = (byte)objectreader.valueAsInt();
                    }

/* 353*/            objectreader.endArray();
/* 355*/            return Operations.truncateArray(context, i);
                }

                public volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 329*/            return deserialize(objectreader, context);
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 329*/            serialize((byte[])obj, objectwriter, context);
                }

                public static final ByteArrayAsIntArrayConverter instance = new ByteArrayAsIntArrayConverter();


                private ByteArrayAsIntArrayConverter()
                {
                }
    }

    public static final class ByteArrayConverter
        implements Converter
    {

                public final void serialize(byte abyte0[], ObjectWriter objectwriter, Context context)
                {
/* 320*/            objectwriter.writeValue(abyte0);
                }

                public final byte[] deserialize(ObjectReader objectreader, Context context)
                {
/* 325*/            return objectreader.valueAsByteArray();
                }

                public final volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 311*/            return deserialize(objectreader, context);
                }

                public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 311*/            serialize((byte[])obj, objectwriter, context);
                }

                public static final ByteArrayConverter instance = new ByteArrayConverter();


                private ByteArrayConverter()
                {
                }
    }

    public static class ArrayConverter
        implements Converter
    {

                public void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 274*/            objectwriter.beginArray();
/* 275*/            int i = Array.getLength(obj);
/* 276*/            for(int j = 0; j < i; j++)
                    {
/* 278*/                Object obj1 = Array.get(obj, j);
/* 279*/                elementConverter.serialize(obj1, objectwriter, context);
                    }

/* 281*/            objectwriter.endArray();
                }

                public Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 285*/            objectreader.beginArray();
/* 286*/            int i = 10;
/* 287*/            Object obj = Array.newInstance(eClass, 10);
/* 288*/            int j = 0;
/* 289*/            for(; objectreader.hasNext(); Array.set(obj, j++, elementConverter.deserialize(objectreader, context)))
                    {
/* 290*/                objectreader.next();
/* 291*/                if(j >= i)
                        {
/* 292*/                    i = (i << 1) + 1;
/* 293*/                    obj = expandArray(obj, j, i);
                        }
                    }

/* 297*/            objectreader.endArray();
/* 298*/            if(j < i)
/* 299*/                obj = expandArray(obj, j, j);
/* 301*/            return obj;
                }

                private Object expandArray(Object obj, int i, int j)
                {
/* 305*/            j = ((int) (Array.newInstance(eClass, j)));
/* 306*/            System.arraycopy(obj, 0, j, 0, i);
/* 307*/            return j;
                }

                private final Class eClass;
                private final Converter elementConverter;

                public ArrayConverter(Class class1, Converter converter)
                {
/* 269*/            eClass = class1;
/* 270*/            elementConverter = converter;
                }
    }

    public static final class CollectionConverterFactory
        implements Factory
    {

                public final Converter create(Type type, Genson genson)
                {
/* 239*/            genson = genson.provideConverter(TypeUtil.getCollectionType(type));
/* 241*/            Class class1 = TypeUtil.getRawClass(TypeUtil.getCollectionType(type));
/* 242*/            type = TypeUtil.getRawClass(type);
/* 244*/            if(java/util/EnumSet.isAssignableFrom(type) && class1.isEnum())
/* 245*/                return new EnumSetConverter(class1, genson);
/* 246*/            if(java/util/LinkedHashSet.isAssignableFrom(type))
/* 247*/                return new LinkedHashSetConverter(class1, genson);
/* 248*/            if(java/util/TreeSet.isAssignableFrom(type))
/* 249*/                return new TreeSetConverter(class1, genson);
/* 250*/            if(java/util/Set.isAssignableFrom(type))
/* 251*/                return new SetConverter(class1, genson);
/* 252*/            if(java/util/LinkedList.isAssignableFrom(type))
/* 253*/                return new LinkedListConverter(class1, genson);
/* 254*/            if(java/util/ArrayDeque.isAssignableFrom(type))
/* 255*/                return new ArrayDequeConverter(class1, genson);
/* 256*/            if(java/util/PriorityQueue.isAssignableFrom(type))
/* 257*/                return new PriorityQueueConverter(class1, genson);
/* 259*/            else
/* 259*/                return new CollectionConverter(class1, genson);
                }

                public final volatile Object create(Type type, Genson genson)
                {
/* 231*/            return create(type, genson);
                }

                public static final CollectionConverterFactory instance = new CollectionConverterFactory();


                private CollectionConverterFactory()
                {
                }
    }

    public static final class SingleValueAsListFactory
        implements Factory
    {

                public final Converter create(final Type defaultConverter, Genson genson)
                {
/* 209*/            defaultConverter = (CollectionConverter)defaultFactory.create(defaultConverter, genson);
/* 211*/            return new Converter() {

                        public void serialize(Collection collection, ObjectWriter objectwriter, Context context)
                            throws Exception
                        {
/* 214*/                    defaultConverter.serialize(collection, objectwriter, context);
                        }

                        public Collection deserialize(ObjectReader objectreader, Context context)
                            throws Exception
                        {
                            ValueType valuetype;
/* 219*/                    if((valuetype = objectreader.getValueType()) != ValueType.ARRAY && valuetype != ValueType.NULL)
                            {
/* 221*/                        objectreader = ((ObjectReader) (defaultConverter.getElementConverter().deserialize(objectreader, context)));
/* 222*/                        (context = defaultConverter.create()).add(objectreader);
/* 224*/                        return context;
                            } else
                            {
/* 225*/                        return defaultConverter.deserialize(objectreader, context);
                            }
                        }

                        public volatile Object deserialize(ObjectReader objectreader, Context context)
                            throws Exception
                        {
/* 211*/                    return deserialize(objectreader, context);
                        }

                        public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                            throws Exception
                        {
/* 211*/                    serialize((Collection)obj, objectwriter, context);
                        }

                        final CollectionConverter val$defaultConverter;
                        final SingleValueAsListFactory this$0;

                        
                        {
/* 211*/                    this$0 = SingleValueAsListFactory.this;
/* 211*/                    defaultConverter = collectionconverter;
/* 211*/                    super();
                        }
            };
                }

                public final volatile Object create(Type type, Genson genson)
                {
/* 200*/            return create(type, genson);
                }

                public static final SingleValueAsListFactory instance = new SingleValueAsListFactory();
                Factory defaultFactory;


                private SingleValueAsListFactory()
                {
/* 203*/            defaultFactory = CollectionConverterFactory.instance;
                }
    }

    public static class CollectionConverter
        implements Converter
    {

                public Collection deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 172*/            objectreader.beginArray();
/* 173*/            Collection collection = create();
                    Object obj;
/* 174*/            for(; objectreader.hasNext(); collection.add(obj))
                    {
/* 175*/                objectreader.next();
/* 176*/                obj = elementConverter.deserialize(objectreader, context);
                    }

/* 179*/            objectreader.endArray();
/* 180*/            return collection;
                }

                public void serialize(Collection collection, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 184*/            objectwriter.beginArray();
                    Object obj;
/* 185*/            for(collection = collection.iterator(); collection.hasNext(); elementConverter.serialize(obj, objectwriter, context))
/* 185*/                obj = collection.next();

/* 188*/            objectwriter.endArray();
                }

                public Converter getElementConverter()
                {
/* 192*/            return elementConverter;
                }

                protected Collection create()
                {
/* 196*/            return new ArrayList();
                }

                public volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 159*/            return deserialize(objectreader, context);
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 159*/            serialize((Collection)obj, objectwriter, context);
                }

                private final Class eClass;
                private final Converter elementConverter;

                public CollectionConverter(Class class1, Converter converter)
                {
/* 167*/            eClass = class1;
/* 168*/            elementConverter = converter;
                }
    }

    public static class EnumSetConverter extends CollectionConverter
    {

                protected Collection create()
                {
/* 155*/            return EnumSet.noneOf(eClass);
                }

                private final Class eClass;

                public EnumSetConverter(Class class1, Converter converter)
                {
/* 148*/            super(class1, converter);
/* 149*/            eClass = class1;
                }
    }

    public static class PriorityQueueConverter extends CollectionConverter
    {

                public void serialize(Collection collection, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
                    PriorityQueue priorityqueue;
/* 129*/            if((priorityqueue = (PriorityQueue)collection).comparator() != null)
                    {
/* 131*/                throw new UnsupportedOperationException("Serialization and deserialization of PriorityQueue with Comparator is not supported. You need to implement a custom Converter to handle it.");
                    } else
                    {
/* 134*/                super.serialize(collection, objectwriter, context);
/* 135*/                return;
                    }
                }

                protected Collection create()
                {
/* 139*/            return new PriorityQueue();
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 120*/            serialize((Collection)obj, objectwriter, context);
                }

                public PriorityQueueConverter(Class class1, Converter converter)
                {
/* 124*/            super(class1, converter);
                }
    }

    public static class ArrayDequeConverter extends CollectionConverter
    {

                protected Collection create()
                {
/* 116*/            return new ArrayDeque();
                }

                public ArrayDequeConverter(Class class1, Converter converter)
                {
/* 111*/            super(class1, converter);
                }
    }

    public static class LinkedHashSetConverter extends CollectionConverter
    {

                protected Collection create()
                {
/* 103*/            return new LinkedHashSet();
                }

                public LinkedHashSetConverter(Class class1, Converter converter)
                {
/*  98*/            super(class1, converter);
                }
    }

    public static class TreeSetConverter extends CollectionConverter
    {

                public void serialize(Collection collection, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
                    TreeSet treeset;
/*  80*/            if((treeset = (TreeSet)collection).comparator() != null)
                    {
/*  82*/                throw new UnsupportedOperationException("Serialization and deserialization of TreeSet with Comparator is not supported. You need to implement a custom Converter to handle it.");
                    } else
                    {
/*  85*/                super.serialize(collection, objectwriter, context);
/*  86*/                return;
                    }
                }

                protected Collection create()
                {
/*  90*/            return new TreeSet();
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/*  71*/            serialize((Collection)obj, objectwriter, context);
                }

                public TreeSetConverter(Class class1, Converter converter)
                {
/*  75*/            super(class1, converter);
                }
    }

    public static class LinkedListConverter extends CollectionConverter
    {

                protected Collection create()
                {
/*  67*/            return new LinkedList();
                }

                public LinkedListConverter(Class class1, Converter converter)
                {
/*  62*/            super(class1, converter);
                }
    }

    public static class SetConverter extends CollectionConverter
    {

                protected Collection create()
                {
/*  54*/            return new HashSet();
                }

                public SetConverter(Class class1, Converter converter)
                {
/*  49*/            super(class1, converter);
                }
    }


            private DefaultConverters()
            {
            }
}
