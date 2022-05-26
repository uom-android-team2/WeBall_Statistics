package uom.team2.weball_statistics.Service;


import com.google.android.gms.tasks.Task;

import java.util.HashMap;

public interface DAOCRUDService<T> {
      Task<Void> insert(T data);
      Task<Void> update(HashMap<String, T> data);
      Task<Void> delete(T data);
      Task<Void> get();
      Task<Void> get(T data);
}
