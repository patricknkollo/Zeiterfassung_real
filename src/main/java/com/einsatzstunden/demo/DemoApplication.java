package com.einsatzstunden.demo;

import com.einsatzstunden.demo.services.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.MessagingException;

@SpringBootApplication
public class DemoApplication {

	private final  static  Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	@Autowired
	private EmailSenderService emailSenderService;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}
	/**cette methode affiche la date toutes les 2 secs sauf que dans ce cas
	 * initialDelay est le temps que la methode attend juste après que le context se soit demarré et
	 * fixedDelay est le temps choisi entre la fin de la dernière invocation et le debut de la prochaine
	 * invocation*/
	@Scheduled(initialDelay = 1000l, fixedDelay = 2000L)
	void myjob1(){
		//logger.info("the time is {}", LocalDateTime.now());
	}
	/**cette methode affiche la date toutes les 2 secs. Ici la methode sera
	 * executée en definitv toutes les 3 secondes. c'est à dire 1sec de sommeil
	 * et 2sec de latence avant execution*/
	@Scheduled(fixedRate = 2000L)
	void myjob2() throws  Exception{
		//logger.info("the time is {}", LocalDateTime.now());
	//	Thread.sleep(1000L);// cette commande fait dormir la methode myjob2() pendant une seconde
	}
	/** cette methode sera executée toutes les 1 heure*/
	@Scheduled(initialDelay = 1000l, fixedDelayString = "PT1H")
	void myjob3(){
	}
	/** la methode attend 1 sec après l'etablissement du context de l'application
	 * NB: cron(sec min hour day[of month] month day[of week]) ---> see the website   crontab.guru*/
//	@Scheduled(initialDelay = 1000l, cron = "* * * * * *")
	void myjob4(){
	}


	/*@EventListener(ApplicationReadyEvent.class)
	public void sendMail(){
		emailSenderService.sendMail("nkollopatrick@yahoo.fr",
				"This is email body",
				"This is email subject");

	}*/
}

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
class SchedulingConfig{

}