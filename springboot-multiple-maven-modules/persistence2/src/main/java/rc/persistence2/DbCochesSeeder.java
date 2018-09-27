package rc.persistence2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import rc.domain2.Coche;

@Component
public class DbCochesSeeder implements CommandLineRunner{
	
	private CocheRepository cochesRepository;
	
	public DbCochesSeeder(CocheRepository hotelRepository) {
		this.cochesRepository=hotelRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		Coche marriot = new Coche("Ferrari",5,true);
		Coche ibis = new Coche("Masserati",3,false);
		Coche goldenTulip = new Coche("Aston Martin",4,true);
		
		List<Coche> hotels = new ArrayList<Coche>();
		
		hotels.add(marriot);
		hotels.add(ibis);
		hotels.add(goldenTulip);
		
		cochesRepository.saveAll(hotels);
	}
}
