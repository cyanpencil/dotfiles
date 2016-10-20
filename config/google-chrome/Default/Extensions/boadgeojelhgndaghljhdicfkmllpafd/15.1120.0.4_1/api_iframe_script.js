var chrome = window.chrome || {};
chrome.cast = chrome.cast || {};
chrome.cast.media = chrome.cast.media || {};
var b = b || {};
b.global = this;
b.B = function(a) {
  return void 0 !== a;
};
b.T = function(a, c, d) {
  a = a.split(".");
  d = d || b.global;
  a[0] in d || !d.execScript || d.execScript("var " + a[0]);
  for (var f;a.length && (f = a.shift());) {
    !a.length && b.B(c) ? d[f] = c : d = d[f] ? d[f] : d[f] = {};
  }
};
b.Nd = function(a, c) {
  b.T(a, c);
};
b.w = !0;
b.xd = "en";
b.dc = !0;
b.Xb = !1;
b.Ra = !b.w;
b.da = !1;
b.ke = function(a) {
  b.ra(a);
};
b.ra = function(a, c) {
  b.T(a, c);
};
b.qc = /^[a-zA-Z_$][a-zA-Z0-9._$]*$/;
b.module = function(a) {
  if (!b.isString(a) || !a || -1 == a.search(b.qc)) {
    throw Error("Invalid module identifier");
  }
  if (!b.Mc()) {
    throw Error("Module " + a + " has been loaded incorrectly.");
  }
  if (b.g.Y) {
    throw Error("goog.module may only be called once per module.");
  }
  b.g.Y = a;
};
b.module.get = function(a) {
  return b.module.Dc(a);
};
b.module.Dc = function() {
};
b.g = null;
b.Mc = function() {
  return null != b.g;
};
b.module.S = function() {
  b.g.S = !0;
};
b.ne = function(a) {
  if (b.Ra) {
    throw a = a || "", Error("Importing test-only code into non-debug environment" + (a ? ": " + a : "."));
  }
};
b.Od = function() {
};
b.Ud = function(a, c) {
  for (var d = a.split("."), f = c || b.global, g;g = d.shift();) {
    if (b.Lc(f[g])) {
      f = f[g];
    } else {
      return null;
    }
  }
  return f;
};
b.Vd = function(a, c) {
  var d = c || b.global, f;
  for (f in a) {
    d[f] = a[f];
  }
};
b.Ld = function(a, c, d, f) {
  if (b.ba) {
    var g;
    a = a.replace(/\\/g, "/");
    for (var h = b.f, k = 0;g = c[k];k++) {
      h.C[g] = a, h.Z[a] = !!f;
    }
    for (f = 0;c = d[f];f++) {
      a in h.requires || (h.requires[a] = {}), h.requires[a][c] = !0;
    }
  }
};
b.pe = !1;
b.nd = !0;
b.de = function(a) {
  b.global.console && b.global.console.error(a);
};
b.require = function() {
};
b.s = "";
b.he = function() {
};
b.Kd = function() {
  throw Error("unimplemented abstract method");
};
b.Md = function(a) {
  a.Rd = function() {
    if (a.wa) {
      return a.wa;
    }
    b.w && (b.xa[b.xa.length] = a);
    return a.wa = new a;
  };
};
b.xa = [];
b.gb = !0;
b.Sb = b.w;
b.Qc = {};
b.ba = !1;
b.ba && (b.f = {Z:{}, C:{}, requires:{}, Ga:{}, L:{}, F:{}}, b.va = function() {
  var a = b.global.document;
  return null != a && "write" in a;
}, b.Cc = function() {
  if (b.B(b.global.La)) {
    b.s = b.global.La;
  } else {
    if (b.va()) {
      for (var a = b.global.document.getElementsByTagName("SCRIPT"), c = a.length - 1;0 <= c;--c) {
        var d = a[c].src, f = d.lastIndexOf("?"), f = -1 == f ? d.length : f;
        if ("base.js" == d.substr(f - 7, 7)) {
          b.s = d.substr(0, f - 7);
          break;
        }
      }
    }
  }
}, b.X = function(a, c) {
  (b.global.kd || b.ed)(a, c) && (b.f.L[a] = !0);
}, b.cb = !(b.global.atob || !b.global.document || !b.global.document.all), b.Jc = function(a) {
  b.X("", 'goog.retrieveAndExecModule_("' + a + '");') && (b.f.L[a] = !0);
}, b.$ = [], b.qe = function(a, c) {
  return b.gb && b.B(b.global.JSON) ? "goog.loadModule(" + b.global.JSON.stringify(c + "\n//# sourceURL=" + a + "\n") + ");" : 'goog.loadModule(function(exports) {"use strict";' + c + "\n;return exports});\n//# sourceURL=" + a + "\n";
}, b.Pc = function() {
  var a = b.$.length;
  if (0 < a) {
    var c = b.$;
    b.$ = [];
    for (var d = 0;d < a;d++) {
      b.Aa(c[d]);
    }
  }
}, b.ee = function(a) {
  b.ya(a) && b.sc(a) && b.Aa(b.s + b.W(a));
}, b.ya = function(a) {
  return (a = b.W(a)) && b.f.Z[a] ? b.s + a in b.f.F : !1;
}, b.sc = function(a) {
  if ((a = b.W(a)) && a in b.f.requires) {
    for (var c in b.f.requires[a]) {
      if (!b.Nc(c) && !b.ya(c)) {
        return !1;
      }
    }
  }
  return !0;
}, b.Aa = function(a) {
  if (a in b.f.F) {
    var c = b.f.F[a];
    delete b.f.F[a];
    b.Hc(c);
  }
}, b.ce = function(a) {
  b.Yc(a);
}, b.be = function(a) {
  var c = b.g;
  try {
    b.g = {Y:void 0, S:!1};
    var d;
    if (b.isFunction(a)) {
      d = a.call(b.global, {});
    } else {
      if (b.isString(a)) {
        d = b.Oc.call(b.global, a);
      } else {
        throw Error("Invalid module definition");
      }
    }
    var f = b.g.Y;
    if (!b.isString(f) || !f) {
      throw Error('Invalid module name "' + f + '"');
    }
    b.g.S ? b.ra(f, d) : b.Sb && Object.seal && Object.seal(d);
    b.Qc[f] = d;
  } finally {
    b.g = c;
  }
}, b.Oc = function(a) {
  eval(a);
  return {};
}, b.dd = function(a) {
  b.global.document.write('<script type="text/javascript" src="' + a + '">\x3c/script>');
}, b.tc = function(a) {
  var c = b.global.document, d = c.createElement("script");
  d.type = "text/javascript";
  d.src = a;
  d.defer = !1;
  d.async = !1;
  c.head.appendChild(d);
}, b.ed = function(a, c) {
  if (b.va()) {
    var d = b.global.document;
    if (!b.da && "complete" == d.readyState) {
      if (/\bdeps.js$/.test(a)) {
        return !1;
      }
      throw Error('Cannot write "' + a + '" after document load');
    }
    var f = b.cb;
    void 0 === c ? f ? (f = " onreadystatechange='goog.onScriptLoad_(this, " + ++b.za + ")' ", d.write('<script type="text/javascript" src="' + a + '"' + f + ">\x3c/script>")) : b.da ? b.tc(a) : b.dd(a) : d.write('<script type="text/javascript">' + c + "\x3c/script>");
    return !0;
  }
  return !1;
}, b.za = 0, b.ie = function(a, c) {
  "complete" == a.readyState && b.za == c && b.Pc();
  return !0;
}, b.re = function(a) {
  function c(a) {
    if (!(a in g.L || a in g.Ga)) {
      g.Ga[a] = !0;
      if (a in g.requires) {
        for (var h in g.requires[a]) {
          if (!b.Nc(h)) {
            if (h in g.C) {
              c(g.C[h]);
            } else {
              throw Error("Undefined nameToPath for " + h);
            }
          }
        }
      }
      a in f || (f[a] = !0, d.push(a));
    }
  }
  var d = [], f = {}, g = b.f;
  c(a);
  for (a = 0;a < d.length;a++) {
    var h = d[a];
    b.f.L[h] = !0;
  }
  var k = b.g;
  b.g = null;
  for (a = 0;a < d.length;a++) {
    if (h = d[a]) {
      g.Z[h] ? b.Jc(b.s + h) : b.X(b.s + h);
    } else {
      throw b.g = k, Error("Undefined script input");
    }
  }
  b.g = k;
}, b.W = function(a) {
  return a in b.f.C ? b.f.C[a] : null;
}, b.Cc(), b.global.ld || b.X(b.s + "deps.js"));
b.ge = function(a) {
  a = a.split("/");
  for (var c = 0;c < a.length;) {
    "." == a[c] ? a.splice(c, 1) : c && ".." == a[c] && a[c - 1] && ".." != a[c - 1] ? a.splice(--c, 2) : c++;
  }
  return a.join("/");
};
b.ae = function(a) {
  if (b.global.Ma) {
    return b.global.Ma(a);
  }
  var c = new b.global.XMLHttpRequest;
  c.open("get", a, !1);
  c.send();
  return c.responseText;
};
b.Yc = function() {
};
b.K = function(a) {
  var c = typeof a;
  if ("object" == c) {
    if (a) {
      if (a instanceof Array) {
        return "array";
      }
      if (a instanceof Object) {
        return c;
      }
      var d = Object.prototype.toString.call(a);
      if ("[object Window]" == d) {
        return "object";
      }
      if ("[object Array]" == d || "number" == typeof a.length && "undefined" != typeof a.splice && "undefined" != typeof a.propertyIsEnumerable && !a.propertyIsEnumerable("splice")) {
        return "array";
      }
      if ("[object Function]" == d || "undefined" != typeof a.call && "undefined" != typeof a.propertyIsEnumerable && !a.propertyIsEnumerable("call")) {
        return "function";
      }
    } else {
      return "null";
    }
  } else {
    if ("function" == c && "undefined" == typeof a.call) {
      return "object";
    }
  }
  return c;
};
b.$d = function(a) {
  return null === a;
};
b.Lc = function(a) {
  return null != a;
};
b.isArray = function(a) {
  return "array" == b.K(a);
};
b.Xd = function(a) {
  var c = b.K(a);
  return "array" == c || "object" == c && "number" == typeof a.length;
};
b.Zd = function(a) {
  return b.isObject(a) && "function" == typeof a.getFullYear;
};
b.isString = function(a) {
  return "string" == typeof a;
};
b.Yd = function(a) {
  return "boolean" == typeof a;
};
b.isNumber = function(a) {
  return "number" == typeof a;
};
b.isFunction = function(a) {
  return "function" == b.K(a);
};
b.isObject = function(a) {
  var c = typeof a;
  return "object" == c && null != a || "function" == c;
};
b.Fc = function(a) {
  return a[b.o] || (a[b.o] = ++b.cd);
};
b.Wd = function(a) {
  return !!a[b.o];
};
b.Xc = function(a) {
  null !== a && "removeAttribute" in a && a.removeAttribute(b.o);
  try {
    delete a[b.o];
  } catch (c) {
  }
};
b.o = "closure_uid_" + (1E9 * Math.random() >>> 0);
b.cd = 0;
b.Qd = b.Fc;
b.le = b.Xc;
b.xc = function(a) {
  var c = b.K(a);
  if ("object" == c || "array" == c) {
    if (a.clone) {
      return a.clone();
    }
    var c = "array" == c ? [] : {}, d;
    for (d in a) {
      c[d] = b.xc(a[d]);
    }
    return c;
  }
  return a;
};
b.wc = function(a, c, d) {
  return a.call.apply(a.bind, arguments);
};
b.vc = function(a, c, d) {
  if (!a) {
    throw Error();
  }
  if (2 < arguments.length) {
    var f = Array.prototype.slice.call(arguments, 2);
    return function() {
      var d = Array.prototype.slice.call(arguments);
      Array.prototype.unshift.apply(d, f);
      return a.apply(c, d);
    };
  }
  return function() {
    return a.apply(c, arguments);
  };
};
b.bind = function(a, c, d) {
  b.bind = Function.prototype.bind && -1 != Function.prototype.bind.toString().indexOf("native code") ? b.wc : b.vc;
  return b.bind.apply(null, arguments);
};
b.je = function(a, c) {
  var d = Array.prototype.slice.call(arguments, 1);
  return function() {
    var c = d.slice();
    c.push.apply(c, arguments);
    return a.apply(this, c);
  };
};
b.fe = function(a, c) {
  for (var d in c) {
    a[d] = c[d];
  }
};
b.now = b.dc && Date.now || function() {
  return +new Date;
};
b.Hc = function(a) {
  if (b.global.execScript) {
    b.global.execScript(a, "JavaScript");
  } else {
    if (b.global.eval) {
      if (null == b.G) {
        if (b.global.eval("var _evalTest_ = 1;"), "undefined" != typeof b.global._evalTest_) {
          try {
            delete b.global._evalTest_;
          } catch (f) {
          }
          b.G = !0;
        } else {
          b.G = !1;
        }
      }
      if (b.G) {
        b.global.eval(a);
      } else {
        var c = b.global.document, d = c.createElement("SCRIPT");
        d.type = "text/javascript";
        d.defer = !1;
        d.appendChild(c.createTextNode(a));
        c.body.appendChild(d);
        c.body.removeChild(d);
      }
    } else {
      throw Error("goog.globalEval not available");
    }
  }
};
b.G = null;
b.Pd = function(a, c) {
  var d = function(a) {
    return b.sa[a] || a;
  }, f = function(a) {
    a = a.split("-");
    for (var c = [], f = 0;f < a.length;f++) {
      c.push(d(a[f]));
    }
    return c.join("-");
  }, f = b.sa ? "BY_WHOLE" == b.Ac ? d : f : function(a) {
    return a;
  };
  return c ? a + "-" + f(c) : f(a);
};
b.me = function(a, c) {
  b.sa = a;
  b.Ac = c;
};
b.Sd = function(a, c) {
  c && (a = a.replace(/\{\$([^}]+)}/g, function(a, f) {
    return null != c && f in c ? c[f] : a;
  }));
  return a;
};
b.Td = function(a) {
  return a;
};
b.a = function(a, c, d) {
  b.T(a, c, d);
};
b.l = function(a, c, d) {
  a[c] = d;
};
b.Kc = function(a, c) {
  function d() {
  }
  d.prototype = c.prototype;
  a.J = c.prototype;
  a.prototype = new d;
  a.prototype.constructor = a;
  a.uc = function(a, d, h) {
    for (var k = Array(arguments.length - 2), l = 2;l < arguments.length;l++) {
      k[l - 2] = arguments[l];
    }
    return c.prototype[d].apply(a, k);
  };
};
b.uc = function(a, c, d) {
  var f = arguments.callee.caller;
  if (b.Xb || b.w && !f) {
    throw Error("arguments.caller not defined.  goog.base() cannot be used with strict mode code. See http://www.ecma-international.org/ecma-262/5.1/#sec-C");
  }
  if (f.J) {
    for (var g = Array(arguments.length - 1), h = 1;h < arguments.length;h++) {
      g[h - 1] = arguments[h];
    }
    return f.J.constructor.apply(a, g);
  }
  g = Array(arguments.length - 2);
  for (h = 2;h < arguments.length;h++) {
    g[h - 2] = arguments[h];
  }
  for (var h = !1, k = a.constructor;k;k = k.J && k.J.constructor) {
    if (k.prototype[c] === f) {
      h = !0;
    } else {
      if (h) {
        return k.prototype[c].apply(a, g);
      }
    }
  }
  if (a[c] === f) {
    return a.constructor.prototype[c].apply(a, g);
  }
  throw Error("goog.base called from a method of one name to a method of a different name");
};
b.scope = function(a) {
  a.call(b.global);
};
b.j = function(a, c) {
  var d = c.constructor, f = c.bd;
  d && d != Object.prototype.constructor || (d = function() {
    throw Error("cannot instantiate an interface (no constructor defined).");
  });
  d = b.j.zc(d, a);
  a && b.Kc(d, a);
  delete c.constructor;
  delete c.bd;
  b.j.qa(d.prototype, c);
  null != f && (f instanceof Function ? f(d) : b.j.qa(d, f));
  return d;
};
b.j.Rb = b.w;
b.j.zc = function(a, c) {
  if (b.j.Rb && Object.seal instanceof Function) {
    if (c && c.prototype && c.prototype[b.nc]) {
      return a;
    }
    var d = function() {
      var c = a.apply(this, arguments) || this;
      c[b.o] = c[b.o];
      this.constructor === d && Object.seal(c);
      return c;
    };
    return d;
  }
  return a;
};
b.j.ja = "constructor hasOwnProperty isPrototypeOf propertyIsEnumerable toLocaleString toString valueOf".split(" ");
b.j.qa = function(a, c) {
  for (var d in c) {
    Object.prototype.hasOwnProperty.call(c, d) && (a[d] = c[d]);
  }
  for (var f = 0;f < b.j.ja.length;f++) {
    d = b.j.ja[f], Object.prototype.hasOwnProperty.call(c, d) && (a[d] = c[d]);
  }
};
b.oe = function() {
};
b.nc = "goog_defineClass_legacy_unsealable";
chrome.cast.aa = {TAB_AND_ORIGIN_SCOPED:"tab_and_origin_scoped", ORIGIN_SCOPED:"origin_scoped", PAGE_SCOPED:"page_scoped"};
b.a("chrome.cast.AutoJoinPolicy", chrome.cast.aa);
chrome.cast.ca = {CREATE_SESSION:"create_session", CAST_THIS_TAB:"cast_this_tab"};
b.a("chrome.cast.DefaultActionPolicy", chrome.cast.ca);
chrome.cast.M = {VIDEO_OUT:"video_out", AUDIO_OUT:"audio_out", VIDEO_IN:"video_in", AUDIO_IN:"audio_in"};
b.a("chrome.cast.Capability", chrome.cast.M);
chrome.cast.ea = {CANCEL:"cancel", TIMEOUT:"timeout", API_NOT_INITIALIZED:"api_not_initialized", INVALID_PARAMETER:"invalid_parameter", EXTENSION_NOT_COMPATIBLE:"extension_not_compatible", EXTENSION_MISSING:"extension_missing", RECEIVER_UNAVAILABLE:"receiver_unavailable", SESSION_ERROR:"session_error", CHANNEL_ERROR:"channel_error", LOAD_MEDIA_FAILED:"load_media_failed"};
b.a("chrome.cast.ErrorCode", chrome.cast.ea);
chrome.cast.Ob = {AVAILABLE:"available", UNAVAILABLE:"unavailable"};
b.a("chrome.cast.ReceiverAvailability", chrome.cast.Ob);
chrome.cast.$b = {CHROME:"chrome", IOS:"ios", ANDROID:"android"};
b.a("chrome.cast.SenderPlatform", chrome.cast.$b);
chrome.cast.la = {CAST:"cast", DIAL:"dial", HANGOUT:"hangout", CUSTOM:"custom"};
b.a("chrome.cast.ReceiverType", chrome.cast.la);
chrome.cast.Ta = {RUNNING:"running", STOPPED:"stopped", ERROR:"error"};
b.a("chrome.cast.DialAppState", chrome.cast.Ta);
chrome.cast.Nb = {CAST:"cast", STOP:"stop"};
b.a("chrome.cast.ReceiverAction", chrome.cast.Nb);
chrome.cast.na = {CONNECTED:"connected", DISCONNECTED:"disconnected", STOPPED:"stopped"};
b.a("chrome.cast.SessionStatus", chrome.cast.na);
chrome.cast.VERSION = [1, 2];
b.a("chrome.cast.VERSION", chrome.cast.VERSION);
chrome.cast.Error = function(a, c, d) {
  this.code = a;
  this.description = c || null;
  this.details = d || null;
};
b.a("chrome.cast.Error", chrome.cast.Error);
chrome.cast.Zb = function(a) {
  this.platform = a;
  this.packageId = this.url = null;
};
b.a("chrome.cast.SenderApplication", chrome.cast.Zb);
chrome.cast.Image = function(a) {
  this.url = a;
  this.width = this.height = null;
};
b.a("chrome.cast.Image", chrome.cast.Image);
chrome.cast.Volume = function(a, c) {
  this.level = b.B(a) ? a : null;
  this.muted = b.B(c) ? c : null;
};
b.a("chrome.cast.Volume", chrome.cast.Volume);
var e = {b:{sd:"LAUNCH", ma:"STOP", Wb:"SET_VOLUME", Za:"GET_STATUS", Cd:"RECEIVER_STATUS", Id:"CONNECT", Jd:"CLOSE", od:"GET_APP_AVAILABILITY", jb:"LOAD", kb:"PAUSE", qb:"SEEK", lb:"PLAY", ia:"STOP_MEDIA", ga:"MEDIA_GET_STATUS", ha:"MEDIA_SET_VOLUME", ib:"EDIT_TRACKS_INFO", nb:"QUEUE_LOAD", mb:"QUEUE_INSERT", O:"QUEUE_UPDATE", ob:"QUEUE_REMOVE", pb:"QUEUE_REORDER", pd:"INVALID_PLAYER_STATE", wd:"LOAD_FAILED", vd:"LOAD_CANCELLED", qd:"INVALID_REQUEST", zd:"MEDIA_STATUS", td:"LAUNCH_ERROR", PING:"PING", 
Ad:"PONG"}, P:{}};
e.P[e.b.ia] = e.b.ma;
e.P[e.b.ha] = e.b.Wb;
e.P[e.b.ga] = e.b.Za;
e.fd = function(a, c, d) {
  this.sessionId = a;
  this.namespaceName = c;
  this.message = d;
};
e.Hd = function(a) {
  this.type = e.b.ma;
  this.requestId = null;
  this.sessionId = a || null;
};
chrome.cast.media.tb = {PAUSE:"pause", SEEK:"seek", STREAM_VOLUME:"stream_volume", STREAM_MUTE:"stream_mute"};
b.a("chrome.cast.media.MediaCommand", chrome.cast.media.tb);
chrome.cast.media.i = {GENERIC:0, MOVIE:1, TV_SHOW:2, MUSIC_TRACK:3, PHOTO:4};
b.a("chrome.cast.media.MetadataType", chrome.cast.media.i);
chrome.cast.media.ka = {IDLE:"IDLE", PLAYING:"PLAYING", PAUSED:"PAUSED", BUFFERING:"BUFFERING"};
b.a("chrome.cast.media.PlayerState", chrome.cast.media.ka);
chrome.cast.media.R = {OFF:"REPEAT_OFF", ALL:"REPEAT_ALL", SINGLE:"REPEAT_SINGLE", ALL_AND_SHUFFLE:"REPEAT_ALL_AND_SHUFFLE"};
b.a("chrome.cast.media.RepeatMode", chrome.cast.media.R);
chrome.cast.media.Qb = {PLAYBACK_START:"PLAYBACK_START", PLAYBACK_PAUSE:"PLAYBACK_PAUSE"};
b.a("chrome.cast.media.ResumeState", chrome.cast.media.Qb);
chrome.cast.media.oa = {BUFFERED:"BUFFERED", LIVE:"LIVE", OTHER:"OTHER"};
b.a("chrome.cast.media.StreamType", chrome.cast.media.oa);
chrome.cast.media.eb = {CANCELLED:"CANCELLED", INTERRUPTED:"INTERRUPTED", FINISHED:"FINISHED", ERROR:"ERROR"};
b.a("chrome.cast.media.IdleReason", chrome.cast.media.eb);
chrome.cast.media.lc = {TEXT:"TEXT", AUDIO:"AUDIO", VIDEO:"VIDEO"};
b.a("chrome.cast.media.TrackType", chrome.cast.media.lc);
chrome.cast.media.ic = {SUBTITLES:"SUBTITLES", CAPTIONS:"CAPTIONS", DESCRIPTIONS:"DESCRIPTIONS", CHAPTERS:"CHAPTERS", METADATA:"METADATA"};
b.a("chrome.cast.media.TextTrackType", chrome.cast.media.ic);
chrome.cast.media.ec = {NONE:"NONE", OUTLINE:"OUTLINE", DROP_SHADOW:"DROP_SHADOW", RAISED:"RAISED", DEPRESSED:"DEPRESSED"};
b.a("chrome.cast.media.TextTrackEdgeType", chrome.cast.media.ec);
chrome.cast.media.jc = {NONE:"NONE", NORMAL:"NORMAL", ROUNDED_CORNERS:"ROUNDED_CORNERS"};
b.a("chrome.cast.media.TextTrackWindowType", chrome.cast.media.jc);
chrome.cast.media.fc = {SANS_SERIF:"SANS_SERIF", MONOSPACED_SANS_SERIF:"MONOSPACED_SANS_SERIF", SERIF:"SERIF", MONOSPACED_SERIF:"MONOSPACED_SERIF", CASUAL:"CASUAL", CURSIVE:"CURSIVE", SMALL_CAPITALS:"SMALL_CAPITALS"};
b.a("chrome.cast.media.TextTrackFontGenericFamily", chrome.cast.media.fc);
chrome.cast.media.gc = {NORMAL:"NORMAL", BOLD:"BOLD", BOLD_ITALIC:"BOLD_ITALIC", ITALIC:"ITALIC"};
b.a("chrome.cast.media.TextTrackFontStyle", chrome.cast.media.gc);
chrome.cast.media.ab = function() {
  this.type = e.b.ga;
  this.customData = null;
};
b.a("chrome.cast.media.GetStatusRequest", chrome.cast.media.ab);
chrome.cast.media.yb = function() {
  this.type = e.b.kb;
  this.customData = null;
};
b.a("chrome.cast.media.PauseRequest", chrome.cast.media.yb);
chrome.cast.media.Ab = function() {
  this.type = e.b.lb;
  this.customData = null;
};
b.a("chrome.cast.media.PlayRequest", chrome.cast.media.Ab);
chrome.cast.media.Yb = function() {
  this.type = e.b.qb;
  this.customData = this.resumeState = this.currentTime = null;
};
b.a("chrome.cast.media.SeekRequest", chrome.cast.media.Yb);
chrome.cast.media.bc = function() {
  this.type = e.b.ia;
  this.customData = null;
};
b.a("chrome.cast.media.StopRequest", chrome.cast.media.bc);
chrome.cast.media.rc = function(a) {
  this.type = e.b.ha;
  this.volume = a;
  this.customData = null;
};
b.a("chrome.cast.media.VolumeRequest", chrome.cast.media.rc);
chrome.cast.media.hb = function(a) {
  this.type = e.b.jb;
  this.sessionId = this.requestId = null;
  this.media = a;
  this.activeTrackIds = null;
  this.autoplay = !0;
  this.customData = this.currentTime = null;
};
b.a("chrome.cast.media.LoadRequest", chrome.cast.media.hb);
chrome.cast.media.Ya = function(a, c) {
  this.type = e.b.ib;
  this.requestId = null;
  this.activeTrackIds = a || null;
  this.textTrackStyle = c || null;
};
b.a("chrome.cast.media.EditTracksInfoRequest", chrome.cast.media.Ya);
chrome.cast.media.Fb = function(a) {
  this.type = e.b.nb;
  this.sessionId = this.requestId = null;
  this.items = a;
  this.startIndex = 0;
  this.repeatMode = chrome.cast.media.R.OFF;
  this.customData = this.currentTime = null;
};
b.a("chrome.cast.media.QueueLoadRequest", chrome.cast.media.Fb);
chrome.cast.media.Cb = function(a) {
  this.type = e.b.mb;
  this.sessionId = this.requestId = null;
  this.items = a;
  this.customData = this.currentTime = this.currentItemIndex = this.currentItemId = this.insertBefore = null;
};
b.a("chrome.cast.media.QueueInsertItemsRequest", chrome.cast.media.Cb);
chrome.cast.media.Bb = function(a) {
  this.item = a;
  this.customData = this.currentTime = this.insertBefore = null;
};
b.a("chrome.cast.media.QueueInsertAndPlayItemRequest", chrome.cast.media.Bb);
chrome.cast.media.Jb = function(a) {
  this.type = e.b.O;
  this.sessionId = this.requestId = null;
  this.items = a;
  this.customData = null;
};
b.a("chrome.cast.media.QueueUpdateItemsRequest", chrome.cast.media.Jb);
chrome.cast.media.Eb = function() {
  this.type = e.b.O;
  this.customData = this.currentTime = this.jump = this.currentItemId = this.sessionId = this.requestId = null;
};
b.a("chrome.cast.media.QueueJumpRequest", chrome.cast.media.Eb);
chrome.cast.media.Ib = function() {
  this.type = e.b.O;
  this.customData = this.repeatMode = this.sessionId = this.requestId = null;
};
b.a("chrome.cast.media.QueueSetPropertiesRequest", chrome.cast.media.Ib);
chrome.cast.media.Gb = function(a) {
  this.type = e.b.ob;
  this.sessionId = this.requestId = null;
  this.itemIds = a;
  this.customData = null;
};
b.a("chrome.cast.media.QueueRemoveItemsRequest", chrome.cast.media.Gb);
chrome.cast.media.Hb = function(a) {
  this.type = e.b.pb;
  this.sessionId = this.requestId = null;
  this.itemIds = a;
  this.customData = this.insertBefore = null;
};
b.a("chrome.cast.media.QueueReorderItemsRequest", chrome.cast.media.Hb);
chrome.cast.media.$a = function() {
  this.metadataType = this.type = chrome.cast.media.i.GENERIC;
  this.releaseDate = this.releaseYear = this.images = this.subtitle = this.title = null;
};
b.a("chrome.cast.media.GenericMediaMetadata", chrome.cast.media.$a);
chrome.cast.media.vb = function() {
  this.metadataType = this.type = chrome.cast.media.i.MOVIE;
  this.releaseDate = this.releaseYear = this.images = this.subtitle = this.studio = this.title = null;
};
b.a("chrome.cast.media.MovieMediaMetadata", chrome.cast.media.vb);
chrome.cast.media.mc = function() {
  this.metadataType = this.type = chrome.cast.media.i.TV_SHOW;
  this.originalAirdate = this.releaseYear = this.images = this.episode = this.episodeNumber = this.season = this.seasonNumber = this.episodeTitle = this.title = this.seriesTitle = null;
};
b.a("chrome.cast.media.TvShowMediaMetadata", chrome.cast.media.mc);
chrome.cast.media.wb = function() {
  this.metadataType = this.type = chrome.cast.media.i.MUSIC_TRACK;
  this.releaseDate = this.releaseYear = this.images = this.discNumber = this.trackNumber = this.artistName = this.songName = this.composer = this.artist = this.albumArtist = this.title = this.albumName = null;
};
b.a("chrome.cast.media.MusicTrackMediaMetadata", chrome.cast.media.wb);
chrome.cast.media.zb = function() {
  this.metadataType = this.type = chrome.cast.media.i.PHOTO;
  this.creationDateTime = this.height = this.width = this.longitude = this.latitude = this.images = this.location = this.artist = this.title = null;
};
b.a("chrome.cast.media.PhotoMediaMetadata", chrome.cast.media.zb);
chrome.cast.media.ub = function(a, c) {
  this.contentId = a;
  this.streamType = chrome.cast.media.oa.BUFFERED;
  this.contentType = c;
  this.customData = this.textTrackStyle = this.tracks = this.duration = this.metadata = null;
};
b.a("chrome.cast.media.MediaInfo", chrome.cast.media.ub);
chrome.cast.media.Db = function(a) {
  this.itemId = null;
  this.media = a;
  this.autoplay = !0;
  this.startTime = 0;
  this.playbackDuration = null;
  this.preloadTime = 0;
  this.customData = this.activeTrackIds = null;
};
b.a("chrome.cast.media.QueueItem", chrome.cast.media.Db);
chrome.cast.media.sb = function(a, c) {
  this.sessionId = a;
  this.mediaSessionId = c;
  this.media = null;
  this.playbackRate = 1;
  this.playerState = chrome.cast.media.ka.IDLE;
  this.currentTime = 0;
  this.supportedMediaCommands = [];
  this.volume = new chrome.cast.Volume;
  this.items = this.preloadedItemId = this.loadingItemId = this.currentItemId = this.customData = this.activeTrackIds = this.idleReason = null;
  this.repeatMode = chrome.cast.media.R.OFF;
};
b.a("chrome.cast.media.Media", chrome.cast.media.sb);
chrome.cast.media.Qa = "CC1AD845";
b.a("chrome.cast.media.DEFAULT_MEDIA_RECEIVER_APP_ID", chrome.cast.media.Qa);
chrome.cast.media.timeout = {};
b.a("chrome.cast.media.timeout", chrome.cast.media.timeout);
chrome.cast.media.timeout.load = 0;
b.l(chrome.cast.media.timeout, "load", chrome.cast.media.timeout.load);
chrome.cast.media.timeout.Ec = 0;
b.l(chrome.cast.media.timeout, "getStatus", chrome.cast.media.timeout.Ec);
chrome.cast.media.timeout.play = 0;
b.l(chrome.cast.media.timeout, "play", chrome.cast.media.timeout.play);
chrome.cast.media.timeout.pause = 0;
b.l(chrome.cast.media.timeout, "pause", chrome.cast.media.timeout.pause);
chrome.cast.media.timeout.seek = 0;
b.l(chrome.cast.media.timeout, "seek", chrome.cast.media.timeout.seek);
chrome.cast.media.timeout.stop = 0;
b.l(chrome.cast.media.timeout, "stop", chrome.cast.media.timeout.stop);
chrome.cast.media.timeout.ad = 0;
b.l(chrome.cast.media.timeout, "setVolume", chrome.cast.media.timeout.ad);
chrome.cast.media.timeout.Bc = 0;
b.l(chrome.cast.media.timeout, "editTracksInfo", chrome.cast.media.timeout.Bc);
chrome.cast.media.timeout.Wc = 0;
b.l(chrome.cast.media.timeout, "queue", chrome.cast.media.timeout.Wc);
chrome.cast.media.kc = function(a, c) {
  this.trackId = a;
  this.trackContentType = this.trackContentId = null;
  this.type = c;
  this.customData = this.subtype = this.language = this.name = null;
};
b.a("chrome.cast.media.Track", chrome.cast.media.kc);
chrome.cast.media.hc = function() {
  this.customData = this.fontStyle = this.fontGenericFamily = this.fontFamily = this.fontScale = this.windowRoundedCornerRadius = this.windowColor = this.windowType = this.edgeColor = this.edgeType = this.backgroundColor = this.foregroundColor = null;
};
b.a("chrome.cast.media.TextTrackStyle", chrome.cast.media.hc);
chrome.cast.Ka = function(a, c, d, f, g) {
  this.sessionRequest = a;
  this.sessionListener = c;
  this.receiverListener = d;
  this.autoJoinPolicy = f || chrome.cast.aa.TAB_AND_ORIGIN_SCOPED;
  this.defaultActionPolicy = g || chrome.cast.ca.CREATE_SESSION;
  this.customDialLaunchCallback = null;
};
b.a("chrome.cast.ApiConfig", chrome.cast.Ka);
chrome.cast.Wa = function(a, c) {
  this.appName = a;
  this.launchParameter = c || null;
};
b.a("chrome.cast.DialRequest", chrome.cast.Wa);
chrome.cast.Ua = function(a, c, d) {
  this.receiver = a;
  this.appState = c;
  this.extraData = d || null;
};
b.a("chrome.cast.DialLaunchData", chrome.cast.Ua);
chrome.cast.Va = function(a, c) {
  this.doLaunch = a;
  this.launchParameter = c || null;
};
b.a("chrome.cast.DialLaunchResponse", chrome.cast.Va);
chrome.cast.ac = function(a, c, d) {
  this.appId = a;
  this.capabilities = c || [chrome.cast.M.VIDEO_OUT, chrome.cast.M.AUDIO_OUT];
  this.dialRequest = null;
  this.requestSessionTimeout = d || chrome.cast.timeout.requestSession;
  this.language = null;
};
b.a("chrome.cast.SessionRequest", chrome.cast.ac);
chrome.cast.Receiver = function(a, c, d, f) {
  this.label = a;
  this.friendlyName = c;
  this.capabilities = d || [];
  this.volume = f || null;
  this.receiverType = chrome.cast.la.CAST;
  this.ipAddress = this.displayStatus = this.isActiveInput = null;
};
b.a("chrome.cast.Receiver", chrome.cast.Receiver);
chrome.cast.Pb = function(a, c) {
  this.statusText = a;
  this.appImages = c;
  this.showStop = null;
};
b.a("chrome.cast.ReceiverDisplayStatus", chrome.cast.Pb);
chrome.cast.D = function(a, c, d, f, g) {
  this.sessionId = a;
  this.appId = c;
  this.displayName = d;
  this.statusText = null;
  this.appImages = f;
  this.receiver = g;
  this.senderApps = [];
  this.namespaces = [];
  this.media = [];
  this.status = chrome.cast.na.CONNECTED;
  this.transportId = "";
};
b.a("chrome.cast.Session", chrome.cast.D);
chrome.cast.D.Pa = "custom_receiver_session_id";
b.l(chrome.cast.D, "CUSTOM_RECEIVER_SESSION_ID", chrome.cast.D.Pa);
chrome.cast.timeout = {};
chrome.cast.timeout.requestSession = 1E4;
chrome.cast.timeout.leaveSession = 3E3;
chrome.cast.timeout.stopSession = 3E3;
chrome.cast.timeout.setReceiverVolume = 3E3;
chrome.cast.timeout.sendCustomMessage = 3E3;
chrome.cast.rb = "mirror_app_id";
b.a("chrome.cast.MIRROR_APP_ID", chrome.cast.rb);
e.rd = {};
e.N = function(a, c, d, f, g, h) {
  this.type = a;
  this.message = c;
  this.seqNum = d || null;
  this.clientId = f || null;
  this.appOrigin = null;
  this.timeoutMillis = b.isNumber(g) ? g : 0;
  this.receiverId = h || null;
  this.receiverList = null;
};
e.c = {bb:"iframe_init_result", fa:"fail_to_connect_to_extension", jd:"client_reconnect", pc:"v2_message", Ia:"app_message", hd:"client_init", yd:"log_message", Dd:"request_session", Ed:"request_session_by_id", ud:"leave_session", gd:"client_disconnect", Fd:"set_custom_receivers", md:"custom_dial_launch_response", Gd:"set_receiver_display_status", Bd:"query_tab_broadcast_status", Xa:"extension_ready", Ha:"api_iframe_ready", Lb:"receiver_availability", Kb:"receiver_action", xb:"new_session", oc:"update_session", 
Sa:"disconnect_session", Mb:"remove_session", Ja:"app_message_success", fb:"leave_session_success", Vb:"set_receiver_volume_success", Tb:"set_custom_receivers_success", ERROR:"error", Oa:"custom_dial_launch_request", Ub:"set_receiver_display_status_success", cc:"tab_broadcast_status"};
e.u = function(a) {
  this.v = a;
  this.U = this.m = null;
};
e.u.prototype.$c = function(a) {
  this.U = a;
};
e.u.prototype.Fa = function(a) {
  a.clientId = this.v;
  if (!this.m && (this.Rc(), !this.m)) {
    return;
  }
  this.m.postMessage(a);
};
e.u.prototype.Rc = function() {
  !this.m && (this.m = chrome.runtime.connect({name:this.v})) && (this.m.onMessage.addListener(b.bind(this.Ba, this)), this.m.onDisconnect.addListener(b.bind(this.Tc, this)));
};
e.u.prototype.Ba = function(a) {
  this.U && this.U(a);
};
e.u.prototype.Tc = function() {
  this.m = null;
  this.Ba(new e.N(e.c.fa, null));
};
e.A = function(a) {
  this.Ca = a;
  this.pa = null;
};
e.A.prototype.init = function() {
  window.addEventListener("message", this.Sc.bind(this), !1);
};
e.A.prototype.Zc = function(a) {
  this.pa = a;
};
e.A.prototype.Sc = function(a) {
  if (a.source != window) {
    var c = a.data;
    this.Ca = c.appOrigin = a.origin;
    this.pa(c);
  }
};
e.A.prototype.Ea = function(a) {
  a.clientId = null;
  window.parent.postMessage(a, this.Ca);
};
e.h = function() {
  this.v = "client-" + String(Math.floor(1E5 * Math.random()));
  this.sessionRequest = null;
  this.I = new e.A(e.h.Gc(window.location.href, "appOrigin"));
  this.V = new e.u(this.v);
  this.ua = 0;
  this.H = null;
};
e.h.prototype.init = function() {
  this.I.init();
  this.I.Zc(this.Uc.bind(this));
  this.V.$c(this.Vc.bind(this));
  this.yc();
};
e.h.Na = 1E3;
e.h.prototype.yc = function() {
  var a = this;
  this.ta();
  this.H = setInterval(function() {
    6 > a.ua ? a.ta() : a.Da(new chrome.cast.Error(chrome.cast.ea.EXTENSION_MISSING));
  }, e.h.Na);
};
e.h.prototype.ta = function() {
  this.ua++;
  this.V.Fa(new e.N(e.c.Ha, void 0, void 0, this.v));
};
e.h.prototype.Da = function(a) {
  this.H && (clearInterval(this.H), this.H = null, this.I.Ea(new e.N(e.c.bb, a)));
};
e.h.prototype.Uc = function(a) {
  a.clientId = this.v;
  this.V.Fa(a);
};
e.h.prototype.Vc = function(a) {
  switch(a.type) {
    case e.c.Ia:
    ;
    case e.c.Ja:
    ;
    case e.c.ERROR:
    ;
    case e.c.xb:
    ;
    case e.c.oc:
    ;
    case e.c.Sa:
    ;
    case e.c.Mb:
    ;
    case e.c.Lb:
    ;
    case e.c.pc:
    ;
    case e.c.fb:
    ;
    case e.c.Vb:
    ;
    case e.c.Tb:
    ;
    case e.c.fa:
    ;
    case e.c.Oa:
    ;
    case e.c.Ub:
    ;
    case e.c.Kb:
    ;
    case e.c.cc:
      this.I.Ea(a);
      break;
    case e.c.Xa:
      this.Da(null);
  }
};
e.h.Gc = function(a, c) {
  var d = decodeURIComponent(a).split("?")[1];
  if (!d) {
    return null;
  }
  for (var d = d.split("&"), f, g = 0;g < d.length;g++) {
    if (0 == d[g].indexOf(c)) {
      f = d[g];
      break;
    }
  }
  return f ? f.substring(c.length + 1) || null : null;
};
e.Ic = new e.h;
e.Ic.init();

