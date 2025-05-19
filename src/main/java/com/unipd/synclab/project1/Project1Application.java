package com.unipd.synclab.project1;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import com.unipd.synclab.project1.model.SrteamAPIprova;

@SpringBootApplication
public class Project1Application {

	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);

		// esercizi per imparare stream APi
		// Integer x = 0;
		// ArrayList<SrteamAPIprova> lista = new ArrayList<>();
		// while(x<100){
		// 	boolean y = true;
		// 	if(x < 50) y = false;
		// 	lista.add(new SrteamAPIprova("descrizione" + x,y, x));
		// 	x++;
		// }

		// List<String> taskNotCompleted = lista.stream().filter(task -> !task.getCompletato()).map(task -> task.getDescrizione()).collect(Collectors.toList());
		// Long nrTaskMoreThan3priority = lista.stream().filter(task -> task.getPriorità() > 3).count();
	}

}
