// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Serialization.java

package jersey.repackaged.com.google.common.collect;

import java.io.*;
import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Multimap, Multiset

final class Serialization
{

            static int readCount(ObjectInputStream objectinputstream)
                throws IOException
            {
/*  50*/        return objectinputstream.readInt();
            }

            static void writeMultimap(Multimap multimap, ObjectOutputStream objectoutputstream)
                throws IOException
            {
/* 153*/        objectoutputstream.writeInt(multimap.asMap().size());
/* 154*/        for(multimap = multimap.asMap().entrySet().iterator(); multimap.hasNext();)
                {
/* 154*/            Object obj = (java.util.Map.Entry)multimap.next();
/* 155*/            objectoutputstream.writeObject(((java.util.Map.Entry) (obj)).getKey());
/* 156*/            objectoutputstream.writeInt(((Collection)((java.util.Map.Entry) (obj)).getValue()).size());
/* 157*/            obj = ((Collection)((java.util.Map.Entry) (obj)).getValue()).iterator();
/* 157*/            while(((Iterator) (obj)).hasNext()) 
                    {
/* 157*/                Object obj1 = ((Iterator) (obj)).next();
/* 158*/                objectoutputstream.writeObject(obj1);
                    }
                }

            }

            static void populateMultimap(Multimap multimap, ObjectInputStream objectinputstream)
                throws IOException, ClassNotFoundException
            {
/* 170*/        int i = objectinputstream.readInt();
/* 171*/        populateMultimap(multimap, objectinputstream, i);
            }

            static void populateMultimap(Multimap multimap, ObjectInputStream objectinputstream, int i)
                throws IOException, ClassNotFoundException
            {
/* 182*/        for(int j = 0; j < i; j++)
                {
/* 184*/            Object obj = objectinputstream.readObject();
/* 185*/            obj = multimap.get(obj);
/* 186*/            int k = objectinputstream.readInt();
/* 187*/            for(int l = 0; l < k; l++)
                    {
/* 189*/                Object obj1 = objectinputstream.readObject();
/* 190*/                ((Collection) (obj)).add(obj1);
                    }

                }

            }
}
