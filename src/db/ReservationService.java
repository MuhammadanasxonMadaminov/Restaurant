package db;

import bean.ReservationBean;
import bean.TableBean;

import java.util.List;

public class ReservationService {
    static List<ReservationBean> reservations = DB.getReservations();
    static List<TableBean> tables = DB.getTables();


    public ReservationBean add(ReservationBean reservation) {
        if(tables.get(reservation.getTableId()).getReserve()) {
            return null;
        }
        DB.getTables().get(reservation.getTableId()).setReserve(true);
        reservation.setId(reservations.size());
        reservations.add(reservation);
        return reservation;
    }

    public ReservationBean get(Integer id) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getId().equals(id)) {
                return reservations.get(i);
            }
        }
        return null;
    }


    public Boolean update(ReservationBean reservation) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getId().equals(reservation.getId())) {
                reservations.get(i).setMeals(reservation.getMeals());
                reservations.get(i).setNumberOfPeople(reservation.getNumberOfPeople());
                reservations.get(i).setUserLogin(reservation.getUserLogin());
                return true;
            }
        }
        return false;
    }


    public Boolean delete(Integer id) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getId().equals(id)) {
                reservations.get(i).setDeleted(true);
                tables.get(reservations.get(id).getTableId()).setReserve(false);
                return true;
            }
        }
        return false;
    }
}
