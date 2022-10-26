<?php
require_once 'db_access.php';

switch ($_SERVER['REQUEST_METHOD']) {
	case 'GET':
		$params = array_filter($_GET, "filter_valuables_get", ARRAY_FILTER_USE_KEY);
		if (count($params) == 0 || count($params) > 3) {
			http_response_code(400);
			echo "Bad request: invalid request params";
			return;
		}
		read_from(valuables, array_keys($params), array_values($params));
		break;
	case 'POST':
		$params = array_filter($_POST, "filter_valuables_post_patch", ARRAY_FILTER_USE_KEY);
		if (count($params) != 3) {
			http_response_code(400);
			echo "Bad request: invalid request params";
			return;
		}
		insert_into(valuables, array_keys($params), array_values($params));
		break;
	case 'PATCH':
		parse_str(file_get_contents('php://input'), $_PATCH);
		$params = array_filter($_PATCH, "filter_valuables_post_patch", ARRAY_FILTER_USE_KEY);
		if (!array_key_exists('id', $params) || count($params) == 0 || count($params) > 3) {
			http_response_code(400);
			echo "Bad request: invalid request params";
			return;
		}
		update_into(valuables, $params['id'], array_keys($params), array_values($params));
		break;
	case 'DELETE':
		parse_str(file_get_contents('php://input'), $_DELETE);
		if (!array_key_exists('id', $_DELETE) || count($_DELETE) != 1) {
			http_response_code(400);
			echo "Bad request: invalid request params";
			return;
		}
		delete_from(valuables, $_DELETE['id']);
		break;
}

function filter_valuables_get($key): bool {
	return $key == 'id' || $key == 'title' || $key == 'cost' || $key == 'description';
}
function filter_valuables_post_patch($key): bool {
	return $key == 'title' || $key == 'cost' || $key == 'description';
}