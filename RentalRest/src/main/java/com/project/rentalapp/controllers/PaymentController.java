package com.project.rentalapp.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.rentalapp.entities.Payment;
import com.project.rentalapp.service.PaymentService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class PaymentController {

	@Autowired
	PaymentService paymentSvc;

	@DeleteMapping("payment/{id}")
	public void deletePayment(HttpServletRequest req, HttpServletResponse res, @PathVariable int id) {

		try {
			if (paymentSvc.destroyPayment(id)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}

		} catch (Exception e) {

			e.printStackTrace();
			res.setStatus(400);
		}
	}

	@PostMapping("payment")
	public Payment createNewPayment(HttpServletRequest req, HttpServletResponse res, @RequestBody Payment payment,
			Principal principal) {

		try {
			payment = paymentSvc.addPayment(payment);

			if (payment == null) {

				res.setStatus(404);
			}

			else {

				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(payment.getId());
				res.setHeader("Location", url.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Payment Json");
			payment = null;
		}
		return payment;
	}

	@GetMapping("payment/{id}")
	public Payment show(HttpServletResponse res, HttpServletRequest req, @PathVariable int id, Principal principal) {
		Payment pay = paymentSvc.showPayment(id);
		if (pay == null) {
			res.setStatus(404);
		}
		return pay;
	}

	@GetMapping("payment")
	public List<Payment> paymentIndex(HttpServletRequest req, HttpServletResponse res, Principal principal) {

		try {
			List<Payment> pay = paymentSvc.index();

			if (pay == null) {
				res.setStatus(404);
			}
			return pay;

		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}

	@PutMapping("payment/{id}")
	public Payment update(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal,
			@RequestBody Payment payment) {

		payment = paymentSvc.updatePayment(payment, id);

		if (payment == null) {
			res.setStatus(400);
		} else {
			res.setStatus(201);
		}
		return payment;
	}

}
