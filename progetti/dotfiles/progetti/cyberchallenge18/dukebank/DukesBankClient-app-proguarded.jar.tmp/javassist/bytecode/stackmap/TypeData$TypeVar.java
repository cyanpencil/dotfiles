// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypeData.java

package javassist.bytecode.stackmap;

import java.util.*;
import javassist.*;
import javassist.bytecode.*;

// Referenced classes of package javassist.bytecode.stackmap:
//            TypeData, TypeTag

public static class ype extends ar
{

            public String getName()
            {
/* 172*/        if(fixedType == null)
/* 173*/            return ((TypeData)lowers.get(0)).getName();
/* 175*/        else
/* 175*/            return fixedType;
            }

            public e isBasicType()
            {
/* 179*/        if(fixedType == null)
/* 180*/            return ((TypeData)lowers.get(0)).isBasicType();
/* 182*/        else
/* 182*/            return null;
            }

            public boolean is2WordType()
            {
/* 186*/        if(fixedType == null)
/* 187*/            return is2WordType;
/* 191*/        else
/* 191*/            return false;
            }

            public boolean isNullType()
            {
/* 195*/        if(fixedType == null)
/* 196*/            return ((TypeData)lowers.get(0)).isNullType();
/* 198*/        else
/* 198*/            return false;
            }

            public boolean isUninit()
            {
/* 202*/        if(fixedType == null)
/* 203*/            return ((TypeData)lowers.get(0)).isUninit();
/* 205*/        else
/* 205*/            return false;
            }

            public void merge(TypeData typedata)
            {
/* 209*/        lowers.add(typedata);
/* 210*/        if(typedata instanceof lowers)
/* 211*/            ((lowers)typedata).usedBy.add(this);
            }

            public int getTypeTag()
            {
/* 218*/        if(fixedType == null)
/* 219*/            return ((TypeData)lowers.get(0)).getTypeTag();
/* 221*/        else
/* 221*/            return super.getTypeTag();
            }

            public int getTypeData(ConstPool constpool)
            {
/* 225*/        if(fixedType == null)
/* 226*/            return ((TypeData)lowers.get(0)).getTypeData(constpool);
/* 228*/        else
/* 228*/            return super.getTypeData(constpool);
            }

            public void setType(String s, ClassPool classpool)
                throws BadBytecode
            {
/* 232*/        if(uppers == null)
/* 233*/            uppers = new ArrayList();
/* 235*/        uppers.add(s);
            }

            protected uppers toTypeVar()
            {
/* 238*/        return this;
            }

            public int dfs(ArrayList arraylist, int i, ClassPool classpool)
                throws NotFoundException
            {
/* 246*/        if(visited > 0)
/* 247*/            return i;
/* 249*/        visited = smallest = ++i;
/* 250*/        arraylist.add(this);
/* 251*/        inList = true;
/* 252*/        int j = lowers.size();
/* 253*/        for(int k = 0; k < j; k++)
                {
                    uppers uppers1;
/* 254*/            if((uppers1 = ((TypeData)lowers.get(k)).toTypeVar()) == null)
/* 256*/                continue;
/* 256*/            if(uppers1.visited == 0)
                    {
/* 257*/                i = uppers1.dfs(arraylist, i, classpool);
/* 258*/                if(uppers1.smallest < smallest)
/* 259*/                    smallest = uppers1.smallest;
/* 259*/                continue;
                    }
/* 261*/            if(uppers1.inList && uppers1.visited < smallest)
/* 263*/                smallest = uppers1.visited;
                }

/* 266*/        if(visited == smallest)
                {
/* 267*/            ArrayList arraylist1 = new ArrayList();
                    uppers uppers2;
/* 270*/            do
                    {
/* 270*/                (uppers2 = (smallest)arraylist.remove(arraylist.size() - 1)).inList = false;
/* 272*/                arraylist1.add(uppers2);
                    } while(uppers2 != this);
/* 274*/            fixTypes(arraylist1, classpool);
                }
/* 277*/        return i;
            }

            private void fixTypes(ArrayList arraylist, ClassPool classpool)
                throws NotFoundException
            {
/* 281*/        HashSet hashset = new HashSet();
/* 282*/        boolean flag = false;
/* 283*/        Object obj = null;
/* 284*/        int i = arraylist.size();
/* 285*/        for(int j = 0; j < i; j++)
                {
                    ArrayList arraylist1;
/* 286*/            int i1 = (arraylist1 = ((e)arraylist.get(j)).lowers).size();
/* 288*/            for(int j1 = 0; j1 < i1; j1++)
                    {
                        TypeData typedata;
/* 289*/                e e2 = (typedata = (TypeData)arraylist1.get(j1)).isBasicType();
/* 291*/                if(obj == null)
                        {
/* 292*/                    if(e2 == null)
                            {
/* 293*/                        flag = false;
/* 294*/                        obj = typedata;
/* 299*/                        if(typedata.isUninit())
/* 300*/                            break;
                            } else
                            {
/* 303*/                        flag = true;
/* 304*/                        obj = e2;
                            }
                        } else
/* 308*/                if(e2 == null && flag || e2 != null && obj != e2)
                        {
/* 310*/                    flag = true;
/* 311*/                    obj = TypeTag.TOP;
/* 312*/                    break;
                        }
/* 316*/                if(e2 == null && !typedata.isNullType())
/* 317*/                    hashset.add(typedata.getName());
                    }

                }

/* 321*/        if(flag)
                {
/* 322*/            for(int k = 0; k < i; k++)
                    {
                        e e;
/* 323*/                (e = (pe)arraylist.get(k)).lowers.clear();
/* 325*/                e.lowers.add(obj);
/* 326*/                is2WordType = ((TypeData) (obj)).is2WordType();
                    }

/* 322*/            return;
                }
/* 330*/        String s = fixTypes2(arraylist, hashset, classpool);
/* 331*/        for(int l = 0; l < i; l++)
                {
                    e e1;
/* 332*/            (e1 = (fixTypes2)arraylist.get(l)).fixedType = s;
                }

            }

            private String fixTypes2(ArrayList arraylist, HashSet hashset, ClassPool classpool)
                throws NotFoundException
            {
/* 339*/        Iterator iterator = hashset.iterator();
/* 340*/        if(hashset.size() == 0)
/* 341*/            return null;
/* 342*/        if(hashset.size() == 1)
/* 343*/            return (String)iterator.next();
/* 345*/        for(hashset = classpool.get((String)iterator.next()); iterator.hasNext(); hashset = commonSuperClassEx(hashset, classpool.get((String)iterator.next())));
/* 349*/        if(hashset.getSuperclass() == null || isObjectArray(hashset))
/* 350*/            hashset = fixByUppers(arraylist, classpool, new HashSet(), hashset);
/* 352*/        if(hashset.isArray())
/* 353*/            return Descriptor.toJvmName(hashset);
/* 355*/        else
/* 355*/            return hashset.getName();
            }

            private static boolean isObjectArray(CtClass ctclass)
                throws NotFoundException
            {
/* 360*/        return ctclass.isArray() && ctclass.getComponentType().getSuperclass() == null;
            }

            private CtClass fixByUppers(ArrayList arraylist, ClassPool classpool, HashSet hashset, CtClass ctclass)
                throws NotFoundException
            {
/* 366*/        if(arraylist == null)
/* 367*/            return ctclass;
/* 369*/        int i = arraylist.size();
/* 370*/        for(int j = 0; j < i; j++)
                {
/* 371*/            fixByUppers fixbyuppers = (fixByUppers)arraylist.get(j);
/* 372*/            if(!hashset.add(fixbyuppers))
/* 373*/                return ctclass;
/* 375*/            if(fixbyuppers.uppers != null)
                    {
/* 376*/                int k = fixbyuppers.uppers.size();
/* 377*/                for(int l = 0; l < k; l++)
                        {
                            CtClass ctclass1;
/* 378*/                    if((ctclass1 = classpool.get((String)fixbyuppers.uppers.get(l))).subtypeOf(ctclass))
/* 380*/                        ctclass = ctclass1;
                        }

                    }
/* 384*/            ctclass = fixByUppers(fixbyuppers.usedBy, classpool, hashset, ctclass);
                }

/* 387*/        return ctclass;
            }

            protected ArrayList lowers;
            protected ArrayList usedBy;
            protected ArrayList uppers;
            protected String fixedType;
            private boolean is2WordType;
            private int visited;
            private int smallest;
            private boolean inList;

            public e(TypeData typedata)
            {
/* 240*/        visited = 0;
/* 241*/        smallest = 0;
/* 242*/        inList = false;
/* 163*/        uppers = null;
/* 164*/        lowers = new ArrayList(2);
/* 165*/        usedBy = new ArrayList(2);
/* 166*/        merge(typedata);
/* 167*/        fixedType = null;
/* 168*/        is2WordType = typedata.is2WordType();
            }
}
