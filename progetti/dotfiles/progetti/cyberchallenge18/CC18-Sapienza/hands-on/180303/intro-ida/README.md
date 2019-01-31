Compile and run using MinGW on Windows:
```
> gcc -m32 main.c code.s -o main.exe
> strip -s main.exe
> main.exe a b c
```

Resources:
 - IDA Pro Book 2nd edition: [[PDF]](http://staff.ustc.edu.cn/~sycheng/ssat/books/The.IDA.Pro.Book.2ed.pdf)
 - IDA Freeware version (no debug, no decompiler): [[URL]](https://www.hex-rays.com/products/ida/support/download_freeware.shtml)
 - Quick reference sheet for common shortcuts in IDA Pro: [[PDF]](https://www.hex-rays.com/products/ida/support/freefiles/IDA_Pro_Shortcuts.pdf)
 - Slides on anti-disassmbler tricks: [[PDF]](https://github.com/CyberChallengeIT/CC18-Sapienza/raw/master/hands-on/20180303/intro-ida/anti-disassambler-tricks.pdf)
 - Chaper 15 "ANTI-DISASSEMBLY",  Michael Sikorski and Andrew Honig. Practical Malware Analysis: the Hands-On Guide to Dissecting Malicious Software. 2012. [[PDF]](http://venom630.free.fr/pdf/Practical_Malware_Analysis.pdf)
