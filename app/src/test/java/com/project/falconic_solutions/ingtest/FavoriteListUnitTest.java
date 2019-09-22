package com.project.falconic_solutions.ingtest;

import com.project.falconic_solutions.ingtest.backend.repository.Helper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FavoriteListUnitTest {

    @Test
    public void addItemToList() {
        Helper.favoriteList.add(1L);
        assertTrue(Helper.favoriteList.contains(1L));
    }

    @Test
    public void addItemsToList(){
        Helper.favoriteList.add(3L);
        Helper.favoriteList.add(4L);
        Helper.favoriteList.add(5L);
        assertEquals(3, Helper.favoriteList.size());
    }

    @Test
    public void RemoveItemFromList(){
        Helper.favoriteList.add(2L);
        if(Helper.favoriteList.contains(2L)){
            Helper.favoriteList.remove(2L);
        }
        assertFalse(Helper.favoriteList.contains(2L));
    }

}
