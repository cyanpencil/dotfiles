// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GUIRunner.java

package com.google.zxing.client.j2se;

import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import javax.swing.*;
import javax.swing.text.JTextComponent;

// Referenced classes of package com.google.zxing.client.j2se:
//            BufferedImageLuminanceSource, ImageReader

public final class GUIRunner extends JFrame
{

            private GUIRunner()
            {
/*  58*/        textArea.setEditable(false);
/*  59*/        textArea.setMaximumSize(new Dimension(400, 200));
                JPanel jpanel;
/*  60*/        (jpanel = new JPanel()).setLayout(new FlowLayout());
/*  62*/        jpanel.add(imageLabel);
/*  63*/        jpanel.add(textArea);
/*  64*/        setTitle("ZXing");
/*  65*/        setSize(400, 400);
/*  66*/        setDefaultCloseOperation(3);
/*  67*/        setContentPane(jpanel);
/*  68*/        setLocationRelativeTo(null);
            }

            public static void main(String args[])
                throws MalformedURLException
            {
/*  72*/        (args = new GUIRunner()).setVisible(true);
/*  74*/        args.chooseImage();
            }

            private void chooseImage()
                throws MalformedURLException
            {
                Object obj;
/*  78*/        ((JFileChooser) (obj = new JFileChooser())).showOpenDialog(this);
/*  80*/        obj = ((JFileChooser) (obj)).getSelectedFile().toPath();
/*  81*/        ImageIcon imageicon = new ImageIcon(((Path) (obj)).toUri().toURL());
/*  82*/        setSize(imageicon.getIconWidth(), imageicon.getIconHeight() + 100);
/*  83*/        imageLabel.setIcon(imageicon);
/*  84*/        obj = getDecodeText(((Path) (obj)));
/*  85*/        textArea.setText(((String) (obj)));
            }

            private static String getDecodeText(Path path)
            {
/*  91*/        path = ImageReader.readImage(path.toUri());
                  goto _L1
/*  92*/        JVM INSTR dup ;
/*  93*/        path;
/*  93*/        toString();
/*  93*/        return;
_L1:
/*  95*/        path = new BufferedImageLuminanceSource(path);
/*  96*/        path = new BinaryBitmap(new HybridBinarizer(path));
/*  99*/        path = (new MultiFormatReader()).decode(path);
                  goto _L2
/* 100*/        JVM INSTR dup ;
/* 101*/        path;
/* 101*/        toString();
/* 101*/        return;
_L2:
/* 103*/        return String.valueOf(path.getText());
            }

            private final JLabel imageLabel = new JLabel();
            private final JTextComponent textArea = new JTextArea();
}
