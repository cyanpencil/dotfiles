// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OctIcon.java

package de.jensd.fx.glyphs.octicons;

import de.jensd.fx.glyphs.GlyphIcons;

public final class OctIcon extends Enum
    implements GlyphIcons
{

            public static OctIcon[] values()
            {
/*  28*/        return (OctIcon[])$VALUES.clone();
            }

            public static OctIcon valueOf(String s)
            {
/*  28*/        return (OctIcon)Enum.valueOf(de/jensd/fx/glyphs/octicons/OctIcon, s);
            }

            private OctIcon(String s, int i, char c)
            {
/* 227*/        super(s, i);
/* 228*/        character = c;
            }

            public final char getChar()
            {
/* 233*/        return character;
            }

            public final String unicodeToString()
            {
/* 238*/        return String.format("\\u%04x", new Object[] {
/* 238*/            Integer.valueOf(character)
                });
            }

            public final String characterToString()
            {
/* 243*/        return Character.toString(character);
            }

            public final String getFontFamily()
            {
/* 248*/        return "'Octicons'";
            }

            public static final OctIcon ALERT;
            public static final OctIcon ARROW_DOWN;
            public static final OctIcon ARROW_LEFT;
            public static final OctIcon ARROW_RIGHT;
            public static final OctIcon ARROW_SMALL_DOWN;
            public static final OctIcon ARROW_SMALL_LEFT;
            public static final OctIcon ARROW_SMALL_RIGHT;
            public static final OctIcon ARROW_SMALL_UP;
            public static final OctIcon ARROW_UP;
            public static final OctIcon MICROSCOPE;
            public static final OctIcon BEAKER;
            public static final OctIcon BELL;
            public static final OctIcon BOLD;
            public static final OctIcon BOOK;
            public static final OctIcon BOOKMARK;
            public static final OctIcon BRIEFCASE;
            public static final OctIcon BROADCAST;
            public static final OctIcon BROWSER;
            public static final OctIcon BUG;
            public static final OctIcon CALENDAR;
            public static final OctIcon CHECK;
            public static final OctIcon CHECKLIST;
            public static final OctIcon CHEVRON_DOWN;
            public static final OctIcon CHEVRON_LEFT;
            public static final OctIcon CHEVRON_RIGHT;
            public static final OctIcon CHEVRON_UP;
            public static final OctIcon CIRCLE_SLASH;
            public static final OctIcon CIRCUIT_BOARD;
            public static final OctIcon CLIPPY;
            public static final OctIcon CLOCK;
            public static final OctIcon CLOUD_DOWNLOAD;
            public static final OctIcon CLOUD_UPLOAD;
            public static final OctIcon CODE;
            public static final OctIcon COLOR_MODE;
            public static final OctIcon COMMENT_ADD;
            public static final OctIcon COMMENT;
            public static final OctIcon COMMENT_DISCUSSION;
            public static final OctIcon CREDIT_CARD;
            public static final OctIcon DASH;
            public static final OctIcon DASHBOARD;
            public static final OctIcon DATABASE;
            public static final OctIcon CLONE;
            public static final OctIcon DESKTOP_DOWNLOAD;
            public static final OctIcon DEVICE_CAMERA;
            public static final OctIcon DEVICE_CAMERA_VIDEO;
            public static final OctIcon DEVICE_DESKTOP;
            public static final OctIcon DEVICE_MOBILE;
            public static final OctIcon DIFF;
            public static final OctIcon DIFF_ADDED;
            public static final OctIcon DIFF_IGNORED;
            public static final OctIcon DIFF_MODIFIED;
            public static final OctIcon DIFF_REMOVED;
            public static final OctIcon DIFF_RENAMED;
            public static final OctIcon ELLIPSIS;
            public static final OctIcon EYE_UNWATCH;
            public static final OctIcon EYE_WATCH;
            public static final OctIcon EYE;
            public static final OctIcon FILE_BINARY;
            public static final OctIcon FILE_CODE;
            public static final OctIcon FILE_DIRECTORY;
            public static final OctIcon FILE_MEDIA;
            public static final OctIcon FILE_PDF;
            public static final OctIcon FILE_SUBMODULE;
            public static final OctIcon FILE_SYMLINK_DIRECTORY;
            public static final OctIcon FILE_SYMLINK_FILE;
            public static final OctIcon FILE_TEXT;
            public static final OctIcon FILE_ZIP;
            public static final OctIcon FLAME;
            public static final OctIcon FOLD;
            public static final OctIcon GEAR;
            public static final OctIcon GIFT;
            public static final OctIcon GIST;
            public static final OctIcon GIST_SECRET;
            public static final OctIcon GIT_BRANCH_CREATE;
            public static final OctIcon GIT_BRANCH_DELETE;
            public static final OctIcon GIT_BRANCH;
            public static final OctIcon GIT_COMMIT;
            public static final OctIcon GIT_COMPARE;
            public static final OctIcon GIT_MERGE;
            public static final OctIcon GIT_PULL_REQUEST_ABANDONED;
            public static final OctIcon GIT_PULL_REQUEST;
            public static final OctIcon GLOBE;
            public static final OctIcon GRAPH;
            public static final OctIcon HEART;
            public static final OctIcon HISTORY;
            public static final OctIcon HOME;
            public static final OctIcon HORIZONTAL_RULE;
            public static final OctIcon HUBOT;
            public static final OctIcon INBOX;
            public static final OctIcon INFO;
            public static final OctIcon ISSUE_CLOSED;
            public static final OctIcon ISSUE_OPENED;
            public static final OctIcon ISSUE_REOPENED;
            public static final OctIcon ITALIC;
            public static final OctIcon JERSEY;
            public static final OctIcon KEY;
            public static final OctIcon KEYBOARD;
            public static final OctIcon LAW;
            public static final OctIcon LIGHT_BULB;
            public static final OctIcon LINK;
            public static final OctIcon LINK_EXTERNAL;
            public static final OctIcon LIST_ORDERED;
            public static final OctIcon LIST_UNORDERED;
            public static final OctIcon LOCATION;
            public static final OctIcon GIST_PRIVATE;
            public static final OctIcon MIRROR_PRIVATE;
            public static final OctIcon GIT_FORK_PRIVATE;
            public static final OctIcon LOCK;
            public static final OctIcon LOGO_GIST;
            public static final OctIcon LOGO_GITHUB;
            public static final OctIcon MAIL;
            public static final OctIcon MAIL_READ;
            public static final OctIcon MAIL_REPLY;
            public static final OctIcon MARK_GITHUB;
            public static final OctIcon MARKDOWN;
            public static final OctIcon MEGAPHONE;
            public static final OctIcon MENTION;
            public static final OctIcon MILESTONE;
            public static final OctIcon MIRROR_PUBLIC;
            public static final OctIcon MIRROR;
            public static final OctIcon MORTAR_BOARD;
            public static final OctIcon MUTE;
            public static final OctIcon NO_NEWLINE;
            public static final OctIcon OCTOFACE;
            public static final OctIcon ORGANIZATION;
            public static final OctIcon PACKAGE;
            public static final OctIcon PAINTCAN;
            public static final OctIcon PENCIL;
            public static final OctIcon PERSON_ADD;
            public static final OctIcon PERSON_FOLLOW;
            public static final OctIcon PERSON;
            public static final OctIcon PIN;
            public static final OctIcon PLUG;
            public static final OctIcon REPO_CREATE;
            public static final OctIcon GIST_NEW;
            public static final OctIcon FILE_DIRECTORY_CREATE;
            public static final OctIcon FILE_ADD;
            public static final OctIcon PLUS;
            public static final OctIcon PRIMITIVE_DOT;
            public static final OctIcon PRIMITIVE_SQUARE;
            public static final OctIcon PULSE;
            public static final OctIcon QUESTION;
            public static final OctIcon QUOTE;
            public static final OctIcon RADIO_TOWER;
            public static final OctIcon REPO_DELETE;
            public static final OctIcon REPO;
            public static final OctIcon REPO_CLONE;
            public static final OctIcon REPO_FORCE_PUSH;
            public static final OctIcon GIST_FORK;
            public static final OctIcon REPO_FORKED;
            public static final OctIcon REPO_PULL;
            public static final OctIcon REPO_PUSH;
            public static final OctIcon ROCKET;
            public static final OctIcon RSS;
            public static final OctIcon RUBY;
            public static final OctIcon SEARCH_SAVE;
            public static final OctIcon SEARCH;
            public static final OctIcon SERVER;
            public static final OctIcon SETTINGS;
            public static final OctIcon SHIELD;
            public static final OctIcon LOG_IN;
            public static final OctIcon SIGN_IN;
            public static final OctIcon LOG_OUT;
            public static final OctIcon SIGN_OUT;
            public static final OctIcon SQUIRREL;
            public static final OctIcon STAR_ADD;
            public static final OctIcon STAR_DELETE;
            public static final OctIcon STAR;
            public static final OctIcon STOP;
            public static final OctIcon REPO_SYNC;
            public static final OctIcon SYNC;
            public static final OctIcon TAG_REMOVE;
            public static final OctIcon TAG_ADD;
            public static final OctIcon TAG;
            public static final OctIcon TASKLIST;
            public static final OctIcon TELESCOPE;
            public static final OctIcon TERMINAL;
            public static final OctIcon TEXT_SIZE;
            public static final OctIcon THREE_BARS;
            public static final OctIcon THUMBSDOWN;
            public static final OctIcon THUMBSUP;
            public static final OctIcon TOOLS;
            public static final OctIcon TRASHCAN;
            public static final OctIcon TRIANGLE_DOWN;
            public static final OctIcon TRIANGLE_LEFT;
            public static final OctIcon TRIANGLE_RIGHT;
            public static final OctIcon TRIANGLE_UP;
            public static final OctIcon UNFOLD;
            public static final OctIcon UNMUTE;
            public static final OctIcon VERSIONS;
            public static final OctIcon WATCH;
            public static final OctIcon REMOVE_CLOSE;
            public static final OctIcon X;
            public static final OctIcon ZAP;
            private final char character;
            private static final OctIcon $VALUES[];

            static 
            {
/*  30*/        ALERT = new OctIcon("ALERT", 0, '\uF02D');
/*  31*/        ARROW_DOWN = new OctIcon("ARROW_DOWN", 1, '\uF03F');
/*  32*/        ARROW_LEFT = new OctIcon("ARROW_LEFT", 2, '\uF040');
/*  33*/        ARROW_RIGHT = new OctIcon("ARROW_RIGHT", 3, '\uF03E');
/*  34*/        ARROW_SMALL_DOWN = new OctIcon("ARROW_SMALL_DOWN", 4, '\uF0A0');
/*  35*/        ARROW_SMALL_LEFT = new OctIcon("ARROW_SMALL_LEFT", 5, '\uF0A1');
/*  36*/        ARROW_SMALL_RIGHT = new OctIcon("ARROW_SMALL_RIGHT", 6, '\uF071');
/*  37*/        ARROW_SMALL_UP = new OctIcon("ARROW_SMALL_UP", 7, '\uF09F');
/*  38*/        ARROW_UP = new OctIcon("ARROW_UP", 8, '\uF03D');
/*  39*/        MICROSCOPE = new OctIcon("MICROSCOPE", 9, '\uF0DD');
/*  40*/        BEAKER = new OctIcon("BEAKER", 10, '\uF0DD');
/*  41*/        BELL = new OctIcon("BELL", 11, '\uF0DE');
/*  42*/        BOLD = new OctIcon("BOLD", 12, '\uF0E2');
/*  43*/        BOOK = new OctIcon("BOOK", 13, '\uF007');
/*  44*/        BOOKMARK = new OctIcon("BOOKMARK", 14, '\uF07B');
/*  45*/        BRIEFCASE = new OctIcon("BRIEFCASE", 15, '\uF0D3');
/*  46*/        BROADCAST = new OctIcon("BROADCAST", 16, '\uF048');
/*  47*/        BROWSER = new OctIcon("BROWSER", 17, '\uF0C5');
/*  48*/        BUG = new OctIcon("BUG", 18, '\uF091');
/*  49*/        CALENDAR = new OctIcon("CALENDAR", 19, '\uF068');
/*  50*/        CHECK = new OctIcon("CHECK", 20, '\uF03A');
/*  51*/        CHECKLIST = new OctIcon("CHECKLIST", 21, '\uF076');
/*  52*/        CHEVRON_DOWN = new OctIcon("CHEVRON_DOWN", 22, '\uF0A3');
/*  53*/        CHEVRON_LEFT = new OctIcon("CHEVRON_LEFT", 23, '\uF0A4');
/*  54*/        CHEVRON_RIGHT = new OctIcon("CHEVRON_RIGHT", 24, '\uF078');
/*  55*/        CHEVRON_UP = new OctIcon("CHEVRON_UP", 25, '\uF0A2');
/*  56*/        CIRCLE_SLASH = new OctIcon("CIRCLE_SLASH", 26, '\uF084');
/*  57*/        CIRCUIT_BOARD = new OctIcon("CIRCUIT_BOARD", 27, '\uF0D6');
/*  58*/        CLIPPY = new OctIcon("CLIPPY", 28, '\uF035');
/*  59*/        CLOCK = new OctIcon("CLOCK", 29, '\uF046');
/*  60*/        CLOUD_DOWNLOAD = new OctIcon("CLOUD_DOWNLOAD", 30, '\uF00B');
/*  61*/        CLOUD_UPLOAD = new OctIcon("CLOUD_UPLOAD", 31, '\uF00C');
/*  62*/        CODE = new OctIcon("CODE", 32, '\uF05F');
/*  63*/        COLOR_MODE = new OctIcon("COLOR_MODE", 33, '\uF065');
/*  64*/        COMMENT_ADD = new OctIcon("COMMENT_ADD", 34, '\uF02B');
/*  65*/        COMMENT = new OctIcon("COMMENT", 35, '\uF02B');
/*  66*/        COMMENT_DISCUSSION = new OctIcon("COMMENT_DISCUSSION", 36, '\uF04F');
/*  67*/        CREDIT_CARD = new OctIcon("CREDIT_CARD", 37, '\uF045');
/*  68*/        DASH = new OctIcon("DASH", 38, '\uF0CA');
/*  69*/        DASHBOARD = new OctIcon("DASHBOARD", 39, '\uF07D');
/*  70*/        DATABASE = new OctIcon("DATABASE", 40, '\uF096');
/*  71*/        CLONE = new OctIcon("CLONE", 41, '\uF0DC');
/*  72*/        DESKTOP_DOWNLOAD = new OctIcon("DESKTOP_DOWNLOAD", 42, '\uF0DC');
/*  73*/        DEVICE_CAMERA = new OctIcon("DEVICE_CAMERA", 43, '\uF056');
/*  74*/        DEVICE_CAMERA_VIDEO = new OctIcon("DEVICE_CAMERA_VIDEO", 44, '\uF057');
/*  75*/        DEVICE_DESKTOP = new OctIcon("DEVICE_DESKTOP", 45, '\uF27C');
/*  76*/        DEVICE_MOBILE = new OctIcon("DEVICE_MOBILE", 46, '\uF038');
/*  77*/        DIFF = new OctIcon("DIFF", 47, '\uF04D');
/*  78*/        DIFF_ADDED = new OctIcon("DIFF_ADDED", 48, '\uF06B');
/*  79*/        DIFF_IGNORED = new OctIcon("DIFF_IGNORED", 49, '\uF099');
/*  80*/        DIFF_MODIFIED = new OctIcon("DIFF_MODIFIED", 50, '\uF06D');
/*  81*/        DIFF_REMOVED = new OctIcon("DIFF_REMOVED", 51, '\uF06C');
/*  82*/        DIFF_RENAMED = new OctIcon("DIFF_RENAMED", 52, '\uF06E');
/*  83*/        ELLIPSIS = new OctIcon("ELLIPSIS", 53, '\uF09A');
/*  84*/        EYE_UNWATCH = new OctIcon("EYE_UNWATCH", 54, '\uF04E');
/*  85*/        EYE_WATCH = new OctIcon("EYE_WATCH", 55, '\uF04E');
/*  86*/        EYE = new OctIcon("EYE", 56, '\uF04E');
/*  87*/        FILE_BINARY = new OctIcon("FILE_BINARY", 57, '\uF094');
/*  88*/        FILE_CODE = new OctIcon("FILE_CODE", 58, '\uF010');
/*  89*/        FILE_DIRECTORY = new OctIcon("FILE_DIRECTORY", 59, '\uF016');
/*  90*/        FILE_MEDIA = new OctIcon("FILE_MEDIA", 60, '\uF012');
/*  91*/        FILE_PDF = new OctIcon("FILE_PDF", 61, '\uF014');
/*  92*/        FILE_SUBMODULE = new OctIcon("FILE_SUBMODULE", 62, '\uF017');
/*  93*/        FILE_SYMLINK_DIRECTORY = new OctIcon("FILE_SYMLINK_DIRECTORY", 63, '\uF0B1');
/*  94*/        FILE_SYMLINK_FILE = new OctIcon("FILE_SYMLINK_FILE", 64, '\uF0B0');
/*  95*/        FILE_TEXT = new OctIcon("FILE_TEXT", 65, '\uF011');
/*  96*/        FILE_ZIP = new OctIcon("FILE_ZIP", 66, '\uF013');
/*  97*/        FLAME = new OctIcon("FLAME", 67, '\uF0D2');
/*  98*/        FOLD = new OctIcon("FOLD", 68, '\uF0CC');
/*  99*/        GEAR = new OctIcon("GEAR", 69, '\uF02F');
/* 100*/        GIFT = new OctIcon("GIFT", 70, '\uF042');
/* 101*/        GIST = new OctIcon("GIST", 71, '\uF00E');
/* 102*/        GIST_SECRET = new OctIcon("GIST_SECRET", 72, '\uF08C');
/* 103*/        GIT_BRANCH_CREATE = new OctIcon("GIT_BRANCH_CREATE", 73, '\uF020');
/* 104*/        GIT_BRANCH_DELETE = new OctIcon("GIT_BRANCH_DELETE", 74, '\uF020');
/* 105*/        GIT_BRANCH = new OctIcon("GIT_BRANCH", 75, '\uF020');
/* 106*/        GIT_COMMIT = new OctIcon("GIT_COMMIT", 76, '\uF01F');
/* 107*/        GIT_COMPARE = new OctIcon("GIT_COMPARE", 77, '\uF0AC');
/* 108*/        GIT_MERGE = new OctIcon("GIT_MERGE", 78, '\uF023');
/* 109*/        GIT_PULL_REQUEST_ABANDONED = new OctIcon("GIT_PULL_REQUEST_ABANDONED", 79, '\uF009');
/* 110*/        GIT_PULL_REQUEST = new OctIcon("GIT_PULL_REQUEST", 80, '\uF009');
/* 111*/        GLOBE = new OctIcon("GLOBE", 81, '\uF0B6');
/* 112*/        GRAPH = new OctIcon("GRAPH", 82, '\uF043');
/* 113*/        HEART = new OctIcon("HEART", 83, '\u2665');
/* 114*/        HISTORY = new OctIcon("HISTORY", 84, '\uF07E');
/* 115*/        HOME = new OctIcon("HOME", 85, '\uF08D');
/* 116*/        HORIZONTAL_RULE = new OctIcon("HORIZONTAL_RULE", 86, '\uF070');
/* 117*/        HUBOT = new OctIcon("HUBOT", 87, '\uF09D');
/* 118*/        INBOX = new OctIcon("INBOX", 88, '\uF0CF');
/* 119*/        INFO = new OctIcon("INFO", 89, '\uF059');
/* 120*/        ISSUE_CLOSED = new OctIcon("ISSUE_CLOSED", 90, '\uF028');
/* 121*/        ISSUE_OPENED = new OctIcon("ISSUE_OPENED", 91, '\uF026');
/* 122*/        ISSUE_REOPENED = new OctIcon("ISSUE_REOPENED", 92, '\uF027');
/* 123*/        ITALIC = new OctIcon("ITALIC", 93, '\uF0E4');
/* 124*/        JERSEY = new OctIcon("JERSEY", 94, '\uF019');
/* 125*/        KEY = new OctIcon("KEY", 95, '\uF049');
/* 126*/        KEYBOARD = new OctIcon("KEYBOARD", 96, '\uF00D');
/* 127*/        LAW = new OctIcon("LAW", 97, '\uF0D8');
/* 128*/        LIGHT_BULB = new OctIcon("LIGHT_BULB", 98, '\uF000');
/* 129*/        LINK = new OctIcon("LINK", 99, '\uF05C');
/* 130*/        LINK_EXTERNAL = new OctIcon("LINK_EXTERNAL", 100, '\uF07F');
/* 131*/        LIST_ORDERED = new OctIcon("LIST_ORDERED", 101, '\uF062');
/* 132*/        LIST_UNORDERED = new OctIcon("LIST_UNORDERED", 102, '\uF061');
/* 133*/        LOCATION = new OctIcon("LOCATION", 103, '\uF060');
/* 134*/        GIST_PRIVATE = new OctIcon("GIST_PRIVATE", 104, '\uF06A');
/* 135*/        MIRROR_PRIVATE = new OctIcon("MIRROR_PRIVATE", 105, '\uF06A');
/* 136*/        GIT_FORK_PRIVATE = new OctIcon("GIT_FORK_PRIVATE", 106, '\uF06A');
/* 137*/        LOCK = new OctIcon("LOCK", 107, '\uF06A');
/* 138*/        LOGO_GIST = new OctIcon("LOGO_GIST", 108, '\uF0AD');
/* 139*/        LOGO_GITHUB = new OctIcon("LOGO_GITHUB", 109, '\uF092');
/* 140*/        MAIL = new OctIcon("MAIL", 110, '\uF03B');
/* 141*/        MAIL_READ = new OctIcon("MAIL_READ", 111, '\uF03C');
/* 142*/        MAIL_REPLY = new OctIcon("MAIL_REPLY", 112, '\uF051');
/* 143*/        MARK_GITHUB = new OctIcon("MARK_GITHUB", 113, '\uF00A');
/* 144*/        MARKDOWN = new OctIcon("MARKDOWN", 114, '\uF0C9');
/* 145*/        MEGAPHONE = new OctIcon("MEGAPHONE", 115, '\uF077');
/* 146*/        MENTION = new OctIcon("MENTION", 116, '\uF0BE');
/* 147*/        MILESTONE = new OctIcon("MILESTONE", 117, '\uF075');
/* 148*/        MIRROR_PUBLIC = new OctIcon("MIRROR_PUBLIC", 118, '\uF024');
/* 149*/        MIRROR = new OctIcon("MIRROR", 119, '\uF024');
/* 150*/        MORTAR_BOARD = new OctIcon("MORTAR_BOARD", 120, '\uF0D7');
/* 151*/        MUTE = new OctIcon("MUTE", 121, '\uF080');
/* 152*/        NO_NEWLINE = new OctIcon("NO_NEWLINE", 122, '\uF09C');
/* 153*/        OCTOFACE = new OctIcon("OCTOFACE", 123, '\uF008');
/* 154*/        ORGANIZATION = new OctIcon("ORGANIZATION", 124, '\uF037');
/* 155*/        PACKAGE = new OctIcon("PACKAGE", 125, '\uF0C4');
/* 156*/        PAINTCAN = new OctIcon("PAINTCAN", 126, '\uF0D1');
/* 157*/        PENCIL = new OctIcon("PENCIL", 127, '\uF058');
/* 158*/        PERSON_ADD = new OctIcon("PERSON_ADD", 128, '\uF018');
/* 159*/        PERSON_FOLLOW = new OctIcon("PERSON_FOLLOW", 129, '\uF018');
/* 160*/        PERSON = new OctIcon("PERSON", 130, '\uF018');
/* 161*/        PIN = new OctIcon("PIN", 131, '\uF041');
/* 162*/        PLUG = new OctIcon("PLUG", 132, '\uF0D4');
/* 163*/        REPO_CREATE = new OctIcon("REPO_CREATE", 133, '\uF05D');
/* 164*/        GIST_NEW = new OctIcon("GIST_NEW", 134, '\uF05D');
/* 165*/        FILE_DIRECTORY_CREATE = new OctIcon("FILE_DIRECTORY_CREATE", 135, '\uF05D');
/* 166*/        FILE_ADD = new OctIcon("FILE_ADD", 136, '\uF05D');
/* 167*/        PLUS = new OctIcon("PLUS", 137, '\uF05D');
/* 168*/        PRIMITIVE_DOT = new OctIcon("PRIMITIVE_DOT", 138, '\uF052');
/* 169*/        PRIMITIVE_SQUARE = new OctIcon("PRIMITIVE_SQUARE", 139, '\uF053');
/* 170*/        PULSE = new OctIcon("PULSE", 140, '\uF085');
/* 171*/        QUESTION = new OctIcon("QUESTION", 141, '\uF02C');
/* 172*/        QUOTE = new OctIcon("QUOTE", 142, '\uF063');
/* 173*/        RADIO_TOWER = new OctIcon("RADIO_TOWER", 143, '\uF030');
/* 174*/        REPO_DELETE = new OctIcon("REPO_DELETE", 144, '\uF001');
/* 175*/        REPO = new OctIcon("REPO", 145, '\uF001');
/* 176*/        REPO_CLONE = new OctIcon("REPO_CLONE", 146, '\uF04C');
/* 177*/        REPO_FORCE_PUSH = new OctIcon("REPO_FORCE_PUSH", 147, '\uF04A');
/* 178*/        GIST_FORK = new OctIcon("GIST_FORK", 148, '\uF002');
/* 179*/        REPO_FORKED = new OctIcon("REPO_FORKED", 149, '\uF002');
/* 180*/        REPO_PULL = new OctIcon("REPO_PULL", 150, '\uF006');
/* 181*/        REPO_PUSH = new OctIcon("REPO_PUSH", 151, '\uF005');
/* 182*/        ROCKET = new OctIcon("ROCKET", 152, '\uF033');
/* 183*/        RSS = new OctIcon("RSS", 153, '\uF034');
/* 184*/        RUBY = new OctIcon("RUBY", 154, '\uF047');
/* 185*/        SEARCH_SAVE = new OctIcon("SEARCH_SAVE", 155, '\uF02E');
/* 186*/        SEARCH = new OctIcon("SEARCH", 156, '\uF02E');
/* 187*/        SERVER = new OctIcon("SERVER", 157, '\uF097');
/* 188*/        SETTINGS = new OctIcon("SETTINGS", 158, '\uF07C');
/* 189*/        SHIELD = new OctIcon("SHIELD", 159, '\uF0E1');
/* 190*/        LOG_IN = new OctIcon("LOG_IN", 160, '\uF036');
/* 191*/        SIGN_IN = new OctIcon("SIGN_IN", 161, '\uF036');
/* 192*/        LOG_OUT = new OctIcon("LOG_OUT", 162, '\uF032');
/* 193*/        SIGN_OUT = new OctIcon("SIGN_OUT", 163, '\uF032');
/* 194*/        SQUIRREL = new OctIcon("SQUIRREL", 164, '\uF0B2');
/* 195*/        STAR_ADD = new OctIcon("STAR_ADD", 165, '\uF02A');
/* 196*/        STAR_DELETE = new OctIcon("STAR_DELETE", 166, '\uF02A');
/* 197*/        STAR = new OctIcon("STAR", 167, '\uF02A');
/* 198*/        STOP = new OctIcon("STOP", 168, '\uF08F');
/* 199*/        REPO_SYNC = new OctIcon("REPO_SYNC", 169, '\uF087');
/* 200*/        SYNC = new OctIcon("SYNC", 170, '\uF087');
/* 201*/        TAG_REMOVE = new OctIcon("TAG_REMOVE", 171, '\uF015');
/* 202*/        TAG_ADD = new OctIcon("TAG_ADD", 172, '\uF015');
/* 203*/        TAG = new OctIcon("TAG", 173, '\uF015');
/* 204*/        TASKLIST = new OctIcon("TASKLIST", 174, '\uF0E5');
/* 205*/        TELESCOPE = new OctIcon("TELESCOPE", 175, '\uF088');
/* 206*/        TERMINAL = new OctIcon("TERMINAL", 176, '\uF0C8');
/* 207*/        TEXT_SIZE = new OctIcon("TEXT_SIZE", 177, '\uF0E3');
/* 208*/        THREE_BARS = new OctIcon("THREE_BARS", 178, '\uF05E');
/* 209*/        THUMBSDOWN = new OctIcon("THUMBSDOWN", 179, '\uF0DB');
/* 210*/        THUMBSUP = new OctIcon("THUMBSUP", 180, '\uF0DA');
/* 211*/        TOOLS = new OctIcon("TOOLS", 181, '\uF031');
/* 212*/        TRASHCAN = new OctIcon("TRASHCAN", 182, '\uF0D0');
/* 213*/        TRIANGLE_DOWN = new OctIcon("TRIANGLE_DOWN", 183, '\uF05B');
/* 214*/        TRIANGLE_LEFT = new OctIcon("TRIANGLE_LEFT", 184, '\uF044');
/* 215*/        TRIANGLE_RIGHT = new OctIcon("TRIANGLE_RIGHT", 185, '\uF05A');
/* 216*/        TRIANGLE_UP = new OctIcon("TRIANGLE_UP", 186, '\uF0AA');
/* 217*/        UNFOLD = new OctIcon("UNFOLD", 187, '\uF039');
/* 218*/        UNMUTE = new OctIcon("UNMUTE", 188, '\uF0BA');
/* 219*/        VERSIONS = new OctIcon("VERSIONS", 189, '\uF064');
/* 220*/        WATCH = new OctIcon("WATCH", 190, '\uF0E0');
/* 221*/        REMOVE_CLOSE = new OctIcon("REMOVE_CLOSE", 191, '\uF081');
/* 222*/        X = new OctIcon("X", 192, '\uF081');
/* 223*/        ZAP = new OctIcon("ZAP", 193, '\u26A1');
/*  28*/        $VALUES = (new OctIcon[] {
/*  28*/            ALERT, ARROW_DOWN, ARROW_LEFT, ARROW_RIGHT, ARROW_SMALL_DOWN, ARROW_SMALL_LEFT, ARROW_SMALL_RIGHT, ARROW_SMALL_UP, ARROW_UP, MICROSCOPE, 
/*  28*/            BEAKER, BELL, BOLD, BOOK, BOOKMARK, BRIEFCASE, BROADCAST, BROWSER, BUG, CALENDAR, 
/*  28*/            CHECK, CHECKLIST, CHEVRON_DOWN, CHEVRON_LEFT, CHEVRON_RIGHT, CHEVRON_UP, CIRCLE_SLASH, CIRCUIT_BOARD, CLIPPY, CLOCK, 
/*  28*/            CLOUD_DOWNLOAD, CLOUD_UPLOAD, CODE, COLOR_MODE, COMMENT_ADD, COMMENT, COMMENT_DISCUSSION, CREDIT_CARD, DASH, DASHBOARD, 
/*  28*/            DATABASE, CLONE, DESKTOP_DOWNLOAD, DEVICE_CAMERA, DEVICE_CAMERA_VIDEO, DEVICE_DESKTOP, DEVICE_MOBILE, DIFF, DIFF_ADDED, DIFF_IGNORED, 
/*  28*/            DIFF_MODIFIED, DIFF_REMOVED, DIFF_RENAMED, ELLIPSIS, EYE_UNWATCH, EYE_WATCH, EYE, FILE_BINARY, FILE_CODE, FILE_DIRECTORY, 
/*  28*/            FILE_MEDIA, FILE_PDF, FILE_SUBMODULE, FILE_SYMLINK_DIRECTORY, FILE_SYMLINK_FILE, FILE_TEXT, FILE_ZIP, FLAME, FOLD, GEAR, 
/*  28*/            GIFT, GIST, GIST_SECRET, GIT_BRANCH_CREATE, GIT_BRANCH_DELETE, GIT_BRANCH, GIT_COMMIT, GIT_COMPARE, GIT_MERGE, GIT_PULL_REQUEST_ABANDONED, 
/*  28*/            GIT_PULL_REQUEST, GLOBE, GRAPH, HEART, HISTORY, HOME, HORIZONTAL_RULE, HUBOT, INBOX, INFO, 
/*  28*/            ISSUE_CLOSED, ISSUE_OPENED, ISSUE_REOPENED, ITALIC, JERSEY, KEY, KEYBOARD, LAW, LIGHT_BULB, LINK, 
/*  28*/            LINK_EXTERNAL, LIST_ORDERED, LIST_UNORDERED, LOCATION, GIST_PRIVATE, MIRROR_PRIVATE, GIT_FORK_PRIVATE, LOCK, LOGO_GIST, LOGO_GITHUB, 
/*  28*/            MAIL, MAIL_READ, MAIL_REPLY, MARK_GITHUB, MARKDOWN, MEGAPHONE, MENTION, MILESTONE, MIRROR_PUBLIC, MIRROR, 
/*  28*/            MORTAR_BOARD, MUTE, NO_NEWLINE, OCTOFACE, ORGANIZATION, PACKAGE, PAINTCAN, PENCIL, PERSON_ADD, PERSON_FOLLOW, 
/*  28*/            PERSON, PIN, PLUG, REPO_CREATE, GIST_NEW, FILE_DIRECTORY_CREATE, FILE_ADD, PLUS, PRIMITIVE_DOT, PRIMITIVE_SQUARE, 
/*  28*/            PULSE, QUESTION, QUOTE, RADIO_TOWER, REPO_DELETE, REPO, REPO_CLONE, REPO_FORCE_PUSH, GIST_FORK, REPO_FORKED, 
/*  28*/            REPO_PULL, REPO_PUSH, ROCKET, RSS, RUBY, SEARCH_SAVE, SEARCH, SERVER, SETTINGS, SHIELD, 
/*  28*/            LOG_IN, SIGN_IN, LOG_OUT, SIGN_OUT, SQUIRREL, STAR_ADD, STAR_DELETE, STAR, STOP, REPO_SYNC, 
/*  28*/            SYNC, TAG_REMOVE, TAG_ADD, TAG, TASKLIST, TELESCOPE, TERMINAL, TEXT_SIZE, THREE_BARS, THUMBSDOWN, 
/*  28*/            THUMBSUP, TOOLS, TRASHCAN, TRIANGLE_DOWN, TRIANGLE_LEFT, TRIANGLE_RIGHT, TRIANGLE_UP, UNFOLD, UNMUTE, VERSIONS, 
/*  28*/            WATCH, REMOVE_CLOSE, X, ZAP
                });
            }
}
