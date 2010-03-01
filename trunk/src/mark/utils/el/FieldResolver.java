package mark.utils.el;

import mark.utils.bean.DefaultFormatter;
import mark.utils.bean.Formatter;
import mark.utils.el.handler.FieldAccessHandler;
import mark.utils.el.handler.FieldHandler;

/**
 *The class to access the field value.
 * 
 *@author Marcos Vasconcelos
 */
public class FieldResolver {
	private String fieldName;// The field Name.
	private String name;// A name for this field column.
	private Formatter formatter;
	private FieldAccessHandler method;
	private Class<?> owner;
	private static Class<? extends FieldAccessHandler> defaultHandler;
	private static Class<? extends Formatter> defaultFormatter;

	static {
		defaultHandler = FieldHandler.class;
		defaultFormatter = DefaultFormatter.class;
	}

	public static void setDefaultHandler(
			Class<? extends FieldAccessHandler> handler) {
		if (handler == null)
			throw new RuntimeException("Handler can not be null!");
		defaultHandler = handler;
	}

	public static void setDefaultFormatter(Class<? extends Formatter> formatter) {
		if (formatter == null)
			throw new RuntimeException("Formatter can not be null!");
		defaultFormatter = formatter;
	}

	public FieldResolver(Class<?> clazz, String fieldName, String name) {
		this(clazz, fieldName, name, null);
	}

	public FieldResolver(Class<?> clazz, String fieldName) {
		this(clazz, fieldName, "", null);
	}

	public FieldResolver(Class<?> clazz, String fieldName,
			FieldAccessHandler handler) {
		this(clazz, fieldName, "", handler);
	}

	public FieldResolver(Class<?> clazz, String fieldName, String name,
			FieldAccessHandler handler) {
		if (handler == null)
			try {
				handler = defaultHandler.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}

		owner = clazz;

		this.fieldName = fieldName;
		this.name = name;

		method = handler;
		method.resolveField(clazz, fieldName);

		try {
			setFormatter(defaultFormatter.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FieldResolver setFormatter(Formatter formatter) {
		if (formatter == null)
			throw new IllegalArgumentException("Formatter can't be null!");
		this.formatter = formatter;
		return this;
	}

	public void setValue(Object t, Object value) {
		method.setValue(t, value, formatter);
	}

	public Object getValue(Object t) {
		return method.getValue(t, formatter);
	}

	public String getName() {
		return name;
	}

	public Class<?> getFieldType() {
		return method.getFieldType();
	}

	public String getFieldName() {
		return fieldName;
	}

	public Class<?> getOwnerClass() {
		return owner;
	}

	public Formatter getFormatter() {
		return formatter;
	}

	public Class<?> getTraceClassAt(int idx) {
		return method.getTraceClassAt(idx);
	}
}
