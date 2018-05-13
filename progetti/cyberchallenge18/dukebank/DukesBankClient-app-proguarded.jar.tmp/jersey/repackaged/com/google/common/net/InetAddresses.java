// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InetAddresses.java

package jersey.repackaged.com.google.common.net;

import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import jersey.repackaged.com.google.common.base.MoreObjects;
import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.hash.*;
import jersey.repackaged.com.google.common.io.ByteArrayDataInput;
import jersey.repackaged.com.google.common.io.ByteStreams;
import jersey.repackaged.com.google.common.primitives.Ints;

public final class InetAddresses
{
    public static final class TeredoInfo
    {

                public final Inet4Address getClient()
                {
/* 660*/            return client;
                }

                private final Inet4Address server;
                private final Inet4Address client;
                private final int port;
                private final int flags;

                public TeredoInfo(Inet4Address inet4address, Inet4Address inet4address1, int i, int j)
                {
/* 644*/            Preconditions.checkArgument(i >= 0 && i <= 65535, "port '%s' is out of range (0 <= port <= 0xffff)", new Object[] {
/* 644*/                Integer.valueOf(i)
                    });
/* 646*/            Preconditions.checkArgument(j >= 0 && j <= 65535, "flags '%s' is out of range (0 <= flags <= 0xffff)", new Object[] {
/* 646*/                Integer.valueOf(j)
                    });
/* 649*/            server = (Inet4Address)MoreObjects.firstNonNull(inet4address, InetAddresses.ANY4);
/* 650*/            client = (Inet4Address)MoreObjects.firstNonNull(inet4address1, InetAddresses.ANY4);
/* 651*/            port = i;
/* 652*/            flags = j;
                }
    }


            private InetAddresses()
            {
            }

            private static Inet4Address getInet4Address(byte abyte0[])
            {
/* 130*/        Preconditions.checkArgument(abyte0.length == 4, "Byte array has invalid length for an IPv4 address: %s != 4.", new Object[] {
/* 130*/            Integer.valueOf(abyte0.length)
                });
/* 135*/        return (Inet4Address)bytesToInetAddress(abyte0);
            }

            public static InetAddress forString(String s)
            {
                byte abyte0[];
/* 149*/        if((abyte0 = ipStringToBytes(s)) == null)
/* 153*/            throw new IllegalArgumentException(String.format("'%s' is not an IP string literal.", new Object[] {
/* 153*/                s
                    }));
/* 157*/        else
/* 157*/            return bytesToInetAddress(abyte0);
            }

            public static boolean isInetAddress(String s)
            {
/* 168*/        return ipStringToBytes(s) != null;
            }

            private static byte[] ipStringToBytes(String s)
            {
/* 173*/        boolean flag = false;
/* 174*/        boolean flag1 = false;
/* 175*/        for(int i = 0; i < s.length(); i++)
                {
                    char c;
/* 176*/            if((c = s.charAt(i)) == '.')
                    {
/* 178*/                flag1 = true;
/* 178*/                continue;
                    }
/* 179*/            if(c == ':')
                    {
/* 180*/                if(flag1)
/* 181*/                    return null;
/* 183*/                flag = true;
/* 183*/                continue;
                    }
/* 184*/            if(Character.digit(c, 16) == -1)
/* 185*/                return null;
                }

/* 190*/        if(flag)
/* 191*/            if(flag1 && (s = convertDottedQuadToHex(s)) == null)
/* 194*/                return null;
/* 197*/            else
/* 197*/                return textToNumericFormatV6(s);
/* 198*/        if(flag1)
/* 199*/            return textToNumericFormatV4(s);
/* 201*/        else
/* 201*/            return null;
            }

            private static byte[] textToNumericFormatV4(String s)
            {
/* 205*/        if((s = s.split("\\.", 5)).length != 4)
/* 207*/            return null;
/* 210*/        byte abyte0[] = new byte[4];
/* 212*/        try
                {
/* 212*/            for(int i = 0; i < 4; i++)
/* 213*/                abyte0[i] = parseOctet(s[i]);

                }
/* 215*/        catch(NumberFormatException _ex)
                {
/* 216*/            return null;
                }
/* 219*/        return abyte0;
            }

            private static byte[] textToNumericFormatV6(String s)
            {
/* 224*/        if((s = s.split(":", 10)).length < 3 || s.length > 9)
/* 226*/            return null;
/* 231*/        int i = -1;
/* 232*/        for(int j = 1; j < s.length - 1; j++)
                {
/* 233*/            if(s[j].length() != 0)
/* 234*/                continue;
/* 234*/            if(i >= 0)
/* 235*/                return null;
/* 237*/            i = j;
                }

                int k;
                int l;
/* 243*/        if(i >= 0)
                {
/* 245*/            k = i;
/* 246*/            l = s.length - i - 1;
/* 247*/            if(s[0].length() == 0 && --k != 0)
/* 248*/                return null;
/* 250*/            if(s[s.length - 1].length() == 0 && --l != 0)
/* 251*/                return null;
                } else
                {
/* 256*/            k = s.length;
/* 257*/            l = 0;
                }
/* 262*/        int i1 = 8 - (k + l);
/* 263*/        if(i < 0 ? i1 != 0 : i1 <= 0)
/* 264*/            return null;
/* 268*/        ByteBuffer bytebuffer = ByteBuffer.allocate(16);
/* 270*/        try
                {
/* 270*/            for(int j1 = 0; j1 < k; j1++)
/* 271*/                bytebuffer.putShort(parseHextet(s[j1]));

/* 273*/            for(int k1 = 0; k1 < i1; k1++)
/* 274*/                bytebuffer.putShort((short)0);

/* 276*/            for(int l1 = l; l1 > 0; l1--)
/* 277*/                bytebuffer.putShort(parseHextet(s[s.length - l1]));

                }
/* 279*/        catch(NumberFormatException _ex)
                {
/* 280*/            return null;
                }
/* 282*/        return bytebuffer.array();
            }

            private static String convertDottedQuadToHex(String s)
            {
/* 286*/        int i = s.lastIndexOf(':');
/* 287*/        String s2 = s.substring(0, i + 1);
/* 288*/        if((s = textToNumericFormatV4(s = s.substring(i + 1))) == null)
                {
/* 291*/            return null;
                } else
                {
/* 293*/            String s1 = Integer.toHexString((s[0] & 0xff) << 8 | s[1] & 0xff);
/* 294*/            s = Integer.toHexString((s[2] & 0xff) << 8 | s[3] & 0xff);
/* 295*/            s2 = String.valueOf(String.valueOf(s2));
/* 295*/            s1 = String.valueOf(String.valueOf(s1));
/* 295*/            s = String.valueOf(String.valueOf(s));
/* 295*/            return (new StringBuilder(1 + s2.length() + s1.length() + s.length())).append(s2).append(s1).append(":").append(s).toString();
                }
            }

            private static byte parseOctet(String s)
            {
                int i;
/* 300*/        if((i = Integer.parseInt(s)) > 255 || s.startsWith("0") && s.length() > 1)
/* 304*/            throw new NumberFormatException();
/* 306*/        else
/* 306*/            return (byte)i;
            }

            private static short parseHextet(String s)
            {
/* 311*/        if((s = Integer.parseInt(s, 16)) > 65535)
/* 313*/            throw new NumberFormatException();
/* 315*/        else
/* 315*/            return (short)s;
            }

            private static InetAddress bytesToInetAddress(byte abyte0[])
            {
/* 331*/        return InetAddress.getByAddress(abyte0);
/* 332*/        abyte0;
/* 333*/        throw new AssertionError(abyte0);
            }

            public static String toAddrString(InetAddress inetaddress)
            {
/* 355*/        Preconditions.checkNotNull(inetaddress);
/* 356*/        if(inetaddress instanceof Inet4Address)
/* 358*/            return inetaddress.getHostAddress();
/* 360*/        Preconditions.checkArgument(inetaddress instanceof Inet6Address);
/* 361*/        inetaddress = inetaddress.getAddress();
/* 362*/        int ai[] = new int[8];
/* 363*/        for(int i = 0; i < 8; i++)
/* 364*/            ai[i] = Ints.fromBytes((byte)0, (byte)0, inetaddress[2 * i], inetaddress[2 * i + 1]);

/* 367*/        compressLongestRunOfZeroes(ai);
/* 368*/        return hextetsToIPv6String(ai);
            }

            private static void compressLongestRunOfZeroes(int ai[])
            {
/* 381*/        int i = -1;
/* 382*/        int j = -1;
/* 383*/        int k = -1;
/* 384*/        for(int l = 0; l < ai.length + 1; l++)
                {
/* 385*/            if(l < ai.length && ai[l] == 0)
                    {
/* 386*/                if(k < 0)
/* 387*/                    k = l;
/* 387*/                continue;
                    }
/* 389*/            if(k < 0)
/* 390*/                continue;
                    int i1;
/* 390*/            if((i1 = l - k) > j)
                    {
/* 392*/                i = k;
/* 393*/                j = i1;
                    }
/* 395*/            k = -1;
                }

/* 398*/        if(j >= 2)
/* 399*/            Arrays.fill(ai, i, i + j, -1);
            }

            private static String hextetsToIPv6String(int ai[])
            {
/* 418*/        StringBuilder stringbuilder = new StringBuilder(39);
/* 419*/        boolean flag = false;
/* 420*/        for(int i = 0; i < ai.length; i++)
                {
                    boolean flag1;
/* 421*/            if(flag1 = ai[i] >= 0)
                    {
/* 423*/                if(flag)
/* 424*/                    stringbuilder.append(':');
/* 426*/                stringbuilder.append(Integer.toHexString(ai[i]));
                    } else
/* 428*/            if(i == 0 || flag)
/* 429*/                stringbuilder.append("::");
/* 432*/            flag = flag1;
                }

/* 434*/        return stringbuilder.toString();
            }

            public static String toUriString(InetAddress inetaddress)
            {
/* 463*/        if(inetaddress instanceof Inet6Address)
                {
/* 464*/            inetaddress = String.valueOf(String.valueOf(toAddrString(inetaddress)));
/* 464*/            return (new StringBuilder(2 + inetaddress.length())).append("[").append(inetaddress).append("]").toString();
                } else
                {
/* 466*/            return toAddrString(inetaddress);
                }
            }

            public static InetAddress forUriString(String s)
            {
/* 485*/        Preconditions.checkNotNull(s);
                String s1;
                byte byte0;
/* 490*/        if(s.startsWith("[") && s.endsWith("]"))
                {
/* 491*/            s1 = s.substring(1, s.length() - 1);
/* 492*/            byte0 = 16;
                } else
                {
/* 494*/            s1 = s;
/* 495*/            byte0 = 4;
                }
                byte abyte0[];
/* 499*/        if((abyte0 = ipStringToBytes(s1)) == null || abyte0.length != byte0)
/* 501*/            throw new IllegalArgumentException(String.format("Not a valid URI IP literal: '%s'", new Object[] {
/* 501*/                s
                    }));
/* 505*/        else
/* 505*/            return bytesToInetAddress(abyte0);
            }

            public static boolean isUriInetAddress(String s)
            {
/* 517*/        forUriString(s);
/* 518*/        return true;
/* 519*/        JVM INSTR pop ;
/* 520*/        return false;
            }

            public static boolean isCompatIPv4Address(Inet6Address inet6address)
            {
/* 549*/        if(!inet6address.isIPv4CompatibleAddress())
/* 550*/            return false;
/* 553*/        return (inet6address = inet6address.getAddress())[12] != 0 || inet6address[13] != 0 || inet6address[14] != 0 || inet6address[15] != 0 && inet6address[15] != 1;
            }

            public static Inet4Address getCompatIPv4Address(Inet6Address inet6address)
            {
/* 570*/        Preconditions.checkArgument(isCompatIPv4Address(inet6address), "Address '%s' is not IPv4-compatible.", new Object[] {
/* 570*/            toAddrString(inet6address)
                });
/* 573*/        return getInet4Address(Arrays.copyOfRange(inet6address.getAddress(), 12, 16));
            }

            public static boolean is6to4Address(Inet6Address inet6address)
            {
/* 591*/        return (inet6address = inet6address.getAddress())[0] == 32 && inet6address[1] == 2;
            }

            public static Inet4Address get6to4IPv4Address(Inet6Address inet6address)
            {
/* 603*/        Preconditions.checkArgument(is6to4Address(inet6address), "Address '%s' is not a 6to4 address.", new Object[] {
/* 603*/            toAddrString(inet6address)
                });
/* 606*/        return getInet4Address(Arrays.copyOfRange(inet6address.getAddress(), 2, 6));
            }

            public static boolean isTeredoAddress(Inet6Address inet6address)
            {
/* 681*/        return (inet6address = inet6address.getAddress())[0] == 32 && inet6address[1] == 1 && inet6address[2] == 0 && inet6address[3] == 0;
            }

            public static TeredoInfo getTeredoInfo(Inet6Address inet6address)
            {
/* 694*/        Preconditions.checkArgument(isTeredoAddress(inet6address), "Address '%s' is not a Teredo address.", new Object[] {
/* 694*/            toAddrString(inet6address)
                });
/* 697*/        Inet4Address inet4address = getInet4Address(Arrays.copyOfRange(inet6address = inet6address.getAddress(), 4, 8));
/* 700*/        int i = ByteStreams.newDataInput(inet6address, 8).readShort() & 0xffff;
/* 703*/        int j = ~ByteStreams.newDataInput(inet6address, 10).readShort() & 0xffff;
/* 705*/        inet6address = Arrays.copyOfRange(inet6address, 12, 16);
/* 706*/        for(int k = 0; k < inet6address.length; k++)
/* 708*/            inet6address[k] = (byte)(~inet6address[k]);

/* 710*/        Inet4Address inet4address1 = getInet4Address(inet6address);
/* 712*/        return new TeredoInfo(inet4address, inet4address1, j, i);
            }

            public static boolean isIsatapAddress(Inet6Address inet6address)
            {
/* 734*/        if(isTeredoAddress(inet6address))
/* 735*/            return false;
/* 738*/        if(((inet6address = inet6address.getAddress())[8] | 3) != 3)
/* 744*/            return false;
/* 747*/        return inet6address[9] == 0 && inet6address[10] == 94 && inet6address[11] == -2;
            }

            public static Inet4Address getIsatapIPv4Address(Inet6Address inet6address)
            {
/* 759*/        Preconditions.checkArgument(isIsatapAddress(inet6address), "Address '%s' is not an ISATAP address.", new Object[] {
/* 759*/            toAddrString(inet6address)
                });
/* 762*/        return getInet4Address(Arrays.copyOfRange(inet6address.getAddress(), 12, 16));
            }

            public static boolean hasEmbeddedIPv4ClientAddress(Inet6Address inet6address)
            {
/* 778*/        return isCompatIPv4Address(inet6address) || is6to4Address(inet6address) || isTeredoAddress(inet6address);
            }

            public static Inet4Address getEmbeddedIPv4ClientAddress(Inet6Address inet6address)
            {
/* 795*/        if(isCompatIPv4Address(inet6address))
/* 796*/            return getCompatIPv4Address(inet6address);
/* 799*/        if(is6to4Address(inet6address))
/* 800*/            return get6to4IPv4Address(inet6address);
/* 803*/        if(isTeredoAddress(inet6address))
/* 804*/            return getTeredoInfo(inet6address).getClient();
/* 807*/        else
/* 807*/            throw new IllegalArgumentException(String.format("'%s' has no embedded IPv4 address.", new Object[] {
/* 807*/                toAddrString(inet6address)
                    }));
            }

            public static boolean isMappedIPv4Address(String s)
            {
/* 834*/        if((s = ipStringToBytes(s)) != null && s.length == 16)
                {
/* 836*/            for(int i = 0; i < 10; i++)
/* 837*/                if(s[i] != 0)
/* 838*/                    return false;

/* 841*/            for(int j = 10; j < 12; j++)
/* 842*/                if(s[j] != -1)
/* 843*/                    return false;

/* 846*/            return true;
                } else
                {
/* 848*/            return false;
                }
            }

            public static Inet4Address getCoercedIPv4Address(InetAddress inetaddress)
            {
/* 872*/        if(inetaddress instanceof Inet4Address)
/* 873*/            return (Inet4Address)inetaddress;
/* 877*/        byte abyte0[] = inetaddress.getAddress();
/* 878*/        boolean flag = true;
/* 879*/        int i = 0;
/* 879*/        do
                {
/* 879*/            if(i >= 15)
/* 880*/                break;
/* 880*/            if(abyte0[i] != 0)
                    {
/* 881*/                flag = false;
/* 882*/                break;
                    }
/* 879*/            i++;
                } while(true);
/* 885*/        if(flag && abyte0[15] == 1)
/* 886*/            return LOOPBACK4;
/* 887*/        if(flag && abyte0[15] == 0)
/* 888*/            return ANY4;
                Inet6Address inet6address;
                long l;
/* 891*/        if(hasEmbeddedIPv4ClientAddress(inet6address = (Inet6Address)inetaddress))
/* 894*/            l = getEmbeddedIPv4ClientAddress(inet6address).hashCode();
/* 898*/        else
/* 898*/            l = ByteBuffer.wrap(inet6address.getAddress(), 0, 8).getLong();
/* 902*/        if((inetaddress = (inetaddress = Hashing.murmur3_32().hashLong(l).asInt()) | 0xe0000000) == -1)
/* 910*/            inetaddress = -2;
/* 913*/        return getInet4Address(Ints.toByteArray(inetaddress));
            }

            public static int coerceToInteger(InetAddress inetaddress)
            {
/* 938*/        return ByteStreams.newDataInput(getCoercedIPv4Address(inetaddress).getAddress()).readInt();
            }

            public static Inet4Address fromInteger(int i)
            {
/* 949*/        return getInet4Address(Ints.toByteArray(i));
            }

            public static InetAddress fromLittleEndianByteArray(byte abyte0[])
                throws UnknownHostException
            {
/* 964*/        byte abyte1[] = new byte[abyte0.length];
/* 965*/        for(int i = 0; i < abyte0.length; i++)
/* 966*/            abyte1[i] = abyte0[abyte0.length - i - 1];

/* 968*/        return InetAddress.getByAddress(abyte1);
            }

            public static InetAddress decrement(InetAddress inetaddress)
            {
                byte abyte0[];
                int i;
/* 981*/        for(i = (abyte0 = inetaddress.getAddress()).length - 1; i >= 0 && abyte0[i] == 0; i--)
/* 984*/            abyte0[i] = -1;

/* 988*/        Preconditions.checkArgument(i >= 0, "Decrementing %s would wrap.", new Object[] {
/* 988*/            inetaddress
                });
/* 990*/        abyte0[i]--;
/* 991*/        return bytesToInetAddress(abyte0);
            }

            public static InetAddress increment(InetAddress inetaddress)
            {
                byte abyte0[];
                int i;
/*1004*/        for(i = (abyte0 = inetaddress.getAddress()).length - 1; i >= 0 && abyte0[i] == -1; i--)
/*1007*/            abyte0[i] = 0;

/*1011*/        Preconditions.checkArgument(i >= 0, "Incrementing %s would wrap.", new Object[] {
/*1011*/            inetaddress
                });
/*1013*/        abyte0[i]++;
/*1014*/        return bytesToInetAddress(abyte0);
            }

            public static boolean isMaximum(InetAddress inetaddress)
            {
/*1026*/        inetaddress = inetaddress.getAddress();
/*1027*/        for(int i = 0; i < inetaddress.length; i++)
/*1028*/            if(inetaddress[i] != -1)
/*1029*/                return false;

/*1032*/        return true;
            }

            private static final int IPV4_PART_COUNT = 4;
            private static final int IPV6_PART_COUNT = 8;
            private static final Inet4Address LOOPBACK4 = (Inet4Address)forString("127.0.0.1");
            private static final Inet4Address ANY4 = (Inet4Address)forString("0.0.0.0");


}
