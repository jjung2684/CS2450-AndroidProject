package com.android.cs2450_androidproject;

import static java.sql.Types.NULL;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import java.io.Serializable;

public class CardButton extends androidx.appcompat.widget.AppCompatImageButton implements Serializable {
    private int cardNum;
    private boolean isFlipped = false;
    int cardDrawable;
    private boolean isMatched = false;

    public CardButton(Context context) {
        super(context);

    }

    public CardButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public void setResource(){
        if(cardDrawable != NULL)
            this.setImageResource(cardDrawable);
    }

    public void setMatched(boolean matched){
        isMatched = matched;
    }

    public boolean getIsMatched(){
        return isMatched;
    }

    public void setCardDrawable(int d){
        cardDrawable = d;
    }

    public int getCardDrawable(){
        return cardDrawable;
    }

    public void setFlipped(boolean val){
        isFlipped = val;
    }

    public boolean isFlipped(){
        return isFlipped;
    }

    public void setCardNum(int num){
        cardNum = num;
    }

    public int getCardNum(){
        return cardNum;
    }
}
