package com.project.bookstore.controller;

import com.paypal.api.payments.Links;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.project.bookstore.config.PaypalPaymentIntent;
import com.project.bookstore.config.PaypalPaymentMethod;
import com.project.bookstore.service.PaypalServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tier3")
public class PaymentController {

    public static final String PAYPAL_SUCCESS_URL = "http://localhost:8080/paymentSuccess.html";
    public static final String PAYPAL_CANCEL_URL = "http://localhost:8080/paymentCancel.html";

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PaypalServiceImpl paypalService;

    @PostMapping(value = "/pay/{userId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String pay(@PathVariable int userId) {
        System.out.println(userId);

        double totalPrice = paypalService.computePrice(userId);
        try {
            Payment payment = paypalService.createPayment(
                    totalPrice,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    PAYPAL_CANCEL_URL,
                    PAYPAL_SUCCESS_URL);
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }
}
