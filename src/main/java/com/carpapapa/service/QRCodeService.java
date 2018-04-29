package com.carpapapa.service;

import com.carpapapa.domain.InventoryCheck;
import com.carpapapa.domain.Product;
import com.google.zxing.WriterException;

import java.io.IOException;
import java.util.List;

/**
 * Created by chandler on 4/11/18.
 */
public interface QRCodeService {

    String createQRCodeImage(String vin) throws IOException, WriterException;

    Product checkVIN(String vin);

    List<InventoryCheck> getChecklist();
}
