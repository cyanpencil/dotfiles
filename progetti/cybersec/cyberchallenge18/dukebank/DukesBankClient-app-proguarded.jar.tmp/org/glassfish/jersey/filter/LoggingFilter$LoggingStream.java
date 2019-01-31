// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LoggingFilter.java

package org.glassfish.jersey.filter;

import java.io.*;
import java.nio.charset.Charset;

// Referenced classes of package org.glassfish.jersey.filter:
//            LoggingFilter

class b extends FilterOutputStream
{

            StringBuilder getStringBuilder(Charset charset)
            {
/* 330*/        byte abyte0[] = baos.toByteArray();
/* 332*/        b.append(new String(abyte0, 0, Math.min(abyte0.length, LoggingFilter.access$000(LoggingFilter.this)), charset));
/* 333*/        if(abyte0.length > LoggingFilter.access$000(LoggingFilter.this))
/* 334*/            b.append("...more...");
/* 336*/        b.append('\n');
/* 338*/        return b;
            }

            public void write(int i)
                throws IOException
            {
/* 343*/        if(baos.size() <= LoggingFilter.access$000(LoggingFilter.this))
/* 344*/            baos.write(i);
/* 346*/        out.write(i);
            }

            private final StringBuilder b;
            private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final LoggingFilter this$0;

            (StringBuilder stringbuilder, OutputStream outputstream)
            {
/* 322*/        this$0 = LoggingFilter.this;
/* 323*/        super(outputstream);
/* 325*/        b = stringbuilder;
            }
}
