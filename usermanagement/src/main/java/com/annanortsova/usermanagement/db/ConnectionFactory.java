package com.annanortsova.usermanagement.db;

import java.sql.Connection;


public interface ConnectionFactory {
	Connection createConnection() throws DatabaseException;
	
	
}
