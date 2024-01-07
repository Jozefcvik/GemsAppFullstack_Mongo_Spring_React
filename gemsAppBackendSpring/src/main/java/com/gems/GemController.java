package com.gems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin("http://localhost:3000/")
public class GemController {

    @Autowired
    private GemService gemService;

    @GetMapping("/gems")
    public ResponseEntity<List<Gem>> getAllGems(@RequestParam(required = false) String title) {
        if (title != null) {
            return new ResponseEntity<>(gemService.findByTitle(title), HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Gem>>(gemService.allGems(), HttpStatus.OK);
        }
    }

    @GetMapping("/gems/{id}")
    public ResponseEntity<Optional<Gem>> getGemById(@PathVariable("id") String id) {
        return new ResponseEntity<>(gemService.getGemById(id), HttpStatus.OK);
    }

    @GetMapping("/gems/reserved")
    public ResponseEntity<List<Gem>> findByReserved() {
        return new ResponseEntity<>(gemService.findAllReservedGem(true),HttpStatus.OK);
    }


    @PostMapping("/gems")
    public ResponseEntity<Gem> postCreateGem(@RequestBody Gem gem) throws IllegalStateException {
        return new ResponseEntity<>(gemService.createGem(gem), HttpStatus.OK);
    }


    @PutMapping("/gems/{id}")
    public ResponseEntity<Gem> putUpdateGem(@PathVariable("id") String id, @RequestBody Gem gem) {
        Optional<Gem> gemData = gemService.getGemById(id);

        if (gemData.isPresent()) {
            return new ResponseEntity<>(gemService.updateGem(id, gem), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/gems/{id}")
    public ResponseEntity<Optional<Gem>> deleteDeleteGem(@PathVariable("id") String id) {
        return new ResponseEntity<Optional<Gem>>(gemService.deleteGem(id), HttpStatus.OK);
    }


    @DeleteMapping("/gems")
    public ResponseEntity<String> deleteDeleteAllGems() {
        return new ResponseEntity<String>(gemService.deleteAllGems(), HttpStatus.NO_CONTENT);
    }
}
