<?php
/*
http://kys.hys.cz/DISCORD%20BOT(S)/new.php?boolean=0&&src2url=http://kys.hys.cz/DISCORD%20BOT(S)/honza_slightlyconfused.png&desturl=https://imgflip.com/s/meme/Distracted-Boyfriend.jpg&srcurl=http://kys.hys.cz/DISCORD%20BOT(S)/honza_slightlyconfused.png
*/
if (empty($_GET['boolean']) == false || assert($_GET['boolean'])) {
    if ((empty($_GET['desturl']) == false ||
        empty($_GET['srcurl']) == false

        ||

        (assert($_GET['desturl']) && assert($_GET['srcurl'])))) {

        $boolean = $_GET['boolean'];
        $url_1 = $_GET['desturl'];
        $url_2 = $_GET['srcurl'];
        mergeImg($url_1, $url_2);
    } else {
        if ($boolean == 1) {
            $invalid_URL = (isset($_SERVER['HTTPS']) && $_SERVER['HTTPS'] === 'on' ? "https" : "http") . "://$_SERVER[HTTP_HOST]$_SERVER[REQUEST_URI]";

            echo
            "<b>your address</b> <br> <i>" . $invalid_URL . "</i><br> <b>is invalid </b>";
        }
        if ($boolean == 0) {
            echo
            "invalid parameters";
        }
    }
} else {
    echo "access denied";
}
function mergeImg($img1, $img2)
{

    $dest =  imagecreatefromstring(file_get_contents($img1));
    $src =  imagecreatefromstring(file_get_contents($img2));
    list($dest_width, $dest_height) = getimagesize($img1);
    list($src_width, $src_height) = getimagesize($img2);

    $dst_x = 250;
    $dst_y = 230;
    $src_x = $dest_width / 2;
    $src_y = $dest_height / 2;
    // $src_width = $dest_width /2 ;
    // $src_height = $dest_width /2;
    $alpha = 100;




    imagecopymerge($dest, $src, $dst_x, $dst_y, $src_x, $src_y, $src_width, $src_height, $alpha);
    /*$black = imagecolorallocate($dest, 0, 0, 0); 
imagecolortransparent($dest, $black);*/

    header('Content-Type: image/png');
    imagepng($dest);



    imagedestroy($dest);
    imagedestroy($src);
    /*memory cleared*/
}
?>