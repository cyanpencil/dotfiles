// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HtmlAssetTranslator.java

package com.google.zxing;

import java.io.IOException;
import java.nio.file.*;

// Referenced classes of package com.google.zxing:
//            HtmlAssetTranslator

static class 
    implements java.nio.file.
{

            public final boolean accept(Path path)
            {
/*  91*/        String s = path.getFileName().toString();
/*  92*/        return Files.isDirectory(path, new LinkOption[0]) && !Files.isSymbolicLink(path) && s.startsWith("html-") && !"html-en".equals(s);
            }

            public final volatile boolean accept(Object obj)
                throws IOException
            {
/*  88*/        return accept((Path)obj);
            }

            ()
            {
            }
}
