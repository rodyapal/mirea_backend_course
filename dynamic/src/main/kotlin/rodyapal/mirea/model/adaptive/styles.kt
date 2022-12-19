package rodyapal.mirea.model.adaptive

const val STYLE_DARK = """
	body {
		--text-color: #eee;
		--bkg-color: #121212;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
	}
	
	* {
		font-family: Arial, Helvetica, sans-serif;
	}
	
	button {
		margin: 10px;
		height: 40px;
		border: 0;
		width: 200px;
		outline: none;
		background: white;
		color: black;
	}
	
	input {
		height: 40px;
		width: 200px;
	}
	
	body {
		background: var(--bkg-color);
	}
	
	h1,
	p, label {
		color: var(--text-color);
	}
"""

const val STYLE_LIGHT = """
	body {
		--text-color: #222;
		--bkg-color: #fff;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
	}
	
	* {
		font-family: Arial, Helvetica, sans-serif;
	}
	
	button {
		margin: 10px;
		height: 40px;
		border: 0;
		width: 200px;
		outline: none;
		background: blue;
		color: white;
	}
	
	input {
		height: 40px;
		width: 200px;
	}
	
	body {
		background: var(--bkg-color);
	}
	
	h1,
	p, label {
		color: var(--text-color);
	}
"""