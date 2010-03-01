package mark.utils.bean;

/**
 *This formatter assume all Object are String.
 */
public class DefaultFormatter implements Formatter {
	@Override
	public String format(Object obj) {
		if (obj == null)
			return "";
		return obj.toString();
	}

	@Override
	public Object parse(String obj) {
		return obj;
	}

	@Override
	public String getName() {
		return "string_basic";
	}
};
