package com.example.mobilevirtualkeyboard;

import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.app.SearchManager;

public class GetKeyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener
{
    KeyboardView keyboardView;
    Keyboard keyboard;

    private boolean isCaps = false;

    @Override
    public View onCreateInputView()
    {
        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_layout,null);
        keyboard = new Keyboard(this,R.xml.keyboard_key);
        Drawable drawable = getResources().getDrawable(R.drawable.android_background_app_7);
        keyboardView.setBackground(drawable);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;

    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode)
    {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes)
    {
        InputConnection ic = getCurrentInputConnection();
        playClick(primaryCode);

        switch (primaryCode)
        {
            case Keyboard.KEYCODE_DELETE:
                ic.deleteSurroundingText(1,0);
                ic.commitText("",1);
                break;

            case Keyboard.KEYCODE_SHIFT:
                isCaps = !isCaps;
                keyboard.setShifted(isCaps);
                keyboardView.invalidateAllKeys();
                break;

            case Keyboard.KEYCODE_DONE :
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;

            default:
                char code = (char) primaryCode;
                if (Character.isLetter(code) && isCaps)
                    code = Character.toUpperCase(code);
                ic.commitText(String.valueOf(code),1);
        }
    }

    private void playClick(int primaryCode)
    {
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        switch(primaryCode)
        {
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;

            case Keyboard.KEYCODE_DONE:
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;

            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;

            default:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown()
    {
        
    }

    @Override
    public void swipeUp() {

    }
}
