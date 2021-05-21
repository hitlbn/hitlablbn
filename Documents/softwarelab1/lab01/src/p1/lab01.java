package p1;

import java.io.*;

public class lab01 {

	public static void main(String[] args) throws IOException {
		// TODO �Զ����ɵķ������
		boolean mark = true;
		int n6 = 5;
		System.out.println("1.txt��");
		try {
			mark = isLegalMagicSquare("./src/p1/txt/1.txt");
			System.out.println(mark);
			System.out.println("2.txt��");
			mark = isLegalMagicSquare("./src/p1/txt/2.txt");
			System.out.println(mark);
			System.out.println("3.txt��");
			mark = isLegalMagicSquare("./src/p1/txt/3.txt");
			System.out.println(mark);
			System.out.println("4.txt��");
			mark = isLegalMagicSquare("./src/p1/txt/4.txt");
			System.out.println(mark);
			System.out.println("5.txt��");
			mark = isLegalMagicSquare("./src/p1/txt/5.txt");
			System.out.println(mark);
			System.out.println("����6.txt");
			mark = generateMagicSquare(n6);
			System.out.println("6.txt��");
			mark = isLegalMagicSquare("./src/p1/txt/6.txt");
			System.out.println(mark);
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
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
							System.out.println("��ʽ����");
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
					System.out.println("��ʽ����");
					return false;
				}
			}
			orderint[i][j] = Integer.parseInt(temps);
			if(gint != j)
			{
				System.out.println("��ʽ����");
				return false;
			}
			reader.close();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
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
				System.out.println("��ʽ����");
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
				System.out.println("��ʽ����");
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
			System.out.println("��ʽ����");
			return false;
		}
		return true;
	}
	
	public static boolean generateMagicSquare(int n) throws IOException {
		if(n % 2 == 0)
		{
			System.out.println("nΪż�������Ϸ���");
			return false;
		}
		else if(n <= 0)
		{
			System.out.println("n�����������Ϸ���");
			return false;
		}
		int magic[][] = new int[n][n];//����һ��n*n��ά��������magic�洢magicsquare
		int row = 0, col = n / 2, i, j, square = n * n;//�����ʼ����row,col,i.j
		for (i = 1; i <= square; i++) {
			magic[row][col] = i;//��ʼ���λ���ڵ�һ���м䱣֤�����������м�ֵ��magicsquare�м�λ��
			if (i % n == 0)
				row++;//���������/�еı���������һ����䣬��֤��ͬһ��/�У�����һ���ֵ��n������֮ǰֵ����ͬ
			else {
				if (row == 0)
					row = n - 1;//�����������0���������޸�Ϊn-1
				else
					row--;//����������һ
				if (col == (n - 1))
					col = 0;//��������ﵽ���n-1�����޸�Ϊ0
				else
					col++;//����������һ
			}//��䷽ʽÿn��Ϊһ�飬ÿ���ԶԽ��߷�����䡣�����򷴵�
		}//ͨ����б�߷�����䣬ÿn��һ�飬ÿ��λ������һ���·�һ��֤����������ͬһ�������������ͬһ�л��У�ͬʱÿһ���а���ÿһ���һ������������ÿ������n�������ͬ��
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				System.out.print(magic[i][j] + "\t");//���magicsquare
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return true;//����true������ɹ�
	}
}
