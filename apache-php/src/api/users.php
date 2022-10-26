<?php
require_once 'db_access.php';

switch ($_SERVER['REQUEST_METHOD']) {
	case 'GET':
		$params = array_filter($_GET, "filter_user_get", ARRAY_FILTER_USE_KEY);
		if (count($params) == 0 || count($params) > 3) {
			http_response_code(400);
			echo "Bad request: invalid request params";
			return;
		}
		read_from(users, array_keys($params), array_values($params));
		break;
	case 'POST':
		$params = array_filter($_POST, "filter_user_post", ARRAY_FILTER_USE_KEY);
		if (count($params) != 2) {
			http_response_code(400);
			echo "Bad request: invalid request params";
			return;
		}
		insert_into(users, array_keys($params), array_values($params));
		break;
	case 'PATCH':
		parse_str(file_get_contents('php://input'), $_PATCH);
		$params = array_filter($_PATCH, "filter_user_get_patch", ARRAY_FILTER_USE_KEY);
		if (!array_key_exists('id', $params) || count($params) == 0 || count($params) > 3) {
			http_response_code(400);
			echo "Bad request: invalid request params";
			return;
		}
		update_into(users, $params['id'], array_keys($params), array_values($params));
		break;
	case 'DELETE':
		parse_str(file_get_contents('php://input'), $_DELETE);
		if (!array_key_exists('id', $_DELETE) || count($_DELETE) != 1) {
			http_response_code(400);
			echo "Bad request: invalid request params";
			return;
		}
		delete_from(users, $_DELETE['id']);
		break;
}

function filter_user_get_patch($key): bool {
	return $key == 'id' || $key == 'username' || $key == 'password';
}
function filter_user_post($key): bool {
	return $key == 'username' || $key == 'password';
}