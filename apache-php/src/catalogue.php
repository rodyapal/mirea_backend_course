<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Catalogue</title>
	<style>
        span { margin: 10px; }
        .list {
            display: flex;
            flex-direction: column;
        }
        .item {
            display: flex;
            flex-direction: row;
            cursor: pointer;
        }
        .item:hover { background-color: cadetblue; }
	</style>
</head>
<body>
<h1>Catalogue</h1>
<?php
require_once 'utils.php';
$mysqli = openMysqli();
$result = $mysqli->query("select * from " . valuables);
?>
<div class="list"><?php if ($result->num_rows > 0) foreach ($result as $valuable) echo <<<A
            <div
                onclick="window.location.replace('view.php?id={$valuable[id]}')" 
                class="item">
                <span>{$valuable[title]}</span>
                <span>{$valuable[description]}</span>
                <span>{$valuable[cost]}</span>
            </div>
        A; else echo 'Empty'; ?></div>
</body>
</html>