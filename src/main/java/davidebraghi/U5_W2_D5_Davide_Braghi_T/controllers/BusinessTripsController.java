package davidebraghi.U5_W2_D5_Davide_Braghi_T.controllers;

import davidebraghi.U5_W2_D5_Davide_Braghi_T.entities.BusinessTrip;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.exceptions.BadRequestException;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.payloads.BusinessTrips.NewBusinessTripDTO;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.payloads.BusinessTrips.NewBusinessTripResponseDTO;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.services.BusinessTripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/businessTrips")
public class BusinessTripsController {
    @Autowired
    BusinessTripsService businessTripsService;

    // GET http://localhost:3001/businessTrips

    @GetMapping("")
    public Page<BusinessTrip> getBusinessTrips(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size,
                                               @RequestParam(defaultValue = "id") String sortBy) {
        return businessTripsService.getBusinessTrips(page, size, sortBy);
    }

    // POST http://localhost:3001/businessTrips (+ body)

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NewBusinessTripResponseDTO saveBusinessTrip(@RequestBody @Validated NewBusinessTripDTO body,
                                                       BindingResult validation)
            throws Exception {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        BusinessTrip newBusinessTrip = businessTripsService.save(body);
        return new NewBusinessTripResponseDTO(newBusinessTrip.getBusinessTripId());
    }

    // GET http://localhost:3001/businessTrips/{id}

    @GetMapping("/{businessTripId}")
    public BusinessTrip findById(@PathVariable long businessTripId) {
        return businessTripsService.findById(businessTripId);
    }

    // PUT http://localhost:3001/businessTrips/{id} (+ body)

    @PutMapping("/{businessTripId}")
    public BusinessTrip findByIdAndUpdate(@PathVariable long businessTripId,
                                          @RequestBody BusinessTrip body) {
        return businessTripsService.findByIdAndUpdate(businessTripId, body);
    }

    // DELETE http://localhost:3001/businessTrips/{id}

    @DeleteMapping("/{businessTripId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long businessTripId) {
        businessTripsService.findByIdAndDelete(businessTripId);
    }
}