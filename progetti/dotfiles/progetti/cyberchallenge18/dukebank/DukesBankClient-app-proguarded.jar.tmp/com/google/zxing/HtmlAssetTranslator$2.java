// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HtmlAssetTranslator.java

package com.google.zxing;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.Collection;

// Referenced classes of package com.google.zxing:
//            HtmlAssetTranslator

static class val.filesToTranslate
    implements java.nio.file.
{

            public final boolean accept(Path path)
            {
/* 136*/        return (path = path.getFileName().toString()).endsWith(".html") && (val$filesToTranslate.isEmpty() || val$filesToTranslate.contains(path));
            }

            public final volatile boolean accept(Object obj)
                throws IOException
            {
/* 133*/        return accept((Path)obj);
            }

            final Collection val$filesToTranslate;

            (Collection collection)
            {
/* 133*/        val$filesToTranslate = collection;
/* 133*/        super();
            }
}
