package com.mglf.captcha;

import com.octo.captcha.Captcha;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;

public class BlueManageableImageCaptchaService extends
		DefaultManageableImageCaptchaService {

    public BlueManageableImageCaptchaService() {
        super(new BlueCaptchaStore(), new GMailEngine(), 180,
                100000, 75000);
    }
    
    public void removeCaptcha(String ID){
    	store.removeCaptcha(ID);
    }
    
    public Boolean validateResponseForID(String ID, Object response)
            throws CaptchaServiceException {
    	Captcha captcha = store.getCaptcha(ID);
    	
        if (captcha == null) {
            throw new CaptchaServiceException("Invalid ID, could not validate unexisting or already validated captcha");
        } else {
            Boolean valid = captcha.validateResponse(response);
           // store.removeCaptcha(ID);
            return valid;
        }
    }
    
    
    
}
