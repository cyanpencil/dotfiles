// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 

package com.owlike.genson.internal.asm;


// Referenced classes of package com.owlike.genson.internal.asm:
//            ClassWriter, Item, Label, Type

final class Frame
{

    Frame()
    {
    }

    private int a(int j)
    {
        if(e == null || j >= e.length)
            return 0x2000000 | j;
        int k;
        if((k = e[j]) == 0)
            k = e[j] = 0x2000000 | j;
        return k;
    }

    private void a(int j, int k)
    {
        if(e == null)
            e = new int[10];
        int l = e.length;
        if(j >= l)
        {
            int ai[] = new int[Math.max(j + 1, 2 * l)];
            System.arraycopy(e, 0, ai, 0, l);
            e = ai;
        }
        e[j] = k;
    }

    private void b(int j)
    {
        if(f == null)
            f = new int[10];
        int k = f.length;
        if(g >= k)
        {
            int ai[] = new int[Math.max(g + 1, 2 * k)];
            System.arraycopy(f, 0, ai, 0, k);
            f = ai;
        }
        f[g++] = j;
        int l;
        if((l = b.f + g) > b.g)
            b.g = l;
    }

    private void a(ClassWriter classwriter, String s)
    {
        if((classwriter = b(classwriter, s)) != 0)
        {
            b(classwriter);
            if(classwriter == 0x1000004 || classwriter == 0x1000003)
                b(0x1000000);
        }
    }

    private static int b(ClassWriter classwriter, String s)
    {
        int j = s.charAt(0) != '(' ? 0 : s.indexOf(')') + 1;
        int k;
        switch(s.charAt(j))
        {
        case 86: // 'V'
            return 0;

        case 66: // 'B'
        case 67: // 'C'
        case 73: // 'I'
        case 83: // 'S'
        case 90: // 'Z'
            return 0x1000001;

        case 70: // 'F'
            return 0x1000002;

        case 74: // 'J'
            return 0x1000004;

        case 68: // 'D'
            return 0x1000003;

        case 76: // 'L'
            s = s.substring(j + 1, s.length() - 1);
            return 0x1700000 | classwriter.c(s);

        case 69: // 'E'
        case 71: // 'G'
        case 72: // 'H'
        case 75: // 'K'
        case 77: // 'M'
        case 78: // 'N'
        case 79: // 'O'
        case 80: // 'P'
        case 81: // 'Q'
        case 82: // 'R'
        case 84: // 'T'
        case 85: // 'U'
        case 87: // 'W'
        case 88: // 'X'
        case 89: // 'Y'
        default:
            k = j + 1;
            break;
        }
        for(; s.charAt(k) == '['; k++);
        switch(s.charAt(k))
        {
        case 90: // 'Z'
            classwriter = 0x1000009;
            break;

        case 67: // 'C'
            classwriter = 0x100000b;
            break;

        case 66: // 'B'
            classwriter = 0x100000a;
            break;

        case 83: // 'S'
            classwriter = 0x100000c;
            break;

        case 73: // 'I'
            classwriter = 0x1000001;
            break;

        case 70: // 'F'
            classwriter = 0x1000002;
            break;

        case 74: // 'J'
            classwriter = 0x1000004;
            break;

        case 68: // 'D'
            classwriter = 0x1000003;
            break;

        case 69: // 'E'
        case 71: // 'G'
        case 72: // 'H'
        case 75: // 'K'
        case 76: // 'L'
        case 77: // 'M'
        case 78: // 'N'
        case 79: // 'O'
        case 80: // 'P'
        case 81: // 'Q'
        case 82: // 'R'
        case 84: // 'T'
        case 85: // 'U'
        case 86: // 'V'
        case 87: // 'W'
        case 88: // 'X'
        case 89: // 'Y'
        default:
            s = s.substring(k + 1, s.length() - 1);
            classwriter = 0x1700000 | classwriter.c(s);
            break;
        }
        return k - j << 28 | classwriter;
    }

    private int a()
    {
        if(g > 0)
            return f[--g];
        else
            return 0x3000000 | ---b.f;
    }

    private void c(int j)
    {
        if(g >= j)
        {
            g -= j;
            return;
        } else
        {
            b.f -= j - g;
            g = 0;
            return;
        }
    }

    private void a(String s)
    {
        char c1;
        if((c1 = s.charAt(0)) == '(')
        {
            c((Type.getArgumentsAndReturnSizes(s) >> 2) - 1);
            return;
        }
        if(c1 == 'J' || c1 == 'D')
        {
            c(2);
            return;
        } else
        {
            c(1);
            return;
        }
    }

    private void d(int j)
    {
        if(i == null)
            i = new int[2];
        int k = i.length;
        if(h >= k)
        {
            int ai[] = new int[Math.max(h + 1, 2 * k)];
            System.arraycopy(i, 0, ai, 0, k);
            i = ai;
        }
        i[h++] = j;
    }

    private int a(ClassWriter classwriter, int j)
    {
        if(j == 0x1000006)
            classwriter = 0x1700000 | classwriter.c(classwriter.I);
        else
        if((j & 0xfff00000) == 0x1800000)
        {
            String s = classwriter.H[j & 0xfffff].g;
            classwriter = 0x1700000 | classwriter.c(s);
        } else
        {
            return j;
        }
        for(int k = 0; k < h; k++)
        {
            int l;
            int i1 = (l = i[k]) & 0xf0000000;
            int j1;
            if((j1 = l & 0xf000000) == 0x2000000)
                l = i1 + c[l & 0x7fffff];
            else
            if(j1 == 0x3000000)
                l = i1 + d[d.length - (l & 0x7fffff)];
            if(j == l)
                return classwriter;
        }

        return j;
    }

    final void a(ClassWriter classwriter, int j, Type atype[], int k)
    {
        c = new int[k];
        d = new int[0];
        int l = 0;
        if((j & 8) == 0)
            if((j & 0x80000) == 0)
            {
                l++;
                c[0] = 0x1700000 | classwriter.c(classwriter.I);
            } else
            {
                l++;
                c[0] = 0x1000006;
            }
        for(j = 0; j < atype.length; j++)
        {
            int i1 = b(classwriter, atype[j].getDescriptor());
            c[l++] = i1;
            if(i1 == 0x1000004 || i1 == 0x1000003)
                c[l++] = 0x1000000;
        }

        while(l < k) 
            c[l++] = 0x1000000;
    }

    final void a(int j, int k, ClassWriter classwriter, Item item)
    {
        switch(j)
        {
        case 0: // '\0'
        case 116: // 't'
        case 117: // 'u'
        case 118: // 'v'
        case 119: // 'w'
        case 145: 
        case 146: 
        case 147: 
        case 167: 
        case 177: 
            return;

        case 1: // '\001'
            b(0x1000005);
            return;

        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 16: // '\020'
        case 17: // '\021'
        case 21: // '\025'
            b(0x1000001);
            return;

        case 9: // '\t'
        case 10: // '\n'
        case 22: // '\026'
            b(0x1000004);
            b(0x1000000);
            return;

        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 23: // '\027'
            b(0x1000002);
            return;

        case 14: // '\016'
        case 15: // '\017'
        case 24: // '\030'
            b(0x1000003);
            b(0x1000000);
            return;

        case 18: // '\022'
            switch(item.b)
            {
            case 3: // '\003'
                b(0x1000001);
                return;

            case 5: // '\005'
                b(0x1000004);
                b(0x1000000);
                return;

            case 4: // '\004'
                b(0x1000002);
                return;

            case 6: // '\006'
                b(0x1000003);
                b(0x1000000);
                return;

            case 7: // '\007'
                b(0x1700000 | classwriter.c("java/lang/Class"));
                return;

            case 8: // '\b'
                b(0x1700000 | classwriter.c("java/lang/String"));
                return;

            case 16: // '\020'
                b(0x1700000 | classwriter.c("java/lang/invoke/MethodType"));
                return;

            case 9: // '\t'
            case 10: // '\n'
            case 11: // '\013'
            case 12: // '\f'
            case 13: // '\r'
            case 14: // '\016'
            case 15: // '\017'
            default:
                b(0x1700000 | classwriter.c("java/lang/invoke/MethodHandle"));
                return;
            }

        case 25: // '\031'
            b(a(k));
            return;

        case 46: // '.'
        case 51: // '3'
        case 52: // '4'
        case 53: // '5'
            c(2);
            b(0x1000001);
            return;

        case 47: // '/'
        case 143: 
            c(2);
            b(0x1000004);
            b(0x1000000);
            return;

        case 48: // '0'
            c(2);
            b(0x1000002);
            return;

        case 49: // '1'
        case 138: 
            c(2);
            b(0x1000003);
            b(0x1000000);
            return;

        case 50: // '2'
            c(1);
            int l = a();
            b(l + 0xf0000000);
            return;

        case 54: // '6'
        case 56: // '8'
        case 58: // ':'
            int i1 = a();
            a(k, i1);
            if(k > 0)
            {
                if((j = a(k - 1)) == 0x1000004 || j == 0x1000003)
                {
                    a(k - 1, 0x1000000);
                    return;
                }
                if((j & 0xf000000) != 0x1000000)
                {
                    a(k - 1, j | 0x800000);
                    return;
                }
            }
            break;

        case 55: // '7'
        case 57: // '9'
            c(1);
            int j1 = a();
            a(k, j1);
            a(k + 1, 0x1000000);
            if(k <= 0)
                break;
            if((j = a(k - 1)) == 0x1000004 || j == 0x1000003)
            {
                a(k - 1, 0x1000000);
                return;
            }
            if((j & 0xf000000) != 0x1000000)
            {
                a(k - 1, j | 0x800000);
                return;
            }
            break;

        case 79: // 'O'
        case 81: // 'Q'
        case 83: // 'S'
        case 84: // 'T'
        case 85: // 'U'
        case 86: // 'V'
            c(3);
            return;

        case 80: // 'P'
        case 82: // 'R'
            c(4);
            return;

        case 87: // 'W'
        case 153: 
        case 154: 
        case 155: 
        case 156: 
        case 157: 
        case 158: 
        case 170: 
        case 171: 
        case 172: 
        case 174: 
        case 176: 
        case 191: 
        case 194: 
        case 195: 
        case 198: 
        case 199: 
            c(1);
            return;

        case 88: // 'X'
        case 159: 
        case 160: 
        case 161: 
        case 162: 
        case 163: 
        case 164: 
        case 165: 
        case 166: 
        case 173: 
        case 175: 
            c(2);
            return;

        case 89: // 'Y'
            int k1 = a();
            b(k1);
            b(k1);
            return;

        case 90: // 'Z'
            int l1 = a();
            j = a();
            b(l1);
            b(j);
            b(l1);
            return;

        case 91: // '['
            int i2 = a();
            j = a();
            k = a();
            b(i2);
            b(k);
            b(j);
            b(i2);
            return;

        case 92: // '\\'
            int j2 = a();
            j = a();
            b(j);
            b(j2);
            b(j);
            b(j2);
            return;

        case 93: // ']'
            int k2 = a();
            j = a();
            k = a();
            b(j);
            b(k2);
            b(k);
            b(j);
            b(k2);
            return;

        case 94: // '^'
            int l2 = a();
            j = a();
            k = a();
            classwriter = a();
            b(j);
            b(l2);
            b(classwriter);
            b(k);
            b(j);
            b(l2);
            return;

        case 95: // '_'
            int i3 = a();
            j = a();
            b(i3);
            b(j);
            return;

        case 96: // '`'
        case 100: // 'd'
        case 104: // 'h'
        case 108: // 'l'
        case 112: // 'p'
        case 120: // 'x'
        case 122: // 'z'
        case 124: // '|'
        case 126: // '~'
        case 128: 
        case 130: 
        case 136: 
        case 142: 
        case 149: 
        case 150: 
            c(2);
            b(0x1000001);
            return;

        case 97: // 'a'
        case 101: // 'e'
        case 105: // 'i'
        case 109: // 'm'
        case 113: // 'q'
        case 127: // '\177'
        case 129: 
        case 131: 
            c(4);
            b(0x1000004);
            b(0x1000000);
            return;

        case 98: // 'b'
        case 102: // 'f'
        case 106: // 'j'
        case 110: // 'n'
        case 114: // 'r'
        case 137: 
        case 144: 
            c(2);
            b(0x1000002);
            return;

        case 99: // 'c'
        case 103: // 'g'
        case 107: // 'k'
        case 111: // 'o'
        case 115: // 's'
            c(4);
            b(0x1000003);
            b(0x1000000);
            return;

        case 121: // 'y'
        case 123: // '{'
        case 125: // '}'
            c(3);
            b(0x1000004);
            b(0x1000000);
            return;

        case 132: 
            a(k, 0x1000001);
            return;

        case 133: 
        case 140: 
            c(1);
            b(0x1000004);
            b(0x1000000);
            return;

        case 134: 
            c(1);
            b(0x1000002);
            return;

        case 135: 
        case 141: 
            c(1);
            b(0x1000003);
            b(0x1000000);
            return;

        case 139: 
        case 190: 
        case 193: 
            c(1);
            b(0x1000001);
            return;

        case 148: 
        case 151: 
        case 152: 
            c(4);
            b(0x1000001);
            return;

        case 168: 
        case 169: 
            throw new RuntimeException("JSR/RET are not supported with computeFrames option");

        case 178: 
            a(classwriter, item.i);
            return;

        case 179: 
            a(item.i);
            return;

        case 180: 
            c(1);
            a(classwriter, item.i);
            return;

        case 181: 
            a(item.i);
            a();
            return;

        case 182: 
        case 183: 
        case 184: 
        case 185: 
            a(item.i);
            if(j != 184)
            {
                int j3 = a();
                if(j == 183 && item.h.charAt(0) == '<')
                    d(j3);
            }
            a(classwriter, item.i);
            return;

        case 186: 
            a(item.h);
            a(classwriter, item.h);
            return;

        case 187: 
            b(0x1800000 | classwriter.a(item.g, k));
            return;

        case 188: 
            a();
            switch(k)
            {
            case 4: // '\004'
                b(0x11000009);
                return;

            case 5: // '\005'
                b(0x1100000b);
                return;

            case 8: // '\b'
                b(0x1100000a);
                return;

            case 9: // '\t'
                b(0x1100000c);
                return;

            case 10: // '\n'
                b(0x11000001);
                return;

            case 6: // '\006'
                b(0x11000002);
                return;

            case 7: // '\007'
                b(0x11000003);
                return;
            }
            b(0x11000004);
            return;

        case 189: 
            j = item.g;
            a();
            if(j.charAt(0) == '[')
            {
                a(classwriter, "[" + j);
                return;
            } else
            {
                b(0x11700000 | classwriter.c(j));
                return;
            }

        case 192: 
            j = item.g;
            a();
            if(j.charAt(0) == '[')
            {
                a(classwriter, j);
                return;
            } else
            {
                b(0x1700000 | classwriter.c(j));
                return;
            }

        case 19: // '\023'
        case 20: // '\024'
        case 26: // '\032'
        case 27: // '\033'
        case 28: // '\034'
        case 29: // '\035'
        case 30: // '\036'
        case 31: // '\037'
        case 32: // ' '
        case 33: // '!'
        case 34: // '"'
        case 35: // '#'
        case 36: // '$'
        case 37: // '%'
        case 38: // '&'
        case 39: // '\''
        case 40: // '('
        case 41: // ')'
        case 42: // '*'
        case 43: // '+'
        case 44: // ','
        case 45: // '-'
        case 59: // ';'
        case 60: // '<'
        case 61: // '='
        case 62: // '>'
        case 63: // '?'
        case 64: // '@'
        case 65: // 'A'
        case 66: // 'B'
        case 67: // 'C'
        case 68: // 'D'
        case 69: // 'E'
        case 70: // 'F'
        case 71: // 'G'
        case 72: // 'H'
        case 73: // 'I'
        case 74: // 'J'
        case 75: // 'K'
        case 76: // 'L'
        case 77: // 'M'
        case 78: // 'N'
        case 196: 
        case 197: 
        default:
            c(k);
            a(classwriter, item.g);
            break;
        }
    }

    final boolean a(ClassWriter classwriter, Frame frame, int j)
    {
        boolean flag = false;
        int k = c.length;
        int l = d.length;
        if(frame.c == null)
        {
            frame.c = new int[k];
            flag = true;
        }
        for(int i1 = 0; i1 < k; i1++)
        {
            int i2;
            int k2;
            if(e != null && i1 < e.length && (i2 = e[i1]) != 0)
            {
                k2 = i2 & 0xf0000000;
                int k3;
                if((k3 = i2 & 0xf000000) == 0x1000000)
                {
                    k2 = i2;
                } else
                {
                    if(k3 == 0x2000000)
                        k2 += c[i2 & 0x7fffff];
                    else
                        k2 += d[l - (i2 & 0x7fffff)];
                    if((i2 & 0x800000) != 0 && (k2 == 0x1000004 || k2 == 0x1000003))
                        k2 = 0x1000000;
                }
            } else
            {
                k2 = c[i1];
            }
            if(i != null)
                k2 = a(classwriter, k2);
            flag |= a(classwriter, k2, frame.c, i1);
        }

        if(j > 0)
        {
            for(int j1 = 0; j1 < k; j1++)
            {
                int l2 = c[j1];
                flag |= a(classwriter, l2, frame.c, j1);
            }

            if(frame.d == null)
            {
                frame.d = new int[1];
                flag = true;
            }
            return flag |= a(classwriter, j, frame.d, 0);
        }
        j = d.length + b.f;
        if(frame.d == null)
        {
            frame.d = new int[j + g];
            flag = true;
        }
        for(int k1 = 0; k1 < j; k1++)
        {
            int i3 = d[k1];
            if(i != null)
                i3 = a(classwriter, i3);
            flag |= a(classwriter, i3, frame.d, k1);
        }

        for(int l1 = 0; l1 < g; l1++)
        {
            int j2;
            int j3 = (j2 = f[l1]) & 0xf0000000;
            int l3;
            if((l3 = j2 & 0xf000000) == 0x1000000)
            {
                j3 = j2;
            } else
            {
                if(l3 == 0x2000000)
                    j3 += c[j2 & 0x7fffff];
                else
                    j3 += d[l - (j2 & 0x7fffff)];
                if((j2 & 0x800000) != 0 && (j3 == 0x1000004 || j3 == 0x1000003))
                    j3 = 0x1000000;
            }
            if(i != null)
                j3 = a(classwriter, j3);
            flag |= a(classwriter, j3, frame.d, j + l1);
        }

        return flag;
    }

    private static boolean a(ClassWriter classwriter, int j, int ai[], int k)
    {
        int l;
        if((l = ai[k]) == j)
            return false;
        if((j & 0xfffffff) == 0x1000005)
        {
            if(l == 0x1000005)
                return false;
            j = 0x1000005;
        }
        if(l == 0)
        {
            ai[k] = j;
            return true;
        }
        if((l & 0xff00000) == 0x1700000 || (l & 0xf0000000) != 0)
        {
            if(j == 0x1000005)
                return false;
            if((j & 0xfff00000) == (l & 0xfff00000))
            {
                if((l & 0xff00000) == 0x1700000)
                    classwriter = j & 0xf0000000 | 0x1700000 | classwriter.a(j & 0xfffff, l & 0xfffff);
                else
                    classwriter = (j = 0xf0000000 + (l & 0xf0000000)) | 0x1700000 | classwriter.c("java/lang/Object");
            } else
            if((j & 0xff00000) == 0x1700000 || (j & 0xf0000000) != 0)
            {
                j = ((j & 0xf0000000) != 0 && (j & 0xff00000) != 0x1700000 ? 0xf0000000 : 0) + (j & 0xf0000000);
                int i1 = ((l & 0xf0000000) != 0 && (l & 0xff00000) != 0x1700000 ? 0xf0000000 : 0) + (l & 0xf0000000);
                classwriter = Math.min(j, i1) | 0x1700000 | classwriter.c("java/lang/Object");
            } else
            {
                classwriter = 0x1000000;
            }
        } else
        if(l == 0x1000005)
            classwriter = (j & 0xff00000) != 0x1700000 && (j & 0xf0000000) == 0 ? 0x1000000 : ((ClassWriter) (j));
        else
            classwriter = 0x1000000;
        if(l != classwriter)
        {
            ai[k] = classwriter;
            return true;
        } else
        {
            return false;
        }
    }

    static void _clinit_()
    {
    }

    static final int a[];
    Label b;
    int c[];
    int d[];
    private int e[];
    private int f[];
    private int g;
    private int h;
    private int i[];

    static 
    {
        _clinit_();
        int ai[] = new int[202];
        String s = "EFFFFFFFFGGFFFGGFFFEEFGFGFEEEEEEEEEEEEEEEEEEEEDEDEDDDDDCDCDEEEEEEEEEEEEEEEEEEEEBABABBBBDCFFFGGGEDCDCDCDCDCDCDCDCDCDCEEEEDDDDDDDCDCDCEFEFDDEEFFDEDEEEBDDBBDDDDDDCCCCCCCCEFEDDDCDCDEEEEEEEEEEFEEEEEEDDEEDDEE";
        for(int j = 0; j < 202; j++)
            ai[j] = s.charAt(j) - 69;

        a = ai;
    }
}
