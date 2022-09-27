<?php
include_once 'UnixProcessor.php';
try {
	new UnixProcessor(urldecode($_GET['prompt']));
} catch (Exception $e) {
	echo "Bad request: $e";
}