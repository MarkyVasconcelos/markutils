package mark.utils.el.handler;

import mark.utils.bean.Formatter;
import mark.utils.el.NotResolvableFieldException;

public class BlankHandler implements FieldAccessHandler {
	@Override
	public Class<?> getFieldType() {
		return String.class;
	}

	@Override
	public Class<?> getTraceClassAt(int idx) {
		return String.class;
	}

	@Override
	public Object getValue(Object t, Formatter formatter) {
		return "";
	}

	@Override
	public void resolveField(Class<?> clazz, String expression)
			throws NotResolvableFieldException {
	}

	@Override
	public void setValue(Object t, Object value, Formatter formatter) {
	}

}
