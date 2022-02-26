<?php
//getAndDo($_GET['rnd']);
if (assert($_GET['profpic1'])) {
    $arg = $_GET['profpic1'];

    switch ($_GET['rnd']) {
        case 1:
            Distracted_BoyFriend($arg);
            break;
        case 2:
            But_That_Thing_It_Scares_Me($arg);
            break;
        default:
            echo 'err';
            break;
    }
}





/*
http://eveuwu.g6.cz/memes/But_That_Thing_It_Scares_Me_meme.php?&profpic1=https://cdn.discordapp.com/avatars/348358747825111040/72cd3210b9272374c0cd844c8242816a.png
*/

function Distracted_BoyFriend($girl_)
{


    if ((assert($_GET['profpic1']) && empty($_GET['profpic1']) == false)) {
        $bcg_ = 'https://i.ibb.co/KXxq5DP/Distracted-Boyfriend.jpg';

        $pic_bcg =  imagecreatefromstring(file_get_contents($bcg_));
        $pic_grl =  imagecreatefromstring(file_get_contents($girl_));


        list($bcg_width, $bcg_height) = getimagesize($bcg_);
        list($grl_width, $grl_height) = getimagesize($girl_);

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


        list($bcg_width, $bcg_height) = getimagesize($bcg_);
        list($grl_width, $grl_height) = getimagesize($user_);

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
