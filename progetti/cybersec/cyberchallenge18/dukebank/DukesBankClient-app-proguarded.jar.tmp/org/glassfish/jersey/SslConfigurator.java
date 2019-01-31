// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SslConfigurator.java

package org.glassfish.jersey;

import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Logger;
import javax.net.ssl.*;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.PropertiesHelper;

public final class SslConfigurator
{

            public static SSLContext getDefaultContext()
            {
/* 286*/        return getDefaultContext(true);
            }

            public static SSLContext getDefaultContext(boolean flag)
            {
/* 300*/        if(flag)
/* 301*/            return (new SslConfigurator(true)).createSSLContext();
/* 303*/        else
/* 303*/            return DEFAULT_CONFIG_NO_PROPS.createSSLContext();
            }

            public static SslConfigurator newInstance()
            {
/* 316*/        return new SslConfigurator(false);
            }

            public static SslConfigurator newInstance(boolean flag)
            {
/* 328*/        return new SslConfigurator(flag);
            }

            private SslConfigurator(boolean flag)
            {
/* 275*/        securityProtocol = "TLS";
/* 332*/        if(flag)
/* 333*/            retrieve((Properties)AccessController.doPrivileged(PropertiesHelper.getSystemProperties()));
            }

            private SslConfigurator(SslConfigurator sslconfigurator)
            {
/* 275*/        securityProtocol = "TLS";
/* 338*/        keyStore = sslconfigurator.keyStore;
/* 339*/        trustStore = sslconfigurator.trustStore;
/* 340*/        trustStoreProvider = sslconfigurator.trustStoreProvider;
/* 341*/        keyStoreProvider = sslconfigurator.keyStoreProvider;
/* 342*/        trustStoreType = sslconfigurator.trustStoreType;
/* 343*/        keyStoreType = sslconfigurator.keyStoreType;
/* 344*/        trustStorePass = sslconfigurator.trustStorePass;
/* 345*/        keyStorePass = sslconfigurator.keyStorePass;
/* 346*/        keyPass = sslconfigurator.keyPass;
/* 347*/        trustStoreFile = sslconfigurator.trustStoreFile;
/* 348*/        keyStoreFile = sslconfigurator.keyStoreFile;
/* 349*/        trustStoreBytes = sslconfigurator.trustStoreBytes;
/* 350*/        keyStoreBytes = sslconfigurator.keyStoreBytes;
/* 351*/        trustManagerFactoryAlgorithm = sslconfigurator.trustManagerFactoryAlgorithm;
/* 352*/        keyManagerFactoryAlgorithm = sslconfigurator.keyManagerFactoryAlgorithm;
/* 353*/        trustManagerFactoryProvider = sslconfigurator.trustManagerFactoryProvider;
/* 354*/        keyManagerFactoryProvider = sslconfigurator.keyManagerFactoryProvider;
/* 355*/        securityProtocol = sslconfigurator.securityProtocol;
            }

            public final SslConfigurator copy()
            {
/* 364*/        return new SslConfigurator(this);
            }

            public final SslConfigurator trustStoreProvider(String s)
            {
/* 374*/        trustStoreProvider = s;
/* 375*/        return this;
            }

            public final SslConfigurator keyStoreProvider(String s)
            {
/* 385*/        keyStoreProvider = s;
/* 386*/        return this;
            }

            public final SslConfigurator trustStoreType(String s)
            {
/* 396*/        trustStoreType = s;
/* 397*/        return this;
            }

            public final SslConfigurator keyStoreType(String s)
            {
/* 407*/        keyStoreType = s;
/* 408*/        return this;
            }

            public final SslConfigurator trustStorePassword(String s)
            {
/* 418*/        trustStorePass = s.toCharArray();
/* 419*/        return this;
            }

            public final SslConfigurator keyStorePassword(String s)
            {
/* 429*/        keyStorePass = s.toCharArray();
/* 430*/        return this;
            }

            public final SslConfigurator keyStorePassword(char ac[])
            {
/* 440*/        keyStorePass = (char[])ac.clone();
/* 441*/        return this;
            }

            public final SslConfigurator keyPassword(String s)
            {
/* 451*/        keyPass = s.toCharArray();
/* 452*/        return this;
            }

            public final SslConfigurator keyPassword(char ac[])
            {
/* 462*/        keyPass = (char[])ac.clone();
/* 463*/        return this;
            }

            public final SslConfigurator trustStoreFile(String s)
            {
/* 477*/        trustStoreFile = s;
/* 478*/        trustStoreBytes = null;
/* 479*/        trustStore = null;
/* 480*/        return this;
            }

            public final SslConfigurator trustStoreBytes(byte abyte0[])
            {
/* 494*/        trustStoreBytes = (byte[])abyte0.clone();
/* 495*/        trustStoreFile = null;
/* 496*/        trustStore = null;
/* 497*/        return this;
            }

            public final SslConfigurator keyStoreFile(String s)
            {
/* 511*/        keyStoreFile = s;
/* 512*/        keyStoreBytes = null;
/* 513*/        keyStore = null;
/* 514*/        return this;
            }

            public final SslConfigurator keyStoreBytes(byte abyte0[])
            {
/* 528*/        keyStoreBytes = (byte[])abyte0.clone();
/* 529*/        keyStoreFile = null;
/* 530*/        keyStore = null;
/* 531*/        return this;
            }

            public final SslConfigurator trustManagerFactoryAlgorithm(String s)
            {
/* 541*/        trustManagerFactoryAlgorithm = s;
/* 542*/        return this;
            }

            public final SslConfigurator keyManagerFactoryAlgorithm(String s)
            {
/* 552*/        keyManagerFactoryAlgorithm = s;
/* 553*/        return this;
            }

            public final SslConfigurator trustManagerFactoryProvider(String s)
            {
/* 563*/        trustManagerFactoryAlgorithm = s;
/* 564*/        return this;
            }

            public final SslConfigurator keyManagerFactoryProvider(String s)
            {
/* 574*/        keyManagerFactoryAlgorithm = s;
/* 575*/        return this;
            }

            public final SslConfigurator securityProtocol(String s)
            {
/* 585*/        securityProtocol = s;
/* 586*/        return this;
            }

            final KeyStore getKeyStore()
            {
/* 595*/        return keyStore;
            }

            public final SslConfigurator keyStore(KeyStore keystore)
            {
/* 609*/        keyStore = keystore;
/* 610*/        keyStoreFile = null;
/* 611*/        keyStoreBytes = null;
/* 612*/        return this;
            }

            final KeyStore getTrustStore()
            {
/* 625*/        return trustStore;
            }

            public final SslConfigurator trustStore(KeyStore keystore)
            {
/* 635*/        trustStore = keystore;
/* 636*/        trustStoreFile = null;
/* 637*/        trustStoreBytes = null;
/* 638*/        return this;
            }

            public final SSLContext createSSLContext()
            {
                Object obj;
                KeyManagerFactory keymanagerfactory;
                Object obj1;
/* 647*/        obj = null;
/* 648*/        keymanagerfactory = null;
/* 650*/        if((obj1 = keyStore) != null || keyStoreBytes == null && keyStoreFile == null)
/* 653*/            break MISSING_BLOCK_LABEL_276;
                Object obj2;
/* 653*/        if(keyStoreProvider != null)
/* 654*/            obj1 = KeyStore.getInstance(keyStoreType == null ? KeyStore.getDefaultType() : keyStoreType, keyStoreProvider);
/* 657*/        else
/* 657*/            obj1 = KeyStore.getInstance(keyStoreType == null ? KeyStore.getDefaultType() : keyStoreType);
/* 659*/        obj2 = null;
/* 661*/        if(keyStoreBytes != null)
/* 662*/            obj2 = new ByteArrayInputStream(keyStoreBytes);
/* 663*/        else
/* 663*/        if(!keyStoreFile.equals("NONE"))
/* 664*/            obj2 = new FileInputStream(keyStoreFile);
/* 666*/        ((KeyStore) (obj1)).load(((InputStream) (obj2)), keyStorePass);
/* 669*/        try
                {
/* 669*/            if(obj2 != null)
/* 670*/                ((InputStream) (obj2)).close();
                }
/* 672*/        catch(IOException _ex) { }
/* 674*/        break MISSING_BLOCK_LABEL_276;
/* 668*/        obj1;
/* 669*/        try
                {
/* 669*/            if(obj2 != null)
/* 670*/                ((InputStream) (obj2)).close();
                }
/* 672*/        catch(IOException _ex) { }
/* 673*/        throw obj1;
                Object obj3;
/* 675*/        obj3;
/* 676*/        throw new IllegalStateException(LocalizationMessages.SSL_KS_IMPL_NOT_FOUND(), ((Throwable) (obj3)));
/* 677*/        obj3;
/* 678*/        throw new IllegalStateException(LocalizationMessages.SSL_KS_CERT_LOAD_ERROR(), ((Throwable) (obj3)));
/* 679*/        obj3;
/* 680*/        throw new IllegalStateException(LocalizationMessages.SSL_KS_FILE_NOT_FOUND(keyStoreFile), ((Throwable) (obj3)));
/* 681*/        obj3;
/* 682*/        throw new IllegalStateException(LocalizationMessages.SSL_KS_LOAD_ERROR(keyStoreFile), ((Throwable) (obj3)));
/* 683*/        obj3;
/* 684*/        throw new IllegalStateException(LocalizationMessages.SSL_KS_PROVIDERS_NOT_REGISTERED(), ((Throwable) (obj3)));
/* 685*/        obj3;
/* 686*/        throw new IllegalStateException(LocalizationMessages.SSL_KS_INTEGRITY_ALGORITHM_NOT_FOUND(), ((Throwable) (obj3)));
/* 689*/        if(obj1 != null)
                {
/* 690*/            if((obj3 = keyManagerFactoryAlgorithm) == null)
/* 692*/                obj3 = (String)AccessController.doPrivileged(PropertiesHelper.getSystemProperty("ssl.keyManagerFactory.algorithm", KeyManagerFactory.getDefaultAlgorithm()));
/* 696*/            try
                    {
/* 696*/                if(keyManagerFactoryProvider != null)
/* 697*/                    keymanagerfactory = KeyManagerFactory.getInstance(((String) (obj3)), keyManagerFactoryProvider);
/* 699*/                else
/* 699*/                    keymanagerfactory = KeyManagerFactory.getInstance(((String) (obj3)));
                        char ac[];
/* 701*/                if((ac = keyPass == null ? keyStorePass : keyPass) != null)
                        {
/* 703*/                    keymanagerfactory.init(((KeyStore) (obj1)), ac);
                        } else
                        {
/* 705*/                    obj1 = keyStoreProvider == null ? ((Object) (keyStoreBytes == null ? ((Object) (keyStoreFile)) : ((Object) (LocalizationMessages.SSL_KMF_NO_PASSWORD_FOR_BYTE_BASED_KS())))) : ((Object) (LocalizationMessages.SSL_KMF_NO_PASSWORD_FOR_PROVIDER_BASED_KS()));
/* 710*/                    LOGGER.config(LocalizationMessages.SSL_KMF_NO_PASSWORD_SET(obj1));
/* 711*/                    keymanagerfactory = null;
                        }
                    }
/* 713*/            catch(KeyStoreException keystoreexception1)
                    {
/* 714*/                throw new IllegalStateException(LocalizationMessages.SSL_KMF_INIT_FAILED(), keystoreexception1);
                    }
/* 715*/            catch(UnrecoverableKeyException unrecoverablekeyexception)
                    {
/* 716*/                throw new IllegalStateException(LocalizationMessages.SSL_KMF_UNRECOVERABLE_KEY(), unrecoverablekeyexception);
                    }
/* 717*/            catch(NoSuchAlgorithmException nosuchalgorithmexception1)
                    {
/* 718*/                throw new IllegalStateException(LocalizationMessages.SSL_KMF_ALGORITHM_NOT_SUPPORTED(), nosuchalgorithmexception1);
                    }
/* 719*/            catch(NoSuchProviderException nosuchproviderexception1)
                    {
/* 720*/                throw new IllegalStateException(LocalizationMessages.SSL_KMF_PROVIDER_NOT_REGISTERED(), nosuchproviderexception1);
                    }
                }
/* 724*/        if((obj3 = trustStore) != null || trustStoreBytes == null && trustStoreFile == null)
/* 727*/            break MISSING_BLOCK_LABEL_748;
                Object obj4;
/* 727*/        if(trustStoreProvider != null)
/* 728*/            obj3 = KeyStore.getInstance(trustStoreType == null ? KeyStore.getDefaultType() : trustStoreType, trustStoreProvider);
/* 731*/        else
/* 731*/            obj3 = KeyStore.getInstance(trustStoreType == null ? KeyStore.getDefaultType() : trustStoreType);
/* 734*/        obj4 = null;
/* 736*/        if(trustStoreBytes != null)
/* 737*/            obj4 = new ByteArrayInputStream(trustStoreBytes);
/* 738*/        else
/* 738*/        if(!trustStoreFile.equals("NONE"))
/* 739*/            obj4 = new FileInputStream(trustStoreFile);
/* 741*/        ((KeyStore) (obj3)).load(((InputStream) (obj4)), trustStorePass);
/* 744*/        try
                {
/* 744*/            if(obj4 != null)
/* 745*/                ((InputStream) (obj4)).close();
                }
/* 747*/        catch(IOException _ex) { }
/* 749*/        break MISSING_BLOCK_LABEL_748;
/* 743*/        obj;
/* 744*/        try
                {
/* 744*/            if(obj4 != null)
/* 745*/                ((InputStream) (obj4)).close();
                }
/* 747*/        catch(IOException _ex) { }
/* 748*/        throw obj;
                Object obj5;
/* 750*/        obj5;
/* 751*/        throw new IllegalStateException(LocalizationMessages.SSL_TS_IMPL_NOT_FOUND(), ((Throwable) (obj5)));
/* 752*/        obj5;
/* 753*/        throw new IllegalStateException(LocalizationMessages.SSL_TS_CERT_LOAD_ERROR(), ((Throwable) (obj5)));
/* 754*/        obj5;
/* 755*/        throw new IllegalStateException(LocalizationMessages.SSL_TS_FILE_NOT_FOUND(trustStoreFile), ((Throwable) (obj5)));
/* 756*/        obj5;
/* 757*/        throw new IllegalStateException(LocalizationMessages.SSL_TS_LOAD_ERROR(trustStoreFile), ((Throwable) (obj5)));
/* 758*/        obj5;
/* 759*/        throw new IllegalStateException(LocalizationMessages.SSL_TS_PROVIDERS_NOT_REGISTERED(), ((Throwable) (obj5)));
/* 760*/        obj5;
/* 761*/        throw new IllegalStateException(LocalizationMessages.SSL_TS_INTEGRITY_ALGORITHM_NOT_FOUND(), ((Throwable) (obj5)));
/* 764*/        if(obj3 != null)
                {
                    String s;
/* 765*/            if((s = trustManagerFactoryAlgorithm) == null)
/* 767*/                s = (String)AccessController.doPrivileged(PropertiesHelper.getSystemProperty("ssl.trustManagerFactory.algorithm", TrustManagerFactory.getDefaultAlgorithm()));
/* 772*/            try
                    {
/* 772*/                if(trustManagerFactoryProvider != null)
/* 773*/                    obj = TrustManagerFactory.getInstance(s, trustManagerFactoryProvider);
/* 775*/                else
/* 775*/                    obj = TrustManagerFactory.getInstance(s);
/* 777*/                ((TrustManagerFactory) (obj)).init(((KeyStore) (obj3)));
                    }
/* 778*/            catch(KeyStoreException keystoreexception)
                    {
/* 779*/                throw new IllegalStateException(LocalizationMessages.SSL_TMF_INIT_FAILED(), keystoreexception);
                    }
/* 780*/            catch(NoSuchAlgorithmException nosuchalgorithmexception)
                    {
/* 781*/                throw new IllegalStateException(LocalizationMessages.SSL_TMF_ALGORITHM_NOT_SUPPORTED(), nosuchalgorithmexception);
                    }
/* 782*/            catch(NoSuchProviderException nosuchproviderexception)
                    {
/* 783*/                throw new IllegalStateException(LocalizationMessages.SSL_TMF_PROVIDER_NOT_REGISTERED(), nosuchproviderexception);
                    }
                }
/* 788*/        String s1 = "TLS";
/* 789*/        if(securityProtocol != null)
/* 790*/            s1 = securityProtocol;
/* 792*/        (nosuchproviderexception = SSLContext.getInstance(s1)).init(keymanagerfactory == null ? null : keymanagerfactory.getKeyManagers(), obj == null ? null : ((TrustManagerFactory) (obj)).getTrustManagers(), null);
/* 797*/        return nosuchproviderexception;
/* 798*/        s1;
/* 799*/        throw new IllegalStateException(LocalizationMessages.SSL_CTX_INIT_FAILED(), s1);
/* 800*/        s1;
/* 801*/        throw new IllegalStateException(LocalizationMessages.SSL_CTX_ALGORITHM_NOT_SUPPORTED(), s1);
            }

            public final SslConfigurator retrieve(Properties properties)
            {
/* 812*/        trustStoreProvider = properties.getProperty("javax.net.ssl.trustStoreProvider");
/* 813*/        keyStoreProvider = properties.getProperty("javax.net.ssl.keyStoreProvider");
/* 815*/        trustManagerFactoryProvider = properties.getProperty("ssl.trustManagerFactory.provider");
/* 816*/        keyManagerFactoryProvider = properties.getProperty("ssl.keyManagerFactory.provider");
/* 818*/        trustStoreType = properties.getProperty("javax.net.ssl.trustStoreType");
/* 819*/        keyStoreType = properties.getProperty("javax.net.ssl.keyStoreType");
/* 821*/        if(properties.getProperty("javax.net.ssl.trustStorePassword") != null)
/* 822*/            trustStorePass = properties.getProperty("javax.net.ssl.trustStorePassword").toCharArray();
/* 824*/        else
/* 824*/            trustStorePass = null;
/* 827*/        if(properties.getProperty("javax.net.ssl.keyStorePassword") != null)
/* 828*/            keyStorePass = properties.getProperty("javax.net.ssl.keyStorePassword").toCharArray();
/* 830*/        else
/* 830*/            keyStorePass = null;
/* 833*/        trustStoreFile = properties.getProperty("javax.net.ssl.trustStore");
/* 834*/        keyStoreFile = properties.getProperty("javax.net.ssl.keyStore");
/* 836*/        trustStoreBytes = null;
/* 837*/        keyStoreBytes = null;
/* 839*/        trustStore = null;
/* 840*/        keyStore = null;
/* 842*/        securityProtocol = "TLS";
/* 844*/        return this;
            }

            public final boolean equals(Object obj)
            {
/* 849*/        if(this == obj)
/* 850*/            return true;
/* 852*/        if(obj == null || getClass() != obj.getClass())
/* 853*/            return false;
/* 856*/        obj = (SslConfigurator)obj;
/* 858*/        if(keyManagerFactoryAlgorithm == null ? ((SslConfigurator) (obj)).keyManagerFactoryAlgorithm != null : !keyManagerFactoryAlgorithm.equals(((SslConfigurator) (obj)).keyManagerFactoryAlgorithm))
/* 860*/            return false;
/* 862*/        if(keyManagerFactoryProvider == null ? ((SslConfigurator) (obj)).keyManagerFactoryProvider != null : !keyManagerFactoryProvider.equals(((SslConfigurator) (obj)).keyManagerFactoryProvider))
/* 864*/            return false;
/* 866*/        if(!Arrays.equals(keyPass, ((SslConfigurator) (obj)).keyPass))
/* 867*/            return false;
/* 869*/        if(keyStore == null ? ((SslConfigurator) (obj)).keyStore != null : !keyStore.equals(((SslConfigurator) (obj)).keyStore))
/* 870*/            return false;
/* 872*/        if(!Arrays.equals(keyStoreBytes, ((SslConfigurator) (obj)).keyStoreBytes))
/* 873*/            return false;
/* 875*/        if(keyStoreFile == null ? ((SslConfigurator) (obj)).keyStoreFile != null : !keyStoreFile.equals(((SslConfigurator) (obj)).keyStoreFile))
/* 876*/            return false;
/* 878*/        if(!Arrays.equals(keyStorePass, ((SslConfigurator) (obj)).keyStorePass))
/* 879*/            return false;
/* 881*/        if(keyStoreProvider == null ? ((SslConfigurator) (obj)).keyStoreProvider != null : !keyStoreProvider.equals(((SslConfigurator) (obj)).keyStoreProvider))
/* 882*/            return false;
/* 884*/        if(keyStoreType == null ? ((SslConfigurator) (obj)).keyStoreType != null : !keyStoreType.equals(((SslConfigurator) (obj)).keyStoreType))
/* 885*/            return false;
/* 887*/        if(securityProtocol == null ? ((SslConfigurator) (obj)).securityProtocol != null : !securityProtocol.equals(((SslConfigurator) (obj)).securityProtocol))
/* 888*/            return false;
/* 890*/        if(trustManagerFactoryAlgorithm == null ? ((SslConfigurator) (obj)).trustManagerFactoryAlgorithm != null : !trustManagerFactoryAlgorithm.equals(((SslConfigurator) (obj)).trustManagerFactoryAlgorithm))
/* 892*/            return false;
/* 894*/        if(trustManagerFactoryProvider == null ? ((SslConfigurator) (obj)).trustManagerFactoryProvider != null : !trustManagerFactoryProvider.equals(((SslConfigurator) (obj)).trustManagerFactoryProvider))
/* 896*/            return false;
/* 898*/        if(trustStore == null ? ((SslConfigurator) (obj)).trustStore != null : !trustStore.equals(((SslConfigurator) (obj)).trustStore))
/* 899*/            return false;
/* 901*/        if(!Arrays.equals(trustStoreBytes, ((SslConfigurator) (obj)).trustStoreBytes))
/* 902*/            return false;
/* 904*/        if(trustStoreFile == null ? ((SslConfigurator) (obj)).trustStoreFile != null : !trustStoreFile.equals(((SslConfigurator) (obj)).trustStoreFile))
/* 905*/            return false;
/* 907*/        if(!Arrays.equals(trustStorePass, ((SslConfigurator) (obj)).trustStorePass))
/* 908*/            return false;
/* 910*/        if(trustStoreProvider == null ? ((SslConfigurator) (obj)).trustStoreProvider != null : !trustStoreProvider.equals(((SslConfigurator) (obj)).trustStoreProvider))
/* 911*/            return false;
/* 913*/        return trustStoreType == null ? ((SslConfigurator) (obj)).trustStoreType == null : trustStoreType.equals(((SslConfigurator) (obj)).trustStoreType);
            }

            public final int hashCode()
            {
/* 922*/        int i = keyStore == null ? 0 : keyStore.hashCode();
/* 923*/        i = i * 31 + (trustStore == null ? 0 : trustStore.hashCode());
/* 924*/        i = i * 31 + (trustStoreProvider == null ? 0 : trustStoreProvider.hashCode());
/* 925*/        i = i * 31 + (keyStoreProvider == null ? 0 : keyStoreProvider.hashCode());
/* 926*/        i = i * 31 + (trustStoreType == null ? 0 : trustStoreType.hashCode());
/* 927*/        i = i * 31 + (keyStoreType == null ? 0 : keyStoreType.hashCode());
/* 928*/        i = i * 31 + (trustStorePass == null ? 0 : Arrays.hashCode(trustStorePass));
/* 929*/        i = i * 31 + (keyStorePass == null ? 0 : Arrays.hashCode(keyStorePass));
/* 930*/        i = i * 31 + (keyPass == null ? 0 : Arrays.hashCode(keyPass));
/* 931*/        i = i * 31 + (trustStoreFile == null ? 0 : trustStoreFile.hashCode());
/* 932*/        i = i * 31 + (keyStoreFile == null ? 0 : keyStoreFile.hashCode());
/* 933*/        i = i * 31 + (trustStoreBytes == null ? 0 : Arrays.hashCode(trustStoreBytes));
/* 934*/        i = i * 31 + (keyStoreBytes == null ? 0 : Arrays.hashCode(keyStoreBytes));
/* 935*/        i = i * 31 + (trustManagerFactoryAlgorithm == null ? 0 : trustManagerFactoryAlgorithm.hashCode());
/* 936*/        i = i * 31 + (keyManagerFactoryAlgorithm == null ? 0 : keyManagerFactoryAlgorithm.hashCode());
/* 937*/        i = i * 31 + (trustManagerFactoryProvider == null ? 0 : trustManagerFactoryProvider.hashCode());
/* 938*/        i = i * 31 + (keyManagerFactoryProvider == null ? 0 : keyManagerFactoryProvider.hashCode());
/* 939*/        return i = i * 31 + (securityProtocol == null ? 0 : securityProtocol.hashCode());
            }

            public static final String TRUST_STORE_PROVIDER = "javax.net.ssl.trustStoreProvider";
            public static final String KEY_STORE_PROVIDER = "javax.net.ssl.keyStoreProvider";
            public static final String TRUST_STORE_FILE = "javax.net.ssl.trustStore";
            public static final String KEY_STORE_FILE = "javax.net.ssl.keyStore";
            public static final String TRUST_STORE_PASSWORD = "javax.net.ssl.trustStorePassword";
            public static final String KEY_STORE_PASSWORD = "javax.net.ssl.keyStorePassword";
            public static final String TRUST_STORE_TYPE = "javax.net.ssl.trustStoreType";
            public static final String KEY_STORE_TYPE = "javax.net.ssl.keyStoreType";
            public static final String KEY_MANAGER_FACTORY_ALGORITHM = "ssl.keyManagerFactory.algorithm";
            public static final String KEY_MANAGER_FACTORY_PROVIDER = "ssl.keyManagerFactory.provider";
            public static final String TRUST_MANAGER_FACTORY_ALGORITHM = "ssl.trustManagerFactory.algorithm";
            public static final String TRUST_MANAGER_FACTORY_PROVIDER = "ssl.trustManagerFactory.provider";
            private static final SslConfigurator DEFAULT_CONFIG_NO_PROPS = new SslConfigurator(false);
            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/SslConfigurator.getName());
            private KeyStore keyStore;
            private KeyStore trustStore;
            private String trustStoreProvider;
            private String keyStoreProvider;
            private String trustStoreType;
            private String keyStoreType;
            private char trustStorePass[];
            private char keyStorePass[];
            private char keyPass[];
            private String trustStoreFile;
            private String keyStoreFile;
            private byte trustStoreBytes[];
            private byte keyStoreBytes[];
            private String trustManagerFactoryAlgorithm;
            private String keyManagerFactoryAlgorithm;
            private String trustManagerFactoryProvider;
            private String keyManagerFactoryProvider;
            private String securityProtocol;

}
