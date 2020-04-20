<html>
<head>
<title>[demostream]</title>
<style >

@font-face {
font-family: 'IBMVGA8';
src: url(fonts/PxPlus_IBM_VGA8.eot);
src: url(fonts/PxPlus_IBM_VGA8.eot?#iefix) format('embedded-opentype'),
		 url(fonts/PxPlus_IBM_VGA8.woff2) format('woff2'),
		 url(fonts/PxPlus_IBM_VGA8.woff) format('woff'),
		 url(fonts/PxPlus_IBM_VGA8.ttf) format('truetype');
font-weight: normal;
font-style: normal;
}

@font-face {
font-family: 'IBMCGAthin';
src: url(fonts/PxPlus_IBM_CGAthin.eot);
src: url(fonts/PxPlus_IBM_CGAthin.eot?#iefix) format('embedded-opentype'),
		 url(fonts/PxPlus_IBM_CGAthin.woff2) format('woff2'),
		 url(fonts/PxPlus_IBM_CGAthin.woff) format('woff'),
		 url(fonts/PxPlus_IBM_CGAthin.ttf) format('truetype');
font-weight: normal;
font-style: normal;
}

@font-face {
font-family: 'IBMEGA';
src: url(fonts/PxPlus_IBM_EGA8.eot);
src: url(fonts/PxPlus_IBM_EGA8.eot?#iefix) format('embedded-opentype'),
		 url(fonts/PxPlus_IBM_EGA8.woff2) format('woff2'),
		 url(fonts/PxPlus_IBM_EGA8.woff) format('woff'),
		 url(fonts/PxPlus_IBM_EGA8.ttf) format('truetype');
font-weight: normal;
font-style: normal;
}
</style>
<style >

body {
	background-color: #260c22;
	font-family: 'IBMVGA8', monospace;
	font-size: 16pt;
	line-height: 120%;
	text-shadow: 0 0 2px rgba(255,255,255,0.8);
	max-width: 60%;
	min-width: 800px;
	border: auto;
	margin: auto;
	color: white;
}

h1 {
	font-family: 'IBMCGAthin', monospace;
	font-size: 30pt;
	line-height: 160%;
	padding-top: 3%;
}

p, h3 {
	padding: 0 50px;
}

a {
	color: #bb93b2;
}

.github {
	position:relative;
	width:100%;
	max-width:100%;
	text-align: right;
	font-size: 70%;
	color: white;
}


</style>
<script src="https://hls-js.netlify.com/dist/hls.js"></script>
</head>
<body>



<center>
	<h1>cyan's stream</h1>
</center>
<p>
<center> Welcome to <a href="http://demostream.cyanpencil.xyz">demostream</a>! </center> 

This is a broadcasting service aimed at reproducing <b> demos</b>. 

Play it in background to have a stimulating visual and audio effect to improve whatever hacks you're hacking about (playing <a href="https://ctftime.org/">ctfs</a> is recommended) 
</p>
<center>
	<video height="600" width="100%" id="video" controls></video>
	<script>
		if(Hls.isSupported()) {
			var video = document.getElementById('video');
			var hls = new Hls();
			hls.loadSource('http://cyanpencil.xyz:8080/media_server/demostream/index.m3u8');
			hls.attachMedia(video);
			hls.on(Hls.Events.MANIFEST_PARSED,function() {
				video.play();
			});
		}

	else if (video.canPlayType('application/vnd.apple.mpegurl')) {
	video.src = 'http://cyanpencil.xyz:8080/media_server/demostream/index.m3u8';
	video.addEventListener('canplay',function() {
	video.play();
	});
	}
	</script>
	<br/>
	<br/>
	<a href="http://demostream.cyanpencil.xyz/stat">some stats</a> -
	<a href="http://demostream.cyanpencil.xyz/demostream.m3u8">x264 feed</a> (open with mpv or vlc)  
	- [<?php
	echo shell_exec("ss -t state established sport 8080 | wc -l");
	?>
	viewers]

</center>

<p>
Instructions: <br/> Leave the stream open in the background. You don't have to watch it. Just enjoy the music and work on something you love. Whenever you are in need of inspiration, look at it for a few moments, and go create a masterpiece like those authors did.
</p>
<p>
Available categories are Commodore 64, Amiga, Atari ST, pc 64 kB, pc 4kB, pc 1kB, ZX spectrum, msdos
</p>

<br/>
<hr/>

<h3> What is a demo?</h3>
<p>
Demos are computer generated audiovisual clips that geeks show off with. They are built to use any given hardware full potential, sometimes using bugs, exploits or undocumented features to achieve so. 

They were born together with the first cracking groups - demos were included in crack releases, keygens and various warez. With time, they became a thing of their own, and people organized parties and the <i>demoscene</i> was born. <br/>

Want to know some more? Here are a few great links to delve into: <br/>
<center>
<a href="https://pouet.net"> pouet.net</a> - 
<a href="https://demozoo.org"> demozoo.org</a> - 
<a href="https://scene.org"> scene.org</a> - 
<a href="https://shadertoy.com"> shadertoy.com</a> 
</center>





</p>

<div class="github" > <a href="https://github.com/cyanpencil/demostream"> source on github </a> </div>

</html>
