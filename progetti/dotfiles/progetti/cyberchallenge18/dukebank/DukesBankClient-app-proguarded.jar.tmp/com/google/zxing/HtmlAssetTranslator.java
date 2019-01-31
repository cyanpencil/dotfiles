// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HtmlAssetTranslator.java

package com.google.zxing;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.util.*;
import java.util.regex.Pattern;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;

// Referenced classes of package com.google.zxing:
//            StringsResourceTranslator

public final class HtmlAssetTranslator
{

            private HtmlAssetTranslator()
            {
            }

            public static void main(String args[])
                throws IOException
            {
/*  70*/        if(args.length < 3)
                {
/*  71*/            System.err.println("Usage: HtmlAssetTranslator android/assets/ (all|lang1[,lang2 ...]) (all|file1.html[ file2.html ...])");
/*  73*/            return;
                }
                Path path;
/*  75*/        Object obj = parseLanguagesToTranslate(path = Paths.get(args[0], new String[0]), args[1]);
/*  77*/        args = Arrays.asList(args).subList(2, args.length);
/*  78*/        args = parseFileNamesToTranslate(path, args);
                String s;
/*  79*/        for(obj = ((Collection) (obj)).iterator(); ((Iterator) (obj)).hasNext(); translateOneLanguage(path, s, args))
/*  79*/            s = (String)((Iterator) (obj)).next();

            }

            private static Collection parseLanguagesToTranslate(Path path, CharSequence charsequence)
                throws IOException
            {
                Object obj;
/*  86*/        if(!"all".equals(charsequence))
/*  87*/            break MISSING_BLOCK_LABEL_141;
/*  87*/        charsequence = new ArrayList();
/*  88*/        obj = new java.nio.file.DirectoryStream.Filter() {

                    public final boolean accept(Path path2)
                    {
/*  91*/                String s = path2.getFileName().toString();
/*  92*/                return Files.isDirectory(path2, new LinkOption[0]) && !Files.isSymbolicLink(path2) && s.startsWith("html-") && !"html-en".equals(s);
                    }

                    public final volatile boolean accept(Object obj1)
                        throws IOException
                    {
/*  88*/                return accept((Path)obj1);
                    }

        };
/*  96*/        path = Files.newDirectoryStream(path, ((java.nio.file.DirectoryStream.Filter) (obj)));
/*  96*/        obj = null;
                Path path1;
/*  97*/        for(Iterator iterator = path.iterator(); iterator.hasNext(); charsequence.add(path1.getFileName().toString().substring(5)))
/*  97*/            path1 = (Path)iterator.next();

/* 100*/        if(path != null)
/* 100*/            path.close();
/* 100*/        break MISSING_BLOCK_LABEL_139;
/*  96*/        JVM INSTR dup ;
                Throwable throwable;
/*  96*/        throwable;
/*  96*/        obj;
/*  96*/        throw throwable;
/* 100*/        charsequence;
/* 100*/        if(path != null)
/* 100*/            if(obj != null)
/* 100*/                try
                        {
/* 100*/                    path.close();
                        }
                        // Misplaced declaration of an exception variable
/* 100*/                catch(Path path)
                        {
/* 100*/                    ((Throwable) (obj)).addSuppressed(path);
                        }
/* 100*/            else
/* 100*/                path.close();
/* 100*/        throw charsequence;
/* 101*/        return charsequence;
/* 103*/        return Arrays.asList(COMMA.split(charsequence));
            }

            private static Collection parseFileNamesToTranslate(Path path, List list)
                throws IOException
            {
                Throwable throwa