package com.mglf.captcha;

import java.util.Collection;
import java.util.Locale;

import com.mglf.util.CacheUtil;
import com.octo.captcha.Captcha;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.CaptchaStore;

public class BlueCaptchaStore implements CaptchaStore {
	
	public BlueCaptchaStore(){
	}

	@Override
	public void cleanAndShutdown() {
	}

	@Override
	public void empty() {
	}

	@Override
	public Captcha getCaptcha(String id) throws CaptchaServiceException {
		try{
			return (Captcha)CacheUtil.load(CacheUtil.GROUP_CAPTCHA, id);
		}catch(Exception e){
		}
		return null;
	}

	@Override
	public Collection getKeys() {
		return null;
	}

	@Override
	public Locale getLocale(String arg0) throws CaptchaServiceException {
		return null;
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public boolean hasCaptcha(String id) {
		try{
			return CacheUtil.load(CacheUtil.GROUP_CAPTCHA, id) != null;	
		}catch(Exception e){
		}
		return false;
	}

	@Override
	public void initAndStart() {
	}

	@Override
	public boolean removeCaptcha(String id) {
		try{
			CacheUtil.delete(CacheUtil.GROUP_CAPTCHA, id);	
		}catch(Exception e){
		}
		return true;
	}

	@Override
	public void storeCaptcha(String arg0, Captcha arg1)
			throws CaptchaServiceException {
		storeCaptcha(arg0, arg1, null);
	}

	@Override
	public void storeCaptcha(String id, Captcha captcha, Locale arg2)
			throws CaptchaServiceException {
		try{
			CacheUtil.save(CacheUtil.GROUP_CAPTCHA, id, captcha);			
		}catch(Exception e){
			throw new CaptchaServiceException(e);
		}
	}

}
