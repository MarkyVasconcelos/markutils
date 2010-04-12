package mark.utils.bean;

/**
 *Simple interface to convert a object to String and a String to a object.
 * 
 *@author Marcos Vasconcelos
 */
public interface Formatter {
	/**
	 * Convert a object to String.
	 */
	public abstract String format(Object obj);

	/**
	 * Convert the String to the Object.
	 */
	public abstract Object parse(String s);

	/**
	 * Naming proposes only
	 */
	public abstract String getName();
}