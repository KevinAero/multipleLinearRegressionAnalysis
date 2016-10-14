package LogicRegression.multipleLinearRegressionAnalysis;

public class LinearRegression {

	private static final int MAX_POINTS = 10;
	
	private double E;
	
	/**
	 * 
	 */
	public static void main(String[] args) {
		
		RegressionLine line = new RegressionLine();  
		  
        line.addDataPoint(new DataPoint(1, 136));  
        line.addDataPoint(new DataPoint(2, 143));  
        line.addDataPoint(new DataPoint(3, 132));  
        line.addDataPoint(new DataPoint(4, 142));  
        line.addDataPoint(new DataPoint(5, 147));  
  
        printSums(line);  
        printLine(line);  
    }  
  
    /** 
     *  打印和
     * @param line 
     *           回归线
     */  
    private static void printSums(RegressionLine line) {  
        System.out.println("\n数据点个数 n = " + line.getDataPointCount());  
        System.out.println("\nSum x  = " + line.getSumX());  
        System.out.println("Sum y  = " + line.getSumY());  
        System.out.println("Sum xx = " + line.getSumXX());  
        System.out.println("Sum xy = " + line.getSumXY());  
        System.out.println("Sum yy = " + line.getSumYY());  
  
    }  
  
    /** 
     * 打印回归线功能 
     *  
     * @param line 
     *            回归线
     */  
    private static void printLine(RegressionLine line) {  
        System.out.println("\n回归线公式:  y = " + line.getA1() + "x + "  
                + line.getA0());  
        System.out.println("误差：     R^2 = " + line.getR());  
    }  
}
