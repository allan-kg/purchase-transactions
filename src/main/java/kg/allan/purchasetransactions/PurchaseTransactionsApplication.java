package kg.allan.purchasetransactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PurchaseTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurchaseTransactionsApplication.class, args);
	}

}
