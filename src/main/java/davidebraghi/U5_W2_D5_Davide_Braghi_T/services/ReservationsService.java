package davidebraghi.U5_W2_D5_Davide_Braghi_T.services;

import davidebraghi.U5_W2_D5_Davide_Braghi_T.entities.BusinessTrip;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.entities.Employee;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.entities.Reservation;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.exceptions.BadRequestException;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.exceptions.NotFoundException;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.payloads.Reservations.NewReservationDTO;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.repositories.BusinessTripRepo;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.repositories.EmployeeRepo;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.repositories.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ReservationsService {
    @Autowired
    private ReservationRepo reservationRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private BusinessTripRepo businessTripRepo;

    // Paginazione // findAll

    public Page<Reservation> getReservation(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return reservationRepo.findAll(pageable);
    }

    // findById

    public Reservation findById(long id) {
        return reservationRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    // Save

    public Reservation save(NewReservationDTO body) {
        Employee emp = employeeRepo.findById(body.employee().getEmployeeId()).orElseThrow(() -> new NotFoundException(body.employee().getEmployeeId()));

        BusinessTrip trip = businessTripRepo.findById(body.businessTrip().getBusinessTripId()).orElseThrow(() -> new NotFoundException(body.businessTrip().getBusinessTripId()));

        if (reservationRepo.existsByEmployee_EmployeeIdAndReservationDate(emp.getEmployeeId(), trip.getReservationDate())) {
            throw new BadRequestException("The Employer has been already assigned in this date.");
        }

        Reservation reservation = new Reservation();
        reservation.setReservationDate(body.reservationDate());
        reservation.setNotes(body.notes());
        reservation.setEmployee(emp);
        reservation.setBusinessTrip(trip);

        return reservationRepo.save(reservation);
    }

    // findByIdAndUpdate

    public Reservation findByIdAndUpdate(long id, Reservation body) {
        Reservation found = this.findById(id);
        found.setReservationDate(body.getReservationDate());
        found.setNotes(body.getNotes());
        found.setEmployee(body.getEmployee());
        found.setBusinessTrip(body.getBusinessTrip());

        return reservationRepo.save(found);
    }

    // findByIdAndDelete

    public void findByIdAndDelete(long id) {
        Reservation found = this.findById(id);
        reservationRepo.delete(found);
    }
}