package spring.lot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import spring.lot.model.Lot;
import spring.lot.repository.LotRepository;

@SpringBootApplication
@EnableJpaRepositories("spring.lot.repository")
public class SpringLotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLotApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(LotRepository repository) {
		return args -> {
			if(repository.findAll().isEmpty()){
				Lot lot = new Lot(10, 10);
				repository.save(lot);
			}
		};
	}

}
