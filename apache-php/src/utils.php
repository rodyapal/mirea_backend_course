<?php
const
host = 'mysql',
users = 'users',
name = 'name',
dbUser = 'user',
password = 'password',
db = 'appDb',
valuables = 'valuables',
id = 'ID',
title = 'title',
description = 'description',
cost = 'cost';

function openMysqli(): mysqli
{
	return new mysqli(
		host, dbUser, password, db
	);
}