// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UriParser.java

package org.glassfish.jersey.uri.internal;

import org.glassfish.jersey.internal.LocalizationMessages;

// Referenced classes of package org.glassfish.jersey.uri.internal:
//            CharacterIterator

class UriParser
{

            UriParser(String s)
            {
/*  76*/        input = s;
            }

            private String parseComponentWithIP(String s, boolean flag)
            {
/*  80*/        return parseComponent(s, flag, true);
            }

            private String parseComponent(String s, boolean flag)
            {
/*  85*/        return parseComponent(s, flag, false);
            }

            private String parseComponent(String s, boolean flag, boolean flag1)
            {
/* 100*/        int i = 0;
/* 101*/        int j = 0;
/* 103*/        StringBuilder stringbuilder = new StringBuilder();
/* 105*/        boolean flag2 = false;
/* 106*/        Character character = Character.valueOf(ci.current());
/* 107*/        do
                {
/* 107*/            if(flag2)
/* 108*/                break;
/* 108*/            if(character.charValue() == '{')
                    {
/* 109*/                i++;
/* 110*/                stringbuilder.append(character);
                    } else
/* 111*/            if(character.charValue() == '}')
                    {
/* 112*/                i--;
/* 113*/                stringbuilder.append(character);
                    } else
/* 114*/            if(flag1 && character.charValue() == '[')
                    {
/* 115*/                j++;
/* 116*/                stringbuilder.append(character);
                    } else
/* 117*/            if(flag1 && character.charValue() == ']')
                    {
/* 118*/                j--;
/* 119*/                stringbuilder.append(character);
                    } else
                    {
/* 122*/                if(s != null && s.indexOf(character.charValue()) >= 0 && (!flag1 || j == 0) && i == 0)
/* 124*/                    if(stringbuilder.length() == 0)
/* 124*/                        return null;
/* 124*/                    else
/* 124*/                        return stringbuilder.toString();
/* 126*/                stringbuilder.append(character);
                    }
/* 128*/            if(!(flag2 = !ci.hasNext()))
/* 130*/                character = Character.valueOf(ci.next());
                } while(true);
/* 133*/        if(flag)
                {
/* 134*/            if(stringbuilder.length() == 0)
/* 134*/                return null;
/* 134*/            else
/* 134*/                return stringbuilder.toString();
                } else
                {
/* 136*/            throw new IllegalArgumentException(LocalizationMessages.URI_PARSER_COMPONENT_DELIMITER(s, Integer.valueOf(ci.pos())));
                }
            }

            public void parse()
            {
/* 144*/        parserExecuted = true;
/* 145*/        ci = new CharacterIterator(input);
/* 146*/        if(!ci.hasNext())
                {
/* 148*/            path = "";
/* 149*/            ssp = "";
/* 150*/            return;
                }
/* 152*/        ci.next();
/* 153*/        String s = parseComponent(":/?#", true);
/* 155*/        if(ci.hasNext())
/* 156*/            ssp = ci.getInput().substring(ci.pos() + 1);
/* 159*/        opaque = false;
/* 160*/        if(ci.current() == ':')
                {
/* 162*/            if(s == null)
/* 163*/                throw new IllegalArgumentException(LocalizationMessages.URI_PARSER_SCHEME_EXPECTED(Integer.valueOf(ci.pos()), input));
/* 165*/            scheme = s;
/* 166*/            if(!ci.hasNext())
                    {
/* 168*/                path = "";
/* 169*/                ssp = "";
/* 170*/                return;
                    }
                    char c;
/* 172*/            if((c = ci.next()) == '/')
                    {
/* 175*/                parseHierarchicalUri();
                    } else
                    {
/* 179*/                opaque = true;
/* 181*/                return;
                    }
                } else
                {
/* 182*/            ci.setPosition(0);
/* 184*/            if(ci.current() == '/')
                    {
/* 185*/                parseHierarchicalUri();
/* 185*/                return;
                    }
/* 187*/            parsePath();
                }
            }

            private void parseHierarchicalUri()
            {
/* 193*/        if(ci.hasNext() && ci.peek() == '/')
                {
/* 195*/            ci.next();
/* 196*/            ci.next();
/* 197*/            parseAuthority();
                }
/* 200*/        if(!ci.hasNext())
                {
/* 201*/            if(ci.current() == '/')
/* 202*/                path = "/";
/* 204*/            return;
                } else
                {
/* 206*/            parsePath();
/* 207*/            return;
                }
            }

            private void parseAuthority()
            {
/* 210*/        int i = ci.pos();
/* 211*/        String s = parseComponentWithIP("@/?#", true);
/* 212*/        if(ci.current() == '@')
                {
/* 213*/            userInfo = s;
/* 214*/            if(!ci.hasNext())
/* 215*/                return;
/* 217*/            ci.next();
/* 218*/            s = parseComponentWithIP(":/?#", true);
                } else
                {
/* 220*/            ci.setPosition(i);
/* 221*/            s = parseComponentWithIP("@:/?#", true);
                }
/* 224*/        host = s;
/* 226*/        if(ci.current() == ':')
                {
/* 227*/            if(!ci.hasNext())
/* 228*/                return;
/* 230*/            ci.next();
/* 231*/            port = parseComponent("/?#", true);
                }
/* 233*/        authority = ci.getInput().substring(i, ci.pos());
/* 234*/        if(authority.length() == 0)
/* 235*/            authority = null;
            }

            private void parsePath()
            {
/* 243*/        path = parseComponent("?#", true);
/* 246*/        if(ci.current() == '?')
                {
/* 247*/            if(!ci.hasNext())
/* 248*/                return;
/* 250*/            ci.next();
/* 252*/            query = parseComponent("#", true);
                }
/* 255*/        if(ci.current() == '#')
                {
/* 256*/            if(!ci.hasNext())
/* 257*/                return;
/* 259*/            ci.next();
/* 261*/            fragment = parseComponent(null, true);
                }
            }

            public String getSsp()
            {
/* 271*/        if(!parserExecuted)
/* 272*/            throw new IllegalStateException(ERROR_STATE);
/* 274*/        else
/* 274*/            return ssp;
            }

            public String getScheme()
            {
/* 283*/        if(!parserExecuted)
/* 284*/            throw new IllegalStateException(ERROR_STATE);
/* 286*/        else
/* 286*/            return scheme;
            }

            public String getUserInfo()
            {
/* 295*/        if(!parserExecuted)
/* 296*/            throw new IllegalStateException(ERROR_STATE);
/* 298*/        else
/* 298*/            return userInfo;
            }

            public String getHost()
            {
/* 307*/        if(!parserExecuted)
/* 308*/            throw new IllegalStateException(ERROR_STATE);
/* 310*/        else
/* 310*/            return host;
            }

            public String getPort()
            {
/* 319*/        if(!parserExecuted)
/* 320*/            throw new IllegalStateException(ERROR_STATE);
/* 322*/        else
/* 322*/            return port;
            }

            public String getQuery()
            {
/* 331*/        if(!parserExecuted)
/* 332*/            throw new IllegalStateException(ERROR_STATE);
/* 334*/        else
/* 334*/            return query;
            }

            public String getPath()
            {
/* 343*/        if(!parserExecuted)
/* 344*/            throw new IllegalStateException(ERROR_STATE);
/* 346*/        else
/* 346*/            return path;
            }

            public String getFragment()
            {
/* 355*/        if(!parserExecuted)
/* 356*/            throw new IllegalStateException(ERROR_STATE);
/* 358*/        else
/* 358*/            return fragment;
            }

            public String getAuthority()
            {
/* 367*/        if(!parserExecuted)
/* 368*/            throw new IllegalStateException(ERROR_STATE);
/* 370*/        else
/* 370*/            return authority;
            }

            public boolean isOpaque()
            {
/* 379*/        if(!parserExecuted)
/* 380*/            throw new IllegalStateException(ERROR_STATE);
/* 382*/        else
/* 382*/            return opaque;
            }

            private static final String ERROR_STATE = LocalizationMessages.URI_PARSER_NOT_EXECUTED();
            private final String input;
            private CharacterIterator ci;
            private String scheme;
            private String userInfo;
            private String host;
            private String port;
            private String query;
            private String path;
            private String fragment;
            private String ssp;
            private String authority;
            private boolean opaque;
            private boolean parserExecuted;

}
