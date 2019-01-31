// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StringsResourceTranslator.java

package com.google.zxing;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringsResourceTranslator
{

            private StringsResourceTranslator()
            {
            }

            public static void main(String args[])
                throws IOException
            {
                Object obj;
                Path path;
                Object obj1;
/*  93*/        path = (path = ((Path) (obj = Paths.get(args[0], new String[0]))).resolve("values")).resolve("strings.xml");
/*  96*/        args = Arrays.asList(args).subList(1, args.length);
/*  98*/        obj1 = new java.nio.file.DirectoryStream.Filter() {

                    public final boolean accept(Path path2)
                    {
/* 101*/                return Files.isDirectory(path2, new LinkOption[0]) && !Files.isSymbolicLink(path2) && StringsResourceTranslator.VALUES_DIR_PATTERN.matcher(path2.getFileName().toString()).matches();
                    }

                    public final volatile boolean accept(Object obj2)
                        throws IOException
                    {
/*  98*/                return accept((Path)obj2);
                    }

        };
/* 105*/        obj = Files.newDirectoryStream(((Path) (obj)), ((java.nio.file.DirectoryStream.Filter) (obj1)));
/* 105*/        obj1 = null;
                Path path1;
/* 106*/        for(Iterator iterator = ((DirectoryStream) (obj)).iterator(); iterator.hasNext(); translate(path, path1.resolve("strings.xml"), args))
/* 106*/            path1 = (Path)iterator.next();

                Throwable throwable;
/* 109*/        if(obj != null)
                {
/* 109*/            ((DirectoryStream) (obj)).close();
/* 109*/            return;
                } else
                {
/* 110*/            return;
                }
/* 105*/        JVM INSTR dup ;
/* 105*/        throwable;
/* 105*/        obj1;
/* 105*/        throw throwable;
/* 109*/        args;
/* 109*/        if(obj != null)
/* 109*/            if(obj1 != null)
/* 109*/                try
                        {
/* 109*/                    ((DirectoryStream) (obj)).close();
                        }
                        // Misplaced declaration of an exception variable
/* 109*/                catch(Object obj)
                        {
/* 109*/                    ((Throwable) (obj1)).addSuppressed(((Throwable) (obj)));
                        }
/* 109*/            else
/* 109*/                ((DirectoryStream) (obj)).close();
/* 109*/        throw args;
            }

            private static void translate(Path path, Path path1, Collection collection)
                throws IOException
            {
                Map map;
                Object obj;
                Object obj1;
                boolean flag;
                BufferedWriter bufferedwriter;
                Throwable throwable;
/* 116*/        path = readLines(path);
/* 117*/        map = readLines(path1);
/* 118*/        obj = path1.getParent().getFileName().toString();
/* 120*/        ((Matcher) (obj = STRINGS_FILE_NAME_PATTERN.matcher(((CharSequence) (obj))))).find();
/* 122*/        obj = ((Matcher) (obj)).group(1);
/* 123*/        if((obj1 = (String)LANGUAGE_CODE_MASSAGINGS.get(obj)) != null)
/* 125*/            obj = obj1;
/* 128*/        System.out.println((new StringBuilder("Translating ")).append(((String) (obj))).toString());
/* 130*/        obj1 = Files.createTempFile(null, null, new FileAttribute[0]);
/* 132*/        flag = false;
/* 133*/        bufferedwriter = Files.newBufferedWriter(((Path) (obj1)), StandardCharsets.UTF_8, new OpenOption[0]);
/* 133*/        throwable = null;
/* 134*/        bufferedwriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
/* 135*/        bufferedwriter.write("<!--\n Copyright (C) 2014 ZXing authors\n\n Licensed under the Apache License, Version 2.0 (the \"License\");\n you may not use this file except in compliance with the License.\n You may obtain a copy of the License at\n\n      http://www.apache.org/licenses/LICENSE-2.0\n\n Unless required by applicable law or agreed to in writing, software\n distributed under the License is distributed on an \"AS IS\" BASIS,\n WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n See the License for the specific language governing permissions and\n limitations under the License.\n -->\n");
/* 136*/        bufferedwriter.write("<resources>\n");
/* 138*/        for(path = path.entrySet().iterator(); path.hasNext(); bufferedwriter.write("</string>\n"))
                {
                    Object obj2;
/* 138*/            String s = (String)((java.util.Map.Entry) (obj2 = (java.util.Map.Entry)path.next())).getKey();
/* 140*/            obj2 = (String)((java.util.Map.Entry) (obj2)).getValue();
/* 141*/            bufferedwriter.write("  <string name=\"");
/* 142*/            bufferedwriter.write(s);
/* 143*/            bufferedwriter.write(34);
/* 144*/            if(((String) (obj2)).contains("%s") || ((String) (obj2)).contains("%f"))
/* 146*/                bufferedwriter.write(" formatted=\"false\"");
/* 148*/            bufferedwriter.write(62);
                    String s1;
/* 150*/            if((s1 = (String)map.get(s)) == null || collection.contains(s))
                    {
/* 152*/                flag = true;
/* 153*/                s1 = translateString(((String) (obj2)), ((String) (obj)));
                    }
/* 155*/            bufferedwriter.write(s1);
                }

/* 160*/        bufferedwriter.write("</resources>\n");
/* 161*/        bufferedwriter.flush();
/* 162*/        if(bufferedwriter != null)
/* 162*/            bufferedwriter.close();
/* 162*/        break MISSING_BLOCK_LABEL_383;
/* 133*/        JVM INSTR dup ;
/* 133*/        path;
/* 133*/        throwable;
/* 133*/        throw path;
/* 162*/        path;
/* 162*/        if(bufferedwriter != null)
/* 162*/            if(throwable != null)
/* 162*/                try
                        {
/* 162*/                    bufferedwriter.close();
                        }
                        // Misplaced declaration of an exception variable
/* 162*/                catch(Path path1)
                        {
/* 162*/                    throwable.addSuppressed(path1);
                        }
/* 162*/            else
/* 162*/                bufferedwriter.close();
/* 162*/        throw path;
/* 164*/        if(flag)
                {
/* 165*/            System.out.println("  Writing translations");
/* 166*/            Files.move(((Path) (obj1)), path1, new CopyOption[] {
/* 166*/                StandardCopyOption.REPLACE_EXISTING
                    });
/* 166*/            return;
                } else
                {
/* 168*/            Files.delete(((Path) (obj1)));
/* 170*/            return;
                }
            }

            static String translateString(String s, String s1)
                throws IOException
            {
/* 173*/        if("en".equals(s1))
/* 174*/            return s;
                Object obj;
/* 176*/        if((obj = (String)LANGUAGE_CODE_MASSAGINGS.get(s1)) != null)
/* 178*/            s1 = ((String) (obj));
/* 180*/        System.out.println((new StringBuilder("  Need translation for ")).append(s).toString());
/* 182*/        s1 = fetch(s1 = URI.create((new StringBuilder("https://www.googleapis.com/language/translate/v2?key=")).append(API_KEY).append("&q=").append(URLEncoder.encode(s, "UTF-8")).append("&source=en&target=").append(s1).toString()));
/* 187*/        if(!((Matcher) (obj = TRANSLATE_RESPONSE_PATTERN.matcher(s1))).find())
                {
/* 189*/            System.err.println("No translate result");
/* 190*/            System.err.println(s1);
/* 191*/            return s;
                } else
                {
/* 193*/            s = (s = (s = (s = (s = ((Matcher) (obj)).group(1)).replaceAll("&quot;", "\"")).replaceAll("&#39;", "'")).replaceAll("&amp;quot;", "\"")).replaceAll("&amp;#39;", "'");
/* 201*/            System.out.println((new StringBuilder("  Got translation ")).append(s).toString());
/* 202*/            return s;
                }
            }

            private static CharSequence fetch(URI uri)
                throws IOException
            {
                Object obj;
                Throwable throwable;
/* 206*/        (uri = uri.toURL().openConnection()).connect();
/* 208*/        obj = new StringBuilder(200);
/* 209*/        uri = new BufferedReader(new InputStreamReader(uri.getInputStream(), StandardCharsets.UTF_8));
/* 209*/        throwable = null;
/* 210*/        char ac[] = new char[8192];
                int i;
/* 212*/        while((i = uri.read(ac)) > 0) 
/* 213*/            ((StringBuilder) (obj)).append(ac, 0, i);
/* 215*/        uri.close();
/* 215*/        break MISSING_BLOCK_LABEL_115;
/* 209*/        JVM INSTR dup ;
                Throwable throwable1;
/* 209*/        throwable1;
/* 209*/        throwable;
/* 209*/        throw throwable1;
/* 215*/        obj;
/* 215*/        if(throwable != null)
/* 215*/            try
                    {
/* 215*/                uri.close();
                    }
                    // Misplaced declaration of an exception variable
/* 215*/            catch(URI uri)
                    {
/* 215*/                throwable.addSuppressed(uri);
                    }
/* 215*/        else
/* 215*/            uri.close();
/* 215*/        throw obj;
/* 216*/        return ((CharSequence) (obj));
            }

            private static Map readLines(Path path)
                throws IOException
            {
/* 220*/        if(Files.exists(path, new LinkOption[0]))
                {
/* 221*/            TreeMap treemap = new TreeMap();
/* 222*/            path = Files.readAllLines(path, StandardCharsets.UTF_8).iterator();
/* 222*/            do
                    {
/* 222*/                if(!path.hasNext())
/* 222*/                    break;
/* 222*/                Object obj = (String)path.next();
/* 223*/                if(((Matcher) (obj = ENTRY_PATTERN.matcher(((CharSequence) (obj))))).find())
/* 225*/                    treemap.put(((Matcher) (obj)).group(1), ((Matcher) (obj)).group(2));
                    } while(true);
/* 228*/            return treemap;
                } else
                {
/* 230*/            return Collections.emptyMap();
                }
            }

            private static final String API_KEY;
            private static final Pattern ENTRY_PATTERN;
            private static final Pattern STRINGS_FILE_NAME_PATTERN;
            private static final Pattern TRANSLATE_RESPONSE_PATTERN;
            private static final Pattern VALUES_DIR_PATTERN;
            private static final String APACHE_2_LICENSE = "<!--\n Copyright (C) 2014 ZXing authors\n\n Licensed under the Apache License, Version 2.0 (the \"License\");\n you may not use this file except in compliance with the License.\n You may obtain a copy of the License at\n\n      http://www.apache.org/licenses/LICENSE-2.0\n\n Unless required by applicable law or agreed to in writing, software\n distributed under the License is distributed on an \"AS IS\" BASIS,\n WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n See the License for the specific language governing permissions and\n limitations under the License.\n -->\n";
            private static final Map LANGUAGE_CODE_MASSAGINGS;

            static 
            {
/*  55*/        if((API_KEY = System.getProperty("translateAPI.key")) == null)
                {
/*  58*/            throw new IllegalArgumentException("translateAPI.key is not specified");
                } else
                {
/*  62*/            ENTRY_PATTERN = Pattern.compile("<string name=\"([^\"]+)\".*>([^<]+)</string>");
/*  63*/            STRINGS_FILE_NAME_PATTERN = Pattern.compile("values-(.+)");
/*  64*/            TRANSLATE_RESPONSE_PATTERN = Pattern.compile("translatedText\":\\s*\"([^\"]+)\"");
/*  65*/            VALUES_DIR_PATTERN = Pattern.compile("values-[a-z]{2}(-[a-zA-Z]{2,3})?");
/*  84*/            (LANGUAGE_CODE_MASSAGINGS = new HashMap(3)).put("zh-rCN", "zh-cn");
/*  87*/            LANGUAGE_CODE_MASSAGINGS.put("zh-rTW", "zh-tw");
                }
            }

}
