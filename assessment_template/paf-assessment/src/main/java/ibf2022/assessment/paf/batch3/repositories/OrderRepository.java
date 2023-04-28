package ibf2022.assessment.paf.batch3.repositories;

import java.util.Date;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Add;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@Repository
public class OrderRepository {


	@Autowired
	private MongoTemplate mongoTemplate;

	// TODO: Task 5

	public String placeOrder(String quantity, String beerId, String breweryId){
		Date d = new Date();
		System.out.println(d);
		long epoch = d.getTime();
		System.out.println(epoch);

		UUID uuid = UUID.randomUUID();
            String shortId = uuid.toString();
            String id = shortId.substring(0, 8);

		JsonObject obj = Json.createObjectBuilder()
		.add("orderId", id)
		.add("date", epoch)
		.add("breweryId", breweryId)
		.add("orders", Json.createArrayBuilder()).build();
		;
		
		String json = obj.toString();

		Document doc = Document.parse(json);

		if (!mongoTemplate.getCollectionNames().contains("orders")){
			mongoTemplate.createCollection("orders");
		}
		

		Document document = mongoTemplate.insert(doc, "orders");
		
		System.out.println(document);
		return json;
	}

}
