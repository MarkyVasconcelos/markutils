package mark.utils.bean;

/**
 *This formatter assume all Object are String.
 */
public class DefaultFormatter implements Formatter {
	@Override
	public Object format(Object obj) {
		if (obj == null)
			return "";
		return obj;
	}

	@Override
	public Object parse(Object obj) {
		return obj;
	}

	@Override
	public String getName() {
		return "string_basic";
	}
};
