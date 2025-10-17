package davidebraghi.U5_W2_D5_Davide_Braghi_T.services;

import davidebraghi.U5_W2_D5_Davide_Braghi_T.entities.BusinessTrip;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.exceptions.NotFoundException;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.payloads.NewBusinessTripDTO;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.repositories.BusinessTripRepo;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BusinessTripsService {
    @Autowired
    private BusinessTripRepo businessTripRepo;

    // Paginazione // findAll

    public Page<BusinessTrip> getBusinessTrips(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return businessTripRepo.findAll(pageable);
    }

    // findById

    public BusinessTrip findById(long id) {
        return businessTripRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    // Save

    public BusinessTrip save(NewBusinessTripDTO body) throws IOException {
        if (businessTripRepo.findByDestination(body.destination()).isPresent()) {
            throw new BadRequestException("The destination " + body.destination() + " has been already assigned.");
        }
        ;

        BusinessTrip newBusinessTrip = new BusinessTrip();
        newBusinessTrip.setDestination(body.destination());
        newBusinessTrip.setDate(body.date());
        newBusinessTrip.setStatus(body.status());

        return businessTripRepo.save(newBusinessTrip);
    }

    // findByIdAndUpdate

    public BusinessTrip findByIdAndUpdate(long id, BusinessTrip body) {
        BusinessTrip found = this.findById(id);
        found.setDestination(body.getDestination());
        found.setDate(body.getDate());
        found.setStatus(body.getStatus());

        return businessTripRepo.save(found);
    }

    // findByIdAndDelete

    public void findByIdAndDelete(long id) {
        BusinessTrip found = this.findById(id);
        businessTripRepo.delete(found);
    }
}