<html>
<?php
// Create image instances
//$dest = imagecreatefromjpeg('http://www.easytolearning.com/webroot/ck_files/files/php-variables.jpg'); 
$dest= imagecreatefromstring(file_get_contents('http://www.easytolearning.com/webroot/ck_files/files/php-variables.jpg'));
//$src = imagecreatefromjpeg('https://easytolearning.com/img/free-download-projects.jpg');
$src= imagecreatefromstring(file_get_contents('https://easytolearning.com/img/free-download-projects.jpg'));


// Copy and merge
//imagecopymerge($dest, $src, 100, 100, 100, 100, 100, 100, 100);
imagecopymerge ($dest, $src, 20, 20, 20, 20, 500, 500,75);
// Output and free from memory


header('Content-Type: image/jpeg');

imagepng($dest);

imagedestroy($dest);
imagedestroy($src);
?>
</html>