// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Tracer.java

package javassist.bytecode.stackmap;

import javassist.ClassPool;
import javassist.bytecode.*;

// Referenced classes of package javassist.bytecode.stackmap:
//            TypeData, TypeTag

public abstract class Tracer
    implements TypeTag
{

            public Tracer(ClassPool classpool, ConstPool constpool, int i, int j, String s)
            {
/*  42*/        classPool = classpool;
/*  43*/        cpool = constpool;
/*  44*/        returnType = s;
/*  45*/        stackTop = 0;
/*  46*/        stackTypes = TypeData.make(i);
/*  47*/        localsTypes = TypeData.make(j);
            }

            public Tracer(Tracer tracer)
            {
/*  51*/        classPool = tracer.classPool;
/*  52*/        cpool = tracer.cpool;
/*  53*/        returnType = tracer.returnType;
/*  54*/        stackTop = tracer.stackTop;
/*  55*/        stackTypes = TypeData.make(tracer.stackTypes.length);
/*  56*/        localsTypes = TypeData.make(tracer.localsTypes.length);
            }

            protected int doOpcode(int i, byte abyte0[])
                throws BadBytecode
            {
                int j;
/*  71*/        if((j = abyte0[i] & 0xff) >= 96)
/*  73*/            break MISSING_BLOCK_LABEL_36;
/*  73*/        if(j < 54)
/*  74*/            return doOpcode0_53(i, abyte0, j);
/*  76*/        return doOpcode54_95(i, abyte0, j);
/*  78*/        if(j < 148)
/*  79*/            return doOpcode96_147(i, abyte0, j);
/*  81*/        return doOpcode148_201(i, abyte0, j);
                ArrayIndexOutOfBoundsException arrayindexoutofboundsexception;
/*  83*/        arrayindexoutofboundsexception;
/*  84*/        throw new BadBytecode((new StringBuilder("inconsistent stack height ")).append(arrayindexoutofboundsexception.getMessage()).toString(), arrayindexoutofboundsexception);
            }

            protected void visitBranch(int i, byte abyte0[], int j)
                throws BadBytecode
            {
            }

            protected void visitGoto(int i, byte abyte0[], int j)
                throws BadBytecode
            {
            }

            protected void visitReturn(int i, byte abyte0[])
                throws BadBytecode
            {
            }

            protected void visitThrow(int i, byte abyte0[])
                throws BadBytecode
            {
            }

            protected void visitTableSwitch(int i, byte abyte0[], int j, int k, int l)
                throws BadBytecode
            {
            }

            protected void visitLookupSwitch(int i, byte abyte0[], int j, int k, int l)
                throws BadBytecode
            {
            }

            protected void visitJSR(int i, byte abyte0[])
                throws BadBytecode
            {
            }

            protected void visitRET(int i, byte abyte0[])
                throws BadBytecode
            {
            }

            private int doOpcode0_53(int i, byte abyte0[], int j)
                throws BadBytecode
            {
/* 134*/        TypeData atypedata[] = stackTypes;
/* 135*/        switch(j)
                {
/* 134*/        case 0: // '\0'
                    break;

/* 139*/        case 1: // '\001'
/* 139*/            atypedata[stackTop++] = new TypeData.NullType();
/* 140*/            break;

/* 148*/        case 2: // '\002'
/* 148*/        case 3: // '\003'
/* 148*/        case 4: // '\004'
/* 148*/        case 5: // '\005'
/* 148*/        case 6: // '\006'
/* 148*/        case 7: // '\007'
/* 148*/        case 8: // '\b'
/* 148*/            atypedata[stackTop++] = INTEGER;
/* 149*/            break;

/* 152*/        case 9: // '\t'
/* 152*/        case 10: // '\n'
/* 152*/            atypedata[stackTop++] = LONG;
/* 153*/            atypedata[stackTop++] = TOP;
/* 154*/            break;

/* 158*/        case 11: // '\013'
/* 158*/        case 12: // '\f'
/* 158*/        case 13: // '\r'
/* 158*/            atypedata[stackTop++] = FLOAT;
/* 159*/            break;

/* 162*/        case 14: // '\016'
/* 162*/        case 15: // '\017'
/* 162*/            atypedata[stackTop++] = DOUBLE;
/* 163*/            atypedata[stackTop++] = TOP;
/* 164*/            break;

/* 167*/        case 16: // '\020'
/* 167*/        case 17: // '\021'
/* 167*/            atypedata[stackTop++] = INTEGER;
/* 168*/            return j != 17 ? 2 : 3;

/* 170*/        case 18: // '\022'
/* 170*/            doLDC(abyte0[i + 1] & 0xff);
/* 171*/            return 2;

/* 174*/        case 19: // '\023'
/* 174*/        case 20: // '\024'
/* 174*/            doLDC(ByteArray.readU16bit(abyte0, i + 1));
/* 175*/            return 3;

/* 177*/        case 21: // '\025'
/* 177*/            return doXLOAD(INTEGER, abyte0, i);

/* 179*/        case 22: // '\026'
/* 179*/            return doXLOAD(LONG, abyte0, i);

/* 181*/        case 23: // '\027'
/* 181*/            return doXLOAD(FLOAT, abyte0, i);

/* 183*/        case 24: // '\030'
/* 183*/            return doXLOAD(DOUBLE, abyte0, i);

/* 185*/        case 25: // '\031'
/* 185*/            return doALOAD(abyte0[i + 1] & 0xff);

/* 190*/        case 26: // '\032'
/* 190*/        case 27: // '\033'
/* 190*/        case 28: // '\034'
/* 190*/        case 29: // '\035'
/* 190*/            atypedata[stackTop++] = INTEGER;
                    break;

/* 196*/        case 30: // '\036'
/* 196*/        case 31: // '\037'
/* 196*/        case 32: // ' '
/* 196*/        case 33: // '!'
/* 196*/            atypedata[stackTop++] = LONG;
/* 197*/            atypedata[stackTop++] = TOP;
                    break;

/* 203*/        case 34: // '"'
/* 203*/        case 35: // '#'
/* 203*/        case 36: // '$'
/* 203*/        case 37: // '%'
/* 203*/            atypedata[stackTop++] = FLOAT;
                    break;

/* 209*/        case 38: // '&'
/* 209*/        case 39: // '\''
/* 209*/        case 40: // '('
/* 209*/        case 41: // ')'
/* 209*/            atypedata[stackTop++] = DOUBLE;
/* 210*/            atypedata[stackTop++] = TOP;
                    break;

/* 216*/        case 42: // '*'
/* 216*/        case 43: // '+'
/* 216*/        case 44: // ','
/* 216*/        case 45: // '-'
/* 216*/            i = j - 42;
/* 217*/            atypedata[stackTop++] = localsTypes[i];
                    break;

/* 220*/        case 46: // '.'
/* 220*/            atypedata[--stackTop - 1] = INTEGER;
                    break;

/* 223*/        case 47: // '/'
/* 223*/            atypedata[stackTop - 2] = LONG;
/* 224*/            atypedata[stackTop - 1] = TOP;
                    break;

/* 227*/        case 48: // '0'
/* 227*/            atypedata[--stackTop - 1] = FLOAT;
                    break;

/* 230*/        case 49: // '1'
/* 230*/            atypedata[stackTop - 2] = DOUBLE;
/* 231*/            atypedata[stackTop - 1] = TOP;
                    break;

/* 234*/        case 50: // '2'
/* 234*/            i = --stackTop - 1;
/* 235*/            abyte0 = atypedata[i];
/* 236*/            atypedata[i] = TypeData.ArrayElement.make(abyte0);
                    break;

/* 241*/        case 51: // '3'
/* 241*/        case 52: // '4'
/* 241*/        case 53: // '5'
/* 241*/            atypedata[--stackTop - 1] = INTEGER;
                    break;

/* 244*/        default:
/* 244*/            throw new RuntimeException("fatal");
                }
/* 247*/        return 1;
            }

            private void doLDC(int i)
            {
/* 251*/        TypeData atypedata[] = stackTypes;
/* 252*/        if((i = cpool.getTag(i)) == 8)
                {
/* 254*/            atypedata[stackTop++] = new TypeData.ClassName("java.lang.String");
/* 254*/            return;
                }
/* 255*/        if(i == 3)
                {
/* 256*/            atypedata[stackTop++] = INTEGER;
/* 256*/            return;
                }
/* 257*/        if(i == 4)
                {
/* 258*/            atypedata[stackTop++] = FLOAT;
/* 258*/            return;
                }
/* 259*/        if(i == 5)
                {
/* 260*/            atypedata[stackTop++] = LONG;
/* 261*/            atypedata[stackTop++] = TOP;
/* 261*/            return;
                }
/* 263*/        if(i == 6)
                {
/* 264*/            atypedata[stackTop++] = DOUBLE;
/* 265*/            atypedata[stackTop++] = TOP;
/* 265*/            return;
                }
/* 267*/        if(i == 7)
                {
/* 268*/            atypedata[stackTop++] = new TypeData.ClassName("java.lang.Class");
/* 268*/            return;
                } else
                {
/* 270*/            throw new RuntimeException((new StringBuilder("bad LDC: ")).append(i).toString());
                }
            }

            private int doXLOAD(TypeData typedata, byte abyte0[], int i)
            {
/* 274*/        abyte0 = abyte0[i + 1] & 0xff;
/* 275*/        return doXLOAD(abyte0, typedata);
            }

            private int doXLOAD(int i, TypeData typedata)
            {
/* 279*/        stackTypes[stackTop++] = typedata;
/* 280*/        if(typedata.is2WordType())
/* 281*/            stackTypes[stackTop++] = TOP;
/* 283*/        return 2;
            }

            private int doALOAD(int i)
            {
/* 287*/        stackTypes[stackTop++] = localsTypes[i];
/* 288*/        return 2;
            }

            private int doOpcode54_95(int i, byte abyte0[], int j)
                throws BadBytecode
            {
/* 292*/        switch(j)
                {
/* 294*/        case 54: // '6'
/* 294*/            return doXSTORE(i, abyte0, INTEGER);

/* 296*/        case 55: // '7'
/* 296*/            return doXSTORE(i, abyte0, LONG);

/* 298*/        case 56: // '8'
/* 298*/            return doXSTORE(i, abyte0, FLOAT);

/* 300*/        case 57: // '9'
/* 300*/            return doXSTORE(i, abyte0, DOUBLE);

/* 302*/        case 58: // ':'
/* 302*/            return doASTORE(abyte0[i + 1] & 0xff);

/* 307*/        case 59: // ';'
/* 307*/        case 60: // '<'
/* 307*/        case 61: // '='
/* 307*/        case 62: // '>'
/* 307*/            i = j - 59;
/* 308*/            localsTypes[i] = INTEGER;
/* 309*/            stackTop--;
                    break;

/* 315*/        case 63: // '?'
/* 315*/        case 64: // '@'
/* 315*/        case 65: // 'A'
/* 315*/        case 66: // 'B'
/* 315*/            i = j - 63;
/* 316*/            localsTypes[i] = LONG;
/* 317*/            localsTypes[i + 1] = TOP;
/* 318*/            stackTop -= 2;
                    break;

/* 324*/        case 67: // 'C'
/* 324*/        case 68: // 'D'
/* 324*/        case 69: // 'E'
/* 324*/        case 70: // 'F'
/* 324*/            i = j - 67;
/* 325*/            localsTypes[i] = FLOAT;
/* 326*/            stackTop--;
                    break;

/* 332*/        case 71: // 'G'
/* 332*/        case 72: // 'H'
/* 332*/        case 73: // 'I'
/* 332*/        case 74: // 'J'
/* 332*/            i = j - 71;
/* 333*/            localsTypes[i] = DOUBLE;
/* 334*/            localsTypes[i + 1] = TOP;
/* 335*/            stackTop -= 2;
                    break;

/* 341*/        case 75: // 'K'
/* 341*/        case 76: // 'L'
/* 341*/        case 77: // 'M'
/* 341*/        case 78: // 'N'
/* 341*/            i = j - 75;
/* 342*/            doASTORE(i);
                    break;

/* 348*/        case 79: // 'O'
/* 348*/        case 80: // 'P'
/* 348*/        case 81: // 'Q'
/* 348*/        case 82: // 'R'
/* 348*/            stackTop -= j != 80 && j != 82 ? 3 : 4;
                    break;

/* 351*/        case 83: // 'S'
/* 351*/            TypeData.ArrayElement.aastore(stackTypes[stackTop - 3], stackTypes[stackTop - 1], classPool);
/* 354*/            stackTop -= 3;
                    break;

/* 359*/        case 84: // 'T'
/* 359*/        case 85: // 'U'
/* 359*/        case 86: // 'V'
/* 359*/            stackTop -= 3;
                    break;

/* 362*/        case 87: // 'W'
/* 362*/            stackTop--;
                    break;

/* 365*/        case 88: // 'X'
/* 365*/            stackTop -= 2;
                    break;

/* 368*/        case 89: // 'Y'
/* 368*/            i = stackTop;
/* 369*/            stackTypes[i] = stackTypes[i - 1];
/* 370*/            stackTop = i + 1;
                    break;

/* 374*/        case 90: // 'Z'
/* 374*/        case 91: // '['
/* 374*/            i = (j - 90) + 2;
/* 375*/            doDUP_XX(1, i);
/* 376*/            abyte0 = stackTop;
/* 377*/            stackTypes[abyte0 - i] = stackTypes[abyte0];
/* 378*/            stackTop = abyte0 + 1;
                    break;

/* 381*/        case 92: // '\\'
/* 381*/            doDUP_XX(2, 2);
/* 382*/            stackTop += 2;
                    break;

/* 386*/        case 93: // ']'
/* 386*/        case 94: // '^'
/* 386*/            i = (j - 93) + 3;
/* 387*/            doDUP_XX(2, i);
/* 388*/            abyte0 = stackTop;
/* 389*/            stackTypes[abyte0 - i] = stackTypes[abyte0];
/* 390*/            stackTypes[(abyte0 - i) + 1] = stackTypes[abyte0 + 1];
/* 391*/            stackTop = abyte0 + 2;
                    break;

/* 394*/        case 95: // '_'
/* 394*/            i = stackTop - 1;
/* 395*/            abyte0 = stackTypes[i];
/* 396*/            stackTypes[i] = stackTypes[i - 1];
/* 397*/            stackTypes[i - 1] = abyte0;
                    break;

/* 400*/        default:
/* 400*/            throw new RuntimeException("fatal");
                }
/* 403*/        return 1;
            }

            private int doXSTORE(int i, byte abyte0[], TypeData typedata)
            {
/* 407*/        i = abyte0[i + 1] & 0xff;
/* 408*/        return doXSTORE(i, typedata);
            }

            private int doXSTORE(int i, TypeData typedata)
            {
/* 412*/        stackTop--;
/* 413*/        localsTypes[i] = typedata;
/* 414*/        if(typedata.is2WordType())
                {
/* 415*/            stackTop--;
/* 416*/            localsTypes[i + 1] = TOP;
                }
/* 419*/        return 2;
            }

            private int doASTORE(int i)
            {
/* 423*/        stackTop--;
/* 425*/        localsTypes[i] = stackTypes[stackTop];
/* 426*/        return 2;
            }

            private void doDUP_XX(int i, int j)
            {
/* 430*/        TypeData atypedata[] = stackTypes;
                int k;
/* 431*/        for(j = (k = stackTop - 1) - j; k > j; k--)
/* 434*/            atypedata[k + i] = atypedata[k];

            }

            private int doOpcode96_147(int i, byte abyte0[], int j)
            {
/* 440*/        if(j <= 131)
                {
/* 441*/            stackTop += Opcode.STACK_GROW[j];
/* 442*/            return 1;
                }
/* 445*/        switch(j)
                {
/* 448*/        case 132: 
/* 448*/            return 3;

/* 450*/        case 133: 
/* 450*/            stackTypes[stackTop - 1] = LONG;
/* 451*/            stackTypes[stackTop] = TOP;
/* 452*/            stackTop++;
                    break;

/* 455*/        case 134: 
/* 455*/            stackTypes[stackTop - 1] = FLOAT;
                    break;

/* 458*/        case 135: 
/* 458*/            stackTypes[stackTop - 1] = DOUBLE;
/* 459*/            stackTypes[stackTop] = TOP;
/* 460*/            stackTop++;
                    break;

/* 463*/        case 136: 
/* 463*/            stackTypes[--stackTop - 1] = INTEGER;
                    break;

/* 466*/        case 137: 
/* 466*/            stackTypes[--stackTop - 1] = FLOAT;
                    break;

/* 469*/        case 138: 
/* 469*/            stackTypes[stackTop - 2] = DOUBLE;
                    break;

/* 472*/        case 139: 
/* 472*/            stackTypes[stackTop - 1] = INTEGER;
                    break;

/* 475*/        case 140: 
/* 475*/            stackTypes[stackTop - 1] = LONG;
/* 476*/            stackTypes[stackTop] = TOP;
/* 477*/            stackTop++;
                    break;

/* 480*/        case 141: 
/* 480*/            stackTypes[stackTop - 1] = DOUBLE;
/* 481*/            stackTypes[stackTop] = TOP;
/* 482*/            stackTop++;
                    break;

/* 485*/        case 142: 
/* 485*/            stackTypes[--stackTop - 1] = INTEGER;
                    break;

/* 488*/        case 143: 
/* 488*/            stackTypes[stackTop - 2] = LONG;
                    break;

/* 491*/        case 144: 
/* 491*/            stackTypes[--stackTop - 1] = FLOAT;
                    break;

/* 498*/        default:
/* 498*/            throw new RuntimeException("fatal");

/* 440*/        case 145: 
/* 440*/        case 146: 
/* 440*/        case 147: 
                    break;
                }
/* 501*/        return 1;
            }

            private int doOpcode148_201(int i, byte abyte0[], int j)
                throws BadBytecode
            {
/* 505*/        switch(j)
                {
/* 505*/        default:
                    break;

/* 507*/        case 148: 
/* 507*/            stackTypes[stackTop - 4] = INTEGER;
/* 508*/            stackTop -= 3;
/* 509*/            break;

/* 512*/        case 149: 
/* 512*/        case 150: 
/* 512*/            stackTypes[--stackTop - 1] = INTEGER;
/* 513*/            break;

/* 516*/        case 151: 
/* 516*/        case 152: 
/* 516*/            stackTypes[stackTop - 4] = INTEGER;
/* 517*/            stackTop -= 3;
/* 518*/            break;

/* 525*/        case 153: 
/* 525*/        case 154: 
/* 525*/        case 155: 
/* 525*/        case 156: 
/* 525*/        case 157: 
/* 525*/        case 158: 
/* 525*/            stackTop--;
/* 526*/            visitBranch(i, abyte0, ByteArray.readS16bit(abyte0, i + 1));
/* 527*/            return 3;

/* 536*/        case 159: 
/* 536*/        case 160: 
/* 536*/        case 161: 
/* 536*/        case 162: 
/* 536*/        case 163: 
/* 536*/        case 164: 
/* 536*/        case 165: 
/* 536*/        case 166: 
/* 536*/            stackTop -= 2;
/* 537*/            visitBranch(i, abyte0, ByteArray.readS16bit(abyte0, i + 1));
/* 538*/            return 3;

/* 540*/        case 167: 
/* 540*/            visitGoto(i, abyte0, ByteArray.readS16bit(abyte0, i + 1));
/* 541*/            return 3;

/* 543*/        case 168: 
/* 543*/            visitJSR(i, abyte0);
/* 544*/            return 3;

/* 546*/        case 169: 
/* 546*/            visitRET(i, abyte0);
/* 547*/            return 2;

/* 549*/        case 170: 
/* 549*/            stackTop--;
/* 550*/            j = (i & -4) + 8;
/* 551*/            int k = ByteArray.read32bit(abyte0, j);
                    int i1;
/* 552*/            k = ((i1 = ByteArray.read32bit(abyte0, j + 4)) - k) + 1;
/* 554*/            visitTableSwitch(i, abyte0, k, j + 8, ByteArray.read32bit(abyte0, j - 4));
/* 555*/            return ((k << 2) + 16) - (i & 3);

/* 557*/        case 171: 
/* 557*/            stackTop--;
/* 558*/            j = (i & -4) + 8;
/* 559*/            int l = ByteArray.read32bit(abyte0, j);
/* 560*/            visitLookupSwitch(i, abyte0, l, j + 4, ByteArray.read32bit(abyte0, j - 4));
/* 561*/            return ((l << 3) + 12) - (i & 3);

/* 563*/        case 172: 
/* 563*/            stackTop--;
/* 564*/            visitReturn(i, abyte0);
/* 565*/            break;

/* 567*/        case 173: 
/* 567*/            stackTop -= 2;
/* 568*/            visitReturn(i, abyte0);
/* 569*/            break;

/* 571*/        case 174: 
/* 571*/            stackTop--;
/* 572*/            visitReturn(i, abyte0);
/* 573*/            break;

/* 575*/        case 175: 
/* 575*/            stackTop -= 2;
/* 576*/            visitReturn(i, abyte0);
/* 577*/            break;

/* 579*/        case 176: 
/* 579*/            stackTypes[--stackTop].setType(returnType, classPool);
/* 580*/            visitReturn(i, abyte0);
/* 581*/            break;

/* 583*/        case 177: 
/* 583*/            visitReturn(i, abyte0);
/* 584*/            break;

/* 586*/        case 178: 
/* 586*/            return doGetField(i, abyte0, false);

/* 588*/        case 179: 
/* 588*/            return doPutField(i, abyte0, false);

/* 590*/        case 180: 
/* 590*/            return doGetField(i, abyte0, true);

/* 592*/        case 181: 
/* 592*/            return doPutField(i, abyte0, true);

/* 595*/        case 182: 
/* 595*/        case 183: 
/* 595*/            return doInvokeMethod(i, abyte0, true);

/* 597*/        case 184: 
/* 597*/            return doInvokeMethod(i, abyte0, false);

/* 599*/        case 185: 
/* 599*/            return doInvokeIntfMethod(i, abyte0);

/* 601*/        case 186: 
/* 601*/            return doInvokeDynamic(i, abyte0);

/* 603*/        case 187: 
/* 603*/            j = ByteArray.readU16bit(abyte0, i + 1);
/* 604*/            stackTypes[stackTop++] = new TypeData.UninitData(i, cpool.getClassInfo(j));
/* 606*/            return 3;

/* 608*/        case 188: 
/* 608*/            return doNEWARRAY(i, abyte0);

/* 610*/        case 189: 
/* 610*/            j = ByteArray.readU16bit(abyte0, i + 1);
                    String s;
/* 611*/            if((s = cpool.getClassInfo(j).replace('.', '/')).charAt(0) == '[')
/* 613*/                s = (new StringBuilder("[")).append(s).toString();
/* 615*/            else
/* 615*/                s = (new StringBuilder("[L")).append(s).append(";").toString();
/* 617*/            stackTypes[stackTop - 1] = new TypeData.ClassName(s);
/* 619*/            return 3;

/* 621*/        case 190: 
/* 621*/            stackTypes[stackTop - 1].setType("[Ljava.lang.Object;", classPool);
/* 622*/            stackTypes[stackTop - 1] = INTEGER;
/* 623*/            break;

/* 625*/        case 191: 
/* 625*/            stackTypes[--stackTop].setType("java.lang.Throwable", classPool);
/* 626*/            visitThrow(i, abyte0);
/* 627*/            break;

/* 630*/        case 192: 
/* 630*/            j = ByteArray.readU16bit(abyte0, i + 1);
                    String s1;
/* 631*/            if((s1 = cpool.getClassInfo(j)).charAt(0) == '[')
/* 633*/                s1 = s1.replace('.', '/');
/* 635*/            stackTypes[stackTop - 1] = new TypeData.ClassName(s1);
/* 636*/            return 3;

/* 639*/        case 193: 
/* 639*/            stackTypes[stackTop - 1] = INTEGER;
/* 640*/            return 3;

/* 643*/        case 194: 
/* 643*/        case 195: 
/* 643*/            stackTop--;
                    break;

/* 647*/        case 196: 
/* 647*/            return doWIDE(i, abyte0);

/* 649*/        case 197: 
/* 649*/            return doMultiANewArray(i, abyte0);

/* 652*/        case 198: 
/* 652*/        case 199: 
/* 652*/            stackTop--;
/* 653*/            visitBranch(i, abyte0, ByteArray.readS16bit(abyte0, i + 1));
/* 654*/            return 3;

/* 656*/        case 200: 
/* 656*/            visitGoto(i, abyte0, ByteArray.read32bit(abyte0, i + 1));
/* 657*/            return 5;

/* 659*/        case 201: 
/* 659*/            visitJSR(i, abyte0);
/* 660*/            return 5;
                }
/* 662*/        return 1;
            }

            private int doWIDE(int i, byte abyte0[])
                throws BadBytecode
            {
                int j;
/* 666*/        switch(j = abyte0[i + 1] & 0xff)
                {
/* 669*/        case 21: // '\025'
/* 669*/            doWIDE_XLOAD(i, abyte0, INTEGER);
                    break;

/* 672*/        case 22: // '\026'
/* 672*/            doWIDE_XLOAD(i, abyte0, LONG);
                    break;

/* 675*/        case 23: // '\027'
/* 675*/            doWIDE_XLOAD(i, abyte0, FLOAT);
                    break;

/* 678*/        case 24: // '\030'
/* 678*/            doWIDE_XLOAD(i, abyte0, DOUBLE);
                    break;

/* 681*/        case 25: // '\031'
/* 681*/            i = ByteArray.readU16bit(abyte0, i + 2);
/* 682*/            doALOAD(i);
                    break;

/* 685*/        case 54: // '6'
/* 685*/            doWIDE_STORE(i, abyte0, INTEGER);
                    break;

/* 688*/        case 55: // '7'
/* 688*/            doWIDE_STORE(i, abyte0, LONG);
                    break;

/* 691*/        case 56: // '8'
/* 691*/            doWIDE_STORE(i, abyte0, FLOAT);
                    break;

/* 694*/        case 57: // '9'
/* 694*/            doWIDE_STORE(i, abyte0, DOUBLE);
                    break;

/* 697*/        case 58: // ':'
/* 697*/            i = ByteArray.readU16bit(abyte0, i + 2);
/* 698*/            doASTORE(i);
                    break;

/* 702*/        case 132: 
/* 702*/            return 6;

/* 704*/        case 169: 
/* 704*/            visitRET(i, abyte0);
                    break;

/* 707*/        default:
/* 707*/            throw new RuntimeException((new StringBuilder("bad WIDE instruction: ")).append(j).toString());
                }
/* 710*/        return 4;
            }

            private void doWIDE_XLOAD(int i, byte abyte0[], TypeData typedata)
            {
/* 714*/        i = ByteArray.readU16bit(abyte0, i + 2);
/* 715*/        doXLOAD(i, typedata);
            }

            private void doWIDE_STORE(int i, byte abyte0[], TypeData typedata)
            {
/* 719*/        i = ByteArray.readU16bit(abyte0, i + 2);
/* 720*/        doXSTORE(i, typedata);
            }

            private int doPutField(int i, byte abyte0[], boolean flag)
                throws BadBytecode
            {
/* 724*/        i = ByteArray.readU16bit(abyte0, i + 1);
/* 725*/        abyte0 = cpool.getFieldrefType(i);
/* 726*/        stackTop -= Descriptor.dataSize(abyte0);
                char c;
/* 727*/        if((c = abyte0.charAt(0)) == 'L')
/* 729*/            stackTypes[stackTop].setType(getFieldClassName(abyte0, 0), classPool);
/* 730*/        else
/* 730*/        if(c == '[')
/* 731*/            stackTypes[stackTop].setType(abyte0, classPool);
/* 733*/        setFieldTarget(flag, i);
/* 734*/        return 3;
            }

            private int doGetField(int i, byte abyte0[], boolean flag)
                throws BadBytecode
            {
/* 738*/        i = ByteArray.readU16bit(abyte0, i + 1);
/* 739*/        setFieldTarget(flag, i);
/* 740*/        i = cpool.getFieldrefType(i);
/* 741*/        pushMemberType(i);
/* 742*/        return 3;
            }

            private void setFieldTarget(boolean flag, int i)
                throws BadBytecode
            {
/* 746*/        if(flag)
                {
/* 747*/            flag = cpool.getFieldrefClassName(i);
/* 748*/            stackTypes[--stackTop].setType(flag, classPool);
                }
            }

            private int doNEWARRAY(int i, byte abyte0[])
            {
/* 753*/        int j = stackTop - 1;
/* 755*/        switch(abyte0[i + 1] & 0xff)
                {
/* 757*/        case 4: // '\004'
/* 757*/            i = "[Z";
                    break;

/* 760*/        case 5: // '\005'
/* 760*/            i = "[C";
                    break;

/* 763*/        case 6: // '\006'
/* 763*/            i = "[F";
                    break;

/* 766*/        case 7: // '\007'
/* 766*/            i = "[D";
                    break;

/* 769*/        case 8: // '\b'
/* 769*/            i = "[B";
                    break;

/* 772*/        case 9: // '\t'
/* 772*/            i = "[S";
                    break;

/* 775*/        case 10: // '\n'
/* 775*/            i = "[I";
                    break;

/* 778*/        case 11: // '\013'
/* 778*/            i = "[J";
                    break;

/* 781*/        default:
/* 781*/            throw new RuntimeException("bad newarray");
                }
/* 784*/        stackTypes[j] = new TypeData.ClassName(i);
/* 785*/        return 2;
            }

            private int doMultiANewArray(int i, byte abyte0[])
            {
/* 789*/        int j = ByteArray.readU16bit(abyte0, i + 1);
/* 790*/        i = abyte0[i + 3] & 0xff;
/* 791*/        stackTop -= i - 1;
/* 793*/        i = cpool.getClassInfo(j).replace('.', '/');
/* 794*/        stackTypes[stackTop - 1] = new TypeData.ClassName(i);
/* 795*/        return 4;
            }

            private int doInvokeMethod(int i, byte abyte0[], boolean flag)
                throws BadBytecode
            {
/* 799*/        i = ByteArray.readU16bit(abyte0, i + 1);
/* 800*/        abyte0 = cpool.getMethodrefType(i);
/* 801*/        checkParamTypes(abyte0, 1);
/* 802*/        if(flag)
                {
/* 803*/            i = cpool.getMethodrefClassName(i);
/* 804*/            if(((flag = stackTypes[--stackTop]) instanceof TypeData.UninitTypeVar) && flag.isUninit())
/* 806*/                constructorCalled(flag, ((TypeData.UninitTypeVar)flag).offset());
/* 807*/            else
/* 807*/            if(flag instanceof TypeData.UninitData)
/* 808*/                constructorCalled(flag, ((TypeData.UninitData)flag).offset());
/* 810*/            flag.setType(i, classPool);
                }
/* 813*/        pushMemberType(abyte0);
/* 814*/        return 3;
            }

            private void constructorCalled(TypeData typedata, int i)
            {
/* 823*/        typedata.constructorCalled(i);
/* 824*/        for(typedata = 0; typedata < stackTop; typedata++)
/* 825*/            stackTypes[typedata].constructorCalled(i);

/* 827*/        for(typedata = 0; typedata < localsTypes.length; typedata++)
/* 828*/            localsTypes[typedata].constructorCalled(i);

            }

            private int doInvokeIntfMethod(int i, byte abyte0[])
                throws BadBytecode
            {
/* 832*/        i = ByteArray.readU16bit(abyte0, i + 1);
/* 833*/        abyte0 = cpool.getInterfaceMethodrefType(i);
/* 834*/        checkParamTypes(abyte0, 1);
/* 835*/        i = cpool.getInterfaceMethodrefClassName(i);
/* 836*/        stackTypes[--stackTop].setType(i, classPool);
/* 837*/        pushMemberType(abyte0);
/* 838*/        return 5;
            }

            private int doInvokeDynamic(int i, byte abyte0[])
                throws BadBytecode
            {
/* 842*/        i = ByteArray.readU16bit(abyte0, i + 1);
/* 843*/        i = cpool.getInvokeDynamicType(i);
/* 844*/        checkParamTypes(i, 1);
/* 852*/        pushMemberType(i);
/* 853*/        return 5;
            }

            private void pushMemberType(String s)
            {
/* 857*/        int i = 0;
/* 858*/        if(s.charAt(0) == '(' && (i = s.indexOf(')') + 1) <= 0)
/* 861*/            throw new IndexOutOfBoundsException((new StringBuilder("bad descriptor: ")).append(s).toString());
/* 865*/        TypeData atypedata[] = stackTypes;
/* 866*/        int j = stackTop;
/* 867*/        switch(s.charAt(i))
                {
/* 869*/        case 91: // '['
/* 869*/            atypedata[j] = new TypeData.ClassName(s.substring(i));
                    break;

/* 872*/        case 76: // 'L'
/* 872*/            atypedata[j] = new TypeData.ClassName(getFieldClassName(s, i));
                    break;

/* 875*/        case 74: // 'J'
/* 875*/            atypedata[j] = LONG;
/* 876*/            atypedata[j + 1] = TOP;
/* 877*/            stackTop += 2;
/* 878*/            return;

/* 880*/        case 70: // 'F'
/* 880*/            atypedata[j] = FLOAT;
                    break;

/* 883*/        case 68: // 'D'
/* 883*/            atypedata[j] = DOUBLE;
/* 884*/            atypedata[j + 1] = TOP;
/* 885*/            stackTop += 2;
/* 886*/            return;

/* 888*/        case 86: // 'V'
/* 888*/            return;

/* 890*/        default:
/* 890*/            atypedata[j] = INTEGER;
                    break;
                }
/* 894*/        stackTop++;
            }

            private static String getFieldClassName(String s, int i)
            {
/* 898*/        return s.substring(i + 1, s.length() - 1).replace('/', '.');
            }

            private void checkParamTypes(String s, int i)
                throws BadBytecode
            {
                char c;
/* 902*/        if((c = s.charAt(i)) == ')')
/* 904*/            return;
/* 906*/        int j = i;
/* 907*/        boolean flag = false;
/* 908*/        for(; c == '['; c = s.charAt(++j))
/* 909*/            flag = true;

/* 913*/        if(c == 'L')
                {
/* 914*/            if((j = s.indexOf(';', j) + 1) <= 0)
/* 916*/                throw new IndexOutOfBoundsException("bad descriptor");
                } else
                {
/* 919*/            j++;
                }
/* 921*/        checkParamTypes(s, j);
/* 922*/        if(!flag && (c == 'J' || c == 'D'))
/* 923*/            stackTop -= 2;
/* 925*/        else
/* 925*/            stackTop--;
/* 927*/        if(flag)
                {
/* 928*/            stackTypes[stackTop].setType(s.substring(i, j), classPool);
/* 928*/            return;
                }
/* 929*/        if(c == 'L')
/* 930*/            stackTypes[stackTop].setType(s.substring(i + 1, j - 1).replace('/', '.'), classPool);
            }

            protected ClassPool classPool;
            protected ConstPool cpool;
            protected String returnType;
            protected int stackTop;
            protected TypeData stackTypes[];
            protected TypeData localsTypes[];
}
