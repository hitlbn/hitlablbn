package p1;

import java.io.*;

public class lab01 {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		boolean mark = true;
		int n6 = 5;
		System.out.println("1.txt：");
		try {
			mark = isLegalMagicSquare("./src/p1/txt/1.txt");
			System.out.println(mark);
			System.out.println("2.txt：");
			mark = isLegalMagicSquare("./src/p1/txt/2.txt");
			System.out.println(mark);
			System.out.println("3.txt：");
			mark = isLegalMagicSquare("./src/p1/txt/3.txt");
			System.out.println(mark);
			System.out.println("4.txt：");
			mark = isLegalMagicSquare("./src/p1/txt/4.txt");
			System.out.println(mark);
			System.out.println("5.txt：");
			mark = isLegalMagicSquare("./src/p1/txt/5.txt");
			System.out.println(mark);
			System.out.println("生成6.txt");
			mark = generateMagicSquare(n6);
			System.out.println("6.txt：");
			mark = isLegalMagicSquare("./src/p1/txt/6.txt");
			System.out.println(mark);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	static boolean isLegalMagicSquare(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		Reader reader = new InputStreamReader(new FileInputStream(file));
		int temp, gint = 0, values1 = 0, values0 = 0;
		char chartemp;
		String temps = "00";
		int orderint[][] = new int[200][200];
		int i = 0, j = 0;
		try {
			while((temp = reader.read()) != -1) {
				chartemp = (char)temp;
				if(chartemp <= '9' && chartemp >= '0')
				{
					temps = temps + chartemp;
				}
				else if(chartemp == '\t')
				{
					orderint[i][j] = Integer.parseInt(temps);
					temps = "00";
					i++;
				}
				else if(chartemp == '\n')
				{
					
					orderint[i][j] = Integer.parseInt(temps);
					temps = "00";
					if(j == 0)
					{
						gint = i;
						i = 0;
					}
					else
					{
						if(i != gint)
						{
							System.out.println("格式错误！");
							return false;
						}
						i = 0;
					}
					j++;
				}
				else if(chartemp == '\r')
				{
					
				}
				else
				{
					System.out.println("格式错误！");
					return false;
				}
			}
			orderint[i][j] = Integer.parseInt(temps);
			if(gint != j)
			{
				System.out.println("格式错误！");
				return false;
			}
			reader.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		j = 0;
		for(i = 0; i <= gint; i++)
		{
			values0 = orderint[i][j] + values0;
		}
		while(j <= gint)
		{
			for(i = 0; i <= gint; i++)
			{
				values1 = orderint[i][j] + values1;
			}
			if(values1 != values0)
			{
				System.out.println("格式错误！");
				return false;
			}
			j++;
			values1 = 0;
		}
		i = 0;
		j = 0;
		values1 = 0;
		while(i <= gint)
		{
			for(j = 0; j <= gint; j++)
			{
				values1 = orderint[i][j] + values1;
			}
			if(values1 != values0)
			{
				System.out.println("格式错误！");
				return false;
			}
			i++;
			values1 = 0;
		}
		i = 0;
		j = 0;
		values1 = 0;
		while(i <= gint && j <= gint)
		{
			values1 = orderint[i][j] + values1;
			i++;
			j++;
		}
		if(values1 != values0)
		{
			System.out.println("格式错误！");
			return false;
		}
		return true;
	}
	
	public static boolean generateMagicSquare(int n) throws IOException {
		if(n % 2 == 0)
		{
			System.out.println("n为偶数，不合法！");
			return false;
		}
		else if(n <= 0)
		{
			System.out.println("n非正数，不合法！");
			return false;
		}
		int magic[][] = new int[n][n];//定义一个n*n二维整型数组magic存储magicsquare
		int row = 0, col = n / 2, i, j, square = n * n;//定义初始变量row,col,i.j
		for (i = 1; i <= square; i++) {
			magic[row][col] = i;//初始填充位置于第一行中间保证填充结束后其中间值于magicsquare中间位置
			if (i % n == 0)
				row++;//如果遇到行/列的倍数，向下一格填充，保证对同一行/列，其下一填充值对n余数与之前值均不同
			else {
				if (row == 0)
					row = n - 1;//如果行数减至0，则将行数修改为n-1
				else
					row--;//否则，行数减一
				if (col == (n - 1))
					col = 0;//如果列数达到最大n-1。则修改为0
				else
					col++;//否则列数加一
			}//填充方式每n个为一组，每组以对角线方向填充。遇顶则反弹
		}//通过以斜线方向填充，每n个一组，每组位置于上一组下方一格保证不会有属于同一组的两个数处于同一行或列，同时每一行列包含每一组的一个数且行列中每个数对n求余均不同。
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				System.out.print(magic[i][j] + "\t");//输出magicsquare
			System.out.println();
		}
		try {
			FileOutputStream op = new FileOutputStream("./src/p1/txt/6.txt", false);
			String txt6 = new String();
			for(i = 0; i < n; i++)
			{
				for(j = 0; j < n; j++)
				{
					txt6 = txt6 + magic[i][j];
					if(j == n - 1 && i != n - 1)
					{
						txt6 = txt6 + "\n";
					}
					else
					{
						txt6 = txt6 + "\t";
					}
				}
			}
			op.write(txt6.getBytes());
			op.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return true;//返回true，输出成功
	}
}
