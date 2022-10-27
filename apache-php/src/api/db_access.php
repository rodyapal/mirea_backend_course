<?php
require_once '../utils.php';

function read_from(
	string $table,
	$columns,
	$values
): string
{
	if (count($columns) == 0) {
		$query = "select * from " . $table;
	}
	else {
		$query = "select * from " . $table . " where ";
		for ($i = 0; $i < count($columns); $i++) {
			$query .= $columns[$i] . " = '" . $values[$i] . "'";
			if ($i < count($columns) - 1) $query .= " and ";
		}
	}
	$mysqli = openMysqli();
	$result = $mysqli->query($query);
	$mysqli->close();
	return json_encode($result->fetch_all(MYSQLI_ASSOC));
}

function delete_from(
	string $table,
	$id
): bool
{
	$mysqli = openMysqli();
	$result = $mysqli->query("delete from " . $table . " where " . id . " = " . $id);
	$mysqli->close();
	return $result;
}

function update_into(
	string $table,
	$id,
	$columns,
	$values
): bool
{
	$query = "update " . $table . " set ";
	for ($i = 0; $i < count($columns); $i++) {
		$query .= $columns[$i] . " = '" . $values[$i] . "'";
		if ($i < count($columns) - 1) $query .= ", ";
	}
	$query .= " where " . id . " = " . $id;
	$mysqli = openMysqli();
	$result = $mysqli->query($query);
	$mysqli->close();
	return $result;
}

function insert_into(
	string $table,
	$columns,
	$values
): bool
{
	$query = "insert into " . $table . " (";
	for ($i = 0; $i < count($columns); $i++) {
		$query .= $columns[$i];
		if ($i < count($columns) - 1) $query .= ", ";
	}
	$query .= ") values (";
	for ($i = 0; $i < count($values); $i++) {
		if (is_string($values[$i])) $query .= "'" . $values[$i] . "'";
		else $query .= $values[$i];
		if ($i < count($values) - 1) $query .= ", ";
	}
	$query .= ")";
	$mysqli = openMysqli();
	$result = $mysqli->query($query);
	$mysqli->close();
	return $result;
}