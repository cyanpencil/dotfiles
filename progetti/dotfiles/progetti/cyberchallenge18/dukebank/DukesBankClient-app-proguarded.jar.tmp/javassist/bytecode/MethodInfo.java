// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MethodInfo.java

package javassist.bytecode;

import java.io.*;
import java.util.*;
import javassist.ClassPool;
import javassist.bytecode.stackmap.MapMaker;

// Referenced classes of package javassist.bytecode:
//            AnnotationDefaultAttribute, AttributeInfo, BadBytecode, ByteArray, 
//            ClassFile, CodeAttribute, CodeIterator, ConstPool, 
//            Descriptor, ExceptionsAttribute, LineNumberAttribute

public class MethodInfo
{

            private MethodInfo(ConstPool constpool)
            {
/*  66*/        constPool = constpool;
/*  67*/        attribute = null;
            }

            public MethodInfo(ConstPool constpool, String s, String s1)
            {
/*  83*/        this(constpool);
/*  84*/        accessFlags = 0;
/*  85*/        name = constpool.addUtf8Info(s);
/*  86*/        cachedName = s;
/*  87*/        descriptor = constPool.addUtf8Info(s1);
            }

            MethodInfo(ConstPool constpool, DataInputStream datainputstream)
                throws IOException
            {
/*  91*/        this(constpool);
/*  92*/        read(datainputstream);
            }

            public MethodInfo(ConstPool constpool, String s, MethodInfo methodinfo, Map map)
                throws BadBytecode
            {
/* 116*/        this(constpool);
/* 117*/        read(methodinfo, s, map);
            }

            public String toString()
            {
/* 124*/        return (new StringBuilder()).append(getName()).append(" ").append(getDescriptor()).toString();
            }

            void compact(ConstPool constpool)
            {
/* 136*/        name = constpool.addUtf8Info(getName());
/* 137*/        descriptor = constpool.addUtf8Info(getDescriptor());
/* 138*/        attribute = AttributeInfo.copyAll(attribute, constpool);
/* 139*/        constPool = constpool;
            }

            void prune(ConstPool constpool)
            {
/* 143*/        ArrayList arraylist = new ArrayList();
                Object obj;
/* 145*/        if((obj = getAttribute("RuntimeInvisibleAnnotations")) != null)
                {
/* 148*/            obj = ((AttributeInfo) (obj)).copy(constpool, null);
/* 149*/            arraylist.add(obj);
                }
/* 152*/        if((obj = getAttribute("RuntimeVisibleAnnotations")) != null)
                {
/* 155*/            obj = ((AttributeInfo) (obj)).copy(constpool, null);
/* 156*/            arraylist.add(obj);
                }
/* 159*/        if((obj = getAttribute("RuntimeInvisibleParameterAnnotations")) != null)
                {
/* 162*/            obj = ((AttributeInfo) (obj)).copy(constpool, null);
/* 163*/            arraylist.add(obj);
                }
/* 166*/        if((obj = getAttribute("RuntimeVisibleParameterAnnotations")) != null)
                {
/* 169*/            obj = ((AttributeInfo) (obj)).copy(constpool, null);
/* 170*/            arraylist.add(obj);
                }
/* 173*/        if((obj = (AnnotationDefaultAttribute)getAttribute("AnnotationDefault")) != null)
/* 176*/            arraylist.add(obj);
/* 178*/        if((obj = getExceptionsAttribute()) != null)
/* 180*/            arraylist.add(obj);
/* 182*/        if((obj = getAttribute("Signature")) != null)
                {
/* 185*/            obj = ((AttributeInfo) (obj)).copy(constpool, null);
/* 186*/            arraylist.add(obj);
                }
/* 189*/        attribute = arraylist;
/* 190*/        name = constpool.addUtf8Info(getName());
/* 191*/        descriptor = constpool.addUtf8Info(getDescriptor());
/* 192*/        constPool = constpool;
            }

            public String getName()
            {
/* 199*/        if(cachedName == null)
/* 200*/            cachedName = constPool.getUtf8Info(name);
/* 202*/        return cachedName;
            }

            public void setName(String s)
            {
/* 209*/        name = constPool.addUtf8Info(s);
/* 210*/        cachedName = s;
            }

            public boolean isMethod()
            {
                String s;
/* 218*/        return !(s = getName()).equals("<init>") && !s.equals("<clinit>");
            }

            public ConstPool getConstPool()
            {
/* 226*/        return constPool;
            }

            public boolean isConstructor()
            {
/* 233*/        return getName().equals("<init>");
            }

            public boolean isStaticInitializer()
            {
/* 240*/        return getName().equals("<clinit>");
            }

            public int getAccessFlags()
            {
/* 249*/        return accessFlags;
            }

            public void setAccessFlags(int i)
            {
/* 258*/        accessFlags = i;
            }

            public String getDescriptor()
            {
/* 267*/        return constPool.getUtf8Info(descriptor);
            }

            public void setDescriptor(String s)
            {
/* 276*/        if(!s.equals(getDescriptor()))
/* 277*/            descriptor = constPool.addUtf8Info(s);
            }

            public List getAttributes()
            {
/* 291*/        if(attribute == null)
/* 292*/            attribute = new ArrayList();
/* 294*/        return attribute;
            }

            public AttributeInfo getAttribute(String s)
            {
/* 306*/        return AttributeInfo.lookup(attribute, s);
            }

            public void addAttribute(AttributeInfo attributeinfo)
            {
/* 316*/        if(attribute == null)
/* 317*/            attribute = new ArrayList();
/* 319*/        AttributeInfo.remove(attribute, attributeinfo.getName());
/* 320*/        attribute.add(attributeinfo);
            }

            public ExceptionsAttribute getExceptionsAttribute()
            {
                AttributeInfo attributeinfo;
/* 329*/        return (ExceptionsAttribute)(attributeinfo = AttributeInfo.lookup(attribute, "Exceptions"));
            }

            public CodeAttribute getCodeAttribute()
            {
                AttributeInfo attributeinfo;
/* 340*/        return (CodeAttribute)(attributeinfo = AttributeInfo.lookup(attribute, "Code"));
            }

            public void removeExceptionsAttribute()
            {
/* 348*/        AttributeInfo.remove(attribute, "Exceptions");
            }

            public void setExceptionsAttribute(ExceptionsAttribute exceptionsattribute)
            {
/* 359*/        removeExceptionsAttribute();
/* 360*/        if(attribute == null)
/* 361*/            attribute = new ArrayList();
/* 363*/        attribute.add(exceptionsattribute);
            }

            public void removeCodeAttribute()
            {
/* 370*/        AttributeInfo.remove(attribute, "Code");
            }

            public void setCodeAttribute(CodeAttribute codeattribute)
            {
/* 381*/        removeCodeAttribute();
/* 382*/        if(attribute == null)
/* 383*/            attribute = new ArrayList();
/* 385*/        attribute.add(codeattribute);
            }

            public void rebuildStackMapIf6(ClassPool classpool, ClassFile classfile)
                throws BadBytecode
            {
/* 404*/        if(classfile.getMajorVersion() >= 50)
/* 405*/            rebuildStackMap(classpool);
/* 407*/        if(doPreverify)
/* 408*/            rebuildStackMapForME(classpool);
            }

            public void rebuildStackMap(ClassPool classpool)
                throws BadBytecode
            {
                CodeAttribute codeattribute;
/* 421*/        if((codeattribute = getCodeAttribute()) != null)
                {
/* 423*/            classpool = MapMaker.make(classpool, this);
/* 424*/            codeattribute.setAttribute(classpool);
                }
            }

            public void rebuildStackMapForME(ClassPool classpool)
                throws BadBytecode
            {
                CodeAttribute codeattribute;
/* 438*/        if((codeattribute = getCodeAttribute()) != null)
                {
/* 440*/            classpool = MapMaker.make2(classpool, this);
/* 441*/            codeattribute.setAttribute(classpool);
                }
            }

            public int getLineNumber(int i)
            {
                Object obj;
/* 455*/        if((obj = getCodeAttribute()) == null)
/* 457*/            return -1;
/* 459*/        if((obj = (LineNumberAttribute)((CodeAttribute) (obj)).getAttribute("LineNumberTable")) == null)
/* 462*/            return -1;
/* 464*/        else
/* 464*/            return ((LineNumberAttribute) (obj)).toLineNumber(i);
            }

            public void setSuperclass(String s)
                throws BadBytecode
            {
/* 489*/        if(!isConstructor())
/* 490*/            return;
                Object obj;
/* 492*/        byte abyte0[] = ((CodeAttribute) (obj = getCodeAttribute())).getCode();
                int i;
/* 494*/        if((i = ((CodeIterator) (obj = ((CodeAttribute) (obj)).iterator())).skipSuperConstructor()) >= 0)
                {
/* 497*/            ConstPool constpool = constPool;
/* 498*/            int j = ByteArray.readU16bit(abyte0, i + 1);
/* 499*/            j = constpool.getMethodrefNameAndType(j);
/* 500*/            s = constpool.addClassInfo(s);
/* 501*/            ByteArray.write16bit(s = constpool.addMethodrefInfo(s, j), abyte0, i + 1);
                }
            }

            private void read(MethodInfo methodinfo, String s, Map map)
                throws BadBytecode
            {
/* 508*/        ConstPool constpool = constPool;
/* 509*/        accessFlags = methodinfo.accessFlags;
/* 510*/        name = constpool.addUtf8Info(s);
/* 511*/        cachedName = s;
/* 512*/        s = Descriptor.rename(s = (s = methodinfo.constPool).getUtf8Info(methodinfo.descriptor), map);
/* 515*/        descriptor = constpool.addUtf8Info(s);
/* 517*/        attribute = new ArrayList();
/* 518*/        if((s = methodinfo.getExceptionsAttribute()) != null)
/* 520*/            attribute.add(s.copy(constpool, map));
/* 522*/        if((methodinfo = methodinfo.getCodeAttribute()) != null)
/* 524*/            attribute.add(methodinfo.copy(constpool, map));
            }

            private void read(DataInputStream datainputstream)
                throws IOException
            {
/* 528*/        accessFlags = datainputstream.readUnsignedShort();
/* 529*/        name = datainputstream.readUnsignedShort();
/* 530*/        descriptor = datainputstream.readUnsignedShort();
/* 531*/        int i = datainputstream.readUnsignedShort();
/* 532*/        attribute = new ArrayList();
/* 533*/        for(int j = 0; j < i; j++)
/* 534*/            attribute.add(AttributeInfo.read(constPool, datainputstream));

            }

            void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/* 538*/        dataoutputstream.writeShort(accessFlags);
/* 539*/        dataoutputstream.writeShort(name);
/* 540*/        dataoutputstream.writeShort(descriptor);
/* 542*/        if(attribute == null)
                {
/* 543*/            dataoutputstream.writeShort(0);
/* 543*/            return;
                } else
                {
/* 545*/            dataoutputstream.writeShort(attribute.size());
/* 546*/            AttributeInfo.writeAll(attribute, dataoutputstream);
/* 548*/            return;
                }
            }

            ConstPool constPool;
            int accessFlags;
            int name;
            String cachedName;
            int descriptor;
            ArrayList attribute;
            public static boolean doPreverify = false;
            public static final String nameInit = "<init>";
            public static final String nameClinit = "<clinit>";

}
