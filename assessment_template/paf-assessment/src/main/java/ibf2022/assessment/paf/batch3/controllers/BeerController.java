package ibf2022.assessment.paf.batch3.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ibf2022.assessment.paf.batch3.exception.NotFoundException;
import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;
import ibf2022.assessment.paf.batch3.repositories.BeerRepository;
import ibf2022.assessment.paf.batch3.services.BeerService;

@RestController
@RequestMapping
public class BeerController {

	@Autowired
	private BeerRepository beerRepo;

	@Autowired
	private BeerService svc;

	//TODO Task 2 - view 0
	@GetMapping
	public ModelAndView getStyles(){
		List<Style> styles = beerRepo.getStyles();
		ModelAndView mv =  new ModelAndView();

		mv.setViewName("view0");
		mv.addObject("styles", styles);
		mv.setStatus(HttpStatusCode.valueOf(200));

		return mv;
		
	}
	
	//TODO Task 3 - view 1
	@GetMapping(path="/beer/style/0{styleName}")
	public ModelAndView getBeers(@PathVariable String styleName) throws NotFoundException {
		ModelAndView mv =  new ModelAndView();
		List<Beer> beers = beerRepo.getBreweriesByBeer(styleName);
	

		mv.setViewName("view1");
		mv.addObject("beers", beers);
		mv.addObject("styleName", styleName);
		mv.setStatus(HttpStatusCode.valueOf(200));
		
		if (beers == null) {
			mv.setViewName("error");
			mv.addObject("error", "No beers found for this style");
			mv.setStatus(HttpStatusCode.valueOf(404));
		}

		return mv;
	}


	//TODO Task 4 - view 2
	@GetMapping(path="/beer/style/{breweryName}")
	public ModelAndView getBeersFromBrewery(@PathVariable String breweryName){

		Optional<Brewery> brewery = beerRepo.getBeersFromBrewery(breweryName);
		ModelAndView mv = new ModelAndView();
		System.out.println(brewery);

		 if (brewery.isEmpty()){
			mv.setViewName("error");
			mv.addObject("error", "Brewery not found");
			mv.setStatus(HttpStatusCode.valueOf(404));
		 }

	
		 mv.setViewName("view2");
		 mv.addObject("brewery", brewery);
		 mv.setStatus(HttpStatusCode.valueOf(200));

		 return mv;

	}


	
	//TODO Task 5 - view 2, place order
	@PostMapping(path="/brewery/{breweryId}/order", consumes=MediaType.)
	public ResponseEntity<String> placeOrder(@RequestParam String quantity){

		

	}


}
