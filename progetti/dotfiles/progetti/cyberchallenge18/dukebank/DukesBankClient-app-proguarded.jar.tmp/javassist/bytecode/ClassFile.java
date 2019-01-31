// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassFile.java

package javassist.bytecode;

import java.io.*;
import java.util.*;
import javassist.CannotCompileException;

// Referenced classes of package javassist.bytecode:
//            AttributeInfo, BadBytecode, ConstPool, Descriptor, 
//            DuplicateMemberException, FieldInfo, InnerClassesAttribute, MethodInfo, 
//            SourceFileAttribute

public final class ClassFile
{

            public ClassFile(DataInputStream datainputstream)
                throws IOException
            {
/* 118*/        read(datainputstream);
            }

            public ClassFile(boolean flag, String s, String s1)
            {
/* 132*/        major = MAJOR_VERSION;
/* 133*/        minor = 0;
/* 134*/        constPool = new ConstPool(s);
/* 135*/        thisClass = constPool.getThisClassInfo();
/* 136*/        if(flag)
/* 137*/            accessFlags = 1536;
/* 139*/        else
/* 139*/            accessFlags = 32;
/* 141*/        initSuperclass(s1);
/* 142*/        interfaces = null;
/* 143*/        fields = new ArrayList();
/* 144*/        methods = new ArrayList();
/* 145*/        thisclassname = s;
/* 147*/        attributes = new ArrayList();
/* 148*/        attributes.add(new SourceFileAttribute(constPool, getSourcefileName(thisclassname)));
            }

            private void initSuperclass(String s)
            {
/* 153*/        if(s != null)
                {
/* 154*/            superClass = constPool.addClassInfo(s);
/* 155*/            cachedSuperclass = s;
/* 155*/            return;
                } else
                {
/* 158*/            superClass = constPool.addClassInfo("java.lang.Object");
/* 159*/            cachedSuperclass = "java.lang.Object";
/* 161*/            return;
                }
            }

            private static String getSourcefileName(String s)
            {
                int i;
/* 164*/        if((i = s.lastIndexOf('.')) >= 0)
/* 166*/            s = s.substring(i + 1);
/* 168*/        return (new StringBuilder()).append(s).append(".java").toString();
            }

            public final void compact()
            {
/* 177*/        ConstPool constpool = compact0();
                ArrayList arraylist;
/* 178*/        int i = (arraylist = methods).size();
/* 180*/        for(int j = 0; j < i; j++)
                {
                    MethodInfo methodinfo;
/* 181*/            (methodinfo = (MethodInfo)arraylist.get(j)).compact(constpool);
                }

/* 185*/        i = (arraylist = fields).size();
/* 187*/        for(int k = 0; k < i; k++)
                {
                    FieldInfo fieldinfo;
/* 188*/            (fieldinfo = (FieldInfo)arraylist.get(k)).compact(constpool);
                }

/* 192*/        attributes = AttributeInfo.copyAll(attributes, constpool);
/* 193*/        constPool = constpool;
            }

            private ConstPool compact0()
            {
/* 197*/        ConstPool constpool = new ConstPool(thisclassname);
/* 198*/        thisClass = constpool.getThisClassInfo();
                String s;
/* 199*/        if((s = getSuperclass()) != null)
/* 201*/            superClass = constpool.addClassInfo(getSuperclass());
/* 203*/        if(interfaces != null)
                {
/* 204*/            int i = interfaces.length;
/* 205*/            for(int j = 0; j < i; j++)
/* 206*/                interfaces[j] = constpool.addClassInfo(constPool.getClassInfo(interfaces[j]));

                }
/* 210*/        return constpool;
            }

            public final void prune()
            {
/* 220*/        ConstPool constpool = compact0();
/* 221*/        ArrayList arraylist = new ArrayList();
                Object obj;
/* 222*/        if((obj = getAttribute("RuntimeInvisibleAnnotations")) != null)
                {
/* 225*/            obj = ((AttributeInfo) (obj)).copy(constpool, null);
/* 226*/            arraylist.add(obj);
                }
/* 229*/        if((obj = getAttribute("RuntimeVisibleAnnotations")) != null)
                {
/* 232*/            obj = ((AttributeInfo) (obj)).copy(constpool, null);
/* 233*/            arraylist.add(obj);
                }
/* 236*/        if((obj = getAttribute("Signature")) != null)
                {
/* 239*/            obj = ((AttributeInfo) (obj)).copy(constpool, null);
/* 240*/            arraylist.add(obj);
                }
/* 243*/        int i = ((ArrayList) (obj = methods)).size();
/* 245*/        for(int j = 0; j < i; j++)
                {
                    MethodInfo methodinfo;
/* 246*/            (methodinfo = (MethodInfo)((ArrayList) (obj)).get(j)).prune(constpool);
                }

/* 250*/        i = ((ArrayList) (obj = fields)).size();
/* 252*/        for(int k = 0; k < i; k++)
                {
                    FieldInfo fieldinfo;
/* 253*/            (fieldinfo = (FieldInfo)((ArrayList) (obj)).get(k)).prune(constpool);
                }

/* 257*/        attributes = arraylist;
/* 258*/        constPool = constpool;
            }

            public final ConstPool getConstPool()
            {
/* 265*/        return constPool;
            }

            public final boolean isInterface()
            {
/* 272*/        return (accessFlags & 0x200) != 0;
            }

            public final boolean isFinal()
            {
/* 279*/        return (accessFlags & 0x10) != 0;
            }

            public final boolean isAbstract()
            {
/* 286*/        return (accessFlags & 0x400) != 0;
            }

            public final int getAccessFlags()
            {
/* 295*/        return accessFlags;
            }

            public final void setAccessFlags(int i)
            {
/* 304*/        if((i & 0x200) == 0)
/* 305*/            i |= 0x20;
/* 307*/        accessFlags = i;
            }

            public final int getInnerAccessFlags()
            {
                InnerClassesAttribute innerclassesattribute;
/* 319*/        if((innerclassesattribute = (InnerClassesAttribute)getAttribute("InnerClasses")) == null)
/* 322*/            return -1;
/* 324*/        String s = getName();
/* 325*/        int i = innerclassesattribute.tableLength();
/* 326*/        for(int j = 0; j < i; j++)
/* 327*/            if(s.equals(innerclassesattribute.innerClass(j)))
/* 328*/                return innerclassesattribute.accessFlags(j);

/* 330*/        return -1;
            }

            public final String getName()
            {
/* 337*/        return thisclassname;
            }

            public final void setName(String s)
            {
/* 345*/        renameClass(thisclassname, s);
            }

            public final String getSuperclass()
            {
/* 352*/        if(cachedSuperclass == null)
/* 353*/            cachedSuperclass = constPool.getClassInfo(superClass);
/* 355*/        return cachedSuperclass;
            }

            public final int getSuperclassId()
            {
/* 363*/        return superClass;
            }

            public final void setSuperclass(String s)
                throws CannotCompileException
            {
/* 375*/        if(s == null)
/* 376*/            s = "java.lang.Object";
/* 379*/        try
                {
/* 379*/            superClass = constPool.addClassInfo(s);
                    ArrayList arraylist;
/* 380*/            int i = (arraylist = methods).size();
/* 382*/            for(int j = 0; j < i; j++)
                    {
                        MethodInfo methodinfo;
/* 383*/                (methodinfo = (MethodInfo)arraylist.get(j)).setSuperclass(s);
                    }

                }
/* 387*/        catch(BadBytecode badbytecode)
                {
/* 388*/            throw new CannotCompileException(badbytecode);
                }
/* 390*/        cachedSuperclass = s;
            }

            public final void renameClass(String s, String s1)
            {
/* 411*/        if(s.equals(s1))
/* 412*/            return;
/* 414*/        if(s.equals(thisclassname))
/* 415*/            thisclassname = s1;
/* 417*/        s = Descriptor.toJvmName(s);
/* 418*/        s1 = Descriptor.toJvmName(s1);
/* 419*/        constPool.renameClass(s, s1);
/* 421*/        AttributeInfo.renameClass(attributes, s, s1);
                ArrayList arraylist;
/* 422*/        int i = (arraylist = methods).size();
/* 424*/        for(int j = 0; j < i; j++)
                {
                    MethodInfo methodinfo;
/* 425*/            String s2 = (methodinfo = (MethodInfo)arraylist.get(j)).getDescriptor();
/* 427*/            methodinfo.setDescriptor(Descriptor.rename(s2, s, s1));
/* 428*/            AttributeInfo.renameClass(methodinfo.getAttributes(), s, s1);
                }

/* 431*/        i = (arraylist = fields).size();
/* 433*/        for(int k = 0; k < i; k++)
                {
                    FieldInfo fieldinfo;
/* 434*/            String s3 = (fieldinfo = (FieldInfo)arraylist.get(k)).getDescriptor();
/* 436*/            fieldinfo.setDescriptor(Descriptor.rename(s3, s, s1));
/* 437*/            AttributeInfo.renameClass(fieldinfo.getAttributes(), s, s1);
                }

            }

            public final void renameClass(Map map)
            {
                Object obj;
/* 451*/        if((obj = (String)map.get(Descriptor.toJvmName(thisclassname))) != null)
/* 454*/            thisclassname = Descriptor.toJavaName(((String) (obj)));
/* 456*/        constPool.renameClass(map);
/* 458*/        AttributeInfo.renameClass(attributes, map);
/* 459*/        int i = ((ArrayList) (obj = methods)).size();
/* 461*/        for(int j = 0; j < i; j++)
                {
                    MethodInfo methodinfo;
/* 462*/            String s = (methodinfo = (MethodInfo)((ArrayList) (obj)).get(j)).getDescriptor();
/* 464*/            methodinfo.setDescriptor(Descriptor.rename(s, map));
/* 465*/            AttributeInfo.renameClass(methodinfo.getAttributes(), map);
                }

/* 468*/        i = ((ArrayList) (obj = fields)).size();
/* 470*/        for(int k = 0; k < i; k++)
                {
                    FieldInfo fieldinfo;
/* 471*/            String s1 = (fieldinfo = (FieldInfo)((ArrayList) (obj)).get(k)).getDescriptor();
/* 473*/            fieldinfo.setDescriptor(Descriptor.rename(s1, map));
/* 474*/            AttributeInfo.renameClass(fieldinfo.getAttributes(), map);
                }

            }

            public final void getRefClasses(Map map)
            {
/* 483*/        constPool.renameClass(map);
/* 485*/        AttributeInfo.getRefClasses(attributes, map);
                ArrayList arraylist;
/* 486*/        int i = (arraylist = methods).size();
/* 488*/        for(int j = 0; j < i; j++)
                {
                    MethodInfo methodinfo;
                    String s;
/* 489*/            Descriptor.rename(s = (methodinfo = (MethodInfo)arraylist.get(j)).getDescriptor(), map);
/* 492*/            AttributeInfo.getRefClasses(methodinfo.getAttributes(), map);
                }

/* 495*/        i = (arraylist = fields).size();
/* 497*/        for(int k = 0; k < i; k++)
                {
                    FieldInfo fieldinfo;
                    String s1;
/* 498*/            Descriptor.rename(s1 = (fieldinfo = (FieldInfo)arraylist.get(k)).getDescriptor(), map);
/* 501*/            AttributeInfo.getRefClasses(fieldinfo.getAttributes(), map);
                }

            }

            public final String[] getInterfaces()
            {
/* 510*/        if(cachedInterfaces != null)
/* 511*/            return cachedInterfaces;
                String as[];
/* 514*/        if(interfaces == null)
                {
/* 515*/            as = new String[0];
                } else
                {
/* 517*/            String as1[] = new String[as = interfaces.length];
/* 519*/            for(int i = 0; i < as; i++)
/* 520*/                as1[i] = constPool.getClassInfo(interfaces[i]);

/* 522*/            as = as1;
                }
/* 525*/        cachedInterfaces = as;
/* 526*/        return as;
            }

            public final void setInterfaces(String as[])
            {
/* 536*/        cachedInterfaces = null;
/* 537*/        if(as != null)
                {
/* 538*/            int i = as.length;
/* 539*/            interfaces = new int[i];
/* 540*/            for(int j = 0; j < i; j++)
/* 541*/                interfaces[j] = constPool.addClassInfo(as[j]);

                }
            }

            public final void addInterface(String s)
            {
/* 549*/        cachedInterfaces = null;
/* 550*/        s = constPool.addClassInfo(s);
/* 551*/        if(interfaces == null)
                {
/* 552*/            interfaces = new int[1];
/* 553*/            interfaces[0] = s;
/* 553*/            return;
                } else
                {
                    int i;
/* 556*/            int ai[] = new int[(i = interfaces.length) + 1];
/* 558*/            System.arraycopy(interfaces, 0, ai, 0, i);
/* 559*/            ai[i] = s;
/* 560*/            interfaces = ai;
/* 562*/            return;
                }
            }

            public final List getFields()
            {
/* 571*/        return fields;
            }

            public final void addField(FieldInfo fieldinfo)
                throws DuplicateMemberException
            {
/* 580*/        testExistingField(fieldinfo.getName(), fieldinfo.getDescriptor());
/* 581*/        fields.add(fieldinfo);
            }

            public final void addField2(FieldInfo fieldinfo)
            {
/* 593*/        fields.add(fieldinfo);
            }

            private void testExistingField(String s, String s1)
                throws DuplicateMemberException
            {
                FieldInfo fieldinfo;
/* 598*/        for(s1 = fields.listIterator(0); s1.hasNext();)
/* 600*/            if((fieldinfo = (FieldInfo)s1.next()).getName().equals(s))
/* 602*/                throw new DuplicateMemberException((new StringBuilder("duplicate field: ")).append(s).toString());

            }

            public final List getMethods()
            {
/* 613*/        return methods;
            }

            public final MethodInfo getMethod(String s)
            {
                ArrayList arraylist;
/* 623*/        int i = (arraylist = methods).size();
/* 625*/        for(int j = 0; j < i; j++)
                {
                    MethodInfo methodinfo;
/* 626*/            if((methodinfo = (MethodInfo)arraylist.get(j)).getName().equals(s))
/* 628*/                return methodinfo;
                }

/* 631*/        return null;
            }

            public final MethodInfo getStaticInitializer()
            {
/* 639*/        return getMethod("<clinit>");
            }

            public final void addMethod(MethodInfo methodinfo)
                throws DuplicateMemberException
            {
/* 650*/        testExistingMethod(methodinfo);
/* 651*/        methods.add(methodinfo);
            }

            public final void addMethod2(MethodInfo methodinfo)
            {
/* 663*/        methods.add(methodinfo);
            }

            private void testExistingMethod(MethodInfo methodinfo)
                throws DuplicateMemberException
            {
/* 669*/        String s = methodinfo.getName();
/* 670*/        String s1 = methodinfo.getDescriptor();
/* 671*/        for(ListIterator listiterator = methods.listIterator(0); listiterator.hasNext();)
/* 673*/            if(isDuplicated(methodinfo, s, s1, (MethodInfo)listiterator.next(), listiterator))
/* 674*/                throw new DuplicateMemberException((new StringBuilder("duplicate method: ")).append(s).append(" in ").append(getName()).toString());

            }

            private static boolean isDuplicated(MethodInfo methodinfo, String s, String s1, MethodInfo methodinfo1, ListIterator listiterator)
            {
/* 682*/        if(!methodinfo1.getName().equals(s))
/* 683*/            return false;
/* 685*/        if(!Descriptor.eqParamTypes(methodinfo = methodinfo1.getDescriptor(), s1))
/* 687*/            return false;
/* 689*/        if(methodinfo.equals(s1))
                {
/* 690*/            if(notBridgeMethod(methodinfo1))
                    {
/* 691*/                return true;
                    } else
                    {
/* 695*/                listiterator.remove();
/* 696*/                return false;
                    }
                } else
                {
/* 700*/            return false;
                }
            }

            private static boolean notBridgeMethod(MethodInfo methodinfo)
            {
/* 707*/        return (methodinfo.getAccessFlags() & 0x40) == 0;
            }

            public final List getAttributes()
            {
/* 721*/        return attributes;
            }

            public final AttributeInfo getAttribute(String s)
            {
                ArrayList arraylist;
/* 733*/        int i = (arraylist = attributes).size();
/* 735*/        for(int j = 0; j < i; j++)
                {
                    AttributeInfo attributeinfo;
/* 736*/            if((attributeinfo = (AttributeInfo)arraylist.get(j)).getName().equals(s))
/* 738*/                return attributeinfo;
                }

/* 741*/        return null;
            }

            public final void addAttribute(AttributeInfo attributeinfo)
            {
/* 751*/        AttributeInfo.remove(attributes, attributeinfo.getName());
/* 752*/        attributes.add(attributeinfo);
            }

            public final String getSourceFile()
            {
                SourceFileAttribute sourcefileattribute;
/* 761*/        if((sourcefileattribute = (SourceFileAttribute)getAttribute("SourceFile")) == null)
/* 764*/            return null;
/* 766*/        else
/* 766*/            return sourcefileattribute.getFileName();
            }

            private void read(DataInputStream datainputstream)
                throws IOException
            {
                int i;
/* 771*/        if((i = datainputstream.readInt()) != 0xcafebabe)
/* 773*/            throw new IOException((new StringBuilder("bad magic number: ")).append(Integer.toHexString(i)).toString());
/* 775*/        minor = datainputstream.readUnsignedShort();
/* 776*/        major = datainputstream.readUnsignedShort();
/* 777*/        constPool = new ConstPool(datainputstream);
/* 778*/        accessFlags = datainputstream.readUnsignedShort();
/* 779*/        thisClass = datainputstream.readUnsignedShort();
/* 780*/        constPool.setThisClassInfo(thisClass);
/* 781*/        superClass = datainputstream.readUnsignedShort();
                int j1;
/* 782*/        if((j1 = datainputstream.readUnsignedShort()) == 0)
                {
/* 784*/            interfaces = null;
                } else
                {
/* 786*/            interfaces = new int[j1];
/* 787*/            for(int j = 0; j < j1; j++)
/* 788*/                interfaces[j] = datainputstream.readUnsignedShort();

                }
/* 791*/        ConstPool constpool = constPool;
/* 792*/        j1 = datainputstream.readUnsignedShort();
/* 793*/        fields = new ArrayList();
/* 794*/        for(int k = 0; k < j1; k++)
/* 795*/            addField2(new FieldInfo(constpool, datainputstream));

/* 797*/        j1 = datainputstream.readUnsignedShort();
/* 798*/        methods = new ArrayList();
/* 799*/        for(int l = 0; l < j1; l++)
/* 800*/            addMethod2(new MethodInfo(constpool, datainputstream));

/* 802*/        attributes = new ArrayList();
/* 803*/        j1 = datainputstream.readUnsignedShort();
/* 804*/        for(int i1 = 0; i1 < j1; i1++)
/* 805*/            addAttribute(AttributeInfo.read(constpool, datainputstream));

/* 807*/        thisclassname = constPool.getClassInfo(thisClass);
            }

            public final void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/* 816*/        dataoutputstream.writeInt(0xcafebabe);
/* 817*/        dataoutputstream.writeShort(minor);
/* 818*/        dataoutputstream.writeShort(major);
/* 819*/        constPool.write(dataoutputstream);
/* 820*/        dataoutputstream.writeShort(accessFlags);
/* 821*/        dataoutputstream.writeShort(thisClass);
/* 822*/        dataoutputstream.writeShort(superClass);
                int l;
/* 824*/        if(interfaces == null)
/* 825*/            l = 0;
/* 827*/        else
/* 827*/            l = interfaces.length;
/* 829*/        dataoutputstream.writeShort(l);
/* 830*/        for(int i = 0; i < l; i++)
/* 831*/            dataoutputstream.writeShort(interfaces[i]);

                ArrayList arraylist;
/* 833*/        l = (arraylist = fields).size();
/* 835*/        dataoutputstream.writeShort(l);
/* 836*/        for(int j = 0; j < l; j++)
                {
                    FieldInfo fieldinfo;
/* 837*/            (fieldinfo = (FieldInfo)arraylist.get(j)).write(dataoutputstream);
                }

/* 841*/        l = (arraylist = methods).size();
/* 843*/        dataoutputstream.writeShort(l);
/* 844*/        for(int k = 0; k < l; k++)
                {
                    MethodInfo methodinfo;
/* 845*/            (methodinfo = (MethodInfo)arraylist.get(k)).write(dataoutputstream);
                }

/* 849*/        dataoutputstream.writeShort(attributes.size());
/* 850*/        AttributeInfo.writeAll(attributes, dataoutputstream);
            }

            public final int getMajorVersion()
            {
/* 859*/        return major;
            }

            public final void setMajorVersion(int i)
            {
/* 869*/        major = i;
            }

            public final int getMinorVersion()
            {
/* 878*/        return minor;
            }

            public final void setMinorVersion(int i)
            {
/* 888*/        minor = i;
            }

            public final void setVersionToJava5()
            {
/* 899*/        major = 49;
/* 900*/        minor = 0;
            }

            int major;
            int minor;
            ConstPool constPool;
            int thisClass;
            int accessFlags;
            int superClass;
            int interfaces[];
            ArrayList fields;
            ArrayList methods;
            ArrayList attributes;
            String thisclassname;
            String cachedInterfaces[];
            String cachedSuperclass;
            public static final int JAVA_1 = 45;
            public static final int JAVA_2 = 46;
            public static final int JAVA_3 = 47;
            public static final int JAVA_4 = 48;
            public static final int JAVA_5 = 49;
            public static final int JAVA_6 = 50;
            public static final int JAVA_7 = 51;
            public static int MAJOR_VERSION = 47;

            static 
            {
/* 104*/        try
                {
/* 104*/            Class.forName("java.lang.StringBuilder");
/* 105*/            MAJOR_VERSION = 49;
/* 106*/            Class.forName("java.util.zip.DeflaterInputStream");
/* 107*/            MAJOR_VERSION = 50;
/* 108*/            Class.forName("java.lang.invoke.CallSite");
/* 109*/            MAJOR_VERSION = 51;
                }
/* 111*/        catch(Throwable _ex) { }
            }
}
