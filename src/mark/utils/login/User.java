package mark.utils.login;

import mark.utils.role.RoleMember;

public interface User extends RoleMember {
	public String getPassword();

	public String getUsername();
}
