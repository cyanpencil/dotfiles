// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DescriptorImpl.java

package org.glassfish.hk2.utilities;

import java.io.*;
import java.util.*;
import javax.inject.Singleton;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.general.GeneralUtilities;
import org.glassfish.hk2.utilities.reflection.ReflectionHelper;

public class DescriptorImpl
    implements Externalizable, Descriptor
{

            public DescriptorImpl()
            {
/* 103*/        scope = org/glassfish/hk2/api/PerLookup.getName();
/* 106*/        descriptorType = DescriptorType.CLASS;
/* 107*/        descriptorVisibility = DescriptorVisibility.NORMAL;
            }

            public DescriptorImpl(Descriptor descriptor)
            {
/* 103*/        scope = org/glassfish/hk2/api/PerLookup.getName();
/* 106*/        descriptorType = DescriptorType.CLASS;
/* 107*/        descriptorVisibility = DescriptorVisibility.NORMAL;
/* 128*/        name = descriptor.getName();
/* 129*/        scope = descriptor.getScope();
/* 130*/        implementation = descriptor.getImplementation();
/* 131*/        descriptorType = descriptor.getDescriptorType();
/* 132*/        descriptorVisibility = descriptor.getDescriptorVisibility();
/* 133*/        loader = descriptor.getLoader();
/* 134*/        rank = descriptor.getRanking();
/* 135*/        proxiable = descriptor.isProxiable();
/* 136*/        proxyForSameScope = descriptor.isProxyForSameScope();
/* 137*/        id = descriptor.getServiceId();
/* 138*/        locatorId = descriptor.getLocatorId();
/* 139*/        analysisName = descriptor.getClassAnalysisName();
/* 141*/        if(descriptor.getAdvertisedContracts() != null && !descriptor.getAdvertisedContracts().isEmpty())
                {
/* 142*/            contracts = new LinkedHashSet();
/* 143*/            contracts.addAll(descriptor.getAdvertisedContracts());
                }
/* 146*/        if(descriptor.getQualifiers() != null && !descriptor.getQualifiers().isEmpty())
                {
/* 147*/            qualifiers = new LinkedHashSet();
/* 148*/            qualifiers.addAll(descriptor.getQualifiers());
                }
/* 151*/        if(descriptor.getMetadata() != null && !descriptor.getMetadata().isEmpty())
                {
/* 152*/            metadatas = new LinkedHashMap();
/* 153*/            metadatas.putAll(ReflectionHelper.deepCopyMetadata(descriptor.getMetadata()));
                }
            }

            public DescriptorImpl(Set set, String s, String s1, String s2, Map map, Set set1, DescriptorType descriptortype, 
                    DescriptorVisibility descriptorvisibility, HK2Loader hk2loader, int i, Boolean boolean1, Boolean boolean2, String s3, Long long1, 
                    Long long2)
            {
/* 103*/        scope = org/glassfish/hk2/api/PerLookup.getName();
/* 106*/        descriptorType = DescriptorType.CLASS;
/* 107*/        descriptorVisibility = DescriptorVisibility.NORMAL;
/* 193*/        if(set != null && !set.isEmpty())
                {
/* 194*/            contracts = new LinkedHashSet();
/* 195*/            contracts.addAll(set);
                }
/* 198*/        implementation = s2;
/* 200*/        name = s;
/* 201*/        scope = s1;
/* 202*/        if(map != null && !map.isEmpty())
                {
/* 203*/            metadatas = new LinkedHashMap();
/* 204*/            metadatas.putAll(ReflectionHelper.deepCopyMetadata(map));
                }
/* 206*/        if(set1 != null && !set1.isEmpty())
                {
/* 207*/            qualifiers = new LinkedHashSet();
/* 208*/            qualifiers.addAll(set1);
                }
/* 211*/        descriptorType = descriptortype;
/* 212*/        descriptorVisibility = descriptorvisibility;
/* 213*/        id = long1;
/* 214*/        rank = i;
/* 215*/        proxiable = boolean1;
/* 216*/        proxyForSameScope = boolean2;
/* 217*/        analysisName = s3;
/* 218*/        locatorId = long2;
/* 219*/        loader = hk2loader;
            }

            public synchronized Set getAdvertisedContracts()
            {
/* 224*/        if(contracts == null)
/* 224*/            return EMPTY_CONTRACTS_SET;
/* 225*/        else
/* 225*/            return Collections.unmodifiableSet(contracts);
            }

            public synchronized void addAdvertisedContract(String s)
            {
/* 233*/        if(s == null)
/* 233*/            return;
/* 234*/        if(contracts == null)
/* 234*/            contracts = new LinkedHashSet();
/* 235*/        contracts.add(s);
            }

            public synchronized boolean removeAdvertisedContract(String s)
            {
/* 244*/        if(s == null || contracts == null)
/* 244*/            return false;
/* 245*/        else
/* 245*/            return contracts.remove(s);
            }

            public synchronized String getImplementation()
            {
/* 250*/        return implementation;
            }

            public synchronized void setImplementation(String s)
            {
/* 258*/        implementation = s;
            }

            public synchronized String getScope()
            {
/* 263*/        return scope;
            }

            public synchronized void setScope(String s)
            {
/* 271*/        scope = s;
            }

            public synchronized String getName()
            {
/* 276*/        return name;
            }

            public synchronized void setName(String s)
            {
/* 284*/        name = s;
            }

            public synchronized Set getQualifiers()
            {
/* 289*/        if(qualifiers == null)
/* 289*/            return EMPTY_QUALIFIER_SET;
/* 290*/        else
/* 290*/            return Collections.unmodifiableSet(qualifiers);
            }

            public synchronized void addQualifier(String s)
            {
/* 299*/        if(s == null)
/* 299*/            return;
/* 300*/        if(qualifiers == null)
/* 300*/            qualifiers = new LinkedHashSet();
/* 301*/        qualifiers.add(s);
            }

            public synchronized boolean removeQualifier(String s)
            {
/* 311*/        if(s == null)
/* 311*/            return false;
/* 312*/        if(qualifiers == null)
/* 312*/            return false;
/* 313*/        else
/* 313*/            return qualifiers.remove(s);
            }

            public synchronized DescriptorType getDescriptorType()
            {
/* 318*/        return descriptorType;
            }

            public synchronized void setDescriptorType(DescriptorType descriptortype)
            {
/* 326*/        if(descriptortype == null)
                {
/* 326*/            throw new IllegalArgumentException();
                } else
                {
/* 327*/            descriptorType = descriptortype;
/* 328*/            return;
                }
            }

            public synchronized DescriptorVisibility getDescriptorVisibility()
            {
/* 332*/        return descriptorVisibility;
            }

            public synchronized void setDescriptorVisibility(DescriptorVisibility descriptorvisibility)
            {
/* 340*/        if(descriptorvisibility == null)
                {
/* 340*/            throw new IllegalArgumentException();
                } else
                {
/* 341*/            descriptorVisibility = descriptorvisibility;
/* 342*/            return;
                }
            }

            public synchronized Map getMetadata()
            {
/* 346*/        if(metadatas == null)
/* 346*/            return EMPTY_METADATAS_MAP;
/* 347*/        else
/* 347*/            return Collections.unmodifiableMap(metadatas);
            }

            public synchronized void setMetadata(Map map)
            {
/* 360*/        if(metadatas == null)
/* 361*/            metadatas = new LinkedHashMap();
/* 364*/        else
/* 364*/            metadatas.clear();
/* 367*/        metadatas.putAll(ReflectionHelper.deepCopyMetadata(map));
            }

            public synchronized void addMetadata(Map map)
            {
/* 378*/        if(metadatas == null)
/* 378*/            metadatas = new LinkedHashMap();
/* 380*/        metadatas.putAll(ReflectionHelper.deepCopyMetadata(map));
            }

            public synchronized void addMetadata(String s, String s1)
            {
/* 391*/        if(metadatas == null)
/* 391*/            metadatas = new LinkedHashMap();
/* 392*/        ReflectionHelper.addMetadata(metadatas, s, s1);
            }

            public synchronized boolean removeMetadata(String s, String s1)
            {
/* 404*/        if(metadatas == null)
/* 404*/            return false;
/* 405*/        else
/* 405*/            return ReflectionHelper.removeMetadata(metadatas, s, s1);
            }

            public synchronized boolean removeAllMetadata(String s)
            {
/* 415*/        if(metadatas == null)
/* 415*/            return false;
/* 416*/        else
/* 416*/            return ReflectionHelper.removeAllMetadata(metadatas, s);
            }

            public synchronized void clearMetadata()
            {
/* 423*/        metadatas = null;
            }

            public synchronized HK2Loader getLoader()
            {
/* 431*/        return loader;
            }

            public synchronized void setLoader(HK2Loader hk2loader)
            {
/* 439*/        loader = hk2loader;
            }

            public synchronized int getRanking()
            {
/* 444*/        return rank;
            }

            public synchronized int setRanking(int i)
            {
/* 452*/        int j = rank;
/* 453*/        rank = i;
/* 454*/        return j;
            }

            public synchronized Long getServiceId()
            {
/* 459*/        return id;
            }

            public synchronized void setServiceId(Long long1)
            {
/* 467*/        id = long1;
            }

            public Boolean isProxiable()
            {
/* 472*/        return proxiable;
            }

            public void setProxiable(Boolean boolean1)
            {
/* 482*/        proxiable = boolean1;
            }

            public Boolean isProxyForSameScope()
            {
/* 487*/        return proxyForSameScope;
            }

            public void setProxyForSameScope(Boolean boolean1)
            {
/* 500*/        proxyForSameScope = boolean1;
            }

            public String getClassAnalysisName()
            {
/* 505*/        return analysisName;
            }

            public void setClassAnalysisName(String s)
            {
/* 517*/        analysisName = s;
            }

            public synchronized Long getLocatorId()
            {
/* 522*/        return locatorId;
            }

            public synchronized void setLocatorId(Long long1)
            {
/* 530*/        locatorId = long1;
            }

            public int hashCode()
            {
/* 534*/        int i = 0;
/* 536*/        if(implementation != null)
/* 537*/            i = 0 ^ implementation.hashCode();
/* 539*/        if(contracts != null)
                {
/* 540*/            for(Iterator iterator = contracts.iterator(); iterator.hasNext();)
                    {
/* 540*/                String s = (String)iterator.next();
/* 541*/                i ^= s.hashCode();
                    }

                }
/* 544*/        if(name != null)
/* 545*/            i ^= name.hashCode();
/* 547*/        if(scope != null)
/* 548*/            i ^= scope.hashCode();
/* 550*/        if(qualifiers != null)
                {
/* 551*/            for(Iterator iterator1 = qualifiers.iterator(); iterator1.hasNext();)
                    {
/* 551*/                String s1 = (String)iterator1.next();
/* 552*/                i ^= s1.hashCode();
                    }

                }
/* 555*/        if(descriptorType != null)
/* 556*/            i ^= descriptorType.hashCode();
/* 558*/        if(descriptorVisibility != null)
/* 559*/            i ^= descriptorVisibility.hashCode();
/* 561*/        if(metadatas != null)
                {
/* 562*/            for(Iterator iterator2 = metadatas.entrySet().iterator(); iterator2.hasNext();)
                    {
/* 562*/                Object obj = (java.util.Map.Entry)iterator2.next();
/* 563*/                i ^= ((String)((java.util.Map.Entry) (obj)).getKey()).hashCode();
/* 565*/                obj = ((List)((java.util.Map.Entry) (obj)).getValue()).iterator();
/* 565*/                while(((Iterator) (obj)).hasNext()) 
                        {
/* 565*/                    String s2 = (String)((Iterator) (obj)).next();
/* 566*/                    i ^= s2.hashCode();
                        }
                    }

                }
/* 570*/        if(proxiable != null)
/* 571*/            if(proxiable.booleanValue())
/* 572*/                i ^= 1;
/* 575*/            else
/* 575*/                i = ~i;
/* 578*/        if(proxyForSameScope != null)
/* 579*/            if(proxyForSameScope.booleanValue())
/* 580*/                i ^= 2;
/* 583*/            else
/* 583*/                i ^= -2;
/* 586*/        if(analysisName != null)
/* 587*/            i ^= analysisName.hashCode();
/* 590*/        return i;
            }

            private static boolean equalOrderedCollection(Collection collection, Collection collection1)
            {
/* 594*/        if(collection == collection1)
/* 594*/            return true;
/* 595*/        if(collection == null)
/* 595*/            return false;
/* 596*/        if(collection1 == null)
/* 596*/            return false;
/* 598*/        if(collection.size() != collection1.size())
/* 598*/            return false;
/* 600*/        Object aobj[] = collection.toArray();
/* 601*/        collection1 = ((Collection) (collection1.toArray()));
/* 603*/        for(int i = 0; i < collection.size(); i++)
/* 604*/            if(!GeneralUtilities.safeEquals(aobj[i], collection1[i]))
/* 604*/                return false;

/* 607*/        return true;
            }

            private static boolean equalMetadata(Map map, Map map1)
            {
/* 611*/        if(map == map1)
/* 611*/            return true;
/* 612*/        if(map == null)
/* 612*/            return false;
/* 613*/        if(map1 == null)
/* 613*/            return false;
/* 615*/        if(map.size() != map1.size())
/* 615*/            return false;
/* 617*/        for(map = map.entrySet().iterator(); map.hasNext();)
                {
                    Object obj;
/* 617*/            Object obj1 = (String)((java.util.Map.Entry) (obj = (java.util.Map.Entry)map.next())).getKey();
/* 619*/            obj = (List)((java.util.Map.Entry) (obj)).getValue();
/* 621*/            if((obj1 = (List)map1.get(obj1)) == null)
/* 622*/                return false;
/* 624*/            if(!equalOrderedCollection(((Collection) (obj)), ((Collection) (obj1))))
/* 624*/                return false;
                }

/* 627*/        return true;
            }

            public boolean equals(Object obj)
            {
/* 631*/        if(obj == null)
/* 631*/            return false;
/* 632*/        if(!(obj instanceof Descriptor))
/* 632*/            return false;
/* 633*/        obj = (Descriptor)obj;
/* 635*/        if(!GeneralUtilities.safeEquals(implementation, ((Descriptor) (obj)).getImplementation()))
/* 635*/            return false;
/* 636*/        if(!equalOrderedCollection(contracts != null ? ((Collection) (contracts)) : ((Collection) (EMPTY_CONTRACTS_SET)), ((Descriptor) (obj)).getAdvertisedContracts()))
/* 636*/            return false;
/* 637*/        if(!GeneralUtilities.safeEquals(name, ((Descriptor) (obj)).getName()))
/* 637*/            return false;
/* 638*/        if(!GeneralUtilities.safeEquals(scope, ((Descriptor) (obj)).getScope()))
/* 638*/            return false;
/* 639*/        if(!equalOrderedCollection(qualifiers != null ? ((Collection) (qualifiers)) : ((Collection) (EMPTY_QUALIFIER_SET)), ((Descriptor) (obj)).getQualifiers()))
/* 639*/            return false;
/* 640*/        if(!GeneralUtilities.safeEquals(descriptorType, ((Descriptor) (obj)).getDescriptorType()))
/* 640*/            return false;
/* 641*/        if(!GeneralUtilities.safeEquals(descriptorVisibility, ((Descriptor) (obj)).getDescriptorVisibility()))
/* 641*/            return false;
/* 642*/        if(!equalMetadata(metadatas != null ? metadatas : EMPTY_METADATAS_MAP, ((Descriptor) (obj)).getMetadata()))
/* 642*/            return false;
/* 643*/        if(!GeneralUtilities.safeEquals(proxiable, ((Descriptor) (obj)).isProxiable()))
/* 643*/            return false;
/* 644*/        if(!GeneralUtilities.safeEquals(proxyForSameScope, ((Descriptor) (obj)).isProxyForSameScope()))
/* 644*/            return false;
/* 645*/        return GeneralUtilities.safeEquals(analysisName, ((Descriptor) (obj)).getClassAnalysisName());
            }

            public static void pretty(StringBuffer stringbuffer, Descriptor descriptor)
            {
/* 657*/        if(stringbuffer == null || descriptor == null)
/* 657*/            return;
/* 659*/        stringbuffer.append((new StringBuilder("\n\timplementation=")).append(descriptor.getImplementation()).toString());
/* 661*/        if(descriptor.getName() != null)
/* 662*/            stringbuffer.append((new StringBuilder("\n\tname=")).append(descriptor.getName()).toString());
/* 665*/        stringbuffer.append("\n\tcontracts=");
/* 666*/        stringbuffer.append(ReflectionHelper.writeSet(descriptor.getAdvertisedContracts()));
/* 668*/        stringbuffer.append((new StringBuilder("\n\tscope=")).append(descriptor.getScope()).toString());
/* 670*/        stringbuffer.append("\n\tqualifiers=");
/* 671*/        stringbuffer.append(ReflectionHelper.writeSet(descriptor.getQualifiers()));
/* 673*/        stringbuffer.append((new StringBuilder("\n\tdescriptorType=")).append(descriptor.getDescriptorType()).toString());
/* 675*/        stringbuffer.append((new StringBuilder("\n\tdescriptorVisibility=")).append(descriptor.getDescriptorVisibility()).toString());
/* 677*/        stringbuffer.append("\n\tmetadata=");
/* 678*/        stringbuffer.append(ReflectionHelper.writeMetadata(descriptor.getMetadata()));
/* 680*/        stringbuffer.append((new StringBuilder("\n\trank=")).append(descriptor.getRanking()).toString());
/* 682*/        stringbuffer.append((new StringBuilder("\n\tloader=")).append(descriptor.getLoader()).toString());
/* 684*/        stringbuffer.append((new StringBuilder("\n\tproxiable=")).append(descriptor.isProxiable()).toString());
/* 686*/        stringbuffer.append((new StringBuilder("\n\tproxyForSameScope=")).append(descriptor.isProxyForSameScope()).toString());
/* 688*/        stringbuffer.append((new StringBuilder("\n\tanalysisName=")).append(descriptor.getClassAnalysisName()).toString());
/* 690*/        stringbuffer.append((new StringBuilder("\n\tid=")).append(descriptor.getServiceId()).toString());
/* 692*/        stringbuffer.append((new StringBuilder("\n\tlocatorId=")).append(descriptor.getLocatorId()).toString());
/* 694*/        stringbuffer.append((new StringBuilder("\n\tidentityHashCode=")).append(System.identityHashCode(descriptor)).toString());
            }

            public synchronized String toString()
            {
                StringBuffer stringbuffer;
/* 699*/        pretty(stringbuffer = new StringBuffer("Descriptor("), this);
/* 703*/        stringbuffer.append(")");
/* 705*/        return stringbuffer.toString();
            }

            public void writeObject(PrintWriter printwriter)
                throws IOException
            {
/* 717*/        printwriter.print("[");
/* 720*/        if(implementation != null)
/* 721*/            printwriter.print(implementation);
/* 724*/        printwriter.print("]");
/* 726*/        if(scope != null && scope.equals(javax/inject/Singleton.getName()))
/* 727*/            printwriter.print("S");
/* 730*/        boolean flag = true;
/* 731*/        if(contracts != null && implementation != null && !contracts.contains(implementation))
                {
/* 732*/            printwriter.print("-");
/* 733*/            flag = false;
                }
/* 736*/        printwriter.println();
/* 739*/        if(contracts != null && !contracts.isEmpty() && (!flag || contracts.size() > 1))
                {
/* 741*/            String s = flag ? implementation : null;
/* 743*/            printwriter.println((new StringBuilder("contract=")).append(ReflectionHelper.writeSet(contracts, s)).toString());
                }
/* 746*/        if(name != null)
/* 747*/            printwriter.println((new StringBuilder("name=")).append(name).toString());
/* 750*/        if(scope != null && !scope.equals(org/glassfish/hk2/api/PerLookup.getName()) && !scope.equals(javax/inject/Singleton.getName()))
/* 753*/            printwriter.println((new StringBuilder("scope=")).append(scope).toString());
/* 756*/        if(qualifiers != null && !qualifiers.isEmpty())
/* 757*/            printwriter.println((new StringBuilder("qualifier=")).append(ReflectionHelper.writeSet(qualifiers)).toString());
/* 760*/        if(descriptorType != null && descriptorType.equals(DescriptorType.PROVIDE_METHOD))
/* 761*/            printwriter.println("type=PROVIDE");
/* 764*/        if(descriptorVisibility != null && descriptorVisibility.equals(DescriptorVisibility.LOCAL))
/* 765*/            printwriter.println("visibility=LOCAL");
/* 768*/        if(rank != 0)
/* 769*/            printwriter.println((new StringBuilder("rank=")).append(rank).toString());
/* 772*/        if(proxiable != null)
/* 773*/            printwriter.println((new StringBuilder("proxiable=")).append(proxiable.booleanValue()).toString());
/* 776*/        if(proxyForSameScope != null)
/* 777*/            printwriter.println((new StringBuilder("proxyForSameScope=")).append(proxyForSameScope.booleanValue()).toString());
/* 780*/        if(analysisName != null && !"default".equals(analysisName))
/* 782*/            printwriter.println((new StringBuilder("analysis=")).append(analysisName).toString());
/* 785*/        if(metadatas != null && !metadatas.isEmpty())
/* 786*/            printwriter.println((new StringBuilder("metadata=")).append(ReflectionHelper.writeMetadata(metadatas)).toString());
/* 789*/        printwriter.println();
            }

            private void reinitialize()
            {
/* 793*/        contracts = null;
/* 794*/        implementation = null;
/* 795*/        name = null;
/* 796*/        scope = org/glassfish/hk2/api/PerLookup.getName();
/* 797*/        metadatas = null;
/* 798*/        qualifiers = null;
/* 799*/        descriptorType = DescriptorType.CLASS;
/* 800*/        descriptorVisibility = DescriptorVisibility.NORMAL;
/* 801*/        loader = null;
/* 802*/        rank = 0;
/* 803*/        proxiable = null;
/* 804*/        proxyForSameScope = null;
/* 805*/        analysisName = null;
/* 806*/        id = null;
/* 807*/        locatorId = null;
            }

            public boolean readObject(BufferedReader bufferedreader)
                throws IOException
            {
/* 820*/        reinitialize();
/* 822*/        Object obj = bufferedreader.readLine();
/* 824*/        boolean flag = false;
/* 825*/        for(; obj != null; obj = bufferedreader.readLine())
                {
/* 826*/            obj = ((String) (obj)).trim();
/* 828*/            if(!flag)
                    {
/* 829*/                if(!((String) (obj)).startsWith("["))
/* 830*/                    continue;
/* 830*/                flag = true;
                        int i;
/* 832*/                if((i = ((String) (obj)).indexOf(']', 1)) < 0)
/* 834*/                    throw new IOException((new StringBuilder("Start of implementation ends without ] character: ")).append(((String) (obj))).toString());
/* 838*/                if(i > 1)
/* 839*/                    implementation = ((String) (obj)).substring(1, i);
/* 842*/                String s = ((String) (obj)).substring(i + 1);
/* 844*/                obj = 0;
/* 845*/                if(s != null)
                        {
/* 846*/                    for(int j = 0; j < s.length(); j++)
                            {
                                char c;
/* 847*/                        if((c = s.charAt(j)) == 'S')
                                {
/* 850*/                            scope = javax/inject/Singleton.getName();
/* 850*/                            continue;
                                }
/* 852*/                        if(c == '-')
/* 853*/                            obj = 1;
                            }

                        }
/* 858*/                if(obj != 0 || implementation == null)
/* 859*/                    continue;
/* 859*/                if(contracts == null)
/* 859*/                    contracts = new LinkedHashSet();
/* 860*/                contracts.add(implementation);
/* 862*/                continue;
                    }
/* 865*/            if(((String) (obj)).length() <= 0)
/* 867*/                return true;
                    int k;
/* 870*/            if((k = ((String) (obj)).indexOf('=')) <= 0)
/* 874*/                continue;
/* 874*/            String s1 = ((String) (obj)).substring(0, k + 1);
/* 875*/            obj = ((String) (obj)).substring(k + 1);
/* 877*/            if(s1.equalsIgnoreCase("contract="))
                    {
/* 878*/                if(contracts == null)
/* 878*/                    contracts = new LinkedHashSet();
/* 879*/                ReflectionHelper.readSet(((String) (obj)), contracts);
/* 879*/                continue;
                    }
/* 881*/            if(s1.equals("qualifier="))
                    {
/* 882*/                LinkedHashSet linkedhashset = new LinkedHashSet();
/* 883*/                ReflectionHelper.readSet(((String) (obj)), linkedhashset);
/* 884*/                if(!linkedhashset.isEmpty())
/* 884*/                    qualifiers = linkedhashset;
/* 885*/                continue;
                    }
/* 886*/            if(s1.equals("name="))
                    {
/* 887*/                name = ((String) (obj));
/* 887*/                continue;
                    }
/* 889*/            if(s1.equals("scope="))
                    {
/* 890*/                scope = ((String) (obj));
/* 890*/                continue;
                    }
/* 892*/            if(s1.equals("type="))
                    {
/* 893*/                if(((String) (obj)).equals("PROVIDE"))
/* 894*/                    descriptorType = DescriptorType.PROVIDE_METHOD;
/* 894*/                continue;
                    }
/* 897*/            if(s1.equals("visibility="))
                    {
/* 898*/                if(((String) (obj)).equals("LOCAL"))
/* 899*/                    descriptorVisibility = DescriptorVisibility.LOCAL;
/* 899*/                continue;
                    }
/* 902*/            if(s1.equals("metadata="))
                    {
/* 903*/                LinkedHashMap linkedhashmap = new LinkedHashMap();
/* 904*/                ReflectionHelper.readMetadataMap(((String) (obj)), linkedhashmap);
/* 905*/                if(!linkedhashmap.isEmpty())
/* 905*/                    metadatas = linkedhashmap;
/* 906*/                continue;
                    }
/* 907*/            if(s1.equals("rank="))
                    {
/* 908*/                rank = Integer.parseInt(((String) (obj)));
/* 908*/                continue;
                    }
/* 910*/            if(s1.equals("proxiable="))
                    {
/* 911*/                proxiable = Boolean.valueOf(Boolean.parseBoolean(((String) (obj))));
/* 911*/                continue;
                    }
/* 913*/            if(s1.equals("proxyForSameScope="))
                    {
/* 914*/                proxyForSameScope = Boolean.valueOf(Boolean.parseBoolean(((String) (obj))));
/* 914*/                continue;
                    }
/* 916*/            if(s1.equals("analysis="))
/* 917*/                analysisName = ((String) (obj));
                }

/* 927*/        return flag;
            }

            public void writeExternal(ObjectOutput objectoutput)
                throws IOException
            {
/* 933*/        StringWriter stringwriter = new StringWriter();
/* 934*/        writeObject(new PrintWriter(stringwriter));
/* 935*/        objectoutput.writeObject(stringwriter.toString());
            }

            public void readExternal(ObjectInput objectinput)
                throws IOException, ClassNotFoundException
            {
/* 940*/        objectinput = (String)objectinput.readObject();
/* 942*/        readObject(new BufferedReader(new StringReader(objectinput)));
            }

            private static final long serialVersionUID = 0x15a0b3309a1b3434L;
            private static final String CONTRACT_KEY = "contract=";
            private static final String NAME_KEY = "name=";
            private static final String SCOPE_KEY = "scope=";
            private static final String QUALIFIER_KEY = "qualifier=";
            private static final String TYPE_KEY = "type=";
            private static final String VISIBILITY_KEY = "visibility=";
            private static final String METADATA_KEY = "metadata=";
            private static final String RANKING_KEY = "rank=";
            private static final String PROXIABLE_KEY = "proxiable=";
            private static final String PROXY_FOR_SAME_SCOPE_KEY = "proxyForSameScope=";
            private static final String ANALYSIS_KEY = "analysis=";
            private static final String PROVIDE_METHOD_DT = "PROVIDE";
            private static final String LOCAL_DT = "LOCAL";
            private static final String START_START = "[";
            private static final String END_START = "]";
            private static final char END_START_CHAR = 93;
            private static final String SINGLETON_DIRECTIVE = "S";
            private static final String NOT_IN_CONTRACTS_DIRECTIVE = "-";
            private static final char SINGLETON_DIRECTIVE_CHAR = 83;
            private static final char NOT_IN_CONTRACTS_DIRECTIVE_CHAR = 45;
            private static final Set EMPTY_CONTRACTS_SET = Collections.emptySet();
            private static final Set EMPTY_QUALIFIER_SET = Collections.emptySet();
            private static final Map EMPTY_METADATAS_MAP = Collections.emptyMap();
            private Set contracts;
            private String implementation;
            private String name;
            private String scope;
            private Map metadatas;
            private Set qualifiers;
            private DescriptorType descriptorType;
            private DescriptorVisibility descriptorVisibility;
            private transient HK2Loader loader;
            private int rank;
            private Boolean proxiable;
            private Boolean proxyForSameScope;
            private String analysisName;
            private Long id;
            private Long locatorId;

}
