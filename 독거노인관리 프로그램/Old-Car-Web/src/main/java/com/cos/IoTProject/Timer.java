package com.cos.IoTProject;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.cos.IoTProject.controller.MainController;
import com.cos.IoTProject.model.GasDetect;
import com.cos.IoTProject.model.Management;
import com.cos.IoTProject.model.RaspiSensor;
import com.cos.IoTProject.model.TempAndHumi;
import com.cos.IoTProject.model.Users;
import com.cos.IoTProject.repository.GasDetectRepository;
import com.cos.IoTProject.repository.HumanDetectRepository;
import com.cos.IoTProject.repository.ManagementRepository;
import com.cos.IoTProject.repository.ManagerRepository;
import com.cos.IoTProject.repository.TempAndHumiRepository;
import com.cos.IoTProject.repository.UsersRepository;

@Component
public class Timer {
	//RaspiSensor rs;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private AtomicInteger loopCounter = new AtomicInteger();

	@Autowired
	private StopWatch watch;
	@Autowired
	private TempAndHumiRepository tempAndHumiRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private GasDetectRepository gasDetectRepository;
	@Autowired
	private ManagementRepository managementRepository;

	@PostConstruct
	public void init() {
		watch.start();
	}

	@Scheduled(fixedDelayString = "60000000")
	public void tick() throws InterruptedException {
		watch.stop();
	    if(MainController.raspisensor!=null) {
	    	RaspiSensor ras = MainController.raspisensor;
	    	//String res = "온도 : "+ras.getTemp()+"℃ , "+"습도 : "+ras.getHumi()+"%";
	    	//System.out.println(res);
	    	if(ras.getManagement()!=null) {
		    	TempAndHumi tah = TempAndHumi.builder()
		    			.temp(ras.getTemp())
		    			.humi(ras.getHumi())
		    			.management(ras.getManagement())
		    			.build();
		    	tempAndHumiRepository.save(tah);
	    	}
	    }
		watch.start();
	}
	
	@Scheduled(cron = "0 0 0 * * *")
	public void mamageInit() throws InterruptedException {
		watch.stop();
		List<Users> list = usersRepository.findAll();
	    if(list!=null) {
	    	for(int i=0; i<list.size();i++) {
		    	Management management = Management.builder()
						.user(list.get(i))
						.build();
				managementRepository.save(management);
				GasDetect gasDetect = GasDetect.builder()
						.gasCheck("false")
						.management(management)
						.build();
				gasDetectRepository.save(gasDetect);
	    	}
	    }
		watch.start();
	}
	

	@Bean
	public StopWatch watch() {
		return new StopWatch();
	}
}
