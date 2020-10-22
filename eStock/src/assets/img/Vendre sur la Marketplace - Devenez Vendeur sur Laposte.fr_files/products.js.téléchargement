/*
 * Common .js for all weborama products
 *
 */
// The 1st thing to do is to find the CMP frame (if this code is executed in an iframe,
// it will have to emulate the __cmp function)
var f = window;
var cmpFrame;
var level_up=0;

while(!cmpFrame) {
  try {
    if(f.frames["__cmpLocator"]) cmpFrame = f;
  } catch(e) {}
  if(f === window.top) break;
  level_up++;
  f = f.parent;
}

/* Set up a __cmp function to do the postMessage and
   stash the callback.
   This function behaves (from the caller's perspective)
   identically to the in-frame __cmp call */

if (level_up && cmpFrame) {
	var cmpCallbacks = {}
	window.__cmp = function(cmd, arg, callback) {
		var callId = Math.random() + "";
		var msg = {
			__cmpCall: {
				command: cmd,
				parameter: arg,
				callId: callId
			}
		};

		cmpCallbacks[callId] = callback;
		cmpFrame.postMessage(msg, '*');
	}

	/* When we get the returned message, call the stashed callback */

	window.addEventListener("message", function(event) {
		var json;
		try{
			json = typeof event.data === "string"? JSON.parse(event.data): event.data;
		}catch(ignore){}
		if(json && json.__cmpReturn) {
			var i = json.__cmpReturn;
			cmpCallbacks[i.callId](i.returnValue, i.success);
			delete cmpCallbacks[i.callId];
		}
	}, false);
}
/* gdpr protection */
function _send_after_cmp_check(type,url) {
	if (typeof window.__cmp == 'function'){
		window.__cmp('getConsentData', null, function (response, success) {
			url+= ((url.indexOf("?") == -1) ? "?" : "&");
			if(!success){
				url += "gdpr=1&gdpr_cmp_failure=1";
			} else if(response['gdprApplies']) {
				url += "gdpr=1&gdpr_consent="+(response['consentData']|| "");
			} else {
				url += "gdpr=0"
			}
			_create_element_of_given_type(type,url)
		});
	} else {
		// No CMP found, just process the call as usual
		_create_element_of_given_type(type,url)
	}
}
function _create_element_of_given_type(type,url){
	if (type == 'image') {
		_create_image_for(url);
	} else {
		_create_iframe_for(url)
	}
}
function _create_image_for(url) {
	var b = new Image();
	b.src = url;
	b.onload = function() { return;};
}
function _create_iframe_for(ifrurl){
	try {
		var wbo_ifrm = document.createElement('IFRAME'), bodyRef = document.getElementsByTagName('body').item(0) || document.documentElement.childNodes[0];
		wbo_ifrm.style.width = wbo_ifrm.style.height = '1px';
		wbo_ifrm.style.border = '0px';
		wbo_ifrm.style.position = 'absolute';
		wbo_ifrm.style.display = 'none';
		wbo_ifrm.style.top = wbo_ifrm.style.left = '0px';
		wbo_ifrm.style.zIndex = 0;
		wbo_ifrm.src = ifrurl;
		if (bodyRef.firstChild != null) {
			bodyRef.insertBefore(wbo_ifrm, bodyRef.firstChild);
		} else {
			bodyRef.appendChild(wbo_ifrm);
		}
	} catch (e) {
		document.write("<iframe src='" + ifrurl + "' width=1 height=1 frameborder=0 style='border:0px'><\/iframe>");
	}
}
/* ======================== common ======================== */
function _ap_defined(name) {
	return (typeof (window[name]) == "undefined") ? false : true;
}
function wis_defined(name) {
	return (typeof (name) == 'undefined' || name == null) ? false : true;
}

/* cookies */
function getCookieVal(offset) {
	var endstr = document.cookie.indexOf(";", offset);
	if (endstr == -1)
		endstr = document.cookie.length;
	return unescape(document.cookie.substring(offset, endstr));
}

function GetCookie(name) {
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while (i < clen) {
		var j = i + alen;
		if (document.cookie.substring(i, j) == arg)
			return getCookieVal(j);
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0) break;
	}
	return null;
}

function SetCookie(name, value, expires, path, domain, secure, sameSite) {
	document.cookie = name + "=" + escape(value) +
		((expires) ? "; expires=" + expires.toGMTString() : "") +
		((path) ? "; path=" + path : "") +
		((domain) ? "; domain=" + domain : "") +
		((secure) ? "; secure" : "") +
		((sameSite)? "; SameSite=" + sameSite : "");
}

/* string */
function encode_en_lettre(num) {
	num = parseInt(num, 10);
	if (num > 2500) return '';
	var num1 = parseInt(num / 52, 10);
	var num2 = num % 52;

	num1 += 65;
	if (num1 > 90) num1 += 6;

	num2 += 65;
	if (num2 > 90) num2 += 6;

	var mon_code52 = String.fromCharCode(num1) + String.fromCharCode(num2);
	return mon_code52;
}

function traite_chaine(str, taille_max) {
	if (str == null) return null;
	var s = traduction(str);
	var bag = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-.,;:_ ";
	var i;
	var returnString = "";
	if (s == null) return "";
	s = "" + s;
	s = s.substr(0, taille_max);
	for (i = 0; i < s.length; i++) {
		var c = s.charAt(i);
		if (bag.indexOf(c) != -1) returnString += c;
	}
	returnString = unicite_espace(returnString);
	returnString = escape(returnString);
	return returnString;
}

function convertir(chaine) {
	var str = chaine.replace(/&#(\d+);/, "$1");
	return String.fromCharCode(str);
}

function traduction(chaine) {
	var chaine_b = unescape(chaine);
	var i = 0;
	while (chaine_b != chaine && i < 5) {
		i++;
		chaine = chaine_b;
		chaine_b = unescape(chaine_b);
	}
	return chaine_b.replace(/&#(\d+);/gi, convertir);
}

function unicite_espace(chaine) {
	var str = chaine.replace(/(\s+)/g, ' ');
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

// Identification du type de navigateur
function wf_uaO(c) {
	var wf_ual = navigator.userAgent.toLowerCase();
	return (wf_ual.indexOf(c))
}


/* ======================== analytics ======================== */
_NB_MAX_CONTENU_ = 1;
_TAILLE_MAX_CONTENU_ = 100;
_TAILLE_MAX_CHAINE_ = 50;
_TAILLE_MAX_ALPHANUM_ = 30;
_COOKIE_SEGMENTATION = 'wbo_segment_';
var _NB_MAX_EXTEND_PARAMETERS = 5;
var _TAILLE_MAX_EXTEND_PARAMETER_ = 100;

var _ap_an = {};
_ap_an.wrp_host = (_ap_defined('WRP_HOST')) ? WRP_HOST : 'pro.weborama.fr';
_ap_an.wrp_host_ssl = (_ap_defined('WRP_HOST_SSL')) ? WRP_HOST_SSL : _ap_an.wrp_host;
_ap_an.site_grp = (_ap_defined('WRP_ID_GRP')) ? WRP_ID_GRP : null;
_ap_an.site = (_ap_defined('WRP_ID')) ? WRP_ID : null;
_ap_an.section = (_ap_defined('WRP_SECTION')) ? WRP_SECTION : null;
_ap_an.subsection = (_ap_defined('WRP_SUBSECTION')) ? WRP_SUBSECTION : null;
_ap_an.section_grp = (_ap_defined('WRP_SECTION_GRP')) ? WRP_SECTION_GRP : null;
_ap_an.subsection_grp = (_ap_defined('WRP_SUBSECTION_GRP')) ? WRP_SUBSECTION_GRP : null;
_ap_an.frame = (_ap_defined('WRP_ACC')) ? WRP_ACC : null;
_ap_an.channel = (_ap_defined('WRP_CHANNEL')) ? WRP_CHANNEL : null;
_ap_an.content = (_ap_defined('WRP_CONTENT')) ? WRP_CONTENT : null;
_ap_an.wrp_profiling_mode = (_ap_defined('WRP_PROFILING_COOKIE_MODE')) ? WRP_PROFILING_COOKIE_MODE : 1;

_ap_an.wreport_counter = function () {
	if (this.site == null || this.section == null || this.subsection == null) return 1;

	this.nb_content = 0;
	this.url = '';
	this.segmentation = new Array(5);
	this.profiles = '';

	this.cookie_segmentation = _COOKIE_SEGMENTATION + this.site;
	this.domaine_segmentation = '';

	this.host_ssl = this.wrp_host_ssl + '/fcgi-bin/comptage_wreport.fcgi';

	this.extendparameters = '';
	this.extend_parameters = new Array(_NB_MAX_EXTEND_PARAMETERS);

	/* Initialisation */
	this.section = traite_chaine(this.section, _TAILLE_MAX_ALPHANUM_);
	this.subsection = traite_chaine(this.subsection, _TAILLE_MAX_ALPHANUM_);
	this.chaine = traite_chaine(this.chaine, _TAILLE_MAX_CHAINE_)
	this.section_grp = traite_chaine(this.section_grp, _TAILLE_MAX_ALPHANUM_);
	this.subsection_grp = traite_chaine(this.subsection_grp, _TAILLE_MAX_ALPHANUM_);
	this.channel = traite_chaine(this.channel, _TAILLE_MAX_CHAINE_);

	/* groupe */
	if (this.site_grp == null || this.section_grp == null || this.subsection_grp == null) {
		this.site_grp = null;
		this.section_grp = null;
		this.subsection_grp = null;
	}


	this.generate_url();
	_send_after_cmp_check('image', this.url)

	return 0;
}

// Evolution #38044: Activate bigsea call for a list of FR crawlable accounts
_ap_an.bigsea_rd = function () {
        var mod_date = Date.now() % 100;
        _send_after_cmp_check('image','https://dx.frontend.weborama.com/collect?touchpoint=0&url=' + escape(document.location));
         if (mod_date == 0) {
            _send_after_cmp_check('image','https://dx.frontend-preprod.weborama.com/collect?touchpoint=0&url=' + escape(document.location));
         }

	return 0;
}

// Génére l'url de comptage
_ap_an.generate_url = function () {
	var _date_ = new Date();
	this.date = parseInt(_date_.getTime() / 1000 - 60 * _date_.getTimezoneOffset());
	this.ref = '' + escape(document.referrer);

	this.ta = '0x0';
	this.co = 0;
	this.nav = navigator.appName;

	this.get_profiles();
	this.get_extend_parameters();

	if (parseInt(navigator.appVersion) >= 4) {
		this.ta = screen.width + "x" + screen.height;
		this.co = (this.nav != "Netscape") ? screen.colorDepth : screen.pixelDepth;
	}

	if ((this.frame != null) && (this.nav != "Netscape")) {
		var reftmp = 'parent.document.referrer';
		if ((this.frame < 5) && (this.frame > 0)) { for (_k = this.frame; _k > 1; _k--) reftmp = 'parent.' + reftmp; }
		var mon_ref = eval(reftmp);
		if (document.referrer == parent.location.href || document.referrer == '') this.ref = '' + escape(mon_ref);
	}

	this.url = "https://" + this.host_ssl + "?WRP_ID=" + this.site;

	if (this.profiles != null) this.url += "&WRP_PFL=" + escape(this.profiles);
	if (this.extendparameters != null) this.url += this.extendparameters;

	var is_mac = (wf_uaO('mac') != -1);
	var is_opera = (wf_uaO('opera') != -1);
	if ((!is_mac) && (!is_opera)) {
		var msieind = navigator.userAgent.indexOf('MSIE');
		if (msieind > 0) {
			if (parseInt(navigator.userAgent.charAt(msieind + 5)) >= 5) {
				document.body.addBehavior("#default#clientCaps");
				this.cnx = (document.body.connectionType == 'modem') ? 'A' : 'B';
				document.body.addBehavior("#default#homePage");
				this.home = (document.body.isHomePage(location.href)) ? 'A' : 'B';
				this.url += "&CONN=" + this.cnx + "&ISHOME=" + this.home;
			}
		}
	}
	this.url += "&WRP_SECTION=" + this.section + "&WRP_SUBSECTION=" + this.subsection;

	if (this.site_grp != null && this.section_grp != null && this.subsection_grp != null) {
		this.url += "&WRP_ID_GRP=" + this.site_grp + "&WRP_SECTION_GRP=" + this.section_grp + "&WRP_SUBSECTION_GRP=" + this.subsection_grp;
	}

	if (this.content != null) this.url += "&WRP_CONTENT=" + this.content;
	if (this.channel != null) this.url += "&WRP_CHANNEL=" + this.channel;

	this.url += "&ver=2&da2=" + this.date + "&ta=" + this.ta + "&co=" + this.co + "&ref=" + this.ref;
}

/* ------------------ Profilling ------------------------ */

// Ajout d'un profil pour le profiling
_ap_an.add_profile = function (numero, valeur) {
	numero = parseInt(numero, 10);
	if ((numero < 1) || (numero > 5)) return -2;
	if (this.wrp_profiling_mode == 0) {
		numero--;
		this.segmentation[numero] = valeur;
		return 1;
	}
	if (parseInt(navigator.appVersion, 10) <= 3) return -1;
	nb_mois = 12;
	var verif_val_I = /^\d+$/;
	if (verif_val_I.test(valeur)) valeur = encode_en_lettre(valeur);
	var verif_val_A = /^\w*$/;
	if (verif_val_A.test(valeur)) {
		var mon_profil_wbo = GetCookie(this.cookie_segmentation);
		tab_segment = new Array('', '', '', '', '');
		if (mon_profil_wbo != null) {
			tab_segment = mon_profil_wbo.split('|');
			if (tab_segment.length != 5) tab_segment = ('', '', '', '', '');
			//On vérifie qu'une mise à jour est nécessaire.
			if (tab_segment[numero - 1] == valeur) return 1;
		}
		if (this.domaine_segmentation == '') {
			this.domaine_segmentation = window.location.host;
			tab_points = new Array();
			tab_points = window.location.host.split('.');
			if (tab_points.length > 2) this.domaine_segmentation = this.domaine_segmentation.substring(this.domaine_segmentation.indexOf('.'), this.domaine_segmentation.length);
			if (tab_points.length == 2) this.domaine_segmentation = '.' + this.domaine_segmentation;
		}
		var ma_chaine_profil = '';
		for (var i = 1; i <= 5; i++) {
			if (i == numero) tab_segment[i - 1] = valeur;
			if ((tab_segment[i - 1] == '') || (tab_segment[i - 1] == null)) tab_segment[i - 1] = '';
			ma_chaine_profil += tab_segment[i - 1];
			if (i < 5) ma_chaine_profil += '|';
		}
		expd = new Date();
		expd.setTime(expd.getTime() + (nb_mois * 30 * 24 * 3600 * 1000));
		SetCookie(this.cookie_segmentation, ma_chaine_profil, expd, '/', this.domaine_segmentation);
		return 2;
	}
	else return -3;
}

// Ajout du profiling
_ap_an.add_profiles = function (p1, p2, p3, p4, p5) {
	this.add_profile(1, p1);
	this.add_profile(2, p2);
	this.add_profile(3, p3);
	this.add_profile(4, p4);
	this.add_profile(5, p5);
}

// Destruction du cookie de profiling ou de la variable
_ap_an.delete_profiles = function () {
	if (this.wrp_profiling_mode == 0) {
		this.segmentation = Array('', '', '', '', '');
	}
	else {
		expd = new Date();
		expd.setTime(expd.getTime() - (24 * 3600 * 1000));
		SetCookie(this.cookie_segmentation, '||||', expd, '/', this.domaine_segmentation);
	}
}

// Recuperation des informations de profiling ( on renvoie une chaine escapée )
_ap_an.get_profiles = function () {
	this.profiles = GetCookie(this.cookie_segmentation);
	if (this.profiles == null) {
		this.profiles = '';
		var verif_val_I = /^\d+$/;
		var is_def = 0;
		for (var i = 0; i <= 4; i++) {
			if (verif_val_I.test(this.segmentation[i])) this.segmentation[i] = encode_en_lettre(this.segmentation[i]);
			if (wis_defined(this.segmentation[i])) { this.profiles += this.segmentation[i]; is_def = 1; }
			if (i < 4) this.profiles += '|';
		}
		if (is_def == 0) this.profiles = null;
	}
}


/* ------------------ EXTEND PARAMETERS -------------------- */

_ap_an.add_extend_parameters = function (p1, p2, p3, p4, p5) {
	this.add_extend_parameter(1, p1);
	this.add_extend_parameter(2, p2);
	this.add_extend_parameter(3, p3);
	this.add_extend_parameter(4, p4);
	this.add_extend_parameter(5, p5);
}

_ap_an.add_extend_parameter = function (numero, valeur) {
	numero = parseInt(numero, 10);
	if ((numero < 1) || (numero > _NB_MAX_EXTEND_PARAMETERS)) return -2;
	this.extend_parameters[--numero] = clean_extend_parameter(valeur);
}

_ap_an.clean_extend_parameter = function (s) {
	if (s == null) return "";
	s = "" + s;
	return s.substr(0, _TAILLE_MAX_EXTEND_PARAMETER_);
}

_ap_an.get_extend_parameters = function () {
	this.extendparameters = '';
	for (var i = 1; i <= _NB_MAX_EXTEND_PARAMETERS; i++) {
		if (wis_defined(this.extend_parameters[i - 1])) this.extendparameters += "&BI" + i + "=" + encodeURIComponent(this.extend_parameters[i - 1]);
	}
}


function create_ifrtrk() {
	var weboidsync = _ap_defined('WEBOIDSYNC') ? WEBOIDSYNC : 'all';
	_send_after_cmp_check('iframe','https://cstatic.weborama.fr/iframe/external_' + weboidsync + '.html')
}
// main
_ap_an.wreport_counter();
// Evolution #38044: Activate bigsea call for a list of FR crawlable accounts
// Evolution #38973: Push most of the WAI traffic to BigSea using a static list in the JS
var acc_list = { 261612: 1, 340158: 1, 389168: 1, 414184: 1, 427616: 1, 445081: 1, 461323: 1, 466169: 1, 468315: 1, 468316: 1, 468317: 1, 468397: 1, 469341: 1, 470182: 1, 471258: 1, 471357: 1, 471781: 1, 475494: 1, 475825: 1, 478564: 1, 478585: 1, 480284: 1, 480836: 1, 481660: 1, 482721: 1, 482722: 1, 482727: 1, 482936: 1, 482938: 1, 484297: 1, 484372: 1, 484636: 1, 484690: 1, 484805: 1, 484806: 1, 484833: 1, 484835: 1, 484837: 1, 484839: 1, 484859: 1, 484860: 1, 484862: 1, 484878: 1, 484880: 1, 484882: 1, 484888: 1, 484894: 1, 484897: 1, 484926: 1, 484930: 1, 484932: 1, 484958: 1, 484981: 1, 485012: 1, 485015: 1, 485016: 1, 485069: 1, 485071: 1, 485074: 1, 485079: 1, 485080: 1, 485085: 1, 485087: 1, 485099: 1, 485100: 1, 485102: 1, 485105: 1, 485109: 1, 485114: 1, 485115: 1, 485116: 1, 485119: 1, 485132: 1, 485133: 1, 485153: 1, 485161: 1, 485170: 1, 485174: 1, 485180: 1, 485181: 1, 485210: 1, 485211: 1, 485214: 1, 485217: 1, 485228: 1, 485229: 1, 485231: 1, 485236: 1, 485237: 1, 485241: 1, 485242: 1, 485243: 1, 485247: 1, 485248: 1, 485253: 1, 485256: 1, 485259: 1, 485262: 1, 485265: 1, 485266: 1, 485267: 1, 485268: 1, 485270: 1, 485275: 1, 485283: 1, 485285: 1, 485286: 1, 485291: 1, 485302: 1, 485303: 1, 485305: 1, 485307: 1, 485309: 1, 485310: 1, 485311: 1, 485312: 1, 485318: 1, 485330: 1, 485335: 1, 485341: 1, 485344: 1, 485345: 1, 485348: 1, 485349: 1, 485350: 1, 485351: 1, 485353: 1, 485355: 1, 485358: 1, 485361: 1, 485362: 1, 485363: 1, 485365: 1, 485368: 1, 485370: 1, 485375: 1, 485380: 1, 485382: 1, 485385: 1, 485389: 1, 485393: 1, 485398: 1, 485400: 1, 485402: 1, 485403: 1, 485404: 1, 485405: 1, 485406: 1, 485407: 1, 485408: 1, 485409: 1, 485410: 1, 485411: 1, 485412: 1, 485413: 1, 485414: 1, 485415: 1, 485416: 1, 485417: 1, 485418: 1, 485419: 1, 485420: 1, 485421: 1, 485422: 1, 485423: 1, 485424: 1, 485425: 1, 485426: 1, 485429: 1, 485431: 1, 485432: 1, 485436: 1, 485437: 1, 485443: 1, 485448: 1, 485451: 1, 485452: 1, 485453: 1, 485458: 1, 485461: 1, 485462: 1, 485467: 1, 485469: 1, 485470: 1, 485472: 1, 485473: 1, 485477: 1, 485478: 1, 485480: 1, 485482: 1, 485484: 1, 485487: 1, 485489: 1, 485493: 1, 485501: 1, 485505: 1, 485508: 1, 485511: 1, 485512: 1, 485513: 1, 485516: 1, 485519: 1, 485527: 1, 485529: 1, 485534: 1, 485535: 1, 485536: 1, 485542: 1, 485544: 1, 485545: 1, 485546: 1, 485547: 1, 485548: 1, 485549: 1, 485550: 1, 485551: 1, 485552: 1, 485664: 1, 485665: 1, 485666: 1, 485667: 1, 485668: 1, 485669: 1, 485670: 1, 485672: 1, 485673: 1, 485674: 1, 485675: 1, 485676: 1, 485677: 1, 485678: 1, 485679: 1, 485680: 1, 485681: 1, 485682: 1, 485683: 1, 485684: 1, 485686: 1, 485687: 1, 485688: 1, 485689: 1, 485690: 1, 485691: 1, 485692: 1, 485693: 1, 485694: 1, 485695: 1, 485696: 1, 485697: 1, 485698: 1, 485699: 1, 485700: 1, 485701: 1, 485702: 1, 485703: 1, 485704: 1, 485705: 1, 485706: 1, 485707: 1, 485708: 1, 485709: 1, 485710: 1, 485711: 1, 485712: 1, 485713: 1, 485714: 1, 485715: 1, 485716: 1, 485717: 1, 485718: 1, 485719: 1, 485720: 1, 485721: 1, 485722: 1, 485723: 1, 485724: 1, 485725: 1, 485726: 1, 485727: 1, 485728: 1, 485729: 1, 485730: 1, 485731: 1, 485732: 1, 485733: 1, 485734: 1, 485735: 1, 485736: 1, 485737: 1, 485738: 1, 485739: 1, 485740: 1, 485741: 1, 485742: 1, 485743: 1, 485744: 1, 485745: 1, 485746: 1, 485748: 1, 485749: 1, 485750: 1, 485751: 1, 485752: 1, 485753: 1, 485754: 1, 485755: 1, 485756: 1, 485757: 1, 485758: 1, 485759: 1, 485760: 1, 485761: 1, 485762: 1, 485763: 1, 485764: 1, 485765: 1, 485766: 1, 485768: 1, 485769: 1, 485770: 1, 485771: 1, 485772: 1, 485773: 1, 485776: 1, 485777: 1, 485778: 1, 485779: 1, 485780: 1, 485781: 1, 485782: 1, 485783: 1, 485784: 1, 485785: 1, 485786: 1, 485787: 1, 485788: 1, 485789: 1, 485790: 1, 485791: 1, 485792: 1, 485793: 1, 485796: 1, 485797: 1, 485798: 1, 485800: 1, 485801: 1, 485802: 1, 485803: 1, 485804: 1, 485805: 1, 485806: 1, 485807: 1, 485809: 1, 485810: 1, 485811: 1, 485812: 1, 485813: 1, 485814: 1, 485815: 1, 485816: 1, 485817: 1, 485818: 1, 485819: 1, 485820: 1, 485821: 1, 485823: 1, 485824: 1, 485825: 1, 485826: 1, 485827: 1, 485828: 1, 485829: 1, 485830: 1, 485831: 1, 485832: 1, 485833: 1, 485834: 1, 485835: 1, 485836: 1, 485837: 1, 485838: 1, 485839: 1, 485840: 1, 485841: 1, 485842: 1, 485843: 1, 485844: 1, 485845: 1, 485846: 1, 485847: 1, 485848: 1, 485849: 1, 485850: 1, 485851: 1, 485852: 1, 485853: 1, 485854: 1, 485855: 1, 485856: 1, 485857: 1, 485858: 1, 485859: 1, 485860: 1, 485861: 1, 485862: 1, 485863: 1, 485864: 1, 485865: 1, 485866: 1, 485867: 1};
try {
	if (acc_list[_ap_an.site] == 1) {
		_ap_an.bigsea_rd();
	}
} catch (e) { }

/* ======================== publisher ======================== */

create_ifrtrk();

function wr_aff_pub(n, l, h) {
	if (typeof l == 'undefined') l = 1; if (typeof h == 'undefined') h = 1;
	document.write('<img src="https://cstatic.weborama.fr/transp.gif" width=' + l + ' height=' + h + '/>');
}

var _ap_ad = {};
var wr_solutions = 'solution.weborama.fr/fcgi-bin/diff.fcgi?';

_ap_ad.write = function (src) {
	document.write('<scr' + 'ipt type="text/' + 'jav' + 'ascri' + 'pt" src="' + src + '"></script>');
}
_ap_ad.host = (_ap_defined('wr_host')) ? wr_host : 'aimfar';
_ap_ad.board = (_ap_defined('wr_board')) ? 'ide=' + wr_board : null;
_ap_ad.boardsize = (_ap_defined('wr_boardsize')) ? 'emp=' + wr_boardsize : 'emp=1x1';
_ap_ad.autopage = (_ap_defined('wr_detail')) ? 'kp=' + wr_detail : null;
_ap_ad.floating = (_ap_defined('wr_floating')) ? 'floating=' + wr_floating : 'floating=0';
_ap_ad.site = (_ap_defined('wr_site')) ? 'ids=' + wr_site : null;
_ap_ad.page = (_ap_defined('wr_page')) ? 'pageid=' + wr_page : null;
_ap_ad.extparams = (_ap_defined('wr_extparams')) ? 'ext_params=' + escape(wr_extparams) : null;
_ap_ad.crealist = (_ap_defined('wr_crealist')) ? 'cl=' + wr_crealist : null;
_ap_ad.advlist = (_ap_defined('wr_advlist')) ? 'al=' + wr_advlist : null;
_ap_ad.camplist = (_ap_defined('wr_camplist')) ? 'cal=' + wr_camplist : null;
_ap_ad.model = (_ap_defined('wr_model')) ? 'model=' + wr_model : null;
_ap_ad.thema = (_ap_defined('wr_thema')) ? 'wthema=' + wr_thema : null;
_ap_ad.click = (_ap_defined('wr_click')) ? 'ext_click=' + escape(wr_click) : null;
_ap_ad.ex_secteurs = (_ap_defined('wr_ex_secteurs')) ? 'nsa=' + escape(wr_ex_secteurs) : null;
_ap_ad.backup = (_ap_defined('wr_backup')) ? 'bak=' + escape(wr_backup) : null;
_ap_ad.cache = (_ap_defined('wr_cache')) ? 'cache=' + wr_cache : null;
_ap_ad.ref = null;
_ap_ad.url = null;
try {
	_ap_ad.url = 'url=' + escape(document.location);
	var ref = (top != null && top.location != null && typeof (top.location.href) == "string") ? top.document.referrer : document.referrer;
	_ap_ad.ref = 'ref=' + escape(ref);
} catch (e) { }

_ap_adlist = ['site', 'page', 'boardsize', 'autopage', 'board', 'crealist',
	'advlist', 'camplist', 'model', 'thema', 'floating', 'extparams', 'ref', 'url', 'click',
	'ex_secteurs', 'backup', 'cache'
];
// IMPORTANT: not protected by gdpr
var _ap_script = 'https://' + _ap_ad.host + '.' + wr_solutions;
var _ap_first = true;


for (var k in _ap_adlist) {
	var key = _ap_adlist[k];
	if ('undefined' != typeof (_ap_ad[key]) && _ap_ad[key] != null) {
		_ap_script += (_ap_first ? '' : '&') + _ap_ad[key];
		_ap_first = false;
	}
}
if (_ap_ad.board != null) _ap_ad.write(_ap_script);

/* ======================== advertiser ======================== */
