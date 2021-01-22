package com.octolearn;

import org.junit.Test;
import static com.google.common.truth.Truth.assertThat;


public class CreateFlashcardActivityTest {

    @Test
    public void testNotEmptyValues(){
        assertThat(CreateFlashcardActivity.addFlashcard("a dog","pies","I like big dogs.","dog")).isTrue();
    }
    @Test
    public void testEmptyWord(){
        assertThat(CreateFlashcardActivity.addFlashcard("","pies","I like big dogs.","dog")).isFalse();
    }
    @Test
    public void testEmptyTranslation(){
        assertThat(CreateFlashcardActivity.addFlashcard("a dog","","I like big dogs.","dog")).isFalse();
    }
    @Test
    public void testEmptyWordAndTranslation(){
        assertThat(CreateFlashcardActivity.addFlashcard("","","I like big dogs.","dog")).isFalse();
    }
    @Test
    public void testEmptySample(){
        assertThat(CreateFlashcardActivity.addFlashcard("a dog","pies","","dog")).isTrue();
    }
    @Test
    public void testEmptyTranscription(){
        assertThat(CreateFlashcardActivity.addFlashcard("a dog","pies","I like big dogs.","")).isTrue();
    }
    @Test
    public void testAllEmptyValues(){
        assertThat(CreateFlashcardActivity.addFlashcard("","","","")).isFalse();
    }
}
