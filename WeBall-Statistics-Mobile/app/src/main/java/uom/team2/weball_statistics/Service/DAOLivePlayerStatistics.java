package uom.team2.weball_statistics.Service;

import com.google.android.gms.tasks.Task;

import java.util.HashMap;

import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LivePlayerStatistics;

public class DAOLivePlayerStatistics implements DAOCRUDService<LivePlayerStatistics>{
    @Override
    public Task<Void> insert(LivePlayerStatistics data) {
        return null;
    }

    @Override
    public Task<Void> update(HashMap<String, LivePlayerStatistics> data) {
        return null;
    }

    @Override
    public Task<Void> delete(LivePlayerStatistics data) {
        return null;
    }

    @Override
    public Task<Void> get() {
        return null;
    }

    @Override
    public Task<Void> get(LivePlayerStatistics data) {
        return null;
    }
}
