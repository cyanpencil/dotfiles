// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanDescriptor.java

package com.owlike.genson.reflect;

import com.owlike.genson.*;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import java.util.*;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanCreator, PropertyAccessor, PropertyMutator, RuntimePropertyFilter, 
//            BeanProperty

public class BeanDescriptor
    implements Converter
{

            public BeanDescriptor(Class class1, Class class2, List list, Map map, BeanCreator beancreator, boolean flag)
            {
/*  62*/        ofClass = class1;
/*  63*/        fromDeclaringClass = class2;
/*  64*/        creator = beancreator;
/*  65*/        failOnMissingProperty = flag;
/*  66*/        mutableProperties = map;
/*  68*/        Collections.sort(list, _readablePropsComparator);
/*  70*/        accessibleProperties = Collections.unmodifiableList(list);
/*  71*/        if(creator != null)
                {
/*  72*/            _noArgCtr = creator.parameters.size() == 0;
/*  73*/            globalCreatorArgs = new Object[beancreator.parameters.size()];
/*  74*/            Arrays.fill(globalCreatorArgs, MISSING);
/*  74*/            return;
                } else
                {
/*  76*/            _noArgCtr = false;
/*  78*/            return;
                }
            }

            public boolean isReadable()
            {
/*  81*/        return !accessibleProperties.isEmpty();
            }

            public boolean isWritable()
            {
/*  85*/        return creator != null;
            }

            public void serialize(Object obj, ObjectWriter objectwriter, Context context)
            {
/*  89*/        objectwriter.beginObject();
/*  90*/        RuntimePropertyFilter runtimepropertyfilter = context.genson.runtimePropertyFilter();
/*  91*/        Iterator iterator = accessibleProperties.iterator();
/*  91*/        do
                {
/*  91*/            if(!iterator.hasNext())
/*  91*/                break;
/*  91*/            PropertyAccessor propertyaccessor = (PropertyAccessor)iterator.next();
/*  92*/            if(runtimepropertyfilter.shouldInclude(propertyaccessor, context))
/*  92*/                propertyaccessor.serialize(obj, objectwriter, context);
                } while(true);
/*  94*/        objectwriter.endObject();
            }

            public Object deserialize(ObjectReader objectreader, Context context)
            {
                Object obj;
/* 100*/        if(_noArgCtr)
                {
/* 101*/            obj = ofClass.cast(creator.create(new Object[0]));
/* 102*/            deserialize(obj, objectreader, context);
                } else
                {
/* 104*/            if(creator == null)
/* 105*/                throw new JsonBindingException((new StringBuilder("No constructor has been found for type ")).append(ofClass).toString());
/* 107*/            obj = _deserWithCtrArgs(objectreader, context);
                }
/* 109*/        return obj;
            }

            public void deserialize(Object obj, ObjectReader objectreader, Context context)
            {
/* 113*/        objectreader.beginObject();
/* 114*/        RuntimePropertyFilter runtimepropertyfilter = context.genson.runtimePropertyFilter();
/* 115*/        while(objectreader.hasNext()) 
                {
/* 116*/            objectreader.next();
/* 117*/            String s = objectreader.name();
                    PropertyMutator propertymutator;
/* 118*/            if((propertymutator = (PropertyMutator)mutableProperties.get(s)) != null)
                    {
/* 120*/                if(runtimepropertyfilter.shouldInclude(propertymutator, context))
/* 121*/                    propertymutator.deserialize(obj, objectreader, context);
/* 123*/                else
/* 123*/                    objectreader.skipValue();
                    } else
                    {
/* 125*/                if(failOnMissingProperty)
/* 125*/                    throw missingPropertyException(s);
/* 126*/                objectreader.skipValue();
                    }
                }
/* 128*/        objectreader.endObject();
            }

            protected Object _deserWithCtrArgs(ObjectReader objectreader, Context context)
            {
/* 133*/        ArrayList arraylist = new ArrayList();
/* 134*/        ArrayList arraylist1 = new ArrayList();
/* 135*/        RuntimePropertyFilter runtimepropertyfilter = context.genson.runtimePropertyFilter();
/* 137*/        objectreader.beginObject();
/* 138*/        while(objectreader.hasNext()) 
                {
/* 139*/            objectreader.next();
/* 140*/            String s = objectreader.name();
                    PropertyMutator propertymutator;
/* 141*/            if((propertymutator = (PropertyMutator)mutableProperties.get(s)) != null)
                    {
/* 144*/                if(runtimepropertyfilter.shouldInclude(propertymutator, context))
                        {
/* 145*/                    Object obj = propertymutator.deserialize(objectreader, context);
/* 146*/                    arraylist.add(s);
/* 147*/                    arraylist1.add(obj);
                        } else
                        {
/* 149*/                    objectreader.skipValue();
                        }
                    } else
                    {
/* 151*/                if(failOnMissingProperty)
/* 151*/                    throw missingPropertyException(s);
/* 152*/                objectreader.skipValue();
                    }
                }
/* 155*/        int i = arraylist.size();
/* 156*/        int j = 0;
/* 157*/        Object aobj[] = (Object[])((Object []) (globalCreatorArgs)).clone();
/* 158*/        String as[] = new String[i];
/* 159*/        Object aobj1[] = new Object[i];
/* 161*/        int k = 0;
/* 161*/        int l = 0;
/* 161*/        for(; k < i; k++)
                {
                    BeanCreator.BeanCreatorProperty beancreatorproperty;
/* 162*/            if((beancreatorproperty = (BeanCreator.BeanCreatorProperty)creator.paramsAndAliases.get(arraylist.get(k))) != null)
                    {
/* 164*/                aobj[beancreatorproperty.index] = arraylist1.get(k);
/* 165*/                j++;
                    } else
                    {
/* 167*/                as[l] = (String)arraylist.get(k);
/* 168*/                aobj1[l] = arraylist1.get(k);
/* 169*/                l++;
                    }
                }

/* 173*/        if(j < creator.parameters.size())
/* 173*/            updateWithDefaultValues(aobj, context.genson);
/* 175*/        Object obj1 = ofClass.cast(creator.create(aobj));
/* 176*/        for(int i1 = 0; i1 < i; i1++)
                {
                    PropertyMutator propertymutator1;
/* 177*/            if((propertymutator1 = (PropertyMutator)mutableProperties.get(as[i1])) != null)
/* 178*/                propertymutator1.mutate(obj1, aobj1[i1]);
                }

/* 180*/        objectreader.endObject();
/* 181*/        return obj1;
            }

            private void updateWithDefaultValues(Object aobj[], Genson genson)
            {
/* 185*/label0:
/* 185*/        for(int i = 0; i < aobj.length; i++)
                {
/* 186*/            if(aobj[i] != MISSING)
/* 187*/                continue;
/* 187*/            Iterator iterator = creator.parameters.values().iterator();
                    BeanCreator.BeanCreatorProperty beancreatorproperty;
/* 187*/            do
/* 187*/                if(!iterator.hasNext())
/* 187*/                    continue label0;
/* 188*/            while((beancreatorproperty = (BeanCreator.BeanCreatorProperty)iterator.next()).index != i);
/* 189*/            aobj[i] = genson.defaultValue(beancreatorproperty.getRawClass());
                }

            }

            public Class getOfClass()
            {
/* 198*/        return ofClass;
            }

            private JsonBindingException missingPropertyException(String s)
            {
/* 202*/        return new JsonBindingException((new StringBuilder("No matching property in ")).append(getOfClass()).append(" for key ").append(s).toString());
            }

            final Class fromDeclaringClass;
            final Class ofClass;
            final Map mutableProperties;
            final List accessibleProperties;
            final boolean failOnMissingProperty;
            final BeanCreator creator;
            private final boolean _noArgCtr;
            private static final Object MISSING = new Object();
            private Object globalCreatorArgs[];
            private static final Comparator _readablePropsComparator = new Comparator() {

                public final int compare(BeanProperty beanproperty, BeanProperty beanproperty1)
                {
/*  54*/            return beanproperty.name.compareToIgnoreCase(beanproperty1.name);
                }

                public final volatile int compare(Object obj, Object obj1)
                {
/*  52*/            return compare((BeanProperty)obj, (BeanProperty)obj1);
                }

    };

}
