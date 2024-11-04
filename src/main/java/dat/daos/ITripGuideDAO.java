package dat.daos;

import dat.dtos.TripDTO;

import java.util.List;
import java.util.Set;

public interface ITripGuideDAO {

    void addGuideToTrip(int tripId, int guideId);
    List<TripDTO> getTripsByGuide(int guideId);
}
