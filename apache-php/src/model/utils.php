<?php
const
host = 'mysql',
users = 'users',
name = 'name',
dbUser = 'user',
password = 'password',
db = 'appDb',
valuables = 'valuables',
id = 'id',
title = 'title',
description = 'description',
cost = 'cost';

function openMysqli(): mysqli
{
	return new mysqli(
		host, dbUser, password, db
	);
}