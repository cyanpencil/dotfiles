(function() {var b, chrome = window.chrome || {};
chrome.cast = chrome.cast || {};
chrome.cast.media = chrome.cast.media || {};
var g = g || {};
g.global = this;
g.V = function(a) {
  return void 0 !== a;
};
g.bb = function(a, c, d) {
  a = a.split(".");
  d = d || g.global;
  a[0] in d || !d.execScript || d.execScript("var " + a[0]);
  for (var e;a.length && (e = a.shift());) {
    !a.length && g.V(c) ? d[e] = c : d = d[e] ? d[e] : d[e] = {};
  }
};
g.si = function(a, c) {
  g.bb(a, c);
};
g.Z = !0;
g.rh = "en";
g.Ta = !0;
g.re = !1;
g.pd = !g.Z;
g.Kb = !1;
g.tj = function(a) {
  g.tc(a);
};
g.tc = function(a, c) {
  g.bb(a, c);
};
g.Je = /^[a-zA-Z_$][a-zA-Z0-9._$]*$/;
g.module = function(a) {
  if (!g.isString(a) || !a || -1 == a.search(g.Je)) {
    throw Error("Invalid module identifier");
  }
  if (!g.Af()) {
    throw Error("Module " + a + " has been loaded incorrectly.");
  }
  if (g.G.nb) {
    throw Error("goog.module may only be called once per module.");
  }
  g.G.nb = a;
};
g.module.get = function(a) {
  return g.module.rf(a);
};
g.module.rf = function() {
};
g.G = null;
g.Af = function() {
  return null != g.G;
};
g.module.Za = function() {
  g.G.Za = !0;
};
g.Cj = function(a) {
  if (g.pd) {
    throw a = a || "", Error("Importing test-only code into non-debug environment" + (a ? ": " + a : "."));
  }
};
g.xi = function() {
};
g.Hi = function(a, c) {
  for (var d = a.split("."), e = c || g.global, f;f = d.shift();) {
    if (g.o(e[f])) {
      e = e[f];
    } else {
      return null;
    }
  }
  return e;
};
g.Li = function(a, c) {
  var d = c || g.global, e;
  for (e in a) {
    d[e] = a[e];
  }
};
g.Kh = function(a, c, d, e) {
  if (g.Ib) {
    var f;
    a = a.replace(/\\/g, "/");
    for (var h = g.C, k = 0;f = c[k];k++) {
      h.ia[f] = a, h.ob[a] = !!e;
    }
    for (e = 0;c = d[e];e++) {
      a in h.requires || (h.requires[a] = {}), h.requires[a][c] = !0;
    }
  }
};
g.Uj = !1;
g.eh = !0;
g.hj = function(a) {
  g.global.console && g.global.console.error(a);
};
g.require = function() {
};
g.X = "";
g.Mc = function() {
};
g.Jh = function() {
  throw Error("unimplemented abstract method");
};
g.Lh = function(a) {
  a.Di = function() {
    if (a.Gc) {
      return a.Gc;
    }
    g.Z && (g.Hc[g.Hc.length] = a);
    return a.Gc = new a;
  };
};
g.Hc = [];
g.Gd = !0;
g.oe = g.Z;
g.Jf = {};
g.Ib = !1;
g.Ib && (g.C = {ob:{}, ia:{}, requires:{}, ad:{}, Ja:{}, ta:{}}, g.Ec = function() {
  var a = g.global.document;
  return null != a && "write" in a;
}, g.kf = function() {
  if (g.V(g.global.jd)) {
    g.X = g.global.jd;
  } else {
    if (g.Ec()) {
      for (var a = g.global.document.getElementsByTagName("SCRIPT"), c = a.length - 1;0 <= c;--c) {
        var d = a[c].src, e = d.lastIndexOf("?"), e = -1 == e ? d.length : e;
        if ("base.js" == d.substr(e - 7, 7)) {
          g.X = d.substr(0, e - 7);
          break;
        }
      }
    }
  }
}, g.gb = function(a, c) {
  (g.global.Yg || g.Rg)(a, c) && (g.C.Ja[a] = !0);
}, g.Dd = !(g.global.atob || !g.global.document || !g.global.document.all), g.wf = function(a) {
  g.gb("", 'goog.retrieveAndExecModule_("' + a + '");') && (g.C.Ja[a] = !0);
}, g.pb = [], g.Wj = function(a, c) {
  return g.Gd && g.V(g.global.JSON) ? "goog.loadModule(" + g.global.JSON.stringify(c + "\n//# sourceURL=" + a + "\n") + ");" : 'goog.loadModule(function(exports) {"use strict";' + c + "\n;return exports});\n//# sourceURL=" + a + "\n";
}, g.If = function() {
  var a = g.pb.length;
  if (0 < a) {
    var c = g.pb;
    g.pb = [];
    for (var d = 0;d < a;d++) {
      g.Lc(c[d]);
    }
  }
}, g.jj = function(a) {
  g.Ic(a) && g.Pe(a) && g.Lc(g.X + g.fb(a));
}, g.Ic = function(a) {
  return (a = g.fb(a)) && g.C.ob[a] ? g.X + a in g.C.ta : !1;
}, g.Pe = function(a) {
  if ((a = g.fb(a)) && a in g.C.requires) {
    for (var c in g.C.requires[a]) {
      if (!g.Df(c) && !g.Ic(c)) {
        return !1;
      }
    }
  }
  return !0;
}, g.Lc = function(a) {
  if (a in g.C.ta) {
    var c = g.C.ta[a];
    delete g.C.ta[a];
    g.tf(c);
  }
}, g.gj = function(a) {
  g.zg(a);
}, g.fj = function(a) {
  var c = g.G;
  try {
    g.G = {nb:void 0, Za:!1};
    var d;
    if (g.isFunction(a)) {
      d = a.call(g.global, {});
    } else {
      if (g.isString(a)) {
        d = g.Hf.call(g.global, a);
      } else {
        throw Error("Invalid module definition");
      }
    }
    var e = g.G.nb;
    if (!g.isString(e) || !e) {
      throw Error('Invalid module name "' + e + '"');
    }
    g.G.Za ? g.tc(e, d) : g.oe && Object.seal && Object.seal(d);
    g.Jf[e] = d;
  } finally {
    g.G = c;
  }
}, g.Hf = function(a) {
  eval(a);
  return {};
}, g.Qg = function(a) {
  g.global.document.write('<script type="text/javascript" src="' + a + '">\x3c/script>');
}, g.Qe = function(a) {
  var c = g.global.document, d = c.createElement("script");
  d.type = "text/javascript";
  d.src = a;
  d.defer = !1;
  d.async = !1;
  c.head.appendChild(d);
}, g.Rg = function(a, c) {
  if (g.Ec()) {
    var d = g.global.document;
    if (!g.Kb && "complete" == d.readyState) {
      if (/\bdeps.js$/.test(a)) {
        return !1;
      }
      throw Error('Cannot write "' + a + '" after document load');
    }
    var e = g.Dd;
    void 0 === c ? e ? (e = " onreadystatechange='goog.onScriptLoad_(this, " + ++g.Kc + ")' ", d.write('<script type="text/javascript" src="' + a + '"' + e + ">\x3c/script>")) : g.Kb ? g.Qe(a) : g.Qg(a) : d.write('<script type="text/javascript">' + c + "\x3c/script>");
    return !0;
  }
  return !1;
}, g.Kc = 0, g.qj = function(a, c) {
  "complete" == a.readyState && g.Kc == c && g.If();
  return !0;
}, g.Xj = function(a) {
  function c(a) {
    if (!(a in f.Ja || a in f.ad)) {
      f.ad[a] = !0;
      if (a in f.requires) {
        for (var h in f.requires[a]) {
          if (!g.Df(h)) {
            if (h in f.ia) {
              c(f.ia[h]);
            } else {
              throw Error("Undefined nameToPath for " + h);
            }
          }
        }
      }
      a in e || (e[a] = !0, d.push(a));
    }
  }
  var d = [], e = {}, f = g.C;
  c(a);
  for (a = 0;a < d.length;a++) {
    var h = d[a];
    g.C.Ja[h] = !0;
  }
  var k = g.G;
  g.G = null;
  for (a = 0;a < d.length;a++) {
    if (h = d[a]) {
      f.ob[h] ? g.wf(g.X + h) : g.gb(g.X + h);
    } else {
      throw g.G = k, Error("Undefined script input");
    }
  }
  g.G = k;
}, g.fb = function(a) {
  return a in g.C.ia ? g.C.ia[a] : null;
}, g.kf(), g.global.Zg || g.gb(g.X + "deps.js"));
g.mj = function(a) {
  a = a.split("/");
  for (var c = 0;c < a.length;) {
    "." == a[c] ? a.splice(c, 1) : c && ".." == a[c] && a[c - 1] && ".." != a[c - 1] ? a.splice(--c, 2) : c++;
  }
  return a.join("/");
};
g.ej = function(a) {
  if (g.global.kd) {
    return g.global.kd(a);
  }
  var c = new g.global.XMLHttpRequest;
  c.open("get", a, !1);
  c.send();
  return c.responseText;
};
g.zg = function() {
};
g.I = function(a) {
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
g.Cf = function(a) {
  return null === a;
};
g.o = function(a) {
  return null != a;
};
g.isArray = function(a) {
  return "array" == g.I(a);
};
g.za = function(a) {
  var c = g.I(a);
  return "array" == c || "object" == c && "number" == typeof a.length;
};
g.Ui = function(a) {
  return g.isObject(a) && "function" == typeof a.getFullYear;
};
g.isString = function(a) {
  return "string" == typeof a;
};
g.ha = function(a) {
  return "boolean" == typeof a;
};
g.isNumber = function(a) {
  return "number" == typeof a;
};
g.isFunction = function(a) {
  return "function" == g.I(a);
};
g.isObject = function(a) {
  var c = typeof a;
  return "object" == c && null != a || "function" == c;
};
g.Cc = function(a) {
  return a[g.W] || (a[g.W] = ++g.Kg);
};
g.Mi = function(a) {
  return !!a[g.W];
};
g.yg = function(a) {
  null !== a && "removeAttribute" in a && a.removeAttribute(g.W);
  try {
    delete a[g.W];
  } catch (c) {
  }
};
g.W = "closure_uid_" + (1E9 * Math.random() >>> 0);
g.Kg = 0;
g.Ci = g.Cc;
g.xj = g.yg;
g.$e = function(a) {
  var c = g.I(a);
  if ("object" == c || "array" == c) {
    if (a.clone) {
      return a.clone();
    }
    var c = "array" == c ? [] : {}, d;
    for (d in a) {
      c[d] = g.$e(a[d]);
    }
    return c;
  }
  return a;
};
g.Te = function(a, c, d) {
  return a.call.apply(a.bind, arguments);
};
g.Se = function(a, c, d) {
  if (!a) {
    throw Error();
  }
  if (2 < arguments.length) {
    var e = Array.prototype.slice.call(arguments, 2);
    return function() {
      var d = Array.prototype.slice.call(arguments);
      Array.prototype.unshift.apply(d, e);
      return a.apply(c, d);
    };
  }
  return function() {
    return a.apply(c, arguments);
  };
};
g.bind = function(a, c, d) {
  Function.prototype.bind && -1 != Function.prototype.bind.toString().indexOf("native code") ? g.bind = g.Te : g.bind = g.Se;
  return g.bind.apply(null, arguments);
};
g.ag = function(a, c) {
  var d = Array.prototype.slice.call(arguments, 1);
  return function() {
    var c = d.slice();
    c.push.apply(c, arguments);
    return a.apply(this, c);
  };
};
g.kj = function(a, c) {
  for (var d in c) {
    a[d] = c[d];
  }
};
g.now = g.Ta && Date.now || function() {
  return +new Date;
};
g.tf = function(a) {
  if (g.global.execScript) {
    g.global.execScript(a, "JavaScript");
  } else {
    if (g.global.eval) {
      if (null == g.va) {
        if (g.global.eval("var _evalTest_ = 1;"), "undefined" != typeof g.global._evalTest_) {
          try {
            delete g.global._evalTest_;
          } catch (e) {
          }
          g.va = !0;
        } else {
          g.va = !1;
        }
      }
      if (g.va) {
        g.global.eval(a);
      } else {
        var c = g.global.document, d = c.createElement("SCRIPT");
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
g.va = null;
g.Bi = function(a, c) {
  var d = function(a) {
    return g.wc[a] || a;
  }, e = function(a) {
    a = a.split("-");
    for (var c = [], e = 0;e < a.length;e++) {
      c.push(d(a[e]));
    }
    return c.join("-");
  }, e = g.wc ? "BY_WHOLE" == g.gf ? d : e : function(a) {
    return a;
  };
  return c ? a + "-" + e(c) : e(a);
};
g.zj = function(a, c) {
  g.wc = a;
  g.gf = c;
};
g.Fi = function(a, c) {
  c && (a = a.replace(/\{\$([^}]+)}/g, function(a, e) {
    return null != c && e in c ? c[e] : a;
  }));
  return a;
};
g.Gi = function(a) {
  return a;
};
g.c = function(a, c, d) {
  g.bb(a, c, d);
};
g.h = function(a, c, d) {
  a[c] = d;
};
g.hb = function(a, c) {
  function d() {
  }
  d.prototype = c.prototype;
  a.Ia = c.prototype;
  a.prototype = new d;
  a.prototype.constructor = a;
  a.Re = function(a, d, h) {
    for (var k = Array(arguments.length - 2), m = 2;m < arguments.length;m++) {
      k[m - 2] = arguments[m];
    }
    return c.prototype[d].apply(a, k);
  };
};
g.Re = function(a, c, d) {
  var e = arguments.callee.caller;
  if (g.re || g.Z && !e) {
    throw Error("arguments.caller not defined.  goog.base() cannot be used with strict mode code. See http://www.ecma-international.org/ecma-262/5.1/#sec-C");
  }
  if (e.Ia) {
    for (var f = Array(arguments.length - 1), h = 1;h < arguments.length;h++) {
      f[h - 1] = arguments[h];
    }
    return e.Ia.constructor.apply(a, f);
  }
  f = Array(arguments.length - 2);
  for (h = 2;h < arguments.length;h++) {
    f[h - 2] = arguments[h];
  }
  for (var h = !1, k = a.constructor;k;k = k.Ia && k.Ia.constructor) {
    if (k.prototype[c] === e) {
      h = !0;
    } else {
      if (h) {
        return k.prototype[c].apply(a, f);
      }
    }
  }
  if (a[c] === e) {
    return a.constructor.prototype[c].apply(a, f);
  }
  throw Error("goog.base called from a method of one name to a method of a different name");
};
g.scope = function(a) {
  a.call(g.global);
};
g.O = function(a, c) {
  var d = c.constructor, e = c.Fg;
  d && d != Object.prototype.constructor || (d = function() {
    throw Error("cannot instantiate an interface (no constructor defined).");
  });
  d = g.O.ef(d, a);
  a && g.hb(d, a);
  delete c.constructor;
  delete c.Fg;
  g.O.lc(d.prototype, c);
  null != e && (e instanceof Function ? e(d) : g.O.lc(d, e));
  return d;
};
g.O.ne = g.Z;
g.O.ef = function(a, c) {
  if (g.O.ne && Object.seal instanceof Function) {
    if (c && c.prototype && c.prototype[g.He]) {
      return a;
    }
    var d = function() {
      var c = a.apply(this, arguments) || this;
      c[g.W] = c[g.W];
      this.constructor === d && Object.seal(c);
      return c;
    };
    return d;
  }
  return a;
};
g.O.Vb = "constructor hasOwnProperty isPrototypeOf propertyIsEnumerable toLocaleString toString valueOf".split(" ");
g.O.lc = function(a, c) {
  for (var d in c) {
    Object.prototype.hasOwnProperty.call(c, d) && (a[d] = c[d]);
  }
  for (var e = 0;e < g.O.Vb.length;e++) {
    d = g.O.Vb[e], Object.prototype.hasOwnProperty.call(c, d) && (a[d] = c[d]);
  }
};
g.Kj = function() {
};
g.He = "goog_defineClass_legacy_unsealable";
chrome.cast.Fb = {TAB_AND_ORIGIN_SCOPED:"tab_and_origin_scoped", ORIGIN_SCOPED:"origin_scoped", PAGE_SCOPED:"page_scoped"};
g.c("chrome.cast.AutoJoinPolicy", chrome.cast.Fb);
chrome.cast.Jb = {CREATE_SESSION:"create_session", CAST_THIS_TAB:"cast_this_tab"};
g.c("chrome.cast.DefaultActionPolicy", chrome.cast.Jb);
chrome.cast.Ka = {VIDEO_OUT:"video_out", AUDIO_OUT:"audio_out", VIDEO_IN:"video_in", AUDIO_IN:"audio_in"};
g.c("chrome.cast.Capability", chrome.cast.Ka);
chrome.cast.K = {CANCEL:"cancel", TIMEOUT:"timeout", API_NOT_INITIALIZED:"api_not_initialized", INVALID_PARAMETER:"invalid_parameter", EXTENSION_NOT_COMPATIBLE:"extension_not_compatible", EXTENSION_MISSING:"extension_missing", RECEIVER_UNAVAILABLE:"receiver_unavailable", SESSION_ERROR:"session_error", CHANNEL_ERROR:"channel_error", LOAD_MEDIA_FAILED:"load_media_failed"};
g.c("chrome.cast.ErrorCode", chrome.cast.K);
chrome.cast.ke = {AVAILABLE:"available", UNAVAILABLE:"unavailable"};
g.c("chrome.cast.ReceiverAvailability", chrome.cast.ke);
chrome.cast.ue = {CHROME:"chrome", IOS:"ios", ANDROID:"android"};
g.c("chrome.cast.SenderPlatform", chrome.cast.ue);
chrome.cast.pa = {CAST:"cast", DIAL:"dial", HANGOUT:"hangout", CUSTOM:"custom"};
g.c("chrome.cast.ReceiverType", chrome.cast.pa);
chrome.cast.rd = {RUNNING:"running", STOPPED:"stopped", ERROR:"error"};
g.c("chrome.cast.DialAppState", chrome.cast.rd);
chrome.cast.je = {CAST:"cast", STOP:"stop"};
g.c("chrome.cast.ReceiverAction", chrome.cast.je);
chrome.cast.ga = {CONNECTED:"connected", DISCONNECTED:"disconnected", STOPPED:"stopped"};
g.c("chrome.cast.SessionStatus", chrome.cast.ga);
chrome.cast.VERSION = [1, 2];
g.c("chrome.cast.VERSION", chrome.cast.VERSION);
chrome.cast.Error = function(a, c, d) {
  this.code = a;
  this.description = c || null;
  this.details = d || null;
};
g.c("chrome.cast.Error", chrome.cast.Error);
chrome.cast.te = function(a) {
  this.platform = a;
  this.packageId = this.url = null;
};
g.c("chrome.cast.SenderApplication", chrome.cast.te);
chrome.cast.Image = function(a) {
  this.url = a;
  this.width = this.height = null;
};
g.c("chrome.cast.Image", chrome.cast.Image);
chrome.cast.Volume = function(a, c) {
  this.level = g.V(a) ? a : null;
  this.muted = g.V(c) ? c : null;
};
g.c("chrome.cast.Volume", chrome.cast.Volume);
var l = {m:{mh:"LAUNCH", fc:"STOP", dc:"SET_VOLUME", Ad:"GET_STATUS", zh:"RECEIVER_STATUS", Gh:"CONNECT", Hh:"CLOSE", ih:"GET_APP_AVAILABILITY", Ld:"LOAD", Md:"PAUSE", Sd:"SEEK", Nd:"PLAY", Sb:"STOP_MEDIA", Qb:"MEDIA_GET_STATUS", Rb:"MEDIA_SET_VOLUME", Kd:"EDIT_TRACKS_INFO", Pd:"QUEUE_LOAD", Od:"QUEUE_INSERT", Ma:"QUEUE_UPDATE", Qd:"QUEUE_REMOVE", Rd:"QUEUE_REORDER", jh:"INVALID_PLAYER_STATE", qh:"LOAD_FAILED", ph:"LOAD_CANCELLED", kh:"INVALID_REQUEST", Na:"MEDIA_STATUS", nh:"LAUNCH_ERROR", PING:"PING", 
wh:"PONG"}, Pa:{}};
l.Pa[l.m.Sb] = l.m.fc;
l.Pa[l.m.Rb] = l.m.dc;
l.Pa[l.m.Qb] = l.m.Ad;
l.fd = function(a, c, d) {
  this.sessionId = a;
  this.namespaceName = c;
  this.message = d;
};
l.we = function(a) {
  this.type = l.m.fc;
  this.requestId = null;
  this.sessionId = a || null;
};
chrome.cast.media.Ud = {PAUSE:"pause", SEEK:"seek", STREAM_VOLUME:"stream_volume", STREAM_MUTE:"stream_mute"};
g.c("chrome.cast.media.MediaCommand", chrome.cast.media.Ud);
chrome.cast.media.L = {GENERIC:0, MOVIE:1, TV_SHOW:2, MUSIC_TRACK:3, PHOTO:4};
g.c("chrome.cast.media.MetadataType", chrome.cast.media.L);
chrome.cast.media.ea = {IDLE:"IDLE", PLAYING:"PLAYING", PAUSED:"PAUSED", BUFFERING:"BUFFERING"};
g.c("chrome.cast.media.PlayerState", chrome.cast.media.ea);
chrome.cast.media.Ra = {OFF:"REPEAT_OFF", ALL:"REPEAT_ALL", SINGLE:"REPEAT_SINGLE", ALL_AND_SHUFFLE:"REPEAT_ALL_AND_SHUFFLE"};
g.c("chrome.cast.media.RepeatMode", chrome.cast.media.Ra);
chrome.cast.media.me = {PLAYBACK_START:"PLAYBACK_START", PLAYBACK_PAUSE:"PLAYBACK_PAUSE"};
g.c("chrome.cast.media.ResumeState", chrome.cast.media.me);
chrome.cast.media.Sa = {BUFFERED:"BUFFERED", LIVE:"LIVE", OTHER:"OTHER"};
g.c("chrome.cast.media.StreamType", chrome.cast.media.Sa);
chrome.cast.media.Ed = {CANCELLED:"CANCELLED", INTERRUPTED:"INTERRUPTED", FINISHED:"FINISHED", ERROR:"ERROR"};
g.c("chrome.cast.media.IdleReason", chrome.cast.media.Ed);
chrome.cast.media.Fe = {TEXT:"TEXT", AUDIO:"AUDIO", VIDEO:"VIDEO"};
g.c("chrome.cast.media.TrackType", chrome.cast.media.Fe);
chrome.cast.media.Ce = {SUBTITLES:"SUBTITLES", CAPTIONS:"CAPTIONS", DESCRIPTIONS:"DESCRIPTIONS", CHAPTERS:"CHAPTERS", METADATA:"METADATA"};
g.c("chrome.cast.media.TextTrackType", chrome.cast.media.Ce);
chrome.cast.media.ye = {NONE:"NONE", OUTLINE:"OUTLINE", DROP_SHADOW:"DROP_SHADOW", RAISED:"RAISED", DEPRESSED:"DEPRESSED"};
g.c("chrome.cast.media.TextTrackEdgeType", chrome.cast.media.ye);
chrome.cast.media.De = {NONE:"NONE", NORMAL:"NORMAL", ROUNDED_CORNERS:"ROUNDED_CORNERS"};
g.c("chrome.cast.media.TextTrackWindowType", chrome.cast.media.De);
chrome.cast.media.ze = {SANS_SERIF:"SANS_SERIF", MONOSPACED_SANS_SERIF:"MONOSPACED_SANS_SERIF", SERIF:"SERIF", MONOSPACED_SERIF:"MONOSPACED_SERIF", CASUAL:"CASUAL", CURSIVE:"CURSIVE", SMALL_CAPITALS:"SMALL_CAPITALS"};
g.c("chrome.cast.media.TextTrackFontGenericFamily", chrome.cast.media.ze);
chrome.cast.media.Ae = {NORMAL:"NORMAL", BOLD:"BOLD", BOLD_ITALIC:"BOLD_ITALIC", ITALIC:"ITALIC"};
g.c("chrome.cast.media.TextTrackFontStyle", chrome.cast.media.Ae);
chrome.cast.media.Nb = function() {
  this.type = l.m.Qb;
  this.customData = null;
};
g.c("chrome.cast.media.GetStatusRequest", chrome.cast.media.Nb);
chrome.cast.media.Xb = function() {
  this.type = l.m.Md;
  this.customData = null;
};
g.c("chrome.cast.media.PauseRequest", chrome.cast.media.Xb);
chrome.cast.media.Yb = function() {
  this.type = l.m.Nd;
  this.customData = null;
};
g.c("chrome.cast.media.PlayRequest", chrome.cast.media.Yb);
chrome.cast.media.se = function() {
  this.type = l.m.Sd;
  this.customData = this.resumeState = this.currentTime = null;
};
g.c("chrome.cast.media.SeekRequest", chrome.cast.media.se);
chrome.cast.media.hc = function() {
  this.type = l.m.Sb;
  this.customData = null;
};
g.c("chrome.cast.media.StopRequest", chrome.cast.media.hc);
chrome.cast.media.Ke = function(a) {
  this.type = l.m.Rb;
  this.volume = a;
  this.customData = null;
};
g.c("chrome.cast.media.VolumeRequest", chrome.cast.media.Ke);
chrome.cast.media.Id = function(a) {
  this.type = l.m.Ld;
  this.sessionId = this.requestId = null;
  this.media = a;
  this.activeTrackIds = null;
  this.autoplay = !0;
  this.customData = this.currentTime = null;
};
g.c("chrome.cast.media.LoadRequest", chrome.cast.media.Id);
chrome.cast.media.xd = function(a, c) {
  this.type = l.m.Kd;
  this.requestId = null;
  this.activeTrackIds = a || null;
  this.textTrackStyle = c || null;
};
g.c("chrome.cast.media.EditTracksInfoRequest", chrome.cast.media.xd);
chrome.cast.media.ce = function(a) {
  this.type = l.m.Pd;
  this.sessionId = this.requestId = null;
  this.items = a;
  this.startIndex = 0;
  this.repeatMode = chrome.cast.media.Ra.OFF;
  this.customData = this.currentTime = null;
};
g.c("chrome.cast.media.QueueLoadRequest", chrome.cast.media.ce);
chrome.cast.media.Qa = function(a) {
  this.type = l.m.Od;
  this.sessionId = this.requestId = null;
  this.items = a;
  this.customData = this.currentTime = this.currentItemIndex = this.currentItemId = this.insertBefore = null;
};
g.c("chrome.cast.media.QueueInsertItemsRequest", chrome.cast.media.Qa);
chrome.cast.media.be = function(a) {
  this.item = a;
  this.customData = this.currentTime = this.insertBefore = null;
};
g.c("chrome.cast.media.QueueInsertAndPlayItemRequest", chrome.cast.media.be);
chrome.cast.media.de = function(a) {
  this.type = l.m.Ma;
  this.sessionId = this.requestId = null;
  this.items = a;
  this.customData = null;
};
g.c("chrome.cast.media.QueueUpdateItemsRequest", chrome.cast.media.de);
chrome.cast.media.fa = function() {
  this.type = l.m.Ma;
  this.customData = this.currentTime = this.jump = this.currentItemId = this.sessionId = this.requestId = null;
};
g.c("chrome.cast.media.QueueJumpRequest", chrome.cast.media.fa);
chrome.cast.media.cc = function() {
  this.type = l.m.Ma;
  this.customData = this.repeatMode = this.sessionId = this.requestId = null;
};
g.c("chrome.cast.media.QueueSetPropertiesRequest", chrome.cast.media.cc);
chrome.cast.media.ac = function(a) {
  this.type = l.m.Qd;
  this.sessionId = this.requestId = null;
  this.itemIds = a;
  this.customData = null;
};
g.c("chrome.cast.media.QueueRemoveItemsRequest", chrome.cast.media.ac);
chrome.cast.media.bc = function(a) {
  this.type = l.m.Rd;
  this.sessionId = this.requestId = null;
  this.itemIds = a;
  this.customData = this.insertBefore = null;
};
g.c("chrome.cast.media.QueueReorderItemsRequest", chrome.cast.media.bc);
chrome.cast.media.Bd = function() {
  this.metadataType = this.type = chrome.cast.media.L.GENERIC;
  this.releaseDate = this.releaseYear = this.images = this.subtitle = this.title = null;
};
g.c("chrome.cast.media.GenericMediaMetadata", chrome.cast.media.Bd);
chrome.cast.media.Wd = function() {
  this.metadataType = this.type = chrome.cast.media.L.MOVIE;
  this.releaseDate = this.releaseYear = this.images = this.subtitle = this.studio = this.title = null;
};
g.c("chrome.cast.media.MovieMediaMetadata", chrome.cast.media.Wd);
chrome.cast.media.Ge = function() {
  this.metadataType = this.type = chrome.cast.media.L.TV_SHOW;
  this.originalAirdate = this.releaseYear = this.images = this.episode = this.episodeNumber = this.season = this.seasonNumber = this.episodeTitle = this.title = this.seriesTitle = null;
};
g.c("chrome.cast.media.TvShowMediaMetadata", chrome.cast.media.Ge);
chrome.cast.media.Xd = function() {
  this.metadataType = this.type = chrome.cast.media.L.MUSIC_TRACK;
  this.releaseDate = this.releaseYear = this.images = this.discNumber = this.trackNumber = this.artistName = this.songName = this.composer = this.artist = this.albumArtist = this.title = this.albumName = null;
};
g.c("chrome.cast.media.MusicTrackMediaMetadata", chrome.cast.media.Xd);
chrome.cast.media.$d = function() {
  this.metadataType = this.type = chrome.cast.media.L.PHOTO;
  this.creationDateTime = this.height = this.width = this.longitude = this.latitude = this.images = this.location = this.artist = this.title = null;
};
g.c("chrome.cast.media.PhotoMediaMetadata", chrome.cast.media.$d);
chrome.cast.media.Vd = function(a, c) {
  this.contentId = a;
  this.streamType = chrome.cast.media.Sa.BUFFERED;
  this.contentType = c;
  this.customData = this.textTrackStyle = this.tracks = this.duration = this.metadata = null;
};
g.c("chrome.cast.media.MediaInfo", chrome.cast.media.Vd);
chrome.cast.media.$b = function(a) {
  this.itemId = null;
  this.media = a;
  this.autoplay = !0;
  this.startTime = 0;
  this.playbackDuration = null;
  this.preloadTime = 0;
  this.customData = this.activeTrackIds = null;
};
g.c("chrome.cast.media.QueueItem", chrome.cast.media.$b);
chrome.cast.media.f = function(a, c) {
  this.sessionId = a;
  this.mediaSessionId = c;
  this.media = null;
  this.playbackRate = 1;
  this.playerState = chrome.cast.media.ea.IDLE;
  this.currentTime = 0;
  this.lb = -1;
  this.supportedMediaCommands = [];
  this.volume = new chrome.cast.Volume;
  this.items = this.preloadedItemId = this.loadingItemId = this.currentItemId = this.customData = this.activeTrackIds = this.idleReason = null;
  this.repeatMode = chrome.cast.media.Ra.OFF;
  this.ya = this.mb = !1;
  this.ma = [];
};
g.c("chrome.cast.media.Media", chrome.cast.media.f);
chrome.cast.media.od = "CC1AD845";
g.c("chrome.cast.media.DEFAULT_MEDIA_RECEIVER_APP_ID", chrome.cast.media.od);
chrome.cast.media.timeout = {};
g.c("chrome.cast.media.timeout", chrome.cast.media.timeout);
chrome.cast.media.timeout.load = 0;
g.h(chrome.cast.media.timeout, "load", chrome.cast.media.timeout.load);
chrome.cast.media.timeout.xa = 0;
g.h(chrome.cast.media.timeout, "getStatus", chrome.cast.media.timeout.xa);
chrome.cast.media.timeout.play = 0;
g.h(chrome.cast.media.timeout, "play", chrome.cast.media.timeout.play);
chrome.cast.media.timeout.pause = 0;
g.h(chrome.cast.media.timeout, "pause", chrome.cast.media.timeout.pause);
chrome.cast.media.timeout.seek = 0;
g.h(chrome.cast.media.timeout, "seek", chrome.cast.media.timeout.seek);
chrome.cast.media.timeout.stop = 0;
g.h(chrome.cast.media.timeout, "stop", chrome.cast.media.timeout.stop);
chrome.cast.media.timeout.Ha = 0;
g.h(chrome.cast.media.timeout, "setVolume", chrome.cast.media.timeout.Ha);
chrome.cast.media.timeout.ua = 0;
g.h(chrome.cast.media.timeout, "editTracksInfo", chrome.cast.media.timeout.ua);
chrome.cast.media.timeout.D = 0;
g.h(chrome.cast.media.timeout, "queue", chrome.cast.media.timeout.D);
chrome.cast.media.Ee = function(a, c) {
  this.trackId = a;
  this.trackContentType = this.trackContentId = null;
  this.type = c;
  this.customData = this.subtype = this.language = this.name = null;
};
g.c("chrome.cast.media.Track", chrome.cast.media.Ee);
chrome.cast.media.Be = function() {
  this.customData = this.fontStyle = this.fontGenericFamily = this.fontFamily = this.fontScale = this.windowRoundedCornerRadius = this.windowColor = this.windowType = this.edgeColor = this.edgeType = this.backgroundColor = this.foregroundColor = null;
};
g.c("chrome.cast.media.TextTrackStyle", chrome.cast.media.Be);
chrome.cast.dd = function(a, c, d, e, f) {
  this.sessionRequest = a;
  this.sessionListener = c;
  this.receiverListener = d;
  this.autoJoinPolicy = e || chrome.cast.Fb.TAB_AND_ORIGIN_SCOPED;
  this.defaultActionPolicy = f || chrome.cast.Jb.CREATE_SESSION;
  this.customDialLaunchCallback = null;
};
g.c("chrome.cast.ApiConfig", chrome.cast.dd);
chrome.cast.ud = function(a, c) {
  this.appName = a;
  this.launchParameter = c || null;
};
g.c("chrome.cast.DialRequest", chrome.cast.ud);
chrome.cast.sd = function(a, c, d) {
  this.receiver = a;
  this.appState = c;
  this.extraData = d || null;
};
g.c("chrome.cast.DialLaunchData", chrome.cast.sd);
chrome.cast.td = function(a, c) {
  this.doLaunch = a;
  this.launchParameter = c || null;
};
g.c("chrome.cast.DialLaunchResponse", chrome.cast.td);
chrome.cast.ve = function(a, c, d) {
  this.appId = a;
  this.capabilities = c || [chrome.cast.Ka.VIDEO_OUT, chrome.cast.Ka.AUDIO_OUT];
  this.dialRequest = null;
  this.requestSessionTimeout = d || chrome.cast.timeout.requestSession;
  this.language = null;
};
g.c("chrome.cast.SessionRequest", chrome.cast.ve);
chrome.cast.Receiver = function(a, c, d, e) {
  this.label = a;
  this.friendlyName = c;
  this.capabilities = d || [];
  this.volume = e || null;
  this.receiverType = chrome.cast.pa.CAST;
  this.ipAddress = this.displayStatus = this.isActiveInput = null;
};
g.c("chrome.cast.Receiver", chrome.cast.Receiver);
chrome.cast.le = function(a, c) {
  this.statusText = a;
  this.appImages = c;
  this.showStop = null;
};
g.c("chrome.cast.ReceiverDisplayStatus", chrome.cast.le);
chrome.cast.j = function(a, c, d, e, f) {
  this.sessionId = a;
  this.appId = c;
  this.displayName = d;
  this.statusText = null;
  this.appImages = e;
  this.receiver = f;
  this.senderApps = [];
  this.namespaces = [];
  this.media = [];
  this.status = chrome.cast.ga.CONNECTED;
  this.transportId = "";
};
g.c("chrome.cast.Session", chrome.cast.j);
chrome.cast.j.Hb = "custom_receiver_session_id";
g.h(chrome.cast.j, "CUSTOM_RECEIVER_SESSION_ID", chrome.cast.j.Hb);
chrome.cast.timeout = {};
chrome.cast.timeout.requestSession = 1E4;
chrome.cast.timeout.leaveSession = 3E3;
chrome.cast.timeout.stopSession = 3E3;
chrome.cast.timeout.setReceiverVolume = 3E3;
chrome.cast.timeout.sendCustomMessage = 3E3;
chrome.cast.Td = "mirror_app_id";
g.c("chrome.cast.MIRROR_APP_ID", chrome.cast.Td);
l.ed = function(a) {
  this.sessionRequest = a.sessionRequest;
  this.autoJoinPolicy = a.autoJoinPolicy;
  this.defaultActionPolicy = a.defaultActionPolicy;
  this.useCustomDialLaunch = !!a.customDialLaunchCallback;
  this.isTopLevelWindow = window.self == window.top;
};
l.gc = function(a, c) {
  this.type = l.m.dc;
  this.requestId = null;
  this.volume = a;
  this.expectedVolume = c || null;
};
g.debug = {};
g.debug.Error = function(a) {
  if (Error.captureStackTrace) {
    Error.captureStackTrace(this, g.debug.Error);
  } else {
    var c = Error().stack;
    c && (this.stack = c);
  }
  a && (this.message = String(a));
};
g.hb(g.debug.Error, Error);
g.debug.Error.prototype.name = "CustomError";
g.xc = {};
g.xc.Zd = {vd:1, Ug:2, TEXT:3, Vg:4, gh:5, fh:6, xh:7, $g:8, ah:9, dh:10, bh:11, vh:12};
g.a = {};
g.a.La = !1;
g.a.zd = !1;
g.a.Ie = {Yd:"\u00a0"};
g.a.startsWith = function(a, c) {
  return 0 == a.lastIndexOf(c, 0);
};
g.a.endsWith = function(a, c) {
  var d = a.length - c.length;
  return 0 <= d && a.indexOf(c, d) == d;
};
g.a.ei = function(a, c) {
  return 0 == g.a.pc(c, a.substr(0, c.length));
};
g.a.ci = function(a, c) {
  return 0 == g.a.pc(c, a.substr(a.length - c.length, c.length));
};
g.a.di = function(a, c) {
  return a.toLowerCase() == c.toLowerCase();
};
g.a.Gg = function(a, c) {
  for (var d = a.split("%s"), e = "", f = Array.prototype.slice.call(arguments, 1);f.length && 1 < d.length;) {
    e += d.shift() + f.shift();
  }
  return e + d.join("%s");
};
g.a.ji = function(a) {
  return a.replace(/[\s\xa0]+/g, " ").replace(/^\s+|\s+$/g, "");
};
g.a.jb = function(a) {
  return /^[\s\xa0]*$/.test(a);
};
g.a.Wi = function(a) {
  return 0 == a.length;
};
g.a.Jc = g.a.jb;
g.a.yf = function(a) {
  return g.a.jb(g.a.Kf(a));
};
g.a.Vi = g.a.yf;
g.a.Ti = function(a) {
  return !/[^\t\n\r ]/.test(a);
};
g.a.Ri = function(a) {
  return !/[^a-zA-Z]/.test(a);
};
g.a.Zi = function(a) {
  return !/[^0-9]/.test(a);
};
g.a.Si = function(a) {
  return !/[^a-zA-Z0-9]/.test(a);
};
g.a.aj = function(a) {
  return " " == a;
};
g.a.bj = function(a) {
  return 1 == a.length && " " <= a && "~" >= a || "\u0080" <= a && "\ufffd" >= a;
};
g.a.Ij = function(a) {
  return a.replace(/(\r\n|\r|\n)+/g, " ");
};
g.a.$h = function(a) {
  return a.replace(/(\r\n|\r|\n)/g, "\n");
};
g.a.oj = function(a) {
  return a.replace(/\xa0|\s/g, " ");
};
g.a.nj = function(a) {
  return a.replace(/\xa0|[ \t]+/g, " ");
};
g.a.ii = function(a) {
  return a.replace(/[\t\r\n ]+/g, " ").replace(/^[\t\r\n ]+|[\t\r\n ]+$/g, "");
};
g.a.trim = g.Ta && String.prototype.trim ? function(a) {
  return a.trim();
} : function(a) {
  return a.replace(/^[\s\xa0]+|[\s\xa0]+$/g, "");
};
g.a.trimLeft = function(a) {
  return a.replace(/^[\s\xa0]+/, "");
};
g.a.trimRight = function(a) {
  return a.replace(/[\s\xa0]+$/, "");
};
g.a.pc = function(a, c) {
  var d = String(a).toLowerCase(), e = String(c).toLowerCase();
  return d < e ? -1 : d == e ? 0 : 1;
};
g.a.Oc = function(a, c, d) {
  if (a == c) {
    return 0;
  }
  if (!a) {
    return -1;
  }
  if (!c) {
    return 1;
  }
  for (var e = a.toLowerCase().match(d), f = c.toLowerCase().match(d), h = Math.min(e.length, f.length), k = 0;k < h;k++) {
    d = e[k];
    var m = f[k];
    if (d != m) {
      return a = parseInt(d, 10), !isNaN(a) && (c = parseInt(m, 10), !isNaN(c) && a - c) ? a - c : d < m ? -1 : 1;
    }
  }
  return e.length != f.length ? e.length - f.length : a < c ? -1 : 1;
};
g.a.Pi = function(a, c) {
  return g.a.Oc(a, c, /\d+|\D+/g);
};
g.a.of = function(a, c) {
  return g.a.Oc(a, c, /\d+|\.\d+|\D+/g);
};
g.a.pj = g.a.of;
g.a.Tj = function(a) {
  return encodeURIComponent(String(a));
};
g.a.Sj = function(a) {
  return decodeURIComponent(a.replace(/\+/g, " "));
};
g.a.Nf = function(a, c) {
  return a.replace(/(\r\n|\r|\n)/g, c ? "<br />" : "<br>");
};
g.a.Dc = function(a, c) {
  if (c) {
    a = a.replace(g.a.Db, "&amp;").replace(g.a.Pb, "&lt;").replace(g.a.Mb, "&gt;").replace(g.a.Zb, "&quot;").replace(g.a.ec, "&#39;").replace(g.a.Ub, "&#0;"), g.a.La && (a = a.replace(g.a.Lb, "&#101;"));
  } else {
    if (!g.a.bd.test(a)) {
      return a;
    }
    -1 != a.indexOf("&") && (a = a.replace(g.a.Db, "&amp;"));
    -1 != a.indexOf("<") && (a = a.replace(g.a.Pb, "&lt;"));
    -1 != a.indexOf(">") && (a = a.replace(g.a.Mb, "&gt;"));
    -1 != a.indexOf('"') && (a = a.replace(g.a.Zb, "&quot;"));
    -1 != a.indexOf("'") && (a = a.replace(g.a.ec, "&#39;"));
    -1 != a.indexOf("\x00") && (a = a.replace(g.a.Ub, "&#0;"));
    g.a.La && -1 != a.indexOf("e") && (a = a.replace(g.a.Lb, "&#101;"));
  }
  return a;
};
g.a.Db = /&/g;
g.a.Pb = /</g;
g.a.Mb = />/g;
g.a.Zb = /"/g;
g.a.ec = /'/g;
g.a.Ub = /\x00/g;
g.a.Lb = /e/g;
g.a.bd = g.a.La ? /[\x00&<>"'e]/ : /[\x00&<>"']/;
g.a.Cb = function(a) {
  return g.a.contains(a, "&") ? !g.a.zd && "document" in g.global ? g.a.Yc(a) : g.a.Lg(a) : a;
};
g.a.Rj = function(a, c) {
  return g.a.contains(a, "&") ? g.a.Yc(a, c) : a;
};
g.a.Yc = function(a, c) {
  var d = {"&amp;":"&", "&lt;":"<", "&gt;":">", "&quot;":'"'}, e;
  e = c ? c.createElement("div") : g.global.document.createElement("div");
  return a.replace(g.a.Cd, function(a, c) {
    var k = d[a];
    if (k) {
      return k;
    }
    if ("#" == c.charAt(0)) {
      var m = Number("0" + c.substr(1));
      isNaN(m) || (k = String.fromCharCode(m));
    }
    k || (e.innerHTML = a + " ", k = e.firstChild.nodeValue.slice(0, -1));
    return d[a] = k;
  });
};
g.a.Lg = function(a) {
  return a.replace(/&([^;]+);/g, function(a, d) {
    switch(d) {
      case "amp":
        return "&";
      case "lt":
        return "<";
      case "gt":
        return ">";
      case "quot":
        return '"';
      default:
        if ("#" == d.charAt(0)) {
          var e = Number("0" + d.substr(1));
          if (!isNaN(e)) {
            return String.fromCharCode(e);
          }
        }
        return a;
    }
  });
};
g.a.Cd = /&([^;\s<&]+);?/g;
g.a.Vj = function(a, c) {
  return g.a.Nf(a.replace(/  /g, " &#160;"), c);
};
g.a.sj = function(a) {
  return a.replace(/(^|[\n ]) /g, "$1" + g.a.Ie.Yd);
};
g.a.Jj = function(a, c) {
  for (var d = c.length, e = 0;e < d;e++) {
    var f = 1 == d ? c : c.charAt(e);
    if (a.charAt(0) == f && a.charAt(a.length - 1) == f) {
      return a.substring(1, a.length - 1);
    }
  }
  return a;
};
g.a.truncate = function(a, c, d) {
  d && (a = g.a.Cb(a));
  a.length > c && (a = a.substring(0, c - 3) + "...");
  d && (a = g.a.Dc(a));
  return a;
};
g.a.Qj = function(a, c, d, e) {
  d && (a = g.a.Cb(a));
  if (e && a.length > c) {
    e > c && (e = c), a = a.substring(0, c - e) + "..." + a.substring(a.length - e);
  } else {
    if (a.length > c) {
      e = Math.floor(c / 2);
      var f = a.length - e;
      a = a.substring(0, e + c % 2) + "..." + a.substring(f);
    }
  }
  d && (a = g.a.Dc(a));
  return a;
};
g.a.Ab = {"\x00":"\\0", "\b":"\\b", "\f":"\\f", "\n":"\\n", "\r":"\\r", "\t":"\\t", "\x0B":"\\x0B", '"':'\\"', "\\":"\\\\", "<":"<"};
g.a.Ba = {"'":"\\'"};
g.a.quote = function(a) {
  a = String(a);
  for (var c = ['"'], d = 0;d < a.length;d++) {
    var e = a.charAt(d), f = e.charCodeAt(0);
    c[d + 1] = g.a.Ab[e] || (31 < f && 127 > f ? e : g.a.yc(e));
  }
  c.push('"');
  return c.join("");
};
g.a.ui = function(a) {
  for (var c = [], d = 0;d < a.length;d++) {
    c[d] = g.a.yc(a.charAt(d));
  }
  return c.join("");
};
g.a.yc = function(a) {
  if (a in g.a.Ba) {
    return g.a.Ba[a];
  }
  if (a in g.a.Ab) {
    return g.a.Ba[a] = g.a.Ab[a];
  }
  var c = a, d = a.charCodeAt(0);
  if (31 < d && 127 > d) {
    c = a;
  } else {
    if (256 > d) {
      if (c = "\\x", 16 > d || 256 < d) {
        c += "0";
      }
    } else {
      c = "\\u", 4096 > d && (c += "0");
    }
    c += d.toString(16).toUpperCase();
  }
  return g.a.Ba[a] = c;
};
g.a.contains = function(a, c) {
  return -1 != a.indexOf(c);
};
g.a.bi = function(a, c) {
  return g.a.contains(a.toLowerCase(), c.toLowerCase());
};
g.a.oi = function(a, c) {
  return a && c ? a.split(c).length - 1 : 0;
};
g.a.ja = function(a, c, d) {
  var e = a;
  0 <= c && c < a.length && 0 < d && (e = a.substr(0, c) + a.substr(c + d, a.length - c - d));
  return e;
};
g.a.remove = function(a, c) {
  var d = new RegExp(g.a.qb(c), "");
  return a.replace(d, "");
};
g.a.removeAll = function(a, c) {
  var d = new RegExp(g.a.qb(c), "g");
  return a.replace(d, "");
};
g.a.qb = function(a) {
  return String(a).replace(/([-()\[\]{}+?*.$\^|,:#<!\\])/g, "\\$1").replace(/\x08/g, "\\x08");
};
g.a.repeat = String.prototype.repeat ? function(a, c) {
  return a.repeat(c);
} : function(a, c) {
  return Array(c + 1).join(a);
};
g.a.rj = function(a, c, d) {
  a = g.V(d) ? a.toFixed(d) : String(a);
  d = a.indexOf(".");
  -1 == d && (d = a.length);
  return g.a.repeat("0", Math.max(0, c - d)) + a;
};
g.a.Kf = function(a) {
  return null == a ? "" : String(a);
};
g.a.Zh = function(a) {
  return Array.prototype.join.call(arguments, "");
};
g.a.Ii = function() {
  return Math.floor(2147483648 * Math.random()).toString(36) + Math.abs(Math.floor(2147483648 * Math.random()) ^ g.now()).toString(36);
};
g.a.li = function(a, c) {
  for (var d = 0, e = g.a.trim(String(a)).split("."), f = g.a.trim(String(c)).split("."), h = Math.max(e.length, f.length), k = 0;0 == d && k < h;k++) {
    var m = e[k] || "", n = f[k] || "", p = /(\d*)(\D*)/g, t = /(\d*)(\D*)/g;
    do {
      var q = p.exec(m) || ["", "", ""], r = t.exec(n) || ["", "", ""];
      if (0 == q[0].length && 0 == r[0].length) {
        break;
      }
      d = g.a.Ya(0 == q[1].length ? 0 : parseInt(q[1], 10), 0 == r[1].length ? 0 : parseInt(r[1], 10)) || g.a.Ya(0 == q[2].length, 0 == r[2].length) || g.a.Ya(q[2], r[2]);
    } while (0 == d);
  }
  return d;
};
g.a.Ya = function(a, c) {
  return a < c ? -1 : a > c ? 1 : 0;
};
g.a.Ni = function(a) {
  for (var c = 0, d = 0;d < a.length;++d) {
    c = 31 * c + a.charCodeAt(d) >>> 0;
  }
  return c;
};
g.a.Mg = 2147483648 * Math.random() | 0;
g.a.ri = function() {
  return "goog_" + g.a.Mg++;
};
g.a.Mj = function(a) {
  var c = Number(a);
  return 0 == c && g.a.jb(a) ? NaN : c;
};
g.a.Yi = function(a) {
  return /^[a-z]+([A-Z][a-z]*)*$/.test(a);
};
g.a.cj = function(a) {
  return /^([A-Z][a-z]*)+$/.test(a);
};
g.a.Lj = function(a) {
  return String(a).replace(/\-([a-z])/g, function(a, d) {
    return d.toUpperCase();
  });
};
g.a.Oj = function(a) {
  return String(a).replace(/([A-Z])/g, "-$1").toLowerCase();
};
g.a.Pj = function(a, c) {
  var d = g.isString(c) ? g.a.qb(c) : "\\s";
  return a.replace(new RegExp("(^" + (d ? "|[" + d + "]+" : "") + ")([a-z])", "g"), function(a, c, d) {
    return c + d.toUpperCase();
  });
};
g.a.ai = function(a) {
  return String(a.charAt(0)).toUpperCase() + String(a.substr(1)).toLowerCase();
};
g.a.parseInt = function(a) {
  isFinite(a) && (a = String(a));
  return g.isString(a) ? /^\s*-?0x/i.test(a) ? parseInt(a, 16) : parseInt(a, 10) : NaN;
};
g.a.Gj = function(a, c, d) {
  a = a.split(c);
  for (var e = [];0 < d && a.length;) {
    e.push(a.shift()), d--;
  }
  a.length && e.push(a.join(c));
  return e;
};
g.a.ti = function(a, c) {
  var d = [], e = [];
  if (a == c) {
    return 0;
  }
  if (!a.length || !c.length) {
    return Math.max(a.length, c.length);
  }
  for (var f = 0;f < c.length + 1;f++) {
    d[f] = f;
  }
  for (f = 0;f < a.length;f++) {
    e[0] = f + 1;
    for (var h = 0;h < c.length;h++) {
      e[h + 1] = Math.min(e[h] + 1, d[h + 1] + 1, d[h] + (a[f] != c[h]));
    }
    for (h = 0;h < d.length;h++) {
      d[h] = e[h];
    }
  }
  return e[c.length];
};
g.g = {};
g.g.J = g.Z;
g.g.na = function(a, c) {
  c.unshift(a);
  g.debug.Error.call(this, g.a.Gg.apply(null, c));
  c.shift();
};
g.hb(g.g.na, g.debug.Error);
g.g.na.prototype.name = "AssertionError";
g.g.nd = function(a) {
  throw a;
};
g.g.ab = g.g.nd;
g.g.S = function(a, c, d, e) {
  var f = "Assertion failed";
  if (d) {
    var f = f + (": " + d), h = e
  } else {
    a && (f += ": " + a, h = c);
  }
  a = new g.g.na("" + f, h || []);
  g.g.ab(a);
};
g.g.Aj = function(a) {
  g.g.J && (g.g.ab = a);
};
g.g.assert = function(a, c, d) {
  g.g.J && !a && g.g.S("", null, c, Array.prototype.slice.call(arguments, 2));
  return a;
};
g.g.jf = function(a, c) {
  g.g.J && g.g.ab(new g.g.na("Failure" + (a ? ": " + a : ""), Array.prototype.slice.call(arguments, 1)));
};
g.g.Rh = function(a, c, d) {
  g.g.J && !g.isNumber(a) && g.g.S("Expected number but got %s: %s.", [g.I(a), a], c, Array.prototype.slice.call(arguments, 2));
  return a;
};
g.g.Uh = function(a, c, d) {
  g.g.J && !g.isString(a) && g.g.S("Expected string but got %s: %s.", [g.I(a), a], c, Array.prototype.slice.call(arguments, 2));
  return a;
};
g.g.Ph = function(a, c, d) {
  g.g.J && !g.isFunction(a) && g.g.S("Expected function but got %s: %s.", [g.I(a), a], c, Array.prototype.slice.call(arguments, 2));
  return a;
};
g.g.Sh = function(a, c, d) {
  g.g.J && !g.isObject(a) && g.g.S("Expected object but got %s: %s.", [g.I(a), a], c, Array.prototype.slice.call(arguments, 2));
  return a;
};
g.g.Mh = function(a, c, d) {
  g.g.J && !g.isArray(a) && g.g.S("Expected array but got %s: %s.", [g.I(a), a], c, Array.prototype.slice.call(arguments, 2));
  return a;
};
g.g.Nh = function(a, c, d) {
  g.g.J && !g.ha(a) && g.g.S("Expected boolean but got %s: %s.", [g.I(a), a], c, Array.prototype.slice.call(arguments, 2));
  return a;
};
g.g.Oh = function(a, c, d) {
  !g.g.J || g.isObject(a) && a.nodeType == g.xc.Zd.vd || g.g.S("Expected Element but got %s: %s.", [g.I(a), a], c, Array.prototype.slice.call(arguments, 2));
  return a;
};
g.g.Qh = function(a, c, d, e) {
  !g.g.J || a instanceof c || g.g.S("Expected instanceof %s but got %s.", [g.g.Bc(c), g.g.Bc(a)], d, Array.prototype.slice.call(arguments, 3));
  return a;
};
g.g.Th = function() {
  for (var a in Object.prototype) {
    g.g.jf(a + " should not be enumerable in Object.prototype.");
  }
};
g.g.Bc = function(a) {
  return a instanceof Function ? a.displayName || a.name || "unknown type name" : a instanceof Object ? a.constructor.displayName || a.constructor.name || Object.prototype.toString.call(a) : null === a ? "null" : typeof a;
};
g.b = {};
g.R = g.Ta;
g.b.P = !1;
g.b.bg = function(a) {
  return a[a.length - 1];
};
g.b.dj = g.b.bg;
g.b.indexOf = g.R && (g.b.P || Array.prototype.indexOf) ? function(a, c, d) {
  return Array.prototype.indexOf.call(a, c, d);
} : function(a, c, d) {
  d = null == d ? 0 : 0 > d ? Math.max(0, a.length + d) : d;
  if (g.isString(a)) {
    return g.isString(c) && 1 == c.length ? a.indexOf(c, d) : -1;
  }
  for (;d < a.length;d++) {
    if (d in a && a[d] === c) {
      return d;
    }
  }
  return -1;
};
g.b.lastIndexOf = g.R && (g.b.P || Array.prototype.lastIndexOf) ? function(a, c, d) {
  return Array.prototype.lastIndexOf.call(a, c, null == d ? a.length - 1 : d);
} : function(a, c, d) {
  d = null == d ? a.length - 1 : d;
  0 > d && (d = Math.max(0, a.length + d));
  if (g.isString(a)) {
    return g.isString(c) && 1 == c.length ? a.lastIndexOf(c, d) : -1;
  }
  for (;0 <= d;d--) {
    if (d in a && a[d] === c) {
      return d;
    }
  }
  return -1;
};
g.b.forEach = g.R && (g.b.P || Array.prototype.forEach) ? function(a, c, d) {
  Array.prototype.forEach.call(a, c, d);
} : function(a, c, d) {
  for (var e = a.length, f = g.isString(a) ? a.split("") : a, h = 0;h < e;h++) {
    h in f && c.call(d, f[h], h, a);
  }
};
g.b.Ac = function(a, c, d) {
  for (var e = a.length, f = g.isString(a) ? a.split("") : a, e = e - 1;0 <= e;--e) {
    e in f && c.call(d, f[e], e, a);
  }
};
g.b.filter = g.R && (g.b.P || Array.prototype.filter) ? function(a, c, d) {
  return Array.prototype.filter.call(a, c, d);
} : function(a, c, d) {
  for (var e = a.length, f = [], h = 0, k = g.isString(a) ? a.split("") : a, m = 0;m < e;m++) {
    if (m in k) {
      var n = k[m];
      c.call(d, n, m, a) && (f[h++] = n);
    }
  }
  return f;
};
g.b.map = g.R && (g.b.P || Array.prototype.map) ? function(a, c, d) {
  return Array.prototype.map.call(a, c, d);
} : function(a, c, d) {
  for (var e = a.length, f = Array(e), h = g.isString(a) ? a.split("") : a, k = 0;k < e;k++) {
    k in h && (f[k] = c.call(d, h[k], k, a));
  }
  return f;
};
g.b.reduce = g.R && (g.b.P || Array.prototype.reduce) ? function(a, c, d, e) {
  e && (c = g.bind(c, e));
  return Array.prototype.reduce.call(a, c, d);
} : function(a, c, d, e) {
  var f = d;
  g.b.forEach(a, function(d, k) {
    f = c.call(e, f, d, k, a);
  });
  return f;
};
g.b.reduceRight = g.R && (g.b.P || Array.prototype.reduceRight) ? function(a, c, d, e) {
  e && (c = g.bind(c, e));
  return Array.prototype.reduceRight.call(a, c, d);
} : function(a, c, d, e) {
  var f = d;
  g.b.Ac(a, function(d, k) {
    f = c.call(e, f, d, k, a);
  });
  return f;
};
g.b.some = g.R && (g.b.P || Array.prototype.some) ? function(a, c, d) {
  return Array.prototype.some.call(a, c, d);
} : function(a, c, d) {
  for (var e = a.length, f = g.isString(a) ? a.split("") : a, h = 0;h < e;h++) {
    if (h in f && c.call(d, f[h], h, a)) {
      return !0;
    }
  }
  return !1;
};
g.b.every = g.R && (g.b.P || Array.prototype.every) ? function(a, c, d) {
  return Array.prototype.every.call(a, c, d);
} : function(a, c, d) {
  for (var e = a.length, f = g.isString(a) ? a.split("") : a, h = 0;h < e;h++) {
    if (h in f && !c.call(d, f[h], h, a)) {
      return !1;
    }
  }
  return !0;
};
g.b.count = function(a, c, d) {
  var e = 0;
  g.b.forEach(a, function(a, h, k) {
    c.call(d, a, h, k) && ++e;
  }, d);
  return e;
};
g.b.find = function(a, c, d) {
  c = g.b.findIndex(a, c, d);
  return 0 > c ? null : g.isString(a) ? a.charAt(c) : a[c];
};
g.b.findIndex = function(a, c, d) {
  for (var e = a.length, f = g.isString(a) ? a.split("") : a, h = 0;h < e;h++) {
    if (h in f && c.call(d, f[h], h, a)) {
      return h;
    }
  }
  return -1;
};
g.b.vi = function(a, c, d) {
  c = g.b.lf(a, c, d);
  return 0 > c ? null : g.isString(a) ? a.charAt(c) : a[c];
};
g.b.lf = function(a, c, d) {
  for (var e = a.length, f = g.isString(a) ? a.split("") : a, e = e - 1;0 <= e;e--) {
    if (e in f && c.call(d, f[e], e, a)) {
      return e;
    }
  }
  return -1;
};
g.b.contains = function(a, c) {
  return 0 <= g.b.indexOf(a, c);
};
g.b.Jc = function(a) {
  return 0 == a.length;
};
g.b.clear = function(a) {
  if (!g.isArray(a)) {
    for (var c = a.length - 1;0 <= c;c--) {
      delete a[c];
    }
  }
  a.length = 0;
};
g.b.insert = function(a, c) {
  g.b.contains(a, c) || a.push(c);
};
g.b.Fc = function(a, c, d) {
  g.b.splice(a, d, 0, c);
};
g.b.Oi = function(a, c, d) {
  g.ag(g.b.splice, a, d, 0).apply(null, c);
};
g.b.insertBefore = function(a, c, d) {
  var e;
  2 == arguments.length || 0 > (e = g.b.indexOf(a, d)) ? a.push(c) : g.b.Fc(a, c, e);
};
g.b.remove = function(a, c) {
  var d = g.b.indexOf(a, c), e;
  (e = 0 <= d) && g.b.ja(a, d);
  return e;
};
g.b.ja = function(a, c) {
  return 1 == Array.prototype.splice.call(a, c, 1).length;
};
g.b.yj = function(a, c, d) {
  c = g.b.findIndex(a, c, d);
  return 0 <= c ? (g.b.ja(a, c), !0) : !1;
};
g.b.vj = function(a, c, d) {
  var e = 0;
  g.b.Ac(a, function(f, h) {
    c.call(d, f, h, a) && g.b.ja(a, h) && e++;
  });
  return e;
};
g.b.concat = function(a) {
  return Array.prototype.concat.apply(Array.prototype, arguments);
};
g.b.join = function(a) {
  return Array.prototype.concat.apply(Array.prototype, arguments);
};
g.b.Ig = function(a) {
  var c = a.length;
  if (0 < c) {
    for (var d = Array(c), e = 0;e < c;e++) {
      d[e] = a[e];
    }
    return d;
  }
  return [];
};
g.b.clone = g.b.Ig;
g.b.extend = function(a, c) {
  for (var d = 1;d < arguments.length;d++) {
    var e = arguments[d];
    if (g.za(e)) {
      var f = a.length || 0, h = e.length || 0;
      a.length = f + h;
      for (var k = 0;k < h;k++) {
        a[f + k] = e[k];
      }
    } else {
      a.push(e);
    }
  }
};
g.b.splice = function(a, c, d, e) {
  return Array.prototype.splice.apply(a, g.b.slice(arguments, 1));
};
g.b.slice = function(a, c, d) {
  return 2 >= arguments.length ? Array.prototype.slice.call(a, c) : Array.prototype.slice.call(a, c, d);
};
g.b.wj = function(a, c, d) {
  c = c || a;
  var e = function(a) {
    return g.isObject(a) ? "o" + g.Cc(a) : (typeof a).charAt(0) + a;
  };
  d = d || e;
  for (var e = {}, f = 0, h = 0;h < a.length;) {
    var k = a[h++], m = d(k);
    Object.prototype.hasOwnProperty.call(e, m) || (e[m] = !0, c[f++] = k);
  }
  c.length = f;
};
g.b.mc = function(a, c, d) {
  return g.b.nc(a, d || g.b.U, !1, c);
};
g.b.Xh = function(a, c, d) {
  return g.b.nc(a, c, !0, void 0, d);
};
g.b.nc = function(a, c, d, e, f) {
  for (var h = 0, k = a.length, m;h < k;) {
    var n = h + k >> 1, p;
    p = d ? c.call(f, a[n], n, a) : c(e, a[n]);
    0 < p ? h = n + 1 : (k = n, m = !p);
  }
  return m ? h : ~h;
};
g.b.sort = function(a, c) {
  a.sort(c || g.b.U);
};
g.b.Hj = function(a, c) {
  for (var d = 0;d < a.length;d++) {
    a[d] = {index:d, value:a[d]};
  }
  var e = c || g.b.U;
  g.b.sort(a, function(a, c) {
    return e(a.value, c.value) || a.index - c.index;
  });
  for (d = 0;d < a.length;d++) {
    a[d] = a[d].value;
  }
};
g.b.Eg = function(a, c, d) {
  var e = d || g.b.U;
  g.b.sort(a, function(a, d) {
    return e(c(a), c(d));
  });
};
g.b.Fj = function(a, c, d) {
  g.b.Eg(a, function(a) {
    return a[c];
  }, d);
};
g.b.$i = function(a, c, d) {
  c = c || g.b.U;
  for (var e = 1;e < a.length;e++) {
    var f = c(a[e - 1], a[e]);
    if (0 < f || 0 == f && d) {
      return !1;
    }
  }
  return !0;
};
g.b.equals = function(a, c, d) {
  if (!g.za(a) || !g.za(c) || a.length != c.length) {
    return !1;
  }
  var e = a.length;
  d = d || g.b.hf;
  for (var f = 0;f < e;f++) {
    if (!d(a[f], c[f])) {
      return !1;
    }
  }
  return !0;
};
g.b.ki = function(a, c, d) {
  d = d || g.b.U;
  for (var e = Math.min(a.length, c.length), f = 0;f < e;f++) {
    var h = d(a[f], c[f]);
    if (0 != h) {
      return h;
    }
  }
  return g.b.U(a.length, c.length);
};
g.b.U = function(a, c) {
  return a > c ? 1 : a < c ? -1 : 0;
};
g.b.Qi = function(a, c) {
  return -g.b.U(a, c);
};
g.b.hf = function(a, c) {
  return a === c;
};
g.b.Vh = function(a, c, d) {
  d = g.b.mc(a, c, d);
  return 0 > d ? (g.b.Fc(a, c, -(d + 1)), !0) : !1;
};
g.b.Wh = function(a, c, d) {
  c = g.b.mc(a, c, d);
  return 0 <= c ? g.b.ja(a, c) : !1;
};
g.b.Yh = function(a, c, d) {
  for (var e = {}, f = 0;f < a.length;f++) {
    var h = a[f], k = c.call(d, h, f, a);
    g.V(k) && (e[k] || (e[k] = [])).push(h);
  }
  return e;
};
g.b.Nj = function(a, c, d) {
  var e = {};
  g.b.forEach(a, function(f, h) {
    e[c.call(d, f, h, a)] = f;
  });
  return e;
};
g.b.uj = function(a, c, d) {
  var e = [], f = 0, h = a;
  d = d || 1;
  void 0 !== c && (f = a, h = c);
  if (0 > d * (h - f)) {
    return [];
  }
  if (0 < d) {
    for (a = f;a < h;a += d) {
      e.push(a);
    }
  } else {
    for (a = f;a > h;a += d) {
      e.push(a);
    }
  }
  return e;
};
g.b.repeat = function(a, c) {
  for (var d = [], e = 0;e < c;e++) {
    d[e] = a;
  }
  return d;
};
g.b.nf = function(a) {
  for (var c = [], d = 0;d < arguments.length;d++) {
    var e = arguments[d];
    if (g.isArray(e)) {
      for (var f = 0;f < e.length;f += 8192) {
        for (var h = g.b.nf.apply(null, g.b.slice(e, f, f + 8192)), k = 0;k < h.length;k++) {
          c.push(h[k]);
        }
      }
    } else {
      c.push(e);
    }
  }
  return c;
};
g.b.rotate = function(a, c) {
  a.length && (c %= a.length, 0 < c ? Array.prototype.unshift.apply(a, a.splice(-c, c)) : 0 > c && Array.prototype.push.apply(a, a.splice(0, -c)));
  return a;
};
g.b.lj = function(a, c, d) {
  c = Array.prototype.splice.call(a, c, 1);
  Array.prototype.splice.call(a, d, 0, c[0]);
};
g.b.Yj = function(a) {
  if (!arguments.length) {
    return [];
  }
  for (var c = [], d = arguments[0].length, e = 1;e < arguments.length;e++) {
    arguments[e].length < d && (d = arguments[e].length);
  }
  for (e = 0;e < d;e++) {
    for (var f = [], h = 0;h < arguments.length;h++) {
      f.push(arguments[h][e]);
    }
    c.push(f);
  }
  return c;
};
g.b.Ej = function(a, c) {
  for (var d = c || Math.random, e = a.length - 1;0 < e;e--) {
    var f = Math.floor(d() * (e + 1)), h = a[e];
    a[e] = a[f];
    a[f] = h;
  }
};
g.b.ni = function(a, c) {
  var d = [];
  g.b.forEach(c, function(c) {
    d.push(a[c]);
  });
  return d;
};
g.object = {};
g.object.forEach = function(a, c, d) {
  for (var e in a) {
    c.call(d, a[e], e, a);
  }
};
g.object.filter = function(a, c, d) {
  var e = {}, f;
  for (f in a) {
    c.call(d, a[f], f, a) && (e[f] = a[f]);
  }
  return e;
};
g.object.map = function(a, c, d) {
  var e = {}, f;
  for (f in a) {
    e[f] = c.call(d, a[f], f, a);
  }
  return e;
};
g.object.some = function(a, c, d) {
  for (var e in a) {
    if (c.call(d, a[e], e, a)) {
      return !0;
    }
  }
  return !1;
};
g.object.every = function(a, c, d) {
  for (var e in a) {
    if (!c.call(d, a[e], e, a)) {
      return !1;
    }
  }
  return !0;
};
g.object.Ai = function(a) {
  var c = 0, d;
  for (d in a) {
    c++;
  }
  return c;
};
g.object.yi = function(a) {
  for (var c in a) {
    return c;
  }
};
g.object.zi = function(a) {
  for (var c in a) {
    return a[c];
  }
};
g.object.contains = function(a, c) {
  return g.object.uc(a, c);
};
g.object.Ki = function(a) {
  var c = [], d = 0, e;
  for (e in a) {
    c[d++] = a[e];
  }
  return c;
};
g.object.Ei = function(a) {
  var c = [], d = 0, e;
  for (e in a) {
    c[d++] = e;
  }
  return c;
};
g.object.Ji = function(a, c) {
  for (var d = g.za(c), e = d ? c : arguments, d = d ? 0 : 1;d < e.length && (a = a[e[d]], g.V(a));d++) {
  }
  return a;
};
g.object.mi = function(a, c) {
  return null !== a && c in a;
};
g.object.uc = function(a, c) {
  for (var d in a) {
    if (a[d] == c) {
      return !0;
    }
  }
  return !1;
};
g.object.mf = function(a, c, d) {
  for (var e in a) {
    if (c.call(d, a[e], e, a)) {
      return e;
    }
  }
};
g.object.wi = function(a, c, d) {
  return (c = g.object.mf(a, c, d)) && a[c];
};
g.object.Jc = function(a) {
  for (var c in a) {
    return !1;
  }
  return !0;
};
g.object.clear = function(a) {
  for (var c in a) {
    delete a[c];
  }
};
g.object.remove = function(a, c) {
  var d;
  (d = c in a) && delete a[c];
  return d;
};
g.object.add = function(a, c, d) {
  if (null !== a && c in a) {
    throw Error('The object already contains the key "' + c + '"');
  }
  g.object.set(a, c, d);
};
g.object.get = function(a, c, d) {
  return null !== a && c in a ? a[c] : d;
};
g.object.set = function(a, c, d) {
  a[c] = d;
};
g.object.Bj = function(a, c, d) {
  return c in a ? a[c] : a[c] = d;
};
g.object.Dj = function(a, c, d) {
  if (c in a) {
    return a[c];
  }
  d = d();
  return a[c] = d;
};
g.object.equals = function(a, c) {
  for (var d in a) {
    if (!(d in c) || a[d] !== c[d]) {
      return !1;
    }
  }
  for (d in c) {
    if (!(d in a)) {
      return !1;
    }
  }
  return !0;
};
g.object.clone = function(a) {
  var c = {}, d;
  for (d in a) {
    c[d] = a[d];
  }
  return c;
};
g.object.Ng = function(a) {
  var c = g.I(a);
  if ("object" == c || "array" == c) {
    if (g.isFunction(a.clone)) {
      return a.clone();
    }
    var c = "array" == c ? [] : {}, d;
    for (d in a) {
      c[d] = g.object.Ng(a[d]);
    }
    return c;
  }
  return a;
};
g.object.Jg = function(a) {
  var c = {}, d;
  for (d in a) {
    c[a[d]] = d;
  }
  return c;
};
g.object.Wb = "constructor hasOwnProperty isPrototypeOf propertyIsEnumerable toLocaleString toString valueOf".split(" ");
g.object.extend = function(a, c) {
  for (var d, e, f = 1;f < arguments.length;f++) {
    e = arguments[f];
    for (d in e) {
      a[d] = e[d];
    }
    for (var h = 0;h < g.object.Wb.length;h++) {
      d = g.object.Wb[h], Object.prototype.hasOwnProperty.call(e, d) && (a[d] = e[d]);
    }
  }
};
g.object.create = function(a) {
  var c = arguments.length;
  if (1 == c && g.isArray(arguments[0])) {
    return g.object.create.apply(null, arguments[0]);
  }
  if (c % 2) {
    throw Error("Uneven number of arguments");
  }
  for (var d = {}, e = 0;e < c;e += 2) {
    d[arguments[e]] = arguments[e + 1];
  }
  return d;
};
g.object.ff = function(a) {
  var c = arguments.length;
  if (1 == c && g.isArray(arguments[0])) {
    return g.object.ff.apply(null, arguments[0]);
  }
  for (var d = {}, e = 0;e < c;e++) {
    d[arguments[e]] = !0;
  }
  return d;
};
g.object.pi = function(a) {
  var c = a;
  Object.isFrozen && !Object.isFrozen(a) && (c = Object.create(a), Object.freeze(c));
  return c;
};
g.object.Xi = function(a) {
  return !!Object.isFrozen && Object.isFrozen(a);
};
l.u = {};
l.u.Oa = "urn:x-cast:";
l.u.Jd = 128;
l.u.Ff = function(a) {
  return a.length > l.u.Oa.length && g.a.startsWith(a, l.u.Oa) && a.length <= l.u.Jd;
};
l.u.$ = function(a) {
  return l.u.Oa + "com.google.cast." + a;
};
l.u.md = {Eh:l.u.$("tp.connection"), Fh:l.u.$("tp.heartbeat"), yh:l.u.$("receiver"), sh:l.u.$("media"), th:l.u.$("media.universalRemote.optIn"), Ih:l.u.$("webrtc")};
l.u.xe = g.object.Jg(l.u.md);
l.u.xf = function(a) {
  return l.u.xe.hasOwnProperty(a);
};
l.s = {};
l.s.gi = function(a) {
  return a ? g.isString(a.sessionId) ? g.o(a.media) ? g.o(a.autoplay) && !g.ha(a.autoplay) ? "Invalid autoplay in LoadRequest." : g.o(a.currentTime) && !g.isNumber(a.currentTime) ? "Invalid currentTime in LoadRequest." : l.s.qc(a.media) : "media cannot be null or undefined in LoadRequest." : "Invalid sessionId in LoadRequest." : "LoadRequest cannot be null.";
};
l.s.hi = function(a) {
  if (!a) {
    return "QueueLoadRequest cannot be null.";
  }
  if (!g.isString(a.sessionId)) {
    return "Invalid sessionId in QueueLoadRequest.";
  }
  if (!g.o(a.items) || 0 >= a.items.length) {
    return "items cannot be null or undefined or empty in QueueLoadRequest.";
  }
  if (!g.o(a.repeatMode) || !g.isString(a.repeatMode)) {
    return "Invalid repeatMode in QueueLoadRequest.";
  }
  if (!g.o(a.startIndex) || !g.isNumber(a.startIndex) || 0 > a.startIndex || a.startIndex >= a.items.length) {
    return "Invalid startIndex in QueueLoadRequest.";
  }
  for (var c = 0;c < a.items.length;c++) {
    var d = l.s.Ye(!1, a.items[c]);
    if ("" != d) {
      return "At index " + c + ": " + d;
    }
  }
  return "";
};
l.s.qc = function(a) {
  return a ? !g.isString(a.contentId) || 1E3 < a.contentId.length ? "Invalid contentId in MediaInfo." : g.object.uc(chrome.cast.media.Sa, a.streamType) ? g.isString(a.contentType) ? g.o(a.duration) && !g.isNumber(a.duration) ? "Invalid duration in MediaInfo." : "" : "Invalid contentType in MediaInfo." : "Invalid streamType in MediaInfo." : "MediaInfo cannot be null.";
};
l.s.Ye = function(a, c) {
  if (!c) {
    return "QueueItem cannot be null.";
  }
  if (a) {
    if (g.o(c.itemId) && !g.isNumber(c.itemId)) {
      return "Invalid itemId in QueueItem.";
    }
  } else {
    if (g.o(c.itemId)) {
      return "itemId cannot be set in QueueItem.";
    }
  }
  return g.o(c.autoplay) && g.ha(c.autoplay) ? !g.o(c.startTime) || !g.isNumber(c.startTime) || 0 > c.startTime ? "Invalid startTime in QueueItem." : g.o(c.playbackDuration) && !g.isNumber(c.playbackDuration) ? "Invalid playbackDuration in QueueItem." : !g.o(c.preloadTime) || !g.isNumber(c.preloadTime) || 0 > c.preloadTime ? "Invalid preloadTime in QueueItem." : l.s.qc(c.media) : "Invalid autoplay in QueueItem.";
};
l.s.We = function(a) {
  return !!a && g.o(a.sessionId) && g.isString(a.namespaceName) && l.u.Ff(a.namespaceName) && !l.u.xf(a.namespaceName);
};
l.s.Ve = function(a) {
  return a && g.isFunction(a.sessionListener) && g.isFunction(a.receiverListener) ? l.s.rc(a.sessionRequest) : !1;
};
l.s.Xe = function(a) {
  return a ? !g.b.find(a, function(a) {
    return !((a.receiverType == chrome.cast.pa.CUSTOM || a.receiverType == chrome.cast.pa.DIAL) && g.o(a.friendlyName) && 0 == a.capabilities.length && g.Cf(a.volume));
  }) : !1;
};
l.s.rc = function(a) {
  return !a || !g.o(a.appId) || g.o(a.dialRequest) && (!g.isString(a.dialRequest.appName) || g.o(a.dialRequest.launchParameter) && !g.isString(a.dialRequest.launchParameter)) ? !1 : !0;
};
l.s.Ze = function(a) {
  return a && g.o(a.volume) && l.s.sc(a.volume) ? g.o(a.expectedVolume) ? l.s.sc(a.expectedVolume) : !0 : !1;
};
l.s.sc = function(a) {
  return a ? g.o(a.level) ? g.isNumber(a.level) && 0 <= a.level && 1 >= a.level : g.ha(a.muted) : !1;
};
l.s.fi = function(a) {
  return !!a && g.ha(a.doLaunch) && (!g.o(a.launchParameter) || g.isString(a.launchParameter));
};
l.lh = {};
l.F = function(a, c, d, e, f, h) {
  this.type = a;
  this.message = c;
  this.seqNum = d || null;
  this.clientId = e || null;
  this.appOrigin = null;
  this.timeoutMillis = g.isNumber(f) ? f : 0;
  this.receiverId = h || null;
  this.receiverList = null;
};
l.l = {Ob:"iframe_init_result", yd:"fail_to_connect_to_extension", Xg:"client_reconnect", qa:"v2_message", Eb:"app_message", hd:"client_init", Hd:"log_message", he:"request_session", ie:"request_session_by_id", Fd:"leave_session", Wg:"client_disconnect", pe:"set_custom_receivers", Gb:"custom_dial_launch_response", qe:"set_receiver_display_status", ae:"query_tab_broadcast_status", hh:"extension_ready", Sg:"api_iframe_ready", fe:"receiver_availability", ee:"receiver_action", Tb:"new_session", ic:"update_session", 
qd:"disconnect_session", ge:"remove_session", Tg:"app_message_success", oh:"leave_session_success", Ch:"set_receiver_volume_success", Ah:"set_custom_receivers_success", ERROR:"error", ld:"custom_dial_launch_request", Bh:"set_receiver_display_status_success", Dh:"tab_broadcast_status"};
l.uh = function() {
  this.type = l.m.Na;
  this.requestId = null;
  this.status = [];
  this.customData = null;
  this.sessionId = "";
};
chrome.cast.da = function(a, c) {
  this.vf = a;
  this.kc = c;
  this.jc = null;
};
chrome.cast.da.prototype.init = function() {
  window.addEventListener("message", this.Sf.bind(this), !1);
};
chrome.cast.da.prototype.Bg = function(a) {
  this.jc = a;
};
chrome.cast.da.prototype.Sf = function(a) {
  a.source != window && a.origin == this.kc && (a = a.data, a.type == l.l.Ob && (this.zf = !a.message), this.jc(a));
};
chrome.cast.da.prototype.wb = function(a) {
  this.zf && this.vf.contentWindow.postMessage(a, this.kc);
};
l.w = function(a, c, d) {
  this.Xc = a;
  this.$a = c;
  this.Bb = g.isNumber(d) ? d : 0;
  this.Aa = !1;
  this.la = null;
};
l.w.gd = 432E5;
l.w.prototype.Ef = function() {
  return this.Aa;
};
l.w.prototype.vb = function() {
  this.Aa = !0;
  this.$a = this.Xc = null;
  this.la && (clearTimeout(this.la), this.la = null);
};
l.w.Nc = function() {
};
l.w.prototype.$c = function() {
  var a = this.Xc;
  this.vb();
  return a || l.w.Nc;
};
l.w.prototype.Zc = function() {
  var a = this.$a;
  this.vb();
  return a || l.w.Nc;
};
l.w.prototype.Wc = function(a, c) {
  if (!this.Aa && !this.la) {
    var d = function() {
      if (!this.Aa) {
        a && a();
        var d = this.$a;
        this.vb();
        if (0 < this.Bb) {
          var f = new chrome.cast.Error(chrome.cast.K.TIMEOUT);
          c && (f.description = c);
          d(f);
        }
      }
    }.bind(this);
    this.la = setTimeout(d, 0 < this.Bb ? this.Bb : l.w.gd);
  }
};
l.oa = function() {
  this.T = {};
};
b = l.oa.prototype;
b.add = function(a, c) {
  var d = this.T[a];
  if (d) {
    return -1 == d.indexOf(c) && d.push(c), !1;
  }
  this.T[a] = [c];
  return !0;
};
b.remove = function(a, c) {
  var d = this.T[a];
  if (!d) {
    return !1;
  }
  var e = d.indexOf(c);
  if (-1 == e) {
    return !1;
  }
  if (1 == d.length) {
    return delete this.T[a], !0;
  }
  d.splice(e, 1);
  return !1;
};
b.Pc = function(a) {
  if (!(a in this.T)) {
    return !1;
  }
  delete this.T[a];
  return !0;
};
b.sg = function(a) {
  var c = !1;
  Object.keys(this.T).forEach(function(d) {
    0 == d.indexOf(a) && (delete this.T[d], c = !0);
  }, this);
  return c;
};
b.get = function(a) {
  return this.T[a] || [];
};
b.contains = function(a, c) {
  return -1 != this.get(a).indexOf(c);
};
l.H = function() {
  this.ca = {};
  this.Da = {};
};
b = l.H.prototype;
b.wg = function(a, c) {
  var d = this.ca[a];
  return d ? (d.status = c, d.media.forEach(function(a) {
    delete this.Da[this.eb(a)];
  }, this), delete this.ca[a], !0) : !1;
};
b.tg = function(a) {
  delete this.Da[this.eb(a)];
  var c = this.ca[a.sessionId];
  c && (a = c.media.indexOf(a), -1 != a && c.media.splice(a, 1));
};
b.bf = function(a) {
  if (a.sessionId == chrome.cast.j.Hb) {
    return a;
  }
  var c = this.ca[a.sessionId];
  if (c) {
    return c.statusText = a.statusText, c.namespaces = a.namespaces || [], c.receiver.volume = a.receiver.volume, c;
  }
  var c = new chrome.cast.j(a.sessionId, a.appId, a.displayName, a.appImages, a.receiver), d;
  for (d in a) {
    "media" == d ? c.media = a.media.map(function(a) {
      a = this.vc(a);
      a.mb = !1;
      a.ya = !0;
      return a;
    }.bind(this)) : a.hasOwnProperty(d) && (c[d] = a[d]);
  }
  return this.ca[a.sessionId] = c;
};
b.vc = function(a) {
  var c = this.eb(a), d = this.Da[c];
  d || (d = new chrome.cast.media.f(a.sessionId, a.mediaSessionId), this.Da[c] = d, (c = this.ca[a.sessionId]) && c.media.push(d));
  l.H.Pg(d, a);
  return d;
};
b.eb = function(a) {
  return a.sessionId + "#" + a.mediaSessionId;
};
l.H.Pg = function(a, c) {
  a.currentItemId = null;
  a.loadingItemId = null;
  a.preloadedItemId = null;
  for (var d in c) {
    "items" != d && c.hasOwnProperty(d) && ("volume" == d ? (a.volume.level = c.volume.level, a.volume.muted = c.volume.muted) : a[d] = c[d]);
  }
  "currentTime" in c && (a.lb = g.now());
  l.H.uf(a.playerState, a.loadingItemId) ? (a.currentItemId = null, a.loadingItemId = null, a.preloadedItemId = null, a.items = null) : l.H.Og(a, c);
};
l.H.uf = function(a, c) {
  return a == chrome.cast.media.ea.IDLE && null == c;
};
l.H.Ue = function(a) {
  var c = {};
  if (a) {
    for (var d = 0;d < a.length;d++) {
      c[a[d].itemId] = d;
    }
  }
  return c;
};
l.H.Og = function(a, c) {
  if (c.hasOwnProperty("items") && c.items) {
    for (var d = [], e = l.H.Ue(a.items), f = c.items, h = 0;h < f.length;h++) {
      var k = f[h];
      if (!k.media) {
        var m = k.itemId, n = a.items ? a.items[e[m]] : null;
        n && n.media ? k.media = n.media : m == a.currentItemId && a.media && (k.media = a.media);
      }
      d.push(l.H.df(k));
    }
    a.items = d;
  }
};
l.H.df = function(a) {
  var c = new chrome.cast.media.$b(a.media), d;
  for (d in a) {
    a.hasOwnProperty(d) && (c[d] = a[d]);
  }
  return c;
};
chrome.cast.A = function(a) {
  this.Of = 1E3 * Math.floor(1E5 * Math.random());
  this.Ea = a;
  this.ba = {};
  this.ra = !1;
  this.aa = this.B = this.Xa = null;
  this.sa = new l.oa;
  this.Ca = new l.oa;
  this.ka = new l.oa;
  this.Fa = [];
  this.Ga = new l.H(this.ij);
  this.zc = !1;
};
b = chrome.cast.A.prototype;
b.init = function() {
  this.Ea.Bg(this.cg.bind(this));
};
b.sf = function() {
  return "a" + this.Of++;
};
b.Lf = function(a) {
  var c = a.seqNum;
  if (!c) {
    return !1;
  }
  var d = this.ba[c];
  if (d) {
    var e = a.message;
    a.type == l.l.ERROR ? d.Zc()(a.message) : d.$c()(e);
    delete this.ba[c];
  }
  return !!d;
};
b.Mf = function(a) {
  switch(a.type) {
    case l.l.Tb:
    ;
    case l.l.ic:
      a.message = this.cf(a.message);
      break;
    case l.l.qa:
      a = a.message, a.type == l.m.Na && a.status && (a.status = a.status.map(this.af.bind(this)));
  }
};
b.cf = function(a) {
  return this.Ga.bf(a);
};
b.cg = function(a) {
  this.Mf(a);
  if (!this.Lf(a)) {
    switch(a.type) {
      case l.l.Ob:
        this.Pf(a);
        break;
      case l.l.fe:
        this.Yf(a);
        break;
      case l.l.ee:
        this.Xf(a);
        break;
      case l.l.yd:
        this.zc = !0;
        break;
      case l.l.Tb:
        this.Wf(a);
        break;
      case l.l.ic:
        this.$f(a);
        break;
      case l.l.qd:
        this.Rf(a);
        break;
      case l.l.ge:
        this.Zf(a);
        break;
      case l.l.Eb:
        this.Tf(a.message);
        break;
      case l.l.qa:
        this.Vf(a);
        break;
      case l.l.ld:
        this.Qf(a);
    }
  }
};
b.Qf = function(a) {
  var c = a.message;
  this.B && this.B.customDialLaunchCallback && this.B.customDialLaunchCallback(c).then(g.bind(function(c) {
    this.Ea.wb(new l.F(l.l.Gb, c, a.seqNum));
  }, this), g.bind(function() {
    this.Ea.wb(new l.F(l.l.Gb, null, a.seqNum));
  }, this));
};
b.Vf = function(a) {
  switch(a.message.type) {
    case l.m.Na:
      this.Uf(a.message);
  }
};
b.Uf = function(a) {
  a.status.forEach(this.oc.bind(this));
};
b.Wf = function(a) {
  this.B && this.B.sessionListener(a.message);
};
b.$f = function(a) {
  (a = a.message) && this.ka.get(a.sessionId).forEach(function(a) {
    a(!0);
  });
};
b.Rf = function(a) {
  this.Qc(a.message, chrome.cast.ga.DISCONNECTED);
};
b.Zf = function(a) {
  this.Qc(a.message, chrome.cast.ga.STOPPED);
};
b.Qc = function(a, c) {
  var d = c != chrome.cast.ga.STOPPED;
  this.Ga.wg(a, c) && (this.sa.sg(a + "#"), this.Ca.Pc(a), this.ka.get(a).forEach(function(a) {
    a(d);
  }), this.ka.Pc(a));
};
b.Tf = function(a) {
  this.pf(a.sessionId, a.namespaceName).forEach(function(c) {
    c(a.namespaceName, a.message);
  });
};
b.Yf = function(a) {
  if (this.B) {
    var c = a.message;
    a.receiverList ? this.B.receiverListener.apply(null, [c, a.receiverList]) : this.B.receiverListener(c);
  }
};
b.Xf = function(a) {
  this.Fa.forEach(function(c) {
    c(a.message.receiver, a.message.receiverAction);
  }, this);
};
b.Pf = function(a) {
  (a = a.message) ? (this.Xa = a, this.aa && this.aa.Zc()(a)) : (this.ra = !0, this.Sc(), this.aa && this.aa.$c()(void 0));
};
b.xb = function(a, c, d) {
  this.N(d) && (a = a || [], l.s.Xe(a) ? this.M(new l.F(l.l.pe, a), new l.w(c, d)) : d && d(new chrome.cast.Error(chrome.cast.K.INVALID_PARAMETER)));
};
chrome.cast.A.prototype.setReceiverVolume = function(a, c, d, e) {
  this.N(e) && (l.s.Ze(c) ? (c.sessionId = a, this.M(new l.F(l.l.qa, c, null, null, chrome.cast.timeout.setReceiverVolume), new l.w(d, e, chrome.cast.timeout.setReceiverVolume))) : e && e(new chrome.cast.Error(chrome.cast.K.INVALID_PARAMETER)));
};
chrome.cast.A.prototype.leaveSession = function(a, c, d) {
  this.N(d) && this.M(new l.F(l.l.Fd, a, null, null, chrome.cast.timeout.leaveSession), new l.w(c, d, chrome.cast.timeout.leaveSession));
};
b = chrome.cast.A.prototype;
b.Rc = function(a, c, d, e) {
  this.N(d) && this.M(new l.F(l.l.qa, a, null, null, e), new l.w(c, d, e));
};
b.Y = function(a) {
  this.N(g.Mc) && this.M(new l.F(l.l.Hd, a));
};
b.Uc = function(a, c, d, e, f) {
  null != a && (c.mediaSessionId = a.mediaSessionId, c.sessionId = a.sessionId);
  c.requestId = null;
  this.Rc(c, function(a) {
    d && a.status && 1 == a.status.length ? d(a.status[0]) : e && e(new chrome.cast.Error(chrome.cast.K.SESSION_ERROR));
  }, e, f);
};
b.Tc = function(a, c, d) {
  this.Uc(null, a, function(a) {
    a.ya = !0;
    a.mb = !0;
    c && c(a);
  }.bind(this), d, chrome.cast.media.timeout.load);
};
b.v = function(a, c, d, e, f) {
  this.Uc(a, c, function(a) {
    this.oc(a);
    d && d();
  }.bind(this), e, f);
};
b.Ag = function(a, c, d) {
  this.N(d) && (l.s.We(a) ? this.M(new l.F(l.l.Eb, a, null, null, chrome.cast.timeout.sendCustomMessage), new l.w(c, d, chrome.cast.timeout.sendCustomMessage)) : d && d(new chrome.cast.Error(chrome.cast.K.INVALID_PARAMETER)));
};
b.Sc = function() {
  this.B && this.ra && this.M(new l.F(l.l.hd, new l.ed(this.B)));
};
b.M = function(a, c) {
  var d = this.sf();
  a.seqNum = d;
  if (this.ba[d] && !this.ba[d].Ef()) {
    throw "Try to send a request with the existing seqNum: " + a.seqNum;
  }
  c && (this.ba[d] = c, c.Wc(function() {
    delete this.ba[d];
  }.bind(this)));
  this.Ea.wb(a);
};
b.kb = function(a, c) {
  this.N(c) && this.M(new l.F(l.l.ae, void 0), new l.w(a, c));
};
b.ib = function(a, c, d) {
  l.s.Ve(a) ? this.Xa ? d && d(this.Xa) : this.B ? c && c() : (this.B = a, this.ra ? (this.Sc(), c && c()) : (this.aa = new l.w(c, d, 6E3), this.aa.Wc())) : d && d(new chrome.cast.Error(chrome.cast.K.INVALID_PARAMETER));
};
chrome.cast.A.prototype.requestSession = function(a, c, d, e) {
  this.N(c) && (d && !l.s.rc(d) ? c && c(new chrome.cast.Error(chrome.cast.K.INVALID_PARAMETER)) : (!d && this.B && (d = this.B.sessionRequest), this.M(new l.F(l.l.he, d, null, null, d.requestSessionTimeout, e), new l.w(a, c, 0))));
};
chrome.cast.A.prototype.ub = function(a) {
  this.N(g.Mc) && a && this.M(new l.F(l.l.ie, a));
};
chrome.cast.A.cd = new chrome.cast.Error(chrome.cast.K.API_NOT_INITIALIZED);
chrome.cast.A.wd = new chrome.cast.Error(chrome.cast.K.EXTENSION_MISSING);
b = chrome.cast.A.prototype;
b.N = function(a) {
  return this.ra ? this.zc ? (a && a(chrome.cast.A.wd), !1) : !0 : (a && a(chrome.cast.A.cd), !1);
};
b.cb = function(a, c) {
  return a + "#" + c;
};
b.Le = function(a, c, d) {
  this.sa.add(this.cb(a, c), d);
};
b.rg = function(a, c, d) {
  this.sa.remove(this.cb(a, c), d);
};
b.pf = function(a, c) {
  return this.sa.get(this.cb(a, c));
};
b.Ua = function(a, c) {
  this.Ca.add(a, c);
};
b.rb = function(a, c) {
  this.Ca.remove(a, c);
};
b.Me = function(a, c) {
  -1 == a.ma.indexOf(c) && a.ma.push(c);
};
b.ug = function(a, c) {
  var d = a.ma.indexOf(c);
  -1 != d && a.ma.splice(d, 1);
};
chrome.cast.A.Bf = function(a) {
  return a.playerState != chrome.cast.media.ea.IDLE || null != a.loadingItemId;
};
b = chrome.cast.A.prototype;
b.oc = function(a) {
  if (a.ya) {
    var c = chrome.cast.A.Bf(a);
    a.ma.forEach(function(a) {
      a(c);
    });
    c || this.Ga.tg(a);
  } else {
    a.ya = !0, a.mb || this.Ca.get(a.sessionId).forEach(function(c) {
      c(a);
    });
  }
};
b.af = function(a) {
  return this.Ga.vc(a);
};
b.Oe = function(a, c) {
  this.ka.add(a, c);
};
b.xg = function(a, c) {
  this.ka.remove(a, c);
};
b.Va = function(a) {
  this.Fa.push(a);
};
b.sb = function(a) {
  a = this.Fa.indexOf(a);
  0 <= a && this.Fa.splice(a, 1);
};
b.yb = function(a, c, d) {
  this.N(d) && this.M(new l.F(l.l.qe, a), new l.w(c, d));
};
chrome.cast.isAvailable = !1;
g.c("chrome.cast.isAvailable", chrome.cast.isAvailable);
chrome.cast.i = null;
chrome.cast.kb = function(a, c) {
  chrome.cast.i.kb(a, c);
};
g.c("chrome.cast.isTabBroadcast", chrome.cast.kb);
chrome.cast.ib = function(a, c, d) {
  chrome.cast.i.ib(a, c, d);
};
g.c("chrome.cast.initialize", chrome.cast.ib);
chrome.cast.requestSession = function(a, c, d, e) {
  chrome.cast.i.requestSession(a, c, d, e);
};
g.c("chrome.cast.requestSession", chrome.cast.requestSession);
chrome.cast.ub = function(a) {
  chrome.cast.i.ub(a);
};
g.c("chrome.cast.requestSessionById", chrome.cast.ub);
chrome.cast.Va = function(a) {
  chrome.cast.i.Va(a);
};
g.c("chrome.cast.addReceiverActionListener", chrome.cast.Va);
chrome.cast.sb = function(a) {
  chrome.cast.i.sb(a);
};
g.c("chrome.cast.removeReceiverActionListener", chrome.cast.sb);
chrome.cast.Y = function(a) {
  chrome.cast.i.Y(a);
};
g.c("chrome.cast.logMessage", chrome.cast.Y);
chrome.cast.xb = function(a, c, d) {
  chrome.cast.i.xb(a, c, d);
};
g.c("chrome.cast.setCustomReceivers", chrome.cast.xb);
chrome.cast.yb = function(a, c, d) {
  chrome.cast.i.yb(a, c, d);
};
g.c("chrome.cast.setReceiverDisplayStatus", chrome.cast.yb);
chrome.cast.unescape = function(a) {
  return g.a.Cb(a);
};
g.c("chrome.cast.unescape", chrome.cast.unescape);
chrome.cast.j.prototype.Dg = function(a, c, d) {
  a = new l.gc(new chrome.cast.Volume(a, null), this.receiver.volume);
  chrome.cast.i.setReceiverVolume(this.sessionId, a, c, d);
};
g.h(chrome.cast.j.prototype, "setReceiverVolumeLevel", chrome.cast.j.prototype.Dg);
chrome.cast.j.prototype.Cg = function(a, c, d) {
  a = new l.gc(new chrome.cast.Volume(null, a), this.receiver.volume);
  chrome.cast.i.setReceiverVolume(this.sessionId, a, c, d);
};
g.h(chrome.cast.j.prototype, "setReceiverMuted", chrome.cast.j.prototype.Cg);
chrome.cast.j.prototype.leave = function(a, c) {
  chrome.cast.i.leaveSession(this.sessionId, a, c);
};
g.h(chrome.cast.j.prototype, "leave", chrome.cast.j.prototype.leave);
chrome.cast.j.prototype.stop = function(a, c) {
  chrome.cast.i.Rc(new l.we(this.sessionId), a, c, chrome.cast.timeout.stopSession);
};
g.h(chrome.cast.j.prototype, "stop", chrome.cast.j.prototype.stop);
chrome.cast.j.prototype.sendMessage = function(a, c, d, e) {
  chrome.cast.i.Ag(new l.fd(this.sessionId, a, c), d, e);
};
g.h(chrome.cast.j.prototype, "sendMessage", chrome.cast.j.prototype.sendMessage);
chrome.cast.j.prototype.Wa = function(a) {
  chrome.cast.i.Oe(this.sessionId, a);
};
g.h(chrome.cast.j.prototype, "addUpdateListener", chrome.cast.j.prototype.Wa);
chrome.cast.j.prototype.tb = function(a) {
  chrome.cast.i.xg(this.sessionId, a);
};
g.h(chrome.cast.j.prototype, "removeUpdateListener", chrome.cast.j.prototype.tb);
chrome.cast.j.prototype.Ne = function(a, c) {
  chrome.cast.i.Le(this.sessionId, a, c);
};
g.h(chrome.cast.j.prototype, "addMessageListener", chrome.cast.j.prototype.Ne);
chrome.cast.j.prototype.vg = function(a, c) {
  chrome.cast.i.rg(this.sessionId, a, c);
};
g.h(chrome.cast.j.prototype, "removeMessageListener", chrome.cast.j.prototype.vg);
chrome.cast.j.prototype.Ua = function(a) {
  chrome.cast.i.Ua(this.sessionId, a);
};
g.h(chrome.cast.j.prototype, "addMediaListener", chrome.cast.j.prototype.Ua);
chrome.cast.j.prototype.rb = function(a) {
  chrome.cast.i.rb(this.sessionId, a);
};
g.h(chrome.cast.j.prototype, "removeMediaListener", chrome.cast.j.prototype.rb);
chrome.cast.j.prototype.Gf = function(a, c, d) {
  a.sessionId = this.sessionId;
  chrome.cast.i.Tc(a, c, d);
};
g.h(chrome.cast.j.prototype, "loadMedia", chrome.cast.j.prototype.Gf);
chrome.cast.media.f.prototype.xa = function(a, c, d) {
  a || (a = new chrome.cast.media.Nb);
  chrome.cast.i.v(this, a, c, d, chrome.cast.media.timeout.xa);
};
g.h(chrome.cast.media.f.prototype, "getStatus", chrome.cast.media.f.prototype.xa);
chrome.cast.media.f.prototype.play = function(a, c, d) {
  a || (a = new chrome.cast.media.Yb);
  chrome.cast.i.v(this, a, c, d, chrome.cast.media.timeout.play);
};
g.h(chrome.cast.media.f.prototype, "play", chrome.cast.media.f.prototype.play);
chrome.cast.media.f.prototype.pause = function(a, c, d) {
  a || (a = new chrome.cast.media.Xb);
  chrome.cast.i.v(this, a, c, d, chrome.cast.media.timeout.pause);
};
g.h(chrome.cast.media.f.prototype, "pause", chrome.cast.media.f.prototype.pause);
chrome.cast.media.f.prototype.seek = function(a, c, d) {
  chrome.cast.i.v(this, a, c, d, chrome.cast.media.timeout.seek);
};
g.h(chrome.cast.media.f.prototype, "seek", chrome.cast.media.f.prototype.seek);
chrome.cast.media.f.prototype.stop = function(a, c, d) {
  a || (a = new chrome.cast.media.hc);
  chrome.cast.i.v(this, a, c, d, chrome.cast.media.timeout.stop);
};
g.h(chrome.cast.media.f.prototype, "stop", chrome.cast.media.f.prototype.stop);
chrome.cast.media.f.prototype.Ha = function(a, c, d) {
  chrome.cast.i.v(this, a, c, d, chrome.cast.media.timeout.Ha);
};
g.h(chrome.cast.media.f.prototype, "setVolume", chrome.cast.media.f.prototype.Ha);
chrome.cast.media.f.prototype.ua = function(a, c, d) {
  chrome.cast.i.v(this, a, c, d, chrome.cast.media.timeout.ua);
};
g.h(chrome.cast.media.f.prototype, "editTracksInfo", chrome.cast.media.f.prototype.ua);
chrome.cast.j.prototype.ig = function(a, c, d) {
  a.sessionId = this.sessionId;
  chrome.cast.i.Tc(a, c, d);
};
g.h(chrome.cast.j.prototype, "queueLoad", chrome.cast.j.prototype.ig);
chrome.cast.media.f.prototype.fg = function(a, c, d) {
  chrome.cast.i.v(this, a, c, d, chrome.cast.media.timeout.D);
};
g.h(chrome.cast.media.f.prototype, "queueInsertItems", chrome.cast.media.f.prototype.fg);
chrome.cast.media.f.prototype.dg = function(a, c, d) {
  chrome.cast.i.v(this, new chrome.cast.media.Qa([a]), c, d, chrome.cast.media.timeout.D);
};
g.h(chrome.cast.media.f.prototype, "queueAppendItem", chrome.cast.media.f.prototype.dg);
chrome.cast.media.f.prototype.eg = function(a, c, d) {
  var e = new chrome.cast.media.Qa([a.item]);
  e.currentItemIndex = 0;
  e.insertBefore = a.insertBefore;
  e.currentTime = a.currentTime;
  e.customData = a.customData;
  chrome.cast.i.v(this, e, c, d, chrome.cast.media.timeout.D);
};
g.h(chrome.cast.media.f.prototype, "queueInsertAndPlayItem", chrome.cast.media.f.prototype.eg);
chrome.cast.media.f.prototype.qg = function(a, c, d) {
  chrome.cast.i.v(this, a, c, d, chrome.cast.media.timeout.D);
};
g.h(chrome.cast.media.f.prototype, "queueUpdateItems", chrome.cast.media.f.prototype.qg);
chrome.cast.media.f.prototype.lg = function(a, c) {
  var d = new chrome.cast.media.fa;
  d.jump = -1;
  chrome.cast.i.v(this, d, a, c, chrome.cast.media.timeout.D);
};
g.h(chrome.cast.media.f.prototype, "queuePrev", chrome.cast.media.f.prototype.lg);
chrome.cast.media.f.prototype.kg = function(a, c) {
  var d = new chrome.cast.media.fa;
  d.jump = 1;
  chrome.cast.i.v(this, d, a, c, chrome.cast.media.timeout.D);
};
g.h(chrome.cast.media.f.prototype, "queueNext", chrome.cast.media.f.prototype.kg);
chrome.cast.media.f.prototype.gg = function(a, c, d) {
  if (0 > this.wa(a)) {
    chrome.cast.Y("itemId not found in the queue.");
  } else {
    var e = new chrome.cast.media.fa;
    e.currentItemId = a;
    chrome.cast.i.v(this, e, c, d, chrome.cast.media.timeout.D);
  }
};
g.h(chrome.cast.media.f.prototype, "queueJumpToItem", chrome.cast.media.f.prototype.gg);
chrome.cast.media.f.prototype.hg = function(a, c, d, e) {
  if (0 > this.wa(a)) {
    chrome.cast.Y("itemId not found in the queue.");
  } else {
    var f = new chrome.cast.media.fa;
    f.currentItemId = a;
    f.currentTime = c;
    chrome.cast.i.v(this, f, d, e, chrome.cast.media.timeout.D);
  }
};
g.h(chrome.cast.media.f.prototype, "queueJumpToItemWithCurrentTime", chrome.cast.media.f.prototype.hg);
chrome.cast.media.f.prototype.pg = function(a, c, d) {
  var e = new chrome.cast.media.cc;
  e.repeatMode = a;
  chrome.cast.i.v(this, e, c, d, chrome.cast.media.timeout.D);
};
g.h(chrome.cast.media.f.prototype, "queueSetRepeatMode", chrome.cast.media.f.prototype.pg);
chrome.cast.media.f.prototype.ng = function(a, c, d) {
  chrome.cast.i.v(this, a, c, d, chrome.cast.media.timeout.D);
};
g.h(chrome.cast.media.f.prototype, "queueRemoveItems", chrome.cast.media.f.prototype.ng);
chrome.cast.media.f.prototype.mg = function(a, c, d) {
  0 > this.wa(a) ? chrome.cast.Y("itemId not found in the queue.") : chrome.cast.i.v(this, new chrome.cast.media.ac([a]), c, d, chrome.cast.media.timeout.D);
};
g.h(chrome.cast.media.f.prototype, "queueRemoveItem", chrome.cast.media.f.prototype.mg);
chrome.cast.media.f.prototype.og = function(a, c, d) {
  chrome.cast.i.v(this, a, c, d, chrome.cast.media.timeout.D);
};
g.h(chrome.cast.media.f.prototype, "queueReorderItems", chrome.cast.media.f.prototype.og);
chrome.cast.media.f.prototype.jg = function(a, c, d, e) {
  var f = this.wa(a);
  if (0 > f) {
    chrome.cast.Y("itemId not found in the queue.");
  } else {
    if (0 > c) {
      e && e(new chrome.cast.Error(chrome.cast.K.INVALID_PARAMETER));
    } else {
      if (f == c) {
        d && d();
      } else {
        var h = null;
        c = c > f ? c + 1 : c;
        c < this.items.length && (h = this.items[c]);
        a = new chrome.cast.media.bc([a]);
        a.insertBefore = h ? h.itemId : null;
        chrome.cast.i.v(this, a, d, e, chrome.cast.media.timeout.D);
      }
    }
  }
};
g.h(chrome.cast.media.f.prototype, "queueMoveItemToNewIndex", chrome.cast.media.f.prototype.jg);
chrome.cast.media.f.prototype.Hg = function(a) {
  return -1 < this.supportedMediaCommands.indexOf(a);
};
g.h(chrome.cast.media.f.prototype, "supportsCommand", chrome.cast.media.f.prototype.Hg);
chrome.cast.media.f.prototype.qf = function() {
  if (this.playerState == chrome.cast.media.ea.PLAYING && 0 <= this.lb) {
    var a = (g.now() - this.lb) / 1E3, a = this.currentTime + this.playbackRate * a;
    this.media && null != this.media.duration && a > this.media.duration && (a = this.media.duration);
    0 > a && (a = 0);
    return a;
  }
  return this.currentTime;
};
g.h(chrome.cast.media.f.prototype, "getEstimatedTime", chrome.cast.media.f.prototype.qf);
chrome.cast.media.f.prototype.Wa = function(a) {
  chrome.cast.i.Me(this, a);
};
g.h(chrome.cast.media.f.prototype, "addUpdateListener", chrome.cast.media.f.prototype.Wa);
chrome.cast.media.f.prototype.tb = function(a) {
  chrome.cast.i.ug(this, a);
};
g.h(chrome.cast.media.f.prototype, "removeUpdateListener", chrome.cast.media.f.prototype.tb);
chrome.cast.media.f.prototype.wa = function(a) {
  for (var c = 0;c < this.items.length;c++) {
    if (this.items[c].itemId == a) {
      return c;
    }
  }
  return -1;
};
chrome.cast.zb = function() {
  if (!chrome.cast.Vc && (chrome.cast.Vc = !0, chrome.cast.extensionId)) {
    var a = "chrome-extension://" + chrome.cast.extensionId, c = document.createElement("iframe");
    c.src = a + "/api_iframe.html?appOrigin=" + window.location.origin;
    c.setAttribute("style", "display:none");
    document.body.appendChild(c);
    a = new chrome.cast.da(c, a);
    a.init();
    chrome.cast.i = new chrome.cast.A(a);
    chrome.cast.i.init();
    chrome.cast.isAvailable = !0;
    (a = window.__onGCastApiAvailable) && "function" == typeof a && a(!0);
  }
};
chrome.cast.Vc = !1;
"complete" == document.readyState ? chrome.cast.zb() : (window.addEventListener("load", chrome.cast.zb, !1), window.addEventListener("DOMContentLoaded", chrome.cast.zb, !1));
})();
