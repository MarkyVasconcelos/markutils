package mark.utils.math;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class Expression {
	private String expression;
	private List<Double> numbers = new LinkedList<Double>();
	private List<Operator> operators = new LinkedList<Operator>();

	public Expression(String exp) {
		expression = exp;
	}

	public static double resolve(String expression) {
		return new Expression(expression).resolve();
	}

	public void setVariable(String name, Object value) {
		expression = expression.replaceAll(name, value.toString());
	}

	public double resolve() {
		numbers = new LinkedList<Double>();
		operators = new LinkedList<Operator>();
		StringBuilder current = new StringBuilder();
		if (expression.startsWith("-") || expression.startsWith("+"))
			numbers.add(0.0);

		for (int i = 0; i < expression.length(); i++) {
			String s = expression.substring(i, i + 1);
			if (s.equals("(")) {
				int opens = 0;
				String sub = expression.substring(i);
				for (int j = 0; j < sub.length(); j++) {
					String ss = sub.substring(j, j + 1);
					if (ss.equals("("))
						opens++;
					if (ss.equals(")"))
						opens--;
					if (opens == 0) {
						String exp = sub.substring(0, j + 1);
						String expPure = sub.substring(1, j);
						double value = new Expression(expPure).resolve();
						expression = expression.replace(exp, Double
								.toString(value));
						return resolve();
					}
				}
				continue;
			}

			if (s.matches(operator.pattern())) {
				if (s.equals(add.symbol()))
					operators.add(add);
				else if (s.equals(minus.symbol()))
					operators.add(minus);
				else if (s.equals(div.symbol()))
					operators.add(div);
				else
					operators.add(times);
				continue;
			}
			current.append(s);
			String next = "";
			if (expression.length() >= i + 2)
				next = expression.substring(i + 1, i + 2);
			if (!next.matches(digit.pattern())) {
				numbers.add(Double.parseDouble(current.toString()));
				current.delete(0, current.length());
			}
		}
		resolveTimesDivs();

		double result = 0;
		if (numbers.size() == 1)
			result = numbers.get(0);

		else {
			result = operators.get(0).resolve(numbers.get(0), numbers.get(1));
			for (int i = 1; i < operators.size(); i++) {
				Operator op = operators.get(i);
				result = op.resolve(result, numbers.get(i + 1));
			}
		}
		return result;
	}

	private void resolveTimesDivs() {
		for (int i = 0; i < operators.size(); i++) {
			Operator op = operators.get(i);
			boolean resolve = op.symbol().equals(div.symbol())
					|| op.symbol().equals(times.symbol());
			if (resolve) {
				double d1 = numbers.get(i);
				double d2 = numbers.get(i + 1);
				double result = op.resolve(d1, d2);
				numbers.set(i, result);
				numbers.remove(i + 1);
				operators.remove(i);
				resolveTimesDivs();
				break;
			}
		}
	}

	private static final Pattern digit = Pattern.compile("[0-9\\.]");
	private static final Pattern operator = Pattern.compile("[+\\-/*]");

	private interface Operator {
		public double resolve(double first, double second);

		public String symbol();
	}

	private Operator minus = new Operator() {
		@Override
		public double resolve(double first, double second) {
			return first - second;
		}

		@Override
		public String symbol() {
			return "-";
		}
	};
	private Operator add = new Operator() {
		@Override
		public double resolve(double first, double second) {
			return first + second;
		}

		@Override
		public String symbol() {
			return "+";
		}
	};
	private Operator div = new Operator() {
		@Override
		public double resolve(double first, double second) {
			return first / second;
		}

		@Override
		public String symbol() {
			return "/";
		}
	};
	private Operator times = new Operator() {
		@Override
		public double resolve(double first, double second) {
			return first * second;
		}

		@Override
		public String symbol() {
			return "*";
		}
	};
}
