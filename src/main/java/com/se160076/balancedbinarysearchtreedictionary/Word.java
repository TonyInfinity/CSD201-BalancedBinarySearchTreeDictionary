/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se160076.balancedbinarysearchtreedictionary;

/**
 *
 * @author Tony
 */
public class Word implements Comparable<Word> {

    private String word;
    private String translation;
    
    public Word(String word) {
        this.word = word;
    }

    public Word(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public int compareTo(Word w) {
        return this.word.compareTo(w.word);
    }

    @Override
    public String toString() {
        return getWord();
    }
}
