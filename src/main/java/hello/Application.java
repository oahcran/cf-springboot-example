package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) throws Exception {

		int size = args.length;

		System.out.println("args[] length:" + size);

		for (int i = 0; i < size; i++)
			System.out.println("args[" + i + "]: " + args[i]);
		
        SpringApplication.run(Application.class);
    }
}
