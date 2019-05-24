package xin.ss.util;

/**
 * @author King
 *
 */
public class RankMangaer {
	private static final int GOOD=2;
	private static final int BAD=-2;
	private static final int SHARE=2;
	private static final int COMMENT=1;
	private static final int DELETE_SHARE=-2;
	private static final int PLUSFAN=10;
	private static final int MINUSFAN=-10;
	
	public static int getGood() {
		return GOOD;
	}
	public static int getBad() {
		return BAD;
	}
	public static int getShare() {
		return SHARE;
	}
	public static int getComment() {
		return COMMENT;
	}
	public static int getDeleteShare() {
		return DELETE_SHARE;
	}
	public static int getMinusfan() {
		return MINUSFAN;
	}
	public static int getPlusfan() {
		return PLUSFAN;
	}
	
	
}
