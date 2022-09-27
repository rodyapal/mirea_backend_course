<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Drawer</title>
</head>
<body>
<?php
include_once 'ShapeDrawer.php';
$shapeData = $_GET['shape'];

if (!is_numeric($shapeData))
	echo 'Url parameter must be an uint32';
$drawer = new ShapeDrawer($shapeData);
$drawer->drawShape();
?>
</body>
</html>