// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Executor.java

package javassist.bytecode.analysis;

import javassist.*;
import javassist.bytecode.*;

// Referenced classes of package javassist.bytecode.analysis:
//            Frame, Subroutine, Type

public class Executor
    implements Opcode
{

            public Executor(ClassPool classpool, ConstPool constpool)
            {
/*  42*/        constPool = constpool;
/*  43*/        classPool = classpool;
/*  46*/        try
                {
/*  46*/            STRING_TYPE = getType("java.lang.String");
/*  47*/            CLASS_TYPE = getType("java.lang.Class");
/*  48*/            THROWABLE_TYPE = getType("java.lang.Throwable");
/*  51*/            return;
                }
                // Misplaced declaration of an exception variable
/*  49*/        catch(ClassPool classpool)
                {
/*  50*/            throw new RuntimeException(classpool);
                }
            }

            public void execute(MethodInfo methodinfo, int i, CodeIterator codeiterator, Frame frame, Subroutine subroutine)
                throws BadBytecode
            {
/*  68*/        lastPos = i;
                int j;
/*  69*/        switch(j = codeiterator.byteAt(i))
                {
/*  68*/        default:
                    break;

/*  75*/        case 0: // '\0'
/*  75*/            return;

/*  77*/        case 1: // '\001'
/*  77*/            frame.push(Type.UNINIT);
/*  78*/            return;

/*  86*/        case 2: // '\002'
/*  86*/        case 3: // '\003'
/*  86*/        case 4: // '\004'
/*  86*/        case 5: // '\005'
/*  86*/        case 6: // '\006'
/*  86*/        case 7: // '\007'
/*  86*/        case 8: // '\b'
/*  86*/            frame.push(Type.INTEGER);
/*  87*/            return;

/*  90*/        case 9: // '\t'
/*  90*/        case 10: // '\n'
/*  90*/            frame.push(Type.LONG);
/*  91*/            frame.push(Type.TOP);
/*  92*/            return;

/*  96*/        case 11: // '\013'
/*  96*/        case 12: // '\f'
/*  96*/        case 13: // '\r'
/*  96*/            frame.push(Type.FLOAT);
/*  97*/            return;

/* 100*/        case 14: // '\016'
/* 100*/        case 15: // '\017'
/* 100*/            frame.push(Type.DOUBLE);
/* 101*/            frame.push(Type.TOP);
/* 102*/            return;

/* 105*/        case 16: // '\020'
/* 105*/        case 17: // '\021'
/* 105*/            frame.push(Type.INTEGER);
/* 106*/            return;

/* 108*/        case 18: // '\022'
/* 108*/            evalLDC(codeiterator.byteAt(i + 1), frame);
/* 109*/            return;

/* 112*/        case 19: // '\023'
/* 112*/        case 20: // '\024'
/* 112*/            evalLDC(codeiterator.u16bitAt(i + 1), frame);
/* 113*/            return;

/* 115*/        case 21: // '\025'
/* 115*/            evalLoad(Type.INTEGER, codeiterator.byteAt(i + 1), frame, subroutine);
/* 116*/            return;

/* 118*/        case 22: // '\026'
/* 118*/            evalLoad(Type.LONG, codeiterator.byteAt(i + 1), frame, subroutine);
/* 119*/            return;

/* 121*/        case 23: // '\027'
/* 121*/            evalLoad(Type.FLOAT, codeiterator.byteAt(i + 1), frame, subroutine);
/* 122*/            return;

/* 124*/        case 24: // '\030'
/* 124*/            evalLoad(Type.DOUBLE, codeiterator.byteAt(i + 1), frame, subroutine);
/* 125*/            return;

/* 127*/        case 25: // '\031'
/* 127*/            evalLoad(Type.OBJECT, codeiterator.byteAt(i + 1), frame, subroutine);
/* 128*/            return;

/* 133*/        case 26: // '\032'
/* 133*/        case 27: // '\033'
/* 133*/        case 28: // '\034'
/* 133*/        case 29: // '\035'
/* 133*/            evalLoad(Type.INTEGER, j - 26, frame, subroutine);
/* 134*/            return;

/* 139*/        case 30: // '\036'
/* 139*/        case 31: // '\037'
/* 139*/        case 32: // ' '
/* 139*/        case 33: // '!'
/* 139*/            evalLoad(Type.LONG, j - 30, frame, subroutine);
/* 140*/            return;

/* 145*/        case 34: // '"'
/* 145*/        case 35: // '#'
/* 145*/        case 36: // '$'
/* 145*/        case 37: // '%'
/* 145*/            evalLoad(Type.FLOAT, j - 34, frame, subroutine);
/* 146*/            return;

/* 151*/        case 38: // '&'
/* 151*/        case 39: // '\''
/* 151*/        case 40: // '('
/* 151*/        case 41: // ')'
/* 151*/            evalLoad(Type.DOUBLE, j - 38, frame, subroutine);
/* 152*/            return;

/* 157*/        case 42: // '*'
/* 157*/        case 43: // '+'
/* 157*/        case 44: // ','
/* 157*/        case 45: // '-'
/* 157*/            evalLoad(Type.OBJECT, j - 42, frame, subroutine);
/* 158*/            return;

/* 160*/        case 46: // '.'
/* 160*/            evalArrayLoad(Type.INTEGER, frame);
/* 161*/            return;

/* 163*/        case 47: // '/'
/* 163*/            evalArrayLoad(Type.LONG, frame);
/* 164*/            return;

/* 166*/        case 48: // '0'
/* 166*/            evalArrayLoad(Type.FLOAT, frame);
/* 167*/            return;

/* 169*/        case 49: // '1'
/* 169*/            evalArrayLoad(Type.DOUBLE, frame);
/* 170*/            return;

/* 172*/        case 50: // '2'
/* 172*/            evalArrayLoad(Type.OBJECT, frame);
/* 173*/            return;

/* 177*/        case 51: // '3'
/* 177*/        case 52: // '4'
/* 177*/        case 53: // '5'
/* 177*/            evalArrayLoad(Type.INTEGER, frame);
/* 178*/            return;

/* 180*/        case 54: // '6'
/* 180*/            evalStore(Type.INTEGER, codeiterator.byteAt(i + 1), frame, subroutine);
/* 181*/            return;

/* 183*/        case 55: // '7'
/* 183*/            evalStore(Type.LONG, codeiterator.byteAt(i + 1), frame, subroutine);
/* 184*/            return;

/* 186*/        case 56: // '8'
/* 186*/            evalStore(Type.FLOAT, codeiterator.byteAt(i + 1), frame, subroutine);
/* 187*/            return;

/* 189*/        case 57: // '9'
/* 189*/            evalStore(Type.DOUBLE, codeiterator.byteAt(i + 1), frame, subroutine);
/* 190*/            return;

/* 192*/        case 58: // ':'
/* 192*/            evalStore(Type.OBJECT, codeiterator.byteAt(i + 1), frame, subroutine);
/* 193*/            return;

/* 198*/        case 59: // ';'
/* 198*/        case 60: // '<'
/* 198*/        case 61: // '='
/* 198*/        case 62: // '>'
/* 198*/            evalStore(Type.INTEGER, j - 59, frame, subroutine);
/* 199*/            return;

/* 204*/        case 63: // '?'
/* 204*/        case 64: // '@'
/* 204*/        case 65: // 'A'
/* 204*/        case 66: // 'B'
/* 204*/            evalStore(Type.LONG, j - 63, frame, subroutine);
/* 205*/            return;

/* 210*/        case 67: // 'C'
/* 210*/        case 68: // 'D'
/* 210*/        case 69: // 'E'
/* 210*/        case 70: // 'F'
/* 210*/            evalStore(Type.FLOAT, j - 67, frame, subroutine);
/* 211*/            return;

/* 216*/        case 71: // 'G'
/* 216*/        case 72: // 'H'
/* 216*/        case 73: // 'I'
/* 216*/        case 74: // 'J'
/* 216*/            evalStore(Type.DOUBLE, j - 71, frame, subroutine);
/* 217*/            return;

/* 222*/        case 75: // 'K'
/* 222*/        case 76: // 'L'
/* 222*/        case 77: // 'M'
/* 222*/        case 78: // 'N'
/* 222*/            evalStore(Type.OBJECT, j - 75, frame, subroutine);
/* 223*/            return;

/* 225*/        case 79: // 'O'
/* 225*/            evalArrayStore(Type.INTEGER, frame);
/* 226*/            return;

/* 228*/        case 80: // 'P'
/* 228*/            evalArrayStore(Type.LONG, frame);
/* 229*/            return;

/* 231*/        case 81: // 'Q'
/* 231*/            evalArrayStore(Type.FLOAT, frame);
/* 232*/            return;

/* 234*/        case 82: // 'R'
/* 234*/            evalArrayStore(Type.DOUBLE, frame);
/* 235*/            return;

/* 237*/        case 83: // 'S'
/* 237*/            evalArrayStore(Type.OBJECT, frame);
/* 238*/            return;

/* 242*/        case 84: // 'T'
/* 242*/        case 85: // 'U'
/* 242*/        case 86: // 'V'
/* 242*/            evalArrayStore(Type.INTEGER, frame);
/* 243*/            return;

/* 245*/        case 87: // 'W'
/* 245*/            if(frame.pop() == Type.TOP)
/* 246*/                throw new BadBytecode((new StringBuilder("POP can not be used with a category 2 value, pos = ")).append(i).toString());
/* 249*/            break;

/* 249*/        case 88: // 'X'
/* 249*/            frame.pop();
/* 250*/            frame.pop();
/* 251*/            return;

/* 253*/        case 89: // 'Y'
/* 253*/            if((methodinfo = frame.peek()) == Type.TOP)
                    {
/* 255*/                throw new BadBytecode((new StringBuilder("DUP can not be used with a category 2 value, pos = ")).append(i).toString());
                    } else
                    {
/* 257*/                frame.push(frame.peek());
/* 258*/                return;
                    }

/* 262*/        case 90: // 'Z'
/* 262*/        case 91: // '['
/* 262*/            if((methodinfo = frame.peek()) == Type.TOP)
/* 264*/                throw new BadBytecode((new StringBuilder("DUP can not be used with a category 2 value, pos = ")).append(i).toString());
/* 265*/            i = (codeiterator = frame.getTopIndex()) - (j - 90) - 1;
/* 267*/            frame.push(methodinfo);
/* 269*/            for(; codeiterator > i; codeiterator--)
/* 270*/                frame.setStack(codeiterator, frame.getStack(codeiterator - 1));

/* 273*/            frame.setStack(i, methodinfo);
/* 274*/            return;

/* 277*/        case 92: // '\\'
/* 277*/            frame.push(frame.getStack(frame.getTopIndex() - 1));
/* 278*/            frame.push(frame.getStack(frame.getTopIndex() - 1));
/* 279*/            return;

/* 282*/        case 93: // ']'
/* 282*/        case 94: // '^'
/* 282*/            codeiterator = (methodinfo = frame.getTopIndex()) - (j - 93) - 1;
/* 284*/            i = frame.getStack(frame.getTopIndex() - 1);
/* 285*/            subroutine = frame.peek();
/* 286*/            frame.push(i);
/* 287*/            frame.push(subroutine);
/* 288*/            for(; methodinfo > codeiterator; methodinfo--)
/* 289*/                frame.setStack(methodinfo, frame.getStack(methodinfo - 2));

/* 292*/            frame.setStack(codeiterator, subroutine);
/* 293*/            frame.setStack(codeiterator - 1, i);
/* 294*/            return;

/* 297*/        case 95: // '_'
/* 297*/            methodinfo = frame.pop();
/* 298*/            codeiterator = frame.pop();
/* 299*/            if(methodinfo.getSize() == 2 || codeiterator.getSize() == 2)
                    {
/* 300*/                throw new BadBytecode((new StringBuilder("Swap can not be used with category 2 values, pos = ")).append(i).toString());
                    } else
                    {
/* 301*/                frame.push(methodinfo);
/* 302*/                frame.push(codeiterator);
/* 303*/                return;
                    }

/* 308*/        case 96: // '`'
/* 308*/            evalBinaryMath(Type.INTEGER, frame);
/* 309*/            return;

/* 311*/        case 97: // 'a'
/* 311*/            evalBinaryMath(Type.LONG, frame);
/* 312*/            return;

/* 314*/        case 98: // 'b'
/* 314*/            evalBinaryMath(Type.FLOAT, frame);
/* 315*/            return;

/* 317*/        case 99: // 'c'
/* 317*/            evalBinaryMath(Type.DOUBLE, frame);
/* 318*/            return;

/* 320*/        case 100: // 'd'
/* 320*/            evalBinaryMath(Type.INTEGER, frame);
/* 321*/            return;

/* 323*/        case 101: // 'e'
/* 323*/            evalBinaryMath(Type.LONG, frame);
/* 324*/            return;

/* 326*/        case 102: // 'f'
/* 326*/            evalBinaryMath(Type.FLOAT, frame);
/* 327*/            return;

/* 329*/        case 103: // 'g'
/* 329*/            evalBinaryMath(Type.DOUBLE, frame);
/* 330*/            return;

/* 332*/        case 104: // 'h'
/* 332*/            evalBinaryMath(Type.INTEGER, frame);
/* 333*/            return;

/* 335*/        case 105: // 'i'
/* 335*/            evalBinaryMath(Type.LONG, frame);
/* 336*/            return;

/* 338*/        case 106: // 'j'
/* 338*/            evalBinaryMath(Type.FLOAT, frame);
/* 339*/            return;

/* 341*/        case 107: // 'k'
/* 341*/            evalBinaryMath(Type.DOUBLE, frame);
/* 342*/            return;

/* 344*/        case 108: // 'l'
/* 344*/            evalBinaryMath(Type.INTEGER, frame);
/* 345*/            return;

/* 347*/        case 109: // 'm'
/* 347*/            evalBinaryMath(Type.LONG, frame);
/* 348*/            return;

/* 350*/        case 110: // 'n'
/* 350*/            evalBinaryMath(Type.FLOAT, frame);
/* 351*/            return;

/* 353*/        case 111: // 'o'
/* 353*/            evalBinaryMath(Type.DOUBLE, frame);
/* 354*/            return;

/* 356*/        case 112: // 'p'
/* 356*/            evalBinaryMath(Type.INTEGER, frame);
/* 357*/            return;

/* 359*/        case 113: // 'q'
/* 359*/            evalBinaryMath(Type.LONG, frame);
/* 360*/            return;

/* 362*/        case 114: // 'r'
/* 362*/            evalBinaryMath(Type.FLOAT, frame);
/* 363*/            return;

/* 365*/        case 115: // 's'
/* 365*/            evalBinaryMath(Type.DOUBLE, frame);
/* 366*/            return;

/* 370*/        case 116: // 't'
/* 370*/            verifyAssignable(Type.INTEGER, simplePeek(frame));
/* 371*/            return;

/* 373*/        case 117: // 'u'
/* 373*/            verifyAssignable(Type.LONG, simplePeek(frame));
/* 374*/            return;

/* 376*/        case 118: // 'v'
/* 376*/            verifyAssignable(Type.FLOAT, simplePeek(frame));
/* 377*/            return;

/* 379*/        case 119: // 'w'
/* 379*/            verifyAssignable(Type.DOUBLE, simplePeek(frame));
/* 380*/            return;

/* 384*/        case 120: // 'x'
/* 384*/            evalShift(Type.INTEGER, frame);
/* 385*/            return;

/* 387*/        case 121: // 'y'
/* 387*/            evalShift(Type.LONG, frame);
/* 388*/            return;

/* 390*/        case 122: // 'z'
/* 390*/            evalShift(Type.INTEGER, frame);
/* 391*/            return;

/* 393*/        case 123: // '{'
/* 393*/            evalShift(Type.LONG, frame);
/* 394*/            return;

/* 396*/        case 124: // '|'
/* 396*/            evalShift(Type.INTEGER, frame);
/* 397*/            return;

/* 399*/        case 125: // '}'
/* 399*/            evalShift(Type.LONG, frame);
/* 400*/            return;

/* 404*/        case 126: // '~'
/* 404*/            evalBinaryMath(Type.INTEGER, frame);
/* 405*/            return;

/* 407*/        case 127: // '\177'
/* 407*/            evalBinaryMath(Type.LONG, frame);
/* 408*/            return;

/* 410*/        case 128: 
/* 410*/            evalBinaryMath(Type.INTEGER, frame);
/* 411*/            return;

/* 413*/        case 129: 
/* 413*/            evalBinaryMath(Type.LONG, frame);
/* 414*/            return;

/* 416*/        case 130: 
/* 416*/            evalBinaryMath(Type.INTEGER, frame);
/* 417*/            return;

/* 419*/        case 131: 
/* 419*/            evalBinaryMath(Type.LONG, frame);
/* 420*/            return;

/* 423*/        case 132: 
/* 423*/            methodinfo = codeiterator.byteAt(i + 1);
/* 424*/            verifyAssignable(Type.INTEGER, frame.getLocal(methodinfo));
/* 425*/            access(methodinfo, Type.INTEGER, subroutine);
/* 426*/            return;

/* 431*/        case 133: 
/* 431*/            verifyAssignable(Type.INTEGER, simplePop(frame));
/* 432*/            simplePush(Type.LONG, frame);
/* 433*/            return;

/* 435*/        case 134: 
/* 435*/            verifyAssignable(Type.INTEGER, simplePop(frame));
/* 436*/            simplePush(Type.FLOAT, frame);
/* 437*/            return;

/* 439*/        case 135: 
/* 439*/            verifyAssignable(Type.INTEGER, simplePop(frame));
/* 440*/            simplePush(Type.DOUBLE, frame);
/* 441*/            return;

/* 443*/        case 136: 
/* 443*/            verifyAssignable(Type.LONG, simplePop(frame));
/* 444*/            simplePush(Type.INTEGER, frame);
/* 445*/            return;

/* 447*/        case 137: 
/* 447*/            verifyAssignable(Type.LONG, simplePop(frame));
/* 448*/            simplePush(Type.FLOAT, frame);
/* 449*/            return;

/* 451*/        case 138: 
/* 451*/            verifyAssignable(Type.LONG, simplePop(frame));
/* 452*/            simplePush(Type.DOUBLE, frame);
/* 453*/            return;

/* 455*/        case 139: 
/* 455*/            verifyAssignable(Type.FLOAT, simplePop(frame));
/* 456*/            simplePush(Type.INTEGER, frame);
/* 457*/            return;

/* 459*/        case 140: 
/* 459*/            verifyAssignable(Type.FLOAT, simplePop(frame));
/* 460*/            simplePush(Type.LONG, frame);
/* 461*/            return;

/* 463*/        case 141: 
/* 463*/            verifyAssignable(Type.FLOAT, simplePop(frame));
/* 464*/            simplePush(Type.DOUBLE, frame);
/* 465*/            return;

/* 467*/        case 142: 
/* 467*/            verifyAssignable(Type.DOUBLE, simplePop(frame));
/* 468*/            simplePush(Type.INTEGER, frame);
/* 469*/            return;

/* 471*/        case 143: 
/* 471*/            verifyAssignable(Type.DOUBLE, simplePop(frame));
/* 472*/            simplePush(Type.LONG, frame);
/* 473*/            return;

/* 475*/        case 144: 
/* 475*/            verifyAssignable(Type.DOUBLE, simplePop(frame));
/* 476*/            simplePush(Type.FLOAT, frame);
/* 477*/            return;

/* 481*/        case 145: 
/* 481*/        case 146: 
/* 481*/        case 147: 
/* 481*/            verifyAssignable(Type.INTEGER, frame.peek());
/* 482*/            return;

/* 484*/        case 148: 
/* 484*/            verifyAssignable(Type.LONG, simplePop(frame));
/* 485*/            verifyAssignable(Type.LONG, simplePop(frame));
/* 486*/            frame.push(Type.INTEGER);
/* 487*/            return;

/* 490*/        case 149: 
/* 490*/        case 150: 
/* 490*/            verifyAssignable(Type.FLOAT, simplePop(frame));
/* 491*/            verifyAssignable(Type.FLOAT, simplePop(frame));
/* 492*/            frame.push(Type.INTEGER);
/* 493*/            return;

/* 496*/        case 151: 
/* 496*/        case 152: 
/* 496*/            verifyAssignable(Type.DOUBLE, simplePop(frame));
/* 497*/            verifyAssignable(Type.DOUBLE, simplePop(frame));
/* 498*/            frame.push(Type.INTEGER);
/* 499*/            return;

/* 508*/        case 153: 
/* 508*/        case 154: 
/* 508*/        case 155: 
/* 508*/        case 156: 
/* 508*/        case 157: 
/* 508*/        case 158: 
/* 508*/            verifyAssignable(Type.INTEGER, simplePop(frame));
/* 509*/            return;

/* 516*/        case 159: 
/* 516*/        case 160: 
/* 516*/        case 161: 
/* 516*/        case 162: 
/* 516*/        case 163: 
/* 516*/        case 164: 
/* 516*/            verifyAssignable(Type.INTEGER, simplePop(frame));
/* 517*/            verifyAssignable(Type.INTEGER, simplePop(frame));
/* 518*/            return;

/* 521*/        case 165: 
/* 521*/        case 166: 
/* 521*/            verifyAssignable(Type.OBJECT, simplePop(frame));
/* 522*/            verifyAssignable(Type.OBJECT, simplePop(frame));
/* 523*/            return;

/* 525*/        case 167: 
/* 525*/            return;

/* 527*/        case 168: 
/* 527*/            frame.push(Type.RETURN_ADDRESS);
/* 528*/            return;

/* 530*/        case 169: 
/* 530*/            verifyAssignable(Type.RETURN_ADDRESS, frame.getLocal(codeiterator.byteAt(i + 1)));
/* 531*/            return;

/* 535*/        case 170: 
/* 535*/        case 171: 
/* 535*/        case 172: 
/* 535*/            verifyAssignable(Type.INTEGER, simplePop(frame));
/* 536*/            return;

/* 538*/        case 173: 
/* 538*/            verifyAssignable(Type.LONG, simplePop(frame));
/* 539*/            return;

/* 541*/        case 174: 
/* 541*/            verifyAssignable(Type.FLOAT, simplePop(frame));
/* 542*/            return;

/* 544*/        case 175: 
/* 544*/            verifyAssignable(Type.DOUBLE, simplePop(frame));
/* 545*/            return;

/* 548*/        case 176: 
/* 548*/            try
                    {
/* 548*/                methodinfo = Descriptor.getReturnType(methodinfo.getDescriptor(), classPool);
/* 549*/                verifyAssignable(Type.get(methodinfo), simplePop(frame));
/* 552*/                return;
                    }
                    // Misplaced declaration of an exception variable
/* 550*/            catch(MethodInfo methodinfo)
                    {
/* 551*/                throw new RuntimeException(methodinfo);
                    }

/* 555*/        case 177: 
/* 555*/            return;

/* 557*/        case 178: 
/* 557*/            evalGetField(j, codeiterator.u16bitAt(i + 1), frame);
/* 558*/            return;

/* 560*/        case 179: 
/* 560*/            evalPutField(j, codeiterator.u16bitAt(i + 1), frame);
/* 561*/            return;

/* 563*/        case 180: 
/* 563*/            evalGetField(j, codeiterator.u16bitAt(i + 1), frame);
/* 564*/            return;

/* 566*/        case 181: 
/* 566*/            evalPutField(j, codeiterator.u16bitAt(i + 1), frame);
/* 567*/            return;

/* 571*/        case 182: 
/* 571*/        case 183: 
/* 571*/        case 184: 
/* 571*/            evalInvokeMethod(j, codeiterator.u16bitAt(i + 1), frame);
/* 572*/            return;

/* 574*/        case 185: 
/* 574*/            evalInvokeIntfMethod(j, codeiterator.u16bitAt(i + 1), frame);
/* 575*/            return;

/* 577*/        case 186: 
/* 577*/            evalInvokeDynamic(j, codeiterator.u16bitAt(i + 1), frame);
/* 578*/            return;

/* 580*/        case 187: 
/* 580*/            frame.push(resolveClassInfo(constPool.getClassInfo(codeiterator.u16bitAt(i + 1))));
/* 581*/            return;

/* 583*/        case 188: 
/* 583*/            evalNewArray(i, codeiterator, frame);
/* 584*/            return;

/* 586*/        case 189: 
/* 586*/            evalNewObjectArray(i, codeiterator, frame);
/* 587*/            return;

/* 589*/        case 190: 
/* 589*/            if(!(methodinfo = simplePop(frame)).isArray() && methodinfo != Type.UNINIT)
                    {
/* 591*/                throw new BadBytecode((new StringBuilder("Array length passed a non-array [pos = ")).append(i).append("]: ").append(methodinfo).toString());
                    } else
                    {
/* 592*/                frame.push(Type.INTEGER);
/* 593*/                return;
                    }

/* 596*/        case 191: 
/* 596*/            verifyAssignable(THROWABLE_TYPE, simplePop(frame));
/* 597*/            return;

/* 599*/        case 192: 
/* 599*/            verifyAssignable(Type.OBJECT, simplePop(frame));
/* 600*/            frame.push(typeFromDesc(constPool.getClassInfoByDescriptor(codeiterator.u16bitAt(i + 1))));
/* 601*/            return;

/* 603*/        case 193: 
/* 603*/            verifyAssignable(Type.OBJECT, simplePop(frame));
/* 604*/            frame.push(Type.INTEGER);
/* 605*/            return;

/* 608*/        case 194: 
/* 608*/        case 195: 
/* 608*/            verifyAssignable(Type.OBJECT, simplePop(frame));
/* 609*/            return;

/* 611*/        case 196: 
/* 611*/            evalWide(i, codeiterator, frame, subroutine);
/* 612*/            return;

/* 614*/        case 197: 
/* 614*/            evalNewObjectArray(i, codeiterator, frame);
/* 615*/            return;

/* 618*/        case 198: 
/* 618*/        case 199: 
/* 618*/            verifyAssignable(Type.OBJECT, simplePop(frame));
/* 619*/            return;

/* 621*/        case 200: 
/* 621*/            return;

/* 623*/        case 201: 
/* 623*/            frame.push(Type.RETURN_ADDRESS);
                    break;
                }
            }

            private Type zeroExtend(Type type)
            {
/* 629*/        if(type == Type.SHORT || type == Type.BYTE || type == Type.CHAR || type == Type.BOOLEAN)
/* 630*/            return Type.INTEGER;
/* 632*/        else
/* 632*/            return type;
            }

            private void evalArrayLoad(Type type, Frame frame)
                throws BadBytecode
            {
/* 636*/        Type type1 = frame.pop();
                Type type2;
/* 637*/        if((type2 = frame.pop()) == Type.UNINIT)
                {
/* 642*/            verifyAssignable(Type.INTEGER, type1);
/* 643*/            if(type == Type.OBJECT)
                    {
/* 644*/                simplePush(Type.UNINIT, frame);
/* 644*/                return;
                    } else
                    {
/* 646*/                simplePush(type, frame);
/* 648*/                return;
                    }
                }
/* 651*/        if((type2 = type2.getComponent()) == null)
                {
/* 654*/            throw new BadBytecode((new StringBuilder("Not an array! [pos = ")).append(lastPos).append("]: ").append(type2).toString());
                } else
                {
/* 656*/            type2 = zeroExtend(type2);
/* 658*/            verifyAssignable(type, type2);
/* 659*/            verifyAssignable(Type.INTEGER, type1);
/* 660*/            simplePush(type2, frame);
/* 661*/            return;
                }
            }

            private void evalArrayStore(Type type, Frame frame)
                throws BadBytecode
            {
/* 664*/        Type type1 = simplePop(frame);
/* 665*/        Type type2 = frame.pop();
/* 666*/        if((frame = frame.pop()) == Type.UNINIT)
                {
/* 669*/            verifyAssignable(Type.INTEGER, type2);
/* 670*/            return;
                }
/* 673*/        if((frame = frame.getComponent()) == null)
/* 676*/            throw new BadBytecode((new StringBuilder("Not an array! [pos = ")).append(lastPos).append("]: ").append(frame).toString());
/* 678*/        frame = zeroExtend(frame);
/* 680*/        verifyAssignable(type, frame);
/* 681*/        verifyAssignable(Type.INTEGER, type2);
/* 689*/        if(type == Type.OBJECT)
                {
/* 690*/            verifyAssignable(type, type1);
/* 690*/            return;
                } else
                {
/* 692*/            verifyAssignable(frame, type1);
/* 694*/            return;
                }
            }

            private void evalBinaryMath(Type type, Frame frame)
                throws BadBytecode
            {
/* 697*/        Type type1 = simplePop(frame);
/* 698*/        Type type2 = simplePop(frame);
/* 700*/        verifyAssignable(type, type1);
/* 701*/        verifyAssignable(type, type2);
/* 702*/        simplePush(type2, frame);
            }

            private void evalGetField(int i, int j, Frame frame)
                throws BadBytecode
            {
/* 706*/        Object obj = constPool.getFieldrefType(j);
/* 707*/        obj = zeroExtend(typeFromDesc(((String) (obj))));
/* 709*/        if(i == 180)
                {
/* 710*/            i = resolveClassInfo(constPool.getFieldrefClassName(j));
/* 711*/            verifyAssignable(i, simplePop(frame));
                }
/* 714*/        simplePush(((Type) (obj)), frame);
            }

            private void evalInvokeIntfMethod(int i, int j, Frame frame)
                throws BadBytecode
            {
/* 718*/        i = constPool.getInterfaceMethodrefType(j);
                Type atype[];
/* 719*/        for(int k = (atype = paramTypesFromDesc(i)).length; k > 0;)
/* 723*/            verifyAssignable(zeroExtend(atype[--k]), simplePop(frame));

/* 725*/        j = constPool.getInterfaceMethodrefClassName(j);
/* 726*/        j = resolveClassInfo(j);
/* 727*/        verifyAssignable(j, simplePop(frame));
/* 729*/        if((i = returnTypeFromDesc(i)) != Type.VOID)
/* 731*/            simplePush(zeroExtend(i), frame);
            }

            private void evalInvokeMethod(int i, int j, Frame frame)
                throws BadBytecode
            {
/* 735*/        String s = constPool.getMethodrefType(j);
                Type atype[];
/* 736*/        for(int k = (atype = paramTypesFromDesc(s)).length; k > 0;)
/* 740*/            verifyAssignable(zeroExtend(atype[--k]), simplePop(frame));

/* 742*/        if(i != 184)
                {
/* 743*/            i = resolveClassInfo(constPool.getMethodrefClassName(j));
/* 744*/            verifyAssignable(i, simplePop(frame));
                }
/* 747*/        if((i = returnTypeFromDesc(s)) != Type.VOID)
/* 749*/            simplePush(zeroExtend(i), frame);
            }

            private void evalInvokeDynamic(int i, int j, Frame frame)
                throws BadBytecode
            {
/* 753*/        i = constPool.getInvokeDynamicType(j);
/* 754*/        for(int k = (j = paramTypesFromDesc(i)).length; k > 0;)
/* 758*/            verifyAssignable(zeroExtend(j[--k]), simplePop(frame));

/* 762*/        if((i = returnTypeFromDesc(i)) != Type.VOID)
/* 764*/            simplePush(zeroExtend(i), frame);
            }

            private void evalLDC(int i, Frame frame)
                throws BadBytecode
            {
/* 768*/        switch(i = constPool.getTag(i))
                {
/* 772*/        case 8: // '\b'
/* 772*/            i = STRING_TYPE;
                    break;

/* 775*/        case 3: // '\003'
/* 775*/            i = Type.INTEGER;
                    break;

/* 778*/        case 4: // '\004'
/* 778*/            i = Type.FLOAT;
                    break;

/* 781*/        case 5: // '\005'
/* 781*/            i = Type.LONG;
                    break;

/* 784*/        case 6: // '\006'
/* 784*/            i = Type.DOUBLE;
                    break;

/* 787*/        case 7: // '\007'
/* 787*/            i = CLASS_TYPE;
                    break;

/* 790*/        default:
/* 790*/            throw new BadBytecode((new StringBuilder("bad LDC [pos = ")).append(lastPos).append("]: ").append(i).toString());
                }
/* 793*/        simplePush(i, frame);
            }

            private void evalLoad(Type type, int i, Frame frame, Subroutine subroutine)
                throws BadBytecode
            {
/* 797*/        Type type1 = frame.getLocal(i);
/* 799*/        verifyAssignable(type, type1);
/* 801*/        simplePush(type1, frame);
/* 802*/        access(i, type1, subroutine);
            }

            private void evalNewArray(int i, CodeIterator codeiterator, Frame frame)
                throws BadBytecode
            {
/* 806*/        verifyAssignable(Type.INTEGER, simplePop(frame));
/* 808*/        switch(codeiterator = codeiterator.byteAt(i + 1))
                {
/* 811*/        case 4: // '\004'
/* 811*/            i = getType("boolean[]");
                    break;

/* 814*/        case 5: // '\005'
/* 814*/            i = getType("char[]");
                    break;

/* 817*/        case 8: // '\b'
/* 817*/            i = getType("byte[]");
                    break;

/* 820*/        case 9: // '\t'
/* 820*/            i = getType("short[]");
                    break;

/* 823*/        case 10: // '\n'
/* 823*/            i = getType("int[]");
                    break;

/* 826*/        case 11: // '\013'
/* 826*/            i = getType("long[]");
                    break;

/* 829*/        case 6: // '\006'
/* 829*/            i = getType("float[]");
                    break;

/* 832*/        case 7: // '\007'
/* 832*/            i = getType("double[]");
                    break;

/* 835*/        default:
/* 835*/            throw new BadBytecode((new StringBuilder("Invalid array type [pos = ")).append(i).append("]: ").append(codeiterator).toString());
                }
/* 839*/        frame.push(i);
            }

            private void evalNewObjectArray(int i, CodeIterator codeiterator, Frame frame)
                throws BadBytecode
            {
                Object obj;
/* 844*/        obj = ((Type) (obj = resolveClassInfo(constPool.getClassInfo(codeiterator.u16bitAt(i + 1))))).getCtClass().getName();
                int j;
/* 846*/        if((j = codeiterator.byteAt(i)) == 197)
                {
/* 850*/            i = codeiterator.byteAt(i + 3);
                } else
                {
/* 852*/            obj = (new StringBuilder()).append(((String) (obj))).append("[]").toString();
/* 853*/            i = 1;
                }
/* 856*/        while(i-- > 0) 
/* 857*/            verifyAssignable(Type.INTEGER, simplePop(frame));
/* 860*/        simplePush(getType(((String) (obj))), frame);
            }

            private void evalPutField(int i, int j, Frame frame)
                throws BadBytecode
            {
/* 864*/        Object obj = constPool.getFieldrefType(j);
/* 865*/        obj = zeroExtend(typeFromDesc(((String) (obj))));
/* 867*/        verifyAssignable(((Type) (obj)), simplePop(frame));
/* 869*/        if(i == 181)
                {
/* 870*/            i = resolveClassInfo(constPool.getFieldrefClassName(j));
/* 871*/            verifyAssignable(i, simplePop(frame));
                }
            }

            private void evalShift(Type type, Frame frame)
                throws BadBytecode
            {
/* 876*/        Type type1 = simplePop(frame);
/* 877*/        Type type2 = simplePop(frame);
/* 879*/        verifyAssignable(Type.INTEGER, type1);
/* 880*/        verifyAssignable(type, type2);
/* 881*/        simplePush(type2, frame);
            }

            private void evalStore(Type type, int i, Frame frame, Subroutine subroutine)
                throws BadBytecode
            {
/* 885*/        Type type1 = simplePop(frame);
/* 888*/        if(type != Type.OBJECT || type1 != Type.RETURN_ADDRESS)
/* 889*/            verifyAssignable(type, type1);
/* 890*/        simpleSetLocal(i, type1, frame);
/* 891*/        access(i, type1, subroutine);
            }

            private void evalWide(int i, CodeIterator codeiterator, Frame frame, Subroutine subroutine)
                throws BadBytecode
            {
/* 895*/        int j = codeiterator.byteAt(i + 1);
/* 896*/        codeiterator = codeiterator.u16bitAt(i + 2);
/* 897*/        switch(j)
                {
/* 899*/        case 21: // '\025'
/* 899*/            evalLoad(Type.INTEGER, codeiterator, frame, subroutine);
/* 900*/            return;

/* 902*/        case 22: // '\026'
/* 902*/            evalLoad(Type.LONG, codeiterator, frame, subroutine);
/* 903*/            return;

/* 905*/        case 23: // '\027'
/* 905*/            evalLoad(Type.FLOAT, codeiterator, frame, subroutine);
/* 906*/            return;

/* 908*/        case 24: // '\030'
/* 908*/            evalLoad(Type.DOUBLE, codeiterator, frame, subroutine);
/* 909*/            return;

/* 911*/        case 25: // '\031'
/* 911*/            evalLoad(Type.OBJECT, codeiterator, frame, subroutine);
/* 912*/            return;

/* 914*/        case 54: // '6'
/* 914*/            evalStore(Type.INTEGER, codeiterator, frame, subroutine);
/* 915*/            return;

/* 917*/        case 55: // '7'
/* 917*/            evalStore(Type.LONG, codeiterator, frame, subroutine);
/* 918*/            return;

/* 920*/        case 56: // '8'
/* 920*/            evalStore(Type.FLOAT, codeiterator, frame, subroutine);
/* 921*/            return;

/* 923*/        case 57: // '9'
/* 923*/            evalStore(Type.DOUBLE, codeiterator, frame, subroutine);
/* 924*/            return;

/* 926*/        case 58: // ':'
/* 926*/            evalStore(Type.OBJECT, codeiterator, frame, subroutine);
/* 927*/            return;

/* 929*/        case 132: 
/* 929*/            verifyAssignable(Type.INTEGER, frame.getLocal(codeiterator));
/* 930*/            return;

/* 932*/        case 169: 
/* 932*/            verifyAssignable(Type.RETURN_ADDRESS, frame.getLocal(codeiterator));
/* 933*/            return;
                }
/* 935*/        throw new BadBytecode((new StringBuilder("Invalid WIDE operand [pos = ")).append(i).append("]: ").append(j).toString());
            }

            private Type getType(String s)
                throws BadBytecode
            {
/* 942*/        return Type.get(classPool.get(s));
/* 943*/        JVM INSTR pop ;
/* 944*/        throw new BadBytecode((new StringBuilder("Could not find class [pos = ")).append(lastPos).append("]: ").append(s).toString());
            }

            private Type[] paramTypesFromDesc(String s)
                throws BadBytecode
            {
                CtClass actclass[];
/* 951*/        try
                {
/* 951*/            actclass = Descriptor.getParameterTypes(s, classPool);
                }
                // Misplaced declaration of an exception variable
/* 952*/        catch(String s)
                {
/* 953*/            throw new BadBytecode((new StringBuilder("Could not find class in descriptor [pos = ")).append(lastPos).append("]: ").append(s.getMessage()).toString());
                }
/* 956*/        if(actclass == null)
/* 957*/            throw new BadBytecode((new StringBuilder("Could not obtain parameters for descriptor [pos = ")).append(lastPos).append("]: ").append(s).toString());
/* 959*/        s = new Type[actclass.length];
/* 960*/        for(int i = 0; i < s.length; i++)
/* 961*/            s[i] = Type.get(actclass[i]);

/* 963*/        return s;
            }

            private Type returnTypeFromDesc(String s)
                throws BadBytecode
            {
                CtClass ctclass;
/* 969*/        try
                {
/* 969*/            ctclass = Descriptor.getReturnType(s, classPool);
                }
                // Misplaced declaration of an exception variable
/* 970*/        catch(String s)
                {
/* 971*/            throw new BadBytecode((new StringBuilder("Could not find class in descriptor [pos = ")).append(lastPos).append("]: ").append(s.getMessage()).toString());
                }
/* 974*/        if(ctclass == null)
/* 975*/            throw new BadBytecode((new StringBuilder("Could not obtain return type for descriptor [pos = ")).append(lastPos).append("]: ").append(s).toString());
/* 977*/        else
/* 977*/            return Type.get(ctclass);
            }

            private Type simplePeek(Frame frame)
            {
                Type type;
/* 981*/        if((type = frame.peek()) == Type.TOP)
/* 982*/            return frame.getStack(frame.getTopIndex() - 1);
/* 982*/        else
/* 982*/            return type;
            }

            private Type simplePop(Frame frame)
            {
                Type type;
/* 986*/        if((type = frame.pop()) == Type.TOP)
/* 987*/            return frame.pop();
/* 987*/        else
/* 987*/            return type;
            }

            private void simplePush(Type type, Frame frame)
            {
/* 991*/        frame.push(type);
/* 992*/        if(type.getSize() == 2)
/* 993*/            frame.push(Type.TOP);
            }

            private void access(int i, Type type, Subroutine subroutine)
            {
/* 997*/        if(subroutine == null)
/* 998*/            return;
/* 999*/        subroutine.access(i);
/*1000*/        if(type.getSize() == 2)
/*1001*/            subroutine.access(i + 1);
            }

            private void simpleSetLocal(int i, Type type, Frame frame)
            {
/*1005*/        frame.setLocal(i, type);
/*1006*/        if(type.getSize() == 2)
/*1007*/            frame.setLocal(i + 1, Type.TOP);
            }

            private Type resolveClassInfo(String s)
                throws BadBytecode
            {
                CtClass ctclass;
/*1013*/        try
                {
/*1013*/            if(s.charAt(0) == '[')
/*1014*/                ctclass = Descriptor.toCtClass(s, classPool);
/*1016*/            else
/*1016*/                ctclass = classPool.get(s);
                }
                // Misplaced declaration of an exception variable
/*1019*/        catch(String s)
                {
/*1020*/            throw new BadBytecode((new StringBuilder("Could not find class in descriptor [pos = ")).append(lastPos).append("]: ").append(s.getMessage()).toString());
                }
/*1023*/        if(ctclass == null)
/*1024*/            throw new BadBytecode((new StringBuilder("Could not obtain type for descriptor [pos = ")).append(lastPos).append("]: ").append(s).toString());
/*1026*/        else
/*1026*/            return Type.get(ctclass);
            }

            private Type typeFromDesc(String s)
                throws BadBytecode
            {
                CtClass ctclass;
/*1032*/        try
                {
/*1032*/            ctclass = Descriptor.toCtClass(s, classPool);
                }
                // Misplaced declaration of an exception variable
/*1033*/        catch(String s)
                {
/*1034*/            throw new BadBytecode((new StringBuilder("Could not find class in descriptor [pos = ")).append(lastPos).append("]: ").append(s.getMessage()).toString());
                }
/*1037*/        if(ctclass == null)
/*1038*/            throw new BadBytecode((new StringBuilder("Could not obtain type for descriptor [pos = ")).append(lastPos).append("]: ").append(s).toString());
/*1040*/        else
/*1040*/            return Type.get(ctclass);
            }

            private void verifyAssignable(Type type, Type type1)
                throws BadBytecode
            {
/*1044*/        if(!type.isAssignableFrom(type1))
/*1045*/            throw new BadBytecode((new StringBuilder("Expected type: ")).append(type).append(" Got: ").append(type1).append(" [pos = ").append(lastPos).append("]").toString());
/*1046*/        else
/*1046*/            return;
            }

            private final ConstPool constPool;
            private final ClassPool classPool;
            private final Type STRING_TYPE;
            private final Type CLASS_TYPE;
            private final Type THROWABLE_TYPE;
            private int lastPos;
}
