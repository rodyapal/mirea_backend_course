<?php

class UnixProcessor
{
	private const LS = 'ls';
	private const PS = 'ps';
	private const WHOAMI = 'whoami';
	private const ID = 'id';
	private const PWD = 'pwd';
	private const UNAME_A = 'uname -a';

	public function __construct(string $command) {
		$result = match ($command) {
			self::LS, self::PS, self::WHOAMI, self::ID, self::PWD, self::UNAME_A => $this->exec($command),
			default => throw new Exception('Unsupported operation')
		};
		if ($result == null) throw new Exception();
		echo $result;
	}

	private function exec(string $command): string | null {
		exec($command, $output, $status);
		return implode(' ', $output);
	}
}