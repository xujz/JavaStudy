// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Global.java

package cn.itcast.tetris.util;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

public class Global
{

	private static Properties properties;
	private static String CONFIG_FILE;
	public static final int CELL_WIDTH;
	public static final int CELL_HEIGHT;
	public static final int WIDTH;
	public static final int HEIGHT;
	public static final int DEFAULT_SPEED;
	public static int CURRENT_SPEED;
	public static final int SWIFT_SPEED;
	public static final int SPEED_STEP;
	public static final int DEFAULT_STAY_TIME;
	public static int STAY_TIME;
	private static Random random;
	public static final String TITLE_LABEL_TEXT;
	public static final String INFO_LABEL_TEXT;
	private static final Color DEFAULT_COLORS[];
	public static final java.util.List COMMON_COLORS;

	public static Color getRandomColor()
	{
		return DEFAULT_COLORS[random.nextInt(DEFAULT_COLORS.length)];
	}

	private Global()
	{
	}

	private static Integer getIntValue(String key)
	{
		if (key == null)
			throw new RuntimeException("key 不能为空");
		return new Integer(properties.getProperty(key));
		NumberFormatException e;
		e;
		return null;
	}

	private static String getValue(String key)
	{
		return new String(properties.getProperty(key).getBytes("iso8859-1"));
		Exception e;
		e;
		return null;
	}

	private static java.util.List loadColors()
	{
		java.util.List l = new ArrayList(7);
		for (int i = 0; i < 7; i++)
			l.add(null);

		Set set = properties.keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();)
		{
			String key = (String)iter.next();
			if ("1".equals(key.trim()))
				addColor(l, 0, getValue(key));
			else
			if ("2".equals(key.trim()))
				addColor(l, 1, getValue(key));
			else
			if ("3".equals(key.trim()))
				addColor(l, 2, getValue(key));
			else
			if ("4".equals(key.trim()))
				addColor(l, 3, getValue(key));
			else
			if ("5".equals(key.trim()))
				addColor(l, 4, getValue(key));
			else
			if ("6".equals(key.trim()))
				addColor(l, 5, getValue(key));
			else
			if ("7".equals(key.trim()))
				addColor(l, 6, getValue(key));
		}

		for (int i = 0; i < 7; i++)
			l.remove(null);

		if (l.size() < 1)
		{
			for (int i = 0; i < DEFAULT_COLORS.length; i++)
				l.add(DEFAULT_COLORS[i]);

		} else
		{
			if (l.size() != 7)
				System.out.println((new StringBuilder("您一共设置了 ")).append(l.size()).append(" 种有效颜色， 建议设置七种").toString());
			return l.subList(0, l.size() <= 7 ? l.size() : 7);
		}
		return l;
	}

	private static void addColor(java.util.List l, int index, String str)
	{
		str = str.trim();
		if (!str.startsWith("0x") || str.length() < 3)
		{
			System.out.println((new StringBuilder("颜色设置有误，请检查 : ")).append(str).append(" (key)").toString());
			return;
		}
		try
		{
			String strRGB = str.substring(2, str.length() < 8 ? str.length() : 8);
			int rgb = Integer.valueOf(strRGB, 16).intValue();
			Color c = new Color(rgb);
			if (c != null)
				l.add(index, c);
		}
		catch (Exception e)
		{
			System.out.println((new StringBuilder("(e)颜色设置有误，请检查:")).append(str).append("(key)").toString());
			e.printStackTrace();
			return;
		}
	}

	static 
	{
		InputStream inputStream;
		properties = new Properties();
		CONFIG_FILE = "tetris.ini";
		random = new Random();
		DEFAULT_COLORS = (new Color[] {
			new Color(0x990066), new Color(0x990099), new Color(0x330099), new Color(0x663300), new Color(39270), new Color(13107)
		});
		inputStream = null;
		inputStream = new FileInputStream(CONFIG_FILE);
		properties.load(inputStream);
		break MISSING_BLOCK_LABEL_200;
		FileNotFoundException e;
		e;
		System.out.println("没有配置文件");
		try
		{
			if (inputStream != null)
				inputStream.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		break MISSING_BLOCK_LABEL_216;
		e;
		e.printStackTrace();
		try
		{
			if (inputStream != null)
				inputStream.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		break MISSING_BLOCK_LABEL_216;
		Exception exception;
		exception;
		try
		{
			if (inputStream != null)
				inputStream.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		throw exception;
		try
		{
			if (inputStream != null)
				inputStream.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Integer temp = null;
		WIDTH = (temp = getIntValue("width")) == null || temp.intValue() > 80 || temp.intValue() < 10 ? 15 : temp.intValue();
		HEIGHT = (temp = getIntValue("height")) == null || temp.intValue() > 60 || temp.intValue() < 10 ? 20 : temp.intValue();
		DEFAULT_SPEED = CURRENT_SPEED = (temp = getIntValue("speed")) == null || temp.intValue() < 10 ? 300 : temp.intValue();
		SWIFT_SPEED = (temp = getIntValue("swift_speed")) == null || temp.intValue() < 0 ? 15 : temp.intValue();
		SPEED_STEP = (temp = getIntValue("speed_step")) == null || temp.intValue() < 1 ? 25 : temp.intValue();
		DEFAULT_STAY_TIME = STAY_TIME = (temp = getIntValue("stay_time")) == null || temp.intValue() < 0 ? 200 : temp.intValue();
		int defaultCellSize = (temp = getIntValue("cell_size")) == null || temp.intValue() <= 0 || temp.intValue() > 100 ? 23 : temp.intValue();
		CELL_WIDTH = (temp = getIntValue("cell_width")) == null || temp.intValue() <= 0 || temp.intValue() > 100 ? defaultCellSize : temp.intValue();
		CELL_HEIGHT = (temp = getIntValue("cell_height")) == null || temp.intValue() <= 0 || temp.intValue() > 100 ? defaultCellSize : temp.intValue();
		String tempStr = null;
		TITLE_LABEL_TEXT = (tempStr = getValue("title")) != null ? tempStr : "说明：";
		INFO_LABEL_TEXT = (tempStr = getValue("info")) != null ? tempStr : "方向键控制方向, 回车键暂停/继续\nPAGE UP, PAGE DOWN 加速或减速\n\n更多请看 www.itcast.cn ";
		COMMON_COLORS = loadColors();
	}
}
