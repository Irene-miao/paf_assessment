package ibf2022.assessment.paf.batch3.services;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.assessment.paf.batch3.repositories.OrderRepository;

@Service
public class BeerService {

	@Autowired
	private OrderRepository orderRepo;

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public String placeOrder(String quantity, String beerId, String breweryId) {
		// TODO: Task 5 
		
		String json = orderRepo.placeOrder(quantity, beerId, breweryId);
	
		return json;
		

		
	}

}
