// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PresenterFactory.java

package com.airhacks.afterburner.injection;

import java.util.ServiceLoader;
import java.util.function.Function;

public interface PresenterFactory
{

            public abstract Object instantiatePresenter(Class class1, Function function);

            public static Iterable discover()
            {
/*  76*/        return ServiceLoader.load(com/airhacks/afterburner/injection/PresenterFactory);
            }
}
