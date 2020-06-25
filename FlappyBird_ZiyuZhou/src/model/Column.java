package model;

import java.util.Random;

/**
 * the column
 * @author ziyuzhou
 */
public class Column {
	
	private int columnX;
	private int columnY;
	private int columnIndex;
	Random r = new Random();
	
	public final static int DISTANCE = 432/2 + 78/2;
	public final static int COLUMNWIDTH = 78;
	public final static int COLUMNHEIGHT = 528;
	public final static int GAP = 144;
	
	
	public Column(int n) {
		columnX = DISTANCE*(n-1) + 432;
		columnY = r.nextInt(270)-470;
		columnIndex = n;
	}
	
	public void step(){
		columnX--;
		if(columnX <= -COLUMNWIDTH){
			columnX = 432;
			columnY = r.nextInt(270)-470;
		}
	}

	public int getColumnX() {
		return columnX;
	}


	public int getColumnY() {
		return columnY;
	}

	public void reSetColumnX() {
		columnX = DISTANCE*(columnIndex - 1) + 432;
	}

}
