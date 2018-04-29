package com.carpapapa.service;

import com.carpapapa.dao.InventoryChecklistDAO;
import com.carpapapa.dao.ProductDAO;
import com.carpapapa.domain.InventoryCheck;
import com.carpapapa.domain.Product;
import com.carpapapa.domain.Products;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by chandler on 4/11/18.
 */
@Service
public class DefaultQRCodeService implements QRCodeService {

    @Value("${qr.url.check}")
    private String URL;

    @Value("${qr.image.path}")
    private String QR_CODE_IMAGE_PATH;

    @Autowired
    private InventoryChecklistDAO inventoryChecklistDAO;

    @Autowired
    private ProductDAO productDAO;

    @Override
    public String createQRCodeImage(String vin) throws IOException, WriterException {
        return generateQRCodeImage(vin, 350, 350, QR_CODE_IMAGE_PATH);
    }

    @Override
    public Product checkVIN(String vin) {
        inventoryChecklistDAO.checkVIN(vin);

        Products products = productDAO.searchProducts(0, 0, null, null, null, null, null, null, vin);
        if (products.getProducts() != null && products.getProducts().size() > 0) {
            return products.getProducts().get(0);
        }

        return null;
    }

    @Override
    public List<InventoryCheck> getChecklist() {
        return inventoryChecklistDAO.getChecklist();
    }

    private String generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(this.URL + text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath + text + ".png");
        MatrixToImageWriter.writeToPath(bitMatrix, "png", path);

        return "/images/qr/" + text + ".png";
    }
}
