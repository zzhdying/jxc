package com.mglf.captcha;

import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.CaptchaQuestionHelper;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.image.ImageCaptcha;
import com.octo.captcha.image.ImageCaptchaFactory;
import com.octo.captcha.image.gimpy.Gimpy;

public class BlueGimpyFactory extends ImageCaptchaFactory {

    private Random myRandom = new SecureRandom();
    private WordToImage wordToImage;
    private WordGenerator wordGenerator;

    public static final String BUNDLE_QUESTION_KEY = Gimpy.class.getName();

    public BlueGimpyFactory(WordGenerator generator, WordToImage word2image) {
        if (word2image == null) {
            throw new CaptchaException("Invalid configuration" +
                    " for a GimpyFactory : WordToImage can't be null");
        }
        if (generator == null) {
            throw new CaptchaException("Invalid configuration" +
                    " for a GimpyFactory : WordGenerator can't be null");
        }
        wordToImage = word2image;
        wordGenerator = generator;

    }

    /**
     * gimpies are ImageCaptcha
     *
     * @return the image captcha with default locale
     */
    public ImageCaptcha getImageCaptcha() {
        return getImageCaptcha(Locale.getDefault());
    }

    public WordToImage getWordToImage() {
        return wordToImage;
    }

    public WordGenerator getWordGenerator() {
        return wordGenerator;
    }

    /**
     * gimpies are ImageCaptcha
     *
     * @return a pixCaptcha with the question :"spell the word"
     */
    public ImageCaptcha getImageCaptcha(Locale locale) {

        //length
        Integer wordLength = getRandomLength();

        String word = getWordGenerator().getWord(wordLength, locale);

        BufferedImage image = null;
        try {
            image = getWordToImage().getImage(word);
        } catch (Throwable e) {
            throw new CaptchaException(e);
        }

        ImageCaptcha captcha = new BlueImageCaptcha(CaptchaQuestionHelper.getQuestion(locale, BUNDLE_QUESTION_KEY),
                image, word);
        return captcha;
    }

    protected Integer getRandomLength() {
        Integer wordLength;
        int range = getWordToImage().getMaxAcceptedWordLength() -
                getWordToImage().getMinAcceptedWordLength();
        int randomRange = range != 0 ? myRandom.nextInt(range + 1) : 0;
        wordLength = new Integer(randomRange +
                getWordToImage().getMinAcceptedWordLength());
        return wordLength;
    }

}
