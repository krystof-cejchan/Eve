<!DOCTYPE html>
<html>
    <style>
    #imgs { 
  width:500px;
  height: 500px;
  background-repeat: no-repeat, repeat;
  background-image: 
  url(
  <?php echo ($_GET["url1"]); ?>), 
  url(<?php echo ($_GET["url2"]); ?>);
  background-blend-mode: multiply;
}
    </style>
<head>
</head>
<body>
  <!--  <form method="GET">
            <table>
            	<tr>
            		<td>url1</td>
            		<td><input name="url1" type="text" /></td>
            	</tr>
            	<tr>
            		<td>url2</td>
            		<td><input name="url2" type="text" /></td>
            	</tr>
			
        	</table>
        
            
            <input type="submit" value="Odeslat" />
        </form >  -->
        <div id="imgs"></div>
      
</body>
</html>
<?php

function get1($pic1) {
$img1 = base64_encode(file_get_contents($pic1));
/* add size */
echo  '<img src="data:image/jpg;base64,'.$img1.'">';
}
function get2($pic2) {

$img2 = base64_encode(file_get_contents($pic2));

echo  '<img src="data:image/jpg;base64,'.$img2.'">';
}


?>



<!--<!DOCTYPE html>
<html>
<head>
<style>
#pic { 
  width: 400px;
  height: 400px;
  background-repeat: no-repeat, repeat;
  background-image: url("https://memetemplates.in/uploads/1641286889.png"), url("https://t3.ftcdn.net/jpg/03/46/83/96/360_F_346839683_6nAPzbhpSkIpb8pmAwufkC7c5eD7wYws.jpg");
  background-blend-mode: multiply;
}
</style>
</head>
<body>

<div id="pic"></div>

</body>
</html>
-->






