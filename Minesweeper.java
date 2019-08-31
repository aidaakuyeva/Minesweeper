import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

@SuppressWarnings("serial")
public class Minesweeper extends JPanel {
	private Cell[][] board;
	private boolean reset;
	private boolean flag;
	private int score;
	private boolean play;
	private boolean firstClick;

	public Dimension getPrefferedSize() {
		return new Dimension(900, 900);
	}
	
	public Minesweeper(String filename) {
		board = new Cell[9][9];
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				board[row][col] = new Cell();
			}
		}
	}
	
	//reset the game
	public void setReset(boolean reset) {
		this.reset = reset;
	}
	
	//flag mode
	public void changeFlag() {
		this.flag = !this.flag;
	}
	
	public void reset() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				board[row][col] = new Cell();
			}
		}
		flag = false;
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 0; col++) {
				Cell c = board[row][col];
				try {

				g.drawImage((new ImageIcon(c.getImage()).getImage()), 50 + (52 * row), 
						50 + (52 * col), 50, 50, null);
				} catch (Exception e) {
					
				}
			}
			
		}
	}
	
	public void generateRandomBombs() {
		for (int i = 1; i <= 10; i++) {
			int row = (int) Math.random() * 8;
			int col = (int) Math.random() * 8;

			while (board[row][col].getIfOpened() || board[row][col].containsMine()) {
				row = (int) Math.random() * 8;
				col = (int) Math.random() * 8;
			}

			board[row][col].setType(1);
		}
	}
	
	
	public void pressCell(int row, int col) {
		Cell c = board[row][col];
		
		if (!c.getIfOpened()) {
			
			//if in the flag mode
			if (flag) {
				c.setFlag(!c.getIfFlag());
			} else {
			if (c.containsMine()) {
				play = false;
				System.out.println("You lost");
			} else {
				c.setOpened();
				//reveal the neighboring cells
				for (int i = row - 1; i <= row + 1; i++) {
					for (int j = col - 1; j <= col + 1; j++) {
						if (!board[i][j].containsMine()) {
							board[i][j].setOpened();
						}
					}
				}
			}
			}
		}
	}
	
    public void minesNear() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                int count = 0;

                for (int i = x - 1; i <= x + 1; i++) {
                    if (i == -1 || i == 9) { 
                        continue;
                    }
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (j == -1 || j == 9) { 
                            continue;
                        }
                        if (board[i][j].containsMine()) {
                            count++;
                        }
                    }
                }
                board[x][y].setMineNeighbors(count);

            }
        }
    }
	
    public void firstPress(int col, int row) {
    	generateRandomBombs();
        minesNear(); 
        firstClick = false;
        pressCell(row, col);
    }
	
	
    public void game(){

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(play){
                    int col = (int) (e.getX() - 50) / 52;
                    int row = (int) (e.getY() - 180) / 52;
                    if(col < 0 || col > 8 || row < 0 || row > 8){
                        return;
                    } else if(firstClick){
                        firstPress(row, col);
                        firstClick = false;

                        repaint();
                    } else{
                        pressCell(col, row);
                        repaint();
                    }

                }

            }
        });

        play = true; 
    }
	
	
}
