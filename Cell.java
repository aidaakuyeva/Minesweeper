import javax.swing.*;

public class Cell {
	private int type; // 0 - empty, 1 - contains a mine, 2 - has a number
	private int x, y;
	private boolean flag;
	private boolean wasOpened;
	private String img;
	private int mineNeighbors; // number from 0 to 8

	//initial state
	public Cell() {
		this.wasOpened = false;
		getImage();
	}
	
	public Cell(boolean flag, boolean wasOpened, int mineNeighbors, int type) {
		this.flag = flag;
		this.wasOpened = wasOpened;
		this.mineNeighbors = mineNeighbors;
		this.type = type;
	    getImage();
	}

	public String getImage() {
		if (flag) {
			img = "/Users/aakuyeva/Minesweeper/files/Flag.png";
		} else if (wasOpened) {
			if (type == 2) {
				img = "/Users/aakuyeva/Minesweeper/files/minePress.png";
			} else if (mineNeighbors == 0) {
				img = "/Users/aakuyeva/Minesweeper/files/0.png";
			} else if (mineNeighbors == 1) {
				img = "/Users/aakuyeva/Minesweeper/files/1.png";
			} else if (mineNeighbors == 2) {
				img = "/Users/aakuyeva/Minesweeper/files/2.png";
			} else if (mineNeighbors == 3) {
				img = "/Users/aakuyeva/Minesweeper/files/3.png";
			} else if (mineNeighbors == 4) {
				img = "/Users/aakuyeva/Minesweeper/files/4.png";
			} else if (mineNeighbors == 5) {
				img = "/Users/aakuyeva/Minesweeper/files/5.png";
			} else if (mineNeighbors == 6) {
				img = "/Users/aakuyeva/Minesweeper/files/6.png";
			} else if (mineNeighbors == 7) {
				img = "/Users/aakuyeva/Minesweeper/files/7.png";
			} else if (mineNeighbors == 8) {
				img = "/Users/aakuyeva/Minesweeper/files/8.png";
			} else {
				img = "/Users/aakuyeva/Minesweeper/files/mine.png";
			}
		} else {
			img = "/Users/aakuyeva/Minesweeper/files/unrevealed.png";
		}
		
		return img;
	}
	
	//getters
	public int getMineNeighbors() {
		return this.mineNeighbors;
	}
	
	public String getAssociatedImg() {
		return this.img;
	}
	
	public boolean getIfFlag() {
		return this.flag;
	}
	
	public int getType() {
		return this.type;
	}
	
	public boolean getIfOpened() {
		return this.wasOpened;
	}
	
	public boolean containsMine() {
		return this.type == 1;
	}
	
	//setters - change the state of the cell
	public void setType(int type) {
		this.type = type;
		getImage();
	}
	
	public void setOpened() {
		this.wasOpened = true;
		getImage();
	}
	
	public void setFlag(boolean f) {
		flag = f;
		getImage();
	}
	
	
	public void setMineNeighbors(int mineNeighbors) {
		this.mineNeighbors = mineNeighbors;
		getImage();
	}
	
}
