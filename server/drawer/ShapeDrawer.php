<?php

//  Test data:
//  Circles:
//      1 0b00111011111001111111111100101101 | 1005059885
//      2 0b00101100111111111001111011111000 | 754949880
//      3 0b00111111111001111111101101110111 | 1072167799
//  Rectangles
//      1 0b01111011111001111111111100101101 | 2078801709
//      2 0b01101100111111111001111011111001 | 1828691705
//      3 0b01111111111001111011111101110110 | 2145894262
//  Triangles
//      1 0b10111011111001100001111100101101 | 3152420653
//      2 0b10101100111111111111111011111000 | 2902458104
//      3 0b11111111111001111111101101110110 | 4293393270

class ShapeDrawer
{
	private const MASK_SHAPE_TYPE = 0b11000000000000000000000000000000;
	private const MASK_WIDTH =      0b00111000000000000000000000000000;
	private const MASK_HEIGHT =     0b00000111000000000000000000000000;
	private const MASK_COLOR =	0b00000000111111111111111111111111;

	private $shapeType;
	private $shapeColor;
	private $shapeWidth;
	private $shapeHeight;

	public function __construct($shapeData) {
		$this->shapeWidth = (($shapeData & self::MASK_WIDTH) >> 27) * 100;
		$this->shapeHeight = (($shapeData & self::MASK_HEIGHT) >> 24) * 100;
		$this->shapeColor = dechex($shapeData & self::MASK_COLOR);
		$this->shapeType = ($shapeData & self::MASK_SHAPE_TYPE) >> 29;
	}

	public function drawShape() {
		$center = min($this->shapeWidth, $this->shapeHeight) / 2;
		$shapeSvg = match ($this->shapeType) {
			0 => "<circle cx='$center' cy='$center' r='$center' fill='#$this->shapeColor'/>",
			1 => "<rect width='$this->shapeWidth' height='$this->shapeHeight' fill='#$this->shapeColor'/>",
			default => "<polygon  points='0,0 $this->shapeWidth,$this->shapeHeight $this->shapeWidth,0' fill='#$this->shapeColor'/>"
		};
		echo "<svg width='$this->shapeWidth' height='$this->shapeHeight'> $shapeSvg </svg>";
	}
}
