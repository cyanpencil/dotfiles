// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMaker.java

package javassist.bytecode.stackmap;

import java.util.ArrayList;
import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.bytecode.*;

// Referenced classes of package javassist.bytecode.stackmap:
//            Tracer, BasicBlock, TypeData, TypedBlock

public class MapMaker extends Tracer
{

            public static StackMapTable make(ClassPool classpool, MethodInfo methodinfo)
                throws BadBytecode
            {
                CodeAttribute codeattribute;
/*  91*/        if((codeattribute = methodinfo.getCodeAttribute()) == null)
/*  93*/            return null;
                TypedBlock atypedblock[];
/*  97*/        try
                {
/*  97*/            atypedblock = TypedBlock.makeBlocks(methodinfo, codeattribute, true);
                }
/*  99*/        catch(BasicBlock.JsrBytecode _ex)
                {
/* 100*/            return null;
                }
/* 103*/        if(atypedblock == null)
/* 104*/            return null;
/* 106*/        classpool = new MapMaker(classpool, methodinfo, codeattribute);
/* 108*/        try
                {
/* 108*/            classpool.make(atypedblock, codeattribute.getCode());
                }
                // Misplaced declaration of an exception variable
/* 110*/        catch(ClassPool classpool)
                {
/* 111*/            throw new BadBytecode(methodinfo, classpool);
                }
/* 114*/        return classpool.toStackMap(atypedblock);
            }

            public static StackMap make2(ClassPool classpool, MethodInfo methodinfo)
                throws BadBytecode
            {
                CodeAttribute codeattribute;
/* 125*/        if((codeattribute = methodinfo.getCodeAttribute()) == null)
/* 127*/            return null;
                TypedBlock atypedblock[];
/* 131*/        try
                {
/* 131*/            atypedblock = TypedBlock.makeBlocks(methodinfo, codeattribute, true);
                }
/* 133*/        catch(BasicBlock.JsrBytecode _ex)
                {
/* 134*/            return null;
                }
/* 137*/        if(atypedblock == null)
/* 138*/            return null;
/* 140*/        classpool = new MapMaker(classpool, methodinfo, codeattribute);
/* 142*/        try
                {
/* 142*/            classpool.make(atypedblock, codeattribute.getCode());
                }
                // Misplaced declaration of an exception variable
/* 144*/        catch(ClassPool classpool)
                {
/* 145*/            throw new BadBytecode(methodinfo, classpool);
                }
/* 147*/        return classpool.toStackMap2(methodinfo.getConstPool(), atypedblock);
            }

            public MapMaker(ClassPool classpool, MethodInfo methodinfo, CodeAttribute codeattribute)
            {
/* 151*/        super(classpool, methodinfo.getConstPool(), codeattribute.getMaxStack(), codeattribute.getMaxLocals(), TypedBlock.getRetType(methodinfo.getDescriptor()));
            }

            protected MapMaker(MapMaker mapmaker)
            {
/* 156*/        super(mapmaker);
            }

            void make(TypedBlock atypedblock[], byte abyte0[])
                throws BadBytecode
            {
/* 164*/        make(abyte0, atypedblock[0]);
/* 165*/        findDeadCatchers(abyte0, atypedblock);
/* 167*/        try
                {
/* 167*/            fixTypes(abyte0, atypedblock);
/* 170*/            return;
                }
                // Misplaced declaration of an exception variable
/* 168*/        catch(TypedBlock atypedblock[])
                {
/* 169*/            throw new BadBytecode("failed to resolve types", atypedblock);
                }
            }

            private void make(byte abyte0[], TypedBlock typedblock)
                throws BadBytecode
            {
/* 178*/        copyTypeData(typedblock.stackTop, typedblock.stackTypes, stackTypes);
/* 179*/        stackTop = typedblock.stackTop;
/* 180*/        copyTypeData(typedblock.localsTypes.length, typedblock.localsTypes, localsTypes);
/* 182*/        traceException(abyte0, typedblock.toCatch);
                int i;
/* 184*/        for(int k = (i = typedblock.position) + typedblock.length; i < k; i += doOpcode(i, abyte0));
/* 189*/        traceException(abyte0, typedblock.toCatch);
/* 191*/        if(typedblock.exit != null)
                {
/* 192*/            for(int j = 0; j < typedblock.exit.length; j++)
                    {
                        TypedBlock typedblock1;
/* 193*/                if((typedblock1 = (TypedBlock)typedblock.exit[j]).alreadySet())
                        {
/* 195*/                    mergeMap(typedblock1, true);
                        } else
                        {
/* 197*/                    recordStackMap(typedblock1);
                            MapMaker mapmaker;
/* 198*/                    (mapmaker = new MapMaker(this)).make(abyte0, typedblock1);
                        }
                    }

                }
            }

            private void traceException(byte abyte0[], BasicBlock.Catch catch1)
                throws BadBytecode
            {
/* 208*/        for(; catch1 != null; catch1 = catch1.next)
                {
                    TypedBlock typedblock;
/* 209*/            if((typedblock = (TypedBlock)catch1.body).alreadySet())
                    {
/* 211*/                mergeMap(typedblock, false);
/* 212*/                if(typedblock.stackTop <= 0)
/* 213*/                    throw new BadBytecode((new StringBuilder("bad catch clause: ")).append(catch1.typeIndex).toString());
/* 215*/                typedblock.stackTypes[0] = merge(toExceptionType(catch1.typeIndex), typedblock.stackTypes[0]);
                    } else
                    {
/* 219*/                recordStackMap(typedblock, catch1.typeIndex);
                        MapMaker mapmaker;
/* 220*/                (mapmaker = new MapMaker(this)).make(abyte0, typedblock);
                    }
                }

            }

            private void mergeMap(TypedBlock typedblock, boolean flag)
                throws BadBytecode
            {
/* 229*/        int i = localsTypes.length;
/* 230*/        for(int k = 0; k < i; k++)
/* 231*/            typedblock.localsTypes[k] = merge(validateTypeData(localsTypes, i, k), typedblock.localsTypes[k]);

/* 234*/        if(flag)
                {
/* 235*/            int j = stackTop;
/* 236*/            for(int l = 0; l < j; l++)
/* 237*/                typedblock.stackTypes[l] = merge(stackTypes[l], typedblock.stackTypes[l]);

                }
            }

            private TypeData merge(TypeData typedata, TypeData typedata1)
                throws BadBytecode
            {
/* 242*/        if(typedata == typedata1)
/* 243*/            return typedata1;
/* 244*/        if((typedata1 instanceof TypeData.ClassName) || (typedata1 instanceof TypeData.BasicType))
/* 246*/            return typedata1;
/* 247*/        if(typedata1 instanceof TypeData.AbsTypeVar)
                {
/* 248*/            ((TypeData.AbsTypeVar)typedata1).merge(typedata);
/* 249*/            return typedata1;
                } else
                {
/* 252*/            throw new RuntimeException("fatal: this should never happen");
                }
            }

            private void recordStackMap(TypedBlock typedblock)
                throws BadBytecode
            {
/* 258*/        TypeData atypedata[] = TypeData.make(stackTypes.length);
                int i;
/* 259*/        recordTypeData(i = stackTop, stackTypes, atypedata);
/* 261*/        recordStackMap0(typedblock, i, atypedata);
            }

            private void recordStackMap(TypedBlock typedblock, int i)
                throws BadBytecode
            {
                TypeData atypedata[];
/* 267*/        (atypedata = TypeData.make(stackTypes.length))[0] = toExceptionType(i).join();
/* 269*/        recordStackMap0(typedblock, 1, atypedata);
            }

            private TypeData.ClassName toExceptionType(int i)
            {
/* 274*/        if(i == 0)
/* 275*/            i = "java.lang.Throwable";
/* 277*/        else
/* 277*/            i = cpool.getClassInfo(i);
/* 279*/        return new TypeData.ClassName(i);
            }

            private void recordStackMap0(TypedBlock typedblock, int i, TypeData atypedata[])
                throws BadBytecode
            {
                int j;
/* 285*/        TypeData atypedata1[] = TypeData.make(j = localsTypes.length);
/* 287*/        j = recordTypeData(j, localsTypes, atypedata1);
/* 288*/        typedblock.setStackMap(i, atypedata, j, atypedata1);
            }

            protected static int recordTypeData(int i, TypeData atypedata[], TypeData atypedata1[])
            {
/* 292*/        int j = -1;
/* 293*/        for(int k = 0; k < i; k++)
                {
/* 294*/            TypeData typedata = validateTypeData(atypedata, i, k);
/* 295*/            atypedata1[k] = typedata.join();
/* 296*/            if(typedata != TOP)
/* 297*/                j = k + 1;
                }

/* 300*/        return j + 1;
            }

            protected static void copyTypeData(int i, TypeData atypedata[], TypeData atypedata1[])
            {
/* 304*/        for(int j = 0; j < i; j++)
/* 305*/            atypedata1[j] = atypedata[j];

            }

            private static TypeData validateTypeData(TypeData atypedata[], int i, int j)
            {
                TypeData typedata;
/* 309*/        if((typedata = atypedata[j]).is2WordType() && j + 1 < i && atypedata[j + 1] != TOP)
/* 312*/            return TOP;
/* 314*/        else
/* 314*/            return typedata;
            }

            private void findDeadCatchers(byte abyte0[], TypedBlock atypedblock[])
                throws BadBytecode
            {
/* 326*/        int i = atypedblock.length;
/* 327*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/* 328*/            if(((TypedBlock) (obj = atypedblock[j])).alreadySet())
/* 330*/                continue;
/* 330*/            fixDeadcode(abyte0, ((TypedBlock) (obj)));
                    TypedBlock typedblock;
/* 331*/            if((obj = ((TypedBlock) (obj)).toCatch) != null && !(typedblock = (TypedBlock)((BasicBlock.Catch) (obj)).body).alreadySet())
                    {
/* 337*/                recordStackMap(typedblock, ((BasicBlock.Catch) (obj)).typeIndex);
/* 338*/                fixDeadcode(abyte0, typedblock);
/* 339*/                typedblock.incoming = 1;
                    }
                }

            }

            private void fixDeadcode(byte abyte0[], TypedBlock typedblock)
                throws BadBytecode
            {
/* 348*/        int i = typedblock.position;
                int j;
/* 349*/        if((j = typedblock.length - 3) < 0)
                {
/* 352*/            if(j == -1)
/* 353*/                abyte0[i] = 0;
/* 355*/            abyte0[(i + typedblock.length) - 1] = -65;
/* 356*/            typedblock.incoming = 1;
/* 357*/            recordStackMap(typedblock, 0);
/* 358*/            return;
                }
/* 363*/        typedblock.incoming = 0;
/* 365*/        for(typedblock = 0; typedblock < j; typedblock++)
/* 366*/            abyte0[i + typedblock] = 0;

/* 368*/        abyte0[i + j] = -89;
/* 369*/        ByteArray.write16bit(-j, abyte0, i + j + 1);
            }

            private void fixTypes(byte abyte0[], TypedBlock atypedblock[])
                throws NotFoundException, BadBytecode
            {
/* 382*/        abyte0 = new ArrayList();
/* 383*/        int i = atypedblock.length;
/* 384*/        int j = 0;
/* 385*/        for(int k = 0; k < i; k++)
                {
                    TypedBlock typedblock;
/* 386*/            if(!(typedblock = atypedblock[k]).alreadySet())
/* 388*/                continue;
/* 388*/            int l = typedblock.localsTypes.length;
/* 389*/            for(int i1 = 0; i1 < l; i1++)
/* 390*/                j = typedblock.localsTypes[i1].dfs(abyte0, j, classPool);

/* 392*/            l = typedblock.stackTop;
/* 393*/            for(int j1 = 0; j1 < l; j1++)
/* 394*/                j = typedblock.stackTypes[j1].dfs(abyte0, j, classPool);

                }

            }

            public StackMapTable toStackMap(TypedBlock atypedblock[])
            {
/* 402*/        javassist.bytecode.StackMapTable.Writer writer = new javassist.bytecode.StackMapTable.Writer(32);
/* 403*/        int i = atypedblock.length;
                TypedBlock typedblock;
/* 404*/        int j = (typedblock = atypedblock[0]).length;
/* 406*/        if(typedblock.incoming > 0)
                {
/* 407*/            writer.sameFrame(0);
/* 408*/            j--;
                }
/* 411*/        for(int k = 1; k < i; k++)
                {
/* 412*/            TypedBlock typedblock1 = atypedblock[k];
/* 413*/            if(isTarget(typedblock1, atypedblock[k - 1]))
                    {
/* 414*/                typedblock1.resetNumLocals();
/* 415*/                int l = stackMapDiff(typedblock.numLocals, typedblock.localsTypes, typedblock1.numLocals, typedblock1.localsTypes);
/* 417*/                toStackMapBody(writer, typedblock1, l, j, typedblock);
/* 418*/                j = typedblock1.length - 1;
/* 419*/                typedblock = typedblock1;
/* 420*/                continue;
                    }
/* 421*/            if(typedblock1.incoming == 0)
                    {
/* 423*/                writer.sameFrame(j);
/* 424*/                j = typedblock1.length - 1;
/* 425*/                typedblock = typedblock1;
                    } else
                    {
/* 428*/                j += typedblock1.length;
                    }
                }

/* 431*/        return writer.toStackMapTable(cpool);
            }

            private boolean isTarget(TypedBlock typedblock, TypedBlock typedblock1)
            {
/* 438*/        if((typedblock = typedblock.incoming) > 1)
/* 440*/            return true;
/* 441*/        if(typedblock <= 0)
/* 442*/            return false;
/* 444*/        else
/* 444*/            return typedblock1.stop;
            }

            private void toStackMapBody(javassist.bytecode.StackMapTable.Writer writer, TypedBlock typedblock, int i, int j, TypedBlock typedblock1)
            {
                int k;
/* 452*/        if((k = typedblock.stackTop) == 0)
                {
/* 454*/            if(i == 0)
                    {
/* 455*/                writer.sameFrame(j);
/* 456*/                return;
                    }
/* 458*/            if(i < 0 && i >= -3)
                    {
/* 459*/                writer.chopFrame(j, -i);
/* 460*/                return;
                    }
/* 462*/            if(i > 0 && i <= 3)
                    {
/* 463*/                i = new int[i];
/* 464*/                typedblock1 = fillStackMap(typedblock.numLocals - typedblock1.numLocals, typedblock1.numLocals, i, typedblock.localsTypes);
/* 467*/                writer.appendFrame(j, typedblock1, i);
/* 468*/                return;
                    }
                } else
                {
/* 471*/            if(k == 1 && i == 0)
                    {
/* 472*/                i = typedblock.stackTypes[0];
/* 473*/                writer.sameLocals(j, i.getTypeTag(), i.getTypeData(cpool));
/* 474*/                return;
                    }
/* 476*/            if(k == 2 && i == 0 && (i = typedblock.stackTypes[0]).is2WordType())
                    {
/* 480*/                writer.sameLocals(j, i.getTypeTag(), i.getTypeData(cpool));
/* 481*/                return;
                    }
                }
/* 485*/        i = new int[k];
/* 486*/        typedblock1 = fillStackMap(k, 0, i, typedblock.stackTypes);
/* 487*/        int ai[] = new int[typedblock.numLocals];
/* 488*/        typedblock = fillStackMap(typedblock.numLocals, 0, ai, typedblock.localsTypes);
/* 489*/        writer.fullFrame(j, typedblock, ai, typedblock1, i);
            }

            private int[] fillStackMap(int i, int j, int ai[], TypeData atypedata[])
            {
/* 493*/        int k = diffSize(atypedata, j, j + i);
/* 494*/        ConstPool constpool = cpool;
/* 495*/        int ai1[] = new int[k];
/* 496*/        int l = 0;
/* 497*/        for(int i1 = 0; i1 < i; i1++)
                {
/* 498*/            TypeData typedata = atypedata[j + i1];
/* 499*/            ai1[l] = typedata.getTypeTag();
/* 500*/            ai[l] = typedata.getTypeData(constpool);
/* 501*/            if(typedata.is2WordType())
/* 502*/                i1++;
/* 504*/            l++;
                }

/* 507*/        return ai1;
            }

            private static int stackMapDiff(int i, TypeData atypedata[], int j, TypeData atypedata1[])
            {
                int k;
                int l;
/* 513*/        if((k = j - i) > 0)
/* 516*/            l = i;
/* 518*/        else
/* 518*/            l = j;
/* 520*/        if(stackMapEq(atypedata, atypedata1, l))
                {
/* 521*/            if(k > 0)
/* 522*/                return diffSize(atypedata1, l, j);
/* 524*/            else
/* 524*/                return -diffSize(atypedata, l, i);
                } else
                {
/* 526*/            return -100;
                }
            }

            private static boolean stackMapEq(TypeData atypedata[], TypeData atypedata1[], int i)
            {
/* 530*/        for(int j = 0; j < i; j++)
/* 531*/            if(!atypedata[j].eq(atypedata1[j]))
/* 532*/                return false;

/* 535*/        return true;
            }

            private static int diffSize(TypeData atypedata[], int i, int j)
            {
/* 539*/        int k = 0;
/* 540*/        do
                {
/* 540*/            if(i >= j)
/* 541*/                break;
/* 541*/            TypeData typedata = atypedata[i++];
/* 542*/            k++;
/* 543*/            if(typedata.is2WordType())
/* 544*/                i++;
                } while(true);
/* 547*/        return k;
            }

            public StackMap toStackMap2(ConstPool constpool, TypedBlock atypedblock[])
            {
/* 553*/        javassist.bytecode.StackMap.Writer writer = new javassist.bytecode.StackMap.Writer();
                int i;
/* 554*/        boolean aflag[] = new boolean[i = atypedblock.length];
/* 556*/        TypedBlock typedblock = atypedblock[0];
/* 559*/        aflag[0] = typedblock.incoming > 0;
/* 561*/        int j = aflag[0] ? 1 : 0;
/* 562*/        for(int k = 1; k < i; k++)
                {
/* 563*/            TypedBlock typedblock1 = atypedblock[k];
/* 564*/            if(aflag[k] = isTarget(typedblock1, atypedblock[k - 1]))
                    {
/* 565*/                typedblock1.resetNumLocals();
/* 567*/                j++;
                    }
                }

/* 571*/        if(j == 0)
/* 572*/            return null;
/* 574*/        writer.write16bit(j);
/* 575*/        for(int l = 0; l < i; l++)
/* 576*/            if(aflag[l])
/* 577*/                writeStackFrame(writer, constpool, atypedblock[l].position, atypedblock[l]);

/* 579*/        return writer.toStackMap(constpool);
            }

            private void writeStackFrame(javassist.bytecode.StackMap.Writer writer, ConstPool constpool, int i, TypedBlock typedblock)
            {
/* 583*/        writer.write16bit(i);
/* 584*/        writeVerifyTypeInfo(writer, constpool, typedblock.localsTypes, typedblock.numLocals);
/* 585*/        writeVerifyTypeInfo(writer, constpool, typedblock.stackTypes, typedblock.stackTop);
            }

            private void writeVerifyTypeInfo(javassist.bytecode.StackMap.Writer writer, ConstPool constpool, TypeData atypedata[], int i)
            {
/* 589*/        int j = 0;
/* 590*/        for(int k = 0; k < i; k++)
                {
                    TypeData typedata;
/* 591*/            if((typedata = atypedata[k]) != null && typedata.is2WordType())
                    {
/* 593*/                j++;
/* 594*/                k++;
                    }
                }

/* 598*/        writer.write16bit(i - j);
/* 599*/        for(int l = 0; l < i; l++)
                {
/* 600*/            TypeData typedata1 = atypedata[l];
/* 601*/            writer.writeVerifyTypeInfo(typedata1.getTypeTag(), typedata1.getTypeData(constpool));
/* 602*/            if(typedata1.is2WordType())
/* 603*/                l++;
                }

            }
}
