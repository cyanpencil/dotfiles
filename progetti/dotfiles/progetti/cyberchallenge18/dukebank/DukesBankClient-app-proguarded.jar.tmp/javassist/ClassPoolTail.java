// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassPoolTail.java

package javassist;

import java.io.*;
import java.net.URL;

// Referenced classes of package javassist:
//            CannotCompileException, ClassClassPath, ClassPath, ClassPathList, 
//            DirClassPath, JarClassPath, JarDirClassPath, NotFoundException

final class ClassPoolTail
{

            public ClassPoolTail()
            {
/* 183*/        pathList = null;
            }

            public final String toString()
            {
                StringBuffer stringbuffer;
/* 187*/        (stringbuffer = new StringBuffer()).append("[class path: ");
/* 189*/        for(ClassPathList classpathlist = pathList; classpathlist != null; classpathlist = classpathlist.next)
                {
/* 191*/            stringbuffer.append(classpathlist.path.toString());
/* 192*/            stringbuffer.append(File.pathSeparatorChar);
                }

/* 196*/        stringbuffer.append(']');
/* 197*/        return stringbuffer.toString();
            }

            public final synchronized ClassPath insertClassPath(ClassPath classpath)
            {
/* 201*/        pathList = new ClassPathList(classpath, pathList);
/* 202*/        return classpath;
            }

            public final synchronized ClassPath appendClassPath(ClassPath classpath)
            {
/* 206*/        ClassPathList classpathlist = new ClassPathList(classpath, null);
                ClassPathList classpathlist1;
/* 207*/        if((classpathlist1 = pathList) == null)
                {
/* 209*/            pathList = classpathlist;
                } else
                {
/* 211*/            for(; classpathlist1.next != null; classpathlist1 = classpathlist1.next);
/* 214*/            classpathlist1.next = classpathlist;
                }
/* 217*/        return classpath;
            }

            public final synchronized void removeClassPath(ClassPath classpath)
            {
                ClassPathList classpathlist;
/* 221*/        if((classpathlist = pathList) != null)
/* 223*/            if(classpathlist.path == classpath)
/* 224*/                pathList = classpathlist.next;
/* 226*/            else
/* 226*/                while(classpathlist.next != null) 
/* 227*/                    if(classpathlist.next.path == classpath)
/* 228*/                        classpathlist.next = classpathlist.next.next;
/* 230*/                    else
/* 230*/                        classpathlist = classpathlist.next;
/* 233*/        classpath.close();
            }

            public final ClassPath appendSystemPath()
            {
/* 237*/        return appendClassPath(new ClassClassPath());
            }

            public final ClassPath insertClassPath(String s)
                throws NotFoundException
            {
/* 243*/        return insertClassPath(makePathObject(s));
            }

            public final ClassPath appendClassPath(String s)
                throws NotFoundException
            {
/* 249*/        return appendClassPath(makePathObject(s));
            }

            private static ClassPath makePathObject(String s)
                throws NotFoundException
            {
                String s1;
/* 255*/        if((s1 = s.toLowerCase()).endsWith(".jar") || s1.endsWith(".zip"))
/* 257*/            return new JarClassPath(s);
                int i;
/* 259*/        if((i = s.length()) > 2 && s.charAt(i - 1) == '*' && (s.charAt(i - 2) == '/' || s.charAt(i - 2) == File.separatorChar))
                {
/* 263*/            s = s.substring(0, i - 2);
/* 264*/            return new JarDirClassPath(s);
                } else
                {
/* 267*/            return new DirClassPath(s);
                }
            }

            final void writeClassfile(String s, OutputStream outputstream)
                throws NotFoundException, IOException, CannotCompileException
            {
                InputStream inputstream;
/* 276*/        if((inputstream = openClassfile(s)) == null)
/* 278*/            throw new NotFoundException(s);
/* 281*/        copyStream(inputstream, outputstream);
/* 284*/        inputstream.close();
/* 285*/        return;
/* 284*/        s;
/* 284*/        inputstream.close();
/* 284*/        throw s;
            }

            final InputStream openClassfile(String s)
                throws NotFoundException
            {
/* 318*/        ClassPathList classpathlist = pathList;
/* 319*/        InputStream inputstream = null;
/* 320*/        NotFoundException notfoundexception = null;
/* 321*/        while(classpathlist != null) 
                {
/* 323*/            try
                    {
/* 323*/                inputstream = classpathlist.path.openClassfile(s);
                    }
/* 325*/            catch(NotFoundException notfoundexception1)
                    {
/* 326*/                if(notfoundexception == null)
/* 327*/                    notfoundexception = notfoundexception1;
                    }
/* 330*/            if(inputstream == null)
/* 331*/                classpathlist = classpathlist.next;
/* 333*/            else
/* 333*/                return inputstream;
                }
/* 336*/        if(notfoundexception != null)
/* 337*/            throw notfoundexception;
/* 339*/        else
/* 339*/            return null;
            }

            public final URL find(String s)
            {
                URL url;
/* 351*/        for(ClassPathList classpathlist = pathList; classpathlist != null;)
/* 354*/            if((url = classpathlist.path.find(s)) == null)
/* 356*/                classpathlist = classpathlist.next;
/* 358*/            else
/* 358*/                return url;

/* 361*/        return null;
            }

            public static byte[] readStream(InputStream inputstream)
                throws IOException
            {
/* 370*/        byte abyte0[][] = new byte[8][];
/* 371*/        int i = 4096;
/* 373*/        for(int j = 0; j < 8; j++)
                {
/* 374*/            abyte0[j] = new byte[i];
/* 375*/            int k = 0;
                    int l;
/* 378*/            do
/* 378*/                if((l = inputstream.read(abyte0[j], k, i - k)) >= 0)
                        {
/* 380*/                    k += l;
                        } else
                        {
/* 382*/                    inputstream = new byte[(i - 4096) + k];
/* 383*/                    i = 0;
/* 384*/                    for(int i1 = 0; i1 < j; i1++)
                            {
/* 385*/                        System.arraycopy(abyte0[i1], 0, inputstream, i, i + 4096);
/* 386*/                        i = i + i + 4096;
                            }

/* 389*/                    System.arraycopy(abyte0[j], 0, inputstream, i, k);
/* 390*/                    return inputstream;
                        }
/* 392*/            while(k < i);
/* 393*/            i <<= 1;
                }

/* 396*/        throw new IOException("too much data");
            }

            public static void copyStream(InputStream inputstream, OutputStream outputstream)
                throws IOException
            {
/* 407*/        int i = 4096;
/* 408*/        byte abyte0[] = null;
/* 409*/        for(int j = 0; j < 64; j++)
                {
/* 410*/            if(j < 8)
/* 411*/                abyte0 = new byte[i <<= 1];
/* 414*/            int k = 0;
                    int l;
/* 417*/            do
/* 417*/                if((l = inputstream.read(abyte0, k, i - k)) >= 0)
                        {
/* 419*/                    k += l;
                        } else
                        {
/* 421*/                    outputstream.write(abyte0, 0, k);
/* 422*/                    return;
                        }
/* 424*/            while(k < i);
/* 425*/            outputstream.write(abyte0);
                }

/* 428*/        throw new IOException("too much data");
            }

            protected ClassPathList pathList;
}
