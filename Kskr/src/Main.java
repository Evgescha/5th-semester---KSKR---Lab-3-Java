import java.util.Scanner;

public class Main {

	static float[][] matrixB = { { 9, 1 }, { 4, 18 }, };
	static double deter = 0;
	static double opr;

	public static void main(String[] args) {
		
		System.out.println("������� ����������� �������");
		Scanner sc = new Scanner(System.in);
		int  a = sc.nextInt() ;
		int b=a;
		if (a <= 1) {
			return;
		}
		float[][] matrixA = new float[a][a];
		float[][] matrixB = new float[b][1];
		System.out.println("������� �������");
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < a; j++) {
				matrixA[i][j] = sc.nextInt();
			}
		}

		System.out.println("���� �������");
		printArr(matrixA, a);
		
		System.out.println("������� ������� ��������� ����");
		for(int i=0; i<b; i++) {
			matrixB[i][0]=sc.nextInt();
		}
		//System.out.println("��� ������ ��������� ����");
		//printArr(matrixB, b);
		
		
		//System.out.println("������������ ������� ");
		opr = CalculateMatrix(matrixA);
		//System.out.println("" + opr);
		

		// ������� �������������� �������
		double[][] matrixPris = new double[a][a];
		// ��������� �� �������������� �������
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < a; j++) {
				matrixPris[i][j] = CalculateMatrix(GetMinor(matrixA, i, j));
			}
		}
		
		if (opr != 0) {
			//System.out.println(" ������� �������");
			//printArr(matrixPris, a);
			//System.out.println(" ������� ����������������");
			transpon(matrixPris);
			//printArr(matrixPris, a);
			//System.out.println(" ������� �����������������");
			deter(matrixPris);
			//printArr(matrixPris, a);
			reshYr(matrixA,matrixB);
			
			
		}else {
			System.out.println(" ������� �� ����� ��������");
			}
		
		}
	//������� ���������
	public static void reshYr(float[][] matrixA, float[][] matrixB) {
		float[][] mA = matrixA;
        
        float[][] mB =matrixB;

        
        int m = mA.length;
        int n = mB[0].length;
        int o = mB.length;
        int[][] res = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < o; k++) {
                    res[i][j] += mA[i][k] * mB[k][j]; 
                }
            }
        }
        
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.format("%6d ", res[i][j]);
            }
            System.out.println();
        }


	}

	public static void deter(double[][] A) {
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				A[i][j] = A[i][j] / opr;
			}
		}
	}

	public static void transpon(double[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				double temp = a[i][j];
				a[i][j] = a[j][i];
				a[j][i] = temp;
			}
		}
	}

	// ����� ������� � �������
	public static void printArr(float[][] A, int a) {
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < A.length; j++) {
				System.out.print(A[i][j] + " \t");
			}
			System.out.println("");
		}
	}

	public static void printArr(float[] A, int a) {
		for (int i = 0; i < a; i++) {
			System.out.println(A[i]);

		}
	}

	public static void printArr(double[][] A, int a) {
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < a; j++) {
				System.out.print(A[i][j] + " \t");
			}
			System.out.println("");
		}
	}

	// ������������ ������� 2 �� 2
	public static void CalculateMatrix2(float[][] matrix) {
		float calcResult = 0.0f;
		calcResult = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];

		System.out.println("������������ ������� 2 �� 2 ����� " + calcResult);
	}

	// ������� ������������ �� ������. ��� ������� ������ ��������� ���
	// ������������, ���������� ������� �� �� �������..
	public static float CalculateMatrix(float[][] matrix) {
		float calcResult = 0.0f;
		if (matrix.length == 2) {
			return matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
		}
		int koeff = 1;
		// ���������� �� ������ ������ (i+0)
		for (int i = 0; i < matrix.length; i++) {
			if (i % 2 == 1) { // ���� �� ������, �� ���������� � �������
				koeff = -1;
			} else {
				koeff = 1;
			}
			;
			// ���������� ����������:
			calcResult += koeff * matrix[0][i] * CalculateMatrix(GetMinor(matrix, 0, i));
		}

		// ���������� �����
		return calcResult;
	}

	// �������, �-� ���������� ������ ��� �����. �� ����� - ������������, �� �-��
	// ���� ������� ����� � ������ �����-��������, �-� ���� ����������.
	public static float[][] GetMinor(float[][] matrix, int row, int column) {
		int minorLength = matrix.length - 1;
		float[][] minor = new float[minorLength][minorLength];
		int dI = 0;// ��� ���������� ��� ����, ����� "����������" �������� ��� ������ � �������
		int dJ = 0;
		// ���� �� �������� �����
		for (int i = 0; i <= minorLength; i++) {
			// ���������� ��� ������ ��������
			dJ = 0;
			for (int j = 0; j <= minorLength; j++) {
				// ���� �������� � �������, ������� ����� ����������, ���������� �� ����� �
				// ����������
				if (i == row) {
					dI = 1;
				} else {
					// ���� ������� � ��������, ������� ����� ����������, ����������� �� ����� �
					// �����������
					if (j == column) {
						dJ = 1;
					}
					// ���� ������ ����������� �� �����, ���������� ������ � �������� �������
					else {
						minor[i - dI][j - dJ] = matrix[i][j];
					}
				}
			}
		}

		return minor;

	}

	// �������� �������
	static void inversion(float[][] A, int N) {
		// ��������� ���������
		double temp;
		// ������� ��������� �������
		float[][] E = new float[N][N];
		// ��� ��������� ������
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				E[i][j] = 0f;
				// ����� ������� ���������
				if (i == j)
					E[i][j] = 1f;
			}
		// �������� ���� ������
		for (int k = 0; k < N; k++) {
			// � ���������� ������� �������� �������� � ������� ���������
			temp = A[k][k];
			// ���� �������� ��� �������� �������
			for (int j = 0; j < N; j++) {
				// ������ ������� � ��������� � ����� ������� ����� �� ������� ������� ���������
				A[k][j] /= temp;
				E[k][j] /= temp;
			}

			for (int i = k + 1; i < N; i++) {
				temp = A[i][k];

				for (int j = 0; j < N; j++) {
					A[i][j] -= A[k][j] * temp;
					E[i][j] -= E[k][j] * temp;
				}
			}
		}

		for (int k = N - 1; k > 0; k--) {
			for (int i = k - 1; i >= 0; i--) {
				temp = A[i][k];

				for (int j = 0; j < N; j++) {
					A[i][j] -= A[k][j] * temp;
					E[i][j] -= E[k][j] * temp;
				}
			}
		}

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				A[i][j] = E[i][j];

		printArr(A, 2);

	}

}
