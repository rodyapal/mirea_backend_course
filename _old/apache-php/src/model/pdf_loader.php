<?php
$upload_dir = '/var/www/html-dynamic/pdf/files/';
$upload_file = $upload_dir . basename($_FILES['userfile']['name']);

echo '<pre>';
setlocale(LC_ALL,'en_US.UTF-8');

$temp_file = $_FILES['userfile']['tmp_name'];
$handle = fopen($temp_file, 'rb');
$content = fread($handle, filesize($temp_file));

if (!str_contains($content, '%PDF') ) {
    echo "Вы попытались загрузить не pdf файл";
} else {
    if (move_uploaded_file($_FILES['userfile']['tmp_name'], $upload_file)) {
        echo "Файл был успешно загружен.\n";
    } else {
        echo "Возможная атака с помощью файловой загрузки!\n";
    }
}
echo "</pre>";
?>
<a href="../view/pdf_files.php">К списку</a>
