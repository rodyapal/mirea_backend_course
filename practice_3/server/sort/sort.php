<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Selection sort</title>
</head>
<body>
<?php
include_once 'selectionSort.php';
$array = selection_sort(explode(',', $_GET['array']));
foreach ($array as $item) {
    echo "$item ";
}
?>
</body>
</html>