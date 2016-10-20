// Copyright (c) 2012 Lam Phan Viet. All rights reserved.
// Contact: lamphanviet@gmail.com

// Google analytics code
var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-41275252-1']);
_gaq.push(['_trackPageview']);

(function() {
  var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
  ga.src = 'https://ssl.google-analytics.com/ga.js';
  var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
})();
// end - Google analytics code

// UVA LINK
var uva_quickSubmit = "http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=25";
var uva_pstat = "http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=problem_stats&problemid="; // + problem Id
var uhuntHome = "http://uhunt.felix-halim.net/";
var uhuntLiveEvent = "http://uhunt.felix-halim.net/api/poll/"; // + min Id
var uhuntProblemList = "http://uhunt.felix-halim.net/api/p";
var uhuntProblemId = "http://uhunt.felix-halim.net/api/p/id/"; // + problem Id
var uhuntProblemNum = "http://uhunt.felix-halim.net/api/p/num/"; // + problem Number
var uhuntSubsNum = "http://uhunt.felix-halim.net/api/subs-nums/"; // + userid + probNum
var uhuntSubsId = "http://uhunt.felix-halim.net/api/subs-pids/"; // + userid + problem Id
var uhuntRankList = "http://uhunt.felix-halim.net/api/ranklist/"; // + userid/up/down
var uhuntUserSubmissions = "http://uhunt.felix-halim.net/api/subs/"; // + userid
var uhuntUserNameToId = "http://uhunt.felix-halim.net/api/uname2uid/" // + username

var timeDifference = 0;

function getId(id) {
	return document.getElementById(id);
}

function getCurrentTime() {
	return Math.round(new Date().getTime() / 1000);
}

function isDigit(charCode) {
	return (48 <= charCode && charCode <= 57);
}

function isUpper(charCode) {
	return (65 <= charCode && charCode <= 90);
}

var Set = function() {
	this.add = function(key) { this[key] = true; }
	this.remove = function(key) { delete this[key]; }
	this.exist = function(key) { return (key in this); }
}
var Map = function() {
	this.exist = function(key) { return (key in this); }
	this.erase = function(key) { delete this[key]; }
	this.add = function(key, val) { this[key] = val; }
	this.get = function(key) { return this[key]; }
}

var Submission = function(sid, pid, ver, run, sbt, lan, rank, uid, name) {
	this.sid = sid;
	this.pid = pid;
	this.ver = ver;
	this.run = run;
	this.sbt = sbt;
	this.lan = lan;
	this.rank = rank;
	this.uid = uid;
	this.name = name;
}

/*======= FORMAT =======*/
var format = new function() {
	var langList = ["", "C", "Java", "C++", "PASCAL", "C++11"]; // OK
	var verList = new function() {}; // OK
		verList[0] = ["<i>?</i>", "QU", "#000000"];
		verList[10] = ["SE","SE","#000000"];
		verList[15] = ["CJ","CJ","#000000"];
		verList[20] = ["IQ","QU","#000000"];
		verList[30] = ["CE","CE","#AAAA00"];
		verList[35] = ["RF","RF","#000000"];
		verList[40] = ["RE","RE","#00AAAA"];
		verList[45] = ["OL","OL","#000066"];
		verList[50] = ["TLE","TL","#0000FF"];
		verList[60] = ["ML","ML","#0000AA"];
		verList[70] = ["WA","WA","#FF0000"];
		verList[80] = ["PE","PE","#666600"];
		verList[90] = ["AC","AC","#00AA00"];
		
	var uvaLink1 = "http://uva.onlinejudge.org/external/"; // + [prob cat]/[pro num].html
	var uvaLink2 = "http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem="; // + problem Id
	
	this.verdict = function(ver) { // OK
		return "<b><font color='" + verList[ver][2] + "'>" + verList[ver][0] + "</font></b>";
	}
	
	this.language = function(lang) { // OK
		return langList[lang];
	}
	
	this.username = function(_id, _username) {
		return ["<strong><a href='http://uhunt.felix-halim.net/id/", _id, "' target='_blank'>", _username, "</a></strong>"].join('');
	}
	
	this.fullUsername = function(_id, _name, _username) {
		return ["<a href='http://uhunt.felix-halim.net/id/", _id, "' target='_blank' style='text-decoration:none;'>", _name, "</a>", " (<strong><a href='http://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_authorstats&userid=", _id, "' target='_blank'>", _username, "</a></strong>)"].join('');
	}
	
	this.name = function(_id, _name) {
		return ["<a href='http://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_authorstats&userid=", _id, "' target='_blank'>", _name, "</a>"].join('');
	}
	
	this.submitTime = function(sbt) {
		var curTime = getCurrentTime();
		var second = curTime - sbt, res = "";
		if (second + timeDifference <= 0) timeDifference = -second;
		second += timeDifference;
		if (second < 60) { res = second + " sec"; }
		else if (second < 60 * 60) { second = Math.floor(second / 60); res = second + " min"; }
		else if (second < 60 * 60 * 24) { second = Math.floor(second / (60 * 60)); res = second + " hour"; }
		else if (second < 60 * 60 * 24 * 30) { second = Math.floor(second / (60 * 60 * 24)); res = second + " day"; }
		else { second = Math.floor(second / (60 * 60 * 24 * 30)); res = second + " month"; }
		if (second != 1) res += "s";
		return res;
	}
	
	this.problemNumberWithId = function(id) {
		return format.problemNumber(problemInformation.problemIdToNumber(id)); // id = problem number
	}
	
	this.problemNumber = function(number) {
		return "<strong><a href='" + uvaLink1 + Math.floor(number / 100) + "/" + number + ".html' target='_bank'>" + number + "</a></strong>";
	}
	
	this.problemName = function(id) {
		var name = problemInformation.problemIdToName(id);
		return "<a href='" + uvaLink2 + id + "' target='_bank'>" + name + "</a>";
	}
	
	this.runTime = function(time) { return (time / 1000.0).toFixed(3); }
	
	this.rank = function(rank) {
		if (rank < 1) return "-";
		if (rank > 20)
			return "<font color='blue'>" + rank + "</font>";
		return "<font color='red'>" + rank + "</font>";
	}
	
	this.timeCost = function(s) {
		if (s + timeDifference <= 0) timeDifference = -s + 3;
		s += timeDifference;
		var d, h, m, ret = "";
		d = Math.floor(s / (60 * 60 * 24));
		s -= d * 60 * 60 * 24;
		h = Math.floor(s / (60 * 60));
		s -= h * 60 * 60;
		m = Math.floor(s / 60);
		s -= m * 60;
		if (d > 0) {
			ret = d + " day";
			if (d > 1) ret += "s";
		}
		else {
			ret = (h >= 10) ? h : "0" + h;
			ret += ":";
			ret += (m >= 10) ? m : "0" + m;
			ret += ":";
			ret += (s >= 10) ? s : "0" + s;
		}
		return ret;
	}
	
	this.timeCostFull = function(s) {
		if (s + timeDifference <= 0) timeDifference = -s + 3;
		s += timeDifference;
		var d, h, m, ret = "";
		d = Math.floor(s / (60 * 60 * 24));
		s -= d * 60 * 60 * 24;
		h = Math.floor(s / (60 * 60));
		s -= h * 60 * 60;
		m = Math.floor(s / 60);
		s -= m * 60;
		if (d > 0) {
			ret = d + " day";
			if (d > 1) ret += "s";
			ret += " ";
		}
		ret += (h >= 10) ? h : "0" + h;
		ret += ":";
		ret += (m >= 10) ? m : "0" + m;
		ret += ":";
		ret += (s >= 10) ? s : "0" + s;
		return ret;
	}
	
	this.contestantProblem = function(subs, time, solved) {
		if (solved || subs > 0) {
			subs = ["<strong>", subs, "</strong>"].join('');
			return [subs, "<br />", format.timeCost(time)].join('');
		}
		return "&nbsp;</br >&nbsp;";
	}
	
	this.statNumber = function(n) {
		var clr = n == 0 ? "#6D6D6D" : "#D80000";
		return ["<font color='", clr, "'>", n, "</font>"].join('');
	}
	
	this.dateTime = function(date) {
		var Y = date.getUTCFullYear();
		var m = date.getUTCMonth() + 1;
		var d = date.getUTCDate();
		var H = date.getUTCHours();
		var i = date.getUTCMinutes();
		var s = date.getUTCSeconds();
		if (m < 10) m = "0" + m;
		if (d < 10) d = "0" + d;
		if (H < 10) H = "0" + H;
		if (i < 10) i = "0" + i;
		if (s < 10) s = "0" + s;
		return [Y, "-", m, "-", d, " ", H, ":", i, ":", s].join('');
	}
	
	this.problemTimeLimit = function(timeLimit) {
		return [(timeLimit / 1000.0).toFixed(3), "s"].join('');
	}
}

var problemInformation = new function() {
	var isDone = false;
	var problemSet = new function() {} // all information of uva problem sets
	var problemId = new function() {}; // get problem Id from problem number
	var problemNumber = new function() {}; // get problem number from problem Id
	var problemName = new function() {}; // get problem name from problem number
	var problemTimeLimit = new function() {}; // get problem run-time limit from problum Id
	var counter = 0;
	this.get = function(id, number, name, timeLimit) {
		problemId[number] = id;
		problemNumber[id] = number;
		problemName[number] = name;
		problemTimeLimit[id] = timeLimit;
	}
	function loadProblems(data) {
		for (var i = 0; i < data.length; i++) {
			problemSet[data[i][1]] = data[i];
			problemInformation.get(data[i][0], data[i][1], data[i][2], data[i][19]);
		}
		isDone = true;
	}
	this.getAll = function() {
		if (localStorage.uvaProblems) {
			loadProblems($.parseJSON(localStorage.uvaProblems));
			setTimeout("problemInformation.getFromUhunting()", 10000); // reload data after 10s
		}
		else problemInformation.getFromUhunting();
	}
	this.getFromUhunting = function() {
		$.get(uhuntProblemList, function(data) {
			localStorage.uvaProblems = data;
			loadProblems($.parseJSON(localStorage.uvaProblems));
			setTimeout("problemInformation.getFromUhunting()", 1800000); // update every 30-minutes
		}, "text")
		.error(function() { // try again
			problemInformation.getFromUhunting();
		})
	}
	this.isReady = function() {
		return isDone;
	}
	this.getProblem = function(number) {
		return problemSet[number];
	}
	this.problemIdToNumber = function(id) { // convert problem ID to problem NUMBER
		return problemNumber[id];
	}
	this.problemIdToName = function(id) { // convert problem ID to problem NAME
		return problemName[problemNumber[id]];
	}
	this.problemNumberToId = function(number) { // convert problem NUMBER to problem ID
		return problemId[number];
	}
	this.problemNumberToName = function(number) { // convert problem NUMBER to problem NAME
		return problemName[number];
	}
	this.problemIdToTimeLimit = function(id) { // get Run-Time Limit of problem
		return problemTimeLimit[id];
	}
	this.existNumber = function(number) {
		return (number in problemId);
	}
}

var liveJudge, userJudge;

function compareSubmissionsArray(a, b) {
	if (a[0] == b[0]) return 0;
	return a[0] < b[0] ? -1 : 1;
}

function compareSubmissionsObject(a, b) { // compare Sid
	if (a.sid == b.sid) return 0;
	return (a.sid < b.sid) ? -1 : 1;
}

function LiveJudge(thisName) {
	var table = null;
	var submissions = [], lastSubmissionId = 0;
	var maxNumberOfRows = 5, numberOfRows = 5;
	var userSet = new Set(); // set of usersId if specificUsers = true (liveJudge.startSpecificUsers is called)
	var specificUsers = false, counter = 0, numberOfUsers = 0;
	var isStopped;
	
	this.init = function() {
		if (table != null) table.innerHTML = "";
		submissions = [];
		lastSubmissionId = 0;
		userSet = new Set();
		counter = numberOfUsers = 0;
	}
	
	this.start = function(tableId) {
		table = getId(tableId);
		this.init();
		specificUsers = false;
		isStopped = false;
		this.watchLiveEvent();
		this.updateInfo();
	}
	
	this.stop = function() {
		isStopped = true;
		this.init();
	}
	
	this.startSpecificUsers = function(tableId, users) {
		table = getId(tableId);
		this.init();
		specificUsers = true;
		isStopped = false;
		numberOfUsers = users.length;
		for (var i = 0; i < numberOfUsers; i++) {
			userSet.add(users[i]);
			loadUserSubmissions(users[i]);
		}
		this.processUserSubmissions();
	}
	
	this.setNumberOfRows = function(value) {
		if (value < numberOfRows) {
			for (var i = numberOfRows - 1; i >= value && i >= 0; i--) {
				if (i < table.rows.length)
					table.deleteRow(i);
			}
		}
		else if (numberOfRows < value) {
			for (var i = numberOfRows; i < value && i < maxNumberOfRows && i < submissions.length; i++) {
				insertNewRow(submissions[i], i);
			}
		}
		numberOfRows = value;
	}
	
	function loadUserSubmissions(id) {
		// result.sub[i] => 0:subId, 1:probId, 2:verdict, 3:runtime, 4:subtime, 5:language, 6:rank
		// uhunt Submission = { sid, pid, ver, run, sbt, lan, rank, uid, name, uname }
		$.getJSON(uhuntUserSubmissions + id, function(data) {
			var subs = $.parseJSON(data.subs);
			subs.sort(compareSubmissionsArray); subs.reverse();
			if (subs.length > maxNumberOfRows) subs.splice(maxNumberOfRows, subs.length - maxNumberOfRows);
			for (var i = 0; i < subs.length; i++) {
				var row = subs[i];
				var newSubmission = new Submission(row[0], row[1], row[2], row[3], row[4], row[5], row[6], id, data.name);
				newSubmission.uname = data.uname;
				submissions.push(newSubmission);
			}
			submissions.sort(compareSubmissionsObject); submissions.reverse();
			if (submissions.length > maxNumberOfRows) submissions.splice(maxNumberOfRows, submissions.length - maxNumberOfRows);
			counter++;
		})
		.error(function() {
			loadUserSubmissions(id);
		});
	}
	
	this.processUserSubmissions = function() {
		if (counter < numberOfUsers) {
			setTimeout(thisName + '.processUserSubmissions()', 200);
			return;
		}
		for (var i = 0; i < submissions.length && i < numberOfRows; i++)
			insertNewRow(submissions[i], i);
		this.watchLiveEvent();
		this.updateInfo();
	}
	
	function accept(userId) {
		if (!specificUsers) return true;
		return userSet.exist(userId);
	}
	
	function insertNewRow(row, pos) {
		var newRow = table.insertRow(pos);
		newRow.insertCell(0).innerHTML = row.sid; // submission id
		newRow.cells[0].setAttribute("align", "center");
		newRow.insertCell(1).innerHTML = format.problemNumberWithId(row.pid); // problem number
		newRow.cells[1].setAttribute("align", "right");
		newRow.insertCell(2).innerHTML = format.problemName(row.pid); // problem name
		newRow.cells[2].setAttribute("align", "left");
		newRow.insertCell(3).innerHTML = format.fullUsername(row.uid, row.name, row.uname); // user (username)
		newRow.cells[3].setAttribute("align", "left");
		newRow.insertCell(4).innerHTML = format.verdict(row.ver); // result
		newRow.cells[4].setAttribute("align", "center");
		newRow.insertCell(5).innerHTML = format.language(row.lan); // language
		newRow.cells[5].setAttribute("align", "center");
		newRow.insertCell(6).innerHTML = format.runTime(row.run); // run time
		newRow.cells[6].setAttribute("align", "right");
		newRow.insertCell(7).innerHTML = format.rank(row.rank); // rank
		newRow.cells[7].setAttribute("align", "center");
		newRow.insertCell(8).innerHTML = format.submitTime(row.sbt); // submission time
		newRow.cells[8].setAttribute("align", "center");
	}
	
	this.watchLiveEvent = function() {
		if (isStopped) return;
		$.getJSON(uhuntLiveEvent + lastSubmissionId, function(data) {
			if (isStopped) return;
			for (var i = 0; i < data.length; i++) {
				if (data[i].type != "lastsubs") continue;
				if (data[i].id > lastSubmissionId) { lastSubmissionId = data[i].id; }
				var row = data[i].msg, isAdd = true;
				
				if (!accept(row.uid)) continue;
				
				for (var j = 0; j < submissions.length && isAdd; j++) {
					if (submissions[j].sid == row.sid) {
						if (j < table.rows.length) {
							table.rows[j].cells[4].innerHTML = format.verdict(row.ver); // result
							if (row.ver == 90) {
								table.rows[j].cells[7].innerHTML = format.rank(row.rank); // rank
							}
							table.rows[j].cells[6].innerHTML = format.runTime(row.run); // run time
						}
						submissions[j] = row;
						isAdd = false;
					}
				}
				if (!isAdd) continue;
				insertNewRow(row, 0);
				
				submissions.unshift(row);
				while (submissions.length > maxNumberOfRows) {
					submissions.pop();
				}
				while (table.rows.length > numberOfRows) {
					table.deleteRow(numberOfRows);
				}
			}
		})
		.complete(function() {
			if (!isStopped) setTimeout(thisName + '.watchLiveEvent();', 100);
		});
	}
	
	this.updateInfo = function() {
		for (var i = 0; i < table.rows.length; i++) {
			var curRow = table.rows[i].cells;
			if (problemInformation.isReady() && curRow[2].firstChild.innerHTML == "undefined") {
				curRow[1].innerHTML = format.problemNumberWithId(submissions[i].pid);
				curRow[2].innerHTML = format.problemName(submissions[i].pid);
			}
			curRow[8].innerHTML = format.submitTime(submissions[i].sbt);
		}
		while (submissions.length > maxNumberOfRows) submissions.pop();
		while (table.rows.length > numberOfRows) table.deleteRow(numberOfRows);
		if (!isStopped) setTimeout(thisName + '.updateInfo()', 1000);
	}
}

function updateProblemDetails() {
	if (!problemInformation.isReady()) {
		setTimeout("updateProblemDetails()", 50);
		return;
	}
	var number = "", id = "", title = "undefined", timeLimit = 0, brun = 0;
	var ndacu = 0, nre = 0, ntle = 0, nwa = 0, nac = 0;
	number = $("#problemNumber").val();
	if (problemInformation.existNumber(number)) {
		var problem = problemInformation.getProblem(number);
		id = problem[0];
		title = format.problemNumber(number) + " - " + format.problemName(id);
		timeLimit = problem[19];
		brun = problem[4];
		
		ndacu = problem[3];
		nre = problem[12];
		ntle = problem[14];
		nwa = problem[16];
		nac = problem[18];
	}
	
	$("#ptitle").html(title);
	$("#ptl").html(format.problemTimeLimit(timeLimit));
	$("#pbrun").html(format.problemTimeLimit(brun));
	$("#pstat").attr("href", uva_pstat + id);
	$("#pdis").attr("href", "http://online-judge.uva.es/board/search.php?keywords=" + number);
	$("#pgoo").attr("href", "http://www.google.com/search?btnG=1&pws=0&q=uva%20problem%20" + number);
	$("#pndacu").html(ndacu);
	$("#pnac").html(nac);
	$("#pnwa").html(nwa);
	$("#pntle").html(ntle);
	$("#pnre").html(nre);
	
	localStorage.problemNumber = number;
}

function changeUserName() {
	var username = $("#username").val();
	$.get(uhuntUserNameToId + username, function(userId) {
		if (userId > 0) { // valid username
			userJudge.stop();
			userJudge.startSpecificUsers("userJudgeBody", [userId]);
			localStorage.userName = username;
		}
	}, "text")
	.error(function() {
		changeUserName();
	});
}

$("#problemNumber").keyup(function() {
	updateProblemDetails();
});

$("#username").change(function() {
	changeUserName();
});

chrome.tabs.getSelected(null, function(tab) {
	problemInformation.getAll();
	
	liveJudge = new LiveJudge("liveJudge");
	liveJudge.start("liveJudgeBody");
	
	userJudge = new LiveJudge("userJudge");
	if (localStorage.userName) $("#username").val(localStorage.userName);
	changeUserName();
	
	if (!(localStorage.problemNumber)) localStorage.problemNumber = "10000";
	$("#problemNumber").val(localStorage.problemNumber);
	updateProblemDetails();
});

