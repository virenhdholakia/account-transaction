package com.coding.challenge.transactions.beans;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CustomerTest {

    private Random random;

    @Before
    public void setUp() {
        random = new Random();
    }

    @After
    public void cleanUp() {
        random = null;
    }

    @Test
    public void getterTest() {

        Customer customer = mock(Customer.class);

        String customerId = Integer.toString(random.nextInt());
        when(customer.getCustomerId()).thenReturn(customerId);
        assertEquals(customer.getCustomerId(), customerId);

        String customerName = "Test Name";
        when(customer.getCustomerName()).thenReturn(customerName);
        assertEquals(customer.getCustomerName(), customerName);

    }

    @Test
    public void setterTest() {

        Customer customer = new Customer();

        String customerId = Integer.toString(random.nextInt());
        customer.setCustomerId(customerId);
        assertEquals(customer.getCustomerId(), customerId);

        String customerName = "Test Name";
        customer.setCustomerName(customerName);
        assertEquals(customer.getCustomerName(), customerName);

    }
}
