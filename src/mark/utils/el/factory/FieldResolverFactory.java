package mark.utils.el.factory;

import mark.utils.bean.Formatter;
import mark.utils.el.FieldResolver;
import mark.utils.el.NotResolvableFieldException;

public class FieldResolverFactory {
	private Class<?> clazz;

	public FieldResolverFactory(Class<?> tClass) {
		if (tClass == null)
			throw new IllegalArgumentException("Class can't be null!");
		clazz = tClass;
	}

	public FieldResolver createResolver(String fieldName, String colName) {
		if (fieldName == null)
			throw NotResolvableFieldException.create(null, fieldName, clazz);
		return new FieldResolver(clazz, fieldName, colName);
	}

	public FieldResolver createResolver(String fieldName, String colName,
			Formatter formatter) {
		FieldResolver resolver = new FieldResolver(clazz, fieldName, colName);
		resolver.setFormatter(formatter);
		return resolver;
	}

	public FieldResolver createResolver(String string) {
		return createResolver(string, string);
	}

	public FieldResolver createResolver(String string, Formatter formatter) {
		return createResolver(string, string, formatter);
	}
}
