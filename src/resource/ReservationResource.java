package resource;


import bean.ApiResponse;
import bean.ReservationBean;
import db.ReservationService;


public class ReservationResource implements BaseCrudResource<ReservationBean> {
    ReservationService reservationService = new ReservationService();


    @Override
    public ApiResponse add(ReservationBean reservation) {
        ReservationBean newReservation = reservationService.add(reservation);
        return new ApiResponse(newReservation == null ? 400 : 200,newReservation == null ? "you can not reserve" : "successfuly created", newReservation);
    }


    @Override
    public ApiResponse get(Integer id) {
        ReservationBean newReservation = reservationService.get(id);
        return new ApiResponse(newReservation == null ? 400 : 200,newReservation == null ? "not found" : "success",newReservation);
    }

    @Override
    public ApiResponse update(ReservationBean reservation) {
        Boolean isUpdate = reservationService.update(reservation);
        return new ApiResponse(!isUpdate ? 400 : 200,!isUpdate ? "reservation not found" : "updated", isUpdate);
    }

    @Override
    public ApiResponse delete(Integer id) {
        Boolean isDelete = reservationService.delete(id);
        return new ApiResponse(!isDelete ? 400 : 200,!isDelete ? "reservation id not found" : "deleted", isDelete);
    }
}
