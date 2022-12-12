<?php

namespace stats;
class FakerDataInstance
{
	public string $name;
	public string $full_name;
	public string $email;
	public string $gender;
	public string $bloodType;

	public function __construct(
		$name,
		$full_name,
		$email,
		$gender,
		$bloodType
	)
	{
		$this->name = $name;
		$this->full_name = $full_name;
		$this->email = $email;
		$this->gender = $gender;
		$this->bloodType = $bloodType;
	}
}