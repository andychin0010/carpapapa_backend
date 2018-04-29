package com.carpapapa.dao;

import com.carpapapa.domain.InventoryCheck;

import java.util.List;

/**
 * Created by chandler on 4/11/18.
 */
public interface InventoryChecklistDAO {

    void checkVIN(String vin);

    List<InventoryCheck> getChecklist();
}
