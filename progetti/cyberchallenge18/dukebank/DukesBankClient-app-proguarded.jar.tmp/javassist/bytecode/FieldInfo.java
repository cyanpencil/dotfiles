// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FieldInfo.java

package javassist.bytecode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package javassist.bytecode:
//            AttributeInfo, ConstPool, ConstantAttribute

public final class FieldInfo
{

            private FieldInfo(ConstPool constpool)
            {
/*  40*/        constPool = constpool;
/*  41*/        accessFlags = 0;
/*  42*/        attribute = null;
            }

            public FieldInfo(ConstPool constpool, String s, String s1)
            {
/*  55*/        this(constpool);
/*  56*/        name = constpool.addUtf8Info(s);
/*  57*/        cachedName = s;
/*  58*/        descriptor = constpool.addUtf8Info(s1);
            }

            FieldInfo(ConstPool constpool, DataInputStream datainputstream)
                throws IOException
            {
/*  62*/        this(constpool);
/*  63*/        read(datainputstream);
            }

            public final String toString()
            {
/*  70*/        return (new StringBuilder()).append(getName()).append(" ").append(getDescriptor()).toString();
            }

            final void compact(ConstPool constpool)
            {
/*  82*/        name = constpool.addUtf8Info(getName());
/*  83*/        descriptor = constpool.addUtf8Info(getDescriptor());
/*  84*/        attribute = AttributeInfo.copyAll(attribute, constpool);
/*  85*/        constPool = constpool;
            }

            final void prune(ConstPool constpool)
            {
/*  89*/        ArrayList arraylist = new ArrayList();
                AttributeInfo attributeinfo;
/*  90*/        if((attributeinfo = getAttribute("RuntimeInvisibleAnnotations")) != null)
                {
/*  93*/            attributeinfo = attributeinfo.copy(constpool, null);
/*  94*/            arraylist.add(attributeinfo);
                }
/*  97*/        if((attributeinfo = getAttribute("RuntimeVisibleAnnotations")) != null)
                {
/* 100*/            attributeinfo = attributeinfo.copy(constpool, null);
/* 101*/            arraylist.add(attributeinfo);
                }
/* 104*/        if((attributeinfo = getAttribute("Signature")) != null)
                {
/* 107*/            attributeinfo = attributeinfo.copy(constpool, null);
/* 108*/            arraylist.add(attributeinfo);
                }
                int i;
/* 111*/        if((i = getConstantValue()) != 0)
                {
/* 113*/            i = constPool.copy(i, constpool, null);
/* 114*/            arraylist.add(new ConstantAttribute(constpool, i));
                }
/* 117*/        attribute = arraylist;
/* 118*/        name = constpool.addUtf8Info(getName());
/* 119*/        descriptor = constpool.addUtf8Info(getDescriptor());
/* 120*/        constPool = constpool;
            }

            public final ConstPool getConstPool()
            {
/* 128*/        return constPool;
            }

            public final String getName()
            {
/* 135*/        if(cachedName == null)
/* 136*/            cachedName = constPool.getUtf8Info(name);
/* 138*/        return cachedName;
            }

            public final void setName(String s)
            {
/* 145*/        name = constPool.addUtf8Info(s);
/* 146*/        cachedName = s;
            }

            public final int getAccessFlags()
            {
/* 155*/        return accessFlags;
            }

            public final void setAccessFlags(int i)
            {
/* 164*/        accessFlags = i;
            }

            public final String getDescriptor()
            {
/* 173*/        return constPool.getUtf8Info(descriptor);
            }

            public final void setDescriptor(String s)
            {
/* 182*/        if(!s.equals(getDescriptor()))
/* 183*/            descriptor = constPool.addUtf8Info(s);
            }

            public final int getConstantValue()
            {
/* 193*/        if((accessFlags & 8) == 0)
/* 194*/            return 0;
                ConstantAttribute constantattribute;
/* 196*/        if((constantattribute = (ConstantAttribute)getAttribute("ConstantValue")) == null)
/* 199*/            return 0;
/* 201*/        else
/* 201*/            return constantattribute.getConstantValue();
            }

            public final List getAttributes()
            {
/* 215*/        if(attribute == null)
/* 216*/            attribute = new ArrayList();
/* 218*/        return attribute;
            }

            public final AttributeInfo getAttribute(String s)
            {
/* 229*/        return AttributeInfo.lookup(attribute, s);
            }

            public final void addAttribute(AttributeInfo attributeinfo)
            {
/* 239*/        if(attribute == null)
/* 240*/            attribute = new ArrayList();
/* 242*/        AttributeInfo.remove(attribute, attributeinfo.getName());
/* 243*/        attribute.add(attributeinfo);
            }

            private void read(DataInputStream datainputstream)
                throws IOException
            {
/* 247*/        accessFlags = datainputstream.readUnsignedShort();
/* 248*/        name = datainputstream.readUnsignedShort();
/* 249*/        descriptor = datainputstream.readUnsignedShort();
/* 250*/        int i = datainputstream.readUnsignedShort();
/* 251*/        attribute = new ArrayList();
/* 252*/        for(int j = 0; j < i; j++)
/* 253*/            attribute.add(AttributeInfo.read(constPool, datainputstream));

            }

            final void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/* 257*/        dataoutputstream.writeShort(accessFlags);
/* 258*/        dataoutputstream.writeShort(name);
/* 259*/        dataoutputstream.writeShort(descriptor);
/* 260*/        if(attribute == null)
                {
/* 261*/            dataoutputstream.writeShort(0);
/* 261*/            return;
                } else
                {
/* 263*/            dataoutputstream.writeShort(attribute.size());
/* 264*/            AttributeInfo.writeAll(attribute, dataoutputstream);
/* 266*/            return;
                }
            }

            ConstPool constPool;
            int accessFlags;
            int name;
            String cachedName;
            String cachedType;
            int descriptor;
            ArrayList attribute;
}
