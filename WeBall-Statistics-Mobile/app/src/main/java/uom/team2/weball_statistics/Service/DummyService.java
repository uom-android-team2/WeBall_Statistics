package uom.team2.weball_statistics.Service;

import uom.team2.weball_statistics.Model.DummyModel;

public class DummyService {
    public DummyModel getDummyModel(){
        return new DummyModel(123l, "Dummy name", "dummy@dummy.com");
    }
}
