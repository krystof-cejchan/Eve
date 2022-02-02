<html>   <button onclick="window.location.href='http://kys.hys.cz/';">
         <span>Domů</span> 
    </button><center>


<?php
echo('<h1>Malá násobilka pomocí cyklu</h1>');
echo('<table border="1"><tr>');
for ($i = 1; $i <= 10; $i++)
    echo('<td>' . $i . '</td>');
echo('</tr><tr>');
for ($i = 1; $i <= 10; $i++)
    echo('<td>' . ($i * 2) . '</td>');
echo('</tr><tr>');
for ($i = 1; $i <= 10; $i++)
    echo('<td>' . ($i * 3) . '</td>');
echo('</tr><tr>');
for ($i = 1; $i <= 10; $i++)
    echo('<td>' . ($i * 4) . '</td>');
echo('</tr><tr>');
for ($i = 1; $i <= 10; $i++)
    echo('<td>' . ($i * 5) . '</td>');
echo('</tr><tr>');
for ($i = 1; $i <= 10; $i++)
    echo('<td>' . ($i * 6) . '</td>');
echo('</tr><tr>');
for ($i = 1; $i <= 10; $i++)
    echo('<td>' . ($i * 7) . '</td>');
echo('</tr><tr>');
for ($i = 1; $i <= 10; $i++)
    echo('<td>' . ($i * 8) . '</td>');
echo('</tr><tr>');
for ($i = 1; $i <= 10; $i++)
    echo('<td>' . ($i * 9) . '</td>');
echo('</tr><tr>');
for ($i = 1; $i <= 10; $i++)
    echo('<td>' . ($i * 10) . '</td>');
echo('</tr></table>');

?>
</center>
</html>