package platformer.coop.tilemap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import platformer.coop.controller.GameController;

public class TileMap {

	private int x;
	private int y;

	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;

	private int[][] map;

	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;

	private BufferedImage tileSet;
	private int numTilesAcross;
	private Tile[][] tiles;

	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;
	private static Pattern pattern = Pattern.compile(Pattern.quote("||"));

	public TileMap(int tileSize) {

		this.tileSize = tileSize;
		numRowsToDraw = GameController.HEIGHT / tileSize + 2;
		numColsToDraw = GameController.WIDTH / tileSize + 2;

	}

	public void loadTiles(String path) {
		try {
			tileSet = ImageIO.read(ClassLoader.getSystemResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		numTilesAcross = tileSet.getWidth() / tileSize;
		tiles = new Tile[2][numTilesAcross];

		BufferedImage subimage;

		for (int i = 0; i < numTilesAcross; i++) {
			subimage = tileSet.getSubimage(i * tileSize, 0, tileSize, tileSize);

			tiles[0][i] = new Tile(subimage, Tile.NORMAL);
			subimage = tileSet.getSubimage(i * tileSize, tileSize, tileSize,
					tileSize);
			tiles[1][i] = new Tile(subimage, Tile.BLOCKED);
		}

	}

	public void loadMap(String path) {

		InputStream stream = ClassLoader.getSystemResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));

		try {

			numRows = Integer.parseInt(br.readLine());
			numCols = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;

			for (int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] split = pattern.split(line);

				for (int col = 0; col < split.length; col++) {
					map[row][col] = Integer.parseInt(split[col]);
				}
			}

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;

		fixBounds();

		rowOffset = -this.y / tileSize;
		colOffset = -this.x / tileSize;
	}

	public void draw(Graphics2D g2d) {

		int tileNumber = 0;

		for (int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {

			if (row >= numRows)
				break;

			for (int col = colOffset; col < colOffset + numColsToDraw; col++) {

				if (col >= numCols)
					break;
				tileNumber = map[row][col];

				if (tileNumber == 0)
					continue;

				tileNumber = map[row][col];
				g2d.drawImage(getImageForTileNumber(tileNumber - 1), x + col
						* tileSize, y + row * tileSize, null);
			}
		}
	}

	private BufferedImage getImageForTileNumber(int tileNumber) {
		return tiles[getRowForTileNumber(tileNumber)][getColumnForTileNumber(tileNumber)]
				.getImage();
	}

	private void fixBounds() {
		if (x > xmax) {
			x = xmax;
		}

		if (x < xmin) {
			x = xmin;
		}
		if (y > ymax) {
			y = ymax;
		}

		if (y < ymin) {
			y = ymin;
		}
	}

	public int getType(int row, int column) {

		int tileNumber = map[row][column];

		int tileRow = getRowForTileNumber(tileNumber);
		int tileColumn = getColumnForTileNumber(tileNumber);

		return tiles[tileRow][tileColumn].getType();

	}

	private int getColumnForTileNumber(int tileNumber) {
		int tileColumn = tileNumber % numTilesAcross;
		return tileColumn;
	}

	private int getRowForTileNumber(int tileNumber) {
		int tileRow = tileNumber / numTilesAcross;
		return tileRow;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getXmin() {
		return xmin;
	}

	public void setXmin(int xmin) {
		this.xmin = xmin;
	}

	public int getYmin() {
		return ymin;
	}

	public void setYmin(int ymin) {
		this.ymin = ymin;
	}

	public int getXmax() {
		return xmax;
	}

	public void setXmax(int xmax) {
		this.xmax = xmax;
	}

	public int getYmax() {
		return ymax;
	}

	public void setYmax(int ymax) {
		this.ymax = ymax;
	}

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public int getNumCols() {
		return numCols;
	}

	public void setNumCols(int numCols) {
		this.numCols = numCols;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BufferedImage getTileSet() {
		return tileSet;
	}

	public void setTileSet(BufferedImage tileSet) {
		this.tileSet = tileSet;
	}

	public int getNumTilesAcross() {
		return numTilesAcross;
	}

	public void setNumTilesAcross(int numTilesAcross) {
		this.numTilesAcross = numTilesAcross;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	public int getRowOffset() {
		return rowOffset;
	}

	public void setRowOffset(int rowOffset) {
		this.rowOffset = rowOffset;
	}

	public int getColOffset() {
		return colOffset;
	}

	public void setColOffset(int colOffset) {
		this.colOffset = colOffset;
	}

	public int getNumRowsToDraw() {
		return numRowsToDraw;
	}

	public void setNumRowsToDraw(int numRowsToDraw) {
		this.numRowsToDraw = numRowsToDraw;
	}

	public int getNumColsToDraw() {
		return numColsToDraw;
	}

	public void setNumColsToDraw(int numColsToDraw) {
		this.numColsToDraw = numColsToDraw;
	}

}
