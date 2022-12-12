<?php
session_start();
?>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <title>Графики и таблицы с данными</title>
    <link rel="stylesheet" href="/style.css" type="text/css"/>
</head>
<body>
<?php
require_once "../model/stats/faker.php";
require_once "../model/stats/data_load.php";

generate_data();
?>
<h1>User data</h1>
<table class="table" style="font-size: 30%">
    <tr>
        <th>Username</th>
        <th>Full name</th>
        <th>Email</th>
        <th>Gender</th>
    </tr>
    <?php
    $data = get_raw_data();

    foreach ($data as $data_row) {
        echo "<tr>";
        echo "<td>".$data_row->name."</td>";
        echo "<td>".$data_row->full_name."</td>";
        echo "<td>".$data_row->email."</td>";
        echo "<td>".$data_row->gender."</td>";
        echo "</tr>";
    }
    ?>
</table>

<br>
<?php
require_once "../model/stats/watermark.php";
require_once "../model/stats/plot_bar.php";
require_once "../model/stats/plot_pie.php";
require_once "../model/stats/plot_scatter.php";

draw_plot_pie();
draw_plot_bar();
draw_plot_scatter();

$images = array("../storage/images/plot_bar.png", "../storage/mages/plot_pie.png", "../storage/images/plot_scatter.png");

foreach ($images as $image) {
    add_watermark($image);
    echo "<img src=\"$image\">";
}
?>
</body>
</html>