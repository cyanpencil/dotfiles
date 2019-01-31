// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StringsResourceTranslator.java

package com.google.zxing;

import java.io.IOException;
import java.nio.file.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing:
//            StringsResourceTranslator

static class 
    implements java.nio.file.._cls1
{

            public final boolean accept(Path path)
            {
/* 101*/        return Files.isDirectory(path, new LinkOption[0]) && !Files.isSymbolicLink(path) && StringsResourceTranslator.access$000().matcher(path.getFileName().toString()).matches();
            }

            public final volatile boolean accept(Object obj)
                throws IOException
            {
/*  98*/        return accept((Path)obj);
            }

            ()
            {
            }
}
