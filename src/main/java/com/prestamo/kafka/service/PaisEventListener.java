package com.prestamo.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.prestamo.entity.Pais;
import com.prestamo.kafka.config.Event;
import com.prestamo.kafka.entity.PaisCreateEvent;

@Component
public class PaisEventListener {

	@KafkaListener(topics = "${topic.customer.name:topic-pais}",
				   containerFactory = "kafkaListenerContainerFactory",
				   groupId = "escuchador-pais")
	
	public void consumer(Event<?> event) {
		System.out.println("1 >> Evento recibido: "+event);
		
		if (event.getClass().isAssignableFrom(PaisCreateEvent.class)) {
			PaisCreateEvent objPaisEvent = (PaisCreateEvent) event;
			System.out.println("2 >> Pais recibido: " + objPaisEvent.getData());
			
			String id = objPaisEvent.getId();
			String type = objPaisEvent.getType().toString();
			String date = objPaisEvent.getDate().toString();
			Pais objPais = objPaisEvent.getData();
			
			int idPais = objPais.getIdPais();
			String nombre = objPais.getNombre();
			String iso = objPais.getIso();
			
			System.out.println("3 >> ID: "+ id);
			System.out.println("4 >> Type: "+ type);
			System.out.println("5 >> Date: "+ date);
			System.out.println("6 >> ID Pais: "+ idPais);
			System.out.println("7 >> Nombre: "+ nombre);
			System.out.println("8 >> ISO: "+ iso);
			
		}
		
		
	 }
}
