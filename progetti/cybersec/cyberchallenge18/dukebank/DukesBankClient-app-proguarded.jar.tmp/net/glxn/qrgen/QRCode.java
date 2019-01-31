// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   QRCode.java

package net.glxn.qrgen;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.*;
import java.util.HashMap;
import net.glxn.qrgen.exception.QRGenerationException;
import net.glxn.qrgen.image.ImageType;
import net.glxn.qrgen.vcard.VCard;

public class QRCode
{

            private QRCode(String s)
            {
/*  37*/        width = 125;
/*  39*/        height = 125;
/*  41*/        imageType = ImageType.PNG;
/*  44*/        text = s;
/*  45*/        qrWriter = new QRCodeWriter();
            }

            public static QRCode from(String s)
            {
/*  64*/        return new QRCode(s);
            }

            public static QRCode from(VCard vcard)
            {
/*  76*/        return new QRCode(vcard.toString());
            }

            public QRCode to(ImageType imagetype)
            {
/*  86*/        imageType = imagetype;
/*  87*/        return this;
            }

            public QRCode withSize(int i, int j)
            {
/*  98*/        width = i;
/*  99*/        height = j;
/* 100*/        return this;
            }

            public QRCode withCharset(String s)
            {
/* 110*/        return withHint(EncodeHintType.CHARACTER_SET, s);
            }

            public QRCode withErrorCorrection(ErrorCorrectionLevel errorcorrectionlevel)
            {
/* 120*/        return withHint(EncodeHintType.ERROR_CORRECTION, errorcorrectionlevel);
            }

            public QRCode withHint(EncodeHintType encodehinttype, Object obj)
            {
/* 129*/        hints.put(encodehinttype, obj);
/* 130*/        return this;
            }

            public File file()
            {
                Object obj;
/* 142*/        try
                {
/* 142*/            obj = createTempFile();
/* 143*/            MatrixToImageWriter.writeToPath(createMatrix(), imageType.toString(), ((File) (obj)).toPath());
                }
                // Misplaced declaration of an exception variable
/* 144*/        catch(Object obj)
                {
/* 145*/            throw new QRGenerationException("Failed to create QR image from text due to underlying exception", ((Throwable) (obj)));
                }
/* 148*/        return ((File) (obj));
            }

            public File file(String s)
            {
/* 163*/        try
                {
/* 163*/            s = createTempFile(s);
/* 164*/            MatrixToImageWriter.writeToPath(createMatrix(), imageType.toString(), s.toPath());
                }
                // Misplaced declaration of an exception variable
/* 165*/        catch(String s)
                {
/* 166*/            throw new QRGenerationException("Failed to create QR image from text due to underlying exception", s);
                }
/* 169*/        return s;
            }

            public ByteArrayOutputStream stream()
            {
/* 178*/        Object obj = new ByteArrayOutputStream();
/* 180*/        try
                {
/* 180*/            writeToStream(((OutputStream) (obj)));
                }
                // Misplaced declaration of an exception variable
/* 181*/        catch(Object obj)
                {
/* 182*/            throw new QRGenerationException("Failed to create QR image from text due to underlying exception", ((Throwable) (obj)));
                }
/* 185*/        return ((ByteArrayOutputStream) (obj));
            }

            public void writeTo(OutputStream outputstream)
            {
/* 195*/        try
                {
/* 195*/            writeToStream(outputstream);
/* 198*/            return;
                }
                // Misplaced declaration of an exception variable
/* 196*/        catch(OutputStream outputstream)
                {
/* 197*/            throw new QRGenerationException("Failed to create QR image from text due to underlying exception", outputstream);
                }
            }

            private void writeToStream(OutputStream outputstream)
                throws IOException, WriterException
            {
/* 202*/        MatrixToImageWriter.writeToStream(createMatrix(), imageType.toString(), outputstream);
            }

            private BitMatrix createMatrix()
                throws WriterException
            {
/* 206*/        return qrWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            }

            private File createTempFile()
                throws IOException
            {
                File file1;
/* 210*/        (file1 = File.createTempFile("QRCode", (new StringBuilder(".")).append(imageType.toString().toLowerCase()).toString())).deleteOnExit();
/* 212*/        return file1;
            }

            private File createTempFile(String s)
                throws IOException
            {
/* 216*/        (s = File.createTempFile(s, (new StringBuilder(".")).append(imageType.toString().toLowerCase()).toString())).deleteOnExit();
/* 218*/        return s;
            }

            private final String text;
            private final HashMap hints = new HashMap();
            Writer qrWriter;
            private int width;
            private int height;
            private ImageType imageType;
}
