package com.carpapapa.controller;

import com.carpapapa.domain.InventoryCheck;
import com.carpapapa.domain.Product;
import com.carpapapa.service.QRCodeService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chandler on 4/11/18.
 */

@RestController
@RequestMapping("/qr")
public class QRCodeController {

    private String HTML = "<html>\n" +
            "<body>\n" +
            "    <div style=\"background-color:#000; color:#81d8d0; text-align:center; padding:30px; font-weight:bold; font-size:3em; font-family: Sans-Serif;\">CARPAPAPA AUTO GROUP</div>\n" +
            "    <div style=\"font-family: Sans-Serif; padding: 30px 0 0 30px\">\n" +
            "        <div style=\"font-weight:bold; font-size:2em; padding-bottom:30px\">%s %s %s</div>\n" +
            "        <div style=\"padding-bottom:10px\">Vin: %s</div>\n" +
            "        <div style=\"padding-bottom:10px\">Mileage: %s</div>\n" +
            "        <div style=\"padding-bottom:10px\">Drivetrain: %s</div>\n" +
            "        <div style=\"padding-bottom:10px\">Exterior Color: %s</div>\n" +
            "        <div style=\"padding-bottom:10px\">Interior Color: %s</div>\n" +
            "        <div style=\"padding-bottom:10px\">Horsepower: %s</div>\n" +
            "        <div style=\"padding-bottom:10px\">Torque: %s</div>\n" +
            "        <div style=\"padding-bottom:10px\">Engine: %s</div>\n" +
            "        <div style=\"padding-bottom:10px\">MPG City: %s</div>\n" +
            "        <div style=\"padding-bottom:10px\">MPG Highway: %s</div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>";

    @Autowired
    private QRCodeService qrCodeService;

    @CrossOrigin
    @RequestMapping(value = "/{vin}/create", method = RequestMethod.POST)
    ResponseEntity<?> createQRCode(@PathVariable String vin) throws IOException, WriterException {
        Map retval = new HashMap<>();
        retval.put("url", qrCodeService.createQRCodeImage(vin));

        return new ResponseEntity<>(retval, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/check/{vin}", method = RequestMethod.GET)
    ResponseEntity<?> checkVIN (@PathVariable String vin) {
        Product product = qrCodeService.checkVIN(vin);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String responseHtml = String.format(HTML,
                                            product.getYear(),
                                            product.getMake(),
                                            product.getModel(),
                                            product.getVin(),
                                            product.getMileage(),
                                            product.getDrivetrain(),
                                            product.getExColor(),
                                            product.getInColor(),
                                            product.getHorsePower(),
                                            product.getTorque(),
                                            product.getEngine(),
                                            product.getMpgCity(),
                                            product.getMpgHighway());

        return new ResponseEntity<>(responseHtml, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/checklist", method = RequestMethod.GET)
    ResponseEntity<?> getChecklist() {
        List<InventoryCheck> checkList = qrCodeService.getChecklist();

        if (checkList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(checkList, HttpStatus.OK);
    }
}
