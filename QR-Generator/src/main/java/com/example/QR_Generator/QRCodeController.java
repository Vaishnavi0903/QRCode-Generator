package com.example.QR_Generator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QRCodeController {

	// at this path QR Code Image will get saved
	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";

	// codeText is just a random text which will act as secret text.
	// this API is used to generate the image form of the QR Code and also provide the path to save the QR code automatically
	// in the local system
	@GetMapping(value = "/generateAndDownloadQRCode/{codeText}/{width}/{height}")
	public void download(@PathVariable("codeText") String codeText, @PathVariable("width") Integer width,
			@PathVariable("height") Integer height) throws Exception {

		QRCode.generateQRCodeImage(codeText, width, height, QR_CODE_IMAGE_PATH);

	}

	
	// it will generate QR Code in the form of byte array , which we used to return as a response for Http Request
	@GetMapping(value = "/generateQRCode/{codeText}/{width}/{height}")
	public ResponseEntity<byte[]>generateQRCode(@PathVariable("codeText") String codeText, @PathVariable("width") Integer width,
			@PathVariable("height") Integer height) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(QRCode.getQRCodeImage(codeText, width, height));
	}

}
