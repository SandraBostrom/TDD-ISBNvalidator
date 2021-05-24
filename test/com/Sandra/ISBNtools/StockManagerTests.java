package com.Sandra.ISBNtools;

import com.Sandra.ISBNtools.ISBNtools.Book;
import com.Sandra.ISBNtools.ISBNtools.ExternalISBNDataService;
import com.Sandra.ISBNtools.ISBNtools.StockManager;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;

public class StockManagerTests {

    ExternalISBNDataService testWebService;
    ExternalISBNDataService testDatabaseService;
    StockManager stockManager;

    @Before
    public void setup(){
        testWebService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        testDatabaseService =  mock(ExternalISBNDataService.class);



    }

    @Test
    public void testCanGetACorrectLocatorCode(){

        when(testWebService.lookup(anyString())).thenReturn(new Book("0140177396", "Of Mice And Men", "J. Steinbeck"));
        when(testDatabaseService.lookup(anyString())).thenReturn(null);

        stockManager.setDatabaseService(testDatabaseService);

        String isbn="0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);

    }

    @Test
    public void databaseIsUsedIfDataIsPresent(){

        when(testDatabaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc","abc"));

        String isbn="0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(testDatabaseService).lookup("0140177396");
        verify(testWebService,never()).lookup(anyString());
    }

    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase(){

        when(testDatabaseService.lookup("0140177396")).thenReturn(null);
        when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc","abc"));

        String isbn="0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(testDatabaseService).lookup("0140177396");
        verify(testWebService).lookup(("0140177396"));
    }
}
