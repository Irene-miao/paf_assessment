package ibf2022.assessment.paf.batch3.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.RealLiteral;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;

@Repository
public class BeerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// DO NOT CHANGE THE SIGNATURE OF THIS METHOD
	public List<Style> getStyles() {
		// TODO: Task 2

		List<Style> styles = new ArrayList<>();
		String SELECT_ALL_STYLES = "select style_name, count(style_name) as beer_count from styles group by style_name   order by count(style_name) desc, style_name asc";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(
			SELECT_ALL_STYLES);
		while (rs.next()){
			styles.add(Style.create(rs));
		}

		return styles;
		
	}
		
	
	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public List<Beer> getBreweriesByBeer(String styleName) {
		// TODO: Task 3
		List<Beer> beers = new ArrayList<>();
		String SELECT_ALL_BEERS = "select b.name , b.descript, b.brewery_id, s.id, s.style_name as style_name, br.name  as brewery_name from beers as b inner join styles as s on b.style_id = s.id inner join breweries as br on  b.brewery_id = br.id where style_name = ? order by name asc";

		SqlRowSet rs = jdbcTemplate.queryForRowSet(
			SELECT_ALL_BEERS, styleName);


			System.out.println(rs.next());

		while (rs.next()){
			beers.add(Beer.create(rs));
		}

		System.out.println(beers);
		return beers;

		
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public Optional<Brewery> getBeersFromBrewery(String breweryName) {
		// TODO: Task 4
		String GET_BEERS_FROM_BREWERY = "select br.descript as brewery_des, br.address1, br.address2, br.phone, br.website, b.name, b.descript as beer_des from breweries as br inner join beers as b on br.id = b.brewery_id where br.name = ?";


		SqlRowSet rs = jdbcTemplate.queryForRowSet(
			GET_BEERS_FROM_BREWERY, breweryName);

			System.out.println(rs.next());

		while (!rs.next()){
			return Optional.empty();
		}

		return Optional.of(Brewery.create(rs)); 


	}
}
