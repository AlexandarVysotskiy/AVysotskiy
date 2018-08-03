package ru.job4j.anagram;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class AnagramTest {
    Anagram an = new Anagram();

    @Test
    public void whenAnagramCheckIsTrue() {
        assertThat(an.checkAnagram("вася", "сява"), is(true));
        assertThat(an.checkAnagram("mama", "amam"), is(true));
    }

    @Test
    public void whenAnagramCheckIsFalse() {
        assertThat(an.checkAnagram("112", "122"), is(false));
    }
}