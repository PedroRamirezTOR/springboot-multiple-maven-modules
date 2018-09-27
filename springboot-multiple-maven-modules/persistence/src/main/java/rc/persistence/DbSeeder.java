package rc.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import rc.domain.Hotel;

@Component
public class DbSeeder implements CommandLineRunner{
	
	private HotelRepository hotelRepository;
	
	public DbSeeder(HotelRepository hotelRepository) {
		this.hotelRepository=hotelRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		Hotel marriot = new Hotel("NH",5,true);
		Hotel ibis = new Hotel("Hilton",3,false);
		Hotel goldenTulip = new Hotel("Palace",4,true);
		
		List<Hotel> hotels = new ArrayList<Hotel>();
		
		hotels.add(marriot);
		hotels.add(ibis);
		hotels.add(goldenTulip);
		
		hotelRepository.saveAll(hotels);
	}
}
