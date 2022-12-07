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
require_once "faker.php";
require_once "data_load.php";

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
require_once "watermark.php";
require_once "plot_bar.php";
require_once "plot_pie.php";
require_once "plot_scatter.php";

draw_plot_pie();
draw_plot_bar();
draw_plot_scatter();

$images = array("images/plot_bar.png", "images/plot_pie.png", "images/plot_scatter.png");

foreach ($images as $image) {
    add_watermark($image);
    echo "<img src=\"$image\">";
}
?>
</body>
</html>