<?php
require_once 'db_access.php';
require_once '../utils.php';

switch ($_SERVER['REQUEST_METHOD']) {
	case 'GET':
		$params = array_filter($_GET, 'filter_user_get_patch', ARRAY_FILTER_USE_KEY);
		if (count($params) > 3) {
			http_response_code(400);
			echo "Bad request: invalid request params";
			return;
		}
		echo read_from(users, array_keys($params), array_values($params));
		break;
	case 'POST':
		$body = json_decode(file_get_contents('php://input'), true);
		$params = array_filter($body, 'filter_user_post', ARRAY_FILTER_USE_KEY);
		if (count($params) != 2) {
			http_response_code(400);
			echo "Bad request: invalid request params";
			return;
		}
		$params['password'] = base64_encode($params['password']);
		echo insert_into(users, array_keys($params), array_values($params));
		break;
	case 'PATCH':
		$body = json_decode(file_get_contents('php://input'), true);
		$params = array_filter($body, 'filter_user_get_patch', ARRAY_FILTER_USE_KEY);
		if (!array_key_exists('id', $params) || count($params) == 0 || count($params) > 3) {
			http_response_code(400);
			echo "Bad request: invalid request params";
			return;
		}
		if (array_key_exists('password', $params)) {
			$params['password'] = base64_encode($params['password']);;
		}
		echo update_into(users, $params['id'], array_keys($params), array_values($params));
		break;
	case 'DELETE':
		if (array_key_exists('id', getallheaders())) {
			echo delete_from(users, getallheaders()['id']);
		}
		else {
			http_response_code(400);
			echo "Bad request: invalid request params";
		}
		break;
}

function filter_user_get_patch($key): bool {
	return $key == 'id' || $key == 'name' || $key == 'password';
}
function filter_user_post($key): bool {
	return $key == 'name' || $key == 'password';
}