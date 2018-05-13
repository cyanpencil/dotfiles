// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstPool.java

package javassist.bytecode;

import java.io.*;
import java.util.*;
import javassist.CtClass;

// Referenced classes of package javassist.bytecode:
//            ClassInfo, ConstInfo, ConstInfoPadding, Descriptor, 
//            DoubleInfo, FieldrefInfo, FloatInfo, IntegerInfo, 
//            InterfaceMethodrefInfo, InvokeDynamicInfo, LongInfo, LongVector, 
//            MemberrefInfo, MethodHandleInfo, MethodTypeInfo, MethodrefInfo, 
//            NameAndTypeInfo, StringInfo, Utf8Info

public final class ConstPool
{

            public ConstPool(String s)
            {
/* 158*/        items = new LongVector();
/* 159*/        itemsCache = null;
/* 160*/        numOfItems = 0;
/* 161*/        addItem0(null);
/* 162*/        thisClassInfo = addClassInfo(s);
            }

            public ConstPool(DataInputStream datainputstream)
                throws IOException
            {
/* 171*/        itemsCache = null;
/* 172*/        thisClassInfo = 0;
/* 175*/        read(datainputstream);
            }

            final void prune()
            {
/* 179*/        itemsCache = null;
            }

            public final int getSize()
            {
/* 186*/        return numOfItems;
            }

            public final String getClassName()
            {
/* 193*/        return getClassInfo(thisClassInfo);
            }

            public final int getThisClassInfo()
            {
/* 201*/        return thisClassInfo;
            }

            final void setThisClassInfo(int i)
            {
/* 205*/        thisClassInfo = i;
            }

            final ConstInfo getItem(int i)
            {
/* 209*/        return items.elementAt(i);
            }

            public final int getTag(int i)
            {
/* 217*/        return getItem(i).getTag();
            }

            public final String getClassInfo(int i)
            {
/* 232*/        if((i = (ClassInfo)getItem(i)) == null)
/* 234*/            return null;
/* 236*/        else
/* 236*/            return Descriptor.toJavaName(getUtf8Info(((ClassInfo) (i)).name));
            }

            public final String getClassInfoByDescriptor(int i)
            {
/* 249*/        if((i = (ClassInfo)getItem(i)) == null)
/* 251*/            return null;
/* 253*/        if((i = getUtf8Info(((ClassInfo) (i)).name)).charAt(0) == '[')
/* 255*/            return i;
/* 257*/        else
/* 257*/            return Descriptor.of(i);
            }

            public final int getNameAndTypeName(int i)
            {
/* 267*/        return ((NameAndTypeInfo) (i = (NameAndTypeInfo)getItem(i))).memberName;
            }

            public final int getNameAndTypeDescriptor(int i)
            {
/* 277*/        return ((NameAndTypeInfo) (i = (NameAndTypeInfo)getItem(i))).typeDescriptor;
            }

            public final int getMemberClass(int i)
            {
/* 291*/        return ((MemberrefInfo) (i = (MemberrefInfo)getItem(i))).classIndex;
            }

            public final int getMemberNameAndType(int i)
            {
/* 305*/        return ((MemberrefInfo) (i = (MemberrefInfo)getItem(i))).nameAndTypeIndex;
            }

            public final int getFieldrefClass(int i)
            {
/* 315*/        return ((FieldrefInfo) (i = (FieldrefInfo)getItem(i))).classIndex;
            }

            public final String getFieldrefClassName(int i)
            {
/* 327*/        if((i = (FieldrefInfo)getItem(i)) == null)
/* 329*/            return null;
/* 331*/        else
/* 331*/            return getClassInfo(((FieldrefInfo) (i)).classIndex);
            }

            public final int getFieldrefNameAndType(int i)
            {
/* 340*/        return ((FieldrefInfo) (i = (FieldrefInfo)getItem(i))).nameAndTypeIndex;
            }

            public final String getFieldrefName(int i)
            {
/* 353*/        if((i = (FieldrefInfo)getItem(i)) == null)
/* 355*/            return null;
/* 357*/        if((i = (NameAndTypeInfo)getItem(((FieldrefInfo) (i)).nameAndTypeIndex)) == null)
/* 359*/            return null;
/* 361*/        else
/* 361*/            return getUtf8Info(((NameAndTypeInfo) (i)).memberName);
            }

            public final String getFieldrefType(int i)
            {
/* 374*/        if((i = (FieldrefInfo)getItem(i)) == null)
/* 376*/            return null;
/* 378*/        if((i = (NameAndTypeInfo)getItem(((FieldrefInfo) (i)).nameAndTypeIndex)) == null)
/* 380*/            return null;
/* 382*/        else
/* 382*/            return getUtf8Info(((NameAndTypeInfo) (i)).typeDescriptor);
            }

            public final int getMethodrefClass(int i)
            {
/* 392*/        return ((MethodrefInfo) (i = (MethodrefInfo)getItem(i))).classIndex;
            }

            public final String getMethodrefClassName(int i)
            {
/* 404*/        if((i = (MethodrefInfo)getItem(i)) == null)
/* 406*/            return null;
/* 408*/        else
/* 408*/            return getClassInfo(((MethodrefInfo) (i)).classIndex);
            }

            public final int getMethodrefNameAndType(int i)
            {
/* 417*/        return ((MethodrefInfo) (i = (MethodrefInfo)getItem(i))).nameAndTypeIndex;
            }

            public final String getMethodrefName(int i)
            {
/* 430*/        if((i = (MethodrefInfo)getItem(i)) == null)
/* 432*/            return null;
/* 434*/        if((i = (NameAndTypeInfo)getItem(((MethodrefInfo) (i)).nameAndTypeIndex)) == null)
/* 437*/            return null;
/* 439*/        else
/* 439*/            return getUtf8Info(((NameAndTypeInfo) (i)).memberName);
            }

            public final String getMethodrefType(int i)
            {
/* 452*/        if((i = (MethodrefInfo)getItem(i)) == null)
/* 454*/            return null;
/* 456*/        if((i = (NameAndTypeInfo)getItem(((MethodrefInfo) (i)).nameAndTypeIndex)) == null)
/* 459*/            return null;
/* 461*/        else
/* 461*/            return getUtf8Info(((NameAndTypeInfo) (i)).typeDescriptor);
            }

            public final int getInterfaceMethodrefClass(int i)
            {
/* 471*/        return ((InterfaceMethodrefInfo) (i = (InterfaceMethodrefInfo)getItem(i))).classIndex;
            }

            public final String getInterfaceMethodrefClassName(int i)
            {
/* 484*/        i = (InterfaceMethodrefInfo)getItem(i);
/* 486*/        return getClassInfo(((InterfaceMethodrefInfo) (i)).classIndex);
            }

            public final int getInterfaceMethodrefNameAndType(int i)
            {
/* 495*/        return ((InterfaceMethodrefInfo) (i = (InterfaceMethodrefInfo)getItem(i))).nameAndTypeIndex;
            }

            public final String getInterfaceMethodrefName(int i)
            {
/* 510*/        if((i = (InterfaceMethodrefInfo)getItem(i)) == null)
/* 513*/            return null;
/* 515*/        if((i = (NameAndTypeInfo)getItem(((InterfaceMethodrefInfo) (i)).nameAndTypeIndex)) == null)
/* 518*/            return null;
/* 520*/        else
/* 520*/            return getUtf8Info(((NameAndTypeInfo) (i)).memberName);
            }

            public final String getInterfaceMethodrefType(int i)
            {
/* 534*/        if((i = (InterfaceMethodrefInfo)getItem(i)) == null)
/* 537*/            return null;
/* 539*/        if((i = (NameAndTypeInfo)getItem(((InterfaceMethodrefInfo) (i)).nameAndTypeIndex)) == null)
/* 542*/            return null;
/* 544*/        else
/* 544*/            return getUtf8Info(((NameAndTypeInfo) (i)).typeDescriptor);
            }

            public final Object getLdcValue(int i)
            {
                ConstInfo constinfo;
/* 557*/        if((constinfo = getItem(i)) instanceof StringInfo)
/* 560*/            i = getStringInfo(i);
/* 561*/        else
/* 561*/        if(constinfo instanceof FloatInfo)
/* 562*/            i = new Float(getFloatInfo(i));
/* 563*/        else
/* 563*/        if(constinfo instanceof IntegerInfo)
/* 564*/            i = new Integer(getIntegerInfo(i));
/* 565*/        else
/* 565*/        if(constinfo instanceof LongInfo)
/* 566*/            i = new Long(getLongInfo(i));
/* 567*/        else
/* 567*/        if(constinfo instanceof DoubleInfo)
/* 568*/            i = new Double(getDoubleInfo(i));
/* 570*/        else
/* 570*/            i = null;
/* 572*/        return i;
            }

            public final int getIntegerInfo(int i)
            {
/* 582*/        return ((IntegerInfo) (i = (IntegerInfo)getItem(i))).value;
            }

            public final float getFloatInfo(int i)
            {
/* 593*/        return ((FloatInfo) (i = (FloatInfo)getItem(i))).value;
            }

            public final long getLongInfo(int i)
            {
/* 604*/        return ((LongInfo) (i = (LongInfo)getItem(i))).value;
            }

            public final double getDoubleInfo(int i)
            {
/* 615*/        return ((DoubleInfo) (i = (DoubleInfo)getItem(i))).value;
            }

            public final String getStringInfo(int i)
            {
/* 626*/        i = (StringInfo)getItem(i);
/* 627*/        return getUtf8Info(((StringInfo) (i)).string);
            }

            public final String getUtf8Info(int i)
            {
/* 637*/        return ((Utf8Info) (i = (Utf8Info)getItem(i))).string;
            }

            public final int getMethodHandleKind(int i)
            {
/* 658*/        return ((MethodHandleInfo) (i = (MethodHandleInfo)getItem(i))).refKind;
            }

            public final int getMethodHandleIndex(int i)
            {
/* 670*/        return ((MethodHandleInfo) (i = (MethodHandleInfo)getItem(i))).refIndex;
            }

            public final int getMethodTypeInfo(int i)
            {
/* 682*/        return ((MethodTypeInfo) (i = (MethodTypeInfo)getItem(i))).descriptor;
            }

            public final int getInvokeDynamicBootstrap(int i)
            {
/* 694*/        return ((InvokeDynamicInfo) (i = (InvokeDynamicInfo)getItem(i))).bootstrap;
            }

            public final int getInvokeDynamicNameAndType(int i)
            {
/* 706*/        return ((InvokeDynamicInfo) (i = (InvokeDynamicInfo)getItem(i))).nameAndType;
            }

            public final String getInvokeDynamicType(int i)
            {
/* 720*/        if((i = (InvokeDynamicInfo)getItem(i)) == null)
/* 722*/            return null;
/* 724*/        if((i = (NameAndTypeInfo)getItem(((InvokeDynamicInfo) (i)).nameAndType)) == null)
/* 726*/            return null;
/* 728*/        else
/* 728*/            return getUtf8Info(((NameAndTypeInfo) (i)).typeDescriptor);
            }

            public final int isConstructor(String s, int i)
            {
/* 743*/        return isMember(s, "<init>", i);
            }

            public final int isMember(String s, String s1, int i)
            {
/* 763*/        i = (MemberrefInfo)getItem(i);
/* 764*/        if(getClassInfo(((MemberrefInfo) (i)).classIndex).equals(s))
                {
/* 765*/            s = (NameAndTypeInfo)getItem(((MemberrefInfo) (i)).nameAndTypeIndex);
/* 767*/            if(getUtf8Info(((NameAndTypeInfo) (s)).memberName).equals(s1))
/* 768*/                return ((NameAndTypeInfo) (s)).typeDescriptor;
                }
/* 771*/        return 0;
            }

            public final String eqMember(String s, String s1, int i)
            {
/* 792*/        i = (MemberrefInfo)getItem(i);
/* 793*/        NameAndTypeInfo nameandtypeinfo = (NameAndTypeInfo)getItem(((MemberrefInfo) (i)).nameAndTypeIndex);
/* 795*/        if(getUtf8Info(nameandtypeinfo.memberName).equals(s) && getUtf8Info(nameandtypeinfo.typeDescriptor).equals(s1))
/* 797*/            return getClassInfo(((MemberrefInfo) (i)).classIndex);
/* 799*/        else
/* 799*/            return null;
            }

            private int addItem0(ConstInfo constinfo)
            {
/* 803*/        items.addElement(constinfo);
/* 804*/        return numOfItems++;
            }

            private int addItem(ConstInfo constinfo)
            {
/* 808*/        if(itemsCache == null)
/* 809*/            itemsCache = makeItemsCache(items);
                ConstInfo constinfo1;
/* 811*/        if((constinfo1 = (ConstInfo)itemsCache.get(constinfo)) != null)
                {
/* 813*/            return constinfo1.index;
                } else
                {
/* 815*/            items.addElement(constinfo);
/* 816*/            itemsCache.put(constinfo, constinfo);
/* 817*/            return numOfItems++;
                }
            }

            public final int copy(int i, ConstPool constpool, Map map)
            {
/* 833*/        if(i == 0)
/* 834*/            return 0;
/* 836*/        else
/* 836*/            return (i = getItem(i)).copy(this, constpool, map);
            }

            final int addConstInfoPadding()
            {
/* 841*/        return addItem0(new ConstInfoPadding(numOfItems));
            }

            public final int addClassInfo(CtClass ctclass)
            {
/* 853*/        if(ctclass == THIS)
/* 854*/            return thisClassInfo;
/* 855*/        if(!ctclass.isArray())
/* 856*/            return addClassInfo(ctclass.getName());
/* 863*/        else
/* 863*/            return addClassInfo(Descriptor.toJvmName(ctclass));
            }

            public final int addClassInfo(String s)
            {
/* 878*/        s = addUtf8Info(Descriptor.toJvmName(s));
/* 879*/        return addItem(new ClassInfo(s, numOfItems));
            }

            public final int addNameAndTypeInfo(String s, String s1)
            {
/* 892*/        return addNameAndTypeInfo(addUtf8Info(s), addUtf8Info(s1));
            }

            public final int addNameAndTypeInfo(int i, int j)
            {
/* 903*/        return addItem(new NameAndTypeInfo(i, j, numOfItems));
            }

            public final int addFieldrefInfo(int i, String s, String s1)
            {
/* 920*/        s = addNameAndTypeInfo(s, s1);
/* 921*/        return addFieldrefInfo(i, s);
            }

            public final int addFieldrefInfo(int i, int j)
            {
/* 932*/        return addItem(new FieldrefInfo(i, j, numOfItems));
            }

            public final int addMethodrefInfo(int i, String s, String s1)
            {
/* 949*/        s = addNameAndTypeInfo(s, s1);
/* 950*/        return addMethodrefInfo(i, s);
            }

            public final int addMethodrefInfo(int i, int j)
            {
/* 961*/        return addItem(new MethodrefInfo(i, j, numOfItems));
            }

            public final int addInterfaceMethodrefInfo(int i, String s, String s1)
            {
/* 980*/        s = addNameAndTypeInfo(s, s1);
/* 981*/        return addInterfaceMethodrefInfo(i, s);
            }

            public final int addInterfaceMethodrefInfo(int i, int j)
            {
/* 994*/        return addItem(new InterfaceMethodrefInfo(i, j, numOfItems));
            }

            public final int addStringInfo(String s)
            {
/*1008*/        s = addUtf8Info(s);
/*1009*/        return addItem(new StringInfo(s, numOfItems));
            }

            public final int addIntegerInfo(int i)
            {
/*1019*/        return addItem(new IntegerInfo(i, numOfItems));
            }

            public final int addFloatInfo(float f)
            {
/*1029*/        return addItem(new FloatInfo(f, numOfItems));
            }

            public final int addLongInfo(long l)
            {
/*1039*/        if((l = addItem(new LongInfo(l, numOfItems))) == numOfItems - 1)
/*1041*/            addConstInfoPadding();
/*1043*/        return l;
            }

            public final int addDoubleInfo(double d)
            {
/*1053*/        if((d = addItem(new DoubleInfo(d, numOfItems))) == numOfItems - 1)
/*1055*/            addConstInfoPadding();
/*1057*/        return d;
            }

            public final int addUtf8Info(String s)
            {
/*1067*/        return addItem(new Utf8Info(s, numOfItems));
            }

            public final int addMethodHandleInfo(int i, int j)
            {
/*1082*/        return addItem(new MethodHandleInfo(i, j, numOfItems));
            }

            public final int addMethodTypeInfo(int i)
            {
/*1095*/        return addItem(new MethodTypeInfo(i, numOfItems));
            }

            public final int addInvokeDynamicInfo(int i, int j)
            {
/*1109*/        return addItem(new InvokeDynamicInfo(i, j, numOfItems));
            }

            public final Set getClassNames()
            {
/*1118*/        HashSet hashset = new HashSet();
/*1119*/        LongVector longvector = items;
/*1120*/        int i = numOfItems;
/*1121*/        for(int j = 1; j < i; j++)
                {
                    String s;
/*1122*/            if((s = longvector.elementAt(j).getClassName(this)) != null)
/*1124*/                hashset.add(s);
                }

/*1126*/        return hashset;
            }

            public final void renameClass(String s, String s1)
            {
/*1136*/        LongVector longvector = items;
/*1137*/        int i = numOfItems;
/*1138*/        for(int j = 1; j < i; j++)
                {
                    ConstInfo constinfo;
/*1139*/            (constinfo = longvector.elementAt(j)).renameClass(this, s, s1, itemsCache);
                }

            }

            public final void renameClass(Map map)
            {
/*1151*/        LongVector longvector = items;
/*1152*/        int i = numOfItems;
/*1153*/        for(int j = 1; j < i; j++)
                {
                    ConstInfo constinfo;
/*1154*/            (constinfo = longvector.elementAt(j)).renameClass(this, map, itemsCache);
                }

            }

            private void read(DataInputStream datainputstream)
                throws IOException
            {
/*1160*/        int i = datainputstream.readUnsignedShort();
/*1162*/        items = new LongVector(i);
/*1163*/        numOfItems = 0;
/*1164*/        addItem0(null);
/*1166*/        do
                {
/*1166*/            if(--i <= 0)
/*1167*/                break;
                    int j;
/*1167*/            if((j = readOne(datainputstream)) == 5 || j == 6)
                    {
/*1169*/                addConstInfoPadding();
/*1170*/                i--;
                    }
                } while(true);
            }

            private static HashMap makeItemsCache(LongVector longvector)
            {
/*1176*/        HashMap hashmap = new HashMap();
/*1177*/        int i = 1;
                ConstInfo constinfo;
/*1179*/        while((constinfo = longvector.elementAt(i++)) != null) 
/*1183*/            hashmap.put(constinfo, constinfo);
/*1186*/        return hashmap;
            }

            private int readOne(DataInputStream datainputstream)
                throws IOException
            {
                int i;
/*1191*/        switch(i = datainputstream.readUnsignedByte())
                {
/*1194*/        case 1: // '\001'
/*1194*/            datainputstream = new Utf8Info(datainputstream, numOfItems);
                    break;

/*1197*/        case 3: // '\003'
/*1197*/            datainputstream = new IntegerInfo(datainputstream, numOfItems);
                    break;

/*1200*/        case 4: // '\004'
/*1200*/            datainputstream = new FloatInfo(datainputstream, numOfItems);
                    break;

/*1203*/        case 5: // '\005'
/*1203*/            datainputstream = new LongInfo(datainputstream, numOfItems);
                    break;

/*1206*/        case 6: // '\006'
/*1206*/            datainputstream = new DoubleInfo(datainputstream, numOfItems);
                    break;

/*1209*/        case 7: // '\007'
/*1209*/            datainputstream = new ClassInfo(datainputstream, numOfItems);
                    break;

/*1212*/        case 8: // '\b'
/*1212*/            datainputstream = new StringInfo(datainputstream, numOfItems);
                    break;

/*1215*/        case 9: // '\t'
/*1215*/            datainputstream = new FieldrefInfo(datainputstream, numOfItems);
                    break;

/*1218*/        case 10: // '\n'
/*1218*/            datainputstream = new MethodrefInfo(datainputstream, numOfItems);
                    break;

/*1221*/        case 11: // '\013'
/*1221*/            datainputstream = new InterfaceMethodrefInfo(datainputstream, numOfItems);
                    break;

/*1224*/        case 12: // '\f'
/*1224*/            datainputstream = new NameAndTypeInfo(datainputstream, numOfItems);
                    break;

/*1227*/        case 15: // '\017'
/*1227*/            datainputstream = new MethodHandleInfo(datainputstream, numOfItems);
                    break;

/*1230*/        case 16: // '\020'
/*1230*/            datainputstream = new MethodTypeInfo(datainputstream, numOfItems);
                    break;

/*1233*/        case 18: // '\022'
/*1233*/            datainputstream = new InvokeDynamicInfo(datainputstream, numOfItems);
                    break;

/*1236*/        case 2: // '\002'
/*1236*/        case 13: // '\r'
/*1236*/        case 14: // '\016'
/*1236*/        case 17: // '\021'
/*1236*/        default:
/*1236*/            throw new IOException((new StringBuilder("invalid constant type: ")).append(i).append(" at ").append(numOfItems).toString());
                }
/*1239*/        addItem0(datainputstream);
/*1240*/        return i;
            }

            public final void write(DataOutputStream dataoutputstream)
                throws IOException
            {
/*1247*/        dataoutputstream.writeShort(numOfItems);
/*1248*/        LongVector longvector = items;
/*1249*/        int i = numOfItems;
/*1250*/        for(int j = 1; j < i; j++)
/*1251*/            longvector.elementAt(j).write(dataoutputstream);

            }

            public final void print()
            {
/*1258*/        print(new PrintWriter(System.out, true));
            }

            public final void print(PrintWriter printwriter)
            {
/*1265*/        int i = numOfItems;
/*1266*/        for(int j = 1; j < i; j++)
                {
/*1267*/            printwriter.print(j);
/*1268*/            printwriter.print(" ");
/*1269*/            items.elementAt(j).print(printwriter);
                }

            }

            LongVector items;
            int numOfItems;
            int thisClassInfo;
            HashMap itemsCache;
            public static final int CONST_Class = 7;
            public static final int CONST_Fieldref = 9;
            public static final int CONST_Methodref = 10;
            public static final int CONST_InterfaceMethodref = 11;
            public static final int CONST_String = 8;
            public static final int CONST_Integer = 3;
            public static final int CONST_Float = 4;
            public static final int CONST_Long = 5;
            public static final int CONST_Double = 6;
            public static final int CONST_NameAndType = 12;
            public static final int CONST_Utf8 = 1;
            public static final int CONST_MethodHandle = 15;
            public static final CtClass THIS = null;
            public static final int REF_getField = 1;
            public static final int REF_getStatic = 2;
            public static final int REF_putField = 3;
            public static final int REF_putStatic = 4;
            public static final int REF_invokeVirtual = 5;
            public static final int REF_invokeStatic = 6;
            public static final int REF_invokeSpecial = 7;
            public static final int REF_newInvokeSpecial = 8;
            public static final int REF_invokeInterface = 9;

}
