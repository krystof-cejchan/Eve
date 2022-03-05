<?php
//getAndDo($_GET['rnd']);
if (assert($_GET['profpic1']) && empty($_GET['profpic1']) == false) {
    $arg = $_GET['profpic1'];
    switch ($_GET['rnd']) {
        case 1:
            Distracted_BoyFriend($arg);
            break;
        case 2:
            But_That_Thing_It_Scares_Me($arg);
            break;
        case 3:
            Clown($arg);
            break;
        case 4:
            SpongeBob_Burn($arg);
            break;
        case 5:
            Smoking_while_Pregnant($arg);
            break;
        case 6:
            SpiderMan($arg);
            break;
        case 7:
            a_Star_Was_Born($arg);
            break;
        default:
            echo 'err';
            break;
    }
}
if (assert($_GET['getFunctionCount'])) {
    $functions = get_defined_functions();
    $functions_list = array();
    $i = 0;
    foreach ($functions['user'] as $func) {
        $i++;
    }
    echo $i;
}
function Clown($clown_)
{
    if ((assert($_GET['profpic1']) && empty($_GET['profpic1']) == false)) {
        $bcg_ = 'http://eveuwu.g6.cz/memes/bcg_s/clownmeme.jpg';
        $pic_bcg =  imagecreatefromstring(file_get_contents($bcg_));
        $pic_clw =  imagecreatefromstring(file_get_contents($clown_));
        $res_to_x = 278;
        $res_to_y = 155;
        $resized_pic = imagescale($pic_clw, $res_to_x, $res_to_y);
        $bcg_x = 1;
        $bcg_y = 500;
        $grl_x = 0;
        $grl_y = 0;
        $alpha = 100;
        imagecopymerge($pic_bcg, $resized_pic, $bcg_x, $bcg_y, $grl_x, $grl_y, $res_to_x, $res_to_y, $alpha);
        header('Content-Type: image/png');
        imagepng($pic_bcg);
        imagedestroy($pic_bcg);
        imagedestroy($pic_clw);
        /*memory cleared*/
    }
}
function SpiderMan($user_)
{
    if ((assert($_GET['profpic1']) && empty($_GET['profpic1']) == false)) {
        $bcg_ = 'http://eveuwu.g6.cz/memes/bcg_s/spidermanmeme.jpg';
        $pic_bcg =  imagecreatefromstring(file_get_contents($bcg_));
        $pic_clw =  imagecreatefromstring(file_get_contents($user_));
        $res_to_x = 140;
        $res_to_y = 140;
        $resized_pic = imagescale($pic_clw, $res_to_x, $res_to_y);
        $bcg_x = 640;
        $bcg_y = 70;
        $grl_x = 0;
        $grl_y = 0;
        $alpha = 100;
        imagecopymerge($pic_bcg, $resized_pic, $bcg_x, $bcg_y, $grl_x, $grl_y, $res_to_x, $res_to_y, $alpha);
        header('Content-Type: image/png');
        imagepng($pic_bcg);
        imagedestroy($pic_bcg);
        imagedestroy($pic_clw);
        /*memory cleared*/
    }
}
function SpongeBob_Burn($user_)
{
    if ((assert($_GET['profpic1']) && empty($_GET['profpic1']) == false)) {
        $bcg_ = 'https://www.tjtoday.org/wp-content/uploads/2021/01/IMG_7501.jpg';
        $pic_bcg =  imagecreatefromstring(file_get_contents($bcg_));
        $pic_user =  imagecreatefromstring(file_get_contents($user_));
        $res_to_x = 210;
        $res_to_y = 210;
        $resized_pic = imagescale($pic_user, $res_to_x, $res_to_y);
        $bcg_x = 59;
        $bcg_y = 120;
        $grl_x = 0;
        $grl_y = 0;
        $alpha = 100;
        imagecopymerge($pic_bcg, $resized_pic, $bcg_x, $bcg_y, $grl_x, $grl_y, $res_to_x, $res_to_y, $alpha);
        header('Content-Type: image/png');
        imagepng($pic_bcg);
        imagedestroy($pic_bcg);
        imagedestroy($pic_user);
        /*memory cleared*/
    }
}
function Smoking_while_pregnant($user_)
{
    if ((assert($_GET['profpic1']) && empty($_GET['profpic1']) == false)) {
        $bcg_ = 'http://eveuwu.g6.cz/memes/bcg_s/smoking_while_pregnant.jpg';
        $pic_bcg =  imagecreatefromstring(file_get_contents($bcg_));
        $pic_user =  imagecreatefromstring(file_get_contents($user_));
        $res_to_x = 450;
        $res_to_y = 450;
        $resized_pic = imagescale($pic_user, $res_to_x, $res_to_y);
        $bcg_x = (imagesx($pic_bcg) / 2) - ($res_to_x / 2);
        $bcg_y = 480;
        $grl_x = 0;
        $grl_y = 0;
        $alpha = 100;
        imagecopymerge($pic_bcg, $resized_pic, $bcg_x, $bcg_y, $grl_x, $grl_y, $res_to_x, $res_to_y, $alpha);
        header("Content-type: image/jpeg");
        $color = imagecolorallocate($pic_bcg, 255, 255, 0);
        $string = "19 years later...";
        $fontSize = 120;
        $x = $bcg_x;
        $y = $bcg_y + 420;
        imagestring($pic_bcg, $fontSize, $x, $y, $string, $color);
        header('Content-Type: image/png');
        imagepng($pic_bcg);
        imagedestroy($pic_bcg);
        imagedestroy($pic_user);
        /*memory cleared*/
    }
}
function Distracted_BoyFriend($girl_)
{
    if ((assert($_GET['profpic1']) && empty($_GET['profpic1']) == false)) {
        $bcg_ = 'https://i.ibb.co/KXxq5DP/Distracted-Boyfriend.jpg';
        $pic_bcg =  imagecreatefromstring(file_get_contents($bcg_));
        $pic_grl =  imagecreatefromstring(file_get_contents($girl_));
        $res_to_x = 183;
        $res_to_y = 183;
        $resized_pic = imagescale($pic_grl, $res_to_x, $res_to_y);
        $bcg_x = 250;
        $bcg_y = 217;
        $grl_x = 0;
        $grl_y = 0;
        $alpha = 100;
        imagecopymerge($pic_bcg, $resized_pic, $bcg_x, $bcg_y, $grl_x, $grl_y, $res_to_x, $res_to_y, $alpha);
        header('Content-Type: image/png');
        imagepng($pic_bcg);
        imagedestroy($pic_bcg);
        imagedestroy($pic_grl);
        /*memory cleared*/
    }
}
function But_That_Thing_It_Scares_Me($user_)
{
    if ((assert($_GET['profpic1']) && empty($_GET['profpic1']) == false)) {
        $bcg_ = 'https://i.kym-cdn.com/photos/images/original/001/957/289/47d.png';
        $pic_bcg =  imagecreatefromstring(file_get_contents($bcg_));
        $pic_user =  imagecreatefromstring(file_get_contents($user_));
        $res_to_x = 400;
        $res_to_y = 392;
        $resized_pic = imagescale($pic_user, $res_to_x, $res_to_y);
        $bcg_x = 400;
        $bcg_y = 780;
        $grl_x = 0;
        $grl_y = 0;
        $alpha = 100;
        imagecopymerge($pic_bcg, $resized_pic, $bcg_x, $bcg_y, $grl_x, $grl_y, $res_to_x, $res_to_y, $alpha);
        header('Content-Type: image/png');
        imagepng($pic_bcg);
        imagedestroy($pic_bcg);
        imagedestroy($pic_user);
        /*memory cleared*/
    }
}
//https://i.imgflip.com/bgte8.jpg
function a_Star_Was_Born($user_)
{
    if ((assert($_GET['profpic1']) && empty($_GET['profpic1']) == false)) {
        $bcg_ = 'https://i.imgflip.com/bgte8.jpg';
        $pic_bcg =  imagecreatefromstring(file_get_contents($bcg_));
        $pic_user =  imagecreatefromstring(file_get_contents($user_));
        $res_to_x = 80;
        $res_to_y = 80;
        $resized_pic = imagescale($pic_user, $res_to_x, $res_to_y);
        $bcg_x = 200;
        $bcg_y = 173;
        $grl_x = 0;
        $grl_y = 0;
        $alpha = 100;
        imagecopymerge($pic_bcg, $resized_pic, $bcg_x, $bcg_y, $grl_x, $grl_y, $res_to_x, $res_to_y, $alpha);
        header('Content-Type: image/png');
        imagepng($pic_bcg);
        imagedestroy($pic_bcg);
        imagedestroy($pic_user);
        /*memory cleared*/
    }
}
