package com.test.inventoryservice;

import jakarta.annotation.PostConstruct;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@SpringBootApplication
public class InventoryServiceApplication {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@PostConstruct
	private void initDb() {
		String sqlStatements[] = {
				"drop table product if exists",
				"create table product(product_id identity primary key, product_name varchar(255), price decimal(15,2), quantity int not null)",
				"insert into product(product_name, price, quantity) values('Tas', 150000, 4)",
				"insert into product(product_name, price, quantity) values('Handphone', 1500000, 3)",
				"insert into product(product_name, price, quantity) values('PC', 15000000, 2)",
				"insert into product(product_name, price, quantity) values('Emas', 1000000, 1)"
		};

		Arrays.asList(sqlStatements).forEach(sql -> {
			jdbcTemplate.execute(sql);
		});

	}
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseaServer() throws SQLException {
		return Server.createTcpServer(
				"-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
	}
}
