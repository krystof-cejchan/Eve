
<html>
<head>
<title>Music Bot with Emotional Damage</title>
<link rel="icon" href="http://kys.hys.cz/Z0c9owbRc0OnCeQVHdY3/images/pepo-jam-pepe.png">
</head>
<body>
<center>
<div class="widget">
<a href="https://top.gg/bot/933482115037810729">
  <img src="https://top.gg/api/widget/933482115037810729.svg">
</a></div><br>
<div class="invite">
<button class="invite" name="inv" role="button" onclick="Open()")>INVITE BOT</button>
</div>
</center>
</body>

 <script>
        function Open() {
            window.open("https://discord.com/oauth2/authorize?client_id=933482115037810729&permissions=8&scope=bot", "_blank");
        }
    </script>

<style>
body {
  background-color: #800080;
}
.invite,
.invite:after {
  width: 150px;
  height: 76px;
  line-height: 78px;
  font-size: 20px;
  font-family: 'Bebas Neue', sans-serif;
  background: linear-gradient(45deg, transparent 5%, #4d004d 5%);
  border: 0;
  color: #fff;
  letter-spacing: 3px;
  box-shadow: 6px 0px 0px black;
  outline: transparent;
  position: relative;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}

.invite:after {
  --slice-0: inset(50% 50% 50% 50%);
  --slice-1: inset(80% -6px 0 0);
  --slice-2: inset(50% -6px 30% 0);
  --slice-3: inset(10% -6px 85% 0);
  --slice-4: inset(40% -6px 43% 0);
  --slice-5: inset(80% -6px 5% 0);
  
  content: 'INVITE BOT';
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(45deg, transparent 3%, #4d004d 3%, #4d004d 5%, #4d004d 5%);
  text-shadow: -3px -3px 0px #F8F005, 3px 3px 0px #00E6F6;
  clip-path: var(--slice-0);
}

.invite:hover:after {
  animation: 1s glitch;
  animation-timing-function: steps(2, end);
}

@keyframes glitch {
  0% {
    clip-path: var(--slice-1);
    transform: translate(-20px, -10px);
  }
  10% {
    clip-path: var(--slice-3);
    transform: translate(10px, 10px);
  }
  20% {
    clip-path: var(--slice-1);
    transform: translate(-10px, 10px);
  }
  30% {
    clip-path: var(--slice-3);
    transform: translate(0px, 5px);
  }
  40% {
    clip-path: var(--slice-2);
    transform: translate(-5px, 0px);
  }
  50% {
    clip-path: var(--slice-3);
    transform: translate(5px, 0px);
  }
  60% {
    clip-path: var(--slice-4);
    transform: translate(5px, 10px);
  }
  70% {
    clip-path: var(--slice-2);
    transform: translate(-10px, 10px);
  }
  80% {
    clip-path: var(--slice-5);
    transform: translate(20px, -10px);
  }
  90% {
    clip-path: var(--slice-1);
    transform: translate(-10px, 0px);
  }
  100% {
    clip-path: var(--slice-1);
    transform: translate(0);
  }
}

@media (min-width: 768px) {
  .invite,
  .invite:after {
    width: 200px;
    height: 86px;
    line-height: 88px;
  }
}
</style>
</html>


