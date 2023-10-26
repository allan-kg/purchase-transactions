/*
 * PurchaseTransactionsApplication
 *
 * v1.0
 *
 * 2023
 *
 * Author: Allan Krama Guimarães
 */

package kg.allan.purchasetransactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 
 * @author Allan Krama Guimarães
 */
@SpringBootApplication
@EnableJpaRepositories
public class PurchaseTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurchaseTransactionsApplication.class, args);
	}

}
