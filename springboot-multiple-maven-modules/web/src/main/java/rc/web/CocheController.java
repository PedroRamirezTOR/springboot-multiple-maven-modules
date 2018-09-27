package rc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import rc.domain2.Coche;
import rc.persistence2.CocheRepository;

@RestController
public class CocheController {

	@Autowired
	private CocheRepository cocheRepository;

	public CocheController(CocheRepository cocheRepository) {
		this.cocheRepository = cocheRepository;
	}

	@GetMapping(value = "/coches")
	public List<Coche> getCoches() {
		List<Coche> hotels = this.cocheRepository.findAll();
		return hotels;
	}
}
