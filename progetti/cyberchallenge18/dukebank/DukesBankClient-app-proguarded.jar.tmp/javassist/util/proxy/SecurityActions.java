// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SecurityActions.java

package javassist.util.proxy;

import java.lang.reflect.*;
import java.security.*;

class SecurityActions
{

            SecurityActions()
            {
            }

            static Method[] getDeclaredMethods(Class class1)
            {
/*  29*/        if(System.getSecurityManager() == null)
/*  30*/            return class1.getDeclaredMethods();
/*  32*/        else
/*  32*/            return (Method[])AccessController.doPrivileged(new PrivilegedAction(class1) {

                        public final Object run()
                        {
/*  35*/                    return clazz.getDeclaredMethods();
                        }

                        final Class val$clazz;

                    
                    {
/*  33*/                clazz = class1;
/*  33*/                super();
                    }
            });
            }

            static Constructor[] getDeclaredConstructors(Class class1)
            {
/*  42*/        if(System.getSecurityManager() == null)
/*  43*/            return class1.getDeclaredConstructors();
/*  45*/        else
/*  45*/            return (Constructor[])AccessController.doPrivileged(new PrivilegedAction(class1) {

                        public final Object run()
                        {
/*  48*/                    return clazz.getDeclaredConstructors();
                        }

                        final Class val$clazz;

                    
                    {
/*  46*/                clazz = class1;
/*  46*/                super();
                    }
            });
            }

            static Method getDeclaredMethod(Class class1, String s, Class aclass[])
                throws NoSuchMethodException
            {
/*  56*/        if(System.getSecurityManager() == null)
/*  57*/            return class1.getDeclaredMethod(s, aclass);
/*  60*/        return (Method)AccessController.doPrivileged(new PrivilegedExceptionAction(class1, s, aclass) {

                    public final Object run()
                        throws Exception
                    {
/*  63*/                return clazz.getDeclaredMethod(name, types);
                    }

                    final Class val$clazz;
                    final String val$name;
                    final Class val$types[];

                    
                    {
/*  61*/                clazz = class1;
/*  61*/                name = s;
/*  61*/                types = aclass;
/*  61*/                super();
                    }
        });
/*  67*/        JVM INSTR dup ;
/*  68*/        class1;
/*  68*/        getCause();
/*  68*/        JVM INSTR instanceof #6   <Class NoSuchMethodException>;
/*  68*/        JVM INSTR ifeq 49;
                   goto _L1 _L2
_L1:
/*  69*/        break MISSING_BLOCK_LABEL_41;
_L2:
/*  69*/        break MISSING_BLOCK_LABEL_49;
/*  69*/        throw (NoSuchMethodException)class1.getCause();
/*  71*/        throw new RuntimeException(class1.getCause());
            }

            static Constructor getDeclaredConstructor(Class class1, Class aclass[])
                throws NoSuchMethodException
            {
/*  80*/        if(System.getSecurityManager() == null)
/*  81*/            return class1.getDeclaredConstructor(aclass);
/*  84*/        return (Constructor)AccessController.doPrivileged(new PrivilegedExceptionAction(class1, aclass) {

                    public final Object run()
                        throws Exception
                    {
/*  87*/                return clazz.getDeclaredConstructor(types);
                    }

                    final Class val$clazz;
                    final Class val$types[];

                    
                    {
/*  85*/                clazz = class1;
/*  85*/                types = aclass;
/*  85*/                super();
                    }
        });
/*  91*/        JVM INSTR dup ;
/*  92*/        class1;
/*  92*/        getCause();
/*  92*/        JVM INSTR instanceof #6   <Class NoSuchMethodException>;
/*  92*/        JVM INSTR ifeq 47;
                   goto _L1 _L2
_L1:
/*  93*/        break MISSING_BLOCK_LABEL_39;
_L2:
/*  93*/        break MISSING_BLOCK_LABEL_47;
/*  93*/        throw (NoSuchMethodException)class1.getCause();
/*  95*/        throw new RuntimeException(class1.getCause());
            }

            static void setAccessible(AccessibleObject accessibleobject, boolean flag)
            {
/* 102*/        if(System.getSecurityManager() == null)
                {
/* 103*/            accessibleobject.setAccessible(flag);
/* 103*/            return;
                } else
                {
/* 105*/            AccessController.doPrivileged(new PrivilegedAction(accessibleobject, flag) {

                        public final Object run()
                        {
/* 107*/                    ao.setAccessible(accessible);
/* 108*/                    return null;
                        }

                        final AccessibleObject val$ao;
                        final boolean val$accessible;

                    
                    {
/* 105*/                ao = accessibleobject;
/* 105*/                accessible = flag;
/* 105*/                super();
                    }
            });
/* 112*/            return;
                }
            }

            static void set(Field field, Object obj, Object obj1)
                throws IllegalAccessException
            {
/* 117*/        if(System.getSecurityManager() == null)
                {
/* 118*/            field.set(obj, obj1);
/* 118*/            return;
                }
/* 121*/        AccessController.doPrivileged(new PrivilegedExceptionAction(field, obj, obj1) {

                    public final Object run()
                        throws Exception
                    {
/* 123*/                fld.set(target, value);
/* 124*/                return null;
                    }

                    final Field val$fld;
                    final Object val$target;
                    final Object val$value;

                    
                    {
/* 121*/                fld = field;
/* 121*/                target = obj;
/* 121*/                value = obj1;
/* 121*/                super();
                    }
        });
/* 133*/        return;
/* 128*/        JVM INSTR dup ;
/* 129*/        field;
/* 129*/        getCause();
/* 129*/        JVM INSTR instanceof #6   <Class NoSuchMethodException>;
/* 129*/        JVM INSTR ifeq 47;
                   goto _L1 _L2
_L1:
/* 130*/        break MISSING_BLOCK_LABEL_39;
_L2:
/* 130*/        break MISSING_BLOCK_LABEL_47;
/* 130*/        throw (IllegalAccessException)field.getCause();
/* 132*/        throw new RuntimeException(field.getCause());
            }
}
