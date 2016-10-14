package LogicRegression.multipleLinearRegressionAnalysis;

import java.math.BigDecimal;
import java.util.ArrayList;

public class RegressionLine {

	/* x������ * */
	private double sumX;

	/* y������ * */
	private double sumY;

	/* x��ƽ����ֵ * */
	private double sumXX;

	/* x����y��ֵ * */
	private double sumXY;

	/* y��ƽ����ֵ * */
	private double sumYY;

	/* sumDeltaY��ƽ����ֵ * */
	private double sumDeltaY2;

	/* ��� * */
	private double sse;

	private double sst;

	private double E;

	private String[] xy;

	private ArrayList listX;

	private ArrayList listY;

	private int XMin, XMax, YMin, YMax;

	/* ��ϵ��a0 * */
	private float a0;

	/* ��ϵ��a1 * */
	private float a1;

	/* ���ݵ��� * */
	private int pn;

	/* ��¼ϵ���Ƿ���Ч * */
	private boolean coefsValid;

	// ���캯��
	public RegressionLine() {
		XMax = 0;
		YMax = 0;
		pn = 0;
		xy = new String[2];
		listX = new ArrayList();
		listY = new ArrayList();
	}

	/*
	 * ���ص�ǰ���ݵ����Ŀ
	 * 
	 * @return ��ǰ���ݵ����Ŀ
	 **/
	public int getDataPointCount() {
		return pn;
	}

	/*
	 * ������ϵ��a0
	 ***/
	public float getA0() {
		validateCoefficients();
		return a0;
	}

	/*
	 * ������ϵ��a1
	 ***/
	public float getA1() {
		validateCoefficients();
		return a1;
	}

	public double getSumX() {
		return sumX;
	}

	public double getSumY() {
		return sumY;
	}

	public double getSumXX() {
		return sumXX;
	}

	public double getSumXY() {
		return sumXY;
	}

	public double getSumYY() {
		return sumYY;
	}

	public int getXMin() {
		return XMin;
	}

	public int getXMax() {
		return XMax;
	}

	public int getYMin() {
		return YMin;
	}

	public int getYMax() {
		return YMax;
	}

	/*
	 * ���㷽��ϵ�� y=ax+b �е�a
	 ***/
	private void validateCoefficients() {

		if (coefsValid) {
			return;
		}
		if (pn >= 2) {
			float xBar = (float) (sumX / pn);
			float yBar = (float) (sumY / pn);

			a1 = (float) ((pn * sumXY - sumX * sumY) / (pn * sumXX - sumX
					* sumX));
			a0 = (float) (yBar - a1 * xBar);
		} else {
			a0 = a1 = Float.NaN;
		}
		coefsValid = true;
	}

	/*
	 * ���һ���µ����ݵ㣺�ı�����
	 *********/
	public void addDataPoint(DataPoint dataPoint) {
		sumX += dataPoint.x;
		sumY += dataPoint.y;
		sumXX += dataPoint.x * dataPoint.x;
		sumXY += dataPoint.x * dataPoint.y;
		sumYY += dataPoint.y * dataPoint.y;

		if (dataPoint.x >= XMax) {
			XMax = (int) dataPoint.x;
		}
		if (dataPoint.y >= YMax) {
			YMax = (int) dataPoint.y;
		}

		// ��ÿ����ľ����������ArrayList�У�����

		xy[0] = (int) dataPoint.x + "";
		xy[1] = (int) dataPoint.y + "";
		if (dataPoint.x != 0 && dataPoint.y != 0) {
			System.out.print(xy[0] + ",");
			System.out.println(xy[1]);

			try {
				listX.add(pn, xy[0]);
				listY.add(pn, xy[1]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		++pn;
		coefsValid = false;
	}

	/*
	 * ����x�Ļع��ߺ�����ֵ
	 **/
	public float at(int x) {
		if (pn < 2)
			return Float.NaN;

		validateCoefficients();
		return a0 + a1 * x;
	}

	public void reset() {
		pn = 0;
		sumX = sumY = sumXX = sumXY = 0;
		coefsValid = false;
	}

	/**
	 * �������
	 */
	public double getR() {
		// �������list�������ĸ
		for (int i = 0; i < pn - 1; i++) {
			float Yi = (float) Integer.parseInt(listY.get(i).toString());
			float Y = at(Integer.parseInt(listX.get(i).toString()));
			float deltaY = Yi - Y;
			float deltaY2 = deltaY * deltaY;

			sumDeltaY2 += deltaY2;

		}

		sst = sumYY - (sumY * sumY) / pn;
		E = 1 - sumDeltaY2 / sst;

		return round(E, 4);
	}

	// ����ʵ�־�ȷ����������
	public double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"�������������һ������������");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();//��"��ӽ���"�������룬����������������ֵľ�����ȣ���Ϊ�������������ģʽ��

	}

	public float round(float v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"�������������һ������������");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).floatValue();

	}

}
