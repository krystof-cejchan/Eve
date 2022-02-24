<?php
mergeImg('http://kys.hys.cz/DISCORD%20BOT(S)/adam_rage.png', 'http://kys.hys.cz/DISCORD%20BOT(S)/honza_slightlyconfused.png');

function mergeImg($img1, $img2) {
$dest = imagecreatefrompng($img1);
$src = imagecreatefrompng($img2);
 

$dst_x=0;


$dst_y=0;


$src_x=20;


$src_y=20;


$src_width=165;


$src_height=100;


$alpha =100;
imagecopymerge($dest, $src, $dst_x, $dst_y, $src_x, $src_y, $src_width, $src_height, $alpha);
 
// Output and free from memory
header('Content-Type: image/png');
imagepng($dest);
 
imagedestroy($dest);
imagedestroy($src);
}
?>