<?php
require_once 'db_access.php';
require_once '../utils.php';

switch ($_SERVER['REQUEST_METHOD']) {
	case 'GET':
		$params = array_filter($_GET, "filter_valuables_get_patch", ARRAY_FILTER_USE_KEY);
		if (count($params) > 3) {
			http_response_code(400);
			echo "Bad request: invalid request params";
			return;
		}
		echo read_from(valuables, array_keys($params), array_values($params));
		break;
	case 'POST':
		$body = json_decode(file_get_contents('php://input'), true);
		$params = array_filter($body, "filter_valuables_post", ARRAY_FILTER_USE_KEY);
		if (count($params) != 3) {
			http_response_code(400);
			echo "Bad request: invalid request params";
			return;
		}
		echo insert_into(valuables, array_keys($params), array_values($params));
		break;
	case 'PATCH':
		$body = json_decode(file_get_contents('php://input'), true);
		$params = array_filter($body, "filter_valuables_get_patch", ARRAY_FILTER_USE_KEY);
		if (!array_key_exists('id', $params) || count($params) == 0 || count($params) > 3) {
			http_response_code(400);
			echo "Bad request: invalid request params" . implode(', ', $body);
			return;
		}
		echo update_into(valuables, $params['id'], array_keys($params), array_values($params));
		break;
	case 'DELETE':
		if (array_key_exists('id', getallheaders())) {
			echo delete_from(valuables, getallheaders()['id']);
		}
		else {
			http_response_code(400);
			echo "Bad request: invalid request params";
		}
		break;
}

function filter_valuables_get_patch($key): bool {
	return $key == 'id' || $key == 'title' || $key == 'cost' || $key == 'description';
}
function filter_valuables_post($key): bool {
	return $key == 'title' || $key == 'cost' || $key == 'description';
}