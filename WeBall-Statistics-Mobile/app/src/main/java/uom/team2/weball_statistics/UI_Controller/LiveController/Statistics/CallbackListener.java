package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;
/*
 * @author Leonard Pepa ics20033
 */

// provides method that get called after the request if finished and provides you with the returned object
public interface CallbackListener<T> {
    void callback(T returnedObject);
}
