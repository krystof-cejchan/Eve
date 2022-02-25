<?php
/*
http://kys.hys.cz/DISCORD%20BOT(S)/new.php?boolean=0&desturl=http://kys.hys.cz/DISCORD%20BOT(S)/adam_rage.png&srcurl=http://kys.hys.cz/DISCORD%20BOT(S)/honza_slightlyconfused.png
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
    $dst_x = 0;
    $dst_y = 0;
    $src_x = 20;
    $src_y = 20;
    $src_width = 165;
    $src_height = 100;
    $alpha = 100;

    imagecopymerge($dest, $src, $dst_x, $dst_y, $src_x, $src_y, $src_width, $src_height, $alpha);


    header('Content-Type: image/png');
    imagepng($dest);



    imagedestroy($dest);
    imagedestroy($src);
    /*memory cleared*/
}
?>