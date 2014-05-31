package com.xinlan.crystal.role;

import java.util.HashSet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.xinlan.crystal.Resource;
import com.xinlan.crystal.screen.GameScreen;

public final class CoreData {
	public static int PAD = 15;// 边距值
	public static int CUBE = 75;// 色块大小

	public static final int TYPE_NUM = 4;// 类型数量
	public static final int BLUE = 1;// 兰
	public static final int RED = 2;// 红
	public static final int YELLOW = 3;// 黄
	public static final int PINK = 4;// 粉
	public static final int CUBE_WIDTH = 75;// 宽度
	public static final int CUBE_HEIGHT = 60;// 高度
	public static final int CUBE_BORN_Y = 150;

	public static final int STATUS_NORMAL = 1;
	public static final int STATUS_GROWING = 2;
	public int status = STATUS_NORMAL;

	public static final int rowNum = 11;// 行数
	public static final int colNum = 6;// 列数
	
	public static final int SEED=100;

	private GameScreen context;
	public TextureRegion blueTexture;
	public TextureRegion redTexture;
	public TextureRegion yellowTexture;
	public TextureRegion pinkTexture;

	private final Pool<Pos> pointPool = new Pool<Pos>(100, 200) {
		@Override
		protected Pos newObject() {
			return new Pos();
		}
	};

	private Array<Pos> pathPoint = new Array<Pos>();// 计算路径值 存贮容器
	private HashSet<Integer> recordVistPointSet = new HashSet<Integer>();// 记录访问过得节点

	public static float Dump_Grow_Span = 311.5f;// 产生方块毫秒间隔

	private float growDy = 6;
	private float growY = 0;
	private float growDx = 5;
	private float growX = 0;

	public int[][] data = {// 主运算矩阵
	{ 0, 0, 0, 0, 0, 0 },//
			{ 0, 0, 0, 0, 0, 0 },//
			{ 0, 0, 0, 0, 0, 0 },//
			{ 0, 0, 0, 0, 0, 0 },//
			{ 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 0 },//
			{ 0, 0, 0, 0, 0, 0 },//
			{ 0, 0, 0, 0, 0, 0 },//
			{ 0, 0, 0, 0, 0, 0 },//
			{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };

	private int[] temp = new int[colNum];// 临时存贮单行数组变量
	private float countTime = 0;// 计数器

	private float xx;// debug

	public CoreData(GameScreen context) {
		this.context = context;

		blueTexture = Resource.getInstance().blueTextureRegion;
		redTexture = Resource.getInstance().redTextureRegion;
		yellowTexture = Resource.getInstance().yellowTextureRegion;
		pinkTexture = Resource.getInstance().pinkTextureRegion;

		// genBottomOneRow();
	}

	public void genBottomOneRow() {
		for (int i = 0; i < colNum; i++) {
			temp[i] = MathUtils.random(1, TYPE_NUM);
		}// end for i
		for (int j = rowNum - 2; j >= 1; j--) {
			System.arraycopy(data[j - 1], 0, data[j], 0, colNum);
		}// end for
		System.arraycopy(temp, 0, data[0], 0, colNum);
	}

	private void showDataNormal(SpriteBatch batch) {
		int startX = PAD;
		int startY = GameScreen.SC_HEIGHT - CUBE_BORN_Y;
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				drawCube(batch, data[i][j], startX, startY, CUBE_WIDTH,
						CUBE_HEIGHT);
				startX += CUBE_WIDTH;
			}// end for j
			startX = PAD;
			startY -= CUBE_HEIGHT;
		}// end for i
	}

	private void showDataGrowing(SpriteBatch batch) {
		int startX = PAD;
		int startY = GameScreen.SC_HEIGHT - CUBE_BORN_Y;
		for (int i = 0; i < rowNum; i++) {
			if (i == 0) {
				float drawY = startY + CUBE_HEIGHT - growY;
				for (int j = 0; j < colNum; j++) {
					float drawX = startX + CUBE_WIDTH / 2 - growX;
					drawCube(batch, data[i][j], drawX, drawY, 2 * growX, growY);
					startX += CUBE_WIDTH;
				}// end for j
				startX = PAD;
				startY -= growY;
			} else {
				for (int j = 0; j < colNum; j++) {
					drawCube(batch, data[i][j], startX, startY, CUBE_WIDTH,
							CUBE_HEIGHT);
					startX += CUBE_WIDTH;
				}// end for j
				startX = PAD;
				startY -= CUBE_HEIGHT;
			}
		}// end for i
	}

	/**
	 * 绘制方块
	 * 
	 * @param batch
	 * @param type
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 */
	private void drawCube(SpriteBatch batch, int type, float startX,
			float startY, float width, float height) {
		switch (type) {
		case BLUE:
			batch.draw(blueTexture, startX, startY, width, height);
			break;
		case RED:
			batch.draw(redTexture, startX, startY, width, height);
			break;
		case YELLOW:
			batch.draw(yellowTexture, startX, startY, width, height);
			break;
		case PINK:
			batch.draw(pinkTexture, startX, startY, width, height);
			break;
		}
	}

	public void draw(SpriteBatch batch, float delta) {
		switch (status) {
		case STATUS_NORMAL:// 正常状态
			showDataNormal(batch);
			countTime += delta;
			if (countTime >= Dump_Grow_Span)// 产生新的一行
			{
				countTime = 0;
				genBottomOneRow();
				status = STATUS_GROWING;
			}
			break;
		case STATUS_GROWING:// 增长状态
			showDataGrowing(batch);
			if (growY >= CUBE_HEIGHT) {
				growY = 0;
				status = STATUS_NORMAL;
				growX = 0;
			} else {
				growY += growDy;
				if (growX < CUBE_WIDTH >> 1) {
					growX += growDx;
				} else {
					growX = CUBE_WIDTH >> 1;
				}
			}
			break;
		}// end switch
	}

	/**
	 * 根据指定行 计算出当前行的最大y坐标值
	 * 
	 * @param col
	 * @return
	 */
	public int canStayPosYFromCol(int col) {
		int layer = 0;
		for (int i = rowNum - 1; i >= 0; i--) {
			if (data[i][col] != 0) {// 不为空
				layer = i + 1;
				break;
			}
		}// end for i

		context.addDump.nextRowValue = layer;
		return GameScreen.SC_HEIGHT - CUBE_BORN_Y - layer * CUBE_HEIGHT;
	}

	/**
	 * 更新矩阵 计算联通路径
	 */
	public void updateMatrix(int pointRow, int pointCol) {
		clearPathPoint();
		int value = data[pointRow][pointCol];// 取出触发点的值
		Pos point = pointPool.obtain();
		point.row = pointRow;// 记录行值
		point.col = pointCol;// 记录列值
		pathPoint.add(point);// 记录第一个点
		
		int setValue = pointRow*SEED+pointCol;
		recordVistPointSet.add(setValue);//将更新点 加入记录列表中
		
		calPath(pointRow,pointCol,value);//开始递归搜索联通路径
		
		System.out.println(pathPoint.size);
	}

	private void calPath(int row, int col, int value) {
		int originRowValue = row;
		int originColValue = col;
		// 向左搜索'
		int left = originColValue - 1;
		if (left >= 0) {
			int setValue = originRowValue * SEED + left;// 计算行列构成的唯一Set值
															// 用以判断记录表中是否已经有了记录
			if (!recordVistPointSet.contains(setValue))// 未在记录中
			{
				recordVistPointSet.add(setValue);// 加入记录中
				if (value == data[originRowValue][left]) {//路径联通
					Pos pos = pointPool.obtain();//记录点位置信息
					pos.row = originRowValue;
					pos.col = left;
					pathPoint.add(pos);
					calPath(originRowValue,left,value);//递归搜索
				}
			}
		}
		// 向上搜索
		int top = originRowValue - 1;
		if (top >= 0) {//在合法范围之内
			int setValue = top* SEED + originColValue;// 计算行列构成的唯一Set值
															// 用以判断记录表中是否已经有了记录
			if (!recordVistPointSet.contains(setValue))// 未在记录中
			{
				recordVistPointSet.add(setValue);// 加入记录中
				if (value == data[top][originColValue]) {//路径联通
					Pos pos = pointPool.obtain();//记录点位置信息
					pos.row = top;
					pos.col = originColValue;
					pathPoint.add(pos);
					calPath(top,originColValue,value);//递归搜索
				}
			}
		}
		// 向右侧搜索
		int right = originColValue + 1;
		if (right < colNum) {//在合法范围内
			int setValue = originRowValue * SEED + right;// 计算行列构成的唯一Set值
															// 用以判断记录表中是否已经有了记录
			if (!recordVistPointSet.contains(setValue))// 未在记录中
			{
				recordVistPointSet.add(setValue);// 加入记录中
				if (value == data[originRowValue][right]) {//路径联通
					Pos pos = pointPool.obtain();//记录点位置信息
					pos.row = originRowValue;
					pos.col = left;
					pathPoint.add(pos);
					calPath(originRowValue,right,value);//递归搜索
				}
			}
		}
		//向下搜索
		int bottom = originRowValue + 1;
		if (bottom < rowNum) {//在合法范围之内
			int setValue = bottom* SEED + originColValue;// 计算行列构成的唯一Set值
															// 用以判断记录表中是否已经有了记录
			if (!recordVistPointSet.contains(setValue))// 未在记录中
			{
				recordVistPointSet.add(setValue);// 加入记录中
				if (value == data[bottom][originColValue]) {//路径联通
					Pos pos = pointPool.obtain();//记录点位置信息
					pos.row = bottom;
					pos.col = originColValue;
					pathPoint.add(pos);
					calPath(pos.row,pos.col,value);//递归搜索
				}
			}
		}
	}

	/**
	 * 清理路径记录点
	 */
	private void clearPathPoint() {
		pointPool.freeAll(pathPoint);// 清理路径记录点
		pathPoint.clear();
		recordVistPointSet.clear();// 清空记录访问数据结构
	}

	static class Pos {
		int row;
		int col;
	}
}// end class

