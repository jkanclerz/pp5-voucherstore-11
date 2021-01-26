package pl.jkanclerz.voucherstore.sales.ordering;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface ReservationRepository extends Repository<Reservation, String> {

    Reservation save(Reservation reservation);

    @Query("Select r from Reservation r where r.id = :reservationId")
    Optional<Reservation> loadById(@Param("reservationId")String reservationId);
}
