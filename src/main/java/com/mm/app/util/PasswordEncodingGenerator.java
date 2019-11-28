package com.mm.app.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncodingGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String name = "nahid";
		String password = "ThisIsASecuredPassword :)";
		String dbName = "sample_maps_db";
		String schemaName = "public";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println("INSERT INTO USER_PROFILE(type)\n" +
				"VALUES ('USER');\n" +
				"  \n" +
				"INSERT INTO USER_PROFILE(type)\n" +
				"VALUES ('ADMIN');\n" +
				"  \n" +
				"INSERT INTO USER_PROFILE(type)\n" +
				"VALUES ('DBA');" +
				"  \n" +
				"INSERT INTO "+dbName+"."+schemaName+".USER(name, password, is_active)\n" +
				"VALUES ('"+name+"','"+passwordEncoder.encode(password)+"', true);\n" +
				" \n" +
				"INSERT INTO USER_USER_PROFILE (user_id, user_profile_id)\n" +
				"  SELECT "+dbName+"."+schemaName+".user.id, profile.id FROM "+dbName+"."+schemaName+".user, user_profile profile\n" +
				"  where "+dbName+"."+schemaName+".user.name='"+name+"' and profile.type='ADMIN';"
				);
	}

}
