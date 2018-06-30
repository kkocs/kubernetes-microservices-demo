package com.example.springjpademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    BeerRepository beerRepository;

    @CrossOrigin
    @GetMapping(path="/reduce-stock")
    public ResponseEntity reduceStock(@RequestParam("beeramount") Integer amount, @RequestParam("beertype") String type) {

        // Logically this can never be returned.
        String clientResponse = "RESPONSE_NOT_GENERATED";

        String AMOUNT_SUCCESSFULLY_RETAINED_RESPONSE = "amount_successfully_retained";
        String WRONG_BEERTYPE_RESPONSE               = "wrong_beertype";
        String UNSIFICIENT_QUANTITY_RESPONSE         = "insufficient_quantity_response";
        String WRONG_REQUEST_RESPONSE                = "wrong_request";

        if (type.length() <= 0 || amount <= 1) {
            clientResponse = WRONG_REQUEST_RESPONSE;
        }

        List<Beer> beers = beerRepository.findAll();
        boolean found = false;
        for( Beer beer : beers ) {
            if (beer.getType().equals(type)) {
                if(beer.getIn_stock() >= amount) {
                    clientResponse = AMOUNT_SUCCESSFULLY_RETAINED_RESPONSE;
                    // TODO: reduce the amount in the DB.
                    // beerRepository.delete(beer);
                    beer.setIn_stock( beer.getIn_stock() - amount );
                    beerRepository.save(beer);
                } else {
                    clientResponse = UNSIFICIENT_QUANTITY_RESPONSE;
                }
                found = true;
                break;
            }
        }

        if (!found) {
            clientResponse = WRONG_BEERTYPE_RESPONSE;
        }


        return ResponseEntity.status(HttpStatus.OK).body(clientResponse);
    }

    @CrossOrigin
    @GetMapping(path="/beers")
    public List<Beer> getAll() {
        return beerRepository.findAll();
    }

    @CrossOrigin
    @PostMapping(path="/beer")
    public void create(@RequestBody Beer user) {
        beerRepository.save(user) ;
    }

    @CrossOrigin
    @GetMapping(path="/addBeer")
    public void addBeer(@RequestParam("beeramount") Integer amount, @RequestParam("beertype") String type) {
        List<Beer> beers = beerRepository.findAll();
        boolean beerFound = false;
        for (Beer beer : beers) {
            if (beer.getType().equals(type)) {
                // beerRepository.delete(beer);
                beer.setIn_stock( beer.getIn_stock() + amount );
                beerRepository. save(beer);
                beerFound = true;
            }
        }

        if (!beerFound) {
            Beer beer = new Beer();
            beer.setIn_stock(amount);
            beer.setType(type);
            beerRepository.save(beer);
        }
    }

    /*
    @CrossOrigin
    @GetMapping(path="/isValidToken")
    public boolean check(@RequestParam("access_token") String token) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getToken().equals(token)) {
                return true;
            }
        }
        return false;
    }
    */
}
