// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AttributeInfo.java

package javassist.bytecode;

import java.io.*;
import java.util.*;

// Referenced classes of package javassist.bytecode:
//            AnnotationDefaultAttribute, AnnotationsAttribute, BootstrapMethodsAttribute, CodeAttribute, 
//            ConstPool, ConstantAttribute, DeprecatedAttribute, EnclosingMethodAttribute, 
//            ExceptionsAttribute, InnerClassesAttribute, LineNumberAttribute, LocalVariableAttribute, 
//            LocalVariableTypeAttribute, ParameterAnnotationsAttribute, SignatureAttribute, SourceFileAttribute, 
//            StackMap, StackMapTable, SyntheticAttribute

public class AttributeInfo
{

            protected AttributeInfo(ConstPool constpool, int i, byte abyte0[])
            {
/*  40*/        constPool = constpool;
/*  41*/        name = i;
/*  42*/        info = abyte0;
            }

            protected AttributeInfo(ConstPool constpool, String s)
            {
/*  46*/        this(constpool, s, ((byte []) (null)));
            }

            public AttributeInfo(ConstPool constpool, String s, byte abyte0[])
            {
/*  58*/        this(constpool, constpool.addUtf8Info(s), abyte0);
            }

            protected AttributeInfo(ConstPool constpool, int i, DataInputStream datainputstream)
                throws IOException
            {
/*  64*/        constPool = constpool;
/*  65*/        name = i;
/*  66*/        constpool = datainputstream.readInt();
/*  67*/        info = new byte[constpool];
/*  68*/        if(constpool > 0)
/*  69*/            datainputstream.readFully(info);
            }

            static AttributeInfo read(ConstPool constpool, DataInputStream datainputstream)
                throws IOException
            {
/*  75*/        int i = datainputstream.readUnsignedShort();
                String s;
/*  76*/        if((s = constpool.getUtf8Info(i)).charAt(0) < 'L')
                {
/*  78*/            if(s.equals("AnnotationDefault"))
/*  79*/                return new AnnotationDefaultAttribute(constpool, i, datainputstream);
/*  80*/            if(s.equals("BootstrapMethods"))
/*  81*/                return new BootstrapMethodsAttribute(constpool, i, datainputstream);
/*  82*/            if(s.equals("Code"))
/*  83*/                return new CodeAttribute(constpool, i, datainputstream);
/*  84*/            if(s.equals("ConstantValue"))
/*  85*/                return new ConstantAttribute(constpool, i, datainputstream);
/*  86*/            if(s.equals("Deprecated"))
/*  87*/                return new DeprecatedAttribute(constpool, i, datainputstream);
/*  88*/            if(s.equals("EnclosingMethod"))
/*  89*/                return new EnclosingMethodAttribute(constpool, i, datainputstream);
/*  90*/            if(s.equals("Exceptions"))
/*  91*/                return new ExceptionsAttribute(constpool, i, datainputstream);
/*  92*/            if(s.equals("InnerClasses"))
/*  93*/                return new InnerClassesAttribute(constpool, i, datainputstream);
                } else
                {
/*  98*/            if(s.equals("LineNumberTable"))
/*  99*/                return new LineNumberAttribute(constpool, i, datainputstream);
/* 100*/            if(s.equals("LocalVariableTable"))
/* 101*/                return new LocalVariableAttribute(constpool, i, datainputstream);
/* 102*/            if(s.equals("LocalVariableTypeTable"))
/* 103*/                return new LocalVariableTypeAttribute(constpool, i, datainputstream);
/* 104*/            if(s.equals("RuntimeVisibleAnnotations") || s.equals("RuntimeInvisibleAnnotations"))
/* 107*/                return new AnnotationsAttribute(constpool, i, datainputstream);
/* 109*/            if(s.equals("RuntimeVisibleParameterAnnotations") || s.equals("RuntimeInvisibleParameterAnnotations"))
/* 111*/                return new ParameterAnnotationsAttribute(constpool, i, datainputstream);
/* 112*/            if(s.equals("Signature"))
/* 113*/                return new SignatureAttribute(constpool, i, datainputstream);
/* 114*/            if(s.equals("SourceFile"))
/* 115*/                return new SourceFileAttribute(constpool, i, datainputstream);
/* 116*/            if(s.equals("Synthetic"))
/* 117*/                return new SyntheticAttribute(constpool, i, datainputstream);
/* 118*/            if(s.equals("StackMap"))
/* 119*/                return new StackMap(constpool, i, datainputstream);
/* 120*/            if(s.equals("StackMapTable"))
/* 121*/                return new StackMapTable(constpool, i, datainputstream);
                }
/* 124*/        return new AttributeInfo(constpool, i, datainputstream);
            }

            public String getName()
            {
/* 131*/        return constPool.getUtf8Info(name);
            }

            public ConstPool getConstPool()
            {
/* 137*/        return constPool;
            }

            public int length()
            {
/* 145*/        return info.length + 6;
            }

            public byte[] get()
            {
/* 155*/        return info;
            }

            public void set(byte abyte0[])
            {
/* 164*/        info = abyte0;
            }

            public AttributeInfo copy(ConstPool constpool, Map map)
            {
/* 175*/        map = info.length;
/* 176*/        byte abyte0[] = info;
/* 177*/        byte abyte1[] = new byte[map];
/* 178*/        for(int i = 0; i < map; i++)
/* 179*/            abyte1[i] = abyte0[i];

/* 181*/        return new AttributeInfo(constpool, getName(), abyte1);
            }

            void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/* 185*/        dataoutputstream.writeShort(name);
/* 186*/        dataoutputstream.writeInt(info.length);
/* 187*/        if(info.length > 0)
/* 188*/            dataoutputstream.write(info);
            }

            static int getLength(ArrayList arraylist)
            {
/* 192*/        int i = 0;
/* 193*/        int j = arraylist.size();
/* 194*/        for(int k = 0; k < j; k++)
                {
/* 195*/            AttributeInfo attributeinfo = (AttributeInfo)arraylist.get(k);
/* 196*/            i += attributeinfo.length();
                }

/* 199*/        return i;
            }

            static AttributeInfo lookup(ArrayList arraylist, String s)
            {
/* 203*/        if(arraylist == null)
/* 204*/            return null;
                AttributeInfo attributeinfo;
/* 206*/        for(arraylist = arraylist.listIterator(); arraylist.hasNext();)
/* 208*/            if((attributeinfo = (AttributeInfo)arraylist.next()).getName().equals(s))
/* 210*/                return attributeinfo;

/* 213*/        return null;
            }

            static synchronized void remove(ArrayList arraylist, String s)
            {
/* 217*/        if(arraylist == null)
/* 218*/            return;
/* 220*/        arraylist = arraylist.listIterator();
/* 221*/        do
                {
/* 221*/            if(!arraylist.hasNext())
/* 222*/                break;
                    AttributeInfo attributeinfo;
/* 222*/            if((attributeinfo = (AttributeInfo)arraylist.next()).getName().equals(s))
/* 224*/                arraylist.remove();
                } while(true);
            }

            static void writeAll(ArrayList arraylist, DataOutputStream dataoutputstream)
                throws IOException
            {
/* 231*/        if(arraylist == null)
/* 232*/            return;
/* 234*/        int i = arraylist.size();
/* 235*/        for(int j = 0; j < i; j++)
                {
                    AttributeInfo attributeinfo;
/* 236*/            (attributeinfo = (AttributeInfo)arraylist.get(j)).write(dataoutputstream);
                }

            }

            static ArrayList copyAll(ArrayList arraylist, ConstPool constpool)
            {
/* 242*/        if(arraylist == null)
/* 243*/            return null;
/* 245*/        ArrayList arraylist1 = new ArrayList();
/* 246*/        int i = arraylist.size();
/* 247*/        for(int j = 0; j < i; j++)
                {
/* 248*/            AttributeInfo attributeinfo = (AttributeInfo)arraylist.get(j);
/* 249*/            arraylist1.add(attributeinfo.copy(constpool, null));
                }

/* 252*/        return arraylist1;
            }

            void renameClass(String s, String s1)
            {
            }

            void renameClass(Map map)
            {
            }

            static void renameClass(List list, String s, String s1)
            {
                AttributeInfo attributeinfo;
/* 265*/        for(list = list.iterator(); list.hasNext(); (attributeinfo = (AttributeInfo)list.next()).renameClass(s, s1));
            }

            static void renameClass(List list, Map map)
            {
                AttributeInfo attributeinfo;
/* 273*/        for(list = list.iterator(); list.hasNext(); (attributeinfo = (AttributeInfo)list.next()).renameClass(map));
            }

            void getRefClasses(Map map)
            {
            }

            static void getRefClasses(List list, Map map)
            {
                AttributeInfo attributeinfo;
/* 283*/        for(list = list.iterator(); list.hasNext(); (attributeinfo = (AttributeInfo)list.next()).getRefClasses(map));
            }

            protected ConstPool constPool;
            int name;
            byte info[];
}
