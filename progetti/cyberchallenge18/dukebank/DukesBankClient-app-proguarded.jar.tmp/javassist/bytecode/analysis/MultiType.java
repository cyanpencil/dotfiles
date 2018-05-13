// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MultiType.java

package javassist.bytecode.analysis;

import java.util.*;
import javassist.CtClass;

// Referenced classes of package javassist.bytecode.analysis:
//            Type

public class MultiType extends Type
{

            public MultiType(Map map)
            {
/*  57*/        this(map, null);
            }

            public MultiType(Map map, Type type)
            {
/*  61*/        super(null);
/*  54*/        changed = false;
/*  62*/        interfaces = map;
/*  63*/        potentialClass = type;
            }

            public CtClass getCtClass()
            {
/*  71*/        if(resolved != null)
/*  72*/            return resolved.getCtClass();
/*  74*/        else
/*  74*/            return Type.OBJECT.getCtClass();
            }

            public Type getComponent()
            {
/*  81*/        return null;
            }

            public int getSize()
            {
/*  88*/        return 1;
            }

            public boolean isArray()
            {
/*  95*/        return false;
            }

            boolean popChanged()
            {
/* 102*/        boolean flag = changed;
/* 103*/        changed = false;
/* 104*/        return flag;
            }

            public boolean isAssignableFrom(Type type)
            {
/* 108*/        throw new UnsupportedOperationException("Not implemented");
            }

            public boolean isAssignableTo(Type type)
            {
/* 112*/        if(resolved != null)
/* 113*/            return type.isAssignableFrom(resolved);
/* 115*/        if(Type.OBJECT.equals(type))
/* 116*/            return true;
/* 118*/        if(potentialClass != null && !type.isAssignableFrom(potentialClass))
/* 119*/            potentialClass = null;
/* 121*/        if((type = mergeMultiAndSingle(this, type)).size() == 1 && potentialClass == null)
                {
/* 125*/            resolved = Type.get((CtClass)type.values().iterator().next());
/* 126*/            propogateResolved();
/* 128*/            return true;
                }
/* 132*/        if(type.size() > 0)
                {
/* 133*/            interfaces = type;
/* 134*/            propogateState();
/* 136*/            return true;
                }
/* 139*/        if(potentialClass != null)
                {
/* 140*/            resolved = potentialClass;
/* 141*/            propogateResolved();
/* 143*/            return true;
                } else
                {
/* 146*/            return false;
                }
            }

            private void propogateState()
            {
/* 150*/        for(MultiType multitype = mergeSource; multitype != null; multitype = multitype.mergeSource)
                {
/* 152*/            multitype.interfaces = interfaces;
/* 153*/            multitype.potentialClass = potentialClass;
                }

            }

            private void propogateResolved()
            {
/* 159*/        for(MultiType multitype = mergeSource; multitype != null; multitype = multitype.mergeSource)
/* 161*/            multitype.resolved = resolved;

            }

            public boolean isReference()
            {
/* 172*/        return true;
            }

            private Map getAllMultiInterfaces(MultiType multitype)
            {
/* 176*/        HashMap hashmap = new HashMap();
                CtClass ctclass;
/* 178*/        for(multitype = multitype.interfaces.values().iterator(); multitype.hasNext(); getAllInterfaces(ctclass, hashmap))
                {
/* 180*/            ctclass = (CtClass)multitype.next();
/* 181*/            hashmap.put(ctclass.getName(), ctclass);
                }

/* 185*/        return hashmap;
            }

            private Map mergeMultiInterfaces(MultiType multitype, MultiType multitype1)
            {
/* 190*/        multitype = getAllMultiInterfaces(multitype);
/* 191*/        multitype1 = getAllMultiInterfaces(multitype1);
/* 193*/        return findCommonInterfaces(multitype, multitype1);
            }

            private Map mergeMultiAndSingle(MultiType multitype, Type type)
            {
/* 197*/        multitype = getAllMultiInterfaces(multitype);
/* 198*/        type = getAllInterfaces(type.getCtClass(), null);
/* 200*/        return findCommonInterfaces(multitype, type);
            }

            private boolean inMergeSource(MultiType multitype)
            {
/* 204*/        for(; multitype != null; multitype = multitype.mergeSource)
/* 205*/            if(multitype == this)
/* 206*/                return true;

/* 211*/        return false;
            }

            public Type merge(Type type)
            {
/* 215*/        if(this == type)
/* 216*/            return this;
/* 218*/        if(type == UNINIT)
/* 219*/            return this;
/* 221*/        if(type == BOGUS)
/* 222*/            return BOGUS;
/* 224*/        if(type == null)
/* 225*/            return this;
/* 227*/        if(resolved != null)
/* 228*/            return resolved.merge(type);
                Object obj;
/* 230*/        if(potentialClass != null && (!((Type) (obj = potentialClass.merge(type))).equals(potentialClass) || ((Type) (obj)).popChanged()))
                {
/* 233*/            potentialClass = Type.OBJECT.equals(obj) ? null : ((Type) (obj));
/* 234*/            changed = true;
                }
/* 240*/        if(type instanceof MultiType)
                {
/* 241*/            if(((MultiType) (type = (MultiType)type)).resolved != null)
                    {
/* 244*/                obj = mergeMultiAndSingle(this, ((MultiType) (type)).resolved);
                    } else
                    {
/* 246*/                obj = mergeMultiInterfaces(type, this);
/* 247*/                if(!inMergeSource(type))
/* 248*/                    mergeSource = type;
                    }
                } else
                {
/* 251*/            obj = mergeMultiAndSingle(this, type);
                }
/* 255*/        if(((Map) (obj)).size() > 1 || ((Map) (obj)).size() == 1 && potentialClass != null)
                {
/* 257*/            if(((Map) (obj)).size() != interfaces.size())
/* 258*/                changed = true;
/* 259*/            else
/* 259*/            if(!changed)
                    {
/* 260*/                type = ((Map) (obj)).keySet().iterator();
/* 261*/                do
                        {
/* 261*/                    if(!type.hasNext())
/* 262*/                        break;
/* 262*/                    if(!interfaces.containsKey(type.next()))
/* 263*/                        changed = true;
                        } while(true);
                    }
/* 266*/            interfaces = ((Map) (obj));
/* 267*/            propogateState();
/* 269*/            return this;
                }
/* 272*/        if(((Map) (obj)).size() == 1)
/* 273*/            resolved = Type.get((CtClass)((Map) (obj)).values().iterator().next());
/* 274*/        else
/* 274*/        if(potentialClass != null)
/* 275*/            resolved = potentialClass;
/* 277*/        else
/* 277*/            resolved = OBJECT;
/* 280*/        propogateResolved();
/* 282*/        return resolved;
            }

            public boolean equals(Object obj)
            {
/* 286*/        if(!(obj instanceof MultiType))
/* 287*/            return false;
/* 289*/        obj = (MultiType)obj;
/* 290*/        if(resolved != null)
/* 291*/            return resolved.equals(((MultiType) (obj)).resolved);
/* 292*/        if(((MultiType) (obj)).resolved != null)
/* 293*/            return false;
/* 295*/        else
/* 295*/            return interfaces.keySet().equals(((MultiType) (obj)).interfaces.keySet());
            }

            public String toString()
            {
/* 299*/        if(resolved != null)
/* 300*/            return resolved.toString();
/* 302*/        StringBuffer stringbuffer = new StringBuffer("{");
/* 303*/        for(Iterator iterator = interfaces.keySet().iterator(); iterator.hasNext(); stringbuffer.append(", "))
/* 305*/            stringbuffer.append(iterator.next());

/* 308*/        stringbuffer.setLength(stringbuffer.length() - 2);
/* 309*/        if(potentialClass != null)
/* 310*/            stringbuffer.append(", *").append(potentialClass.toString());
/* 311*/        stringbuffer.append("}");
/* 312*/        return stringbuffer.toString();
            }

            private Map interfaces;
            private Type resolved;
            private Type potentialClass;
            private MultiType mergeSource;
            private boolean changed;
}
