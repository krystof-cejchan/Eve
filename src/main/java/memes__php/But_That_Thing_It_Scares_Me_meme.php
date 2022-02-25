<?php
interface IMemes
{
    public function mergeImg($user);
}
/*
http://eveuwu.g6.cz/memes/But_That_Thing_It_Scares_Me_meme.php?&profpic1=https://cdn.discordapp.com/avatars/348358747825111040/72cd3210b9272374c0cd844c8242816a.png
*/
class But_That_Thing_It_Scares_Me_meme implements IMemes{

    public function mergeImg($user_)
{
    $bcg_ = 'https://i.kym-cdn.com/photos/images/original/001/957/289/47d.png';

    $pic_bcg =  imagecreatefromstring(file_get_contents($bcg_));
    $pic_user =  imagecreatefromstring(file_get_contents($user_));


    list($bcg_width, $bcg_height) = getimagesize($bcg_);
    list($grl_width, $grl_height) = getimagesize($user_);

    $res_to_x = 400;
    $res_to_y =392;
    $resized_pic =imagescale($pic_user , $res_to_x , $res_to_y);

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



?>

   







