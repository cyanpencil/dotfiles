// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Bytecode.java

package javassist.bytecode;

import javassist.CtClass;
import javassist.CtPrimitiveType;

// Referenced classes of package javassist.bytecode:
//            ByteVector, CodeAttribute, ConstPool, Descriptor, 
//            ExceptionTable, Opcode

public class Bytecode extends ByteVector
    implements Cloneable, Opcode
{

            public Bytecode(ConstPool constpool, int i, int j)
            {
/* 140*/        constPool = constpool;
/* 141*/        maxStack = i;
/* 142*/        maxLocals = j;
/* 143*/        tryblocks = new ExceptionTable(constpool);
/* 144*/        stackDepth = 0;
            }

            public Bytecode(ConstPool constpool)
            {
/* 157*/        this(constpool, 0, 0);
            }

            public Object clone()
            {
                Bytecode bytecode;
/* 167*/        (bytecode = (Bytecode)super.clone()).tryblocks = (ExceptionTable)tryblocks.clone();
/* 169*/        return bytecode;
                CloneNotSupportedException clonenotsupportedexception;
/* 171*/        clonenotsupportedexception;
/* 172*/        throw new RuntimeException(clonenotsupportedexception);
            }

            public ConstPool getConstPool()
            {
/* 179*/        return constPool;
            }

            public ExceptionTable getExceptionTable()
            {
/* 184*/        return tryblocks;
            }

            public CodeAttribute toCodeAttribute()
            {
/* 190*/        return new CodeAttribute(constPool, maxStack, maxLocals, get(), tryblocks);
            }

            public int length()
            {
/* 198*/        return getSize();
            }

            public byte[] get()
            {
/* 205*/        return copy();
            }

            public int getMaxStack()
            {
/* 211*/        return maxStack;
            }

            public void setMaxStack(int i)
            {
/* 228*/        maxStack = i;
            }

            public int getMaxLocals()
            {
/* 234*/        return maxLocals;
            }

            public void setMaxLocals(int i)
            {
/* 240*/        maxLocals = i;
            }

            public void setMaxLocals(boolean flag, CtClass actclass[], int i)
            {
/* 258*/        if(!flag)
/* 259*/            i++;
/* 261*/        if(actclass != null)
                {
/* 262*/            flag = CtClass.doubleType;
/* 263*/            CtClass ctclass = CtClass.longType;
/* 264*/            int j = actclass.length;
/* 265*/            for(int k = 0; k < j; k++)
                    {
                        CtClass ctclass1;
/* 266*/                if((ctclass1 = actclass[k]) == flag || ctclass1 == ctclass)
/* 268*/                    i += 2;
/* 270*/                else
/* 270*/                    i++;
                    }

                }
/* 274*/        maxLocals = i;
            }

            public void incMaxLocals(int i)
            {
/* 281*/        maxLocals += i;
            }

            public void addExceptionHandler(int i, int j, int k, CtClass ctclass)
            {
/* 289*/        addExceptionHandler(i, j, k, constPool.addClassInfo(ctclass));
            }

            public void addExceptionHandler(int i, int j, int k, String s)
            {
/* 300*/        addExceptionHandler(i, j, k, constPool.addClassInfo(s));
            }

            public void addExceptionHandler(int i, int j, int k, int l)
            {
/* 309*/        tryblocks.add(i, j, k, l);
            }

            public int currentPc()
            {
/* 317*/        return getSize();
            }

            public int read(int i)
            {
/* 327*/        return super.read(i);
            }

            public int read16bit(int i)
            {
/* 335*/        int j = read(i);
/* 336*/        i = read(i + 1);
/* 337*/        return (j << 8) + (i & 0xff);
            }

            public int read32bit(int i)
            {
/* 345*/        int j = read16bit(i);
/* 346*/        i = read16bit(i + 2);
/* 347*/        return (j << 16) + (i & 0xffff);
            }

            public void write(int i, int j)
            {
/* 357*/        super.write(i, j);
            }

            public void write16bit(int i, int j)
            {
/* 365*/        write(i, j >> 8);
/* 366*/        write(i + 1, j);
            }

            public void write32bit(int i, int j)
            {
/* 374*/        write16bit(i, j >> 16);
/* 375*/        write16bit(i + 2, j);
            }

            public void add(int i)
            {
/* 382*/        super.add(i);
            }

            public void add32bit(int i)
            {
/* 389*/        add(i >> 24, i >> 16, i >> 8, i);
            }

            public void addGap(int i)
            {
/* 398*/        super.addGap(i);
            }

            public void addOpcode(int i)
            {
/* 413*/        add(i);
/* 414*/        growStack(STACK_GROW[i]);
            }

            public void growStack(int i)
            {
/* 425*/        setStackDepth(stackDepth + i);
            }

            public int getStackDepth()
            {
/* 431*/        return stackDepth;
            }

            public void setStackDepth(int i)
            {
/* 441*/        stackDepth = i;
/* 442*/        if(stackDepth > maxStack)
/* 443*/            maxStack = stackDepth;
            }

            public void addIndex(int i)
            {
/* 451*/        add(i >> 8, i);
            }

            public void addAload(int i)
            {
/* 460*/        if(i < 4)
                {
/* 461*/            addOpcode(i + 42);
/* 461*/            return;
                }
/* 462*/        if(i < 256)
                {
/* 463*/            addOpcode(25);
/* 464*/            add(i);
/* 464*/            return;
                } else
                {
/* 467*/            addOpcode(196);
/* 468*/            addOpcode(25);
/* 469*/            addIndex(i);
/* 471*/            return;
                }
            }

            public void addAstore(int i)
            {
/* 479*/        if(i < 4)
                {
/* 480*/            addOpcode(i + 75);
/* 480*/            return;
                }
/* 481*/        if(i < 256)
                {
/* 482*/            addOpcode(58);
/* 483*/            add(i);
/* 483*/            return;
                } else
                {
/* 486*/            addOpcode(196);
/* 487*/            addOpcode(58);
/* 488*/            addIndex(i);
/* 490*/            return;
                }
            }

            public void addIconst(int i)
            {
/* 498*/        if(i < 6 && -2 < i)
                {
/* 499*/            addOpcode(i + 3);
/* 499*/            return;
                }
/* 500*/        if(i <= 127 && -128 <= i)
                {
/* 501*/            addOpcode(16);
/* 502*/            add(i);
/* 502*/            return;
                }
/* 504*/        if(i <= 32767 && -32768 <= i)
                {
/* 505*/            addOpcode(17);
/* 506*/            add(i >> 8);
/* 507*/            add(i);
/* 507*/            return;
                } else
                {
/* 510*/            addLdc(constPool.addIntegerInfo(i));
/* 511*/            return;
                }
            }

            public void addConstZero(CtClass ctclass)
            {
/* 520*/        if(ctclass.isPrimitive())
                {
/* 521*/            if(ctclass == CtClass.longType)
                    {
/* 522*/                addOpcode(9);
/* 522*/                return;
                    }
/* 523*/            if(ctclass == CtClass.floatType)
                    {
/* 524*/                addOpcode(11);
/* 524*/                return;
                    }
/* 525*/            if(ctclass == CtClass.doubleType)
                    {
/* 526*/                addOpcode(14);
/* 526*/                return;
                    }
/* 527*/            if(ctclass == CtClass.voidType)
                    {
/* 528*/                throw new RuntimeException("void type?");
                    } else
                    {
/* 530*/                addOpcode(3);
/* 530*/                return;
                    }
                } else
                {
/* 533*/            addOpcode(1);
/* 534*/            return;
                }
            }

            public void addIload(int i)
            {
/* 542*/        if(i < 4)
                {
/* 543*/            addOpcode(i + 26);
/* 543*/            return;
                }
/* 544*/        if(i < 256)
                {
/* 545*/            addOpcode(21);
/* 546*/            add(i);
/* 546*/            return;
                } else
                {
/* 549*/            addOpcode(196);
/* 550*/            addOpcode(21);
/* 551*/            addIndex(i);
/* 553*/            return;
                }
            }

            public void addIstore(int i)
            {
/* 561*/        if(i < 4)
                {
/* 562*/            addOpcode(i + 59);
/* 562*/            return;
                }
/* 563*/        if(i < 256)
                {
/* 564*/            addOpcode(54);
/* 565*/            add(i);
/* 565*/            return;
                } else
                {
/* 568*/            addOpcode(196);
/* 569*/            addOpcode(54);
/* 570*/            addIndex(i);
/* 572*/            return;
                }
            }

            public void addLconst(long l)
            {
/* 580*/        if(l == 0L || l == 1L)
                {
/* 581*/            addOpcode(9 + (int)l);
/* 581*/            return;
                } else
                {
/* 583*/            addLdc2w(l);
/* 584*/            return;
                }
            }

            public void addLload(int i)
            {
/* 592*/        if(i < 4)
                {
/* 593*/            addOpcode(i + 30);
/* 593*/            return;
                }
/* 594*/        if(i < 256)
                {
/* 595*/            addOpcode(22);
/* 596*/            add(i);
/* 596*/            return;
                } else
                {
/* 599*/            addOpcode(196);
/* 600*/            addOpcode(22);
/* 601*/            addIndex(i);
/* 603*/            return;
                }
            }

            public void addLstore(int i)
            {
/* 611*/        if(i < 4)
                {
/* 612*/            addOpcode(i + 63);
/* 612*/            return;
                }
/* 613*/        if(i < 256)
                {
/* 614*/            addOpcode(55);
/* 615*/            add(i);
/* 615*/            return;
                } else
                {
/* 618*/            addOpcode(196);
/* 619*/            addOpcode(55);
/* 620*/            addIndex(i);
/* 622*/            return;
                }
            }

            public void addDconst(double d)
            {
/* 630*/        if(d == 0.0D || d == 1.0D)
                {
/* 631*/            addOpcode(14 + (int)d);
/* 631*/            return;
                } else
                {
/* 633*/            addLdc2w(d);
/* 634*/            return;
                }
            }

            public void addDload(int i)
            {
/* 642*/        if(i < 4)
                {
/* 643*/            addOpcode(i + 38);
/* 643*/            return;
                }
/* 644*/        if(i < 256)
                {
/* 645*/            addOpcode(24);
/* 646*/            add(i);
/* 646*/            return;
                } else
                {
/* 649*/            addOpcode(196);
/* 650*/            addOpcode(24);
/* 651*/            addIndex(i);
/* 653*/            return;
                }
            }

            public void addDstore(int i)
            {
/* 661*/        if(i < 4)
                {
/* 662*/            addOpcode(i + 71);
/* 662*/            return;
                }
/* 663*/        if(i < 256)
                {
/* 664*/            addOpcode(57);
/* 665*/            add(i);
/* 665*/            return;
                } else
                {
/* 668*/            addOpcode(196);
/* 669*/            addOpcode(57);
/* 670*/            addIndex(i);
/* 672*/            return;
                }
            }

            public void addFconst(float f)
            {
/* 680*/        if(f == 0.0F || f == 1.0F || f == 2.0F)
                {
/* 681*/            addOpcode(11 + (int)f);
/* 681*/            return;
                } else
                {
/* 683*/            addLdc(constPool.addFloatInfo(f));
/* 684*/            return;
                }
            }

            public void addFload(int i)
            {
/* 692*/        if(i < 4)
                {
/* 693*/            addOpcode(i + 34);
/* 693*/            return;
                }
/* 694*/        if(i < 256)
                {
/* 695*/            addOpcode(23);
/* 696*/            add(i);
/* 696*/            return;
                } else
                {
/* 699*/            addOpcode(196);
/* 700*/            addOpcode(23);
/* 701*/            addIndex(i);
/* 703*/            return;
                }
            }

            public void addFstore(int i)
            {
/* 711*/        if(i < 4)
                {
/* 712*/            addOpcode(i + 67);
/* 712*/            return;
                }
/* 713*/        if(i < 256)
                {
/* 714*/            addOpcode(56);
/* 715*/            add(i);
/* 715*/            return;
                } else
                {
/* 718*/            addOpcode(196);
/* 719*/            addOpcode(56);
/* 720*/            addIndex(i);
/* 722*/            return;
                }
            }

            public int addLoad(int i, CtClass ctclass)
            {
/* 733*/        if(ctclass.isPrimitive())
                {
/* 734*/            if(ctclass == CtClass.booleanType || ctclass == CtClass.charType || ctclass == CtClass.byteType || ctclass == CtClass.shortType || ctclass == CtClass.intType)
                    {
/* 737*/                addIload(i);
                    } else
                    {
/* 738*/                if(ctclass == CtClass.longType)
                        {
/* 739*/                    addLload(i);
/* 740*/                    return 2;
                        }
/* 742*/                if(ctclass == CtClass.floatType)
/* 743*/                    addFload(i);
/* 744*/                else
/* 744*/                if(ctclass == CtClass.doubleType)
                        {
/* 745*/                    addDload(i);
/* 746*/                    return 2;
                        } else
                        {
/* 749*/                    throw new RuntimeException("void type?");
                        }
                    }
                } else
                {
/* 752*/            addAload(i);
                }
/* 754*/        return 1;
            }

            public int addStore(int i, CtClass ctclass)
            {
/* 766*/        if(ctclass.isPrimitive())
                {
/* 767*/            if(ctclass == CtClass.booleanType || ctclass == CtClass.charType || ctclass == CtClass.byteType || ctclass == CtClass.shortType || ctclass == CtClass.intType)
                    {
/* 770*/                addIstore(i);
                    } else
                    {
/* 771*/                if(ctclass == CtClass.longType)
                        {
/* 772*/                    addLstore(i);
/* 773*/                    return 2;
                        }
/* 775*/                if(ctclass == CtClass.floatType)
/* 776*/                    addFstore(i);
/* 777*/                else
/* 777*/                if(ctclass == CtClass.doubleType)
                        {
/* 778*/                    addDstore(i);
/* 779*/                    return 2;
                        } else
                        {
/* 782*/                    throw new RuntimeException("void type?");
                        }
                    }
                } else
                {
/* 785*/            addAstore(i);
                }
/* 787*/        return 1;
            }

            public int addLoadParameters(CtClass actclass[], int i)
            {
/* 798*/        int j = 0;
/* 799*/        if(actclass != null)
                {
/* 800*/            int k = actclass.length;
/* 801*/            for(int l = 0; l < k; l++)
/* 802*/                j += addLoad(j + i, actclass[l]);

                }
/* 805*/        return j;
            }

            public void addCheckcast(CtClass ctclass)
            {
/* 814*/        addOpcode(192);
/* 815*/        addIndex(constPool.addClassInfo(ctclass));
            }

            public void addCheckcast(String s)
            {
/* 824*/        addOpcode(192);
/* 825*/        addIndex(constPool.addClassInfo(s));
            }

            public void addInstanceof(String s)
            {
/* 834*/        addOpcode(193);
/* 835*/        addIndex(constPool.addClassInfo(s));
            }

            public void addGetfield(CtClass ctclass, String s, String s1)
            {
/* 848*/        add(180);
/* 849*/        ctclass = constPool.addClassInfo(ctclass);
/* 850*/        addIndex(constPool.addFieldrefInfo(ctclass, s, s1));
/* 851*/        growStack(Descriptor.dataSize(s1) - 1);
            }

            public void addGetfield(String s, String s1, String s2)
            {
/* 864*/        add(180);
/* 865*/        s = constPool.addClassInfo(s);
/* 866*/        addIndex(constPool.addFieldrefInfo(s, s1, s2));
/* 867*/        growStack(Descriptor.dataSize(s2) - 1);
            }

            public void addGetstatic(CtClass ctclass, String s, String s1)
            {
/* 880*/        add(178);
/* 881*/        ctclass = constPool.addClassInfo(ctclass);
/* 882*/        addIndex(constPool.addFieldrefInfo(ctclass, s, s1));
/* 883*/        growStack(Descriptor.dataSize(s1));
            }

            public void addGetstatic(String s, String s1, String s2)
            {
/* 896*/        add(178);
/* 897*/        s = constPool.addClassInfo(s);
/* 898*/        addIndex(constPool.addFieldrefInfo(s, s1, s2));
/* 899*/        growStack(Descriptor.dataSize(s2));
            }

            public void addInvokespecial(CtClass ctclass, String s, CtClass ctclass1, CtClass actclass[])
            {
/* 912*/        ctclass1 = Descriptor.ofMethod(ctclass1, actclass);
/* 913*/        addInvokespecial(ctclass, s, ((String) (ctclass1)));
            }

            public void addInvokespecial(CtClass ctclass, String s, String s1)
            {
/* 927*/        addInvokespecial(constPool.addClassInfo(ctclass), s, s1);
            }

            public void addInvokespecial(String s, String s1, String s2)
            {
/* 941*/        addInvokespecial(constPool.addClassInfo(s), s1, s2);
            }

            public void addInvokespecial(int i, String s, String s1)
            {
/* 956*/        add(183);
/* 957*/        addIndex(constPool.addMethodrefInfo(i, s, s1));
/* 958*/        growStack(Descriptor.dataSize(s1) - 1);
            }

            public void addInvokestatic(CtClass ctclass, String s, CtClass ctclass1, CtClass actclass[])
            {
/* 971*/        ctclass1 = Descriptor.ofMethod(ctclass1, actclass);
/* 972*/        addInvokestatic(ctclass, s, ((String) (ctclass1)));
            }

            public void addInvokestatic(CtClass ctclass, String s, String s1)
            {
/* 985*/        addInvokestatic(constPool.addClassInfo(ctclass), s, s1);
            }

            public void addInvokestatic(String s, String s1, String s2)
            {
/* 998*/        addInvokestatic(constPool.addClassInfo(s), s1, s2);
            }

            public void addInvokestatic(int i, String s, String s1)
            {
/*1012*/        add(184);
/*1013*/        addIndex(constPool.addMethodrefInfo(i, s, s1));
/*1014*/        growStack(Descriptor.dataSize(s1));
            }

            public void addInvokevirtual(CtClass ctclass, String s, CtClass ctclass1, CtClass actclass[])
            {
/*1031*/        ctclass1 = Descriptor.ofMethod(ctclass1, actclass);
/*1032*/        addInvokevirtual(ctclass, s, ((String) (ctclass1)));
            }

            public void addInvokevirtual(CtClass ctclass, String s, String s1)
            {
/*1049*/        addInvokevirtual(constPool.addClassInfo(ctclass), s, s1);
            }

            public void addInvokevirtual(String s, String s1, String s2)
            {
/*1066*/        addInvokevirtual(constPool.addClassInfo(s), s1, s2);
            }

            public void addInvokevirtual(int i, String s, String s1)
            {
/*1084*/        add(182);
/*1085*/        addIndex(constPool.addMethodrefInfo(i, s, s1));
/*1086*/        growStack(Descriptor.dataSize(s1) - 1);
            }

            public void addInvokeinterface(CtClass ctclass, String s, CtClass ctclass1, CtClass actclass[], int i)
            {
/*1101*/        ctclass1 = Descriptor.ofMethod(ctclass1, actclass);
/*1102*/        addInvokeinterface(ctclass, s, ((String) (ctclass1)), i);
            }

            public void addInvokeinterface(CtClass ctclass, String s, String s1, int i)
            {
/*1117*/        addInvokeinterface(constPool.addClassInfo(ctclass), s, s1, i);
            }

            public void addInvokeinterface(String s, String s1, String s2, int i)
            {
/*1133*/        addInvokeinterface(constPool.addClassInfo(s), s1, s2, i);
            }

            public void addInvokeinterface(int i, String s, String s1, int j)
            {
/*1150*/        add(185);
/*1151*/        addIndex(constPool.addInterfaceMethodrefInfo(i, s, s1));
/*1152*/        add(j);
/*1153*/        add(0);
/*1154*/        growStack(Descriptor.dataSize(s1) - 1);
            }

            public void addInvokedynamic(int i, String s, String s1)
            {
/*1168*/        s = constPool.addNameAndTypeInfo(s, s1);
/*1169*/        i = constPool.addInvokeDynamicInfo(i, s);
/*1170*/        add(186);
/*1171*/        addIndex(i);
/*1172*/        add(0, 0);
/*1173*/        growStack(Descriptor.dataSize(s1));
            }

            public void addLdc(String s)
            {
/*1183*/        addLdc(constPool.addStringInfo(s));
            }

            public void addLdc(int i)
            {
/*1192*/        if(i > 255)
                {
/*1193*/            addOpcode(19);
/*1194*/            addIndex(i);
/*1194*/            return;
                } else
                {
/*1197*/            addOpcode(18);
/*1198*/            add(i);
/*1200*/            return;
                }
            }

            public void addLdc2w(long l)
            {
/*1206*/        addOpcode(20);
/*1207*/        addIndex(constPool.addLongInfo(l));
            }

            public void addLdc2w(double d)
            {
/*1214*/        addOpcode(20);
/*1215*/        addIndex(constPool.addDoubleInfo(d));
            }

            public void addNew(CtClass ctclass)
            {
/*1224*/        addOpcode(187);
/*1225*/        addIndex(constPool.addClassInfo(ctclass));
            }

            public void addNew(String s)
            {
/*1234*/        addOpcode(187);
/*1235*/        addIndex(constPool.addClassInfo(s));
            }

            public void addAnewarray(String s)
            {
/*1244*/        addOpcode(189);
/*1245*/        addIndex(constPool.addClassInfo(s));
            }

            public void addAnewarray(CtClass ctclass, int i)
            {
/*1255*/        addIconst(i);
/*1256*/        addOpcode(189);
/*1257*/        addIndex(constPool.addClassInfo(ctclass));
            }

            public void addNewarray(int i, int j)
            {
/*1267*/        addIconst(j);
/*1268*/        addOpcode(188);
/*1269*/        add(i);
            }

            public int addMultiNewarray(CtClass ctclass, int ai[])
            {
/*1280*/        int i = ai.length;
/*1281*/        for(int j = 0; j < i; j++)
/*1282*/            addIconst(ai[j]);

/*1284*/        growStack(i);
/*1285*/        return addMultiNewarray(ctclass, i);
            }

            public int addMultiNewarray(CtClass ctclass, int i)
            {
/*1297*/        add(197);
/*1298*/        addIndex(constPool.addClassInfo(ctclass));
/*1299*/        add(i);
/*1300*/        growStack(1 - i);
/*1301*/        return i;
            }

            public int addMultiNewarray(String s, int i)
            {
/*1312*/        add(197);
/*1313*/        addIndex(constPool.addClassInfo(s));
/*1314*/        add(i);
/*1315*/        growStack(1 - i);
/*1316*/        return i;
            }

            public void addPutfield(CtClass ctclass, String s, String s1)
            {
/*1327*/        addPutfield0(ctclass, null, s, s1);
            }

            public void addPutfield(String s, String s1, String s2)
            {
/*1339*/        addPutfield0(null, s, s1, s2);
            }

            private void addPutfield0(CtClass ctclass, String s, String s1, String s2)
            {
/*1344*/        add(181);
/*1346*/        ctclass = s != null ? ((CtClass) (constPool.addClassInfo(s))) : ((CtClass) (constPool.addClassInfo(ctclass)));
/*1348*/        addIndex(constPool.addFieldrefInfo(ctclass, s1, s2));
/*1349*/        growStack(-1 - Descriptor.dataSize(s2));
            }

            public void addPutstatic(CtClass ctclass, String s, String s1)
            {
/*1360*/        addPutstatic0(ctclass, null, s, s1);
            }

            public void addPutstatic(String s, String s1, String s2)
            {
/*1372*/        addPutstatic0(null, s, s1, s2);
            }

            private void addPutstatic0(CtClass ctclass, String s, String s1, String s2)
            {
/*1377*/        add(179);
/*1379*/        ctclass = s != null ? ((CtClass) (constPool.addClassInfo(s))) : ((CtClass) (constPool.addClassInfo(ctclass)));
/*1381*/        addIndex(constPool.addFieldrefInfo(ctclass, s1, s2));
/*1382*/        growStack(-Descriptor.dataSize(s2));
            }

            public void addReturn(CtClass ctclass)
            {
/*1391*/        if(ctclass == null)
                {
/*1392*/            addOpcode(177);
/*1392*/            return;
                }
/*1393*/        if(ctclass.isPrimitive())
                {
/*1394*/            ctclass = (CtPrimitiveType)ctclass;
/*1395*/            addOpcode(ctclass.getReturnOp());
/*1396*/            return;
                } else
                {
/*1398*/            addOpcode(176);
/*1399*/            return;
                }
            }

            public void addRet(int i)
            {
/*1407*/        if(i < 256)
                {
/*1408*/            addOpcode(169);
/*1409*/            add(i);
/*1409*/            return;
                } else
                {
/*1412*/            addOpcode(196);
/*1413*/            addOpcode(169);
/*1414*/            addIndex(i);
/*1416*/            return;
                }
            }

            public void addPrintln(String s)
            {
/*1425*/        addGetstatic("java.lang.System", "err", "Ljava/io/PrintStream;");
/*1426*/        addLdc(s);
/*1427*/        addInvokevirtual("java.io.PrintStream", "println", "(Ljava/lang/String;)V");
            }

            public volatile void add(int i, int j, int k, int l)
            {
/* 114*/        super.add(i, j, k, l);
            }

            public volatile void add(int i, int j)
            {
/* 114*/        super.add(i, j);
            }

            public static final CtClass THIS;
            ConstPool constPool;
            int maxStack;
            int maxLocals;
            ExceptionTable tryblocks;
            private int stackDepth;

            static 
            {
/* 119*/        THIS = ConstPool.THIS;
            }
}
