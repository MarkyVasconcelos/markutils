package test.math;

import mark.utils.math.Expression;

public class ExpressionTest {
	private static void testExpressions(){
		Expression exp = new Expression("10+10*((10+10)+cos(10)-sin(10)*tan(8))");
		System.out.println(exp.resolve());
	}
	
	public static void main(String[] args) {
		testExpressions();
	}
}
