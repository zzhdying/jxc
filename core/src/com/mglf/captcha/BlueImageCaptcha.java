package com.mglf.captcha;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import com.octo.captcha.image.ImageCaptcha;

public class BlueImageCaptcha extends ImageCaptcha implements Serializable{

	private String response;

	public BlueImageCaptcha(String question, BufferedImage challenge, String response) {
        super(question, challenge);
        this.response = response;
    }

    /**
     * Validation routine from the CAPTCHA interface. this methods verify if the response is not null and a String and
     * then compares the given response to the internal string.
     *
     * @return true if the given response equals the internal response, false otherwise.
     */
    public final Boolean validateResponse(final Object response) {
        return (null != response && response instanceof String)
                ? validateResponse((String) response) : Boolean.FALSE;
    }

    /**
     * Very simple validation routine that compares the given response to the internal string.
     *
     * @return true if the given response equals the internal response, false otherwise.
     */
    private final Boolean validateResponse(final String response) {
        return Boolean.valueOf(response.toLowerCase().equals(this.response.toLowerCase()));
    }

}
