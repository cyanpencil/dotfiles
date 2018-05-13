// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PropertiesHelper.java

package org.glassfish.jersey.internal.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.RuntimeType;
import org.glassfish.jersey.internal.LocalizationMessages;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            ReflectionHelper

public final class PropertiesHelper
{

            public static PrivilegedAction getSystemProperties()
            {
/*  75*/        return new PrivilegedAction() {

                    public final Properties run()
                    {
/*  78*/                return System.getProperties();
                    }

                    public final volatile Object run()
                    {
/*  75*/                return run();
                    }

        };
            }

            public static PrivilegedAction getSystemProperty(String s)
            {
/*  94*/        return new PrivilegedAction(s) {

                    public final String run()
                    {
/*  97*/                return System.getProperty(name);
                    }

                    public final volatile Object run()
                    {
/*  94*/                return run();
                    }

                    final String val$name;

                    
                    {
/*  94*/                name = s;
/*  94*/                super();
                    }
        };
            }

            public static PrivilegedAction getSystemProperty(String s, String s1)
            {
/* 114*/        return new PrivilegedAction(s, s1) {

                    public final String run()
                    {
/* 117*/                return System.getProperty(name, def);
                    }

                    public final volatile Object run()
                    {
/* 114*/                return run();
                    }

                    final String val$name;
                    final String val$def;

                    
                    {
/* 114*/                name = s;
/* 114*/                def = s1;
/* 114*/                super();
                    }
        };
            }

            public static Object getValue(Map map, String s, Object obj, Map map1)
            {
/* 135*/        return getValue(map, ((RuntimeType) (null)), s, obj, map1);
            }

            public static Object getValue(Map map, RuntimeType runtimetype, String s, Object obj, Map map1)
            {
/* 160*/        return getValue(map, runtimetype, s, obj, obj.getClass(), map1);
            }

            public static Object getValue(Map map, String s, Object obj, Class class1, Map map1)
            {
/* 180*/        return getValue(map, null, s, obj, class1, map1);
            }

            public static Object getValue(Map map, RuntimeType runtimetype, String s, Object obj, Class class1, Map map1)
            {
/* 201*/        if((map = ((Map) (getValue(map, runtimetype, s, class1, map1)))) == null)
/* 203*/            map = ((Map) (obj));
/* 205*/        return map;
            }

            public static Object getValue(Map map, String s, Class class1, Map map1)
            {
/* 220*/        return getValue(map, ((RuntimeType) (null)), s, class1, map1);
            }

            public static Object getValue(Map map, RuntimeType runtimetype, String s, Class class1, Map map1)
            {
/* 239*/        Object obj = null;
/* 240*/        if(runtimetype != null)
                {
/* 241*/            obj = getPropertyNameForRuntime(s, runtimetype);
/* 242*/            if(s.equals(obj))
/* 244*/                obj = (new StringBuilder()).append(s).append(".").append(runtimetype.name().toLowerCase()).toString();
/* 246*/            obj = map.get(obj);
                }
/* 248*/        if(obj == null)
/* 249*/            obj = map.get(s);
/* 251*/        if(obj == null)
/* 252*/            obj = getLegacyFallbackValue(map, map1, s);
/* 254*/        if(obj == null)
/* 255*/            return null;
/* 258*/        else
/* 258*/            return convertValue(obj, class1);
            }

            public static String getPropertyNameForRuntime(String s, RuntimeType runtimetype)
            {
/* 275*/        if(runtimetype != null && s.startsWith("jersey.config"))
                {
                    RuntimeType aruntimetype[];
/* 276*/            int i = (aruntimetype = aruntimetype = RuntimeType.values()).length;
/* 277*/            for(int j = 0; j < i; j++)
                    {
/* 277*/                RuntimeType runtimetype1 = aruntimetype[j];
/* 278*/                if(s.startsWith((new StringBuilder("jersey.config.")).append(runtimetype1.name().toLowerCase()).toString()))
/* 279*/                    return s;
                    }

/* 282*/            return s.replace("jersey.config", (new StringBuilder("jersey.config.")).append(runtimetype.name().toLowerCase()).toString());
                } else
                {
/* 284*/            return s;
                }
            }

            private static Object getLegacyFallbackValue(Map map, Map map1, String s)
            {
/* 288*/        if(map1 == null || !map1.containsKey(s))
/* 289*/            return null;
/* 291*/        map1 = (String)map1.get(s);
/* 292*/        if((map = ((Map) (map.get(map1)))) != null && LOGGER.isLoggable(Level.CONFIG))
/* 294*/            LOGGER.config(LocalizationMessages.PROPERTIES_HELPER_DEPRECATED_PROPERTY_NAME(map1, s));
/* 296*/        return map;
            }

            public static Object convertValue(Object obj, Class class1)
            {
                Object obj1;
/* 308*/        if(class1.isInstance(obj))
/* 310*/            break MISSING_BLOCK_LABEL_113;
/* 310*/        if((obj1 = (Constructor)AccessController.doPrivileged(ReflectionHelper.getStringConstructorPA(class1))) == null)
/* 313*/            break MISSING_BLOCK_LABEL_41;
/* 313*/        return class1.cast(((Constructor) (obj1)).newInstance(new Object[] {
/* 313*/            obj
                }));
/* 314*/        JVM INSTR pop ;
/* 319*/        if((obj1 = (Method)AccessController.doPrivileged(ReflectionHelper.getValueOfStringMethodPA(class1))) == null)
/* 322*/            break MISSING_BLOCK_LABEL_75;
/* 322*/        return class1.cast(((Method) (obj1)).invoke(null, new Object[] {
/* 322*/            obj
                }));
/* 323*/        JVM INSTR pop ;
/* 329*/        if(LOGGER.isLoggable(Level.WARNING))
/* 330*/            LOGGER.warning(LocalizationMessages.PROPERTIES_HELPER_GET_VALUE_NO_TRANSFORM(String.valueOf(obj), obj.getClass().getName(), class1.getName()));
/* 334*/        return null;
/* 337*/        return class1.cast(obj);
            }

            public static boolean isProperty(Map map, String s)
            {
/* 349*/        return map.containsKey(s) && isProperty(map.get(s));
            }

            public static boolean isProperty(Object obj)
            {
/* 359*/        if(obj instanceof Boolean)
/* 360*/            return ((Boolean)java/lang/Boolean.cast(obj)).booleanValue();
/* 362*/        return obj != null && Boolean.parseBoolean(obj.toString());
            }

            private PropertiesHelper()
            {
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/internal/util/PropertiesHelper.getName());

}
