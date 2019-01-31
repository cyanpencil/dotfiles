// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GenericEntity.java

package javax.ws.rs.core;

import java.lang.reflect.*;

// Referenced classes of package javax.ws.rs.core:
//            GenericType

public class GenericEntity
{

            protected GenericEntity(Object obj)
            {
/* 124*/        if(obj == null)
                {
/* 125*/            throw new IllegalArgumentException("The entity must not be null");
                } else
                {
/* 127*/            entity = obj;
/* 128*/            type = GenericType.getTypeArgument(getClass(), javax/ws/rs/core/GenericEntity);
/* 129*/            rawType = obj.getClass();
/* 130*/            return;
                }
            }

            public GenericEntity(Object obj, Type type1)
            {
/* 147*/        if(obj == null || type1 == null)
                {
/* 148*/            throw new IllegalArgumentException("Arguments must not be null.");
                } else
                {
/* 150*/            entity = obj;
/* 151*/            rawType = obj.getClass();
/* 152*/            checkTypeCompatibility(rawType, type1);
/* 153*/            type = type1;
/* 154*/            return;
                }
            }

            private void checkTypeCompatibility(Class class1, Type type1)
            {
/* 157*/        do
                {
/* 157*/            if(type1 instanceof Class)
                    {
/* 158*/                if((type1 = (Class)type1).isAssignableFrom(class1))
/* 160*/                    return;
/* 162*/                break;
                    }
/* 162*/            if(type1 instanceof ParameterizedType)
                    {
/* 163*/                type1 = (type1 = (ParameterizedType)type1).getRawType();
/* 165*/                type1 = type1;
/* 165*/                class1 = class1;
/* 165*/                this = this;
/* 165*/                continue;
                    }
/* 167*/            if(!class1.isArray() || !(type1 instanceof GenericArrayType))
/* 168*/                break;
/* 168*/            type1 = (type1 = (GenericArrayType)type1).getGenericComponentType();
/* 170*/            type1 = type1;
/* 170*/            class1 = class1.getComponentType();
/* 170*/            this = this;
                } while(true);
/* 173*/        throw new IllegalArgumentException("The type is incompatible with the class of the entity.");
            }

            public final Class getRawType()
            {
/* 184*/        return rawType;
            }

            public final Type getType()
            {
/* 196*/        return type;
            }

            public final Object getEntity()
            {
/* 205*/        return entity;
            }

            public boolean equals(Object obj)
            {
                boolean flag;
/* 210*/        if(!(flag = this == obj) && (obj instanceof GenericEntity))
                {
/* 213*/            obj = (GenericEntity)obj;
/* 214*/            return type.equals(((GenericEntity) (obj)).type) && entity.equals(((GenericEntity) (obj)).entity);
                } else
                {
/* 216*/            return flag;
                }
            }

            public int hashCode()
            {
/* 221*/        return entity.hashCode() + type.hashCode() * 37 + 5;
            }

            public String toString()
            {
/* 226*/        return (new StringBuilder("GenericEntity{")).append(entity.toString()).append(", ").append(type.toString()).append("}").toString();
            }

            private final Class rawType;
            private final Type type;
            private final Object entity;
}
